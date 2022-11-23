package com.resurrection.appbase.ui.photo

import com.resurrection.appbase.data.model.photos.PhotoModel
import com.resurrection.base.core.viewmodel.CoreViewModel
import com.resurrection.base.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(/*private val instaParkRepository: InstaParkRepository*/) :
    CoreViewModel() {

    val photos = liveData<Resource<PhotoModel>>()

/*
    fun getPhotos() = photos.fetchData(
        request = { instaParkRepository.getPhotos() }
    )
*/
}