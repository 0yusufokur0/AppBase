package com.resurrection.appbase.data.model.passenger

data class Passenger(
    val __v: Int,
    val _id: String,
    val airline: List<Airline>,
    val name: String,
    val trips: Int
)