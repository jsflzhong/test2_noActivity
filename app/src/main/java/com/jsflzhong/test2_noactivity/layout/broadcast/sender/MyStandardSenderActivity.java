package com.jsflzhong.test2_noactivity.layout.broadcast.sender;


import android.content.ComponentName;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.jsflzhong.test2_noactivity.BasicActivity;

public class MyStandardSenderActivity extends BasicActivity {

    private static final String TAG = "MyStandardSenderActivit";

    @Override
    public void setContentView() {
        Toast.makeText(this, "[setContentView]", Toast.LENGTH_SHORT).show();
    }

    /**
     * 发送自定义的广播.
     * 该广播会被静态广播接收器:MyStandardReceiver.java所接受, 广播action是在manifest中匹配上的.
     */
    @Override
    public void loadView() {
        //该字符串,需要与manifest.xml中的action的字符串配置一致即可.
        Intent intent = new Intent("com.jsflzhong.test2.noactivity.MY_BROADCAST");
        //Android8.0新特性,需要加上这句话.
        intent.setComponent(new ComponentName(getPackageName(),"com.jsflzhong.test2_noactivity.layout.broadcast.receiver.MyStandardReceiver"));
        Log.d(TAG, "@@@[loadView]:Sending broadcast...... ");
        Toast.makeText(this, "@@@[loa`dView]Sending broadcast", Toast.LENGTH_SHORT).show();
        sendBroadcast(intent);
    }
}
