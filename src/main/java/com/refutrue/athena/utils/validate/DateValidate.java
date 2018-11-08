package com.refutrue.athena.utils.validate;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.refutrue.athena.utils.DateUtil;
import com.refutrue.athena.utils.ReflectUtil;
import com.refutrue.athena.utils.StringUtil;
import com.refutrue.athena.utils.exception.AthenaException;
import com.refutrue.athena.utils.template.annotation.Validate;

@Component("dateValidate")
public class DateValidate extends ValidateAdapter{

	@Override
	public <T> void check(Class<T> cls, T t) throws AthenaException {
		List<Field> fieldList = new ArrayList<>();
		ReflectUtil.getAllFieldByBean(cls, fieldList);
		fieldList.forEach(f ->{
			Validate validate = f.getAnnotation(Validate.class);
			if(validate != null) {
				String titleName = getTitleName(f);
				Object o = null;
				try {
					f.setAccessible(true);
					o = f.get(t);
				} catch (Exception e) {
					e.printStackTrace();
				} 
				String startTimeStr = validate.startTime();
				String endTimeStr = validate.endTime();
				if(o != null && f.getType() == Date.class) {
					Date curDate = (Date)o;
					if(StringUtil.isNotEmptyOrNull(startTimeStr)) {
						Date startTime = convert(startTimeStr);
						if(startTime != null && DateUtil.dayDiff(curDate, startTime) < 0) {
							throw new AthenaException("提交的数据中字段【" + titleName + "】小于最小起始日期【" + DateUtil.date2Str(startTime, "yyyy-MM-dd hh:mm:ss") + "】");
						}
					}
					if(StringUtil.isNotEmptyOrNull(endTimeStr)) {
						Date endTime = convert(endTimeStr);
						if(endTimeStr != null && DateUtil.dayDiff(curDate, endTime) > 0) {
							throw new AthenaException("提交的数据中字段【" + titleName + "】大于最大结束日期【" + DateUtil.date2Str(endTime, "yyyy-MM-dd hh:mm:ss") + "】");
						}
					}
				}
				
			}
		});
	}
	
	/**
	 * 配置日期的个性化转换
	 * @param str
	 * @return
	 */
	private Date convert(String str) {
		Date date = null;
		if(str.equals("now")) {
			date = new Date();
		}else {
			try {
				date = DateUtil.stringtoDate(str, "yyyy-MM-dd hh:mm:ss");
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		return date;
	}

}
