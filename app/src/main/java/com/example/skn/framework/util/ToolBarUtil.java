package com.example.skn.framework.util;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.skn.framework.R;


public class ToolBarUtil {
    private static ToolBarUtil toolBarUtil;

    public static Builder builder(AppCompatActivity activity) {
        return new Builder(activity);
    }

    private static ToolBarUtil getInstance() {
        if (toolBarUtil == null)
            toolBarUtil = new ToolBarUtil();
        return toolBarUtil;
    }

    /**
     * 构建类
     */
    public static class Builder {
        private Toolbar toolbar;
        private TextView toolbar_title;
        private AppCompatActivity activity;
        private boolean isShow = true;

        public Builder(AppCompatActivity activity) {
            this.toolbar = (Toolbar) activity.findViewById(R.id.toolbar);
            this.toolbar_title = (TextView) activity.findViewById(R.id.toolbar_title);
            this.activity = activity;
        }

        public ToolBarUtil build() {
            activity.setSupportActionBar(toolbar);
            ActionBar actionBar = activity.getSupportActionBar();
            if (actionBar != null)
                actionBar.setDisplayHomeAsUpEnabled(isShow);
//            toolbar.setNavigationIcon(mActivity.getResources().getDrawable(R.mipmap.ic_back));
            toolbar.setNavigationOnClickListener(v -> activity.onBackPressed());
            return ToolBarUtil.getInstance();
        }

        public Builder isShow(boolean isShow) {
            this.isShow = isShow;
            return this;
        }

        public Builder setLogo(int id) {
            toolbar.setLogo(id);
            return this;
        }

        public Builder setToolbarTitle(int id) {
            toolbar_title.setVisibility(View.GONE);
            toolbar.setTitle(id);
            return this;
        }

        public Builder setToolbarTitle(String title) {
            toolbar_title.setVisibility(View.GONE);
            toolbar.setTitle(title);
            return this;
        }

        public Builder setTitle(int id) {
            toolbar.setTitle("");
            toolbar_title.setVisibility(View.VISIBLE);
            toolbar_title.setText(id);
            return this;
        }

        public Builder setTitle(String title) {
            toolbar.setTitle("");
            toolbar_title.setVisibility(View.VISIBLE);
            toolbar_title.setText(title);
            return this;
        }

        public Builder setSubtitle(int id) {
            toolbar.setSubtitle(id);
            return this;
        }

        public Builder setSubtitle(String subtitle) {
            toolbar.setSubtitle(subtitle);
            return this;
        }

    }
}
