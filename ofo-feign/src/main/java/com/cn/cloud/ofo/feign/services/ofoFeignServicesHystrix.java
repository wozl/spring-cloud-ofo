package com.cn.cloud.ofo.feign.services;

import com.alibaba.fastjson.JSONObject;
import com.cn.cloud.ofo.ofoapi.bean.Bike;
import com.cn.cloud.ofo.ofoapi.bean.User;
import org.springframework.stereotype.Component;

@Component
public class ofoFeignServicesHystrix implements ofoFeignServices {

    private static final JSONObject json = new JSONObject();

    @Override
    public String AllUserInfo() {
        json.put("code",1004);
        json.put("msg","服务暂时不可用，请稍后再试!");
        return json.toString();
    }

    @Override
    public String AddBikeInfo(Bike bike) {
        json.put("code",1004);
        json.put("msg","服务暂时不可用，请稍后再试!");
        return json.toString();
    }

    @Override
    public String FindUserInfoByName(String name) {
        json.put("code",1004);
        json.put("msg","服务暂时不可用，请稍后再试!");
        return json.toString();
    }

    @Override
    public String UpdateUserInfos(User user) {
        json.put("code",1004);
        json.put("msg","服务暂时不可用，请稍后再试!");
        return json.toString();
    }

    @Override
    public String DelUser(Integer id) {
        json.put("code",1004);
        json.put("msg","服务暂时不可用，请稍后再试!");
        return json.toString();
    }

    @Override
    public String Login(User user) {
        json.put("code",1004);
        json.put("msg","服务暂时不可用，请稍后再试!");
        return json.toString();
    }

    @Override
    public String Reg(User user) {
        json.put("code",1004);
        json.put("msg","服务暂时不可用，请稍后再试!");
        return json.toString();
    }

    @Override
    public String GetProvince() {
        json.put("code",1004);
        json.put("msg","服务暂时不可用，请稍后再试!");
        return json.toString();
    }

    @Override
    public String GetCity(Integer id) {
        json.put("code",1004);
        json.put("msg","服务暂时不可用，请稍后再试!");
        return json.toString();
    }

    @Override
    public String GetBikeInfos(Integer cityid) {
        json.put("code",1004);
        json.put("msg","服务暂时不可用，请稍后再试!");
        return json.toString();
    }

    @Override
    public String LoginOut(String auth) {
        json.put("code",1004);
        json.put("msg","服务暂时不可用，请稍后再试!");
        return json.toString();
    }

    @Override
    public String checkUser(String auth) {
        json.put("code",1004);
        json.put("msg","服务暂时不可用，请稍后再试!");
        return json.toString();
    }

    @Override
    public String checkLogin(String auth) {
        json.put("code",1004);
        json.put("msg","服务暂时不可用，请稍后再试!");
        return json.toString();
    }
}
