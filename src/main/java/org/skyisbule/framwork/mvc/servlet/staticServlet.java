package org.skyisbule.framwork.mvc.servlet;

/**
 * Created by ZDNF on 2017/10/3.
 */
import org.skyisbule.framwork.mvc.utils.Config;
import org.skyisbule.framwork.mvc.utils.FileUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;


public class staticServlet extends HttpServlet {
    @SuppressWarnings("unchecked")
    private static Map<String,String> staticFile = new HashMap();


    public void init() throws ServletException {
        FileUtils.getStaticFile(Config.getProPath()+"static/",staticFile);
    }

    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String key = req.getRequestURI().replace("/static","");
        /*
        System.out.println(key);
        for (String k:staticFile.keySet()){
            System.out.println(k);
        }*/
        if (staticFile.containsKey(key)){
            resp.getWriter().print(staticFile.get(key));
            return;
        }
        resp.sendError(404);
    }

}