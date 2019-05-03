package com.example.hush

import org.junit.runner.RunWith
import org.junit.Test
import org.junit.Before
import org.robolectric.RobolectricTestRunner
import android.media.MediaRecorder
import android.os.Environment
import org.junit.Assert.*
import java.io.File

@RunWith(RobolectricTestRunner::class)
class PlayerTest {

    lateinit var testPlayer: Player

    @Before
    fun setup() {
        testPlayer = Player()
    }

    @Test
    @Throws(Exception::class)
    fun testFile() {
        val path = File(Environment.getExternalStorageDirectory().getPath())
        val file = File.createTempFile("temporary", ".3gp", path)
        testPlayer.setup(file)
    }

}