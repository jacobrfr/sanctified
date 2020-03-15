package com.google.apps.sanctified

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.apps.sanctified.adapters.PrayerAdapter
import com.google.apps.sanctified.databinding.FragmentPrayerBinding
import com.google.apps.sanctified.utilities.InjectorUtils
import com.google.apps.sanctified.utilities.SwipeToDeleteCallback
import com.google.apps.sanctified.viewmodels.PrayerListViewModel
import kotlinx.android.synthetic.main.fragment_prayer.view.*
import kotlinx.android.synthetic.main.prayer_create_dialog.*

class FragmentPrayer : Fragment(), FragmentPrayerCreateDialog.PrayerCreateDialogListener{
    private val viewModel: PrayerListViewModel by viewModels {
        InjectorUtils.providePrayerListViewModelFactory(this)
    }

    companion object {
        fun newInstance() : FragmentPrayer {
            val fragmentPrayer = FragmentPrayer()
            val args = Bundle()
            fragmentPrayer.arguments = args
            return fragmentPrayer
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ) : View? {
        val binding = FragmentPrayerBinding.inflate(inflater, container, false)
        val view = binding.root

        view.prayer_fab.setOnClickListener { showPrayerCreateDialog() }
        view.prayer_recycler_view.layoutManager = LinearLayoutManager(
                context, LinearLayoutManager.VERTICAL, false)
        view.prayer_recycler_view.adapter = PrayerAdapter()

        context ?: return view

        val rv = binding.prayerRecyclerView

        val adapter = PrayerAdapter()
        rv.adapter = adapter
        subscribeUi(adapter)

        ItemTouchHelper(object : SwipeToDeleteCallback(requireContext()) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                viewModel.deletePrayer(adapter.getPrayer(viewHolder.adapterPosition))
            }
        }).attachToRecyclerView(rv)

        return view
    }

    private fun subscribeUi(adapter: PrayerAdapter) {
        viewModel.prayers.observe(viewLifecycleOwner) { prayers ->
            adapter.submitList(prayers)
        }
    }

    private fun showPrayerCreateDialog() {
        FragmentPrayerCreateDialog().show(childFragmentManager, "FragmentPrayerCreateDialog")
    }

    override fun onDialogPositiveClick(subject: String, description: String) {
        viewModel.addPrayer(subject, description)
    }

    override fun onDialogNegativeClick(dialog: DialogFragment) {

    }
}