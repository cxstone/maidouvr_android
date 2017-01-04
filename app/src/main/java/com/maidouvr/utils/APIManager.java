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

    public abstract static class Product {
        //获取商品详情
        public static final String API_GET_PRODUCT_DETAIL = ConstantUtil.HOST.HOST_HYBRIS
                + "/yxtws/v2/hxyxt/app/product/detail/%1$s?channel=yxtapp";
    }

}
