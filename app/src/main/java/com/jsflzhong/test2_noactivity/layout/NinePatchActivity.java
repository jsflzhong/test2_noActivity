package com.jsflzhong.test2_noactivity.layout;


import android.widget.Toast;

import com.jsflzhong.test2_noactivity.BasicActivity;
import com.jsflzhong.test2_noactivity.R;

public class NinePatchActivity extends BasicActivity {

    @Override
    public void setContentView() {
        setContentView(R.layout.nine_patc_layout);
        Toast.makeText(this, "NinePatchActivity", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadView() {

    }
}
