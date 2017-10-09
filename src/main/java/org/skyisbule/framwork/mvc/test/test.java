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

    /**
     * @MapURL 路由注解，代表该请求路径下，由此函数进行处理。
     * @param data Data对象为用户请求的封装，可以使用 getKey方法获取请求参数。
     * @return 返回demo.html的内容，对应的html文件应放在 resources/templates/目录下。
    **/
    @MapURL(value = "/")
    public String index(Data data){
        System.out.println("用户的请求值是："+data.getKey("sky"));
        return "demo";
    }

    /**
     * @ResponseBody 代表该方法的返回值为字符串，而非html文件。
     * data.getKey的返回值永远为String，要根据自己的需要转成相应的数据类型。
     * @return 的返回值可以为任意类型，但需要实现toString方法。
     * 示例请求路径：127.0.0.1/add?a=1&b=1
     * 浏览器可以看到 2
     **/
    @MapURL(value = "/add")
    @ResponseBody
    public int add(Data data){
        int a =Integer.parseInt(data.getKey("a"));
        int b =Integer.parseInt(data.getKey("b"));
        return a+b;
    }

    /**
     * 内置fastJson包，开发者可以方便的将实体类、list、map、set等轻松转为json格式。
     */
    @MapURL(value = "/json")
    @ResponseBody
    public JSONObject getUser(Data data){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userid",data.getKey("id"));
        return jsonObject;
    }

    /**
     *@RequestMethod 如果不写默认支持所有请求类型
     * 但无论怎样，获取参数时用data即可
     */
    @MapURL(value = "/array", RequestMethod = RequestMethod.GET)
    @ResponseBody
    public String getr(Data data){
        List<String> k = new ArrayList<>();
        k.add(data.getKey("a"));
        k.add(data.getKey("b"));
        return k.toString();
    }

}
