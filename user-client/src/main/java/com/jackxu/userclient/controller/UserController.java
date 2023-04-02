package com.jackxu.userclient.controller;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    //自动装配
    @Resource
    private DiscoveryClient discoveryClient;

    // 服务发现
    @GetMapping("/instances")
    public List<ServiceInstance> instances() {
        // 根据order名称获取到urls
        return this.discoveryClient.getInstances("coupon");
    }

}