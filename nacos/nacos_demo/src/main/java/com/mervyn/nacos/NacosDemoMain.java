package com.mervyn.nacos;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;

import java.util.Properties;
import java.util.concurrent.Executor;

public class NacosDemoMain {
    public static void main(String[] args) throws NacosException {
        //nacos url
        String serverAddr = "127.0.0.1:8848";
        //DataId
        String dataId = "db.properties";
        //group
        String group = "DEFAULT_GROUP";
        //namespace
        String namespace = "bfb75518-0f3d-47b1-b520-a27d21206013";
        //属性
        Properties properties = new Properties();
        properties.put("serverAddr", serverAddr);
        //指定命名空间，若不指定则默认public
//        properties.put("namespace", namespace);
        //获取配置
        ConfigService configService = NacosFactory.createConfigService(properties);
        String config = configService.getConfig(dataId, group, 5000);
        System.out.println(config);

        //监听
        configService.addListener(dataId, group, new Listener() {
            public Executor getExecutor() {
                return null;
            }

            public void receiveConfigInfo(String s) {
                System.out.println(s);
            }
        });

        while(true) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
