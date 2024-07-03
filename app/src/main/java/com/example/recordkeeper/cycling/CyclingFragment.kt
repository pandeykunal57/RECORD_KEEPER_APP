package com.example.recordkeeper.cycling

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.recordkeeper.databinding.FragmentCyclingBinding
import com.example.recordkeeper.editrecord.EditRecordActivity

class CyclingFragment : Fragment() {
    private lateinit var binding: FragmentCyclingBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCyclingBinding.inflate(inflater, container, false)
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

    private fun displayRecords() {
        val cyclingPreferences =
            requireContext().getSharedPreferences("cycling", Context.MODE_PRIVATE)

        binding.textViewLongestRideValue.text =
            cyclingPreferences.getString("Longest Ride record", null)
        binding.textViewLongestRideValue2.text =
            cyclingPreferences.getString("Longest Ride date", null)
        binding.textViewBiggestClimbValue.text =
            cyclingPreferences.getString("Biggest Climb record", null)
        binding.textViewBiggestClimbValue2.text =
            cyclingPreferences.getString("Biggest Climb date", null)
        binding.textViewBestSpeedValue.text =
            cyclingPreferences.getString("Best Speed record", null)
        binding.textViewBestSpeedValue2.text = cyclingPreferences.getString("Best Speed date", null)
    }

    private fun setupclicklistners() {
        binding.longestRide.setOnClickListener {
            launchEditCyclingRecordScreen(
                "Longest Ride",
                "Distance"
            )
        }
        binding.containerBiggestClimb.setOnClickListener {
            launchEditCyclingRecordScreen(
                "Biggest Climb",
                "Height"
            )
        }
        binding.containerBestspeed.setOnClickListener {
            launchEditCyclingRecordScreen(
                "Best Speed",
                "Average Speed"
            )
        }
    }


    private fun launchEditCyclingRecordScreen(record: String, recordFieldHint: String) {
        val intent = Intent(context, EditRecordActivity::class.java)
        intent.putExtra(
            "screen_data",
            EditRecordActivity.ScreenData(record, "cycling", recordFieldHint)
        )
        startActivity(intent)
    }
}