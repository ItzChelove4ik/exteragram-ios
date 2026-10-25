package org.webrtc.audio;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.os.Build;
import android.os.Process;
import java.nio.ByteBuffer;
import okio.Buffer$$ExternalSyntheticBUOutline2;
import org.telegram.messenger.FileLog;
import org.webrtc.CalledByNative;
import org.webrtc.Logging;
import org.webrtc.ThreadUtils;
import org.webrtc.audio.JavaAudioDeviceModule;

/* loaded from: classes6.dex */
class WebRtcAudioTrack {
    private static final int AUDIO_TRACK_START = 0;
    private static final int AUDIO_TRACK_STOP = 1;
    private static final long AUDIO_TRACK_THREAD_JOIN_TIMEOUT_MS = 2000;
    private static final int BITS_PER_SAMPLE = 16;
    private static final int BUFFERS_PER_SECOND = 100;
    private static final int CALLBACK_BUFFER_SIZE_MS = 10;
    private static final int DEFAULT_USAGE = 0;
    private static final String TAG = "WebRtcAudioTrackExternal";
    private final AudioAttributes audioAttributes;
    private final AudioManager audioManager;
    private AudioTrackThread audioThread;
    private AudioTrack audioTrack;
    private ByteBuffer byteBuffer;
    private final Context context;
    private byte[] emptyBytes;
    private final JavaAudioDeviceModule.AudioTrackErrorCallback errorCallback;
    private int initialBufferSizeInFrames;
    private long nativeAudioTrack;
    private volatile boolean speakerMute;
    private final JavaAudioDeviceModule.AudioTrackStateCallback stateCallback;
    private final ThreadUtils.ThreadChecker threadChecker;
    private boolean useLowLatency;
    private final VolumeLogger volumeLogger;

    public class AudioTrackThread extends Thread {
        private LowLatencyAudioBufferManager bufferManager;
        private volatile boolean keepAlive;
        private long lastPlaybackHeadPosition;
        private long lastTimestamp;
        private long targetTimeNs;
        final /* synthetic */ WebRtcAudioTrack this$0;
        private long writtenFrames;

        public AudioTrackThread(WebRtcAudioTrack r1, String r2) {
            this.this$0 = r1;
            super(r2);
            this.keepAlive = true;
            this.writtenFrames = 0;
            this.lastPlaybackHeadPosition = 0;
            this.lastTimestamp = System.nanoTime();
            this.bufferManager = new LowLatencyAudioBufferManager();
        }

