package com.exteragram.messenger.camera;

import android.bluetooth.BluetoothAdapter;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTimestamp;
import android.media.MediaCodec;
import android.media.MediaFormat;
import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLExt;
import android.opengl.EGLSurface;
import android.opengl.GLUtils;
import android.os.SystemClock;
import android.view.Surface;
import com.exteragram.messenger.utils.system.SystemUtils;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.mvel2.ast.Instance$$ExternalSyntheticBUOutline0;
import org.mvel2.util.Make$Map$$ExternalSyntheticBUOutline0;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.DispatchQueue;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageLoader;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.video.MP4Builder;
import org.telegram.messenger.video.MediaCodecVideoConvertor;
import org.telegram.messenger.video.Mp4Movie;
import org.telegram.ui.Components.PermissionRequest;
import org.webrtc.EglBase;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;
import org.webrtc.MediaStreamTrack;

/* loaded from: classes.dex */
public class RoundVideoEncoder {
    private AudioCaptureSession activeAudioCapture;
    private boolean allowSendingWhileRecording;
    private final ArrayBlockingQueue<AudioChunkBatch> audioBatchPool;
    private boolean audioBatchesPrepared;
    private MediaCodec.BufferInfo audioBufferInfo;
    private long audioCapUs;
    private int audioCaptureGeneration;
    private final Object audioCaptureLock;
    private boolean audioCaptureRunning;
    private MediaCodec audioEncoder;
    private boolean audioEosQueued;
    private boolean audioEosSeen;
    private long audioSegmentBaseUs;
    private long audioSegmentFramesSubmitted;
    private long audioTotalEndUs;
    private int audioTrackIndex;
    private final Callback callback;
    private final FrameSnapshot currentFrame;
    private int deferredAudioCleanup;
    private int deferredFinish;
    private EGLConfig eglConfig;
    private EGLContext eglContext;
    private EGLDisplay eglDisplay;
    private EGLSurface eglSurface;
    private final DispatchQueue encoderQueue;
    private File fileToWrite;
    private final AtomicBoolean finishRequested;
    private boolean firstEncode;
    private int frameRate;
    private Surface inputSurface;
    private final boolean isSecretChat;
    private int lastCameraId;
    private long lastMuxedAudioPtsUs;
    private long lastMuxedVideoPtsUs;
    private long lastSourceTimestampNs;
    private long lastSubmittedAudioEndUs;
    private long lastSubmittedVideoPtsUs;
    private long lastVideoActiveTimeNs;
    private long lastVideoFrameIndex;
    private long maxDurationUs;
    private MP4Builder mediaMuxer;
    private long minVideoFrameDeltaNs;
    private File pausePreviewFile;
    private final ArrayList<AudioChunkBatch> pendingAudio;
    private final FrameSnapshot pendingFrame;
    private final Object pendingFrameLock;
    private boolean pendingFrameSet;
    private EGLContext pendingResumeContext;
    private int prependHeaderSize;
    private final Renderer renderer;
    private long segmentActiveBaseNs;
    private long segmentFirstArrivalNs;
    private long segmentVideoOriginNs;
    private long sourceAnchorMonotonicNs;
    private boolean sourceAnchorSet;
    private long sourceAnchorSourceNs;
    private volatile boolean started;
    private volatile int state;
    private int videoBitrate;
    private MediaCodec.BufferInfo videoBufferInfo;
    private MediaCodec videoEncoder;
    private boolean videoEosSeen;
    private boolean videoEosSignalled;
    private File videoFile;
    private int videoHeight;
    private int videoTrackIndex;
    private int videoWidth;
    private boolean waitingAudioTail;
    private boolean writingToDifferentFile;

    public class AudioCaptureRunnable implements Runnable {
        private final AudioTimestamp audioTimestamp;
        private final AudioCaptureSession session;
        final /* synthetic */ RoundVideoEncoder this$0;

        public AudioCaptureRunnable(RoundVideoEncoder r1, AudioCaptureSession r2) {
            this.this$0 = r1;
            this.audioTimestamp = new AudioTimestamp();
            this.session = r2;
        }

        private boolean postBatch(AudioChunkBatch r1) {
            return RoundVideoEncoder.m1173$$Nest$fgetencoderQueue(this.this$0).getHandler().post(r1.deliveryRunnable);
        }

        @Override // java.lang.Runnable
        public void run() {
            long r2 = 0;
            int r4 = 0;
            boolean r5 = false;
        L4:
            if (r5 == true) goto L102;
        L14:
            th = move-exception;
            FileLog.e(th);     // Catch: Throwable -> L85
            this.session.releaseRecorder();
            monitor-enter(RoundVideoEncoder.m1171$$Nest$fgetaudioCaptureLock(this.this$0));
        L75:
            th = move-exception;
            throw th;
        L73:
            if (RoundVideoEncoder.m1170$$Nest$fgetactiveAudioCapture(this.this$0) != this.session) goto L77;
            RoundVideoEncoder.m1174$$Nest$fputactiveAudioCapture(this.this$0, null);     // Catch: Throwable -> L75
        L77:
            if (RoundVideoEncoder.m1173$$Nest$fgetencoderQueue(this.this$0).getHandler().post(this.session.completionRunnable) == false) goto L81;
            return;
        L81:
            if (BuildVars.LOGS_ENABLED == false) goto L147;
        L82:
            FileLog.e("RoundVideoEncoder unable to post audio capture completion");
            return;
        L147:
            return;
        L85:
            th = move-exception;
            this.session.releaseRecorder();
            monitor-enter(RoundVideoEncoder.m1171$$Nest$fgetaudioCaptureLock(this.this$0));
        L91:
            th = move-exception;
            throw th;
        L89:
            if (RoundVideoEncoder.m1170$$Nest$fgetactiveAudioCapture(this.this$0) != this.session) goto L93;
            RoundVideoEncoder.m1174$$Nest$fputactiveAudioCapture(this.this$0, null);     // Catch: Throwable -> L91
        L93:
            if (RoundVideoEncoder.m1173$$Nest$fgetencoderQueue(this.this$0).getHandler().post(this.session.completionRunnable) == false) goto L97;
        L99:
            throw th;
        L97:
            if (BuildVars.LOGS_ENABLED == false) goto L99;
            FileLog.e("RoundVideoEncoder unable to post audio capture completion");
            goto L99
        L6:
            if (this.session.stopRequested.get() == true) goto L102;
            AudioChunkBatch r7 = RoundVideoEncoder.m1177$$Nest$mobtainAudioBatch(this.this$0);     // Catch: Throwable -> L14 InterruptedException -> L65
            AudioCaptureSession r0 = this.session;
            if (r7 == null) goto L11;
            if (r0.stopRequested.get() == true) goto L18;
            if (this.session.audioRecorder.getTimestamp(this.audioTimestamp, r4) != 0) goto L23;
            int r02 = 1;
        L24:
            int r9 = r02;
        L28:
            int r10 = r4;
        L30:
            if (r10 >= 10) goto L56;
            ByteBuffer r03 = r7.buffer[r10];     // Catch: Throwable -> L25
            r03.clear();     // Catch: Throwable -> L25
            int r11 = this.session.audioRecorder.read(r7.data[r10], r4, 2048);     // Catch: Throwable -> L25
            if (r11 <= 0) goto L33;
            r03.position(r4);     // Catch: Throwable -> L25
            r03.limit(r11);     // Catch: Throwable -> L25
            if ((r10 % 2) != 0) goto L45;
            double r12 = 0.0d;
            int r14 = r4;
        L38:
            if (r14 >= (r11 / 2)) goto L40;
            short r15 = r03.getShort();     // Catch: Throwable -> L25
            r12 = r12 + (r15 * r15);     // Catch: Throwable -> L25
            r14 = r14 + 1;     // Catch: Throwable -> L25
            r9 = r9;
            goto L38
        L40:
            int r16 = r9;
            r03.position(r4);     // Catch: Throwable -> L25
            RoundVideoEncoder.m1172$$Nest$fgetcallback(this.this$0).onAudioAmplitude(Math.sqrt((r12 / r11) / 2.0d));     // Catch: Throwable -> L43
        L46:
            long r8 = r11 / 2;     // Catch: Throwable -> L25
            if (r16 == 0) goto L49;
            AudioTimestamp r04 = this.audioTimestamp;     // Catch: Throwable -> L25
            boolean r152 = r5;
            long r42 = r04.nanoTime + (((r2 - r04.framePosition) * 1000000000) / 48000);     // Catch: Throwable -> L25
        L50:
            r7.startTimeNs[r10] = r42;     // Catch: Throwable -> L25
            r10 = r10 + 1;     // Catch: Throwable -> L25
            r7.results = r10;     // Catch: Throwable -> L25
            r2 = r2 + r8;     // Catch: Throwable -> L25
            if (this.session.stopRequested.get() == true) goto L33;
            r5 = r152;
            r9 = r16;
            r4 = 0;
            goto L30
        L49:
            r152 = r5;
            r42 = System.nanoTime() - ((1000000000 * r8) / 48000);     // Catch: Throwable -> L25
            goto L50
        L43:
            th = move-exception;
            FileLog.e(th);     // Catch: Throwable -> L25
            goto L46
        L45:
            r16 = r9;
        L33:
            r5 = true;
        L56:
            if (r7.results <= 0) goto L59;
            boolean r05 = postBatch(r7);     // Catch: Throwable -> L25
        L60:
            if (r05 == true) goto L62;
            RoundVideoEncoder.m1178$$Nest$mrecycleAudioBatch(this.this$0, r7);     // Catch: Throwable -> L14
        L62:
            r4 = 0;
            goto L4
        L59:
            r05 = false;
            goto L60
        L23:
            r02 = r4;
        L27:
            r9 = r4;
            goto L28
        L18:
            RoundVideoEncoder.m1178$$Nest$mrecycleAudioBatch(this.this$0, r7);     // Catch: Throwable -> L14
            r5 = true;
            goto L4
        L25:
            th = move-exception;
            RoundVideoEncoder.m1178$$Nest$mrecycleAudioBatch(this.this$0, r7);     // Catch: Throwable -> L14
            throw th;     // Catch: Throwable -> L14
        L11:
            if (r0.stopRequested.get() == true) goto L102;
            FileLog.e("RoundVideoEncoder audio batch pool stalled");     // Catch: Throwable -> L14
            goto L102
        L65:
            boolean r153 = r5;
            if (this.session.stopRequested.get() == true) goto L102;
            r5 = r153;
        L102:
            this.session.releaseRecorder();
            Object r22 = RoundVideoEncoder.m1171$$Nest$fgetaudioCaptureLock(this.this$0);
            monitor-enter(r22);
        L107:
            th = move-exception;
            throw th;
        L105:
            if (RoundVideoEncoder.m1170$$Nest$fgetactiveAudioCapture(this.this$0) != this.session) goto L109;
            RoundVideoEncoder.m1174$$Nest$fputactiveAudioCapture(this.this$0, null);     // Catch: Throwable -> L107
        L109:
            monitor-exit(r22);     // Catch: Throwable -> L107
            if (RoundVideoEncoder.m1173$$Nest$fgetencoderQueue(this.this$0).getHandler().post(this.session.completionRunnable) == false) goto L113;
            return;
        L113:
            if (BuildVars.LOGS_ENABLED == true) goto L82;
        }
    }

