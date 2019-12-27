package com.study.web.auth.jwt.util;

import cn.t.util.common.JsonUtil;
import cn.t.util.common.RandomUtil;
import com.study.web.auth.jwt.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.util.Date;

/**
 * jwt工具
 *
 * @author yj
 * @since 2019-12-03 16:46
 **/
public class JwtUtil {

    private static final Integer EXPIRE_IN_MILLS = 1000 * 60 * 60 * 2;
    private static final String ISSUER = "test-app";
    private static String KEY = "1234567890";

    public static String createJwt(String id, String subject) {
        JwtBuilder jwtBuilder = Jwts.builder().setId(id)
            .signWith(SignatureAlgorithm.HS256, new SecretKeySpec(DatatypeConverter.parseBase64Binary(KEY), SignatureAlgorithm.HS256.getJcaName()))
            .setIssuer(ISSUER)
            .setSubject(subject)
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_IN_MILLS));
        return jwtBuilder.compact();
    }

    public static Claims parseJwt(String token) {
        return Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(KEY)).parseClaimsJws(token).getBody();
    }

    public static void main(String[] args) throws Exception {
        User user = new User();
        user.setId(1L);
        user.setUsername("amen");

        String json = JsonUtil.serialize(user);
        String jwt = createJwt(json, RandomUtil.randomString(32));
        System.out.println("jet: " + jwt);

        Claims claims = parseJwt(jwt);
        System.out.println("claims: " + claims);
    }

}
