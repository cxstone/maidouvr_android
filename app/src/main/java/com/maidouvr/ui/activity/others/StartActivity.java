package com.maidouvr.ui.activity.others;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.maidouvr.R;
import com.maidouvr.ui.activity.BaseActivity;

public class StartActivity extends BaseActivity {
    private boolean isExit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        initView();
    }

    private void initView() {
        new StartAsyncTask().execute();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        isExit = true;
    }

    class StartAsyncTask extends AsyncTask<Void, String, String> {

        @Override
        protected String doInBackground(Void... params) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            if (!isExit) {
                Intent intent = new Intent(context, DrawableActivity.class);
                startActivity(intent);
                finish();
            }
        }

        @Override
        protected void onPreExecute() {
        }
    }
}
