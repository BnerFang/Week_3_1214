package com.fsk.week_3_1214.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.fsk.week_3_1214.activity.CustomerActivity;

/**
 * 作者:  方诗康
 * 描述:
 */
public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //[MyReceiver] 用户点击打开了通知:        JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())
        if (intent.getAction().equals("cn.jpush.android.intent.NOTIFICATION_OPENED")) {
            //打开自定义的Activity
            Intent i = new Intent(context, CustomerActivity.class);
            //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //打开通知跳转
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(i);
        }
    }
}
