package com.example.vuquang.jars.activity.userlogin.login;

import android.support.annotation.NonNull;

import com.example.vuquang.jars.activity.app.JarsApp;
import com.example.vuquang.jars.activity.base.BasePresenter;
import com.example.vuquang.jars.activity.data.DataManager;
import com.example.vuquang.jars.activity.data.db.model.MonthlyHistory;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.GregorianCalendar;

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
                        if (task.isSuccessful()) {
                            getDataManager().getHistoryEndPoint().addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    getMvpView().hideLoading();
                                    MonthlyHistory monthlyHistory = getDataManager()
                                            .getMonthlyHistoryFrom(dataSnapshot, new GregorianCalendar());
                                    if(monthlyHistory != null) {
                                        JarsApp.getApp().setHistoryId(monthlyHistory.historyId);
                                        getMvpView().goToMain();
                                    } else {
                                        getMvpView().openSettingDialog();
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                    getMvpView().showMessage(databaseError.getMessage());
                                }
                            });
                        } else {
                            getMvpView().showMessage("Login failed");
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
