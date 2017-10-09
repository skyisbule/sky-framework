package org.skyisbule.framwork.mvc.structure;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by skyisbule on 2017/10/3.
 * 用来处理用户请求值
 */
public class Data {
    private Map<String,String[]> data;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private Cookie[] cookies;

    public Data(Map map, HttpServletRequest request, HttpServletResponse response){
        this.data = map;
        this.request=request;
        this.response=response;
    }

    public Data(Map map){
        this.data = map;
    }

    public void setData(Map map){
        this.data=map;
    }

    public String[] getAll(String key){
        return data.get(key);
    }

    public String getKey(String key){
        if (data.containsKey("key"))
            return data.get("key")[0];
        System.out.println("没有找到该值："+key);
        return "0";
    }

    public String getIp() {
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public void setCookie(String key,String value){
        Cookie cookie = new Cookie(key,value);
        response.addCookie(cookie);
    }

    public void setCookie(String key,String value,int maxAge){
        Cookie cookie = new Cookie(key,value);
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    public String getCookie(String key){
        if (cookies==null){
            cookies = request.getCookies();
        }
        for (Cookie cookie : cookies){
            if (cookie.getName().equals(key))
                return cookie.getValue();
        }
        System.out.println("sky::没有找到cookie："+key);
        return "";
    }
}
