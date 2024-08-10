package com.example.test

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.test.databinding.ActivityHomePageBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class HomePage : AppCompatActivity() {
    private lateinit var binding: ActivityHomePageBinding
    private lateinit var firebaseRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.main)

        firebaseRef = FirebaseDatabase.getInstance().reference

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val bookImage = findViewById(R.id.bookImageId) as ImageView
        binding.topText.setOnClickListener {
//            firebaseRef.setValue("test").addOnCompleteListener {
//                Toast.makeText(this, "Successfully connected to db", Toast.LENGTH_SHORT).show()
//            }

            insertData()
        }

        bookImage.setOnClickListener {

            bookImage.setOnClickListener {
                Toast.makeText(this, "Successfully connected to db", Toast.LENGTH_SHORT).show()
            }
//            startActivity(Intent(this, CreateBookActivity::class.java))
        }


    }

    data class User(val name: String, val age: Int)

    private fun insertData() {
        // Get a reference to the Firebase Realtime Database
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val myRef: DatabaseReference = database.getReference("users")

        // Create a new user
        val userId = myRef.push().key // Generate a unique key for the new user
        val user = User("John Doe", 25)

        // Insert data into the database
        if (userId != null) {
            myRef.child(userId).setValue(user).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    println("Data inserted successfully")
                } else {
                    println("Error inserting data: ${task.exception}")
                }
            }
        }
    }
}