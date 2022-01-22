package com.example.cinemabookingapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemabookingapp.R;

import java.util.List;

public class CurrentMoviesAdapter extends RecyclerView.Adapter<CurrentMoviesAdapter.CurrentMoviesViewHolder> {

    private List<Integer> currentMoviesList;
    private OnPosterListener mcurrentMovieListListener;
    //Constructor
    public CurrentMoviesAdapter(List<Integer> currentMoviesList, OnPosterListener onPosterListener){
        this.currentMoviesList = currentMoviesList;
        this.mcurrentMovieListListener = onPosterListener;
    }

    @NonNull
    @Override
    public CurrentMoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.currentmovie_layout,parent,false);
        return new CurrentMoviesViewHolder(view, mcurrentMovieListListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrentMoviesViewHolder holder, int position) {
        holder.imageView.setImageResource(currentMoviesList.get(position));
    }

    @Override
    public int getItemCount() {
        return currentMoviesList.size();
    }

    public class CurrentMoviesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView imageView;
        OnPosterListener onPosterListener;
        public CurrentMoviesViewHolder(@NonNull View itemView, OnPosterListener onPosterListener) {
            super(itemView);

            imageView = itemView.findViewById(R.id.movie_poster);
            this.onPosterListener = onPosterListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onPosterListener.onPosterClick(getAdapterPosition());

        }
    }

    public interface OnPosterListener{
        void onPosterClick(int position);
    }
}
