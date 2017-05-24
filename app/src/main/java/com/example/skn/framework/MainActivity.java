package com.example.skn.framework;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewConfiguration;
import android.widget.Button;

import com.example.skn.framework.util.ToolBarUtil;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ToolBarUtil.builder(this).setToolbarTitle("Demo").build();
        Button btnShare = (Button) findViewById(R.id.btn_share);
        btnShare.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.setPackage("com.tencent.wx");
            intent.putExtra(Intent.EXTRA_SUBJECT, "主题");
            intent.putExtra(Intent.EXTRA_TEXT, "内容");
            startActivity(Intent.createChooser(intent, "标题"));
        });
         int scaledTouchSlop = ViewConfiguration.get(this).getScaledTouchSlop();//系统所能识出被认为是滑动的最小距离
    }
}
