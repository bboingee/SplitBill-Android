package com.bignerdranch.android.splitbill

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.bignerdranch.android.splitbill.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.splitBillButton.setOnClickListener {
            val intent = Intent(this, BillActivity::class.java)
            startActivity(intent)
        }

        binding.previousBillsButton.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Under Development")
                .setMessage("Previous Bills is currently under development.")
                .setPositiveButton("OK", null)
                .show()
        }
    }
}