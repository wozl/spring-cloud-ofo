package com.cn.cloud.ofo.ofogateway.services;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class AuthAPIServicesHystrix implements AuthAPIServices {

    private static final JSONObject json = new JSONObject();

    @Override
    public String isAdmin(String auth) {
        json.put("code",1004);
        json.put("msg","服务暂时不可用，请稍后再试!");
        return json.toString();
    }

    @Override
    public String CheckLog(String auth) {
        json.put("code",1004);
        json.put("msg","服务暂时不可用，请稍后再试!");
        return json.toString();
    }
}