        private int writeBytes(AudioTrack r1, ByteBuffer r2, int r3) {
            return r1.write(r2, r3, 0);
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            Process.setThreadPriority(-19);
            Logging.d(WebRtcAudioTrack.TAG, "AudioTrackThread" + WebRtcAudioUtils.getThreadInfo());
            if (WebRtcAudioTrack.m22423$$Nest$fgetaudioTrack(this.this$0).getPlayState() != 3) goto L5;
            boolean r0 = true;
        L6:
            WebRtcAudioTrack.m22431$$Nest$smassertTrue(r0);
            WebRtcAudioTrack.m22429$$Nest$mdoAudioTrackStateCallback(this.this$0, 0);
            int r02 = WebRtcAudioTrack.m22424$$Nest$fgetbyteBuffer(this.this$0).capacity();
            int r2 = WebRtcAudioTrack.m22423$$Nest$fgetaudioTrack(this.this$0).getChannelCount() * 2;
            int r5 = WebRtcAudioTrack.m22423$$Nest$fgetaudioTrack(this.this$0).getSampleRate();
            this.targetTimeNs = System.nanoTime();
        L8:
            if (this.keepAlive == false) goto L32;
            WebRtcAudioTrack.m22432$$Nest$smnativeGetPlayoutData(WebRtcAudioTrack.m22426$$Nest$fgetnativeAudioTrack(this.this$0), r02);
            if (r02 > WebRtcAudioTrack.m22424$$Nest$fgetbyteBuffer(this.this$0).remaining()) goto L12;
            boolean r6 = true;
        L13:
            WebRtcAudioTrack.m22431$$Nest$smassertTrue(r6);
            if (WebRtcAudioTrack.m22427$$Nest$fgetspeakerMute(this.this$0) == false) goto L16;
            WebRtcAudioTrack.m22424$$Nest$fgetbyteBuffer(this.this$0).clear();
            WebRtcAudioTrack.m22424$$Nest$fgetbyteBuffer(this.this$0).put(WebRtcAudioTrack.m22425$$Nest$fgetemptyBytes(this.this$0));
            WebRtcAudioTrack.m22424$$Nest$fgetbyteBuffer(this.this$0).position(0);
        L16:
            int r62 = writeBytes(WebRtcAudioTrack.m22423$$Nest$fgetaudioTrack(this.this$0), WebRtcAudioTrack.m22424$$Nest$fgetbyteBuffer(this.this$0), r02);
            if (r62 == r02) goto L22;
            Logging.e(WebRtcAudioTrack.TAG, "AudioTrack.write played invalid number of bytes: " + r62);
            if (r62 >= 0) goto L22;
            this.keepAlive = false;
            WebRtcAudioTrack.m22430$$Nest$mreportWebRtcAudioTrackError(this.this$0, "AudioTrack.write failed: " + r62);
        L22:
            if (WebRtcAudioTrack.m22428$$Nest$fgetuseLowLatency(this.this$0) == false) goto L24;
            this.bufferManager.maybeAdjustBufferSize(WebRtcAudioTrack.m22423$$Nest$fgetaudioTrack(this.this$0));
        L24:
            WebRtcAudioTrack.m22424$$Nest$fgetbyteBuffer(this.this$0).rewind();
            this.writtenFrames += r62 / r2;
            long r8 = ((this.writtenFrames - WebRtcAudioTrack.m22423$$Nest$fgetaudioTrack(this.this$0).getPlaybackHeadPosition()) * 1000) / r5;
            WebRtcAudioTrack.m22424$$Nest$fgetbyteBuffer(this.this$0).rewind();
            this.targetTimeNs += 10000000;
            long r82 = this.targetTimeNs - System.nanoTime();
            if (r82 > 0) goto L33;
            this.targetTimeNs = System.nanoTime();
            goto L8
        L33:
            Thread.sleep(r82 / 1000000, (int) (r82 % 1000000));     // Catch: InterruptedException -> L29
        L29:
            e = move-exception;
            FileLog.e(e);
            goto L8
        L12:
            r6 = false;
            goto L13
        L32:
            return;
        L5:
            r0 = false;
            goto L6
        }

        public void stopThread() {
            Logging.d(WebRtcAudioTrack.TAG, "stopThread");
            this.keepAlive = false;
        }
    }

    /* renamed from: -$$Nest$fgetaudioTrack, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AudioTrack m22423$$Nest$fgetaudioTrack(WebRtcAudioTrack r0) {
        return r0.audioTrack;
    }

    /* renamed from: -$$Nest$fgetbyteBuffer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ByteBuffer m22424$$Nest$fgetbyteBuffer(WebRtcAudioTrack r0) {
        return r0.byteBuffer;
    }

    /* renamed from: -$$Nest$fgetemptyBytes, reason: not valid java name */
    public static /* bridge */ /* synthetic */ byte[] m22425$$Nest$fgetemptyBytes(WebRtcAudioTrack r0) {
        return r0.emptyBytes;
    }

