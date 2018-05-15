package com.example.vuquang.jars.activity.userlogin.login;

import android.support.annotation.NonNull;

import com.example.vuquang.jars.activity.base.BasePresenter;
import com.example.vuquang.jars.activity.data.DataManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

/**
 * Created by VuQuang on 5/15/2018.
 */

public class LoginPresenter<V extends LoginMvpView> extends BasePresenter<V> implements LoginMvpPresenter<V>{
    public LoginPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void onSignInClicked(String email, String password) {
        getMvpView().showLoading();

        getDataManager().signIn(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        getMvpView().hideLoading();
                        if (task.isSuccessful()) {
                            getMvpView().goToMain();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        getMvpView().hideLoading();
                        getMvpView().hideKeyboard();
                        getMvpView().showSnackBar(e.getMessage());
                    }
                });
    }

    @Override
    public void onSignUpClicked() {
        getMvpView().goToSignUpAct();
    }



}
