package org.skyisbule.framwork.mvc.utils;

/**
 * Created by skyisbule on 2017/10/1.
 */


import java.lang.reflect.Method;
import java.util.Map;

import org.skyisbule.framwork.mvc.structure.Data;



public class ReflectProcessor {

    public static Object parseMethod(final Method method,final Class<?> clazz, String methodname, Object[] value,Map map) throws Exception {
        /*
        if(value == null){
            value = new Object[]{};
        }
        Object o =null;
        final Object obj = clazz.getConstructor(new Class[] {}).newInstance(new Object[] {});
        o = method.invoke(obj,value);
        return o;
        */
        Object o = null;
        final Object obj = clazz.getConstructor(new Class[] {}).newInstance(new Object[] {});
        o = method.invoke(obj,new Data(map));
        return o;
    }
}
