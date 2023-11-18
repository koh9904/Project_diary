package com.example.object_orientedprogramming

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bnv = findViewById<BottomNavigationView>(R.id.bnv)
        supportFragmentManager.beginTransaction().add(R.id.fl, MainFragment()).commit()

        bnv.setOnItemSelectedListener {
            replaceFragment(
                when (it.itemId) {
                    R.id.tab1 -> MainFragment()
                    R.id.tab2 -> DiaryFragment()
                    R.id.tab3 -> FriendsFragment()
                    else -> SettingFragment()
                }
            )
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fl, fragment).commit()
    }
}