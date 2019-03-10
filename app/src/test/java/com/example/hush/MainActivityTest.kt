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
import org.junit.Assert.*
import org.robolectric.RuntimeEnvironment
import org.robolectric.Shadows
import org.robolectric.shadow.api.Shadow

@RunWith(RobolectricTestRunner::class)

class MainActivityTest {

    lateinit var testMainActivity: MainActivity

    @Before
    fun setup() {
        testMainActivity = MainActivity()
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

        assertNull(latestToast)
    }

    @Test
    @Throws(Exception::class)
    fun onRequestPermissionsResultTwoGrantedPermissions() {

        val intent = Intent()
        val bundle = Bundle()
        intent.putExtras(bundle)
        val controller = Robolectric.buildActivity(MainActivity::class.java, intent).create()
        val activity = controller.get() as Activity

        val testRequestCode = 123
        val testRequestPermissions = arrayOf( Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
        //Changed to have 2 permissions granted instead of 3
        val testGrantResults = intArrayOf(PackageManager.PERMISSION_GRANTED,
                PackageManager.PERMISSION_GRANTED)

        controller.start()

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

        val intent = Intent()
        val bundle = Bundle()
        intent.putExtras(bundle)
        val controller = Robolectric.buildActivity(MainActivity::class.java, intent).create()
        val activity = controller.get() as Activity

        val testRequestCode = 123
        val testRequestPermissions = arrayOf( Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
        //Changed to have 1 of the permissions to be denied.
        val testGrantResults = intArrayOf(PackageManager.PERMISSION_DENIED,
                PackageManager.PERMISSION_GRANTED,
                PackageManager.PERMISSION_GRANTED)

        controller.start()

        activity.onRequestPermissionsResult(testRequestCode, testRequestPermissions,
                testGrantResults)
        val latestToast = ShadowToast.getTextOfLatestToast()
        val message = "You must give permissions to use this app. App is exiting."

        assertTrue(activity.isFinishing)
        assertNotNull(latestToast)
        assertEquals(latestToast, message)
    }
}