package com.njuacha.searchflickr.data.rest

import com.njuacha.searchflickr.data.model.PhotosResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiService {

    @GET("rest/")
    suspend fun getSearchPictures(@QueryMap params: Map<String, String>): PhotosResponse
}
