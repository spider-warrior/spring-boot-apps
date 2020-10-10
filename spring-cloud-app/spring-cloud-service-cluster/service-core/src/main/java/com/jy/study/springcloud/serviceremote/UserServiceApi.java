package com.jy.study.springcloud.serviceremote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = "/user")
@FeignClient(value = "${application.feign.service.user:}")
public interface UserServiceApi {

    @GetMapping("{id}")
        //feign使用下面注解时会把id参数放在body体里
//    @GetMapping("{id:\\d+}")
    User getUserById(@PathVariable("id") Long id);

}
