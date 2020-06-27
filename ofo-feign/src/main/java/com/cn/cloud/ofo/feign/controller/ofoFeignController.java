package com.cn.cloud.ofo.feign.controller;

import com.cn.cloud.ofo.ofoapi.bean.Bike;
import com.cn.cloud.ofo.ofoapi.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.cn.cloud.ofo.feign.services.ofoFeignServices;

@RestController
public class ofoFeignController {

    @Autowired
    private ofoFeignServices services;

    /**
     * 查询所有用户信息
     * @return
     */
    @RequestMapping(value = "/admin/UserInfos",method = RequestMethod.GET)
    public  String AllUserInfos(){
        return services.AllUserInfo();
    }

    /**
     * 添加车辆信息
     * @param bike
     * @return
     */
    @RequestMapping(value = "/admin/AddBikeInfo",method = RequestMethod.POST)
    public String AddBikeInfos(Bike bike){
        return services.AddBikeInfo(bike);
    }

    /**
     * 通过姓名查询用户信息
     * @param name
     * @return
     */
    @RequestMapping(value ="/admin/FindUserInfoByName",method = RequestMethod.POST)
    public String FindUserInfoByName(@RequestParam(value = "name",required = false) String name){
        return services.FindUserInfoByName(name);
    }

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    @RequestMapping(value = "/admin/UpdateUserInfos",method = RequestMethod.POST)
    public String UpdateUserInfos(User user){
        return services.UpdateUserInfos(user);
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @RequestMapping(value = "/admin/DelUser",method = RequestMethod.POST)
    public String DelUser(@RequestParam(value = "id",required = false) Integer id){
        return services.DelUser(id);
    }


    /***
     * 以下是普通用户接口的接口服务
     */

    /**
     * 登录
     * @param user
     *
     * @return
     */
    @RequestMapping(value = "/User/Login",method = RequestMethod.POST)
    public String Login(User user){
        return services.Login(user);
    }

    /**
     * 用户注册
     * @param user
     *
     * @return
     */
    @RequestMapping(value = "/User/Reg",method = RequestMethod.POST)
    public String Reg(User user){
        return services.Reg(user);
    }

    /**
     * 查询省份信息
     * @return
     */
    @RequestMapping(value = "/ofo/GetProvinceInfo",method = RequestMethod.POST)
    public String GetProvince(){
        return services.GetProvince();
    }

    /**
     * 查询该省下的所有市区信息
     * @return
     */
    @RequestMapping(value = "/ofo/GetCityInfo",method = RequestMethod.POST)
    public String GetCity(@RequestParam(value = "id",required = false) Integer id){
        return services.GetCity(id);
    }

    /**
     * 查询该市区下的所有车辆信息
     * @param cityid
     * @return
     */
    @RequestMapping(value = "/ofo/GetBikeInfos",method = RequestMethod.POST)
    public String GetBikeInfos(@RequestParam(value = "cityid",required = false) Integer cityid){
        return services.GetBikeInfos(cityid);
    }

    /**
     * 添加车辆信息(用户端)
     * @param bike
     * @return
     */
    @RequestMapping(value = "/ofo/AddBikeInfo",method = RequestMethod.POST)
    public String AddBikeInfo(Bike bike){
        return services.AddBikeInfo(bike);
    }

    /**
     * 退出登录
     *
     * @return
     */
    @RequestMapping(value = "/ofo/LoginOut",method = RequestMethod.POST)
    public String LoginOut(@RequestParam(value = "auth",required = false) String auth){
        return services.LoginOut(auth);
    }

    /**
     * 检查用户是否有权限访问管理员的api
     * @param auth
     * @return
     */
    @RequestMapping(value = "/CheckUserAuth",method = RequestMethod.POST,produces = "application/json; charset=UTF-8")
    public String CheckUserAuth(@RequestParam(value = "auth",required = false) String auth){
        return services.checkUser(auth);
    }

    /**
     * 检查用户的登录状态
     * @param auth
     * @return
     */
    @RequestMapping(value = "/CheckLogin",method = RequestMethod.POST,produces = "application/json; charset=UTF-8")
    public String CheckLogin(@RequestParam(value = "auth",required = false) String auth){
        return services.checkLogin(auth);
    }
}
