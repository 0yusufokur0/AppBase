package com.resurrection.appbase.data.repository

import com.google.gson.reflect.TypeToken
import com.resurrection.appbase.data.model.photos.PhotoModel
import com.resurrection.appbase.data.model.photos.PhotoModelItem
import com.resurrection.appbase.data.remote.InstaParkApiService
import com.resurrection.base.components.network.OkHttpClientManager
import com.resurrection.base.network.getData
import java.lang.reflect.Type
import javax.inject.Inject
import kotlin.collections.ArrayList


class InstaParkRepository @Inject constructor(
    val instaParkApiService: InstaParkApiService,
    val okHttpClientManager: OkHttpClientManager
    ) {


    val medicineListType: Type = object : TypeToken<ArrayList<PhotoModelItem>>() {}.type

    suspend fun getUsers() = getData { instaParkApiService.getUsers() }
    suspend fun getUser(id: String) = getData { instaParkApiService.getUser(id) }
    suspend fun getPosts() = getData { instaParkApiService.getPosts() }
    suspend fun getPhotos() = okHttpClientManager.newRequest(
            path = "/photos",
            responseType = PhotoModel::class.java,
        )


}