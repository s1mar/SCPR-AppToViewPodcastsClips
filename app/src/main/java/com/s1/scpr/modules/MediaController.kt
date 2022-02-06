package com.s1.scpr.modules

import android.media.MediaPlayer
import android.media.AudioAttributes
import android.util.Log
import com.s1.scpr.interfaces.Function
import java.io.IOException

class MediaController(private val musicInterface: Function<Boolean>) {
    private val TAG = MediaController::class.java.simpleName
    private var mediaPlayer: MediaPlayer?
    fun start(url: String?) {
        try {
            mediaPlayer!!.reset()
            mediaPlayer!!.setDataSource(url)
        } catch (e: IOException) {
            Log.e(TAG, e.message!!)
        }
        mediaPlayer!!.prepareAsync()
        musicInterface.call(true) //True means that the music is being prepared before playback
        mediaPlayer!!.setOnPreparedListener { mediaPlayer ->
            musicInterface.call(false) //False means that the music has been prepared
            mediaPlayer.start()
        }
    }

    fun play() {
        mediaPlayer!!.start()
    }

    fun pause() {
        mediaPlayer!!.pause()
    }

    val isPlaying: Boolean
        get() = mediaPlayer!!.isPlaying

    fun onStop() {
        mediaPlayer!!.release()
        mediaPlayer = null
    }

    init {
        mediaPlayer = MediaPlayer()
        mediaPlayer!!.setAudioAttributes(
            AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .build()
        )
    }
}