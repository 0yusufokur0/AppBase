package com.resurrection.appbase.ui.photo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.resurrection.appbase.R
import com.resurrection.appbase.data.model.photos.PhotoModelItem
import com.resurrection.appbase.databinding.PhotoItemBinding

// Model
// ViewDataBinding
// layoutResource
// bind fun


class PhotoAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>()  {

    private val currentList = arrayListOf<PhotoModelItem>()
    private lateinit var binding:PhotoItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.photo_item,
            parent,
            false
        )

        return object :RecyclerView.ViewHolder(binding.root){ }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentItem = currentList.get(position)
        binding.photoTextView.text = currentItem.title
        binding.photoImageView.load(currentItem.thumbnailUrl)
    }

    override fun getItemCount(): Int = currentList.size

    fun setList(list:ArrayList<PhotoModelItem>){
        currentList.clear()
        currentList.addAll(list)
        notifyDataSetChanged()
    }


}