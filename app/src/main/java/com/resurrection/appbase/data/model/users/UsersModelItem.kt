package com.veripark.instapark.data.model.users

import com.resurrection.appbase.data.model.users.Address
import com.resurrection.appbase.data.model.users.Company

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