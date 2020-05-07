package com.jsflzhong.test2_noactivity.ui;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.jsflzhong.test2_noactivity.BasicActivity;
import com.jsflzhong.test2_noactivity.R;

/**
 * 也可以通过实现View.OnClickListener这个接口的方式,来注册点击事件.
 * View.中还有其他的事件接口.
 * <p>
 * 注意, 如果用这种方式, 则需要在下面的loadButton()方法中注册button.
 */
public class UIActivity_1 extends BasicActivity implements View.OnClickListener {

    private static final String TAG = "UIActivity_1";

    //Android这样会不会有线程问题?
    EditText editText1;

    ImageView imageView1;

    ProgressBar progressBar1;

    @Override
    public void setContentView() {
        setContentView(R.layout.ui1_activity);
    }

    @Override
    public void loadView() {
        //需要这样注册点击事件后,下面的Onclick方法才会被触发.
        Button button13 = findViewById(R.id.button_13);
        button13.setOnClickListener(this);
        Button button14 = findViewById(R.id.button_14);
        button14.setOnClickListener(this);
        Button button15 = findViewById(R.id.button_15);
        button15.setOnClickListener(this);

        //用于button同样的方式,根据id拿到editText的实例.
        editText1 = findViewById(R.id.edit_text1);

        //拿到image实例
        imageView1 = findViewById(R.id.image_view1);

        //拿到进度条实例
        progressBar1 = findViewById(R.id.progress_bar1);
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, String.format("@@@[onClick], id:[%s]", v.getId()));
        switch (v.getId()) {
            case R.id.button_13:
                //获取editText中的字符串.
                Toast.makeText(this, editText1.getText().toString(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_14:
                //可以在代码中改变layout中的图片
                imageView1.setImageResource(R.drawable.img_1);
            case R.id.button_15:
                //可以在代码中改变进度条的显示与隐藏
                if (progressBar1.getVisibility() == View.GONE) {
                    progressBar1.setVisibility(View.VISIBLE);
                } else{
                    progressBar1.setVisibility(View.GONE);
                }
                break;
            default:
                break;
        }
    }
}
