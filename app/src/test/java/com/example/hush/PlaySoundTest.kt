package com.example.hush

import android.app.Activity
import android.content.Intent
import android.media.AudioFormat
import android.media.AudioManager
import android.media.AudioTrack
import org.junit.runner.RunWith
import org.junit.Test
import org.junit.Before
import org.robolectric.RobolectricTestRunner
import android.media.MediaRecorder
import android.os.Bundle
import org.junit.Assert.*
import org.robolectric.Robolectric
import org.robolectric.Shadows
import java.io.File

@RunWith(RobolectricTestRunner::class)
class PlaySoundTest {

    lateinit var testPlaySound: PlaySound
    lateinit var testMainActivity: MainActivity
    lateinit var activity: Activity

    @Before
    fun setup() {
        testMainActivity = MainActivity()
        val intent = Intent()
        val bundle = Bundle()
        intent.putExtras(bundle)
        val controller = Robolectric.buildActivity(MainActivity::class.java, intent).create()
        activity = controller.get() as Activity

        controller.start()

        testMainActivity.playSound = PlaySound(400)
        testPlaySound = PlaySound(400)
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