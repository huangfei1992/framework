package com.example.skn.framework.util;

import com.alibaba.fastjson.JSONObject;
import com.example.skn.framework.base.BaseEntity;
import com.example.skn.framework.base.BaseUrl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by skn on 2017/7/5.
 */

public class HttpUtil {

    private static Retrofit retrofit;


    private static void initRetrofit() {


        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS).build();
        retrofit = new Retrofit.Builder().baseUrl(BaseUrl.baseUrl)
                .client(client)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();


    }

    /**
     * post
     */
    public static <T> Call<String> post(String url, Map<String, Object> map, RequestCallBack<T> requestCallBack) {
        initRetrofit();
        if (map == null) map = new HashMap<>();
        RequestServes requestServes = retrofit.create(RequestServes.class);
        Call<String> call = requestServes.post(url, map);
        call.enqueue(requestCallBack);
        return call;

    }

    /**
     * get
     */
    public static <T> Call<String> get(String url, Map<String, Object> map, RequestCallBack<T> requestCallBack) {
        initRetrofit();
        if (map == null) map = new HashMap<>();
        RequestServes requestServes = retrofit.create(RequestServes.class);
        Call<String> call = requestServes.get(url, map);
        call.enqueue(requestCallBack);
        return call;

    }

    /**
     * 下载
     */
    public static void downLoadFile(String url, File file) {
        initRetrofit();
        RequestServes requestServes = retrofit.create(RequestServes.class);
        Call<ResponseBody> call = requestServes.downLoadFile(url);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    if (response.errorBody() == null) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                InputStream is = null;
                                FileOutputStream fos = null;
                                try {
                                    is = response.body().byteStream();
                                    fos = new FileOutputStream(file);
                                    byte bytes[] = new byte[1024];
                                    int sum = 0;
                                    while ((sum = is.read(bytes)) != -1) {
                                        fos.write(bytes, 0, sum);
                                    }

                                } catch (Exception e) {
                                    e.printStackTrace();
                                } finally {
                                    try {
                                        if (is != null)
                                            is.close();
                                        if (fos != null) {
                                            fos.flush();
                                            fos.close();
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }

                                }
                            }
                        }).start();

                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    /**
     * 表单
     */
    public static <T> Call<String> postByFrom(String url, Map<String, Object> map, RequestCallBack<T> requestCallBack) {
        initRetrofit();
        if (map == null) map = new HashMap<>();
        RequestServes requestServes = retrofit.create(RequestServes.class);
        Call<String> call = requestServes.formUrlEncoded(url, map);
        call.enqueue(requestCallBack);
        return call;

    }

    /**
     * 文件
     */
    public static <T> Call<String> file(String url, Map<String, File> files, RequestCallBack<T> requestCallBack) {
        initRetrofit();
        RequestServes requestServes = retrofit.create(RequestServes.class);
        Map<String, MultipartBody.Part> map = new HashMap<>();
        for (String key : files.keySet()) {
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), files.get(key));
            MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", files.get(key).getName(), requestFile);
            map.put(key, filePart);
        }
        Call<String> call = requestServes.multipart(url, map);
        call.enqueue(requestCallBack);
        return call;

    }

    /**
     * 图文
     */
    public static <T> Call<String> postWithFile(String url, Map<String, Object> map, Map<String, File> files, RequestCallBack<T> requestCallBack) {
        initRetrofit();
        RequestServes requestServes = retrofit.create(RequestServes.class);
        Map<String, MultipartBody.Part> partFiles = new HashMap<>();
        for (String key : files.keySet()) {
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), files.get(key));
            MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", files.get(key).getName(), requestFile);
            partFiles.put(key, filePart);
        }
        Call<String> call = requestServes.postWithFile(url, map, partFiles);
        call.enqueue(requestCallBack);
        return call;

    }

    interface RequestServes {


        @Streaming()//下载大文件
        @GET()
        Call<ResponseBody> downLoadFile(@Url String url);

        @GET()
        Call<String> get(@Url String url, @QueryMap Map<String, Object> map);

        @POST()
        Call<String> post(@Url String url, @QueryMap Map<String, Object> map);


        @FormUrlEncoded
        @POST()
        Call<String> formUrlEncoded(@Url String url, @FieldMap Map<String, Object> map);


        @Multipart
        @POST()
        Call<String> postWithFile(@Url String url, @QueryMap Map<String, Object> map, @PartMap Map<String, MultipartBody.Part> files);

        @Multipart
        @POST()
        Call<String> multipart(@Url String url, @PartMap Map<String, MultipartBody.Part> files);
    }

}
