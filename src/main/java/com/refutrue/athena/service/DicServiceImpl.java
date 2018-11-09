package com.refutrue.athena.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.refutrue.athena.bean.Dic;
import com.refutrue.athena.mapper.DicMapper;
import com.refutrue.athena.utils.CommonUtil;
import com.refutrue.athena.utils.RedisUtil;
import com.refutrue.athena.utils.StringUtil;
import com.refutrue.athena.utils.base.BaseServiceImpl;
import com.refutrue.athena.utils.component.interfaces.ISelect;
import com.refutrue.athena.utils.exception.AthenaException;

/**
 * @Author: Michael Xu
 * @DateTime: 2018-11-08 03:31:34
 * @Description: 字典信息维护çä¸å¡å±
 */
@Service("dicServiceImpl")
public class DicServiceImpl extends BaseServiceImpl<Dic> implements ISelect{
	
	public static final String REDIS_KEY = "KEY_DIC";
	
	@Autowired
	private RedisUtil redisUtil;

    @Autowired
    public DicServiceImpl(DicMapper dicMapper) {
        super.entityClass = Dic.class;
        super.baseMapper = dicMapper;
    }
    
    @Override
    @Transactional
    public void add(Dic t) throws AthenaException {
        baseMapper.insert(t);
        cacheList(t.getType());
    }

    @Override
    @Transactional
    public void update(Dic t) throws AthenaException {
        baseMapper.update(t);
        cacheList(t.getType());
    }

    @Override
    @Transactional
    public void delete(String id) throws AthenaException {
    	Dic dic = baseMapper.selectByPrimaryKey(Integer.valueOf(id));
        baseMapper.delete(Integer.valueOf(id));
        cacheList(dic.getType());
    }
    
    @SuppressWarnings("unchecked")
	public List<Dic> getDicByType(String type) throws AthenaException{
    	List<Dic> list = (List<Dic>) redisUtil.hget(REDIS_KEY, type);
    	if(CommonUtil.isNullList(list)) {
    		list = cacheList(type);
    	}
    	return list;
    }
    
    @Override
    public Map<String,String> select(String type){
    	 Map<String,String> outMap = new HashMap<>();
         List<Dic> list = getDicByType(type);
         list.forEach(o -> {
         	outMap.put(o.getCode(), o.getName());
         });
         return outMap;
    }
    
    public List<Map<String,String>> selectList(String type){
   	 List<Map<String,String>> outMapList = new ArrayList<>();
        List<Dic> list = getDicByType(type);
        list.forEach(o -> {
        	Map<String,String> outMap = new HashMap<>();
        	outMap.put("code", o.getCode());
        	outMap.put("name", o.getName());
        	outMapList.add(outMap);
        });
        return outMapList;
   }
    
    private List<Dic> cacheList(String type) {
    	if(StringUtil.isEmptyOrNull(type)) {
    		return new ArrayList<>();
    	}
    	Map<String,Object> inMap = new HashMap<>();
    	inMap.put("type", type);
    	List<Dic> list = baseMapper.selectAll(inMap);
    	redisUtil.hset(REDIS_KEY, type, list);
    	return list;
    }

}
