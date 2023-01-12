package com.example.realtime_database

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class RegistrationActivity : AppCompatActivity() {

    private lateinit var buttonReg : Button
    private lateinit var emailET : EditText
    private lateinit var passET : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        buttonReg = findViewById(R.id.button4)
        emailET = findViewById(R.id.editTextTextEmailAddress)
        passET = findViewById(R.id.editTextTextPassword)

        buttonReg.setOnClickListener {
            if(emailET.text.toString().isEmpty() || passET.text.toString().isEmpty()){
                Toast.makeText(this,"შეიყვანეთ მონაცემები", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(emailET.text.toString(),passET.text.toString())
                .addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        Toast.makeText(this,"წარმატებით დარეგისტრირდით", Toast.LENGTH_LONG).show()
                        val intent = Intent(this,MainActivity::class.java)
                        startActivity(intent)
                    }
                    else{
                        Toast.makeText(this,"არასწორი მონაცემები", Toast.LENGTH_LONG).show()
                    }
                }
        }
    }
}