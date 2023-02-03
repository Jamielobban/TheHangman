package com.example.hangmannewgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hangmannewgame.databinding.ActivityRankingBinding
import com.example.hangmannewgame.services.BackgroundSoundService
import com.google.firebase.database.*

class RankingActivity : AppCompatActivity() {

    private lateinit var dbref: DatabaseReference
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userArrayList: ArrayList<UserInfo>
    private lateinit var binding: ActivityRankingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_ranking)

        binding = ActivityRankingBinding.inflate(layoutInflater)

        setContentView(binding.root)


        userRecyclerView = binding.rankingList
        userRecyclerView.layoutManager = LinearLayoutManager(this)
        userRecyclerView.setHasFixedSize(true)

        userArrayList = arrayListOf<UserInfo>()
        gerUserData()

        binding.backToHome.setOnClickListener{
            val intent = Intent(this@RankingActivity, MainActivity::class.java)
            startActivity(intent)

            val intentS = Intent(this@RankingActivity, BackgroundSoundService::class.java)
            intentS.putExtra("audioIndex", "4")
            startService(intentS)
        }


    }

    private fun gerUserData() {

        dbref = FirebaseDatabase.getInstance("https://hangmannewgame-default-rtdb.europe-west1.firebasedatabase.app")
            .getReference("UserInfo")
        dbref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(userSnapshot in snapshot.children){

                        val user = userSnapshot.getValue(UserInfo::class.java)
                        userArrayList.add(user!!)
                    }
                    userRecyclerView.adapter = RankingAdapter(userArrayList)
                }

            }

            override fun onCancelled(error: DatabaseError) {

                Toast.makeText(this@RankingActivity,"Failed", Toast.LENGTH_SHORT).show()
            }

        })


    }
}