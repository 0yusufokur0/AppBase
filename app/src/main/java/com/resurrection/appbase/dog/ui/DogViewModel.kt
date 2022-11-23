package com.resurrection.appbase.dog.ui

import com.resurrection.appbase.dog.data.model.DogModel
import com.resurrection.appbase.dog.usecase.GetDogUseCase
import com.resurrection.base.core.viewmodel.CoreViewModel
import com.resurrection.base.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DogViewModel @Inject constructor(
    private val dogUseCase: GetDogUseCase
) : CoreViewModel() {

    val dog = liveData<Resource<DogModel>>()

    fun getDog() = dog.fetchData(
        request = dogUseCase::invoke
    )
}