package com.resurrection.base.core.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.resurrection.base.component.*
import com.resurrection.base.component.SecurityManager
import javax.inject.Inject

open class CoreAdapter<T>(
    private val layoutResource: Int,
    private val itemId: Int?,
    private var currentList: ArrayList<T>? = null,
    private val onItemClick: ((T) -> Unit)? = null
) : RecyclerView.Adapter<BaseHolder<T>>() {

    @Inject
    lateinit var appState: AppState
    @Inject
    lateinit var dataHolder: DataHolderManager
    @Inject
    lateinit var sharedPreferences: SharedPreferencesManager
    @Inject
    lateinit var loggerManager: LoggerManager
    @Inject
    lateinit var loadingIndicator: AppLoadingIndicator
    @Inject
    lateinit var networkManager: NetworkManager
    @Inject
    lateinit var securityManager: SecurityManager
    @Inject
    lateinit var biometricManager: BiometricManager
    @Inject
    lateinit var typeConverter: TypeConverter

    open lateinit var binding: ViewDataBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<T> {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), layoutResource, parent, false)
        return BaseHolder(binding, itemId, onItemClick)
    }

    override fun onBindViewHolder(holder: BaseHolder<T>, position: Int) {
        currentList?.let { holder.bind(currentList!![position]) }
    }

    override fun getItemCount() = currentList?.let { currentList!!.size } ?: run { -1 }
}
