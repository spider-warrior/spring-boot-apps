package com.jy.study.dataaccess.h2.service;

import com.jy.study.dataaccess.h2.domain.User;

public interface UserService {
    Long create(User user);
    User queryById(Long id);
}
