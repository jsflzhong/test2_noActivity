package com.jsflzhong.test2_noactivity.lifeCycle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.jsflzhong.test2_noactivity.R;

public class NormalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.normal_layout);
    }
}
