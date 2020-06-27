package com.cn.cloud.ofo.service.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cn.cloud.ofo.ofoapi.bean.Bike;
import com.cn.cloud.ofo.ofoapi.bean.City;
import com.cn.cloud.ofo.ofoapi.bean.Province;
import com.cn.cloud.ofo.ofoapi.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import com.cn.cloud.ofo.service.service.ofoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/ofo")
public class ofoConteroller {

    @Autowired
    private  ofoService ofoService;

    @Autowired
    private RedisTemplate redisTemplate;

    //登录处理
    @RequestMapping(value="/Login",method= RequestMethod.POST,produces = "application/json; charset=UTF-8")
    public String Login(@RequestBody User user, HttpSession session, HttpServletRequest request){
        JSONObject json = new JSONObject();
        String UserId= UUID.randomUUID().toString().replace("-",""); //用户登录成功后保存登录状态的唯一标识
        if(ofoService.Login(user)){
            if(ofoService.checkadmin(user)){ //检查是否是管理员身份
                json.put("code",1);
                json.put("isadmin",1);
                json.put("msg","success");
                json.put("auth",UserId); //反馈给前端，用于后台网关进行安全认证
                json.put("username",user.getUsername());
                redisTemplate.opsForValue().set(UserId,json.toJSONString(),30, TimeUnit.MINUTES); //设置30分钟缓存
            }else{
                json.put("code",1);
                json.put("isadmin",0);
                json.put("msg","success");
                json.put("auth",UserId); //反馈给前端，用于后台网关进行安全认证
                json.put("username",user.getUsername());
                redisTemplate.opsForValue().set(UserId,json.toJSONString(),30, TimeUnit.MINUTES); //设置30分钟缓存
            }
        }else{
            json.put("code",0);
            json.put("msg","登录失败，请检查登录信息!");
        }
        return json.toString();
    }



    //用户注册
    @RequestMapping(value="/regs",method=RequestMethod.POST,produces = "application/json; charset=UTF-8")
    public String reg(@RequestBody User user , HttpServletRequest request){
        JSONObject json = new JSONObject();
        //System.out.println(user.getUsername()+"\t"+user.getPassword());
        if(ofoService.name(user.getUsername())){
            if(ofoService.reg(user)){
                json.put("code",1);
                json.put("msg","success");
            }else{
                json.put("code",0);
                json.put("msg","注册失败，请稍后再试!");
            }
        }else{
            json.put("code",0);
            json.put("msg","注册失败，用户名已存在!");
        }
        return json.toString();
    }


    //查询省
    @RequestMapping(value="/getProvince",method=RequestMethod.POST)
    public String getProvince(){
        JSONObject json = new JSONObject();
        List<Province> listpro=ofoService.listpro();
        json.put("code",1);
        json.put("data",listpro);
        json.put("msg","success");
        return json.toString();
    }

    //查询市
    @RequestMapping(value="/getCity",method=RequestMethod.POST)
    public String getCity(@RequestParam(value = "id",required = false) Integer id){
        JSONObject json = new JSONObject();
        List<City>listcity=ofoService.listcity(id);
        json.put("code",1);
        json.put("data",listcity);
        json.put("msg","success");
        return json.toString();
    }

    //查询市的车辆信息
    @RequestMapping(value="/getBike",method=RequestMethod.POST,produces = "application/json; charset=UTF-8")
    public String getbike(@RequestParam(value = "cityid",required = false) Integer cityid){
        JSONObject json = new JSONObject();
        json.put("code",1);
        List<Bike>listbike=ofoService.listbike(cityid);
        json.put("data",listbike);
        json.put("msg","success");
        return json.toString();
    }

    //用户退出
    @RequestMapping(value="loginout",method = RequestMethod.POST)
    public String loginout(@RequestParam(value = "auth",defaultValue = "",required = false) String auth){
        JSONObject json = new JSONObject();
        if(auth.isEmpty()){
            json.put("code",0);
            json.put("msg","非法请求!");
        }else{
            redisTemplate.delete(auth); //删除登录状态
            json.put("code",1);
            json.put("msg","success");
        }

        return json.toString();
    }

}
