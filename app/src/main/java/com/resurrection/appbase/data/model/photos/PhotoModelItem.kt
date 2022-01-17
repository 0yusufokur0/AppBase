package com.veripark.instapark.data.model.photos

data class PhotoModelItem(
    val albumId: Int,
    val id: Int,
    val thumbnailUrl: String,
    val title: String,
    val url: String
)