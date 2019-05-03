// originally from http://marblemice.blogspot.com/2010/04/generate-and-play-tone-in-android.html
// and modified by Steve Pomeroy <steve@staticfree.info>

package com.example.hush;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.os.Handler;
import android.util.Log;

public class PlaySound {

    private final int duration = 3; // seconds
    private final int sampleRate = 144000;
    private final int numSamples = duration * sampleRate;
    private final double[] sample = new double[numSamples];
    private final double freqOfTone = 400; // hz
    private final double periodInSamples = sampleRate / freqOfTone;
    private final byte[] generatedSnd = new byte[2 * numSamples];
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
        Log.d("play/thread " + thread.getId() + " state", thread.getState().toString());
        thread.start();
        Log.d("play/thread " + thread.getId() + " state", thread.getState().toString());
        Log.d("play/bufferSizeInFrames", Integer.toString(audioTrack.getBufferSizeInFrames()));
    }

    public void stop() {
        audioTrack.stop();
        Log.d("stop/thread " + thread.getId() + " state", thread.getState().toString());
        thread.interrupt();
        Log.d("stop/thread " + thread.getId() + " state", thread.getState().toString());
    }

    void genTone(){
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
        Log.d("phaseShift/thread " + thread.getId() + " state", thread.getState().toString());
        Log.d("phaseShift/position", Integer.toString(position));
        Log.d("phaseShift/periodInSamples", Integer.toString((int) periodInSamples));
        Log.d("phaseShift/starting frame", Integer.toString(position));
        Log.d("phaseShift/ending frame", Integer.toString((int) (sample.length - periodInSamples + position)));

        audioTrack.stop();
        audioTrack.setPlaybackHeadPosition(360 - position);
        audioTrack.setLoopPoints(position, (sampleRate + position), -1);
        audioTrack.play();
    }

    public void changeVolume(int position) {
        float z = (float) position / 100;
        audioTrack.setVolume(z);
    }

    void playSound(){
        Log.d("playSound/thread " + thread.getId() + " state", thread.getState().toString());
        Log.d("playSound/starting frame", Integer.toString(0));
        Log.d("playSound/ending frame", Integer.toString(generatedSnd.length / 2));

        audioTrack.write(generatedSnd, 0, generatedSnd.length);
        audioTrack.setLoopPoints(0, generatedSnd.length / 2, -1);
        audioTrack.play();
    }
}
