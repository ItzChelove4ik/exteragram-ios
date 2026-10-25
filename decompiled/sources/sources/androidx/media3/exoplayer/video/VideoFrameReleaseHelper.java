package androidx.media3.exoplayer.video;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.view.Choreographer;
import android.view.Display;
import android.view.Surface;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;

/* loaded from: classes4.dex */
public final class VideoFrameReleaseHelper {
    private int changeFrameRateStrategy;
    private final DisplayHelper displayHelper;
    private float formatFrameRate;
    private long frameIndex;
    private final FixedFrameRateEstimator frameRateEstimator;
    private long lastAdjustedFrameIndex;
    private long lastAdjustedReleaseTimeNs;
    private long pendingLastAdjustedFrameIndex;
    private long pendingLastAdjustedReleaseTimeNs;
    private float playbackSpeed;
    private boolean started;
    private Surface surface;
    private float surfaceMediaFrameRate;
    private float surfacePlaybackFrameRate;
    private long vsyncDurationNs;
    private long vsyncOffsetNs;
    private final VSyncSampler vsyncSampler;

    public static final class Api30 {
        public static void setSurfaceFrameRate(Surface r1, float r2) {
            if (r2 != 0.0f) goto L5;
            int r0 = 0;
        L11:
            r1.setFrameRate(r2, r0);     // Catch: IllegalStateException -> L8
            return;
        L8:
            e = move-exception;
            Log.e("VideoFrameReleaseHelper", "Failed to call Surface.setFrameRate", e);
            return;
        L5:
            r0 = 1;
            goto L11
        }
    }

    public final class DisplayHelper implements DisplayManager.DisplayListener {
        private final DisplayManager displayManager;
        final /* synthetic */ VideoFrameReleaseHelper this$0;

        public DisplayHelper(VideoFrameReleaseHelper r1, DisplayManager r2) {
            this.this$0 = r1;
            this.displayManager = r2;
        }

