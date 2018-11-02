package com.refutrue.athena.mapper;


import com.refutrue.athena.bean.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    public int insert(User record);

    public int delete(Integer userId);

    public int update(User record);

    public User selectByPrimaryKey(Integer userId);

    public List<User> selectAllUser(Map<String,Object> inMap);

    public List<User> selectPaginationUser(Map<String,Object> inMap);

    public long count(Map<String,Object> inMap);
}