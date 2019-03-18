package com.refutrue.athena.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.refutrue.athena.bean.User;
import com.refutrue.athena.mapper.UserMapper; 
import com.refutrue.athena.utils.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Michael Xu
 * @DateTime: 2019-03-14 04:24:42
 * @Description: 用户基本信息的业务层
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> {

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        super.entityClass = User.class;
        super.baseMapper = userMapper;
    }

    public String getToken(User user) {
        String token ;
        token= JWT.create().withAudience(user.getLoginId())
                .sign(Algorithm.HMAC256(user.getPasswd()));
        return token;
    }

    public User getUserByLoginId(String loginId){
        Map<String,Object> inMap = new HashMap<>();
        inMap.put("loginId",loginId);
        List<User> list = baseMapper.selectAll(inMap);
        if(list.size() == 1){
            return list.get(0);
        }
        return null;
    }

}
