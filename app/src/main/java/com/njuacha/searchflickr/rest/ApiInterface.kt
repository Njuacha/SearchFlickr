package com.njuacha.searchflickr.rest

import com.njuacha.searchflickr.model.PicturesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("method=flickr.photos.search&api_key=37ad288835e4c64fc0cb8af3f3a1a65d&format=json&nojsoncallback=1&safe_search=1")
    fun getSearchPictures(@Query("text") searchText: String): Call<PicturesResponse>
}