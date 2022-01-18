package com.example.cinemabookingapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemabookingapp.R;

import java.util.List;

public class PromosAdapter extends RecyclerView.Adapter<PromosAdapter.PromosViewHolder> {

    private List<Integer> promosList;
    public PromosAdapter(List<Integer> promosList){
        this.promosList = promosList;
    }

    @NonNull
    @Override
    public PromosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.promo_layout,parent,false);
        return new PromosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PromosViewHolder holder, int position) {
        holder.imageView.setImageResource(promosList.get(position));
    }

    @Override
    public int getItemCount() {
        return promosList.size();
    }

    public class PromosViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        public PromosViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.promo_1);
        }
    }
}
