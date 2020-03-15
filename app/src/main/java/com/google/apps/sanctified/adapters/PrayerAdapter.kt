package com.google.apps.sanctified.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.google.apps.sanctified.data.Prayer
import com.google.apps.sanctified.databinding.PrayerRecyclerViewItemBinding
import kotlinx.android.synthetic.main.prayer_recycler_view_item.view.*


class PrayerAdapter : ListAdapter<Prayer, ViewHolder>(PlantDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder {
        return PrayerViewHolder(PrayerRecyclerViewItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val prayer = getItem(position)
        (holder as PrayerViewHolder).bind(prayer)
    }

    fun getPrayer(position: Int) : Prayer {
        return getItem(position)
    }

    class PrayerViewHolder(
        private val binding: PrayerRecyclerViewItemBinding
    ) : ViewHolder(binding.root) {
        fun bind(prayer: Prayer) {
            binding.apply {
                root.prayer_item_subject.text = prayer.subject
                root.prayer_item_date.text = prayer.date
                root.prayer_item_description.text = prayer.description
                executePendingBindings()
            }
        }
    }
}

private class PlantDiffCallback : DiffUtil.ItemCallback<Prayer>() {
    override fun areItemsTheSame(oldItem: Prayer, newItem: Prayer): Boolean {
        return oldItem.prayerId == newItem.prayerId
    }

    override fun areContentsTheSame(oldItem: Prayer, newItem: Prayer): Boolean {
        return oldItem == newItem
    }
}