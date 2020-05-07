package com.jsflzhong.test2_noactivity.lifeCycle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.jsflzhong.test2_noactivity.BasicActivity;
import com.jsflzhong.test2_noactivity.R;

public class NormalActivity extends BasicActivity {

    @Override
    public void setContentView() {
        setContentView(R.layout.normal_layout);
    }
}
