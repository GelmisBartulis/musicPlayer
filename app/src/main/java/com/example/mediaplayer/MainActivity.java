package com.example.mediaplayer;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;


// 1. Setup UI (buttons, list)
// 2. Add RAW files (music)
// 3. Display songs in a ArrayList with an adapter as an listView
// 4. Test


public class MainActivity extends AppCompatActivity {

    private List<String> songs;
    private String[] songName = {"Promiscuous", "Under The Bridge", "Sure Thing", "One, Two Step", "Californication", "In The Morning", "Glamorous", "'Till I Collapse"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_listview, songName);
        ListView listView = (ListView) findViewById(R.id.song_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), ShowSong.class);
                intent.putExtra("name",  i);
                startActivity(intent);
            }
        });
    }
}
