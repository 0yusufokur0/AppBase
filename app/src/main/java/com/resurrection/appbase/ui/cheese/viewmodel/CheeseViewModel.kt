package com.resurrection.appbase.ui.cheese.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.resurrection.appbase.data.dummy.CHEESE_DATA
import com.resurrection.appbase.data.local.dao.CheeseDao
import com.resurrection.appbase.data.model.cheese.Cheese
import com.resurrection.appbase.data.model.cheese.CheeseListItem
import com.resurrection.base.core.viewmodel.CoreViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CheeseViewModel @Inject constructor(private var cheeseDao: CheeseDao) : CoreViewModel() {

    init {
        CoroutineScope(Dispatchers.IO).launch {
            cheeseDao.deleteAllCheese()
            cheeseDao.insert(
                CHEESE_DATA.map { Cheese(id = 0, name = it) }
            )
        }
    }

    val allCheeses: Flow<PagingData<CheeseListItem>> = Pager(
        config = PagingConfig(pageSize = 60, enablePlaceholders = true, maxSize = 200),
        pagingSourceFactory = { cheeseDao.allCheesesByName() }
    ).flow
        .map { pagingData ->
            pagingData
                // Map cheeses to common UI model.
                .map { cheese -> CheeseListItem.Item(cheese) }
                .insertSeparators { before: CheeseListItem?, after: CheeseListItem? ->
                    if (before == null && after == null) {
                        // List is empty after fully loaded; return null to skip adding separator.
                        null
                    } else if (after == null) {
                        // Footer; return null here to skip adding a footer.
                        null
                    } else if (before == null) {
                        // Header
                        CheeseListItem.Separator(after.name.first())
                    } else if (!before.name.first().equals(after.name.first(), ignoreCase = true)) {
                        // Between two items that start with different letters.
                        CheeseListItem.Separator(after.name.first())
                    } else {
                        // Between two items that start with the same letter.
                        null
                    }
                }
        }
        .cachedIn(viewModelScope)

    fun insert(text: CharSequence) = CoroutineScope(Dispatchers.IO).launch {
        cheeseDao.insert(Cheese(id = 0, name = text.toString()))
    }

    fun remove(cheese: Cheese) = CoroutineScope(Dispatchers.IO).launch {
        cheeseDao.delete(cheese)
    }
}
