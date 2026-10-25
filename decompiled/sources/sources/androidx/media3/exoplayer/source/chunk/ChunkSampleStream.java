package androidx.media3.exoplayer.source.chunk;

import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.exoplayer.FormatHolder;
import androidx.media3.exoplayer.LoadingInfo;
import androidx.media3.exoplayer.SeekParameters;
import androidx.media3.exoplayer.drm.DrmSessionEventListener;
import androidx.media3.exoplayer.drm.DrmSessionManager;
import androidx.media3.exoplayer.source.LoadEventInfo;
import androidx.media3.exoplayer.source.MediaLoadData;
import androidx.media3.exoplayer.source.MediaSourceEventListener;
import androidx.media3.exoplayer.source.SampleQueue;
import androidx.media3.exoplayer.source.SampleStream;
import androidx.media3.exoplayer.source.SequenceableLoader;
import androidx.media3.exoplayer.source.chunk.ChunkSource;
import androidx.media3.exoplayer.upstream.Allocator;
import androidx.media3.exoplayer.upstream.LoadErrorHandlingPolicy;
import androidx.media3.exoplayer.upstream.Loader;
import androidx.media3.exoplayer.util.ReleasableExecutor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.mvel2.asm.MethodWriter$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public class ChunkSampleStream<T extends ChunkSource> implements SampleStream, SequenceableLoader, Loader.Callback<Chunk>, Loader.ReleaseCallback {
    private final SequenceableLoader.Callback<ChunkSampleStream<T>> callback;
    private boolean canReportInitialDiscontinuity;
    private BaseMediaChunk canceledMediaChunk;
    private final BaseMediaChunkOutput chunkOutput;
    private final T chunkSource;
    private final SampleQueue[] embeddedSampleQueues;
    private final Format[] embeddedTrackFormats;
    private final int[] embeddedTrackTypes;
    private final boolean[] embeddedTracksSelected;
    private boolean hasInitialDiscontinuity;
    private long lastSeekPositionUs;
    private final LoadErrorHandlingPolicy loadErrorHandlingPolicy;
    private final Loader loader;
    private Chunk loadingChunk;
    boolean loadingFinished;
    private final ArrayList<BaseMediaChunk> mediaChunks;
    private final MediaSourceEventListener.EventDispatcher mediaSourceEventDispatcher;
    private final ChunkHolder nextChunkHolder;
    private int nextNotifyPrimaryFormatMediaChunkIndex;
    private long pendingResetPositionUs;
    private Format primaryDownstreamTrackFormat;
    private final SampleQueue primarySampleQueue;
    public final int primaryTrackType;
    private final List<BaseMediaChunk> readOnlyMediaChunks;
    private ReleaseCallback<T> releaseCallback;

    public final class EmbeddedSampleStream implements SampleStream {
        private final int index;
        private boolean notifiedDownstreamFormat;
        public final ChunkSampleStream<T> parent;
        private final SampleQueue sampleQueue;
        final /* synthetic */ ChunkSampleStream this$0;

        public EmbeddedSampleStream(ChunkSampleStream r1, ChunkSampleStream<T> r2, SampleQueue r3, int r4) {
            this.this$0 = r1;
            this.parent = r2;
            this.sampleQueue = r3;
            this.index = r4;
        }

        private void maybeNotifyDownstreamFormat() {
            if (this.notifiedDownstreamFormat == true) goto L6;
            ChunkSampleStream.access$500(this.this$0).downstreamFormatChanged(ChunkSampleStream.access$200(this.this$0)[this.index], ChunkSampleStream.access$300(this.this$0)[this.index], 0, null, ChunkSampleStream.access$400(this.this$0));
            this.notifiedDownstreamFormat = true;
            return;
        }

        @Override // androidx.media3.exoplayer.source.SampleStream
        public boolean isReady() {
            if (this.this$0.isPendingReset() == false) goto L5;
            return false;
        L5:
            if (this.sampleQueue.isReady(this.this$0.loadingFinished) == false) goto L10;
            return true;
        L10:
            return false;
        }

        @Override // androidx.media3.exoplayer.source.SampleStream
        public void maybeThrowError() {
        }

        @Override // androidx.media3.exoplayer.source.SampleStream
        public int readData(FormatHolder r4, DecoderInputBuffer r5, int r6) {
            if (this.this$0.isPendingReset() == false) goto L6;
            return -3;
        L6:
            if (ChunkSampleStream.access$000(this.this$0) != null) goto L8;
        L10:
            maybeNotifyDownstreamFormat();
            return this.sampleQueue.read(r4, r5, r6, this.this$0.loadingFinished);
        L8:
            if (ChunkSampleStream.access$000(this.this$0).getFirstSampleIndex(this.index + 1) > this.sampleQueue.getReadIndex()) goto L10;
            return -3;
        }

        public void release() {
            Assertions.checkState(ChunkSampleStream.access$100(this.this$0)[this.index]);
            ChunkSampleStream.access$100(this.this$0)[this.index] = false;
        }

        @Override // androidx.media3.exoplayer.source.SampleStream
        public int skipData(long r3) {
            if (this.this$0.isPendingReset() == false) goto L6;
            return 0;
        L6:
            int r32 = this.sampleQueue.getSkipCount(r3, this.this$0.loadingFinished);
            if (ChunkSampleStream.access$000(this.this$0) == null) goto L9;
            r32 = Math.min(r32, ChunkSampleStream.access$000(this.this$0).getFirstSampleIndex(this.index + 1) - this.sampleQueue.getReadIndex());
        L9:
            this.sampleQueue.skip(r32);
            if (r32 <= 0) goto L12;
            maybeNotifyDownstreamFormat();
        L12:
            return r32;
        }
    }

    public interface ReleaseCallback<T extends ChunkSource> {
        void onSampleStreamReleased(ChunkSampleStream<T> r1);
    }

    public ChunkSampleStream(int r2, int[] r3, Format[] r4, T r5, SequenceableLoader.Callback<ChunkSampleStream<T>> r6, Allocator r7, long r8, DrmSessionManager r10, DrmSessionEventListener.EventDispatcher r11, LoadErrorHandlingPolicy r12, MediaSourceEventListener.EventDispatcher r13, boolean r14, ReleasableExecutor r15) {
        this.primaryTrackType = r2;
        int r0 = 0;
        if (r3 != null) goto L5;
        r3 = new int[0];
    L5:
        this.embeddedTrackTypes = r3;
        if (r4 != null) goto L8;
        r4 = new Format[0];
    L8:
        this.embeddedTrackFormats = r4;
        this.chunkSource = r5;
        this.callback = r6;
        this.mediaSourceEventDispatcher = r13;
        this.loadErrorHandlingPolicy = r12;
        this.canReportInitialDiscontinuity = r14;
        if (r15 == null) goto L11;
        Loader r42 = new Loader(r15);
    L12:
        this.loader = r42;
        this.nextChunkHolder = new ChunkHolder();
        ArrayList<BaseMediaChunk> r43 = new ArrayList();
        this.mediaChunks = r43;
        this.readOnlyMediaChunks = Collections.unmodifiableList(r43);
        int r32 = r3.length;
        this.embeddedSampleQueues = new SampleQueue[r32];
        this.embeddedTracksSelected = new boolean[r32];
        int r44 = r32 + 1;
        int[] r52 = new int[r44];
        SampleQueue[] r45 = new SampleQueue[r44];
        SampleQueue r62 = SampleQueue.createWithDrm(r7, r10, r11);
        this.primarySampleQueue = r62;
        r52[0] = r2;
        r45[0] = r62;
    L13:
        if (r0 >= r32) goto L15;
        SampleQueue r22 = SampleQueue.createWithoutDrm(r7);
        this.embeddedSampleQueues[r0] = r22;
        int r63 = r0 + 1;
        r45[r63] = r22;
        r52[r63] = this.embeddedTrackTypes[r0];
        r0 = r63;
        goto L13
    L15:
        this.chunkOutput = new BaseMediaChunkOutput(r52, r45);
        this.pendingResetPositionUs = r8;
        this.lastSeekPositionUs = r8;
        return;
    L11:
        r42 = new Loader("ChunkSampleStream");
        goto L12
    }

    public static /* synthetic */ BaseMediaChunk access$000(ChunkSampleStream r0) {
        return r0.canceledMediaChunk;
    }

    public static /* synthetic */ boolean[] access$100(ChunkSampleStream r0) {
        return r0.embeddedTracksSelected;
    }

    public static /* synthetic */ int[] access$200(ChunkSampleStream r0) {
        return r0.embeddedTrackTypes;
    }

    public static /* synthetic */ Format[] access$300(ChunkSampleStream r0) {
        return r0.embeddedTrackFormats;
    }

    public static /* synthetic */ long access$400(ChunkSampleStream r2) {
        return r2.lastSeekPositionUs;
    }

    public static /* synthetic */ MediaSourceEventListener.EventDispatcher access$500(ChunkSampleStream r0) {
        return r0.mediaSourceEventDispatcher;
    }

    private void discardDownstreamMediaChunks(int r3) {
        int r32 = Math.min(primarySampleIndexToMediaChunkIndex(r3, 0), this.nextNotifyPrimaryFormatMediaChunkIndex);
        if (r32 <= 0) goto L6;
        Util.removeRange(this.mediaChunks, 0, r32);
        this.nextNotifyPrimaryFormatMediaChunkIndex -= r32;
        return;
    }

    private void discardUpstream(int r8) {
        Assertions.checkState(!this.loader.isLoading());
        int r0 = this.mediaChunks.size();
    L4:
        if (r8 >= r0) goto L9;
        if (haveReadFromMediaChunk(r8) == false) goto L10;
        r8 = r8 + 1;
    L10:
        if (r8 != (-1)) goto L12;
        return;
    L12:
        long r5 = getLastMediaChunk().endTimeUs;
        BaseMediaChunk r82 = discardUpstreamMediaChunksFromIndex(r8);
        if (this.mediaChunks.isEmpty() == false) goto L15;
        this.pendingResetPositionUs = this.lastSeekPositionUs;
    L15:
        this.loadingFinished = false;
        this.mediaSourceEventDispatcher.upstreamDiscarded(this.primaryTrackType, r82.startTimeUs, r5);
        return;
    L9:
        r8 = -1;
        goto L10
    }

    private BaseMediaChunk discardUpstreamMediaChunksFromIndex(int r4) {
        BaseMediaChunk r0 = this.mediaChunks.get(r4);
        ArrayList<BaseMediaChunk> r1 = this.mediaChunks;
        Util.removeRange(r1, r4, r1.size());
        this.nextNotifyPrimaryFormatMediaChunkIndex = Math.max(this.nextNotifyPrimaryFormatMediaChunkIndex, this.mediaChunks.size());
        int r12 = 0;
        this.primarySampleQueue.discardUpstreamSamples(r0.getFirstSampleIndex(0));
    L3:
        SampleQueue[] r42 = this.embeddedSampleQueues;
        if (r12 >= r42.length) goto L6;
        SampleQueue r43 = r42[r12];
        r12 = r12 + 1;
        r43.discardUpstreamSamples(r0.getFirstSampleIndex(r12));
        goto L3
    L6:
        return r0;
    }

    private BaseMediaChunk getLastMediaChunk() {
        return this.mediaChunks.get(r1.size() - 1);
    }

    private boolean haveReadFromMediaChunk(int r6) {
        BaseMediaChunk r62 = this.mediaChunks.get(r6);
        if (this.primarySampleQueue.getReadIndex() <= r62.getFirstSampleIndex(0)) goto L5;
        return true;
    L5:
        int r0 = 0;
    L6:
        SampleQueue[] r2 = this.embeddedSampleQueues;
        if (r0 >= r2.length) goto L11;
        int r22 = r2[r0].getReadIndex();
        r0 = r0 + 1;
        if (r22 <= r62.getFirstSampleIndex(r0)) goto L6;
        return true;
    L11:
        return false;
    }

    private boolean isMediaChunk(Chunk r1) {
        return r1 instanceof BaseMediaChunk;
    }

    private void maybeNotifyPrimaryTrackFormatChanged() {
        int r0 = primarySampleIndexToMediaChunkIndex(this.primarySampleQueue.getReadIndex(), this.nextNotifyPrimaryFormatMediaChunkIndex - 1);
    L3:
        int r1 = this.nextNotifyPrimaryFormatMediaChunkIndex;
        if (r1 > r0) goto L6;
        this.nextNotifyPrimaryFormatMediaChunkIndex = r1 + 1;
        maybeNotifyPrimaryTrackFormatChanged(r1);
        goto L3
    }

    private void maybeNotifyPrimaryTrackFormatChanged(int r8) {
        BaseMediaChunk r82 = this.mediaChunks.get(r8);
        Format r2 = r82.trackFormat;
        if (r2.equals(this.primaryDownstreamTrackFormat) == true) goto L5;
        this.mediaSourceEventDispatcher.downstreamFormatChanged(this.primaryTrackType, r2, r82.trackSelectionReason, r82.trackSelectionData, r82.startTimeUs);
    L5:
        this.primaryDownstreamTrackFormat = r2;
    }

    private int primarySampleIndexToMediaChunkIndex(int r3, int r4) {
    L2:
        r4 = r4 + 1;
        int r0 = this.mediaChunks.size();
        ArrayList<BaseMediaChunk> r1 = this.mediaChunks;
        if (r4 >= r0) goto L9;
        if (r1.get(r4).getFirstSampleIndex(0) <= r3) goto L2;
        return r4 - 1;
    L9:
        return r1.size() - 1;
    }

    private void resetSampleQueues() {
        this.primarySampleQueue.reset();
        SampleQueue[] r3 = this.embeddedSampleQueues;
        int r0 = r3.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        r3[r1].reset();
        r1 = r1 + 1;
        goto L3
    }

    public boolean consumeInitialDiscontinuity() {
        boolean r1 = this.hasInitialDiscontinuity;     // Catch: Throwable -> L6
        this.hasInitialDiscontinuity = false;
        return r1;
    L6:
        th = move-exception;
        this.hasInitialDiscontinuity = false;
        throw th;
    }

    @Override // androidx.media3.exoplayer.source.SequenceableLoader
    public boolean continueLoading(LoadingInfo r12) {
        if (this.loadingFinished == false) goto L5;
    L39:
        return false;
    L5:
        if (this.loader.isLoading() == true) goto L39;
        if (this.loader.hasFatalError() == true) goto L39;
        boolean r0 = isPendingReset();
        if (r0 == false) goto L13;
        List<BaseMediaChunk> r2 = Collections.EMPTY_LIST;
        long r3 = this.pendingResetPositionUs;
    L14:
        this.chunkSource.getNextChunk(r12, r3, r2, this.nextChunkHolder);
        ChunkHolder r122 = this.nextChunkHolder;
        boolean r22 = r122.endOfStream;
        Chunk r32 = r122.chunk;
        r122.clear();
        if (r22 == false) goto L18;
        this.pendingResetPositionUs = -9223372036854775807L;
        this.loadingFinished = true;
        return true;
    L18:
        if (r32 != null) goto L20;
        return false;
    L20:
        this.loadingChunk = r32;
        if (isMediaChunk(r32) == false) goto L35;
        BaseMediaChunk r23 = (BaseMediaChunk) r32;
        if (r0 == false) goto L33;
        long r6 = r23.startTimeUs;
        long r8 = this.pendingResetPositionUs;
        if (r6 >= r8) goto L32;
        this.primarySampleQueue.setStartTimeUs(r8);
        SampleQueue[] r02 = this.embeddedSampleQueues;
        int r62 = r02.length;
        int r7 = 0;
    L27:
        if (r7 >= r62) goto L30;
        r02[r7].setStartTimeUs(this.pendingResetPositionUs);
        r7 = r7 + 1;
        goto L27
    L30:
        if (this.canReportInitialDiscontinuity == false) goto L32;
        Format r03 = r23.trackFormat;
        this.hasInitialDiscontinuity = !MimeTypes.allSamplesAreSyncSamples(r03.sampleMimeType, r03.codecs);
    L32:
        this.canReportInitialDiscontinuity = false;
        this.pendingResetPositionUs = -9223372036854775807L;
    L33:
        r23.init(this.chunkOutput);
        this.mediaChunks.add(r23);
    L37:
        this.loader.startLoading(r32, this, this.loadErrorHandlingPolicy.getMinimumLoadableRetryCount(r32.type));
        return true;
    L35:
        if ((r32 instanceof InitializationChunk) == false) goto L37;
        ((InitializationChunk) r32).init(this.chunkOutput);
        goto L37
    L13:
        r2 = this.readOnlyMediaChunks;
        r3 = getLastMediaChunk().endTimeUs;
        goto L14
    }

    public void discardBuffer(long r5, boolean r7) {
        if (isPendingReset() == false) goto L5;
        return;
    L5:
        int r0 = this.primarySampleQueue.getFirstIndex();
        this.primarySampleQueue.discardTo(r5, r7, true);
        int r52 = this.primarySampleQueue.getFirstIndex();
        if (r52 <= r0) goto L11;
        long r02 = this.primarySampleQueue.getFirstTimestampUs();
        int r6 = 0;
    L8:
        SampleQueue[] r2 = this.embeddedSampleQueues;
        if (r6 >= r2.length) goto L11;
        r2[r6].discardTo(r02, r7, this.embeddedTracksSelected[r6]);
        r6 = r6 + 1;
    L11:
        discardDownstreamMediaChunks(r52);
    }

    public void discardUpstreamSamplesForClippedDuration(long r11) {
        Assertions.checkState(!this.loader.isLoading());
        if (isPendingReset() == false) goto L5;
        return;
    L5:
        if (r11 != (-9223372036854775807L)) goto L7;
        return;
    L7:
        if (this.mediaChunks.isEmpty() == true) goto L27;
        BaseMediaChunk r2 = getLastMediaChunk();
        long r3 = r2.clippedEndTimeUs;
        if (r3 != (-9223372036854775807L)) goto L14;
        r3 = r2.endTimeUs;
    L14:
        if (r3 <= r11) goto L28;
        long r5 = this.primarySampleQueue.getLargestQueuedTimestampUs();
        if (r5 <= r11) goto L29;
        this.primarySampleQueue.discardUpstreamFrom(Math.max(r11, this.primarySampleQueue.getLargestReadTimestampUs() + 1));
        SampleQueue[] r0 = this.embeddedSampleQueues;
        int r1 = r0.length;
        int r4 = 0;
    L20:
        if (r4 >= r1) goto L22;
        SampleQueue r7 = r0[r4];
        r7.discardUpstreamFrom(Math.max(r11, r7.getLargestReadTimestampUs() + 1));
        r4 = r4 + 1;
        goto L20
    L22:
        this.mediaSourceEventDispatcher.upstreamDiscarded(this.primaryTrackType, r11, r5);
        return;
    L29:
        return;
    L28:
        return;
    }

    public long getAdjustedSeekPositionUs(long r1, SeekParameters r3) {
        return this.chunkSource.getAdjustedSeekPositionUs(r1, r3);
    }

    @Override // androidx.media3.exoplayer.source.SequenceableLoader
    public long getBufferedPositionUs() {
        if (this.loadingFinished == false) goto L7;
        return Long.MIN_VALUE;
    L7:
        if (isPendingReset() == true) goto L9;
        long r0 = this.lastSeekPositionUs;
        BaseMediaChunk r2 = getLastMediaChunk();
        if (r2.isLoadCompleted() == false) goto L14;
    L17:
        if (r2 == null) goto L20;
        r0 = Math.max(r0, r2.endTimeUs);
    L20:
        return Math.max(r0, this.primarySampleQueue.getLargestQueuedTimestampUs());
    L14:
        if (this.mediaChunks.size() <= 1) goto L16;
        r2 = this.mediaChunks.get(r2.size() - 2);
        goto L17
    L16:
        r2 = null;
        goto L17
    L9:
        return this.pendingResetPositionUs;
    }

    public T getChunkSource() {
        return this.chunkSource;
    }

    @Override // androidx.media3.exoplayer.source.SequenceableLoader
    public long getNextLoadPositionUs() {
        if (isPendingReset() == false) goto L7;
        return this.pendingResetPositionUs;
    L7:
        if (this.loadingFinished == false) goto L11;
        return Long.MIN_VALUE;
    L11:
        return getLastMediaChunk().endTimeUs;
    }

    @Override // androidx.media3.exoplayer.source.SequenceableLoader
    public boolean isLoading() {
        return this.loader.isLoading();
    }

    public boolean isPendingReset() {
        if (this.pendingResetPositionUs == (-9223372036854775807L)) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // androidx.media3.exoplayer.source.SampleStream
    public boolean isReady() {
        if (isPendingReset() == false) goto L5;
        return false;
    L5:
        if (this.primarySampleQueue.isReady(this.loadingFinished) == false) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // androidx.media3.exoplayer.source.SampleStream
    public void maybeThrowError() {
        this.loader.maybeThrowError();
        this.primarySampleQueue.maybeThrowError();
        if (this.loader.isLoading() == true) goto L6;
        this.chunkSource.maybeThrowError();
        return;
    }

    public void onLoadCanceled(Chunk r14, long r15, long r17, boolean r19) {
        this.loadingChunk = null;
        this.canceledMediaChunk = null;
        LoadEventInfo r1 = new LoadEventInfo(r14.loadTaskId, r14.dataSpec, r14.getUri(), r14.getResponseHeaders(), r15, r17, r14.bytesLoaded());
        this.loadErrorHandlingPolicy.onLoadTaskConcluded(r14.loadTaskId);
        this.mediaSourceEventDispatcher.loadCanceled(r1, r14.type, this.primaryTrackType, r14.trackFormat, r14.trackSelectionReason, r14.trackSelectionData, r14.startTimeUs, r14.endTimeUs);
        if (r19 == false) goto L5;
        return;
    L5:
        if (isPendingReset() == false) goto L8;
        resetSampleQueues();
    L12:
        this.callback.onContinueLoadingRequested(this);
        return;
    L8:
        if (isMediaChunk(r14) == false) goto L12;
        discardUpstreamMediaChunksFromIndex(this.mediaChunks.size() - 1);
        if (this.mediaChunks.isEmpty() == false) goto L12;
        this.pendingResetPositionUs = this.lastSeekPositionUs;
        goto L12
    }

    @Override // androidx.media3.exoplayer.upstream.Loader.Callback
    public /* bridge */ /* synthetic */ void onLoadCanceled(Loader.Loadable r1, long r2, long r4, boolean r6) {
        onLoadCanceled((Chunk) r1, r2, r4, r6);
    }

    public void onLoadCompleted(Chunk r14, long r15, long r17) {
        this.loadingChunk = null;
        this.chunkSource.onChunkLoadCompleted(r14);
        LoadEventInfo r1 = new LoadEventInfo(r14.loadTaskId, r14.dataSpec, r14.getUri(), r14.getResponseHeaders(), r15, r17, r14.bytesLoaded());
        this.loadErrorHandlingPolicy.onLoadTaskConcluded(r14.loadTaskId);
        this.mediaSourceEventDispatcher.loadCompleted(r1, r14.type, this.primaryTrackType, r14.trackFormat, r14.trackSelectionReason, r14.trackSelectionData, r14.startTimeUs, r14.endTimeUs);
        this.callback.onContinueLoadingRequested(this);
    }

    @Override // androidx.media3.exoplayer.upstream.Loader.Callback
    public /* bridge */ /* synthetic */ void onLoadCompleted(Loader.Loadable r1, long r2, long r4) {
        onLoadCompleted((Chunk) r1, r2, r4);
    }

    public Loader.LoadErrorAction onLoadError(Chunk r30, long r31, long r33, IOException r35, int r36) {
        long r12 = r30.bytesLoaded();
        boolean r14 = isMediaChunk(r30);
        int r2 = this.mediaChunks.size() - 1;
        if (r12 == 0) goto L9;
        if (r14 == false) goto L9;
        if (haveReadFromMediaChunk(r2) == false) goto L9;
        boolean r3 = false;
    L10:
        boolean r5 = r3;
        LoadEventInfo r17 = new LoadEventInfo(r30.loadTaskId, r30.dataSpec, r30.getUri(), r30.getResponseHeaders(), r31, r33, r12);
        LoadErrorHandlingPolicy.LoadErrorInfo r4 = new LoadErrorHandlingPolicy.LoadErrorInfo(r17, new MediaLoadData(r30.type, this.primaryTrackType, r30.trackFormat, r30.trackSelectionReason, r30.trackSelectionData, Util.usToMs(r30.startTimeUs), Util.usToMs(r30.endTimeUs)), r35, r36);
        if (this.chunkSource.onChunkLoadError(r30, r5, r4, this.loadErrorHandlingPolicy) == false) goto L23;
        if (r5 == false) goto L22;
        Loader.LoadErrorAction r32 = Loader.DONT_RETRY;
        if (r14 == true) goto L16;
    L24:
        if (r32 != null) goto L29;
        long r34 = this.loadErrorHandlingPolicy.getRetryDelayMsFor(r4);
        if (r34 == (-9223372036854775807L)) goto L28;
        r32 = Loader.createRetryAction(false, r34);
        goto L29
    L28:
        r32 = Loader.DONT_RETRY_FATAL;
    L29:
        boolean r42 = r32.isRetry();
        this.mediaSourceEventDispatcher.loadError(r17, r30.type, this.primaryTrackType, r30.trackFormat, r30.trackSelectionReason, r30.trackSelectionData, r30.startTimeUs, r30.endTimeUs, r35, !r42);
        if (r42 == true) goto L32;
        this.loadingChunk = null;
        this.loadErrorHandlingPolicy.onLoadTaskConcluded(r30.loadTaskId);
        this.callback.onContinueLoadingRequested(this);
    L32:
        return r32;
    L16:
        if (discardUpstreamMediaChunksFromIndex(r2) != r30) goto L18;
        boolean r15 = true;
    L19:
        Assertions.checkState(r15);
        if (this.mediaChunks.isEmpty() == false) goto L24;
        this.pendingResetPositionUs = this.lastSeekPositionUs;
        goto L24
    L18:
        r15 = false;
        goto L19
    L22:
        Log.w("ChunkSampleStream", "Ignoring attempt to cancel non-cancelable load.");
    L23:
        r32 = null;
    L9:
        r3 = true;
        goto L10
    }

    @Override // androidx.media3.exoplayer.upstream.Loader.Callback
    public /* bridge */ /* synthetic */ Loader.LoadErrorAction onLoadError(Loader.Loadable r1, long r2, long r4, IOException r6, int r7) {
        return onLoadError((Chunk) r1, r2, r4, r6, r7);
    }

    public void onLoadStarted(Chunk r17, long r18, long r20, int r22) {
        if (r22 != 0) goto L5;
        LoadEventInfo r5 = new LoadEventInfo(r17.loadTaskId, r17.dataSpec, r18);
    L6:
        this.mediaSourceEventDispatcher.loadStarted(r5, r17.type, this.primaryTrackType, r17.trackFormat, r17.trackSelectionReason, r17.trackSelectionData, r17.startTimeUs, r17.endTimeUs, r22);
        return;
    L5:
        r5 = new LoadEventInfo(r17.loadTaskId, r17.dataSpec, r17.getUri(), r17.getResponseHeaders(), r18, r20, r17.bytesLoaded());
        goto L6
    }

    @Override // androidx.media3.exoplayer.upstream.Loader.Callback
    public /* bridge */ /* synthetic */ void onLoadStarted(Loader.Loadable r1, long r2, long r4, int r6) {
        onLoadStarted((Chunk) r1, r2, r4, r6);
    }

    @Override // androidx.media3.exoplayer.upstream.Loader.ReleaseCallback
    public void onLoaderReleased() {
        this.primarySampleQueue.release();
        SampleQueue[] r0 = this.embeddedSampleQueues;
        int r1 = r0.length;
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L5;
        r0[r2].release();
        r2 = r2 + 1;
        goto L3
    L5:
        this.chunkSource.release();
        ReleaseCallback<T> r02 = this.releaseCallback;
        if (r02 == null) goto L10;
        r02.onSampleStreamReleased(this);
        return;
    }

    @Override // androidx.media3.exoplayer.source.SampleStream
    public int readData(FormatHolder r4, DecoderInputBuffer r5, int r6) {
        if (isPendingReset() == false) goto L5;
        return -3;
    L5:
        BaseMediaChunk r0 = this.canceledMediaChunk;
        if (r0 != null) goto L8;
    L10:
        maybeNotifyPrimaryTrackFormatChanged();
        return this.primarySampleQueue.read(r4, r5, r6, this.loadingFinished);
    L8:
        if (r0.getFirstSampleIndex(0) > this.primarySampleQueue.getReadIndex()) goto L10;
        return -3;
    }

    @Override // androidx.media3.exoplayer.source.SequenceableLoader
    public void reevaluateBuffer(long r4) {
        if (this.loader.hasFatalError() == false) goto L5;
        return;
    L5:
        if (isPendingReset() == false) goto L8;
        return;
    L8:
        if (this.loader.isLoading() == false) goto L20;
        Chunk r0 = (Chunk) Assertions.checkNotNull(this.loadingChunk);
        if (isMediaChunk(r0) == false) goto L15;
        if (haveReadFromMediaChunk(this.mediaChunks.size() - 1) == false) goto L15;
        return;
    L15:
        if (this.chunkSource.shouldCancelLoad(r4, r0, this.readOnlyMediaChunks) == false) goto L25;
        this.loader.cancelLoading();
        if (isMediaChunk(r0) == false) goto L26;
        this.canceledMediaChunk = (BaseMediaChunk) r0;
        return;
    L26:
        return;
    L25:
        return;
    L20:
        int r42 = this.chunkSource.getPreferredQueueSize(r4, this.readOnlyMediaChunks);
        if (r42 >= this.mediaChunks.size()) goto L27;
        discardUpstream(r42);
        return;
    }

    public void release(ReleaseCallback<T> r4) {
        this.releaseCallback = r4;
        this.primarySampleQueue.preRelease();
        SampleQueue[] r42 = this.embeddedSampleQueues;
        int r0 = r42.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        r42[r1].preRelease();
        r1 = r1 + 1;
        goto L3
    L5:
        this.loader.release(this);
    }

    public void seekToUs(long r9) {
        this.lastSeekPositionUs = r9;
        int r0 = 0;
        this.canReportInitialDiscontinuity = false;
        if (isPendingReset() == false) goto L6;
        this.pendingResetPositionUs = r9;
        return;
    L6:
        int r1 = 0;
    L8:
        if (r1 >= this.mediaChunks.size()) goto L17;
        BaseMediaChunk r2 = this.mediaChunks.get(r1);
        long r3 = r2.startTimeUs;
        if (r3 == r9) goto L12;
    L14:
        if (r3 > r9) goto L17;
        r1 = r1 + 1;
        goto L8
    L12:
        if (r2.clippedStartTimeUs != (-9223372036854775807L)) goto L14;
    L19:
        if (r2 == null) goto L21;
        boolean r22 = this.primarySampleQueue.seekTo(r2.getFirstSampleIndex(0));
    L29:
        if (r22 == false) goto L34;
        this.nextNotifyPrimaryFormatMediaChunkIndex = primarySampleIndexToMediaChunkIndex(this.primarySampleQueue.getReadIndex(), 0);
        SampleQueue[] r8 = this.embeddedSampleQueues;
        int r23 = r8.length;
    L31:
        if (r0 >= r23) goto L33;
        r8[r0].seekTo(r9, true);
        r0 = r0 + 1;
        goto L31
    L33:
        return;
    L34:
        this.pendingResetPositionUs = r9;
        this.loadingFinished = false;
        this.mediaChunks.clear();
        this.nextNotifyPrimaryFormatMediaChunkIndex = 0;
        if (this.loader.isLoading() == false) goto L41;
        this.primarySampleQueue.discardToEnd();
        SampleQueue[] r92 = this.embeddedSampleQueues;
        int r10 = r92.length;
    L37:
        if (r0 >= r10) goto L39;
        r92[r0].discardToEnd();
        r0 = r0 + 1;
        goto L37
    L39:
        this.loader.cancelLoading();
        return;
    L41:
        this.loader.clearFatalError();
        resetSampleQueues();
        return;
    L21:
        long r24 = getNextLoadPositionUs();
        if (r24 != Long.MIN_VALUE) goto L24;
    L27:
        boolean r25 = true;
    L28:
        r22 = this.primarySampleQueue.seekTo(r9, r25);
        goto L29
    L24:
        if (r9 < r24) goto L27;
        r25 = false;
    L17:
        r2 = null;
        goto L19
    }

    public ChunkSampleStream<T>.EmbeddedSampleStream selectEmbeddedTrack(long r3, int r5) {
        int r0 = 0;
    L4:
        if (r0 >= this.embeddedSampleQueues.length) goto L10;
        if (this.embeddedTrackTypes[r0] == r5) goto L7;
        r0 = r0 + 1;
        goto L4
    L7:
        Assertions.checkState(!this.embeddedTracksSelected[r0]);
        this.embeddedTracksSelected[r0] = true;
        this.embeddedSampleQueues[r0].seekTo(r3, true);
        return new EmbeddedSampleStream(this, this, this.embeddedSampleQueues[r0], r0);
    L10:
        MethodWriter$$ExternalSyntheticBUOutline0.m();
        return null;
    }

    @Override // androidx.media3.exoplayer.source.SampleStream
    public int skipData(long r4) {
        if (isPendingReset() == false) goto L5;
        return 0;
    L5:
        int r42 = this.primarySampleQueue.getSkipCount(r4, this.loadingFinished);
        BaseMediaChunk r5 = this.canceledMediaChunk;
        if (r5 == null) goto L8;
        r42 = Math.min(r42, r5.getFirstSampleIndex(0) - this.primarySampleQueue.getReadIndex());
    L8:
        this.primarySampleQueue.skip(r42);
        maybeNotifyPrimaryTrackFormatChanged();
        return r42;
    }
}
