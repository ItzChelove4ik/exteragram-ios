package org.webrtc.voiceengine;

import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.os.Build;
import androidx.annotation.Keep;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import java.util.Timer;
import java.util.TimerTask;
import okio.Buffer$$ExternalSyntheticBUOutline2;
import org.webrtc.ContextUtils;
import org.webrtc.Logging;
import org.webrtc.MediaStreamTrack;

@Keep
/* loaded from: classes7.dex */
public class WebRtcAudioManager {
    private static final int BITS_PER_SAMPLE = 16;
    private static final boolean DEBUG = false;
    private static final int DEFAULT_FRAME_PER_BUFFER = 256;
    private static final String TAG = "WebRtcAudioManager";
    private static final boolean blacklistDeviceForAAudioUsage = true;
    private static boolean blacklistDeviceForOpenSLESUsage;
    private static boolean blacklistDeviceForOpenSLESUsageIsOverridden;
    private static boolean useStereoInput;
    private static boolean useStereoOutput;
    private boolean aAudio;
    private final AudioManager audioManager;
    private boolean hardwareAEC;
    private boolean hardwareAGC;
    private boolean hardwareNS;
    private boolean initialized;
    private int inputBufferSize;
    private int inputChannels;
    private boolean lowLatencyInput;
    private boolean lowLatencyOutput;
    private final long nativeAudioManager;
    private int nativeChannels;
    private int nativeSampleRate;
    private int outputBufferSize;
    private int outputChannels;
    private boolean proAudio;
    private int sampleRate;
    private final VolumeLogger volumeLogger;

    public static class VolumeLogger {
        private static final String THREAD_NAME = "WebRtcVolumeLevelLoggerThread";
        private static final int TIMER_PERIOD_IN_SECONDS = 30;
        private final AudioManager audioManager;
        private Timer timer;

        public class LogVolumeTask extends TimerTask {
            private final int maxRingVolume;
            private final int maxVoiceCallVolume;
            final /* synthetic */ VolumeLogger this$0;

            public LogVolumeTask(VolumeLogger r1, int r2, int r3) {
                this.this$0 = r1;
                this.maxRingVolume = r2;
                this.maxVoiceCallVolume = r3;
            }

            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                int r0 = VolumeLogger.m22433$$Nest$fgetaudioManager(this.this$0).getMode();
                if (r0 != 1) goto L7;
                Logging.d(WebRtcAudioManager.TAG, "STREAM_RING stream volume: " + VolumeLogger.m22433$$Nest$fgetaudioManager(this.this$0).getStreamVolume(2) + " (max=" + this.maxRingVolume + ")");
                return;
            L7:
                if (r0 != 3) goto L10;
                Logging.d(WebRtcAudioManager.TAG, "VOICE_CALL stream volume: " + VolumeLogger.m22433$$Nest$fgetaudioManager(this.this$0).getStreamVolume(0) + " (max=" + this.maxVoiceCallVolume + ")");
                return;
            }
        }

        /* renamed from: -$$Nest$fgetaudioManager, reason: not valid java name */
        public static /* bridge */ /* synthetic */ AudioManager m22433$$Nest$fgetaudioManager(VolumeLogger r0) {
            return r0.audioManager;
        }

