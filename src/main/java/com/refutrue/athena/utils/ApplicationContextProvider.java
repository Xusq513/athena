package com.refutrue.athena.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.refutrue.athena.utils.template.annotation.Order;

/**
 * 
* <p>Title: ApplicationContextProvider</p>  
* <p>Description: </p>  
* @author Xusq  
* @date 2018年11月9日
 */
@Component
public class ApplicationContextProvider
    implements ApplicationContextAware
{
    /**
     * 上下文对象实例
     */
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * 获取applicationContext
     * @return
     */
    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 通过name获取 Bean.
     * @param name
     * @return
     */
    public Object getBean(String name){
        return getApplicationContext().getBean(name);
    }

    /**
     * 通过class获取Bean.
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getBean(Class<T> clazz){
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 通过name,以及Clazz返回指定的Bean
     * @param name
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getBean(String name,Class<T> clazz){
        return getApplicationContext().getBean(name, clazz);
    }
    
    /**
     * 通过接口获取所有的实现注入类
     * @param cls  eg:IBuilder.class
     * @return     eg:{controllerBuilder=com.refutrue.athena.utils.template.builder.ControllerBuilder@39909d1a,....}
     */
    public <T> Map<String, T> getBeanList(Class<T> cls){
    	return getApplicationContext().getBeansOfType(cls);
    }
    
    public <T> Map<String, T> getBeanMapByOrder(Class<T> cls){
    	Map<String, T> returnMap = new LinkedHashMap<String, T>();
    	Map<String, T> map = getApplicationContext().getBeansOfType(cls);
    	List<TempBean<T>> list = new ArrayList<>();
    	for(String key : map.keySet()) {
    		TempBean<T> tempBean = new TempBean<>();
    		T t = map.get(key);
    		Order order = t.getClass().getAnnotation(Order.class);
    		if(order != null) {
    			tempBean.order = order.value();
    		}else {
    			tempBean.order = 9999;
    		}
    		tempBean.beanName = key;
    		tempBean.t = t;
    		list.add(tempBean);
    	}
    	Collections.sort(list, (f1,f2) -> {
    		return f1.order - f2.order;
    	});
    	list.forEach(f -> {
    		returnMap.put(f.beanName, f.t);
    	});
    	return returnMap;
    } 
    
    public <T> List<T> getBeanListByOrder(Class<T> cls){
    	Map<String, T> map = getBeanMapByOrder(cls);
    	return new ArrayList<T>(map.values()) ;
    }
    
    class TempBean<T>{
    	public String beanName;
    	
    	public T t;
    	
    	public int order;
    }
}

