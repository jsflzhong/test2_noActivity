package com.jsflzhong.test2_noactivity.layout;


import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jsflzhong.test2_noactivity.BasicActivity;
import com.jsflzhong.test2_noactivity.FirstActivity;
import com.jsflzhong.test2_noactivity.R;
import com.jsflzhong.test2_noactivity.persistence.sample1.PersisMainActivity;

/**
 * 登录活动
 */
public class LoginActivity extends BasicActivity {

    private EditText accountEdit;
    private EditText passwordEdit;
    private Button login;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_login);
        handleElement();
    }

    private void handleElement() {
        doLoginButton();
        doToTestHomepageButton();
        toPersistence1();
    }

    private void toPersistence1() {
        Button persisButton1 = findViewById(R.id.persis1);
        persisButton1.setOnClickListener(v -> {
            startActivity(new Intent(this, PersisMainActivity.class));
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

    private void doLoginButton() {
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
