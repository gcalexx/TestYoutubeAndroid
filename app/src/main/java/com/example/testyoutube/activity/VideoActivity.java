package com.example.testyoutube.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.testyoutube.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class VideoActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener{

    private YouTubePlayerView youTubePlayerView;
    private String youTubeKey;
    private String videoId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        youTubeKey = "AIzaSyBkXHLxk-kCajYObs1sZOBDQVu2gabge9Y";

        youTubePlayerView = (YouTubePlayerView)findViewById(R.id.youtubePlayer);

        youTubePlayerView.initialize(youTubeKey, this);


    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {

        if(youTubePlayer == null)return;

        youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.CHROMELESS);

        if(!wasRestored){
            youTubePlayer.cueVideo("yfrEMQgBc2Y");
        }

    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

        if(youTubeInitializationResult.isUserRecoverableError()){
            youTubeInitializationResult.getErrorDialog(this, 1).show();
        }else{
            Toast.makeText(this, "Error: "+youTubeInitializationResult.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        if(resultCode==1){
            getYouTubeProvider().initialize(youTubeKey, this);
        }

    }

    protected YouTubePlayer.Provider getYouTubeProvider(){
        return youTubePlayerView;
    }


    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
