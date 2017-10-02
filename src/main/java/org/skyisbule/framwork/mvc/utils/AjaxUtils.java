package org.skyisbule.framwork.mvc.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by skyisbule on 2017/10/1.
 */
public class AjaxUtils {
    public static boolean isAjaxRequest(HttpServletRequest request){

        String requestedWith = request.getHeader("X-Requested-With");
        Boolean isAjax = requestedWith != null ? requestedWith.equals("XMLHttpRequest"):false;

        return isAjax;
    }
}
