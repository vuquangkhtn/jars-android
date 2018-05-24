package com.example.vuquang.jars.activity.data.db.dao;

import android.content.Intent;
import android.support.annotation.NonNull;

import com.example.vuquang.jars.activity.data.db.model.User;
import com.example.vuquang.jars.activity.utils.KeyPref;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by VuQuang on 5/14/2018.
 */

public class UserDao {
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    public UserDao(DatabaseReference database, FirebaseAuth auth) {
        mDatabase = database;
        mAuth = auth;
    }

    public DatabaseReference getUserEndPoint() {
        return mDatabase.child(KeyPref.USER_KEY);
    }

    public String insertUser(String id, String username, String email){
        User user = new User(username,email);
        getUserEndPoint().child(id).setValue(user);
        return id;
    }

    public boolean isLogined() {
        if(mAuth.getCurrentUser() != null) {
            return true;
        } else {
            return false;
        }
    }

    public Task<AuthResult> signIn(String email, String password) {
        return mAuth.signInWithEmailAndPassword(email, password);
    }

    public FirebaseAuth signOut() {
        mAuth.signOut();
        return mAuth;
    }

    public Task<AuthResult> signUp(String email, String password) {
        return mAuth.createUserWithEmailAndPassword(email, password);
    }

    public String getUsername() {
        String email = mAuth.getCurrentUser().getEmail();
        if(email == null) {
            return "Anonymous";
        }

        if (email.contains("@")) {
            return email.split("@")[0];
        } else {
            return email;
        }
    }
}
