package com.refutrue.athena.service;

import com.refutrue.athena.bean.User;
import com.refutrue.athena.mapper.UserMapper;
import com.refutrue.athena.utils.exception.AthenaException;
import com.refutrue.athena.utils.pojo.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Auther: Michael Xu
 * @Date: 2018/11/2 14:08
 * @Description:
 */
@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public void addUser(User user) throws AthenaException {
        userMapper.insert(user);
    }

    @Override
    public void updateUser(User user) throws AthenaException {
        userMapper.update(user);
    }

    @Override
    public void deleteUser(String id) throws AthenaException {
        userMapper.delete(Integer.valueOf(id));
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(String id) throws AthenaException {
        return userMapper.selectByPrimaryKey(Integer.valueOf(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUserByCondition(Map<String,Object> inMap) throws AthenaException {
        return userMapper.selectAllUser(inMap);
    }

    @Override
    @Transactional(readOnly = true)
    public Pagination<User> getUserPageByConditon(Map<String,Object> inMap) throws AthenaException {
        Pagination<User> pagination = new Pagination<>();
        long count = userMapper.count(inMap);
        pagination.setCount(String.valueOf(count));
        if(count == 0L){
            return  pagination;
        }
        List<User> list = userMapper.selectPaginationUser(inMap);
        pagination.setList(list);
        return pagination;
    }

}
