package com.cn.cloud.ofo.feign.services;

import com.cn.cloud.ofo.ofoapi.bean.Bike;
import com.cn.cloud.ofo.ofoapi.bean.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;



@FeignClient(value = "ofo-services",fallback = ofoFeignServicesHystrix.class) //当调用远程服务失败时，防止雪崩效应，这里会自动转向到定义的fallback中的函数进行错误信息返回
public interface ofoFeignServices {
    /***
     * 这里是注释
     * 1.在使用feign去访问服务时，由于不能POST传递对象，会导致服务端无法获取其参数，建议服务端再对象参数前使用@RequestBody来接收转换的json对象
     * 2.feign中调用服务端接口时需要保存风格统一，若服务端有GET请求的接口，但是又有POST的接口时，建议使用@RequestMapping后来规定是使用GET请求还是POST请求，
     *  并且还需要使用consumes = "application/json"，防止服务端无法接收对象类的参数导致出错。若服务端都是POST请求，建议还是统一使用@PostMapping来请求
     *  服务端，同样使用consumes = "application/json"，用于传递对象参数，当然不用也可以，在对象参数使用@RequestBody也行，但是还是建议第一种规范。
     */

    /**
     * 查询所有用户信息
     *
     * @return
     */
    @RequestMapping(value = "/admin/user/all",method = RequestMethod.POST,produces = "application/json; charset=UTF-8")
    public String AllUserInfo();

    /**
     * 添加车辆信息
     * @param bike
     * @return
     */
    @RequestMapping(value = "/admin/addbikes",method = RequestMethod.POST,consumes = "application/json",produces = "application/json; charset=UTF-8")
    public String AddBikeInfo(Bike bike);

    /**
     * 通过姓名查询用户信息
     * @param name
     * @return
     */
    @RequestMapping(value = "/admin/finduser",method = RequestMethod.POST,produces = "application/json; charset=UTF-8")
    public String FindUserInfoByName(@RequestParam String name);

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    @RequestMapping(value = "/admin/updateuser",method = RequestMethod.POST,consumes = "application/json",produces = "application/json; charset=UTF-8")
    public String UpdateUserInfos(User user);

    /**
     * 删除用户
     * @param id
     * @return
     */
    @RequestMapping(value = "/admin/deleteuser",method = RequestMethod.POST,produces = "application/json; charset=UTF-8")
    public String DelUser(@RequestParam Integer id);

    /**
     * 检查用户是否有权限访问管理员的api
     * @param auth
     * @return
     */
    @RequestMapping(value = "/admin/CheckUserAuth",method = RequestMethod.POST,produces = "application/json; charset=UTF-8")
    public String checkUser(@RequestParam String auth);

    /**
     * 验证当前用户的登录状态是否失效
     * @param auth
     * @return
     */
    @RequestMapping(value = "/admin/CheckLogin",method = RequestMethod.POST,produces = "application/json; charset=UTF-8")
    public String checkLogin(@RequestParam String auth);

    /***
     * 以下是普通用户接口的接口服务
     */

    /**
     * 用户登录
     * @param user
     * @param session
     * @param request
     * @return
     */
    @RequestMapping(value = "/ofo/Login",method = RequestMethod.POST,consumes = "application/json",produces = "application/json; charset=UTF-8")
    public String Login(User user);

    /**
     * 用户注册
     * @param user
     * @param request
     * @return
     */
    @RequestMapping(value = "/ofo/regs",method = RequestMethod.POST,consumes = "application/json",produces = "application/json; charset=UTF-8")
    public String Reg(User user);

    /**
     * 查询省
     * @return
     */
    @RequestMapping(value = "/ofo/getProvince",method = RequestMethod.POST)
    public String GetProvince();

    /**
     * 查询市
     * @return
     */
    @RequestMapping(value = "/ofo/getCity",method = RequestMethod.POST)
    public String GetCity(@RequestParam(value = "id",required = false) Integer id);

    /**
     * 查询该市下的所有车辆信息
     * @param cityid
     * @return
     */
    @RequestMapping(value = "/ofo/getBike",method = RequestMethod.POST,produces = "application/json; charset=UTF-8")
    public String GetBikeInfos(@RequestParam Integer cityid);


    /**
     * 退出登录
     * @param auth
     * @return
     */
    @RequestMapping(value = "/ofo/loginout",method = RequestMethod.POST)
    public String LoginOut(@RequestParam String auth);
}
