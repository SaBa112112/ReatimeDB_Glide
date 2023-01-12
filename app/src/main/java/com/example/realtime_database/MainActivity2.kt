package com.example.realtime_database

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity2 : AppCompatActivity() {

    private lateinit var editTextTextPersonName: EditText
    private lateinit var editTextUrl: EditText
    private lateinit var saveButton: Button
    private lateinit var imageView: ImageView
    private lateinit var textView: TextView
    private lateinit var clearButton: Button

    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseDatabase.getInstance().getReference("USER_INFO")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        editTextTextPersonName = findViewById(R.id.editTextTextPersonName)
        editTextUrl = findViewById(R.id.editTextUrl)
        saveButton = findViewById(R.id.button2)
        imageView = findViewById(R.id.imageView)
        textView = findViewById(R.id.textView)
        clearButton = findViewById(R.id.button3)

        saveButton.setOnClickListener {

            val name = editTextTextPersonName.text.toString()
            val url = editTextUrl.text.toString()

            val personInfo = PersonInfo(name, url)

            db.child(auth.currentUser?.uid!!).setValue(personInfo)

        }

        db.child(auth.currentUser?.uid!!).addValueEventListener(object: ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                val userInfo = snapshot.getValue(PersonInfo::class.java) ?: return

                textView.text = userInfo.name

                Glide.with(this@MainActivity2).load(userInfo.url).into(imageView);

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

        clearButton.setOnClickListener {
            imageView.setImageDrawable(null)
            textView.text = ""
        }
    }
}