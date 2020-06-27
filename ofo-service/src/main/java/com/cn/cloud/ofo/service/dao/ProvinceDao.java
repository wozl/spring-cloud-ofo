package com.cn.cloud.ofo.service.dao;

import com.cn.cloud.ofo.ofoapi.bean.Province;


import java.util.List;


public interface ProvinceDao {

    //查询所有的省份
    List<Province> selectAll();
}
