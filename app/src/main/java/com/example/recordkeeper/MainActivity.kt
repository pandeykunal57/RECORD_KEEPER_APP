package com.example.recordkeeper

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.fragment.app.commit
import com.example.recordkeeper.cycling.CyclingFragment
import com.example.recordkeeper.databinding.ActivityMainBinding
import com.example.recordkeeper.running.RunningFragment
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
       val menuclickedhandle = when (item.itemId) {
            R.id.reset_running -> {

                showConfirmationDialog("running")


                        true

            }

            R.id.reset_cycling -> {
                showConfirmationDialog("cycling")

                true
            }

            R.id.reset_all -> {
                showConfirmationDialog("all")


                true
            }

            else -> {
                false
            }
        }

  

        return super.onOptionsItemSelected(item)
    }

    private fun showConfirmationDialog(selection:String?) {
        AlertDialog.Builder(this)
            .setTitle("Reset $selection Records")
            .setMessage("Are you sure to clear Records?")
            .setPositiveButton("YES") { _, _ ->
                when(selection){


                    "all" ->{
                        getSharedPreferences("running", MODE_PRIVATE).edit { clear() }
                        getSharedPreferences("cycling", MODE_PRIVATE).edit { clear() }}
                    else ->{
                        getSharedPreferences(selection, MODE_PRIVATE).edit { clear() }

                    }
                    
                }
                refreshCurrentFragment()

            }
            .setNegativeButton("NO", null)
            .show()
    }

    private fun refreshCurrentFragment() {
        when (binding.bottomNav.selectedItemId) {
            R.id.nav_running -> {
                onRunningclicked()
            }

            R.id.nav_cycling -> {
                onCyclingclicked()
            }
        }
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
