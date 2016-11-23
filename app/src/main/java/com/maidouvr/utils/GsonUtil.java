package com.maidouvr.utils;

import com.google.gson.Gson;

/**
 * Created by xi.chen01 on 2016/11/23.
 * Project:maidouvr_android
 * Company:Lodestone
 * Email:Xi.chen01@lodestonemc.com
 */

public class GsonUtil {
    private static Gson gson;

    public static synchronized Gson getGson() {
        if (gson == null) {
            return new Gson();
        } else {
            return gson;
        }
    }
}
