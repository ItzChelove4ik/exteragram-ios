package org.webrtc.audio;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.AudioDeviceInfo;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioRecordingConfiguration;
import android.media.AudioTimestamp;
import android.os.Process;
import c.f$$ExternalSyntheticBUOutline1;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.CharCodeKt$$ExternalSyntheticBUOutline0;
import okio.Buffer$$ExternalSyntheticBUOutline2;
import org.webrtc.CalledByNative;
import org.webrtc.Logging;
import org.webrtc.ThreadUtils;
import org.webrtc.audio.JavaAudioDeviceModule;

/* loaded from: classes6.dex */
public class WebRtcAudioRecord {
    private static final int AUDIO_RECORD_START = 0;
    private static final int AUDIO_RECORD_STOP = 1;
    private static final long AUDIO_RECORD_THREAD_JOIN_TIMEOUT_MS = 2000;
    private static final int BUFFERS_PER_SECOND = 100;
    private static final int BUFFER_SIZE_FACTOR = 2;
    private static final int CALLBACK_BUFFER_SIZE_MS = 10;
    private static final int CHECK_REC_STATUS_DELAY_MS = 100;
    public static final int DEFAULT_AUDIO_FORMAT = 2;
    public static final int DEFAULT_AUDIO_SOURCE = 7;
    private static final String TAG = "WebRtcAudioRecordExternal";
    private static final AtomicInteger nextSchedulerId = null;
    private final int audioFormat;
    private final AudioManager audioManager;
    private AudioRecord audioRecord;
    private final JavaAudioDeviceModule.SamplesReadyCallback audioSamplesReadyCallback;
    private final int audioSource;
    private final AtomicReference<Boolean> audioSourceMatchesRecordingSessionRef;
    private AudioRecordThread audioThread;
    private ByteBuffer byteBuffer;
    private final Context context;
    private final WebRtcAudioEffects effects;
    private byte[] emptyBytes;
    private final JavaAudioDeviceModule.AudioRecordErrorCallback errorCallback;
    private final ScheduledExecutorService executor;
    private ScheduledFuture<String> future;
    private final boolean isAcousticEchoCancelerSupported;
    private final boolean isNoiseSuppressorSupported;
    private volatile boolean microphoneMute;
    private long nativeAudioRecord;
    private AudioDeviceInfo preferredDevice;
    private final JavaAudioDeviceModule.AudioRecordStateCallback stateCallback;

    /* renamed from: org.webrtc.audio.WebRtcAudioRecord$1, reason: invalid class name */
    public class AnonymousClass1 implements ThreadFactory {
        final /* synthetic */ AtomicInteger val$nextThreadId;

