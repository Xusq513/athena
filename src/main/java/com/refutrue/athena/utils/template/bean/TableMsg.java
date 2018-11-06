package com.refutrue.athena.utils.template.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 生成数据库文件的实体，贫血模型，目前只实现mysql的一个版本 
* <p>Title: TableMsg</p>  
* <p>Description: </p>  
* @author Xusq  
* @date 2018年11月5日
 */
public class TableMsg {
	
	private String dbType;
	
	private String tableName;
	
	private String tableTitle;
	
	/**
	 * TODO 暂时不开发，oracle数据库表空间
	 */
	private String tableSpace;
	
	/**
	 * TODO mysql引擎，现只默认是InnoDB
	 */
	private String engine = "InnoDB";
	
	private List<ColumnMsg> columnMsgList = new ArrayList<ColumnMsg>();
	
	public void addColumn(ColumnMsg columnMsg) {
		columnMsgList.add(columnMsg);
	}
	
	public void removeColumn(ColumnMsg columnMsg) {
		columnMsgList.remove(columnMsg);
	}

	public List<ColumnMsg> getColumnMsgList() {
		return columnMsgList;
	}

	public void setColumnMsgList(List<ColumnMsg> columnMsgList) {
		this.columnMsgList = columnMsgList;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableTitle() {
		return tableTitle;
	}

	public void setTableTitle(String tableTitle) {
		this.tableTitle = tableTitle;
	}

	public String getTableSpace() {
		return tableSpace;
	}

	public void setTableSpace(String tableSpace) {
		this.tableSpace = tableSpace;
	}

	public String getEngine() {
		return engine;
	}

	public void setEngine(String engine) {
		this.engine = engine;
	}

	public String getDbType() {
		return dbType;
	}

	public void setDbType(String dbType) {
		this.dbType = dbType;
	}
	
	
}
