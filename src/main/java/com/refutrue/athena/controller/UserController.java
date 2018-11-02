package com.refutrue.athena.controller;

import com.alibaba.fastjson.JSON;
import com.refutrue.athena.bean.User;
import com.refutrue.athena.service.UserService;
import com.refutrue.athena.utils.ResponseMsg;
import com.refutrue.athena.utils.StringUtil;
import com.refutrue.athena.utils.constants.WebContants;
import com.refutrue.athena.utils.exception.AthenaException;
import com.refutrue.athena.utils.pojo.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/16.
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseMsg addUser(HttpServletRequest request){
        ResponseMsg responseMsg = new ResponseMsg();
        String _json = request.getParameter(WebContants._JSON);
        User user;
        try{
            user = JSON.parseObject(_json,User.class);
            userService.addUser(user);
        }catch (AthenaException e){
            responseMsg.setMessage(e.getMessage());
            responseMsg.setSuccess(false);
            responseMsg.setCode("503");
        }catch (Exception e){
            responseMsg.setMessage(e.getMessage());
            responseMsg.setSuccess(false);
            responseMsg.setCode("500");
        }
        return responseMsg;
    }

    @PutMapping
    public ResponseMsg updateUser(HttpServletRequest request){
        ResponseMsg responseMsg = new ResponseMsg();
        String _json = request.getParameter(WebContants._JSON);
        User user;
        try{
            user = JSON.parseObject(_json,User.class);
            userService.updateUser(user);
        }catch (AthenaException e){
            responseMsg.setMessage(e.getMessage());
            responseMsg.setSuccess(false);
            responseMsg.setCode("503");
        }catch (Exception e){
            responseMsg.setMessage(e.getMessage());
            responseMsg.setSuccess(false);
            responseMsg.setCode("500");
        }
        return responseMsg;
    }

    @DeleteMapping
    public ResponseMsg deleteUser(HttpServletRequest request){
        ResponseMsg responseMsg = new ResponseMsg();
        String id = request.getParameter(WebContants._ID);
        try{
            userService.deleteUser(id);
        }catch (AthenaException e){
            responseMsg.setMessage(e.getMessage());
            responseMsg.setSuccess(false);
            responseMsg.setCode("503");
        }catch (Exception e){
            responseMsg.setMessage(e.getMessage());
            responseMsg.setSuccess(false);
            responseMsg.setCode("500");
        }
        return responseMsg;
    }

    @GetMapping
    public ResponseMsg getUserById(HttpServletRequest request){
        ResponseMsg responseMsg = new ResponseMsg();
        String id = request.getParameter(WebContants._ID);
        try{
            User user = userService.getUserById(id);
            responseMsg.setData(user);
        }catch (AthenaException e){
            responseMsg.setMessage(e.getMessage());
            responseMsg.setSuccess(false);
            responseMsg.setCode("503");
        }catch (Exception e){
            responseMsg.setMessage(e.getMessage());
            responseMsg.setSuccess(false);
            responseMsg.setCode("500");
        }
        return responseMsg;
    }

    @GetMapping(value = "/all")
    public ResponseMsg getAllUser(HttpServletRequest request){
        ResponseMsg responseMsg = new ResponseMsg();
        try{
            Map<String,Object> inMap = buildSelectMap(request);
            List<User> list = userService.getAllUserByCondition(inMap);
            responseMsg.setData(list);
        }catch (AthenaException e){
            e.printStackTrace();
            responseMsg.setMessage(e.getMessage());
            responseMsg.setSuccess(false);
            responseMsg.setCode("503");
        }catch (Exception e){
            e.printStackTrace();
            responseMsg.setMessage(e.getMessage());
            responseMsg.setSuccess(false);
            responseMsg.setCode("500");
        }
        return responseMsg;
    }

    @GetMapping(value = "/pagination")
    public ResponseMsg getPaginationUser(HttpServletRequest request){
        ResponseMsg responseMsg = new ResponseMsg();
        try{
            Map<String,Object> inMap = buildSelectMap(request);
            Pagination<User> list = userService.getUserPageByConditon(inMap);
            responseMsg.setData(list);
        }catch (AthenaException e){
            e.printStackTrace();
            responseMsg.setMessage(e.getMessage());
            responseMsg.setSuccess(false);
            responseMsg.setCode("503");
        }catch (Exception e){
            e.printStackTrace();
            responseMsg.setMessage(e.getMessage());
            responseMsg.setSuccess(false);
            responseMsg.setCode("500");
        }
        return responseMsg;
    }

    public Map<String,Object> buildSelectMap(HttpServletRequest request){
        Map<String,Object> inMap = new HashMap<>();
        String json = request.getParameter(WebContants._JSON);
        String startRow = StringUtil.obj2Str(request.getParameter(WebContants._startRow));
        String pageSize = StringUtil.obj2Str(request.getParameter(WebContants._pageSize));
        if(StringUtil.isNotEmptyOrNull(startRow)){
            inMap.put("startRow",Long.valueOf(startRow));
        }
        if(StringUtil.isNotEmptyOrNull(pageSize)){
            inMap.put("pageSize",Long.valueOf(pageSize));
        }
        if(StringUtil.isNotEmptyOrNull(json)){
            Map<String,Object> jsonMap = JSON.parseObject(json);
            String whereStr = StringUtil.obj2Str(jsonMap.get("where"));
            if(StringUtil.isNotEmptyOrNull(whereStr)){
                inMap.putAll(JSON.parseObject(whereStr));
            }
            String orderStr = StringUtil.obj2Str(jsonMap.get("order"));
            // TODO 这个地方先不考虑多个order by 字段的顺序问题
            if(StringUtil.isNotEmptyOrNull(orderStr)){
                StringBuilder sb = new StringBuilder();
                sb.append(" order by ");
                Map<String,Object> orderMap = JSON.parseObject(orderStr);
                if(orderMap != null && orderMap.size() > 0){
                    for(String key : orderMap.keySet()){
                        // TODO 这个地方应该要传数据库的字段，而非属性，需要进行优化
                        sb.append(" " + key + " " + orderMap.get(key) + ",");
                    }
                    sb.setLength(sb.length() - 1);
                    inMap.put("order",sb.toString());
                }
            }
        }
        return inMap;
    }



}