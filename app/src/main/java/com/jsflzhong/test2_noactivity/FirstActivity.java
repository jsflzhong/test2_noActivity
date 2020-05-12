package com.jsflzhong.test2_noactivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jsflzhong.test2_noactivity.layout.LinearLayoutActivity1;
import com.jsflzhong.test2_noactivity.layout.ListViewActivity;
import com.jsflzhong.test2_noactivity.layout.ListViewActivity2;
import com.jsflzhong.test2_noactivity.layout.MsgActivity;
import com.jsflzhong.test2_noactivity.layout.NinePatchActivity;
import com.jsflzhong.test2_noactivity.layout.RecyclerViewActivity;
import com.jsflzhong.test2_noactivity.layout.RecyclerViewLinearRollingActivity;
import com.jsflzhong.test2_noactivity.layout.RecyclerViewStaggeredGridActivity;
import com.jsflzhong.test2_noactivity.layout.broadcast.BroadNetWorkActivity;
import com.jsflzhong.test2_noactivity.lifeCycle.DialogActivity;
import com.jsflzhong.test2_noactivity.lifeCycle.NormalActivity;
import com.jsflzhong.test2_noactivity.ui.UIActivity_1;

import java.util.Optional;

public class FirstActivity extends BasicActivity {

    private static final String TAG = "FirstActivity";

