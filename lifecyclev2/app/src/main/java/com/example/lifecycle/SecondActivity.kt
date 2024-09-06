package com.example.lifecycle

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class SecondActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore
    private lateinit var buttonReturn: Button
    private lateinit var dateOfBirthCal: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        db = FirebaseFirestore.getInstance()

        val nameInput: EditText = findViewById(R.id.nameInput)
        val phoneNumberInput: EditText = findViewById(R.id.phoneNumber)
        val driverLicenseCheck: CheckBox = findViewById(R.id.driversLicenseCheck)
        val maleRadio: RadioButton = findViewById(R.id.maleRadio)
        dateOfBirthCal = findViewById(R.id.dateOfBirthCal)
        val buttonSubmit: Button = findViewById(R.id.buttonSubmit)
        buttonReturn = findViewById(R.id.buttonReturn)

    // calendar to choose dates
        dateOfBirthCal.setOnClickListener {
            calendar()
        }

        buttonSubmit.setOnClickListener {
            val name = nameInput.text.toString()
            val phoneNumber = phoneNumberInput.text.toString()
            val hasDriversLicense = driverLicenseCheck.isChecked
            val gender = if (maleRadio.isChecked) "Male" else "Female"
            val dateOfBirth = dateOfBirthCal.text.toString()

            if (name.isNotEmpty() && phoneNumber.isNotEmpty() && dateOfBirth.isNotEmpty()) {
                val userData = hashMapOf(
                    "name" to name,
                    "phoneNumber" to phoneNumber,
                    "hasDriversLicense" to hasDriversLicense,
                    "gender" to gender,
                    "dateOfBirth" to dateOfBirth
                )

                db.collection("users").add(userData)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Application sent successfully", Toast.LENGTH_SHORT)
                            .show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                    }
            }
        }

        buttonReturn.setOnClickListener {
            finish()
        }
    }

    private fun calendar() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val chooseDate = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
            val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
            dateOfBirthCal.setText(selectedDate)
        }, year, month, day)

        chooseDate.show()
    }
}
