package com.wadektech.eventshub.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wadektech.eventshub.R
import com.wadektech.eventshub.databinding.ProfEventsListBinding
import com.wadektech.eventshub.models.ProfessionalEvents

class ProfessionalEventsAdapter(var singleProfEventClicked: OnSingleProfEventClicked) :
        PagedListAdapter<ProfessionalEvents, ProfessionalEventsAdapter.ViewHolder>(ProfessionalDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val professionalEvents = getItem(position)
        if (professionalEvents != null){
            holder.bind(professionalEvents, singleProfEventClicked)
        }

    }

    class ViewHolder(val binding: ProfEventsListBinding) :
            RecyclerView.ViewHolder(binding.root){

        fun bind(professionalEvents: ProfessionalEvents, profEventClicked: OnSingleProfEventClicked){
            binding.tvEventTitle.text = professionalEvents.title
            binding.tvTitleDesc.text = professionalEvents.shortDesc
            binding.tvEventDate.text = professionalEvents.date.toString()
            binding.tvEventLocation.text = professionalEvents.location
            binding.tvEventFees.text = professionalEvents.entryFees.toString()

            itemView.setOnClickListener {
                profEventClicked.singleProfessionalEventClicked(adapterPosition)
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ProfEventsListBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
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