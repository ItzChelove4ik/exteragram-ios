package org.telegram.messenger.video;

import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.Components.PhotoViewerWebView;
import org.telegram.ui.Components.VideoPlayer;

/* loaded from: classes6.dex */
public class OldVideoPlayerRewinder {
    private final Runnable backSeek;
    private float playSpeed;
    private long rewindBackSeekPlayerPosition;
    public boolean rewindByBackSeek;
    public int rewindCount;
    private boolean rewindForward;
    private long rewindLastTime;
    private long rewindLastUpdatePlayerTime;
    private long startRewindFrom;
    private Runnable updateRewindRunnable;
    private VideoPlayer videoPlayer;
    private PhotoViewerWebView webView;

    /* renamed from: org.telegram.messenger.video.OldVideoPlayerRewinder$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ OldVideoPlayerRewinder this$0;

        public AnonymousClass1(OldVideoPlayerRewinder r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (OldVideoPlayerRewinder.m5215$$Nest$fgetvideoPlayer(this.this$0) == null) goto L5;
        L7:
            long r0 = OldVideoPlayerRewinder.m5220$$Nest$mgetDuration(this.this$0);
            if (r0 != 0) goto L10;
        L47:
            OldVideoPlayerRewinder.m5218$$Nest$fputrewindLastTime(this.this$0, System.currentTimeMillis());
            return;
        L10:
            if (r0 == (-9223372036854775807L)) goto L47;
            long r4 = System.currentTimeMillis();
            long r6 = r4 - OldVideoPlayerRewinder.m5212$$Nest$fgetrewindLastTime(this.this$0);
            OldVideoPlayerRewinder.m5218$$Nest$fputrewindLastTime(this.this$0, r4);
            OldVideoPlayerRewinder r42 = this.this$0;
            int r5 = r42.rewindCount;
            if (r5 != 1) goto L17;
            long r8 = 3;
        L15:
            long r62 = r6 * r8;
            boolean r43 = OldVideoPlayerRewinder.m5211$$Nest$fgetrewindForward(r42);
            OldVideoPlayerRewinder r52 = this.this$0;
            if (r43 == false) goto L23;
            OldVideoPlayerRewinder.m5217$$Nest$fputrewindBackSeekPlayerPosition(r52, OldVideoPlayerRewinder.m5210$$Nest$fgetrewindBackSeekPlayerPosition(r52) + r62);
        L24:
            long r44 = OldVideoPlayerRewinder.m5210$$Nest$fgetrewindBackSeekPlayerPosition(this.this$0);
            OldVideoPlayerRewinder r53 = this.this$0;
            if (r44 >= 0) goto L28;
            OldVideoPlayerRewinder.m5217$$Nest$fputrewindBackSeekPlayerPosition(r53, 0);
        L30:
            OldVideoPlayerRewinder r45 = this.this$0;
            if (r45.rewindByBackSeek == true) goto L33;
        L35:
            OldVideoPlayerRewinder r7 = this.this$0;
            r7.updateRewindProgressUi(OldVideoPlayerRewinder.m5210$$Nest$fgetrewindBackSeekPlayerPosition(this.this$0) - OldVideoPlayerRewinder.m5214$$Nest$fgetstartRewindFrom(this.this$0), OldVideoPlayerRewinder.m5210$$Nest$fgetrewindBackSeekPlayerPosition(this.this$0) / OldVideoPlayerRewinder.m5220$$Nest$mgetDuration(this.this$0), r7.rewindByBackSeek);
            if (OldVideoPlayerRewinder.m5210$$Nest$fgetrewindBackSeekPlayerPosition(this.this$0) != 0) goto L38;
        L39:
            OldVideoPlayerRewinder r02 = this.this$0;
            if (r02.rewindByBackSeek == false) goto L42;
            OldVideoPlayerRewinder.m5219$$Nest$fputrewindLastUpdatePlayerTime(r02, OldVideoPlayerRewinder.m5212$$Nest$fgetrewindLastTime(r02));
            OldVideoPlayerRewinder r03 = this.this$0;
            OldVideoPlayerRewinder.m5221$$Nest$mseekTo(r03, OldVideoPlayerRewinder.m5210$$Nest$fgetrewindBackSeekPlayerPosition(r03));
        L42:
            this.this$0.cancelRewind();
        L43:
            OldVideoPlayerRewinder r10 = this.this$0;
            if (r10.rewindCount <= 0) goto L49;
            AndroidUtilities.runOnUIThread(OldVideoPlayerRewinder.m5209$$Nest$fgetbackSeek(r10), 16);
            return;
        L49:
            return;
        L38:
            if (OldVideoPlayerRewinder.m5210$$Nest$fgetrewindBackSeekPlayerPosition(this.this$0) < r0) goto L43;
        L33:
            if ((OldVideoPlayerRewinder.m5212$$Nest$fgetrewindLastTime(r45) - OldVideoPlayerRewinder.m5213$$Nest$fgetrewindLastUpdatePlayerTime(this.this$0)) <= 350) goto L35;
            OldVideoPlayerRewinder r46 = this.this$0;
            OldVideoPlayerRewinder.m5219$$Nest$fputrewindLastUpdatePlayerTime(r46, OldVideoPlayerRewinder.m5212$$Nest$fgetrewindLastTime(r46));
            OldVideoPlayerRewinder r47 = this.this$0;
            OldVideoPlayerRewinder.m5221$$Nest$mseekTo(r47, OldVideoPlayerRewinder.m5210$$Nest$fgetrewindBackSeekPlayerPosition(r47));
            goto L35
        L28:
            if (OldVideoPlayerRewinder.m5210$$Nest$fgetrewindBackSeekPlayerPosition(r53) <= r0) goto L30;
            OldVideoPlayerRewinder.m5217$$Nest$fputrewindBackSeekPlayerPosition(this.this$0, r0);
            goto L30
        L23:
            OldVideoPlayerRewinder.m5217$$Nest$fputrewindBackSeekPlayerPosition(r52, OldVideoPlayerRewinder.m5210$$Nest$fgetrewindBackSeekPlayerPosition(r52) - r62);
            goto L24
        L17:
            if (r5 != 2) goto L19;
            r8 = 6;
            goto L15
        L19:
            r8 = 12;
            goto L15
        L5:
            if (OldVideoPlayerRewinder.m5216$$Nest$fgetwebView(this.this$0) != null) goto L7;
        }
    }

    public static /* synthetic */ void $r8$lambda$dkhN9EGGdUZextjXiKFSKKDdrWo(OldVideoPlayerRewinder r0) {
        r0.lambda$incrementRewindCount$0();
    }