    /* renamed from: -$$Nest$fgetnativeAudioTrack, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m22426$$Nest$fgetnativeAudioTrack(WebRtcAudioTrack r2) {
        return r2.nativeAudioTrack;
    }

    /* renamed from: -$$Nest$fgetspeakerMute, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m22427$$Nest$fgetspeakerMute(WebRtcAudioTrack r0) {
        return r0.speakerMute;
    }

    /* renamed from: -$$Nest$fgetuseLowLatency, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m22428$$Nest$fgetuseLowLatency(WebRtcAudioTrack r0) {
        return r0.useLowLatency;
    }

    /* renamed from: -$$Nest$mdoAudioTrackStateCallback, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22429$$Nest$mdoAudioTrackStateCallback(WebRtcAudioTrack r0, int r1) {
        r0.doAudioTrackStateCallback(r1);
    }

    /* renamed from: -$$Nest$mreportWebRtcAudioTrackError, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22430$$Nest$mreportWebRtcAudioTrackError(WebRtcAudioTrack r0, String r1) {
        r0.reportWebRtcAudioTrackError(r1);
    }

    /* renamed from: -$$Nest$smassertTrue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22431$$Nest$smassertTrue(boolean r0) {
        assertTrue(r0);
    }

    /* renamed from: -$$Nest$smnativeGetPlayoutData, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22432$$Nest$smnativeGetPlayoutData(long r0, int r2) {
        nativeGetPlayoutData(r0, r2);
    }

    static {
        DEFAULT_USAGE = getDefaultUsageAttribute();
    }

    @CalledByNative
    public WebRtcAudioTrack(Context r9, AudioManager r10) {
        this(r9, r10, null, null, null, false, true);
    }

    public WebRtcAudioTrack(Context r2, AudioManager r3, AudioAttributes r4, JavaAudioDeviceModule.AudioTrackErrorCallback r5, JavaAudioDeviceModule.AudioTrackStateCallback r6, boolean r7, boolean r8) {
        ThreadUtils.ThreadChecker r0 = new ThreadUtils.ThreadChecker();
        this.threadChecker = r0;
        r0.detachThread();
        this.context = r2;
        this.audioManager = r3;
        this.audioAttributes = r4;
        this.errorCallback = r5;
        this.stateCallback = r6;
        if (r8 == false) goto L5;
        VolumeLogger r22 = new VolumeLogger(r3);
    L6:
        this.volumeLogger = r22;
        this.useLowLatency = r7;
        Logging.d(TAG, "ctor" + WebRtcAudioUtils.getThreadInfo());
        return;
    L5:
        r22 = null;
        goto L6
    }

    @CalledByNative
    private int GetPlayoutUnderrunCount() {
        AudioTrack r0 = this.audioTrack;
        if (r0 != null) goto L5;
        return -1;
    L5:
        return r0.getUnderrunCount();
    }

    @TargetApi(29)
    private static AudioAttributes.Builder applyAttributesOnQOrHigher(AudioAttributes.Builder r0, AudioAttributes r1) {
        return r0.setAllowedCapturePolicy(r1.getAllowedCapturePolicy());
    }

    private static void assertTrue(boolean r0) {
        if (r0 == false) goto L4;
        return;
    L4:
        Buffer$$ExternalSyntheticBUOutline2.m("Expected condition to be true");
    }

    private int channelCountToConfiguration(int r1) {
        if (r1 != 1) goto L6;
        return 4;
    L6:
        return 12;
    }

    private static AudioTrack createAudioTrackBeforeOreo(int r8, int r9, int r10, AudioAttributes r11) {
        Logging.d(TAG, "createAudioTrackBeforeOreo");
        logNativeOutputSampleRate(r8);
        return new AudioTrack(getAudioAttributes(r11), new AudioFormat.Builder().setEncoding(2).setSampleRate(r8).setChannelMask(r9).build(), r10, 1, 0);
    }

    @TargetApi(26)
    private static AudioTrack createAudioTrackOnOreoOrHigher(int r2, int r3, int r4, AudioAttributes r5) {
        Logging.d(TAG, "createAudioTrackOnOreoOrHigher");
        logNativeOutputSampleRate(r2);
        return new AudioTrack.Builder().setAudioAttributes(getAudioAttributes(r5)).setAudioFormat(new AudioFormat.Builder().setEncoding(2).setSampleRate(r2).setChannelMask(r3).build()).setBufferSizeInBytes(r4).setPerformanceMode(1).setTransferMode(1).setSessionId(0).build();
    }

    private void doAudioTrackStateCallback(int r3) {
        Logging.d(TAG, "doAudioTrackStateCallback: " + r3);
        JavaAudioDeviceModule.AudioTrackStateCallback r2 = this.stateCallback;
        if (r2 == null) goto L13;
        if (r3 != 0) goto L8;
        r2.onWebRtcAudioTrackStart();
        return;
    L8:
        if (r3 != 1) goto L11;
        r2.onWebRtcAudioTrackStop();
        return;
    L11:
        Logging.e(TAG, "Invalid audio state");
        return;
    }

    @TargetApi(21)
    private static AudioAttributes getAudioAttributes(AudioAttributes r3) {
        AudioAttributes.Builder r0 = new AudioAttributes.Builder().setUsage(DEFAULT_USAGE).setContentType(1);
        if (r3 == null) goto L14;
        if (r3.getUsage() == 0) goto L8;
        r0.setUsage(r3.getUsage());
    L8:
        if (r3.getContentType() == 0) goto L10;
        r0.setContentType(r3.getContentType());
    L10:
        r0.setFlags(r3.getFlags());
        if (Build.VERSION.SDK_INT < 29) goto L14;
        r0 = applyAttributesOnQOrHigher(r0, r3);
    L14:
        return r0.build();
    }

    @CalledByNative
    private int getBufferSizeInFrames() {
        return this.audioTrack.getBufferSizeInFrames();
    }

    private static int getDefaultUsageAttribute() {
        return 2;
    }

    @CalledByNative
    private int getInitialBufferSizeInFrames() {
        return this.initialBufferSizeInFrames;
    }

    @CalledByNative
    private int getStreamMaxVolume() {
        this.threadChecker.checkIsOnValidThread();
        Logging.d(TAG, "getStreamMaxVolume");
        return this.audioManager.getStreamMaxVolume(0);
    }

    @CalledByNative
    private int getStreamVolume() {
        this.threadChecker.checkIsOnValidThread();
        Logging.d(TAG, "getStreamVolume");
        return this.audioManager.getStreamVolume(0);
    }

    @CalledByNative
    private int initPlayout(int r6, int r7, double r8) {
        this.threadChecker.checkIsOnValidThread();
        Logging.d(TAG, "initPlayout(sampleRate=" + r6 + ", channels=" + r7 + ", bufferSizeFactor=" + r8 + ")");
        this.byteBuffer = ByteBuffer.allocateDirect((r7 * 2) * (r6 / 100));
        StringBuilder r0 = new StringBuilder("byteBuffer.capacity: ");
        r0.append(this.byteBuffer.capacity());
        Logging.d(TAG, r0.toString());
        this.emptyBytes = new byte[this.byteBuffer.capacity()];
        nativeCacheDirectBufferAddress(this.nativeAudioTrack, this.byteBuffer);
        int r72 = channelCountToConfiguration(r7);
        int r02 = (int) (AudioTrack.getMinBufferSize(r6, r72, 2) * r8);
        Logging.d(TAG, "minBufferSizeInBytes: " + r02);
        if (r02 >= this.byteBuffer.capacity()) goto L7;
        reportWebRtcAudioTrackInitError("AudioTrack.getMinBufferSize returns an invalid value.");
        return -1;
    L7:
        if (r8 <= 1.0d) goto L10;
        this.useLowLatency = false;
    L10:
        if (this.audioTrack == null) goto L32;
        reportWebRtcAudioTrackInitError("Conflict with existing AudioTrack.");
        return -1;
    L32:
    L18:
        e = move-exception;
        reportWebRtcAudioTrackInitError(e.getMessage());
        releaseAudioResources();
        return -1;
    L14:
        if (this.useLowLatency == true) goto L16;
    L20:
        this.audioTrack = createAudioTrackBeforeOreo(r6, r72, r02, this.audioAttributes);     // Catch: IllegalArgumentException -> L18
    L21:
        AudioTrack r62 = this.audioTrack;
        if (r62 != null) goto L24;
    L28:
        reportWebRtcAudioTrackInitError("Initialization of audio track failed.");
        releaseAudioResources();
        return -1;
    L24:
        if (r62.getState() != 1) goto L28;
        this.initialBufferSizeInFrames = this.audioTrack.getBufferSizeInFrames();
        logMainParameters();
        logMainParametersExtended();
        return r02;
    L16:
        if (Build.VERSION.SDK_INT < 26) goto L20;
        this.audioTrack = createAudioTrackOnOreoOrHigher(r6, r72, r02, this.audioAttributes);     // Catch: IllegalArgumentException -> L18
        goto L21
    }

    private void logBufferCapacityInFrames() {
        Logging.d(TAG, "AudioTrack: buffer capacity in frames: " + this.audioTrack.getBufferCapacityInFrames());
    }

    private void logBufferSizeInFrames() {
        Logging.d(TAG, "AudioTrack: buffer size in frames: " + this.audioTrack.getBufferSizeInFrames());
    }

    private void logMainParameters() {
        Logging.d(TAG, "AudioTrack: session ID: " + this.audioTrack.getAudioSessionId() + ", channels: " + this.audioTrack.getChannelCount() + ", sample rate: " + this.audioTrack.getSampleRate() + ", max gain: " + AudioTrack.getMaxVolume());
    }

    private void logMainParametersExtended() {
        logBufferSizeInFrames();
        logBufferCapacityInFrames();
    }

    private static void logNativeOutputSampleRate(int r3) {
        int r0 = AudioTrack.getNativeOutputSampleRate(0);
        Logging.d(TAG, "nativeOutputSampleRate: " + r0);
        if (r3 == r0) goto L6;
        Logging.w(TAG, "Unable to use fast mode since requested sample rate is not native");
        return;
    }

    private void logUnderrunCount() {
        Logging.d(TAG, "underrun count: " + this.audioTrack.getUnderrunCount());
    }

    private static native void nativeCacheDirectBufferAddress(long r0, ByteBuffer r2);

    private static native void nativeGetPlayoutData(long r0, int r2);

    private void releaseAudioResources() {
        Logging.d(TAG, "releaseAudioResources");
        AudioTrack r0 = this.audioTrack;
        if (r0 == null) goto L6;
        r0.release();
        this.audioTrack = null;
        return;
    }

    private void reportWebRtcAudioTrackError(String r4) {
        Logging.e(TAG, "Run-time playback error: " + r4);
        WebRtcAudioUtils.logAudioState(TAG, this.context, this.audioManager);
        JavaAudioDeviceModule.AudioTrackErrorCallback r3 = this.errorCallback;
        if (r3 == null) goto L6;
        r3.onWebRtcAudioTrackError(r4);
        return;
    }

    private void reportWebRtcAudioTrackInitError(String r4) {
        Logging.e(TAG, "Init playout error: " + r4);
        WebRtcAudioUtils.logAudioState(TAG, this.context, this.audioManager);
        JavaAudioDeviceModule.AudioTrackErrorCallback r3 = this.errorCallback;
        if (r3 == null) goto L6;
        r3.onWebRtcAudioTrackInitError(r4);
        return;
    }

    private void reportWebRtcAudioTrackStartError(JavaAudioDeviceModule.AudioTrackStartErrorCode r4, String r5) {
        Logging.e(TAG, "Start playout error: " + r4 + ". " + r5);
        WebRtcAudioUtils.logAudioState(TAG, this.context, this.audioManager);
        JavaAudioDeviceModule.AudioTrackErrorCallback r3 = this.errorCallback;
        if (r3 == null) goto L6;
        r3.onWebRtcAudioTrackStartError(r4, r5);
        return;
    }

    @CalledByNative
    private boolean setStreamVolume(int r4) {
        this.threadChecker.checkIsOnValidThread();
        Logging.d(TAG, "setStreamVolume(" + r4 + ")");
        if (this.audioManager.isVolumeFixed() == false) goto L6;
        Logging.e(TAG, "The device implements a fixed volume policy.");
        return false;
    L6:
        this.audioManager.setStreamVolume(0, r4, 0);
        return true;
    }

    @CalledByNative
    private boolean startPlayout() {
        this.threadChecker.checkIsOnValidThread();
        VolumeLogger r0 = this.volumeLogger;
        if (r0 == null) goto L5;
        r0.start();
    L5:
        Logging.d(TAG, "startPlayout");
        if (this.audioTrack == null) goto L8;
        boolean r02 = true;
    L9:
        assertTrue(r02);
        if (this.audioThread != null) goto L12;
        boolean r03 = true;
    L13:
        assertTrue(r03);
        this.audioTrack.play();     // Catch: IllegalStateException -> L21
        if (this.audioTrack.getPlayState() == 3) goto L19;
        reportWebRtcAudioTrackStartError(JavaAudioDeviceModule.AudioTrackStartErrorCode.AUDIO_TRACK_START_STATE_MISMATCH, "AudioTrack.play failed - incorrect state :" + this.audioTrack.getPlayState());
        releaseAudioResources();
        return false;
    L19:
        AudioTrackThread r04 = new AudioTrackThread(this, "AudioTrackJavaThread");
        this.audioThread = r04;
        r04.start();
        return true;
    L21:
        e = move-exception;
        reportWebRtcAudioTrackStartError(JavaAudioDeviceModule.AudioTrackStartErrorCode.AUDIO_TRACK_START_EXCEPTION, "AudioTrack.play failed: " + e.getMessage());
        releaseAudioResources();
        return false;
    L12:
        r03 = false;
        goto L13
    L8:
        r02 = false;
        goto L9
    }

    @CalledByNative
    private boolean stopPlayout() {
        this.threadChecker.checkIsOnValidThread();
        VolumeLogger r0 = this.volumeLogger;
        if (r0 == null) goto L5;
        r0.stop();
    L5:
        Logging.d(TAG, "stopPlayout");
        if (this.audioThread == null) goto L8;
        boolean r02 = true;
    L9:
        assertTrue(r02);
        logUnderrunCount();
        this.audioThread.stopThread();
        Logging.d(TAG, "Stopping the AudioTrackThread...");
        this.audioThread.interrupt();
        if (ThreadUtils.joinUninterruptibly(this.audioThread, AUDIO_TRACK_THREAD_JOIN_TIMEOUT_MS) == true) goto L12;
        Logging.e(TAG, "Join of AudioTrackThread timed out.");
        WebRtcAudioUtils.logAudioState(TAG, this.context, this.audioManager);
    L12:
        Logging.d(TAG, "AudioTrackThread has now been stopped.");
        this.audioThread = null;
        if (this.audioTrack == null) goto L19;
        Logging.d(TAG, "Calling AudioTrack.stop...");
        this.audioTrack.stop();     // Catch: IllegalStateException -> L17
        Logging.d(TAG, "AudioTrack.stop is done.");     // Catch: IllegalStateException -> L17
        doAudioTrackStateCallback(1);     // Catch: IllegalStateException -> L17
    L17:
        e = move-exception;
        Logging.e(TAG, "AudioTrack.stop failed: " + e.getMessage());
    L19:
        releaseAudioResources();
        return true;
    L8:
        r02 = false;
        goto L9
    }

    @CalledByNative
    public void setNativeAudioTrack(long r1) {
        this.nativeAudioTrack = r1;
    }

    public void setSpeakerMute(boolean r3) {
        Logging.w(TAG, "setSpeakerMute(" + r3 + ")");
        this.speakerMute = r3;
    }
}
