package org.skyisbule.framwork.mvc.structure;

import java.util.Map;

/**
 * Created by skyisbule on 2017/10/3.
 * 用来处理用户请求值
 * 因为存在复选框或者一个name对应多个value的情况，所以使用String[]
 */
public class Data {
    public Map<String,String[]> data=null;

    public Data(Map map){
        this.data=map;
    }

    public void setData(Map map){
        this.data=map;
    }

    public String[] getAll(String key){
        return data.get(key);
    }

    public String getKey(String key){
        return data.get(key)[0];
    }
}
