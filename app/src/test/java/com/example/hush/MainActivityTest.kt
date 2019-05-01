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
import org.robolectric.shadows.ShadowApplication

@RunWith(RobolectricTestRunner::class)

class MainActivityTest {

    //Can't get this to work. Not sure why. Followed everything found online with no success.
    //Can't test anything else in this class without this functionality.
    //@get:Rule var permissionRule = GrantPermissionRule.grant(android.Manifest.permission.RECORD_AUDIO)

    lateinit var testMainActivity: MainActivity
    lateinit var application: ShadowApplication
    lateinit var activity: Activity

    @Before
    fun setup() {
        testMainActivity = MainActivity()

        //Activity variables
        val intent = Intent()
        val bundle = Bundle()
        intent.putExtras(bundle)
        val controller = Robolectric.buildActivity(MainActivity::class.java, intent).create()
        activity = controller.get() as Activity

        //Start activity
        controller.start()

        application = Shadows.shadowOf(activity.application)
    }

    @Test
    @Throws(Exception::class)
    fun testLateInit() {
        // assertNull(testMainActivity.recorder)
       //  assertEquals(testMainActivity.recorder, null)
        testMainActivity.recorder = Recorder()
        testMainActivity.player = Player()
        testMainActivity.playSound = PlaySound()
//        testMainActivity.button1.performClick()

        assertNotNull(testMainActivity.recorder)
        assertNotNull(testMainActivity.player)
        assertNotNull(testMainActivity.playSound)
    }


    @Test
    @Throws(Exception::class)
    fun onCreateTest() {

        //Asserts
        assertFalse(activity.isFinishing)
    }

    @Test
    @Throws(Exception::class)
    fun onRequestPermissionsResultPermissionGranted() {

        //Method Variables
        val testRequestPermissions = arrayOf( Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
        val testRequestCode = 123
        val testGrantResults = intArrayOf(PackageManager.PERMISSION_GRANTED,
                PackageManager.PERMISSION_GRANTED,
                PackageManager.PERMISSION_GRANTED)
        testMainActivity.onRequestPermissionsResult(testRequestCode, testRequestPermissions,
                testGrantResults)
        val latestToast = ShadowToast.getTextOfLatestToast()

        //Asserts
        assertFalse(activity.isFinishing)
        assertNull(latestToast)
    }

    @Test
    @Throws(Exception::class)
    fun onRequestPermissionsResultTwoGrantedPermissions() {

        //Method Variables
        val testRequestCode = 123
        val testRequestPermissions = arrayOf( Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
        //Changed to have 2 permissions granted instead of 3
        val testGrantResults = intArrayOf(PackageManager.PERMISSION_GRANTED,
                PackageManager.PERMISSION_GRANTED)

        //Call method that is being tested.
        activity.onRequestPermissionsResult(testRequestCode, testRequestPermissions,
                testGrantResults)
        val latestToast = ShadowToast.getTextOfLatestToast()
        val message = "You must give permissions to use this app. App is exiting."

        //Asserts
        assertTrue(activity.isFinishing)
        assertNotNull(latestToast)
        assertEquals(latestToast, message)
    }

    @Test
    @Throws(Exception::class)
    fun onRequestPermissionsResultPermissionDenied() {

        //Method Variables
        val testRequestCode = 123
        val testRequestPermissions = arrayOf( Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
        //Changed to have 1 of the permissions to be denied.
        val testGrantResults = intArrayOf(PackageManager.PERMISSION_DENIED,
                PackageManager.PERMISSION_GRANTED,
                PackageManager.PERMISSION_GRANTED)

        //Call the method that is being tested
        activity.onRequestPermissionsResult(testRequestCode, testRequestPermissions,
                testGrantResults)
        val latestToast = ShadowToast.getTextOfLatestToast()
        val message = "You must give permissions to use this app. App is exiting."

        //Asserts
        assertTrue(activity.isFinishing)
        assertNotNull(latestToast)
        assertEquals(latestToast, message)
    }

    @Test
    @Throws(Exception::class)
    fun clickStartButton() {
        val activity = Robolectric.setupActivity(MainActivity::class.java)
        assertNotNull(activity)
        activity.setContentView(com.example.hush.R.layout.activity_main)
        val button = activity.findViewById(com.example.hush.R.id.btnStart) as Button
        assertNotNull(button)
        button.performClick()
    }

    @Test
    @Throws(Exception::class)
    fun clickStopButton() {
        val activity = Robolectric.setupActivity(MainActivity::class.java)
        assertNotNull(activity)
        activity.setContentView(com.example.hush.R.layout.activity_main)
        val button = activity.findViewById(com.example.hush.R.id.btnStop) as Button
        assertNotNull(button)
        button.performClick()
    }

    @Test
    @Throws(Exception::class)
    fun clickMenuButton() {
        val activity = Robolectric.setupActivity(MainActivity::class.java)
        assertNotNull(activity)
        activity.setContentView(com.example.hush.R.layout.content_main)
//        val button = activity.findViewById(com.example.hush.R.id.btnStop) as Button
//        assertNotNull(button)
//        button.performClick()
    }
}