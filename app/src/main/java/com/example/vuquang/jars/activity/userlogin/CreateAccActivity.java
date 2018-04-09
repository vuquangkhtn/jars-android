package com.example.vuquang.jars.activity.userlogin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vuquang.jars.R;
import com.example.vuquang.jars.activity.app.JarsApp;

/**
 * Created by VuQuang on 4/9/2018.
 */

public class CreateAccActivity extends AppCompatActivity {
    private static final String TAG = "CreateAccActivity";

    private EditText mEdtUsername, mEdtPassword, mEdtConfirmPass, mEdtEmail;
    private Button mBtnCreateAcc;
    private View mBtnNaviBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_acc);

        mBtnNaviBack = findViewById(R.id.imv_navi_back);
        mBtnNaviBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLogin();
            }
        });

        mBtnCreateAcc = findViewById(R.id.btn_create_account);
        mBtnCreateAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValid()) {
                    Toast.makeText(CreateAccActivity.this, "Successful!", Toast.LENGTH_SHORT).show();
                    requestCreateAcc();
                } else {
                    Toast.makeText(CreateAccActivity.this, "Create failed. Check your fill", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mEdtUsername = findViewById(R.id.edt_username);
        mEdtUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkAllEdtFilled();
            }
        });
        
        mEdtPassword = findViewById(R.id.edt_password);
        mEdtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkAllEdtFilled();
            }
        });

        mEdtConfirmPass = findViewById(R.id.edt_confirm_password);
        mEdtConfirmPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkAllEdtFilled();
            }
        });

        mEdtEmail = findViewById(R.id.edt_email);
        mEdtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkAllEdtFilled();
            }
        });
        checkAllEdtFilled();
    }

    private void checkAllEdtFilled() {
        if(!TextUtils.isEmpty(mEdtUsername.getText())
                && !TextUtils.isEmpty(mEdtPassword.getText())
                && !TextUtils.isEmpty(mEdtConfirmPass.getText())
                && !TextUtils.isEmpty(mEdtEmail.getText())) {
            mBtnCreateAcc.setEnabled(true);
        } else {
            mBtnCreateAcc.setEnabled(false);
        }
    }

    private void goToLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }

    private void requestCreateAcc() {
        //Todo
        goToLogin();
    }

    public boolean isValid() {
        if(mBtnCreateAcc.isEnabled() && mEdtConfirmPass.getText().toString().equals(mEdtPassword.getText().toString())) {
            return true;
        }
        return false;
    }
}
