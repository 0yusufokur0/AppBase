package com.resurrection.appbase.data.repository.instapark

/*
class InstaParkRepositoryImpl @Inject constructor(
    private val instaParkApiService: InstaParkApiService,
    private val okHttpClientManager: OkHttpClientManager
):InstaParkRepository {

    override suspend fun getUsers() = getData { instaParkApiService.getUsers() }

    override suspend fun getUser(id: String) = getData { instaParkApiService.getUser(id) }

    override suspend fun getPosts() = getData { instaParkApiService.getPosts() }

    override suspend fun getPhotos() = okHttpClientManager.newRequest(
        path = "/photos",
        responseType = PhotoModel::class.java,
    )

}*/
