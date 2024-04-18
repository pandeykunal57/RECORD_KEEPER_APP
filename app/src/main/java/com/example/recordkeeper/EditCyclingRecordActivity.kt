package com.example.recordkeeper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.recordkeeper.databinding.ActivityEditCyclingRecordBinding
import com.example.recordkeeper.databinding.FragmentCyclingBinding

class EditCyclingRecordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditCyclingRecordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityEditCyclingRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val record=intent.getStringExtra("Record")
        title="$record Record"
    }
}