package com.maidouvr.ui.fragment.index;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.maidouvr.R;
import com.maidouvr.model.response.hybris.HybrisBase;
import com.maidouvr.net.ErrorInfo;
import com.maidouvr.net.HttpLoad;
import com.maidouvr.net.ResponseCallback;
import com.maidouvr.ui.fragment.BaseFragment;
import com.maidouvr.utils.SPManager;
import com.maidouvr.utils.ToastUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * Created by xi.chen01 on 2016/11/8.
 * Project:Maidouvr
 * Company:Lodestone
 * Email:Xi.chen01@lodestonemc.com
 */

public class MyFragment extends BaseFragment {
    private static final int REQUEST_PERMISSION_1 = 1;

    private static final int REQUEST_CAMERA = 100;
    private static final int REQUEST_GALLERY = 200;
    private static final int REQUEST_CROP = 300;

    private ImageView ivIcon;

    private String fileName = "xxx.jpg";
    private Uri uri;
    private long[] mClicks = new long[7];


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        uri = getUri();

        view.findViewById(R.id.btn_next).setOnClickListener(this);
        view.findViewById(R.id.btn_head).setOnClickListener(this);
        view.findViewById(R.id.btn_add).setOnClickListener(this);
        view.findViewById(R.id.btn_delete).setOnClickListener(this);
        ivIcon = (ImageView) view.findViewById(R.id.iv_icon);

        return view;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_head) {
            requestPermission(REQUEST_PERMISSION_1, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE});

        } else if (id == R.id.btn_next) {
//            System.arraycopy(mClicks, 1, mClicks, 0, mClicks.length - 1);
//            mClicks[mClicks.length - 1] = SystemClock.uptimeMillis();
//            if (mClicks[0] >= (SystemClock.uptimeMillis() - 3000)) {
//                mClicks = null;
//                mClicks = new long[7];
//                Intent intent = new Intent(context, DrawableActivity.class);
//                startActivity(intent);
//            }
            HttpLoad.UserModule.login(context, tag, "18602808274", "000000", new ResponseCallback<HybrisBase>() {
                @Override
                public void onRequestSuccess(HybrisBase result) {
                    ToastUtil.show(context, "success");
                }

                @Override
                public void onRequestFailed(ErrorInfo error) {
                    ToastUtil.show(context, error);
                }
            });
        } else if (id == R.id.btn_add) {
            SPManager.putHostFlag(context, true);
            SPManager.saveHostHybris(context, "https://www.baidu.com");
        } else if (id == R.id.btn_delete) {
            SPManager.putHostFlag(context, false);
        }
    }

    @Override
    public void onRequestPermissionSuccess(int requestCode) {
        if (requestCode == REQUEST_PERMISSION_1) {
            openCamera();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CAMERA) {
            if (resultCode == Activity.RESULT_OK) {
                cropImage(uri, uri, 500, 500, REQUEST_CROP);
            } else if (resultCode == Activity.RESULT_CANCELED) {
                ToastUtil.show(context, "取消拍照");
            } else {
                ToastUtil.show(context, "拍照失败");
            }
        } else if (requestCode == REQUEST_CROP) {
            if (resultCode == Activity.RESULT_OK) {
                try {
                    ivIcon.setImageBitmap(BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uri)));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                ToastUtil.show(context, "取消剪裁");
            } else {
                ToastUtil.show(context, "剪裁失败");
            }
        } else if (requestCode == REQUEST_GALLERY) {
            if (resultCode == Activity.RESULT_OK) {
                cropImage(data.getData(), uri, 500, 500, REQUEST_CROP);
            } else if (resultCode == Activity.RESULT_CANCELED) {
                ToastUtil.show(context, "取消选取");
            } else {
                ToastUtil.show(context, "选取失败");
            }
        }
    }

    private void openCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        //将存储图片的uri读写权限授权给相机应用
        List<ResolveInfo> resInfoList = getActivity().getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        for (ResolveInfo resolveInfo : resInfoList) {
            String packageName = resolveInfo.activityInfo.packageName;
            getActivity().grantUriPermission(packageName, uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
        startActivityForResult(Intent.createChooser(intent, "选择拍照工具"), REQUEST_CAMERA);
    }

    private void openGallery() {
        //调用系统相册
        Intent intent;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        } else {
            intent = new Intent(Intent.ACTION_GET_CONTENT);
        }
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_GALLERY);
    }

    private Uri getUri() {
        File path = new File(Environment.getExternalStorageDirectory(), "Android/data/com.maidouvr/files/header");
        if (!path.exists()) {
            path.mkdirs();
        }
        File file = new File(path, fileName);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return FileProvider.getUriForFile(context, "com.maidouvr.fileprovider", file);
        } else {
            return Uri.fromFile(file);
        }
    }


    private void cropImage(Uri originUri, Uri desUri, int width, int height, int requestCode) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

        intent.setDataAndType(originUri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", width);
        intent.putExtra("outputY", height);
        intent.putExtra("scale", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, desUri);
        intent.putExtra("return-data", false);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);

        //将存储图片的uri读写权限授权给剪裁工具应用
        List<ResolveInfo> resInfoList = getActivity().getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        for (ResolveInfo resolveInfo : resInfoList) {
            String packageName = resolveInfo.activityInfo.packageName;
            getActivity().grantUriPermission(packageName, uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
        startActivityForResult(Intent.createChooser(intent, "选择剪裁工具"), requestCode);
    }

}
