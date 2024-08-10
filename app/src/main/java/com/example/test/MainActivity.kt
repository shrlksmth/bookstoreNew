package com.example.test

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.test.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var firebaseref : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        firebaseref = FirebaseDatabase.getInstance().getReference("test")

        var userInputUserID = findViewById(R.id.userID) as EditText
        var userInputPassword = findViewById(R.id.password) as EditText
        var LoginButton = findViewById(R.id.button) as Button


        LoginButton.setOnClickListener {
            if (userInputPassword.text.toString() == "11111" && userInputUserID.text.toString() == "SS") {
                Toast.makeText(this, "Success Login", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, HomePage::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Failed Login", Toast.LENGTH_SHORT).show()
            }
        }


        fun onCreateOptionsMenu(menu: Menu?): Boolean {
            menuInflater.inflate(R.menu.menu, menu)
            return true
        }

         fun onOptionsItemSelected(item: MenuItem): Boolean {
            return when (item.itemId) {
                R.id.action_search -> {
                    // Handle search action
                    true
                }
                R.id.action_settings -> {
                    // Handle settings action
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }
        }



    }
}