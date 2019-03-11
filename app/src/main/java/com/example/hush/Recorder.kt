package com.example.hush

import android.media.MediaRecorder
import android.os.Environment
import java.io.File
import java.io.IOException

class Recorder {
    lateinit var recorder: MediaRecorder
    lateinit var file: File

    fun setup() {
        recorder = MediaRecorder()
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC)
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)

        val path = File(Environment.getExternalStorageDirectory().getPath())
        try {
            file = File.createTempFile("temporary", ".3gp", path)
        } catch (e: IOException) {
        }

        recorder.setOutputFile(file.absolutePath)
        try {
            recorder.prepare()
        } catch (e: IOException) {
        }

    }

    fun record() {
        recorder.start()
    }

    fun stop() {
        recorder.stop()
        recorder.release()
    }
}