    public class AudioCaptureSession {
        public final AudioRecord audioRecorder;
        public final Runnable completionRunnable;
        public final int generation;
        public final Object recorderLock;
        public boolean recorderReleased;
        public final AtomicBoolean stopRequested;
        public final Runnable stopRunnable;
        final /* synthetic */ RoundVideoEncoder this$0;
        public Thread thread;

        public static /* synthetic */ void $r8$lambda$1SNHV7hE9zRnhmKPINVh2eqThTM(AudioCaptureSession r0) {
            r0.lambda$new$0();
        }

        public static /* synthetic */ void $r8$lambda$Sva_3YlOVTApHgVVeufuG6ZvBkc(AudioCaptureSession r0) {
            r0.stopRecorder();
        }

        public AudioCaptureSession(RoundVideoEncoder r2, AudioRecord r3, int r4) {
            this.this$0 = r2;
            this.stopRequested = new AtomicBoolean(false);
            this.recorderLock = new Object();
            this.audioRecorder = r3;
            this.generation = r4;
            this.stopRunnable = new RoundVideoEncoder$AudioCaptureSession$$ExternalSyntheticLambda0(this);
            this.completionRunnable = new RoundVideoEncoder$AudioCaptureSession$$ExternalSyntheticLambda1(this);
        }

        private /* synthetic */ void lambda$new$0() {
            RoundVideoEncoder.m1176$$Nest$mhandleAudioCaptureFinished(this.this$0, this);
        }

        private void stopRecorder() {
            Object r0 = this.recorderLock;
            monitor-enter(r0);
        L7:
            th = move-exception;
            throw th;
        L5:
            if (this.recorderReleased == true) goto L9;
            RoundVideoEncoder.m1180$$Nest$mstopAudioRecorder(this.this$0, this.audioRecorder);     // Catch: Throwable -> L7
        L9:
            monitor-exit(r0);     // Catch: Throwable -> L7
        }

        public void releaseRecorder() {
            Object r0 = this.recorderLock;
            monitor-enter(r0);
        L8:
            th = move-exception;
            throw th;
        L5:
            if (this.recorderReleased == false) goto L10;
            monitor-exit(r0);     // Catch: Throwable -> L8
            return;
        L10:
            this.recorderReleased = true;     // Catch: Throwable -> L8
            RoundVideoEncoder.m1179$$Nest$mreleaseAudioRecorder(this.this$0, this.audioRecorder);     // Catch: Throwable -> L8
            monitor-exit(r0);     // Catch: Throwable -> L8
        }

        public void requestStop() {
            boolean r0 = this.stopRequested.compareAndSet(false, true);
            Thread r1 = this.thread;
            if (r1 != null) goto L5;
        L7:
            if (r0 == true) goto L9;
            return;
        L9:
            if (Utilities.globalQueue.postRunnable(this.stopRunnable) == true) goto L13;
            FileLog.e("RoundVideoEncoder unable to schedule AudioRecord stop");
            return;
        L13:
            return;
        L5:
            if (r1 == Thread.currentThread()) goto L7;
            r1.interrupt();
            goto L7
        }
    }

    public class AudioChunkBatch {
        public final ByteBuffer[] buffer;
        public final byte[][] data;
        public final Runnable deliveryRunnable;
        public int drained;
        public int results;
        public final long[] startTimeNs;
        final /* synthetic */ RoundVideoEncoder this$0;

        public static /* synthetic */ void $r8$lambda$RQlUA6qJfppe1U39XuJPT7abkSE(AudioChunkBatch r0) {
            r0.lambda$new$0();
        }

        public AudioChunkBatch(RoundVideoEncoder r5) {
            this.this$0 = r5;
            this.data = new byte[10][];
            this.buffer = new ByteBuffer[10];
            this.startTimeNs = new long[10];
            this.deliveryRunnable = new RoundVideoEncoder$AudioChunkBatch$$ExternalSyntheticLambda0(this);
            int r0 = 0;
        L3:
            if (r0 >= 10) goto L5;
            byte[] r2 = new byte[2048];
            this.data[r0] = r2;
            this.buffer[r0] = ByteBuffer.wrap(r2).order(ByteOrder.nativeOrder());
            r0 = r0 + 1;
            goto L3
        }

        private /* synthetic */ void lambda$new$0() {
            RoundVideoEncoder.m1175$$Nest$mhandleAudioBatch(this.this$0, this);
        }
    }

    public interface Callback {
        void onAudioAmplitude(double r1);

        void onFinished(FinishReason r1);

        void onPaused(File r1);

        void onRecordingStarted(boolean r1);

        void onWriteData(long r1);
    }

    public enum FinishReason extends Enum<FinishReason> {
        private static final /* synthetic */ FinishReason[] $VALUES = null;
        public static final FinishReason CANCELLED = null;
        public static final FinishReason COMPLETED = null;
        public static final FinishReason FAILED = null;

        private static /* synthetic */ FinishReason[] $values() {
            return new FinishReason[]{COMPLETED, CANCELLED, FAILED};
        }

        static {
            COMPLETED = new FinishReason("COMPLETED", 0);
            CANCELLED = new FinishReason("CANCELLED", 1);
            FAILED = new FinishReason("FAILED", 2);
            $VALUES = $values();
        }

        FinishReason(String r1, int r2) {
        }

        public static FinishReason valueOf(String r1) {
            return (FinishReason) Enum.valueOf(FinishReason.class, r1);
        }

        public static FinishReason[] values() {
            return (FinishReason[]) $VALUES.clone();
        }
    }

    public static final class FrameSnapshot {
        public long arrivalTimeNs;
        public int cameraId;
        public final float[] mvpMatrix;
        public int previewHeight;
        public int previewWidth;
        public long sourceTimestampNs;
        public final float[] stMatrix;
        public int surfaceIndex;
        public final float[] textureCoords;
        public int textureId;

        public FrameSnapshot() {
            this.stMatrix = new float[16];
            this.mvpMatrix = new float[16];
            this.textureCoords = new float[8];
        }

        public void copyFrom(FrameSnapshot r5) {
            this.sourceTimestampNs = r5.sourceTimestampNs;
            this.arrivalTimeNs = r5.arrivalTimeNs;
            this.cameraId = r5.cameraId;
            this.surfaceIndex = r5.surfaceIndex;
            this.textureId = r5.textureId;
            System.arraycopy(r5.stMatrix, 0, this.stMatrix, 0, 16);
            System.arraycopy(r5.mvpMatrix, 0, this.mvpMatrix, 0, 16);
            System.arraycopy(r5.textureCoords, 0, this.textureCoords, 0, 8);
            this.previewWidth = r5.previewWidth;
            this.previewHeight = r5.previewHeight;
        }
    }

    public interface Renderer {
        boolean onDrawEncoderFrame(long r1, FrameSnapshot r3);

        void onEncoderSurfaceCreated(int r1, int r2);

        void onEncoderSurfaceDestroyed();
    }

    public static /* synthetic */ void $r8$lambda$9d8vLyH8GZBH92i7o6h4f3X1T7U(RoundVideoEncoder r0) {
        r0.lambda$stop$2();
    }

