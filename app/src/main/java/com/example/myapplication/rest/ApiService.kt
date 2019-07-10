package com.example.myapplication.rest

import com.example.myapplication.BuildConfig
import com.example.myapplication.model.DataModel
import retrofit.http.GET
import rx.Observable

interface ApiService {

    @GET("detil_jadwal?key=" + BuildConfig.API_KEY + "&npm=" + BuildConfig.NPM)
    fun getJadwalKuliah(): Observable<DataModel>

    @GET("detil_pribadi?key=" + BuildConfig.API_KEY + "&npm=" + BuildConfig.NPM)
    fun getProfil(): Observable<DataModel>

}