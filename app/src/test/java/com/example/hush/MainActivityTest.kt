package com.example.hush

import android.Manifest
import android.content.pm.PackageManager
import android.widget.Button
import org.junit.runner.RunWith
import org.junit.Test
import org.junit.Before
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.shadows.ShadowToast
import android.app.Activity
import android.os.Bundle
import android.content.Intent
import android.media.MediaPlayer
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import org.junit.Assert.*
import org.robolectric.Shadows
import org.robolectric.shadows.ShadowApplication

@RunWith(RobolectricTestRunner::class)
class MainActivityTest {

    //Can't get GrantPermissionRule to work. Not sure why. Followed everything found online with no success.
    //Can't test anything else in this class without this functionality.
    //@get:Rule var permissionRule = GrantPermissionRule.grant(android.Manifest.permission.RECORD_AUDIO)

    lateinit var testMainActivity: MainActivity
    lateinit var application: ShadowApplication
    lateinit var activity: Activity

    @Before
    fun setup() {
        testMainActivity = MainActivity()
        val intent = Intent()
        val bundle = Bundle()
        intent.putExtras(bundle)
        val controller = Robolectric.buildActivity(MainActivity::class.java, intent).create()
        activity = controller.get() as Activity

        controller.start()

        application = Shadows.shadowOf(activity.application)
    }

    @Test
    @Throws(Exception::class)
    fun testLateInit() {

        testMainActivity.recorder = Recorder()
        testMainActivity.player = Player()
        testMainActivity.playSound = PlaySound(400)
        testMainActivity.playSound2 = PlaySound(400)
        testMainActivity.button1 = Button(activity)
        testMainActivity.button2 = Button(activity)
        testMainActivity.button3 = Button(activity)
        testMainActivity.button4 = Button(activity)
        testMainActivity.button5 = Button(activity)
        testMainActivity.buttonMinus = Button(activity)
        testMainActivity.buttonPlus = Button(activity)
        testMainActivity.buttonMinusA = Button(activity)
        testMainActivity.buttonPlusA = Button(activity)
        testMainActivity.tv1 = TextView(activity)

        assertNotNull(testMainActivity.recorder)
        assertNotNull(testMainActivity.player)
        assertNotNull(testMainActivity.playSound)
        assertNotNull(testMainActivity.playSound2)
        assertNotNull(testMainActivity.button1)
        assertNotNull(testMainActivity.button2)
        assertNotNull(testMainActivity.button3)
        assertNotNull(testMainActivity.button4)
        assertNotNull(testMainActivity.button5)
        assertNotNull(testMainActivity.buttonMinus)
        assertNotNull(testMainActivity.buttonPlus)
        assertNotNull(testMainActivity.buttonMinusA)
        assertNotNull(testMainActivity.buttonPlusA)
        assertNotNull(testMainActivity.tv1)
    }

    @Test
    @Throws(Exception::class)
    fun onCreateTest() {

        assertFalse(activity.isFinishing)
    }

    @Test
    @Throws(Exception::class)
    fun onRequestPermissionsResultPermissionGranted() {

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

        assertFalse(activity.isFinishing)
        assertNull(latestToast)
    }

    @Test
    @Throws(Exception::class)
    fun onRequestPermissionsResultTwoGrantedPermissions() {

        val testRequestCode = 123
        val testRequestPermissions = arrayOf( Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
        //Changed to have 2 permissions granted instead of 3
        val testGrantResults = intArrayOf(PackageManager.PERMISSION_GRANTED,
                PackageManager.PERMISSION_GRANTED)

        activity.onRequestPermissionsResult(testRequestCode, testRequestPermissions,
                testGrantResults)
        val latestToast = ShadowToast.getTextOfLatestToast()
        val message = "You must give permissions to use this app. App is exiting."

        assertTrue(activity.isFinishing)
        assertNotNull(latestToast)
        assertEquals(latestToast, message)
    }

    @Test
    @Throws(Exception::class)
    fun onRequestPermissionsResultPermissionDenied() {

        val testRequestCode = 123
        val testRequestPermissions = arrayOf( Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
        //Changed to have 1 of the permissions to be denied.
        val testGrantResults = intArrayOf(PackageManager.PERMISSION_DENIED,
                PackageManager.PERMISSION_GRANTED,
                PackageManager.PERMISSION_GRANTED)

        activity.onRequestPermissionsResult(testRequestCode, testRequestPermissions,
                testGrantResults)
        val latestToast = ShadowToast.getTextOfLatestToast()
        val message = "You must give permissions to use this app. App is exiting."

        assertTrue(activity.isFinishing)
        assertNotNull(latestToast)
        assertEquals(latestToast, message)
    }

    @Test
    @Throws(Exception::class)
    fun clickStartButton() {

        val activity = Robolectric.setupActivity(MainActivity::class.java)
        assertNotNull(activity)
        activity.setContentView(R.layout.activity_main)
        val button = activity.findViewById(R.id.btnStart) as Button
        assertNotNull(button)
        button.performClick()
    }

    @Test
    @Throws(Exception::class)
    fun clickStopButton() {

        val activity = Robolectric.setupActivity(MainActivity::class.java)
        assertNotNull(activity)
        activity.setContentView(R.layout.activity_main)
        val button = activity.findViewById(R.id.btnStop) as Button
        assertNotNull(button)
        button.performClick()
    }

    @Test
    @Throws(Exception::class)
    fun clickMenuButton() {

        val activity = Robolectric.setupActivity(MainActivity::class.java)
        assertNotNull(activity)
        activity.setContentView(R.layout.content_main)
    }

    @Test
    @Throws(Exception::class)
    fun testOnCompletion(){
        testMainActivity.button1 = Button(activity)
        testMainActivity.button2 = Button(activity)
        testMainActivity.button3 = Button(activity)
        testMainActivity.tv1 = TextView(activity)
        var mediaPlayer = MediaPlayer()

        testMainActivity.onCompletion(mediaPlayer)

        assertTrue(testMainActivity.button1.isEnabled)
        assertTrue(testMainActivity.button2.isEnabled)
        assertTrue(testMainActivity.button3.isEnabled)
        assertEquals("Ready", testMainActivity.tv1.text)

    }
}

