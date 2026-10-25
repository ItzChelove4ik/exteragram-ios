package org.telegram.messenger.video;

import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Utilities;
import org.telegram.ui.Components.PhotoViewerWebView;
import org.telegram.ui.Components.SeekSpeedDrawable;
import org.telegram.ui.Components.VideoPlayer;

/* loaded from: classes6.dex */
public class VideoPlayerRewinder {
    private final Runnable backSeek;
    private boolean fastSeeking;
    private VideoFramesRewinder framesRewinder;
    private float playSpeed;
    private long rewindBackSeekLastPlayerPosition;
    private long rewindBackSeekPlayerPosition;
    public boolean rewindByBackSeek;
    public int rewindCount;
    private boolean rewindForward;
    private long rewindLastTime;
    private long rewindLastUpdatePlayerTime;
    public boolean rewinding;
    private SeekSpeedDrawable seekSpeedDrawable;
    private long startRewindFrom;
    private Runnable updateRewindRunnable;
    private float value;
    private VideoPlayer videoPlayer;
    private boolean wasMuted;
    private boolean wasPaused;
    private PhotoViewerWebView webView;
    private float x;

    /* renamed from: org.telegram.messenger.video.VideoPlayerRewinder$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ VideoPlayerRewinder this$0;

        public AnonymousClass1(VideoPlayerRewinder r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (VideoPlayerRewinder.m5258$$Nest$fgetvideoPlayer(this.this$0) == null) goto L5;
        L7:
            long r3 = VideoPlayerRewinder.m5264$$Nest$mgetDuration(this.this$0);
            if (r3 != 0) goto L10;
        L36:
            VideoPlayerRewinder.m5261$$Nest$fputrewindLastTime(this.this$0, System.currentTimeMillis());
            return;
        L10:
            if (r3 == (-9223372036854775807L)) goto L36;
            long r0 = System.currentTimeMillis();
            long r5 = r0 - VideoPlayerRewinder.m5255$$Nest$fgetrewindLastTime(this.this$0);
            VideoPlayerRewinder.m5261$$Nest$fputrewindLastTime(this.this$0, r0);
            float r02 = Math.max(0.0f, (-this.this$0.getRewindSpeed()) * VideoPlayerRewinder.m5253$$Nest$fgetplaySpeed(this.this$0));
            long r1 = (long) (r5 * r02);
            VideoPlayerRewinder r52 = this.this$0;
            VideoPlayerRewinder.m5260$$Nest$fputrewindBackSeekPlayerPosition(r52, VideoPlayerRewinder.m5254$$Nest$fgetrewindBackSeekPlayerPosition(r52) - r1);
            VideoPlayerRewinder r10 = this.this$0;
            VideoPlayerRewinder.m5260$$Nest$fputrewindBackSeekPlayerPosition(r10, Utilities.clamp(VideoPlayerRewinder.m5254$$Nest$fgetrewindBackSeekPlayerPosition(r10), r3, 0));
            VideoPlayerRewinder r12 = this.this$0;
            if (r12.rewindByBackSeek == true) goto L15;
        L22:
            VideoPlayerRewinder r6 = this.this$0;
            r6.updateRewindProgressUi(VideoPlayerRewinder.m5254$$Nest$fgetrewindBackSeekPlayerPosition(this.this$0) - VideoPlayerRewinder.m5257$$Nest$fgetstartRewindFrom(this.this$0), VideoPlayerRewinder.m5254$$Nest$fgetrewindBackSeekPlayerPosition(this.this$0) / VideoPlayerRewinder.m5264$$Nest$mgetDuration(this.this$0), r6.rewindByBackSeek);
            if (VideoPlayerRewinder.m5254$$Nest$fgetrewindBackSeekPlayerPosition(this.this$0) != 0) goto L25;
        L26:
            VideoPlayerRewinder r03 = this.this$0;
            if (r03.rewindByBackSeek == false) goto L29;
            VideoPlayerRewinder.m5262$$Nest$fputrewindLastUpdatePlayerTime(r03, VideoPlayerRewinder.m5255$$Nest$fgetrewindLastTime(r03));
            VideoPlayerRewinder r04 = this.this$0;
            VideoPlayerRewinder.m5265$$Nest$mseekTo(r04, VideoPlayerRewinder.m5254$$Nest$fgetrewindBackSeekPlayerPosition(r04), false);
        L29:
            this.this$0.cancelRewind();
        L30:
            VideoPlayerRewinder r05 = this.this$0;
            if (r05.rewinding == true) goto L33;
            return;
        L33:
            if (r05.getRewindSpeed() >= 0.0f) goto L39;
            AndroidUtilities.runOnUIThread(VideoPlayerRewinder.m5251$$Nest$fgetbackSeek(this.this$0), 16);
            return;
        L39:
            return;
        L25:
            if (VideoPlayerRewinder.m5254$$Nest$fgetrewindBackSeekPlayerPosition(this.this$0) < r3) goto L30;
        L15:
            if (VideoPlayerRewinder.m5263$$Nest$mgetCurrentPosition(r12) <= VideoPlayerRewinder.m5254$$Nest$fgetrewindBackSeekPlayerPosition(this.this$0)) goto L22;
            if ((VideoPlayerRewinder.m5255$$Nest$fgetrewindLastTime(this.this$0) - VideoPlayerRewinder.m5256$$Nest$fgetrewindLastUpdatePlayerTime(this.this$0)) <= 10) goto L22;
            VideoPlayerRewinder r13 = this.this$0;
            VideoPlayerRewinder.m5262$$Nest$fputrewindLastUpdatePlayerTime(r13, VideoPlayerRewinder.m5255$$Nest$fgetrewindLastTime(r13));
            VideoFramesRewinder r14 = VideoPlayerRewinder.m5252$$Nest$fgetframesRewinder(this.this$0);
            VideoPlayerRewinder r2 = this.this$0;
            if (r14 == null) goto L21;
            VideoPlayerRewinder.m5252$$Nest$fgetframesRewinder(r2).seek(VideoPlayerRewinder.m5254$$Nest$fgetrewindBackSeekPlayerPosition(this.this$0), Math.abs(r02));
            goto L22
        L21:
            VideoPlayerRewinder.m5265$$Nest$mseekTo(r2, VideoPlayerRewinder.m5254$$Nest$fgetrewindBackSeekPlayerPosition(r2), false);
            goto L22
        L5:
            if (VideoPlayerRewinder.m5259$$Nest$fgetwebView(this.this$0) != null) goto L7;
        }
    }

    public static /* synthetic */ void $r8$lambda$KCO90ALURlTf5ap0g318lD7GZD0(VideoPlayerRewinder r0) {
        r0.lambda$updateRewindSpeed$0();
    }

