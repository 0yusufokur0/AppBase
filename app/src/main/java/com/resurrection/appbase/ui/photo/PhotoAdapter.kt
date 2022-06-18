package com.resurrection.appbase.ui.photo

import com.resurrection.appbase.BR
import com.resurrection.appbase.R
import com.resurrection.appbase.data.model.photos.PhotoModelItem
import com.resurrection.appbase.databinding.PhotoItemBinding
import com.resurrection.base.core.adapter.BaseAdapter

class PhotoAdapter :
    BaseAdapter<PhotoModelItem, PhotoItemBinding>(R.layout.photo_item, BR.photoItem) {
    override fun bindItem(binding: PhotoItemBinding, item: PhotoModelItem) {}
}