
import android.R
import android.widget.TextView
import org.robolectric.Robolectric
import android.app.Activity
import org.robolectric.RobolectricTestRunner
import org.junit.runner.RunWith

import android.R
import android.app.Activity
import android.widget.Button
import android.widget.TextView
import org.junit.Test

import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class MyActivityTest {

    @Test
    @Throws(Exception::class)
    fun clickingButton_shouldChangeResultsViewText() {
        val activity = Robolectric.setupActivity(MyActivity::class.java)

        val button = activity.findViewById(R.id.press_me_button) as Button
        val results = activity.findViewById(R.id.results_text_view) as TextView

        button.performClick()
        assertThat(results.text.toString(), equalTo("Testing Android Rocks!"))
    }
}