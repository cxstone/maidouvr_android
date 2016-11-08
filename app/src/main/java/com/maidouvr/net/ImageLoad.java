package com.maidouvr.net;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.BitmapRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.maidouvr.R;

/**
 * 基于Glide图片加载框架工具类封装
 * Created by xi.chen01 on 2016/11/8.
 * Project:Maidouvr
 * Company:Lodestone
 * Email:Xi.chen01@lodestonemc.com
 */

public class ImageLoad {

    public enum ScaleType {
        CENTER_CROP, FIT_CENTER
    }

    /**
     * 加载本地资源图片
     * 缩放方式：默认缩放方式
     */
    public static void loadImg(Context context, ImageView imageView, int imgRes) {
        loadImgFromRes(context, imageView, imgRes, null);
    }

    /**
     * 加载本地资源图片
     * 缩放方式：使用自定义缩放方式
     */
    public static void loadImg(Context context, ImageView imageView, int imgRes, ScaleType scaleType) {
        loadImgFromRes(context, imageView, imgRes, scaleType);
    }

    /**
     * 加载网络图片
     * 默认图片：默认使用系统统一的图片
     * 缩放方式：默认缩放方式
     */
    public static void loadImg(Context context, ImageView imageView, String url) {
        loadImgFromNet(context, imageView, url, R.mipmap.ic_default_image, null);
    }

    /**
     * 加载网络图片
     * 默认图片：默认使用系统统一的图片
     * 缩放方式：使用自定义缩放方式
     */
    public static void loadImg(Context context, ImageView imageView, String url, ScaleType scaleType) {
        loadImgFromNet(context, imageView, url, R.mipmap.ic_default_image, scaleType);
    }

    /**
     * 加载网络图片
     * 默认图片：使用自定义的图片
     * 缩放方式：默认缩放方式
     */
    public static void loadImg(Context context, ImageView imageView, String url, int imgRes) {
        loadImgFromNet(context, imageView, url, imgRes, null);
    }

    /**
     * 加载网络图片
     * 默认图片：使用自定义图片
     * 缩放方式：使用自定义缩放方式
     */
    public static void loadImg(Context context, ImageView imageView, String url, int imgRes, ScaleType scaleType) {
        loadImgFromNet(context, imageView, url, imgRes, scaleType);
    }

    /**
     * 加载网络图片
     */
    private static void loadImgFromNet(Context context, ImageView imageView, String url, int defaultRes, ScaleType scaleType) {
        BitmapRequestBuilder builder = Glide.with(context)
                .load(url)
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .placeholder(defaultRes);

        if (scaleType == ScaleType.FIT_CENTER) {
            builder.fitCenter();
        } else if (scaleType == ScaleType.CENTER_CROP) {
            builder.centerCrop();
        }

        builder.into(imageView);
    }

    /**
     * 加载本地资源图片
     */
    private static void loadImgFromRes(Context context, ImageView imageView, int imgRes, ScaleType scaleType) {
        BitmapRequestBuilder builder = Glide.with(context)
                .load(imgRes)
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .dontAnimate();

        if (scaleType == ScaleType.FIT_CENTER) {
            builder.fitCenter();
        } else if (scaleType == ScaleType.CENTER_CROP) {
            builder.centerCrop();
        }

        builder.into(imageView);
    }
}
