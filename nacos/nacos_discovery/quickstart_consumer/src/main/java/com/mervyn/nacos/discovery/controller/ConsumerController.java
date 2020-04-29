package com.mervyn.nacos.discovery.controller;

import com.mervyn.nacos.discovery.client.ProviderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {

    @Autowired
    private ProviderClient providerClient;

    @GetMapping("service")
    public String service() {
        String providerResult = providerClient.service();
        return "consumer invoke | " + providerResult;
    }

}
