package com.wadektech.eventshub.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.wadektech.eventshub.R;
import com.wadektech.eventshub.models.SocialEvents;
import java.util.List;

public class SocialEventsAdapter extends RecyclerView.Adapter<SocialEventsAdapter.ViewHolder> {
    public List<SocialEvents> socialEventsList ;
    public Context context ;
    public OnSingleSocialEventClicked singleSocialEventClicked ;

    public SocialEventsAdapter(List<SocialEvents> socialEventsList, Context context, OnSingleSocialEventClicked singleSocialEventClicked) {
        this.socialEventsList = socialEventsList;
        this.context = context;
        this.singleSocialEventClicked = singleSocialEventClicked;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.events_list, parent, false);
        return new ViewHolder(view , singleSocialEventClicked);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SocialEvents socialEvents = socialEventsList.get(position);

        holder.date.setText(String.valueOf(socialEvents.getDate()));
        holder.entryFees.setText(String.valueOf(socialEvents.getTitle()));
        holder.eventTitle.setText(socialEvents.getTitle());
        holder.location.setText(socialEvents.getLocation());
        holder.shortDescription.setText(socialEvents.getShortDesc());
    }

    @Override
    public int getItemCount() {
        return socialEventsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView eventTitle , shortDescription, date, location , entryFees ;
        OnSingleSocialEventClicked socialEventClicked ;
        public ViewHolder(@NonNull View itemView , OnSingleSocialEventClicked socialEventClicked) {
            super(itemView);
            eventTitle = itemView.findViewById(R.id.tv_event_title);
            shortDescription = itemView.findViewById(R.id.tv_title_desc);
            date = itemView.findViewById(R.id.tv_event_date);
            location = itemView.findViewById(R.id.tv_event_location);
            entryFees = itemView.findViewById(R.id.tv_event_fees);
            this.socialEventClicked = socialEventClicked ;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            socialEventClicked.onSingleSocialEventItemClicked(getAdapterPosition());
        }
    }

    public interface OnSingleSocialEventClicked{
        void onSingleSocialEventItemClicked(int position) ;
    }
}
