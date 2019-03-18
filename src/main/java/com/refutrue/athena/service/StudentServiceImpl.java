package com.refutrue.athena.service;

import com.refutrue.athena.bean.Student; 
import com.refutrue.athena.mapper.StudentMapper; 
import com.refutrue.athena.utils.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Michael Xu
 * @DateTime: 2019-03-13 05:05:55
 * @Description: 学生信息的业务层
 */
@Service
public class StudentServiceImpl extends BaseServiceImpl<Student> {

    @Autowired
    public StudentServiceImpl(StudentMapper studentMapper) {
        super.entityClass = Student.class;
        super.baseMapper = studentMapper;
    }



}
