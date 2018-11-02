package com.refutrue.athena.service;

import com.refutrue.athena.bean.User;
import com.refutrue.athena.utils.exception.AthenaException;
import com.refutrue.athena.utils.pojo.Pagination;

import java.util.List;
import java.util.Map;

public interface UserService {

    /**
     *  新增用户
     * @param user
     * @throws AthenaException
     */
    public void addUser(User user) throws AthenaException;

    /**
     * 更新用户
     * @param user
     * @throws AthenaException
     */
    public void updateUser(User user) throws AthenaException;

    /**
     * 删除用户
     * @param id
     * @throws AthenaException
     */
    public void deleteUser(String id) throws AthenaException;

    /**
     *  通过主键获取到用户
     * @param id
     * @return
     * @throws AthenaException
     */
    public User getUserById(String id) throws AthenaException;

    /**
     *  根据查询条件获取到所有的用户
     * @param inMap
     * @return
     * @throws AthenaException
     */
    public List<User> getAllUserByCondition(Map<String,Object> inMap) throws AthenaException;


    /**
     * 根据查询条件分页获取到用户
     * @param inMap
     * @return
     * @throws AthenaException
     */
    public Pagination<User> getUserPageByConditon(Map<String,Object> inMap) throws AthenaException;
}
