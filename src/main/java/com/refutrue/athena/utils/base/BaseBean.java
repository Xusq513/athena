package com.refutrue.athena.utils.base;

import com.refutrue.athena.utils.template.annotation.Column;
import com.refutrue.athena.utils.template.annotation.Title;

import java.util.Date;

/**
 * @Auther: Michael Xu
 * @Date: 2018/11/6 16:56
 * @Description:
 */
public class BaseBean {

    @Column(columnType = "INT",isPrimaryKey = true,isAutoIncrement = true)
    private Integer id;

    private String operateUser;

    private Date operateTime;

    private Date createTime;
}
