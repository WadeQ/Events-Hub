package com.wadektech.eventshub.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wadektech.eventshub.R
import com.wadektech.eventshub.databinding.FriendsEventsListBinding
import com.wadektech.eventshub.models.FriendsEvents

class FriendsEventsAdapter( var singleFriendsEventItemClicked: OnSingleFriendsEventItemClicked) :
        PagedListAdapter<FriendsEvents, FriendsEventsAdapter.ViewHolder>(FriendsDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val friends = getItem(position)
        if (friends != null){
            holder.bind(friends, singleFriendsEventItemClicked)
        }
    }

    class ViewHolder private constructor(val binding: FriendsEventsListBinding) :
            RecyclerView.ViewHolder(binding.root) {

        fun bind(friendsEvents: FriendsEvents, singleFriendsEventClicked: OnSingleFriendsEventItemClicked) {
            binding.tvEventTitle.text= friendsEvents.title
            binding.tvTitleDesc.text = friendsEvents.shortDesc
            binding.tvEventDate.text = friendsEvents.date.toString()
            binding.tvEventLocation.text = friendsEvents.location
            binding.tvEventFees.text = friendsEvents.entryFees.toString()

            itemView.setOnClickListener {
                singleFriendsEventClicked.singleFriendsEventsItemClicked(adapterPosition)
            }
        }
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = FriendsEventsListBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
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