    /**
     * 在本活动被关闭时,会调用下面的onSaveInstanceState(),会存储一个值, 这里可以在再重启时拿到.
     *
     * @param savedInstanceState savedInstanceState
     */
    @Override
    public void getSavedInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            String tempData = savedInstanceState.getString("tempKey");
            //这里可以做很多操作,例如把这个值再写回到指定的文本框里,等.
            Log.d(TAG, "@@@[onCreate] tempData is:" + tempData);
        }
    }

    /**
     * 绑定指定的layout.xml
     */
    @Override
    public void setContentView() {
        setContentView(R.layout.first_layout);
    }

    public void getIntentFromLastActivity() {
        Log.d(TAG, "@@@[getIntentFromLastActivity]");
    }

    @Override
    public void hideSupportActionBar() {
        ActionBar actionBar = getSupportActionBar();
        Optional.ofNullable(actionBar).ifPresent(ActionBar::hide);
    }

    @Override
    public void loadView() {
        loadButton1();
        loadButton2();
        loadButton3();
        loadButton4();
        loadButton5();
        loadButton6();
        loadButton7();
        loadButton9();
        loadButton10();
        loadButton12();
        loadButton22();
        loadButton23();
        loadButton24();
        loadButton25();
        loadButton26();
        loadButton27();
        loadButton28();
        loadButton29();
        loadButton30();
    }

    /**
     * 加载第一个button,事件中给个toast
     */
    private void loadButton1() {
        //加载自定义的Button布局,并给一个点击监听器, 行为是出来一个Toast.
        Button button1 = (Button) findViewById(R.id.button_1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FirstActivity.this, "You clicked Button 1", Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }

    /**
     * 显示intent
     * 加载第二个button,事件中跳到第二个活动(中的第二个布局)中去. 并且带值过去.
     */
    private void loadButton2() {
        Button button2 = (Button) findViewById(R.id.button_2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //新建一个intent,用于在点击第二个button时,打开第二个活动.
                //而第二个活动中又调用了自定义的第二个layout,所以UI上会出来第二个布局.
                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                //传递数据给secondActivity.
                String data = "testValue";
                intent.putExtra("key", data);
                //执行上面的intent, 调用内置API.
                startActivity(intent);
            }
        });
    }

    /**
     * 隐式intent1.
     * 加载第三个button,事件中跳到第二个活动(中的第二个布局)中去.
     * 在AndroidManifest.xml中使用default的category,此处不用调用category.
     */
    private void loadButton3() {
        Button button3 = findViewById(R.id.button_3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //新建一个intent,用于在点击第三个button时,隐式的打开第二个活动.
                //字符串需要与manifest.xml中的intent-filter中的字符串相匹配
                //该字符串是定义在manifest.xml中的SecondActivity中的,即,当点击firstActivity中的该按钮时,会跳转到secondActivity的layout中去.
                Intent intent = new Intent("com.example.activitytest.ACTION_START");
                //执行上面的intent, 调用内置API.
                startActivity(intent);
            }
        });
    }

    /**
     * 隐式intent2.
     * 加载第四个button,事件中跳到第二个活动(中的第二个布局)中去.
     * 在AndroidManifest.xml中使用自定义的的category,此处需要调用category.
     */
    private void loadButton4() {
        Button button3 = findViewById(R.id.button_4);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //新建一个intent,用于在点击第四个button时,隐式的打开第二个活动.
                //字符串需要与manifest.xml中的intent-filter中的字符串相匹配
                //该字符串是定义在manifest.xml中的SecondActivity中的,即,当点击firstActivity中的该按钮时,会跳转到secondActivity的layout中去.
                Intent intent = new Intent("com.example.activitytest.ACTION_START");
                //由于manifest.xml中配置了非default的category,所以这里需要调用该category.
                intent.addCategory("android.intent.category.MY_CATEGORY");
                //执行上面的intent, 调用内置API.
                startActivity(intent);
            }
        });
    }

    /**
     * 隐式intent3.
     * --ACTION_VIEW 浏览器
     * 加载第五个button,事件中: 调用系统的浏览器,打开指定的网址
     * 注意: 并不是内嵌入系统浏览器, 而是调用并跳转过去.
     */
    private void loadButton5() {
        Button button3 = findViewById(R.id.button_5);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //安卓系统内置的动作常量
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.baidu.com"));
                startActivity(intent);
            }
        });
    }

    /**
     * 隐式intent4.
     * 加载第六个button,事件中: 调用系统的拨打电话.
     */
    private void loadButton6() {
        Button button6 = findViewById(R.id.button_6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:10086"));
                startActivity(intent);
            }
        });
    }

    /**
     * 在上个活动中(本活动),获得从下个活动(SecondActivity.class)传回来的数据.
     * 加载第七个button,事件中:调用secondActivity,并调用回调API.
     */
    private void loadButton7() {
        View button7 = findViewById(R.id.button_7);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                //调用回调API, 参数二是个唯一值.
                startActivityForResult(intent, 1);
            }
        });
    }

    /**
     * 回调函数.
     * 接收从下个活动中(SecondActivity.class), 在那边调用finish()销毁那个活动后, 传回来的数据.
     *
     * @param requestCode 在启动活动时传入的请求码.
     * @param resultCode  在返回数据时传入的处理结果.
     * @param data        携带返回数据的intent.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    String returnValue = data.getStringExtra("returnKey");
                    Log.d(TAG, "@@@returnValue:" + returnValue);
                }
                break;
            default:
        }
    }

    /**
     * 加载第9个button,事件中:打开NormalActivity
     */
    private void loadButton9() {
        View button9 = findViewById(R.id.button_9);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, NormalActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 加载第10个button,事件中:打开DialogActivity.
     */
    private void loadButton10() {
        View button10 = findViewById(R.id.button_10);
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, DialogActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 加载第12个button,事件中:打开UIActivity1
     */
    private void loadButton12() {
        View button12 = findViewById(R.id.button_12);
        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, UIActivity_1.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 加载第22个button,事件中:打开LayoutActivity
     */
    private void loadButton22() {
        View button22 = findViewById(R.id.button_22);
        button22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, LinearLayoutActivity1.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 加载第23个button,事件中:打开listView Activity
     */
    private void loadButton23() {
        View button23 = findViewById(R.id.button_23);
        button23.setOnClickListener(v -> {
            Intent intent = new Intent(FirstActivity.this, ListViewActivity.class);
            startActivity(intent);
        });
    }

    /**
     * 加载第24个button,事件中:打开listView Activity
     */
    private void loadButton24() {
        View button24 = findViewById(R.id.button_24);
        button24.setOnClickListener(v -> {
            Intent intent = new Intent(FirstActivity.this, ListViewActivity2.class);
            startActivity(intent);
        });
    }

    /**
     * 加载第25个button,事件中:打开 RecyclerViewActivity.
     */
    private void loadButton25() {
        View button25 = findViewById(R.id.button_25);
        button25.setOnClickListener(v -> {
            Intent intent = new Intent(FirstActivity.this, RecyclerViewActivity.class);
            startActivity(intent);
        });
    }

    /**
     * 加载第26个button,事件中:打开 RecyclerViewLinearRollingActivity.
     */
    private void loadButton26() {
        View button26 = findViewById(R.id.button_26);
        button26.setOnClickListener(v -> {
            Intent intent = new Intent(FirstActivity.this, RecyclerViewLinearRollingActivity.class);
            startActivity(intent);
        });
    }

    /**
     * 加载第27个button,事件中:打开 RecyclerViewStaggeredGridActivity.
     */
    private void loadButton27() {
        View button27 = findViewById(R.id.button_27);
        button27.setOnClickListener(v -> {
            Intent intent = new Intent(FirstActivity.this, RecyclerViewStaggeredGridActivity.class);
            startActivity(intent);
        });
    }

    /**
     * 加载第28个button,事件中:打开 RecyclerViewStaggeredGridActivity.
     */
    private void loadButton28() {
        View button27 = findViewById(R.id.button_28);
        button27.setOnClickListener(v -> {
            Intent intent = new Intent(FirstActivity.this, NinePatchActivity.class);
            startActivity(intent);
        });
    }

    /**
     * 加载第29个button,事件中:打开 RecyclerViewStaggeredGridActivity.
     */
    private void loadButton29() {
        View button29 = findViewById(R.id.button_29);
        button29.setOnClickListener(v -> {
            Intent intent = new Intent(FirstActivity.this, MsgActivity.class);
            startActivity(intent);
        });
    }

    /**
     * 加载第30个button,事件中:打开 RecyclerViewStaggeredGridActivity.
     */
    private void loadButton30() {
        View button30 = findViewById(R.id.button_30);
        button30.setOnClickListener(v -> {
            Intent intent = new Intent(FirstActivity.this, BroadNetWorkActivity.class);
            startActivity(intent);
        });
    }

    /**
     * 创建菜单
     *
     * @param menu menu
     * @return true:显示出来菜单
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //创建菜单, 加载自定义的菜单布局(res/menu/main.xml)
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * 自定义菜单响应事件
     *
     * @param item item
     * @return true:
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item:
                Toast.makeText(this, "You clicked add", Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item:
                Toast.makeText(this, "You clicked remove", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }

    /**
     * 在本activity被销毁时, 会被调用.可用来保存页面上的临时数据.
     * 在onCreate()方法中,也有个Bundle类型的参数, 即, 在本活动被销毁后, 重新新建时,会调用onCreate(),就会在该方法中拿到该值, 到此形成一个闭环.
     * <p>
     * 注意,也可以把该Bundle放入Intent对象中传递!
     *
     * @param outState outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String tempData = "TempData on the UI";
        outState.putString("tempKey", tempData);
    }
}
