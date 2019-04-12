package com.example.hush

import android.media.AudioFormat
import android.media.MediaRecorder
import android.os.Environment
import java.io.File
import java.io.IOException


class Recorder {
    lateinit var recorder: MediaRecorder
    lateinit var file: File
    private val RECORDER_AUDIO_ENCODING = AudioFormat.ENCODING_PCM_16BIT

    fun setup() {
        recorder = MediaRecorder()
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC)
        recorder.setOutputFormat(RECORDER_AUDIO_ENCODING)
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)

        setupFileForRecording()
        prepareForRecording()
    }

    private fun setupFileForRecording() {
        val path = File(Environment.getExternalStorageDirectory().getPath())
        try {
            file = File.createTempFile("temporary", ".wav", path)
        } catch (e: IOException) {
        }
        recorder.setOutputFile(file.absolutePath)
    }

    private fun prepareForRecording() {
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
