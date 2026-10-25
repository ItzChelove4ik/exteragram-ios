package androidx.media3.exoplayer.source;

import android.net.Uri;
import android.os.Looper;
import androidx.media3.common.Format;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.TransferListener;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.drm.DefaultDrmSessionManagerProvider;
import androidx.media3.exoplayer.drm.DrmSessionEventListener;
import androidx.media3.exoplayer.drm.DrmSessionManager;
import androidx.media3.exoplayer.drm.DrmSessionManagerProvider;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.MediaSourceEventListener;
import androidx.media3.exoplayer.source.ProgressiveMediaExtractor;
import androidx.media3.exoplayer.source.ProgressiveMediaPeriod;
import androidx.media3.exoplayer.upstream.Allocator;
import androidx.media3.exoplayer.upstream.DefaultLoadErrorHandlingPolicy;
import androidx.media3.exoplayer.upstream.LoadErrorHandlingPolicy;
import androidx.media3.exoplayer.util.ReleasableExecutor;
import androidx.media3.extractor.DefaultExtractorsFactory;
import androidx.media3.extractor.ExtractorsFactory;
import androidx.media3.extractor.SeekMap;
import com.google.common.base.Supplier;
import java.util.Objects;

/* loaded from: classes4.dex */
public final class ProgressiveMediaSource extends BaseMediaSource implements ProgressiveMediaPeriod.Listener {
    private final int continueLoadingCheckIntervalBytes;
    private final DataSource.Factory dataSourceFactory;
    private final Supplier<ReleasableExecutor> downloadExecutorSupplier;
    private final DrmSessionManager drmSessionManager;
    private final LoadErrorHandlingPolicy loadableLoadErrorHandlingPolicy;
    private MediaItem mediaItem;
    private final ProgressiveMediaExtractor.Factory progressiveMediaExtractorFactory;
    private final Format singleTrackFormat;
    private final int singleTrackId;
    private long timelineDurationUs;
    private boolean timelineIsLive;
    private boolean timelineIsPlaceholder;
    private boolean timelineIsSeekable;
    private TransferListener transferListener;

    /* renamed from: androidx.media3.exoplayer.source.ProgressiveMediaSource$1, reason: invalid class name */
    public class AnonymousClass1 extends ForwardingTimeline {
        final /* synthetic */ ProgressiveMediaSource this$0;

        public AnonymousClass1(ProgressiveMediaSource r1, Timeline r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // androidx.media3.exoplayer.source.ForwardingTimeline, androidx.media3.common.Timeline
        public Timeline.Period getPeriod(int r1, Timeline.Period r2, boolean r3) {
            super.getPeriod(r1, r2, r3);
            r2.isPlaceholder = true;
            return r2;
        }

        @Override // androidx.media3.exoplayer.source.ForwardingTimeline, androidx.media3.common.Timeline
        public Timeline.Window getWindow(int r1, Timeline.Window r2, long r3) {
            super.getWindow(r1, r2, r3);
            r2.isPlaceholder = true;
            return r2;
        }
    }

    public static final class Factory implements MediaSourceFactory {
        private int continueLoadingCheckIntervalBytes;
        private final DataSource.Factory dataSourceFactory;
        private Supplier<ReleasableExecutor> downloadExecutorSupplier;
        private DrmSessionManagerProvider drmSessionManagerProvider;
        private LoadErrorHandlingPolicy loadErrorHandlingPolicy;
        private ProgressiveMediaExtractor.Factory progressiveMediaExtractorFactory;
        private Format singleTrackFormat;
        private int singleTrackId;

        public static /* synthetic */ ProgressiveMediaExtractor $r8$lambda$h6Gx0DBq5OUxdxhehQgXI7wPWuk(ExtractorsFactory r0, PlayerId r1) {
            return new BundledExtractorsAdapter(r0);
        }

        public Factory(DataSource.Factory r2) {
            this(r2, new DefaultExtractorsFactory());
        }

        public Factory(DataSource.Factory r7, ProgressiveMediaExtractor.Factory r8) {
            this(r7, r8, new DefaultDrmSessionManagerProvider(), new DefaultLoadErrorHandlingPolicy(), 1048576);
        }

