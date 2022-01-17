package com.veripark.instapark.data.repository


import com.resurrection.base.util.Resource
import com.resurrection.base.util.getResourceByNetworkRequest
import com.veripark.instapark.data.model.photos.PhotoModel
import com.veripark.instapark.data.model.posts.PostModel
import com.veripark.instapark.data.model.users.UsersModel
import com.veripark.instapark.data.model.users.UsersModelItem
import com.veripark.instapark.data.remote.InstaParkApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class InstaParkRepositoryImpl @Inject constructor(private val instaParkApiService: InstaParkApiService)
    : InstaParkRepository {

    override suspend fun getUsers(): Flow<Resource<UsersModel>> = flow {
            emit(getResourceByNetworkRequest { instaParkApiService.getUsers() })
        }

    override suspend fun getUser(id: String): Flow<Resource<UsersModelItem>> = flow {
        emit(getResourceByNetworkRequest { instaParkApiService.getUser(id) })
    }

    override suspend fun getPosts(): Flow<Resource<PostModel>> = flow {
        emit(getResourceByNetworkRequest { instaParkApiService.getPosts() })
    }

    override suspend fun getPhotos(): Flow<Resource<PhotoModel>> = flow {
        emit(getResourceByNetworkRequest { instaParkApiService.getPhotos() })
    }

}