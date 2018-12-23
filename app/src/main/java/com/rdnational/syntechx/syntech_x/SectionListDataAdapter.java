package com.rdnational.syntechx.syntech_x;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class SectionListDataAdapter extends RecyclerView.Adapter<SectionListDataAdapter.SingleItemRowHolder> {

    private ArrayList<HomeSingleItemsModel> itemsModels;
    private Context mContext;

    public SectionListDataAdapter(ArrayList<HomeSingleItemsModel> itemsModels, Context mContext) {
        this.itemsModels = itemsModels;
        this.mContext = mContext;
    }

    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_home_layout, null);
        SingleItemRowHolder singleItemRowHolder = new SingleItemRowHolder(v);
        return singleItemRowHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SectionListDataAdapter.SingleItemRowHolder holder, int position) {
        HomeSingleItemsModel itemsModel = itemsModels.get(position);
        holder.imgTitle.setText(itemsModel.getEvent_name());
        Glide.with(mContext).load(itemsModel.getEvent_logo()).into(holder.itemImage);
    }

    @Override
    public int getItemCount() {
        return (null != itemsModels ? itemsModels.size() : 0);
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder{

        protected ImageView itemImage;
        protected TextView imgTitle;

        public SingleItemRowHolder(View itemView) {
            super(itemView);
            this.itemImage = itemView.findViewById(R.id.itemImage);
            this.imgTitle = itemView.findViewById(R.id.tvTitle);

            this.itemImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}
