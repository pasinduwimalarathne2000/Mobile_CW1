package com.example.mobile_cw1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class MainActivity : AppCompatActivity() {
    lateinit var startBtn: Button // Declare a lateinit variable for the start button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Set the layout for this activity

        startBtn=findViewById(R.id.strBtn) // Initialize the start button with its ID from the layout

        startBtn.setOnClickListener{ // Set a click listener for the start button
            val intent = Intent(this, MainActivity2::class.java) // Create a new intent to switch to MainActivity2
            startActivity(intent) // Start the MainActivity2 activity using the created intent
        }
    }
}

/**
 * Reference,
 * https://developer.android.com/codelabs/basic-android-kotlin-training-create-dice-roller-app-with-button#7
 * https://stackoverflow.com/
 * https://medium.com/@kuopingl/journey-with-kotlin-002-dice-roller-9e7299bfeda0
 **/

/**
 * Video Link,
 * https://drive.google.com/drive/folders/1A0N0AolNMlRqdiTeShqvS-LzCSUKLiKO?usp=share_link
 **/