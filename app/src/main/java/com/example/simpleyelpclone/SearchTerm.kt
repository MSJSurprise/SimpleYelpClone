package com.example.simpleyelpclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class SearchTerm : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_term)

        val edtSearchTerm: EditText = findViewById(R.id.edtSearchTerm)
        val edtLocation: EditText = findViewById(R.id.edtLocation)
        val btnSearch: Button = findViewById(R.id.btnSearch)

        btnSearch.setOnClickListener {
            val searchTerm = edtSearchTerm.text.toString().trim()
            val location = edtLocation.text.toString().trim()
            if (searchTerm.isEmpty() || location.isEmpty()) {
                Toast.makeText(this, "Enter the search term and Location",
                    Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("searchTerm", searchTerm)
            intent.putExtra("location", location)
            startActivity(intent)
        }


    }
}