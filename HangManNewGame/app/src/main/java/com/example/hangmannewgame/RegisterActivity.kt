package com.example.hangmannewgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.example.hangmannewgame.databinding.ActivityRegisterBinding
import com.example.hangmannewgame.services.BackgroundSoundService


class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        firebaseAuth = FirebaseAuth.getInstance()

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton2.setOnClickListener(){
            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(intent)

            val intentS = Intent(this@RegisterActivity, BackgroundSoundService::class.java)
            intentS.putExtra("audioIndex", "4")
            startService(intentS)
        }
        //to register buttono
        binding.registerButton2.setOnClickListener(View.OnClickListener() {

            val email = binding.emailInput.text.toString()
            val password = binding.passwordInput.text.toString()

            val intentS = Intent(this@RegisterActivity, BackgroundSoundService::class.java)
            intentS.putExtra("audioIndex", "4")
            startService(intentS)

            if(TextUtils.isEmpty(email)){
                Toast.makeText(this@RegisterActivity,"Enter email", Toast.LENGTH_SHORT).show()
            }
            if(TextUtils.isEmpty(password)){
                Toast.makeText(this@RegisterActivity,"Enter password", Toast.LENGTH_SHORT).show()
            }
            if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)){
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this@RegisterActivity, "Authentication successful.",
                                Toast.LENGTH_SHORT).show()
                        } else {
                            if(password.length < 6){
                                Toast.makeText(this@RegisterActivity, "Password must be more than 5 characters",
                                    Toast.LENGTH_SHORT).show()
                            }
                                // If sign in fails, display a message to the user.
                                Toast.makeText(
                                    this@RegisterActivity, "Authentication failed.",
                                    Toast.LENGTH_SHORT
                                ).show()


                        }

                    }
                }
            else{
                Toast.makeText(this@RegisterActivity, "password or email cant be empty",
                    Toast.LENGTH_SHORT).show()
            }

        })

        binding.emailInput.setOnFocusChangeListener { _, hasFocus ->
            if(!hasFocus)
            {
                val email = binding.emailInput.text.toString()
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                    binding.emailInput.error = "invalid email"
                else
                    binding.emailInput.error = null
            }
        }

    }
}