    /* renamed from: -$$Nest$fgetbackSeek, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m5209$$Nest$fgetbackSeek(OldVideoPlayerRewinder r0) {
        return r0.backSeek;
    }

    /* renamed from: -$$Nest$fgetrewindBackSeekPlayerPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m5210$$Nest$fgetrewindBackSeekPlayerPosition(OldVideoPlayerRewinder r2) {
        return r2.rewindBackSeekPlayerPosition;
    }

    /* renamed from: -$$Nest$fgetrewindForward, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m5211$$Nest$fgetrewindForward(OldVideoPlayerRewinder r0) {
        return r0.rewindForward;
    }

    /* renamed from: -$$Nest$fgetrewindLastTime, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m5212$$Nest$fgetrewindLastTime(OldVideoPlayerRewinder r2) {
        return r2.rewindLastTime;
    }

    /* renamed from: -$$Nest$fgetrewindLastUpdatePlayerTime, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m5213$$Nest$fgetrewindLastUpdatePlayerTime(OldVideoPlayerRewinder r2) {
        return r2.rewindLastUpdatePlayerTime;
    }

    /* renamed from: -$$Nest$fgetstartRewindFrom, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m5214$$Nest$fgetstartRewindFrom(OldVideoPlayerRewinder r2) {
        return r2.startRewindFrom;
    }

    /* renamed from: -$$Nest$fgetvideoPlayer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ VideoPlayer m5215$$Nest$fgetvideoPlayer(OldVideoPlayerRewinder r0) {
        return r0.videoPlayer;
    }

    /* renamed from: -$$Nest$fgetwebView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ PhotoViewerWebView m5216$$Nest$fgetwebView(OldVideoPlayerRewinder r0) {
        return r0.webView;
    }

    /* renamed from: -$$Nest$fputrewindBackSeekPlayerPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5217$$Nest$fputrewindBackSeekPlayerPosition(OldVideoPlayerRewinder r0, long r1) {
        r0.rewindBackSeekPlayerPosition = r1;
    }

    /* renamed from: -$$Nest$fputrewindLastTime, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5218$$Nest$fputrewindLastTime(OldVideoPlayerRewinder r0, long r1) {
        r0.rewindLastTime = r1;
    }

    /* renamed from: -$$Nest$fputrewindLastUpdatePlayerTime, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5219$$Nest$fputrewindLastUpdatePlayerTime(OldVideoPlayerRewinder r0, long r1) {
        r0.rewindLastUpdatePlayerTime = r1;
    }

    /* renamed from: -$$Nest$mgetDuration, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m5220$$Nest$mgetDuration(OldVideoPlayerRewinder r2) {
        return r2.getDuration();
    }

    /* renamed from: -$$Nest$mseekTo, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5221$$Nest$mseekTo(OldVideoPlayerRewinder r0, long r1) {
        r0.seekTo(r1);
    }

    public OldVideoPlayerRewinder() {
        this.playSpeed = 1.0f;
        this.backSeek = new AnonymousClass1(this);
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

    private void incrementRewindCount() {
        if (this.videoPlayer == null) goto L5;
    L7:
        int r0 = this.rewindCount + 1;
        this.rewindCount = r0;
        boolean r2 = false;
        if (r0 != 1) goto L16;
        if (this.rewindForward == true) goto L12;
    L14:
        this.rewindByBackSeek = true;
        goto L16
    L12:
        if (isPlaying() == false) goto L14;
        this.rewindByBackSeek = false;
    L16:
        if (this.rewindForward == true) goto L18;
    L26:
        int r02 = this.rewindCount;
        if (r02 != 1) goto L28;
    L22:
        r2 = true;
    L31:
        if (this.rewindCount != 1) goto L33;
        this.rewindBackSeekPlayerPosition = getCurrentPosition();
        long r03 = System.currentTimeMillis();
        this.rewindLastTime = r03;
        this.rewindLastUpdatePlayerTime = r03;
        this.startRewindFrom = getCurrentPosition();
        onRewindStart(this.rewindForward);
    L33:
        AndroidUtilities.cancelRunOnUIThread(this.backSeek);
        AndroidUtilities.runOnUIThread(this.backSeek);
        if (r2 == false) goto L40;
        Runnable r04 = this.updateRewindRunnable;
        if (r04 == null) goto L38;
        AndroidUtilities.cancelRunOnUIThread(r04);
    L38:
        OldVideoPlayerRewinder$$ExternalSyntheticLambda0 r05 = new OldVideoPlayerRewinder$$ExternalSyntheticLambda0(this);
        this.updateRewindRunnable = r05;
        AndroidUtilities.runOnUIThread(r05, 2000);
        return;
    L40:
        return;
    L28:
        if (r02 != 2) goto L31;
    L18:
        if (this.rewindByBackSeek == true) goto L26;
        int r06 = this.rewindCount;
        if (r06 != 1) goto L23;
        setPlaybackSpeed(4.0f);
        goto L22
    L23:
        if (r06 != 2) goto L25;
        setPlaybackSpeed(7.0f);
        goto L22
    L25:
        setPlaybackSpeed(13.0f);
        goto L31
    L5:
        if (this.webView != null) goto L7;
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

    private /* synthetic */ void lambda$incrementRewindCount$0() {
        this.updateRewindRunnable = null;
        incrementRewindCount();
    }

