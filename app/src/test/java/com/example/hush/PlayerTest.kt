package com.example.hush

import android.media.MediaPlayer
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.io.File
//import javax.sql.DataSource
import org.junit.Before
import org.junit.Test
//import org.robolectric.Shadows.shadowOf

@RunWith(RobolectricTestRunner::class)

class PlayerTest {
    lateinit var testPlayer: Player
    lateinit var mediaPlayer: MediaPlayer

    @Before
    fun setup(){
        testPlayer = Player()
        mediaPlayer = MediaPlayer()
       // mediaPlayer = Shadow.newInstanceOf(MediaPlayer::class.java)
        //shadowMediaPlayer = shadowOf(mediaPlayer)
    }

    @Test
    @Throws(Exception::class)

    fun testPlay(){
        val file: File? = null
        if (::testPlayer.isInitialized) {
            if(file != null) {
                testPlayer.setup(file)
                testPlayer.play()
            }
        }
    }

}