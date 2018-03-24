package com.example.vuquang.jars.activity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vuquang.jars.R;
import com.example.vuquang.jars.activity.app.Common;
import com.example.vuquang.jars.activity.app.JarsApp;
import com.example.vuquang.jars.activity.utils.SharePrefHelper;

/**
 * Created by chuongnguyen on 4/22/17.
 */

public class InputPasswordActivity extends NotAuthenAcitivity {
    private static final String TAG = "InputPasswordActivity";

    View mBtnNaviBack;
    EditText mEdtPassword;
    Button mBtnNext;
//    View keyboardView;

    String userName;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_password);


        mBtnNaviBack = findViewById(R.id.imv_navi_back);
        mBtnNaviBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mEdtPassword = (EditText) findViewById(R.id.edt_password);

        mBtnNext = (Button) findViewById(R.id.btn_next);
        mBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestLogin();
            }
        });

        userName = getIntent().getStringExtra(Common.EXTRA_USER_NAME);
    }


    private void goToMain() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        sendBroadcast(new Intent(NotAuthenAcitivity.ACTION_LOGIN));
        finish();
    }

    private void requestLogin(){
        final String password = mEdtPassword.getText().toString();
        if(!TextUtils.isEmpty(password)) {
            if(isRealAccount(password)) {
                SharePrefHelper.get().setString("username_pref", userName);
                SharePrefHelper.get().setString("password_pref", password);

                JarsApp.getApp().getAccount().user = userName;
                JarsApp.getApp().getAccount().password = password;
                goToMain();
            } else {
                Toast.makeText(InputPasswordActivity.this, "Đăng nhập thất bại!", Toast.LENGTH_SHORT).show();
            }
        }


    }

    private boolean isRealAccount(String password) {
        return true;
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        JarsApp.getApp().logout(InputPasswordActivity.this);
    }
}
