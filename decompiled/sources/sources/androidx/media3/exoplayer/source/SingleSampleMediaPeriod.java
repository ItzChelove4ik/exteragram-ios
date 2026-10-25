package androidx.media3.exoplayer.source;

import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.TrackGroup;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DataSourceUtil;
import androidx.media3.datasource.DataSpec;
import androidx.media3.datasource.StatsDataSource;
import androidx.media3.datasource.TransferListener;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.exoplayer.FormatHolder;
import androidx.media3.exoplayer.LoadingInfo;
import androidx.media3.exoplayer.SeekParameters;
import androidx.media3.exoplayer.source.MediaPeriod;
import androidx.media3.exoplayer.source.MediaSourceEventListener;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import androidx.media3.exoplayer.upstream.LoadErrorHandlingPolicy;
import androidx.media3.exoplayer.upstream.Loader;
import androidx.media3.exoplayer.util.ReleasableExecutor;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;

/* loaded from: classes4.dex */
final class SingleSampleMediaPeriod implements MediaPeriod, Loader.Callback<SourceLoadable> {
    private final DataSource.Factory dataSourceFactory;
    private final DataSpec dataSpec;
    private final long durationUs;
    private final MediaSourceEventListener.EventDispatcher eventDispatcher;
    final Format format;
    private final LoadErrorHandlingPolicy loadErrorHandlingPolicy;
    final Loader loader;
    boolean loadingFinished;
    byte[] sampleData;
    int sampleSize;
    private final ArrayList<SampleStreamImpl> sampleStreams;
    private final TrackGroupArray tracks;
    private final TransferListener transferListener;
    final boolean treatLoadErrorsAsEndOfStream;

    /* renamed from: androidx.media3.exoplayer.source.SingleSampleMediaPeriod$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public final class SampleStreamImpl implements SampleStream {
        private boolean notifiedDownstreamFormat;
        private int streamState;
        final /* synthetic */ SingleSampleMediaPeriod this$0;

        private SampleStreamImpl(SingleSampleMediaPeriod r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ SampleStreamImpl(SingleSampleMediaPeriod r1, AnonymousClass1 r2) {
            this(r1);
        }

        private void maybeNotifyDownstreamFormat() {
            if (this.notifiedDownstreamFormat == true) goto L6;
            SingleSampleMediaPeriod.access$300(this.this$0).downstreamFormatChanged(MimeTypes.getTrackType(this.this$0.format.sampleMimeType), this.this$0.format, 0, null, 0);
            this.notifiedDownstreamFormat = true;
            return;
        }

        @Override // androidx.media3.exoplayer.source.SampleStream
        public boolean isReady() {
            return this.this$0.loadingFinished;
        }

        @Override // androidx.media3.exoplayer.source.SampleStream
        public void maybeThrowError() {
            SingleSampleMediaPeriod r1 = this.this$0;
            if (r1.treatLoadErrorsAsEndOfStream == true) goto L6;
            r1.loader.maybeThrowError();
            return;
        }

        @Override // androidx.media3.exoplayer.source.SampleStream
        public int readData(FormatHolder r8, DecoderInputBuffer r9, int r10) {
            maybeNotifyDownstreamFormat();
            SingleSampleMediaPeriod r0 = this.this$0;
            boolean r1 = r0.loadingFinished;
            if (r1 == true) goto L5;
        L7:
            int r3 = this.streamState;
            if (r3 != 2) goto L12;
            r9.addFlag(4);
            return -4;
        L12:
            if ((r10 & 2) != 0) goto L25;
            if (r3 == 0) goto L25;
            if (r1 == true) goto L18;
            return -3;
        L18:
            Assertions.checkNotNull(r0.sampleData);
            r9.addFlag(1);
            r9.timeUs = 0;
            if ((r10 & 4) != 0) goto L22;
            r9.ensureSpaceForWrite(this.this$0.sampleSize);
            ByteBuffer r82 = r9.data;
            SingleSampleMediaPeriod r92 = this.this$0;
            r82.put(r92.sampleData, 0, r92.sampleSize);
        L22:
            if ((r10 & 1) != 0) goto L24;
            this.streamState = 2;
        L24:
            return -4;
        L25:
            r8.format = r0.format;
            this.streamState = 1;
            return -5;
        L5:
            if (r0.sampleData != null) goto L7;
            this.streamState = 2;
            goto L7
        }

        public void reset() {
            if (this.streamState != 2) goto L6;
            this.streamState = 1;
            return;
        }

        @Override // androidx.media3.exoplayer.source.SampleStream
        public int skipData(long r3) {
            maybeNotifyDownstreamFormat();
            if (r3 > 0) goto L5;
            return 0;
        L5:
            if (this.streamState == 2) goto L10;
            this.streamState = 2;
            return 1;
        L10:
            return 0;
        }
    }