        public AnonymousClass1(AtomicInteger r1) {
            this.val$nextThreadId = r1;
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable r2) {
            Thread r22 = Executors.defaultThreadFactory().newThread(r2);
            r22.setName(String.format("WebRtcAudioRecordScheduler-%s-%s", new Object[]{Integer.valueOf(WebRtcAudioRecord.m22421$$Nest$sfgetnextSchedulerId().getAndIncrement()), Integer.valueOf(this.val$nextThreadId.getAndIncrement())}));
            return r22;
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
            if (WebRtcAudioRecord.m22412$$Nest$fgetaudioRecord(this.this$0).getRecordingState() != 3) goto L5;
            boolean r0 = true;
        L6:
            WebRtcAudioRecord.m22422$$Nest$smassertTrue(r0);
            WebRtcAudioRecord.m22418$$Nest$mdoAudioRecordStateCallback(this.this$0, 0);
            System.nanoTime();
            AudioTimestamp r02 = new AudioTimestamp();
        L7:
            boolean r2 = this.keepAlive;
            WebRtcAudioRecord r5 = this.this$0;
            if (r2 == false) goto L37;
            int r10 = WebRtcAudioRecord.m22412$$Nest$fgetaudioRecord(r5).read(WebRtcAudioRecord.m22414$$Nest$fgetbyteBuffer(this.this$0), WebRtcAudioRecord.m22414$$Nest$fgetbyteBuffer(this.this$0).capacity());
            if (r10 == WebRtcAudioRecord.m22414$$Nest$fgetbyteBuffer(this.this$0).capacity()) goto L12;
            String r22 = "AudioRecord.read failed: " + r10;
            Logging.e(WebRtcAudioRecord.TAG, r22);
            if (r10 != (-3)) goto L7;
            this.keepAlive = false;
            WebRtcAudioRecord.m22420$$Nest$mreportWebRtcAudioRecordError(this.this$0, r22);
            goto L7
        L12:
            if (WebRtcAudioRecord.m22416$$Nest$fgetmicrophoneMute(this.this$0) == false) goto L15;
            WebRtcAudioRecord.m22414$$Nest$fgetbyteBuffer(this.this$0).clear();
            WebRtcAudioRecord.m22414$$Nest$fgetbyteBuffer(this.this$0).put(WebRtcAudioRecord.m22415$$Nest$fgetemptyBytes(this.this$0));
        L15:
            if (this.keepAlive == false) goto L23;
            if (WebRtcAudioRecord.m22412$$Nest$fgetaudioRecord(this.this$0).getTimestamp(r02, 0) != 0) goto L20;
            long r52 = r02.nanoTime;
        L19:
            long r11 = r52;
            WebRtcAudioRecord r7 = this.this$0;
            WebRtcAudioRecord.m22419$$Nest$mnativeDataIsRecorded(r7, WebRtcAudioRecord.m22417$$Nest$fgetnativeAudioRecord(r7), r10, r11);
            goto L23
        L20:
            r52 = 0;
        L23:
            if (WebRtcAudioRecord.m22413$$Nest$fgetaudioSamplesReadyCallback(this.this$0) == null) goto L7;
            WebRtcAudioRecord.m22413$$Nest$fgetaudioSamplesReadyCallback(this.this$0).onWebRtcAudioRecordSamplesReady(new JavaAudioDeviceModule.AudioSamples(WebRtcAudioRecord.m22412$$Nest$fgetaudioRecord(this.this$0).getAudioFormat(), WebRtcAudioRecord.m22412$$Nest$fgetaudioRecord(this.this$0).getChannelCount(), WebRtcAudioRecord.m22412$$Nest$fgetaudioRecord(this.this$0).getSampleRate(), Arrays.copyOfRange(WebRtcAudioRecord.m22414$$Nest$fgetbyteBuffer(this.this$0).array(), WebRtcAudioRecord.m22414$$Nest$fgetbyteBuffer(this.this$0).arrayOffset(), WebRtcAudioRecord.m22414$$Nest$fgetbyteBuffer(this.this$0).capacity() + WebRtcAudioRecord.m22414$$Nest$fgetbyteBuffer(this.this$0).arrayOffset())));
            goto L7
        L37:
        L32:
            e = move-exception;
            Logging.e(WebRtcAudioRecord.TAG, "AudioRecord.stop failed: " + e.getMessage());
            return;
        L29:
            if (WebRtcAudioRecord.m22412$$Nest$fgetaudioRecord(r5) == null) goto L34;
            WebRtcAudioRecord.m22412$$Nest$fgetaudioRecord(this.this$0).stop();     // Catch: IllegalStateException -> L32
            WebRtcAudioRecord.m22418$$Nest$mdoAudioRecordStateCallback(this.this$0, 1);     // Catch: IllegalStateException -> L32
            return;
        L34:
            return;
        L5:
            r0 = false;
            goto L6
        }

        public void stopThread() {
            Logging.d(WebRtcAudioRecord.TAG, "stopThread");
            this.keepAlive = false;
        }
    }

