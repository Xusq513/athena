package com.refutrue.athena.utils.base;

import java.util.Date;

import com.refutrue.athena.utils.template.annotation.Calender;
import com.refutrue.athena.utils.template.annotation.Column;

/**
 * @Auther: Michael Xu
 * @Date: 2018/11/6 16:56
 * @Description:
 */
public class BaseBean {

    @Column(columnName="id", columnType = "INT",isPrimaryKey = true,isAutoIncrement = true)
    private Integer id;

    private String operateUser;
    
    @Calender(javaFormatter="yyyy-MM-dd hh:mm:ss")
    private Date operateTime;

    @Calender(javaFormatter="yyyy-MM-dd hh:mm:ss")
    private Date createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOperateUser() {
		return operateUser;
	}

	public void setOperateUser(String operateUser) {
		this.operateUser = operateUser;
	}

	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
    
    
}
