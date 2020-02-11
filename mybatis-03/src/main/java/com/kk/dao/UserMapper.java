package com.kk.dao;

import com.kk.pojo.User;

public interface UserMapper {

    //根据id查询用户
    User getUserById(int id);
}
