package com.example.vuquang.jars.activity.userlogin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vuquang.jars.R;
import com.example.vuquang.jars.activity.app.JarsApp;
import com.example.vuquang.jars.activity.userlogin.model.AccessMode;
import com.example.vuquang.jars.activity.utils.Pref;
import com.example.vuquang.jars.activity.utils.SharePrefHelper;

/**
 * Created by VuQuang on 4/8/2018.
 */

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";

    View mBtnNaviBack;
    EditText mEdtUsername,mEdtPassword;
    Button mBtnLogin, mBtnCreateAcc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mBtnNaviBack = findViewById(R.id.imv_navi_back);
        mBtnNaviBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JarsApp.getApp().logout(LoginActivity.this);
                finish();
            }
        });

        mEdtPassword = (EditText) findViewById(R.id.edt_password);
        mEdtUsername = (EditText) findViewById(R.id.edt_username);

        mBtnLogin = (Button) findViewById(R.id.btn_login);
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestLogin();
            }
        });

        mBtnCreateAcc = (Button) findViewById(R.id.btn_create_account);
        mBtnCreateAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCreateAcc();
            }
        });


    }

    private void goToCreateAcc() {
        Intent i = new Intent(this, CreateAccActivity.class);
        startActivity(i);
        finish();
    }


    private void goToMain(AccessMode mode) {
//        Intent i = new Intent(this, MainActivity.class);
//        startActivity(i);
//        sendBroadcast(new Intent(NotAuthenAcitivity.ACTION_LOGIN));
//        finish();
    }

    private void requestLogin(){
        final String userName = mEdtPassword.getText().toString();
        final String password = mEdtUsername.getText().toString();
        if(!TextUtils.isEmpty(password)) {
            if(isRealAccount(password)) {
                SharePrefHelper.get().setString(Pref.username_pref, userName);
                SharePrefHelper.get().setString(Pref.password_pref, password);

//                JarsApp.getApp().getAccount().user = userName;
//                JarsApp.getApp().getAccount().password = password;
                goToMain(AccessMode.ONLINE);
            } else {
                Toast.makeText(LoginActivity.this, "Đăng nhập thất bại!", Toast.LENGTH_SHORT).show();
            }
        }


    }

    private boolean isRealAccount(String password) {
        return true;
    }

//    @Override
//    public void onBackPressed() {
//        JarsApp.getApp().logout(LoginActivity.this);
//    }
}
