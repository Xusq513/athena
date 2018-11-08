package com.refutrue.athena.controller; 

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.refutrue.athena.bean.Dic;
import com.refutrue.athena.service.DicServiceImpl;
import com.refutrue.athena.utils.ResponseMsg;
import com.refutrue.athena.utils.StringUtil;
import com.refutrue.athena.utils.base.BaseController;
import com.refutrue.athena.utils.exception.AthenaException;

/**
 * @Author: Michael Xu
 * @DateTime: 2018-11-08 03:31:34
 * @Description: 字典信息维护çæ§å¶å±ï¼éµä»Restè§è
 */
@RestController
@RequestMapping(value = "/Dic")
public class DicController extends BaseController<Dic> {
	
	@Autowired
	private DicServiceImpl dicService;
	
    @Autowired
    public DicController(DicServiceImpl dicService) {
        super.entityClass = Dic.class;
        super.baseService = dicService;
    }
    
    @GetMapping(value = "/type")
    public ResponseMsg getDicByType(HttpServletRequest request){
        ResponseMsg responseMsg = new ResponseMsg();
        try{
            String type = StringUtil.obj2Str(request.getParameter("type"));
            List<Map<String,String>> outMapList = new ArrayList<>();
            List<Dic> list = dicService.getDicByType(type);
            list.forEach(o -> {
            	Map<String,String> outMap = new HashMap<>();
            	outMap.put("code", o.getCode());
            	outMap.put("name", o.getName());
            	outMapList.add(outMap);
            });
            responseMsg.setData(outMapList);
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
}
