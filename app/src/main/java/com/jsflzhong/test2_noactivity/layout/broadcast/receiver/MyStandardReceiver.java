package com.jsflzhong.test2_noactivity.layout.broadcast.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyStandardReceiver extends BroadcastReceiver {
    private static final String TAG = "MyStandardReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "@@@onReceive......");
        Toast.makeText(context, "@@@Received in [MyBroadcastReceiver]", Toast.LENGTH_SHORT).show();
    }
}
