package com.rabbitmq.demo;

import cn.hutool.cron.CronUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        CronUtil.setMatchSecond(true);
        CronUtil.start();
    }
}
