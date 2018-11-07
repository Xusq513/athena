package com.refutrue.athena.utils.base;

import java.util.List;
import java.util.Map;

/**
 * @Auther: Michael Xu
 * @Date: 2018/11/5 09:34
 * @Description:
 */
public interface BaseMapper<T> {

    public int delete(Integer userId);

    public long count(Map<String,Object> inMap);

    public int insert(T record);

    public int update(T record);

    public T selectByPrimaryKey(Integer userId);

    public List<T> selectAll(Map<String,Object> inMap);

    public List<T> selectPagination(Map<String,Object> inMap);

}
