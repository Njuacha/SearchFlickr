package com.njuacha.searchflickr.data.api

import com.njuacha.searchflickr.data.model.PhotosResponse
import retrofit2.Call
import retrofit2.http.QueryMap

interface ApiHelper {
    fun getSearchPictures(@QueryMap params: Map<String, String>): Call<PhotosResponse>
}