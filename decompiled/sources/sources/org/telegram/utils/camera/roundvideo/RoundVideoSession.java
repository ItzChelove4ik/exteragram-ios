package org.telegram.utils.camera.roundvideo;

import android.content.Context;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.TextureView;
import androidx.media3.common.MediaItem;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.Player;
import androidx.media3.exoplayer.ExoPlayer;
import com.google.android.gms.cast.MediaError;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import okio.Segment$$ExternalSyntheticBUOutline1;
import okio.internal.ZipFilesKt$$ExternalSyntheticBUOutline1;
import org.telegram.utils.camera.roundvideo.RoundVideoCameraController;
import org.telegram.utils.camera.roundvideo.RoundVideoRemuxer;
import org.vosk.Model$$ExternalSyntheticBUOutline0;

/* loaded from: classes6.dex */
public final class RoundVideoSession {
    private CameraResolution activeCameraResolution;
    private CameraFacing activeFacing;
    private FrameRate activeFrameRate;
    private final RoundVideoCameraController.Callback cameraCallback;
    private final RoundVideoCameraController cameraController;
    private boolean cameraStopPending;
    private int cameraSwitchCount;
    private boolean cameraSwitching;
    private boolean cancelAfterCameraStops;
    private volatile boolean cancelRequested;
    private boolean cleanupStarted;
    private final boolean compositionEnabled;
    private final Context context;
    private final RoundVideoDiagnostics diagnostics;
    private final Runnable durationLimitRunnable;
    private final ExecutorService fileExecutor;
    private boolean finishAfterCameraStops;
    private boolean finishWithAudio;
    private boolean flashEnabled;
    private FlashType flashType;
    private final Matrix identityPreviewTransform;
    private final Listener listener;
    private final Handler mainHandler;
    private final long maximumDurationMs;
    private float maximumZoomRatio;
    private long nextOutputId;
    private final File outputDirectory;
    private final ExecutorService outputExecutor;
    private volatile OutputGeneration outputGeneration;
    private final OutputListener outputListener;
    private final Object outputLock;
    private final OutputResolution outputResolution;
    private int pauseCount;
    private ExoPlayer player;
    private final Player.Listener playerListener;
    private volatile File previewFile;
    private boolean previewPlaying;
    private final Runnable previewProgressRunnable;
    private long previewSnapshotStartedNs;
    private long previewSourceDurationMs;
    private final TextureView previewView;
    private long recordedDurationMs;
    private long recordingStartedAtRealtimeMs;
    private final CameraResolution requestedCameraResolution;
    private CameraFacing requestedFacing;
    private final FrameRate requestedFrameRate;
    private int resumeCount;
    private final ScreenFlashController screenFlashController;
    private boolean screenFlashEnabled;
    private State state;
    private boolean summaryLogged;
    private long trimEndMs;
    private long trimStartMs;
    private final int videoBitrate;
    private volatile RoundVideoMp4Writer writer;
    private float zoomProgress;

    /* renamed from: org.telegram.utils.camera.roundvideo.RoundVideoSession$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ RoundVideoSession this$0;

        public AnonymousClass1(RoundVideoSession r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (RoundVideoSession.m22190$$Nest$fgetstate(this.this$0) == State.PREVIEWING) goto L5;
            return;
        L5:
            if (RoundVideoSession.m22187$$Nest$fgetplayer(this.this$0) != null) goto L7;
            return;
        L7:
            if (RoundVideoSession.m22188$$Nest$fgetpreviewPlaying(this.this$0) == false) goto L18;
            long r0 = RoundVideoSession.m22187$$Nest$fgetplayer(this.this$0).getCurrentPosition();
            if (r0 >= RoundVideoSession.m22192$$Nest$fgettrimStartMs(this.this$0)) goto L12;
        L13:
            RoundVideoSession.m22187$$Nest$fgetplayer(this.this$0).seekTo(RoundVideoSession.m22192$$Nest$fgettrimStartMs(this.this$0));
            r0 = RoundVideoSession.m22192$$Nest$fgettrimStartMs(this.this$0);
        L14:
            RoundVideoSession.m22185$$Nest$fgetlistener(this.this$0).onPreviewProgress(r0, RoundVideoSession.m22189$$Nest$fgetrecordedDurationMs(this.this$0));
            RoundVideoSession.m22186$$Nest$fgetmainHandler(this.this$0).postDelayed(this, 33);
            return;
        L12:
            if (r0 < RoundVideoSession.m22191$$Nest$fgettrimEndMs(this.this$0)) goto L14;
        }
    }

    /* renamed from: org.telegram.utils.camera.roundvideo.RoundVideoSession$2, reason: invalid class name */
    public class AnonymousClass2 implements RoundVideoCameraController.Callback {
        final /* synthetic */ RoundVideoSession this$0;

        public static /* synthetic */ void $r8$lambda$ItmFGDw5OkrDrtHygUF9YgfMCwc(AnonymousClass2 r0, Exception r1) {
            r0.lambda$onError$2(r1);
        }

        public static /* synthetic */ void $r8$lambda$_4dJfQvAS6FobjN0vqbmQo3zSMU(RoundVideoSession r0) {
            RoundVideoSession.m22197$$Nest$mhandleRecordingStarted(r0);
        }

