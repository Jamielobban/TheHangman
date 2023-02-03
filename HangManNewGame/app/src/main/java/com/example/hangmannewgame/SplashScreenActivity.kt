package com.example.hangmannewgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.hangmannewgame.databinding.ActivitySplashScreenBinding
import com.example.hangmannewgame.services.BackgroundSoundService
import com.example.hangmannewgame.services.Prefs


class SplashScreenActivity : AppCompatActivity() {

    companion object{
        lateinit var prefs: Prefs
    }

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intentS = Intent(this@SplashScreenActivity, BackgroundSoundService::class.java)
        intentS.putExtra("audioIndex", "0")
        startService(intentS)

        //binding.welcomeMessage.text = "Hello"

        prefs = Prefs(applicationContext)
        if(prefs.getName() == "" || prefs.getName() == null){
            prefs.saveName("Guest")
        }
        //startService(Intent(this@SplashScreenActivity, BackgroundSoundService::class.java))

        //supportActionBar?.hide()

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            val intent = Intent(this@SplashScreenActivity,MainActivity::class.java)
            startActivity(intent)
            finish()
        },3000)

        //Thread.sleep(5000)
        //val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
        //startActivity(intent)
        //finish()
        }
    }
