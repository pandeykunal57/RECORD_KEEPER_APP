package com.example.recordkeeper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.recordkeeper.databinding.ActivityEditRunningRecordBinding
import com.example.recordkeeper.databinding.FragmentRunningBinding

class EditRunningRecordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditRunningRecordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityEditRunningRecordBinding.inflate(layoutInflater)
       // setContentView(R.layout.activity_edit_running_record)
        setContentView(binding.root)
       // title="NEW"     way to give a hardcoded title

        val distance=intent.getStringExtra("Distance")   // dynamic title is retrieved using this code
        title= "$distance Record"
    }
}