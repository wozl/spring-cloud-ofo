package com.cn.cloud.ofo.service.service;

import com.cn.cloud.ofo.ofoapi.bean.Bike;
import com.cn.cloud.ofo.ofoapi.bean.City;
import com.cn.cloud.ofo.ofoapi.bean.Province;
import com.cn.cloud.ofo.ofoapi.bean.User;
import com.cn.cloud.ofo.service.dao.BikeDao;
import com.cn.cloud.ofo.service.dao.CityDao;
import com.cn.cloud.ofo.service.dao.ProvinceDao;
import com.cn.cloud.ofo.service.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ofoService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private ProvinceDao provinceDao;

    @Autowired
    private CityDao cityDao;

    @Autowired
    private BikeDao bikeDao;

    /**
     * 登录处理
     * **/
    public boolean Login(User user){
        User user2=userDao.selectByName(user.getUsername());
        if(user2!=null){
            if(user2.getUsername().equals(user.getUsername())&&
                    user2.getPassword().equals(user.getPassword())){
                return true;
            }
        }
        return false;
    }

    //检查登录的是否是管理员
    public boolean checkadmin(User user){
        User user3=userDao.selectByName(user.getUsername());
        if(user3!=null&&user3.getIsadmin()==1){
            return true;
        }
        return false;
    }

    //查询所有省份
    public List<Province> listpro(){

        return provinceDao.selectAll();
    }

    //查询所有城市
    public List<City> listcity(Integer id){

        return cityDao.selectAll(id);
    }

    //查询市的所有车辆信息
    public List<Bike>listbike(Integer id){
        return bikeDao.selectAll(id);
    }

    //注册用户
    public boolean reg(User user){
        if(userDao.adduser(user)>0){
            return true;
        }
        return false;
    }

    //查询用户名是否存在
    public boolean name(String name){
        if(userDao.selectByName(name)!=null){
            return false;
        }
        return true;
    }
}

