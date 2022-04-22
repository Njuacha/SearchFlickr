package com.njuacha.searchflickr

import com.njuacha.searchflickr.model.Photo
import com.njuacha.searchflickr.rest.ApiClient
import com.njuacha.searchflickr.rest.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository {
    companion object {

        // function to get the pictures from the flickr api;
        suspend fun getSearchPicturesFromApi(searchText: String): List<Photo>? {
            val apiService: ApiInterface? = ApiClient.getClient()?.create(ApiInterface::class.java)
            // move execution of the coroutine to the io dispatcher
            return withContext(Dispatchers.IO) {
                val picturesResponse = apiService?.getSearchPictures(searchText)?.execute()
                picturesResponse?.body()?.photos
            }
        }
    }
}