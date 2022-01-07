package com.resurrection.basesample.base.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import com.resurrection.basesample.R
import com.resurrection.basesample.base.core.BaseAdapter

class AppVerticalRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.recyclerViewStyle
) : RecyclerView(context, attrs, defStyleAttr){

    init {
        android.R.layout.simple_list_item_1


    }
}