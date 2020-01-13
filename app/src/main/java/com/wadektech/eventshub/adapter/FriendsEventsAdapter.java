package com.wadektech.eventshub.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.wadektech.eventshub.R;
import com.wadektech.eventshub.models.FriendsEvents;

import java.util.List;

public class FriendsEventsAdapter extends RecyclerView.Adapter<FriendsEventsAdapter.ViewHolder> {
    public List<FriendsEvents> friendsEventsList ;
    public Context context;
    public OnSingleFriendsEventItemClicked singleFriendsEventItemClicked ;

    public FriendsEventsAdapter(List<FriendsEvents> friendsEventsList, Context context, OnSingleFriendsEventItemClicked singleFriendsEventItemClicked) {
        this.friendsEventsList = friendsEventsList;
        this.context = context;
        this.singleFriendsEventItemClicked = singleFriendsEventItemClicked;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.events_list, parent, false);
        return new ViewHolder(view, singleFriendsEventItemClicked);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FriendsEvents friendsEvents = friendsEventsList.get(position);

        holder.date.setText(String.valueOf(friendsEvents.getDate()));
        holder.entryFees.setText(String.valueOf(friendsEvents.getEntryFees()));
        holder.eventTitle.setText(friendsEvents.getTitle());
        holder.location.setText(friendsEvents.getLocation());
        holder.shortDescription.setText(friendsEvents.getShortDesc());

    }

    @Override
    public int getItemCount() {
        return friendsEventsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public OnSingleFriendsEventItemClicked friendsEventItemClicked;
        public TextView eventTitle , shortDescription, date, location , entryFees ;

        public ViewHolder(@NonNull View itemView, OnSingleFriendsEventItemClicked friendsEventItemClicked) {
            super(itemView);
            eventTitle = itemView.findViewById(R.id.tv_event_title);
            shortDescription = itemView.findViewById(R.id.tv_title_desc);
            date = itemView.findViewById(R.id.tv_event_date);
            location = itemView.findViewById(R.id.tv_event_location);
            entryFees = itemView.findViewById(R.id.tv_event_fees);
            this.friendsEventItemClicked = friendsEventItemClicked ;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            friendsEventItemClicked.singleFriendsEventsItemClicked(getAdapterPosition());
        }
    }

    public interface OnSingleFriendsEventItemClicked{
        void singleFriendsEventsItemClicked(int position);
    }
}
