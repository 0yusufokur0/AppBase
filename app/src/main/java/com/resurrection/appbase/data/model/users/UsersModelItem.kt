package com.veripark.instapark.data.model.users

import android.os.Parcelable

import java.io.Serializable

data class UsersModelItem(
    val address: Address,
    val company: Company,
    val email: String,
    val id: Int,
    val name: String,
    val phone: String,
    val username: String,
    val website: String
)