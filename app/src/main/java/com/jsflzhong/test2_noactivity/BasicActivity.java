package com.jsflzhong.test2_noactivity;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.jsflzhong.test2_noactivity.common.ActivityCollector;
import com.jsflzhong.test2_noactivity.layout.LoginActivity;

public abstract class BasicActivity extends AppCompatActivity {

    private static final boolean LOG_SWITCH = false;

    private static final String TAG = "BasicActivity";

    private ForceOfflineReceiver receiver;

    /**
     * 创建主活动
     * 在第一次被创建时会执行. 执行顺序:1
     *
     * @param savedInstanceState savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("@@@BaseActivity", getClass().getSimpleName());
        setContentView();
        //用自定义集合来管理所有活动
        ActivityCollector.addActivity(this);

        loadView();
        getSavedInstanceState(savedInstanceState);
        getIntentFromLastActivity();
        hideSupportActionBar();

    }

    /**
     * 隐藏布局中自带的标题栏
     */
    public void hideSupportActionBar() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (LOG_SWITCH) {
            Log.d(TAG, "@@@[onDestroy]");
        }
        //用自定义集合来管理所有活动. 此处把本活动从list中移除.
        ActivityCollector.removeActivity(this);
    }

    public void loadView() {
        Log.d(TAG, "@@@[loadView] in parent");
    }

    /**
     * 在本活动被关闭时,会调用下面的onSaveInstanceState(),会存储一个值, 这里可以在再重启时拿到.
     *
     * @param savedInstanceState savedInstanceState
     */
    public void getSavedInstanceState(Bundle savedInstanceState) {
        if (LOG_SWITCH) {
            Log.d(TAG, "@@@[getSavedInstanceState] in parent");
        }
    }

    /**
     * 需要子类指定对应的layout文件
     */
    public abstract void setContentView();

    /**
     * 从intent中, 拿出来从上个活动传过来的数据
     */
    public void getIntentFromLastActivity() {
        if (LOG_SWITCH) {
            Log.d(TAG, "@@@[]getIntentFromLastActivity in parent");
        }
    }

    /**
     * 在第一次被创建时会执行. 执行顺序:2
     * <p>
     * 当按下back键返回MainActivity时也会被调用.(由于之前MainActivity已经进入了停止状态，所以onRestart() 方法会得到执行)
     */
    @Override
    protected void onStart() {
        super.onStart();
        if (LOG_SWITCH) {
            Log.d(TAG, "@@@[onStart]");
        }
    }

    /**
     * 当按下back键返回MainActivity时会被调用.
     */
    @Override
    protected void onRestart() {
        super.onRestart();
        if (LOG_SWITCH) {
            Log.d(TAG, "@@@[onRestart]");
        }
    }

    /**
     * 在第一次被创建时会执行. 执行顺序:3
     * <p>
     * 当按下back键返回MainActivity时也会被调用.
     * <p>
     * 之前不都是在onCreate() 和onDestroy() 方法里来注册和取消注
     * 册广播接收器的么？这是因为我们始终需要保证只有处于栈顶的活动才能接收到这条强制下线
     * 广播，非栈顶的活动不应该也没有必要去接收这条广播，所以写在onResume() 和onPause()
     * 方法里就可以很好地解决这个问题，当一个活动失去栈顶位置时就会自动取消广播接收器的注册。
     */
    @Override
    protected void onResume() {
        super.onResume();
        if (LOG_SWITCH) {
            Log.d(TAG, "@@@[onResume]");
        }

        //注册强制下线接收器.
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.jsflzhong.test2.noactivity.FORCE_OFFLINE");
        receiver = new ForceOfflineReceiver();
        registerReceiver(receiver, intentFilter);
    }

    /**
     * 当跳转到其他activity时,本activity中的该方法被执行.
     * 由于NormalActivity已经把MainActivity完全遮挡住，因此onPause() 和onStop() 方法都会得到执行.
     * <p>
     * 但是, 当跳转到DialogActivity时,由于它并没有完全遮挡住本MainActivity, 此时MainActivity只是进入了暂停状态，
     * 并没有进入停止状态,所以,只有本onPause会被调用,而onStop不会被调用.
     * <p>
     * 在MainActivity按下Back键退出程序时,也会被调用.
     * <p>
     * 之前不都是在onCreate() 和onDestroy() 方法里来注册和取消注
     * 册广播接收器的么？这是因为我们始终需要保证只有处于栈顶的活动才能接收到这条强制下线
     * 广播，非栈顶的活动不应该也没有必要去接收这条广播，所以写在onResume() 和onPause()
     * 方法里就可以很好地解决这个问题，当一个活动失去栈顶位置时就会自动取消广播接收器的注册。
     */
    @Override
    protected void onPause() {
        super.onPause();
        if (LOG_SWITCH) {
            Log.d(TAG, "@@@[onPause]");
        }

        //注销强制下线接收器.
        if (receiver != null) {
            unregisterReceiver(receiver);
            receiver = null;
        }
    }

    /**
     * 当跳转到其他activity时,本activity中的该方法被执行.
     * <p>
     * 在MainActivity按下Back键退出程序时,也会被调用.
     */
    @Override
    protected void onStop() {
        super.onStop();
        if (LOG_SWITCH) {
            Log.d(TAG, "@@@[onStop]");
        }
    }

    /**
     * 在父类中定义全局的强制下线的广播接收器.
     * 目的: 广播接收器里面需要弹出一个对话框来阻塞用户的正常操作.
     * <p>
     * 之所以在父类中定义, 目的是让所有的子类activity都自动具备了接收强制下线广播的功能.
     * <p>
     * onResume() 和onPause() 这两个生命周期函数，分别在这两个方法里注册和取消注册了ForceOfflineReceiver。
     */
    class ForceOfflineReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(final Context context, Intent intent) {
            //构建一个对话框.
            //由于在ForceOfflineReceiver中弹出了一个系统级别的对话框, 所以必须在AndroidManifest中声明权限"android.permission.SYSTEM_ALERT_WINDOW".
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Warning");
            builder.setMessage("You are forced to be offline. Please try to login again.");
            //禁止用户点击'back'键来关闭这个对话框.
            builder.setCancelable(false);
            builder.setPositiveButton("OK", (dialog, which) -> {
                // 销毁所有活动
                ActivityCollector.finishAll();
                //重新启动LoginActivity这个活
                Intent loginIntent = new Intent(context, LoginActivity.class);
                //***由于我们是在广播接收器里启动活动的，因此一定要给Intent 加入FLAG_ACTIVITY_NEW_TASK 这个标志
                loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Log.d(TAG, "@@@[onReceive]: Opening login activity...");
                //重新启动LoginActivity
                context.startActivity(loginIntent);
            });
            builder.show();
        }
    }
}