    public static /* synthetic */ String $r8$lambda$Zpf0ZnAH8bhRwXfqZHjvFidZLgw(WebRtcAudioRecord r0, AudioRecord r1) {
        return r0.lambda$scheduleLogRecordingConfigurationsTask$0(r1);
    }

    /* renamed from: -$$Nest$fgetaudioRecord, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AudioRecord m22412$$Nest$fgetaudioRecord(WebRtcAudioRecord r0) {
        return r0.audioRecord;
    }

    /* renamed from: -$$Nest$fgetaudioSamplesReadyCallback, reason: not valid java name */
    public static /* bridge */ /* synthetic */ JavaAudioDeviceModule.SamplesReadyCallback m22413$$Nest$fgetaudioSamplesReadyCallback(WebRtcAudioRecord r0) {
        return r0.audioSamplesReadyCallback;
    }

    /* renamed from: -$$Nest$fgetbyteBuffer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ByteBuffer m22414$$Nest$fgetbyteBuffer(WebRtcAudioRecord r0) {
        return r0.byteBuffer;
    }

    /* renamed from: -$$Nest$fgetemptyBytes, reason: not valid java name */
    public static /* bridge */ /* synthetic */ byte[] m22415$$Nest$fgetemptyBytes(WebRtcAudioRecord r0) {
        return r0.emptyBytes;
    }

