package org.webrtc.audio;

import android.content.Context;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import org.webrtc.CalledByNative;
import org.webrtc.Logging;
import org.webrtc.MediaStreamTrack;

/* loaded from: classes6.dex */
class WebRtcAudioManager {
    private static final int BITS_PER_SAMPLE = 16;
    private static final int DEFAULT_FRAME_PER_BUFFER = 256;
    private static final int DEFAULT_SAMPLE_RATE_HZ = 16000;
    private static final String TAG = "WebRtcAudioManagerExternal";

    public WebRtcAudioManager() {
    }

    @CalledByNative
    public static AudioManager getAudioManager(Context r1) {
        return (AudioManager) r1.getSystemService(MediaStreamTrack.AUDIO_TRACK_KIND);
    }

    @CalledByNative
    public static int getInputBufferSize(Context r0, AudioManager r1, int r2, int r3) {
        if (isLowLatencyInputSupported(r0) == false) goto L7;
        return getLowLatencyFramesPerBuffer(r1);
    L7:
        return getMinInputFrameSize(r2, r3);
    }

    private static int getLowLatencyFramesPerBuffer(AudioManager r1) {
        String r12 = r1.getProperty("android.media.property.OUTPUT_FRAMES_PER_BUFFER");
        if (r12 != null) goto L7;
        return 256;
    L7:
        return Integer.parseInt(r12);
    }

    private static int getMinInputFrameSize(int r2, int r3) {
        int r0 = r3 * 2;
        if (r3 != 1) goto L5;
        int r32 = 16;
    L7:
        return AudioRecord.getMinBufferSize(r2, r32, 2) / r0;
    L5:
        r32 = 12;
        goto L7
    }

    private static int getMinOutputFrameSize(int r2, int r3) {
        int r0 = r3 * 2;
        if (r3 != 1) goto L5;
        int r32 = 4;
    L7:
        return AudioTrack.getMinBufferSize(r2, r32, 2) / r0;
    L5:
        r32 = 12;
        goto L7
    }

    @CalledByNative
    public static int getOutputBufferSize(Context r0, AudioManager r1, int r2, int r3) {
        if (isLowLatencyOutputSupported(r0) == false) goto L7;
        return getLowLatencyFramesPerBuffer(r1);
    L7:
        return getMinOutputFrameSize(r2, r3);
    }

    @CalledByNative
    public static int getSampleRate(AudioManager r3) {
        if (WebRtcAudioUtils.runningOnEmulator() == false) goto L6;
        Logging.d(TAG, "Running emulator, overriding sample rate to 8 kHz.");
        return 8000;
    L6:
        int r32 = getSampleRateForApiLevel(r3);
        Logging.d(TAG, "Sample rate is set to " + r32 + " Hz");
        return r32;
    }

    private static int getSampleRateForApiLevel(AudioManager r1) {
        String r12 = r1.getProperty("android.media.property.OUTPUT_SAMPLE_RATE");
        if (r12 != null) goto L7;
        return 16000;
    L7:
        return Integer.parseInt(r12);
    }

    @CalledByNative
    public static boolean isLowLatencyInputSupported(Context r0) {
        return isLowLatencyOutputSupported(r0);
    }

    @CalledByNative
    public static boolean isLowLatencyOutputSupported(Context r1) {
        return r1.getPackageManager().hasSystemFeature("android.hardware.audio.low_latency");
    }
}
