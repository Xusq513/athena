package com.refutrue.athena.controller; 

import com.refutrue.athena.bean.User; 
import com.refutrue.athena.service.UserServiceImpl; 
import com.refutrue.athena.utils.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Michael Xu
 * @DateTime: 2018-11-07 11:06:39
 * @Description: 用户信息çæ§å¶å±ï¼éµä»Restè§è
 */
@RestController
@RequestMapping(value = "/User")
public class UserController extends BaseController<User> {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        super.entityClass = User.class;
        super.baseService = userService;
    }
}
