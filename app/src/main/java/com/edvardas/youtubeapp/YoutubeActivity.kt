package com.edvardas.youtubeapp

import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView

class YoutubeActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {
    private val TAG = "YoutubeActivity"
    companion object {
        @JvmStatic
        val GOOGLE_API_KEY = "ADD YOUR GOOGLE API KEY HERE"
        @JvmStatic
        val YOUTUBE_VIDEO_ID = "e-6eWEhjMa4"
        @JvmStatic
        val YOUTUBE_PLAYLIST = "FLbRbCysswNzO9vIb1ASdNyg"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layout = layoutInflater.inflate(R.layout.activity_youtube, null) as ConstraintLayout
        setContentView(layout)
        
        val playerView = YouTubePlayerView(this)
        playerView.layoutParams = ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        layout.addView(playerView)
        playerView.initialize(GOOGLE_API_KEY, this)
    }

    override fun onInitializationSuccess(
        provider: YouTubePlayer.Provider?,
        player: YouTubePlayer?,
        wasRestored: Boolean
    ) {
        Log.d(TAG, "onInitializationSuccess: provider is ${provider?.javaClass}")
        Toast.makeText(this, "Initialized Youtube successfully", Toast.LENGTH_LONG).show()
        player?.setPlayerStateChangeListener(playerStateChangeListener)!!
        player.setPlaybackEventListener(playbackEventListener)
        if (!wasRestored) {
            player.cueVideo(YOUTUBE_VIDEO_ID)
        }
    }

    override fun onInitializationFailure(
        provider: YouTubePlayer.Provider?,
        result: YouTubeInitializationResult?
    ) {
        val REQUEST_CODE = 1

        if (result?.isUserRecoverableError!!) {
            result.getErrorDialog(this, REQUEST_CODE).show()
        } else {
            val errorMessage = String.format("There was an error initializing the YoutubePlayer (%1\$s)", result.toString())
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
        }
    }

    private val playbackEventListener = object : YouTubePlayer.PlaybackEventListener {
        override fun onPlaying() {
            Toast.makeText(this@YoutubeActivity, "Video is playing ok", Toast.LENGTH_LONG).show()
        }

        override fun onPaused() {
            Toast.makeText(this@YoutubeActivity, "Video was paused", Toast.LENGTH_LONG).show()
        }

        override fun onStopped() {
            Toast.makeText(this@YoutubeActivity, "Video was stopped", Toast.LENGTH_LONG).show()
        }

        override fun onBuffering(p0: Boolean) {

        }

        override fun onSeekTo(p0: Int) {

        }
    }

    private val playerStateChangeListener = object : YouTubePlayer.PlayerStateChangeListener {
        override fun onLoading() {

        }

        override fun onLoaded(p0: String?) {

        }

        override fun onAdStarted() {
            Toast.makeText(this@YoutubeActivity, "Ad started while playing video", Toast.LENGTH_LONG).show()
        }

        override fun onVideoStarted() {
            Toast.makeText(this@YoutubeActivity, "Video has started playing", Toast.LENGTH_LONG).show()
        }

        override fun onVideoEnded() {
            Toast.makeText(this@YoutubeActivity, "Video has ended playing", Toast.LENGTH_LONG).show()
        }

        override fun onError(p0: YouTubePlayer.ErrorReason?) {

        }
    }
}