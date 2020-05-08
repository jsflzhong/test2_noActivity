package com.jsflzhong.test2_noactivity;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.jsflzhong.test2_noactivity.common.ActivityCollector;

public abstract class BasicActivity extends AppCompatActivity {

    private static final String TAG = "BasicActivity";

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
        Log.d(TAG, "@@@[onDestroy]");
        //用自定义集合来管理所有活动. 此处把本活动从list中移除.
        ActivityCollector.removeActivity(this);
    }

    public void loadView() {
        Log.d(TAG,"@@@[loadButton] in parent");
    }

    /**
     * 在本活动被关闭时,会调用下面的onSaveInstanceState(),会存储一个值, 这里可以在再重启时拿到.
     * @param savedInstanceState savedInstanceState
     */
    public void getSavedInstanceState(Bundle savedInstanceState) {
        Log.d(TAG, "@@@[getSavedInstanceState] in parent");
    }

    /**
     * 需要子类指定对应的layout文件
     */
    public abstract void setContentView();

    /**
     * 从intent中, 拿出来从上个活动传过来的数据
     */
    public void getIntentFromLastActivity() {
        Log.d(TAG, "@@@[]getIntentFromLastActivity in parent");
    }

    /**
     * 在第一次被创建时会执行. 执行顺序:2
     *
     * 当按下back键返回MainActivity时也会被调用.(由于之前MainActivity已经进入了停止状态，所以onRestart() 方法会得到执行)
     */
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "@@@[onStart]");
    }

    /**
     * 在第一次被创建时会执行. 执行顺序:3
     *
     * 当按下back键返回MainActivity时也会被调用.
     */
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "@@@[onResume]");
    }

    /**
     * 当按下back键返回MainActivity时会被调用.
     */
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "@@@[onRestart]");
    }

    /**
     * 当跳转到其他activity时,本activity中的该方法被执行.
     * 由于NormalActivity已经把MainActivity完全遮挡住，因此onPause() 和onStop() 方法都会得到执行.
     *
     * 但是, 当跳转到DialogActivity时,由于它并没有完全遮挡住本MainActivity, 此时MainActivity只是进入了暂停状态，
     *  并没有进入停止状态,所以,只有本onPause会被调用,而onStop不会被调用.
     *
     *  在MainActivity按下Back键退出程序时,也会被调用.
     */
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "@@@[onPause]");
    }

    /**
     * 当跳转到其他activity时,本activity中的该方法被执行.
     *
     * 在MainActivity按下Back键退出程序时,也会被调用.
     */
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "@@@[onStop]");
    }
}
