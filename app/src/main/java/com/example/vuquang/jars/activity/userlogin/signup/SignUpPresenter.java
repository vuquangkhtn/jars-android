package com.example.vuquang.jars.activity.userlogin.signup;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.example.vuquang.jars.activity.base.BasePresenter;
import com.example.vuquang.jars.activity.data.DataManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by VuQuang on 5/24/2018.
 */

public class SignUpPresenter<V extends SignUpMvpView> extends BasePresenter<V> implements SignUpMvpPresenter<V>{
    public SignUpPresenter(DataManager dataManager) {
        super(dataManager);
    }

    public void onCreateAccClicked(String email, String password) {
        if (getMvpView().isValid()) {
            getMvpView().showLoading();
            getDataManager().signUp(email,password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            getMvpView().hideLoading();
                            if (task.isSuccessful()) {
                                onAuthSuccess(task.getResult().getUser());
                            } else {
                                getMvpView().showMessage("Sign Up Failed");
                            }
                        }
                    });
        } else {
            getMvpView().showMessage("password is unconfirmed");
        }
    }

    @Override
    public void onNaviBackClicked() {
        getMvpView().goToLogin();
    }

    @Override
    public void checkAllEditTextFilled() {
        getMvpView().checkAllEdtFilled();
    }

    private void onAuthSuccess(FirebaseUser user) {
        String username = usernameFromEmail(user.getEmail());

        // Write new user
        getDataManager().insertUser(user.getUid(), username, user.getEmail());

        // Go to MainActivity
        getMvpView().goToLogin();
        getMvpView().showMessage("Sign Up Successful");
    }

    private String usernameFromEmail(String email) {
        if (email.contains("@")) {
            return email.split("@")[0];
        } else {
            return email;
        }
    }
}
