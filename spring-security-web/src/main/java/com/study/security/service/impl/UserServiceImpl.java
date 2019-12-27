package com.study.security.service.impl;

import com.study.security.dao.BaseMapper;
import com.study.security.dao.UserMapper;
import com.study.security.entity.User;
import com.study.security.entity.UserExample;
import com.study.security.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Transactional(rollbackFor = Exception.class)
@Service
public class UserServiceImpl extends BaseServiceImpl<User, UserExample, Long, UserMapper> implements UserService, UserDetailsService {

    @Override
    public User queryByUsernameAndPassword(String username, String password) {
        return getDao().selectUserByUsernameAndPassword(username, password);
    }

    public UserServiceImpl(BaseMapper<User, UserExample, Long> baseMapper) {
        super(baseMapper);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getDao().selectUserByUsername(username);
        if(user == null) {
            return null;
        } else {
            return new org.springframework.security.core.userdetails.User(username, user.getPassword(), Collections.emptyList());
        }
    }
}
