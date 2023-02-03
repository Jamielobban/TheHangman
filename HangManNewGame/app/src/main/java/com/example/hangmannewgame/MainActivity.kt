package com.example.hangmannewgame



import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity



import com.example.hangmannewgame.databinding.ActivityMainBinding
import android.content.Intent


import android.widget.Toast

import com.example.hangmannewgame.SplashScreenActivity.Companion.prefs
import com.example.hangmannewgame.services.BackgroundSoundService




class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        //setTheme(R.style.Theme_HangManNewGame_AppBarOverlay)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        initUI()
        checkUserValues()




        binding.settingsButton.setOnClickListener {
            val intent = Intent(this@MainActivity, OptionsActivity::class.java)
            startActivity(intent)

            val intentS = Intent(this@MainActivity, BackgroundSoundService::class.java)
            intentS.putExtra("audioIndex", "4")
            startService(intentS)
        }

        binding.playButton.setOnClickListener{

            if(binding.nameChange.text.toString().isNotEmpty()){
                val intent = Intent(this@MainActivity, GameActivity::class.java)
                startActivity(intent)

                val intentS = Intent(this@MainActivity, BackgroundSoundService::class.java)
                intentS.putExtra("audioIndex", "1")
                startService(intentS)
            }
            else{
                Toast.makeText(this@MainActivity,"Please write a name and save the changes", Toast.LENGTH_SHORT).show()
            }
            /*
            var systemService = getSystemService(BackgroundSoundService::class.java)
            systemService?.let {
                systemService.PauseThis(0);
                systemService.PlayThis("1");

            }
            */
        }

        binding.rankingButton.setOnClickListener{
            val intent = Intent(this@MainActivity, RankingActivity::class.java)
            startActivity(intent)

            val intentS = Intent(this@MainActivity, BackgroundSoundService::class.java)
            intentS.putExtra("audioIndex", "4")
            startService(intentS)
        }

    }


    fun initUI(){
        binding.saveChanges.setOnClickListener{ accessToDetail()
        }

    }
    fun accessToDetail(){

        if(binding.nameChange.text.toString().isNotEmpty()){
            prefs.saveName(binding.nameChange.text.toString())
            Toast.makeText(this@MainActivity,"Name Saved Correctly", Toast.LENGTH_SHORT).show()
            //Toast.makeText(this@MainActivity,"Name changed successfully", Toast.LENGTH_SHORT).show()
            //val name = binding.nameChange.text.toString()
            //val score = prefs.getScore()
            //database = FirebaseDatabase.getInstance("https://hangmannewgame-default-rtdb.europe-west1.firebasedatabase.app").getReference("UserInfo")
            //val User = UserInfo(name,score)
            //database.child(name).setValue(User).addOnSuccessListener {
                //Toast.makeText(this@MainActivity,"this did work", Toast.LENGTH_SHORT).show()
            //}.addOnFailureListener {
                //Toast.makeText(this@MainActivity,"this did not work", Toast.LENGTH_SHORT).show()
            //}

        }
        else{
            Toast.makeText(this@MainActivity,"Name cant be void", Toast.LENGTH_SHORT).show()
        }

    }

    fun checkUserValues(){
        if(prefs.getName().isNotEmpty()){
            binding.nameChange.setText(prefs.getName())
        }
        if(prefs.getScore() != 0){
            //Toast.makeText(this@MainActivity,"just a check", Toast.LENGTH_SHORT).show()
            binding.highscore.setText("Highscore + ${prefs.getScore()}")
        }
        else{
            binding.highscore.setText("Highscore 0")
        }
    }

}
        //if(binding.nameChange.text == null || binding.nameChange.text.toString() == ""){
            //prefs.USERNAME = ""

        //}

            //if(users.name == null || users.name.toString() == ""){
                //binding.nameChange.setText(prefs.USERNAME)
                //Toast.makeText(this@MainActivity,"nameIsNull", Toast.LENGTH_SHORT).show()
            //}
            //Toast.makeText(this@MainActivity,"isInitialized", Toast.LENGTH_SHORT).show()
            //else{
                //binding.nameChange.setText(prefs.USERNAME)
                //Toast.makeText(this@MainActivity,"name is something", Toast.LENGTH_SHORT).show()
            //}






       // binding.settingsButton.setOnClickListener {
            //val intent = Intent(this@MainActivity, OptionsActivity::class.java)
            //startActivity(intent)
        //}

        //binding.nameChange.setOnClickListener(){
           // if(binding.nameChange.text == null || binding.nameChange.text.toString() == "") {
                //Toast.makeText(this@MainActivity,"Name cant be void", Toast.LENGTH_SHORT).show()
            //}
            //else{
                //val name = binding.nameChange.text.toString()
                //users?.username ?: name
                //prefs.USERNAME = name
                //if(users.name == null){
                    //Toast.makeText(this@MainActivity,"Is null", Toast.LENGTH_SHORT).show()
                //}
                //else{
                    //binding.nameChange.setText(users.name)

                //}

            //}
        //}
       // binding.saveChanges.setOnClickListener{
            //if(binding.nameChange.text == null || binding.nameChange.text.toString() == "") {
                //Toast.makeText(this@MainActivity,"Name cant be void", Toast.LENGTH_SHORT).show()
           // }
           // else{
                //val name = binding.nameChange.text.toString()
                //prefs.USERNAME = name
                //binding.nameChange.setText(prefs.USERNAME)
                //users.name = name
                //if(prefs.USERNAME == name){
                    //Toast.makeText(this@MainActivity,prefs.USERNAME,Toast.LENGTH_SHORT).show()
                    //Toast.makeText(this@MainActivity,"usernameIsName", Toast.LENGTH_SHORT).show()

                //Toast.makeText(this@MainActivity,"Name changed successfully", Toast.LENGTH_SHORT).show()




        //binding.rankingButton.setOnClickListener{
            //val intent = Intent(this@MainActivity, ::class.java)
            //startActivity(intent)
        //}



