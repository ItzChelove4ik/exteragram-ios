package androidx.media3.exoplayer.source;

import android.net.Uri;
import androidx.media3.common.Format;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.Assertions;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DataSpec;
import androidx.media3.datasource.TransferListener;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.MediaSourceEventListener;
import androidx.media3.exoplayer.upstream.Allocator;
import androidx.media3.exoplayer.upstream.DefaultLoadErrorHandlingPolicy;
import androidx.media3.exoplayer.upstream.LoadErrorHandlingPolicy;
import androidx.media3.exoplayer.util.ReleasableExecutor;
import com.google.common.base.MoreObjects;
import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableList;

@Deprecated
/* loaded from: classes4.dex */
public final class SingleSampleMediaSource extends BaseMediaSource {
    private final DataSource.Factory dataSourceFactory;
    private final DataSpec dataSpec;
    private final Supplier<ReleasableExecutor> downloadExecutorSupplier;
    private final long durationUs;
    private final Format format;
    private final LoadErrorHandlingPolicy loadErrorHandlingPolicy;
    private final MediaItem mediaItem;
    private final Timeline timeline;
    private TransferListener transferListener;
    private final boolean treatLoadErrorsAsEndOfStream;

    /* renamed from: androidx.media3.exoplayer.source.SingleSampleMediaSource$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class Factory {
        private final DataSource.Factory dataSourceFactory;
        private Supplier<ReleasableExecutor> downloadExecutorSupplier;
        private LoadErrorHandlingPolicy loadErrorHandlingPolicy;
        private Object tag;
        private String trackId;
        private boolean treatLoadErrorsAsEndOfStream;

        public Factory(DataSource.Factory r1) {
            this.dataSourceFactory = (DataSource.Factory) Assertions.checkNotNull(r1);
            this.loadErrorHandlingPolicy = new DefaultLoadErrorHandlingPolicy();
            this.treatLoadErrorsAsEndOfStream = true;
        }

        public SingleSampleMediaSource createMediaSource(MediaItem.SubtitleConfiguration r12, long r13) {
            return new SingleSampleMediaSource(this.trackId, r12, this.dataSourceFactory, r13, this.loadErrorHandlingPolicy, this.treatLoadErrorsAsEndOfStream, this.tag, this.downloadExecutorSupplier, null);
        }

        public Factory setLoadErrorHandlingPolicy(LoadErrorHandlingPolicy r1) {
            if (r1 != null) goto L5;
            r1 = new DefaultLoadErrorHandlingPolicy();
        L5:
            this.loadErrorHandlingPolicy = r1;
            return this;
        }
    }

    private SingleSampleMediaSource(String r9, MediaItem.SubtitleConfiguration r10, DataSource.Factory r11, long r12, LoadErrorHandlingPolicy r14, boolean r15, Object r16, Supplier<ReleasableExecutor> r17) {
        this.dataSourceFactory = r11;
        this.durationUs = r12;
        this.loadErrorHandlingPolicy = r14;
        this.treatLoadErrorsAsEndOfStream = r15;
        MediaItem r7 = new MediaItem.Builder().setUri(Uri.EMPTY).setMediaId(r10.uri.toString()).setSubtitleConfigurations(ImmutableList.of(r10)).setTag(r16).build();
        this.mediaItem = r7;
        Format.Builder r112 = new Format.Builder().setSampleMimeType((String) MoreObjects.firstNonNull(r10.mimeType, "text/x-unknown")).setLanguage(r10.language).setSelectionFlags(r10.selectionFlags).setRoleFlags(r10.roleFlags).setLabel(r10.label);
        String r142 = r10.id;
        if (r142 == null) goto L5;
        r9 = r142;
    L5:
        this.format = r112.setId(r9).build();
        this.dataSpec = new DataSpec.Builder().setUri(r10.uri).setFlags(1).build();
        this.timeline = new SinglePeriodTimeline(r12, true, false, false, null, r7);
        this.downloadExecutorSupplier = r17;
    }

    public /* synthetic */ SingleSampleMediaSource(String r1, MediaItem.SubtitleConfiguration r2, DataSource.Factory r3, long r4, LoadErrorHandlingPolicy r6, boolean r7, Object r8, Supplier r9, AnonymousClass1 r10) {
        this(r1, r2, r3, r4, r6, r7, r8, r9);
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public MediaPeriod createPeriod(MediaSource.MediaPeriodId r12, Allocator r13, long r14) {
        DataSpec r1 = this.dataSpec;
        DataSource.Factory r2 = this.dataSourceFactory;
        TransferListener r3 = this.transferListener;
        Format r4 = this.format;
        long r5 = this.durationUs;
        LoadErrorHandlingPolicy r7 = this.loadErrorHandlingPolicy;
        MediaSourceEventListener.EventDispatcher r8 = createEventDispatcher(r12);
        boolean r9 = this.treatLoadErrorsAsEndOfStream;
        Supplier<ReleasableExecutor> r11 = this.downloadExecutorSupplier;
        if (r11 == null) goto L6;
        ReleasableExecutor r112 = r11.get();
    L8:
        return new SingleSampleMediaPeriod(r1, r2, r3, r4, r5, r7, r8, r9, r112);
    L6:
        r112 = null;
        goto L8
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public MediaItem getMediaItem() {
        return this.mediaItem;
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public void maybeThrowSourceInfoRefreshError() {
    }

    @Override // androidx.media3.exoplayer.source.BaseMediaSource
    public void prepareSourceInternal(TransferListener r1) {
        this.transferListener = r1;
        refreshSourceInfo(this.timeline);
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public void releasePeriod(MediaPeriod r1) {
        ((SingleSampleMediaPeriod) r1).release();
    }

    @Override // androidx.media3.exoplayer.source.BaseMediaSource
    public void releaseSourceInternal() {
    }
}