    /* renamed from: -$$Nest$fgetmicrophoneMute, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m22416$$Nest$fgetmicrophoneMute(WebRtcAudioRecord r0) {
        return r0.microphoneMute;
    }

    /* renamed from: -$$Nest$fgetnativeAudioRecord, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m22417$$Nest$fgetnativeAudioRecord(WebRtcAudioRecord r2) {
        return r2.nativeAudioRecord;
    }

    /* renamed from: -$$Nest$mdoAudioRecordStateCallback, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22418$$Nest$mdoAudioRecordStateCallback(WebRtcAudioRecord r0, int r1) {
        r0.doAudioRecordStateCallback(r1);
    }

    /* renamed from: -$$Nest$mnativeDataIsRecorded, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22419$$Nest$mnativeDataIsRecorded(WebRtcAudioRecord r0, long r1, int r3, long r4) {
        r0.nativeDataIsRecorded(r1, r3, r4);
    }

    /* renamed from: -$$Nest$mreportWebRtcAudioRecordError, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22420$$Nest$mreportWebRtcAudioRecordError(WebRtcAudioRecord r0, String r1) {
        r0.reportWebRtcAudioRecordError(r1);
    }

    /* renamed from: -$$Nest$sfgetnextSchedulerId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AtomicInteger m22421$$Nest$sfgetnextSchedulerId() {
        return nextSchedulerId;
    }

    /* renamed from: -$$Nest$smassertTrue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22422$$Nest$smassertTrue(boolean r0) {
        assertTrue(r0);
    }

    static {
        nextSchedulerId = new AtomicInteger(0);
    }

    @CalledByNative
    public WebRtcAudioRecord(Context r12, AudioManager r13) {
        this(r12, newDefaultScheduler(), r13, 7, 2, null, null, null, WebRtcAudioEffects.isAcousticEchoCancelerSupported(), WebRtcAudioEffects.isNoiseSuppressorSupported());
    }

    public WebRtcAudioRecord(Context r3, ScheduledExecutorService r4, AudioManager r5, int r6, int r7, JavaAudioDeviceModule.AudioRecordErrorCallback r8, JavaAudioDeviceModule.AudioRecordStateCallback r9, JavaAudioDeviceModule.SamplesReadyCallback r10, boolean r11, boolean r12) {
        this.effects = new WebRtcAudioEffects();
        this.audioSourceMatchesRecordingSessionRef = new AtomicReference();
        if (r11 == true) goto L5;
    L9:
        if (r12 == true) goto L11;
    L15:
        this.context = r3;
        this.executor = r4;
        this.audioManager = r5;
        this.audioSource = r6;
        this.audioFormat = r7;
        this.errorCallback = r8;
        this.stateCallback = r9;
        this.audioSamplesReadyCallback = r10;
        this.isAcousticEchoCancelerSupported = r11;
        this.isNoiseSuppressorSupported = r12;
        Logging.d(TAG, "ctor" + WebRtcAudioUtils.getThreadInfo());
        return;
    L11:
        if (WebRtcAudioEffects.isNoiseSuppressorSupported() == true) goto L15;
        f$$ExternalSyntheticBUOutline1.m("HW NS not supported");
        throw null;
    L5:
        if (WebRtcAudioEffects.isAcousticEchoCancelerSupported() == true) goto L9;
        f$$ExternalSyntheticBUOutline1.m("HW AEC not supported");
        throw null;
    }

    private static void assertTrue(boolean r0) {
        if (r0 == false) goto L4;
        return;
    L4:
        Buffer$$ExternalSyntheticBUOutline2.m("Expected condition to be true");
    }

    private static String audioStateToString(int r1) {
        if (r1 != 0) goto L4;
        return "START";
    L4:
        if (r1 == 1) goto L7;
        return "INVALID";
    L7:
        return "STOP";
    }

    private int channelCountToConfiguration(int r1) {
        if (r1 != 1) goto L6;
        return 16;
    L6:
        return 12;
    }

    @TargetApi(24)
    private static boolean checkDeviceMatch(AudioDeviceInfo r2, AudioDeviceInfo r3) {
        if (r2.getId() == r3.getId()) goto L5;
        return false;
    L5:
        if (r2.getType() != r3.getType()) goto L10;
        return true;
    L10:
        return false;
    }

    private static AudioRecord createAudioRecordOnLowerThanM(int r8, int r9, int r10, int r11, int r12) {
        Logging.d(TAG, "createAudioRecordOnLowerThanM");
        return new AudioRecord(r8, r9, r10, r11, r12);
    }

    @TargetApi(23)
    private static AudioRecord createAudioRecordOnMOrHigher(int r2, int r3, int r4, int r5, int r6) {
        Logging.d(TAG, "createAudioRecordOnMOrHigher");
        return new AudioRecord.Builder().setAudioSource(r2).setAudioFormat(new AudioFormat.Builder().setEncoding(r5).setSampleRate(r3).setChannelMask(r4).build()).setBufferSizeInBytes(r6).build();
    }

    private void doAudioRecordStateCallback(int r3) {
        Logging.d(TAG, "doAudioRecordStateCallback: " + audioStateToString(r3));
        JavaAudioDeviceModule.AudioRecordStateCallback r2 = this.stateCallback;
        if (r2 == null) goto L13;
        if (r3 != 0) goto L8;
        r2.onWebRtcAudioRecordStart();
        return;
    L8:
        if (r3 != 1) goto L11;
        r2.onWebRtcAudioRecordStop();
        return;
    L11:
        Logging.e(TAG, "Invalid audio state");
        return;
    }

    @CalledByNative
    private boolean enableBuiltInAEC(boolean r3) {
        Logging.d(TAG, "enableBuiltInAEC(" + r3 + ")");
        return this.effects.setAEC(r3);
    }

    @CalledByNative
    private boolean enableBuiltInNS(boolean r3) {
        Logging.d(TAG, "enableBuiltInNS(" + r3 + ")");
        return this.effects.setNS(r3);
    }

    private static int getBytesPerSample(int r3) {
        int r1 = 1;
        if (r3 == 1) goto L15;
        if (r3 == 2) goto L15;
        if (r3 == 3) goto L14;
        r1 = 4;
        if (r3 == 4) goto L14;
        if (r3 == 13) goto L15;
        CharCodeKt$$ExternalSyntheticBUOutline0.m("Bad audio format ", r3);
        return 0;
    L14:
        return r1;
    L15:
        return 2;
    }

    @CalledByNative
    private int initRecording(int r7, int r8) {
        Logging.d(TAG, "initRecording(sampleRate=" + r7 + ", channels=" + r8 + ")");
        if (this.audioRecord == null) goto L6;
        reportWebRtcAudioRecordInitError("InitRecording called twice without StopRecording.");
        return -1;
    L6:
        int r3 = r7 / 100;
        ByteBuffer r0 = ByteBuffer.allocateDirect((getBytesPerSample(this.audioFormat) * r8) * r3);
        this.byteBuffer = r0;
        if (r0.hasArray() == true) goto L10;
        reportWebRtcAudioRecordInitError("ByteBuffer does not have backing array.");
        return -1;
    L10:
        Logging.d(TAG, "byteBuffer.capacity: " + this.byteBuffer.capacity());
        this.emptyBytes = new byte[this.byteBuffer.capacity()];
        nativeCacheDirectBufferAddress(this.nativeAudioRecord, this.byteBuffer);
        int r82 = channelCountToConfiguration(r8);
        int r02 = AudioRecord.getMinBufferSize(r7, r82, this.audioFormat);
        if (r02 != (-1)) goto L13;
    L37:
        reportWebRtcAudioRecordInitError("AudioRecord.getMinBufferSize failed: " + r02);
        return -1;
    L13:
        if (r02 == (-2)) goto L37;
        Logging.d(TAG, "AudioRecord.getMinBufferSize: " + r02);
        int r03 = Math.max(r02 * 2, this.byteBuffer.capacity());
        Logging.d(TAG, "bufferSizeInBytes: " + r03);
        this.audioRecord = createAudioRecordOnMOrHigher(this.audioSource, r7, r82, this.audioFormat, r03);     // Catch: Throwable -> L20 IllegalArgumentException -> L22
        this.audioSourceMatchesRecordingSessionRef.set(null);     // Catch: Throwable -> L20 IllegalArgumentException -> L22
        AudioDeviceInfo r72 = this.preferredDevice;     // Catch: Throwable -> L20 IllegalArgumentException -> L22
        if (r72 == null) goto L24;
        setPreferredDevice(r72);     // Catch: Throwable -> L20 IllegalArgumentException -> L22
    L24:
        AudioRecord r73 = this.audioRecord;
        if (r73 != null) goto L27;
    L33:
        reportWebRtcAudioRecordInitError("Creation or initialization of audio recorder failed.");
        releaseAudioResources();
        return -1;
    L27:
        if (r73.getState() != 1) goto L33;
        this.effects.enable(this.audioRecord.getAudioSessionId());
        logMainParameters();
        logMainParametersExtended();
        int r6 = logRecordingConfigurations(this.audioRecord, false);
        if (r6 == 0) goto L32;
        Logging.w(TAG, "Potential microphone conflict. Active sessions: " + r6);
    L32:
        return r3;
    L20:
        e = move-exception;
        reportWebRtcAudioRecordInitError(e.getMessage());
        releaseAudioResources();
        return -1;
    }

    private /* synthetic */ String lambda$scheduleLogRecordingConfigurationsTask$0(AudioRecord r2) {
        if (this.audioRecord != r2) goto L5;
        logRecordingConfigurations(r2, true);
        return "Scheduled task is done";
    L5:
        Logging.d(TAG, "audio record has changed");
        return "Scheduled task is done";
    }

