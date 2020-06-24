package com.edvardas.youtubeapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSingle = findViewById<Button>(R.id.btnPlaySingle)
        val btnStandalone = findViewById<Button>(R.id.btnStandalone)

        btnSingle.setOnClickListener(this)
        btnStandalone.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        var intent: Intent? = null

        when(view?.id) {
            R.id.btnPlaySingle -> {
                intent = Intent(this, YoutubeActivity::class.java)
            }

            R.id.btnStandalone -> {
                intent = Intent(this, StandaloneActivity::class.java)
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