package com.jsflzhong.test2_noactivity.persistence.sample1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.jsflzhong.test2_noactivity.R;

/**
 * 以SharedPreferences的方式存储和读取数据
 */
public class PersisSharedPreferencesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persis_shared_preferences);
        doSaveDataButoon();
        doLoadDataButton();
    }

    /**
     * 以SharedPreferences的方式存储读取数据.
     */
    private void doLoadDataButton() {
        Button restoreData = findViewById(R.id.restore_data);
        restoreData.setOnClickListener(v -> {
            //参数1: key名字, 参数2:当取不到值时的默认值.
            SharedPreferences pref = getSharedPreferences("data", MODE_PRIVATE);
            String name = pref.getString("name", "");
            int age = pref.getInt("age", 0);
            boolean married = pref.getBoolean("married", false);
            Log.d("@@@MainActivity", "name is " + name);
            Log.d("@@@MainActivity", "age is " + age);
            Log.d("@@@MainActivity", "married is " + married);
        });
    }

    /**
     * 以 SharedPreferences 的方式存储写数据.
     */
    private void doSaveDataButoon() {
        Button saveData = findViewById(R.id.save_data);
        saveData.setOnClickListener(v -> {
            //第一参: 文件名
            SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
            editor.putString("name", "Tom");
            editor.putInt("age", 28);
            editor.putBoolean("married", false);
            editor.apply();
        });
    }
}
