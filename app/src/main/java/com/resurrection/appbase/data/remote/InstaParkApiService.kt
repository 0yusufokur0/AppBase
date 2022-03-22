package com.resurrection.appbase.data.remote

import com.resurrection.appbase.data.model.photos.PhotoModel
import com.resurrection.appbase.data.model.photos.PhotoModelItem
import com.resurrection.appbase.data.model.posts.PostModel
import com.resurrection.appbase.data.model.users.UsersModel
import com.resurrection.appbase.data.model.users.UsersModelItem
import retrofit2.Response
import retrofit2.http.*

interface InstaParkApiService{
    @GET("users")
    suspend fun getUsers(): Response<UsersModel>

    @GET("posts")
    suspend fun getPosts(): Response<PostModel>

    @GET("photos")
    suspend fun getPhotos(): Response<ArrayList<PhotoModelItem>>

    @GET("users/{id}")
    suspend fun getUser(@Path("id") id:String): Response<UsersModelItem>

}