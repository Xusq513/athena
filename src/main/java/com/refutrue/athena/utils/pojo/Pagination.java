package com.refutrue.athena.utils.pojo;

import java.util.List;
import java.util.Map;

/**
 * @Auther: Michael Xu
 * @Date: 2018/11/2 13:05
 * @Description: 分页poji
 */
public class Pagination<T> {

    /**
     * 数据列表
     */
    private List<Map<String,String>> list ;

    /**
     * 数据总数
     */
    private String count;


    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

	public List<Map<String, String>> getList() {
		return list;
	}

	public void setList(List<Map<String, String>> list) {
		this.list = list;
	}
    
    
}
