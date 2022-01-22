package com.example.cinemabookingapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemabookingapp.R;

import java.util.List;

public class TicketsAdapter extends RecyclerView.Adapter<TicketsAdapter.TicketViewHolder> {

    private List<Integer> currentTicket;
    //Constructor
    public TicketsAdapter(List<Integer> currentTicket){
        this.currentTicket = currentTicket;
    }

    @NonNull
    @Override
    public TicketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ticket_layout,parent,false);
        return new TicketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketViewHolder holder, int position) {
        holder.imageView.setImageResource(currentTicket.get(position));
    }

    @Override
    public int getItemCount() {
        return currentTicket.size();
    }

    public class TicketViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        public TicketViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.tickets);
        }
    }
}
