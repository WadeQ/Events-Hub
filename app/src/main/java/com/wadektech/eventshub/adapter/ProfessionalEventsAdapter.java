package com.wadektech.eventshub.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.wadektech.eventshub.R;
import com.wadektech.eventshub.models.ProfessionalEvents;
import java.util.List;

public class ProfessionalEventsAdapter extends RecyclerView.Adapter<ProfessionalEventsAdapter.ViewHolder> {
    public List<ProfessionalEvents> professionalEventsList ;
    public Context context;
    public OnSingleProfEventClicked singleProfEventClicked ;

    public ProfessionalEventsAdapter(List<ProfessionalEvents> professionalEventsList, Context context, OnSingleProfEventClicked singleProfEventClicked) {
        this.professionalEventsList = professionalEventsList;
        this.context = context;
        this.singleProfEventClicked = singleProfEventClicked ;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.prodessional_events_list, parent, false);
        return new ViewHolder(view , singleProfEventClicked);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProfessionalEvents professionalEvents = professionalEventsList.get(position);
        holder.date.setText(String.valueOf(professionalEvents.getDate()));
        holder.entryFees.setText(String.valueOf(professionalEvents.getEntryFees()));
        holder.eventTitle.setText(professionalEvents.getTitle());
        holder.location.setText(professionalEvents.getLocation());
        holder.shortDescription.setText(professionalEvents.getShortDesc());
    }

    @Override
    public int getItemCount() {
        return professionalEventsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView eventTitle , shortDescription, date, location , entryFees ;
        OnSingleProfEventClicked profEventClicked ;
        public ViewHolder(@NonNull View itemView, OnSingleProfEventClicked profEventClicked) {
            super(itemView);
            eventTitle = itemView.findViewById(R.id.tv_event_title);
            shortDescription = itemView.findViewById(R.id.tv_title_desc);
            date = itemView.findViewById(R.id.tv_event_date);
            location = itemView.findViewById(R.id.tv_event_location);
            entryFees = itemView.findViewById(R.id.tv_event_fees);
            this.profEventClicked = profEventClicked ;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            profEventClicked.singleProfessionalEventClicked(getAdapterPosition());
        }
    }

    public interface OnSingleProfEventClicked{
        void singleProfessionalEventClicked(int position);
    }
}
