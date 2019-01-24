package com.example.muhammaddanyialkhan.demoformatricesapp1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    TextInputLayout uEmailWrapper, uPasswordWrapper;
    EditText uEmail, uPassword;
    Button uLoginBtn;

    FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        uEmailWrapper=findViewById(R.id.UserEmailWrapper);
        uPasswordWrapper=findViewById(R.id.UserPasswordWrapper);

        uEmail=findViewById(R.id.UserEmail);
        uPassword=findViewById(R.id.UserPassword);

        uLoginBtn=findViewById(R.id.btnUserLogin);

        mAuth=FirebaseAuth.getInstance();

        uLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, password;

                email=uEmail.getText().toString().trim();
                password=uPassword.getText().toString().trim();

                if(email.isEmpty()){
                    uEmailWrapper.setError("Enter Email.");
                    uEmailWrapper.requestFocus();
                    return;
                }
                if(password.isEmpty()){
                    uPasswordWrapper.setError("Enter password.");
                    uPasswordWrapper.requestFocus();
                    return;
                }



                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //we can return to another activity
                            Intent intent = new Intent(LoginActivity.this, ShopActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        }else {
                            Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}
