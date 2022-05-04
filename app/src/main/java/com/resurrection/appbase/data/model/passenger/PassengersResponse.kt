package com.resurrection.appbase.data.model.passenger

import com.resurrection.appbase.data.model.passenger.Passenger

data class PassengersResponse(
    val `data`: List<Passenger>,
    val totalPages: Int,
    val totalPassengers: Int
)