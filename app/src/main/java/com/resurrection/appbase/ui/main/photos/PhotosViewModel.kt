package com.resurrection.appbase.ui.main.photos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.resurrection.base.core.BaseViewModel
import com.resurrection.base.util.Resource
import com.veripark.instapark.data.model.photos.PhotoModel
import com.veripark.instapark.data.repository.InstaParkRepository

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(private val instaParkRepository: InstaParkRepository) :
    BaseViewModel() {

    private val _photos = MutableLiveData<Resource<PhotoModel>>()
    val photos: LiveData<Resource<PhotoModel>> = _photos

    fun getPhotos() = _photos.getData { instaParkRepository.getPhotos() }


}