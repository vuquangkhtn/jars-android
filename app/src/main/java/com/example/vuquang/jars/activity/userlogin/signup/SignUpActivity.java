package com.example.vuquang.jars.activity.userlogin.signup;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vuquang.jars.R;
import com.example.vuquang.jars.activity.base.BaseActivity;
import com.example.vuquang.jars.activity.base.MvpView;
import com.example.vuquang.jars.activity.data.AppDataManager;
import com.example.vuquang.jars.activity.data.db.AppDbHelper;
import com.example.vuquang.jars.activity.data.db.model.User;
import com.example.vuquang.jars.activity.userlogin.login.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by VuQuang on 4/9/2018.
 */

public class SignUpActivity extends BaseActivity implements SignUpMvpView{
    private static final String TAG = "SignUpActivity";

    private SignUpPresenter mPresenter;

    private EditText mEdtEmail, mEdtPassword, mEdtConfirmPass;
    private Button mBtnCreateAcc;
    private View mBtnNaviBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_acc);

        mPresenter = new SignUpPresenter(AppDataManager.getDataManager());

        mPresenter.onAttach(SignUpActivity.this);

        mBtnNaviBack = findViewById(R.id.imv_navi_back);
        mBtnNaviBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onNaviBackClicked();
            }
        });

        mBtnCreateAcc = findViewById(R.id.btn_create_account);
        mBtnCreateAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEdtEmail.getText().toString();
                String password = mEdtPassword.getText().toString();
                mPresenter.onCreateAccClicked(email, password);
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
                mPresenter.checkAllEditTextFilled();
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
                mPresenter.checkAllEditTextFilled();
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
                mPresenter.checkAllEditTextFilled();
            }
        });

        mPresenter.checkAllEditTextFilled();
    }

    @Override
    public void checkAllEdtFilled() {
        if(!TextUtils.isEmpty(mEdtEmail.getText())
                && !TextUtils.isEmpty(mEdtPassword.getText())
                && !TextUtils.isEmpty(mEdtConfirmPass.getText())) {
            mBtnCreateAcc.setEnabled(true);
        } else {
            mBtnCreateAcc.setEnabled(false);
        }
    }

    @Override
    public void goToLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean isValid() {
        if(mBtnCreateAcc.isEnabled()
                && validateForm()
                && mEdtConfirmPass.getText().toString().equals(mEdtPassword.getText().toString())) {
            return true;
        }
        return false;
    }

    @Override
    public boolean validateForm() {
        boolean result = true;
        if (TextUtils.isEmpty(mEdtConfirmPass.getText().toString())) {
            mEdtConfirmPass.setError("Required");
            result = false;
        } else {
            mEdtConfirmPass.setError(null);
        }

        if (TextUtils.isEmpty(mEdtPassword.getText().toString())) {
            mEdtPassword.setError("Required");
            result = false;
        } else {
            mEdtPassword.setError(null);
        }

        if (TextUtils.isEmpty(mEdtEmail.getText().toString())) {
            mEdtEmail.setError("Required");
            result = false;
        } else {
            mEdtEmail.setError(null);
        }

        return result;
    }
}
