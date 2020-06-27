package com.cn.cloud.ofo.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class}) //禁止使用父pom文件中的数据源配置
@EnableEurekaClient
@EnableFeignClients(basePackages = {"com.cn.cloud.ofo.feign.services"})
@EnableCircuitBreaker // 启动断路器，如果要监控hystrix的流必须开启此注解，即使fegin已经通过属性
public class FeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeignApplication.class, args);
    }

}
