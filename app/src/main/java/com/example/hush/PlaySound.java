package com.example.hush;

import android.app.Activity;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class PlaySound {
    // originally from http://marblemice.blogspot.com/2010/04/generate-and-play-tone-in-android.html
    // and modified by Steve Pomeroy <steve@staticfree.info>
    private final int duration = 3; // seconds
    private final int sampleRate = 144000;
    private final int numSamples = duration * sampleRate; // 18000
    private final double sample[] = new double[numSamples];
    private final double freqOfTone = 400; // hz

    private final double periodInSeconds = 1 / freqOfTone;
    private final double periodInSamples = sampleRate / freqOfTone;
    private final double numPeriods = numSamples / periodInSamples;

    private final byte generatedSnd[] = new byte[2 * numSamples];
    final AudioTrack audioTrack = new AudioTrack(
            AudioManager.STREAM_MUSIC,
            sampleRate,
            AudioFormat.CHANNEL_OUT_MONO,
            AudioFormat.ENCODING_PCM_16BIT,
            generatedSnd.length,
            AudioTrack.MODE_STATIC);

    Handler handler = new Handler();


    final Thread thread = new Thread(new Runnable() {
        public void run() {
            genTone();
            handler.post(new Runnable() {

                public void run() {
                    playSound();
                }
            });
        }
    });


    public void play() {
        // Use a new tread as this can take a while
//        final Thread thread = new Thread(new Runnable() {
//            public void run() {
//                genTone();
//                handler.post(new Runnable() {
//
//                    public void run() {
//                        playSound();
//                    }
//                });
//            }
//        });

        thread.start();
        Log.d("bufferSizeInFrames:", Integer.toString(audioTrack.getBufferSizeInFrames()));
    }

    public void stop() {
        audioTrack.stop();
        thread.stop();
    }


    void genTone(){
        // fill out the array
        for (int i = 0; i < numSamples; ++i) {
            sample[i] = Math.sin(2 * Math.PI * i / (sampleRate/freqOfTone));
        }

        // convert to 16 bit pcm sound array
        // assumes the sample buffer is normalised.
        int idx = 0;
        for (final double dVal : sample) {
            // scale to maximum amplitude
            final short val = (short) ((dVal * 32767));
            // in 16 bit wav PCM, first byte is the low order byte
            generatedSnd[idx++] = (byte) (val & 0x00ff);
            generatedSnd[idx++] = (byte) ((val & 0xff00) >>> 8);

        }
    }

    public void phaseShift(int position) {
        Log.d("position:", Integer.toString(position));
        Log.d("periodInSamples:", Integer.toString((int) periodInSamples));
        Log.d("starting frame:", Integer.toString(position));
        Log.d("ending frame:", Integer.toString((int) (sample.length - periodInSamples + position)));

        audioTrack.stop();
        // audioTrack.setPlaybackHeadPosition((int) ((360 - position) / periodInSamples));
        audioTrack.setLoopPoints(position, (int) (sample.length - periodInSamples + position), -1);
        audioTrack.play();
    }

    void playSound(){
        Log.d("starting frame:", Integer.toString(0));
        Log.d("ending frame:", Integer.toString(generatedSnd.length / 2));

        audioTrack.write(generatedSnd, 0, generatedSnd.length);
        audioTrack.setLoopPoints(0, generatedSnd.length / 2, -1);
        // audioTrack.setLoopPoints((int) ((period) * ((float) position / 360)), sample.length - ((int) period * (position / 360)), -1);
        audioTrack.play();
    }
}
