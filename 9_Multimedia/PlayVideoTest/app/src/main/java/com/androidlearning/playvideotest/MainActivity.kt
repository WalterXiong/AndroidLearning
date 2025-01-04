package com.androidlearning.playvideotest

import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.VideoView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var videoView: VideoView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        videoView = findViewById(R.id.videoView)
        val play: Button = findViewById(R.id.play)
        val pause: Button = findViewById(R.id.pause)
        val replay: Button = findViewById(R.id.replay)

        val uri = Uri.parse("android.resource://$packageName/${R.raw.video}")

        videoView.setVideoURI(uri)


        play.setOnClickListener {
            if (!videoView.isPlaying) {
                videoView.start()
            }
        }


        pause.setOnClickListener {
            if (videoView.isPlaying) {
                videoView.pause()
            }
        }

        replay.setOnClickListener {
            if (videoView.isPlaying) {
                videoView.resume()
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        videoView.suspend()
    }
}