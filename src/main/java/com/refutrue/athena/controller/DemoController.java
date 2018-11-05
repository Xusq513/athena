package com.refutrue.athena.controller;

import com.refutrue.athena.bean.User;
import com.refutrue.athena.service.DemoServiceImpl;
import com.refutrue.athena.utils.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Michael Xu
 * @Date: 2018/11/5 10:47
 * @Description:
 */
@RestController
@RequestMapping(value = "/demo")
public class DemoController extends BaseController<User> {

    @Autowired
    private DemoServiceImpl demoService;

    @Autowired
    public DemoController(DemoServiceImpl demoService) {
        super.entityClass = User.class;
        super.baseService = demoService;
    }
}
