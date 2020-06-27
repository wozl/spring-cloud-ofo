package com.cn.cloud.ofo.service.dao;

import com.cn.cloud.ofo.ofoapi.bean.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface UserDao {

    //查询所有的用户
    List<User> selectAll();

    //通过用户名查询
    User selectByName(String username);

    //通过用户id查询
    User selectById(Integer id);

    //查询用户数量
    @Select("SELECT COUNT(1) FROM `users`;")
    int countUser();

    //添加用户
    int adduser(User user);

    //更新用户信息
    int updateuser(User user);

    //删除用户
    int deleteuser(Integer id);

}