        public Factory(DataSource.Factory r1, ProgressiveMediaExtractor.Factory r2, DrmSessionManagerProvider r3, LoadErrorHandlingPolicy r4, int r5) {
            this.dataSourceFactory = r1;
            this.progressiveMediaExtractorFactory = r2;
            this.drmSessionManagerProvider = r3;
            this.loadErrorHandlingPolicy = r4;
            this.continueLoadingCheckIntervalBytes = r5;
        }

        public Factory(DataSource.Factory r2, ExtractorsFactory r3) {
            this(r2, new ProgressiveMediaSource$Factory$$ExternalSyntheticLambda0(r3));
        }

        @Override // androidx.media3.exoplayer.source.MediaSource.Factory
        public /* bridge */ /* synthetic */ MediaSource createMediaSource(MediaItem r1) {
            return createMediaSource(r1);
        }

        @Override // androidx.media3.exoplayer.source.MediaSource.Factory
        public ProgressiveMediaSource createMediaSource(MediaItem r13) {
            Assertions.checkNotNull(r13.localConfiguration);
            return new ProgressiveMediaSource(r13, this.dataSourceFactory, this.progressiveMediaExtractorFactory, this.drmSessionManagerProvider.get(r13), this.loadErrorHandlingPolicy, this.continueLoadingCheckIntervalBytes, this.singleTrackId, this.singleTrackFormat, this.downloadExecutorSupplier, null);
        }

        public Factory enableLazyLoadingWithSingleTrack(int r1, Format r2) {
            this.singleTrackId = r1;
            this.singleTrackFormat = (Format) Assertions.checkNotNull(r2);
            return this;
        }

        @Override // androidx.media3.exoplayer.source.MediaSource.Factory
        public /* bridge */ /* synthetic */ MediaSource.Factory setDrmSessionManagerProvider(DrmSessionManagerProvider r1) {
            return setDrmSessionManagerProvider(r1);
        }

        @Override // androidx.media3.exoplayer.source.MediaSource.Factory
        public Factory setDrmSessionManagerProvider(DrmSessionManagerProvider r2) {
            this.drmSessionManagerProvider = (DrmSessionManagerProvider) Assertions.checkNotNull(r2, "MediaSource.Factory#setDrmSessionManagerProvider no longer handles null by instantiating a new DefaultDrmSessionManagerProvider. Explicitly construct and pass an instance in order to retain the old behavior.");
            return this;
        }

        @Override // androidx.media3.exoplayer.source.MediaSource.Factory
        public /* bridge */ /* synthetic */ MediaSource.Factory setLoadErrorHandlingPolicy(LoadErrorHandlingPolicy r1) {
            return setLoadErrorHandlingPolicy(r1);
        }

        @Override // androidx.media3.exoplayer.source.MediaSource.Factory
        public Factory setLoadErrorHandlingPolicy(LoadErrorHandlingPolicy r2) {
            this.loadErrorHandlingPolicy = (LoadErrorHandlingPolicy) Assertions.checkNotNull(r2, "MediaSource.Factory#setLoadErrorHandlingPolicy no longer handles null by instantiating a new DefaultLoadErrorHandlingPolicy. Explicitly construct and pass an instance in order to retain the old behavior.");
            return this;
        }
    }

    private ProgressiveMediaSource(MediaItem r1, DataSource.Factory r2, ProgressiveMediaExtractor.Factory r3, DrmSessionManager r4, LoadErrorHandlingPolicy r5, int r6, int r7, Format r8, Supplier<ReleasableExecutor> r9) {
        this.mediaItem = r1;
        this.dataSourceFactory = r2;
        this.progressiveMediaExtractorFactory = r3;
        this.drmSessionManager = r4;
        this.loadableLoadErrorHandlingPolicy = r5;
        this.continueLoadingCheckIntervalBytes = r6;
        this.singleTrackFormat = r8;
        this.singleTrackId = r7;
        this.timelineIsPlaceholder = true;
        this.timelineDurationUs = -9223372036854775807L;
        this.downloadExecutorSupplier = r9;
    }

    public /* synthetic */ ProgressiveMediaSource(MediaItem r1, DataSource.Factory r2, ProgressiveMediaExtractor.Factory r3, DrmSessionManager r4, LoadErrorHandlingPolicy r5, int r6, int r7, Format r8, Supplier r9, AnonymousClass1 r10) {
        this(r1, r2, r3, r4, r5, r6, r7, r8, r9);
    }

