package com.cn.cloud.ofo.ofogateway.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "ofo-feign",fallback = AuthAPIServicesHystrix.class)
public interface AuthAPIServices {

    @RequestMapping(value = "/CheckUserAuth",method = RequestMethod.POST,produces = "application/json; charset=UTF-8")
    public String isAdmin(@RequestParam(value = "auth",required = false) String auth);

    @RequestMapping(value = "/CheckLogin",method = RequestMethod.POST,produces = "application/json; charset=UTF-8")
    public String CheckLog(@RequestParam(value = "auth",required = false) String auth);
}
