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
import android.support.v4.content.ContextCompat
import org.junit.Assert.*
import org.junit.Rule
import org.robolectric.RuntimeEnvironment
import org.robolectric.Shadows
import org.robolectric.shadow.api.Shadow

@RunWith(RobolectricTestRunner::class)

class RecorderTest {

    //@get:Rule var permissionRule = GrantPermissionRule.grant(android.Manifest.permission.RECORD_AUDIO)

    lateinit var testRecorder: Recorder

    @Before
    fun setup() {
        testRecorder = Recorder()
    }

    @Test
    @Throws(Exception::class)
    fun onCreateTest() {
        testRecorder.setup()

        //Activity variables
//        val intent = Intent()
//        val bundle = Bundle()
//        intent.putExtras(bundle)
//        val controller = Robolectric.setupContentProvider(Recorder::class.java).create()
//        ' val controller = Robolectric.buildActivity(MainActivity::class.java, intent).create()
//        val activity = controller.get() as Activity
//
//        //Start activity
//        controller.start()
//
//        //Asserts
//        assertFalse(activity.isFinishing)
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

//    @Test
//    fun onCompletionTest() {
//
//        //Method Variables
//        var mp = MediaPlayer()
//
//        //Activity Variables
//        val intent = Intent()
//        val bundle = Bundle()
//        intent.putExtras(bundle)
//        val controller = Robolectric.buildActivity(MainActivity::class.java, intent).create()
//        val activity = controller.get() as Activity
//
//        controller.start()
//
//        //Call method that is being tested.
//        testMainActivity.onCompletion(mp)
//
//        //Asserts
//        assertNotNull(mp)
//
//    }
//
//    @Test
//    @Throws(Exception::class)
//    fun onRequestPermissionsResultPermissionGranted() {
//
//        //Method Variables
//        val testRequestPermissions = arrayOf( Manifest.permission.READ_EXTERNAL_STORAGE,
//                Manifest.permission.RECORD_AUDIO,
//                Manifest.permission.WRITE_EXTERNAL_STORAGE)
//        val testRequestCode = 123
//        val testGrantResults = intArrayOf(PackageManager.PERMISSION_GRANTED,
//                PackageManager.PERMISSION_GRANTED,
//                PackageManager.PERMISSION_GRANTED)
//        testMainActivity.onRequestPermissionsResult(testRequestCode, testRequestPermissions,
//                testGrantResults)
//        val latestToast = ShadowToast.getTextOfLatestToast()
//
//        //Asserts
//        assertNull(latestToast)
//    }
//
//    @Test
//    @Throws(Exception::class)
//    fun onRequestPermissionsResultTwoGrantedPermissions() {
//
//        //Activity Variables
//        val intent = Intent()
//        val bundle = Bundle()
//        intent.putExtras(bundle)
//        val controller = Robolectric.buildActivity(MainActivity::class.java, intent).create()
//        val activity = controller.get() as Activity
//
//        //Method Variables
//        val testRequestCode = 123
//        val testRequestPermissions = arrayOf( Manifest.permission.READ_EXTERNAL_STORAGE,
//                Manifest.permission.RECORD_AUDIO,
//                Manifest.permission.WRITE_EXTERNAL_STORAGE)
//        //Changed to have 2 permissions granted instead of 3
//        val testGrantResults = intArrayOf(PackageManager.PERMISSION_GRANTED,
//                PackageManager.PERMISSION_GRANTED)
//
//        controller.start()
//
//        //Call method that is being tested.
//        activity.onRequestPermissionsResult(testRequestCode, testRequestPermissions,
//                testGrantResults)
//        val latestToast = ShadowToast.getTextOfLatestToast()
//        val message = "You must give permissions to use this app. App is exiting."
//
//        //Asserts
//        assertTrue(activity.isFinishing)
//        assertNotNull(latestToast)
//        assertEquals(latestToast, message)
//    }
//
//    @Test
//    @Throws(Exception::class)
//    fun onRequestPermissionsResultPermissionDenied() {
//
//        //Activity Variables
//        val intent = Intent()
//        val bundle = Bundle()
//        intent.putExtras(bundle)
//        val controller = Robolectric.buildActivity(MainActivity::class.java, intent).create()
//        val activity = controller.get() as Activity
//
//        //Method Variables
//        val testRequestCode = 123
//        val testRequestPermissions = arrayOf( Manifest.permission.READ_EXTERNAL_STORAGE,
//                Manifest.permission.RECORD_AUDIO,
//                Manifest.permission.WRITE_EXTERNAL_STORAGE)
//        //Changed to have 1 of the permissions to be denied.
//        val testGrantResults = intArrayOf(PackageManager.PERMISSION_DENIED,
//                PackageManager.PERMISSION_GRANTED,
//                PackageManager.PERMISSION_GRANTED)
//
//        controller.start()
//
//        //Call the method that is being tested
//        activity.onRequestPermissionsResult(testRequestCode, testRequestPermissions,
//                testGrantResults)
//        val latestToast = ShadowToast.getTextOfLatestToast()
//        val message = "You must give permissions to use this app. App is exiting."
//
//        //Asserts
//        assertTrue(activity.isFinishing)
//        assertNotNull(latestToast)
//        assertEquals(latestToast, message)
//    }
//
//    @Test
//    @Throws(Exception::class)
//    fun clickStartButton() {
//        val activity = Robolectric.setupActivity(MainActivity::class.java)
//        assertNotNull(activity)
//        activity.setContentView(com.example.hush.R.layout.activity_main)
//        val button = activity.findViewById(com.example.hush.R.id.btnStart) as Button
//        assertNotNull(button)
//        button.performClick()
//    }
//
//    @Test
//    @Throws(Exception::class)
//    fun clickStopButton() {
//        val activity = Robolectric.setupActivity(MainActivity::class.java)
//        assertNotNull(activity)
//        activity.setContentView(com.example.hush.R.layout.activity_main)
//        val button = activity.findViewById(com.example.hush.R.id.btnStop) as Button
//        assertNotNull(button)
//        button.performClick()
//    }
//
//    @Test
//    @Throws(Exception::class)
//    fun clickMenuButton() {
//        val activity = Robolectric.setupActivity(MainActivity::class.java)
//        assertNotNull(activity)
//        activity.setContentView(com.example.hush.R.layout.content_main)
////        val button = activity.findViewById(com.example.hush.R.id.btnStop) as Button
////        assertNotNull(button)
////        button.performClick()
//    }
}