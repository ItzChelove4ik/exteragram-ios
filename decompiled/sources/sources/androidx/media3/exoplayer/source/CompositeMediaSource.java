package androidx.media3.exoplayer.source;

import android.os.Handler;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.TransferListener;
import androidx.media3.exoplayer.drm.DrmSessionEventListener;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.MediaSourceEventListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;

/* loaded from: classes4.dex */
public abstract class CompositeMediaSource<T> extends BaseMediaSource {
    private final HashMap<T, MediaSourceAndListener<T>> childSources;
    private Handler eventHandler;
    private TransferListener mediaTransferListener;

    public final class ForwardingEventListener implements MediaSourceEventListener, DrmSessionEventListener {
        private DrmSessionEventListener.EventDispatcher drmEventDispatcher;
        private final T id;
        private MediaSourceEventListener.EventDispatcher mediaSourceEventDispatcher;
        final /* synthetic */ CompositeMediaSource this$0;

        public ForwardingEventListener(CompositeMediaSource r3, T r4) {
            this.this$0 = r3;
            this.mediaSourceEventDispatcher = r3.createEventDispatcher(null);
            this.drmEventDispatcher = r3.createDrmEventDispatcher(null);
            this.id = r4;
        }

        private boolean maybeUpdateEventDispatcher(int r3, MediaSource.MediaPeriodId r4) {
            if (r4 == null) goto L7;
            MediaSource.MediaPeriodId r42 = this.this$0.getMediaPeriodIdForChildMediaPeriodId(this.id, r4);
            if (r42 != null) goto L8;
            return false;
        L8:
            int r32 = this.this$0.getWindowIndexForChildWindowIndex(this.id, r3);
            MediaSourceEventListener.EventDispatcher r0 = this.mediaSourceEventDispatcher;
            if (r0.windowIndex == r32) goto L11;
        L12:
            this.mediaSourceEventDispatcher = this.this$0.createEventDispatcher(r32, r42);
        L13:
            DrmSessionEventListener.EventDispatcher r02 = this.drmEventDispatcher;
            if (r02.windowIndex == r32) goto L16;
        L17:
            this.drmEventDispatcher = this.this$0.createDrmEventDispatcher(r32, r42);
            return true;
        L16:
            if (Objects.equals(r02.mediaPeriodId, r42) == false) goto L17;
            return true;
        L11:
            if (Objects.equals(r0.mediaPeriodId, r42) == true) goto L13;
        L7:
            r42 = null;
            goto L8
        }

        private MediaLoadData maybeUpdateMediaLoadData(MediaLoadData r18, MediaSource.MediaPeriodId r19) {
            long r13 = this.this$0.getMediaTimeForChildMediaTime(this.id, r18.mediaStartTimeMs, r19);
            long r15 = this.this$0.getMediaTimeForChildMediaTime(this.id, r18.mediaEndTimeMs, r19);
            if (r13 != r18.mediaStartTimeMs) goto L8;
            if (r15 != r18.mediaEndTimeMs) goto L8;
            return r18;
        L8:
            return new MediaLoadData(r18.dataType, r18.trackType, r18.trackFormat, r18.trackSelectionReason, r18.trackSelectionData, r13, r15);
        }

        @Override // androidx.media3.exoplayer.source.MediaSourceEventListener
        public void onDownstreamFormatChanged(int r1, MediaSource.MediaPeriodId r2, MediaLoadData r3) {
            if (maybeUpdateEventDispatcher(r1, r2) == false) goto L6;
            this.mediaSourceEventDispatcher.downstreamFormatChanged(maybeUpdateMediaLoadData(r3, r2));
            return;
        }

        @Override // androidx.media3.exoplayer.drm.DrmSessionEventListener
        public void onDrmKeysLoaded(int r1, MediaSource.MediaPeriodId r2) {
            if (maybeUpdateEventDispatcher(r1, r2) == false) goto L6;
            this.drmEventDispatcher.drmKeysLoaded();
            return;
        }

