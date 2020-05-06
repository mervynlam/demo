package com.mervyn.microservice.service1.service;

import com.mervyn.microservice.service1.api.ConsumerService;
import com.mervyn.microservice.service2.api.ProviderService;

@org.apache.dubbo.config.annotation.Service
public class ConsumerServiceImpl implements ConsumerService {

    @org.apache.dubbo.config.annotation.Reference
    private ProviderService providerService;

    public String service() {
        String service = providerService.service();
        return "Consumer invoke | " + service;
    }
}