    @TargetApi(24)
    private static boolean logActiveRecordingConfigs(int r10, List<AudioRecordingConfiguration> r11) {
        assertTrue(!r11.isEmpty());
        Iterator<AudioRecordingConfiguration> r112 = r11.iterator();
        Logging.d(TAG, "AudioRecordingConfigurations: ");
    L4:
        if (r112.hasNext() == false) goto L9;
        AudioRecordingConfiguration r0 = r112.next();
        StringBuilder r3 = new StringBuilder("  client audio source=");
        r3.append(WebRtcAudioUtils.audioSourceToString(r0.getClientAudioSource()));
        r3.append(", client session id=");
        r3.append(r0.getClientAudioSessionId());
        r3.append(" (");
        r3.append(r10);
        r3.append(")\n  Device AudioFormat: channel count=");
        AudioFormat r4 = r0.getFormat();
        r3.append(r4.getChannelCount());
        r3.append(", channel index mask=");
        r3.append(r4.getChannelIndexMask());
        r3.append(", channel mask=");
        r3.append(WebRtcAudioUtils.channelMaskToString(r4.getChannelMask()));
        r3.append(", encoding=");
        r3.append(WebRtcAudioUtils.audioEncodingToString(r4.getEncoding()));
        r3.append(", sample rate=");
        r3.append(r4.getSampleRate());
        r3.append("\n  Client AudioFormat: channel count=");
        AudioFormat r42 = r0.getClientFormat();
        r3.append(r42.getChannelCount());
        r3.append(", channel index mask=");
        r3.append(r42.getChannelIndexMask());
        r3.append(", channel mask=");
        r3.append(WebRtcAudioUtils.channelMaskToString(r42.getChannelMask()));
        r3.append(", encoding=");
        r3.append(WebRtcAudioUtils.audioEncodingToString(r42.getEncoding()));
        r3.append(", sample rate=");
        r3.append(r42.getSampleRate());
        r3.append("\n");
        AudioDeviceInfo r02 = r0.getAudioDevice();
        if (r02 == null) goto L8;
        assertTrue(r02.isSource());
        r3.append("  AudioDevice: type=");
        r3.append(WebRtcAudioUtils.deviceTypeToString(r02.getType()));
        r3.append(", id=");
        r3.append(r02.getId());
    L8:
        Logging.d(TAG, r3.toString());
        goto L4
    L9:
        return true;
    }

