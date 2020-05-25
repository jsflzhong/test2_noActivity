package com.jsflzhong.test2_noactivity.persistence.sample1;


import android.content.Context;
import android.os.Bundle;
import android.widget.EditText;

import com.jsflzhong.test2_noactivity.BasicActivity;
import com.jsflzhong.test2_noactivity.R;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class PersisMainActivity extends BasicActivity {

    private EditText edit;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_persis_main);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persis_main);
        edit = (EditText) findViewById(R.id.edit);
    }

    /**
     * 保证在活动销毁之前一定会调用这个方法, 来持久化上面在onCreate()中拿到的页面中的文本.
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        String inputText = edit.getText().toString();
        save(inputText);
    }

    public void save(String inputText) {
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try {
            out = openFileOutput("data", Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(inputText);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
