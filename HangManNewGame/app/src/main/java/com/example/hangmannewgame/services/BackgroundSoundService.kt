package com.example.hangmannewgame.services

import android.app.Service
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.media.MediaPlayer
import com.example.hangmannewgame.MainActivity
import com.example.hangmannewgame.R


class BackgroundSoundService : Service(){

    //private lateinit var mMediaPlayer: MediaPlayer
    //private var loopAudio: Boolean = false;

    private var allGameMediaPlayers : MutableList<MediaPlayer> = mutableListOf<MediaPlayer>()
    private var indexPlaying = -1;

    override fun onBind(arg0: Intent): IBinder? {

        return null
    }

    public fun PlayThis(index: String){
        if(index == "0"){
            PauseThis(1);
            allGameMediaPlayers[1].seekTo(0)
            allGameMediaPlayers[0].start();
        }
        else if(index == "1"){
            PauseThis(0);
            allGameMediaPlayers[0].seekTo(0)
            allGameMediaPlayers[1].start();
        }
        else if(index == "2"){
            allGameMediaPlayers[2].start();
        }
        else if(index == "3"){
            allGameMediaPlayers[3].start();
        }
        else {
            if(index == "5"){
                allGameMediaPlayers.forEach{ it.setVolume(0f,0f)}
            }
            if(index == "6"){
                allGameMediaPlayers[0].setVolume(0.6f, 0.6f);
                allGameMediaPlayers[1].setVolume(0.7f, 0.7f);
                allGameMediaPlayers[2].setVolume(1f, 1f);
                allGameMediaPlayers[3].setVolume(1f, 1f);
                allGameMediaPlayers[4].setVolume(1f, 1f);
            }
            if(index == "7"){
                allGameMediaPlayers[1].pause();
            }
            if(index == "8"){
                allGameMediaPlayers[1].start();
            }

            if(allGameMediaPlayers[4].isPlaying){
                allGameMediaPlayers[4].seekTo(0)
            }
            allGameMediaPlayers[4].start();



        }

    }

    public fun PauseThis(index: Int){
        allGameMediaPlayers[index]?.let {
            if (allGameMediaPlayers[index].isPlaying) {
                allGameMediaPlayers[index].pause();
            }
        }

    }

    override fun onCreate() {
        super.onCreate()

        var myMP : MediaPlayer;

        myMP = MediaPlayer.create(this, R.raw.menubocurt);
        myMP.isLooping = true;
        myMP.setVolume(0.6f,0.6f)
        allGameMediaPlayers.add(myMP);
        myMP = MediaPlayer.create(this, R.raw.musicagame);
        myMP.isLooping = true;
        myMP.setVolume(0.7f,0.7f)
        allGameMediaPlayers.add(myMP);
        myMP = MediaPlayer.create(this, R.raw.yehawsound);
        myMP.isLooping = false;
        myMP.setVolume(1f,1f)
        allGameMediaPlayers.add(myMP);
        myMP = MediaPlayer.create(this, R.raw.revolversound);
        myMP.isLooping = false;
        myMP.setVolume(1f,1f)
        allGameMediaPlayers.add(myMP);
        myMP = MediaPlayer.create(this, R.raw.salondoorfort);
        myMP.isLooping = false;
        myMP.setVolume(1f,1f)
        allGameMediaPlayers.add(myMP);

    }

    override fun onStartCommand(startIntent: Intent?, flags: Int, startId: Int): Int {
       // mMediaPlayer = MediaPlayer.create(this, R.raw.background_sound_hangman)
        startIntent?.let{
            if(startIntent.hasExtra("audioIndex")){
                var info = startIntent.getStringExtra("audioIndex").toString();
                PlayThis(info);
            }
        }

        return START_STICKY;
    }


    override fun onDestroy() {
        super.onDestroy()
        if(indexPlaying != -1){
            if(allGameMediaPlayers[indexPlaying] != null && allGameMediaPlayers[indexPlaying].isPlaying){
                allGameMediaPlayers[indexPlaying].pause();
            }
        }
    }




}