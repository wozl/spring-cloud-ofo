package com.cn.cloud.ofo.ofogateway.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cn.cloud.ofo.ofogateway.services.AuthAPIServices;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Slf4j
public class LoginFilter implements GatewayFilter, Ordered {

    @Autowired
    private AuthAPIServices apiServices;


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("====进入第一层过滤器。验证用户是否是登录后请求api的=====");
        JSONObject json = new JSONObject();
        if(exchange.getRequest().getHeaders().getFirst("auth")==null){ //登录验证
            log.error("======非法登录请求======");
            json.put("code",1021);
            json.put("msg","请求非法!");
            byte[] bytes= json.toJSONString().getBytes(StandardCharsets.UTF_8);
            DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            //设置编码，防止乱码
            exchange.getResponse().getHeaders().add("Content-Type","text/plan;charset=UTF-8");
            return exchange.getResponse().writeWith(Mono.just(buffer));
        }else{ //对用户的登录状态进行校验，防止非法请求
            String token=exchange.getRequest().getHeaders().getFirst("auth");
            log.warn(token);
            log.warn(apiServices.CheckLog(token));
           JSONObject jsb= JSON.parseObject(apiServices.CheckLog(token));
            if(jsb.getInteger("code")==1){ //登录状态有效
                return chain.filter(exchange);
            }else{ //无效的登录状态
                log.warn("========登录状态已失效=========");
                byte[] bytes= apiServices.CheckLog(token).getBytes(StandardCharsets.UTF_8);
                DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                //设置编码，防止乱码
                exchange.getResponse().getHeaders().add("Content-Type","text/plan;charset=UTF-8");
                return exchange.getResponse().writeWith(Mono.just(buffer));
            }
        }
        /*Object requestBody=exchange.getAttribute("cachedRequestBodyObject");
        requestBody=JSON.toJSON(requestBody); //转换成json对象
        JSONObject json = new JSONObject();
        JSONObject js=JSON.parseObject(requestBody.toString());
        //log.warn(js.getString("auth"));
        String UserAuth=js.getString("auth");
        if(UserAuth==null || UserAuth.isEmpty()){
            log.error("非法请求");
            json.put("code",1021);
            json.put("msg","请求非法!");
            byte[] bytes= json.toJSONString().getBytes(StandardCharsets.UTF_8);
            DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            //设置编码，防止乱码
            exchange.getResponse().getHeaders().add("Content-Type","text/plan;charset=UTF-8");
            return exchange.getResponse().writeWith(Mono.just(buffer));
        }else { //验证是否登录，若未登录，就无法继续访问，需要重新进行登录
            log.warn(UserAuth);
            //log.warn(apiServices.CheckLog("8600d4ccbee943a5b0e97804dddf44f8"));
            JSONObject jsb= JSON.parseObject(apiServices.CheckLog(UserAuth));
            //return chain.filter(exchange);
            if(jsb.getInteger("code")==1){
                return chain.filter(exchange);
            }else{
                byte[] bytes= apiServices.CheckLog(UserAuth).getBytes(StandardCharsets.UTF_8);
                DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                //设置编码，防止乱码
                exchange.getResponse().getHeaders().add("Content-Type","text/plan;charset=UTF-8");
                return exchange.getResponse().writeWith(Mono.just(buffer));
            }

        }
*/
    }

    @Override
    public int getOrder() { //数值越低，优先级越高
        return 10;
    }
}
