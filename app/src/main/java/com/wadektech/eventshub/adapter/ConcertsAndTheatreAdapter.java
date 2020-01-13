package com.wadektech.eventshub.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.wadektech.eventshub.R;
import com.wadektech.eventshub.models.Concerts;

import java.util.List;

public class ConcertsAndTheatreAdapter extends RecyclerView.Adapter<ConcertsAndTheatreAdapter.ViewHolder> {
    public List<Concerts> concertsList;
    public Context context ;
    public OnSingleConcertItemClicked singleConcertItemClicked ;

    public ConcertsAndTheatreAdapter(List<Concerts> concertsList, Context context, OnSingleConcertItemClicked singleConcertItemClicked) {
        this.concertsList = concertsList;
        this.context = context;
        this.singleConcertItemClicked = singleConcertItemClicked;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.events_list, parent, false);
        return new ViewHolder(view , singleConcertItemClicked);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Concerts concerts = concertsList.get(position);

        holder.date.setText(String.valueOf(concerts.getDate()));
        holder.entryFees.setText(String.valueOf(concerts.getEntryFees()));
        holder.eventTitle.setText(concerts.getTitle());
        holder.location.setText(concerts.getLocation());
        holder.shortDescription.setText(concerts.getShortDesc());

    }

    @Override
    public int getItemCount() {
        return concertsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView eventTitle , shortDescription, date, location , entryFees ;
        OnSingleConcertItemClicked concertItemClicked ;
        public ViewHolder(@NonNull View itemView , OnSingleConcertItemClicked concertItemClicked) {
            super(itemView);
            eventTitle = itemView.findViewById(R.id.tv_event_title);
            shortDescription = itemView.findViewById(R.id.tv_title_desc);
            date = itemView.findViewById(R.id.tv_event_date);
            location = itemView.findViewById(R.id.tv_event_location);
            entryFees = itemView.findViewById(R.id.tv_event_fees);
            this.concertItemClicked = concertItemClicked;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            concertItemClicked.onSingleConcertItemClicked(getAdapterPosition());
        }
    }

    public interface OnSingleConcertItemClicked{
        void onSingleConcertItemClicked(int position) ;
    }
}
