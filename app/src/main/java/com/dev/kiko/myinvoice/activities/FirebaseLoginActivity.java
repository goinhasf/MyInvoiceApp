package com.dev.kiko.myinvoice.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.SupportActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.dev.kiko.myinvoice.R;
import com.dev.kiko.myinvoice.controller.CompanyController;
import com.dev.kiko.myinvoice.controller.UserManager;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.Arrays;
import java.util.List;

public class FirebaseLoginActivity extends Activity implements View.OnClickListener {

    // Tag name for this acitivity
    private static final String TAG = "FirebaseLoginActivity";
    // Firebase Authenticator
    private FirebaseAuth mAuth;
    // The current user
    private FirebaseUser currentUser;
    // User Manager object
    private UserManager userManager;
    // Google client
    private GoogleSignInClient mGoogleSignInClient;
    // The controller of this activity
    private CompanyController controller;
    //Normal Sign in button
    private Button mSignInButton;
    // The sign in google button
    private SignInButton mGoogleSignInButton;
    // The facebook sign in button
    private LoginButton mFacebookLogInButton;
    // The progress dialog
    private ProgressBar mProgressBar;
    // Email field
    private EditText mEmailEditText;
    // Password field
    private EditText mPasswordEditText;
    // The sign in request code for this app
    private static final int RC_SIGN_IN = 980;
    // CallbackManager for facebook
    private CallbackManager mCallbackManager;
    // The context
    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_login);

        // Initialize UI components
        mGoogleSignInButton = (SignInButton) findViewById(R.id.google_bt_sign_in);
        mFacebookLogInButton = (LoginButton) findViewById(R.id.facebook_bt_sign_in);
        mSignInButton = (Button) findViewById(R.id.bt_sign_in);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        mEmailEditText = (EditText) findViewById(R.id.et_sign_in_email);
        mPasswordEditText = (EditText) findViewById(R.id.et_sign_in_password);


        // Google login setup
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // Initializes controller
        controller = CompanyController.getInstance();


        mContext = this;

        // Add OnClickListeners to Buttons
        mGoogleSignInButton.setOnClickListener(this);
        mFacebookLogInButton.setOnClickListener(this);
        mSignInButton.setOnClickListener(this);


    }

    @Override
    protected void onStart() {
        super.onStart();

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        userManager = new UserManager(mAuth, this);

        if (userManager.isUserConnected(currentUser)) {

            startActivity(new Intent(FirebaseLoginActivity.this, MainActivity.class));

        }

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        mCallbackManager.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {

            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
                Toast.makeText(this, "Signed in", Toast.LENGTH_LONG).show();
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.e(TAG, "Google sign in failed", e);

            }
        } else {

            Toast.makeText(this, "Not Signed in", Toast.LENGTH_LONG).show();

        }


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.bt_sign_in:
                signIn();
                break;
            case R.id.google_bt_sign_in:
                googleSignIn();
                break;
            case R.id.facebook_bt_sign_in:
                facebookSignIn();
                break;

        }
    }

    private void signIn() {

        String email = mEmailEditText.getText().toString();
        String password = mEmailEditText.getText().toString();

        if (!email.contains("@") || email.length() < 4) {
            mEmailEditText.setError("Invalid Email");
            // TODO: Check if the email is valid.
            return;
        }

        if (password.length() < 8) {
            mPasswordEditText.setError("Password must be at least 8 characters long");
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            controller.setCurrentUser(user);
                            startActivity(new Intent(FirebaseLoginActivity.this, MainActivity.class));

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(FirebaseLoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            controller.setCurrentUser(null);
                        }

                        // ...
                    }
                });
    }

    private void googleSignIn() {


        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);


    }

    private void facebookSignIn() {

        mCallbackManager = CallbackManager.Factory.create();

        mFacebookLogInButton.setReadPermissions("email", "public_profile");
        mFacebookLogInButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "facebook:onSuccess:" + loginResult);
                firebaseAuthWithFacebook(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "facebook:onCancel");
                // ...
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "facebook:onError", error);
                // ...
            }
        });

    }

    private void firebaseAuthWithFacebook(AccessToken accessToken) {

        AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            controller.setCurrentUser(user);
                            startActivity(new Intent(FirebaseLoginActivity.this, MainActivity.class));

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(FirebaseLoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            controller.setCurrentUser(null);
                        }
                        // ...
                    }
                });
    }


    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());
        // [START_EXCLUDE silent]
        showProgressDialog();
        // [END_EXCLUDE]

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            controller.setCurrentUser(user);
                            startActivity(new Intent(FirebaseLoginActivity.this, MainActivity.class));

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(FirebaseLoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            controller.setCurrentUser(null);
                        }

                        // [START_EXCLUDE]
                        hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
    }

    private void showProgressDialog() {

        mProgressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressDialog() {

        mProgressBar.setVisibility(View.INVISIBLE);
    }


}
