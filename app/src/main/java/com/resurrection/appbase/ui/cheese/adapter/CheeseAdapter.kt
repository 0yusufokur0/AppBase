/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.resurrection.appbase.ui.cheese.adapter

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.resurrection.appbase.R
import com.resurrection.appbase.data.model.cheese.Cheese
import com.resurrection.appbase.data.model.cheese.CheeseListItem

class CheeseAdapter :
    PagingDataAdapter<CheeseListItem, CheeseAdapter.CheeseViewHolder>(diffCallback) {
    override fun onBindViewHolder(holder: CheeseViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheeseViewHolder {
        return CheeseViewHolder(parent)
    }

    companion object {

        val diffCallback = object : DiffUtil.ItemCallback<CheeseListItem>() {
            override fun areItemsTheSame(
                oldItem: CheeseListItem,
                newItem: CheeseListItem
            ): Boolean {
                return if (oldItem is CheeseListItem.Item && newItem is CheeseListItem.Item) {
                    oldItem.cheese.id == newItem.cheese.id
                } else if (oldItem is CheeseListItem.Separator && newItem is CheeseListItem.Separator) {
                    oldItem.name == newItem.name
                } else {
                    oldItem == newItem
                }
            }

            override fun areContentsTheSame(
                oldItem: CheeseListItem,
                newItem: CheeseListItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    class CheeseViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.cheese_item, parent, false)
    ) {
        var cheese: Cheese? = null
            private set
        private val nameView = itemView.findViewById<TextView>(R.id.name)

        fun bindTo(item: CheeseListItem?) {
            if (item is CheeseListItem.Separator) {
                nameView.text = item.name
                nameView.setTypeface(null, Typeface.BOLD)
            } else {
                nameView.text = item?.name
                nameView.setTypeface(null, Typeface.NORMAL)
            }
            cheese = (item as? CheeseListItem.Item)?.cheese
            nameView.text = item?.name
        }
    }
}

