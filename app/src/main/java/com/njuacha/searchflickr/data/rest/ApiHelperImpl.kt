package com.njuacha.searchflickr.data.rest

import com.njuacha.searchflickr.data.model.PhotosResponse
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {
    override suspend fun getSearchPictures(params: Map<String, String>): PhotosResponse =
        apiService.getSearchPictures(params)
}