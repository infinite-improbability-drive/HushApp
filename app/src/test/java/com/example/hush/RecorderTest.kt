package com.example.hush

import org.junit.runner.RunWith
import org.junit.Test
import org.junit.Before
import org.robolectric.RobolectricTestRunner
import android.media.MediaRecorder
import org.junit.Assert.*
import java.io.File

@RunWith(RobolectricTestRunner::class)
class RecorderTest {

    lateinit var testRecorder: Recorder

    @Before
    fun setup() {
        testRecorder = Recorder()
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