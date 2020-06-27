package com.cn.cloud.ofo.ofoapi.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@AllArgsConstructor //全参构造方法
@NoArgsConstructor//空参构造方法
@Data//set get方法
@Accessors(chain = true) //链式赋值写法支持
/**
 *  市/县
 */
public class City {
    private Integer id;
    private String name;
    private Integer pid;
}
