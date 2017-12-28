package com.dev.kiko.myinvoice.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.dev.kiko.myinvoice.activities.FirebaseLoginActivity;
import com.dev.kiko.myinvoice.activities.MainActivity;
import com.dev.kiko.myinvoice.activities.NewInvoiceActivity;
import com.dev.kiko.myinvoice.manager.MasterManager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.Executor;

import static com.facebook.GraphRequest.TAG;

/**
 * Created by kikogoinhas on 21/12/2017.
 */

public class UserManager implements MasterManager.MyInvoiceUserManager {

    private FirebaseAuth mAuth;

    private Context mContext;

    public UserManager(FirebaseAuth auth, Context context) {
        mAuth = auth;
        mContext = context;

    }
    @Override
        public void signIn(FirebaseUser user) {

    }

    public void signIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener((Executor) this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            mContext.startActivity(new Intent(mContext, MainActivity.class));
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(mContext, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }


                    }
                });
    }

    @Override
    public void signOut(FirebaseUser user) {

    }

    @Override
    public boolean isUserConnected(FirebaseUser user) {

        if (user != null) {
            return true;
        }

        return false;
    }

    @Override
    public void createNewAccount(String email, String password) {
        
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener((Executor) this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            signIn(user);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(mContext, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });

    }
}
