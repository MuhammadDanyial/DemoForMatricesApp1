package com.example.muhammaddanyialkhan.demoformatricesapp1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    EditText uFirstName, uLastName, uEmailAddress, uPassword, uConfPassward, uContact;
    Button uResistration;

    TextInputLayout ufNameWrapper, uLnameWrapper, upasswordWrapper, uconfPasswordWrapper,
            uEmailWrapper, uContactWrapper;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth=FirebaseAuth.getInstance();
        uFirstName =findViewById(R.id.f_name);
        uLastName=findViewById(R.id.l_name);
        uEmailAddress=findViewById(R.id.email);
        uPassword=findViewById(R.id.password);
        uConfPassward=findViewById(R.id.confirm_password);
        uContact=findViewById(R.id.contact_no);
        ufNameWrapper=findViewById(R.id.f_name_wrapper);
        uLnameWrapper=findViewById(R.id.l_name_wrapper);
        upasswordWrapper=findViewById(R.id.password_wrapper);
        uconfPasswordWrapper=findViewById(R.id.confirm_password_wrapper);
        uEmailWrapper=findViewById(R.id.emailwrapper);
        uContactWrapper=findViewById(R.id.contact_wrapper);

        uResistration=findViewById(R.id.btn_register);

        uResistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mAuth.getCurrentUser()!=null){
                    //user id logged in and can redirect to another activity
                    Toast.makeText(RegisterActivity.this, "hello", Toast.LENGTH_LONG).show();
                }else {
                    final String fname = uFirstName.getText().toString().trim();
                    final String lname = uLastName.getText().toString().trim();
                    final String email = uEmailAddress.getText().toString().trim();
                    String password = uPassword.getText().toString().trim();
                    String confpassword = uConfPassward.getText().toString().trim();
                    final String contact = uContact.getText().toString().trim();

                    if (fname.isEmpty()) {
                        ufNameWrapper.setError("Enter FirstName");
                        ufNameWrapper.requestFocus();
                        return;
                    }
                    if (lname.isEmpty()) {
                        uLnameWrapper.setError("Enter lastName");
                        uLnameWrapper.requestFocus();
                        return;
                    }
                    if (email.isEmpty()) {
                        uEmailWrapper.setError("Enter Email Address");
                        uEmailWrapper.requestFocus();
                        return;
                    }
                    if (password.isEmpty()) {
                        upasswordWrapper.setError("Enter Password");
                        uconfPasswordWrapper.requestFocus();
                        return;
                    }
                    if (confpassword.isEmpty()) {
                        uconfPasswordWrapper.setError("Enter Confirm Password");
                        uconfPasswordWrapper.requestFocus();
                        return;
                    }
                    if (!password.equals(confpassword)) {
                        uconfPasswordWrapper.setError("Password didn't match");
                        uconfPasswordWrapper.requestFocus();
                        return;
                    }
                    if (contact.isEmpty()) {
                        uContactWrapper.setError("Enter Contact");
                        uContactWrapper.requestFocus();
                        return;
                    }


                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull final Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                //we need to add rest of ProductData in firebase database
                                User user = new User(fname, lname, email, contact);
                                FirebaseDatabase.getInstance().getReference("Users")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()) {
                                            Toast.makeText(RegisterActivity.this, "User Created Successfully.", Toast.LENGTH_LONG).show();
                                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                            startActivity(intent);
                                        }else{
                                            Toast.makeText(RegisterActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                            }else{
                                Toast.makeText(RegisterActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                }
            }
        });

    }
}