    public static /* synthetic */ void $r8$lambda$9i5xteSOho9pBZbXau5V6o2V0ow(RoundVideoEncoder r0, File r1) {
        r0.lambda$pause$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$A8T6K7hu0bS0docM6neHtfLGfsI(RoundVideoEncoder r0, File r1) {
        r0.lambda$finishPause$6(r1);
    }

    public static /* synthetic */ void $r8$lambda$EOQtcMDeeCn42Q2UiJwDvfxty94(RoundVideoEncoder r0, File r1, EGLContext r2, int r3) {
        r0.lambda$startRecording$0(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$P7GAUZEAqs66bTiluwydtkqjk1M(RoundVideoEncoder r0) {
        r0.lambda$finalizeFailure$10();
    }

    public static /* synthetic */ void $r8$lambda$PzFI4S7fWSxo4YxhnVXr87zTpqo(RoundVideoEncoder r0, FinishReason r1) {
        r0.lambda$finalizeStop$8(r1);
    }

    public static /* synthetic */ void $r8$lambda$cFXuHyQPNeO3YVw1K8dLeGy0KYM(RoundVideoEncoder r0) {
        r0.handleFrame();
    }

    public static /* synthetic */ void $r8$lambda$dYVievzQrIUAGsP7JNV3sY1q8l8(RoundVideoEncoder r0) {
        r0.lambda$handleResume$5();
    }

    public static /* synthetic */ void $r8$lambda$kIAYXNAu6X67IT3tUImSgJjq6TM(RoundVideoEncoder r0) {
        r0.lambda$finalizeCancel$9();
    }

    public static /* synthetic */ void $r8$lambda$kd6f_YpfCoqbb0PrgaCrmf1DV9g(RoundVideoEncoder r0, FinishReason r1) {
        r0.lambda$handleFinish$7(r1);
    }

    /* renamed from: $r8$lambda$lbQI-CIXKF5JHlEpuOKpXIU8j9Y, reason: not valid java name */
    public static /* synthetic */ void m1168$r8$lambda$lbQICIXKF5JHlEpuOKpXIU8j9Y(RoundVideoEncoder r0) {
        r0.lambda$handleStart$4();
    }

    /* renamed from: $r8$lambda$u4T-cLrUH3xT1N8uYngbb95b57s, reason: not valid java name */
    public static /* synthetic */ void m1169$r8$lambda$u4TcLrUH3xT1N8uYngbb95b57s(RoundVideoEncoder r0) {
        r0.lambda$cancel$3();
    }

    /* renamed from: -$$Nest$fgetactiveAudioCapture, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AudioCaptureSession m1170$$Nest$fgetactiveAudioCapture(RoundVideoEncoder r0) {
        return r0.activeAudioCapture;
    }

    /* renamed from: -$$Nest$fgetaudioCaptureLock, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Object m1171$$Nest$fgetaudioCaptureLock(RoundVideoEncoder r0) {
        return r0.audioCaptureLock;
    }

    /* renamed from: -$$Nest$fgetcallback, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Callback m1172$$Nest$fgetcallback(RoundVideoEncoder r0) {
        return r0.callback;
    }

    /* renamed from: -$$Nest$fgetencoderQueue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ DispatchQueue m1173$$Nest$fgetencoderQueue(RoundVideoEncoder r0) {
        return r0.encoderQueue;
    }

    /* renamed from: -$$Nest$fputactiveAudioCapture, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1174$$Nest$fputactiveAudioCapture(RoundVideoEncoder r0, AudioCaptureSession r1) {
        r0.activeAudioCapture = r1;
    }

    /* renamed from: -$$Nest$mhandleAudioBatch, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1175$$Nest$mhandleAudioBatch(RoundVideoEncoder r0, AudioChunkBatch r1) {
        r0.handleAudioBatch(r1);
    }

    /* renamed from: -$$Nest$mhandleAudioCaptureFinished, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1176$$Nest$mhandleAudioCaptureFinished(RoundVideoEncoder r0, AudioCaptureSession r1) {
        r0.handleAudioCaptureFinished(r1);
    }

    /* renamed from: -$$Nest$mobtainAudioBatch, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AudioChunkBatch m1177$$Nest$mobtainAudioBatch(RoundVideoEncoder r0) {
        return r0.obtainAudioBatch();
    }

    /* renamed from: -$$Nest$mrecycleAudioBatch, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1178$$Nest$mrecycleAudioBatch(RoundVideoEncoder r0, AudioChunkBatch r1) {
        r0.recycleAudioBatch(r1);
    }

    /* renamed from: -$$Nest$mreleaseAudioRecorder, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1179$$Nest$mreleaseAudioRecorder(RoundVideoEncoder r0, AudioRecord r1) {
        r0.releaseAudioRecorder(r1);
    }

    /* renamed from: -$$Nest$mstopAudioRecorder, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1180$$Nest$mstopAudioRecorder(RoundVideoEncoder r0, AudioRecord r1) {
        r0.stopAudioRecorder(r1);
    }

    public RoundVideoEncoder(Renderer r6, Callback r7, boolean r8) {
        this.finishRequested = new AtomicBoolean(false);
        this.state = 0;
        this.frameRate = 30;
        this.videoTrackIndex = -5;
        this.audioTrackIndex = -5;
        this.eglDisplay = EGL14.EGL_NO_DISPLAY;
        this.eglContext = EGL14.EGL_NO_CONTEXT;
        this.eglSurface = EGL14.EGL_NO_SURFACE;
        this.pendingFrameLock = new Object();
        this.pendingFrame = new FrameSnapshot();
        this.currentFrame = new FrameSnapshot();
        this.lastCameraId = Integer.MIN_VALUE;
        this.segmentFirstArrivalNs = -1;
        this.segmentVideoOriginNs = -1;
        this.lastVideoFrameIndex = -1;
        this.lastSubmittedVideoPtsUs = -1;
        this.lastMuxedVideoPtsUs = -1;
        this.audioCaptureLock = new Object();
        this.audioBatchPool = new ArrayBlockingQueue(25);
        this.pendingAudio = new ArrayList();
        this.audioSegmentBaseUs = -1;
        this.lastSubmittedAudioEndUs = -1;
        this.lastMuxedAudioPtsUs = -1;
        this.deferredFinish = 0;
        this.deferredAudioCleanup = 0;
        this.renderer = r6;
        this.callback = r7;
        this.isSecretChat = r8;
        DispatchQueue r62 = new DispatchQueue("RoundVideoEncoder", false);
        this.encoderQueue = r62;
        r62.setPriority(10);
        r62.start();
    }

    private boolean acceptFrame(long r5, long r7, long r9) {
        if (makeEglCurrent() == true) goto L27;
        fail();
        return false;
    L27:
        if (this.renderer.onDrawEncoderFrame(r9, this.currentFrame) == true) goto L9;
        return false;
    L9:
        EGLExt.eglPresentationTimeANDROID(this.eglDisplay, this.eglSurface, r7);
        if (EGL14.eglSwapBuffers(this.eglDisplay, this.eglSurface) == true) goto L14;
        FileLog.e("RoundVideoEncoder eglSwapBuffers failed at frame " + r5 + ": " + GLUtils.getEGLErrorString(EGL14.eglGetError()));
        fail();
        return false;
    L14:
        if (this.lastVideoFrameIndex >= 0) goto L16;
    L22:
        this.lastVideoFrameIndex = r5;
        this.lastVideoActiveTimeNs = r7;
        this.lastSubmittedVideoPtsUs = r7 / 1000;
        return true;
    L16:
        if (r9 <= 0) goto L22;
        long r0 = this.minVideoFrameDeltaNs;
        if (r0 != 0) goto L20;
    L21:
        this.minVideoFrameDeltaNs = r9;
        goto L22
    L20:
        if (r9 >= r0) goto L22;
    L24:
        th = move-exception;
        FileLog.e(th);
        fail();
        return false;
    }

    private void alignAudioSegment() {
    L3:
        if (this.pendingAudio.isEmpty() == true) goto L32;
        AudioChunkBatch r0 = this.pendingAudio.get(0);
    L5:
        int r2 = r0.drained;
        if (r2 >= r0.results) goto L23;
        ByteBuffer r22 = r0.buffer[r2];
        int r3 = r22.remaining() / 2;
        if (r3 <= 0) goto L17;
        long r5 = r0.startTimeNs[r0.drained];
        long r7 = ((r3 * 1000000000) / 48000) + r5;
        long r13 = this.segmentVideoOriginNs;
        if (r7 <= r13) goto L17;
        if (r5 >= r13) goto L19;
        int r4 = (int) (((r13 - r5) * 48000) / 1000000000);
        if (r4 >= r3) goto L17;
        r22.position(r22.position() + (r4 * 2));
        r5 = r5 + ((r4 * 1000000000) / 48000);
    L19:
        this.audioSegmentBaseUs = Math.max((this.segmentActiveBaseNs + (r5 - this.segmentVideoOriginNs)) / 1000, this.audioTotalEndUs);
        this.audioSegmentFramesSubmitted = 0;
        if (BuildVars.LOGS_ENABLED == false) goto L24;
        FileLog.d("RoundVideoEncoder audio segment base " + this.audioSegmentBaseUs + "us");
        return;
    L24:
        return;
    L17:
        r0.drained++;
        goto L5
    L23:
        this.pendingAudio.remove(0);
        recycleAudioBatch(r0);
        goto L3
    }

    private void createEncoderEgl(EGLContext r14) {
        if (this.eglDisplay != EGL14.EGL_NO_DISPLAY) goto L37;
        EGLDisplay r1 = EGL14.eglGetDisplay(0);
        this.eglDisplay = r1;
        if (r1 == EGL14.EGL_NO_DISPLAY) goto L35;
        int[] r3 = new int[2];
        if (EGL14.eglInitialize(r1, r3, 0, r3, 1) == false) goto L33;
        EGLConfig[] r8 = new EGLConfig[1];
        EGLDisplay r5 = this.eglDisplay;
        if (EGL14.eglChooseConfig(r5, new int[]{12324, 8, 12323, 8, 12322, 8, 12321, 8, 12352, 4, EglBase.EGL_RECORDABLE_ANDROID, 1, 12344}, 0, r8, 0, 1, new int[1], 0) == false) goto L31;
        EGLConfig r2 = r8[0];
        this.eglConfig = r2;
        EGLDisplay r4 = this.eglDisplay;
        EGLContext r142 = EGL14.eglCreateContext(r4, r2, r14, new int[]{12440, 2, 12344}, 0);
        this.eglContext = r142;
        if (r142 != null) goto L13;
    L29:
        this.eglContext = EGL14.EGL_NO_CONTEXT;
        Instance$$ExternalSyntheticBUOutline0.m("eglCreateContext failed ", GLUtils.getEGLErrorString(EGL14.eglGetError()));
        return;
    L13:
        if (r142 == EGL14.EGL_NO_CONTEXT) goto L29;
        EGLDisplay r12 = this.eglDisplay;
        EGLConfig r22 = this.eglConfig;
        Surface r32 = this.inputSurface;
        EGLSurface r143 = EGL14.eglCreateWindowSurface(r12, r22, r32, new int[]{12344}, 0);
        this.eglSurface = r143;
        if (r143 != null) goto L17;
    L27:
        this.eglSurface = EGL14.EGL_NO_SURFACE;
        Instance$$ExternalSyntheticBUOutline0.m("eglCreateWindowSurface failed ", GLUtils.getEGLErrorString(EGL14.eglGetError()));
        return;
    L17:
        if (r143 == EGL14.EGL_NO_SURFACE) goto L27;
        if (EGL14.eglMakeCurrent(this.eglDisplay, r143, r143, this.eglContext) == true) goto L39;
        Instance$$ExternalSyntheticBUOutline0.m("eglMakeCurrent failed ", GLUtils.getEGLErrorString(EGL14.eglGetError()));
        return;
    L39:
        this.renderer.onEncoderSurfaceCreated(this.videoWidth, this.videoHeight);     // Catch: Throwable -> L22
        return;
    L22:
        th = move-exception;
        Make$Map$$ExternalSyntheticBUOutline0.m("encoder renderer initialization failed", th);
        return;
    L31:
        GlShader$$ExternalSyntheticBUOutline1.m("Unable to find a suitable EGLConfig");
        return;
    L33:
        this.eglDisplay = EGL14.EGL_NO_DISPLAY;
        GlShader$$ExternalSyntheticBUOutline1.m("unable to initialize EGL14");
        return;
    L35:
        GlShader$$ExternalSyntheticBUOutline1.m("unable to get EGL14 display");
        return;
    L37:
        GlShader$$ExternalSyntheticBUOutline1.m("EGL already set up");
    }

    private boolean drainAudioOnce(long r10) {
        MediaCodec r1 = this.audioEncoder;
        if (r1 != null) goto L5;
        return false;
    L5:
        int r102 = r1.dequeueOutputBuffer(this.audioBufferInfo, r10);
        if (r102 != (-1)) goto L9;
        return false;
    L9:
        if (r102 != (-3)) goto L12;
        return true;
    L12:
        if (r102 != (-2)) goto L19;
        MediaFormat r103 = this.audioEncoder.getOutputFormat();
        if (this.audioTrackIndex != (-5)) goto L18;
        MP4Builder r11 = this.mediaMuxer;
        if (r11 == null) goto L18;
        this.audioTrackIndex = r11.addTrack(r103, true);
    L18:
        return true;
    L19:
        if (r102 >= 0) goto L52;
        return false;
    L52:
        ByteBuffer r112 = this.audioEncoder.getOutputBuffer(r102);     // Catch: Throwable -> L26
        if (r112 == null) goto L49;
        MediaCodec.BufferInfo r0 = this.audioBufferInfo;     // Catch: Throwable -> L26
        if ((r0.flags & 2) == 0) goto L29;
        r0.size = 0;     // Catch: Throwable -> L26
    L29:
        if (r0.size == 0) goto L42;
        MP4Builder r3 = this.mediaMuxer;     // Catch: Throwable -> L26
        if (r3 == null) goto L42;
        int r4 = this.audioTrackIndex;     // Catch: Throwable -> L26
        if (r4 < 0) goto L42;
        long r32 = r3.writeSampleData(r4, r112, r0, false);     // Catch: Throwable -> L26
        this.lastMuxedAudioPtsUs = Math.max(this.lastMuxedAudioPtsUs, this.audioBufferInfo.presentationTimeUs);     // Catch: Throwable -> L26
        if (r32 == 0) goto L42;
        if (this.writingToDifferentFile == true) goto L42;
        if (this.allowSendingWhileRecording == false) goto L42;
        this.callback.onWriteData(r32);     // Catch: Throwable -> L26
    L42:
        if ((this.audioBufferInfo.flags & 4) == 0) goto L46;
        this.audioEosSeen = true;     // Catch: Throwable -> L26
        this.audioEncoder.releaseOutputBuffer(r102, false);
        return false;
    L46:
        this.audioEncoder.releaseOutputBuffer(r102, false);
        return true;
    L49:
        throw new RuntimeException("audioEncoderOutputBuffer " + r102 + " was null");     // Catch: Throwable -> L26
    L26:
        th = move-exception;
        this.audioEncoder.releaseOutputBuffer(r102, false);
        throw th;
    }

    private void drainEncoders() {
        if (this.videoEncoder == null) goto L8;
    L5:
        if (drainVideoOnce(0) == true) goto L5;
    L8:
        if (this.audioEncoder != null) goto L10;
        return;
    L10:
        if (drainAudioOnce(0) == true) goto L10;
    }

    private boolean drainToEndOfStream() {
        long r0 = SystemClock.elapsedRealtime() + 5000;
        if (this.audioEncoder != null) goto L6;
        this.audioEosSeen = true;
    L6:
        if (this.videoEncoder != null) goto L8;
    L9:
        this.videoEosSeen = true;
    L11:
        if (this.videoEosSeen == false) goto L15;
        if (this.audioEosSeen == false) goto L15;
    L34:
        if (this.videoEosSeen == true) goto L36;
    L37:
        FileLog.e("RoundVideoEncoder end of stream drain timed out, video=" + this.videoEosSeen + " audio=" + this.audioEosSeen);
    L39:
        if (this.videoEosSeen == true) goto L41;
    L43:
        return false;
    L41:
        if (this.audioEosSeen == false) goto L43;
        return true;
    L36:
        if (this.audioEosSeen == true) goto L39;
    L15:
        if (SystemClock.elapsedRealtime() >= r0) goto L34;
        if (this.audioEosQueued == false) goto L19;
    L45:
        if (this.videoEosSeen == true) goto L29;
        drainVideoOnce(NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS);     // Catch: Exception -> L26
    L29:
        if (this.audioEosSeen == true) goto L11;
        drainAudioOnce(NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS);     // Catch: Exception -> L26
    L26:
        e = move-exception;
        FileLog.e(e);
        goto L34
    L19:
        if (this.audioEosSeen == true) goto L45;
        feedPendingAudio();
        if (hasFeedablePendingAudio() == true) goto L45;
        queueAudioEndOfStream();
        goto L45
    L8:
        if (this.videoEosSignalled == true) goto L11;
        goto L9
    }

    private boolean drainVideoOnce(long r11) {
        MediaCodec r1 = this.videoEncoder;
        int r2 = 0;
        if (r1 != null) goto L5;
        return false;
    L5:
        int r112 = r1.dequeueOutputBuffer(this.videoBufferInfo, r11);
        if (r112 != (-1)) goto L9;
        return false;
    L9:
        if (r112 != (-3)) goto L12;
        return true;
    L12:
        if (r112 != (-2)) goto L30;
        MediaFormat r113 = this.videoEncoder.getOutputFormat();
        if (this.videoTrackIndex != (-5)) goto L29;
        MP4Builder r12 = this.mediaMuxer;
        if (r12 == null) goto L29;
        this.videoTrackIndex = r12.addTrack(r113, false);
        if (r113.containsKey("prepend-sps-pps-to-idr-frames") == false) goto L29;
        if (r113.getInteger("prepend-sps-pps-to-idr-frames") != 1) goto L29;
        ByteBuffer r122 = r113.getByteBuffer("csd-0");
        ByteBuffer r114 = r113.getByteBuffer("csd-1");
        if (r122 != null) goto L24;
        int r123 = 0;
    L25:
        if (r114 == null) goto L28;
        r2 = r114.limit();
    L28:
        this.prependHeaderSize = r123 + r2;
        goto L29
    L24:
        r123 = r122.limit();
    L29:
        return true;
    L30:
        if (r112 >= 0) goto L96;
        return false;
    L96:
        ByteBuffer r124 = this.videoEncoder.getOutputBuffer(r112);     // Catch: Throwable -> L44
        if (r124 == null) goto L93;
        MediaCodec.BufferInfo r0 = this.videoBufferInfo;     // Catch: Throwable -> L44
        int r6 = r0.size;     // Catch: Throwable -> L44
        if (r6 <= 1) goto L86;
        int r7 = r0.flags;     // Catch: Throwable -> L44
        if ((r7 & 2) != 0) goto L63;
        int r3 = this.prependHeaderSize;     // Catch: Throwable -> L44
        if (r3 == 0) goto L47;
        if ((r7 & 1) == 0) goto L47;
        r0.offset += r3;
        r0.size = r6 - r3;     // Catch: Throwable -> L44
    L47:
        if (this.firstEncode == true) goto L49;
    L51:
        MP4Builder r02 = this.mediaMuxer;     // Catch: Throwable -> L44
        if (r02 == null) goto L86;
        int r32 = this.videoTrackIndex;     // Catch: Throwable -> L44
        if (r32 < 0) goto L86;
        long r33 = r02.writeSampleData(r32, r124, this.videoBufferInfo, true);     // Catch: Throwable -> L44
        this.lastMuxedVideoPtsUs = Math.max(this.lastMuxedVideoPtsUs, this.videoBufferInfo.presentationTimeUs);     // Catch: Throwable -> L44
        if (r33 == 0) goto L86;
        if (this.writingToDifferentFile == true) goto L86;
        if (this.allowSendingWhileRecording == false) goto L86;
        this.callback.onWriteData(r33);     // Catch: Throwable -> L44
        goto L86
    L49:
        if ((r7 & 1) == 0) goto L51;
        MediaCodecVideoConvertor.cutOfNalData(MediaController.VIDEO_MIME_TYPE, r124, r0);     // Catch: Throwable -> L44
        this.firstEncode = false;     // Catch: Throwable -> L44
        goto L51
    L63:
        if (this.videoTrackIndex != (-5)) goto L86;
        if (this.mediaMuxer == null) goto L86;
        byte[] r5 = new byte[r6];     // Catch: Throwable -> L44
        r124.limit(r0.offset + r6);     // Catch: Throwable -> L44
        r124.position(this.videoBufferInfo.offset);     // Catch: Throwable -> L44
        r124.get(r5);     // Catch: Throwable -> L44
        int r125 = this.videoBufferInfo.size - 1;     // Catch: Throwable -> L44
    L67:
        if (r125 < 0) goto L80;
        if (r125 <= 3) goto L80;
        if (r5[r125] != 1) goto L79;
        if (r5[r125 - 1] != 0) goto L79;
        if (r5[r125 - 2] != 0) goto L79;
        int r03 = r125 - 3;
        if (r5[r03] != 0) goto L79;
        ByteBuffer r126 = ByteBuffer.allocate(r03);     // Catch: Throwable -> L44
        ByteBuffer r62 = ByteBuffer.allocate(this.videoBufferInfo.size - r03);     // Catch: Throwable -> L44
        r126.put(r5, 0, r03).position(0);     // Catch: Throwable -> L44
        r62.put(r5, r03, this.videoBufferInfo.size - r03).position(0);     // Catch: Throwable -> L44
    L81:
        MediaFormat r04 = MediaFormat.createVideoFormat(MediaController.VIDEO_MIME_TYPE, this.videoWidth, this.videoHeight);     // Catch: Throwable -> L44
        if (r126 == null) goto L84;
        r04.setByteBuffer("csd-0", r126);     // Catch: Throwable -> L44
        r04.setByteBuffer("csd-1", r62);     // Catch: Throwable -> L44
    L84:
        this.videoTrackIndex = this.mediaMuxer.addTrack(r04, false);     // Catch: Throwable -> L44
    L79:
        r125 = r125 - 1;
    L80:
        r126 = null;
        r62 = null;
    L86:
        if ((this.videoBufferInfo.flags & 4) == 0) goto L90;
        this.videoEosSeen = true;     // Catch: Throwable -> L44
        this.videoEncoder.releaseOutputBuffer(r112, false);
        return false;
    L90:
        this.videoEncoder.releaseOutputBuffer(r112, false);
        return true;
    L93:
        throw new RuntimeException("videoEncoderOutputBuffer " + r112 + " was null");     // Catch: Throwable -> L44
    L44:
        th = move-exception;
        this.videoEncoder.releaseOutputBuffer(r112, false);
        throw th;
    }

    private void fail() {
        if (this.state != 6) goto L5;
        return;
    L5:
        if (this.state != 7) goto L7;
        return;
    L7:
        if (this.deferredAudioCleanup != 0) goto L17;
        FileLog.e("RoundVideoEncoder failed in state " + this.state);
        this.state = 5;
        if (stopAudioCapture(true) == true) goto L13;
        this.deferredAudioCleanup = 2;
        return;
    L13:
        finalizeFailure();
        return;
    }

    private void failIfActive() {
        if (this.state != 1) goto L5;
    L12:
        fail();
        return;
    L5:
        if (this.state == 2) goto L12;
        if (this.state == 3) goto L12;
        if (this.state == 4) goto L12;
    }

    private void feedPendingAudio() {
        if (this.audioEncoder != null) goto L5;
        return;
    L5:
        if (this.audioEosQueued == false) goto L7;
        return;
    L7:
        if (this.segmentVideoOriginNs != (-1)) goto L10;
        return;
    L10:
        if (this.audioSegmentBaseUs != (-1)) goto L15;
        alignAudioSegment();
        if (this.audioSegmentBaseUs != (-1)) goto L15;
        return;
    L15:
        if (hasFeedablePendingAudio() == false) goto L99;
        long r4 = 48000;
        long r6 = 1000000;
        long r2 = (((this.audioCapUs - this.audioSegmentBaseUs) * 48000) / 1000000) - this.audioSegmentFramesSubmitted;
        if (r2 <= 0) goto L18;
        int r11 = this.audioEncoder.dequeueInputBuffer(0);     // Catch: Exception -> L70
        if (r11 < 0) goto L96;
        ByteBuffer r0 = this.audioEncoder.getInputBuffer(r11);     // Catch: Exception -> L70
        if (r0 == null) goto L25;
        r0.clear();
        long r14 = this.audioSegmentBaseUs + ((this.audioSegmentFramesSubmitted * 1000000) / 48000);
        boolean r9 = false;
        int r10 = 0;
    L29:
        if (this.pendingAudio.isEmpty() == true) goto L59;
        if (r9 == true) goto L59;
        AudioChunkBatch r12 = this.pendingAudio.get(0);
    L32:
        int r13 = r12.drained;
        long r16 = r4;
        if (r13 >= r12.results) goto L50;
        ByteBuffer r42 = r12.buffer[r13];
        int r132 = r42.remaining();
        if (r132 > 0) goto L37;
        long r19 = r6;
    L49:
        r12.drained++;
        r4 = r16;
        r6 = r19;
        goto L32
    L37:
        boolean r18 = true;
        r19 = r6;
        if ((r132 / 2) <= r2) goto L44;
        r42.limit(r42.position() + (((int) r2) * 2));
        r132 = r42.remaining();
        if (r132 <= 0) goto L41;
        r9 = true;
        goto L44
    L41:
        boolean r5 = true;
        r9 = true;
    L51:
        if (r5 == false) goto L52;
        if (r12.drained < r12.results) goto L56;
        this.pendingAudio.remove(0);
        recycleAudioBatch(r12);
    L56:
        if (r9 == false) goto L58;
        recyclePendingAudio();
    L58:
        r4 = r16;
        r6 = r19;
    L52:
        int r133 = r10;
        this.audioTotalEndUs = this.audioSegmentBaseUs + ((this.audioSegmentFramesSubmitted * r19) / r16);
        MediaCodec r102 = this.audioEncoder;
        if (r133 <= 0) goto L66;
        r102.queueInputBuffer(r11, 0, r133, r14, 0);     // Catch: Exception -> L64
        this.lastSubmittedAudioEndUs = this.audioTotalEndUs;     // Catch: Exception -> L64
    L64:
        e = move-exception;
        FileLog.e(e);
        failIfActive();
        return;
    L66:
        r102.queueInputBuffer(r11, 0, 0, r14, 0);     // Catch: Exception -> L64
        return;
    L44:
        if (r0.remaining() < r132) goto L45;
        r0.put(r42);
        long r62 = r132 / 2;
        this.audioSegmentFramesSubmitted += r62;
        r2 = r2 - r62;
        r10 = r10 + r132;
        if (r9 == false) goto L49;
    L48:
        r5 = r18;
        goto L51
    L45:
        r5 = false;
        goto L51
    L50:
        r19 = r6;
        r18 = true;
    L59:
        r16 = r4;
        r19 = r6;
        goto L52
    L25:
        FileLog.e("RoundVideoEncoder audio input buffer was null");
        failIfActive();
        return;
    L96:
        return;
    L70:
        e = move-exception;
        FileLog.e(e);
        failIfActive();
        return;
    L18:
        recyclePendingAudio();
        return;
    }

    private void finalizeCancel() {
        releaseEgl();
        releaseCodecs();
        releaseInputSurface();
        recyclePendingAudio();
        MP4Builder r0 = this.mediaMuxer;
        if (r0 != null) goto L20;
    L8:
        File r02 = this.fileToWrite;
        if (r02 != null) goto L22;
    L11:
        File r03 = this.videoFile;
        if (r03 != null) goto L18;
    L14:
        this.state = 6;
        scheduleQueueRecycle();
        AndroidUtilities.runOnUIThread(new RoundVideoEncoder$$ExternalSyntheticLambda11(this));
        return;
    L18:
        r03.delete();     // Catch: Throwable -> L17
        goto L14
    L22:
        r02.delete();     // Catch: Throwable -> L16
        goto L11
    L20:
        r0.finishMovie();     // Catch: Exception -> L6
    L6:
        e = move-exception;
        FileLog.e(e);
        goto L8
    }

    private void finalizeFailure() {
        releaseEgl();
        releaseCodecs();
        releaseInputSurface();
        recyclePendingAudio();
        MP4Builder r0 = this.mediaMuxer;
        if (r0 != null) goto L20;
    L8:
        File r02 = this.fileToWrite;
        if (r02 != null) goto L22;
    L11:
        File r03 = this.videoFile;
        if (r03 != null) goto L18;
    L14:
        this.state = 7;
        scheduleQueueRecycle();
        AndroidUtilities.runOnUIThread(new RoundVideoEncoder$$ExternalSyntheticLambda6(this));
        return;
    L18:
        r03.delete();     // Catch: Throwable -> L17
        goto L14
    L22:
        r02.delete();     // Catch: Throwable -> L16
        goto L11
    L20:
        r0.finishMovie();     // Catch: Exception -> L6
    L6:
        e = move-exception;
        FileLog.e(e);
        goto L8
    }

    private void finalizeStop() {
        if (this.segmentVideoOriginNs == (-1)) goto L5;
    L7:
        boolean r0 = drainToEndOfStream();
        releaseEgl();
        releaseCodecs();
        MP4Builder r1 = this.mediaMuxer;
        if (r1 != null) goto L55;
    L34:
        if (r0 == true) goto L43;
        File r12 = this.fileToWrite;
        if (r12 != null) goto L57;
    L38:
        File r13 = this.videoFile;
        if (r13 == null) goto L43;
        if (r13.equals(this.fileToWrite) == true) goto L43;
        this.videoFile.delete();     // Catch: Throwable -> L54
        goto L43
    L57:
        r12.delete();     // Catch: Throwable -> L53
    L43:
        releaseInputSurface();
        recyclePendingAudio();
        if (r0 == false) goto L46;
        int r14 = 6;
    L47:
        this.state = r14;
        scheduleQueueRecycle();
        if (r0 == false) goto L50;
        FinishReason r02 = FinishReason.COMPLETED;
    L51:
        AndroidUtilities.runOnUIThread(new RoundVideoEncoder$$ExternalSyntheticLambda9(this, r02));
        return;
    L50:
        r02 = FinishReason.FAILED;
        goto L51
    L46:
        r14 = 7;
        goto L47
    L55:
        r1.setAllowSyncFiles(this.allowSendingWhileRecording);     // Catch: Exception -> L12
        this.mediaMuxer.finishMovie();     // Catch: Exception -> L12
    L14:
        FileLog.d("RoundVideoEncoder finished muxer, video end " + videoEndTimeUs() + "us audio end " + this.audioTotalEndUs + "us");
        if (this.writingToDifferentFile == false) goto L34;
        if (this.videoFile.exists() == false) goto L23;
        this.videoFile.delete();     // Catch: Exception -> L20
    L20:
        e = move-exception;
        FileLog.e("RoundVideoEncoder copying fileToWrite to videoFile, deleting videoFile error " + this.videoFile);
        FileLog.e(e);
    L23:
        if (this.fileToWrite.renameTo(this.videoFile) == true) goto L34;
        FileLog.e("RoundVideoEncoder unable to rename file, try move file");
    L28:
        e = move-exception;
        FileLog.e(e);
        FileLog.e("RoundVideoEncoder unable to move file");
    L33:
        r0 = false;
        goto L34
    L26:
        if (AndroidUtilities.copyFile(this.fileToWrite, this.videoFile) == false) goto L30;
        this.fileToWrite.delete();     // Catch: IOException -> L28
        goto L34
    L30:
        FileLog.e("RoundVideoEncoder unable to copy file");     // Catch: IOException -> L28
    L12:
        e = move-exception;
        FileLog.e(e);
        r0 = false;
        goto L14
    L5:
        if (this.audioSegmentBaseUs != (-1)) goto L7;
        recyclePendingAudio();
        goto L7
    }

    private void finishPause() {
        feedPendingAudio();
        long r0 = SystemClock.elapsedRealtime() + 500;     // Catch: Exception -> L11
    L4:
        if (SystemClock.elapsedRealtime() >= r0) goto L13;
        drainVideoOnce(NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS);     // Catch: Exception -> L11
        drainAudioOnce(NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS);     // Catch: Exception -> L11
        feedPendingAudio();     // Catch: Exception -> L11
        if (hasFeedablePendingAudio() == true) goto L4;
        if (hasReachedPauseTargets() == false) goto L4;
    L13:
        recyclePendingAudio();
        MP4Builder r02 = this.mediaMuxer;
        if (r02 != null) goto L16;
    L22:
        releaseEgl();
        File r03 = this.pausePreviewFile;
        this.pausePreviewFile = null;
        this.state = 4;
        if (BuildVars.LOGS_ENABLED == false) goto L25;
        FileLog.d("RoundVideoEncoder paused, video end " + videoEndTimeUs() + "us audio end " + this.audioTotalEndUs + "us");
    L25:
        int r3 = this.deferredFinish;
        if (r3 != 0) goto L28;
        EGLContext r1 = this.pendingResumeContext;
        if (r1 == null) goto L37;
        this.pendingResumeContext = null;
        handleResume(r1);
        return;
    L37:
        AndroidUtilities.runOnUIThread(new RoundVideoEncoder$$ExternalSyntheticLambda5(this, r03));
        return;
    L28:
        if (r3 != 2) goto L30;
        boolean r04 = true;
    L31:
        this.deferredFinish = 0;
        this.pendingResumeContext = null;
        handleFinish(r04);
        return;
    L30:
        r04 = false;
        goto L31
    L16:
        if (this.pausePreviewFile == null) goto L22;
        r02.setAllowSyncFiles(this.allowSendingWhileRecording);     // Catch: Exception -> L19
        this.mediaMuxer.finishMovie(this.pausePreviewFile);     // Catch: Exception -> L19
        this.mediaMuxer.setAllowSyncFiles(false);     // Catch: Exception -> L19
    L19:
        e = move-exception;
        FileLog.e(e);
        fail();
        return;
    L11:
        e = move-exception;
        FileLog.e(e);
        fail();
    }

    private AudioCaptureSession getActiveAudioCapture() {
        Object r0 = this.audioCaptureLock;
        monitor-enter(r0);
        AudioCaptureSession r1 = this.activeAudioCapture;     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    private void handleAudioBatch(AudioChunkBatch r5) {
        if (this.state != 2) goto L5;
    L13:
        this.pendingAudio.add(r5);
        if (this.segmentVideoOriginNs == (-1)) goto L16;
    L24:
        drainEncoders();     // Catch: Exception -> L21
        feedPendingAudio();
        return;
    L21:
        e = move-exception;
        FileLog.e(e);
        fail();
        return;
    L16:
        if (this.pendingAudio.size() <= 24) goto L24;
        recycleAudioBatch(this.pendingAudio.remove(0));
        goto L24
    L5:
        if (this.state == 3) goto L13;
        if (this.state == 5) goto L9;
    L11:
        recycleAudioBatch(r5);
        return;
    L9:
        if (this.waitingAudioTail == false) goto L11;
        goto L11
    }

    private void handleAudioCaptureFinished(AudioCaptureSession r3) {
        if (r3.generation != this.audioCaptureGeneration) goto L28;
        this.audioCaptureRunning = false;
        int r0 = this.deferredAudioCleanup;
        if (r0 == 0) goto L14;
        this.deferredAudioCleanup = 0;
        if (r0 != 1) goto L11;
        finalizeCancel();
        return;
    L11:
        finalizeFailure();
        return;
    L14:
        if (this.state != 3) goto L18;
        finishPause();
        return;
    L18:
        if (this.state != 5) goto L24;
        if (this.waitingAudioTail == false) goto L24;
        this.waitingAudioTail = false;
        finalizeStop();
        return;
    L24:
        if (this.state != 2) goto L27;
        FileLog.e("RoundVideoEncoder audio capture ended unexpectedly");
        fail();
        return;
    L27:
        return;
    }

    private void handleFinish(boolean r6) {
        if (this.state != 6) goto L5;
        return;
    L5:
        if (this.state != 7) goto L7;
        return;
    L7:
        if (this.state == 5) goto L52;
        int r4 = 1;
        if (this.state != 3) goto L16;
        if (r6 == false) goto L13;
        r4 = 2;
    L13:
        this.deferredFinish = r4;
        return;
    L16:
        if (BuildVars.LOGS_ENABLED == false) goto L19;
        FileLog.d("RoundVideoEncoder finish cancel=" + r6 + " state=" + this.state);
    L19:
        if (this.state != 0) goto L26;
        this.state = 6;
        scheduleQueueRecycle();
        if (r6 == false) goto L23;
        FinishReason r62 = FinishReason.CANCELLED;
    L24:
        AndroidUtilities.runOnUIThread(new RoundVideoEncoder$$ExternalSyntheticLambda8(this, r62));
        return;
    L23:
        r62 = FinishReason.COMPLETED;
        goto L24
    L26:
        this.state = 5;
        if (r6 == true) goto L29;
        this.audioCapUs = Math.min(this.audioCapUs, videoEndTimeUs());
        MediaCodec r63 = this.videoEncoder;
        if (r63 == null) goto L43;
        if (this.videoEosSignalled == true) goto L43;
        r63.signalEndOfInputStream();     // Catch: Exception -> L40
        this.videoEosSignalled = true;     // Catch: Exception -> L40
    L40:
        e = move-exception;
        FileLog.e(e);
        this.videoEosSeen = true;
    L43:
        if (this.audioCaptureRunning == false) goto L45;
        stopAudioCapture(false);
        this.waitingAudioTail = true;
        return;
    L45:
        finalizeStop();
        return;
    L29:
        if (stopAudioCapture(true) == false) goto L32;
        finalizeCancel();
        return;
    L32:
        this.deferredAudioCleanup = 1;
        return;
    }

    private void handleFrame() {
        Object r2 = this.pendingFrameLock;
        monitor-enter(r2);
    L8:
        th = move-exception;
        throw th;
    L5:
        if (this.pendingFrameSet == true) goto L10;
        monitor-exit(r2);     // Catch: Throwable -> L8
        return;
    L10:
        this.pendingFrameSet = false;     // Catch: Throwable -> L8
        this.currentFrame.copyFrom(this.pendingFrame);     // Catch: Throwable -> L8
        monitor-exit(r2);     // Catch: Throwable -> L8
        FrameSnapshot r22 = this.currentFrame;
        long r3 = r22.sourceTimestampNs;
        long r5 = r22.arrivalTimeNs;
        int r23 = r22.cameraId;
        if (this.state != 2) goto L76;
        drainEncoders();     // Catch: Exception -> L67
        feedPendingAudio();
        boolean r8 = true;
        if (r23 == this.lastCameraId) goto L19;
        boolean r7 = true;
    L20:
        this.lastCameraId = r23;
        if (r3 > 0) goto L24;
        this.sourceAnchorSet = false;
        long r13 = r5;
        long r15 = 0;
    L35:
        if (this.segmentFirstArrivalNs != (-1)) goto L38;
        this.segmentFirstArrivalNs = r5;
    L38:
        if ((r5 - this.segmentFirstArrivalNs) < 200000000) goto L77;
        long r24 = this.segmentVideoOriginNs;
        if (r24 == (-1)) goto L43;
        long r6 = this.segmentActiveBaseNs + (r13 - r24);
        long r25 = (this.frameRate * r6) / 1000000000;
        if (r25 > this.lastVideoFrameIndex) goto L60;
        return;
    L60:
        if ((r6 / 1000) >= this.maxDurationUs) goto L75;
        if (r8 == false) goto L64;
        long r4 = r6;
        long r62 = r15;
    L65:
        acceptFrame(r25, r4, r62);
        return;
    L64:
        r4 = r6;
        r62 = r6 - this.lastVideoActiveTimeNs;
        goto L65
    L75:
        return;
    L43:
        if (this.lastVideoFrameIndex >= r15) goto L45;
        long r9 = r15;
    L47:
        if ((r9 / 1000) >= this.maxDurationUs) goto L78;
        long r26 = (this.frameRate * r9) / 1000000000;
        this.segmentVideoOriginNs = r13;
        this.segmentActiveBaseNs = r9;
        if (BuildVars.LOGS_ENABLED == false) goto L53;
        FileLog.d("RoundVideoEncoder segment origin at " + r9 + "ns slot " + r26);
    L53:
        if (acceptFrame(r26, r9, 0) == false) goto L61;
        feedPendingAudio();
        return;
    L61:
        return;
    L78:
        return;
    L45:
        r9 = this.lastVideoActiveTimeNs + videoFallbackFrameDurationNs();
        goto L47
    L77:
        return;
    L24:
        if (this.sourceAnchorSet == false) goto L30;
        if (r7 == true) goto L30;
        long r132 = this.lastSourceTimestampNs;
        if (r3 <= r132) goto L30;
        if ((r3 - r132) > 1000000000) goto L30;
        r15 = 0;
        r13 = this.sourceAnchorMonotonicNs + (r3 - this.sourceAnchorSourceNs);
        r8 = r7;
    L33:
        this.lastSourceTimestampNs = r3;
    L30:
        r15 = 0;
        this.sourceAnchorSourceNs = r3;
        this.sourceAnchorMonotonicNs = r5;
        this.sourceAnchorSet = true;
        r13 = r5;
        goto L33
    L19:
        r7 = false;
    L67:
        e = move-exception;
        FileLog.e(e);
        fail();
        return;
    }

    private void handlePause(File r5) {
        if (this.state == 2) goto L6;
        return;
    L6:
        if (BuildVars.LOGS_ENABLED == false) goto L8;
        FileLog.d("RoundVideoEncoder pause");
    L8:
        this.state = 3;
        this.pausePreviewFile = r5;
        this.audioCapUs = Math.min(this.audioCapUs, videoEndTimeUs());
        if (this.audioCaptureRunning == false) goto L12;
        stopAudioCapture(false);
        return;
    L12:
        finishPause();
    }

    private void handleResume(EGLContext r3) {
        if (this.state == 4) goto L5;
        return;
    L5:
        this.state = 1;
        if (BuildVars.LOGS_ENABLED == false) goto L14;
        FileLog.d("RoundVideoEncoder resume");
    L14:
        this.sourceAnchorSet = false;     // Catch: Throwable -> L11
        this.segmentFirstArrivalNs = -1;     // Catch: Throwable -> L11
        this.segmentVideoOriginNs = -1;     // Catch: Throwable -> L11
        this.audioSegmentBaseUs = -1;     // Catch: Throwable -> L11
        this.audioCapUs = this.maxDurationUs;     // Catch: Throwable -> L11
        createEncoderEgl(r3);     // Catch: Throwable -> L11
        startAudioCapture();     // Catch: Throwable -> L11
        this.state = 2;     // Catch: Throwable -> L11
        AndroidUtilities.runOnUIThread(new RoundVideoEncoder$$ExternalSyntheticLambda12(this));     // Catch: Throwable -> L11
        return;
    L11:
        th = move-exception;
        FileLog.e(th);
        fail();
    }

    private void handleStart(File r9, EGLContext r10, int r11) {
        if (this.state != 4) goto L7;
        handleResume(r10);
        return;
    L7:
        if (this.state != 3) goto L11;
        this.pendingResumeContext = r10;
        return;
    L11:
        if (this.state != 0) goto L50;
        this.state = 1;
        if (BuildVars.LOGS_ENABLED == false) goto L48;
        FileLog.d("RoundVideoEncoder start " + r9);
    L48:
        this.videoFile = r9;     // Catch: Throwable -> L26
        int r92 = SystemUtils.getRoundVideoResolution();     // Catch: Throwable -> L26
        this.videoWidth = r92;     // Catch: Throwable -> L26
        this.videoHeight = r92;     // Catch: Throwable -> L26
        this.videoBitrate = SystemUtils.getRoundVideoBitrate() * 1024;     // Catch: Throwable -> L26
        int r93 = 60;
        if (r11 == 60) goto L20;
        r93 = 30;
    L20:
        this.frameRate = r93;     // Catch: Throwable -> L26
        long r4 = SystemUtils.getRoundVideoMaxDurationMs() * 1000;     // Catch: Throwable -> L26
        this.maxDurationUs = r4;     // Catch: Throwable -> L26
        this.audioCapUs = r4;     // Catch: Throwable -> L26
        this.allowSendingWhileRecording = SharedConfig.deviceIsHigh();     // Catch: Throwable -> L26
        prepareAudioBatches();     // Catch: Throwable -> L26
        this.videoBufferInfo = new MediaCodec.BufferInfo();     // Catch: Throwable -> L26
        this.audioBufferInfo = new MediaCodec.BufferInfo();     // Catch: Throwable -> L26
        MediaFormat r94 = new MediaFormat();     // Catch: Throwable -> L26
        r94.setString("mime", MediaController.AUDIO_MIME_TYPE);     // Catch: Throwable -> L26
        r94.setInteger("sample-rate", 48000);     // Catch: Throwable -> L26
        r94.setInteger("channel-count", 1);     // Catch: Throwable -> L26
        r94.setInteger("bitrate", SystemUtils.getRoundAudioBitrate() * 1024);     // Catch: Throwable -> L26
        r94.setInteger("max-input-size", 20480);     // Catch: Throwable -> L26
        MediaCodec r112 = MediaCodec.createEncoderByType(MediaController.AUDIO_MIME_TYPE);     // Catch: Throwable -> L26
        this.audioEncoder = r112;     // Catch: Throwable -> L26
        r112.configure(r94, null, null, 1);     // Catch: Throwable -> L26
        this.audioEncoder.start();     // Catch: Throwable -> L26
        MediaFormat r95 = MediaFormat.createVideoFormat(MediaController.VIDEO_MIME_TYPE, this.videoWidth, this.videoHeight);     // Catch: Throwable -> L26
        r95.setInteger("color-format", 2130708361);     // Catch: Throwable -> L26
        r95.setInteger("bitrate", this.videoBitrate);     // Catch: Throwable -> L26
        r95.setInteger("max-bitrate", this.videoBitrate);     // Catch: Throwable -> L26
        MediaCodec r113 = MediaCodec.createEncoderByType(MediaController.VIDEO_MIME_TYPE);     // Catch: Throwable -> L26
        this.videoEncoder = r113;     // Catch: Throwable -> L26
    L28:
        e = move-exception;
        FileLog.e(e);     // Catch: Throwable -> L26
    L30:
        r95.setInteger("frame-rate", this.frameRate);     // Catch: Throwable -> L26
        r95.setInteger("i-frame-interval", 1);     // Catch: Throwable -> L26
        this.videoEncoder.configure(r95, null, null, 1);     // Catch: Throwable -> L26
        this.inputSurface = this.videoEncoder.createInputSurface();     // Catch: Throwable -> L26
        this.videoEncoder.start();     // Catch: Throwable -> L26
        this.firstEncode = true;     // Catch: Throwable -> L26
        File r96 = this.videoFile;     // Catch: Throwable -> L26
        this.fileToWrite = r96;     // Catch: Throwable -> L26
        this.writingToDifferentFile = false;     // Catch: Throwable -> L26
        if (ImageLoader.isSdCardPath(r96) == true) goto L46;
    L40:
        Mp4Movie r97 = new Mp4Movie();     // Catch: Throwable -> L26
        r97.setCacheFile(this.fileToWrite);     // Catch: Throwable -> L26
        r97.setRotation(0);     // Catch: Throwable -> L26
        r97.setSize(this.videoWidth, this.videoHeight);     // Catch: Throwable -> L26
        MP4Builder r98 = new MP4Builder().createMovie(r97, this.isSecretChat, false);     // Catch: Throwable -> L26
        this.mediaMuxer = r98;     // Catch: Throwable -> L26
        r98.setAllowSyncFiles(false);     // Catch: Throwable -> L26
        createEncoderEgl(r10);     // Catch: Throwable -> L26
        startAudioCapture();     // Catch: Throwable -> L26
        this.state = 2;     // Catch: Throwable -> L26
        AndroidUtilities.runOnUIThread(new RoundVideoEncoder$$ExternalSyntheticLambda7(this));     // Catch: Throwable -> L26
        return;
    L46:
        File r99 = new File(ApplicationLoader.getFilesDirFixed(), "camera_tmp.mp4");     // Catch: Throwable -> L35
        this.fileToWrite = r99;     // Catch: Throwable -> L35
        if (r99.exists() == false) goto L37;
        this.fileToWrite.delete();     // Catch: Throwable -> L35
    L37:
        this.writingToDifferentFile = true;     // Catch: Throwable -> L35
    L35:
        th = move-exception;
        FileLog.e(th);     // Catch: Throwable -> L26
        this.fileToWrite = this.videoFile;     // Catch: Throwable -> L26
        this.writingToDifferentFile = false;     // Catch: Throwable -> L26
        goto L40
    L23:
        if (r113.getCodecInfo().getCapabilitiesForType(MediaController.VIDEO_MIME_TYPE).getEncoderCapabilities().isBitrateModeSupported(2) == false) goto L30;
        r95.setInteger("bitrate-mode", 2);     // Catch: Throwable -> L26 Exception -> L28
    L26:
        th = move-exception;
        FileLog.e(th);
        fail();
        return;
    }

    private boolean hasFeedablePendingAudio() {
    L3:
        if (this.pendingAudio.isEmpty() == true) goto L13;
        AudioChunkBatch r0 = this.pendingAudio.get(0);
        int r2 = r0.drained;
    L6:
        if (r2 >= r0.results) goto L12;
        if (r0.buffer[r2].remaining() > 0) goto L9;
        r2 = r2 + 1;
        goto L6
    L9:
        return true;
    L12:
        this.pendingAudio.remove(0);
        recycleAudioBatch(r0);
        goto L3
    L13:
        return false;
    }

    private boolean hasReachedPauseTargets() {
        long r0 = this.lastSubmittedVideoPtsUs;
        if (r0 >= 0) goto L5;
    L8:
        boolean r02 = true;
    L9:
        long r7 = this.lastSubmittedAudioEndUs;
        if (r7 >= 0) goto L12;
        long r72 = -1;
    L14:
        if (r72 >= 0) goto L16;
    L19:
        boolean r11 = true;
    L20:
        if (r02 == false) goto L23;
        if (r11 == false) goto L23;
        return true;
    L23:
        return false;
    L16:
        if (this.lastMuxedAudioPtsUs >= r72) goto L19;
        r11 = false;
        goto L20
    L12:
        r72 = Math.max(0, r7 - 21333);
        goto L14
    L5:
        if (this.lastMuxedVideoPtsUs >= r0) goto L8;
        r02 = false;
        goto L9
    }

    private /* synthetic */ void lambda$cancel$3() {
        handleFinish(true);
    }

    private /* synthetic */ void lambda$finalizeCancel$9() {
        this.callback.onFinished(FinishReason.CANCELLED);
    }

    private /* synthetic */ void lambda$finalizeFailure$10() {
        this.callback.onFinished(FinishReason.FAILED);
    }

    private /* synthetic */ void lambda$finalizeStop$8(FinishReason r1) {
        this.callback.onFinished(r1);
    }

    private /* synthetic */ void lambda$finishPause$6(File r1) {
        this.callback.onPaused(r1);
    }

    private /* synthetic */ void lambda$handleFinish$7(FinishReason r1) {
        this.callback.onFinished(r1);
    }

    private /* synthetic */ void lambda$handleResume$5() {
        this.callback.onRecordingStarted(true);
    }

    private /* synthetic */ void lambda$handleStart$4() {
        this.callback.onRecordingStarted(false);
    }

    private /* synthetic */ void lambda$pause$1(File r1) {
        handlePause(r1);
    }

    private /* synthetic */ void lambda$startRecording$0(File r1, EGLContext r2, int r3) {
        handleStart(r1, r2, r3);
    }

    private /* synthetic */ void lambda$stop$2() {
        handleFinish(false);
    }

    private boolean makeEglCurrent() {
        if (this.eglDisplay != EGL14.EGL_NO_DISPLAY) goto L5;
    L17:
        return false;
    L5:
        if (this.eglSurface == EGL14.EGL_NO_SURFACE) goto L17;
        if (this.eglContext.equals(EGL14.eglGetCurrentContext()) == true) goto L10;
    L11:
        EGLDisplay r0 = this.eglDisplay;
        EGLSurface r1 = this.eglSurface;
        if (EGL14.eglMakeCurrent(r0, r1, r1, this.eglContext) == true) goto L18;
        FileLog.e("RoundVideoEncoder eglMakeCurrent failed " + GLUtils.getEGLErrorString(EGL14.eglGetError()));
        return false;
    L18:
        return true;
    L10:
        if (this.eglSurface.equals(EGL14.eglGetCurrentSurface(12377)) == false) goto L11;
        return true;
    }

    private AudioChunkBatch obtainAudioBatch() {
        AudioChunkBatch r3 = this.audioBatchPool.poll(250, TimeUnit.MILLISECONDS);
        if (r3 != null) goto L6;
        return null;
    L6:
        r3.results = 0;
        r3.drained = 0;
        return r3;
    }

    private void prepareAudioBatches() {
        if (this.audioBatchesPrepared == false) goto L5;
        return;
    L5:
        int r0 = 0;
    L7:
        if (r0 >= 25) goto L9;
        this.audioBatchPool.add(new AudioChunkBatch(this));
        r0 = r0 + 1;
        goto L7
    L9:
        this.audioBatchesPrepared = true;
    }

    private void queueAudioEndOfStream() {
        int r5 = this.audioEncoder.dequeueInputBuffer(NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS);     // Catch: Exception -> L7
        if (r5 < 0) goto L9;
        this.audioEncoder.queueInputBuffer(r5, 0, 0, this.audioTotalEndUs, 4);     // Catch: Exception -> L7
        this.audioEosQueued = true;     // Catch: Exception -> L7
        return;
    L9:
        return;
    L7:
        e = move-exception;
        FileLog.e(e);
        this.audioEosSeen = true;
    }

    private void recycleAudioBatch(AudioChunkBatch r1) {
        if (this.audioBatchPool.offer(r1) == true) goto L6;
        FileLog.e("RoundVideoEncoder audio batch pool overflow");
        return;
    }

    private void recyclePendingAudio() {
        int r0 = 0;
    L3:
        int r1 = this.pendingAudio.size();
        ArrayList<AudioChunkBatch> r2 = this.pendingAudio;
        if (r0 >= r1) goto L6;
        recycleAudioBatch(r2.get(r0));
        r0 = r0 + 1;
        goto L3
    L6:
        r2.clear();
    }

    private void releaseAudioRecorder(AudioRecord r1) {
        stopAudioRecorder(r1);
        r1.release();     // Catch: Throwable -> L5
        return;
    L5:
        th = move-exception;
        FileLog.e(th);
    }

    private void releaseCodecs() {
        MediaCodec r0 = this.videoEncoder;
        if (r0 != null) goto L29;
    L13:
        MediaCodec r02 = this.audioEncoder;
        if (r02 == null) goto L33;
        r02.stop();     // Catch: Throwable -> L17
    L31:
        this.audioEncoder.release();     // Catch: Throwable -> L21
    L23:
        this.audioEncoder = null;
        setBluetoothScoOn(false);
        return;
    L21:
        th = move-exception;
        FileLog.e(th);
    L17:
        th = move-exception;
        FileLog.e(th);
        goto L31
    L33:
        return;
    L29:
        r0.stop();     // Catch: Throwable -> L6
    L27:
        this.videoEncoder.release();     // Catch: Throwable -> L10
    L12:
        this.videoEncoder = null;
    L10:
        th = move-exception;
        FileLog.e(th);
    L6:
        th = move-exception;
        FileLog.e(th);
        goto L27
    }

    private void releaseEgl() {
        if (this.eglDisplay != EGL14.EGL_NO_DISPLAY) goto L6;
        return;
    L6:
        if (makeEglCurrent() == true) goto L19;
    L11:
        EGLSurface r0 = this.eglSurface;
        if (r0 == EGL14.EGL_NO_SURFACE) goto L14;
        EGL14.eglDestroySurface(this.eglDisplay, r0);
        this.eglSurface = EGL14.EGL_NO_SURFACE;
    L14:
        EGLDisplay r02 = this.eglDisplay;
        EGLSurface r1 = EGL14.EGL_NO_SURFACE;
        EGL14.eglMakeCurrent(r02, r1, r1, EGL14.EGL_NO_CONTEXT);
        EGLContext r03 = this.eglContext;
        if (r03 == EGL14.EGL_NO_CONTEXT) goto L17;
        EGL14.eglDestroyContext(this.eglDisplay, r03);
        this.eglContext = EGL14.EGL_NO_CONTEXT;
    L17:
        EGL14.eglReleaseThread();
        EGL14.eglTerminate(this.eglDisplay);
        this.eglDisplay = EGL14.EGL_NO_DISPLAY;
        this.eglConfig = null;
        return;
    L19:
        this.renderer.onEncoderSurfaceDestroyed();     // Catch: Throwable -> L9
    L9:
        th = move-exception;
        FileLog.e(th);
        goto L11
    }

    private void releaseInputSurface() {
        Surface r0 = this.inputSurface;
        if (r0 == null) goto L12;
        r0.release();     // Catch: Throwable -> L6
    L8:
        this.inputSurface = null;
        return;
    L6:
        th = move-exception;
        FileLog.e(th);
        goto L8
    }

    private void requestAudioCaptureStop(AudioCaptureSession r1) {
        if (r1 == null) goto L5;
        r1.requestStop();
        return;
    }

    private void scheduleQueueRecycle() {
        DispatchQueue r1 = this.encoderQueue;
        Objects.requireNonNull(r1);
        r1.postRunnable(new RoundVideoEncoder$$ExternalSyntheticLambda10(r1));
    }

    private void setBluetoothScoOn(boolean r3) {
        AudioManager r2 = (AudioManager) ApplicationLoader.applicationContext.getSystemService(MediaStreamTrack.AUDIO_TRACK_KIND);
        if (SharedConfig.recordViaSco == false) goto L8;
        if (PermissionRequest.hasPermission("android.permission.BLUETOOTH_CONNECT") == true) goto L8;
        SharedConfig.recordViaSco = false;
        SharedConfig.saveConfig();
    L8:
        if (r2.isBluetoothScoAvailableOffCall() == true) goto L10;
    L11:
        if (r3 == true) goto L44;
    L12:
        BluetoothAdapter r0 = BluetoothAdapter.getDefaultAdapter();
        if (r0 != null) goto L43;
    L20:
        if (r3 == true) goto L45;
    L21:
        if (r3 == true) goto L23;
    L26:
        if (r3 == false) goto L28;
        return;
    L28:
        if (r2.isBluetoothScoOn() == false) goto L47;
        r2.stopBluetoothSco();     // Catch: Throwable -> L18 SecurityException -> L40
        return;
    L47:
        return;
    L23:
        if (r2.isBluetoothScoOn() == true) goto L26;
        r2.startBluetoothSco();     // Catch: Throwable -> L18 SecurityException -> L40
        return;
    L45:
        return;
    L43:
    L18:
        th = move-exception;
        FileLog.e(th);
        if (r3 == true) goto L48;
    L37:
        e = move-exception;
        FileLog.e(e);
        return;
    L34:
        if (r2.isBluetoothScoOn() == false) goto L49;
        r2.stopBluetoothSco();     // Catch: Exception -> L37
        return;
    L49:
        return;
    L48:
        return;
    L16:
        if (r0.getProfileConnectionState(1) == 2) goto L21;
    L51:
        return;
    L44:
        return;
    L10:
        if (SharedConfig.recordViaSco == true) goto L12;
        goto L11
    }

    private void startAudioCapture() {
        setBluetoothScoOn(true);
        int r2 = AudioRecord.getMinBufferSize(48000, 16, 2);
        if (r2 > 0) goto L5;
        r2 = 3584;
    L5:
        int r3 = 49152;
        if (49152 >= r2) goto L8;
        r3 = ((r2 / 2048) + 1) * 4096;
    L8:
        int r9 = r3;
        AudioRecord r4 = new AudioRecord(0, 48000, 16, 2, r9);
    L14:
        th = th;
        AudioCaptureSession r1 = null;
    L30:
        Object r32 = this.audioCaptureLock;
        monitor-enter(r32);
    L35:
        th = move-exception;
        throw th;
    L33:
        if (this.activeAudioCapture != r1) goto L37;
        this.activeAudioCapture = null;     // Catch: Throwable -> L35
    L37:
        monitor-exit(r32);     // Catch: Throwable -> L35
        this.audioCaptureRunning = false;
        if (r1 == null) goto L41;
        r1.releaseRecorder();
    L42:
        throw th;
    L41:
        releaseAudioRecorder(r4);
        goto L42
    L10:
        if (r4.getState() != 1) goto L29;
        r4.startRecording();     // Catch: Throwable -> L14
        if (BuildVars.LOGS_ENABLED == false) goto L16;
        FileLog.d("RoundVideoEncoder initied audio record with channels " + r4.getChannelCount() + " sample rate = " + r4.getSampleRate() + " bufferSize = " + r9);     // Catch: Throwable -> L14
    L16:
        this.audioCaptureRunning = true;     // Catch: Throwable -> L14
        int r0 = this.audioCaptureGeneration + 1;     // Catch: Throwable -> L14
        this.audioCaptureGeneration = r0;     // Catch: Throwable -> L14
        r1 = new AudioCaptureSession(this, r4, r0);     // Catch: Throwable -> L14
        Thread r02 = new Thread(new AudioCaptureRunnable(this, r1), "RoundVideoAudioCapture");     // Catch: Throwable -> L23
        r1.thread = r02;     // Catch: Throwable -> L23
        r02.setPriority(10);     // Catch: Throwable -> L23
        Object r33 = this.audioCaptureLock;     // Catch: Throwable -> L23
        monitor-enter(r33);     // Catch: Throwable -> L23
        this.activeAudioCapture = r1;     // Catch: Throwable -> L25
        monitor-exit(r33);     // Catch: Throwable -> L25
        r02.start();     // Catch: Throwable -> L23
        return;
    L25:
        th = move-exception;
        throw th;     // Catch: Throwable -> L23
    L23:
        th = th;
        goto L30
    L29:
        throw new RuntimeException("AudioRecord init failed");     // Catch: Throwable -> L14
    }

    private boolean stopAudioCapture(boolean r4) {
        AudioCaptureSession r0 = getActiveAudioCapture();
        requestAudioCaptureStop(r0);
        if (r0 != null) goto L5;
        return true;
    L5:
        Thread r02 = r0.thread;
        if (r4 == false) goto L17;
        if (r02 == null) goto L17;
        if (r02 == Thread.currentThread()) goto L17;
        r02.join(1500);     // Catch: InterruptedException -> L13
    L15:
        if (r02.isAlive() == false) goto L17;
        FileLog.e("RoundVideoEncoder audio thread did not stop within timeout");
    L13:
        Thread.currentThread().interrupt();
    L17:
        if (r02 != null) goto L19;
        return true;
    L19:
        if (r02.isAlive() == false) goto L26;
        return false;
    L26:
        return true;
    }

    private void stopAudioRecorder(AudioRecord r2) {
    L6:
        th = move-exception;
        FileLog.e(th);
        return;
    L3:
        if (r2.getRecordingState() == 1) goto L11;
        r2.stop();     // Catch: Throwable -> L6
        return;
    }

    private long videoEndTimeUs() {
        if (this.lastVideoFrameIndex >= 0) goto L6;
        return 0;
    L6:
        return (this.lastVideoActiveTimeNs + videoFallbackFrameDurationNs()) / 1000;
    }

    private long videoFallbackFrameDurationNs() {
        long r0 = this.minVideoFrameDeltaNs;
        if (r0 <= 0) goto L7;
        return Math.max(r0, 1000000);
    L7:
        return 1000000000 / this.frameRate;
    }

    public void cancel() {
        if (this.finishRequested.compareAndSet(false, true) == true) goto L5;
        return;
    L5:
        AudioCaptureSession r0 = getActiveAudioCapture();
        this.encoderQueue.postRunnable(new RoundVideoEncoder$$ExternalSyntheticLambda2(this));
        requestAudioCaptureStop(r0);
    }

    public void frameAvailable(FrameSnapshot r3) {
        if (this.started == true) goto L5;
        return;
    L5:
        Object r0 = this.pendingFrameLock;
        monitor-enter(r0);
        this.pendingFrame.copyFrom(r3);     // Catch: Throwable -> L11
        if (this.pendingFrameSet == false) goto L13;
        monitor-exit(r0);     // Catch: Throwable -> L11
        return;
    L13:
        this.pendingFrameSet = true;     // Catch: Throwable -> L11
        monitor-exit(r0);     // Catch: Throwable -> L11
        this.encoderQueue.postRunnable(new RoundVideoEncoder$$ExternalSyntheticLambda4(this));
        return;
    L11:
        th = move-exception;
        throw th;
    }

    public boolean isStarted() {
        return this.started;
    }

    public void pause(File r4) {
        AudioCaptureSession r0 = getActiveAudioCapture();
        this.encoderQueue.postRunnable(new RoundVideoEncoder$$ExternalSyntheticLambda1(this, r4));
        requestAudioCaptureStop(r0);
    }

    public void startRecording(File r3, EGLContext r4, int r5) {
        this.started = true;
        this.encoderQueue.postRunnable(new RoundVideoEncoder$$ExternalSyntheticLambda3(this, r3, r4, r5));
    }

    public void stop() {
        if (this.finishRequested.compareAndSet(false, true) == true) goto L5;
        return;
    L5:
        AudioCaptureSession r0 = getActiveAudioCapture();
        this.encoderQueue.postRunnable(new RoundVideoEncoder$$ExternalSyntheticLambda0(this));
        requestAudioCaptureStop(r0);
    }
}
