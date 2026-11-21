package com.bignerdranch.android.splitbill

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_summary)

        val restaurantResultTextView =
            findViewById<TextView>(R.id.restaurant_result_text_view)

        val billTotalResultTextView =
            findViewById<TextView>(R.id.bill_total_result_text_view)

        val taxResultTextView =
            findViewById<TextView>(R.id.tax_result_text_view)

        val tipResultTextView =
            findViewById<TextView>(R.id.tip_result_text_view)

        val finalTotalResultTextView =
            findViewById<TextView>(R.id.final_total_result_text_view)

        val peopleResultTextView =
            findViewById<TextView>(R.id.people_result_text_view)

        val perPersonResultTextView =
            findViewById<TextView>(R.id.per_person_result_text_view)

        val peopleListResultTextView =
            findViewById<TextView>(R.id.people_list_result_text_view)

        val sendButton =
            findViewById<Button>(R.id.send_button)

        val restaurantName =
            intent.getStringExtra("restaurantName") ?: ""

        val billTotal =
            intent.getDoubleExtra("billTotal", 0.0)

        val tax =
            intent.getDoubleExtra("tax", 0.0)

        val tipPercent =
            intent.getIntExtra("tipPercent", 0)

        val tipAmount =
            intent.getDoubleExtra("tipAmount", 0.0)

        val finalTotal =
            intent.getDoubleExtra("finalTotal", 0.0)

        val numberOfPeople =
            intent.getIntExtra("numberOfPeople", 1)

        val amountPerPerson =
            intent.getDoubleExtra("amountPerPerson", 0.0)

        val peopleList =
            intent.getStringArrayListExtra("peopleList")
                ?: arrayListOf()

        restaurantResultTextView.text =
            "Restaurant: $restaurantName"

        billTotalResultTextView.text =
            "Bill Total: $%.2f".format(billTotal)

        taxResultTextView.text =
            "Tax: $%.2f".format(tax)

        tipResultTextView.text =
            "Tip: %d%% ($%.2f)".format(
                tipPercent,
                tipAmount
            )

        finalTotalResultTextView.text =
            "Final Total: $%.2f".format(finalTotal)

        peopleResultTextView.text =
            "Number of People: $numberOfPeople"

        perPersonResultTextView.text =
            "Each Person Owes: $%.2f".format(
                amountPerPerson
            )

        if (peopleList.isEmpty()) {
            peopleListResultTextView.text =
                "No people added."
        } else {
            peopleListResultTextView.text =
                peopleList.joinToString("\n")
        }

        sendButton.setOnClickListener {

            AlertDialog.Builder(this)
                .setTitle("Under Development")
                .setMessage(
                    "The send feature is currently under development."
                )
                .setPositiveButton("OK", null)
                .show()
        }
    }
}