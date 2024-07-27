package com.example.firebaseimplementation

import android.content.Intent
import android.os.Bundle
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.firebaseimplementation.databinding.ActivitySignUpBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUp : AppCompatActivity() {

lateinit var database : DatabaseReference
    lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        binding.signUpButton.setOnClickListener {

            var name = binding.name.text.toString()
            var mobile = binding.mobile.text.toString()
            var email = binding.email.text.toString()
            var password = binding.password.text.toString()

            if (name.isNotEmpty() && mobile.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()){


                var user = student(name,mobile,email,password)
                database = FirebaseDatabase.getInstance().getReference("Students")
                database.child(mobile).setValue(user).addOnSuccessListener {


                    Toast.makeText(this, "Successfully Registered", Toast.LENGTH_SHORT).show()
                    binding.name.text.clear()
                    binding.mobile.text.clear()
                    binding.email.text.clear()
                    binding.password.text.clear()

                    var i = Intent(this,MainActivity::class.java)
                    startActivity(i)


                }.addOnFailureListener {

                    Toast.makeText(this, "Please check your internet Connection", Toast.LENGTH_SHORT).show()

                }

            }
            else{

                Toast.makeText(this, "Enter all credentials", Toast.LENGTH_SHORT).show()

            }

        }
        binding.signInRedirectTextView.setOnClickListener {

            var i = Intent(this, MainActivity::class.java)
            startActivity(i)

        }




    }
}