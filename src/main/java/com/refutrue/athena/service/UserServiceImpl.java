package com.refutrue.athena.service;

import com.refutrue.athena.bean.User; 
import com.refutrue.athena.mapper.UserMapper; 
import com.refutrue.athena.utils.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Michael Xu
 * @DateTime: 2018-11-07 11:06:39
 * @Description: 用户信息çä¸å¡å±
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        super.baseMapper = userMapper;
    }

}
