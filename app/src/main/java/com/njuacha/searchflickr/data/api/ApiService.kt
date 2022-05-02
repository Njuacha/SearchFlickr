package com.njuacha.searchflickr.data.api

import com.njuacha.searchflickr.data.model.PhotosResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiService {

    @GET("rest/")
    fun getSearchPictures(@QueryMap params: Map<String, String>): Call<PhotosResponse>
}
