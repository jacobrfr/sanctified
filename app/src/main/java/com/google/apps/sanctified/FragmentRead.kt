package com.google.apps.sanctified

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class FragmentRead : Fragment() {
    companion object {
        fun newInstance() : FragmentRead {
            val fragmentRead = FragmentRead()
            val args = Bundle()
            fragmentRead.arguments = args
            return fragmentRead
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View? {
        return inflater!!.inflate(R.layout.fragment_read, container, false)
    }
}