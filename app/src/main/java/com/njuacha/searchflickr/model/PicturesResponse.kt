package com.njuacha.searchflickr.model

import com.google.gson.annotations.SerializedName

data class PicturesResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("pages") val pages: Int,
    @SerializedName("perpage") val perPage: Int,
    @SerializedName("total") val total: Int,
    @SerializedName("photo") val photos: List<Photo>
)