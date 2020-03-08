package com.google.apps.sanctified

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class FragmentPraise : Fragment() {
    companion object {
        fun newInstance() : FragmentPraise {
            val fragmentPraise = FragmentPraise()
            val args = Bundle()
            fragmentPraise.arguments = args
            return fragmentPraise
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View? {
        return inflater!!.inflate(R.layout.fragment_praise, container, false)
    }
}