    public static final class SourceLoadable implements Loader.Loadable {
        private final StatsDataSource dataSource;
        public final DataSpec dataSpec;
        public final long loadTaskId;
        private byte[] sampleData;

        public SourceLoadable(DataSpec r3, DataSource r4) {
            this.loadTaskId = LoadEventInfo.getNewId();
            this.dataSpec = r3;
            this.dataSource = new StatsDataSource(r4);
        }

        public static /* synthetic */ StatsDataSource access$100(SourceLoadable r0) {
            return r0.dataSource;
        }

        public static /* synthetic */ byte[] access$200(SourceLoadable r0) {
            return r0.sampleData;
        }

        @Override // androidx.media3.exoplayer.upstream.Loader.Loadable
        public void cancelLoad() {
        }

        @Override // androidx.media3.exoplayer.upstream.Loader.Loadable
        public void load() {
            this.dataSource.resetBytesRead();
            this.dataSource.open(this.dataSpec);     // Catch: Throwable -> L10
            int r0 = 0;
        L5:
            StatsDataSource r1 = this.dataSource;
            if (r0 == (-1)) goto L17;
            int r02 = (int) r1.getBytesRead();     // Catch: Throwable -> L10
            byte[] r12 = this.sampleData;     // Catch: Throwable -> L10
            if (r12 != null) goto L13;
            this.sampleData = new byte[1024];     // Catch: Throwable -> L10
        L15:
            StatsDataSource r13 = this.dataSource;     // Catch: Throwable -> L10
            byte[] r2 = this.sampleData;     // Catch: Throwable -> L10
            r0 = r13.read(r2, r02, r2.length - r02);     // Catch: Throwable -> L10
            goto L5
        L13:
            if (r02 != r12.length) goto L15;
            this.sampleData = Arrays.copyOf(r12, r12.length * 2);     // Catch: Throwable -> L10
            goto L15
        L17:
            DataSourceUtil.closeQuietly(r1);
            return;
        L10:
            th = move-exception;
            DataSourceUtil.closeQuietly(this.dataSource);
            throw th;
        }
    }

    public SingleSampleMediaPeriod(DataSpec r1, DataSource.Factory r2, TransferListener r3, Format r4, long r5, LoadErrorHandlingPolicy r7, MediaSourceEventListener.EventDispatcher r8, boolean r9, ReleasableExecutor r10) {
        this.dataSpec = r1;
        this.dataSourceFactory = r2;
        this.transferListener = r3;
        this.format = r4;
        this.durationUs = r5;
        this.loadErrorHandlingPolicy = r7;
        this.eventDispatcher = r8;
        this.treatLoadErrorsAsEndOfStream = r9;
        this.tracks = new TrackGroupArray(new TrackGroup[]{new TrackGroup(new Format[]{r4})});
        this.sampleStreams = new ArrayList();
        if (r10 == null) goto L5;
        Loader r12 = new Loader(r10);
    L6:
        this.loader = r12;
        return;
    L5:
        r12 = new Loader("SingleSampleMediaPeriod");
        goto L6
    }

