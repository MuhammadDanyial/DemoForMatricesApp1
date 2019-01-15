package com.example.muhammaddanyialkhan.demoformatricesapp1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class ShopActivity extends AppCompatActivity {

    ViewFlipper imgBanner;
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
