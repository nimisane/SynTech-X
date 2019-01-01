package com.rdnational.syntechx.syntech_x;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class SponsorAdapter extends RecyclerView.Adapter<SponsorAdapter.SponsorViewHolder> {

    private Context context;
    private ArrayList<SponsorItems> sponsorItems;

    public SponsorAdapter(Context context, ArrayList<SponsorItems> sponsorItems) {
        this.context = context;
        this.sponsorItems = sponsorItems;
    }

    @NonNull
    @Override
    public SponsorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.sponsors_layout,parent,false);
        return new SponsorViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SponsorViewHolder holder, int position) {
        SponsorItems currentItem = sponsorItems.get(position);
        String imgUrl = currentItem.getsImg();
        Glide.with(context).load(imgUrl).into(holder.sponsorImageView);
    }

    @Override
    public int getItemCount() {
        return sponsorItems.size();
    }

    public class SponsorViewHolder extends RecyclerView.ViewHolder{
        public ImageView sponsorImageView;

        public SponsorViewHolder(View itemView) {
            super(itemView);
            sponsorImageView = itemView.findViewById(R.id.sponsor_image);
        }
    }
}
