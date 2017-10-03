package org.skyisbule.framwork.mvc.structure;

import java.util.Map;

/**
 * Created by skyisbule on 2017/10/3.
 * 用来处理用户请求值
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
        return data.containsKey(key)?
                data.get(key)[0]: "没有找到该请求值："+key;
    }
}
