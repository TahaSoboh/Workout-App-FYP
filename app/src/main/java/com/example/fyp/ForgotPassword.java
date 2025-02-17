package com.example.fyp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

     EditText emailEditText;
     Button resetPasswordButton;

     ProgressBar progressBar;

     FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        emailEditText= (EditText) findViewById(R.id.email);
        resetPasswordButton = (Button) findViewById(R.id.forgotPassbtn);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        auth = FirebaseAuth.getInstance();

        resetPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                resetPassword();
            }
        });
    }
        private void resetPassword() {
            String email = emailEditText.getText().toString();

            if (email.isEmpty()){
                emailEditText.setError("Email is required!!");
                emailEditText.requestFocus();
                return;
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                emailEditText.setError("Provide a registered email!!");
                emailEditText.requestFocus();
                return;
            }

            progressBar.setVisibility(View.VISIBLE);
            auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(ForgotPassword.this, "Check your email to reset password.", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(ForgotPassword.this, "Try again. Something went wrong. ", Toast.LENGTH_LONG).show();
                    }
                }
            });
    }
}