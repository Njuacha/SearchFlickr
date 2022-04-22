package com.njuacha.searchflickr.rest

import com.njuacha.searchflickr.model.PhotosResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiInterface {

    @GET("rest/")
    fun getSearchPictures(@QueryMap params: Map<String, String>): Call<PhotosResponse>
}