        @Override // androidx.media3.exoplayer.drm.DrmSessionEventListener
        public void onDrmKeysRemoved(int r1, MediaSource.MediaPeriodId r2) {
            if (maybeUpdateEventDispatcher(r1, r2) == false) goto L6;
            this.drmEventDispatcher.drmKeysRemoved();
            return;
        }

        @Override // androidx.media3.exoplayer.drm.DrmSessionEventListener
        public void onDrmKeysRestored(int r1, MediaSource.MediaPeriodId r2) {
            if (maybeUpdateEventDispatcher(r1, r2) == false) goto L6;
            this.drmEventDispatcher.drmKeysRestored();
            return;
        }

        @Override // androidx.media3.exoplayer.drm.DrmSessionEventListener
        public void onDrmSessionAcquired(int r1, MediaSource.MediaPeriodId r2, int r3) {
            if (maybeUpdateEventDispatcher(r1, r2) == false) goto L6;
            this.drmEventDispatcher.drmSessionAcquired(r3);
            return;
        }

        @Override // androidx.media3.exoplayer.drm.DrmSessionEventListener
        public void onDrmSessionManagerError(int r1, MediaSource.MediaPeriodId r2, Exception r3) {
            if (maybeUpdateEventDispatcher(r1, r2) == false) goto L6;
            this.drmEventDispatcher.drmSessionManagerError(r3);
            return;
        }

        @Override // androidx.media3.exoplayer.drm.DrmSessionEventListener
        public void onDrmSessionReleased(int r1, MediaSource.MediaPeriodId r2) {
            if (maybeUpdateEventDispatcher(r1, r2) == false) goto L6;
            this.drmEventDispatcher.drmSessionReleased();
            return;
        }

        @Override // androidx.media3.exoplayer.source.MediaSourceEventListener
        public void onLoadCanceled(int r1, MediaSource.MediaPeriodId r2, LoadEventInfo r3, MediaLoadData r4) {
            if (maybeUpdateEventDispatcher(r1, r2) == false) goto L6;
            this.mediaSourceEventDispatcher.loadCanceled(r3, maybeUpdateMediaLoadData(r4, r2));
            return;
        }

        @Override // androidx.media3.exoplayer.source.MediaSourceEventListener
        public void onLoadCompleted(int r1, MediaSource.MediaPeriodId r2, LoadEventInfo r3, MediaLoadData r4) {
            if (maybeUpdateEventDispatcher(r1, r2) == false) goto L6;
            this.mediaSourceEventDispatcher.loadCompleted(r3, maybeUpdateMediaLoadData(r4, r2));
            return;
        }

        @Override // androidx.media3.exoplayer.source.MediaSourceEventListener
        public void onLoadError(int r1, MediaSource.MediaPeriodId r2, LoadEventInfo r3, MediaLoadData r4, IOException r5, boolean r6) {
            if (maybeUpdateEventDispatcher(r1, r2) == false) goto L6;
            this.mediaSourceEventDispatcher.loadError(r3, maybeUpdateMediaLoadData(r4, r2), r5, r6);
            return;
        }

        @Override // androidx.media3.exoplayer.source.MediaSourceEventListener
        public void onLoadStarted(int r1, MediaSource.MediaPeriodId r2, LoadEventInfo r3, MediaLoadData r4, int r5) {
            if (maybeUpdateEventDispatcher(r1, r2) == false) goto L6;
            this.mediaSourceEventDispatcher.loadStarted(r3, maybeUpdateMediaLoadData(r4, r2), r5);
            return;
        }

        @Override // androidx.media3.exoplayer.source.MediaSourceEventListener
        public void onUpstreamDiscarded(int r1, MediaSource.MediaPeriodId r2, MediaLoadData r3) {
            if (maybeUpdateEventDispatcher(r1, r2) == false) goto L6;
            this.mediaSourceEventDispatcher.upstreamDiscarded(maybeUpdateMediaLoadData(r3, r2));
            return;
        }
    }

