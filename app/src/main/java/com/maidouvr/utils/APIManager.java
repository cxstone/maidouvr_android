package com.maidouvr.utils;

/**
 * Created by xi.chen01 on 2016/11/23.
 * Project:maidouvr_android
 */

public class APIManager {

    public static abstract class User {
        //用户登录
        public static final String LOGIN = ConstantUtil.HOST.HOST_HYBRIS
                + "/yxtws/v2/hxyxt/app/customer/login";
    }

}
