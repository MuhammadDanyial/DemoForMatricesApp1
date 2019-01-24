package com.example.muhammaddanyialkhan.demoformatricesapp1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

public class PopularAdaptor extends RecyclerView.Adapter<PopularAdaptor.ImageViewHolder> {

    private Context mContext;
    private List<Popular> mPopular;

    public PopularAdaptor(Context mContext, List<Popular> mPopular) {
        this.mContext = mContext;
        this.mPopular = mPopular;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.popular_items, viewGroup, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder imageViewHolder, int i) {

        Popular CurPopular = mPopular.get(i);
        imageViewHolder.prod_name.setText(CurPopular.getName());
        imageViewHolder.prod_price.setText(CurPopular.getPrice());
        Picasso.with(mContext)
                .load(CurPopular.getProduct_image())
                .placeholder(R.drawable.img_placeholder_24dp)
                .fit()
                .centerCrop()
                .into((Target) imageViewHolder.prod_img);
    }

    @Override
    public int getItemCount() {
        return mPopular.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {

        public TextView prod_name, prod_price;
        public ImageView prod_img;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            prod_name=itemView.findViewById(R.id.prod_name);
            prod_price=itemView.findViewById(R.id.product_Price);
            prod_img=itemView.findViewById(R.id.product_image);
        }
    }
}
