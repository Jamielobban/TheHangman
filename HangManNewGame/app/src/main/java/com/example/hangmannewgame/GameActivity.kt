package com.example.hangmannewgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.hangmannewgame.databinding.ActivityGameBinding
import com.google.firebase.database.DatabaseReference
import com.example.hangmannewgame.SplashScreenActivity.Companion.prefs
import com.example.hangmannewgame.services.BackgroundSoundService
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.delay
import java.util.*



class GameActivity : AppCompatActivity() {

    // When kill reaches 10 - player loses.
    private var kill = 0
    private var secretWord = ""
    private var secretDisplay = ""
    private val correctGuesses = mutableListOf<String>()
    private val lettersUsed = mutableListOf<String>()
    private var timeLeft : Long = 0
    private var score : Int = 0
    private var streak : Int = 0
    private lateinit var binding: ActivityGameBinding
    private lateinit var database : DatabaseReference
    private lateinit var timer : CountDownTimer
    private lateinit var timerShow: TextView
    private lateinit var letterUsed: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        timerShow = binding.timer
        letterUsed = binding.letterUsed

        binding.pauseButton.setOnClickListener {
            timer.cancel()
            binding.playButton2.visibility = View.VISIBLE
            binding.pauseButton.visibility = View.INVISIBLE

            val intentS = Intent(this@GameActivity, BackgroundSoundService::class.java);
            intentS.putExtra("audioIndex", "7");
            startService(intentS);

        }

        binding.playButton2.setOnClickListener {
            binding.pauseButton.visibility = View.VISIBLE
            binding.playButton2.visibility = View.INVISIBLE
            startGameTimer(timeLeft)

            val intentS = Intent(this@GameActivity, BackgroundSoundService::class.java);
            intentS.putExtra("audioIndex", "8");
            startService(intentS);
        }

