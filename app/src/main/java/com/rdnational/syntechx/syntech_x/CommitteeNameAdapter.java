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

public class CommitteeNameAdapter extends RecyclerView.Adapter<CommitteeNameAdapter.CommitteeNameViewHolder>  {

    private Context context;
    private ArrayList<CommitteeNameItems> mCommitteeName;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public CommitteeNameAdapter(Context context,ArrayList<CommitteeNameItems> mCommitteeName){
        this.context = context;
        this.mCommitteeName = mCommitteeName;
    }

    @NonNull
    @Override
    public CommitteeNameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.committee_name,parent,false);
        return new CommitteeNameViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CommitteeNameViewHolder holder, int position) {
        CommitteeNameItems currentItem = mCommitteeName.get(position);
        String committee_name = currentItem.getCommitteeName();
        String committee_img = currentItem.getCommitteeImage();

        holder.commName.setText("\t\t\t"+committee_name);
        Glide.with(context).load(committee_img).into(holder.commImage);
    }

    @Override
    public int getItemCount() {
        return mCommitteeName.size();
    }

    public class CommitteeNameViewHolder extends RecyclerView.ViewHolder {
        public ImageView commImage;
        public TextView commName;


        public CommitteeNameViewHolder(View itemView) {
            super(itemView);
            commImage = itemView.findViewById(R.id.developer_img);
            commName = itemView.findViewById(R.id.developer_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
