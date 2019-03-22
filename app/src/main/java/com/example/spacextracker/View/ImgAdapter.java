package com.example.spacextracker.View;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.spacextracker.R;
import com.squareup.picasso.Picasso;

public class ImgAdapter extends RecyclerView.Adapter<ImgAdapter.MyViewHolder> {

    private String[] tabImg;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ImageView imgView;
        public MyViewHolder(View v) {
            super(v);
            imgView = v.findViewById(R.id.imgView);
        }
    }

    public ImgAdapter(String[] tabImg){
        this.tabImg = tabImg;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_img, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Picasso
                .get()
                .load(tabImg[position])
                .placeholder(R.drawable.ic_launcher_foreground)
                .resize(0,512)
                .into(holder.imgView);
    }

    @Override
    public int getItemCount() {
        return tabImg.length;
    }
}
