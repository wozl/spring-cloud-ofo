package com.cn.cloud.ofo.service.service;

import com.cn.cloud.ofo.ofoapi.bean.Bike;
import com.cn.cloud.ofo.ofoapi.bean.User;
import com.cn.cloud.ofo.service.dao.BikeDao;
import com.cn.cloud.ofo.service.dao.CityDao;
import com.cn.cloud.ofo.service.dao.ProvinceDao;
import com.cn.cloud.ofo.service.dao.UserDao;
import org.hibernate.validator.internal.constraintvalidators.bv.time.past.PastValidatorForZonedDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ofoAdminService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private ProvinceDao provinceDao;

    @Autowired
    private CityDao cityDao;

    @Autowired
    private BikeDao bikeDao;


    //查询所有用户信息
    public List<User> listuser(){

        return userDao.selectAll();
    }

    //添加车辆信息
	/*@SuppressWarnings({ "unused", "finally" })
	@Transactional*/
    public boolean addbikes(Bike bike){
        System.out.println(bike.getId()+"\t"+bike.getBikeno()+"\t"+bike.getPassword()+"\t"+bike.getCityid());
        if(bikeDao.addbike(bike)>0){
            return true;
        }else{
            return false;
        }

    }

    //按名字查询用户
    public User finduser(String name){
        return userDao.selectByName(name);
    }

    //统计系统用户数量
    public int countUser(){
        return userDao.countUser();
    }

    //更新用户信息
    public int updateuser(User user){
        System.out.println(user.getUsername()+"\t"+user.getPassword());
        return userDao.updateuser(user);
    }

    //删除用户
    public int deleteuser(Integer id){
        return userDao.deleteuser(id);
    }



}