    public static final class MediaSourceAndListener<T> {
        public final MediaSource.MediaSourceCaller caller;
        public final CompositeMediaSource<T>.ForwardingEventListener eventListener;
        public final MediaSource mediaSource;

        public MediaSourceAndListener(MediaSource r1, MediaSource.MediaSourceCaller r2, CompositeMediaSource<T>.ForwardingEventListener r3) {
            this.mediaSource = r1;
            this.caller = r2;
            this.eventListener = r3;
        }
    }

    public static /* synthetic */ void $r8$lambda$rq_us2tG3vcSaIoUs5QzhKY_2b4(CompositeMediaSource r0, Object r1, MediaSource r2, Timeline r3) {
        r0.onChildSourceInfoRefreshed(r1, r2, r3);
    }

    public CompositeMediaSource() {
        this.childSources = new HashMap();
    }

    @Override // androidx.media3.exoplayer.source.BaseMediaSource
    public void disableInternal() {
        Iterator<MediaSourceAndListener<T>> r2 = this.childSources.values().iterator();
    L4:
        if (r2.hasNext() == false) goto L6;
        MediaSourceAndListener<T> r0 = r2.next();
        r0.mediaSource.disable(r0.caller);
        goto L4
    }

    @Override // androidx.media3.exoplayer.source.BaseMediaSource
    public void enableInternal() {
        Iterator<MediaSourceAndListener<T>> r2 = this.childSources.values().iterator();
    L4:
        if (r2.hasNext() == false) goto L6;
        MediaSourceAndListener<T> r0 = r2.next();
        r0.mediaSource.enable(r0.caller);
        goto L4
    }

    public abstract MediaSource.MediaPeriodId getMediaPeriodIdForChildMediaPeriodId(T r1, MediaSource.MediaPeriodId r2);

    public long getMediaTimeForChildMediaTime(T r1, long r2, MediaSource.MediaPeriodId r4) {
        return r2;
    }

    public int getWindowIndexForChildWindowIndex(T r1, int r2) {
        return r2;
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public void maybeThrowSourceInfoRefreshError() {
        Iterator<MediaSourceAndListener<T>> r1 = this.childSources.values().iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        r1.next().mediaSource.maybeThrowSourceInfoRefreshError();
        goto L4
    }

    public abstract void onChildSourceInfoRefreshed(T r1, MediaSource r2, Timeline r3);

    public final void prepareChildSource(T r5, MediaSource r6) {
        Assertions.checkArgument(!this.childSources.containsKey(r5));
        MediaSource.MediaSourceCaller r0 = new CompositeMediaSource$$ExternalSyntheticLambda0(this, r5);
        ForwardingEventListener r1 = new ForwardingEventListener(this, r5);
        this.childSources.put(r5, new MediaSourceAndListener(r6, r0, r1));
        r6.addEventListener((Handler) Assertions.checkNotNull(this.eventHandler), r1);
        r6.addDrmEventListener((Handler) Assertions.checkNotNull(this.eventHandler), r1);
        r6.prepareSource(r0, this.mediaTransferListener, getPlayerId());
        if (isEnabled() == true) goto L6;
        r6.disable(r0);
        return;
    }

    @Override // androidx.media3.exoplayer.source.BaseMediaSource
    public void prepareSourceInternal(TransferListener r1) {
        this.mediaTransferListener = r1;
        this.eventHandler = Util.createHandlerForCurrentLooper();
    }

    @Override // androidx.media3.exoplayer.source.BaseMediaSource
    public void releaseSourceInternal() {
        Iterator<MediaSourceAndListener<T>> r0 = this.childSources.values().iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        MediaSourceAndListener<T> r1 = r0.next();
        r1.mediaSource.releaseSource(r1.caller);
        r1.mediaSource.removeEventListener(r1.eventListener);
        r1.mediaSource.removeDrmEventListener(r1.eventListener);
        goto L4
    L6:
        this.childSources.clear();
    }
}
