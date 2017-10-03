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

public class run {
    static final int port = 81;
    static final String docBase = "d:/tmp/tomcat";
    public static void main(String[] args) throws Exception {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(port);
        tomcat.setBaseDir(docBase);
        tomcat.getHost().setAutoDeploy(false);


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