package com.google.apps.sanctified.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.google.apps.sanctified.R
import com.google.apps.sanctified.data.Prayer
import com.google.apps.sanctified.databinding.PrayerRecyclerViewItemBinding
import kotlinx.android.synthetic.main.fragment_prayer.view.*
import kotlinx.android.synthetic.main.prayer_recycler_view_item.view.*


class PrayerAdapter(private val itemClickListener: OnItemClickListener)
    : ListAdapter<Prayer, ViewHolder>(PrayerDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder {
        return PrayerViewHolder(PrayerRecyclerViewItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val prayer = getItem(position)
        (holder as PrayerViewHolder).bind(prayer, itemClickListener)
    }

    fun getPrayer(position: Int) : Prayer {
        return getItem(position)
    }

    class PrayerViewHolder(
        private val binding: PrayerRecyclerViewItemBinding
    ) : ViewHolder(binding.root) {
        fun bind(prayer: Prayer, clickListener: OnItemClickListener) {
            binding.apply {
                root.prayer_item_subject.text = prayer.subject
                root.prayer_item_date.text = prayer.date
                root.prayer_item_description.text = prayer.description
                val prayerOptions = root.prayer_options
                prayerOptions.setOnClickListener { clickListener.onItemClicked(prayerOptions) }
                executePendingBindings()
            }
        }
    }
}

private class PrayerDiffCallback : DiffUtil.ItemCallback<Prayer>() {
    override fun areItemsTheSame(oldItem: Prayer, newItem: Prayer): Boolean {
        return oldItem.prayerId == newItem.prayerId
    }

    override fun areContentsTheSame(oldItem: Prayer, newItem: Prayer): Boolean {
        return oldItem == newItem
    }
}

interface OnItemClickListener {
    fun onItemClicked(view: View)
}