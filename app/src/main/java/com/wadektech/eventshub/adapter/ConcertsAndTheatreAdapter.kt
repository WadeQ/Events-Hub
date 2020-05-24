package com.wadektech.eventshub.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wadektech.eventshub.R
import com.wadektech.eventshub.models.Concerts

class ConcertsAndTheatreAdapter(var singleConcertItemClicked: OnSingleConcertItemClicked) :
        ListAdapter<Concerts, ConcertsAndTheatreAdapter.ViewHolder>(ConcertsDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.events_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val concerts = getItem(position)
        if (concerts != null){
            holder.bind(concerts , singleConcertItemClicked)
        }
    }

    inner class ViewHolder(itemView: View) :
            RecyclerView.ViewHolder(itemView) {
        private val eventTitle : TextView = itemView.findViewById(R.id.tv_event_title)
        private val shortDescription : TextView = itemView.findViewById(R.id.tv_title_desc)
        private val date : TextView = itemView.findViewById(R.id.tv_event_date)
        private val location : TextView = itemView.findViewById(R.id.tv_event_location)
        private val entryFees : TextView = itemView.findViewById(R.id.tv_event_fees)

        fun bind(concerts: Concerts, singleConcertItemClicked: OnSingleConcertItemClicked){
            eventTitle.text = concerts.title
            shortDescription.text = concerts.shortDesc
            date.text = concerts.date.toString()
            location.text = concerts.location
            entryFees.text = concerts.entryFees.toString()
            itemView.setOnClickListener {
                singleConcertItemClicked.onSingleConcertItemClicked(adapterPosition)
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