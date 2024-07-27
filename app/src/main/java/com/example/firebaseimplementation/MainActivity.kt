package com.example.firebaseimplementation

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.firebaseimplementation.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    lateinit var database : DatabaseReference
    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.signInButton.setOnClickListener {

            var mobile = binding.mobile.text.toString()
            var pass = binding.password.text.toString()

            if (mobile.isNotEmpty() && pass.isNotEmpty()) {
                database = FirebaseDatabase.getInstance().getReference("Students")
                database.child(mobile).get().addOnSuccessListener {

                    if (it.exists()) {

                        var mobiled = it.child("mobile").value.toString()
                        var passd = it.child("password").value.toString()

                        if (pass == passd && mobile == mobiled) {

                            var i = Intent(this, Welcome::class.java)
                            startActivity(i)

                        } else {

                            Toast.makeText(
                                this,
                                "Please enter correct password",
                                Toast.LENGTH_SHORT
                            ).show()

                        }
                    } else {

                        Toast.makeText(
                            this,
                            "User does not exist please signup first",
                            Toast.LENGTH_LONG
                        ).show()

                    }
                }.addOnFailureListener {


                    Toast.makeText(this, "Please check your internet connection", Toast.LENGTH_LONG)
                        .show()

                }
            }
            else{

                Toast.makeText(this, "Enter credentials first", Toast.LENGTH_SHORT).show()

            }








        }

binding.signUpRedirectTextView.setOnClickListener {

    var i = Intent(this,SignUp::class.java)
    startActivity(i)

}

    }
}