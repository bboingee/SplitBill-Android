package com.bignerdranch.android.splitbill

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PeopleActivity : AppCompatActivity() {

    private val peopleList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_people)

        val personNameEditText =
            findViewById<EditText>(R.id.person_name_edit_text)

        val contactEditText =
            findViewById<EditText>(R.id.contact_edit_text)

        val personAmountEditText =
            findViewById<EditText>(R.id.person_amount_edit_text)

        val addPersonButton =
            findViewById<Button>(R.id.add_person_button)

        val finishPeopleButton =
            findViewById<Button>(R.id.finish_people_button)

        val peopleListTextView =
            findViewById<TextView>(R.id.people_list_text_view)

        addPersonButton.setOnClickListener {

            val name =
                personNameEditText.text.toString()

            val contact =
                contactEditText.text.toString()

            val amount =
                personAmountEditText.text.toString()

            if (name.isEmpty()) {
                Toast.makeText(
                    this,
                    "Please enter a name.",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            if (amount.isEmpty()) {
                Toast.makeText(
                    this,
                    "Please enter an amount.",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            val personInfo =
                "$name | $contact | $$amount"

            peopleList.add(personInfo)

            peopleListTextView.text =
                peopleList.joinToString("\n")

            Toast.makeText(
                this,
                "$name added.",
                Toast.LENGTH_SHORT
            ).show()

            personNameEditText.text.clear()
            contactEditText.text.clear()
            personAmountEditText.text.clear()
        }

        finishPeopleButton.setOnClickListener {

            if (peopleList.isEmpty()) {
                Toast.makeText(
                    this,
                    "Please add at least one person.",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

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

            val resultIntent =
                Intent(this, ResultActivity::class.java)

            resultIntent.putExtra(
                "restaurantName",
                restaurantName
            )

            resultIntent.putExtra(
                "billTotal",
                billTotal
            )

            resultIntent.putExtra(
                "tax",
                tax
            )

            resultIntent.putExtra(
                "tipPercent",
                tipPercent
            )

            resultIntent.putExtra(
                "tipAmount",
                tipAmount
            )

            resultIntent.putExtra(
                "finalTotal",
                finalTotal
            )

            resultIntent.putExtra(
                "numberOfPeople",
                numberOfPeople
            )

            resultIntent.putExtra(
                "amountPerPerson",
                amountPerPerson
            )

            resultIntent.putStringArrayListExtra(
                "peopleList",
                ArrayList(peopleList)
            )

            startActivity(resultIntent)
        }
    }
}