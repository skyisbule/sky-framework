## 一个轻便快捷的Java MVC开发框架

#### 前言
传统的java web开发时，我们大都使用ssh或ssm等"重量级框架",它们功能十分强大，性能也十分优秀，
但并不适合开发“小型”的web程序，因为他们太重了，光是搭建就要耗费好长的时间。而传统的servlet+jsp+tomcat
又太偏底层，加上大量的配置文件和部署难度，大大削减了新手的学习兴趣，对新手十分不友好。<br>
于是，我决定编写一个轻量级mvc框架，屏蔽对开发者不友好的部分，使一个哪怕只对java了解一点点的小白，
也能快速简便地开发出一个web应用。

#### 特性
1. 零依赖，只要在ide中导入唯一的jar包即可使用。
2. 摆脱臃肿，开发过程极简。
3. 低速的学习曲线，只需要对java有基础的了解即可快速上手。
4. Restful风格的路由设计
5. 高性能，100并发下tps 5w/s
6. 内嵌tomcat，免除一切部署烦恼。
7. 注解至上，零配置文件。
8. 内置json输出，简易开发API。
9. 封装请求Data，简易获取请求参数。


#### 功能代码演示

```Java

@Controller
public class test {

    /**
         * @MapURL 路由注解，代表该请求路径下，由此函数进行处理。
         * @param data Data对象为用户请求的封装，可以使用 getKey方法获取请求参数。
         * @return 返回demo.html的内容，对应的html文件应放在 resources/templates/目录下。
        **/
        @MapURL(value = "/")
        public String index(Data data){
            System.out.println("用户的请求值是："+data.getKey("sky"));
            return "demo";
        }
    
        /**
         * @ResponseBody 代表该方法的返回值为字符串，而非html文件。
         * data.getKey的返回值永远为String，要根据自己的需要转成相应的数据类型。
         * @return 的返回值可以为任意类型，但需要实现toString方法。
         * 示例请求路径：127.0.0.1/add?a=1&b=1
         * 浏览器可以看到 2
         **/
        @MapURL(value = "/add")
        @ResponseBody
        public int add(Data data){
            int a =Integer.parseInt(data.getKey("a"));
            int b =Integer.parseInt(data.getKey("b"));
            return a+b;
        }
    
        /**
         * 内置fastJson包，开发者可以方便的将实体类、list、map、set等轻松转为json格式。
         */
        @MapURL(value = "/json")
        @ResponseBody
        public JSONObject getUser(Data data){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("userid",data.getKey("id"));
            return jsonObject;
        }
    
        /**
         *@RequestMethod 如果不写默认支持所有请求类型
         * 但无论怎样，获取参数时用data即可
         */
        @MapURL(value = "/array", RequestMethod = RequestMethod.GET)
        @ResponseBody
        public String getr(Data data){
            List<String> k = new ArrayList<>();
            k.add(data.getKey("a"));
            k.add(data.getKey("b"));
            return k.toString();
        }
}

```
#### mvc框架功能介绍
* 注解实现
* @MapURL注解实现http请求路由
* 反射实现方法参数注入
* String返回类型方法转发请求
* @ResponseBody注解实现ajax接口
* 增加config.ini配置文件,实现定向动态扫描项目中的@Controller 类
* 增加注解参数RequestMethod 默认http请求类型,请求类型不合法返回405状态码
* 可以返回html、css、js等静态资源文件
* 对请求进行了封装
* 内嵌tomcat 极简部署