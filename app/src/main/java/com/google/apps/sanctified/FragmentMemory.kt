package com.google.apps.sanctified

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class FragmentMemory : Fragment() {
    companion object {
        fun newInstance() : FragmentMemory {
            val fragmentMemory = FragmentMemory()
            val args = Bundle()
            fragmentMemory.arguments = args
            return fragmentMemory
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View? {
        return inflater!!.inflate(R.layout.fragment_memory, container, false)
    }
}