package com.example.hush

import java.io.File
import android.media.MediaPlayer
import java.io.IOException

class Player {
    lateinit var player: MediaPlayer

    fun setup(file: File) {
        player = MediaPlayer()
        try {
            player.setDataSource(file.absolutePath)
        } catch (e: IOException) {
        }

        try {
            player.prepare()
        } catch (e: IOException) {
        }

    }

    fun play() {
        player.start()
    }
}