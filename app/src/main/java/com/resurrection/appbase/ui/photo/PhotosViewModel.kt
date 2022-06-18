package com.resurrection.appbase.ui.photo

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.resurrection.appbase.data.model.photos.PhotoModel
import com.resurrection.appbase.data.repository.InstaParkRepository
import com.resurrection.base.core.viewmodel.BaseViewModel
import com.resurrection.base.utils.Resource
import com.resurrection.base.utils.callPrivateFunc
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.reflect.full.declaredMemberFunctions
import kotlin.reflect.jvm.isAccessible

@HiltViewModel
class PhotosViewModel @Inject constructor(private val instaParkRepository: InstaParkRepository) :
    BaseViewModel() {

    val photos = liveData<Resource<PhotoModel>>()

     fun getPhotos() = fetchLiveData(
         liveData = photos,
         request = { instaParkRepository.getPhotos() }
     )

}