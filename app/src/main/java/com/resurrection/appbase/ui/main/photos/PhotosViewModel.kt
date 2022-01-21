package com.resurrection.appbase.ui.main.photos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.resurrection.base.core.BaseViewModel
import com.resurrection.base.util.Resource
import com.resurrection.appbase.data.model.photos.PhotoModel
import com.veripark.instapark.data.repository.InstaParkRepository

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(private val instaParkRepository: InstaParkRepository) :
    BaseViewModel() {

    private val _photos = MutableLiveData<Resource<PhotoModel>>()
    val photos: LiveData<Resource<PhotoModel>> = _photos

    fun getPhotos() = _photos.setData { instaParkRepository.getPhotos() }

}