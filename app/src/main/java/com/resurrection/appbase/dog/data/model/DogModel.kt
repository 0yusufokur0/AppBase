package com.resurrection.appbase.dog.data.model

import com.google.gson.annotations.SerializedName

data class DogModel(

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("status")
    val status: String? = null
)
