package com.maidouvr.utils;

/**
 * 应用的常量工具类
 * Created by xi.chen01 on 2016/11/8.
 * Project:Maidouvr
 * Company:Lodestone
 * Email:Xi.chen01@lodestonemc.com
 */

public class ConstantUtil {

    //系统常量参数
    public static abstract class System {
        //系统调试模式开关
        public static boolean IS_DEBUG_MODE = true;
    }

    //SharedPreference中所用的key
    public static abstract class SP {
        public static abstract class Host {
            public static final String IS_CUSTOM_HOST = "is_custom_host";
            public static final String HYBRIS = "hybris";
        }

        public static abstract class APP {
            public static final String ACCESS_TOKEN = "access_token";
            public static final String THEME_STORE = "theme_store";
        }

        public static abstract class User {
            public static final String NAME = "user_name";
            public static final String ICON = "user_icon";
            public static final String MOBILE = "user_mobile";
            public static final String UID = "user_uid";
            public static final String NICKNAME = "user_nick_name";
            public static final String SEX = "user_sex";
            public static final String BIRTH = "user_birth";
            public static final String IS_STORE_EMPLOYEE_GROUP = "user_is_store_employee_group";
            public static final String IS_YXT_EMPLOYEE_GROUP = "user_is_yxt_employee_group";
        }

    }

    public static abstract class BC {
        public static final String GOTO_STORE = "goto_store";
        public static final String GOTO_CART = "goto_cart";
        public static final String GOTO_MY = "goto_my";

        public static final String UPDATE_USER_INFO = "update_user_info";
        public static final String UPDATE_CART_NUMBER = "update_cart_number";
    }

    //网络接口HOST
    public static abstract class HOST {
        public static String HOST_HYBRIS = "https://www1.hxyxt.com";
        public static String HOST_PHP = "http://pt.yxtmart.cn";
    }

}
