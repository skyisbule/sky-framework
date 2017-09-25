package sky.Controller;

/**
 * Created by skyisbule on 2017/9/25.
 * 读取配置文件进行初始化
 */
public interface Configer {
    String CONFIG_FILE = "sky";
    String JDBC_DRIVER = "com.mysql.Driver";
    String JDBC_URL = "jdbc:mysql://locallhost:3306/sky";
    String JDBC_USERNAME = "root";
    String JDBC_PASSWORD = "111111";

    String APP_BASE_PACKAGE = "sky";
    String APP_JSP_PATH = "/WEB-INF/view/";
    String APP_ASSET_PATH = "/asset/";
}
