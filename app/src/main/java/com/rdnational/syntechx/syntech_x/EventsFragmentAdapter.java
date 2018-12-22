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

public class EventsFragmentAdapter extends RecyclerView.Adapter<EventsFragmentAdapter.EventsViewHolder> {
    private ArrayList<EventsFragmentItems> EventsFragmentList;
    private OnItemClickListner mListner;
    private Context context;

    public interface OnItemClickListner {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListner listener){
        mListner = listener;
    }

    public class EventsViewHolder extends RecyclerView.ViewHolder{
        public ImageView events_imageview;
        public TextView events_eventagline;

        public EventsViewHolder(View itemView) {
            super(itemView);
            events_imageview = itemView.findViewById(R.id.events_logo);
            events_eventagline = itemView.findViewById(R.id.events_tagline);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListner!=null){
                        int position = getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            mListner.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public EventsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.fragment_events_layout,parent,false);
        //EventsViewHolder eventsViewHolder = new EventsViewHolder(v,mListner);
        return new EventsViewHolder(v);
    }

    public EventsFragmentAdapter(Context mcontext,ArrayList<EventsFragmentItems> eventsFragmentlist){
        context = mcontext;
        EventsFragmentList = eventsFragmentlist;
    }
    @Override
    public void onBindViewHolder(EventsViewHolder holder, int position) {
        EventsFragmentItems currentItem = EventsFragmentList.get(position);
        Glide.with(context).load(currentItem.getEvent_logo()).into(holder.events_imageview);
        holder.events_eventagline.setText(currentItem.getEvent_name());
    }

    @Override
    public int getItemCount() {
        return EventsFragmentList.size();
    }
}
