package com.refutrue.athena.utils.template.builder;

import java.lang.reflect.Field;

import com.refutrue.athena.utils.StringUtil;
import com.refutrue.athena.utils.template.annotation.Column;
import com.refutrue.athena.utils.template.annotation.Ignore;
import com.refutrue.athena.utils.template.annotation.Table;
import com.refutrue.athena.utils.template.annotation.Title;
import com.refutrue.athena.utils.template.bean.ColumnMsg;
import com.refutrue.athena.utils.template.bean.GlobalConfig;
import com.refutrue.athena.utils.template.bean.TableMsg;
import com.refutrue.athena.utils.template.exception.TemplateException;

public abstract class BuilderAdapter implements IBuilder{

	
	public TableMsg collectionTableMsg(Class<?> cls) throws TemplateException{
		GlobalConfig globalConfig = GlobalConfig.getInstance().init();
		TableMsg tableMsg = new TableMsg();
		String dbType = globalConfig.getDbType();
		String tablePrefix = globalConfig.getTablePrefix();
		String columnPrefix = globalConfig.getColumnPrefix();
		String tableName = "";
		String tableTitle = "";
		String tableSpace = "";
		String tableEngine = "";
		// 先根据全局配置初始化
		tableName = getStringByHump(cls.getSimpleName(), tablePrefix,true);
		// TODO 先写死，后面改在GlobalConfig.properties中
		tableEngine = "InnoDB";
		// TODO 先不写，后面改在GlobalConfig.properties中
		tableSpace = "";
		Table table = cls.getAnnotation(Table.class);
		if(table != null) {
			if(StringUtil.isNotEmptyOrNull(table.tableName())) {
				tableName = StringUtil.obj2Str(table.tableName());
			}
			if(StringUtil.isNotEmptyOrNull(table.tableSpace())) {
				tableSpace = StringUtil.obj2Str(table.tableSpace());
			}
			if(StringUtil.isNotEmptyOrNull(table.tableEngine())) {
				tableEngine = StringUtil.obj2Str(table.tableEngine());
			}
		}
		Title tableTitleAnnotation = cls.getAnnotation(Title.class);
		if(tableTitleAnnotation != null) {
			tableTitle = tableTitleAnnotation.value();
		}else {
			tableTitle = cls.getSimpleName();
		}
		tableMsg.setEngine(tableEngine);
		tableMsg.setTableName(tableName);
		tableMsg.setTableSpace(tableSpace);
		tableMsg.setTableTitle(tableTitle);
		tableMsg.setDbType(dbType);
		Field[] fieldList = cls.getDeclaredFields();
		for(Field field : fieldList) {
			ColumnMsg columnMsg = new ColumnMsg();
			String columnName = "";
			String columnTitle = "";
			String columnType = "";
			boolean isPrimaryKey = false;
		    boolean isNotNull = false;
		    boolean isAutoIncrement = false;
		    Ignore ignore = field.getAnnotation(Ignore.class);
		    if(ignore != null) {
		    	continue;
		    }
		    // 先根据全局配置初始化
		    columnName = getStringByHump(field.getName(), columnPrefix,false);
	    	columnType = globalConfig.getTypeConvertRelMap().get(field.getType());
	    	if(StringUtil.isEmptyOrNull(columnType)) {
	    		throw new TemplateException("全局配置转换类型中没有支持Java类型【" + field.getType() + "】的转化，请修改配置！");
	    	}
		    Column column = field.getAnnotation(Column.class);
		    if(column != null) {
		    	if(StringUtil.isNotEmptyOrNull(column.columnName())) {
		    		columnName = column.columnName();
		    	}
		    	if(StringUtil.isNotEmptyOrNull(column.columnType())) {
		    		columnType = column.columnType();
		    	}
		    	isPrimaryKey = column.isPrimaryKey();
		    	isNotNull = column.isNotNull();
		    	if(isPrimaryKey) {
		    		isAutoIncrement = column.isAutoIncrement();
		    	}else {
		    		isAutoIncrement = false;
		    	}
		    		
		    }
		    Title columnTitleAnnotation = field.getAnnotation(Title.class);
		    if(columnTitleAnnotation != null) {
		    	columnTitle = columnTitleAnnotation.value();
		    }else{
		    	columnTitle = field.getName();
		    }
		    columnMsg.setColumnName(columnName);
		    columnMsg.setColumnTitle(columnTitle);
		    columnMsg.setColumnType(columnType);
		    columnMsg.setNotNull(isNotNull);
		    columnMsg.setPrimaryKey(isPrimaryKey);
		    columnMsg.setAutoIncrement(isAutoIncrement);
		    tableMsg.addColumn(columnMsg);
		}
		return tableMsg;
	}
	
	/**
	 * Project下Java和Resource目录地址获取
	 * @param basePackage
	 * @param dir
	 * @param fileName
	 * @return
	 */
	public String getProjectFilePath(String basePackage,String dir,String fileName) {
		StringBuilder sb = new StringBuilder();
		sb.append(basePackage.replaceAll("\\.", "/"));
		sb.append("/");
		sb.append(dir);
		sb.append("/");
		sb.append(fileName);
		return sb.toString();
	}
	
	/**
	 * 根据前缀和名称以驼峰法则进行转化：
	 * eg: str="tableName"  prefix="t"   result="t_table_name"
	 * @param str
	 * @param prefix
	 * @return
	 */
	private String getStringByHump(String str,String prefix,boolean isSplit) {
		String returnStr = "";
		char[] charArray = str.toCharArray();
		for(int i=0;i<charArray.length;i++) {
			if(i + 1 < charArray.length) {
				if(charArray[i+1] >= 'A' && charArray[i+1] <= 'Z') {
					returnStr += String.valueOf(charArray[i]).toLowerCase() + "_";
				}else {
					returnStr += String.valueOf(charArray[i]).toLowerCase() ;
				}
			}else {
				returnStr += String.valueOf(charArray[i]).toLowerCase() ;
			}
		}
		return prefix + (isSplit?"_":"") + returnStr;
	}
}
