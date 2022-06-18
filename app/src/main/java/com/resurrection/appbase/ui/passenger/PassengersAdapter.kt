package com.resurrection.appbase.ui.passenger

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.resurrection.appbase.R
import com.resurrection.appbase.data.model.passenger.Passenger
import com.resurrection.appbase.databinding.ItemPassengerBinding
import com.resurrection.base.utils.loadImageWithRoundedCorner

class PassengersAdapter :
    PagingDataAdapter<Passenger, PassengersAdapter.PassengersViewHolder>(PassengersComparator) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PassengersViewHolder {
        return PassengersViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_passenger,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PassengersViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bindPassenger(it) }
    }

    inner class PassengersViewHolder(private val binding: ItemPassengerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bindPassenger(item: Passenger) = with(binding) {
            imageViewAirlinesLogo.loadImageWithRoundedCorner(item.airline[0].logo)
            textViewHeadquarters.text = item.airline[0].head_quaters
            textViewNameWithTrips.text =item.name + item.trips
        }
    }

    object PassengersComparator : DiffUtil.ItemCallback<Passenger>() {
        override fun areItemsTheSame(oldItem: Passenger, newItem: Passenger): Boolean {
            return oldItem._id == newItem._id
        }

        override fun areContentsTheSame(oldItem: Passenger, newItem: Passenger): Boolean {
            return oldItem == newItem
        }
    }
}