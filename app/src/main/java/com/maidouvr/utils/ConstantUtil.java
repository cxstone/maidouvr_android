package com.maidouvr.utils;

/**
 * 应用的常量工具类
 * Created by xi.chen01 on 2016/11/8.
 * Project:Maidouvr
 * Company:Lodestone
 * Email:Xi.chen01@lodestonemc.com
 */

public class ConstantUtil {

    public static abstract class System {
        //系统调试模式开关
        public static boolean IS_DEBUG_MODE = true;
    }

    public static abstract class SP {

    }

    public static abstract class API {
        static final String HOST = "http://www.maidouvr.com";
        static final String BASE_URL = HOST + "/api/";

        public static abstract class Test {
            public static final String GET_CATEGORY = BASE_URL + "get_category_posts/";
        }

    }
}
