package com.refutrue.athena.utils;



/** 
 * @description
 * @author     作者：Xusq
 * @create     创建时间：2018-5-22
 * @update     修改时间：2018-5-22
 */

public class ResponseMsg {
	
	private boolean success = true;
	
	private String code = "0000";
	
	private String message = "操作成功";
	
	private Object data;

	public boolean isSuccess() {

		return success;
	}

	public String getCode() {

		return code;
	}

	public String getMessage() {

		return message;
	}

	public Object getData() {

		return data;
	}

	
	public void setSuccess(boolean success) {
	
		this.success = success;
	}
	public void setCode(String code) {
	
		this.code = code;
	}
	public void setMessage(String message) {
	
		this.message = message;
	}
	public void setData(Object data) {
	
		this.data = data;
	}
	
}
