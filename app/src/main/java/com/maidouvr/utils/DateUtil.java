package com.maidouvr.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by xi.chen01 on 2016/11/23.
 * Project:maidouvr_android
 */

public class DateUtil {
    //服务端时间格式
    private static final String SERVICE_DATE_FORMATTER = "yyyy-MM-dd'T'HH:mm:ssZ";

    //客户端订单时间格式
    private static final String CLIENT_ORDER_FORMATTER = "yyyy-MM-dd HH:mm:ss";

    public String getOrderDate(String dateString) {
        return getClientDate(dateString, SERVICE_DATE_FORMATTER, CLIENT_ORDER_FORMATTER);
    }


    private static String getClientDate(String dateString, String serviceFormat, String clientFormat) {
        SimpleDateFormat serviceFormatter = new SimpleDateFormat(serviceFormat, Locale.CHINA);
        Date date;
        try {
            date = serviceFormatter.parse(dateString);
            return getClientDate(date, clientFormat);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

    }

    private static String getClientDate(Date date, String clientFormat) {
        SimpleDateFormat clientFormatter = new SimpleDateFormat(clientFormat, Locale.CHINA);
        if (date != null) {
            return clientFormatter.format(date);
        } else {
            return "";
        }
    }

}
