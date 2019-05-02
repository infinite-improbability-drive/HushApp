package com.example.hush

import android.media.AudioFormat
import android.media.AudioManager
import android.media.AudioTrack
import org.junit.runner.RunWith
import org.junit.Test
import org.junit.Before
import org.robolectric.RobolectricTestRunner
import android.media.MediaRecorder
import org.junit.Assert.*
import java.io.File

@RunWith(RobolectricTestRunner::class)
class PlaySoundTest {

    lateinit var testPlaySound: PlaySound

    @Before
    fun setup() {
        testPlaySound = PlaySound()
    }

    @Test
    @Throws(Exception::class)
    fun testPlay() {

        var play = testPlaySound.play()
        assertNotNull(play)
        assertTrue(testPlaySound.thread.isAlive)
    }

//    @Test
//    @Throws(Exception::class)
//    fun testStop() {
//
//        testPlaySound.play()
//        Thread.sleep(10000)
//        var stop = testPlaySound.stop()
//        assertNotNull(stop)
//    }

    @Test
    @Throws(Exception::class)
    fun testGenTone() {

        var tone = testPlaySound.genTone()
        assertNotNull(tone)
    }

//    @Test
//    @Throws(Exception::class)
//    fun testPhaseShift() {
//
//        var phase = testPlaySound.phaseShift(0)
//        assertNotNull(phase)
//    }

    @Test
    @Throws(Exception::class)
    fun testChangeVolume() {

        var volume = testPlaySound.changeVolume(50)
        assertNotNull(volume)
    }

//    @Test
//    @Throws(Exception::class)
//    fun testPlaySound() {
//
//        var play = testPlaySound.playSound()
//        assertNotNull(play)
//    }
}