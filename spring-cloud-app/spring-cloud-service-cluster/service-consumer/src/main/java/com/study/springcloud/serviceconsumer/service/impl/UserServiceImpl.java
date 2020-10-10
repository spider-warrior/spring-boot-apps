package com.study.springcloud.serviceconsumer.service.impl;

import cn.t.util.common.RandomUtil;
import com.jy.study.springcloud.serviceremote.User;
import com.jy.study.springcloud.serviceremote.UserServiceApi;
import com.study.springcloud.serviceconsumer.service.UserService;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceImpl implements UserService {

    private final UserServiceApi userServiceApi;
    private final RestTemplate restTemplate;
    private final LoadBalancerClient loadBalancerClient;

    @Override
    public User getUserById(Long id) {
        ServiceInstance serviceInstance = loadBalancerClient.choose("user-service");
        ResponseEntity<User> responseEntity = restTemplate.getForEntity("http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/user/{1}{2}", User.class, id, RandomUtil.randomInt(10,99));
        return responseEntity.getBody();
    }

    @Override
    public User getUserByIdWithFeign(Long id) {
        return userServiceApi.getUserById(id);
    }

    @Override
    public User getUserByUsername(String username) {
        User user = new User();
        user.setPassword(username);
        user.setPassword("123456");
        return user;
    }

    public UserServiceImpl(UserServiceApi userServiceApi, RestTemplate restTemplate, LoadBalancerClient loadBalancerClient) {
        this.userServiceApi = userServiceApi;
        this.restTemplate = restTemplate;
        this.loadBalancerClient = loadBalancerClient;
    }
}
