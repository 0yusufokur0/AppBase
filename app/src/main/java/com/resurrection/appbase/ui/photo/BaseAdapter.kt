package com.resurrection.appbase.ui.photo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class BaseAdapter<Model,VDB:ViewDataBinding>(val layoutResource:Int): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private val currentList = arrayListOf<Model>()
    private lateinit var binding: VDB

    private var bindViewFunction :(VDB,Model) -> Unit = { viewDataBinding,model->  }

    fun setViewBindFun(bind:(VDB,Model) -> Unit){
        bindViewFunction = bind
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            layoutResource,
            parent,
            false
        )

        return object :RecyclerView.ViewHolder(binding.root){ }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentItem = currentList.get(position)
        bindViewFunction.invoke(binding,currentItem)
    }

    override fun getItemCount(): Int = currentList.size

    fun setList(list:ArrayList<Model>){
        currentList.clear()
        currentList.addAll(list)
        notifyDataSetChanged()
    }

}