package com.jsflzhong.test2_noactivity.layout.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * 系统重启后的广播接收器.
 *
 * 静态广播接收器. 如果使用动态的接收器, 由于是代码写的,无法在系统启动时就启动.
 *
 * 静态的广播接收器一定要在AndroidManifest.xml文件中注册才可以使用，不过由于我们是
 * 使用Android Studio的快捷方式创建的广播接收器，因此注册这一步已经被自动完成了(manifest里自动多了个<receiver节点.)
 */
public class BootCompleteReceiver extends BroadcastReceiver {

    private static final String TAG = "BootCompleteReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive: @@@System boot complete!");
        Toast.makeText(context, "@@@System boot complete!", Toast.LENGTH_SHORT).show();
    }


}