    /* renamed from: $r8$lambda$kK3FTs-xIxLCUbelZxLVjGHtzUs, reason: not valid java name */
    public static /* synthetic */ void m5250$r8$lambda$kK3FTsxIxLCUbelZxLVjGHtzUs(VideoPlayerRewinder r0) {
        r0.lambda$cancelRewind$1();
    }

    /* renamed from: -$$Nest$fgetbackSeek, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m5251$$Nest$fgetbackSeek(VideoPlayerRewinder r0) {
        return r0.backSeek;
    }

    /* renamed from: -$$Nest$fgetframesRewinder, reason: not valid java name */
    public static /* bridge */ /* synthetic */ VideoFramesRewinder m5252$$Nest$fgetframesRewinder(VideoPlayerRewinder r0) {
        return r0.framesRewinder;
    }

    /* renamed from: -$$Nest$fgetplaySpeed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m5253$$Nest$fgetplaySpeed(VideoPlayerRewinder r0) {
        return r0.playSpeed;
    }

    /* renamed from: -$$Nest$fgetrewindBackSeekPlayerPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m5254$$Nest$fgetrewindBackSeekPlayerPosition(VideoPlayerRewinder r2) {
        return r2.rewindBackSeekPlayerPosition;
    }

    /* renamed from: -$$Nest$fgetrewindLastTime, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m5255$$Nest$fgetrewindLastTime(VideoPlayerRewinder r2) {
        return r2.rewindLastTime;
    }

    /* renamed from: -$$Nest$fgetrewindLastUpdatePlayerTime, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m5256$$Nest$fgetrewindLastUpdatePlayerTime(VideoPlayerRewinder r2) {
        return r2.rewindLastUpdatePlayerTime;
    }

    /* renamed from: -$$Nest$fgetstartRewindFrom, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m5257$$Nest$fgetstartRewindFrom(VideoPlayerRewinder r2) {
        return r2.startRewindFrom;
    }

    /* renamed from: -$$Nest$fgetvideoPlayer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ VideoPlayer m5258$$Nest$fgetvideoPlayer(VideoPlayerRewinder r0) {
        return r0.videoPlayer;
    }

    /* renamed from: -$$Nest$fgetwebView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ PhotoViewerWebView m5259$$Nest$fgetwebView(VideoPlayerRewinder r0) {
        return r0.webView;
    }

    /* renamed from: -$$Nest$fputrewindBackSeekPlayerPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5260$$Nest$fputrewindBackSeekPlayerPosition(VideoPlayerRewinder r0, long r1) {
        r0.rewindBackSeekPlayerPosition = r1;
    }

    /* renamed from: -$$Nest$fputrewindLastTime, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5261$$Nest$fputrewindLastTime(VideoPlayerRewinder r0, long r1) {
        r0.rewindLastTime = r1;
    }

    /* renamed from: -$$Nest$fputrewindLastUpdatePlayerTime, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5262$$Nest$fputrewindLastUpdatePlayerTime(VideoPlayerRewinder r0, long r1) {
        r0.rewindLastUpdatePlayerTime = r1;
    }

    /* renamed from: -$$Nest$mgetCurrentPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m5263$$Nest$mgetCurrentPosition(VideoPlayerRewinder r2) {
        return r2.getCurrentPosition();
    }

    /* renamed from: -$$Nest$mgetDuration, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m5264$$Nest$mgetDuration(VideoPlayerRewinder r2) {
        return r2.getDuration();
    }

    /* renamed from: -$$Nest$mseekTo, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5265$$Nest$mseekTo(VideoPlayerRewinder r0, long r1, boolean r3) {
        r0.seekTo(r1, r3);
    }

    public VideoPlayerRewinder(VideoFramesRewinder r3) {
        this.rewindBackSeekPlayerPosition = -1;
        this.playSpeed = 1.0f;
        this.backSeek = new AnonymousClass1(this);
        this.framesRewinder = r3;
    }

    private long getCurrentPosition() {
        if (this.webView != null) goto L5;
        VideoPlayer r2 = this.videoPlayer;
        if (r2 != null) goto L11;
        return 0;
    L11:
        return r2.getCurrentPosition();
    L5:
        return r0.getCurrentPosition();
    }

    private long getDuration() {
        if (this.webView != null) goto L5;
        VideoPlayer r2 = this.videoPlayer;
        if (r2 != null) goto L11;
        return 0;
    L11:
        return r2.getDuration();
    L5:
        return r0.getVideoDuration();
    }

    private boolean isPlaying() {
        PhotoViewerWebView r0 = this.webView;
        if (r0 != null) goto L5;
        VideoPlayer r1 = this.videoPlayer;
        if (r1 != null) goto L11;
        return false;
    L11:
        return r1.isPlaying();
    L5:
        return r0.isPlaying();
    }

    private /* synthetic */ void lambda$cancelRewind$1() {
        VideoFramesRewinder r0 = this.framesRewinder;
        if (r0 == null) goto L6;
        r0.release();
        return;
    }

