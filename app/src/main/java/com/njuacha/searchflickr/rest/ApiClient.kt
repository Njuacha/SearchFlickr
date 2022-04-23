package com.njuacha.searchflickr.rest

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiClient {

    companion object {
        private val BASE_URL = "https://api.flickr.com/services/"

        private var retrofit: Retrofit? = null

        fun getClient(): Retrofit? {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }

        var queryMap = mutableMapOf(
            Pair("method", "flickr.photos.search"),
            Pair("api_key", ""),
            Pair("format", "json"),
            Pair("nojsoncallback", "1"),
            Pair("safe_search", "1")
        )

        fun setApiKey(apiKey: String) {
            queryMap["api_key"] = apiKey
        }

        fun getSearchQueryMap(searchText: String): Map<String, String> {
            queryMap["text"] = searchText
            return queryMap
        }

    }
}