        /* renamed from: -$$Nest$mstop, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m22434$$Nest$mstop(VolumeLogger r0) {
            r0.stop();
        }

        public VolumeLogger(AudioManager r1) {
            this.audioManager = r1;
        }

        private void stop() {
            Timer r0 = this.timer;
            if (r0 == null) goto L6;
            r0.cancel();
            this.timer = null;
            return;
        }

        public void start() {
            Timer r0 = new Timer(THREAD_NAME);
            this.timer = r0;
            r0.schedule(new LogVolumeTask(this, this.audioManager.getStreamMaxVolume(2), this.audioManager.getStreamMaxVolume(0)), 0, NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS);
        }
    }

    public WebRtcAudioManager(long r17) {
        Logging.d(TAG, "ctor" + WebRtcAudioUtils.getThreadInfo());
        this.nativeAudioManager = r17;
        AudioManager r1 = (AudioManager) ContextUtils.getApplicationContext().getSystemService(MediaStreamTrack.AUDIO_TRACK_KIND);
        this.audioManager = r1;
        this.volumeLogger = new VolumeLogger(r1);
        storeAudioParameters();
        nativeCacheAudioParameters(this.sampleRate, this.outputChannels, this.inputChannels, this.hardwareAEC, this.hardwareAGC, this.hardwareNS, this.lowLatencyOutput, this.lowLatencyInput, this.proAudio, this.aAudio, this.outputBufferSize, this.inputBufferSize, r17);
        WebRtcAudioUtils.logAudioState(TAG);
    }

    private static void assertTrue(boolean r0) {
        if (r0 == false) goto L4;
        return;
    L4:
        Buffer$$ExternalSyntheticBUOutline2.m("Expected condition to be true");
    }

    private void dispose() {
        Logging.d(TAG, "dispose" + WebRtcAudioUtils.getThreadInfo());
        if (this.initialized == true) goto L5;
        return;
    L5:
        VolumeLogger.m22434$$Nest$mstop(this.volumeLogger);
    }

    private int getLowLatencyInputFramesPerBuffer() {
        assertTrue(isLowLatencyInputSupported());
        return getLowLatencyOutputFramesPerBuffer();
    }

    private int getLowLatencyOutputFramesPerBuffer() {
        assertTrue(isLowLatencyOutputSupported());
        String r1 = this.audioManager.getProperty("android.media.property.OUTPUT_FRAMES_PER_BUFFER");
        if (r1 != null) goto L7;
        return 256;
    L7:
        return Integer.parseInt(r1);
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

    private int getNativeOutputSampleRate() {
        if (WebRtcAudioUtils.runningOnEmulator() == false) goto L7;
        Logging.d(TAG, "Running emulator, overriding sample rate to 8 kHz.");
        return 8000;
    L7:
        if (WebRtcAudioUtils.isDefaultSampleRateOverridden() == false) goto L10;
        Logging.d(TAG, "Default sample rate is overriden to " + WebRtcAudioUtils.getDefaultSampleRateHz() + " Hz");
        return WebRtcAudioUtils.getDefaultSampleRateHz();
    L10:
        int r4 = getSampleRateForApiLevel();
        Logging.d(TAG, "Sample rate is set to " + r4 + " Hz");
        return r4;
    }

    private int getSampleRateForApiLevel() {
        String r1 = this.audioManager.getProperty("android.media.property.OUTPUT_SAMPLE_RATE");
        if (r1 != null) goto L7;
        return WebRtcAudioUtils.getDefaultSampleRateHz();
    L7:
        return Integer.parseInt(r1);
    }

    public static synchronized boolean getStereoInput() {
        monitor-enter(WebRtcAudioManager.class);
        boolean r1 = useStereoInput;     // Catch: Throwable -> L7
        monitor-exit(WebRtcAudioManager.class);
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    public static synchronized boolean getStereoOutput() {
        monitor-enter(WebRtcAudioManager.class);
        boolean r1 = useStereoOutput;     // Catch: Throwable -> L7
        monitor-exit(WebRtcAudioManager.class);
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    private boolean hasEarpiece() {
        return ContextUtils.getApplicationContext().getPackageManager().hasSystemFeature("android.hardware.telephony");
    }

    private boolean init() {
        Logging.d(TAG, "init" + WebRtcAudioUtils.getThreadInfo());
        if (this.initialized == false) goto L5;
        return true;
    L5:
        Logging.d(TAG, "audio mode is: " + WebRtcAudioUtils.modeToString(this.audioManager.getMode()));
        this.initialized = true;
        this.volumeLogger.start();
        return true;
    }

    private boolean isAAudioSupported() {
        Logging.w(TAG, "AAudio support is currently disabled on all devices!");
        return false;
    }

    private static boolean isAcousticEchoCancelerSupported() {
        return WebRtcAudioEffects.canUseAcousticEchoCanceler();
    }

    private boolean isCommunicationModeEnabled() {
        if (this.audioManager.getMode() != 3) goto L6;
        return true;
    L6:
        return false;
    }

    private boolean isDeviceBlacklistedForOpenSLESUsage() {
        if (blacklistDeviceForOpenSLESUsageIsOverridden == false) goto L5;
        boolean r1 = blacklistDeviceForOpenSLESUsage;
    L6:
        if (r1 == false) goto L10;
        Logging.d(TAG, Build.MODEL + " is blacklisted for OpenSL ES usage!");
        return true;
    L10:
        return true;
    L5:
        r1 = WebRtcAudioUtils.deviceIsBlacklistedForOpenSLESUsage();
        goto L6
    }

    private boolean isLowLatencyOutputSupported() {
        return ContextUtils.getApplicationContext().getPackageManager().hasSystemFeature("android.hardware.audio.low_latency");
    }

    private static boolean isNoiseSuppressorSupported() {
        return WebRtcAudioEffects.canUseNoiseSuppressor();
    }

    private boolean isProAudioSupported() {
        return ContextUtils.getApplicationContext().getPackageManager().hasSystemFeature("android.hardware.audio.pro");
    }

    private native void nativeCacheAudioParameters(int r1, int r2, int r3, boolean r4, boolean r5, boolean r6, boolean r7, boolean r8, boolean r9, boolean r10, int r11, int r12, long r13);

    public static synchronized void setBlacklistDeviceForOpenSLESUsage(boolean r2) {
        monitor-enter(WebRtcAudioManager.class);
        blacklistDeviceForOpenSLESUsageIsOverridden = true;     // Catch: Throwable -> L8
        blacklistDeviceForOpenSLESUsage = r2;     // Catch: Throwable -> L8
        monitor-exit(WebRtcAudioManager.class);
        return;
    L8:
        th = move-exception;
        throw th;
    }

    public static synchronized void setStereoInput(boolean r4) {
        monitor-enter(WebRtcAudioManager.class);
        Logging.w(TAG, "Overriding default input behavior: setStereoInput(" + r4 + ')');     // Catch: Throwable -> L7
        useStereoInput = r4;     // Catch: Throwable -> L7
        monitor-exit(WebRtcAudioManager.class);
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public static synchronized void setStereoOutput(boolean r4) {
        monitor-enter(WebRtcAudioManager.class);
        Logging.w(TAG, "Overriding default output behavior: setStereoOutput(" + r4 + ')');     // Catch: Throwable -> L7
        useStereoOutput = r4;     // Catch: Throwable -> L7
        monitor-exit(WebRtcAudioManager.class);
        return;
    L7:
        th = move-exception;
        throw th;
    }

    private void storeAudioParameters() {
        int r1 = 1;
        if (getStereoOutput() == false) goto L5;
        int r0 = 2;
    L6:
        this.outputChannels = r0;
        if (getStereoInput() == false) goto L9;
        r1 = 2;
    L9:
        this.inputChannels = r1;
        this.sampleRate = getNativeOutputSampleRate();
        this.hardwareAEC = isAcousticEchoCancelerSupported();
        this.hardwareAGC = false;
        this.hardwareNS = isNoiseSuppressorSupported();
        this.lowLatencyOutput = isLowLatencyOutputSupported();
        this.lowLatencyInput = isLowLatencyInputSupported();
        this.proAudio = isProAudioSupported();
        this.aAudio = isAAudioSupported();
        if (this.lowLatencyOutput == false) goto L12;
        int r02 = getLowLatencyOutputFramesPerBuffer();
    L13:
        this.outputBufferSize = r02;
        if (this.lowLatencyInput == false) goto L16;
        int r03 = getLowLatencyInputFramesPerBuffer();
    L17:
        this.inputBufferSize = r03;
        return;
    L16:
        r03 = getMinInputFrameSize(this.sampleRate, this.inputChannels);
        goto L17
    L12:
        r02 = getMinOutputFrameSize(this.sampleRate, this.outputChannels);
        goto L13
    L5:
        r0 = 1;
        goto L6
    }

    public boolean isLowLatencyInputSupported() {
        return isLowLatencyOutputSupported();
    }
}
