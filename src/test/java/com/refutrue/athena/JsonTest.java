package com.refutrue.athena;

import com.alibaba.fastjson.JSON;
import com.refutrue.athena.bean.Dic;

public class JsonTest {

	public static void main(String[] args) {
		Dic dic = new Dic();
		dic.setName("美元");
		dic.setType("币种");
		dic.setCode("USD");
		System.out.println(JSON.toJSON(dic));
	}
}
