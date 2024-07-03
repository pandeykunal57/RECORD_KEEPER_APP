package com.example.recordkeeper.editrecord

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import com.example.recordkeeper.cycling.CyclingFragment
import com.example.recordkeeper.databinding.ActivityEditRecordBinding
import com.example.recordkeeper.running.RunningFragment
import java.io.Serializable

const val INTENT_EXTRA_SCREEN_DATA = "screen_data"

class EditRecordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditRecordBinding
    private val recordPreferences: SharedPreferences by lazy {
        getSharedPreferences(
            screenData.sharedPreferencesName,
            Context.MODE_PRIVATE
        )
    }
    private val screenData: ScreenData by lazy {
        intent.getSerializableExtra(INTENT_EXTRA_SCREEN_DATA) as ScreenData


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            ActivityEditRecordBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setupUI()

        displayRecord()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupUI() {
        title =
            "${screenData.record} Record"
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
            remove("${screenData.record} $SHARED_PREFERENCE_RECORD_KEY")
            remove("${screenData.record} $SHARED_PREFERENCE_DATE_KEY")
        }
    }

    private fun displayRecord() {
        val cyclingPreferences =
            getSharedPreferences(CyclingFragment.FILENAME, Context.MODE_PRIVATE)
        binding.editTextRecord.setText(
            cyclingPreferences.getString(
                "${screenData.record} $SHARED_PREFERENCE_RECORD_KEY",
                null
            )
        )
        binding.editTextDate.setText(
            cyclingPreferences.getString(
                "${screenData.record} $SHARED_PREFERENCE_DATE_KEY",
                null
            )
        )

        val runningPreferences =
            getSharedPreferences(RunningFragment.FILENAME, Context.MODE_PRIVATE)
        binding.editTextRecord.setText(
            runningPreferences.getString(
                "${screenData.record} $SHARED_PREFERENCE_RECORD_KEY",
                null
            )
        )
        binding.editTextDate.setText(
            runningPreferences.getString(
                "${screenData.record} $SHARED_PREFERENCE_DATE_KEY",
                null
            )
        )

    }

    private fun saveRecord() {
        val record = binding.editTextRecord.text.toString()
        val date = binding.editTextDate.text.toString()


        val runningPreferences =
            getSharedPreferences(RunningFragment.FILENAME, Context.MODE_PRIVATE)
        val cyclingPreferences =
            getSharedPreferences(CyclingFragment.FILENAME, Context.MODE_PRIVATE)




        runningPreferences.edit {
            putString(
                "${this@EditRecordActivity.screenData.record} $SHARED_PREFERENCE_RECORD_KEY",
                record
            )
            putString(
                "${this@EditRecordActivity.screenData.record} $SHARED_PREFERENCE_DATE_KEY",
                date
            )
        }
        cyclingPreferences.edit {
            putString(
                "${this@EditRecordActivity.screenData.record} $SHARED_PREFERENCE_RECORD_KEY",
                record
            )
            putString(
                "${this@EditRecordActivity.screenData.record} $SHARED_PREFERENCE_DATE_KEY",
                date
            )
        }

    }

    data class ScreenData(
        val record: String,
        val sharedPreferencesName: String,
        val recordFieldHint: String
    ) : Serializable

    companion object {
        const val SHARED_PREFERENCE_RECORD_KEY = "record"
        const val SHARED_PREFERENCE_DATE_KEY = "date"

    }

}
