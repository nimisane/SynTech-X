package com.rdnational.syntechx.syntech_x;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class CommitteeHeadAdapter extends RecyclerView.Adapter<CommitteeHeadAdapter.CommitteeHeadViewHolder> {

    private Context context;
    private ArrayList<CommitteeHeadItems> committeeHeadItems;

    public CommitteeHeadAdapter(Context context,ArrayList<CommitteeHeadItems> committeeHeadItems){
        this.context = context;
        this.committeeHeadItems = committeeHeadItems;
    }

    @NonNull
    @Override
    public CommitteeHeadViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.committee_heads,parent,false);
        return new CommitteeHeadViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CommitteeHeadViewHolder holder, int position) {
        CommitteeHeadItems currentItem = committeeHeadItems.get(position);
        String committee_headName = currentItem.getCommittee_head_name();
        final String committee_headimg = currentItem.getCommittee_head_image();

        holder.commHeadName.setText("\t\t"+committee_headName);
        Glide.with(context).load(committee_headimg).apply(new RequestOptions().placeholder(R.drawable.ic_user).error(R.drawable.ic_user)).into(holder.commImg);

        holder.commImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertadd = new AlertDialog.Builder(context);
                LayoutInflater factory = LayoutInflater.from(context);
                final View view = factory.inflate(R.layout.view_profile, null);
                ImageView image= (ImageView) view.findViewById(R.id.view_profile);
                Glide.with(context).load(committee_headimg).apply(new RequestOptions().placeholder(R.drawable.ic_user).error(R.drawable.ic_user)).into(image);
                alertadd.setView(view);
                alertadd.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return committeeHeadItems.size();
    }

    public class CommitteeHeadViewHolder extends RecyclerView.ViewHolder{

        public ImageView commImg;
        public TextView commHeadName;
        public CommitteeHeadViewHolder(View itemView) {
            super(itemView);
            commImg = itemView.findViewById(R.id.developer_img);
            commHeadName = itemView.findViewById(R.id.developer_name);
        }
    }
}
