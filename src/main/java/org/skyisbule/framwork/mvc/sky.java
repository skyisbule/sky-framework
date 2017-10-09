package org.skyisbule.framwork.mvc;

import org.apache.catalina.LifecycleException;
import org.skyisbule.framwork.mvc.classcollection.ClassCollection;
import org.skyisbule.framwork.mvc.servlet.DspatcherServlet;

import javax.servlet.ServletException;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.startup.Tomcat.FixContextListener;
import org.skyisbule.framwork.mvc.structure.MethodPro;
import org.skyisbule.framwork.mvc.utils.Config;

import org.skyisbule.framwork.mvc.utils.FileUtils;
import org.skyisbule.framwork.mvc.utils.doGet;
import org.skyisbule.framwork.mvc.servlet.staticServlet;

import java.util.Map;
import java.util.Set;

/**
 * Created by skyisbule on 2017/10/3.
 * 整个框架的启动器
 */


public class sky {

    private  Map<String,MethodPro> methodMap;
    private  Set<Class<?>>         classSet;
    private  Map<String,Class<?>>  classMap;
    public static Map<String,String> htmlMap;

    private int port = 81;
    //Tomcat工作缓存区，相当于安装板的root目录所在地，在这里放在工程目录下。
    private String docBase = Config.getProPath()+"templates";



    @SuppressWarnings("unchecked")
    public void init(){
        ClassCollection.scanClassSetByPackage(Config.getAnnoClassConfig("base-package"));
        this.methodMap=ClassCollection.getMethodMap();
        this.classMap =ClassCollection.getClassMap();
        this.classSet = ClassCollection.getClassSet();
        FileUtils.makDir(Config.getProPath(),"templates");
        FileUtils.makDir(Config.getProPath(),"static");
        htmlMap=FileUtils.getHtml(Config.getProPath()+"templates\\");
    }

    public Map getMethodMap(){return this.methodMap;}
    public Set getClassSet(){return this.classSet;}
    public Map getClassMap(){return this.classMap;}

    public void setPort(int port){this.port=port;}

    public void get(String url,doGet get){}

    public static sky me(){
        return new sky();
    }

    public void start() throws ServletException, LifecycleException {
        Tomcat tomcat = new Tomcat();
        //端口号
        tomcat.setPort(port);
        //tomcat目录
        tomcat.setBaseDir(docBase);
        tomcat.getHost().setAutoDeploy(false);
        tomcat.getHost().setAppBase(docBase);

        //设置jsp存放目录
        tomcat.addWebapp("/jsp",docBase);

        String contextPath = "";
        StandardContext context = new StandardContext();
        context.setPath(contextPath);
        context.addLifecycleListener(new FixContextListener());
        tomcat.getHost().addChild(context);

        tomcat.addServlet(contextPath, "homeServlet", new DspatcherServlet());
        context.addServletMappingDecoded("/*", "homeServlet");


        tomcat.addServlet("","staticServlet",new staticServlet());
        context.addServletMappingDecoded("/static/*","staticServlet");

        tomcat.start();
        tomcat.getServer().await();
    }
}
