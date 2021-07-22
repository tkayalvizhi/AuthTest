package com.example.authtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.amplifyframework.auth.options.AuthSignOutOptions;
import com.amplifyframework.core.Amplify;

public class ProfileUpdateActivity extends AppCompatActivity {

    public void toastMsg(String msg) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                runOnUiThread(() -> Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show());
            }
        };
        thread.start();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_update);

        Button signOut = findViewById(R.id.sign_out);
        signOut.setOnClickListener(v -> {
            Amplify.Auth.signOut(
                    AuthSignOutOptions.builder().globalSignOut(true).build(),
                    () -> {
                        Log.i("AuthQuickstart", "Signed out successfully");
                        toastMsg("Signed out successfully");
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    },
                    error -> {
                        Log.e("AuthQuickstart", error.toString());
                        toastMsg(error.getMessage());
                    }
            );
        });
    }
}