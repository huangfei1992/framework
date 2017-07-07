package com.example.skn.framework.util;

import com.alibaba.fastjson.JSONObject;
import com.example.skn.framework.base.BaseEntity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by skn on 2017/7/6.
 */

public abstract class RequestCallBack<T> implements Callback<String> {
    private Class<T> tClass;
    private static final String errorCode = "-1";

    public RequestCallBack(Class<T> tClass) {
        this.tClass = tClass;

    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.code() == 200) {
            if (response.errorBody() == null) {
                String result = response.body();
                BaseEntity baseEntity = JSONObject.parseObject(result, BaseEntity.class);
                if (baseEntity != null) {
                    if (baseEntity.getCode().equals("000")) {
                        T t = JSONObject.parseObject(baseEntity.getResult(), tClass);
                        if (t != null) {
                            success(t);
                        } else {
                            error(errorCode, "result结果为空！");
                        }
                    } else {
                        error(baseEntity.getCode(), baseEntity.getMsg());
                    }
                } else {
                    error(errorCode, "服务器数据为空！");
                }
            } else {
                error(errorCode, "服务器数据错误！");
            }
        } else {
            error(errorCode, "服务器访问失败！");
        }
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        error(errorCode, t.getMessage());
    }

    public abstract void success(T t);

    public abstract void error(String code, String msg);


}
