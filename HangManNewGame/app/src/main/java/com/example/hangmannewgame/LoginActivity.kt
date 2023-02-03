package com.example.hangmannewgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent

import android.text.TextUtils
import android.util.Patterns
import android.view.View

import com.example.hangmannewgame.services.BackgroundSoundService
import com.example.hangmannewgame.databinding.ActivityLoginBinding



import com.google.firebase.auth.FirebaseAuth
import android.widget.Toast


class LoginActivity : AppCompatActivity() {



    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = firebaseAuth.currentUser

        if(currentUser != null){
            intent = Intent(this@LoginActivity, MainActivity::class.java)
            //Toast.makeText(this@LoginActivity,"Logged in", Toast.LENGTH_SHORT).show()
            startActivity(intent)
            finish()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        firebaseAuth = FirebaseAuth.getInstance()

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)






        binding.loginButton.setOnClickListener(View.OnClickListener {
            val email = binding.emailInput.text.toString()
            val password = binding.passwordInput.text.toString()

            val intentS = Intent(this@LoginActivity, BackgroundSoundService::class.java)
            intentS.putExtra("audioIndex", "4")
            startService(intentS)

            if(TextUtils.isEmpty(password) && TextUtils.isEmpty(email)){
            Toast.makeText(this@LoginActivity,"Please enter a valid email and password", Toast.LENGTH_SHORT).show()
            }
            else if(TextUtils.isEmpty(email)){
                Toast.makeText(this@LoginActivity,"Enter email", Toast.LENGTH_SHORT).show()
            }
            else if(TextUtils.isEmpty(password)){
                Toast.makeText(this@LoginActivity,"Enter password", Toast.LENGTH_SHORT).show()
            }

            if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password))
                firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this@LoginActivity, "Authentication correct.",
                                Toast.LENGTH_SHORT).show()
                            intent = Intent(this@LoginActivity, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {

                            Toast.makeText(this@LoginActivity, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                    }
                }

            // intent = Intent(this@LoginActivity, MainActivity::class.java)
            //startActivity(intent)


        })

        binding.emailInput.setOnFocusChangeListener{_, hasFocus ->
            if(!hasFocus){
                val email = binding.emailInput.text.toString()
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                    binding.emailInput.error = "invalid email"
                else
                    binding.emailInput.error = null
            }
        }

        binding.registerButton.setOnClickListener{
            val intent = Intent(this@LoginActivity,RegisterActivity::class.java)
            startActivity(intent)
            finish()

            val intentS = Intent(this@LoginActivity, BackgroundSoundService::class.java)
            intentS.putExtra("audioIndex", "4")
            startService(intentS)
        }

        binding.backButton3.setOnClickListener{
            val intent = Intent(this@LoginActivity, OptionsActivity::class.java)
            startActivity(intent)

            val intentS = Intent(this@LoginActivity, BackgroundSoundService::class.java)
            intentS.putExtra("audioIndex", "4")
            startService(intentS)
        }

        //binding.anonymousLogin.setOnClickListener(){
            //firebaseAuth.signInAnonymously()
            //val intent = Intent(this@LoginActivity,MainActivity::class.java)
            //startActivity(intent)
            //finish()
        //}

    }

}



