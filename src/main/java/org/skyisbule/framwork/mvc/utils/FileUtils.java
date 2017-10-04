package org.skyisbule.framwork.mvc.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Set;

/**
 * Created by skyisbule on 2017/10/1.
 */
public class FileUtils {
    public static void getClassSet(String path,Set<Class<?>> classSet,String packageName) {
        File file=new File(path.replace("\\","/"));
        for(String fileName:file.list()) {
            String newPath=path+File.separator+fileName;
            if(new File(newPath).isDirectory()) {
                getClassSet(newPath, classSet,packageName);
            }else{
                if(newPath.endsWith(".class"))
                {
                    try {
                        String className=newPath.substring(newPath.lastIndexOf(StringUtils.modifyPackagePath(packageName))).replace(File.separator,".").replace(".class","");
                        //System.out.println(className);
                        Class<?> cls=Class.forName(className);//,false,Thread.currentThread().getContextClassLoader());
                        classSet.add(cls);
                    } catch (ClassNotFoundException e) {
                        continue;
                    }

                }
            }
        }
    }

    public static void makDir(String path,String name){
        File file = new File(path+name);
        if (!file.exists()){
            file.mkdir();
        }
    }

    public static String getFileContent(File file){
        StringBuilder result = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                result.append(System.lineSeparator()+s);
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result.toString();
    }

    public static void getHtml(String path){
        File templagtes=new File(path);
        for(String file:templagtes.list()){

        }
    }
}
