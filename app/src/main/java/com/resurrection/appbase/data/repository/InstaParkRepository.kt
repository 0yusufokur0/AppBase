package com.resurrection.appbase.data.repository

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.resurrection.appbase.data.model.photos.PhotoModel
import com.resurrection.appbase.data.model.photos.PhotoModelItem
import com.resurrection.appbase.data.remote.InstaParkApiService
import com.resurrection.base.component.NetworkManager
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
    val networkManager: NetworkManager) {

    private var okHttpClient = OkHttpClient().newBuilder().build()

    val medicineListType: Type = object : TypeToken<ArrayList<PhotoModelItem>>() {}.type

    suspend fun getUsers() = getData { instaParkApiService.getUsers() }
    suspend fun getUser(id: String) = getData { instaParkApiService.getUser(id) }
    suspend fun getPosts() = getData { instaParkApiService.getPosts() }
    suspend fun getPhotos() = test(
            path = "https://jsonplaceholder.typicode.com/photos",
            responseType = PhotoModel::class.java,
        )


    fun <T> test(
        okHttpClient: OkHttpClient = this.okHttpClient,
        baseUrl: String = "",
        method: String = NetworkManager.GET,
        body: String? = null,
        headers: Map<String, String>? = null,
        path: String,
        responseType: Class<T>,
        map:(responseBody: String) -> T =  {
            Gson().fromJson(it,responseType)
        }
    ) = flow {
        val requestBuilder = Request.Builder()
            .url(baseUrl + path)
            .method(method, body?.toRequestBody())

        headers?.let { requestBuilder.headers(headers.toHeaders()) }

        val request = requestBuilder.build()

        var resultResource: Resource<T>? = null
        try {
            val response = okHttpClient.newCall(request).execute()
            if (response.isSuccessful) {
                response.body?.apply {
                    val mapResult = map(this.string())
                    resultResource = if (map != null) Resource.Success(mapResult)
                    else Resource.Error(Exception("map is null"))
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            resultResource = Resource.Error(e)
        }
        if (resultResource == null) resultResource = Resource.Loading()

        emit(resultResource!!)
    }.flowOn(Dispatchers.IO)
}