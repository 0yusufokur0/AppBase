package com.resurrection.appbase.data.repository

import com.resurrection.appbase.BuildConfig
import com.resurrection.appbase.data.model.photos.PhotoModel
import com.resurrection.appbase.data.remote.InstaParkApiService
import com.resurrection.base.component.NetworkManager
import com.resurrection.base.network.getData
import com.resurrection.base.util.Resource
import javax.inject.Inject

class InstaParkRepository @Inject constructor(
    val instaParkApiService: InstaParkApiService,
    val networkManager: NetworkManager) {

    suspend fun getUsers() = getData { instaParkApiService.getUsers() }
    suspend fun getUser(id: String) = getData { instaParkApiService.getUser(id) }
    suspend fun getPosts() = getData { instaParkApiService.getPosts() }
    suspend fun getPhotos() =
        networkManager.newRequest(
            path = "https://jsonplaceholder.typicode.com/photos",
            responseType = PhotoModel::class.java,
            map = {
                networkManager.typeConverter.fromJson(it, PhotoModel::class.java)
            }
        )

}