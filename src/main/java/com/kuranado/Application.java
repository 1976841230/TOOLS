package com.kuranado;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by JING on 2018/3/8.
 */
@RestController
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.kuranado.*"})
public class Application extends SpringBootServletInitializer {

    @RequestMapping("/hello")
    public String home() {
        return "Hello World!";
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 注意这里要指向原先用main方法执行的Application启动类
        return builder.sources(Application.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(com.kuranado.Application.class, args);
    }
}