    private void logMainParameters() {
        Logging.d(TAG, "AudioRecord: session ID: " + this.audioRecord.getAudioSessionId() + ", channels: " + this.audioRecord.getChannelCount() + ", sample rate: " + this.audioRecord.getSampleRate());
    }

    @TargetApi(23)
    private void logMainParametersExtended() {
        Logging.d(TAG, "AudioRecord: buffer size in frames: " + this.audioRecord.getBufferSizeInFrames());
    }

    @TargetApi(24)
    private int logRecordingConfigurations(AudioRecord r5, boolean r6) {
        if (r5 != null) goto L5;
        return 0;
    L5:
        List<AudioRecordingConfiguration> r0 = this.audioManager.getActiveRecordingConfigurations();
        int r1 = r0.size();
        Logging.d(TAG, "Number of active recording sessions: " + r1);
        if (r1 <= 0) goto L10;
        logActiveRecordingConfigs(r5.getAudioSessionId(), r0);
        if (r6 == false) goto L10;
        this.audioSourceMatchesRecordingSessionRef.set(Boolean.valueOf(verifyAudioConfig(r5.getAudioSource(), r5.getAudioSessionId(), r5.getFormat(), r5.getRoutedDevice(), r0)));
    L10:
        return r1;
    }

    private native void nativeCacheDirectBufferAddress(long r1, ByteBuffer r3);

    private native void nativeDataIsRecorded(long r1, int r3, long r4);

    public static ScheduledExecutorService newDefaultScheduler() {
        return Executors.newScheduledThreadPool(0, new AnonymousClass1(new AtomicInteger(0)));
    }

    private void releaseAudioResources() {
        Logging.d(TAG, "releaseAudioResources");
        AudioRecord r0 = this.audioRecord;
        if (r0 == null) goto L5;
        r0.release();
        this.audioRecord = null;
    L5:
        this.audioSourceMatchesRecordingSessionRef.set(null);
    }

