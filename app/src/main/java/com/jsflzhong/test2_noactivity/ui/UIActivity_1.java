package com.jsflzhong.test2_noactivity.ui;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jsflzhong.test2_noactivity.BasicActivity;
import com.jsflzhong.test2_noactivity.R;

/**
 * 也可以通过实现View.OnClickListener这个接口的方式,来注册点击事件.
 * View.中还有其他的事件接口.
 *
 * 注意, 如果用这种方式, 则需要在下面的loadButton()方法中注册button.
 */
public class UIActivity_1 extends BasicActivity implements View.OnClickListener {

    private static final String TAG = "UIActivity_1";

    @Override
    public void setContentView() {
        setContentView(R.layout.ui1_activity);
    }

    @Override
    public void loadButton() {
        //需要这样注册点击事件后,下面的Onclick方法才会被触发.
        Button button13 = findViewById(R.id.button_13);
        button13.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, String.format("@@@[onClick], id:[%s]",v.getId()));
        switch (v.getId()) {
            case R.id.button_13:
                Toast.makeText(UIActivity_1.this, "button_13", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
    }
}
