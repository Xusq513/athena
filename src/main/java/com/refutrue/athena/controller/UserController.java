package com.refutrue.athena.controller; 

import com.refutrue.athena.bean.User; 
import com.refutrue.athena.service.UserServiceImpl; 
import com.refutrue.athena.utils.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Michael Xu
 * @DateTime: 2019-03-18 03:34:29
 * @Description: �û�������Ϣ�Ŀ��Ʋ㣬���Rest�淶
 */
@RestController
@RequestMapping(value = "/User")
public class UserController extends BaseController<User> {

    @Autowired
    public UserController(UserServiceImpl userService) {
        super.entityClass = User.class;
        super.baseService = userService;
    }
}
