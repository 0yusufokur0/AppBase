package com.resurrection.appbase.dog.data.repository

interface DogRepository {
    suspend fun getDog()
}