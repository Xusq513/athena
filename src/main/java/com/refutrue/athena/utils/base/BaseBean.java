package com.refutrue.athena.utils.base;

import java.util.Date;

import com.refutrue.athena.utils.component.annotation.Calender;
import com.refutrue.athena.utils.template.annotation.Column;

import lombok.Data;

/**
 * @Auther: Michael Xu
 * @Date: 2018/11/6 16:56
 * @Description:
 */
@Data
public class BaseBean {

    @Column(columnName="id", columnType = "INT",isPrimaryKey = true,isAutoIncrement = true)
    private Integer id;

    private String operateUser;
    
    @Calender(javaFormatter="yyyy-MM-dd hh:mm:ss")
    private Date operateTime;

    @Calender(javaFormatter="yyyy-MM-dd hh:mm:ss")
    private Date createTime;
    
}
