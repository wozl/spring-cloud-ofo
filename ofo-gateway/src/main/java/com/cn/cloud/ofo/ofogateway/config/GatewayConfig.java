package com.cn.cloud.ofo.ofogateway.config;


import com.cn.cloud.ofo.ofogateway.filter.LoginFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.cn.cloud.ofo.ofogateway.filter.MyFilter;
import org.springframework.context.annotation.Primary;

/**
 * gateway网关配置
 */

@Configuration
@Slf4j
public class GatewayConfig {



    @Bean
    public LoginFilter loginFilter(){ //检查是否已登录
        return new LoginFilter();
    }

    @Bean
    public MyFilter myFilter(){ //检查是否具有管理员权限
        return new MyFilter();
    }

    @Bean
    public RouteLocator myRoute(RouteLocatorBuilder builder){
        return builder.routes()
                //添加相关路由
                .route(r ->r/*.readBody(Object.class,requestBody  -> {
                            log.warn("requestBody is{},缓存requestBody,不进行任何处理",requestBody );
                            return  true;
                        })
                        .and()*/
                        .path("/admin/**")
                        .filters(f -> f
                                .filters(loginFilter(),myFilter())//引入自定义过滤器规则,这里做了一个登录验证和是否具有管理员权限验证
                        )
                        .uri("lb://ofo-feign")
                        .id("ofo-admin-path")

                )
                .route(r -> r
                        .path("/ofo/**")
                        .filters(f -> f.filters(loginFilter()))//引入自定义过滤器规则，这里仅做登录验证，未登录将无法访问
                        .uri("lb://ofo-feign")
                        .id("ofo-user-path")
                )
                .route(r -> r
                        .path("/User/**")
                        .uri("lb://ofo-feign")
                        .id("ofo-not-auth-path")
                )
                .build();
    }


}
