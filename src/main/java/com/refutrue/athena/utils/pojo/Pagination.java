package com.refutrue.athena.utils.pojo;

import java.util.List;

/**
 * @Auther: Michael Xu
 * @Date: 2018/11/2 13:05
 * @Description: 分页poji
 */
public class Pagination<T> {

    /**
     * 数据列表
     */
    private List<T> list ;

    /**
     * 数据总数
     */
    private String count;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
