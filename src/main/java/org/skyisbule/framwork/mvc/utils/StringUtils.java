package org.skyisbule.framwork.mvc.utils;

import java.io.File;

/**
 * Created by skyisbule on 2017/10/1.
 */
public class StringUtils {
    public static String modifyPackagePath(String packageName)
    {
        if(packageName.indexOf(".")>-1)
            return packageName.replace(".", File.separator);
        else
            return packageName;
    }


}
