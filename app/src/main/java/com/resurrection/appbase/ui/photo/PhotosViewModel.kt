package com.resurrection.appbase.ui.photo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.resurrection.base.core.viewmodel.BaseViewModel
import com.resurrection.base.util.Resource
import com.resurrection.appbase.data.model.photos.PhotoModel
import com.resurrection.appbase.data.repository.InstaParkRepository

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(private val instaParkRepository: InstaParkRepository) :
    BaseViewModel() {

    private val _photos = MutableLiveData<Resource<PhotoModel>>()
    val photos: LiveData<Resource<PhotoModel>> = _photos

    var testData = MutableStateFlow<Resource<PhotoModel>>(Resource.Loading())


    init {
        testData.setData { instaParkRepository.getPhotos() }
    }

    fun getPhotos() = _photos.setData { instaParkRepository.getPhotos() }

}