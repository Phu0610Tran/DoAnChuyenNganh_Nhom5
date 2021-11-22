package com.example.doanchuyennganh_nhom5.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.doanchuyennganh_nhom5.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class PlayvideoActivity extends YouTubeBaseActivity
        implements YouTubePlayer.OnInitializedListener {
    String id = "";
    int REQUEST_VIDEO =12;
    YouTubePlayerView youTubePlayerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playvideo);
        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.myvideo);

        Intent intent = getIntent();
        id = intent.getStringExtra("ID_VIDEO");
        youTubePlayerView.initialize(Home.KEY_API,this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.loadVideo(id);
//        youTubePlayer.setFullscreen(true);
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if(youTubeInitializationResult.isUserRecoverableError()){
            youTubeInitializationResult.getErrorDialog(PlayvideoActivity.this,REQUEST_VIDEO);
        }else
        {
            Toast.makeText(this,"loi",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_VIDEO)
        {
            youTubePlayerView.initialize(Home.KEY_API,PlayvideoActivity.this);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}