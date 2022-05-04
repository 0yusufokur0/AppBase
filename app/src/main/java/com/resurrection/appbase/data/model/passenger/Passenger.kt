package com.resurrection.appbase.data.model.passenger

import com.resurrection.appbase.data.model.passenger.Airline

data class Passenger(
    val __v: Int,
    val _id: String,
    val airline: List<Airline>,
    val name: String,
    val trips: Int
)