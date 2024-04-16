package com.example.recordkeeper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.fragment.app.commit
import com.example.recordkeeper.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

@Suppress("DEPRECATION")
 class MainActivity : AppCompatActivity(), View.OnClickListener,
    BottomNavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.bottomNav.setOnNavigationItemSelectedListener(this)


        /* ALTERNATIVE
        binding.bottomNav.setOnNavigationItemSelectedListener(object:BottomNavigationView.OnNavigationItemSelectedListener{
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                TODO("Not yet implemented")
            }

        }) */


    }


    private fun onCyclingclicked() {
        supportFragmentManager.commit {
            replace(R.id.frame_content, CyclingFragment())
        }
    }
        private fun onRunningclicked() {
            supportFragmentManager.commit {
                replace(R.id.frame_content, RunningFragment())

            }

        }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        if (item.itemId==R.id.nav_cycling) {
            onCyclingclicked()
            return true
        }
        else if (item.itemId==R.id.nav_running){
            onRunningclicked()
            return true // true is returned that yes highlight the button in navigation pane when it si selected
            } else {
                return false
        }
        }

    override fun onClick(p0: View?) {

    }

}
