package com.wadektech.eventshub.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.wadektech.eventshub.R
import com.wadektech.eventshub.databinding.MainEventsListBinding
import com.wadektech.eventshub.models.MainEvents

class MainEventsAdapter(var eventCardClicked: OnSingleEventCardClicked) :
        PagedListAdapter<MainEvents, MainEventsAdapter.MainEventsViewHolder>(MainEventsDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainEventsViewHolder {
        return MainEventsViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MainEventsViewHolder, position: Int) {
        val mainEvents = getItem(position)
        if (mainEvents != null){
            holder.bind(mainEvents, eventCardClicked)
        }
    }

    class MainEventsViewHolder private constructor(val binding: MainEventsListBinding) :
            RecyclerView.ViewHolder(binding.root) {

        fun bind(mainEvents: MainEvents, cardClicked: OnSingleEventCardClicked) {
            binding.tvEventTitle.text = mainEvents.title
            binding.tvTitleDesc.text = mainEvents.shortDesc
            binding.tvEventDate.text = mainEvents.date.toString()
            binding.tvEventLocation.text = mainEvents.location
            binding.tvEventFees.text = mainEvents.entryFees.toString()

            itemView.setOnClickListener {
                cardClicked.singleEventCardClicked(adapterPosition)
            }
        }

        companion object {
            fun from(parent: ViewGroup): MainEventsViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = MainEventsListBinding.inflate(layoutInflater, parent, false)
                return MainEventsViewHolder(binding)
            }
        }
    }

    interface OnSingleEventCardClicked {
        fun singleEventCardClicked(position: Int)
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