        /* renamed from: $r8$lambda$iMiBbiM_nkZoK-GCXxlfYT5pidQ, reason: not valid java name */
        public static /* synthetic */ void m22201$r8$lambda$iMiBbiM_nkZoKGCXxlfYT5pidQ(AnonymousClass2 r0, RoundVideoCameraController.CameraInfo r1, boolean r2) {
            r0.lambda$onConfigured$0(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$kKYFSfscYIzexiEky3RXUdmYbng(AnonymousClass2 r0, CameraFacing r1) {
            r0.lambda$onCameraSwitchStarted$1(r1);
        }

        public static /* synthetic */ void $r8$lambda$tjIIOPNxMhgHG35MEKIarpsBCw8(RoundVideoSession r0) {
            RoundVideoSession.m22198$$Nest$mhandleRecordingStopped(r0);
        }

        public AnonymousClass2(RoundVideoSession r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onCameraSwitchStarted$1(CameraFacing r4) {
            RoundVideoSession r0 = this.this$0;
            RoundVideoSession.m22193$$Nest$fputcameraSwitchCount(r0, RoundVideoSession.m22183$$Nest$fgetcameraSwitchCount(r0) + 1);
            RoundVideoSession.m22194$$Nest$fputcameraSwitching(this.this$0, true);
            RoundVideoSession.m22184$$Nest$fgetdiagnostics(this.this$0).log("camera switch started: target=" + r4);
            RoundVideoSession.m22185$$Nest$fgetlistener(this.this$0).onCameraSwitchStarted(r4);
            RoundVideoSession.m22199$$Nest$mnotifyState(this.this$0);
        }

        private /* synthetic */ void lambda$onConfigured$0(RoundVideoCameraController.CameraInfo r1, boolean r2) {
            RoundVideoSession.m22196$$Nest$mhandleCameraConfigured(this.this$0, r1, r2);
        }

        private /* synthetic */ void lambda$onError$2(Exception r1) {
            RoundVideoSession.m22195$$Nest$mfail(this.this$0, r1);
        }

        @Override // org.telegram.utils.camera.roundvideo.RoundVideoCameraController.Callback
        public void onCameraSwitchFirstFrame() {
            Handler r0 = RoundVideoSession.m22186$$Nest$fgetmainHandler(this.this$0);
            Listener r2 = RoundVideoSession.m22185$$Nest$fgetlistener(this.this$0);
            Objects.requireNonNull(r2);
            r0.post(new RoundVideoSession$2$$ExternalSyntheticLambda5(r2));
        }

        @Override // org.telegram.utils.camera.roundvideo.RoundVideoCameraController.Callback
        public void onCameraSwitchStarted(CameraFacing r3) {
            RoundVideoSession.m22186$$Nest$fgetmainHandler(this.this$0).post(new RoundVideoSession$2$$ExternalSyntheticLambda2(this, r3));
        }

        @Override // org.telegram.utils.camera.roundvideo.RoundVideoCameraController.Callback
        public void onConfigured(RoundVideoCameraController.CameraInfo r3, boolean r4) {
            RoundVideoSession.m22186$$Nest$fgetmainHandler(this.this$0).post(new RoundVideoSession$2$$ExternalSyntheticLambda4(this, r3, r4));
        }

        @Override // org.telegram.utils.camera.roundvideo.RoundVideoCameraController.Callback
        public void onError(Exception r3) {
            RoundVideoSession.m22186$$Nest$fgetmainHandler(this.this$0).post(new RoundVideoSession$2$$ExternalSyntheticLambda3(this, r3));
        }

        @Override // org.telegram.utils.camera.roundvideo.RoundVideoCameraController.Callback
        public void onRecordingStarted() {
            RoundVideoSession.m22186$$Nest$fgetmainHandler(this.this$0).post(new RoundVideoSession$2$$ExternalSyntheticLambda0(this.this$0));
        }

        @Override // org.telegram.utils.camera.roundvideo.RoundVideoCameraController.Callback
        public void onRecordingStopped() {
            RoundVideoSession.m22186$$Nest$fgetmainHandler(this.this$0).post(new RoundVideoSession$2$$ExternalSyntheticLambda1(this.this$0));
        }
    }

    /* renamed from: org.telegram.utils.camera.roundvideo.RoundVideoSession$3, reason: invalid class name */
    public class AnonymousClass3 implements Player.Listener {
        final /* synthetic */ RoundVideoSession this$0;

        public AnonymousClass3(RoundVideoSession r1) {
            this.this$0 = r1;
        }

        @Override // androidx.media3.common.Player.Listener
        public void onIsPlayingChanged(boolean r3) {
            if (RoundVideoSession.m22190$$Nest$fgetstate(this.this$0) != State.PREVIEWING) goto L6;
            RoundVideoSession.m22200$$Nest$mupdatePreviewPlaying(this.this$0, r3);
            return;
        }

        @Override // androidx.media3.common.Player.Listener
        public void onPlaybackStateChanged(int r4) {
            RoundVideoSession.m22184$$Nest$fgetdiagnostics(this.this$0).log("preview playback state=" + r4);
            if (RoundVideoSession.m22190$$Nest$fgetstate(this.this$0) == State.PREVIEWING) goto L5;
            return;
        L5:
            if (r4 == 4) goto L7;
            return;
        L7:
            if (RoundVideoSession.m22187$$Nest$fgetplayer(this.this$0) == null) goto L12;
            RoundVideoSession.m22187$$Nest$fgetplayer(this.this$0).seekTo(RoundVideoSession.m22192$$Nest$fgettrimStartMs(this.this$0));
            RoundVideoSession.m22187$$Nest$fgetplayer(this.this$0).play();
            return;
        }

        @Override // androidx.media3.common.Player.Listener
        public void onPlayerError(PlaybackException r4) {
            RoundVideoSession.m22184$$Nest$fgetdiagnostics(this.this$0).log("preview player error: code=" + r4.errorCode);
            RoundVideoSession.m22195$$Nest$mfail(this.this$0, r4);
        }

        @Override // androidx.media3.common.Player.Listener
        public void onRenderedFirstFrame() {
            if (RoundVideoSession.m22190$$Nest$fgetstate(this.this$0) != State.PREVIEWING) goto L6;
            RoundVideoSession.m22185$$Nest$fgetlistener(this.this$0).onPreviewFirstFrame();
            return;
        }
    }

    public static final class Builder {
        private CameraResolution cameraResolution;
        private boolean compositionEnabled;
        private final Context context;
        private FrameRate frameRate;
        private CameraFacing initialFacing;
        private Listener listener;
        private long maximumDurationMs;
        private File outputDirectory;
        private OutputListener outputListener;
        private OutputResolution outputResolution;
        private final TextureView previewView;
        private ScreenFlashController screenFlashController;
        private int videoBitrate;

        /* renamed from: -$$Nest$fgetcameraResolution, reason: not valid java name */
        public static /* bridge */ /* synthetic */ CameraResolution m22202$$Nest$fgetcameraResolution(Builder r0) {
            return r0.cameraResolution;
        }

        /* renamed from: -$$Nest$fgetcompositionEnabled, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m22203$$Nest$fgetcompositionEnabled(Builder r0) {
            return r0.compositionEnabled;
        }

        /* renamed from: -$$Nest$fgetcontext, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Context m22204$$Nest$fgetcontext(Builder r0) {
            return r0.context;
        }

        /* renamed from: -$$Nest$fgetframeRate, reason: not valid java name */
        public static /* bridge */ /* synthetic */ FrameRate m22205$$Nest$fgetframeRate(Builder r0) {
            return r0.frameRate;
        }

        /* renamed from: -$$Nest$fgetinitialFacing, reason: not valid java name */
        public static /* bridge */ /* synthetic */ CameraFacing m22206$$Nest$fgetinitialFacing(Builder r0) {
            return r0.initialFacing;
        }

        /* renamed from: -$$Nest$fgetlistener, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Listener m22207$$Nest$fgetlistener(Builder r0) {
            return r0.listener;
        }

        /* renamed from: -$$Nest$fgetmaximumDurationMs, reason: not valid java name */
        public static /* bridge */ /* synthetic */ long m22208$$Nest$fgetmaximumDurationMs(Builder r2) {
            return r2.maximumDurationMs;
        }

        /* renamed from: -$$Nest$fgetoutputDirectory, reason: not valid java name */
        public static /* bridge */ /* synthetic */ File m22209$$Nest$fgetoutputDirectory(Builder r0) {
            return r0.outputDirectory;
        }

        /* renamed from: -$$Nest$fgetoutputListener, reason: not valid java name */
        public static /* bridge */ /* synthetic */ OutputListener m22210$$Nest$fgetoutputListener(Builder r0) {
            return r0.outputListener;
        }

        /* renamed from: -$$Nest$fgetoutputResolution, reason: not valid java name */
        public static /* bridge */ /* synthetic */ OutputResolution m22211$$Nest$fgetoutputResolution(Builder r0) {
            return r0.outputResolution;
        }

        /* renamed from: -$$Nest$fgetpreviewView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ TextureView m22212$$Nest$fgetpreviewView(Builder r0) {
            return r0.previewView;
        }

        /* renamed from: -$$Nest$fgetscreenFlashController, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ScreenFlashController m22213$$Nest$fgetscreenFlashController(Builder r0) {
            return r0.screenFlashController;
        }

        /* renamed from: -$$Nest$fgetvideoBitrate, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m22214$$Nest$fgetvideoBitrate(Builder r0) {
            return r0.videoBitrate;
        }

        public Builder(Context r3, TextureView r4) {
            this.maximumDurationMs = 60000;
            this.compositionEnabled = true;
            this.context = r3;
            this.previewView = r4;
        }

        public RoundVideoSession build() {
            if (this.initialFacing != null) goto L5;
            Segment$$ExternalSyntheticBUOutline1.m("Initial camera is required");
            return null;
        L5:
            if (this.outputResolution != null) goto L7;
            Segment$$ExternalSyntheticBUOutline1.m("Output resolution is required");
            return null;
        L7:
            if (this.videoBitrate > 0) goto L9;
            Segment$$ExternalSyntheticBUOutline1.m("Video bitrate is required");
            return null;
        L9:
            if (this.cameraResolution != null) goto L11;
            Segment$$ExternalSyntheticBUOutline1.m("Camera resolution is required");
            return null;
        L11:
            if (this.frameRate != null) goto L13;
            Segment$$ExternalSyntheticBUOutline1.m("Frame rate is required");
            return null;
        L13:
            if (this.maximumDurationMs > 0) goto L15;
            Segment$$ExternalSyntheticBUOutline1.m("Invalid duration limit");
            return null;
        L15:
            if (this.listener != null) goto L17;
            Segment$$ExternalSyntheticBUOutline1.m("Session listener is required");
            return null;
        L17:
            if (this.outputListener != null) goto L19;
            Segment$$ExternalSyntheticBUOutline1.m("Output listener is required");
            return null;
        L19:
            return new RoundVideoSession(this, null);
        }

        public Builder setCameraResolution(CameraResolution r1) {
            this.cameraResolution = r1;
            return this;
        }

        public Builder setCompositionEnabled(boolean r1) {
            this.compositionEnabled = r1;
            return this;
        }

        public Builder setFrameRate(FrameRate r1) {
            this.frameRate = r1;
            return this;
        }

        public Builder setInitialFacing(CameraFacing r1) {
            this.initialFacing = r1;
            return this;
        }

        public Builder setListener(Listener r1) {
            this.listener = r1;
            return this;
        }

        public Builder setOutputDirectory(File r1) {
            this.outputDirectory = r1;
            return this;
        }

        public Builder setOutputListener(OutputListener r1) {
            this.outputListener = r1;
            return this;
        }

        public Builder setOutputResolution(OutputResolution r1) {
            this.outputResolution = r1;
            return this;
        }

        public Builder setScreenFlashController(ScreenFlashController r1) {
            this.screenFlashController = r1;
            return this;
        }

        public Builder setVideoBitrate(int r1) {
            this.videoBitrate = r1;
            return this;
        }
    }

    public static final class CameraCapabilities {
        private final CameraFacing activeFacing;
        private final FrameRate activeFrameRate;
        private final CameraResolution activeResolution;
        private final FlashType flashType;
        private final float maximumZoomRatio;
        private final CameraFacing requestedFacing;
        private final FrameRate requestedFrameRate;
        private final CameraResolution requestedResolution;

        private CameraCapabilities(CameraFacing r1, CameraFacing r2, CameraResolution r3, CameraResolution r4, FrameRate r5, FrameRate r6, FlashType r7, float r8) {
            this.requestedFacing = r1;
            this.activeFacing = r2;
            this.requestedResolution = r3;
            this.activeResolution = r4;
            this.requestedFrameRate = r5;
            this.activeFrameRate = r6;
            this.flashType = r7;
            this.maximumZoomRatio = r8;
        }

        public /* synthetic */ CameraCapabilities(CameraFacing r1, CameraFacing r2, CameraResolution r3, CameraResolution r4, FrameRate r5, FrameRate r6, FlashType r7, float r8, RoundVideoSessionIA r9) {
            this(r1, r2, r3, r4, r5, r6, r7, r8);
        }

        public CameraFacing getActiveFacing() {
            return this.activeFacing;
        }

        public FlashType getFlashType() {
            return this.flashType;
        }

        public float getMaximumZoomRatio() {
            return this.maximumZoomRatio;
        }

        public CameraFacing getRequestedFacing() {
            return this.requestedFacing;
        }
    }

    public enum CameraFacing extends Enum<CameraFacing> {
        private static final /* synthetic */ CameraFacing[] $VALUES = null;
        public static final CameraFacing BACK = null;
        public static final CameraFacing FRONT = null;

        private static /* synthetic */ CameraFacing[] $values() {
            return new CameraFacing[]{FRONT, BACK};
        }

        static {
            FRONT = new CameraFacing("FRONT", 0);
            BACK = new CameraFacing("BACK", 1);
            $VALUES = $values();
        }

        CameraFacing(String r1, int r2) {
        }

        public static CameraFacing valueOf(String r1) {
            return (CameraFacing) Enum.valueOf(CameraFacing.class, r1);
        }

        public static CameraFacing[] values() {
            return (CameraFacing[]) $VALUES.clone();
        }
    }

    public enum CameraResolution extends Enum<CameraResolution> {
        private static final /* synthetic */ CameraResolution[] $VALUES = null;
        public static final CameraResolution HIGH = null;
        public static final CameraResolution LOW = null;
        public static final CameraResolution MEDIUM = null;

        private static /* synthetic */ CameraResolution[] $values() {
            return new CameraResolution[]{HIGH, MEDIUM, LOW};
        }

        static {
            HIGH = new CameraResolution("HIGH", 0);
            MEDIUM = new CameraResolution("MEDIUM", 1);
            LOW = new CameraResolution("LOW", 2);
            $VALUES = $values();
        }

        CameraResolution(String r1, int r2) {
        }

        public static CameraResolution valueOf(String r1) {
            return (CameraResolution) Enum.valueOf(CameraResolution.class, r1);
        }

        public static CameraResolution[] values() {
            return (CameraResolution[]) $VALUES.clone();
        }
    }

    public enum FlashType extends Enum<FlashType> {
        private static final /* synthetic */ FlashType[] $VALUES = null;
        public static final FlashType NONE = null;
        public static final FlashType SCREEN = null;
        public static final FlashType TORCH = null;

        private static /* synthetic */ FlashType[] $values() {
            return new FlashType[]{NONE, TORCH, SCREEN};
        }

        static {
            NONE = new FlashType("NONE", 0);
            TORCH = new FlashType("TORCH", 1);
            SCREEN = new FlashType("SCREEN", 2);
            $VALUES = $values();
        }

        FlashType(String r1, int r2) {
        }

        public static FlashType valueOf(String r1) {
            return (FlashType) Enum.valueOf(FlashType.class, r1);
        }

        public static FlashType[] values() {
            return (FlashType[]) $VALUES.clone();
        }
    }

    public enum FrameRate extends Enum<FrameRate> {
        private static final /* synthetic */ FrameRate[] $VALUES = null;
        public static final FrameRate FPS_30 = null;
        public static final FrameRate FPS_60 = null;
        private final int value;

        private static /* synthetic */ FrameRate[] $values() {
            return new FrameRate[]{FPS_30, FPS_60};
        }

        static {
            FPS_30 = new FrameRate("FPS_30", 0, 30);
            FPS_60 = new FrameRate("FPS_60", 1, 60);
            $VALUES = $values();
        }

        FrameRate(String r1, int r2, int r3) {
            this.value = r3;
        }

        public static FrameRate valueOf(String r1) {
            return (FrameRate) Enum.valueOf(FrameRate.class, r1);
        }

        public static FrameRate[] values() {
            return (FrameRate[]) $VALUES.clone();
        }

        public int getValue() {
            return this.value;
        }
    }

    public interface Listener {
        void onCameraCapabilitiesChanged(CameraCapabilities r1);

        void onCameraSwitchCompleted(CameraFacing r1);

        void onCameraSwitchFirstFrame();

        void onCameraSwitchStarted(CameraFacing r1);

        void onCompleted(Result r1);

        void onError(Exception r1);

        void onPreviewFirstFrame();

        default void onPreviewPlaybackChanged(boolean r1) {
        }

        default void onPreviewProgress(long r1, long r3) {
        }

        void onPreviewReady(long r1, long r3, long r5);

        void onStateChanged(StateInfo r1);
    }

    public static final class OutputGeneration {
        volatile long availableSize;
        volatile boolean completed;
        final File file;
        final long id;
        volatile boolean invalidated;

        public OutputGeneration(long r1, File r3) {
            this.id = r1;
            this.file = r3;
        }
    }

    public enum OutputInvalidationReason extends Enum<OutputInvalidationReason> {
        private static final /* synthetic */ OutputInvalidationReason[] $VALUES = null;
        public static final OutputInvalidationReason AUDIO_REMOVED = null;
        public static final OutputInvalidationReason CANCELLED = null;
        public static final OutputInvalidationReason ERROR = null;
        public static final OutputInvalidationReason TRIMMED = null;

        private static /* synthetic */ OutputInvalidationReason[] $values() {
            return new OutputInvalidationReason[]{TRIMMED, AUDIO_REMOVED, CANCELLED, ERROR};
        }

        static {
            TRIMMED = new OutputInvalidationReason("TRIMMED", 0);
            AUDIO_REMOVED = new OutputInvalidationReason("AUDIO_REMOVED", 1);
            CANCELLED = new OutputInvalidationReason("CANCELLED", 2);
            ERROR = new OutputInvalidationReason(MediaError.ERROR_TYPE_ERROR, 3);
            $VALUES = $values();
        }

        OutputInvalidationReason(String r1, int r2) {
        }

        public static OutputInvalidationReason valueOf(String r1) {
            return (OutputInvalidationReason) Enum.valueOf(OutputInvalidationReason.class, r1);
        }

        public static OutputInvalidationReason[] values() {
            return (OutputInvalidationReason[]) $VALUES.clone();
        }
    }

    public interface OutputListener {
        void onBytesAvailable(long r1, File r3, long r4, long r6);

        void onOutputCompleted(long r1, File r3, long r4, long r6, boolean r8);

        void onOutputError(long r1, Exception r3);

        void onOutputInvalidated(long r1, OutputInvalidationReason r3);

        void onOutputStarted(long r1, File r3);
    }

    public enum OutputResolution extends Enum<OutputResolution> {
        private static final /* synthetic */ OutputResolution[] $VALUES = null;
        public static final OutputResolution P360 = null;
        public static final OutputResolution P480 = null;
        private final int size;

        private static /* synthetic */ OutputResolution[] $values() {
            return new OutputResolution[]{P480, P360};
        }

        static {
            P480 = new OutputResolution("P480", 0, 480);
            P360 = new OutputResolution("P360", 1, 360);
            $VALUES = $values();
        }

        OutputResolution(String r1, int r2, int r3) {
            this.size = r3;
        }

        public static OutputResolution valueOf(String r1) {
            return (OutputResolution) Enum.valueOf(OutputResolution.class, r1);
        }

        public static OutputResolution[] values() {
            return (OutputResolution[]) $VALUES.clone();
        }

        public int getSize() {
            return this.size;
        }
    }

    public static final class Result {
        private final long durationMs;
        private final File file;
        private final FrameRate frameRate;
        private final boolean hasAudio;
        private final long outputId;

        private Result(long r1, File r3, long r4, boolean r6, FrameRate r7) {
            this.outputId = r1;
            this.file = r3;
            this.durationMs = r4;
            this.hasAudio = r6;
            this.frameRate = r7;
        }

        public /* synthetic */ Result(long r1, File r3, long r4, boolean r6, FrameRate r7, RoundVideoSessionIA r8) {
            this(r1, r3, r4, r6, r7);
        }

        public long getDurationMs() {
            return this.durationMs;
        }

        public File getFile() {
            return this.file;
        }

        public long getOutputId() {
            return this.outputId;
        }

        public boolean hasAudio() {
            return this.hasAudio;
        }
    }

    public interface ScreenFlashController {
        void setScreenFlashEnabled(boolean r1);
    }

    public enum State extends Enum<State> {
        private static final /* synthetic */ State[] $VALUES = null;
        public static final State COMPLETED = null;
        public static final State ERROR = null;
        public static final State FINISHING = null;
        public static final State IDLE = null;
        public static final State PAUSING = null;
        public static final State PREVIEWING = null;
        public static final State RECORDING = null;
        public static final State RELEASED = null;
        public static final State RESUMING = null;
        public static final State STARTING = null;

        private static /* synthetic */ State[] $values() {
            return new State[]{IDLE, STARTING, RECORDING, PAUSING, PREVIEWING, RESUMING, FINISHING, COMPLETED, ERROR, RELEASED};
        }

        static {
            IDLE = new State("IDLE", 0);
            STARTING = new State("STARTING", 1);
            RECORDING = new State("RECORDING", 2);
            PAUSING = new State("PAUSING", 3);
            PREVIEWING = new State("PREVIEWING", 4);
            RESUMING = new State("RESUMING", 5);
            FINISHING = new State("FINISHING", 6);
            COMPLETED = new State("COMPLETED", 7);
            ERROR = new State(MediaError.ERROR_TYPE_ERROR, 8);
            RELEASED = new State("RELEASED", 9);
            $VALUES = $values();
        }

        State(String r1, int r2) {
        }

        public static State valueOf(String r1) {
            return (State) Enum.valueOf(State.class, r1);
        }

        public static State[] values() {
            return (State[]) $VALUES.clone();
        }
    }

    public static final class StateInfo {
        private final boolean cameraSwitching;
        private final boolean flashEnabled;
        private final long maximumDurationMs;
        private final boolean previewPlaying;
        private final long recordedDurationMs;
        private final long recordingStartedAtRealtimeMs;
        private final State state;
        private final long trimEndMs;
        private final long trimStartMs;
        private final float zoomProgress;

        private StateInfo(State r1, long r2, long r4, long r6, long r8, long r10, boolean r12, boolean r13, boolean r14, float r15) {
            this.state = r1;
            this.recordedDurationMs = r2;
            this.recordingStartedAtRealtimeMs = r4;
            this.maximumDurationMs = r6;
            this.trimStartMs = r8;
            this.trimEndMs = r10;
            this.previewPlaying = r12;
            this.cameraSwitching = r13;
            this.flashEnabled = r14;
            this.zoomProgress = r15;
        }

        public /* synthetic */ StateInfo(State r1, long r2, long r4, long r6, long r8, long r10, boolean r12, boolean r13, boolean r14, float r15, RoundVideoSessionIA r16) {
            this(r1, r2, r4, r6, r8, r10, r12, r13, r14, r15);
        }

        public boolean canControlCamera() {
            if (this.state == State.RECORDING) goto L5;
            return false;
        L5:
            if (this.cameraSwitching == true) goto L10;
            return true;
        L10:
            return false;
        }

        public long getMaximumDurationMs() {
            return this.maximumDurationMs;
        }

        public long getRecordedDurationMs() {
            return this.recordedDurationMs;
        }

        public long getRecordingStartedAtRealtimeMs() {
            return this.recordingStartedAtRealtimeMs;
        }

        public State getState() {
            return this.state;
        }

        public boolean isFlashEnabled() {
            return this.flashEnabled;
        }
    }

    /* renamed from: $r8$lambda$-4zJsc9a0ObivEm5TA2R2OP6rRE, reason: not valid java name */
    public static /* synthetic */ Thread m22173$r8$lambda$4zJsc9a0ObivEm5TA2R2OP6rRE(Runnable r2) {
        return new Thread(r2, "RoundVideoOutput");
    }

    /* renamed from: $r8$lambda$-G0cSHt5vb9YdKr_eUKl-83lUD0, reason: not valid java name */
    public static /* synthetic */ void m22174$r8$lambda$G0cSHt5vb9YdKr_eUKl83lUD0(RoundVideoSession r0, long r1) {
        r0.lambda$createPreviewSnapshot$3(r1);
    }

    /* renamed from: $r8$lambda$2rHjWRgndDPIRM1ZaoCctkv2D-E, reason: not valid java name */
    public static /* synthetic */ void m22175$r8$lambda$2rHjWRgndDPIRM1ZaoCctkv2DE(RoundVideoSession r0, OutputGeneration r1, OutputInvalidationReason r2) {
        r0.lambda$cancelOutput$21(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$7DGL3MRituUx_WYDgIfA18LWgPQ(RoundVideoSession r0, long r1) {
        r0.lambda$rebuildForResume$6(r1);
    }

    /* renamed from: $r8$lambda$9Mniw4QCjZDo-vGcr9mPESmBNXE, reason: not valid java name */
    public static /* synthetic */ void m22176$r8$lambda$9Mniw4QCjZDovGcr9mPESmBNXE(RoundVideoSession r0, OutputGeneration r1, long r2, File r4, boolean r5) {
        r0.lambda$completeOutput$13(r1, r2, r4, r5);
    }

    /* renamed from: $r8$lambda$Ep_927YGU-cbXTPS7IDyOO_sLgs, reason: not valid java name */
    public static /* synthetic */ void m22177$r8$lambda$Ep_927YGUcbXTPS7IDyOO_sLgs(RoundVideoSession r0, Exception r1) {
        r0.lambda$rebuildForResume$7(r1);
    }

    public static /* synthetic */ void $r8$lambda$FUST4X2EdqFdZmDiSwtm2IOY8YI(RoundVideoSession r0, RoundVideoMp4Writer r1, boolean r2, File r3, boolean r4, OutputInvalidationReason r5, OutputGeneration r6) {
        r0.lambda$finishFromPreview$12(r1, r2, r3, r4, r5, r6);
    }

    /* renamed from: $r8$lambda$OmyhblPyxezbuJ5LN-I2MSa3Gu8, reason: not valid java name */
    public static /* synthetic */ void m22178$r8$lambda$OmyhblPyxezbuJ5LNI2MSa3Gu8(RoundVideoSession r0, OutputGeneration r1, long r2, long r4) {
        r0.lambda$createOutputGenerationOnFileThread$15(r1, r2, r4);
    }

    public static /* synthetic */ void $r8$lambda$Pw2yy79EWxz3r9JycAYsvfaXvyI(RoundVideoSession r0, RoundVideoMp4Writer r1, boolean r2, OutputGeneration r3) {
        r0.lambda$finishActiveRecording$10(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$Q3S91HDJXqVYU2sJYvvD9Dq6H3E(RoundVideoSession r0, Exception r1) {
        r0.lambda$finishActiveRecording$9(r1);
    }

    /* renamed from: $r8$lambda$RAyFSHN-Gwx35He8gSwYqTvaN9A, reason: not valid java name */
    public static /* synthetic */ void m22179$r8$lambda$RAyFSHNGwx35He8gSwYqTvaN9A(RoundVideoSession r0, Exception r1) {
        r0.lambda$finishFromPreview$11(r1);
    }

    public static /* synthetic */ void $r8$lambda$T3XtlSYKN8vIdwjAGCbxys5LaKQ(RoundVideoSession r0, RoundVideoMp4Writer r1, File r2) {
        r0.lambda$createPreviewSnapshot$5(r1, r2);
    }

    /* renamed from: $r8$lambda$VL-TMtiUqwlCi_8GNmUtrXxO8EM, reason: not valid java name */
    public static /* synthetic */ void m22180$r8$lambda$VLTMtiUqwlCi_8GNmUtrXxO8EM(RoundVideoSession r0, OutputGeneration r1, File r2) {
        r0.lambda$createOutputGenerationOnFileThread$17(r1, r2);
    }

    /* renamed from: $r8$lambda$XCDForUIE35_Ic-4IVQureFncQo, reason: not valid java name */
    public static /* synthetic */ Thread m22181$r8$lambda$XCDForUIE35_Ic4IVQureFncQo(Runnable r2) {
        return new Thread(r2, "RoundVideoFiles");
    }

    public static /* synthetic */ void $r8$lambda$bBhtwnBWZ8OKT45xvGCcE_bOFF8(RoundVideoSession r0, OutputGeneration r1, File r2, long r3, boolean r5) {
        r0.lambda$completeOutput$14(r1, r2, r3, r5);
    }

    public static /* synthetic */ void $r8$lambda$d3OubgcYQYKA7K7kDr5g3dY8rKA(RoundVideoSession r0, OutputGeneration r1, OutputInvalidationReason r2) {
        r0.lambda$invalidateOutput$18(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$p6piDDaiFe9Onkck4iaM_SebefE(RoundVideoSession r0) {
        r0.lambda$deleteMediaFiles$20();
    }

    public static /* synthetic */ void $r8$lambda$uQoRsaj34mIlUcf7GdAtdW3Wr1s(RoundVideoSession r0) {
        r0.lambda$new$2();
    }

    public static /* synthetic */ void $r8$lambda$ueyj_D1RCRJeuEocRULWX4c_rvs(RoundVideoSession r0, Exception r1) {
        r0.lambda$createPreviewSnapshot$4(r1);
    }

    public static /* synthetic */ void $r8$lambda$vrNmUXteg7JyaSrkCdzQyChNBIg(RoundVideoSession r0, OutputGeneration r1, Exception r2) {
        r0.lambda$fail$19(r1, r2);
    }

    /* renamed from: $r8$lambda$wwoBa-FBoOXS-IzBqMsXt5hoPsc, reason: not valid java name */
    public static /* synthetic */ void m22182$r8$lambda$wwoBaFBoOXSIzBqMsXt5hoPsc(RoundVideoSession r0, OutputGeneration r1, long r2) {
        r0.lambda$createOutputGenerationOnFileThread$16(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$xCjTDFhOAvgAgt__DxKVwDx15As(RoundVideoSession r0, boolean r1, RoundVideoMp4Writer r2, OutputGeneration r3, File r4) {
        r0.lambda$rebuildForResume$8(r1, r2, r3, r4);
    }

    /* renamed from: -$$Nest$fgetcameraSwitchCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m22183$$Nest$fgetcameraSwitchCount(RoundVideoSession r0) {
        return r0.cameraSwitchCount;
    }

    /* renamed from: -$$Nest$fgetdiagnostics, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RoundVideoDiagnostics m22184$$Nest$fgetdiagnostics(RoundVideoSession r0) {
        return r0.diagnostics;
    }

    /* renamed from: -$$Nest$fgetlistener, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Listener m22185$$Nest$fgetlistener(RoundVideoSession r0) {
        return r0.listener;
    }

    /* renamed from: -$$Nest$fgetmainHandler, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Handler m22186$$Nest$fgetmainHandler(RoundVideoSession r0) {
        return r0.mainHandler;
    }

    /* renamed from: -$$Nest$fgetplayer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ExoPlayer m22187$$Nest$fgetplayer(RoundVideoSession r0) {
        return r0.player;
    }

    /* renamed from: -$$Nest$fgetpreviewPlaying, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m22188$$Nest$fgetpreviewPlaying(RoundVideoSession r0) {
        return r0.previewPlaying;
    }

    /* renamed from: -$$Nest$fgetrecordedDurationMs, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m22189$$Nest$fgetrecordedDurationMs(RoundVideoSession r2) {
        return r2.recordedDurationMs;
    }

    /* renamed from: -$$Nest$fgetstate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ State m22190$$Nest$fgetstate(RoundVideoSession r0) {
        return r0.state;
    }

    /* renamed from: -$$Nest$fgettrimEndMs, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m22191$$Nest$fgettrimEndMs(RoundVideoSession r2) {
        return r2.trimEndMs;
    }

    /* renamed from: -$$Nest$fgettrimStartMs, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m22192$$Nest$fgettrimStartMs(RoundVideoSession r2) {
        return r2.trimStartMs;
    }

    /* renamed from: -$$Nest$fputcameraSwitchCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22193$$Nest$fputcameraSwitchCount(RoundVideoSession r0, int r1) {
        r0.cameraSwitchCount = r1;
    }

    /* renamed from: -$$Nest$fputcameraSwitching, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22194$$Nest$fputcameraSwitching(RoundVideoSession r0, boolean r1) {
        r0.cameraSwitching = r1;
    }

    /* renamed from: -$$Nest$mfail, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22195$$Nest$mfail(RoundVideoSession r0, Exception r1) {
        r0.fail(r1);
    }

    /* renamed from: -$$Nest$mhandleCameraConfigured, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22196$$Nest$mhandleCameraConfigured(RoundVideoSession r0, RoundVideoCameraController.CameraInfo r1, boolean r2) {
        r0.handleCameraConfigured(r1, r2);
    }

    /* renamed from: -$$Nest$mhandleRecordingStarted, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22197$$Nest$mhandleRecordingStarted(RoundVideoSession r0) {
        r0.handleRecordingStarted();
    }

    /* renamed from: -$$Nest$mhandleRecordingStopped, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22198$$Nest$mhandleRecordingStopped(RoundVideoSession r0) {
        r0.handleRecordingStopped();
    }

    /* renamed from: -$$Nest$mnotifyState, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22199$$Nest$mnotifyState(RoundVideoSession r0) {
        r0.notifyState();
    }

    /* renamed from: -$$Nest$mupdatePreviewPlaying, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22200$$Nest$mupdatePreviewPlaying(RoundVideoSession r0, boolean r1) {
        r0.updatePreviewPlaying(r1);
    }

    private RoundVideoSession(Builder r13) {
        this.outputLock = new Object();
        this.identityPreviewTransform = new Matrix();
        this.mainHandler = new Handler(Looper.getMainLooper());
        this.fileExecutor = Executors.newSingleThreadExecutor(new RoundVideoSession$$ExternalSyntheticLambda10());
        this.outputExecutor = Executors.newSingleThreadExecutor(new RoundVideoSession$$ExternalSyntheticLambda11());
        this.state = State.IDLE;
        this.flashType = FlashType.NONE;
        this.maximumZoomRatio = 1.0f;
        this.nextOutputId = 1;
        this.durationLimitRunnable = new RoundVideoSession$$ExternalSyntheticLambda12(this);
        this.previewProgressRunnable = new AnonymousClass1(this);
        AnonymousClass2 r10 = new AnonymousClass2(this);
        this.cameraCallback = r10;
        this.playerListener = new AnonymousClass3(this);
        requireMainThread();
        Context r0 = Builder.m22204$$Nest$fgetcontext(r13);
        Context r1 = r0.getApplicationContext();
        if (r1 != null) goto L5;
        Context r2 = r0;
    L6:
        this.context = r2;
        TextureView r3 = Builder.m22212$$Nest$fgetpreviewView(r13);
        this.previewView = r3;
        if (Builder.m22209$$Nest$fgetoutputDirectory(r13) != null) goto L9;
        File r02 = r2.getCacheDir();
    L10:
        this.outputDirectory = r02;
        this.requestedFacing = Builder.m22206$$Nest$fgetinitialFacing(r13);
        OutputResolution r4 = Builder.m22211$$Nest$fgetoutputResolution(r13);
        this.outputResolution = r4;
        int r5 = Builder.m22214$$Nest$fgetvideoBitrate(r13);
        this.videoBitrate = r5;
        CameraResolution r6 = Builder.m22202$$Nest$fgetcameraResolution(r13);
        this.requestedCameraResolution = r6;
        FrameRate r7 = Builder.m22205$$Nest$fgetframeRate(r13);
        this.requestedFrameRate = r7;
        long r03 = Builder.m22208$$Nest$fgetmaximumDurationMs(r13);
        this.maximumDurationMs = r03;
        boolean r8 = Builder.m22203$$Nest$fgetcompositionEnabled(r13);
        this.compositionEnabled = r8;
        this.listener = Builder.m22207$$Nest$fgetlistener(r13);
        this.outputListener = Builder.m22210$$Nest$fgetoutputListener(r13);
        this.screenFlashController = Builder.m22213$$Nest$fgetscreenFlashController(r13);
        RoundVideoDiagnostics r9 = new RoundVideoDiagnostics();
        this.diagnostics = r9;
        r9.log("session created: device=" + Build.MANUFACTURER + " " + Build.MODEL + ", sdk=" + Build.VERSION.SDK_INT + ", output=" + r4.getSize() + "x" + r4.getSize() + ", bitrate=" + r5 + ", cameraMode=" + r6 + ", fps=" + r7.getValue() + ", composition=" + r8 + ", facing=" + this.requestedFacing + ", maxDurationMs=" + r03);
        this.cameraController = new RoundVideoCameraController(r2, r3, r4, r5, r6, r7, r8, r9, r10);
        return;
    L9:
        r02 = Builder.m22209$$Nest$fgetoutputDirectory(r13);
        goto L10
    L5:
        r2 = r1;
        goto L6
    }

    public /* synthetic */ RoundVideoSession(Builder r1, RoundVideoSessionIA r2) {
        this(r1);
    }

    private static Exception asException(Exception r0) {
        return r0;
    }

    private boolean cancelOutput(OutputInvalidationReason r9) {
        Object r1 = this.outputLock;
        monitor-enter(r1);
        OutputGeneration r2 = this.outputGeneration;     // Catch: Throwable -> L11
        if (r2 != null) goto L7;
    L13:
        this.cancelRequested = true;     // Catch: Throwable -> L11
        if (r2 != null) goto L16;
    L18:
        monitor-exit(r1);     // Catch: Throwable -> L11
        return true;
    L16:
        if (r2.invalidated == true) goto L18;
        r2.invalidated = true;     // Catch: Throwable -> L11
        this.diagnostics.log("output generation invalidated: id=" + r2.id + ", reason=" + r9 + ", availableSize=" + r2.availableSize);     // Catch: Throwable -> L11
        this.outputExecutor.execute(new RoundVideoSession$$ExternalSyntheticLambda6(this, r2, r9));     // Catch: Throwable -> L11
        goto L18
    L7:
        if (r2.completed == false) goto L13;
        monitor-exit(r1);     // Catch: Throwable -> L11
        return false;
    L11:
        th = move-exception;
        throw th;
    }

    private static float clamp(float r0, float r1, float r2) {
        return Math.max(r1, Math.min(r2, r0));
    }

    private static long clamp(long r0, long r2, long r4) {
        return Math.max(r2, Math.min(r4, r0));
    }

    private void completeOutput(OutputGeneration r9, File r10, long r11, boolean r13) {
        this.outputExecutor.execute(new RoundVideoSession$$ExternalSyntheticLambda20(this, r9, r10, r11, r13));
    }

    private void createOutputGeneration(boolean r1) {
        createOutputGenerationOnFileThread(r1);
    }

    private void createOutputGenerationOnFileThread(boolean r10) {
        Object r1 = this.outputLock;
        monitor-enter(r1);
        ensureNotCancelled();     // Catch: Throwable -> L7
        File r4 = createTemporaryFile("round_video_");     // Catch: Throwable -> L7
        long r5 = this.nextOutputId;     // Catch: Throwable -> L7
        this.nextOutputId = 1 + r5;     // Catch: Throwable -> L7
        OutputGeneration r2 = new OutputGeneration(r5, r4);     // Catch: Throwable -> L7
        this.outputGeneration = r2;     // Catch: Throwable -> L7
        this.writer = new RoundVideoMp4Writer(r4, this.outputResolution.getSize(), r10, this.diagnostics, new RoundVideoSession$$ExternalSyntheticLambda13(this, r2));     // Catch: Throwable -> L7
        this.diagnostics.log("output generation started: id=" + r2.id + ", includeAudio=" + r10 + ", file=" + r4.getName());     // Catch: Throwable -> L7
        this.outputExecutor.execute(new RoundVideoSession$$ExternalSyntheticLambda14(this, r2, r4));     // Catch: Throwable -> L7
        monitor-exit(r1);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    private void createPreviewSnapshot() {
        File r0 = createTemporaryFile("round_video_preview_");     // Catch: IOException -> L5
        this.previewFile = r0;
        this.previewSnapshotStartedNs = System.nanoTime();
        this.diagnostics.log("preview snapshot started: file=" + r0.getName());
        this.fileExecutor.execute(new RoundVideoSession$$ExternalSyntheticLambda0(this, this.writer, r0));
        return;
    L5:
        e = move-exception;
        fail(e);
    }

    private StateInfo createStateInfo() {
        return new StateInfo(this.state, this.recordedDurationMs, this.recordingStartedAtRealtimeMs, this.maximumDurationMs, this.trimStartMs, this.trimEndMs, this.previewPlaying, this.cameraSwitching, this.flashEnabled, this.zoomProgress, null);
    }

    private File createTemporaryFile(String r3) {
        boolean r0 = this.outputDirectory.exists();
        File r1 = this.outputDirectory;
        if (r0 == false) goto L10;
        if (r1.isDirectory() == true) goto L17;
        ZipFilesKt$$ExternalSyntheticBUOutline1.m("Round-video output path is not a directory: ", this.outputDirectory);
        return null;
    L17:
        return File.createTempFile(r3, ".mp4", this.outputDirectory);
    L10:
        if (r1.mkdirs() == true) goto L17;
        if (this.outputDirectory.isDirectory() == true) goto L17;
        ZipFilesKt$$ExternalSyntheticBUOutline1.m("Cannot create round-video output directory: ", this.outputDirectory);
        return null;
    }

    private void deleteMediaFiles() {
        this.fileExecutor.execute(new RoundVideoSession$$ExternalSyntheticLambda5(this));
    }

    private void disableCameraEffects() {
        this.zoomProgress = 0.0f;
        this.flashEnabled = false;
        this.cameraController.setZoom(0.0f);
        this.cameraController.setTorchEnabled(false);
        setScreenFlashEnabled(false);
    }

    private static long elapsedMs(long r2) {
        return (System.nanoTime() - r2) / 1000000;
    }

    private void ensureNotCancelled() {
        if (this.cancelRequested == true) goto L5;
        return;
    L5:
        Model$$ExternalSyntheticBUOutline0.m("Round-video operation was cancelled");
    }

    private void enterPreview(long r8) {
        if (this.state == State.PAUSING) goto L5;
        return;
    L5:
        this.previewSourceDurationMs = r8;
        long r82 = Math.min(this.maximumDurationMs, r8);
        this.recordedDurationMs = r82;
        this.trimStartMs = 0;
        this.trimEndMs = r82;
        this.previewView.setSurfaceTextureListener(null);
        this.previewView.setTransform(this.identityPreviewTransform);
        ExoPlayer r83 = new ExoPlayer.Builder(this.context).build();
        this.player = r83;
        r83.setVideoTextureView(this.previewView);
        this.player.setMediaItem(MediaItem.fromUri(Uri.fromFile(this.previewFile)));
        this.player.setRepeatMode(0);
        this.player.setVolume(1.0f);
        this.player.addListener(this.playerListener);
        this.player.prepare();
        this.player.seekTo(this.trimStartMs);
        this.diagnostics.log("preview player prepared: durationMs=" + this.recordedDurationMs + ", trim=" + this.trimStartMs + ".." + this.trimEndMs);
        setState(State.PREVIEWING);
        this.listener.onPreviewReady(this.recordedDurationMs, this.trimStartMs, this.trimEndMs);
        playPreview();
    }

    private void fail(Exception r5) {
        State r0 = this.state;
        State r1 = State.ERROR;
        if (r0 != r1) goto L5;
        return;
    L5:
        if (r0 == State.RELEASED) goto L23;
        this.diagnostics.error("fatal error in state=" + this.state, r5);
        if (cancelOutput(OutputInvalidationReason.ERROR) == false) goto L24;
        disableCameraEffects();
        releasePlayer();
        this.mainHandler.removeCallbacks(this.durationLimitRunnable);
        OutputGeneration r02 = this.outputGeneration;
        if (r02 == null) goto L13;
        this.outputExecutor.execute(new RoundVideoSession$$ExternalSyntheticLambda7(this, r02, r5));
    L13:
        setState(r1);
        logSummary("error");
        this.listener.onError(r5);
        if (this.cameraStopPending == false) goto L16;
    L20:
        this.cameraStopPending = true;
        this.cancelAfterCameraStops = true;
        return;
    L16:
        if (this.cameraController.stopSegment() == true) goto L20;
        finishCancellation();
        return;
    L24:
        return;
    }

    private void finishActiveRecording(boolean r5) {
        RoundVideoMp4Writer r0 = this.writer;
        OutputGeneration r1 = this.outputGeneration;
        this.fileExecutor.execute(new RoundVideoSession$$ExternalSyntheticLambda1(this, r0, r5, r1));
    }

    private void finishCancellation() {
        if (this.cleanupStarted == false) goto L5;
        return;
    L5:
        this.cleanupStarted = true;
        logSummary("cancelled");
        deleteMediaFiles();
        this.cameraController.release();
        shutdownExecutors();
    }

    private void finishFromPreview(boolean r10) {
        if (isTrimChanged() == true) goto L8;
        if (r10 == false) goto L8;
        boolean r0 = false;
    L7:
        boolean r4 = r0;
        if (isTrimChanged() == false) goto L13;
        OutputInvalidationReason r02 = OutputInvalidationReason.TRIMMED;
    L12:
        OutputInvalidationReason r7 = r02;
        File r5 = this.previewFile;
        RoundVideoMp4Writer r3 = this.writer;
        OutputGeneration r8 = this.outputGeneration;
        this.fileExecutor.execute(new RoundVideoSession$$ExternalSyntheticLambda8(this, r3, r4, r5, r10, r7, r8));
        return;
    L13:
        r02 = OutputInvalidationReason.AUDIO_REMOVED;
    L8:
        r0 = true;
        goto L7
    }

    private void handleCameraConfigured(RoundVideoCameraController.CameraInfo r4, boolean r5) {
        if (isTerminalState() == true) goto L23;
        CameraFacing r0 = r4.facing;
        this.activeFacing = r0;
        this.activeCameraResolution = r4.cameraResolution;
        this.activeFrameRate = r4.frameRate;
        this.maximumZoomRatio = r4.maximumZoomRatio;
        if (r0 != CameraFacing.FRONT) goto L12;
        if (this.screenFlashController != null) goto L10;
        FlashType r02 = FlashType.NONE;
    L15:
        this.flashType = r02;
        notifyCapabilities();
        this.diagnostics.log("camera configured: facing=" + r4.facing + ", mode=" + r4.cameraResolution + ", preview=" + r4.previewSize + ", recording=" + r4.recordingSize + ", fps=" + r4.frameRate.getValue() + ", maxZoom=" + this.maximumZoomRatio + ", flash=" + this.flashType + ", switch=" + r5);
        if (r5 == false) goto L18;
    L21:
        this.cameraSwitching = false;
        this.listener.onCameraSwitchCompleted(r4.facing);
        notifyState();
        return;
    L18:
        if (this.cameraSwitching == true) goto L21;
        return;
    L10:
        r02 = FlashType.SCREEN;
        goto L15
    L12:
        if (r4.torchAvailable == false) goto L14;
        r02 = FlashType.TORCH;
        goto L15
    L14:
        r02 = FlashType.NONE;
        goto L15
    }

    private void handleRecordingStarted() {
        State r0 = this.state;
        if (r0 != State.STARTING) goto L5;
    L7:
        this.recordingStartedAtRealtimeMs = SystemClock.elapsedRealtime();
        this.diagnostics.log("recording started: retainedDurationMs=" + this.recordedDurationMs);
        setState(State.RECORDING);
        long r02 = this.maximumDurationMs - this.recordedDurationMs;
        if (r02 > 0) goto L11;
        pauseRecording();
        return;
    L11:
        this.mainHandler.postDelayed(this.durationLimitRunnable, r02);
        return;
    L5:
        if (r0 == State.RESUMING) goto L7;
    }

    private void handleRecordingStopped() {
        this.cameraStopPending = false;
        this.diagnostics.log("recording segment stopped: state=" + this.state + ", retainedDurationMs=" + this.recordedDurationMs);
        if (this.cancelAfterCameraStops == false) goto L7;
        this.cancelAfterCameraStops = false;
        finishCancellation();
        return;
    L7:
        if (this.finishAfterCameraStops == false) goto L11;
        this.finishAfterCameraStops = false;
        finishActiveRecording(this.finishWithAudio);
        return;
    L11:
        if (this.state == State.PAUSING) goto L13;
        return;
    L13:
        createPreviewSnapshot();
    }

    private void invalidateOutput(OutputGeneration r7, OutputInvalidationReason r8) {
        Object r1 = this.outputLock;
        monitor-enter(r1);
    L12:
        th = move-exception;
        throw th;
    L5:
        if (r7.invalidated == false) goto L7;
    L14:
        monitor-exit(r1);     // Catch: Throwable -> L12
        return;
    L7:
        if (r7.completed == true) goto L14;
        r7.invalidated = true;     // Catch: Throwable -> L12
        this.diagnostics.log("output generation invalidated: id=" + r7.id + ", reason=" + r8 + ", availableSize=" + r7.availableSize);     // Catch: Throwable -> L12
        this.outputExecutor.execute(new RoundVideoSession$$ExternalSyntheticLambda19(this, r7, r8));     // Catch: Throwable -> L12
        monitor-exit(r1);     // Catch: Throwable -> L12
    }

    private boolean isTerminalState() {
        State r1 = this.state;
        if (r1 != State.FINISHING) goto L5;
        return true;
    L5:
        if (r1 != State.COMPLETED) goto L7;
        return true;
    L7:
        if (r1 != State.ERROR) goto L9;
        return true;
    L9:
        if (r1 == State.RELEASED) goto L17;
        return false;
    L17:
        return true;
    }

    private boolean isTrimChanged() {
        long r0 = this.previewSourceDurationMs;
        if (r0 > 0) goto L7;
        r0 = this.recordedDurationMs;
    L7:
        if (r0 <= this.maximumDurationMs) goto L9;
        return true;
    L9:
        if (this.trimStartMs <= 0) goto L11;
        return true;
    L11:
        if ((this.trimEndMs + 10) < r0) goto L18;
        return false;
    L18:
        return true;
    }

    private /* synthetic */ void lambda$cancelOutput$21(OutputGeneration r3, OutputInvalidationReason r4) {
        this.outputListener.onOutputInvalidated(r3.id, r4);
    }

    private /* synthetic */ void lambda$completeOutput$13(OutputGeneration r11, long r12, File r14, boolean r15) {
        State r0 = this.state;
        if (r0 != State.RELEASED) goto L5;
        return;
    L5:
        if (r0 == State.ERROR) goto L13;
        setState(State.COMPLETED);
        this.diagnostics.log("output completed: generation=" + r11.id + ", durationMs=" + r12 + ", size=" + r14.length() + ", hasAudio=" + r15);
        logSummary("completed");
        Listener r02 = this.listener;
        long r2 = r11.id;
        FrameRate r10 = this.activeFrameRate;
        if (r10 != null) goto L10;
        r10 = FrameRate.FPS_30;
    L10:
        r02.onCompleted(new Result(r2, r14, r12, r15, r10, null));
        return;
    }

    private /* synthetic */ void lambda$completeOutput$14(OutputGeneration r14, File r15, long r16, boolean r18) {
        Object r3 = this.outputLock;
        monitor-enter(r3);
    L15:
        th = move-exception;
        throw th;
    L5:
        if (this.cancelRequested == false) goto L7;
    L17:
        monitor-exit(r3);     // Catch: Throwable -> L15
        return;
    L7:
        if (r14.invalidated == true) goto L17;
        if (r14.completed == true) goto L17;
        r14.completed = true;     // Catch: Throwable -> L15
        monitor-exit(r3);     // Catch: Throwable -> L15
        this.outputListener.onOutputCompleted(r14.id, r15, r15.length(), r16, r18);
        this.mainHandler.post(new RoundVideoSession$$ExternalSyntheticLambda21(this, r14, r16, r15, r18));
    }

    private /* synthetic */ void lambda$createOutputGenerationOnFileThread$15(OutputGeneration r11, long r12, long r14) {
        Object r1 = this.outputLock;
        monitor-enter(r1);
    L12:
        th = move-exception;
        throw th;
    L5:
        if (r11.invalidated == false) goto L7;
    L14:
        monitor-exit(r1);     // Catch: Throwable -> L12
        return;
    L7:
        if (r11.completed == true) goto L14;
        monitor-exit(r1);     // Catch: Throwable -> L12
        this.outputListener.onBytesAvailable(r11.id, r11.file, r12, r14);
    }

    private /* synthetic */ void lambda$createOutputGenerationOnFileThread$16(OutputGeneration r10, long r11) {
        Object r1 = this.outputLock;
        monitor-enter(r1);
        long r5 = r10.availableSize;     // Catch: Throwable -> L12
        long r7 = r11 - r5;     // Catch: Throwable -> L12
        if (r7 > 0) goto L7;
    L14:
        monitor-exit(r1);     // Catch: Throwable -> L12
        return;
    L7:
        if (r10.invalidated == true) goto L14;
        r10.availableSize = r11;     // Catch: Throwable -> L12
        this.outputExecutor.execute(new RoundVideoSession$$ExternalSyntheticLambda18(this, r10, r5, r7));     // Catch: Throwable -> L12
        monitor-exit(r1);     // Catch: Throwable -> L12
        return;
    L12:
        th = move-exception;
        throw th;
    }

    private /* synthetic */ void lambda$createOutputGenerationOnFileThread$17(OutputGeneration r3, File r4) {
        this.outputListener.onOutputStarted(r3.id, r4);
    }

    private /* synthetic */ void lambda$createPreviewSnapshot$3(long r1) {
        enterPreview(r1);
    }

    private /* synthetic */ void lambda$createPreviewSnapshot$4(Exception r1) {
        fail(asException(r1));
    }

    private /* synthetic */ void lambda$createPreviewSnapshot$5(RoundVideoMp4Writer r7, File r8) {
        r7.createPreview(r8);     // Catch: Exception -> L5
        ensureNotCancelled();     // Catch: Exception -> L5
        long r1 = RoundVideoRemuxer.getDurationMs(r8);     // Catch: Exception -> L5
        this.diagnostics.log("preview snapshot completed: durationMs=" + r1 + ", size=" + r8.length() + ", elapsedMs=" + elapsedMs(this.previewSnapshotStartedNs));     // Catch: Exception -> L5
        this.mainHandler.post(new RoundVideoSession$$ExternalSyntheticLambda3(this, r1));     // Catch: Exception -> L5
        return;
    L5:
        e = move-exception;
        this.mainHandler.post(new RoundVideoSession$$ExternalSyntheticLambda4(this, e));
    }

    private /* synthetic */ void lambda$deleteMediaFiles$20() {
        RoundVideoMp4Writer r0 = this.writer;
        File r1 = this.previewFile;
        if (r0 != null) goto L10;
    L6:
        if (r1 == null) goto L12;
        RoundVideoRemuxer.erase(r1);
        return;
    L12:
        return;
    L10:
        r0.closeForReplacement();     // Catch: IOException -> L9
    L5:
        RoundVideoRemuxer.erase(r0.getFile());
        goto L6
    }

    private /* synthetic */ void lambda$fail$19(OutputGeneration r3, Exception r4) {
        this.outputListener.onOutputError(r3.id, r4);
    }

    private /* synthetic */ void lambda$finishActiveRecording$10(RoundVideoMp4Writer r11, boolean r12, OutputGeneration r13) {
        long r2 = System.nanoTime();
        r11.finish();     // Catch: Exception -> L11
        long r4 = RoundVideoRemuxer.getDurationMs(r11.getFile());     // Catch: Exception -> L11
        this.diagnostics.log("active output finalized: size=" + r11.getFile().length() + ", durationMs=" + r4 + ", elapsedMs=" + elapsedMs(r2));     // Catch: Exception -> L11
        ensureNotCancelled();     // Catch: Exception -> L11
        if (r4 <= this.maximumDurationMs) goto L6;
        boolean r0 = true;
    L7:
        if (r12 == false) goto L13;
        if (r0 == true) goto L13;
        completeOutput(r13, r11.getFile(), r4, true);     // Catch: Exception -> L11
        return;
    L13:
        File r22 = r11.getFile();     // Catch: Exception -> L11
        long r5 = Math.min(this.maximumDurationMs, r4);     // Catch: Throwable -> L18
        if (r0 == false) goto L20;
        OutputInvalidationReason r02 = OutputInvalidationReason.TRIMMED;     // Catch: Throwable -> L18
    L21:
        replaceWithFinalRange(r22, 0, r5, r12, r02);     // Catch: Throwable -> L18
        RoundVideoRemuxer.erase(r22);     // Catch: Exception -> L11
        return;
    L20:
        r02 = OutputInvalidationReason.AUDIO_REMOVED;     // Catch: Throwable -> L18
    L18:
        th = move-exception;
        RoundVideoRemuxer.erase(r22);     // Catch: Exception -> L11
        throw th;     // Catch: Exception -> L11
    L6:
        r0 = false;
    L11:
        e = move-exception;
        this.mainHandler.post(new RoundVideoSession$$ExternalSyntheticLambda2(this, e));
    }

    private /* synthetic */ void lambda$finishActiveRecording$9(Exception r1) {
        fail(asException(r1));
    }

    private /* synthetic */ void lambda$finishFromPreview$11(Exception r1) {
        fail(asException(r1));
    }

    private /* synthetic */ void lambda$finishFromPreview$12(RoundVideoMp4Writer r10, boolean r11, File r12, boolean r13, OutputInvalidationReason r14, OutputGeneration r15) {
        long r2 = System.nanoTime();
        r10.finish();     // Catch: Throwable -> L6 Exception -> L8
        this.diagnostics.log("preview output finalized: size=" + r10.getFile().length() + ", replace=" + r11 + ", elapsedMs=" + elapsedMs(r2));     // Catch: Throwable -> L6 Exception -> L8
        ensureNotCancelled();     // Catch: Throwable -> L6 Exception -> L8
        if (r11 == false) goto L10;
        replaceWithFinalRange(r12, this.trimStartMs, this.trimEndMs, r13, r14);     // Catch: Throwable -> L6 Exception -> L8
    L15:
        this.previewFile = null;     // Catch: Throwable -> L6 Exception -> L8
        if (r12 == null) goto L18;
        RoundVideoRemuxer.erase(r12);
    L18:
        if (r11 == false) goto L35;
        RoundVideoRemuxer.erase(r10.getFile());
        return;
    L35:
        return;
    L10:
        File r3 = r10.getFile();     // Catch: Throwable -> L6 Exception -> L8
        long r4 = this.previewSourceDurationMs;     // Catch: Throwable -> L6 Exception -> L8
        if (r4 > 0) goto L14;
        r4 = this.recordedDurationMs;     // Catch: Throwable -> L6 Exception -> L8
    L14:
        completeOutput(r15, r3, r4, true);     // Catch: Throwable -> L6 Exception -> L8
    L8:
        e = move-exception;
        this.mainHandler.post(new RoundVideoSession$$ExternalSyntheticLambda15(this, e));     // Catch: Throwable -> L6
        if (r12 == null) goto L24;
        RoundVideoRemuxer.erase(r12);
    L24:
        if (r11 == false) goto L36;
        RoundVideoRemuxer.erase(r10.getFile());
        return;
    L36:
        return;
    L6:
        th = move-exception;
        if (r12 == null) goto L29;
        RoundVideoRemuxer.erase(r12);
    L29:
        if (r11 == false) goto L31;
        RoundVideoRemuxer.erase(r10.getFile());
    L31:
        throw th;
    }

    private /* synthetic */ void lambda$invalidateOutput$18(OutputGeneration r3, OutputInvalidationReason r4) {
        this.outputListener.onOutputInvalidated(r3.id, r4);
    }

    private /* synthetic */ void lambda$new$2() {
        if (this.state != State.RECORDING) goto L6;
        pauseRecording();
        return;
    }

    private /* synthetic */ void lambda$rebuildForResume$6(long r5) {
        if (this.state == State.RESUMING) goto L5;
        return;
    L5:
        this.recordedDurationMs = r5;
        this.previewSourceDurationMs = 0;
        this.trimStartMs = 0;
        this.trimEndMs = r5;
        setState(State.STARTING);
        this.cameraController.startSegment(this.writer, this.recordedDurationMs * 1000, this.requestedFacing);
    }

    private /* synthetic */ void lambda$rebuildForResume$7(Exception r1) {
        fail(asException(r1));
    }

    private /* synthetic */ void lambda$rebuildForResume$8(boolean r17, RoundVideoMp4Writer r18, OutputGeneration r19, File r20) {
        long r3 = System.nanoTime();
        File r5 = null;
        ensureNotCancelled();     // Catch: Throwable -> L14 Exception -> L16
        long r6 = this.recordedDurationMs;     // Catch: Throwable -> L14 Exception -> L16
        if (r17 == false) goto L18;
        r18.closeForReplacement();     // Catch: Throwable -> L14 Exception -> L16
        ensureNotCancelled();     // Catch: Throwable -> L14 Exception -> L16
        invalidateOutput(r19, OutputInvalidationReason.TRIMMED);     // Catch: Throwable -> L14 Exception -> L16
        File r62 = r18.getFile();     // Catch: Throwable -> L14 Exception -> L16
        createOutputGenerationOnFileThread(true);     // Catch: Throwable -> L10 Exception -> L12
        RoundVideoRemuxer.Result r7 = RoundVideoRemuxer.copyRange(r20, this.writer, this.trimStartMs, this.trimEndMs, true);     // Catch: Throwable -> L10 Exception -> L12
        long r9 = r7.durationMs;     // Catch: Throwable -> L10 Exception -> L12
        this.diagnostics.log("resume trim remux completed: requested=" + this.trimStartMs + ".." + this.trimEndMs + ", actualStartMs=" + r7.startMs + ", retainedDurationMs=" + r9 + ", outputSize=" + this.writer.getFile().length() + ", elapsedMs=" + elapsedMs(r3));     // Catch: Throwable -> L10 Exception -> L12
        File r2 = r62;
        r6 = r9;
    L46:
        ensureNotCancelled();     // Catch: Throwable -> L22 Exception -> L24
        this.previewFile = null;     // Catch: Throwable -> L22 Exception -> L24
        if (r17 == true) goto L26;
        this.diagnostics.log("resume prepared without remux: retainedDurationMs=" + r6 + ", elapsedMs=" + elapsedMs(r3));     // Catch: Throwable -> L22 Exception -> L24
    L26:
        this.mainHandler.post(new RoundVideoSession$$ExternalSyntheticLambda16(this, r6));     // Catch: Throwable -> L22 Exception -> L24
        if (r20 == null) goto L29;
        RoundVideoRemuxer.erase(r20);
    L29:
        if (r2 == null) goto L50;
        RoundVideoRemuxer.erase(r2);
        return;
    L50:
        return;
    L24:
        e = e;
        r5 = r2;
    L32:
        this.mainHandler.post(new RoundVideoSession$$ExternalSyntheticLambda17(this, e));     // Catch: Throwable -> L14
        if (r20 == null) goto L35;
        RoundVideoRemuxer.erase(r20);
    L35:
        if (r5 == null) goto L51;
        RoundVideoRemuxer.erase(r5);
        return;
    L51:
        return;
    L22:
        th = th;
        r5 = r2;
    L38:
        if (r20 == null) goto L40;
        RoundVideoRemuxer.erase(r20);
    L40:
        if (r5 == null) goto L42;
        RoundVideoRemuxer.erase(r5);
    L42:
        throw th;
    L12:
        e = e;
        r5 = r62;
    L10:
        th = th;
        r5 = r62;
        goto L38
    L18:
        r2 = null;
    L16:
        e = e;
    L14:
        th = th;
        goto L38
    }

    private void logSummary(String r6) {
        if (this.summaryLogged == false) goto L5;
        return;
    L5:
        this.summaryLogged = true;
        OutputGeneration r0 = this.outputGeneration;
        RoundVideoDiagnostics r1 = this.diagnostics;
        StringBuilder r2 = new StringBuilder("session summary: terminal=");
        r2.append(r6);
        r2.append(", state=");
        r2.append(this.state);
        r2.append(", durationMs=");
        r2.append(getRecordedDurationMs());
        r2.append(", pauses=");
        r2.append(this.pauseCount);
        r2.append(", resumes=");
        r2.append(this.resumeCount);
        r2.append(", cameraSwitches=");
        r2.append(this.cameraSwitchCount);
        r2.append(", facing=");
        r2.append(this.activeFacing);
        r2.append(", cameraMode=");
        r2.append(this.activeCameraResolution);
        r2.append(", generation=");
        long r5 = 0;
        if (r0 != null) goto L8;
        long r3 = 0;
    L9:
        r2.append(r3);
        r2.append(", availableSize=");
        if (r0 == null) goto L13;
        r5 = r0.availableSize;
    L13:
        r2.append(r5);
        r1.log(r2.toString());
        return;
    L8:
        r3 = r0.id;
        goto L9
    }

    private void notifyCapabilities() {
        this.listener.onCameraCapabilitiesChanged(new CameraCapabilities(this.requestedFacing, this.activeFacing, this.requestedCameraResolution, this.activeCameraResolution, this.requestedFrameRate, this.activeFrameRate, this.flashType, this.maximumZoomRatio, null));
    }

    private void notifyState() {
        this.listener.onStateChanged(createStateInfo());
    }

    private void rebuildForResume() {
        boolean r2 = isTrimChanged();
        File r5 = this.previewFile;
        RoundVideoMp4Writer r3 = this.writer;
        OutputGeneration r4 = this.outputGeneration;
        this.fileExecutor.execute(new RoundVideoSession$$ExternalSyntheticLambda9(this, r2, r3, r4, r5));
    }

    private void releasePlayer() {
        this.mainHandler.removeCallbacks(this.previewProgressRunnable);
        this.previewPlaying = false;
        ExoPlayer r0 = this.player;
        if (r0 != null) goto L5;
        return;
    L5:
        r0.removeListener(this.playerListener);
        this.player.clearVideoTextureView(this.previewView);
        this.player.release();
        this.player = null;
    }

    private void replaceWithFinalRange(File r6, long r7, long r9, boolean r11, OutputInvalidationReason r12) {
        long r0 = System.nanoTime();
        this.diagnostics.log("final range remux started: range=" + r7 + ".." + r9 + ", includeAudio=" + r11 + ", reason=" + r12);
        ensureNotCancelled();
        invalidateOutput(this.outputGeneration, r12);
        createOutputGenerationOnFileThread(r11);
        ensureNotCancelled();
        RoundVideoRemuxer.Result r62 = RoundVideoRemuxer.copyRange(r6, this.writer, r7, r9, r11);
        this.writer.finish();
        long r8 = RoundVideoRemuxer.getDurationMs(this.writer.getFile());
        this.diagnostics.log("final range remux completed: durationMs=" + r8 + ", requestedDurationMs=" + r62.durationMs + ", actualStartMs=" + r62.startMs + ", size=" + this.writer.getFile().length() + ", elapsedMs=" + elapsedMs(r0));
        ensureNotCancelled();
        completeOutput(this.outputGeneration, this.writer.getFile(), r8, r11);
    }

    private static void requireMainThread() {
        if (Looper.myLooper() != Looper.getMainLooper()) goto L5;
        return;
    L5:
        Segment$$ExternalSyntheticBUOutline1.m("RoundVideoSession must be used from the main thread");
    }

    private void setScreenFlashEnabled(boolean r2) {
        if (this.screenFlashEnabled == r2) goto L10;
        this.screenFlashEnabled = r2;
        ScreenFlashController r1 = this.screenFlashController;
        if (r1 == null) goto L9;
        r1.setScreenFlashEnabled(r2);
        return;
    L9:
        return;
    }

    private void setState(State r6) {
        State r0 = this.state;
        this.state = r6;
        this.diagnostics.log("state: " + r0 + " -> " + r6 + ", durationMs=" + getRecordedDurationMs());
        notifyState();
    }

    private void shutdownExecutors() {
        this.mainHandler.removeCallbacksAndMessages(null);
        this.fileExecutor.shutdown();
        this.outputExecutor.shutdown();
    }

    private void updatePreviewPlaying(boolean r3) {
        if (this.previewPlaying != r3) goto L5;
        return;
    L5:
        this.previewPlaying = r3;
        this.mainHandler.removeCallbacks(this.previewProgressRunnable);
        if (r3 == false) goto L8;
        this.mainHandler.post(this.previewProgressRunnable);
    L8:
        this.listener.onPreviewPlaybackChanged(r3);
        notifyState();
    }

    public boolean cancel() {
        requireMainThread();
        State r0 = this.state;
        State r1 = State.RELEASED;
        if (r0 != r1) goto L5;
    L18:
        return false;
    L5:
        if (r0 == State.COMPLETED) goto L18;
        this.diagnostics.log("cancel requested: state=" + this.state);
        if (cancelOutput(OutputInvalidationReason.CANCELLED) == true) goto L10;
        return false;
    L10:
        disableCameraEffects();
        releasePlayer();
        setState(r1);
        if (this.cameraStopPending == false) goto L13;
    L16:
        this.cameraStopPending = true;
        this.cancelAfterCameraStops = true;
    L17:
        return true;
    L13:
        if (this.cameraController.stopSegment() == true) goto L16;
        finishCancellation();
        goto L17
    }

    public boolean finish(boolean r6) {
        requireMainThread();
        State r0 = this.state;
        State r1 = State.RECORDING;
        if (r0 != r1) goto L5;
    L8:
        this.diagnostics.log("finish requested: state=" + this.state + ", includeAudio=" + r6 + ", durationMs=" + getRecordedDurationMs() + ", trim=" + this.trimStartMs + ".." + this.trimEndMs);
        this.finishWithAudio = r6;
        if (this.state != r1) goto L13;
        this.recordedDurationMs = getRecordedDurationMs();
        this.finishAfterCameraStops = true;
        disableCameraEffects();
        this.mainHandler.removeCallbacks(this.durationLimitRunnable);
        setState(State.FINISHING);
        boolean r62 = this.cameraController.stopSegment();
        this.cameraStopPending = r62;
        if (r62 == true) goto L14;
        fail(new IllegalStateException("Unable to stop the camera segment"));
    L14:
        return true;
    L13:
        releasePlayer();
        setState(State.FINISHING);
        finishFromPreview(r6);
        goto L14
    L5:
        if (r0 == State.PREVIEWING) goto L8;
        return false;
    }

    public FrameRate getActiveFrameRate() {
        return this.activeFrameRate;
    }

    public File getPreviewFile() {
        requireMainThread();
        return this.previewFile;
    }

    public long getRecordedDurationMs() {
        requireMainThread();
        if (this.state == State.RECORDING) goto L7;
        return this.recordedDurationMs;
    L7:
        return Math.min(this.maximumDurationMs, (this.recordedDurationMs + SystemClock.elapsedRealtime()) - this.recordingStartedAtRealtimeMs);
    }

    public boolean pausePreview() {
        requireMainThread();
        if (this.state != State.PREVIEWING) goto L9;
        ExoPlayer r0 = this.player;
        if (r0 == null) goto L9;
        r0.pause();
        updatePreviewPlaying(false);
        this.listener.onPreviewProgress(this.player.getCurrentPosition(), this.recordedDurationMs);
        return true;
    L9:
        return false;
    }

    public boolean pauseRecording() {
        requireMainThread();
        if (this.state == State.RECORDING) goto L6;
        return false;
    L6:
        this.recordedDurationMs = getRecordedDurationMs();
        this.pauseCount++;
        this.diagnostics.log("pause requested: durationMs=" + this.recordedDurationMs);
        disableCameraEffects();
        this.mainHandler.removeCallbacks(this.durationLimitRunnable);
        setState(State.PAUSING);
        boolean r0 = this.cameraController.stopSegment();
        this.cameraStopPending = r0;
        if (r0 == true) goto L9;
        fail(new IllegalStateException("Unable to stop the camera segment"));
    L9:
        return true;
    }

    public boolean playPreview() {
        requireMainThread();
        if (this.state != State.PREVIEWING) goto L14;
        ExoPlayer r0 = this.player;
        if (r0 == null) goto L16;
        long r02 = r0.getCurrentPosition();
        long r2 = this.trimStartMs;
        if (r02 >= r2) goto L10;
    L11:
        this.player.seekTo(r2);
    L12:
        this.player.play();
        updatePreviewPlaying(true);
        return true;
    L10:
        if (r02 < this.trimEndMs) goto L12;
    L16:
        return false;
    L14:
        return false;
    }

    public void release() {
        requireMainThread();
        State r0 = this.state;
        State r1 = State.RELEASED;
        if (r0 != r1) goto L9;
        if (this.cleanupStarted == true) goto L14;
        finishCancellation();
        return;
    L14:
        return;
    L9:
        if (r0 == State.COMPLETED) goto L12;
        cancel();
        return;
    L12:
        disableCameraEffects();
        releasePlayer();
        this.cameraController.release();
        this.cleanupStarted = true;
        setState(r1);
        logSummary("released");
        shutdownExecutors();
    }

    public boolean resumeRecording() {
        requireMainThread();
        if (this.state == State.PREVIEWING) goto L5;
        return false;
    L5:
        if ((this.trimEndMs - this.trimStartMs) >= this.maximumDurationMs) goto L11;
        this.resumeCount++;
        this.diagnostics.log("resume requested: trim=" + this.trimStartMs + ".." + this.trimEndMs + ", sourceDurationMs=" + this.recordedDurationMs);
        releasePlayer();
        setState(State.RESUMING);
        rebuildForResume();
        return true;
    L11:
        return false;
    }

    public boolean seekPreview(long r8) {
        requireMainThread();
        if (this.state == State.PREVIEWING) goto L5;
        return false;
    L5:
        if (this.player == null) goto L11;
        long r3 = this.trimStartMs;
        long r82 = clamp(r8, r3, Math.max(r3, this.trimEndMs - 1));
        this.player.seekTo(r82);
        this.listener.onPreviewProgress(r82, this.recordedDurationMs);
        return true;
    L11:
        return false;
    }

    public boolean setCameraFacing(CameraFacing r5) {
        requireMainThread();
        if (isTerminalState() == false) goto L7;
        return false;
    L7:
        if (this.requestedFacing != r5) goto L9;
        return true;
    L9:
        this.diagnostics.log("camera facing requested: " + this.requestedFacing + " -> " + r5 + ", state=" + this.state);
        this.requestedFacing = r5;
        State r0 = this.state;
        if (r0 != State.RECORDING) goto L12;
    L13:
        disableCameraEffects();
        this.cameraSwitching = this.cameraController.setCameraFacing(r5);
    L14:
        notifyCapabilities();
        notifyState();
        return true;
    L12:
        if (r0 != State.STARTING) goto L14;
        goto L13
    }

    public boolean setFlashEnabled(boolean r4) {
        requireMainThread();
        if (this.state == State.RECORDING) goto L5;
    L15:
        return false;
    L5:
        if (this.cameraSwitching == true) goto L15;
        FlashType r0 = this.flashType;
        if (r0 == FlashType.NONE) goto L15;
        this.flashEnabled = r4;
        if (r0 != FlashType.SCREEN) goto L12;
        setScreenFlashEnabled(r4);
        this.cameraController.setTorchEnabled(false);
    L13:
        notifyState();
        return true;
    L12:
        setScreenFlashEnabled(false);
        this.cameraController.setTorchEnabled(r4);
        goto L13
    }

    public boolean setPreviewMuted(boolean r3) {
        requireMainThread();
        if (this.state != State.PREVIEWING) goto L12;
        ExoPlayer r2 = this.player;
        if (r2 == null) goto L14;
        if (r3 == false) goto L9;
        float r32 = 0.0f;
    L10:
        r2.setVolume(r32);
        return true;
    L9:
        r32 = 1.0f;
        goto L10
    L14:
        return false;
    L12:
        return false;
    }

    public boolean setTrimRange(long r17, long r19) {
        requireMainThread();
        if (this.state == State.PREVIEWING) goto L5;
        return false;
    L5:
        long r12 = clamp(r17, 0, this.recordedDurationMs);
        long r1 = clamp(r19, r12, this.recordedDurationMs);
        if ((r1 - r12) >= Math.min(800, this.recordedDurationMs)) goto L8;
        return false;
    L8:
        this.trimStartMs = r12;
        this.trimEndMs = r1;
        ExoPlayer r13 = this.player;
        if (r13 == null) goto L11;
        r13.seekTo(r12);
    L11:
        this.listener.onPreviewProgress(this.trimStartMs, this.recordedDurationMs);
        notifyState();
        return true;
    }

    public boolean setZoom(float r3) {
        requireMainThread();
        if (this.state == State.RECORDING) goto L5;
        return false;
    L5:
        if (this.cameraSwitching == true) goto L11;
        float r32 = clamp(r3, 0.0f, 1.0f);
        this.zoomProgress = r32;
        return this.cameraController.setZoom(r32);
    L11:
        return false;
    }

    public boolean start() {
        requireMainThread();
        if (this.state == State.IDLE) goto L10;
        return false;
    L10:
        this.diagnostics.log("start requested");     // Catch: Exception -> L7
        createOutputGeneration(true);     // Catch: Exception -> L7
        setState(State.STARTING);     // Catch: Exception -> L7
        this.cameraController.startSegment(this.writer, 0, this.requestedFacing);     // Catch: Exception -> L7
        return true;
    L7:
        e = move-exception;
        fail(asException(e));
        return false;
    }
}
