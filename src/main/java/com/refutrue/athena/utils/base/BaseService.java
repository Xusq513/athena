package com.refutrue.athena.utils.base;

import java.util.List;
import java.util.Map;

import com.refutrue.athena.utils.exception.AthenaException;
import com.refutrue.athena.utils.pojo.Pagination;

/**
 * @Auther: Michael Xu
 * @Date: 2018/11/5 09:37
 * @Description:
 */
public interface BaseService<T>{

    /**
     *  新增
     * @param t
     * @throws AthenaException
     */
    public void add(T t) throws AthenaException;

    /**
     * 更新
     * @param t
     * @throws AthenaException
     */
    public void update(T t) throws AthenaException;

    /**
     * 删除
     * @param id
     * @throws AthenaException
     */
    public void delete(String id) throws AthenaException;

    /**
     *  通过主键获取实体
     * @param id
     * @return
     * @throws AthenaException
     */
    public T getEntityById(String id) throws AthenaException;

    /**
     *  根据查询条件获取到所有的实体
     * @param inMap
     * @return
     * @throws AthenaException
     */
    public List<T> getAllEntityByCondition(Map<String,Object> inMap) throws AthenaException;


    /**
     * 根据查询条件分页获取到实体
     * @param inMap
     * @return
     * @throws AthenaException
     */
    public Pagination<T> getEntityPageByConditon(Map<String,Object> inMap) throws AthenaException;

}
