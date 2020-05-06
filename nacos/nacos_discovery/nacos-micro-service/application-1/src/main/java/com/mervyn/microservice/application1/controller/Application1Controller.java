package com.mervyn.microservice.application1.controller;

import com.mervyn.microservice.service1.api.ConsumerService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Application1Controller {

    @org.apache.dubbo.config.annotation.Reference
    private ConsumerService consumerService;

    @GetMapping("/service")
    public String service() {
        String service = consumerService.service();
        return "test | "+service;
    }

}
