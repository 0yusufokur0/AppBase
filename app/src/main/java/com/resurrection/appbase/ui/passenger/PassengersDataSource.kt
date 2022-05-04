package com.resurrection.appbase.ui.passenger

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.resurrection.appbase.data.model.passenger.Passenger
import com.resurrection.appbase.data.model.passenger.PassengersResponse
import com.resurrection.appbase.data.remote.PassengersApiService

class PassengersDataSource(private val api: PassengersApiService) : PagingSource<Int, Passenger>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Passenger> {
        return try {
            val nextPageNumber = params.key ?: 0
            val response: PassengersResponse = api.getPassengersData(nextPageNumber)

            LoadResult.Page(
                data = response.data,
                prevKey = if (nextPageNumber > 0) nextPageNumber - 1 else null,
                nextKey = if (nextPageNumber < response.totalPages) nextPageNumber + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Passenger>): Int? {
        return null
    }


}