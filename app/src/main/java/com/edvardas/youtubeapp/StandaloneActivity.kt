package com.edvardas.youtubeapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.youtube.player.YouTubeStandalonePlayer

class StandaloneActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_standalone)

        val btnPlayVideo = findViewById<Button>(R.id.btnPlayVideo)
        val btnPlayList = findViewById<Button>(R.id.btnPlayList)

        btnPlayVideo.setOnClickListener(this)
        btnPlayList.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        var intent: Intent? = null

        when(view?.id) {
            R.id.btnPlayVideo -> {
                intent = YouTubeStandalonePlayer.createVideoIntent(this, YoutubeActivity.GOOGLE_API_KEY, YoutubeActivity.YOUTUBE_VIDEO_ID, 0, true, false)
            }

            R.id.btnPlayList -> {
                intent = YouTubeStandalonePlayer.createPlaylistIntent(this, YoutubeActivity.GOOGLE_API_KEY, YoutubeActivity.YOUTUBE_PLAYLIST, 0, 0, true, false)
            }

            else -> {}
        }

        if (intent != null) {
            startActivity(intent)
        } else {
            throw RuntimeException("Intent is null")
        }
    }
}