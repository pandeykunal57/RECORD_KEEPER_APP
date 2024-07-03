package com.example.recordkeeper.running

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.recordkeeper.databinding.FragmentRunningBinding
import com.example.recordkeeper.editrecord.EditRecordActivity
import com.example.recordkeeper.editrecord.INTENT_EXTRA_SCREEN_DATA

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

    override fun onResume() {
        super.onResume()
        displayRecords()
    }

    // setting up new functions for all the click listners so that code is well organized and easy to debug

    private fun setupclicklistners() {
        binding.container5KM.setOnClickListener { launchRunningRecordScreen<Any>("5km") }
        binding.container10KM.setOnClickListener { launchRunningRecordScreen<Any>("10KM") }
        binding.containerHalfmarathon.setOnClickListener { launchRunningRecordScreen<Any>("Half Marathon") }
        binding.containerFullmarathon.setOnClickListener { launchRunningRecordScreen<Any>("Full Marathon") }


    }

    private fun displayRecords() {
        val runningPreferences =
            requireContext().getSharedPreferences(FILENAME, Context.MODE_PRIVATE)
        binding.textView5kmValue.text = runningPreferences.getString("5km ${EditRecordActivity.SHARED_PREFERENCE_RECORD_KEY}", null)
        binding.textView5kmValue2.text = runningPreferences.getString("5km ${EditRecordActivity.SHARED_PREFERENCE_DATE_KEY}", null)
        binding.textView10kmValue.text = runningPreferences.getString("10KM ${EditRecordActivity.SHARED_PREFERENCE_RECORD_KEY}", null)
        binding.textView10kmValue2.text = runningPreferences.getString("10KM ${EditRecordActivity.SHARED_PREFERENCE_DATE_KEY}", null)
        binding.textViewHalfmarathonValue.text = runningPreferences.getString("Half Marathon ${EditRecordActivity.SHARED_PREFERENCE_RECORD_KEY}", null)
        binding.textViewHalfmarathonValue2.text = runningPreferences.getString("Half Marathon ${EditRecordActivity.SHARED_PREFERENCE_DATE_KEY}", null)
        binding.textViewFullmarathonValue.text = runningPreferences.getString("Full Marathon ${EditRecordActivity.SHARED_PREFERENCE_RECORD_KEY}", null)
        binding.textViewFullmarathonValue2.text = runningPreferences.getString("Full Marathon ${EditRecordActivity.SHARED_PREFERENCE_DATE_KEY}", null)


    }

    //method for launching the activity for all the on click listners

    private fun <T> launchRunningRecordScreen(distance: String) {

        /* val intent=Intent(this,EditRunningRecordActivity::class.java)
        this, will not work here because a FRAGMENT is not a context an ACTIVITY is
         */

        val intent = Intent(context, EditRecordActivity::class.java)
        // we have context variable available from the parent activity class, can be nullable

        // IN ACTIVITIES WE CAN USE THIS BUT IN FRAGMENTS WE CAN NOT USE THIS

        intent.putExtra(INTENT_EXTRA_SCREEN_DATA, EditRecordActivity.ScreenData(distance, RunningFragment.FILENAME, "Time"))
        startActivity(intent)


    } companion object {
        const val  FILENAME="running"

    }

}







