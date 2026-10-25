package org.telegram.messenger.video;

import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.SurfaceTexture;
import android.net.Uri;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.TextureView;
import androidx.media3.common.text.CueGroup;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DispatchQueue;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.FileStreamLoadOperation;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.VideoPlayer;

/* loaded from: classes6.dex */
public class VideoPlayerHolderBase {
    private boolean allowMultipleInstances;
    boolean audioDisabled;
    private final Runnable betterSeek;
    Uri contentUri;
    private int currentAccount;
    public volatile long currentPosition;
    public float currentSeek;
    public volatile float currentSeekThread;
    final DispatchQueue dispatchQueue;
    public TLRPC.Document document;
    private volatile long duration;
    public boolean firstFrameRendered;
    private volatile boolean firstSeek;
    Runnable initRunnable;
    private long lastBetterSeek;
    private volatile long lastSeek;
    int lastState;
    private Runnable onErrorListener;
    private Runnable onReadyListener;
    private Runnable onSeekUpdate;
    public boolean paused;
    public long pendingSeekTo;
    long playerDuration;
    public Bitmap playerStubBitmap;
    public Paint playerStubPaint;
    public float progress;
    Runnable progressRunnable;
    public volatile boolean released;
    private volatile boolean seeking;
    long startTime;
    public boolean stubAvailable;
    private Surface surface;
    private SurfaceView surfaceView;
    private TextureView textureView;
    private volatile int triesCount;
    private final Runnable updateSeek;
    public Uri uri;
    VideoPlayer videoPlayer;

    /* renamed from: org.telegram.messenger.video.VideoPlayerHolderBase$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ VideoPlayerHolderBase this$0;

        public AnonymousClass1(VideoPlayerHolderBase r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            VideoPlayerHolderBase r0 = this.this$0;
            VideoPlayer r1 = r0.videoPlayer;
            if (r1 != null) goto L5;
            return;
        L5:
            if (r0.lastState != 4) goto L7;
            r0.progress = 1.0f;
        L8:
            VideoPlayerHolderBase r02 = this.this$0;
            int r12 = r02.lastState;
            if (r12 != 3) goto L11;
        L12:
            r02.dispatchQueue.cancelRunnable(r02.progressRunnable);
            VideoPlayerHolderBase r4 = this.this$0;
            r4.dispatchQueue.postRunnable(r4.progressRunnable, 16);
            return;
        L11:
            if (r12 == 2) goto L12;
            return;
        L7:
            r0.currentPosition = r1.getCurrentPosition();
            VideoPlayerHolderBase r03 = this.this$0;
            r03.playerDuration = r03.videoPlayer.getDuration();
            goto L8
        }
    }

    /* renamed from: org.telegram.messenger.video.VideoPlayerHolderBase$2, reason: invalid class name */
    public class AnonymousClass2 implements VideoPlayer.VideoPlayerDelegate {
        final /* synthetic */ VideoPlayerHolderBase this$0;

        public static /* synthetic */ void $r8$lambda$NoLn01RUPyLVpwGbNWeBvl29WKg(AnonymousClass2 r0) {
            r0.lambda$onRenderedFirstFrame$2();
        }

        public static /* synthetic */ void $r8$lambda$U7GPpt_UtH2waavQDJ5kHoUZ0Hg(AnonymousClass2 r0) {
            r0.lambda$onError$1();
        }

        public static /* synthetic */ void $r8$lambda$xZ05r8r4H9x36Q1lzWAo8ozBeZs(AnonymousClass2 r0, long r1) {
            r0.lambda$onError$0(r1);
        }

