package androidx.media3.exoplayer;

import android.os.HandlerThread;
import android.os.Looper;
import androidx.media3.common.util.Assertions;

/* loaded from: classes4.dex */
public final class PlaybackLooperProvider {
    private HandlerThread internalPlaybackThread;
    private final Object lock;
    private Looper playbackLooper;
    private int referenceCount;

    public PlaybackLooperProvider() {
        this(null);
    }

    public PlaybackLooperProvider(Looper r2) {
        this.lock = new Object();
        this.playbackLooper = r2;
        this.internalPlaybackThread = null;
        this.referenceCount = 0;
    }

    public Looper obtainLooper() {
        Object r0 = this.lock;
        monitor-enter(r0);
    L11:
        th = move-exception;
        throw th;
    L5:
        if (this.playbackLooper == null) goto L7;
    L15:
        this.referenceCount++;
        Looper r5 = this.playbackLooper;     // Catch: Throwable -> L11
        monitor-exit(r0);     // Catch: Throwable -> L11
        return r5;
    L7:
        if (this.referenceCount == 0) goto L9;
    L13:
        boolean r1 = false;
    L14:
        Assertions.checkState(r1);     // Catch: Throwable -> L11
        HandlerThread r12 = new HandlerThread("ExoPlayer:Playback", -16);     // Catch: Throwable -> L11
        this.internalPlaybackThread = r12;     // Catch: Throwable -> L11
        r12.start();     // Catch: Throwable -> L11
        this.playbackLooper = this.internalPlaybackThread.getLooper();     // Catch: Throwable -> L11
        goto L15
    L9:
        if (this.internalPlaybackThread != null) goto L13;
        r1 = true;
        goto L14
    }

    public void releaseLooper() {
        Object r0 = this.lock;
        monitor-enter(r0);
    L13:
        th = move-exception;
        throw th;
    L5:
        if (this.referenceCount <= 0) goto L7;
        boolean r1 = true;
    L8:
        Assertions.checkState(r1);     // Catch: Throwable -> L13
        int r12 = this.referenceCount - 1;     // Catch: Throwable -> L13
        this.referenceCount = r12;     // Catch: Throwable -> L13
        if (r12 != 0) goto L15;
        HandlerThread r13 = this.internalPlaybackThread;     // Catch: Throwable -> L13
        if (r13 == null) goto L15;
        r13.quit();     // Catch: Throwable -> L13
        this.internalPlaybackThread = null;     // Catch: Throwable -> L13
        this.playbackLooper = null;     // Catch: Throwable -> L13
    L15:
        monitor-exit(r0);     // Catch: Throwable -> L13
        return;
    L7:
        r1 = false;
        goto L8
    }
}
