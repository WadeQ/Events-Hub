package com.wadektech.eventshub.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.wadektech.eventshub.models.MainEvents
import com.wadektech.eventshub.models.Test
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


private const val BASE_URL = ""

private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

 val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl(BASE_URL)
        .build()

interface EventsApiService{
    @GET("")
    fun getAllEvents() : Deferred<List<Test>>
}

object EventsApi{
    val retrofitService :EventsApiService by lazy {
        retrofit.create(EventsApiService::class.java)
    }
}

