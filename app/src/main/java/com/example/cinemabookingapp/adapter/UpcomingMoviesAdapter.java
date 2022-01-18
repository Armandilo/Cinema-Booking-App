package com.example.cinemabookingapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemabookingapp.R;

import java.util.List;

public class UpcomingMoviesAdapter extends RecyclerView.Adapter<UpcomingMoviesAdapter.UpcomingMoviesViewHolder>{

    private List<Integer> imageList;

    public UpcomingMoviesAdapter(List<Integer> imageList){
        this.imageList = imageList;
    }

    @NonNull
    @Override
    public UpcomingMoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.upcomingmovie_layout,parent,false);
        return new UpcomingMoviesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UpcomingMoviesViewHolder holder, int position) {
        holder.imageView.setImageResource(imageList.get(position));
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public class UpcomingMoviesViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;

        public UpcomingMoviesViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.upcomingmovieposter);
        }
    }

}
