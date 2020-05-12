package com.jsflzhong.test2_noactivity.layout.broadcast.receiver;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.jsflzhong.test2_noactivity.BasicActivity;
import com.jsflzhong.test2_noactivity.R;

/**
 * 使用广播接收器.
 * <p>
 * 动态注册的广播接收器.
 * 缺点:必须要在程序启动之后才能接收到广播，因为注册的逻辑是写在onCreate() 方法中的。
 * 那么有没有什么办法可以让程序在未启动的情况下就能接收到广播呢？这就需要使用静态注册的方式了。
 *
 * <p>
 * 1.广播接收器要继承: BroadcastReceiver
 * 2.Activity要新建一个对应类型的广播的Action.
 * 3.在Activity中注册对应的自定义的广播接收器, 并传入上述的Action.
 */
public class BroadNetWorkActivity extends BasicActivity {

    @Override
    public void setContentView() {
        setContentView(R.layout.broadcast_network);
    }

    private IntentFilter intentFilter;
    private NetworkChangeReceiver networkChangeReceiver;

    /**
     * 当网络状态发生变化时，系统发出的正是一条值为android.net.conn.CONNECTIVITY_CHANGE 的广播，
     */
    @Override
    public void loadView() {
        intentFilter = new IntentFilter();
        //我们的广播接收器想要监听什么广播，就在这里添加相应的action。
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        //在下面自定义的广播接收器.
        networkChangeReceiver = new NetworkChangeReceiver();
        //注册广播接收器,并传入对应的Asction.
        registerReceiver(networkChangeReceiver, intentFilter);
    }

    /**
     * 广播接收器
     */
    class NetworkChangeReceiver extends BroadcastReceiver {

        /**
         * 每当网络状态发生变化时，onReceive() 方法就会得到执行.
         * 问题: 为什么是在网络发生变化时? --是在上面定义的对应的网络变化的action,并传入的.
         * <p>
         * 这里有非常重要的一点需要说明，Android系统为了保护用户设备的安全和隐私，做了严
         * 格的规定：如果程序需要进行一些对用户来说比较敏感的操作，就必须在配置文件中声明权限
         * 才可以，否则程序将会直接崩溃. 比如这里访问系统的网络状态就是需要声明权限的。打开
         * AndroidManifest.xml文件，在里面加入如下权限就可以访问系统网络状态了:
         * <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
         *
         * @param context
         * @param intent
         */
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "network changes", Toast.LENGTH_SHORT).show();
            ConnectivityManager connectionManager = (ConnectivityManager)
                    getSystemService(Context.CONNECTIVITY_SERVICE);
            //以得到NetworkInfo 的实例. 见上面的注释, 如果不在manifest里面配置权限,则这行代码会报错.
            NetworkInfo networkInfo = connectionManager.getActiveNetworkInfo();
            //判断当前是否有网络.
            if (networkInfo != null && networkInfo.isAvailable()) {
                Toast.makeText(context, "network is available", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "network is unavailable", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * 注意:
     * 动态注册的广播接收器一定都要取消注册才行.通过调用unregisterReceiver() 方法来实现的.
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkChangeReceiver);
    }
}
