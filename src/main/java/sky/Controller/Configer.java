package sky.Controller;

/**
 * Created by skyisbule on 2017/9/25.
 * 读取配置文件进行初始化
 */
public interface Configer {
    String CONFIG_FILE = "sky.propertises";
    String JDBC_DRIVER = "sky.jdbc.driver";
    String JDBC_URL = "sky.jdbc.url";
    String JDBC_USERNAME = "sky.jdbc.username";
    String JDBC_PASSWORD = "sky.jdbc.password";

    String APP_BASE_PACKAGE = "sky.app.base_package";
    String APP_JSP_PATH = "sky.app.jsp_path";
    String APP_ASSET_PATH = "sky.app.asset_path";
}
