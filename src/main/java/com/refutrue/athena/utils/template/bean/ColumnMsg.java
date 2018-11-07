package com.refutrue.athena.utils.template.bean;

public class ColumnMsg {
	
	private String columnName;
	
	private String columnType;
	
	private String columnTitle;
	
	private boolean isPrimaryKey;
	
	private boolean isNotNull;
	
	private boolean isAutoIncrement;
	
	private String propertyName;
	
    private String sqlFormatter;
    
    private String javaFormatter;
    
    
	public String getJavaFormatter() {
		return javaFormatter;
	}

	public void setJavaFormatter(String javaFormatter) {
		this.javaFormatter = javaFormatter;
	}

	public String getSqlFormatter() {
		return sqlFormatter;
	}

	public void setSqlFormatter(String sqlFormatter) {
		this.sqlFormatter = sqlFormatter;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public boolean isAutoIncrement() {
		return isAutoIncrement;
	}

	public void setAutoIncrement(boolean isAutoIncrement) {
		this.isAutoIncrement = isAutoIncrement;
	}

	public boolean isPrimaryKey() {
		return isPrimaryKey;
	}

	public void setPrimaryKey(boolean isPrimaryKey) {
		this.isPrimaryKey = isPrimaryKey;
	}

	public boolean isNotNull() {
		return isNotNull;
	}

	public void setNotNull(boolean isNotNull) {
		this.isNotNull = isNotNull;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnType() {
		return columnType;
	}

	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}

	public String getColumnTitle() {
		return columnTitle;
	}

	public void setColumnTitle(String columnTitle) {
		this.columnTitle = columnTitle;
	}
	
	
}
