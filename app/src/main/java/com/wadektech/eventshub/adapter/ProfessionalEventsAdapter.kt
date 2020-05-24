package com.wadektech.eventshub.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wadektech.eventshub.R
import com.wadektech.eventshub.models.ProfessionalEvents

class ProfessionalEventsAdapter(var singleProfEventClicked: OnSingleProfEventClicked) :
        ListAdapter<ProfessionalEvents, ProfessionalEventsAdapter.ViewHolder>(ProfessionalDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.prodessional_events_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val professionalEvents = getItem(position)
        if (professionalEvents != null){
            holder.bind(professionalEvents, singleProfEventClicked)
        }

    }

    inner class ViewHolder(itemView: View) :
            RecyclerView.ViewHolder(itemView){
        private var eventTitle: TextView = itemView.findViewById(R.id.tv_event_title)
        private var shortDescription: TextView = itemView.findViewById(R.id.tv_title_desc)
        var date: TextView = itemView.findViewById(R.id.tv_event_date)
        var location: TextView = itemView.findViewById(R.id.tv_event_location)
        var entryFees: TextView = itemView.findViewById(R.id.tv_event_fees)

        fun bind(professionalEvents: ProfessionalEvents, profEventClicked: OnSingleProfEventClicked){
            eventTitle.text = professionalEvents.title
            shortDescription.text = professionalEvents.shortDesc
            date.text = professionalEvents.date.toString()
            location.text = professionalEvents.location
            entryFees.text = professionalEvents.entryFees.toString()
            itemView.setOnClickListener {
                profEventClicked.singleProfessionalEventClicked(adapterPosition)
            }
        }
    }

    interface OnSingleProfEventClicked {
        fun singleProfessionalEventClicked(position: Int)
    }

    class ProfessionalDiffUtil :DiffUtil.ItemCallback<ProfessionalEvents>() {
        override fun areItemsTheSame(oldItem: ProfessionalEvents, newItem: ProfessionalEvents): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ProfessionalEvents, newItem: ProfessionalEvents): Boolean {
            return oldItem.id == newItem.id
        }
    }
}