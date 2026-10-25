package org.webrtc.voiceengine;

import android.annotation.TargetApi;
import android.media.AudioFormat;
import android.media.AudioPlaybackCaptureConfiguration;
import android.media.AudioRecord;
import android.media.projection.MediaProjection;
import android.os.Build;
import android.os.Process;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import okio.Buffer$$ExternalSyntheticBUOutline2;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.voip.VideoCapturerDevice;
import org.webrtc.Logging;
import org.webrtc.ThreadUtils;

/* loaded from: classes7.dex */
public class WebRtcAudioRecord {
    private static final long AUDIO_RECORD_THREAD_JOIN_TIMEOUT_MS = 2000;
    private static final int BITS_PER_SAMPLE = 16;
    private static final int BUFFERS_PER_SECOND = 100;
    private static final int BUFFER_SIZE_FACTOR = 2;
    private static final int CALLBACK_BUFFER_SIZE_MS = 10;
    private static final int DEFAULT_AUDIO_SOURCE = 0;
    public static WebRtcAudioRecord Instance = null;
    private static final String TAG = "WebRtcAudioRecord";
    private static WebRtcAudioRecordSamplesReadyCallback audioSamplesReadyCallback;
    private static int audioSource;
    private static WebRtcAudioRecordErrorCallback errorCallback;
    private static volatile boolean microphoneMute;
    private AudioRecord audioRecord;
    private AudioRecordThread audioThread;
    private ByteBuffer byteBuffer;
    private int captureType;
    private AudioRecord deviceAudioRecord;
    private ByteBuffer deviceByteBuffer;
    private WebRtcAudioEffects effects;
    private byte[] emptyBytes;
    private final long nativeAudioRecord;
    private int requestedChannels;
    private int requestedSampleRate;

    public enum AudioRecordStartErrorCode extends Enum<AudioRecordStartErrorCode> {
        private static final /* synthetic */ AudioRecordStartErrorCode[] $VALUES = null;
        public static final AudioRecordStartErrorCode AUDIO_RECORD_START_EXCEPTION = null;
        public static final AudioRecordStartErrorCode AUDIO_RECORD_START_STATE_MISMATCH = null;

        private static /* synthetic */ AudioRecordStartErrorCode[] $values() {
            return new AudioRecordStartErrorCode[]{AUDIO_RECORD_START_EXCEPTION, AUDIO_RECORD_START_STATE_MISMATCH};
        }

        static {
            AUDIO_RECORD_START_EXCEPTION = new AudioRecordStartErrorCode("AUDIO_RECORD_START_EXCEPTION", 0);
            AUDIO_RECORD_START_STATE_MISMATCH = new AudioRecordStartErrorCode("AUDIO_RECORD_START_STATE_MISMATCH", 1);
            $VALUES = $values();
        }

        AudioRecordStartErrorCode(String r1, int r2) {
        }

        public static AudioRecordStartErrorCode valueOf(String r1) {
            return (AudioRecordStartErrorCode) Enum.valueOf(AudioRecordStartErrorCode.class, r1);
        }

        public static AudioRecordStartErrorCode[] values() {
            return (AudioRecordStartErrorCode[]) $VALUES.clone();
        }
    }

    public class AudioRecordThread extends Thread {
        private volatile boolean keepAlive;
        final /* synthetic */ WebRtcAudioRecord this$0;