    private void seekTo(long r2) {
        PhotoViewerWebView r0 = this.webView;
        if (r0 == null) goto L6;
        r0.seekTo(r2);
        return;
    L6:
        VideoPlayer r1 = this.videoPlayer;
        if (r1 != null) goto L9;
        return;
    L9:
        r1.seekTo(r2);
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
        if (this.rewindCount == 0) goto L13;
        this.rewindCount = 0;
        if (this.videoPlayer != null) goto L9;
        if (this.webView == null) goto L13;
    L9:
        if (this.rewindByBackSeek == false) goto L11;
        seekTo(this.rewindBackSeekPlayerPosition);
    L12:
        setPlaybackSpeed(this.playSpeed);
        goto L13
    L11:
        seekTo(getCurrentPosition());
    L13:
        AndroidUtilities.cancelRunOnUIThread(this.backSeek);
        Runnable r0 = this.updateRewindRunnable;
        if (r0 == null) goto L16;
        AndroidUtilities.cancelRunOnUIThread(r0);
        this.updateRewindRunnable = null;
    L16:
        onRewindCanceled();
    }

    public float getVideoProgress() {
        return this.rewindBackSeekPlayerPosition / getDuration();
    }

    public void onRewindCanceled() {
    }

    public void onRewindStart(boolean r1) {
    }

    public void startRewind(PhotoViewerWebView r1, boolean r2, float r3) {
        this.webView = r1;
        this.playSpeed = r3;
        this.rewindForward = r2;
        cancelRewind();
        incrementRewindCount();
    }

    public void startRewind(VideoPlayer r1, boolean r2, float r3) {
        this.videoPlayer = r1;
        this.playSpeed = r3;
        this.rewindForward = r2;
        cancelRewind();
        incrementRewindCount();
    }

    public void updateRewindProgressUi(long r1, float r3, boolean r4) {
    }
}
