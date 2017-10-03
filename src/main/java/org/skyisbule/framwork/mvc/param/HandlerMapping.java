package org.skyisbule.framwork.mvc.param;

import org.skyisbule.framwork.mvc.servlet.DspatcherServlet;
import org.skyisbule.framwork.mvc.structure.MethodPro;
import org.skyisbule.framwork.mvc.utils.CollectionUtils;
import org.skyisbule.framwork.mvc.utils.ReflectProcessor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * Created by skyisbule on 2017/10/1.
 */
public class HandlerMapping {

    public static void  HandlerMapping(HttpServletRequest req,
                                       HttpServletResponse resp,
                                       Map<String,MethodPro> methodProMap,
                                       String key, Class reqClass) {

        try {
        List<String> paramlist = MethodResolver.getMethodNames(methodProMap.get(key).getMethod().getDeclaringClass().getName(), key);
        //拿到用户请求值
        Map<String,String[]> params = req.getParameterMap();
        //判断应该用哪个处理器，即用哪个MapUrl函数进行处理
        MethodPro methodPro = methodProMap.get(key);
        //获取请求
        Method method = methodPro.getMethod();
        List<String> classNames = CollectionUtils.classArrToStringList(method.getParameterTypes());
        Object[] invokeParamVulue = MethodResolver.paramarray(paramlist, classNames, req, resp, params);
        //获取返回类型
        Method urlmethod = methodProMap.get(key).getMethod();
        //获取请求类型
        String reqtype = req.getMethod().toUpperCase();
        //获取请求属于哪个类
        //Class reqClass =
            //http请求处理
            if(methodPro.getUrlStyle().equals("POST")){
                    if(!reqtype.equals("POST")) {
                        resp.sendError(405);
                        return;
                    }
            }

            //String返回方法参数类型处理
            //同时处理GET和POST请求
            if (urlmethod.getReturnType().getName().equals("java.lang.String")) {
                //获取要返回的html文件
                String fileName = ReflectProcessor.parseMethod(method,reqClass, key, invokeParamVulue,params).toString();
                //resp.getWriter().print("hello");

                File test = new File(DspatcherServlet.proPath+"templates/" + fileName + ".jsp");

                System.out.println(test.exists());
                req.getRequestDispatcher(DspatcherServlet.proPath+"templates/" + fileName + ".jsp").forward(req, resp);
                return;

            //ajax接口处理
            } else if (methodProMap.get(key).getAjax()) {
                Object o = ReflectProcessor.parseMethod(method,reqClass, key, invokeParamVulue,params);
                resp.getWriter().print(o);
                return;
            }

            ReflectProcessor.parseMethod(method,reqClass, key, invokeParamVulue,params);
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
