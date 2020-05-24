package com.wadektech.eventshub.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wadektech.eventshub.R
import com.wadektech.eventshub.models.FriendsEvents

class FriendsEventsAdapter( var singleFriendsEventItemClicked: OnSingleFriendsEventItemClicked) :
        ListAdapter<FriendsEvents, FriendsEventsAdapter.ViewHolder>(FriendsDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.events_list, parent, false)
        return ViewHolder(view, singleFriendsEventItemClicked)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val friends = getItem(position)
        if (friends != null){
            holder.bind(friends, singleFriendsEventItemClicked)
        }
    }

    inner class ViewHolder(itemView: View, friendsEventItemClicked: OnSingleFriendsEventItemClicked) :
            RecyclerView.ViewHolder(itemView) {
        private val eventTitle : TextView= itemView.findViewById(R.id.tv_event_title)
        private val shortDescription : TextView= itemView.findViewById(R.id.tv_title_desc)
        val date :TextView = itemView.findViewById(R.id.tv_event_date)
        val location : TextView= itemView.findViewById(R.id.tv_event_location)
        val entryFees: TextView = itemView.findViewById(R.id.tv_event_fees)

        fun bind(friendsEvents: FriendsEvents, singleFriendsEventClicked: OnSingleFriendsEventItemClicked) {
            eventTitle.text = friendsEvents.title
            shortDescription.text = friendsEvents.shortDesc
            date.text = friendsEvents.date.toString()
            location.text = friendsEvents.location
            entryFees.text = friendsEvents.entryFees.toString()
            itemView.setOnClickListener {
                singleFriendsEventClicked.singleFriendsEventsItemClicked(adapterPosition)
            }
        }
    }

    interface OnSingleFriendsEventItemClicked {
        fun singleFriendsEventsItemClicked(position: Int)
    }

    class FriendsDiffUtil : DiffUtil.ItemCallback<FriendsEvents>(){
        override fun areItemsTheSame(oldItem: FriendsEvents, newItem: FriendsEvents): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: FriendsEvents, newItem: FriendsEvents): Boolean {
           return  oldItem.id == newItem.id
        }

    }

}