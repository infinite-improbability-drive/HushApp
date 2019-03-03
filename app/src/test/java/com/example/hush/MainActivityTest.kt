package com.example.hush

import android.Manifest
import android.R
import android.content.pm.PackageManager
import android.widget.Button
import android.widget.TextView
import com.example.hush.MainActivity
import org.junit.runner.RunWith
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertThat
import org.junit.Test
import org.junit.Assert
import org.junit.Before
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import java.io.IOException
import java.security.Permission

//import android.R
//import android.app.Activity
//import android.app.Activity
//import android.widget.TextView
//import org.junit.runner.RunWith
//import org.robolectric.Robolectric
//import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)

class MainActivityTest {

    lateinit var testMainActivity: MainActivity

    @Before
    fun setup() {
        testMainActivity = MainActivity()
    }

//    @Test
//    @Throws(Exception::class)
//    fun onCreateTest() {
//
//        val activity = Robolectric.setupActivity(MainActivity::class.java)
//        //val testMainActivity = MainActivity.instance.onCreate(null)
//
//        assertNotNull(activity)
//
//    }

    @Test
    @Throws(Exception::class)
    fun onRequestPermissionsResultPermissionGranted() {

        val testRequestPermissions = arrayOf( Manifest.permission.READ_EXTERNAL_STORAGE,
                                          Manifest.permission.RECORD_AUDIO,
                                          Manifest.permission.WRITE_EXTERNAL_STORAGE)
        val testRequestCode = 123
        val testGrantResults = intArrayOf(PackageManager.PERMISSION_GRANTED, PackageManager.PERMISSION_GRANTED,
                PackageManager.PERMISSION_GRANTED)
        val testPermissions = testMainActivity.onRequestPermissionsResult(testRequestCode,
                testRequestPermissions, testGrantResults)

    }

}