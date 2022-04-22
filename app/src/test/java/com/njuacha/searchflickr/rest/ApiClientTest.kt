package com.njuacha.searchflickr.rest

import org.junit.Assert.*
import org.junit.Test

class ApiClientTest {

    @Test
    fun getSearchQueryMap_ReturnsCorrectQuery() {
        val expectedSearchQueryMap = mapOf(
            Pair("method", "flickr.photos.search"),
            Pair("api_key", "37ad288835e4c64fc0cb8af3f3a1a65d"),
            Pair("format", "json"),
            Pair("nojsoncallback", "1"),
            Pair("safe_search", "1"),
            Pair("text", "offenburg")
        )
        val actualSearchQueryMap = ApiClient.getSearchQueryMap("offenburg")
        assertEquals(expectedSearchQueryMap, actualSearchQueryMap)
    }
}