package com.opensource.soft.BlogServer.common.util;

import java.io.File;
import java.util.Calendar;
import java.util.UUID;

public class PathUtil {

    /**
     *  返回存放路径  img
     * @return
     */
    public static String imgPath(){
        Calendar cal = Calendar.getInstance();
        StringBuffer path = new StringBuffer();
        path.append(cal.get(Calendar.YEAR))
            .append(File.separator)
            .append((cal.get(Calendar.MONTH)+1))
            .append(File.separator)
            .append(cal.get(Calendar.DAY_OF_MONTH))
            .append(File.separator)
            .append(cal.get(Calendar.HOUR_OF_DAY))
            .append(File.separator)
            .append(UUID.randomUUID().toString());
        return path.toString();
    }

    /**
     *  返回存放路径  blog html
     * @return
     */
    public static String htmlPath(){
        Calendar cal = Calendar.getInstance();
        StringBuffer path = new StringBuffer();
        path.append(cal.get(Calendar.YEAR))
                .append(File.separator)
                .append((cal.get(Calendar.MONTH)+1))
                .append(File.separator)
                .append(cal.get(Calendar.DAY_OF_MONTH))
                .append(File.separator)
                .append(cal.get(Calendar.HOUR_OF_DAY));
        return path.toString();
    }

    public static void main(String[] args) {
        System.out.println(PathUtil.htmlPath());
    }
}
