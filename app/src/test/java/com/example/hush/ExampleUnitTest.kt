package com.example.hush

import android.widget.TextView
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
// Nila's  addition
//@RunWith(RobolectticGradleTestRunner.class)
//@Config(constants = BuildConfig::class)
//@Config(constants = BuildConfig.class, SDK = Build.VERSION_CODES.ANDROID)


class ExampleUnitTest {
    // My addition
    /* @Test
    fun shoudNotBeNull() throws Exception{
        assertNotNull(enableButton);
    }
    */

    /*
    // My addition
    @Test
    fun itShouldDisplayStartButton(){
        val activity = Robolectric.setupActivity(MainActivity::class .java)
        val textView = activity.findViewById(R.id.main_text) as TextView

        assetThat(textView.text).isEqualTo("Start")
    }
    */

    @Test
    fun addition_isCorrect() {
        assertEquals(4, (2 + 2).toLong())
    }

    @Test
    fun onCreate() {

    }
    /*  // My addition
    @Test
    fun enableButton_isWorking() {
        assertNotNull(enableButton)
    }
*/
}

/*
    // Nila's addition
    public class MainActivityTest{
        mainActivity activity;

        @Before
        public void setup(){
            activity = new MainActivity();
            activity.OnCreate(null);
        }
        @Test
        public void editTextUpdatesTextView(){
            // Arrange
            String givenString = "test123";
            activity.editText.setText(givenString);
            // Act
            activity.editText.onEditorAction(EditorInfo.IME_ACTION_DONE);
            // Assert
            String actualString = activity.textView.getText().toz
        }


    }
*/