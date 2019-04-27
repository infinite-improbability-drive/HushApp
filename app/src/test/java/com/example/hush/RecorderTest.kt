package com.example.hush

import android.Manifest
import android.R
import android.content.pm.PackageManager
import android.widget.Button
import android.widget.TextView
import com.example.hush.MainActivity
import org.junit.runner.RunWith
import org.junit.Test
import org.junit.Assert
import org.junit.Before
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.shadows.ShadowToast
import java.io.IOException
import java.security.Permission
import android.app.Activity
import android.app.Application
import org.robolectric.android.controller.ActivityController
import android.os.Bundle
import android.content.Intent
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.support.v4.content.ContextCompat
import org.junit.Assert.*
import org.junit.Rule
import org.robolectric.RuntimeEnvironment
import org.robolectric.Shadows
import org.robolectric.shadow.api.Shadow
import java.io.File

@RunWith(RobolectricTestRunner::class)

class RecorderTest {

    lateinit var testRecorder: Recorder

    @Before
    fun setup() {
        testRecorder = Recorder()
    }

    @Test
    @Throws(Exception::class)
    fun onCreateTest() {
        testRecorder.setup()
    }

    @Test
    @Throws(Exception::class)
    fun testFile() {
        testRecorder.file = File("some filepath")
        assertNotNull(testRecorder.file)
    }

    @Test
    @Throws(Exception::class)
    fun testMediaRecord() {
        testRecorder.recorder = MediaRecorder()
        assertNotNull(testRecorder.recorder)
    }

    @Test
    @Throws(Exception::class)
    fun testRecord() {
        testRecorder.setup()
        testRecorder.record()
    }

    @Test
    @Throws(Exception::class)
    fun testStop() {
        testRecorder.setup()
        testRecorder.stop()
    }
}