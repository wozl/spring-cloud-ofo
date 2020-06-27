package com.cn.cloud.ofo.ofohystrixdashboardturbine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class}) //禁止使用父pom文件中的数据源配置
@EnableHystrixDashboard //开启仪表盘服务
@EnableTurbine //激活对Turbine的支持，可同时显示多个实例的健康状态
public class OfoHystrixDashboardTurbineApplication {

    public static void main(String[] args) {
        SpringApplication.run(OfoHystrixDashboardTurbineApplication.class, args);
    }

}
