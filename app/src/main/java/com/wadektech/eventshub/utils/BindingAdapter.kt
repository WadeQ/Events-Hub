package com.wadektech.eventshub.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.wadektech.eventshub.adapter.ConcertsAndTheatreAdapter
import com.wadektech.eventshub.models.Concerts
import timber.log.Timber

@BindingAdapter("concertsBindingAdapter")
fun bindConcertsAdapter(recyclerView: RecyclerView, concertsData: PagedList<Concerts>?){
    val adapter = recyclerView.adapter as ConcertsAndTheatreAdapter
    Timber.d("binding adater list size is: ${concertsData?.size}")
    adapter.submitList(concertsData)
}
