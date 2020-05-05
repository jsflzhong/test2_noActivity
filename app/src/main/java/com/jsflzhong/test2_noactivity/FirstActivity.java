package com.jsflzhong.test2_noactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
    }

    /**
     * 加载第二个button,事件中跳到第二个活动(中的第二个布局)中去.
     */
    private void loadButton2() {
        Button button2 = (Button) findViewById(R.id.button_2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //新建一个intent,用于在点击第一个button时,打开第二个活动.
                //而第二个活动中又调用了自定义的第二个layout,所以UI上会出来第二个布局.
                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                //执行上面的intent, 调用内置API.
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
