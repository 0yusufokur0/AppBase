package com.veripark.instapark.data.model.detail

import com.resurrection.appbase.data.model.detail.Address
import com.resurrection.appbase.data.model.detail.Company

data class DetailModel(
    val address: Address,
    val company: Company,
    val email: String,
    val id: Int,
    val name: String,
    val phone: String,
    val username: String,
    val website: String
)