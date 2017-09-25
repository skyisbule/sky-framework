package sky.Controller;

import sky.Util.PropsUtil;

/**
 * Created by skyisbule on 2017/9/25.
 * 用来读取配置文件
 */
public final class Config{
    //实例化一个读取配置
    private static final PropsUtil propsutil = new PropsUtil(Configer.CONFIG_FILE);
    //读取JDBC驱动设置
    public static String getJdbcDriver(){
        return propsutil.getString("jdbc.driver",Configer.JDBC_DRIVER);
    }
    //读取JDBC URL
    public static String getJdbcUrl(){
        return propsutil.getString("jdbc.url",Configer.JDBC_URL);
    }
    //读取JDBC用户名
    public static String getJdbcUsername(){
        return propsutil.getString("jdbc.usrename",Configer.JDBC_USERNAME);
    }
    //读取JDBC密码
    public static String getJdbcPassword(){
        return propsutil.getString("jdbc.password",Configer.JDBC_PASSWORD);
    }
    //读取基础包名
    public static String getAppBasePackage(){
        return propsutil.getString("app.base_package",Configer.APP_BASE_PACKAGE);
    }
    //读取JSP路径
    public static String getAppJspPath(){
        return propsutil.getString("app.jsp_path",Configer.APP_JSP_PATH);
    }
    //读取静态资源路径
    public static String getAppAssetPath(){
        return propsutil.getString("app.asset_path",Configer.APP_ASSET_PATH);
    }
}
