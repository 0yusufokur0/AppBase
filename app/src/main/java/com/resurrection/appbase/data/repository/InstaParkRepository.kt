package com.resurrection.appbase.data.repository

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.resurrection.appbase.data.model.photos.PhotoModel
import com.resurrection.appbase.data.model.photos.PhotoModelItem
import com.resurrection.appbase.data.remote.InstaParkApiService
import com.resurrection.base.component.NetworkManager
import com.resurrection.base.component.OkHttpClientManager
import com.resurrection.base.network.getData
import com.resurrection.base.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.Headers.Companion.toHeaders
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.lang.reflect.Type
import java.util.*
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