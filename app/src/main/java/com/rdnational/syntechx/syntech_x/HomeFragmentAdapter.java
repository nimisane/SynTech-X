package com.rdnational.syntechx.syntech_x;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class HomeFragmentAdapter extends RecyclerView.Adapter<HomeFragmentAdapter.HomeFragmentViewHolder> {

    String color;
    private Context mContext;
    private ArrayList<HomeSingleItemsModel> HomeFragmentList;

    public class HomeFragmentViewHolder extends RecyclerView.ViewHolder{
        public ImageView home_imageview;
        public TextView home_eventName;

        public HomeFragmentViewHolder(View itemView){
            super(itemView);
            home_imageview = itemView.findViewById(R.id.itemImage);
            home_eventName = itemView.findViewById(R.id.tvTitle);
        }
    }

    public HomeFragmentAdapter(Context context,ArrayList<HomeSingleItemsModel> homeFragmentlist){
        mContext = context;
        HomeFragmentList = homeFragmentlist;
    }

    @NonNull
    @Override
    public HomeFragmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.fragment_home_layout,parent,false);
        return new HomeFragmentViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeFragmentViewHolder holder, int position) {
        HomeSingleItemsModel currentItem = HomeFragmentList.get(position);
        color = currentItem.getEvent_color();
        //holder.home_imageview.setImageResource(currentItem.getEvent_logo());
       // holder.home_imageview.setBackgroundColor(Color.parseColor(color));
        Glide.with(mContext).load(currentItem.getEvent_logo()).into(holder.home_imageview);
        holder.home_eventName.setText(currentItem.getEvent_name());
        if(position==0)
        { holder.home_imageview.setBackgroundColor(Color.parseColor(color));}
        else if(position==1){
            holder.home_imageview.setBackgroundColor(Color.parseColor(color));
        }
        else if(position==2){
            holder.home_imageview.setBackgroundColor(Color.parseColor(color));
        }
        else if(position==3){
            holder.home_imageview.setBackgroundColor(Color.parseColor(color));
        }
        else if(position==4){
            holder.home_imageview.setBackgroundColor(Color.parseColor(color));
        }
        else if(position==5){
            holder.home_imageview.setBackgroundColor(Color.parseColor(color));
        }
        else if(position==6){
            holder.home_imageview.setBackgroundColor(Color.parseColor(color));
        }
        else if(position==7){
            holder.home_imageview.setBackgroundColor(Color.parseColor(color));
        }
        else if(position==8){
            holder.home_imageview.setBackgroundColor(Color.parseColor(color));
        }
        else if(position==9){
            holder.home_imageview.setBackgroundColor(Color.parseColor(color));
        }
        else if(position==10){
            holder.home_imageview.setBackgroundColor(Color.parseColor(color));
        }
        else if(position==11){
            holder.home_imageview.setBackgroundColor(Color.parseColor(color));
        }
        else if(position==12){
            holder.home_imageview.setBackgroundColor(Color.parseColor(color));
        }
        else if(position==13){
            holder.home_imageview.setBackgroundColor(Color.parseColor(color));
        }
        else if(position==14){
            holder.home_imageview.setBackgroundColor(Color.parseColor(color));
        }
        else if(position==15){
            holder.home_imageview.setBackgroundColor(Color.parseColor(color));
        }
        else if(position==16){
            holder.home_imageview.setBackgroundColor(Color.parseColor(color));
        }
        else if(position==17){
            holder.home_imageview.setBackgroundColor(Color.parseColor(color));
        }
        else if(position==18){
            holder.home_imageview.setBackgroundColor(Color.parseColor(color));
        }
        else if(position==19){
            holder.home_imageview.setBackgroundColor(Color.parseColor(color));
        }
        else if(position==20){
            holder.home_imageview.setBackgroundColor(Color.parseColor(color));
        }
    }

    @Override
    public int getItemCount() {
        return HomeFragmentList.size();
    }
}
