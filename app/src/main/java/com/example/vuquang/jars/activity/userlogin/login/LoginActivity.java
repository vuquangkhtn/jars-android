package com.example.vuquang.jars.activity.userlogin.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.vuquang.jars.R;
import com.example.vuquang.jars.activity.base.BaseActivity;
import com.example.vuquang.jars.activity.data.AppDataManager;
import com.example.vuquang.jars.activity.main.MainActivity;
import com.example.vuquang.jars.activity.setting.SettingsFragment;
import com.example.vuquang.jars.activity.userlogin.NotAuthenAcitivity;
import com.example.vuquang.jars.activity.userlogin.signup.SignUpActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by VuQuang on 4/8/2018.
 */

public class LoginActivity extends BaseActivity implements LoginMvpView{
    private static final String TAG = "LoginActivity";

    private LoginMvpPresenter<LoginMvpView> presenter;

    EditText mEdtEmail,mEdtPassword;
    Button mBtnLogin, mBtnSignUp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        presenter = new LoginPresenter<>(new AppDataManager(
                FirebaseDatabase.getInstance().getReference(),
                FirebaseAuth.getInstance()
        ));

        mEdtPassword = (EditText) findViewById(R.id.edt_password);
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

        mEdtEmail = (EditText) findViewById(R.id.edt_email);
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

        mBtnLogin = (Button) findViewById(R.id.btn_login);
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validateForm()) {
                    return;
                }

                String email = String.valueOf(mEdtEmail.getText());
                String pass = String.valueOf(mEdtPassword.getText());

                presenter.onSignInClicked(email, pass);
            }
        });

        mBtnSignUp = (Button) findViewById(R.id.btn_create_account);
        mBtnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onSignUpClicked();
            }
        });

        checkAllEdtFilled();
        presenter.onAttach(LoginActivity.this);

    }

    @Override
    public void goToMain() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        sendBroadcast(new Intent(NotAuthenAcitivity.ACTION_LOGIN));
        finish();
    }

    @Override
    public void goToSignUpAct() {
        Intent i = new Intent(this, SignUpActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void goToSetting() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        sendBroadcast(new Intent(NotAuthenAcitivity.ACTION_LOGIN));
        finish();
    }

    private void checkAllEdtFilled() {
        if(!TextUtils.isEmpty(mEdtEmail.getText())
                && !TextUtils.isEmpty(mEdtPassword.getText()))
        {
            mBtnLogin.setEnabled(true);
        } else {
            mBtnLogin.setEnabled(false);
        }
    }

    private boolean validateForm() {
        boolean result = true;
        if (TextUtils.isEmpty(mEdtEmail.getText().toString())) {
            mEdtEmail.setError("Required");
            result = false;
        } else {
            mEdtEmail.setError(null);
        }

        if (TextUtils.isEmpty(mEdtPassword.getText().toString())) {
            mEdtPassword.setError("Required");
            result = false;
        } else {
            mEdtPassword.setError(null);
        }

        return result;
    }
}
