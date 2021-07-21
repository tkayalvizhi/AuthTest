package com.example.authtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;

import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Context context = getApplicationContext();


        Button signUp = findViewById(R.id.sign_up);
        Button signIn = findViewById(R.id.sign_in);
        Button verify = findViewById(R.id.confirm);

        TextInputEditText emailIDEditText = findViewById(R.id.email_id_edit_text);
        TextInputEditText passwordEditText = findViewById(R.id.password_edit_text);
        TextInputEditText confirmationEditText = findViewById(R.id.confirmation_code_edit_text);


        signUp.setOnClickListener(v -> {

            final String email_id = Objects.requireNonNull(emailIDEditText.getText()).toString();
            final String password = Objects.requireNonNull(passwordEditText.getText()).toString();

            AuthSignUpOptions options = AuthSignUpOptions.builder()
                    .userAttribute(AuthUserAttributeKey.email(), email_id)
                    .build();

            Amplify.Auth.signUp(email_id, password, options,
                    result -> {
                        Log.i("AuthQuickStart",
                                "Result: " + result.toString());
                        Thread thread = new Thread() {
                            public void run() {
                                runOnUiThread(() -> {
                                    Toast.makeText(context,
                                            "Confirmation Code sent to your email address",
                                            Toast.LENGTH_LONG).show();
                                    verify.setVisibility(View.VISIBLE);
                                    findViewById(R.id.confirmationCode).setVisibility(View.VISIBLE);
                                });
                            }
                        };
                        thread.start();
                    },
                    error -> {
                        Log.e("AuthQuickStart", "Sign up failed", error);
                        Thread thread = new Thread() {
                            public void run() {
                                runOnUiThread(() -> Toast.makeText(context,
                                        "Sign Up failed",
                                        Toast.LENGTH_LONG).show());
                            }
                        };
                        thread.start();
                        /* Toast.makeText(context, "Sign up failed", Toast.LENGTH_LONG).show(); */
                    }
            );

        });

        verify.setOnClickListener(v -> {

            final String email_id = Objects.requireNonNull(emailIDEditText.getText()).toString();
            final String confirmation_code = Objects.requireNonNull(confirmationEditText.getText()).toString();


            Amplify.Auth.confirmSignUp(email_id, confirmation_code,
                    result -> {
                        Log.i("AuthQuickstart",
                                result.isSignUpComplete() ?
                                        "Confirm signUp succeeded" : "Confirm sign up not complete");
                        Thread thread = new Thread() {
                            public void run() {
                                runOnUiThread(() -> Toast.makeText(
                                        context,
                                        result.isSignUpComplete() ?
                                                "Confirm signUp succeeded" : "Confirm sign up not complete",
                                        Toast.LENGTH_LONG).show());
                            }
                        };
                        thread.start();
                    },
                    error -> Log.e("AuthQuickstart", error.toString()));
        });

        signIn.setOnClickListener(v -> {

            final String email_id = Objects.requireNonNull(emailIDEditText.getText()).toString();
            final String password = Objects.requireNonNull(passwordEditText.getText()).toString();

            Amplify.Auth.signIn(
                    email_id,
                    password,
                    result -> {
                        Log.i("AuthQuickstart",
                                result.isSignInComplete() ?
                                        "Sign in succeeded" : "Sign in not complete");
                        Thread thread = new Thread() {
                            public void run() {
                                runOnUiThread(() -> Toast.makeText(context,
                                        result.isSignInComplete() ?
                                                "Sign in succeeded" : "Sign in not complete",
                                        Toast.LENGTH_LONG).show());
                            }
                        };
                        thread.start();
                    },
                    error -> {
                        Log.e("AuthQuickstart", error.toString());
                        Thread thread = new Thread() {
                            public void run() {
                                runOnUiThread(() -> Toast.makeText(context,
                                        error.toString(),
                                        Toast.LENGTH_LONG).show());
                            }
                        };
                        thread.start();
                    });
        });
    }

    private boolean handleKeyEvent(View view, int keyCode){
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            // Hide the keyboard
            InputMethodManager inputMethodManager =
                    (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            return true;
        }
        return false;
    }
}