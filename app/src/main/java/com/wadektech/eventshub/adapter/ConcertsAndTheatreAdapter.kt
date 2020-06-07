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
import com.wadektech.eventshub.databinding.ConcertsEventsListBinding

import com.wadektech.eventshub.models.Concerts

class ConcertsAndTheatreAdapter(var singleConcertItemClicked: OnSingleConcertItemClicked) :
        PagedListAdapter<Concerts, ConcertsAndTheatreAdapter.ViewHolder>(ConcertsDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val concerts = getItem(position)
        if (concerts != null){
            holder.bind(concerts , singleConcertItemClicked)
        }
    }

    class ViewHolder private constructor(val binding: ConcertsEventsListBinding) :
            RecyclerView.ViewHolder(binding.root) {

        fun bind(concerts: Concerts, singleConcertItemClicked: OnSingleConcertItemClicked){
            binding.tvEventTitle.text = concerts.title
            binding.tvTitleDesc.text = concerts.shortDesc
            binding.tvEventDate.text = concerts.date.toString()
            binding.tvEventLocation.text = concerts.location
            binding.tvEventFees.text = concerts.entryFees.toString()

            itemView.setOnClickListener {
                singleConcertItemClicked.onSingleConcertItemClicked(adapterPosition)
            }
        }
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ConcertsEventsListBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    interface OnSingleConcertItemClicked {
        fun onSingleConcertItemClicked(position: Int)
    }

    class ConcertsDiffUtil : DiffUtil.ItemCallback<Concerts>(){
        override fun areItemsTheSame(oldItem: Concerts, newItem: Concerts): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Concerts, newItem: Concerts): Boolean {
            return oldItem.id == newItem.id
        }
    }
}