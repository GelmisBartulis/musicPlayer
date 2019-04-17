package com.example.mediaplayer;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class ShowSong extends AppCompatActivity implements View.OnClickListener  {
    private ImageButton play, prev, next, back;
    //Add MediaPlayer class
    private MediaPlayer mp;
    private ImageView artistImage;
    private TextView artist, songDesc;
    private int currentSong = 0 ;
    //Add a resource of music files in Array
    private String[] songName = {"Promiscuous", "Under The Bridge", "Sure Thing", "One, Two Step", "Californication", "In The Morning", "Glamorous", "'Till I Collapse"};
    private String[] songArtist = {"Nelly Furtado", "Red Hot Chili Peppers", "Miguel", "Missy Elliot", "Red Hot Chili Peppers", "J. Cole, Drake", "Fergie, Ludacris", "Eminem"};
    final int[]  resID = {R.raw.song1, R.raw.song2, R.raw.song3, R.raw.song4, R.raw.song5, R.raw.song6, R.raw.song7, R.raw.song8 };
    final int[]  images = {R.drawable.song1, R.drawable.song2, R.drawable.song3, R.drawable.song4, R.drawable.song5, R.drawable.song6, R.drawable.song7, R.drawable.song8};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_song);
        mp = new MediaPlayer();
        artistImage = findViewById(R.id.artistImage);
        artist = findViewById(R.id.artistName);
        songDesc = findViewById(R.id.songName);
        play = findViewById(R.id.play);
        prev = findViewById(R.id.prev);
        next = findViewById(R.id.next);
        back = findViewById(R.id.back);
        play.setOnClickListener(this);
        prev.setOnClickListener(this);
        next.setOnClickListener(this);
        back.setOnClickListener(this);
        Intent intent = getIntent();
        currentSong = intent.getIntExtra("name", 0);
        play.setBackgroundResource(R.drawable.pause);
        setSong(currentSong);
    }
    public void setSong (int song) {
        if(mp.isPlaying()) {
            play.setBackgroundResource(R.drawable.play);
            mp.reset();
        } else {
            song = currentSong;
            play.setBackgroundResource(R.drawable.pause);
            songDesc.setText(songName[song]);
            artist.setText(songArtist[song]);
            artistImage.setImageResource(images[song]);
            mp = MediaPlayer.create(getApplicationContext(), resID[song]);
            mp.start(); // starting mediaplayer
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.play:
                if(mp.isPlaying()) {
                    play.setBackgroundResource(R.drawable.pause);
                    mp.pause();
                } else {
                    play.setBackgroundResource(R.drawable.play);
                    mp.start();
                }
                break;
            case R.id.song_list:
                play.setBackgroundResource(R.drawable.pause);
                break;
            case R.id.prev:
                if(mp.isPlaying() && currentSong < 8) {
                    mp.reset();
                    setSong(currentSong--);
                } else {
                    currentSong = 7;
                    mp.reset();
                    setSong(currentSong--);
                }
                break;
            case R.id.next:
                if(mp.isPlaying() && currentSong < 8) {
                    mp.reset();
                    setSong(currentSong++);
                }else {
                    currentSong = 1;
                    mp.reset();
                    setSong(currentSong++);
                }
                break;
            case R.id.back:
                onBackPressed();
                break;
        }
    }
    @Override
    public void onDestroy() {//whenever your music files has been stopped or completed.
        super.onDestroy();
        mp.release();
    }
}