        public AnonymousClass2(VideoPlayerHolderBase r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onError$0(long r4) {
            if (this.this$0.released == true) goto L9;
            VideoPlayerHolderBase r0 = this.this$0;
            Uri r1 = r0.uri;
            if (r1 == null) goto L10;
            r0.videoPlayer.preparePlayer(r1, "other");
            this.this$0.videoPlayer.seekTo(r4);
            return;
        L10:
            return;
        }

        private /* synthetic */ void lambda$onError$1() {
            if (VideoPlayerHolderBase.m5242$$Nest$fgetonErrorListener(this.this$0) == null) goto L6;
            VideoPlayerHolderBase.m5242$$Nest$fgetonErrorListener(this.this$0).run();
            VideoPlayerHolderBase.m5247$$Nest$fputonErrorListener(this.this$0, null);
            return;
        }

        private /* synthetic */ void lambda$onRenderedFirstFrame$2() {
            if (this.this$0.released == true) goto L10;
            this.this$0.onRenderedFirstFrame();
            if (VideoPlayerHolderBase.m5243$$Nest$fgetonReadyListener(this.this$0) == null) goto L9;
            VideoPlayerHolderBase.m5243$$Nest$fgetonReadyListener(this.this$0).run();
            VideoPlayerHolderBase.m5248$$Nest$fputonReadyListener(this.this$0, null);
            return;
        L9:
            return;
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public void onAudioTrackChanged(int r1) {
            this.this$0.onAudioTrackChanged(r1);
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public /* bridge */ /* synthetic */ void onCues(CueGroup r1) {
            super.onCues(r1);
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public void onError(VideoPlayer r4, Exception r5) {
            FileLog.e(r5);
            long r42 = this.this$0.getCurrentPosition();
            VideoPlayerHolderBase.m5249$$Nest$fputtriesCount(this.this$0, VideoPlayerHolderBase.m5246$$Nest$fgettriesCount(r0) - 1);
            if (VideoPlayerHolderBase.m5246$$Nest$fgettriesCount(this.this$0) <= 0) goto L6;
            VideoPlayerHolderBase r0 = this.this$0;
            DispatchQueue r1 = r0.dispatchQueue;
            VideoPlayerHolderBase$2$$ExternalSyntheticLambda1 r2 = new VideoPlayerHolderBase$2$$ExternalSyntheticLambda1(this, r42);
            r0.initRunnable = r2;
            r1.postRunnable(r2);
            return;
        L6:
            AndroidUtilities.runOnUIThread(new VideoPlayerHolderBase$2$$ExternalSyntheticLambda2(this));
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public void onRenderedFirstFrame() {
            VideoPlayerHolderBase$2$$ExternalSyntheticLambda0 r0 = new VideoPlayerHolderBase$2$$ExternalSyntheticLambda0(this);
            if (VideoPlayerHolderBase.m5244$$Nest$fgetsurface(this.this$0) == null) goto L6;
            long r1 = 0;
        L9:
            AndroidUtilities.runOnUIThread(r0, r1);
            return;
        L6:
            if (VideoPlayerHolderBase.m5245$$Nest$fgetsurfaceView(this.this$0) != null) goto L8;
            r1 = 16;
            goto L9
        L8:
            r1 = 32;
            goto L9
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public /* bridge */ /* synthetic */ void onRenderedFirstFrame(AnalyticsListener.EventTime r1) {
            super.onRenderedFirstFrame(r1);
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public /* bridge */ /* synthetic */ void onSeekFinished(AnalyticsListener.EventTime r1) {
            super.onSeekFinished(r1);
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public /* bridge */ /* synthetic */ void onSeekStarted(AnalyticsListener.EventTime r1) {
            super.onSeekStarted(r1);
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public void onStateChanged(boolean r4, int r5) {
            VideoPlayerHolderBase r0 = this.this$0;
            r0.lastState = r5;
            r0.currentPosition = r0.videoPlayer.getCurrentPosition();
            VideoPlayerHolderBase r02 = this.this$0;
            r02.playerDuration = r02.videoPlayer.getDuration();
            if (r5 != 3) goto L5;
        L13:
            VideoPlayerHolderBase r03 = this.this$0;
            r03.dispatchQueue.cancelRunnable(r03.progressRunnable);
            VideoPlayerHolderBase r04 = this.this$0;
            r04.dispatchQueue.postRunnable(r04.progressRunnable);
        L14:
            this.this$0.onStateChanged(r4, r5);
            return;
        L5:
            if (r5 == 2) goto L13;
            if (r5 != 4) goto L14;
            boolean r05 = this.this$0.needRepeat();
            VideoPlayerHolderBase r1 = this.this$0;
            if (r05 == false) goto L12;
            r1.progress = 0.0f;
            r1.videoPlayer.seekTo(0);
            this.this$0.videoPlayer.play();
            goto L14
        L12:
            r1.progress = 1.0f;
            goto L14
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public /* bridge */ /* synthetic */ boolean onSurfaceDestroyed(SurfaceTexture r1) {
            return super.onSurfaceDestroyed(r1);
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public /* bridge */ /* synthetic */ void onSurfaceTextureUpdated(SurfaceTexture r1) {
            super.onSurfaceTextureUpdated(r1);
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public void onVideoSizeChanged(int r1, int r2, int r3, float r4) {
            this.this$0.onVideoSizeChanged(r1, r2, r3, r4);
        }
    }

    public static /* synthetic */ void $r8$lambda$04rLJMRR1oYNrdEGRaS2on00Gyo(VideoPlayerHolderBase r0) {
        r0.lambda$pause$4();
    }

    public static /* synthetic */ void $r8$lambda$8ptHkvIZhZyVyGYWr2Pf4rSazOY(VideoPlayerHolderBase r0, TLRPC.Document r1, Runnable r2) {
        r0.lambda$release$3(r1, r2);
    }

    /* renamed from: $r8$lambda$HXNNe9SfQ1MB-ht8QAlDwcHzvpE, reason: not valid java name */
    public static /* synthetic */ void m5238$r8$lambda$HXNNe9SfQ1MBht8QAlDwcHzvpE(VideoPlayerHolderBase r0, boolean r1, float r2, Uri r3) {
        r0.lambda$preparePlayer$0(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$L_vl4pXxc7OzdjBUGaDMxuS79vE(VideoPlayerHolderBase r0, boolean r1, boolean r2) {
        r0.lambda$setAudioEnabled$8(r1, r2);
    }

    /* renamed from: $r8$lambda$MO-trhf3b83RSYEWHP4AnMMxNHM, reason: not valid java name */
    public static /* synthetic */ void m5239$r8$lambda$MOtrhf3b83RSYEWHP4AnMMxNHM(VideoPlayerHolderBase r0, float r1) {
        r0.lambda$setSpeed$5(r1);
    }

    public static /* synthetic */ void $r8$lambda$VaN3ZKJVZjQ4mWlnV0tQ3a3RywA(VideoPlayerHolderBase r0) {
        r0.lambda$loopBack$9();
    }

    public static /* synthetic */ void $r8$lambda$ZNT13QCmTCRjl4z9tPZPvUD8RUA(VideoPlayerHolderBase r0) {
        r0.lambda$play$6();
    }

    public static /* synthetic */ void $r8$lambda$b1_IfZWw1Fw25jNUgk_2HtwvC4g(VideoPlayerHolderBase r0) {
        r0.lambda$new$14();
    }

    public static /* synthetic */ void $r8$lambda$cEXZPR87QCyM_FRLYu1KyHpUlBs(VideoPlayerHolderBase r0, float r1) {
        r0.lambda$setVolume$10(r1);
    }

    /* renamed from: $r8$lambda$g4U19oIr8LBEPvhfXwltuTmML-I, reason: not valid java name */
    public static /* synthetic */ void m5240$r8$lambda$g4U19oIr8LBEPvhfXwltuTmMLI(VideoPlayerHolderBase r0) {
        r0.lambda$start$1();
    }

    public static /* synthetic */ void $r8$lambda$ho3fs7DjH22IODWLomzfVU6jgD4(VideoPlayerHolderBase r0) {
        r0.lambda$new$13();
    }

    /* renamed from: $r8$lambda$kSw-PQniaV4GJeugBma6e8a8D2E, reason: not valid java name */
    public static /* synthetic */ void m5241$r8$lambda$kSwPQniaV4GJeugBma6e8a8D2E(VideoPlayerHolderBase r0, boolean r1, float r2, Uri r3, boolean r4, boolean r5, long r6) {
        r0.lambda$start$2(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$pwfYIKocn969_hdCvMYTrExrr2A(VideoPlayerHolderBase r0, long r1, boolean r3, Runnable r4) {
        r0.lambda$seekTo$12(r1, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$zjKIY9RjAHnsRlhYFc5IPN9Q6N4(VideoPlayerHolderBase r0, long r1) {
        r0.lambda$seekTo$11(r1);
    }

    public static /* synthetic */ void $r8$lambda$zoGAPERcDI0S1Nz_tkwbrAOPIlU(VideoPlayerHolderBase r0, float r1) {
        r0.lambda$play$7(r1);
    }

    /* renamed from: -$$Nest$fgetonErrorListener, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m5242$$Nest$fgetonErrorListener(VideoPlayerHolderBase r0) {
        return r0.onErrorListener;
    }

    /* renamed from: -$$Nest$fgetonReadyListener, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m5243$$Nest$fgetonReadyListener(VideoPlayerHolderBase r0) {
        return r0.onReadyListener;
    }

    /* renamed from: -$$Nest$fgetsurface, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Surface m5244$$Nest$fgetsurface(VideoPlayerHolderBase r0) {
        return r0.surface;
    }

    /* renamed from: -$$Nest$fgetsurfaceView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SurfaceView m5245$$Nest$fgetsurfaceView(VideoPlayerHolderBase r0) {
        return r0.surfaceView;
    }

    /* renamed from: -$$Nest$fgettriesCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m5246$$Nest$fgettriesCount(VideoPlayerHolderBase r0) {
        return r0.triesCount;
    }

    /* renamed from: -$$Nest$fputonErrorListener, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5247$$Nest$fputonErrorListener(VideoPlayerHolderBase r0, Runnable r1) {
        r0.onErrorListener = r1;
    }

    /* renamed from: -$$Nest$fputonReadyListener, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5248$$Nest$fputonReadyListener(VideoPlayerHolderBase r0, Runnable r1) {
        r0.onReadyListener = r1;
    }

    /* renamed from: -$$Nest$fputtriesCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5249$$Nest$fputtriesCount(VideoPlayerHolderBase r0, int r1) {
        r0.triesCount = r1;
    }

    public VideoPlayerHolderBase() {
        this.dispatchQueue = Utilities.getOrCreatePlayerQueue();
        this.progressRunnable = new AnonymousClass1(this);
        this.triesCount = 3;
        this.firstSeek = true;
        this.lastSeek = -1;
        this.lastBetterSeek = -1;
        this.currentSeek = 0.0f;
        this.currentSeekThread = 0.0f;
        this.betterSeek = new VideoPlayerHolderBase$$ExternalSyntheticLambda0(this);
        this.updateSeek = new VideoPlayerHolderBase$$ExternalSyntheticLambda1(this);
    }

    private void ensurePlayerCreated(boolean r3) {
        VideoPlayer r0 = this.videoPlayer;
        if (r0 == null) goto L5;
        r0.releasePlayer(true);
    L5:
        VideoPlayer r02 = new VideoPlayer(false, r3);
        this.videoPlayer = r02;
        r02.allowMultipleInstances = this.allowMultipleInstances;
        r02.setDelegate(new AnonymousClass2(this));
        this.videoPlayer.setIsStory();
    }

    private /* synthetic */ void lambda$loopBack$9() {
        VideoPlayer r0 = this.videoPlayer;
        if (r0 == null) goto L5;
        r0.seekTo(0);
    L5:
        this.progress = 0.0f;
        this.currentPosition = 0;
    }

    private /* synthetic */ void lambda$new$13() {
    }

    private /* synthetic */ void lambda$new$14() {
        if (this.videoPlayer == null) goto L17;
        long r0 = (long) (this.currentSeekThread * this.duration);
        if (this.lastSeek > (-1)) goto L8;
        this.lastSeek = r0;
    L8:
        long r2 = Math.abs(r0 - this.lastSeek);
        if (this.firstSeek == false) goto L11;
        int r4 = 350;
    L13:
        if (r2 < r4) goto L16;
        this.firstSeek = false;
        this.lastBetterSeek = r0;
        this.dispatchQueue.cancelRunnable(this.betterSeek);
        this.dispatchQueue.postRunnable(this.betterSeek, 300);
        VideoPlayer r22 = this.videoPlayer;
        this.lastSeek = r0;
        r22.seekTo(r0, true);
        return;
    L16:
        return;
    L11:
        r4 = 40;
        goto L13
    }

    private /* synthetic */ void lambda$pause$4() {
        VideoPlayer r0 = this.videoPlayer;
        if (r0 == null) goto L6;
        r0.pause();
        return;
    }

    private /* synthetic */ void lambda$play$6() {
        VideoPlayer r0 = this.videoPlayer;
        if (r0 == null) goto L16;
        Surface r1 = this.surface;
        if (r1 == null) goto L7;
        r0.setSurface(r1);
    L11:
        long r02 = this.pendingSeekTo;
        if (r02 <= 0) goto L14;
        this.videoPlayer.seekTo(r02);
        this.pendingSeekTo = 0;
    L14:
        this.videoPlayer.setPlayWhenReady(true);
        return;
    L7:
        SurfaceView r12 = this.surfaceView;
        if (r12 == null) goto L10;
        r0.setSurfaceView(r12);
        goto L11
    L10:
        r0.setTextureView(this.textureView);
        goto L11
    }

    private /* synthetic */ void lambda$play$7(float r6) {
        VideoPlayer r0 = this.videoPlayer;
        if (r0 == null) goto L16;
        Surface r1 = this.surface;
        if (r1 == null) goto L7;
        r0.setSurface(r1);
    L11:
        long r02 = this.pendingSeekTo;
        if (r02 <= 0) goto L14;
        this.videoPlayer.seekTo(r02);
        this.pendingSeekTo = 0;
    L14:
        this.videoPlayer.setPlaybackSpeed(r6);
        this.videoPlayer.setPlayWhenReady(true);
        return;
    L7:
        SurfaceView r12 = this.surfaceView;
        if (r12 == null) goto L10;
        r0.setSurfaceView(r12);
        goto L11
    L10:
        r0.setTextureView(this.textureView);
        goto L11
    }

    private /* synthetic */ void lambda$preparePlayer$0(boolean r7, float r8, Uri r9) {
        if (this.released == false) goto L5;
        return;
    L5:
        ensurePlayerCreated(r7);
        this.videoPlayer.setPlaybackSpeed(r8);
        FileLog.d("videoplayerholderbase.preparePlayer(): preparePlayer new player as preload uri=" + r9);
        this.videoPlayer.preparePlayer(r9, "other", 0, 0);
        this.videoPlayer.setPlayWhenReady(false);
        this.videoPlayer.setWorkerQueue(this.dispatchQueue);
    }

    private /* synthetic */ void lambda$release$3(TLRPC.Document r4, Runnable r5) {
        VideoPlayer r0 = this.videoPlayer;
        if (r0 == null) goto L5;
        r0.setSurface(null);
        this.videoPlayer.setTextureView(null);
        this.videoPlayer.setSurfaceView(null);
        this.videoPlayer.releasePlayer(false);
    L5:
        if (r4 == null) goto L7;
        FileLoader.getInstance(this.currentAccount).cancelLoadFile(r4);
    L7:
        if (r5 == null) goto L9;
        AndroidUtilities.runOnUIThread(r5);
    L9:
        this.videoPlayer = null;
        this.dispatchQueue.cancelRunnable(this.progressRunnable);
    }

    private /* synthetic */ void lambda$seekTo$11(long r2) {
        VideoPlayer r0 = this.videoPlayer;
        if (r0 != null) goto L6;
        this.pendingSeekTo = r2;
        return;
    L6:
        r0.seekTo(r2);
    }

    private /* synthetic */ void lambda$seekTo$12(long r2, boolean r4, Runnable r5) {
        VideoPlayer r0 = this.videoPlayer;
        if (r0 != null) goto L6;
        this.pendingSeekTo = r2;
        return;
    L6:
        r0.seekTo(r2, r4, r5);
    }

    private /* synthetic */ void lambda$setAudioEnabled$8(boolean r7, boolean r8) {
        VideoPlayer r0 = this.videoPlayer;
        if (r0 != null) goto L5;
        return;
    L5:
        boolean r02 = r0.isPlaying();
        if (r7 == true) goto L8;
    L28:
        VideoPlayer r6 = this.videoPlayer;
        if (r7 == false) goto L31;
        float r72 = 1.0f;
    L32:
        r6.setVolume(r72);
        return;
    L31:
        r72 = 0.0f;
        goto L32
    L8:
        if (this.videoPlayer.createdWithAudioTrack() == true) goto L28;
        this.videoPlayer.pause();
        long r1 = this.videoPlayer.getCurrentPosition();
        this.videoPlayer.releasePlayer(false);
        this.videoPlayer = null;
        ensurePlayerCreated(this.audioDisabled);
        Uri r73 = this.uri;
        if (r73 != null) goto L12;
        r73 = this.contentUri;
    L12:
        FileLog.d("videoplayerholderbase.setAudioEnabled(): repreparePlayer as audio track is enabled back uri=" + r73);
        this.videoPlayer.preparePlayer(r73, "other");
        this.videoPlayer.setWorkerQueue(this.dispatchQueue);
        if (r8 == true) goto L21;
        Surface r74 = this.surface;
        if (r74 == null) goto L17;
        this.videoPlayer.setSurface(r74);
        goto L21
    L17:
        SurfaceView r75 = this.surfaceView;
        VideoPlayer r4 = this.videoPlayer;
        if (r75 == null) goto L20;
        r4.setSurfaceView(r75);
        goto L21
    L20:
        r4.setTextureView(this.textureView);
    L21:
        this.videoPlayer.seekTo(r1 + 50);
        if (r02 == false) goto L26;
        if (r8 == true) goto L26;
        this.videoPlayer.setPlayWhenReady(true);
        this.videoPlayer.play();
        return;
    L26:
        this.videoPlayer.setPlayWhenReady(false);
        this.videoPlayer.pause();
    }

    private /* synthetic */ void lambda$setSpeed$5(float r1) {
        VideoPlayer r0 = this.videoPlayer;
        if (r0 == null) goto L6;
        r0.setPlaybackSpeed(r1);
        return;
    }

    private /* synthetic */ void lambda$setVolume$10(float r1) {
        VideoPlayer r0 = this.videoPlayer;
        if (r0 == null) goto L6;
        r0.setVolume(r1);
        return;
    }

    private /* synthetic */ void lambda$start$1() {
        this.initRunnable = null;
    }

    private /* synthetic */ void lambda$start$2(boolean r3, float r4, Uri r5, boolean r6, boolean r7, long r8) {
        if (this.released == false) goto L7;
        FileLog.d("videoplayerholderbase returned from start: released");
        return;
    L7:
        if (this.videoPlayer != null) goto L27;
        ensurePlayerCreated(r3);
        this.videoPlayer.setPlaybackSpeed(r4);
        FileLog.d("videoplayerholderbase.start(): preparePlayer new player uri=" + r5);
        this.videoPlayer.preparePlayer(r5, "other");
        this.videoPlayer.setWorkerQueue(this.dispatchQueue);
        if (r6 == true) goto L18;
        Surface r32 = this.surface;
        if (r32 == null) goto L13;
        this.videoPlayer.setSurface(r32);
    L17:
        this.videoPlayer.setPlayWhenReady(true);
    L47:
        if (r8 <= 0) goto L49;
        this.videoPlayer.seekTo(r8);
    L49:
        AndroidUtilities.runOnUIThread(new VideoPlayerHolderBase$$ExternalSyntheticLambda12(this));
        return;
    L13:
        SurfaceView r33 = this.surfaceView;
        VideoPlayer r42 = this.videoPlayer;
        if (r33 == null) goto L16;
        r42.setSurfaceView(r33);
        goto L17
    L16:
        r42.setTextureView(this.textureView);
        goto L17
    L18:
        if (r7 == false) goto L47;
        Surface r34 = this.surface;
        if (r34 == null) goto L22;
        this.videoPlayer.setSurface(r34);
    L26:
        this.videoPlayer.setPlayWhenReady(false);
        goto L47
    L22:
        SurfaceView r35 = this.surfaceView;
        VideoPlayer r43 = this.videoPlayer;
        if (r35 == null) goto L25;
        r43.setSurfaceView(r35);
        goto L26
    L25:
        r43.setTextureView(this.textureView);
        goto L26
    L27:
        FileLog.d("videoplayerholderbase.start(): player already exist");
        if (r6 == true) goto L37;
        Surface r36 = this.surface;
        if (r36 == null) goto L32;
        this.videoPlayer.setSurface(r36);
    L36:
        this.videoPlayer.play();
        goto L47
    L32:
        SurfaceView r37 = this.surfaceView;
        VideoPlayer r44 = this.videoPlayer;
        if (r37 == null) goto L35;
        r44.setSurfaceView(r37);
        goto L36
    L35:
        r44.setTextureView(this.textureView);
        goto L36
    L37:
        if (r7 == false) goto L47;
        Surface r38 = this.surface;
        if (r38 == null) goto L41;
        this.videoPlayer.setSurface(r38);
    L45:
        this.videoPlayer.setPlayWhenReady(false);
        goto L47
    L41:
        SurfaceView r39 = this.surfaceView;
        VideoPlayer r45 = this.videoPlayer;
        if (r39 == null) goto L44;
        r45.setSurfaceView(r39);
        goto L45
    L44:
        r45.setTextureView(this.textureView);
        goto L45
    }

    public void allowMultipleInstances(boolean r1) {
        this.allowMultipleInstances = r1;
    }

    public long getCurrentPosition() {
        return this.currentPosition;
    }

    public Uri getCurrentUri() {
        return this.contentUri;
    }

    public long getDuration() {
        return this.playerDuration;
    }

    public float getPlaybackProgress(long r4) {
        if (this.lastState != 4) goto L5;
        this.progress = 1.0f;
    L13:
        return this.progress;
    L5:
        long r1 = this.currentPosition;
        if (r4 == 0) goto L8;
        float r0 = r1 / r4;
    L9:
        this.progress = r0;
        if (this.seeking == true) goto L13;
        this.currentSeek = this.progress;
        this.lastSeek = this.currentPosition;
        goto L13
    L8:
        r0 = r1 / this.playerDuration;
        goto L9
    }

    public boolean isBuffering() {
        if (this.released == false) goto L5;
        return false;
    L5:
        if (this.lastState != 2) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isPlaying() {
        return !this.paused;
    }

    public void loopBack() {
        this.progress = 0.0f;
        this.lastState = 1;
        this.dispatchQueue.postRunnable(new VideoPlayerHolderBase$$ExternalSyntheticLambda7(this));
    }

    public boolean needRepeat() {
        return false;
    }

    public void onAudioTrackChanged(int r1) {
    }

    public void onRenderedFirstFrame() {
    }

    public void onStateChanged(boolean r1, int r2) {
    }

    public void onVideoSizeChanged(int r1, int r2, int r3, float r4) {
    }

    public void pause() {
        if (this.released == false) goto L6;
        return;
    L6:
        if (this.paused == false) goto L8;
        return;
    L8:
        this.paused = true;
        prepareStub();
        this.dispatchQueue.postRunnable(new VideoPlayerHolderBase$$ExternalSyntheticLambda4(this));
    }

    public void play() {
        if (this.released == false) goto L6;
        return;
    L6:
        if (this.paused == true) goto L8;
        return;
    L8:
        this.paused = false;
        this.dispatchQueue.postRunnable(new VideoPlayerHolderBase$$ExternalSyntheticLambda3(this));
    }

    public void play(float r3) {
        if (this.released == false) goto L6;
        return;
    L6:
        if (this.paused == true) goto L8;
        return;
    L8:
        this.paused = false;
        this.dispatchQueue.postRunnable(new VideoPlayerHolderBase$$ExternalSyntheticLambda14(this, r3));
    }

    public void preparePlayer(Uri r3, boolean r4, float r5) {
        this.audioDisabled = r4;
        this.currentAccount = this.currentAccount;
        this.contentUri = r3;
        this.paused = true;
        Runnable r0 = this.initRunnable;
        if (r0 == null) goto L5;
        this.dispatchQueue.cancelRunnable(r0);
    L5:
        DispatchQueue r02 = this.dispatchQueue;
        VideoPlayerHolderBase$$ExternalSyntheticLambda13 r1 = new VideoPlayerHolderBase$$ExternalSyntheticLambda13(this, r4, r5, r3);
        this.initRunnable = r1;
        r02.postRunnable(r1);
    }

    public void prepareStub() {
        SurfaceView r0 = this.surfaceView;
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.firstFrameRendered == true) goto L7;
        return;
    L7:
        if (r0.getHolder().getSurface().isValid() == false) goto L17;
        this.stubAvailable = true;
        if (this.playerStubBitmap != null) goto L11;
        this.playerStubBitmap = Bitmap.createBitmap(720, 1280, Bitmap.Config.ARGB_8888);
        this.playerStubPaint = new Paint(1);
    L11:
        AndroidUtilities.getBitmapFromSurface(this.surfaceView, this.playerStubBitmap);
        if (this.playerStubBitmap.getPixel(0, 0) != 0) goto L18;
        this.stubAvailable = false;
        return;
    L18:
        return;
    }

    public boolean release(Runnable r9) {
        TLRPC.Document r2 = this.document;
        if (r2 != null) goto L5;
    L7:
        this.released = true;
        this.dispatchQueue.cancelRunnable(this.initRunnable);
        this.dispatchQueue.cancelRunnable(this.progressRunnable);
        this.initRunnable = null;
        this.dispatchQueue.postRunnable(new VideoPlayerHolderBase$$ExternalSyntheticLambda8(this, r2, r9));
        Bitmap r92 = this.playerStubBitmap;
        if (r92 == null) goto L10;
        AndroidUtilities.recycleBitmap(r92);
        this.playerStubBitmap = null;
    L10:
        return true;
    L5:
        if (FileStreamLoadOperation.getStreamPrioriy(r2) == 0) goto L7;
        FileStreamLoadOperation.setPriorityForDocument(r2, 0);
        FileLoader.getInstance(this.currentAccount).changePriority(0, r2, null, null, null, null, null);
        goto L7
    }

    public float seek(float r2, long r3) {
        if (this.videoPlayer == null) goto L5;
        this.duration = r3;
        float r22 = Utilities.clamp(this.currentSeek + r2, 1.0f, 0.0f);
        this.currentSeek = r22;
        this.currentSeekThread = r22;
        this.dispatchQueue.cancelRunnable(this.updateSeek);
        this.dispatchQueue.postRunnable(this.updateSeek);
        return this.currentSeek;
    L5:
        return this.currentSeek;
    }

    public void seekTo(long r3) {
        this.dispatchQueue.postRunnable(new VideoPlayerHolderBase$$ExternalSyntheticLambda6(this, r3));
    }

    public void seekTo(long r8, boolean r10, Runnable r11) {
        this.dispatchQueue.postRunnable(new VideoPlayerHolderBase$$ExternalSyntheticLambda9(this, r8, r10, r11));
    }

    public void setAudioEnabled(boolean r3, boolean r4) {
        boolean r0 = !r3;
        if (this.audioDisabled != r0) goto L5;
        return;
    L5:
        this.audioDisabled = r0;
        this.triesCount = 3;
        this.dispatchQueue.postRunnable(new VideoPlayerHolderBase$$ExternalSyntheticLambda10(this, r3, r4));
    }

    public void setOnErrorListener(Runnable r1) {
        this.onErrorListener = r1;
    }

    public void setOnReadyListener(Runnable r1) {
        this.onReadyListener = r1;
    }

    public void setOnSeekUpdate(Runnable r1) {
        this.onSeekUpdate = r1;
    }

    public void setSeeking(boolean r2) {
        if (r2 == true) goto L4;
    L6:
        this.seeking = r2;
        if (r2 == true) goto L10;
        this.dispatchQueue.cancelRunnable(this.betterSeek);
        return;
    L10:
        return;
    L4:
        if (this.seeking == true) goto L6;
        this.firstSeek = true;
        goto L6
    }

    public void setSpeed(float r3) {
        if (this.released == false) goto L5;
        return;
    L5:
        this.dispatchQueue.postRunnable(new VideoPlayerHolderBase$$ExternalSyntheticLambda11(this, r3));
    }

    public void setVolume(float r3) {
        this.dispatchQueue.postRunnable(new VideoPlayerHolderBase$$ExternalSyntheticLambda5(this, r3));
    }

    public void start(boolean r11, boolean r12, Uri r13, long r14, boolean r16, float r17) {
        this.startTime = System.currentTimeMillis();
        this.audioDisabled = r16;
        this.paused = r12;
        this.triesCount = 3;
        if (r14 <= 0) goto L5;
        this.currentPosition = r14;
    L5:
        DispatchQueue r9 = this.dispatchQueue;
        VideoPlayerHolderBase$$ExternalSyntheticLambda2 r0 = new VideoPlayerHolderBase$$ExternalSyntheticLambda2(this, r16, r17, r13, r12, r11, r14);
        this.initRunnable = r0;
        r9.postRunnable(r0);
    }

    public VideoPlayerHolderBase with(Surface r2) {
        this.surfaceView = null;
        this.textureView = null;
        this.surface = r2;
        return this;
    }

    public VideoPlayerHolderBase with(SurfaceView r1) {
        this.surfaceView = r1;
        this.textureView = null;
        this.surface = null;
        return this;
    }

    public VideoPlayerHolderBase with(TextureView r2) {
        this.surfaceView = null;
        this.textureView = r2;
        this.surface = null;
        return this;
    }
}
