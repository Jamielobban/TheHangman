package com.example.hangmannewgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.hangmannewgame.databinding.ActivityOptionsBinding
import com.example.hangmannewgame.services.BackgroundSoundService
import com.google.firebase.auth.FirebaseAuth

class OptionsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOptionsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOptionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            val intent = Intent(this@OptionsActivity, MainActivity::class.java)
            startActivity(intent)

            val intentS = Intent(this@OptionsActivity, BackgroundSoundService::class.java)
            intentS.putExtra("audioIndex", "4")
            startService(intentS)
        }

        binding.logoutButton.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this@OptionsActivity, MainActivity::class.java)
            startActivity(intent)

            val intentS = Intent(this@OptionsActivity, BackgroundSoundService::class.java)
            intentS.putExtra("audioIndex", "4")
            startService(intentS)
        }

        //binding.adWatch.setOnClickListener(){
            //val intent = Intent(this@OptionsActivity, RewardActivity::class.java)
            //startActivity(intent)
        //}

        binding.soundTickBox.setOnClickListener(){

            binding.soundTickBox.visibility = View.INVISIBLE
            binding.soundTickBox2.visibility = View.VISIBLE
            val intentS = Intent(this@OptionsActivity, BackgroundSoundService::class.java);
            intentS.putExtra("audioIndex", "5");
            startService(intentS);
        }

        binding.soundTickBox2.setOnClickListener(){

            binding.soundTickBox.visibility = View.VISIBLE
            binding.soundTickBox2.visibility = View.INVISIBLE
            val intentS = Intent(this@OptionsActivity, BackgroundSoundService::class.java);
            intentS.putExtra("audioIndex", "6");
            startService(intentS);
        }

        binding.notisTickBox.setOnClickListener(){

            binding.notisTickBox.visibility = View.INVISIBLE
            binding.notisTickBox2.visibility = View.VISIBLE
        }

        binding.notisTickBox2.setOnClickListener(){
            binding.notisTickBox.visibility = View.VISIBLE
            binding.notisTickBox2.visibility = View.VISIBLE
        }



        binding.loginOptionsButton.setOnClickListener{
            val intent = Intent(this@OptionsActivity, LoginActivity::class.java)
            startActivity(intent)

            val intentS = Intent(this@OptionsActivity, BackgroundSoundService::class.java)
            intentS.putExtra("audioIndex", "4")
            startService(intentS)
        }

        binding.registerOptionsButton.setOnClickListener{
            val intent = Intent(this@OptionsActivity, RegisterActivity::class.java)
            startActivity(intent)

            val intentS = Intent(this@OptionsActivity, BackgroundSoundService::class.java)
            intentS.putExtra("audioIndex", "4")
            startService(intentS)
        }

        //binding.musicSlider?.progress = 50

    }

}