    public static /* synthetic */ MediaSourceEventListener.EventDispatcher access$300(SingleSampleMediaPeriod r0) {
        return r0.eventDispatcher;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public boolean continueLoading(LoadingInfo r4) {
        if (this.loadingFinished == false) goto L5;
        return false;
    L5:
        if (this.loader.isLoading() == false) goto L7;
        return false;
    L7:
        if (this.loader.hasFatalError() == true) goto L17;
        DataSource r42 = this.dataSourceFactory.createDataSource();
        TransferListener r0 = this.transferListener;
        if (r0 == null) goto L12;
        r42.addTransferListener(r0);
    L12:
        SourceLoadable r02 = new SourceLoadable(this.dataSpec, r42);
        this.loader.startLoading(r02, this, this.loadErrorHandlingPolicy.getMinimumLoadableRetryCount(1));
        return true;
    L17:
        return false;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public void discardBuffer(long r1, boolean r3) {
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long getAdjustedSeekPositionUs(long r1, SeekParameters r3) {
        return r1;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public long getBufferedPositionUs() {
        if (this.loadingFinished == false) goto L6;
        return Long.MIN_VALUE;
    L6:
        return 0;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public long getNextLoadPositionUs() {
        if (this.loadingFinished == false) goto L5;
        return Long.MIN_VALUE;
    L5:
        if (this.loader.isLoading() == true) goto L11;
        return 0;
    L11:
        return Long.MIN_VALUE;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public TrackGroupArray getTrackGroups() {
        return this.tracks;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public boolean isLoading() {
        return this.loader.isLoading();
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public void maybeThrowPrepareError() {
    }

    public void onLoadCanceled(SourceLoadable r14, long r15, long r17, boolean r19) {
        StatsDataSource r0 = SourceLoadable.access$100(r14);
        LoadEventInfo r1 = new LoadEventInfo(r14.loadTaskId, r14.dataSpec, r0.getLastOpenedUri(), r0.getLastResponseHeaders(), r15, r17, r0.getBytesRead());
        this.loadErrorHandlingPolicy.onLoadTaskConcluded(r14.loadTaskId);
        this.eventDispatcher.loadCanceled(r1, 1, -1, null, 0, null, 0, this.durationUs);
    }

    @Override // androidx.media3.exoplayer.upstream.Loader.Callback
    public /* bridge */ /* synthetic */ void onLoadCanceled(Loader.Loadable r1, long r2, long r4, boolean r6) {
        onLoadCanceled((SourceLoadable) r1, r2, r4, r6);
    }

    public void onLoadCompleted(SourceLoadable r14, long r15, long r17) {
        this.sampleSize = (int) SourceLoadable.access$100(r14).getBytesRead();
        this.sampleData = (byte[]) Assertions.checkNotNull(SourceLoadable.access$200(r14));
        this.loadingFinished = true;
        StatsDataSource r0 = SourceLoadable.access$100(r14);
        LoadEventInfo r1 = new LoadEventInfo(r14.loadTaskId, r14.dataSpec, r0.getLastOpenedUri(), r0.getLastResponseHeaders(), r15, r17, this.sampleSize);
        this.loadErrorHandlingPolicy.onLoadTaskConcluded(r14.loadTaskId);
        this.eventDispatcher.loadCompleted(r1, 1, -1, this.format, 0, null, 0, this.durationUs);
    }

    @Override // androidx.media3.exoplayer.upstream.Loader.Callback
    public /* bridge */ /* synthetic */ void onLoadCompleted(Loader.Loadable r1, long r2, long r4) {
        onLoadCompleted((SourceLoadable) r1, r2, r4);
    }

    public Loader.LoadErrorAction onLoadError(SourceLoadable r27, long r28, long r30, IOException r32, int r33) {
        StatsDataSource r3 = SourceLoadable.access$100(r27);
        LoadEventInfo r14 = new LoadEventInfo(r27.loadTaskId, r27.dataSpec, r3.getLastOpenedUri(), r3.getLastResponseHeaders(), r28, r30, r3.getBytesRead());
        long r34 = this.loadErrorHandlingPolicy.getRetryDelayMsFor(new LoadErrorHandlingPolicy.LoadErrorInfo(r14, new MediaLoadData(1, -1, this.format, 0, null, 0, Util.usToMs(this.durationUs)), r32, r33));
        if (r34 != (-9223372036854775807L)) goto L5;
    L8:
        boolean r2 = true;
    L10:
        if (this.treatLoadErrorsAsEndOfStream == false) goto L14;
        if (r2 == false) goto L14;
        Log.w("SingleSampleMediaPeriod", "Loading failed, treating as end-of-stream.", r32);
        this.loadingFinished = true;
        Loader.LoadErrorAction r22 = Loader.DONT_RETRY;
    L13:
        Loader.LoadErrorAction r15 = r22;
        boolean r16 = r15.isRetry();
        this.eventDispatcher.loadError(r14, 1, -1, this.format, 0, null, 0, this.durationUs, r32, !r16);
        if (r16 == true) goto L20;
        this.loadErrorHandlingPolicy.onLoadTaskConcluded(r27.loadTaskId);
    L20:
        return r15;
    L14:
        if (r34 == (-9223372036854775807L)) goto L16;
        r22 = Loader.createRetryAction(false, r34);
        goto L13
    L16:
        r22 = Loader.DONT_RETRY_FATAL;
        goto L13
    L5:
        if (r33 >= this.loadErrorHandlingPolicy.getMinimumLoadableRetryCount(1)) goto L8;
        r2 = false;
        goto L10
    }

    @Override // androidx.media3.exoplayer.upstream.Loader.Callback
    public /* bridge */ /* synthetic */ Loader.LoadErrorAction onLoadError(Loader.Loadable r1, long r2, long r4, IOException r6, int r7) {
        return onLoadError((SourceLoadable) r1, r2, r4, r6, r7);
    }

    public void onLoadStarted(SourceLoadable r18, long r19, long r21, int r23) {
        StatsDataSource r2 = SourceLoadable.access$100(r18);
        if (r23 != 0) goto L5;
        LoadEventInfo r6 = new LoadEventInfo(r18.loadTaskId, r18.dataSpec, r19);
    L6:
        this.eventDispatcher.loadStarted(r6, 1, -1, this.format, 0, null, 0, this.durationUs, r23);
        return;
    L5:
        r6 = new LoadEventInfo(r18.loadTaskId, r18.dataSpec, r2.getLastOpenedUri(), r2.getLastResponseHeaders(), r19, r21, r2.getBytesRead());
        goto L6
    }

    @Override // androidx.media3.exoplayer.upstream.Loader.Callback
    public /* bridge */ /* synthetic */ void onLoadStarted(Loader.Loadable r1, long r2, long r4, int r6) {
        onLoadStarted((SourceLoadable) r1, r2, r4, r6);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public void prepare(MediaPeriod.Callback r1, long r2) {
        r1.onPrepared(this);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long readDiscontinuity() {
        return -9223372036854775807L;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public void reevaluateBuffer(long r1) {
    }

    public void release() {
        this.loader.release();
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long seekToUs(long r3) {
        int r0 = 0;
    L4:
        if (r0 >= this.sampleStreams.size()) goto L6;
        this.sampleStreams.get(r0).reset();
        r0 = r0 + 1;
        goto L4
    L6:
        return r3;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long selectTracks(ExoTrackSelection[] r5, boolean[] r6, SampleStream[] r7, boolean[] r8, long r9) {
        int r0 = 0;
    L4:
        if (r0 >= r5.length) goto L18;
        SampleStream r1 = r7[r0];
        if (r1 == null) goto L13;
        if (r5[r0] != null) goto L10;
    L11:
        this.sampleStreams.remove(r1);
        r7[r0] = null;
        goto L13
    L10:
        if (r6[r0] == false) goto L11;
    L13:
        if (r7[r0] != null) goto L17;
        if (r5[r0] == null) goto L17;
        SampleStreamImpl r12 = new SampleStreamImpl(this, null);
        this.sampleStreams.add(r12);
        r7[r0] = r12;
        r8[r0] = true;
    L17:
        r0 = r0 + 1;
        goto L4
    L18:
        return r9;
    }
}