        public AudioRecordThread(WebRtcAudioRecord r1, String r2) {
            this.this$0 = r1;
            super(r2);
            this.keepAlive = true;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            Process.setThreadPriority(-19);
            Logging.d(WebRtcAudioRecord.TAG, "AudioRecordThread" + WebRtcAudioUtils.getThreadInfo());
            System.nanoTime();
        L3:
            boolean r0 = this.keepAlive;
            WebRtcAudioRecord r2 = this.this$0;
            if (r0 == false) goto L46;
            int r02 = WebRtcAudioRecord.m22435$$Nest$fgetaudioRecord(r2).read(WebRtcAudioRecord.m22436$$Nest$fgetbyteBuffer(this.this$0), WebRtcAudioRecord.m22436$$Nest$fgetbyteBuffer(this.this$0).capacity());
            if (WebRtcAudioRecord.m22437$$Nest$fgetdeviceAudioRecord(this.this$0) == null) goto L8;
            int r22 = WebRtcAudioRecord.m22437$$Nest$fgetdeviceAudioRecord(this.this$0).read(WebRtcAudioRecord.m22438$$Nest$fgetdeviceByteBuffer(this.this$0), WebRtcAudioRecord.m22438$$Nest$fgetdeviceByteBuffer(this.this$0).capacity());
        L10:
            if (r02 == WebRtcAudioRecord.m22436$$Nest$fgetbyteBuffer(this.this$0).capacity()) goto L12;
            String r23 = "AudioRecord.read failed: " + r02;
            Logging.e(WebRtcAudioRecord.TAG, r23);
            if (r02 != (-3)) goto L3;
            this.keepAlive = false;
            WebRtcAudioRecord.m22442$$Nest$mreportWebRtcAudioRecordError(this.this$0, r23);
            goto L3
        L12:
            if (WebRtcAudioRecord.m22444$$Nest$sfgetmicrophoneMute() == false) goto L14;
            WebRtcAudioRecord.m22436$$Nest$fgetbyteBuffer(this.this$0).clear();
            WebRtcAudioRecord.m22436$$Nest$fgetbyteBuffer(this.this$0).put(WebRtcAudioRecord.m22439$$Nest$fgetemptyBytes(this.this$0));
        L14:
            if (r02 != r22) goto L26;
            WebRtcAudioRecord.m22438$$Nest$fgetdeviceByteBuffer(this.this$0).position(0);
            WebRtcAudioRecord.m22436$$Nest$fgetbyteBuffer(this.this$0).position(0);
            int r24 = 0;
        L17:
            if (r24 >= (r02 / 2)) goto L26;
            int r5 = r24 * 2;
            int r4 = WebRtcAudioRecord.m22436$$Nest$fgetbyteBuffer(this.this$0).getShort(r5) + (WebRtcAudioRecord.m22438$$Nest$fgetdeviceByteBuffer(this.this$0).getShort(r5) / 10);
            if (r4 <= 32767) goto L22;
            r4 = 32767;
        L22:
            if (r4 >= (-32768)) goto L24;
            r4 = -32768;
        L24:
            WebRtcAudioRecord.m22436$$Nest$fgetbyteBuffer(this.this$0).putShort(r5, (short) r4);
            r24 = r24 + 1;
        L26:
            if (this.keepAlive == false) goto L32;
            WebRtcAudioRecord r25 = this.this$0;     // Catch: UnsatisfiedLinkError -> L29
            WebRtcAudioRecord.m22441$$Nest$mnativeDataIsRecorded(r25, r02, WebRtcAudioRecord.m22440$$Nest$fgetnativeAudioRecord(r25));     // Catch: UnsatisfiedLinkError -> L29
        L29:
            e = move-exception;
            FileLog.e(e);
            this.keepAlive = false;
        L32:
            if (WebRtcAudioRecord.m22443$$Nest$sfgetaudioSamplesReadyCallback() == null) goto L3;
            WebRtcAudioRecord.m22443$$Nest$sfgetaudioSamplesReadyCallback().onWebRtcAudioRecordSamplesReady(new AudioSamples(WebRtcAudioRecord.m22435$$Nest$fgetaudioRecord(this.this$0), Arrays.copyOf(WebRtcAudioRecord.m22436$$Nest$fgetbyteBuffer(this.this$0).array(), WebRtcAudioRecord.m22436$$Nest$fgetbyteBuffer(this.this$0).capacity()), null));
            goto L3
        L8:
            r22 = 0;
            goto L10
        L46:
        L41:
            e = move-exception;
            Logging.e(WebRtcAudioRecord.TAG, "AudioRecord.stop failed: " + e.getMessage());
            return;
        L38:
            if (WebRtcAudioRecord.m22435$$Nest$fgetaudioRecord(r2) == null) goto L60;
            WebRtcAudioRecord.m22435$$Nest$fgetaudioRecord(this.this$0).stop();     // Catch: IllegalStateException -> L41
            return;
        }

        public void stopThread() {
            Logging.d(WebRtcAudioRecord.TAG, "stopThread");
            this.keepAlive = false;
        }
    }

    public static class AudioSamples {
        private final int audioFormat;
        private final int channelCount;
        private final byte[] data;
        private final int sampleRate;

        private AudioSamples(AudioRecord r2, byte[] r3) {
            this.audioFormat = r2.getAudioFormat();
            this.channelCount = r2.getChannelCount();
            this.sampleRate = r2.getSampleRate();
            this.data = r3;
        }

        public /* synthetic */ AudioSamples(AudioRecord r1, byte[] r2, WebRtcAudioRecordIA r3) {
            this(r1, r2);
        }

