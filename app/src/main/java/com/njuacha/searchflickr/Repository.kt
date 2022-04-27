package com.njuacha.searchflickr

import com.njuacha.searchflickr.model.Photo
import com.njuacha.searchflickr.rest.ApiClient
import com.njuacha.searchflickr.rest.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

object Repository {
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