package net.yeesky.fzair.util;

/**
 * Created by xi.chen01 on 2016/12/13.
 * Project:maidouvr_android
 */

public class Keys {
    static {
        System.loadLibrary("Fzair");
    }

    public Keys() {
        super();
    }

    public native String desKey();
}
