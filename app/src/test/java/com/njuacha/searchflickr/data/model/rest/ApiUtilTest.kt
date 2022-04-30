package com.njuacha.searchflickr.data.model.rest

import com.njuacha.searchflickr.data.rest.ApiUtil
import org.junit.Assert.*
import org.junit.Test

class ApiUtilTest {

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
        val actualSearchQueryMap = ApiUtil.getSearchQueryMap("offenburg")
        assertEquals(expectedSearchQueryMap, actualSearchQueryMap)
    }
}