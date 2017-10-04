package org.skyisbule.framwork.mvc.servlet;

/**
 * Created by skyisbule on 2017/10/3.
 * 用于处理静态资源的返回
 * url为  /static/*
 */
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

// 扩展 HttpServlet 类
public class SkyServlet extends HttpServlet {

    public void init() throws ServletException {

    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException
    {

    }

    public void destroy() {
        // 什么也不做
    }
}