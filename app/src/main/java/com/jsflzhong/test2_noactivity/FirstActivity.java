package com.jsflzhong.test2_noactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FirstActivity extends AppCompatActivity {

    /**
     * 创建主活动
     * @param savedInstanceState savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //给当前的活动加载一个布局,通过API:setContentView(setContentView)
        //参数是布局的id, 该id可以通过R.layout.first_layout获得.
        setContentView(R.layout.first_layout);
        loadButton();
    }

    private void loadButton() {
        loadButton1();
        loadButton2();
        loadButton3();
        loadButton4();
        loadButton5();
    }

    /**
     * 显示intent
     * 加载第二个button,事件中跳到第二个活动(中的第二个布局)中去.
     */
    private void loadButton2() {
        Button button2 = (Button) findViewById(R.id.button_2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //新建一个intent,用于在点击第二个button时,打开第二个活动.
                //而第二个活动中又调用了自定义的第二个layout,所以UI上会出来第二个布局.
                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
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
}
