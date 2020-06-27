package com.cn.cloud.ofo.ofogateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class}) //禁止使用父pom文件中的数据源配置
@EnableEurekaClient
@EnableFeignClients(basePackages = {"com.cn.cloud.ofo.ofogateway.services"})
public class OfoGatewayApplication {

    public static void main(String[] args) {

        SpringApplication.run(OfoGatewayApplication.class, args);
    }

}
