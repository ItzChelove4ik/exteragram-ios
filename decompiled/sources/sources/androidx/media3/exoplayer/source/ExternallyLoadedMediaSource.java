package androidx.media3.exoplayer.source;

import androidx.media3.common.MediaItem;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.TransferListener;
import androidx.media3.exoplayer.drm.DrmSessionManagerProvider;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.upstream.Allocator;
import androidx.media3.exoplayer.upstream.LoadErrorHandlingPolicy;
import java.util.Objects;

/* loaded from: classes4.dex */
public final class ExternallyLoadedMediaSource extends BaseMediaSource {
    private MediaItem mediaItem;
    private final long timelineDurationUs;

    /* renamed from: androidx.media3.exoplayer.source.ExternallyLoadedMediaSource$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class Factory implements MediaSource.Factory {
        private final long timelineDurationUs;

        public Factory(long r1, ExternalLoader r3) {
            this.timelineDurationUs = r1;
        }

        @Override // androidx.media3.exoplayer.source.MediaSource.Factory
        public ExternallyLoadedMediaSource createMediaSource(MediaItem r7) {
            return new ExternallyLoadedMediaSource(r7, this.timelineDurationUs, null, null);
        }

        @Override // androidx.media3.exoplayer.source.MediaSource.Factory
        public /* bridge */ /* synthetic */ MediaSource createMediaSource(MediaItem r1) {
            return createMediaSource(r1);
        }

        @Override // androidx.media3.exoplayer.source.MediaSource.Factory
        public MediaSource.Factory setDrmSessionManagerProvider(DrmSessionManagerProvider r1) {
            return this;
        }

        @Override // androidx.media3.exoplayer.source.MediaSource.Factory
        public MediaSource.Factory setLoadErrorHandlingPolicy(LoadErrorHandlingPolicy r1) {
            return this;
        }
    }

    private ExternallyLoadedMediaSource(MediaItem r1, long r2, ExternalLoader r4) {
        this.mediaItem = r1;
        this.timelineDurationUs = r2;
    }

    public /* synthetic */ ExternallyLoadedMediaSource(MediaItem r1, long r2, ExternalLoader r4, AnonymousClass1 r5) {
        this(r1, r2, r4);
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public boolean canUpdateMediaItem(MediaItem r5) {
        MediaItem.LocalConfiguration r52 = r5.localConfiguration;
        MediaItem.LocalConfiguration r0 = (MediaItem.LocalConfiguration) Assertions.checkNotNull(getMediaItem().localConfiguration);
        if (r52 != null) goto L5;
        return false;
    L5:
        if (r52.uri.equals(r0.uri) == true) goto L7;
        return false;
    L7:
        if (Objects.equals(r52.mimeType, r0.mimeType) == false) goto L18;
        long r02 = r52.imageDurationMs;
        if (r02 != (-9223372036854775807L)) goto L11;
        return true;
    L11:
        if (Util.msToUs(r02) != this.timelineDurationUs) goto L19;
        return true;
    L19:
        return false;
    L18:
        return false;
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public MediaPeriod createPeriod(MediaSource.MediaPeriodId r1, Allocator r2, long r3) {
        MediaItem r0 = getMediaItem();
        Assertions.checkNotNull(r0.localConfiguration);
        Assertions.checkNotNull(r0.localConfiguration.mimeType, "Externally loaded mediaItems require a MIME type.");
        MediaItem.LocalConfiguration r02 = r0.localConfiguration;
        return new ExternallyLoadedMediaPeriod(r02.uri, r02.mimeType, null);
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public synchronized MediaItem getMediaItem() {
        monitor-enter(this);
        MediaItem r0 = this.mediaItem;     // Catch: Throwable -> L6
        monitor-exit(this);
        return r0;
    L6:
        th = move-exception;
        throw th;
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public void maybeThrowSourceInfoRefreshError() {
    }

    @Override // androidx.media3.exoplayer.source.BaseMediaSource
    public void prepareSourceInternal(TransferListener r9) {
        refreshSourceInfo(new SinglePeriodTimeline(this.timelineDurationUs, true, false, false, null, getMediaItem()));
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public void releasePeriod(MediaPeriod r1) {
        ((ExternallyLoadedMediaPeriod) r1).releasePeriod();
    }

    @Override // androidx.media3.exoplayer.source.BaseMediaSource
    public void releaseSourceInternal() {
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public synchronized void updateMediaItem(MediaItem r1) {
        monitor-enter(this);
        this.mediaItem = r1;     // Catch: Throwable -> L6
        monitor-exit(this);
        return;
    L6:
        th = move-exception;
        throw th;
    }
}
