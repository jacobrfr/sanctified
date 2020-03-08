package com.google.apps.sanctified

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class FragmentPrayer : Fragment() {
    companion object {
        fun newInstance() : FragmentPrayer {
            val fragmentPrayer = FragmentPrayer()
            val args = Bundle()
            fragmentPrayer.arguments = args
            return fragmentPrayer
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View? {
        return inflater!!.inflate(R.layout.fragment_prayer, container, false)
    }
}