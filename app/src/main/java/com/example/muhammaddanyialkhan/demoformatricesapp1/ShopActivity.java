package com.example.muhammaddanyialkhan.demoformatricesapp1;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ShopActivity extends AppCompatActivity {

    ViewFlipper imgBanner;

    private RecyclerView recyclerView;
    private PopularAdaptor popularAdaptor;
    private DatabaseReference mDatabase;
    private List<Popular> mPopular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        imgBanner=findViewById(R.id.bannerViewFlapper);

        int sliders[]={
                R.drawable.banner1,R.drawable.banner2,R.drawable.banner4
        };

        for(int slide: sliders){
            bannerFlapper(slide);
        }

        showPopularProducts();
    }

    private void showPopularProducts() {
        recyclerView=findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mPopular=new ArrayList<>();
        mDatabase= FirebaseDatabase.getInstance().getReference("Popular");

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnapShot: dataSnapshot.getChildren())
                {
                    Popular popular=postSnapShot.getValue(Popular.class);
                    mPopular.add(popular);
                }
                popularAdaptor=new PopularAdaptor(ShopActivity.this, mPopular);
                recyclerView.setAdapter(popularAdaptor);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ShopActivity.this,  databaseError.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    public void bannerFlapper(int image){
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(image);
        imgBanner.addView(imageView);
        imgBanner.setFlipInterval(6000);
        imgBanner.setAutoStart(true);
        imgBanner.setInAnimation(this, android.R.anim.fade_in);
        imgBanner.setOutAnimation(this, android.R.anim.fade_out);
    }
}
