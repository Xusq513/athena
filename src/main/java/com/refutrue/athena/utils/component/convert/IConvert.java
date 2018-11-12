package com.refutrue.athena.utils.component.convert;

import java.lang.reflect.Field;

import com.refutrue.athena.utils.exception.AthenaException;

/**
 * 从数据库到前端列表显示转化接口
* <p>Title: IConvert</p>  
* <p>Description: </p>  
* @author Xusq  
* @date 2018年11月12日
 */
public interface IConvert {
	
	public String convert(Field f, Object o) throws AthenaException;
	
}
