package com.example.hush

import android.media.MediaPlayer
import android.os.Environment
//import org.junit.Assert.assertThat
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.io.File
//import javax.sql.DataSource
import org.junit.Before
import org.junit.Test
import java.io.IOException

@RunWith(RobolectricTestRunner::class)

class PlayerTest {
    lateinit var testPlayer: Player
    // lateinit var mediaPlayer: MediaPlayer

    @Before
    fun setup() {
        testPlayer = Player()
        // mediaPlayer = MediaPlayer()
       // mediaPlayer = Shadow.newInstanceOf(MediaPlayer::class.java)
        //shadowMediaPlayer = shadowOf(mediaPlayer)
    }

    @Test
    @Throws(Exception::class)
    fun testSetup() {
        // val file: File? = null
        if (::testPlayer.isInitialized) {
            val path = File(Environment.getExternalStorageDirectory().getPath())
            try {
                val file = File.createTempFile("temporary", ".3gp", path)
                if(file != null) {
                    testPlayer.setup(file)
                    testPlayer.play()
                }
            } catch (e: IOException) {
            }
        }
    }


    @Test
    @Throws(Exception::class)
    fun testPlay() {
        val file: File? = null
        if (::testPlayer.isInitialized) {
            if(file != null) {
                testPlayer.setup(file)
                testPlayer.play()
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testPrepareForPlayback() {
        val file: File? = null
        if (::testPlayer.isInitialized) {
            if(file != null) {
                testPlayer.setup(file)
//                mediaPlayer.start()
//                    if (::mediaPlayer.isInitialized) {
//                        mediaPlayer.prepare()
//                    }
               }
            }
        }
    }

