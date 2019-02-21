
import android.R
import android.widget.Button
import android.widget.TextView
import com.example.hush.MainActivity
import org.junit.runner.RunWith
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertThat
import org.junit.Test
import org.junit.Assert
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

//import android.R
//import android.app.Activity
//import android.app.Activity
//import android.widget.TextView
//import org.junit.runner.RunWith
//import org.robolectric.Robolectric
//import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)

class MainActivityTest {

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