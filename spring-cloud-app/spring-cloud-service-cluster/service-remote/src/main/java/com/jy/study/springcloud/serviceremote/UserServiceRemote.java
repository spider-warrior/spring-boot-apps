package com.jy.study.springcloud.serviceremote;

import com.jy.study.springcloud.serviceremote.domain.User;

public interface UserServiceRemote {
    User getById(Long id);
}
