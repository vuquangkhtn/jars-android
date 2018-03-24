package com.example.vuquang.jars.activity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.vuquang.jars.R;
import com.example.vuquang.jars.activity.app.Common;

public class WelcomeActivity extends AppCompatActivity {
    private EditText mPhoneInputEdt;
    private Button mBtnLogin;
    private TextView mTvCreateAcc;

    String phoneNum;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        mBtnLogin = (Button) findViewById(R.id.btn_login);
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLogin();
            }
        });

        mPhoneInputEdt = (EditText) findViewById(R.id.user_input);

        mTvCreateAcc = (TextView) findViewById(R.id.tv_create_new_acc);

//        mTvCreateAcc.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                goToCreateAcc();
//            }
//        });
    }

//    private void goToCreateAcc() {
//        Intent intent = new Intent(this, CreateAccActivity.class);
//        startActivity(intent);
//    }

    private void goToLogin() {
        phoneNum = mPhoneInputEdt.getText().toString();
        if(!TextUtils.isEmpty(phoneNum)) {
            Intent intent = new Intent(this, InputPasswordActivity.class);
            intent.putExtra(Common.EXTRA_USER_NAME, phoneNum);
            startActivity(intent);
            finish();
        }
    }
}