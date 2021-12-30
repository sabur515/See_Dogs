package com.example.seedogs

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {


    private var breedText: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        findViewById<Button>(R.id.btnBreedImg).setOnClickListener {
            breedText = findViewById<EditText>(R.id.etBreed).text.toString()
            findViewById<EditText>(R.id.etBreed).setText("")
        }
        Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
    }
}