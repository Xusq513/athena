package com.refutrue.athena.utils.base;

import com.refutrue.athena.utils.exception.AthenaException;
import com.refutrue.athena.utils.pojo.Pagination;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Auther: Michael Xu
 * @Date: 2018/11/5 09:42
 * @Description:
 */
@Transactional
public abstract class BaseServiceImpl<T> implements BaseService<T>{

    protected BaseMapper<T> baseMapper;

    @Override
    public void add(T t) throws AthenaException {
        baseMapper.insert(t);
    }

    @Override
    public void update(T t) throws AthenaException {
        baseMapper.update(t);
    }

    @Override
    public void delete(String id) throws AthenaException {
        baseMapper.delete(Integer.valueOf(id));
    }

    @Override
    @Transactional(readOnly = true)
    public T getEntityById(String id) throws AthenaException {
        return baseMapper.selectByPrimaryKey(Integer.valueOf(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> getAllEntityByCondition(Map<String, Object> inMap) throws AthenaException {
        return baseMapper.selectAll(inMap);
    }

    @Override
    @Transactional(readOnly = true)
    public Pagination<T> getEntityPageByConditon(Map<String, Object> inMap) throws AthenaException {
        Pagination<T> pagination = new Pagination<>();
        long count = baseMapper.count(inMap);
        pagination.setCount(String.valueOf(count));
        if(count == 0L){
            return  pagination;
        }
        List<T> list = baseMapper.selectPagination(inMap);
        pagination.setList(list);
        return pagination;
    }

}
