package com.mervyn.nacos.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
//开启feign远程链接
@EnableFeignClients
//是一个注册发现客户端
@EnableDiscoveryClient
public class NacosProviderApp {

    public static void main(String[] args) {
        SpringApplication.run(NacosProviderApp.class, args);
    }

}
