package org.skyisbule.framwork.mvc.test;

/**
 * Created by skyisbule on 2017/10/2.
 * 测试
 */
import org.skyisbule.framwork.mvc.sky;
import org.skyisbule.framwork.mvc.structure.Data;

public class run {

    public static void main(String[] args) throws Exception {
        sky run = sky.me();
        run.init();

        run.get("/aa",(Data data)->{
            System.out.println(data.getKey("sky"));
            //这里处理数据
            return "<html><body><strong>cnm</strong></body></html>";
        });

        run.start();
    }
}



