package org.skyisbule.framwork.mvc.param;

import org.skyisbule.framwork.mvc.structure.MethodPro;
import org.skyisbule.framwork.mvc.utils.CollectionUtils;
import org.skyisbule.framwork.mvc.utils.ReflectProcessor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * Created by skyisbule on 2017/10/1.
 */
public class HandlerMapping {

    public static void  HandlerMapping(HttpServletRequest req, HttpServletResponse resp, Map<String,MethodPro> methodProMap, String key, Class reqClass) {

        try {
        List<String> paramlist = MethodResolver.getMethodNames(methodProMap.get(key).getMethod().getDeclaringClass().getName(), key);
        //拿到用户请求变量
        Map params = req.getParameterMap();
        //判断应该用哪个处理器
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
                       // resp.getWriter().print("405 not allowed");
                        return;
                    }
            }

            //String返回方法参数类型处理
            if (urlmethod.getReturnType().getName().equals("java.lang.String")) {

                String uri = ReflectProcessor.parseMethod(method,reqClass, key, invokeParamVulue).toString();
                req.getRequestDispatcher("WEB-INF/" + uri + ".html").forward(req, resp);
                return;

            //ajax接口处理
            } else if (methodProMap.get(key).getAjax()) {
                Object o = ReflectProcessor.parseMethod(method,reqClass, key, invokeParamVulue);
                resp.getWriter().print(o);
                return;
            }

            ReflectProcessor.parseMethod(method,reqClass, key, invokeParamVulue);
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
