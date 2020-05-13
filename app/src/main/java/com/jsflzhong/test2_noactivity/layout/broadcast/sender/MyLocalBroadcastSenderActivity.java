package com.jsflzhong.test2_noactivity.layout.broadcast.sender;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.jsflzhong.test2_noactivity.BasicActivity;
import com.jsflzhong.test2_noactivity.R;
import com.jsflzhong.test2_noactivity.layout.broadcast.receiver.MyLocalReceiver;

public class MyLocalBroadcastSenderActivity extends BasicActivity {

    private static final String TAG = "MyLocalBroadcastActivit";

    private LocalBroadcastManager localBroadcastManager;
    private MyLocalReceiver myLocalReceiver;

    @Override
    public void setContentView() {
        setContentView(R.layout.my_local_broadcast);
    }

    @Override
    public void loadView() {
        Button button = findViewById(R.id.button1);
        //如果是本地广播,则需要通过LocalBroadcastManager管理发送以及接收器的注册工作.
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.jsflzhong.test2_noactivity.layout.broadcast.receiver.MyLocalBroadcastReceiver");
        //"本地广播接收器"是需要这样在LocalBroadcastManager中注册一下的(动态,无需在manifest中为接收器配置action.是在这里配置的.)
        myLocalReceiver = new MyLocalReceiver();
        localBroadcastManager.registerReceiver(myLocalReceiver, intentFilter);
        //在点击事件中,发送本地广播.
        button.setOnClickListener(v -> {
            Intent intent = new Intent("com.jsflzhong.test2_noactivity.layout.broadcast.receiver.MyLocalBroadcastReceiver");
            //For Android 8.0
            intent.setComponent(new ComponentName(getPackageName(),"com.jsflzhong.test2_noactivity.layout.broadcast.receiver.MyLocalReceiver"));
            //发送"本地消息广播".
            localBroadcastManager.sendBroadcast(intent);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        localBroadcastManager.unregisterReceiver(myLocalReceiver);
    }
}
