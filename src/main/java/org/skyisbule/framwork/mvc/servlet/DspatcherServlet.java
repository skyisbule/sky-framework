
package org.skyisbule.framwork.mvc.servlet;

import org.skyisbule.framwork.mvc.classcollection.ClassCollection;

import org.skyisbule.framwork.mvc.param.HandlerMapping;
import org.skyisbule.framwork.mvc.structure.MethodPro;
import org.skyisbule.framwork.mvc.utils.Config;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.Map;
/**
 * 请求拦截器
 * 所有请求的中心
 **/


public class DspatcherServlet extends HttpServlet {

	/**
	 * @methodProMap 每个请求对应的封装类=>包括url、请求方法、返回值等
	 * @classMap 用来存储每个请求uri对应的处理类
	 */
	private Map<String,MethodPro> methodProMap =null;
	private Map<String,Class<?>> classMap=null;
	public static String proPath=Config.getProPath();
	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		System.out.println(proPath);
		//System.out.println(Config.getAnnoClassConfig("base-package"));
		//ClassCollection.scanClassSetByPackage(Config.getAnnoClassConfig("base-package"));//初始化配置下的 @Controller类
		methodProMap = ClassCollection.getMethodMap();//拿到封装的每个类方法的属性
		classMap=ClassCollection.getClassMap();//拿到每个url对应的类
		System.out.println("初始化成功！\n以下路由被成功加载");
		methodProMap.forEach((k,v)->System.out.println("url:"+k));

	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String pathInfo=req.getRequestURI();
		System.out.println("pathinfo:"+pathInfo);
		String key = req.getRequestURI();
		if (methodProMap.containsKey(key)) {
			HandlerMapping.HandlerMapping(req,resp,methodProMap,key,classMap.get(key));//转发到映射器进行映射处理
			return ;

		}
		//用户请求url没有映射404处理
			resp.sendError(404);
	}
}

