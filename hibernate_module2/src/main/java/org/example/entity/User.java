package org.example.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    /*hibernate要求实体类有一个属性唯一的
    * 1.配置XXX.hbm.xml的文件
    * 2.配置是xml格式，在配置文件中首先引入xml约束, dtd或者schema约束
    * 3.配置映射关系*/
    int uid;
    String username;
    String password;
    String address;

}
