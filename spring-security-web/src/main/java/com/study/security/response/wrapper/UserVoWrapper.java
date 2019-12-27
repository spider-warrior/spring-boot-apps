package com.study.security.response.wrapper;

import com.study.security.entity.User;
import com.study.security.response.vo.UserVo;
import org.springframework.stereotype.Component;

@Component
public class UserVoWrapper {

    public UserVo buildUserVo(User user) {
        UserVo userVo = new UserVo();
        return userVo;
    }
}
