package com.example.cinemabookingapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemabookingapp.Item;
import com.example.cinemabookingapp.R;


import java.util.ArrayList;
import java.util.List;

public class TicketsAdapter extends RecyclerView.Adapter<TicketsAdapter.TicketViewHolder> {


    private ArrayList<Item> currentTicket;
    Context context;
    //Constructor
    public TicketsAdapter(Context context,ArrayList<Item> currentTicket){
        this.context = context;
        this.currentTicket = currentTicket;
    }

    @NonNull
    @Override
    public TicketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ticketcard_layout,parent,false);
        return new TicketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketViewHolder holder, int position) {
        Item currentItem = currentTicket.get(position);

        holder.mText1.setText(currentItem.getText1());
        holder.mText2.setText(currentItem.getText2());
        holder.mText3.setText(currentItem.getText3());
        holder.mText4.setText(currentItem.getText4());
    }

    @Override
    public int getItemCount() {
        return currentTicket.size();
    }

    public class TicketViewHolder extends RecyclerView.ViewHolder{
        public TextView mText1;
        public TextView mText2;
        public TextView mText3;
        public TextView mText4;
        private ImageView imageView;
        public TicketViewHolder(@NonNull View itemView) {
            super(itemView);

            mText1 = itemView.findViewById(R.id.movieNameLayout);
            mText2 = itemView.findViewById(R.id.movieDateLayout);
            mText3 = itemView.findViewById(R.id.movieLocationLayout);
            mText4 = itemView.findViewById(R.id.movieSeat);

            imageView = itemView.findViewById(R.id.tickets);
        }
    }
}
