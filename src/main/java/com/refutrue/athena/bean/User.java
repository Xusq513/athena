package com.refutrue.athena.bean;

import com.refutrue.athena.utils.base.BaseBean;
import com.refutrue.athena.utils.template.annotation.Title;
import com.refutrue.athena.utils.template.annotation.Validate;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Author: Michael Xu
 * @Date: 2019/3/14 16:17
 * @Description:
 */
@Title("用户基本信息")
@Data
@EqualsAndHashCode(callSuper=false)
public class User extends BaseBean implements Serializable {

    @Title("登录账号")
    @Validate(required=true)
    private String loginId;

    @Title("密码")
    @Validate(required=true)
    private String passwd;
}
