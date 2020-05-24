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
import com.wadektech.eventshub.models.SocialEvents

class SocialEventsAdapter(var singleSocialEventClicked: OnSingleSocialEventClicked) :
        ListAdapter<SocialEvents, SocialEventsAdapter.ViewHolder>(SocialDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.events_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val socialEvents = getItem(position)
        if (socialEvents!=null){
            holder.bind(socialEvents, singleSocialEventClicked)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var eventTitle: TextView = itemView.findViewById(R.id.tv_event_title)
        var shortDescription: TextView = itemView.findViewById(R.id.tv_title_desc)
        var date: TextView = itemView.findViewById(R.id.tv_event_date)
        var location: TextView = itemView.findViewById(R.id.tv_event_location)
        var entryFees: TextView = itemView.findViewById(R.id.tv_event_fees)

        fun bind(socialEvents: SocialEvents, socialEventClicked: OnSingleSocialEventClicked) {
            eventTitle.text = socialEvents.title
            shortDescription.text = socialEvents.shortDesc
            date.text = socialEvents.date.toString()
            location.text = socialEvents.location
            entryFees.text = socialEvents.entryFees.toString()

            itemView.setOnClickListener {
                socialEventClicked.onSingleSocialEventItemClicked(adapterPosition)
            }
        }
    }
        interface OnSingleSocialEventClicked {
            fun onSingleSocialEventItemClicked(position: Int)
        }

    class SocialDiffUtil : DiffUtil.ItemCallback<SocialEvents>(){
        override fun areItemsTheSame(oldItem: SocialEvents, newItem: SocialEvents): Boolean {
            return newItem.id == oldItem.id
        }

        override fun areContentsTheSame(oldItem: SocialEvents, newItem: SocialEvents): Boolean {
            return newItem.id == oldItem.id
        }
    }
}