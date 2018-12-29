package com.rdnational.syntechx.syntech_x;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class GalleryFragmentAdapter extends RecyclerView.Adapter<GalleryFragmentAdapter.GalleryFragmentViewHolder> {

    private Context context;
    private ArrayList<GalleryItems> galleryItems;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public GalleryFragmentAdapter(Context context, ArrayList<GalleryItems> galleryItems) {
        this.context = context;
        this.galleryItems = galleryItems;
    }

    @NonNull
    @Override
    public GalleryFragmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.gallery_layout,parent,false);
        return new GalleryFragmentViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryFragmentViewHolder holder, int position) {
        GalleryItems currentItem = galleryItems.get(position);
        Glide.with(context).load(currentItem.getPhoto()).apply(new RequestOptions().placeholder(R.drawable.ic_image).error(R.drawable.ic_image)).into(holder.galley_img);

    }

    @Override
    public int getItemCount() {
        return (null != galleryItems ? galleryItems.size() : 0);
    }

    public class GalleryFragmentViewHolder extends RecyclerView.ViewHolder {

        public ImageView galley_img;
        public GalleryFragmentViewHolder(View itemView) {
            super(itemView);
            galley_img = itemView.findViewById(R.id.display);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener!=null){
                        int position = getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }

    }
}
