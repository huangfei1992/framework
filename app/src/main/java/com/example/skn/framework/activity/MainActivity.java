package com.example.skn.framework.activity;


import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.widget.Toast;

import com.example.skn.framework.R;
import com.example.skn.framework.base.BaseActivity;
import com.example.skn.framework.util.DialogUtil;
import com.example.skn.framework.util.HttpUtil;
import com.example.skn.framework.util.RequestCallBack;
import com.example.skn.framework.util.ToolBarUtil;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static android.R.attr.dial;

/**
 * Created by skn on 2017/7/5.
 */

public class MainActivity extends BaseActivity {


    @Override
    protected void init() {
        setFlagTranslucentStatus();
        setContentView(R.layout.activity_main);
        ToolBarUtil.builder(this).setTitle("老子").build();
        Map<String, Object> map = new HashMap<>();
        map.put("versionId", "1");
        map.put("versionId", new File(""));
//        File file = new File(Environment.getExternalStorageDirectory() + "/abc/abcd");
//        try {
//            if (!file.exists()) ;
//            file.mkdirs();
//            File a = new File(file, "shabi.apk");
//            if (!a.exists())
//                a.createNewFile();
//            HttpUtil.downLoadFile("download/waterelephantloan.apk", a);

            HttpUtil.postByFrom("beadwalletloanapp/app/version/findBwAppVersionById.do", map, new RequestCallBack<String>(String.class) {
                @Override
                public void error(String code, String msg) {

                }

                @Override
                public void success(String s) {

                }
            });
    }

    @Override
    protected void initVar() {

    }

    @Override
    protected void initData() {

    }
}
