package com.refutrue.athena.utils.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.alibaba.fastjson.JSON;
import com.refutrue.athena.utils.ReflectUtil;
import com.refutrue.athena.utils.ResponseMsg;
import com.refutrue.athena.utils.StringUtil;
import com.refutrue.athena.utils.constants.WebContants;
import com.refutrue.athena.utils.exception.AthenaException;
import com.refutrue.athena.utils.pojo.Pagination;
import com.refutrue.athena.utils.validate.ValidateFactory;

/**
 * @Auther: Michael Xu
 * @Date: 2018/11/5 10:01
 * @Description:
 */
public abstract class BaseController<T> {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected BaseService<T> baseService;

    protected Class<T> entityClass;
    
    @Autowired
    private ValidateFactory ValidateFactory;

    @PostMapping
    public ResponseMsg add(HttpServletRequest request){
        ResponseMsg responseMsg = new ResponseMsg();
        String _json = request.getParameter(WebContants._JSON);
        T t;
        try{
            t = JSON.parseObject(_json,entityClass);
            ValidateFactory.valid(entityClass, t);
            baseService.add(t);
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

    @PutMapping
    public ResponseMsg update(HttpServletRequest request){
        ResponseMsg responseMsg = new ResponseMsg();
        String _json = request.getParameter(WebContants._JSON);
        T t;
        try{
            t = JSON.parseObject(_json,entityClass);
            ValidateFactory.valid(entityClass, t);
            baseService.update(t);
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

    @DeleteMapping
    public ResponseMsg delete(HttpServletRequest request){
        ResponseMsg responseMsg = new ResponseMsg();
        String id = request.getParameter(WebContants._ID);
        try{
            baseService.delete(id);
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

    @GetMapping
    public ResponseMsg getEntityById(HttpServletRequest request){
        ResponseMsg responseMsg = new ResponseMsg();
        String id = request.getParameter(WebContants._ID);
        try{
            T t = baseService.getEntityById(id);
            responseMsg.setData(ReflectUtil.bean2StringMap(entityClass, t));
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

    @GetMapping(value = "/all")
    public ResponseMsg getAllEntity(HttpServletRequest request){
        ResponseMsg responseMsg = new ResponseMsg();
        try{
            Map<String,Object> inMap = buildSelectMap(request);
            List<T> list = baseService.getAllEntityByCondition(inMap);
            responseMsg.setData(ReflectUtil.beans2Map(entityClass, list));
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
    public ResponseMsg getPaginationEntity(HttpServletRequest request){
        ResponseMsg responseMsg = new ResponseMsg();
        try{
            Map<String,Object> inMap = buildSelectMap(request);
            Pagination<T> list = baseService.getEntityPageByConditon(inMap);
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
