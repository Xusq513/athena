package com.refutrue.athena.service;

import com.refutrue.athena.bean.User;
import com.refutrue.athena.mapper.DemoMapper;
import com.refutrue.athena.utils.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: Michael Xu
 * @Date: 2018/11/5 10:36
 * @Description:
 */
@Service
public class DemoServiceImpl extends BaseServiceImpl<User> {

    @Autowired
    private DemoMapper demoMapper;

    @Autowired
    public DemoServiceImpl(DemoMapper demoMapper) {
        super.baseMapper = demoMapper;
    }

}
