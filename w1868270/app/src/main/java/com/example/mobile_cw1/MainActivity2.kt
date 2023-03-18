package com.example.mobile_cw1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog

class MainActivity2 : AppCompatActivity() {
    // Declare variables for the About and New Game buttons
    lateinit var aboutBtn: Button
    lateinit var newgameBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set the layout for the activity
        setContentView(R.layout.activity_main2)
        // Initialize the About and New Game buttons using their IDs
        aboutBtn =findViewById(R.id.abtBtn)
        newgameBtn=findViewById(R.id.newGamBtn)

        // Set a click listener for the About button to display the student information in an AlertDialog
        aboutBtn.setOnClickListener {
            val dialogBuilder = AlertDialog.Builder(this)

            val message = "Student ID: W1868270\n" +
                    "Name: Pasindu Wimalarathne\n\n" +
                    "I confirm that I understand what plagiarism is and have read and understood the section on Assessment Offences in the Essential Information for Students. " +
                    "The work that I have submitted is entirely my own. " +
                    "Any work from other authors is duly referenced and acknowledged."
            // Set the message for the AlertDialog, disable the cancel button and set the OK button to dismiss the dialog
            dialogBuilder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }

            val alert = dialogBuilder.create()
            alert.setTitle("About")
            alert.show()
        }
        // Create and show the AlertDialog
        newgameBtn.setOnClickListener {
            val intent = Intent(this, MainActivity3::class.java)
            startActivity(intent)
        }

    }
}