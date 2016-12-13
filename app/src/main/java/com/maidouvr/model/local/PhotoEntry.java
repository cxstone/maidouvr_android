package com.maidouvr.model.local;

/**
 * Created by xi.chen01 on 2016/12/6.
 * Project:maidouvr_android
 */

public class PhotoEntry {
    public static final int TYPE_ADD = 0;
    public static final int TYPE_PHOTO = TYPE_ADD + 1;

    public PhotoEntry(int type) {
        this.type = type;
    }

    public PhotoEntry(int type, String url) {
        this.type = type;
        this.url = url;
    }

    public int type;
    public String url;
}
