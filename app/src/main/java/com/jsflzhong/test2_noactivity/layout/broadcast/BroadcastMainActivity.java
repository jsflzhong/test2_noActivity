package com.jsflzhong.test2_noactivity.layout.broadcast;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.widget.Toast;

import com.jsflzhong.test2_noactivity.BasicActivity;
import com.jsflzhong.test2_noactivity.R;

/**
 * 使用广播接收器.
 *
 *  1.广播接收器要继承: BroadcastReceiver
 *  2.Activity要新建一个对应类型的广播的Action.
 *  3.在Activity中注册对应的自定义的广播接收器, 并传入上述的Action.
 */
public class BroadcastMainActivity extends BasicActivity {

    @Override
    public void setContentView() {
        setContentView(R.layout.broadcast_main);
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
     * 注意:
     *      动态注册的广播接收器一定都要取消注册才行.通过调用unregisterReceiver() 方法来实现的.
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkChangeReceiver);
    }

    /**
     * 广播接收器
     */
    class NetworkChangeReceiver extends BroadcastReceiver {

        /**
         * 每当网络状态发生变化时，onReceive() 方法就会得到执行.
         * 问题: 为什么是在网络发生变化时? --是在上面定义的对应的网络变化的action,并传入的.
         *
         * @param context
         * @param intent
         */
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "network changes", Toast.LENGTH_SHORT).show();
        }
    }
}
