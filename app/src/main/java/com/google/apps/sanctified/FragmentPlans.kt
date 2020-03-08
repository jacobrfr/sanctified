package com.google.apps.sanctified

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class FragmentPlans : Fragment() {
    companion object {
        fun newInstance() : FragmentPlans {
            val fragmentPlans = FragmentPlans()
            val args = Bundle()
            fragmentPlans.arguments = args
            return fragmentPlans
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View? {
        return inflater!!.inflate(R.layout.fragment_plans, container, false)
    }
}