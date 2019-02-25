package com.example.hush

import android.Manifest
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.Menu
import android.view.MenuItem
import android.app.Activity
import android.content.pm.PackageManager
import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.support.annotation.NonNull
import android.support.annotation.RequiresApi
import android.support.v4.content.ContextCompat
import android.view.KeyEvent
import android.widget.Button
import android.util.Log
import android.widget.Toast
import java.io.*
import kotlin.experimental.and
private const val RECORD_AUDIO_REQUEST_CODE =123
/**
 *
 * @author RAHUL BARADIA
 */

class MainActivity : AppCompatActivity() {

    private lateinit var mp: MediaPlayer
    private var recorder: AudioRecord? = null
    private var recordingThread: Thread? = null
    private var isRecording = false
    val sData = ShortArray(BUFFER_ELEMENTS_TO_REC)

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
            R.id.btnPlay -> {
              Log.d("debug", "here2")
              audioPlayer("/sdcard/voice8K16bitmono.pcm")
          }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getPermissionToRecordAudio()
        }

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
        findViewById<View>(R.id.btnPlay).setOnClickListener(btnClick)
    }

    private fun enableButton(id: Int, isEnable: Boolean) {
        findViewById<View>(id).isEnabled = isEnable
    }

    private fun enableButtons(isRecording: Boolean) {
        enableButton(R.id.btnStart, !isRecording)
        enableButton(R.id.btnStop, isRecording)
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    fun getPermissionToRecordAudio() {
        // 1) Use the support library version ContextCompat.checkSelfPermission(...) to avoid
        // checking the build version since Context.checkSelfPermission(...) is only available
        // in Marshmallow
        // 2) Always check for permission (even if permission has already been granted)
        // since the user can revoke permissions at any time through Settings
        if ((ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
                        !== PackageManager.PERMISSION_GRANTED
                        || ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        !== PackageManager.PERMISSION_GRANTED
                        || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        !== PackageManager.PERMISSION_GRANTED))
        {
            // The permission is NOT already granted.
            // Check if the user has been asked about this permission already and denied
            // it. If so, we want to give more explanation about why the permission is needed.
            // Fire off an async request to actually get the permission
            // This will show the standard permission request dialog UI
            requestPermissions(arrayOf<String>(Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    RECORD_AUDIO_REQUEST_CODE)
        }
    }
    // Callback with the request from calling requestPermissions(...)
    @RequiresApi(api = Build.VERSION_CODES.M)
    override fun onRequestPermissionsResult(requestCode:Int,
                                            @NonNull permissions:Array<String>,
                                            @NonNull grantResults:IntArray) {
        // Make sure it's our original READ_CONTACTS request
        if (requestCode == RECORD_AUDIO_REQUEST_CODE)
        {
            if ((grantResults.size == THREE && grantResultsCorrect(grantResults)))
                //Toast.makeText(this, "Record Audio permission granted", Toast.LENGTH_SHORT).show();
            else
            {
                Toast.makeText(this, "You must give permissions to use this app. App is exiting.",
                        Toast.LENGTH_SHORT).show()
                finishAffinity()
            }
        }
    }

    private fun grantResultsCorrect(grantResults: IntArray): Boolean {
      return grantResults[0] == PackageManager.PERMISSION_GRANTED
              && grantResults[1] == PackageManager.PERMISSION_GRANTED
              && grantResults[2] == PackageManager.PERMISSION_GRANTED
    }

    private fun startRecording() {

        recorder = AudioRecord(MediaRecorder.AudioSource.MIC,
                RECORDER_SAMPLERATE, RECORDER_CHANNELS,
                RECORDER_AUDIO_ENCODING, BUFFER_ELEMENTS_TO_REC * bytesPerElement)

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
            bytes[i * 2 + 1] = (sData[i] and 8).toByte()
            sData[i] = 0
        }
        return bytes

    }

    private fun writeAudioDataToFile() {
        // Write the output audio in byte

        val filePath = "/sdcard/voice8K16bitmono.pcm"


        var os: FileOutputStream? = null
        try {
            os = FileOutputStream(filePath)
        } catch (e: FileNotFoundException) {
            println("File path not found")
            e.printStackTrace()
        }

        while (isRecording) {
            // gets the voice output from microphone to byte format

            recorder!!.read(sData, 0, BUFFER_ELEMENTS_TO_REC)
            println("Short writing to file$sData")
            try {
                // // writes the data to file from buffer
                // // stores the voice buffer
                val bData = short2byte(sData)
                os!!.write(bData, 0, BUFFER_ELEMENTS_TO_REC * bytesPerElement)
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

     private fun audioPlayer(path: String){
     //set up MediaPlayer

       val filePath = Environment.getExternalStorageDirectory().toString() + "/sdcard/voice8K16bitmono.pcm"
       Log.d("SetDatasource path", filePath)
       val filePath1 = File(filePath)
       filePath1.createNewFile();
       val thing = FileInputStream(filePath1)
       mp = MediaPlayer()
        try {
            mp.setDataSource(thing.fd)
            print(filePath)
            mp.prepare()
            mp.start()
              }
        catch (e: Exception) {
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
        internal var BUFFER_ELEMENTS_TO_REC = 1024 // want to play 2048 (2K) since 2 bytes we use only 1024
        internal var bytesPerElement = 2 // 2 bytes in 16bit format
        private val THREE = 3
    }
}

//private infix fun Short.(i: Int): Any {
//
//}
