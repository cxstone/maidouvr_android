package com.maidouvr.ui.activity.others;

import android.Manifest;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.maidouvr.R;
import com.maidouvr.ui.activity.BaseActivity;
import com.maidouvr.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class DrawableActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawable);
        initView();
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("首页");
        toolbar.setNavigationIcon(R.mipmap.ic_menu_white);
        setSupportActionBar(toolbar);


        Spinner spinner = (Spinner) findViewById(R.id.planets_spinner);
        List<String> list = new ArrayList<>();
        list.add("Mercury");
        list.add("Venus");
        list.add("Earth");
        list.add("Mars");
        list.add("Jupiter");
        list.add("Saturn");
        list.add("Uranus");
        list.add("Neptune");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_drawable, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                requestPermission(new String[]{Manifest.permission.CAMERA});
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onRequestPermissionSuccess() {
        ToastUtil.show(context, "权限申请成功！");
    }
}
