package com.mervyn.microservice.service2;

import com.mervyn.microservice.service2.api.ProviderService;
import org.apache.dubbo.config.annotation.Service;

@org.apache.dubbo.config.annotation.Service
public class ProviderServiceImpl implements ProviderService {

    public String service() {
        return "provider invoke";
    }
}
