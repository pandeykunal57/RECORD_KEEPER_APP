package com.example.recordkeeper

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.recordkeeper.databinding.ActivityEditRunningRecordBinding
import com.example.recordkeeper.databinding.FragmentCyclingBinding

class CyclingFragment:Fragment() {
    private lateinit var binding: FragmentCyclingBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentCyclingBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupclicklistners()
    }

    private fun setupclicklistners() {
        binding.longestRide.setOnClickListener { launchEditCyclingRecordScreen("Longest Ride")}
        binding.containerBiggestClimb.setOnClickListener { launchEditCyclingRecordScreen("Biggest Climb")}
        binding.containerBestspeed.setOnClickListener { launchEditCyclingRecordScreen("Longest Ride")}
    }

    private fun launchEditCyclingRecordScreen(record: String) {
        val intent=Intent(context,EditCyclingRecordActivity::class.java)
        intent.putExtra("Record",record)
        startActivity(intent)
    }
}