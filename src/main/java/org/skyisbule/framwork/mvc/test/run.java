package org.skyisbule.framwork.mvc.test;

/**
 * Created by skyisbule on 2017/10/2.
 */
import org.skyisbule.framwork.mvc.servlet.DspatcherServlet;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.startup.Tomcat.FixContextListener;
import org.skyisbule.framwork.mvc.utils.Config;

public class run {
    static final int port = 81;
    //Tomcat工作缓存区，相当于安装板的root目录所在地，在这里放在工程目录下。
    static final String docBase = "C:\\Users\\ZDNF\\Desktop\\AisMVC-master\\target\\classes\\templates";
    //C:\Users\ZDNF\Desktop\AisMVC-master\target\classes\templates
    //webApp存放路径
    static final String jspPath = "C:\\Users\\ZDNF\\Desktop\\AisMVC-master\\target\\classes\\templates";
    public static void main(String[] args) throws Exception {
        Tomcat tomcat = new Tomcat();
        //端口号
        tomcat.setPort(port);
        //tomcat目录
        tomcat.setBaseDir(docBase);
        tomcat.getHost().setAutoDeploy(false);
        tomcat.getHost().setAppBase(docBase);

        //设置jsp存放目录
        tomcat.addWebapp("/jsp",jspPath);

        String contextPath = "";
        StandardContext context = new StandardContext();
        context.setPath(contextPath);
        context.addLifecycleListener(new FixContextListener());
        tomcat.getHost().addChild(context);

        tomcat.addServlet(contextPath, "homeServlet", new DspatcherServlet());
        context.addServletMappingDecoded("/*", "homeServlet");



        tomcat.start();
        tomcat.getServer().await();
    }
}

class HomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("request scheme: " + req.getScheme());
        resp.getWriter().print("hello tomcat");
    }
}