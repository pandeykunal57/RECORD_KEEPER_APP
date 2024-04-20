package com.example.recordkeeper

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.example.recordkeeper.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener


class MainActivity : AppCompatActivity(), View.OnClickListener,
    OnItemSelectedListener {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.bottomNav.setOnItemSelectedListener(this)


        /* ALTERNATIVE
        binding.bottomNav.setOnNavigationItemSelectedListener(object:BottomNavigationView.OnNavigationItemSelectedListener{
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                TODO("Not yet implemented")
            }

        }) */


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {   // method that runs automatically whne app is launched
        menuInflater.inflate(R.menu.tool_bar, menu)
        // like layout inflator , takes 2 parameters , menu created by us and menu variable automatically given to us by the inflator
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean { // to respond to menu items clicked on app
       return when (item.itemId) {
            R.id.reset_running -> {
                Toast.makeText(this, "CLICKED THE RESET RUNNING", Toast.LENGTH_SHORT).show()
                true
            }

            R.id.reset_cycling -> {
                Toast.makeText(this, "CLICKED THE RESET CYCLING", Toast.LENGTH_SHORT).show()
                true
            }

            R.id.reset_all -> {
                Toast.makeText(this, "CLICKED THE RESET ALL", Toast.LENGTH_SHORT).show()
                true
            }

            else -> {
                false
            }
        }

        return super.onOptionsItemSelected(item)
    }


    private fun onCyclingclicked(): Boolean {
        supportFragmentManager.commit {
            replace(R.id.frame_content, CyclingFragment())
        }
        return true
    }

    private fun onRunningclicked(): Boolean {
        supportFragmentManager.commit {
            replace(R.id.frame_content, RunningFragment())
            // true is returned that yes highlight the button in navigation pane when it si selected
        }
        return true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean =
        /* if (item.itemId == R.id.nav_cycling) {
             onCyclingclicked()
             return true
         } else if (item.itemId == R.id.nav_running) {
             onRunningclicked()
             return true // true is returned that yes highlight the button in navigation pane when it si selected
         } else {
             return false
         }*/


        when (item.itemId) {
            R.id.nav_cycling -> onCyclingclicked()
            R.id.nav_running -> onRunningclicked()
            else -> false

        }

    override fun onClick(p0: View?) {

    }

}
