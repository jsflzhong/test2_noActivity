package com.jsflzhong.test2_noactivity.layout;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Button;

import com.jsflzhong.test2_noactivity.BasicActivity;
import com.jsflzhong.test2_noactivity.R;
import com.jsflzhong.test2_noactivity.common.ActivityCollector;

/**
 * login之后跳转到的主页面
 */
public class LoginMainActivity extends BasicActivity {

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_login_main);
        handleButton();
    }

    private void handleButton() {
        handleForceOfflineButton();
    }

    /**
     * 发送强制下线的广播.
     * <p>
     * 接下来我们就需要创建一个广播接收器来接收这条强制下线广播，唯一的问题
     * 就是，应该在哪里创建呢？由于广播接收器里面需要弹出一个对话框来阻塞用户的正常操作，
     * 但如果创建的是一个静态注册的广播接收器，是没有办法在onReceive() 方法里弹出对话框
     * 这样的UI控件的，而我们显然也不可能在每个活动中都去注册一个动态的广播接收器。
     * 方案:只需要在BaseActivity中动态注册一个广播接收器就可以了，因为所有的活动都是继承自BaseActivity的。
     * (抽取BasicActivity的必要性之一)
     */
    private void handleForceOfflineButton() {
        Button button = findViewById(R.id.force_offline);
        button.setOnClickListener(v -> {
            Intent intent = new Intent("com.jsflzhong.test2.noactivity.FORCE_OFFLINE");
            sendBroadcast(intent);
        });
    }

    /**
     * 强制下线的接收器.
     */
    class ForceOfflineReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(final Context context, Intent intent) {
            //构建一个不可取消的对话框, 用来让用户无法继续下一步操作, 也无法取消这个对话框.
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Warning");
            builder.setMessage("You are forced to be offline. Please try to login again.");
            builder.setCancelable(false);
            //对话框注册确定按钮，当用户点击了确定按钮时，就调用ActivityCollector的finishAll() 方法来销毁掉所有活动，并重新启动LoginActivity这个活动
            builder.setPositiveButton("OK", (dialog, which) -> {
                ActivityCollector.finishAll(); // 销毁所有活动
                Intent intent1 = new Intent(context, LoginActivity.class);
                context.startActivity(intent1); // 重新启动LoginActivity
            });
            builder.show();
        }
    }


}