        val extras = intent.extras
        secretWord = resources.getStringArray(R.array.guessWords)[Random().nextInt(resources.getStringArray(R.array.guessWords).size-0)+0]
        prepGame()
    }

    fun guessTry(click: View) {
        if (click === binding.tryButton) {
            val pGuess = binding.playerGuess1.text.toString().toLowerCase()

            binding.playerGuess1.text = null
            if(pGuess == null || pGuess == ""){
                    Toast.makeText(getBaseContext(), "Please select a letter", Toast.LENGTH_SHORT).show();
                return
            }
            if(lettersUsed.contains((pGuess))){
                return
            }
            // Player asks for a letter
            if (pGuess.length < 2) {
                lettersUsed.add(pGuess)
                if (pGuess in secretWord.toLowerCase()) {
                    correctGuesses.add(pGuess)
                    refactorSecret()

                    Toast.makeText(applicationContext,"Good guess!",Toast.LENGTH_SHORT).show()

                    val intentS = Intent(this@GameActivity, BackgroundSoundService::class.java);
                    intentS.putExtra("audioIndex", "2");
                    startService(intentS);

                    letterUsed.text = "Letters used: ${lettersUsed}"
                    score += 50
                    if(score > 0){
                        binding.scoreText.text = "Score ${score}"
                    }
                    checkWin()
                    return
                }
                Toast.makeText(applicationContext,"Bad guess!",Toast.LENGTH_SHORT).show()

                val intentS = Intent(this@GameActivity, BackgroundSoundService::class.java);
                intentS.putExtra("audioIndex", "3");
                startService(intentS);

                score -=30
                if(score < 0){
                    score = 0
                    binding.scoreText.text = "Score ${score}"
                }
                else{
                    binding.scoreText.text = "Score ${score}"
                }
                letterUsed.text = "Letters used: ${lettersUsed}"
            }
            else{
                Toast.makeText(applicationContext,"One letter only!",Toast.LENGTH_SHORT).show()

                return
            }

            // Player tries to guess
            if (pGuess.length > 1) {
                if (pGuess.toLowerCase() == secretWord.toLowerCase()) {
                    winDialogPopUp(true)
                    return
                }
            }

            // Player fails
            kill++
            when (kill) {
                1 -> binding.hangmanDrawing.setImageResource(R.drawable.hangman1)
                2 -> binding.hangmanDrawing.setImageResource(R.drawable.hangman2)
                3 -> binding.hangmanDrawing.setImageResource(R.drawable.hangman3)
                4 -> binding.hangmanDrawing.setImageResource(R.drawable.hangman4)
                5 -> binding.hangmanDrawing.setImageResource(R.drawable.hangman5)
                6 -> binding.hangmanDrawing.setImageResource(R.drawable.hangman6)
                7 -> {
                    binding.hangmanDrawing.setImageResource(R.drawable.hangman7)
                    winDialogPopUp(false)
                }
            }
        }
    }

    // Recreate display of the secret word based on progress
    private fun refactorSecret() {
        secretDisplay = ""
        secretWord.forEach {
                s -> secretDisplay += (checkIfGuessed(s.toString()))
        }
        binding.toBeGuessed.text = secretDisplay
    }

    // Reveal correctly guessed letters
    private fun checkIfGuessed(s: String) : String {
        return when (correctGuesses.contains(s.toLowerCase())) {
            true -> s
            false -> "_"
        }
    }

    // If a char from secretWord isn't in guess chars then player didn't guess everything yet
    private fun checkWin() {
        var everythingGuessed = true
        secretWord.toLowerCase().forEach { c ->
            if (!correctGuesses.contains(c.toString()))
                everythingGuessed = false
        }
        if(everythingGuessed)
            //streak++
            winDialogPopUp(true)
    }

    // Win/Lose alert
    private fun winDialogPopUp(won: Boolean) {
        val builder = AlertDialog.Builder(this@GameActivity)
        score += (timeLeft.toInt()/1000) + score
        binding.scoreText.text = "Score ${score}"
        if(prefs.getScore() < score){
            prefs.saveScore(score)
            //Toast.makeText(applicationContext,"New Highscore",Toast.LENGTH_SHORT).show()
        }
        timer.cancel()
        if(won) {
            builder.setTitle("Congratulations!")
            streak++
        }else{
            builder.setTitle("Boo! You hanged a man!")
            streak = 0
        }
        builder.setMessage("Do you want to play again?")

        builder.setPositiveButton("Let's go"){ _, _ ->

            // This one below is in main view more readable
            secretWord = resources.getStringArray(R.array.guessWords)[Random().nextInt(resources.getStringArray(R.array.guessWords).size-0)+0]

            //timer.cancel()
            prepGame()

            Toast.makeText(applicationContext,"New game started!",Toast.LENGTH_SHORT).show()

        }
        builder.setNegativeButton("No"){ _, _ ->

            val intentS = Intent(this@GameActivity, BackgroundSoundService::class.java);
            intentS.putExtra("audioIndex", "0");
            startService(intentS);

            val name = prefs.getName()
            val score = score
            database = FirebaseDatabase.getInstance("https://hangmannewgame-default-rtdb.europe-west1.firebasedatabase.app").getReference("UserInfo")
            val User = UserInfo(name,score)
            database.child(name).setValue(User).addOnSuccessListener {
            }.addOnFailureListener {
            }


            val intent = Intent(this@GameActivity, MainActivity::class.java)
            startActivity(intent)
            finish()

        }
        val dialog: AlertDialog = builder.create()
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show()
    }

    // Reset & Create New Game
    private fun prepGame(){
        //hangmanDrawing.setImageResource(R.drawable.hangman0)
        binding.hangmanDrawing.setImageResource(R.drawable.hangman0)
        kill = 0
        secretDisplay = ""
        correctGuesses.clear()

        repeat(secretWord.length) {
            secretDisplay += "_"
        }
        lettersUsed.clear()
        letterUsed.text = "Letters used: ${lettersUsed}"
        binding.toBeGuessed.text = secretDisplay
        startGameTimer(60000)
    }


    private fun startGameTimer(countdown : Long)
    {        //Mira el tiempo si no se acaba lo actualiza, si se acaba ejecuta Lose
        timer = object : CountDownTimer(countdown, 1000) {
            override fun onTick(p0: Long) {
                timeLeft = p0
                timerShow.text = "Time left: ${(p0 / 1000).toInt()} +  + ${streak}"
                //gameManager.currentTime = (p0 / 1000).toInt()
            }
            override fun onFinish() {
                winDialogPopUp(false)
            }
        }.start()
    }

    //private fun startFragment(fragment: Fragment){
    //val fragmentManager = supportFragmentManager
    //val fragmentTransaction = fragmentManager.beginTransaction()
    //fragmentTransaction.replace(R.id.fragmentContainer,fragment)
    //fragmentTransaction.commit()
    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()
    }
}
