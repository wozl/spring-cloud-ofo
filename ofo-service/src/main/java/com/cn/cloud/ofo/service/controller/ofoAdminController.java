package com.cn.cloud.ofo.service.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cn.cloud.ofo.ofoapi.bean.Bike;
import com.cn.cloud.ofo.ofoapi.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import com.cn.cloud.ofo.service.service.ofoAdminService;
import com.cn.cloud.ofo.service.service.ofoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class ofoAdminController {

    @Autowired
    private ofoAdminService ofoAdminService;

    @Autowired
    private ofoService ofoService;

    @Autowired
    private RedisTemplate redisTemplate;

    //查询所有用户信息
    @RequestMapping(value = "/user/all",method = RequestMethod.POST)
    public String users(HttpServletRequest request){
        JSONObject json = new JSONObject();
        List<User> listuser=ofoAdminService.listuser();
        json.put("code",1);
        json.put("data",listuser);
        json.put("msg","success");
        return json.toString();
    }


    //添加车辆信息
    @RequestMapping(value="/addbikes",method = RequestMethod.POST,produces = "application/json; charset=UTF-8")
    public String addbikes(@RequestBody Bike bike){
        JSONObject json = new JSONObject();
        if (bike!=null){
            if (ofoAdminService.addbikes(bike)){
                json.put("code",1);
                json.put("msg","success");
            }else{
                json.put("code",0);
                json.put("msg","err");
            }
        }else{
            json.put("code",-1);
            json.put("msg","非法请求!");
        }
        return json.toString();
    }

    //按名字查询用户
    @RequestMapping(value="/finduser",method = RequestMethod.POST,produces = "application/json; charset=UTF-8")
    public String finduser(@RequestParam(defaultValue = "",value = "name",required = false) String name){
        JSONObject json = new JSONObject();
        if(!name.isEmpty()){
            User user=ofoAdminService.finduser(name);
            List<User>lists=new ArrayList<User>();
            lists.add(user);
            json.put("code",1);
            json.put("msg","success");
            json.put("data",lists);
        }else{
            json.put("code",0);
            json.put("msg","请求参数不能为空!");
        }
        return json.toString();
    }

    //更新用户信息
    @RequestMapping(value="/updateuser",method=RequestMethod.POST,produces = "application/json; charset=UTF-8")
    public String updateuser(@RequestBody User user){
        JSONObject json = new JSONObject();
       if(user!=null){
           if(ofoAdminService.updateuser(user)>0){
               json.put("code",1);
               json.put("msg","success");
           }else{
               json.put("code",0);
               json.put("msg","更新用户信息失败!");
           }
       }else{
            json.put("code",0);
            json.put("msg","请求参数不能为空!");
       }

        return json.toString();
    }

    //删除用户
    @RequestMapping(value="/deleteuser",method=RequestMethod.POST,produces = "application/json; charset=UTF-8")
    public String deleteuser(Integer id){
        JSONObject json = new JSONObject();
        if(id>0 && id!=null){
            if(ofoAdminService.deleteuser(id)>0){
                json.put("code",1);
                json.put("msg","success");
            }else {
                json.put("code",0);
                json.put("msg","删除用户失败!");
            }
        }else{
            json.put("code",0);
            json.put("msg","请求参数非法!");
        }
        return json.toString();
    }


    /**
     * 检查用户是否是管理员
     * @param auth
     * @return
     */
    @RequestMapping(value = "/CheckUserAuth",method = RequestMethod.POST,produces = "application/json; charset=UTF-8")
    public String CheckUserAuth(@RequestParam(defaultValue = "",value = "auth",required = false) String auth){
        JSONObject json = new JSONObject();
        if (redisTemplate.opsForValue().get(auth)!=null){
            JSONObject js= JSONObject.parseObject(redisTemplate.opsForValue().get(auth).toString());
            if(js.getInteger("isadmin")==1){//是管理员身份
                json.put("code",1);
                json.put("msg","确认为管理员");
            }else {
                json.put("code", 1000);
                json.put("msg", "验证失败，无权访问!");
            }
        }
        return json.toString();
    }


    /**
     * 检查用户登录状态
     * @param auth
     * @param session
     * @return
     */
    @RequestMapping(value = "/CheckLogin",method = RequestMethod.POST,produces = "application/json; charset=UTF-8")
    public String CheckLogin(@RequestParam(defaultValue = "",value = "auth",required = false) String auth,HttpSession session){
        JSONObject json = new JSONObject();
        if(redisTemplate.opsForValue().get(auth)!=null){
            json.put("code",1);
            json.put("msg","登录有效!");
        }else{
            json.put("code",1002);
            json.put("msg","登录已失效，请重新登录!");
        }
        return  json.toString();
    }
}
