package com.refutrue.athena.utils.template.builder;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: Michael Xu
 * @Date: 2018/11/6 16:26
 * @Description:
 */
@Component
public class BuilderFactory {

    private static List<IBuilder> builderList = new ArrayList<>();

    static {
        builderList.add(new ResourceMapperBuilder());
        builderList.add(new MapperBuilder());
        builderList.add(new ServiceBuilder());
        builderList.add(new ControllerBuilder());
        builderList.add(new DBScriptBuilder());
    }

    public void execute(Class<?> cls){
        builderList.forEach(builder ->{
            builder.execute(cls);
        });
    }
}
