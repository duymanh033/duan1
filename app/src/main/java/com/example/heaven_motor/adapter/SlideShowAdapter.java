package com.example.heaven_motor.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.heaven_motor.R;
import com.example.heaven_motor.model.Slideshow;

import java.util.List;

public class SlideShowAdapter extends RecyclerView.Adapter<SlideShowAdapter.SlideShowViewHolder>{


    private final List<Slideshow> mListPhoto;

    public SlideShowAdapter(List<Slideshow> mListPhoto) {
        this.mListPhoto = mListPhoto;
    }

    @NonNull
    @Override
    public SlideShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo,parent,false);

        return new SlideShowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SlideShowViewHolder holder, int position) {
        Slideshow photo = mListPhoto.get(position);
        if (photo == null){
            return;
        }
        holder.imgPhoto.setImageResource(photo.getResourceID());
    }

    @Override
    public int getItemCount() {
        if (mListPhoto != null){
            return mListPhoto.size();
        }
        return 0;
    }

    public static class SlideShowViewHolder extends RecyclerView.ViewHolder{

        private final ImageView imgPhoto;

        public SlideShowViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_photo);
        }
    }
}
