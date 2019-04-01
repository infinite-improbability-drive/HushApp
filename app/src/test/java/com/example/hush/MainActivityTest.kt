package com.example.hush

import android.Manifest
import android.R
import android.content.pm.PackageManager
import android.widget.Button
import android.widget.TextView
import android.view.View
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
import com.google.common.base.Predicates.equalTo
import org.junit.Assert.*
import org.robolectric.RuntimeEnvironment
import org.robolectric.Shadows
import org.robolectric.shadow.api.Shadow

import kotlinx.android.synthetic.main.activity_main.*
import org.robolectric.shadows.ShadowActivity
import org.robolectric.shadows.ShadowIntent


//

//import com.google.common.Truth.assertThat
import com.example.hush.test.R
import org.robolectric.Shadows.shadowOf
import org.robolectric.shadows.ShadowMediaPlayer.State.END
import org.robolectric.shadows.ShadowMediaPlayer.State.ERROR
import org.robolectric.shadows.ShadowMediaPlayer.State.IDLE
import org.robolectric.shadows.ShadowMediaPlayer.State.INITIALIZED
import org.robolectric.shadows.ShadowMediaPlayer.State.PAUSED
import org.robolectric.shadows.ShadowMediaPlayer.State.PLAYBACK_COMPLETED
import org.robolectric.shadows.ShadowMediaPlayer.State.PREPARED
import org.robolectric.shadows.ShadowMediaPlayer.State.PREPARING
import org.robolectric.shadows.ShadowMediaPlayer.State.STARTED
import org.robolectric.shadows.ShadowMediaPlayer.State.STOPPED
import org.robolectric.shadows.ShadowMediaPlayer.addException
import org.robolectric.shadows.util.DataSource.toDataSource
import android.R
import android.widget.LinearLayout


//



//import android.os.Bundle

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

        val testRequestPermissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE,
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
        val testRequestPermissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE,
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
        val testRequestPermissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE,
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


    // my addition

    // Works
    @Test
    @Throws(Exception::class)
    fun clickingStartButtonShouldStartNewActivity() {
        val button = testMainActivity.findViewById<Button>(R.id.button1)
        assertNotNull(button)
        button.performClick()

        val showActivity: ShadowActivity = Shadows.shadowOf(testMainActivity)
        val intent: Intent = showActivity.nextStartedActivity
        val shadowIntent: ShadowIntent = Shadows.shadowOf(intent)
        assertEquals(shadowIntent.intentClass.name, (MainActivity::class.java!!.getName()))
    }

    // works
    @Test
    @Throws(Exception::class)
    fun projectShouldHaveCorrectAppName() {


        //val hello = testMainActivity.getResources().getString(R.string.autofill)
        //val here = "HushApp"
        val hello = testMainActivity.getResources().getString(R.string.unknownName)
        //val actual = testMainActivity.getResources().getText(hashCode())
        //val hello = testMainActivity.getResources().getText(R.string.("hello_world"))
        //val hello = testMainActivity.getApplicationContext().getString(R.string.hello_world) //."HushApp")
        assertEquals("unknownName", hello)
        //Assert.assertEquals("context text", contextService.getString(R.string.unknownName))

        // assertEquals("com.example.hush", appContext.getPackageName())
        //assertEquals("com.example.hush", testMainActivity.getPackageName())

        // Assert.assertEquals("context text", contextService.getString(R.string.context_text))

        // getResources().getText(R.string.hello_world) : will return String
        // Toast.makeText(getApplicationContext(), getResources().getText(R.string.hello_world), Toast.LENGTH_LONG).show();
        // getResources().getText(R.string.hello_world)
        // getApplicationContext.getResource().getString(R.String.Whatever_String_you_want)
    }

    // works

    @Test
    @Throws(Exception::class)
    fun validateTextViewPresentOrNot() {
        ////val textView = testMainActivity.findViewById<TextView>(R.id.textView) // this is the correct form, does not work.
        val textView = testMainActivity.findViewById<TextView>(R.id.button1)
       // val stringValue = textView.text.toString()
       // assertEquals("Hello World", stringValue)
       // assertNotNull(textView)
       // assertTrue("MyRestaurants" == textView.text.toString()) // to check that textView is not empty

        //assertEquals("text should be empty", "", textView.getText())

        assertNotNull("TextView is null", textView)

        assertTrue("TextView's text does not match.", "Hello Robolectric!".equals(textView.getText().toString()));

    }

    // should work
    @Test
    @Throws(Exception::class)
    fun buttonClickShouldStartNewActivity() {
        // val button = testMainActivity.findViewById(R.id.startNextActivity) as Button
        val button = testMainActivity.findViewById(R.id.button2) as Button
        button.performClick()
        val intent = shadowOf(testMainActivity).peekNextStartedActivity()
        assertEquals(Button::class.java!!.getCanonicalName(), intent.getComponent()!!.getClassName())
    }

    // for onCompletion

    // works but still see for code coverage
    @Test
    @Throws(Exception::class)
    fun onCompletionShouldNotBeNull() {
        val button = testMainActivity.findViewById<Button>(R.id.button1)
        button.performClick()
        assertNotNull(testMainActivity)
        assertNotNull(button)
    }


    // another one

    @Test
    fun testLayoutOnCreate() {
        assertEquals(R.id.test_layout_root, shadowOf(testMainActivity).getContentView().getId());
    }


    @Test
    fun ensureTextView() {
        //Check text inside TextView
        onView(withId(R.id.text_hello)).check(matches(withText("Hello Android!")));

    }
        /*

    @Test
    @Throws(Exception::class)
    //@Config(minSdk = M)
    fun getPermission_shouldReturnRequestedPermissions() {
        // GIVEN
        val permission = arrayOf(Manifest.permission.CAMERA)
        val requestCode = 123
        val activity = Robolectric.setupActivity(Activity::class.java)

        // WHEN
        activity.requestPermissions(permission, requestCode)

        // THEN
        val request = shadowOf(activity).lastRequestedPermission
        assertEquals(request.requestCode, requestCode)
        assertTrue((request.requestedPermissions).equals(permission))
    }



}



