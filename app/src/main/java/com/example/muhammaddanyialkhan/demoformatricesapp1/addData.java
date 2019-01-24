package com.example.muhammaddanyialkhan.demoformatricesapp1;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class addData extends AppCompatActivity {


    Button btnsubmit;
    EditText txtName, txtPrice, txtLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        btnsubmit=findViewById(R.id.btn_submit);

        txtName=findViewById(R.id.product_name_data);
        txtLink=findViewById(R.id.product_link_data);
        txtPrice=findViewById(R.id.product_price_data);

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name, price, link;
                name=txtName.getText().toString().trim();
                price=txtPrice.getText().toString().trim();
                link=txtLink.getText().toString().trim();


                if(name.isEmpty()){
                    Toast.makeText(addData.this, "Enter Product Name", Toast.LENGTH_LONG).show();
                    return;
                }

                if(price.isEmpty()){
                    Toast.makeText(addData.this, "Enter Product Price", Toast.LENGTH_LONG).show();
                    return;
                }
                if(link.isEmpty()){
                    Toast.makeText(addData.this, "Enter Product Link", Toast.LENGTH_LONG).show();
                    return;
                }

                String uniqueID = UUID.randomUUID().toString();

                ProductData _data = new ProductData(name, price, link);

                FirebaseDatabase.getInstance().getReference("Popular")
                        .child(name+uniqueID)
                        .setValue(_data).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(addData.this, "Successfully added", Toast.LENGTH_LONG).show();
                            txtName.setText("");
                            txtLink.setText("");
                            txtPrice.setText("");
                        }else{
                            Toast.makeText(addData.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });
    }
}
