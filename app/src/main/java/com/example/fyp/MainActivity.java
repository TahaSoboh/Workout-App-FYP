package com.example.fyp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    EditText inputEmail, inputPassword, inputRePassword;
    String emailPattern = "[a-zA-Z0-9._-]+=@[a-z]+\\.+[a-z]+";
    Button signup, signin;
    ProgressDialog progressDialog;

    FirebaseAuth mAuth;
    FirebaseUser mUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        inputEmail = findViewById(R.id.email);
        inputPassword= findViewById(R.id.password);
        inputRePassword=findViewById(R.id.repassword);
        signup= (Button) findViewById(R.id.signupbtn);
        signin= (Button) findViewById(R.id.signinbtn);
        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PerformAuth();
            }
        });


        signin.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        }

    private void PerformAuth() {
        String email= inputEmail.getText().toString();
        String password = inputPassword.getText().toString();
        String repassword = inputRePassword.getText().toString();

        if (email.matches(emailPattern))
        {
            inputEmail.setError("Enter your email");
        } else if (password.isEmpty() || password.length()<6)
        {
             inputPassword.setError("enter proper password");
        }else if (!password.equals(repassword))
        {
            inputRePassword.setError("passwords don't match");
        }else
        {
            progressDialog.setMessage("Registration in progress");
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful())
                    {
                        progressDialog.dismiss();
                        sendUserToNextActivity();
                        Toast.makeText(MainActivity.this, "Register Complete", Toast.LENGTH_SHORT).show();
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(MainActivity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void sendUserToNextActivity() {
        Intent intent=new Intent(MainActivity.this, MainActivity2.class);
        intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


}