    private /* synthetic */ void lambda$updateRewindSpeed$0() {
        VideoFramesRewinder r0 = this.framesRewinder;
        if (r0 == null) goto L6;
        r0.clearCurrent();
        return;
    }

    private void seekTo(long r2, boolean r4) {
        PhotoViewerWebView r0 = this.webView;
        if (r0 == null) goto L5;
        r0.seekTo(r2);
    L8:
        this.rewindBackSeekLastPlayerPosition = r2;
        return;
    L5:
        VideoPlayer r02 = this.videoPlayer;
        if (r02 == null) goto L8;
        r02.seekTo(r2, r4);
        goto L8
    }

    private void setMuted(boolean r1) {
        VideoPlayer r0 = this.videoPlayer;
        if (r0 == null) goto L6;
        r0.setMute(r1);
        return;
    }

    private void setPaused(boolean r2) {
        PhotoViewerWebView r0 = this.webView;
        if (r0 == null) goto L9;
        if (r2 == false) goto L7;
        r0.pauseVideo();
        return;
    L7:
        r0.playVideo();
        return;
    L9:
        VideoPlayer r1 = this.videoPlayer;
        if (r1 == null) goto L16;
        if (r2 == false) goto L14;
        r1.pause();
        return;
    L14:
        r1.play();
        return;
    }

