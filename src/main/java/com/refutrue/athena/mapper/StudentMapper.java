package com.refutrue.athena.mapper;

import com.refutrue.athena.bean.Student;
import com.refutrue.athena.utils.base.BaseMapper;

/**
 * @Author: Michael Xu
 * @DateTime: 2018-11-07 04:17:40
 * @Description: 学生信息çDaoå±
 */
public interface StudentMapper extends BaseMapper<Student>{

	public Student selectByPrimaryKey(Integer userId);
}
