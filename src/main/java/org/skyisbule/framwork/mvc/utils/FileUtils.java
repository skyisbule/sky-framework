package org.skyisbule.framwork.mvc.utils;

import java.io.File;
import java.util.Set;

/**
 * Created by skyisbule on 2017/10/1.
 */
public class FileUtils {
    public static void getClassSet(String path,Set<Class<?>> classSet,String packageName)
    {
        File file=new File(path.replace("\\","/"));
        for(String fileName:file.list())
        {
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
}