        public int getAudioFormat() {
            return this.audioFormat;
        }

        public int getChannelCount() {
            return this.channelCount;
        }

        public byte[] getData() {
            return this.data;
        }

        public int getSampleRate() {
            return this.sampleRate;
        }
    }

    public interface WebRtcAudioRecordErrorCallback {
        void onWebRtcAudioRecordError(String r1);

        void onWebRtcAudioRecordInitError(String r1);

        void onWebRtcAudioRecordStartError(AudioRecordStartErrorCode r1, String r2);
    }

    public interface WebRtcAudioRecordSamplesReadyCallback {
        void onWebRtcAudioRecordSamplesReady(AudioSamples r1);
    }

    /* renamed from: -$$Nest$fgetaudioRecord, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AudioRecord m22435$$Nest$fgetaudioRecord(WebRtcAudioRecord r0) {
        return r0.audioRecord;
    }

    /* renamed from: -$$Nest$fgetbyteBuffer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ByteBuffer m22436$$Nest$fgetbyteBuffer(WebRtcAudioRecord r0) {
        return r0.byteBuffer;
    }

    /* renamed from: -$$Nest$fgetdeviceAudioRecord, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AudioRecord m22437$$Nest$fgetdeviceAudioRecord(WebRtcAudioRecord r0) {
        return r0.deviceAudioRecord;
    }

    /* renamed from: -$$Nest$fgetdeviceByteBuffer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ByteBuffer m22438$$Nest$fgetdeviceByteBuffer(WebRtcAudioRecord r0) {
        return r0.deviceByteBuffer;
    }

    /* renamed from: -$$Nest$fgetemptyBytes, reason: not valid java name */
    public static /* bridge */ /* synthetic */ byte[] m22439$$Nest$fgetemptyBytes(WebRtcAudioRecord r0) {
        return r0.emptyBytes;
    }

    /* renamed from: -$$Nest$fgetnativeAudioRecord, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m22440$$Nest$fgetnativeAudioRecord(WebRtcAudioRecord r2) {
        return r2.nativeAudioRecord;
    }

    /* renamed from: -$$Nest$mnativeDataIsRecorded, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22441$$Nest$mnativeDataIsRecorded(WebRtcAudioRecord r0, int r1, long r2) {
        r0.nativeDataIsRecorded(r1, r2);
    }

    /* renamed from: -$$Nest$mreportWebRtcAudioRecordError, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22442$$Nest$mreportWebRtcAudioRecordError(WebRtcAudioRecord r0, String r1) {
        r0.reportWebRtcAudioRecordError(r1);
    }

    /* renamed from: -$$Nest$sfgetaudioSamplesReadyCallback, reason: not valid java name */
    public static /* bridge */ /* synthetic */ WebRtcAudioRecordSamplesReadyCallback m22443$$Nest$sfgetaudioSamplesReadyCallback() {
        return audioSamplesReadyCallback;
    }

