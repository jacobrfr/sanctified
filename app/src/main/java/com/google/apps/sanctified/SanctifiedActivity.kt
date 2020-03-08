package com.google.apps.sanctified

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.apps.sanctified.databinding.ActivitySanctifiedBinding

class SanctifiedActivity : AppCompatActivity() {
    private var content: FrameLayout? = null

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.prayer_navigation_menu_item -> {
                val fragment = FragmentPrayer.newInstance()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.praise_navigation_menu_item -> {
                val fragment = FragmentPraise.newInstance()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.read_navigation_menu_item -> {
                val fragment = FragmentRead.newInstance()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.plans_navigation_menu_item -> {
                val fragment = FragmentPlans.newInstance()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.memory_navigation_menu_item -> {
                val fragment = FragmentMemory.newInstance()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        return@OnNavigationItemSelectedListener false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView<ActivitySanctifiedBinding>(this, R.layout.activity_sanctified)

        content = findViewById(R.id.content)
        val navigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        navigation.selectedItemId = R.id.read_navigation_menu_item
        val fragment = FragmentRead.newInstance()
        addFragment(fragment)
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.nav_host, fragment, fragment.javaClass.simpleName)
                .commit()
    }
}