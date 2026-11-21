package com.bignerdranch.android.splitbill

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bignerdranch.android.splitbill.databinding.ActivityBillBinding

class BillActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBillBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBillBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Hide tip choices until the user selects
        // whether gratuity is already included.
        binding.tipLabelTextView.visibility = View.GONE
        binding.tipRadioGroup.visibility = View.GONE

        binding.gratuityYesRadioButton.setOnClickListener {

            binding.tipLabelTextView.text =
                "Gratuity is already included. Extra tip is optional."

            binding.tipLabelTextView.visibility = View.VISIBLE
            binding.tipRadioGroup.visibility = View.VISIBLE
        }

        binding.gratuityNoRadioButton.setOnClickListener {

            binding.tipLabelTextView.text =
                "Would you like to leave a tip?"

            binding.tipLabelTextView.visibility = View.VISIBLE
            binding.tipRadioGroup.visibility = View.VISIBLE
        }

        binding.continueButton.setOnClickListener {
            checkBillInformation()
        }
    }

    private fun checkBillInformation() {

        val restaurantName =
            binding.restaurantNameEditText.text.toString()

        val billTotalText =
            binding.billTotalEditText.text.toString()

        val taxText =
            binding.taxEditText.text.toString()

        val numberOfPeopleText =
            binding.numberOfPeopleEditText.text.toString()

        // Check restaurant name
        if (restaurantName.isEmpty()) {

            Toast.makeText(
                this,
                "Please enter the restaurant name.",
                Toast.LENGTH_SHORT
            ).show()

            return
        }

        // Check bill total
        if (billTotalText.isEmpty()) {

            Toast.makeText(
                this,
                "Please enter the bill total.",
                Toast.LENGTH_SHORT
            ).show()

            return
        }

        // Check number of people
        if (numberOfPeopleText.isEmpty()) {

            Toast.makeText(
                this,
                "Please enter the number of people.",
                Toast.LENGTH_SHORT
            ).show()

            return
        }

        // Check gratuity selection
        if (binding.gratuityRadioGroup.checkedRadioButtonId == -1) {

            Toast.makeText(
                this,
                "Please select Yes or No for gratuity.",
                Toast.LENGTH_SHORT
            ).show()

            return
        }

        // Check tip selection
        if (binding.tipRadioGroup.checkedRadioButtonId == -1) {

            Toast.makeText(
                this,
                "Please select a tip option.",
                Toast.LENGTH_SHORT
            ).show()

            return
        }

        // Convert the entered values into numbers
        val billTotal = billTotalText.toDouble()

        val tax = if (taxText.isEmpty()) {
            0.0
        } else {
            taxText.toDouble()
        }

        val numberOfPeople = numberOfPeopleText.toInt()

        // Prevent dividing by zero
        if (numberOfPeople <= 0) {

            Toast.makeText(
                this,
                "Number of people must be at least 1.",
                Toast.LENGTH_SHORT
            ).show()

            return
        }

        // Determine selected tip
        var tipPercent = 0

        when (binding.tipRadioGroup.checkedRadioButtonId) {

            R.id.no_tip_radio_button -> {
                tipPercent = 0
            }

            R.id.tip_15_radio_button -> {
                tipPercent = 15
            }

            R.id.tip_18_radio_button -> {
                tipPercent = 18
            }

            R.id.tip_20_radio_button -> {
                tipPercent = 20
            }

            R.id.tip_25_radio_button -> {
                tipPercent = 25
            }
        }

        // Calculate tip based on bill amount
        val tipAmount =
            billTotal * tipPercent / 100

        // Add bill + tax + tip
        val finalTotal =
            billTotal + tax + tipAmount

        // Split final total between everyone
        val amountPerPerson =
            finalTotal / numberOfPeople

        // Open result screen
        val intent =
            Intent(this, PeopleActivity::class.java)

        intent.putExtra("restaurantName", restaurantName)
        intent.putExtra("billTotal", billTotal)
        intent.putExtra("tax", tax)
        intent.putExtra("tipPercent", tipPercent)
        intent.putExtra("tipAmount", tipAmount)
        intent.putExtra("finalTotal", finalTotal)
        intent.putExtra("numberOfPeople", numberOfPeople)
        intent.putExtra("amountPerPerson", amountPerPerson)

        startActivity(intent)
    }
}