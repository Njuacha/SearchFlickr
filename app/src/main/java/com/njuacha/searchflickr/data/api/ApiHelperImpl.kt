package com.njuacha.searchflickr.data.api

import com.njuacha.searchflickr.data.model.PhotosResponse
import retrofit2.Call
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {
    override fun getSearchPictures(params: Map<String, String>): Call<PhotosResponse> =
        apiService.getSearchPictures(params)
}