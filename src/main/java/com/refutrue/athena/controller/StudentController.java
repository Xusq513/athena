package com.refutrue.athena.controller; 

import com.refutrue.athena.bean.Student; 
import com.refutrue.athena.service.StudentServiceImpl; 
import com.refutrue.athena.utils.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Michael Xu
 * @DateTime: 2018-11-07 04:17:40
 * @Description: 学生信息çæ§å¶å±ï¼éµä»Restè§è
 */
@RestController
@RequestMapping(value = "/Student")
public class StudentController extends BaseController<Student> {
	
    @Autowired
    public StudentController(StudentServiceImpl studentService) {
        super.entityClass = Student.class;
        super.baseService = studentService;
    }
}
