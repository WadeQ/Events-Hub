package com.wadektech.eventshub.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.wadektech.eventshub.R
import com.wadektech.eventshub.databinding.SocialEventsListBinding
import com.wadektech.eventshub.models.SocialEvents

class SocialEventsAdapter(var singleSocialEventClicked: OnSingleSocialEventClicked) :
        PagedListAdapter<SocialEvents, SocialEventsAdapter.ViewHolder>(SocialDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val socialEvents = getItem(position)
        if (socialEvents!=null){
            holder.bind(socialEvents, singleSocialEventClicked)
        }
    }

    class ViewHolder private constructor(val binding: SocialEventsListBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(socialEvents: SocialEvents, socialEventClicked: OnSingleSocialEventClicked) {
            binding.tvEventTitle.text = socialEvents.title
            binding.tvTitleDesc.text = socialEvents.shortDesc
            binding.tvEventDate.text = socialEvents.date.toString()
            binding.tvEventLocation.text = socialEvents.location
            binding.tvEventFees.text = socialEvents.entryFees.toString()

            itemView.setOnClickListener {
                socialEventClicked.onSingleSocialEventItemClicked(adapterPosition)
            }
        }
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = SocialEventsListBinding.inflate(layoutInflater , parent, false)
                return ViewHolder(binding)
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