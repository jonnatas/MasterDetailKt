package com.example.masterpostdetail

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun noteService(): NoteService {
        return retrofit.create(NoteService::class.java)
    }
}