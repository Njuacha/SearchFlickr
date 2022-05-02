package com.njuacha.searchflickr.data.repository

import com.njuacha.searchflickr.data.model.Photo
import com.njuacha.searchflickr.utils.ApiUtil
import com.njuacha.searchflickr.data.api.ApiHelper
import com.njuacha.searchflickr.data.model.PhotosResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiHelper: ApiHelper) {

    // function to get the pictures from the flickr api;
    suspend fun getSearchPicturesFromApi(searchText: String): Response<PhotosResponse>? {

        // move execution of the coroutine to the io dispatcher
        return withContext(Dispatchers.IO) {
            try {
                val searchQueryMap = ApiUtil.getSearchQueryMap(searchText)
                val picturesResponse = apiHelper.getSearchPictures(searchQueryMap).execute()
                picturesResponse
            } catch (e: IOException) {
                e.printStackTrace()
                null
            }
        }
    }
}