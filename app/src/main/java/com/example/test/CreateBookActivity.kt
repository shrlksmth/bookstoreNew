package com.example.firebase

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.test.R
import com.example.test.databinding.ActivityCreateBookBinding
import com.example.test.model.BookListModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class CreateBookActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateBookBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCreateBookBinding.inflate(layoutInflater)
        setContentView(binding.main)

        val createButton: Button = binding.createButton
        val bookName: EditText = binding.userInputBookName
        val bookAuthor: EditText = binding.userInputBookAuthor
        val bookImage: ImageView = binding.userInputBookImage
        val bookNotes: EditText = binding.userInputBookNotes

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        createButton.setOnClickListener {

            insertData(
                name = bookName.text.toString(),
                author = bookAuthor.text.toString(),
                imageUrl = "hard-coded string",
                notes = bookNotes.text.toString()
            )
        }

    }

    private fun insertData(name: String, author: String, notes: String, imageUrl: String) {
        // Get a reference to the Firebase Realtime Database
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val myRef: DatabaseReference = database.getReference("books")

        // Create a new book
        val bookId = myRef.push().key // Generate a unique key for the new book
        val user = BookListModel(name = name, author = author, notes = notes, imageUrl = imageUrl)

        // Insert data into the database
        if (bookId != null) {
            myRef.child(bookId).setValue(user).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Successfully transfer data to db", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Toast.makeText(this, "Failed transfer data to db", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}