package com.study.web.auth.jwt.util;


import cn.t.util.common.JsonUtil;
import cn.t.util.common.RandomUtil;
import cn.t.util.common.StringUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.study.web.auth.jwt.entity.User;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.*;

@Slf4j
public class AppContextUtil {

    private static final ThreadLocal<User> REQUEST_THREAD_USER = new ThreadLocal<>();
    //userId => [jwt, jwt, jwt]
    private static final Map<Long, Set<String>> USER_JWT = new HashMap<>();
    // id => jwt
    private static final Map<String, String> CACHED_JWT = new HashMap<>();

    /**
     * 设置当前线程User
     */
    public static void setCurrentUser(User user) {
        if (user == null) {
            return;
        }
        REQUEST_THREAD_USER.set(user);
    }

    /**
     * 获取当前线程User
     */
    public static User getCurrentUser() {
        return REQUEST_THREAD_USER.get();
    }

    /**
     * 去除当前线程User
     */
    public static void removeCurrentUser() {
        REQUEST_THREAD_USER.remove();
    }


    public static void initRequestContext(User user, Date now) {
        setCurrentUser(user);
    }

    public static void clearRequestContext() {
        removeCurrentUser();
    }

    /**
     * 生成jwt
     */
    public static String generateJwt(User user) throws JsonProcessingException {
        String id = RandomUtil.randomString(32);
        String jwt = JwtUtil.createJwt(id, JsonUtil.serialize(user));
        log.info("记录用户登陆状态, username: {}, id: {}, jwt: {}", user.getUsername(), id, jwt);
        CACHED_JWT.put(id, jwt);
        Set<String> jwtSet = USER_JWT.get(user.getId());
        if(jwtSet == null) {
            jwtSet = new HashSet<>();
            USER_JWT.put(user.getId(), jwtSet);
        }
        jwtSet.add(id);
        return jwt;
    }

    /**
     * jwt失效
     * */
    public static void invalidJwt(Long userId) {
        if (userId != null) {
            Set<String> jwtSet = USER_JWT.get(userId);
            if(jwtSet != null) {
                for(String id: jwtSet) {
                    String jwt = CACHED_JWT.remove(id);
                    log.info("remove jwt, id: {}, jwt: {}", id, jwt);
                }
                log.info("清理用户登陆状态, userId: {}, jwtSet: {}", userId, jwtSet);
            }
            USER_JWT.remove(userId);
        }
    }

    public static User getUserByJwt(String jwt) {
        if(StringUtil.isEmpty(jwt)) {
            return null;
        }
        Claims claims = JwtUtil.parseJwt(jwt);
        String id = claims.getId();
        if(!CACHED_JWT.containsKey(id)) {
            return null;
        }
        String subject = claims.getSubject();
        try {
            return JsonUtil.deserialize(subject, User.class);
        } catch (IOException e) {
            log.error("反序列化用户失败", e);
            return null;
        }
    }

}
