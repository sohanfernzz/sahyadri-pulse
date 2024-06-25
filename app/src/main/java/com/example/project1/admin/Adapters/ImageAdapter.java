package com.example.project1.admin.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project1.R;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
    private final Context context;
    private List<Images> imageList;

    public ImageAdapter(Context context, List<Images> imageList) {
        this.context = context;
        this.imageList = imageList;
        Collections.reverse(this.imageList);
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recyclerview_item, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        Images imageData = imageList.get(position);
        holder.textViewCaption.setText(imageData.getCaption());
        holder.textViewDateTime.setText(imageData.getDateTime());
        Picasso.get().load(imageData.getImageUrl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textViewCaption;
        TextView textViewDateTime;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_image);
            textViewCaption = itemView.findViewById(R.id.item_caption);
            textViewDateTime = itemView.findViewById(R.id.item_date_time);
        }
    }
}
