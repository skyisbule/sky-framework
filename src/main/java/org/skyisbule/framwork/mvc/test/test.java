package org.skyisbule.framwork.mvc.test;

import com.alibaba.fastjson.JSONObject;
import org.skyisbule.framwork.mvc.annotation.Controller;
import org.skyisbule.framwork.mvc.annotation.MapURL;
import org.skyisbule.framwork.mvc.annotation.ResponseBody;
import org.skyisbule.framwork.mvc.structure.RequestMethod;

import org.skyisbule.framwork.mvc.structure.Data;

import java.util.ArrayList;
import java.util.List;

@Controller
public class test {


    @MapURL(value = "/get",RequestMethod = RequestMethod.GET)
    public String get(Data data){
        System.out.println(data.getKey("sky"));
        return "demo";
    }

    @MapURL(value = "/", RequestMethod = RequestMethod.GET)
    @ResponseBody
    public JSONObject getUser(Data data){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userid",1);
        return jsonObject;

    }

    @MapURL(value = "/a", RequestMethod = RequestMethod.GET)
    @ResponseBody
    public JSONObject getaUser(Data data){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userid",1);
        return jsonObject;

    }


    @MapURL(value = "/b", RequestMethod = RequestMethod.GET)
    @ResponseBody
    public String getr(Data data){
        List<String> k = new ArrayList<>();
        k.add("sss");
        k.add("sss");
        return k.toString();

    }

}
