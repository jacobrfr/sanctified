package com.google.apps.sanctified

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.google.apps.sanctified.adapters.PrayerAdapter
import com.google.apps.sanctified.data.Prayer
import com.google.apps.sanctified.databinding.FragmentPrayerBinding
import com.google.apps.sanctified.utilities.InjectorUtils
import com.google.apps.sanctified.utilities.SwipeToDeleteCallback
import com.google.apps.sanctified.viewmodels.PrayerListViewModel
import kotlinx.android.synthetic.main.fragment_prayer.view.*

class FragmentPrayer : Fragment(), FragmentPrayerCreateDialog.PrayerCreateDialogListener{
    private val viewModel: PrayerListViewModel by viewModels {
        InjectorUtils.providePrayerListViewModelFactory(this)
    }
    private lateinit var adapter: PrayerAdapter

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

        adapter = PrayerAdapter()
        rv.adapter = adapter
        subscribeUi(adapter)

        ItemTouchHelper(object : SwipeToDeleteCallback(context!!) {
            override fun onSwiped(vH: RecyclerView.ViewHolder, d: Int) { deletePrayer(vH) }
        }).attachToRecyclerView(rv)

        return view
    }

    private fun deletePrayer(viewHolder: RecyclerView.ViewHolder) {
        val position = viewHolder.adapterPosition
        val prayer: Prayer = adapter.getPrayer(position)
        viewModel.deletePrayer(prayer)
        showUndoSnackbar(prayer)
    }

    private fun showUndoSnackbar(prayer: Prayer) {
        val view: View = requireActivity().findViewById(R.id.nav_host)
        val snackbar: Snackbar = Snackbar.make(
            view,
            R.string.undo_snackbar_text,
            7500
        )
        snackbar.setAction(R.string.undone_snackbar_text) { undoDelete(prayer) }
        snackbar.show()
    }

    private fun undoDelete(prayer: Prayer) {
        viewModel.addPrayer(prayer.subject, prayer.description)
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