package com.resurrection.appbase.ui.photo

import androidx.lifecycle.MutableLiveData
import com.resurrection.appbase.data.model.photos.PhotoModel
import com.resurrection.appbase.data.repository.InstaParkRepository
import com.resurrection.base.core.viewmodel.BaseViewModel
import com.resurrection.base.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(private val instaParkRepository: InstaParkRepository) :
    BaseViewModel() {

    private val _photos = MutableLiveData<Resource<PhotoModel>>()
    val photos = _photos.toLiveData()

     fun getPhotos() = fetchLiveData(
         liveData = _photos,
         request = { instaParkRepository.getPhotos() }
     )

}