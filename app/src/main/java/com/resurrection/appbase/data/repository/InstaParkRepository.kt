package com.resurrection.appbase.data.repository

import com.resurrection.appbase.data.model.photos.PhotoModel
import com.resurrection.appbase.data.model.posts.PostModel
import com.resurrection.appbase.data.model.users.UsersModel
import com.resurrection.base.core.BaseRepository
import com.resurrection.appbase.data.remote.InstaParkApiService
import com.resurrection.base.util.Resource
import com.veripark.instapark.data.model.users.UsersModelItem
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class InstaParkRepository @Inject constructor(val instaParkApiService: InstaParkApiService) : BaseRepository() {

    suspend fun getUsers() = getData { instaParkApiService.getUsers() }
    suspend fun getUser(id: String) = getData { instaParkApiService.getUser(id) }
    suspend fun getPosts() = getData { instaParkApiService.getPosts() }
    suspend fun getPhotos() = getData { instaParkApiService.getPhotos() }

}