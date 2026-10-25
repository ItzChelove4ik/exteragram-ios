package org.webrtc.voiceengine;

import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.os.Process;
import java.nio.ByteBuffer;
import okio.Buffer$$ExternalSyntheticBUOutline2;
import org.telegram.messenger.FileLog;
import org.webrtc.ContextUtils;
import org.webrtc.Logging;
import org.webrtc.MediaStreamTrack;
import org.webrtc.ThreadUtils;

/* loaded from: classes7.dex */
public class WebRtcAudioTrack {
    private static final long AUDIO_TRACK_THREAD_JOIN_TIMEOUT_MS = 2000;
    private static final int BITS_PER_SAMPLE = 16;
    private static final int BUFFERS_PER_SECOND = 100;
    private static final int CALLBACK_BUFFER_SIZE_MS = 10;
    private static final boolean DEBUG = false;
    private static final int DEFAULT_USAGE = 0;
    private static final String TAG = "WebRtcAudioTrack";
    private static ErrorCallback errorCallback;
    private static WebRtcAudioTrackErrorCallback errorCallbackOld;
    private static volatile boolean speakerMute;
    private static int streamType;
    private static int usageAttribute;
    private final AudioManager audioManager;
    private AudioTrackThread audioThread;
    private AudioTrack audioTrack;
    private ByteBuffer byteBuffer;
    private byte[] emptyBytes;
    private final long nativeAudioTrack;
    private final ThreadUtils.ThreadChecker threadChecker;

    public enum AudioTrackStartErrorCode extends Enum<AudioTrackStartErrorCode> {
        private static final /* synthetic */ AudioTrackStartErrorCode[] $VALUES = null;
        public static final AudioTrackStartErrorCode AUDIO_TRACK_START_EXCEPTION = null;
        public static final AudioTrackStartErrorCode AUDIO_TRACK_START_STATE_MISMATCH = null;

        private static /* synthetic */ AudioTrackStartErrorCode[] $values() {
            return new AudioTrackStartErrorCode[]{AUDIO_TRACK_START_EXCEPTION, AUDIO_TRACK_START_STATE_MISMATCH};
        }

        static {
            AUDIO_TRACK_START_EXCEPTION = new AudioTrackStartErrorCode("AUDIO_TRACK_START_EXCEPTION", 0);
            AUDIO_TRACK_START_STATE_MISMATCH = new AudioTrackStartErrorCode("AUDIO_TRACK_START_STATE_MISMATCH", 1);
            $VALUES = $values();
        }

        AudioTrackStartErrorCode(String r1, int r2) {
        }

        public static AudioTrackStartErrorCode valueOf(String r1) {
            return (AudioTrackStartErrorCode) Enum.valueOf(AudioTrackStartErrorCode.class, r1);
        }

        public static AudioTrackStartErrorCode[] values() {
            return (AudioTrackStartErrorCode[]) $VALUES.clone();
        }
    }

    public class AudioTrackThread extends Thread {
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
        }

