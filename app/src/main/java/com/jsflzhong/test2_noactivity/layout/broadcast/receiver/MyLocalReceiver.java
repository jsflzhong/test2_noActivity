package com.jsflzhong.test2_noactivity.layout.broadcast.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * 本地广播的接收器
 * 动态接收器, action是在sender那边配置的,而不是在manifest中.
 */
public class MyLocalReceiver extends BroadcastReceiver {
    private static final String TAG = "MyLocalReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "@@@[MyLocalReceiver]onReceive:......");
        Toast.makeText(context, "@@@[LocalReceiver]Received local broadcast!", Toast.LENGTH_SHORT).show();
    }
}
