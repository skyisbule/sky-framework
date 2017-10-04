package org.skyisbule.framwork.mvc.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by skyisbule on 2017/10/1.
 * 文件组件
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

    static String getFileContent(File file){
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

    @SuppressWarnings("unchecked")
    public static Map getHtml(String path){
        Map htmlMap = new HashMap<String,String>();
        File templagtes = new File(path);
        String[] htmlList = templagtes.list();

        if (htmlList!=null)
        for(String file:htmlList){
            if (file.endsWith(".html"))
            htmlMap.put(file.substring(0,file.lastIndexOf('.')),getFileContent(new File(path+file)));
        }
        return htmlMap;
    }

    @SuppressWarnings("unchecked")
    public static void getStaticFile(String path,Map staticMap){

        File files = new File(path);
        String[] fileList = files.list();
        if (fileList!=null)
        for(String file:fileList){
            if (!new File(path+file).isDirectory()){
                //key:  /demo.css  value:content
                staticMap.put(path.substring(path.indexOf("static")+6)+file,getFileContent(new File(path+file)));
            }else{
                String newPath = path+file+"/";
                getStaticFile(newPath,staticMap);
            }
        }

    }
}
