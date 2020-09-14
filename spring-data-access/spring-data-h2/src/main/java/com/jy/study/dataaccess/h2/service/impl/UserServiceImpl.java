package com.jy.study.dataaccess.h2.service.impl;

import com.jy.study.dataaccess.h2.domain.User;
import com.jy.study.dataaccess.h2.repository.UserRepository;
import com.jy.study.dataaccess.h2.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Long create(User user) {
        return userRepository.save(user).getId();
    }

    @Override
    public User queryById(Long id) {
        return userRepository.getOne(id);
    }

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
