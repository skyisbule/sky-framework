package test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.startup.Tomcat.FixContextListener;

class test extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("request scheme: " + req.getScheme());
        resp.getWriter().print("hello tomcat");
    }
}

public class run{
    static final int port = 9080;
    static final String docBase = "d:/tmp/tomcat";
    public static void main(String[] args) throws Exception {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(port);
        tomcat.setBaseDir(docBase);
        tomcat.getHost().setAutoDeploy(false);

        String contextPath = "/book";
        StandardContext context = new StandardContext();
        context.setPath(contextPath);
        context.addLifecycleListener(new FixContextListener());
        tomcat.getHost().addChild(context);

        tomcat.addServlet(contextPath, "homeServlet", new test());
        context.addServletMappingDecoded("/home", "homeServlet");
        tomcat.start();
        tomcat.getServer().await();
    }
}