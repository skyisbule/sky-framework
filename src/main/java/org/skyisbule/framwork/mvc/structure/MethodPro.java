package org.skyisbule.framwork.mvc.structure;

import java.lang.reflect.Method;

/**
 * 此类用来存储请求的信息
 * 在servlet初始化扫描时添加
 */
public class MethodPro {
    private String name;
    private String url;
    private String urlStyle;
    private Method method;
    private Boolean isAjax;

    public MethodPro(Method method, String url, String urlStyle,boolean isAjax) {
        this.method = method;
        this.url = url;
        this.urlStyle = urlStyle;
        this.isAjax = isAjax;
        this.name=method.getName();
    }



    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlStyle() {
        return urlStyle;
    }

    public void setUrlStyle(String urlStyle) {
        this.urlStyle = urlStyle;
    }


    public Boolean getAjax() {
        return isAjax;
    }

    public void setAjax(Boolean ajax) {
        isAjax = ajax;
    }
}