    /* renamed from: -$$Nest$sfgetmicrophoneMute, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m22444$$Nest$sfgetmicrophoneMute() {
        return microphoneMute;
    }

    static {
        int r0 = getDefaultAudioSource();
        DEFAULT_AUDIO_SOURCE = r0;
        audioSource = r0;
    }

    public WebRtcAudioRecord(long r3, int r5) {
        this.requestedSampleRate = 48000;
        this.requestedChannels = 1;
        Logging.d(TAG, "ctor" + WebRtcAudioUtils.getThreadInfo());
        this.nativeAudioRecord = r3;
        this.effects = WebRtcAudioEffects.create();
        this.captureType = r5;
        if (r5 == 2) goto L5;
        return;
    L5:
        if (Instance != null) goto L9;
        Instance = this;
        return;
    }

    private static void assertTrue(boolean r0) {
        if (r0 == false) goto L4;
        return;
    L4:
        Buffer$$ExternalSyntheticBUOutline2.m("Expected condition to be true");
    }

    private int channelCountToConfiguration(int r1) {
        if (r1 != 1) goto L6;
        return 16;
    L6:
        return 12;
    }

    private boolean enableBuiltInAEC(boolean r3) {
        Logging.d(TAG, "enableBuiltInAEC(" + r3 + ')');
        WebRtcAudioEffects r2 = this.effects;
        if (r2 != null) goto L7;
        Logging.e(TAG, "Built-in AEC is not supported on this platform");
        return false;
    L7:
        return r2.setAEC(r3);
    }

    private boolean enableBuiltInNS(boolean r3) {
        Logging.d(TAG, "enableBuiltInNS(" + r3 + ')');
        WebRtcAudioEffects r2 = this.effects;
        if (r2 != null) goto L7;
        Logging.e(TAG, "Built-in NS is not supported on this platform");
        return false;
    L7:
        return r2.setNS(r3);
    }

    private static int getDefaultAudioSource() {
        return 7;
    }

    private int initRecording(int r13, int r14) {
        if (this.captureType == 1) goto L5;
    L7:
        this.requestedSampleRate = r13;
        this.requestedChannels = r14;
        Logging.d(TAG, "initRecording(sampleRate=" + r13 + ", channels=" + r14 + ")");
        if (this.audioRecord == null) goto L11;
        reportWebRtcAudioRecordInitError("InitRecording called twice without StopRecording.");
        return -1;
    L11:
        int r9 = r13 / 100;
        ByteBuffer r1 = ByteBuffer.allocateDirect((r14 * 2) * r9);
        this.byteBuffer = r1;
        r1.order(ByteOrder.nativeOrder());
        Logging.d(TAG, "byteBuffer.capacity: " + this.byteBuffer.capacity());
        this.emptyBytes = new byte[this.byteBuffer.capacity()];
        nativeCacheDirectBufferAddress(this.byteBuffer, this.nativeAudioRecord);
        int r0 = channelCountToConfiguration(r14);
        int r5 = AudioRecord.getMinBufferSize(r13, r0, 2);
        if (r5 != (-1)) goto L14;
    L46:
        reportWebRtcAudioRecordInitError("AudioRecord.getMinBufferSize failed: " + r5);
        return -1;
    L14:
        if (r5 == (-2)) goto L46;
        Logging.d(TAG, "AudioRecord.getMinBufferSize: " + r5);
        int r6 = Math.max(r5 * 2, this.byteBuffer.capacity());
        Logging.d(TAG, "bufferSizeInBytes: " + r6);
        if (this.captureType == 1) goto L19;
        this.audioRecord = new AudioRecord(audioSource, r13, r0, 2, r6);     // Catch: IllegalArgumentException -> L43
    L29:
        AudioRecord r02 = this.audioRecord;
        if (r02 != null) goto L32;
    L41:
        reportWebRtcAudioRecordInitError("Failed to create a new AudioRecord instance");
        releaseAudioResources(false);
        return -1;
    L32:
        if (r02.getState() != 1) goto L41;
        if (this.captureType != 0) goto L39;
        WebRtcAudioEffects r03 = this.effects;
        if (r03 == null) goto L39;
        r03.enable(this.audioRecord.getAudioSessionId());
    L39:
        logMainParameters();
        logMainParametersExtended();
        return r9;
    L43:
        e = move-exception;
        reportWebRtcAudioRecordInitError("AudioRecord ctor error: " + e.getMessage());
        releaseAudioResources(false);
        return -1;
    L19:
        if (Build.VERSION.SDK_INT < 29) goto L29;
        MediaProjection r2 = VideoCapturerDevice.getMediaProjection();     // Catch: Throwable -> L25
        if (r2 != null) goto L23;
        return -1;
    L23:
        WebRtcAudioRecord$$ExternalSyntheticApiModelOutline1.m();     // Catch: Throwable -> L25
        AudioPlaybackCaptureConfiguration.Builder r22 = WebRtcAudioRecord$$ExternalSyntheticApiModelOutline0.m(r2);     // Catch: Throwable -> L25
        r22.addMatchingUsage(1);     // Catch: Throwable -> L25
        r22.addMatchingUsage(14);     // Catch: Throwable -> L25
        r22.addMatchingUsage(0);     // Catch: Throwable -> L25
        AudioRecord.Builder r4 = new AudioRecord.Builder();     // Catch: Throwable -> L25
        r4.setAudioPlaybackCaptureConfig(r22.build());     // Catch: Throwable -> L25
        r4.setAudioFormat(new AudioFormat.Builder().setChannelMask(r0).setSampleRate(r13).setEncoding(2).build());     // Catch: Throwable -> L25
        r4.setBufferSizeInBytes(r6);     // Catch: Throwable -> L25
        this.audioRecord = r4.build();     // Catch: Throwable -> L25
    L25:
        th = move-exception;
        reportWebRtcAudioRecordInitError("AudioRecord ctor error: " + th.getMessage());
        releaseAudioResources(false);
        return -1;
    L5:
        if (Build.VERSION.SDK_INT >= 29) goto L7;
        return -1;
    }

    private void logMainParameters() {
        Logging.d(TAG, "AudioRecord: session ID: " + this.audioRecord.getAudioSessionId() + ", channels: " + this.audioRecord.getChannelCount() + ", sample rate: " + this.audioRecord.getSampleRate());
    }

    private void logMainParametersExtended() {
        Logging.d(TAG, "AudioRecord: buffer size in frames: " + this.audioRecord.getBufferSizeInFrames());
    }

    private native void nativeCacheDirectBufferAddress(ByteBuffer r1, long r2);

    private native void nativeDataIsRecorded(int r1, long r2);

    private void onDestroy() {
        stopDeviceAudioRecord();
        if (Instance != this) goto L6;
        Instance = null;
        return;
    }

    private void releaseAudioResources(boolean r3) {
        Logging.d(TAG, "releaseAudioResources " + r3);
        if (r3 == false) goto L8;
        AudioRecord r32 = this.deviceAudioRecord;
        if (r32 == null) goto L12;
        r32.release();
        this.deviceAudioRecord = null;
        return;
    L12:
        return;
    L8:
        AudioRecord r33 = this.audioRecord;
        if (r33 == null) goto L13;
        r33.release();
        this.audioRecord = null;
        return;
    }

    private void reportWebRtcAudioRecordError(String r2) {
        Logging.e(TAG, "Run-time recording error: " + r2);
        WebRtcAudioUtils.logAudioState(TAG);
        WebRtcAudioRecordErrorCallback r1 = errorCallback;
        if (r1 == null) goto L6;
        r1.onWebRtcAudioRecordError(r2);
        return;
    }

    private void reportWebRtcAudioRecordInitError(String r2) {
        Logging.e(TAG, "Init recording error: " + r2);
        WebRtcAudioUtils.logAudioState(TAG);
        WebRtcAudioRecordErrorCallback r1 = errorCallback;
        if (r1 == null) goto L6;
        r1.onWebRtcAudioRecordInitError(r2);
        return;
    }

    private void reportWebRtcAudioRecordStartError(AudioRecordStartErrorCode r2, String r3) {
        Logging.e(TAG, "Start recording error: " + r2 + ". " + r3);
        WebRtcAudioUtils.logAudioState(TAG);
        WebRtcAudioRecordErrorCallback r1 = errorCallback;
        if (r1 == null) goto L6;
        r1.onWebRtcAudioRecordStartError(r2, r3);
        return;
    }

    public static synchronized void setAudioSource(int r4) {
        monitor-enter(WebRtcAudioRecord.class);
        Logging.w(TAG, "Audio source is changed from: " + audioSource + " to " + r4);     // Catch: Throwable -> L7
        audioSource = r4;     // Catch: Throwable -> L7
        monitor-exit(WebRtcAudioRecord.class);
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public static void setErrorCallback(WebRtcAudioRecordErrorCallback r2) {
        Logging.d(TAG, "Set error callback");
        errorCallback = r2;
    }

    public static void setMicrophoneMute(boolean r2) {
        Logging.w(TAG, "setMicrophoneMute(" + r2 + ")");
        microphoneMute = r2;
    }

    public static void setOnAudioSamplesReady(WebRtcAudioRecordSamplesReadyCallback r0) {
        audioSamplesReadyCallback = r0;
    }

    private boolean startRecording() {
        Logging.d(TAG, "startRecording");
        if (this.audioRecord == null) goto L5;
        boolean r0 = true;
    L6:
        assertTrue(r0);
        if (this.audioThread != null) goto L9;
        boolean r02 = true;
    L10:
        assertTrue(r02);
        this.audioRecord.startRecording();     // Catch: IllegalStateException -> L18
        if (this.audioRecord.getRecordingState() == 3) goto L16;
        reportWebRtcAudioRecordStartError(AudioRecordStartErrorCode.AUDIO_RECORD_START_STATE_MISMATCH, "AudioRecord.startRecording failed - incorrect state :" + this.audioRecord.getRecordingState());
        return false;
    L16:
        AudioRecordThread r03 = new AudioRecordThread(this, "AudioRecordJavaThread");
        this.audioThread = r03;
        r03.start();
        return true;
    L18:
        e = move-exception;
        reportWebRtcAudioRecordStartError(AudioRecordStartErrorCode.AUDIO_RECORD_START_EXCEPTION, "AudioRecord.startRecording failed: " + e.getMessage());
        return false;
    L9:
        r02 = false;
        goto L10
    L5:
        r0 = false;
        goto L6
    }

    private boolean stopRecording() {
        Logging.d(TAG, "stopRecording");
        if (this.audioThread == null) goto L5;
        boolean r0 = true;
    L6:
        assertTrue(r0);
        this.audioThread.stopThread();
        if (ThreadUtils.joinUninterruptibly(this.audioThread, AUDIO_RECORD_THREAD_JOIN_TIMEOUT_MS) == true) goto L9;
        Logging.e(TAG, "Join of AudioRecordJavaThread timed out");
        WebRtcAudioUtils.logAudioState(TAG);
    L9:
        this.audioThread = null;
        WebRtcAudioEffects r02 = this.effects;
        if (r02 == null) goto L18;
        r02.release();
    L18:
        this.audioRecord.stop();     // Catch: Throwable -> L14
    L16:
        releaseAudioResources(false);
        return true;
    L14:
        th = move-exception;
        FileLog.e(th);
        goto L16
    L5:
        r0 = false;
        goto L6
    }

    @TargetApi(29)
    public void initDeviceAudioRecord(MediaProjection r6) {
        if (Build.VERSION.SDK_INT < 29) goto L36;
        ByteBuffer r0 = ByteBuffer.allocateDirect((this.requestedChannels * 2) * (this.requestedSampleRate / 100));
        this.deviceByteBuffer = r0;
        r0.order(ByteOrder.nativeOrder());
        int r02 = channelCountToConfiguration(this.requestedChannels);
        int r2 = AudioRecord.getMinBufferSize(this.requestedSampleRate, r02, 2);
        if (r2 != (-1)) goto L8;
    L29:
        reportWebRtcAudioRecordInitError("AudioRecord.getMinBufferSize failed: " + r2);
        return;
    L8:
        if (r2 == (-2)) goto L29;
        int r22 = Math.max(r2 * 2, this.deviceByteBuffer.capacity());
        WebRtcAudioRecord$$ExternalSyntheticApiModelOutline1.m();     // Catch: Throwable -> L26
        AudioPlaybackCaptureConfiguration.Builder r62 = WebRtcAudioRecord$$ExternalSyntheticApiModelOutline0.m(r6);     // Catch: Throwable -> L26
        r62.addMatchingUsage(1);     // Catch: Throwable -> L26
        r62.addMatchingUsage(14);     // Catch: Throwable -> L26
        AudioRecord.Builder r4 = new AudioRecord.Builder();     // Catch: Throwable -> L26
        r4.setAudioPlaybackCaptureConfig(r62.build());     // Catch: Throwable -> L26
        r4.setAudioFormat(new AudioFormat.Builder().setChannelMask(r02).setSampleRate(this.requestedSampleRate).setEncoding(2).build());     // Catch: Throwable -> L26
        r4.setBufferSizeInBytes(r22);     // Catch: Throwable -> L26
        AudioRecord r63 = r4.build();     // Catch: Throwable -> L26
        this.deviceAudioRecord = r63;     // Catch: Throwable -> L26
        if (r63 != null) goto L14;
    L24:
        reportWebRtcAudioRecordInitError("Failed to create a new AudioRecord instance");
        releaseAudioResources(true);
        return;
    L14:
        if (r63.getState() != 1) goto L24;
        this.deviceAudioRecord.startRecording();     // Catch: IllegalStateException -> L21
        if (this.deviceAudioRecord.getRecordingState() == 3) goto L35;
        reportWebRtcAudioRecordStartError(AudioRecordStartErrorCode.AUDIO_RECORD_START_STATE_MISMATCH, "AudioRecord.startRecording failed - incorrect state :" + this.deviceAudioRecord.getRecordingState());
        return;
    L35:
        return;
    L21:
        e = move-exception;
        reportWebRtcAudioRecordStartError(AudioRecordStartErrorCode.AUDIO_RECORD_START_EXCEPTION, "AudioRecord.startRecording failed: " + e.getMessage());
        return;
    L26:
        th = move-exception;
        reportWebRtcAudioRecordInitError("AudioRecord ctor error: " + th.getMessage());
        releaseAudioResources(true);
        return;
    }

    @TargetApi(29)
    public void stopDeviceAudioRecord() {
        AudioRecord r0 = this.deviceAudioRecord;
        if (r0 != null) goto L11;
        return;
    L11:
        r0.stop();     // Catch: Throwable -> L7
    L9:
        releaseAudioResources(true);
        return;
    L7:
        th = move-exception;
        FileLog.e(th);
        goto L9
    }
}
