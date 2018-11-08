package com.refutrue.athena.service;

import com.refutrue.athena.bean.Student; 
import com.refutrue.athena.mapper.StudentMapper; 
import com.refutrue.athena.utils.base.BaseServiceImpl;
import com.refutrue.athena.utils.exception.AthenaException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: Michael Xu
 * @DateTime: 2018-11-07 04:17:40
 * @Description: 学生信息çä¸å¡å±
 */
@Service
@CacheConfig(cacheNames = "student")
public class StudentServiceImpl extends BaseServiceImpl<Student> {

    @Autowired
    public StudentServiceImpl(StudentMapper studentMapper) {
        super.entityClass = Student.class;
        super.baseMapper = studentMapper;
    }
    
    @Override
    @Transactional(readOnly = true)
    @Cacheable(key="#id")
    public Student getEntityById(String id) throws AthenaException {
        return baseMapper.selectByPrimaryKey(Integer.valueOf(id));
    }

}
