package edu.gatech.cs2340.cs2340application.controller;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Handled log in using Firebase
 *
 * @version 1.0
 * @author Daniel Becker
 */

public class FirebaseLogin implements LoginProviderInterface {
    @Override
    public void login(String username, String password, final LoginResultInterface handler) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful() || FirebaseAuth.getInstance().getCurrentUser() == null) {
                    handler.failure("Incorrect username / password combination.");
                } else {
                    handler.success();
                }
            }
        });
    }
}
