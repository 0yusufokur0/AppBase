package com.resurrection.appbase.ui.photo

import com.resurrection.appbase.data.model.photos.PhotoModel
import com.resurrection.appbase.data.repository.InstaParkRepository
import com.resurrection.base.core.viewmodel.BaseViewModel
import com.resurrection.base.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(private val instaParkRepository: InstaParkRepository) :
    BaseViewModel() {

    val photos = liveData<Resource<PhotoModel>>()

    fun getPhotos() = fetchLiveData(
        liveData = photos,
        request = { instaParkRepository.getPhotos() }
    )

}