    private void setPlaybackSpeed(float r2) {
        PhotoViewerWebView r0 = this.webView;
        if (r0 == null) goto L6;
        r0.setPlaybackSpeed(r2);
        return;
    L6:
        VideoPlayer r1 = this.videoPlayer;
        if (r1 != null) goto L9;
        return;
    L9:
        r1.setPlaybackSpeed(r2);
    }

    public void cancelRewind() {
        if (this.rewinding == false) goto L33;
        this.rewinding = false;
        this.fastSeeking = false;
        VideoPlayer r1 = this.videoPlayer;
        if (r1 != null) goto L12;
        if (this.webView != null) goto L12;
        boolean r12 = false;
    L21:
        setMuted(this.wasMuted);
        setPaused(this.wasPaused);
        AndroidUtilities.cancelRunOnUIThread(this.backSeek);
        VideoFramesRewinder r3 = this.framesRewinder;
        if (r3 == null) goto L25;
        if (r12 == true) goto L25;
        r3.release();
    L25:
        Runnable r13 = this.updateRewindRunnable;
        if (r13 == null) goto L28;
        AndroidUtilities.cancelRunOnUIThread(r13);
        this.updateRewindRunnable = null;
    L28:
        onRewindCanceled();
        SeekSpeedDrawable r6 = this.seekSpeedDrawable;
        if (r6 == null) goto L32;
        r6.setShown(false, true);
        return;
    L32:
        return;
    L12:
        if (this.rewindByBackSeek == false) goto L18;
        if (r1 != null) goto L15;
    L17:
        seekTo(this.rewindBackSeekPlayerPosition, false);
    L19:
        r12 = false;
    L20:
        setPlaybackSpeed(this.playSpeed);
        goto L21
    L15:
        if (this.framesRewinder == null) goto L17;
        r1.seekTo(this.rewindBackSeekPlayerPosition, false, new VideoPlayerRewinder$$ExternalSyntheticLambda1(this));
        r12 = true;
        goto L20
    L18:
        seekTo(getCurrentPosition(), false);
        goto L19
    }

    public float getRewindSpeed() {
        float r2 = this.value;
        if (r2 >= 0.4f) goto L6;
        r2 = r2 - 1.9f;
    L6:
        return Utilities.clamp(r2, 10.0f, -6.0f);
    }

    public float getValueBySpeed(float r1) {
        if (r1 < (-1.5f)) goto L5;
        return r1;
    L5:
        return r1 + 1.9f;
    }

    public float getVideoProgress() {
        return this.rewindBackSeekPlayerPosition / getDuration();
    }

    public void onRewindCanceled() {
    }

    public void onRewindStart(boolean r1) {
    }

    public void setX(float r4) {
        this.value -= (this.x - r4) / AndroidUtilities.dp(40.0f);
        this.x = r4;
        SeekSpeedDrawable r42 = this.seekSpeedDrawable;
        if (r42 == null) goto L5;
        r42.setSpeed(getRewindSpeed(), true);
    L5:
        updateRewindSpeed();
    }

