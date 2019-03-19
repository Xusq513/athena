package com.refutrue.athena.controller; 

import com.alibaba.fastjson.JSONObject;
import com.refutrue.athena.annotation.UserLoginToken;
import com.refutrue.athena.bean.User;
import com.refutrue.athena.service.UserServiceImpl;
import com.refutrue.athena.utils.ResponseMsg;
import com.refutrue.athena.utils.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Michael Xu
 * @DateTime: 2019-03-18 03:34:29
 * @Description:
 */
@RestController
@RequestMapping(value = "/User")
public class UserController extends BaseController<User> {

    @Autowired
    public UserController(UserServiceImpl userService) {
        super.entityClass = User.class;
        super.baseService = userService;
    }

    //登录
    @PostMapping("/login")
    public Object login(@RequestBody User user){
        ResponseMsg responseMsg = new ResponseMsg();
        UserServiceImpl userService = (UserServiceImpl)baseService;
        JSONObject jsonObject=new JSONObject();
        User userForBase= userService.getUserByLoginId(user.getLoginId());
        if(userForBase==null){
            responseMsg.setCode("500");
            responseMsg.setSuccess(false);
            responseMsg.setMessage("登录失败,用户不存在");
            return responseMsg;
        }else {
            if (!userForBase.getPasswd().equals(user.getPasswd())){
                responseMsg.setCode("500");
                responseMsg.setSuccess(false);
                responseMsg.setMessage("登录失败,密码错误");
                return jsonObject;
            }else {
                String token = userService.getToken(userForBase);
                jsonObject.put("token", token);
                responseMsg.setData(jsonObject);
                return responseMsg;
            }
        }
    }

    @GetMapping("/info")
    public Object getUserInfo(){
        ResponseMsg responseMsg = new ResponseMsg();
        Map<String,Object> inMap = new HashMap<>();
        inMap.put("roles",new String[]{"admin"});
        inMap.put("name","admin");
        inMap.put("avatar","VIP");
        responseMsg.setData(inMap);
        return responseMsg;
    }

    @UserLoginToken
    @GetMapping("/getMessage")
    public String getMessage(){
        return "你已通过验证";
    }
}
