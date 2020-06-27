package com.cn.cloud.ofo.service.dao;

import com.cn.cloud.ofo.ofoapi.bean.Bike;

import java.util.List;


public interface BikeDao {

    //查询这个市/县下的所车辆信息
    List<Bike> selectAll(Integer cityid);

    //通过车牌号查询密码
    Bike selectByNo(Integer bikeno);

    //添加车辆信息
    int addbike(Bike bike);
}
