package com.jsflzhong.test2_noactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = "SecondActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);

        //获取intent
        Intent intent = getIntent();
        //从intent中, 拿出来从上个活动传过来的数据.
        String valueFromIntent = intent.getStringExtra("key");
        Log.d(TAG, "@@@valueFromIntent:" + valueFromIntent);

        loadButton();
    }

    private void loadButton() {
        loadButton8();
    }

    /**
     * 加载第七个button,事件中: 把要向上个活动传递的数据,放入intent,
     *  然后调用finish()销毁本活动, 然后触发上个活动中的onActivityResult()方法.
     *
     */
    private void loadButton8() {
        Button button8 = findViewById(R.id.button_8);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //构建一个Intent,由于本次只用来传递数据,没任何意图, 所以不调用带参数的构造函数.
                Intent intent = new Intent();
                intent.putExtra("returnKey", "send testValue to firstActivity");
                //向上个活动传递数据
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
