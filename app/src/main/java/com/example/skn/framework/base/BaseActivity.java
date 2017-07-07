package com.example.skn.framework.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.skn.framework.App;
import com.example.skn.framework.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by skn on 2017/7/5.
 */

abstract public class BaseActivity extends AppCompatActivity {

    protected BaseActivity MyActivity;
    protected Map<String, Object> map;

    @Override

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyActivity = this;
        map = new HashMap<>();
        addActivity();
        setScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        TextView tvAgainUpload = (TextView) findViewById(R.id.tv_again_upload);
        if (tvAgainUpload != null)
            tvAgainUpload.setOnClickListener(v -> setAgainUploadListener());
        init();
        initVar();
        initData();
    }

    abstract protected void init();

    abstract protected void initVar();


    abstract protected void initData();

    protected void setAgainUploadListener() {

    }

    /**
     * 设置为沉浸式状态栏
     */
    protected void setFlagTranslucentStatus() {
        //还需要在布局文件中设置2个属性，用来告诉系统状态栏的颜色和与那个布局保存一直，默认为toolbar
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);//透明导航栏
    }

    /**
     * 设置为全屏
     */
    protected void setFlagFullscreen() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);//全屏
    }

    private void setScreenOrientation(int type) {
        if (getRequestedOrientation() != type) {
            setRequestedOrientation(type);
        }
    }

    private void addActivity() {
        if (!App.getApp().activityList.contains(this)) {
            App.getApp().activityList.add(this);
        }

    }

    private void removeActivity() {
        if (App.getApp().activityList.contains(this)) {
            App.getApp().activityList.remove(this);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        removeActivity();
    }
}
