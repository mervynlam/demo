package com.mervyn.nacos.service1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Service1Bootstrap {
    public static void main(String[] args) {
        SpringApplication.run(Service1Bootstrap.class, args);
    }

    //通过value注解读取配置信息，但无法动态更新配置的修改
    @Value("${common.name}")
    private String config1;

    //配置文件上下文，实现动态获取配置更新
    @Autowired
    private ConfigurableApplicationContext applicationContext;

    @GetMapping("/configs")
    public String getConfigs() {
        //读取配置信息
//        return config1;
        return applicationContext.getEnvironment().getProperty("common.name");
    }

    @GetMapping("/configs2")
    public String getConfigs2() {
        String name = applicationContext.getEnvironment().getProperty("common.name");
        String age = applicationContext.getEnvironment().getProperty("common.age");
        String address = applicationContext.getEnvironment().getProperty("common.address");
        String birthday = applicationContext.getEnvironment().getProperty("common.birthday");
        String fullname = applicationContext.getEnvironment().getProperty("common.fullname");
        return name + "-" + age + "-" + address + "-" + birthday + "-" + fullname;
    }
}
