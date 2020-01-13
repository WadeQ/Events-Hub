package com.wadektech.eventshub.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.wadektech.eventshub.R;
import com.wadektech.eventshub.models.MainEvents;
import java.util.List;

public class MainEventsAdapter extends RecyclerView.Adapter<MainEventsAdapter.MainEventsViewHolder> {
    public List<MainEvents> mainEventsList ;
    public Context context ;
    public OnSingleEventCardClicked eventCardClicked ;
    private static final String TAG = "MainEventsAdapter";

    public MainEventsAdapter(List<MainEvents> mainEventsList, Context context, OnSingleEventCardClicked eventCardClicked) {
        this.mainEventsList = mainEventsList;
        this.context = context;
        this.eventCardClicked = eventCardClicked ;
    }

    @NonNull
    @Override
    public MainEventsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.events_list , parent , false);
        return new MainEventsViewHolder(view , eventCardClicked);
    }

    @Override
    public void onBindViewHolder(@NonNull MainEventsViewHolder holder, int position) {
        MainEvents mainEvents = mainEventsList.get(position);
        holder.eventTitle.setText(mainEvents.getTitle());
        holder.shortDescription.setText(mainEvents.getShortDesc());
        holder.date.setText(String.valueOf(mainEvents.getDate()));
        holder.location.setText(mainEvents.getLocation());
        holder.entryFees.setText(String.valueOf(mainEvents.getEntryFees()));
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount : " + mainEventsList.size());
        return mainEventsList.size();
    }

    public class MainEventsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView eventTitle , shortDescription, date, location , entryFees ;
        OnSingleEventCardClicked cardClicked ;

        public MainEventsViewHolder(@NonNull View itemView, OnSingleEventCardClicked cardClicked) {
            super(itemView);
            eventTitle = itemView.findViewById(R.id.tv_event_title);
            shortDescription = itemView.findViewById(R.id.tv_title_desc);
            date = itemView.findViewById(R.id.tv_event_date);
            location = itemView.findViewById(R.id.tv_event_location);
            entryFees = itemView.findViewById(R.id.tv_event_fees);
            this.cardClicked = cardClicked ;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            cardClicked.singleEventCardClicked(getAdapterPosition());
        }
    }

    public interface OnSingleEventCardClicked{
        void singleEventCardClicked(int position);
    }
}
