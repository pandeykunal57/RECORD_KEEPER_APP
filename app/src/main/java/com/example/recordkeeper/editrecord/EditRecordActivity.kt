package com.example.recordkeeper.editrecord

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.edit
import com.example.recordkeeper.databinding.ActivityEditRecordBinding
import java.io.Serializable

class EditRecordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditRecordBinding
    private val recordPreferences: SharedPreferences by lazy {
        getSharedPreferences(
            screenData.sharedPreferencesName,
            Context.MODE_PRIVATE
        )
    }
    private val screenData: ScreenData by lazy {
        intent.getSerializableExtra("screen_data") as ScreenData


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            ActivityEditRecordBinding.inflate(layoutInflater)   // setContentView(R.layout.activity_edit_running_record)

        setContentView(binding.root)

        setupUI()

        displayRecord()

    }

    private fun setupUI() {
        title =
            "${screenData.record} Record"    // dynamic title is retrieved using this code   // title="NEW"     way to give a hardcoded title
        binding.textInputRecord.hint = screenData.recordFieldHint
        binding.buttonSave.setOnClickListener {
            saveRecord()
            finish()
        }
        binding.buttonDelete.setOnClickListener {
            clearRecord()
            finish()
        }
    }

    private fun clearRecord() {
        recordPreferences.edit {
            remove("${screenData.record} record")
            remove("${screenData.record} date")
        }
    }

    private fun displayRecord() {
        val cyclingPreferences = getSharedPreferences("cycling", Context.MODE_PRIVATE)
        binding.editTextRecord.setText(
            cyclingPreferences.getString(
                "${screenData.record} record",
                null
            )
        )
        binding.editTextDate.setText(
            cyclingPreferences.getString(
                "${screenData.record} date",
                null
            )
        )

        val runningPreferences = getSharedPreferences("running", Context.MODE_PRIVATE)
        binding.editTextRecord.setText(
            runningPreferences.getString(
                "${screenData.record} record",
                null
            )
        )
        binding.editTextDate.setText(
            runningPreferences.getString(
                "${screenData.record} date",
                null
            )
        )

    }

    private fun saveRecord() {
        val record = binding.editTextRecord.text.toString()
        val date = binding.editTextDate.text.toString()


        val runningPreferences = getSharedPreferences("running", Context.MODE_PRIVATE)
        val cyclingPreferences = getSharedPreferences("cycling", Context.MODE_PRIVATE)


//        val editor=runningPreferences.edit()          OLD WAY
//        editor.putString("record",record)
//        editor.putString("date",date)
//        editor.apply()

        runningPreferences.edit {
            putString("${this@EditRecordActivity.screenData.record} record", record)
            putString("${this@EditRecordActivity.screenData.record} date", date)
        }
        cyclingPreferences.edit {
            putString("${this@EditRecordActivity.screenData.record} record", record)
            putString("${this@EditRecordActivity.screenData.record} date", date)
        }

    }

    data class ScreenData(
        val record: String,
        val sharedPreferencesName: String,
        val recordFieldHint: String
    ) : Serializable

}