    public void startRewind(PhotoViewerWebView r4, boolean r5, float r6, float r7, SeekSpeedDrawable r8) {
        cancelRewind();
        this.videoPlayer = null;
        this.webView = null;
        VideoFramesRewinder r0 = this.framesRewinder;
        if (r0 == null) goto L5;
        r0.release();
    L5:
        this.rewindByBackSeek = r5;
        this.rewinding = true;
        this.rewindBackSeekPlayerPosition = -1;
        this.webView = r4;
        this.seekSpeedDrawable = r8;
        this.playSpeed = r7;
        this.wasMuted = false;
        if (r4 != null) goto L8;
    L10:
        boolean r42 = false;
    L11:
        this.wasPaused = r42;
        this.fastSeeking = false;
        this.rewindLastUpdatePlayerTime = 0;
        this.x = r6;
        if (r5 == false) goto L15;
        float r43 = 2.0f;
    L16:
        this.value = getValueBySpeed(r43);
        this.rewindBackSeekLastPlayerPosition = -100;
        if (r8 == null) goto L20;
        r8.setSpeed(getRewindSpeed(), false);
        r8.setShown(true, true);
        return;
    L20:
        return;
    L15:
        r43 = -2.0f;
        goto L16
    L8:
        if (r4.isPlaying() == true) goto L10;
        r42 = true;
        goto L11
    }

    public void startRewind(VideoPlayer r4, boolean r5, float r6, float r7, SeekSpeedDrawable r8) {
        cancelRewind();
        this.videoPlayer = null;
        this.webView = null;
        VideoFramesRewinder r0 = this.framesRewinder;
        if (r0 == null) goto L5;
        r0.release();
    L5:
        this.rewindByBackSeek = r5;
        this.rewinding = true;
        this.rewindBackSeekPlayerPosition = -1;
        this.videoPlayer = r4;
        this.seekSpeedDrawable = r8;
        this.playSpeed = r7;
        if (r4 != null) goto L8;
    L10:
        boolean r1 = false;
    L11:
        this.wasMuted = r1;
        if (r4 != null) goto L14;
    L16:
        boolean r42 = false;
    L17:
        this.wasPaused = r42;
        this.fastSeeking = false;
        this.rewindLastUpdatePlayerTime = 0;
        this.x = r6;
        if (r5 == false) goto L21;
        float r43 = 2.0f;
    L22:
        this.value = getValueBySpeed(r43);
        this.rewindBackSeekLastPlayerPosition = -100;
        if (r8 == null) goto L25;
        r8.setSpeed(getRewindSpeed(), false);
        r8.setShown(true, true);
    L25:
        updateRewindSpeed();
        return;
    L21:
        r43 = -2.0f;
        goto L22
    L14:
        if (r4.isPlaying() == true) goto L16;
        r42 = true;
        goto L17
    L8:
        if (r4.isMuted() == false) goto L10;
        r1 = true;
        goto L11
    }

    public void updateRewindProgressUi(long r1, float r3, boolean r4) {
    }

    public void updateRewindSpeed() {
        float r0 = getRewindSpeed();
        boolean r2 = this.rewindByBackSeek;
        boolean r3 = true;
        if (r0 >= 0.0f) goto L13;
        if (r2 == true) goto L29;
        this.rewindByBackSeek = true;
        this.rewindBackSeekPlayerPosition = getCurrentPosition();
        this.rewindLastTime = System.currentTimeMillis();
        AndroidUtilities.runOnUIThread(this.backSeek);
        setMuted(true);
        setPaused(true);
        setPlaybackSpeed(this.playSpeed);
        VideoFramesRewinder r02 = this.framesRewinder;
        if (r02 != null) goto L8;
        return;
    L8:
        if (r02.isReady() == true) goto L31;
        VideoPlayer r03 = this.videoPlayer;
        if (r03 == null) goto L32;
        this.framesRewinder.setup(r03.getLowestFile());
        return;
    L32:
        return;
    L31:
        return;
    L29:
        return;
    L13:
        if (r2 == false) goto L27;
        this.rewindByBackSeek = false;
        AndroidUtilities.cancelRunOnUIThread(this.backSeek);
        if (this.wasMuted == false) goto L17;
    L20:
        setMuted(r3);
        setPaused(false);
        VideoPlayer r22 = this.videoPlayer;
        if (r22 == null) goto L27;
        if (this.framesRewinder == null) goto L27;
        long r32 = this.rewindBackSeekPlayerPosition;
        if (r32 < 0) goto L27;
        r22.seekTo(r32, false, new VideoPlayerRewinder$$ExternalSyntheticLambda0(this));
        goto L27
    L17:
        if (this.wasPaused == true) goto L20;
        r3 = false;
    L27:
        setPlaybackSpeed(this.playSpeed * r0);
    }
}
