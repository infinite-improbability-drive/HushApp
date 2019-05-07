# HushApp

HushApp is a noise canceling app for Android smartphones in development. This app currently creates two tones. The user can adjust the frequency, amplitude, and phase of one of the signals to cancel the second signal. When played with a music app, this app can cancel outside noise.

Language: Kotlin and Java

API level developed for: 28 - Pie

Continuous Integration: CircleCI

Static Code Analysis: Detekt

Unit Testing Framework: Robolectric version 4.1

Integration Testing: Appium

Core Team: Nick Barnes Heather Minke Nila Niroula John Samson




# Noise Cancellation Resources

https://en.wikipedia.org/wiki/Active_noise_control


# Android Studio

https://developer.android.com/studio/
 

# Android Documentation

https://developer.android.com/reference/android/media/AudioRecord

https://stackoverflow.com/questions/38033068/android-audiorecord-wont-initialize

https://developer.android.com/reference/android/media/MediaPlayer

https://developer.android.com/reference/android/media/AudioTrack

https://developer.android.com/guide/topics/permissions/overview

https://developer.android.com/training/permissions/requesting


# Kotlin Documentation

https://kotlinlang.org/

https://kotlinlang.org/docs/reference/


# Audio Recording Examples

https://www.simplifiedcoding.net/audio-recording-android-example/

https://www.techotopia.com/index.php/Android_Audio_Recording_and_Playback_using_MediaPlayer_and_MediaRecorder

https://inducesmile.com/android/android-voice-recording-application-example/

https://coding180.com/kotlin-android/capturing-audio-using-the-mediarecorder-class/


# Detekt Static Analysis for Android

https://github.com/arturbosch/detekt

To run detekt checks:
- 1. Navigate to repository root folder
- 2. Enter 'gradlew detektGenerateConfig' then 'gradlew detektCheck' to run detekt checks for code smells and the like. 
- 3. Enter 'gradlew tasks' to see all gradle tasks


# Testing Resources

https://stuff.mit.edu/afs/sipb/project/android/docs/tools/testing/activity_test.html


# Robolectric Unit Testing for Android

http://robolectric.org/

http://robolectric.org/writing-a-test/

https://www.coderefer.com/android-unit-testing-robolectric/

https://proandroiddev.com/robolectric-testing-with-androidjunitrunner-86292bceef25

https://android.jlelse.eu/how-to-write-android-unit-tests-using-robolectric-27341d530613


# Appium Automated Testing for Android

https://appium.io/downloads

https://github.com/appium/appium-desktop

https://stackoverflow.com/questions/24813589/how-to-setup-appium-on-mac-os-to-run-automated-tests-from-java-classes-on-androi

https://www.toolsqa.com/mobile-automation/appium/download-and-install-appium-desktop-client/

https://wiki.saucelabs.com/display/DOCS/Getting+Started+with+Appium+for+Mobile+Native+Application+Testing

To run Appium Tests:
 
- 1. Download Appium Desktop Server here https://github.com/appium/appium-desktop/releases
- 2. Run Appium Desktop server using localhost address (127.0.0.1) and the port specified (4723) in ExampleAppiumTest.java
- 3. Run the app in the emulator
- 4. Run Appium Tests


# How to set up Environment Variables (ANDROID_HOME and JAVA_HOME) on Windows/Mac

http://www.software-testing-tutorials-automation.com/2015/09/set-androidhome-and-path-environment.html

https://javatutorial.net/set-java-home-windows-10

https://stackoverflow.com/questions/19986214/setting-android-home-enviromental-variable-on-mac-os-x#19986294

https://www.mkyong.com/java/how-to-set-java_home-environment-variable-on-mac-os-x/


# CircleCI Continuous Integration for Android

https://github.com/marketplace/category/mobile-ci

https://circleci.com/docs/2.0/

https://circleci.com/docs/2.0/writing-yaml/

 
# Superpowered Audio Library

https://superpowered.com/docs/index.html

 

 