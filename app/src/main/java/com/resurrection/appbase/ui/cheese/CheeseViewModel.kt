package com.resurrection.appbase.ui.cheese

import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.resurrection.appbase.data.dummy.CHEESE_DATA
import com.resurrection.appbase.data.local.dao.CheeseDao
import com.resurrection.appbase.data.model.cheese.Cheese
import com.resurrection.appbase.data.model.cheese.CheeseListItem
import com.resurrection.base.core.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.concurrent.Executors
import javax.inject.Inject


@HiltViewModel
class CheeseViewModel @Inject constructor(var cheeseDao: CheeseDao)
    :BaseViewModel() {

    init {
        ioThread {
            cheeseDao.insert(
                CHEESE_DATA.map { Cheese(id = 0, name = it) })
        }
    }

    val allCheeses: Flow<PagingData<CheeseListItem>> = Pager(
        config = PagingConfig(
            pageSize = 60,
            enablePlaceholders = true,
            maxSize = 200
        )
    ) {
        cheeseDao.allCheesesByName()
    }.flow
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
                    } else if (!before.name.first().equals(after.name.first(), ignoreCase = true)){
                        // Between two items that start with different letters.
                        CheeseListItem.Separator(after.name.first())
                    } else {
                        // Between two items that start with the same letter.
                        null
                    }
                }
        }
        .cachedIn(viewModelScope)


    fun insert(text: CharSequence) = ioThread {
        cheeseDao.insert(Cheese(id = 0, name = text.toString()))
    }

    fun remove(cheese: Cheese) = ioThread {
        cheeseDao.delete(cheese)
    }
}

private val IO_EXECUTOR = Executors.newSingleThreadExecutor()

/**
 * Utility method to run blocks on a dedicated background thread, used for io/database work.
 */
fun ioThread(f : () -> Unit) {
    IO_EXECUTOR.execute(f)
}