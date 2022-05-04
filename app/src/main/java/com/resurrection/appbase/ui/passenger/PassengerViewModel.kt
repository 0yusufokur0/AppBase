package com.resurrection.appbase.ui.passenger

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.resurrection.appbase.data.remote.PassengersApiService
import com.resurrection.base.core.viewmodel.BaseViewModel
import com.resurrection.base.core.viewmodel.pagingDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PassengerViewModel @Inject constructor(val passengersApiService: PassengersApiService) :
    BaseViewModel() {

    val passengers = Pager(config = PagingConfig(pageSize = 10, prefetchDistance = 2),
        pagingSourceFactory = {
            pagingDataSource(
                request = { passengersApiService.getPassengersData(it) },
                data = { it.data },
                totalPages = { it.totalPages }
            )
        }
    ).flow.cachedIn(viewModelScope)

}