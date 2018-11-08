package com.refutrue.athena.bean;

import java.io.Serializable;

import com.refutrue.athena.utils.base.BaseBean;
import com.refutrue.athena.utils.template.annotation.Column;
import com.refutrue.athena.utils.template.annotation.Ignore;
import com.refutrue.athena.utils.template.annotation.Title;
import com.refutrue.athena.utils.template.annotation.Validate;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Title("字典信息维护")
@Data
@EqualsAndHashCode(callSuper=false)
public class Dic extends BaseBean implements Serializable {

	@Ignore
	private static final long serialVersionUID = -8987334455108893179L;

	@Title("编码值")
	@Validate(required = true, length = 50)
	@Column(columnType = "VARCHAR(50)")
	private String code;

	@Title("显示值")
	@Validate(required = true, length = 100)
	@Column(columnType = "VARCHAR(100)")
	private String name;

	@Title("类型")
	@Validate(required = true, length = 50)
	@Column(columnType = "VARCHAR(50)")
	private String type;

	@Title("描述")
	@Validate(length = 500)
	@Column(columnType = "VARCHAR(500)")
	private String description;

	@Title("备注字段一")
	@Validate(length = 50)
	@Column(columnType = "VARCHAR(50)")
	private String bak1;

	@Title("备注字段二")
	@Validate(length = 50)
	@Column(columnType = "VARCHAR(50)")
	private String bak2;

	@Title("备注字段三")
	@Validate(length = 50)
	@Column(columnType = "VARCHAR(50)")
	private String bak3;

	@Column(columnType = "INT")
	private Integer dicTypeId;

}
