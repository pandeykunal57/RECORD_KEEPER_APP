package com.example.recordkeeper

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.recordkeeper.databinding.FragmentRunningBinding

class RunningFragment : Fragment() {

    private lateinit var binding: FragmentRunningBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentRunningBinding.inflate(inflater, container, false)
        //return inflater.inflate(R.layout.fragment_running, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupclicklistners()
    }
    // setting up new functions for all the click listners so that code is well organized and easy to debug

    private fun setupclicklistners() {
        binding.container5KM.setOnClickListener { launchRunningRecordScreen<Any>("5km") }
        binding.container10KM.setOnClickListener { launchRunningRecordScreen<Any>("10KM") }
        binding.containerHalfmarathon.setOnClickListener { launchRunningRecordScreen<Any>("Half Marathon") }
        binding.containerFullmarathon.setOnClickListener { launchRunningRecordScreen<Any>("Full Marathon") }


    }

    //method for launching the activity for all the on click listners

    private fun <T> launchRunningRecordScreen(distance: String) {

        /* val intent=Intent(this,EditRunningRecordActivity::class.java)
        this, will not work here because a FRAGMENT is not a context an ACTIVITY is
         */

        val intent = Intent(context, EditRunningRecordActivity::class.java)
        // we have context variable available from the parent activity class, can be nullable

        // IN ACTIVITIES WE CAN USE THIS BUT IN FRAGMENTS WE CAN NOT USE THIS
        intent.putExtra("Distance",distance)
        startActivity(intent)


    }

}





