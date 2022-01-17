package com.veripark.instapark.data.remote

import com.veripark.instapark.data.model.photos.PhotoModel
import com.veripark.instapark.data.model.posts.PostModel
import com.veripark.instapark.data.model.users.UsersModel
import com.veripark.instapark.data.model.users.UsersModelItem
import retrofit2.Response
import retrofit2.http.*

interface InstaParkApiService {
    @GET("users")
    suspend fun getUsers(): Response<UsersModel>

    @GET("posts")
    suspend fun getPosts(): Response<PostModel>

    @GET("photos")
    suspend fun getPhotos(): Response<PhotoModel>

    @GET("users/{id}")
    suspend fun getUser(@Path("id") id:String): Response<UsersModelItem>

/*
    @GET("3/movie/{id}")
    fun getMovieById(
        @Path("id") id: String,
        @Query("api_key") apiKey: String = BuildConfig.MOVIE_TOKEN
    ):Call<SearchDetailMovieResponse>*/

/*
    https://jsonplaceholder.typicode.com/users/1
*/
}