    private void reportWebRtcAudioRecordError(String r4) {
        Logging.e(TAG, "Run-time recording error: " + r4);
        WebRtcAudioUtils.logAudioState(TAG, this.context, this.audioManager);
        JavaAudioDeviceModule.AudioRecordErrorCallback r3 = this.errorCallback;
        if (r3 == null) goto L6;
        r3.onWebRtcAudioRecordError(r4);
        return;
    }

    private void reportWebRtcAudioRecordInitError(String r4) {
        Logging.e(TAG, "Init recording error: " + r4);
        WebRtcAudioUtils.logAudioState(TAG, this.context, this.audioManager);
        logRecordingConfigurations(this.audioRecord, false);
        JavaAudioDeviceModule.AudioRecordErrorCallback r3 = this.errorCallback;
        if (r3 == null) goto L6;
        r3.onWebRtcAudioRecordInitError(r4);
        return;
    }

    private void reportWebRtcAudioRecordStartError(JavaAudioDeviceModule.AudioRecordStartErrorCode r4, String r5) {
        Logging.e(TAG, "Start recording error: " + r4 + ". " + r5);
        WebRtcAudioUtils.logAudioState(TAG, this.context, this.audioManager);
        logRecordingConfigurations(this.audioRecord, false);
        JavaAudioDeviceModule.AudioRecordErrorCallback r3 = this.errorCallback;
        if (r3 == null) goto L6;
        r3.onWebRtcAudioRecordStartError(r4, r5);
        return;
    }

    private void scheduleLogRecordingConfigurationsTask(AudioRecord r5) {
        Logging.d(TAG, "scheduleLogRecordingConfigurationsTask");
        WebRtcAudioRecord$$ExternalSyntheticLambda0 r0 = new WebRtcAudioRecord$$ExternalSyntheticLambda0(this, r5);
        ScheduledFuture<String> r52 = this.future;
        if (r52 != null) goto L5;
    L7:
        this.future = this.executor.schedule(r0, 100, TimeUnit.MILLISECONDS);
        return;
    L5:
        if (r52.isDone() == true) goto L7;
        this.future.cancel(true);
        goto L7
    }

