package com.example.doanchuyennganh_nhom5.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.doanchuyennganh_nhom5.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class myvideo extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    ImageButton imageButton;
    YouTubePlayerView youTubePlayerView;
    YouTubePlayer.OnInitializedListener onInitializedListener;
    String API_KEY = "AIzaSyCG5drb_rq5EEUVPv1QXYbvd-m1Z_wK5II";
    int REQUEST_VIDEO = 123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myvideo);
        Anhxa();
        youTubePlayerView.initialize(API_KEY,this);
    }

    private void Anhxa() {
//        imageButton = (ImageButton) findViewById(R.id.play);
        youTubePlayerView = findViewById(R.id.YoutubePlayerView);


    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.cueVideo("VQttXb6qE6k");
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if(youTubeInitializationResult.isUserRecoverableError()){
            youTubeInitializationResult.getErrorDialog(myvideo.this,REQUEST_VIDEO);
        }else
        {
            Toast.makeText(this, "Error!!!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_VIDEO)
        {
            youTubePlayerView.initialize(API_KEY,myvideo.this);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}