package com.njuacha.searchflickr.data.repository

import com.njuacha.searchflickr.data.model.Photo
import com.njuacha.searchflickr.data.rest.ApiClient
import com.njuacha.searchflickr.data.rest.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

object MainRepository {
        // function to get the pictures from the flickr api;
        suspend fun getSearchPicturesFromApi(searchText: String): List<Photo>? {
            val apiService: ApiInterface? = ApiClient.getClient().create(ApiInterface::class.java)
            // move execution of the coroutine to the io dispatcher
            return withContext(Dispatchers.IO) {
                try {
                    val searchQueryMap = ApiClient.getSearchQueryMap(searchText)
                    val picturesResponse = apiService?.getSearchPictures(searchQueryMap)
                    picturesResponse?.photos?.photos
                } catch (e: IOException) {
                    e.printStackTrace()
                    emptyList<Photo>()
                }
            }
        }
}