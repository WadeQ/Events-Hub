package com.wadektech.eventshub.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wadektech.eventshub.R
import com.wadektech.eventshub.adapter.MainEventsAdapter.MainEventsViewHolder
import com.wadektech.eventshub.models.MainEvents

class MainEventsAdapter(var eventCardClicked: OnSingleEventCardClicked) :
        ListAdapter<MainEvents, MainEventsAdapter.MainEventsViewHolder>(MainEventsDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainEventsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.events_list, parent, false)
        return MainEventsViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainEventsViewHolder, position: Int) {
        val mainEvents = getItem(position)
        if (mainEvents != null){
            holder.bind(mainEvents, eventCardClicked)
        }
    }


    inner class MainEventsViewHolder(itemView: View) :
            RecyclerView.ViewHolder(itemView) {
        private val eventTitle : TextView = itemView.findViewById(R.id.tv_event_title)
        private val shortDescription : TextView = itemView.findViewById(R.id.tv_title_desc)
        private val date : TextView = itemView.findViewById(R.id.tv_event_date)
        private val location : TextView = itemView.findViewById(R.id.tv_event_location)
        private val entryFees :TextView = itemView.findViewById(R.id.tv_event_fees)

        fun bind(mainEvents: MainEvents, cardClicked: OnSingleEventCardClicked) {
            eventTitle.text = mainEvents.title
            shortDescription.text = mainEvents.shortDesc
            date.text = mainEvents.date.toString()
            location.text = mainEvents.location
            entryFees.text = mainEvents.entryFees.toString()
            itemView.setOnClickListener {
                cardClicked.singleEventCardClicked(adapterPosition)
            }
        }
    }

    interface OnSingleEventCardClicked {
        fun singleEventCardClicked(position: Int)
    }

    companion object {
        private const val TAG = "MainEventsAdapter"
    }

    class MainEventsDiffUtil : DiffUtil.ItemCallback<MainEvents>() {
        override fun areItemsTheSame(oldItem: MainEvents, newItem: MainEvents): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MainEvents, newItem: MainEvents): Boolean {
           return oldItem.id == newItem.id
        }
    }
}