package com.jackxu.controller;


import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class RegisterController {

    public static void main(String[] args) throws NacosException, IOException {
        Properties properties = new Properties();
        properties.setProperty("serverAddr","127.0.0.1:8848");  // 集群地址使用","隔开
        NamingService naming = NamingFactory.createNamingService(properties);

        //注册1
        naming.registerInstance("nacos.test.3", "11.11.11.11", 8888, "TEST1");

        Instance instance = new Instance();
        instance.setIp("55.55.55.55");
        instance.setPort(9999);
        instance.setHealthy(false);
        instance.setWeight(2.0);
        Map<String, String> instanceMeta = new HashMap<>();
        instanceMeta.put("site", "et2");
        instance.setMetadata(instanceMeta);
        //注册2
        naming.registerInstance("nacos.test.4", instance);

        System.out.println("-----服务发现1-----");
        System.out.println(naming.getAllInstances("nacos.test.3"));
        System.out.println("-----服务发现2-----");
        System.out.println(naming.getAllInstances("nacos.test.4"));

        // 只要这个进程不停止，就意味着nacos client会不断地向nacos server发送心跳  时间间隔
        System.in.read();
    }

}
