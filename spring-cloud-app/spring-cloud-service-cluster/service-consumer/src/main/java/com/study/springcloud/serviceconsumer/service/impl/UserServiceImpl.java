package com.study.springcloud.serviceconsumer.service.impl;

import com.jy.study.springcloud.serviceremote.User;
import com.study.springcloud.serviceconsumer.service.UserService;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceImpl implements UserService {

    private final RestTemplate restTemplate;
    private final LoadBalancerClient loadBalancerClient;

    @Override
    public User getUserById() {
        ServiceInstance serviceInstance = loadBalancerClient.choose("service-provider");
        StringBuilder builder = new StringBuilder();
        builder.append("http://").append(serviceInstance.getHost()).append(":").append(serviceInstance.getPort()).append("/user/1");
        RestTemplate template = new RestTemplate();
        ResponseEntity<User> responseEntity = template.getForEntity(builder.toString(), User.class);
        return responseEntity.getBody();
    }

    public UserServiceImpl(RestTemplate restTemplate, LoadBalancerClient loadBalancerClient) {
        this.restTemplate = restTemplate;
        this.loadBalancerClient = loadBalancerClient;
    }
}