        private int writeBytes(AudioTrack r1, ByteBuffer r2, int r3, boolean r4) {
            if (r1 != null) goto L6;
            return 0;
        L6:
            return r1.write(r2, r3, !r4 ? 1 : 0);
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            Process.setThreadPriority(-19);
            Logging.d(WebRtcAudioTrack.TAG, "AudioTrackThread" + WebRtcAudioUtils.getThreadInfo());
            if (WebRtcAudioTrack.m22445$$Nest$fgetaudioTrack(this.this$0).getPlayState() != 3) goto L5;
            boolean r0 = true;
        L6:
            WebRtcAudioTrack.m22452$$Nest$smassertTrue(r0);
            int r02 = WebRtcAudioTrack.m22446$$Nest$fgetbyteBuffer(this.this$0).capacity();
            WebRtcAudioTrack.m22445$$Nest$fgetaudioTrack(this.this$0).getChannelCount();
            WebRtcAudioTrack.m22445$$Nest$fgetaudioTrack(this.this$0).getSampleRate();
            this.targetTimeNs = System.nanoTime();
            boolean r2 = false;
        L8:
            if (this.keepAlive == false) goto L34;
            if (WebRtcAudioTrack.m22445$$Nest$fgetaudioTrack(this.this$0) == null) goto L34;
            WebRtcAudioTrack r5 = this.this$0;     // Catch: Throwable -> L32
            WebRtcAudioTrack.m22449$$Nest$mnativeGetPlayoutData(r5, r02, WebRtcAudioTrack.m22448$$Nest$fgetnativeAudioTrack(r5));     // Catch: Throwable -> L32
            if (r02 > WebRtcAudioTrack.m22446$$Nest$fgetbyteBuffer(this.this$0).remaining()) goto L15;
            boolean r52 = true;
        L16:
            WebRtcAudioTrack.m22452$$Nest$smassertTrue(r52);
            if (WebRtcAudioTrack.m22451$$Nest$sfgetspeakerMute() == false) goto L19;
            WebRtcAudioTrack.m22446$$Nest$fgetbyteBuffer(this.this$0).clear();
            WebRtcAudioTrack.m22446$$Nest$fgetbyteBuffer(this.this$0).put(WebRtcAudioTrack.m22447$$Nest$fgetemptyBytes(this.this$0));
            WebRtcAudioTrack.m22446$$Nest$fgetbyteBuffer(this.this$0).position(0);
        L19:
            int r53 = writeBytes(WebRtcAudioTrack.m22445$$Nest$fgetaudioTrack(this.this$0), WebRtcAudioTrack.m22446$$Nest$fgetbyteBuffer(this.this$0), r02, r2);
            if (r53 == r02) goto L24;
            Logging.e(WebRtcAudioTrack.TAG, "AudioTrack.write played invalid number of bytes: " + r53);
            if (r53 >= 0) goto L24;
            this.keepAlive = false;
            WebRtcAudioTrack.m22450$$Nest$mreportWebRtcAudioTrackError(this.this$0, "AudioTrack.write failed: " + r53);
        L24:
            WebRtcAudioTrack.m22446$$Nest$fgetbyteBuffer(this.this$0).rewind();
            r2 = !r2;
            WebRtcAudioTrack.m22446$$Nest$fgetbyteBuffer(this.this$0).rewind();
            this.targetTimeNs += 10000000;
            long r7 = this.targetTimeNs - System.nanoTime();
            if (r7 > 0) goto L43;
            this.targetTimeNs = System.nanoTime();
            goto L8
        L43:
            Thread.sleep(r7 / 1000000, (int) (r7 % 1000000));     // Catch: InterruptedException -> L29
        L29:
            e = move-exception;
            FileLog.e(e);
            goto L8
        L15:
            r52 = false;
        L32:
            this.keepAlive = false;
        L34:
            if (WebRtcAudioTrack.m22445$$Nest$fgetaudioTrack(this.this$0) == null) goto L57;
            Logging.d(WebRtcAudioTrack.TAG, "Calling AudioTrack.stop...");
            WebRtcAudioTrack.m22445$$Nest$fgetaudioTrack(this.this$0).stop();     // Catch: Exception -> L38
            Logging.d(WebRtcAudioTrack.TAG, "AudioTrack.stop is done.");     // Catch: Exception -> L38
            return;
        L38:
            e = move-exception;
            Logging.e(WebRtcAudioTrack.TAG, "AudioTrack.stop failed: " + e.getMessage());
            return;
        L57:
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

    public interface ErrorCallback {
        void onWebRtcAudioTrackError(String r1);

        void onWebRtcAudioTrackInitError(String r1);

        void onWebRtcAudioTrackStartError(AudioTrackStartErrorCode r1, String r2);
    }

    @Deprecated
    public interface WebRtcAudioTrackErrorCallback {
        void onWebRtcAudioTrackError(String r1);

        void onWebRtcAudioTrackInitError(String r1);

        void onWebRtcAudioTrackStartError(String r1);
    }

    /* renamed from: -$$Nest$fgetaudioTrack, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AudioTrack m22445$$Nest$fgetaudioTrack(WebRtcAudioTrack r0) {
        return r0.audioTrack;
    }

    /* renamed from: -$$Nest$fgetbyteBuffer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ByteBuffer m22446$$Nest$fgetbyteBuffer(WebRtcAudioTrack r0) {
        return r0.byteBuffer;
    }

    /* renamed from: -$$Nest$fgetemptyBytes, reason: not valid java name */
    public static /* bridge */ /* synthetic */ byte[] m22447$$Nest$fgetemptyBytes(WebRtcAudioTrack r0) {
        return r0.emptyBytes;
    }

    /* renamed from: -$$Nest$fgetnativeAudioTrack, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m22448$$Nest$fgetnativeAudioTrack(WebRtcAudioTrack r2) {
        return r2.nativeAudioTrack;
    }

    /* renamed from: -$$Nest$mnativeGetPlayoutData, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22449$$Nest$mnativeGetPlayoutData(WebRtcAudioTrack r0, int r1, long r2) {
        r0.nativeGetPlayoutData(r1, r2);
    }

    /* renamed from: -$$Nest$mreportWebRtcAudioTrackError, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22450$$Nest$mreportWebRtcAudioTrackError(WebRtcAudioTrack r0, String r1) {
        r0.reportWebRtcAudioTrackError(r1);
    }

    /* renamed from: -$$Nest$sfgetspeakerMute, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m22451$$Nest$sfgetspeakerMute() {
        return speakerMute;
    }

    /* renamed from: -$$Nest$smassertTrue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22452$$Nest$smassertTrue(boolean r0) {
        assertTrue(r0);
    }

    static {
        int r0 = getDefaultUsageAttribute();
        DEFAULT_USAGE = r0;
        usageAttribute = r0;
        streamType = 0;
    }

    public WebRtcAudioTrack(long r3) {
        ThreadUtils.ThreadChecker r0 = new ThreadUtils.ThreadChecker();
        this.threadChecker = r0;
        r0.checkIsOnValidThread();
        Logging.d(TAG, "ctor" + WebRtcAudioUtils.getThreadInfo());
        this.nativeAudioTrack = r3;
        this.audioManager = (AudioManager) ContextUtils.getApplicationContext().getSystemService(MediaStreamTrack.AUDIO_TRACK_KIND);
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

    private static AudioTrack createAudioTrackOnLollipopOrHigher(int r8, int r9, int r10) {
        Logging.d(TAG, "createAudioTrackOnLollipopOrHigher");
        int r0 = AudioTrack.getNativeOutputSampleRate(streamType);
        Logging.d(TAG, "nativeOutputSampleRate: " + r0);
        if (r8 == r0) goto L6;
        Logging.w(TAG, "Unable to use fast mode since requested sample rate is not native");
    L6:
        if (usageAttribute == DEFAULT_USAGE) goto L9;
        Logging.w(TAG, "A non default usage attribute is used: " + usageAttribute);
    L9:
        return new AudioTrack(new AudioAttributes.Builder().setUsage(usageAttribute).setContentType(1).build(), new AudioFormat.Builder().setEncoding(2).setSampleRate(r8).setChannelMask(r9).build(), r10, 1, 0);
    }

    private static AudioTrack createAudioTrackOnLowerThanLollipop(int r7, int r8, int r9) {
        return new AudioTrack(streamType, r7, r8, 2, r9, 1);
    }

    private int getBufferSizeInFrames() {
        return this.audioTrack.getBufferSizeInFrames();
    }

    private static int getDefaultUsageAttribute() {
        return 2;
    }

    private int getStreamMaxVolume() {
        this.threadChecker.checkIsOnValidThread();
        Logging.d(TAG, "getStreamMaxVolume");
        if (this.audioManager == null) goto L5;
        boolean r0 = true;
    L6:
        assertTrue(r0);
        return this.audioManager.getStreamMaxVolume(streamType);
    L5:
        r0 = false;
        goto L6
    }

    private int getStreamVolume() {
        this.threadChecker.checkIsOnValidThread();
        Logging.d(TAG, "getStreamVolume");
        if (this.audioManager == null) goto L5;
        boolean r0 = true;
    L6:
        assertTrue(r0);
        return this.audioManager.getStreamVolume(streamType);
    L5:
        r0 = false;
        goto L6
    }

    private int initPlayout(int r5, int r6, double r7) {
        this.threadChecker.checkIsOnValidThread();
        Logging.d(TAG, "initPlayout(sampleRate=" + r5 + ", channels=" + r6 + ", bufferSizeFactor=" + r7 + ")");
        this.byteBuffer = ByteBuffer.allocateDirect((r6 * 2) * (r5 / 100));
        StringBuilder r0 = new StringBuilder("byteBuffer.capacity: ");
        r0.append(this.byteBuffer.capacity());
        Logging.d(TAG, r0.toString());
        this.emptyBytes = new byte[this.byteBuffer.capacity()];
        nativeCacheDirectBufferAddress(this.byteBuffer, this.nativeAudioTrack);
        int r62 = channelCountToConfiguration(r6);
        int r72 = (int) (AudioTrack.getMinBufferSize(r5, r62, 2) * r7);
        Logging.d(TAG, "minBufferSizeInBytes: " + r72);
        if (r72 >= this.byteBuffer.capacity()) goto L7;
        reportWebRtcAudioTrackInitError("AudioTrack.getMinBufferSize returns an invalid value.");
        return -1;
    L7:
        if (this.audioTrack == null) goto L22;
        reportWebRtcAudioTrackInitError("Conflict with existing AudioTrack.");
        return -1;
    L22:
        AudioTrack r52 = createAudioTrackOnLollipopOrHigher(r5, r62, r72);     // Catch: IllegalArgumentException -> L19
        this.audioTrack = r52;     // Catch: IllegalArgumentException -> L19
        if (r52 != null) goto L13;
    L17:
        reportWebRtcAudioTrackInitError("Initialization of audio track failed.");
        releaseAudioResources();
        return -1;
    L13:
        if (r52.getState() != 1) goto L17;
        logMainParameters();
        logMainParametersExtended();
        return r72;
    L19:
        e = move-exception;
        reportWebRtcAudioTrackInitError(e.getMessage());
        releaseAudioResources();
        return -1;
    }

    public static boolean isSpeakerMuted() {
        return speakerMute;
    }

    private boolean isVolumeFixed() {
        return this.audioManager.isVolumeFixed();
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

    private void logUnderrunCount() {
        Logging.d(TAG, "underrun count: " + this.audioTrack.getUnderrunCount());
    }

    private native void nativeCacheDirectBufferAddress(ByteBuffer r1, long r2);

    private native void nativeGetPlayoutData(int r1, long r2);

    private void releaseAudioResources() {
        Logging.e(TAG, "releaseAudioResources", new Exception());
        AudioTrack r0 = this.audioTrack;
        if (r0 == null) goto L12;
        this.audioTrack = null;
        r0.release();     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        FileLog.e(th);
        return;
    }

    private void reportWebRtcAudioTrackError(String r2) {
        Logging.e(TAG, "Run-time playback error: " + r2);
        WebRtcAudioUtils.logAudioState(TAG);
        WebRtcAudioTrackErrorCallback r1 = errorCallbackOld;
        if (r1 == null) goto L5;
        r1.onWebRtcAudioTrackError(r2);
    L5:
        ErrorCallback r12 = errorCallback;
        if (r12 == null) goto L9;
        r12.onWebRtcAudioTrackError(r2);
        return;
    }

    private void reportWebRtcAudioTrackInitError(String r2) {
        Logging.e(TAG, "Init playout error: " + r2);
        WebRtcAudioUtils.logAudioState(TAG);
        WebRtcAudioTrackErrorCallback r1 = errorCallbackOld;
        if (r1 == null) goto L5;
        r1.onWebRtcAudioTrackInitError(r2);
    L5:
        ErrorCallback r12 = errorCallback;
        if (r12 == null) goto L9;
        r12.onWebRtcAudioTrackInitError(r2);
        return;
    }

    private void reportWebRtcAudioTrackStartError(AudioTrackStartErrorCode r2, String r3) {
        Logging.e(TAG, "Start playout error: " + r2 + ". " + r3);
        WebRtcAudioUtils.logAudioState(TAG);
        WebRtcAudioTrackErrorCallback r1 = errorCallbackOld;
        if (r1 == null) goto L5;
        r1.onWebRtcAudioTrackStartError(r3);
    L5:
        ErrorCallback r12 = errorCallback;
        if (r12 == null) goto L9;
        r12.onWebRtcAudioTrackStartError(r2, r3);
        return;
    }

    public static synchronized void setAudioStreamType(int r1) {
        monitor-enter(WebRtcAudioTrack.class);
        streamType = r1;     // Catch: Throwable -> L7
        monitor-exit(WebRtcAudioTrack.class);
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public static synchronized void setAudioTrackUsageAttribute(int r4) {
        monitor-enter(WebRtcAudioTrack.class);
        Logging.w(TAG, "Default usage attribute is changed from: " + DEFAULT_USAGE + " to " + r4);     // Catch: Throwable -> L7
        usageAttribute = r4;     // Catch: Throwable -> L7
        monitor-exit(WebRtcAudioTrack.class);
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public static void setErrorCallback(ErrorCallback r2) {
        Logging.d(TAG, "Set extended error callback");
        errorCallback = r2;
    }

    @Deprecated
    public static void setErrorCallback(WebRtcAudioTrackErrorCallback r2) {
        Logging.d(TAG, "Set error callback (deprecated");
        errorCallbackOld = r2;
    }

    public static void setSpeakerMute(boolean r2) {
        Logging.w(TAG, "setSpeakerMute(" + r2 + ")");
        speakerMute = r2;
    }

    private boolean setStreamVolume(int r5) {
        this.threadChecker.checkIsOnValidThread();
        Logging.d(TAG, "setStreamVolume(" + r5 + ")");
        if (this.audioManager == null) goto L5;
        boolean r0 = true;
    L6:
        assertTrue(r0);
        if (isVolumeFixed() == false) goto L10;
        Logging.e(TAG, "The device implements a fixed volume policy.");
        return false;
    L10:
        this.audioManager.setStreamVolume(streamType, r5, 0);
        return true;
    L5:
        r0 = false;
        goto L6
    }

    private boolean startPlayout() {
        this.threadChecker.checkIsOnValidThread();
        Logging.d(TAG, "startPlayout");
        if (this.audioTrack == null) goto L5;
        boolean r0 = true;
    L6:
        assertTrue(r0);
        if (this.audioThread != null) goto L9;
        boolean r02 = true;
    L10:
        assertTrue(r02);
        this.audioTrack.play();     // Catch: IllegalStateException -> L18
        if (this.audioTrack.getPlayState() == 3) goto L16;
        reportWebRtcAudioTrackStartError(AudioTrackStartErrorCode.AUDIO_TRACK_START_STATE_MISMATCH, "AudioTrack.play failed - incorrect state :" + this.audioTrack.getPlayState());
        releaseAudioResources();
        return false;
    L16:
        AudioTrackThread r03 = new AudioTrackThread(this, "AudioTrackJavaThread");
        this.audioThread = r03;
        r03.start();
        return true;
    L18:
        e = move-exception;
        reportWebRtcAudioTrackStartError(AudioTrackStartErrorCode.AUDIO_TRACK_START_EXCEPTION, "AudioTrack.play failed: " + e.getMessage());
        releaseAudioResources();
        return false;
    L9:
        r02 = false;
        goto L10
    L5:
        r0 = false;
        goto L6
    }

    private boolean stopPlayout() {
        this.threadChecker.checkIsOnValidThread();     // Catch: Throwable -> L10
        Logging.d(TAG, "stopPlayout");     // Catch: Throwable -> L10
        if (this.audioThread == null) goto L6;
        boolean r3 = true;
    L7:
        assertTrue(r3);     // Catch: Throwable -> L10
        logUnderrunCount();     // Catch: Throwable -> L10
        this.audioThread.stopThread();     // Catch: Throwable -> L10
        Logging.d(TAG, "Stopping the AudioTrackThread...");     // Catch: Throwable -> L10
        this.audioThread.interrupt();     // Catch: Throwable -> L10
        if (ThreadUtils.joinUninterruptibly(this.audioThread, AUDIO_TRACK_THREAD_JOIN_TIMEOUT_MS) == true) goto L12;
        Logging.e(TAG, "Join of AudioTrackThread timed out.");     // Catch: Throwable -> L10
        WebRtcAudioUtils.logAudioState(TAG);     // Catch: Throwable -> L10
    L12:
        Logging.d(TAG, "AudioTrackThread has now been stopped.");     // Catch: Throwable -> L10
    L13:
        this.audioThread = null;
        releaseAudioResources();     // Catch: Throwable -> L18
    L20:
        return true;
    L18:
        th = move-exception;
        FileLog.e(th);
        goto L20
    L6:
        r3 = false;
    L10:
        th = move-exception;
        FileLog.e(th);     // Catch: Throwable -> L21
    L21:
        th = move-exception;
        this.audioThread = null;
        throw th;
    }
}