    private MediaItem.LocalConfiguration getLocalConfiguration() {
        return (MediaItem.LocalConfiguration) Assertions.checkNotNull(getMediaItem().localConfiguration);
    }

    private void notifySourceInfoRefreshed() {
        Timeline r0 = new SinglePeriodTimeline(this.timelineDurationUs, this.timelineIsSeekable, false, this.timelineIsLive, null, getMediaItem());
        if (this.timelineIsPlaceholder == false) goto L5;
        r0 = new AnonymousClass1(this, r0);
    L5:
        refreshSourceInfo(r0);
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public boolean canUpdateMediaItem(MediaItem r5) {
        MediaItem.LocalConfiguration r4 = getLocalConfiguration();
        MediaItem.LocalConfiguration r52 = r5.localConfiguration;
        if (r52 != null) goto L5;
        return false;
    L5:
        if (r52.uri.equals(r4.uri) == true) goto L7;
        return false;
    L7:
        if (r52.imageDurationMs == r4.imageDurationMs) goto L9;
        return false;
    L9:
        if (Objects.equals(r52.customCacheKey, r4.customCacheKey) == false) goto L16;
        return true;
    L16:
        return false;
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public MediaPeriod createPeriod(MediaSource.MediaPeriodId r18, Allocator r19, long r20) {
        DataSource r2 = this.dataSourceFactory.createDataSource();
        TransferListener r0 = this.transferListener;
        if (r0 == null) goto L5;
        r2.addTransferListener(r0);
    L5:
        MediaItem.LocalConfiguration r02 = getLocalConfiguration();
        Uri r1 = r02.uri;
        ProgressiveMediaExtractor r4 = this.progressiveMediaExtractorFactory.createProgressiveMediaExtractor(getPlayerId());
        DrmSessionManager r42 = this.drmSessionManager;
        DrmSessionEventListener.EventDispatcher r5 = createDrmEventDispatcher(r18);
        LoadErrorHandlingPolicy r6 = this.loadableLoadErrorHandlingPolicy;
        MediaSourceEventListener.EventDispatcher r9 = createEventDispatcher(r18);
        String r10 = r02.customCacheKey;
        int r11 = this.continueLoadingCheckIntervalBytes;
        int r12 = this.singleTrackId;
        Format r13 = this.singleTrackFormat;
        long r14 = Util.msToUs(r02.imageDurationMs);
        Supplier<ReleasableExecutor> r03 = this.downloadExecutorSupplier;
        if (r03 == null) goto L9;
        ReleasableExecutor r04 = r03.get();
    L11:
        return new ProgressiveMediaPeriod(r1, r2, r4, r42, r5, r6, r9, this, r19, r10, r11, r12, r13, r14, r04);
    L9:
        r04 = null;
        goto L11
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

    @Override // androidx.media3.exoplayer.source.ProgressiveMediaPeriod.Listener
    public void onSourceInfoRefreshed(long r3, SeekMap r5, boolean r6) {
        if (r3 != (-9223372036854775807L)) goto L5;
        r3 = this.timelineDurationUs;
    L5:
        boolean r52 = r5.isSeekable();
        if (this.timelineIsPlaceholder == false) goto L8;
    L14:
        this.timelineDurationUs = r3;
        this.timelineIsSeekable = r52;
        this.timelineIsLive = r6;
        this.timelineIsPlaceholder = false;
        notifySourceInfoRefreshed();
        return;
    L8:
        if (this.timelineDurationUs != r3) goto L14;
        if (this.timelineIsSeekable != r52) goto L14;
        if (this.timelineIsLive != r6) goto L14;
    }

    @Override // androidx.media3.exoplayer.source.BaseMediaSource
    public void prepareSourceInternal(TransferListener r3) {
        this.transferListener = r3;
        this.drmSessionManager.setPlayer((Looper) Assertions.checkNotNull(Looper.myLooper()), getPlayerId());
        this.drmSessionManager.prepare();
        notifySourceInfoRefreshed();
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public void releasePeriod(MediaPeriod r1) {
        ((ProgressiveMediaPeriod) r1).release();
    }

    @Override // androidx.media3.exoplayer.source.BaseMediaSource
    public void releaseSourceInternal() {
        this.drmSessionManager.release();
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
