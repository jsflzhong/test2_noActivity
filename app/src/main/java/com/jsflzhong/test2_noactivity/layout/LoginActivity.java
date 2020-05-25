package com.jsflzhong.test2_noactivity.layout;


import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.jsflzhong.test2_noactivity.BasicActivity;
import com.jsflzhong.test2_noactivity.FirstActivity;
import com.jsflzhong.test2_noactivity.R;
import com.jsflzhong.test2_noactivity.persistence.sample1.PersisFileActivity;
import com.jsflzhong.test2_noactivity.persistence.sample1.PersisSharedPreferencesActivity;

/**
 * 登录活动
 */
public class LoginActivity extends BasicActivity {

    private EditText accountEdit;
    private EditText passwordEdit;
    private Button login;

    //持久化相关
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private CheckBox rememberPass;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_login);
        handleElement();
    }

    private void handleElement() {
        doLoginButtonWithRememberPswdInSharedPreferences();
        doToTestHomepageButton();
        toPersistence1();
        toPersistence2();
    }

    private void toPersistence1() {
        Button persisButton1 = findViewById(R.id.persis1);
        persisButton1.setOnClickListener(v -> {
            startActivity(new Intent(this, PersisFileActivity.class));
        });
    }

    private void toPersistence2() {
        Button persisButton1 = findViewById(R.id.persis2);
        persisButton1.setOnClickListener(v -> {
            startActivity(new Intent(this, PersisSharedPreferencesActivity.class));
        });
    }

    /**
     * 跳转到第一个用于测试的主页面activity.
     */
    private void doToTestHomepageButton() {
        Button toTestHomnepageButton = (Button) findViewById(R.id.toTestHomepage);
        toTestHomnepageButton.setOnClickListener(v -> {
            Intent firstActivityIntent = new Intent(this, FirstActivity.class);
            startActivity(firstActivityIntent);
        });
    }

    /**
     * 登录功能, 记住密码功能, 使用SharedPreferences做持久化.(不推荐, 因为不安全, 这里只是示例).
     */
    private void doLoginButtonWithRememberPswdInSharedPreferences() {
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        rememberPass = findViewById(R.id.remember_pass);
        login = findViewById(R.id.login);
        accountEdit = findViewById(R.id.account);
        passwordEdit = findViewById(R.id.password);
        boolean isRemember = pref.getBoolean("remember_password", false);
        if (isRemember) {
            // 将账号和密码都回显到文本框中
            String account = pref.getString("account", "");
            String password = pref.getString("password", "");
            accountEdit.setText(account);
            passwordEdit.setText(password);
            rememberPass.setChecked(true);
        }
        login.setOnClickListener(v -> {
            String account = accountEdit.getText().toString();
            String password = passwordEdit.getText().toString();
            // 如果账号是admin且密码是123456，就认为登录成功
            if (account.equals("admin") && password.equals("123456")) {
                editor = pref.edit();
                // 检查复选框是否被选中. 在上面的判断逻辑中checked的.
                if (rememberPass.isChecked()) {
                    editor.putBoolean("remember_password", true);
                    editor.putString("account", account);
                    editor.putString("password", password);
                } else {
                    editor.clear();
                }
                editor.apply();
                //登录成功, 跳转.
                Intent intent = new Intent(LoginActivity.this, LoginMainActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(LoginActivity.this, "account or password is invalid",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void doLoginButtonWithoutRememberPswd() {
        accountEdit = findViewById(R.id.account);
        passwordEdit = findViewById(R.id.password);
        login = findViewById(R.id.login);
        login.setOnClickListener(v -> {
            String account = accountEdit.getText().toString();
            String password = passwordEdit.getText().toString();
            // 如果账号是admin且密码是123456，就认为登录成功
            if (account.equals("admin") && password.equals("1")) {
                Intent intent = new Intent(LoginActivity.this, LoginMainActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(LoginActivity.this, "@@@[handleElement]account or password is invalid", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
