package com.example.hush

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.Menu
import android.view.MenuItem
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import android.app.Activity
import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import android.view.KeyEvent
import android.widget.Button
import android.util.Log
import kotlin.experimental.and

/**
 *
 * @author RAHUL BARADIA
 */

class MainActivity : AppCompatActivity() {
    private var recorder: AudioRecord? = null
    private var recordingThread: Thread? = null
    private var isRecording = false

    internal var BufferElements2Rec = 1024 // want to play 2048 (2K) since 2 bytes we use only 1024
    internal var BytesPerElement = 2 // 2 bytes in 16bit format

    private val btnClick = View.OnClickListener { v ->
        when (v.id) {
            R.id.btnStart -> {
                enableButtons(true)
                startRecording()
            }
            R.id.btnStop -> {
                enableButtons(false)
                stopRecording()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        setButtonHandlers()
        enableButtons(false)

        val bufferSize = AudioRecord.getMinBufferSize(RECORDER_SAMPLERATE,
                RECORDER_CHANNELS, RECORDER_AUDIO_ENCODING)

        //        FloatingActionButton fab = findViewById(R.id.fab);
        //        fab.setOnClickListener(new View.OnClickListener() {
        //            @Override
        //            public void onClick(View view) {
        //                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        //                        .setAction("Action", null).show();
        //            }
        //        });
    }

    private fun setButtonHandlers() {
        findViewById<View>(R.id.btnStart).setOnClickListener(btnClick)
        findViewById<View>(R.id.btnStop).setOnClickListener(btnClick)
    }

    private fun enableButton(id: Int, isEnable: Boolean) {
        findViewById<View>(id).isEnabled = isEnable
    }

    private fun enableButtons(isRecording: Boolean) {
        enableButton(R.id.btnStart, !isRecording)
        enableButton(R.id.btnStop, isRecording)
    }

    private fun startRecording() {

        recorder = AudioRecord(MediaRecorder.AudioSource.MIC,
                RECORDER_SAMPLERATE, RECORDER_CHANNELS,
                RECORDER_AUDIO_ENCODING, BufferElements2Rec * BytesPerElement)

        recorder!!.startRecording()
        isRecording = true
        recordingThread = Thread(Runnable { writeAudioDataToFile() }, "AudioRecorder Thread")
        recordingThread!!.start()
    }

    //convert short to byte
    private fun short2byte(sData: ShortArray): ByteArray {
        val shortArrsize = sData.size
        val bytes = ByteArray(shortArrsize * 2)
        for (i in 0 until shortArrsize) {
            bytes[i * 2] = (sData[i] and 0x00FF).toByte()
            bytes[i * 2 + 1] = (sData[i] and  8).toByte()
            sData[i] = 0
        }
        return bytes

    }

    private fun writeAudioDataToFile() {
        // Write the output audio in byte

        val filePath = "/sdcard/voice8K16bitmono.pcm"
        val sData = ShortArray(BufferElements2Rec)

        var os: FileOutputStream? = null
        try {
            os = FileOutputStream(filePath)
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }

        while (isRecording) {
            // gets the voice output from microphone to byte format

            recorder!!.read(sData, 0, BufferElements2Rec)
            println("Short wirting to file$sData")
            try {
                // // writes the data to file from buffer
                // // stores the voice buffer
                val bData = short2byte(sData)
                os!!.write(bData, 0, BufferElements2Rec * BytesPerElement)
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
        try {
            os!!.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    private fun stopRecording() {
        // stops the recording activity
        if (null != recorder) {
            isRecording = false
            recorder!!.stop()
            recorder!!.release()
            recorder = null
            recordingThread = null
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish()
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)

    }

    companion object {

        private val RECORDER_SAMPLERATE = 8000
        private val RECORDER_CHANNELS = AudioFormat.CHANNEL_IN_MONO
        private val RECORDER_AUDIO_ENCODING = AudioFormat.ENCODING_PCM_16BIT
    }
}

//private infix fun Short.(i: Int): Any {
//
//}
