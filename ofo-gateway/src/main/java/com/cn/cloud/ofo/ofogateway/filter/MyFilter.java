package com.cn.cloud.ofo.ofogateway.filter;

import com.alibaba.fastjson.JSONObject;
import com.cn.cloud.ofo.ofogateway.services.AuthAPIServices;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Slf4j
public class MyFilter implements GatewayFilter, Ordered {

    //private static Logger logger = LoggerFactory.getLogger(MyFilter.class);

    @Autowired
    @Lazy //解决循环依赖，延迟加载
    private AuthAPIServices services;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("=====请求进入相关服务前，拦截核实用户身份权限信息=====");
        JSONObject json = new JSONObject();
        if(exchange.getRequest().getHeaders().getFirst("auth")==null){ //检查是否为空
            log.error("非法请求");
            json.put("code",1021);
            json.put("msg","请求非法!");
            byte[] bytes= json.toJSONString().getBytes(StandardCharsets.UTF_8);
            DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            //设置编码，防止乱码
            exchange.getResponse().getHeaders().add("Content-Type","text/plan;charset=UTF-8");
            return exchange.getResponse().writeWith(Mono.just(buffer));
        }else{ //这里是在一级过滤器验证是登录状态后，再验证此用户是否有管理员api的访问权限
            String token=exchange.getRequest().getHeaders().getFirst("auth");
            JSONObject js= JSONObject.parseObject(services.isAdmin(token));
            if(js.getInteger("code")==1){ //验证通过放行
                return chain.filter(exchange);
            }else{ //否则显示相关信息
                log.warn("=====已拦截无管理员权限用户访问=====");
                byte[] bytes= services.isAdmin(token).getBytes(StandardCharsets.UTF_8);
                DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                //设置编码，防止乱码
                exchange.getResponse().getHeaders().add("Content-Type","text/plan;charset=UTF-8");
                return exchange.getResponse().writeWith(Mono.just(buffer));
            }
        }

    }

    @Override
    public int getOrder() {
        return 11;
    }
}