        private Display getDefaultDisplay() {
            return this.displayManager.getDisplay(0);
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public void onDisplayAdded(int r1) {
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public void onDisplayChanged(int r1) {
            if (r1 != 0) goto L5;
            VideoFrameReleaseHelper.access$000(this.this$0, getDefaultDisplay());
            return;
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public void onDisplayRemoved(int r1) {
        }

        public void register() {
            this.displayManager.registerDisplayListener(this, Util.createHandlerForCurrentLooper());
            VideoFrameReleaseHelper.access$000(this.this$0, getDefaultDisplay());
        }

        public void unregister() {
            this.displayManager.unregisterDisplayListener(this);
        }
    }

    public static final class VSyncSampler implements Choreographer.FrameCallback, Handler.Callback {
        private static final VSyncSampler INSTANCE = null;
        private Choreographer choreographer;
        private final HandlerThread choreographerOwnerThread;
        private final Handler handler;
        private int observerCount;
        public volatile long sampledVsyncTimeNs;

        static {
            INSTANCE = new VSyncSampler();
        }

        private VSyncSampler() {
            this.sampledVsyncTimeNs = -9223372036854775807L;
            HandlerThread r0 = new HandlerThread("ExoPlayer:FrameReleaseChoreographer");
            this.choreographerOwnerThread = r0;
            r0.start();
            Handler r02 = Util.createHandler(r0.getLooper(), this);
            this.handler = r02;
            r02.sendEmptyMessage(1);
        }

        private void addObserverInternal() {
            Choreographer r0 = this.choreographer;
            if (r0 == null) goto L8;
            int r1 = this.observerCount + 1;
            this.observerCount = r1;
            if (r1 != 1) goto L9;
            r0.postFrameCallback(this);
            return;
        L9:
            return;
        }

        private void createChoreographerInstanceInternal() {
            this.choreographer = Choreographer.getInstance();     // Catch: RuntimeException -> L4
            return;
        L4:
            e = move-exception;
            Log.w("VideoFrameReleaseHelper", "Vsync sampling disabled due to platform error", e);
        }

        public static VSyncSampler getInstance() {
            return INSTANCE;
        }

        private void removeObserverInternal() {
            Choreographer r0 = this.choreographer;
            if (r0 == null) goto L8;
            int r1 = this.observerCount - 1;
            this.observerCount = r1;
            if (r1 != 0) goto L9;
            r0.removeFrameCallback(this);
            this.sampledVsyncTimeNs = -9223372036854775807L;
            return;
        L9:
            return;
        }

        public void addObserver() {
            this.handler.sendEmptyMessage(2);
        }

        @Override // android.view.Choreographer.FrameCallback
        public void doFrame(long r3) {
            this.sampledVsyncTimeNs = r3;
            ((Choreographer) Assertions.checkNotNull(this.choreographer)).postFrameCallbackDelayed(this, 500);
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message r3) {
            int r32 = r3.what;
            if (r32 != 1) goto L5;
            createChoreographerInstanceInternal();
            return true;
        L5:
            if (r32 != 2) goto L7;
            addObserverInternal();
            return true;
        L7:
            if (r32 == 3) goto L10;
            return false;
        L10:
            removeObserverInternal();
            return true;
        }

        public void removeObserver() {
            this.handler.sendEmptyMessage(3);
        }
    }

    public VideoFrameReleaseHelper(Context r3) {
        this.frameRateEstimator = new FixedFrameRateEstimator();
        DisplayHelper r32 = maybeBuildDisplayHelper(r3);
        this.displayHelper = r32;
        if (r32 == null) goto L5;
        VSyncSampler r33 = VSyncSampler.getInstance();
    L6:
        this.vsyncSampler = r33;
        this.vsyncDurationNs = -9223372036854775807L;
        this.vsyncOffsetNs = -9223372036854775807L;
        this.formatFrameRate = -1.0f;
        this.playbackSpeed = 1.0f;
        this.changeFrameRateStrategy = 0;
        return;
    L5:
        r33 = null;
        goto L6
    }

    public static /* synthetic */ void access$000(VideoFrameReleaseHelper r0, Display r1) {
        r0.updateDefaultDisplayRefreshRateParams(r1);
    }

    private static boolean adjustmentAllowed(long r0, long r2) {
        if (Math.abs(r0 - r2) > 20000000) goto L6;
        return true;
    L6:
        return false;
    }

    private void clearSurfaceFrameRate() {
        if (Build.VERSION.SDK_INT < 30) goto L13;
        Surface r0 = this.surface;
        if (r0 != null) goto L7;
        return;
    L7:
        if (this.changeFrameRateStrategy != Integer.MIN_VALUE) goto L9;
        return;
    L9:
        if (this.surfacePlaybackFrameRate == 0.0f) goto L16;
        this.surfacePlaybackFrameRate = 0.0f;
        Api30.setSurfaceFrameRate(r0, 0.0f);
        return;
    L16:
        return;
    }

    private static long closestVsync(long r4, long r6, long r8) {
        long r62 = r6 + (((r4 - r6) / r8) * r8);
        if (r4 > r62) goto L5;
        long r82 = r62 - r8;
    L7:
        if ((r62 - r4) >= (r4 - r82)) goto L9;
        return r62;
    L9:
        return r82;
    L5:
        long r83 = r8 + r62;
        r82 = r62;
        r62 = r83;
        goto L7
    }

    private DisplayHelper maybeBuildDisplayHelper(Context r3) {
        if (r3 != null) goto L5;
        return null;
    L5:
        DisplayManager r32 = (DisplayManager) r3.getSystemService("display");
        if (r32 != null) goto L8;
        return null;
    L8:
        return new DisplayHelper(this, r32);
    }

    private void resetAdjustment() {
        this.frameIndex = 0;
        this.lastAdjustedFrameIndex = -1;
        this.pendingLastAdjustedFrameIndex = -1;
    }

    private void updateDefaultDisplayRefreshRateParams(Display r5) {
        if (r5 == null) goto L5;
        long r0 = (long) (1.0E9d / r5.getRefreshRate());
        this.vsyncDurationNs = r0;
        this.vsyncOffsetNs = (r0 * 80) / 100;
        return;
    L5:
        Log.w("VideoFrameReleaseHelper", "Unable to query display refresh rate");
        this.vsyncDurationNs = -9223372036854775807L;
        this.vsyncOffsetNs = -9223372036854775807L;
    }

    private void updateSurfaceMediaFrameRate() {
        if (Build.VERSION.SDK_INT >= 30) goto L5;
        return;
    L5:
        if (this.surface != null) goto L8;
        return;
    L8:
        if (this.frameRateEstimator.isSynced() == false) goto L10;
        float r0 = this.frameRateEstimator.getFrameRate();
    L11:
        float r2 = this.surfaceMediaFrameRate;
        if (r0 != r2) goto L15;
        return;
    L15:
        if (r0 != (-1.0f)) goto L17;
    L28:
        if (r0 == (-1.0f)) goto L31;
    L32:
        this.surfaceMediaFrameRate = r0;
        updateSurfacePlaybackFrameRate(false);
        return;
    L31:
        if (this.frameRateEstimator.getFramesWithoutSyncCount() >= 30) goto L32;
        return;
    L17:
        if (r2 == (-1.0f)) goto L28;
        if (this.frameRateEstimator.isSynced() == true) goto L21;
    L23:
        float r1 = 1.0f;
    L25:
        if (Math.abs(r0 - this.surfaceMediaFrameRate) >= r1) goto L32;
        return;
    L21:
        if (this.frameRateEstimator.getMatchingFrameDurationSumNs() < 5000000000L) goto L23;
        r1 = 0.02f;
        goto L25
    L10:
        r0 = this.formatFrameRate;
        goto L11
    }

    private void updateSurfacePlaybackFrameRate(boolean r4) {
        if (Build.VERSION.SDK_INT < 30) goto L21;
        Surface r0 = this.surface;
        if (r0 != null) goto L7;
        return;
    L7:
        if (this.changeFrameRateStrategy != Integer.MIN_VALUE) goto L10;
        return;
    L10:
        if (this.started == false) goto L14;
        float r1 = this.surfaceMediaFrameRate;
        if (r1 == (-1.0f)) goto L14;
        float r12 = r1 * this.playbackSpeed;
    L15:
        if (r4 == false) goto L17;
    L19:
        this.surfacePlaybackFrameRate = r12;
        Api30.setSurfaceFrameRate(r0, r12);
        return;
    L17:
        if (this.surfacePlaybackFrameRate != r12) goto L19;
        return;
    L14:
        r12 = 0.0f;
        goto L15
    }

    public long adjustReleaseTime(long r11) {
        if (this.lastAdjustedFrameIndex != (-1)) goto L5;
    L10:
        long r4 = r11;
    L11:
        this.pendingLastAdjustedFrameIndex = this.frameIndex;
        this.pendingLastAdjustedReleaseTimeNs = r4;
        VSyncSampler r112 = this.vsyncSampler;
        if (r112 != null) goto L14;
    L21:
        return r4;
    L14:
        if (this.vsyncDurationNs == (-9223372036854775807L)) goto L21;
        long r6 = r112.sampledVsyncTimeNs;
        if (r6 == (-9223372036854775807L)) goto L21;
        return closestVsync(r4, r6, this.vsyncDurationNs) - this.vsyncOffsetNs;
    L5:
        if (this.frameRateEstimator.isSynced() == false) goto L10;
        long r0 = this.frameRateEstimator.getFrameDurationNs();
        long r2 = this.lastAdjustedReleaseTimeNs + ((long) ((r0 * (this.frameIndex - this.lastAdjustedFrameIndex)) / this.playbackSpeed));
        if (adjustmentAllowed(r11, r2) == false) goto L9;
        r4 = r2;
        goto L11
    L9:
        resetAdjustment();
        goto L10
    }

    public void onFormatChanged(float r1) {
        this.formatFrameRate = r1;
        this.frameRateEstimator.reset();
        updateSurfaceMediaFrameRate();
    }

    public void onNextFrame(long r5) {
        long r0 = this.pendingLastAdjustedFrameIndex;
        if (r0 == (-1)) goto L5;
        this.lastAdjustedFrameIndex = r0;
        this.lastAdjustedReleaseTimeNs = this.pendingLastAdjustedReleaseTimeNs;
    L5:
        this.frameIndex++;
        this.frameRateEstimator.onNextFrame(r5 * 1000);
        updateSurfaceMediaFrameRate();
    }

    public void onPlaybackSpeed(float r1) {
        this.playbackSpeed = r1;
        resetAdjustment();
        updateSurfacePlaybackFrameRate(false);
    }

    public void onPositionReset() {
        resetAdjustment();
    }

    public void onStarted() {
        this.started = true;
        resetAdjustment();
        if (this.displayHelper == null) goto L5;
        ((VSyncSampler) Assertions.checkNotNull(this.vsyncSampler)).addObserver();
        this.displayHelper.register();
    L5:
        updateSurfacePlaybackFrameRate(false);
    }

    public void onStopped() {
        this.started = false;
        DisplayHelper r0 = this.displayHelper;
        if (r0 == null) goto L5;
        r0.unregister();
        ((VSyncSampler) Assertions.checkNotNull(this.vsyncSampler)).removeObserver();
    L5:
        clearSurfaceFrameRate();
    }

    public void onSurfaceChanged(Surface r2) {
        if (this.surface != r2) goto L5;
        return;
    L5:
        clearSurfaceFrameRate();
        this.surface = r2;
        updateSurfacePlaybackFrameRate(true);
    }

    public void setChangeFrameRateStrategy(int r2) {
        if (this.changeFrameRateStrategy != r2) goto L5;
        return;
    L5:
        this.changeFrameRateStrategy = r2;
        updateSurfacePlaybackFrameRate(true);
    }
}
