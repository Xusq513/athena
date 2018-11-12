package com.refutrue.athena.utils.component.convert;

import java.lang.reflect.Field;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.refutrue.athena.utils.ApplicationContextProvider;
import com.refutrue.athena.utils.StringUtil;
import com.refutrue.athena.utils.component.annotation.Select;
import com.refutrue.athena.utils.component.interfaces.ISelect;
import com.refutrue.athena.utils.exception.AthenaException;
import com.refutrue.athena.utils.template.annotation.Order;

@Order(5)
@Component
public class SelectConvert implements IConvert{
	
	@Autowired
	private ApplicationContextProvider context;

	@Override
	public String convert(Field f, Object o) throws AthenaException {
		String returnStr = "";
		if(o != null) {
			// 如果是选项标签，会进行转化
			Select select = f.getAnnotation(Select.class);
			if(select != null) {
				ISelect selectImpl = (ISelect) context.getBean(select.component());
				 Map<String,String> codeMap = selectImpl.select(select.type());
				 if(StringUtil.isNotEmptyOrNull(o.toString())) {
					 StringBuilder sb = new StringBuilder();
					 String[] codes = o.toString().split(",");
					 for(String code : codes) {
						 String value = StringUtil.obj2Str(codeMap.get(code));
						 sb.append(value);
						 sb.append(",");
					 }
					 sb.setLength(sb.length() - 1);
					 returnStr = sb.toString();
				 }
			}
		}
		return returnStr;
	}

}