    @CalledByNative
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
        reportWebRtcAudioRecordStartError(JavaAudioDeviceModule.AudioRecordStartErrorCode.AUDIO_RECORD_START_STATE_MISMATCH, "AudioRecord.startRecording failed - incorrect state: " + this.audioRecord.getRecordingState());
        return false;
    L16:
        AudioRecordThread r03 = new AudioRecordThread(this, "AudioRecordJavaThread");
        this.audioThread = r03;
        r03.start();
        scheduleLogRecordingConfigurationsTask(this.audioRecord);
        return true;
    L18:
        e = move-exception;
        reportWebRtcAudioRecordStartError(JavaAudioDeviceModule.AudioRecordStartErrorCode.AUDIO_RECORD_START_EXCEPTION, "AudioRecord.startRecording failed: " + e.getMessage());
        return false;
    L9:
        r02 = false;
        goto L10
    L5:
        r0 = false;
        goto L6
    }

    @CalledByNative
    private boolean stopRecording() {
        Logging.d(TAG, "stopRecording");
        if (this.audioThread == null) goto L5;
        boolean r0 = true;
    L6:
        assertTrue(r0);
        ScheduledFuture<String> r02 = this.future;
        if (r02 != null) goto L9;
    L12:
        this.audioThread.stopThread();
        if (ThreadUtils.joinUninterruptibly(this.audioThread, AUDIO_RECORD_THREAD_JOIN_TIMEOUT_MS) == true) goto L15;
        Logging.e(TAG, "Join of AudioRecordJavaThread timed out");
        WebRtcAudioUtils.logAudioState(TAG, this.context, this.audioManager);
    L15:
        this.audioThread = null;
        this.effects.release();
        releaseAudioResources();
        return true;
    L9:
        if (r02.isDone() == true) goto L11;
        this.future.cancel(true);
    L11:
        this.future = null;
        goto L12
    L5:
        r0 = false;
        goto L6
    }

    @TargetApi(24)
    private static boolean verifyAudioConfig(int r6, int r7, AudioFormat r8, AudioDeviceInfo r9, List<AudioRecordingConfiguration> r10) {
        assertTrue(!r10.isEmpty());
        Iterator<AudioRecordingConfiguration> r102 = r10.iterator();
    L4:
        if (r102.hasNext() == false) goto L32;
        AudioRecordingConfiguration r0 = r102.next();
        AudioDeviceInfo r3 = r0.getAudioDevice();
        if (r3 == null) goto L4;
        if (r0.getClientAudioSource() != r6) goto L4;
        if (r0.getClientAudioSessionId() != r7) goto L4;
        if (r0.getClientFormat().getEncoding() != r8.getEncoding()) goto L4;
        if (r0.getClientFormat().getSampleRate() != r8.getSampleRate()) goto L4;
        if (r0.getClientFormat().getChannelMask() != r8.getChannelMask()) goto L4;
        if (r0.getClientFormat().getChannelIndexMask() != r8.getChannelIndexMask()) goto L4;
        if (r0.getFormat().getEncoding() == 0) goto L4;
        if (r0.getFormat().getSampleRate() <= 0) goto L4;
        if (r0.getFormat().getChannelMask() != 0) goto L29;
        if (r0.getFormat().getChannelIndexMask() == 0) goto L4;
    L29:
        if (checkDeviceMatch(r3, r9) == false) goto L4;
        Logging.d(TAG, "verifyAudioConfig: PASS");
        return true;
    L32:
        Logging.e(TAG, "verifyAudioConfig: FAILED");
        return false;
    }

    @CalledByNative
    public boolean isAcousticEchoCancelerSupported() {
        return this.isAcousticEchoCancelerSupported;
    }

    @CalledByNative
    public boolean isAudioConfigVerified() {
        if (this.audioSourceMatchesRecordingSessionRef.get() == null) goto L6;
        return true;
    L6:
        return false;
    }

    @CalledByNative
    public boolean isAudioSourceMatchingRecordingSession() {
        Boolean r1 = this.audioSourceMatchesRecordingSessionRef.get();
        if (r1 != null) goto L7;
        Logging.w(TAG, "Audio configuration has not yet been verified");
        return false;
    L7:
        return r1.booleanValue();
    }

    @CalledByNative
    public boolean isNoiseSuppressorSupported() {
        return this.isNoiseSuppressorSupported;
    }

    public void setMicrophoneMute(boolean r3) {
        Logging.w(TAG, "setMicrophoneMute(" + r3 + ")");
        this.microphoneMute = r3;
    }

    @CalledByNative
    public void setNativeAudioRecord(long r1) {
        this.nativeAudioRecord = r1;
    }

    public boolean setNoiseSuppressorEnabled(boolean r4) {
        if (WebRtcAudioEffects.isNoiseSuppressorSupported() == true) goto L6;
        Logging.e(TAG, "Noise suppressor is not supported.");
        return false;
    L6:
        Logging.w(TAG, "SetNoiseSuppressorEnabled(" + r4 + ")");
        return this.effects.toggleNS(r4);
    }

    @TargetApi(23)
    public void setPreferredDevice(AudioDeviceInfo r3) {
        StringBuilder r0 = new StringBuilder("setPreferredDevice ");
        if (r3 == null) goto L5;
        Integer r1 = Integer.valueOf(r3.getId());
    L6:
        r0.append(r1);
        Logging.d(TAG, r0.toString());
        this.preferredDevice = r3;
        AudioRecord r2 = this.audioRecord;
        if (r2 != null) goto L9;
        return;
    L9:
        if (r2.setPreferredDevice(r3) == true) goto L13;
        Logging.e(TAG, "setPreferredDevice failed");
        return;
    L13:
        return;
    L5:
        r1 = null;
        goto L6
    }
}
