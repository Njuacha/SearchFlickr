package com.njuacha.searchflickr.data.rest

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiUtil {

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