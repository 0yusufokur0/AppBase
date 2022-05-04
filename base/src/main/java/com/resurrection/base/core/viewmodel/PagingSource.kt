package com.resurrection.base.core.viewmodel

import androidx.paging.PagingSource
import androidx.paging.PagingState


fun <ResponseModel, ListItemModel:Any> pagingDataSource(
    request: suspend (nextPageNumber:Int) -> ResponseModel,
    data: ((ResponseModel) -> List<ListItemModel>),
    totalPages: ((ResponseModel) -> Int)
): PagingSource<Int, ListItemModel> {

    val pagingSourceObject = object : PagingSource<Int, ListItemModel>(){

        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ListItemModel> {
            return try {
                val nextPageNumber = params.key ?: 0
                val response: ResponseModel = request.invoke(nextPageNumber)
                val itemList = data.invoke(response)
                val totalPageNumber = totalPages.invoke(response)

                LoadResult.Page(
                    data = itemList,
                    prevKey = if (nextPageNumber > 0) nextPageNumber - 1 else null,
                    nextKey = if (nextPageNumber < totalPageNumber) nextPageNumber + 1 else null
                )
            } catch (e: Exception) {
                LoadResult.Error(e)
            }
        }

        override fun getRefreshKey(state: PagingState<Int, ListItemModel>): Int? {
            return null
        }

    }

    return  pagingSourceObject
}