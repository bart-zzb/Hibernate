package org.example.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level= AccessLevel.PRIVATE)
public class User {
    /*hibernate要求实体类有一个属性唯一的*/
    private int uid;
    private String username;
    private String password;
    private String address;
}
