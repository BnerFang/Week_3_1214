package com.fsk.week_3_1214.util;

import android.os.Handler;
import android.os.Message;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 作者:  方诗康
 * 描述: 封装  OkHttpUtil  工具类
 */
public class OkHttpUtil {

    private OkHttpListener mOkHttpListener;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0://成功
                    String data = (String) msg.obj;
                    mOkHttpListener.OkHttpSuccess(data);
                    break;
                case 1://失败
                    String error = (String) msg.obj;
                    mOkHttpListener.OkHttpError(error);
                    break;
            }
        }
    };

    //生成    OkHttpListener  set方法
    public void setOkHttpListener(OkHttpListener okHttpListener) {
        mOkHttpListener = okHttpListener;
    }


    public OkHttpUtil OkHttpGet(String url){
        //获取clench对象
        OkHttpClient okHttpClient = new OkHttpClient();
        //获取Request对象
        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {

            /**
             * 失败回调方法
             * @param call
             * @param e
             */
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.what = 1;
                message.obj = e.getMessage();
                mHandler.sendMessage(message);
            }

            /**
             * 成功回调方法
             * @param call
             * @param response
             * @throws IOException
             */
            @Override
            public void onResponse(Call call, Response response) throws IOException {
               Message message = new Message();
               message.what = 0;
               message.obj = response.body().string();
               mHandler.sendMessage(message);
            }
        });
        return this;
    }


    public OkHttpUtil OkHttoPost(String url, FormBody formBody){
        //创建 clench 对象
        OkHttpClient okHttpClient = new OkHttpClient();
        //获取Request
        Request request = new Request.Builder().url(url).post(formBody).build();
        okHttpClient.newCall(request).enqueue(new Callback() {

            /**
             * 失败回调方法
             * @param call
             * @param e
             */
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.what = 1;
                message.obj = e.getMessage();
                mHandler.sendMessage(message);
            }

            /**
             * 成功回调方法
             * @param call
             * @param response
             * @throws IOException
             */
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Message message = new Message();
                message.what = 0;
                message.obj = response.body().string();
                mHandler.sendMessage(message);
            }
        });
        return this;
    }

    //定义一个接口,进行回调
    public interface OkHttpListener{
        //成功
        void OkHttpSuccess(String data);
        //失败
        void OkHttpError(String error);
    }
}
