package com.cn.cloud.ofo.service.dao;

import com.cn.cloud.ofo.ofoapi.bean.City;


import java.util.List;


public interface CityDao {

    //查询某个省份下的所有市/县
    List<City> selectAll(Integer pid);


}
