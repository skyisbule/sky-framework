package org.skyisbule.framwork.mvc.test;

import com.alibaba.fastjson.JSONObject;
import org.skyisbule.framwork.mvc.annotation.Controller;
import org.skyisbule.framwork.mvc.annotation.MapURL;
import org.skyisbule.framwork.mvc.annotation.ResponseBody;
import org.skyisbule.framwork.mvc.structure.RequestMethod;

import org.skyisbule.framwork.mvc.structure.Data;

@Controller
public class test {


    @MapURL(value = "/get",RequestMethod = RequestMethod.GET)
    public String get(Data data){
        System.out.println(data.getKey("sky"));
        return "show";
    }

    @MapURL(value="foward")
    public String foward(){
        return "page/succ";
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
        System.out.println("successsssssss");
        return jsonObject;

    }


}
