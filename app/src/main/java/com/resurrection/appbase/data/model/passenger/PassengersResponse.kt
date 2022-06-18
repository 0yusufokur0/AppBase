package com.resurrection.appbase.data.model.passenger

data class PassengersResponse(
    val `data`: List<Passenger>,
    val totalPages: Int,
    val totalPassengers: Int
)