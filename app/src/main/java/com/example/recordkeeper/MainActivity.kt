package com.example.recordkeeper

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.fragment.app.commit
import com.example.recordkeeper.cycling.CyclingFragment
import com.example.recordkeeper.databinding.ActivityMainBinding
import com.example.recordkeeper.running.RunningFragment
import com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener
import com.google.android.material.snackbar.Snackbar


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

                showConfirmationDialog(RUNNING_DISPLAY_VALUE)


                        true

            }

            R.id.reset_cycling -> {
                showConfirmationDialog(CYCLING_DISPLAY_VALUE)

                true
            }

            R.id.reset_all -> {
                showConfirmationDialog(ALL_DISPLAY_VALUE)


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


                    ALL_DISPLAY_VALUE ->{
                        getSharedPreferences(RunningFragment.FILENAME, MODE_PRIVATE).edit { clear() }
                        getSharedPreferences(CyclingFragment.FILENAME, MODE_PRIVATE).edit { clear() }}

                    RUNNING_DISPLAY_VALUE->{
                        getSharedPreferences(RunningFragment.FILENAME, MODE_PRIVATE).edit { clear() }
                    }
                    CYCLING_DISPLAY_VALUE ->{
                        getSharedPreferences(CyclingFragment.FILENAME, MODE_PRIVATE).edit { clear() }
                    }

                    else ->{ }
                    
                }
                refreshCurrentFragment()
                SnackbarConfirmation ()


            }
            .setNegativeButton("NO", null)
            .show()
    }

    private fun SnackbarConfirmation() {
        val snackbar = Snackbar.make(
            binding.frameContent,
            "RECORDS CLEARED SUCESSFULLY !",
            Snackbar.LENGTH_SHORT
        )
        snackbar.anchorView = binding.bottomNav
        snackbar.setAction("UNDO") {}
        snackbar.show()
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
    companion object{
        const val RUNNING_DISPLAY_VALUE="running"
        const val CYCLING_DISPLAY_VALUE="cycling"
        const val ALL_DISPLAY_VALUE="all"

    }

}
