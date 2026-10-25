package androidx.media3.exoplayer.source;

import android.os.Handler;
import android.os.Looper;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.Assertions;
import androidx.media3.datasource.TransferListener;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.drm.DrmSessionEventListener;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.MediaSourceEventListener;
import java.util.ArrayList;
import java.util.HashSet;

/* loaded from: classes4.dex */
public abstract class BaseMediaSource implements MediaSource {
    private final DrmSessionEventListener.EventDispatcher drmEventDispatcher;
    private final HashSet<MediaSource.MediaSourceCaller> enabledMediaSourceCallers;
    private final MediaSourceEventListener.EventDispatcher eventDispatcher;
    private Looper looper;
    private final ArrayList<MediaSource.MediaSourceCaller> mediaSourceCallers;
    private PlayerId playerId;
    private Timeline timeline;

    public BaseMediaSource() {
        this.mediaSourceCallers = new ArrayList(1);
        this.enabledMediaSourceCallers = new HashSet(1);
        this.eventDispatcher = new MediaSourceEventListener.EventDispatcher();
        this.drmEventDispatcher = new DrmSessionEventListener.EventDispatcher();
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public final void addDrmEventListener(Handler r1, DrmSessionEventListener r2) {
        Assertions.checkNotNull(r1);
        Assertions.checkNotNull(r2);
        this.drmEventDispatcher.addEventListener(r1, r2);
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public final void addEventListener(Handler r1, MediaSourceEventListener r2) {
        Assertions.checkNotNull(r1);
        Assertions.checkNotNull(r2);
        this.eventDispatcher.addEventListener(r1, r2);
    }

    public final DrmSessionEventListener.EventDispatcher createDrmEventDispatcher(int r1, MediaSource.MediaPeriodId r2) {
        return this.drmEventDispatcher.withParameters(r1, r2);
    }

    public final DrmSessionEventListener.EventDispatcher createDrmEventDispatcher(MediaSource.MediaPeriodId r2) {
        return this.drmEventDispatcher.withParameters(0, r2);
    }

    public final MediaSourceEventListener.EventDispatcher createEventDispatcher(int r1, MediaSource.MediaPeriodId r2) {
        return this.eventDispatcher.withParameters(r1, r2);
    }

    public final MediaSourceEventListener.EventDispatcher createEventDispatcher(MediaSource.MediaPeriodId r2) {
        return this.eventDispatcher.withParameters(0, r2);
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public final void disable(MediaSource.MediaSourceCaller r3) {
        boolean r0 = this.enabledMediaSourceCallers.isEmpty();
        this.enabledMediaSourceCallers.remove(r3);
        if (r0 == false) goto L5;
        return;
    L5:
        if (this.enabledMediaSourceCallers.isEmpty() == false) goto L9;
        disableInternal();
        return;
    }

    public void disableInternal() {
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public final void enable(MediaSource.MediaSourceCaller r3) {
        Assertions.checkNotNull(this.looper);
        boolean r0 = this.enabledMediaSourceCallers.isEmpty();
        this.enabledMediaSourceCallers.add(r3);
        if (r0 == false) goto L6;
        enableInternal();
        return;
    }

    public void enableInternal() {
    }

    public final PlayerId getPlayerId() {
        return (PlayerId) Assertions.checkStateNotNull(this.playerId);
    }

    public final boolean isEnabled() {
        return !this.enabledMediaSourceCallers.isEmpty();
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public final void prepareSource(MediaSource.MediaSourceCaller r3, TransferListener r4, PlayerId r5) {
        Looper r0 = Looper.myLooper();
        Looper r1 = this.looper;
        if (r1 == null) goto L7;
        if (r1 == r0) goto L7;
        boolean r12 = false;
    L8:
        Assertions.checkArgument(r12);
        this.playerId = r5;
        Timeline r52 = this.timeline;
        this.mediaSourceCallers.add(r3);
        if (this.looper != null) goto L12;
        this.looper = r0;
        this.enabledMediaSourceCallers.add(r3);
        prepareSourceInternal(r4);
        return;
    L12:
        if (r52 == null) goto L15;
        enable(r3);
        r3.onSourceInfoRefreshed(this, r52);
        return;
    L15:
        return;
    L7:
        r12 = true;
        goto L8
    }

    public abstract void prepareSourceInternal(TransferListener r1);

    public final void refreshSourceInfo(Timeline r5) {
        this.timeline = r5;
        ArrayList<MediaSource.MediaSourceCaller> r0 = this.mediaSourceCallers;
        int r1 = r0.size();
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L5;
        MediaSource.MediaSourceCaller r3 = r0.get(r2);
        r2 = r2 + 1;
        r3.onSourceInfoRefreshed(this, r5);
        goto L3
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public final void releaseSource(MediaSource.MediaSourceCaller r2) {
        this.mediaSourceCallers.remove(r2);
        if (this.mediaSourceCallers.isEmpty() == false) goto L6;
        this.looper = null;
        this.timeline = null;
        this.playerId = null;
        this.enabledMediaSourceCallers.clear();
        releaseSourceInternal();
        return;
    L6:
        disable(r2);
    }

    public abstract void releaseSourceInternal();

    @Override // androidx.media3.exoplayer.source.MediaSource
    public final void removeDrmEventListener(DrmSessionEventListener r1) {
        this.drmEventDispatcher.removeEventListener(r1);
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public final void removeEventListener(MediaSourceEventListener r1) {
        this.eventDispatcher.removeEventListener(r1);
    }
}
