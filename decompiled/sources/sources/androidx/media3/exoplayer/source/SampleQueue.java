package androidx.media3.exoplayer.source;

import androidx.media3.common.DataReader;
import androidx.media3.common.DrmInitData;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.exoplayer.FormatHolder;
import androidx.media3.exoplayer.drm.DrmSession;
import androidx.media3.exoplayer.drm.DrmSessionEventListener;
import androidx.media3.exoplayer.drm.DrmSessionManager;
import androidx.media3.exoplayer.upstream.Allocator;
import androidx.media3.extractor.TrackOutput;
import java.util.Objects;
import kotlin.jvm.internal.LongCompanionObject;
import org.telegram.tgnet.TLObject;

/* loaded from: classes4.dex */
public class SampleQueue implements TrackOutput {
    private int absoluteFirstIndex;
    private boolean allSamplesAreSyncSamples;
    private int capacity;
    private TrackOutput.CryptoData[] cryptoDatas;
    private DrmSession currentDrmSession;
    private Format downstreamFormat;
    private final DrmSessionEventListener.EventDispatcher drmEventDispatcher;
    private final DrmSessionManager drmSessionManager;
    private final SampleExtrasHolder extrasHolder;
    private int[] flags;
    private boolean isLastSampleQueued;
    private long largestDiscardedTimestampUs;
    private long largestQueuedTimestampUs;
    private int length;
    private boolean loggedUnexpectedNonSyncSample;
    private long[] offsets;
    private boolean pendingSplice;
    private int readPosition;
    private int relativeFirstIndex;
    private final SampleDataQueue sampleDataQueue;
    private long sampleOffsetUs;
    private final SpannedData<SharedSampleMetadata> sharedSampleMetadata;
    private int[] sizes;
    private long[] sourceIds;
    private long startTimeUs;
    private long[] timesUs;
    private Format unadjustedUpstreamFormat;
    private Format upstreamFormat;
    private boolean upstreamFormatAdjustmentRequired;
    private UpstreamFormatChangedListener upstreamFormatChangeListener;
    private boolean upstreamFormatRequired;
    private boolean upstreamKeyframeRequired;
    private long upstreamSourceId;

    /* renamed from: androidx.media3.exoplayer.source.SampleQueue$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class SampleExtrasHolder {
        public TrackOutput.CryptoData cryptoData;
        public long offset;
        public int size;

        public SampleExtrasHolder() {
        }
    }

    public static final class SharedSampleMetadata {
        public final DrmSessionManager.DrmSessionReference drmSessionReference;
        public final Format format;

        private SharedSampleMetadata(Format r1, DrmSessionManager.DrmSessionReference r2) {
            this.format = r1;
            this.drmSessionReference = r2;
        }

        public /* synthetic */ SharedSampleMetadata(Format r1, DrmSessionManager.DrmSessionReference r2, AnonymousClass1 r3) {
            this(r1, r2);
        }
    }

    public interface UpstreamFormatChangedListener {
        void onUpstreamFormatChanged(Format r1);
    }

    public static /* synthetic */ void $r8$lambda$LsBWqNpydWGqbShsuZ5RDoYAwVM(SharedSampleMetadata r0) {
        r0.drmSessionReference.release();
    }

    public SampleQueue(Allocator r1, DrmSessionManager r2, DrmSessionEventListener.EventDispatcher r3) {
        this.drmSessionManager = r2;
        this.drmEventDispatcher = r3;
        this.sampleDataQueue = new SampleDataQueue(r1);
        this.extrasHolder = new SampleExtrasHolder();
        this.capacity = 1000;
        this.sourceIds = new long[1000];
        this.offsets = new long[1000];
        this.timesUs = new long[1000];
        this.flags = new int[1000];
        this.sizes = new int[1000];
        this.cryptoDatas = new TrackOutput.CryptoData[1000];
        this.sharedSampleMetadata = new SpannedData(new SampleQueue$$ExternalSyntheticLambda0());
        this.startTimeUs = Long.MIN_VALUE;
        this.largestDiscardedTimestampUs = Long.MIN_VALUE;
        this.largestQueuedTimestampUs = Long.MIN_VALUE;
        this.upstreamFormatRequired = true;
        this.upstreamKeyframeRequired = true;
        this.allSamplesAreSyncSamples = true;
    }

    private synchronized boolean attemptSplice(long r6) {
        monitor-enter(this);
        boolean r1 = false;
        if (this.length != 0) goto L15;
        if (r6 <= this.largestDiscardedTimestampUs) goto L9;
        r1 = true;
    L9:
        monitor-exit(this);
        return r1;
    L15:
        if (getLargestReadTimestampUs() < r6) goto L18;
        monitor-exit(this);
        return false;
    L18:
        discardUpstreamSampleMetadata(this.absoluteFirstIndex + countUnreadSamplesBefore(r6));     // Catch: Throwable -> L11
        monitor-exit(this);
        return true;
    L11:
        th = move-exception;
        throw th;
    }

    private synchronized void commitSample(long r9, int r11, long r12, int r14, TrackOutput.CryptoData r15) {
        monitor-enter(this);
        int r0 = this.length;     // Catch: Throwable -> L10
        if (r0 <= 0) goto L13;
        if ((this.offsets[getRelativeIndex(r0 - 1)] + this.sizes[r0]) > r12) goto L8;
        boolean r02 = true;
    L9:
        Assertions.checkArgument(r02);     // Catch: Throwable -> L10
        goto L13
    L8:
        r02 = false;
    L13:
        if ((536870912 & r11) == 0) goto L15;
        boolean r03 = true;
    L16:
        this.isLastSampleQueued = r03;     // Catch: Throwable -> L10
        this.largestQueuedTimestampUs = Math.max(this.largestQueuedTimestampUs, r9);     // Catch: Throwable -> L10
        int r04 = getRelativeIndex(this.length);     // Catch: Throwable -> L10
        this.timesUs[r04] = r9;     // Catch: Throwable -> L10
        this.offsets[r04] = r12;     // Catch: Throwable -> L10
        this.sizes[r04] = r14;     // Catch: Throwable -> L10
        this.flags[r04] = r11;     // Catch: Throwable -> L10
        this.cryptoDatas[r04] = r15;     // Catch: Throwable -> L10
        this.sourceIds[r04] = this.upstreamSourceId;     // Catch: Throwable -> L10
        if (this.sharedSampleMetadata.isEmpty() == false) goto L19;
    L20:
        Format r92 = (Format) Assertions.checkNotNull(this.upstreamFormat);     // Catch: Throwable -> L10
        DrmSessionManager r10 = this.drmSessionManager;     // Catch: Throwable -> L10
        if (r10 == null) goto L23;
        DrmSessionManager.DrmSessionReference r102 = r10.preacquireSession(this.drmEventDispatcher, r92);     // Catch: Throwable -> L10
    L24:
        this.sharedSampleMetadata.appendSpan(getWriteIndex(), new SharedSampleMetadata(r92, r102, null));     // Catch: Throwable -> L10
    L25:
        int r93 = this.length + 1;     // Catch: Throwable -> L10
        this.length = r93;     // Catch: Throwable -> L10
        int r103 = this.capacity;     // Catch: Throwable -> L10
        if (r93 != r103) goto L28;
        int r94 = r103 + 1000;     // Catch: Throwable -> L10
        long[] r112 = new long[r94];     // Catch: Throwable -> L10
        long[] r122 = new long[r94];     // Catch: Throwable -> L10
        long[] r13 = new long[r94];     // Catch: Throwable -> L10
        int[] r142 = new int[r94];     // Catch: Throwable -> L10
        int[] r152 = new int[r94];     // Catch: Throwable -> L10
        TrackOutput.CryptoData[] r05 = new TrackOutput.CryptoData[r94];     // Catch: Throwable -> L10
        int r1 = this.relativeFirstIndex;     // Catch: Throwable -> L10
        int r104 = r103 - r1;     // Catch: Throwable -> L10
        System.arraycopy(this.offsets, r1, r122, 0, r104);     // Catch: Throwable -> L10
        System.arraycopy(this.timesUs, this.relativeFirstIndex, r13, 0, r104);     // Catch: Throwable -> L10
        System.arraycopy(this.flags, this.relativeFirstIndex, r142, 0, r104);     // Catch: Throwable -> L10
        System.arraycopy(this.sizes, this.relativeFirstIndex, r152, 0, r104);     // Catch: Throwable -> L10
        System.arraycopy(this.cryptoDatas, this.relativeFirstIndex, r05, 0, r104);     // Catch: Throwable -> L10
        System.arraycopy(this.sourceIds, this.relativeFirstIndex, r112, 0, r104);     // Catch: Throwable -> L10
        int r16 = this.relativeFirstIndex;     // Catch: Throwable -> L10
        System.arraycopy(this.offsets, 0, r122, r104, r16);     // Catch: Throwable -> L10
        System.arraycopy(this.timesUs, 0, r13, r104, r16);     // Catch: Throwable -> L10
        System.arraycopy(this.flags, 0, r142, r104, r16);     // Catch: Throwable -> L10
        System.arraycopy(this.sizes, 0, r152, r104, r16);     // Catch: Throwable -> L10
        System.arraycopy(this.cryptoDatas, 0, r05, r104, r16);     // Catch: Throwable -> L10
        System.arraycopy(this.sourceIds, 0, r112, r104, r16);     // Catch: Throwable -> L10
        this.offsets = r122;     // Catch: Throwable -> L10
        this.timesUs = r13;     // Catch: Throwable -> L10
        this.flags = r142;     // Catch: Throwable -> L10
        this.sizes = r152;     // Catch: Throwable -> L10
        this.cryptoDatas = r05;     // Catch: Throwable -> L10
        this.sourceIds = r112;     // Catch: Throwable -> L10
        this.relativeFirstIndex = 0;     // Catch: Throwable -> L10
        this.capacity = r94;     // Catch: Throwable -> L10
    L28:
        monitor-exit(this);
        return;
    L23:
        r102 = DrmSessionManager.DrmSessionReference.EMPTY;     // Catch: Throwable -> L10
        goto L24
    L19:
        if (this.sharedSampleMetadata.getEndValue().format.equals(this.upstreamFormat) == true) goto L25;
    L15:
        r03 = false;
    L10:
        th = move-exception;
        throw th;
    }

    private int countUnreadSamplesBefore(long r6) {
        int r0 = this.length;
        int r1 = getRelativeIndex(r0 - 1);
    L4:
        if (r0 <= this.readPosition) goto L10;
        if (this.timesUs[r1] < r6) goto L10;
        r0 = r0 - 1;
        r1 = r1 - 1;
        if (r1 != (-1)) goto L4;
        r1 = this.capacity - 1;
    L10:
        return r0;
    }

    public static SampleQueue createWithDrm(Allocator r1, DrmSessionManager r2, DrmSessionEventListener.EventDispatcher r3) {
        return new SampleQueue(r1, (DrmSessionManager) Assertions.checkNotNull(r2), (DrmSessionEventListener.EventDispatcher) Assertions.checkNotNull(r3));
    }

    public static SampleQueue createWithoutDrm(Allocator r2) {
        return new SampleQueue(r2, null, null);
    }

    private synchronized long discardSampleMetadataTo(long r11, boolean r13, boolean r14) {
        monitor-enter(this);
        int r0 = this.length;     // Catch: Throwable -> L26
        if (r0 == 0) goto L28;
        long[] r3 = this.timesUs;     // Catch: Throwable -> L26
        int r5 = this.relativeFirstIndex;     // Catch: Throwable -> L26
        if (r11 < r3[r5]) goto L28;
        if (r14 == false) goto L16;
        int r142 = this.readPosition;     // Catch: Throwable -> L14
        if (r142 == r0) goto L16;
        r0 = r142 + 1;
    L14:
        th = move-exception;
        Throwable r112 = th;
        SampleQueue r4 = this;
    L30:
        monitor-exit(r4);     // Catch: Throwable -> L24
        throw r112;
    L16:
        int r10 = findSampleBefore(r5, r0, r11, r13);     // Catch: Throwable -> L24
        if (r10 != (-1)) goto L21;
        monitor-exit(this);
        return -1;
    L21:
        long r102 = discardSamples(r10);     // Catch: Throwable -> L24
        monitor-exit(this);
        return r102;
    L28:
        monitor-exit(this);
        return -1;
    L24:
        th = th;
    L25:
        r112 = th;
    L26:
        th = th;
        r4 = this;
        goto L25
    }

    private synchronized long discardSampleMetadataToEnd() {
        monitor-enter(this);
        int r0 = this.length;     // Catch: Throwable -> L11
        if (r0 != 0) goto L8;
        monitor-exit(this);
        return -1;
    L8:
        long r02 = discardSamples(r0);     // Catch: Throwable -> L11
        monitor-exit(this);
        return r02;
    L11:
        th = move-exception;
        throw th;
    }

    private long discardSamples(int r5) {
        this.largestDiscardedTimestampUs = Math.max(this.largestDiscardedTimestampUs, getLargestTimestamp(r5));
        this.length -= r5;
        int r0 = this.absoluteFirstIndex + r5;
        this.absoluteFirstIndex = r0;
        int r1 = this.relativeFirstIndex + r5;
        this.relativeFirstIndex = r1;
        int r2 = this.capacity;
        if (r1 < r2) goto L5;
        this.relativeFirstIndex = r1 - r2;
    L5:
        int r12 = this.readPosition - r5;
        this.readPosition = r12;
        if (r12 >= 0) goto L8;
        this.readPosition = 0;
    L8:
        this.sharedSampleMetadata.discardTo(r0);
        if (this.length != 0) goto L16;
        int r52 = this.relativeFirstIndex;
        if (r52 != 0) goto L14;
        r52 = this.capacity;
    L14:
        return this.offsets[r52 - 1] + this.sizes[r5];
    L16:
        return this.offsets[this.relativeFirstIndex];
    }

    private long discardUpstreamSampleMetadata(int r9) {
        int r0 = getWriteIndex() - r9;
        boolean r1 = false;
        if (r0 >= 0) goto L5;
    L7:
        boolean r3 = false;
    L8:
        Assertions.checkArgument(r3);
        int r32 = this.length - r0;
        this.length = r32;
        this.largestQueuedTimestampUs = Math.max(this.largestDiscardedTimestampUs, getLargestTimestamp(r32));
        if (r0 == 0) goto L11;
    L13:
        this.isLastSampleQueued = r1;
        this.sharedSampleMetadata.discardFrom(r9);
        int r92 = this.length;
        if (r92 != 0) goto L16;
        return 0;
    L16:
        return this.offsets[getRelativeIndex(r92 - 1)] + this.sizes[r9];
    L11:
        if (this.isLastSampleQueued == false) goto L13;
        r1 = true;
        goto L13
    L5:
        if (r0 > (this.length - this.readPosition)) goto L7;
        r3 = true;
        goto L8
    }

    private int findSampleAfter(int r6, int r7, long r8, boolean r10) {
        int r1 = 0;
    L3:
        if (r1 >= r7) goto L11;
        if (this.timesUs[r6] >= r8) goto L6;
        r6 = r6 + 1;
        if (r6 != this.capacity) goto L10;
        r6 = 0;
    L10:
        r1 = r1 + 1;
        goto L3
    L6:
        return r1;
    L11:
        if (r10 == false) goto L13;
        return r7;
    L13:
        return -1;
    }

    private int findSampleBefore(int r7, int r8, long r9, boolean r11) {
        int r0 = -1;
        int r2 = 0;
    L3:
        if (r2 >= r8) goto L17;
        long r4 = this.timesUs[r7];
        if (r4 > r9) goto L17;
        if (r11 == false) goto L10;
        if ((this.flags[r7] & 1) != 0) goto L10;
    L13:
        r7 = r7 + 1;
        if (r7 != this.capacity) goto L16;
        r7 = 0;
    L16:
        r2 = r2 + 1;
    L10:
        if (r4 == r9) goto L11;
        r0 = r2;
        goto L13
    L11:
        return r2;
    L17:
        return r0;
    }

    private long getLargestTimestamp(int r8) {
        long r0 = Long.MIN_VALUE;
        if (r8 != 0) goto L5;
        return Long.MIN_VALUE;
    L5:
        int r2 = getRelativeIndex(r8 - 1);
        int r3 = 0;
    L6:
        if (r3 >= r8) goto L14;
        r0 = Math.max(r0, this.timesUs[r2]);
        if ((this.flags[r2] & 1) != 0) goto L9;
        r2 = r2 - 1;
        if (r2 != (-1)) goto L13;
        r2 = this.capacity - 1;
    L13:
        r3 = r3 + 1;
        goto L6
    L9:
        return r0;
    L14:
        return r0;
    }

    private int getRelativeIndex(int r2) {
        int r0 = this.relativeFirstIndex + r2;
        int r1 = this.capacity;
        if (r0 >= r1) goto L6;
        return r0;
    L6:
        return r0 - r1;
    }

    private boolean hasNextSample() {
        if (this.readPosition == this.length) goto L6;
        return true;
    L6:
        return false;
    }

    private boolean mayReadSample(int r3) {
        DrmSession r0 = this.currentDrmSession;
        if (r0 != null) goto L5;
        return true;
    L5:
        if (r0.getState() != 4) goto L7;
        return true;
    L7:
        if ((this.flags[r3] & TLObject.FLAG_30) == 0) goto L9;
        return false;
    L9:
        if (this.currentDrmSession.playClearSamplesWithoutKeys() == true) goto L17;
        return false;
    L17:
        return true;
    }

    private void onFormatResult(Format r5, FormatHolder r6) {
        Format r0 = this.downstreamFormat;
        if (r0 != null) goto L5;
        boolean r1 = true;
    L6:
        if (r0 != null) goto L8;
        DrmInitData r02 = null;
    L9:
        this.downstreamFormat = r5;
        DrmInitData r2 = r5.drmInitData;
        DrmSessionManager r3 = this.drmSessionManager;
        if (r3 == null) goto L12;
        Format r32 = r5.copyWithCryptoType(r3.getCryptoType(r5));
    L13:
        r6.format = r32;
        r6.drmSession = this.currentDrmSession;
        if (this.drmSessionManager == null) goto L25;
        if (r1 == false) goto L18;
    L20:
        DrmSession r03 = this.currentDrmSession;
        DrmSession r52 = this.drmSessionManager.acquireSession(this.drmEventDispatcher, r5);
        this.currentDrmSession = r52;
        r6.drmSession = r52;
        if (r03 == null) goto L24;
        r03.release(this.drmEventDispatcher);
        return;
    L24:
        return;
    L18:
        if (Objects.equals(r02, r2) == false) goto L20;
        return;
    L25:
        return;
    L12:
        r32 = r5;
        goto L13
    L8:
        r02 = r0.drmInitData;
        goto L9
    L5:
        r1 = false;
        goto L6
    }

    private synchronized int peekSampleMetadata(FormatHolder r6, DecoderInputBuffer r7, boolean r8, boolean r9, SampleExtrasHolder r10) {
        monitor-enter(this);
        r7.waitingForKeys = false;     // Catch: Throwable -> L16
        if (hasNextSample() == true) goto L27;
        if (r9 == true) goto L24;
        if (this.isLastSampleQueued == true) goto L24;
        Format r72 = this.upstreamFormat;     // Catch: Throwable -> L16
        if (r72 == null) goto L21;
        if (r8 == false) goto L14;
    L18:
        onFormatResult((Format) Assertions.checkNotNull(r72), r6);     // Catch: Throwable -> L16
        monitor-exit(this);
        return -5;
    L14:
        if (r72 != this.downstreamFormat) goto L18;
    L21:
        monitor-exit(this);
        return -3;
    L24:
        r7.setFlags(4);     // Catch: Throwable -> L16
        r7.timeUs = Long.MIN_VALUE;     // Catch: Throwable -> L16
        monitor-exit(this);
        return -4;
    L27:
        Format r0 = this.sharedSampleMetadata.get(getReadIndex()).format;     // Catch: Throwable -> L16
        if (r8 == false) goto L30;
    L46:
        onFormatResult(r0, r6);     // Catch: Throwable -> L16
        monitor-exit(this);
        return -5;
    L30:
        if (r0 != this.downstreamFormat) goto L46;
        int r62 = getRelativeIndex(this.readPosition);     // Catch: Throwable -> L16
        if (mayReadSample(r62) == true) goto L37;
        r7.waitingForKeys = true;     // Catch: Throwable -> L16
        monitor-exit(this);
        return -3;
    L37:
        r7.setFlags(this.flags[r62]);     // Catch: Throwable -> L16
        if (this.readPosition != (this.length - 1)) goto L43;
        if (r9 == false) goto L41;
    L42:
        r7.addFlag(536870912);     // Catch: Throwable -> L16
        goto L43
    L41:
        if (this.isLastSampleQueued == true) goto L42;
    L43:
        r7.timeUs = this.timesUs[r62];     // Catch: Throwable -> L16
        r10.size = this.sizes[r62];     // Catch: Throwable -> L16
        r10.offset = this.offsets[r62];     // Catch: Throwable -> L16
        r10.cryptoData = this.cryptoDatas[r62];     // Catch: Throwable -> L16
        monitor-exit(this);
        return -4;
    L16:
        th = move-exception;
        throw th;
    }

    private void releaseDrmSessionReferences() {
        DrmSession r0 = this.currentDrmSession;
        if (r0 == null) goto L6;
        r0.release(this.drmEventDispatcher);
        this.currentDrmSession = null;
        this.downstreamFormat = null;
        return;
    }

    private synchronized void rewind() {
        monitor-enter(this);
        this.readPosition = 0;     // Catch: Throwable -> L7
        this.sampleDataQueue.rewind();     // Catch: Throwable -> L7
        monitor-exit(this);
        return;
    L7:
        th = move-exception;
        throw th;
    }

    private synchronized boolean setUpstreamFormat(Format r4) {
        monitor-enter(this);
        this.upstreamFormatRequired = false;     // Catch: Throwable -> L13
        if (Objects.equals(r4, this.upstreamFormat) == false) goto L9;
        monitor-exit(this);
        return false;
    L9:
        if (this.sharedSampleMetadata.isEmpty() == false) goto L11;
    L15:
        this.upstreamFormat = r4;     // Catch: Throwable -> L13
    L16:
        boolean r42 = this.allSamplesAreSyncSamples;     // Catch: Throwable -> L13
        Format r1 = this.upstreamFormat;     // Catch: Throwable -> L13
        this.allSamplesAreSyncSamples = r42 & MimeTypes.allSamplesAreSyncSamples(r1.sampleMimeType, r1.codecs);     // Catch: Throwable -> L13
        this.loggedUnexpectedNonSyncSample = false;     // Catch: Throwable -> L13
        monitor-exit(this);
        return true;
    L11:
        if (this.sharedSampleMetadata.getEndValue().format.equals(r4) == false) goto L15;
        this.upstreamFormat = this.sharedSampleMetadata.getEndValue().format;     // Catch: Throwable -> L13
    L13:
        th = move-exception;
        throw th;
    }

    public synchronized long discardSampleMetadataToRead() {
        monitor-enter(this);
        int r0 = this.readPosition;     // Catch: Throwable -> L11
        if (r0 != 0) goto L8;
        monitor-exit(this);
        return -1;
    L8:
        long r02 = discardSamples(r0);     // Catch: Throwable -> L11
        monitor-exit(this);
        return r02;
    L11:
        th = move-exception;
        throw th;
    }

    public final void discardTo(long r2, boolean r4, boolean r5) {
        this.sampleDataQueue.discardDownstreamTo(discardSampleMetadataTo(r2, r4, r5));
    }

    public final void discardToEnd() {
        this.sampleDataQueue.discardDownstreamTo(discardSampleMetadataToEnd());
    }

    public final void discardToRead() {
        this.sampleDataQueue.discardDownstreamTo(discardSampleMetadataToRead());
    }

    public final void discardUpstreamFrom(long r3) {
        if (this.length != 0) goto L6;
        return;
    L6:
        if (r3 <= getLargestReadTimestampUs()) goto L8;
        boolean r0 = true;
    L9:
        Assertions.checkArgument(r0);
        int r32 = countUnreadSamplesBefore(r3);
        discardUpstreamSamples(this.absoluteFirstIndex + r32);
        return;
    L8:
        r0 = false;
        goto L9
    }

    public final void discardUpstreamSamples(int r2) {
        this.sampleDataQueue.discardUpstreamSampleBytes(discardUpstreamSampleMetadata(r2));
    }

    @Override // androidx.media3.extractor.TrackOutput
    public final void format(Format r3) {
        Format r0 = getAdjustedUpstreamFormat(r3);
        this.upstreamFormatAdjustmentRequired = false;
        this.unadjustedUpstreamFormat = r3;
        boolean r32 = setUpstreamFormat(r0);
        UpstreamFormatChangedListener r2 = this.upstreamFormatChangeListener;
        if (r2 == null) goto L7;
        if (r32 == false) goto L8;
        r2.onUpstreamFormatChanged(r0);
        return;
    L8:
        return;
    }

    public Format getAdjustedUpstreamFormat(Format r5) {
        if (this.sampleOffsetUs != 0) goto L5;
    L8:
        return r5;
    L5:
        if (r5.subsampleOffsetUs == LongCompanionObject.MAX_VALUE) goto L8;
        return r5.buildUpon().setSubsampleOffsetUs(r5.subsampleOffsetUs + this.sampleOffsetUs).build();
    }

    public final int getFirstIndex() {
        return this.absoluteFirstIndex;
    }

    public final synchronized long getFirstTimestampUs() {
        monitor-enter(this);
    L10:
        th = move-exception;
        throw th;
    L4:
        if (this.length != 0) goto L6;
        long r0 = Long.MIN_VALUE;
    L8:
        monitor-exit(this);
        return r0;
    L6:
        r0 = this.timesUs[this.relativeFirstIndex];     // Catch: Throwable -> L10
        goto L8
    }

    public final synchronized long getLargestQueuedTimestampUs() {
        monitor-enter(this);
        long r0 = this.largestQueuedTimestampUs;     // Catch: Throwable -> L6
        monitor-exit(this);
        return r0;
    L6:
        th = move-exception;
        throw th;
    }

    public final synchronized long getLargestReadTimestampUs() {
        monitor-enter(this);
        long r0 = Math.max(this.largestDiscardedTimestampUs, getLargestTimestamp(this.readPosition));     // Catch: Throwable -> L6
        monitor-exit(this);
        return r0;
    L6:
        th = move-exception;
        throw th;
    }

    public final int getReadIndex() {
        return this.absoluteFirstIndex + this.readPosition;
    }

    public final synchronized int getSkipCount(long r9, boolean r11) {
        monitor-enter(this);
        int r2 = getRelativeIndex(this.readPosition);     // Catch: Throwable -> L29
        if (hasNextSample() == false) goto L31;
        if (r9 < this.timesUs[r2]) goto L31;
        if (r9 <= this.largestQueuedTimestampUs) goto L18;
        if (r11 == false) goto L18;
        int r92 = this.length - this.readPosition;
        monitor-exit(this);
        return r92;
    L16:
        th = move-exception;
        Throwable r93 = th;
        SampleQueue r1 = this;
    L33:
        monitor-exit(r1);     // Catch: Throwable -> L27
        throw r93;
    L18:
        int r8 = findSampleBefore(r2, this.length - this.readPosition, r9, true);     // Catch: Throwable -> L27
        if (r8 != (-1)) goto L25;
        monitor-exit(this);
        return 0;
    L25:
        monitor-exit(this);
        return r8;
    L31:
        monitor-exit(this);
        return 0;
    L29:
        th = th;
        r1 = this;
    L28:
        r93 = th;
    L27:
        th = th;
        goto L28
    }

    public final synchronized Format getUpstreamFormat() {
        monitor-enter(this);
    L9:
        th = move-exception;
        throw th;
    L4:
        if (this.upstreamFormatRequired == false) goto L6;
        Format r0 = null;
    L7:
        monitor-exit(this);
        return r0;
    L6:
        r0 = this.upstreamFormat;     // Catch: Throwable -> L9
        goto L7
    }

    public final int getWriteIndex() {
        return this.absoluteFirstIndex + this.length;
    }

    public final void invalidateUpstreamFormatAdjustment() {
        this.upstreamFormatAdjustmentRequired = true;
    }

    public final synchronized boolean isLastSampleQueued() {
        monitor-enter(this);
        boolean r0 = this.isLastSampleQueued;     // Catch: Throwable -> L6
        monitor-exit(this);
        return r0;
    L6:
        th = move-exception;
        throw th;
    }

    public synchronized boolean isReady(boolean r3) {
        monitor-enter(this);
        boolean r1 = true;
        if (hasNextSample() == true) goto L19;
        if (r3 == false) goto L7;
    L16:
        monitor-exit(this);
        return r1;
    L7:
        if (this.isLastSampleQueued == true) goto L16;
        Format r32 = this.upstreamFormat;     // Catch: Throwable -> L13
        if (r32 != null) goto L11;
    L15:
        r1 = false;
        goto L16
    L11:
        if (r32 == this.downstreamFormat) goto L15;
    L19:
        if (this.sharedSampleMetadata.get(getReadIndex()).format == this.downstreamFormat) goto L22;
        monitor-exit(this);
        return true;
    L22:
        boolean r33 = mayReadSample(getRelativeIndex(this.readPosition));     // Catch: Throwable -> L13
        monitor-exit(this);
        return r33;
    L13:
        th = move-exception;
        throw th;
    }

    public void maybeThrowError() {
        DrmSession r0 = this.currentDrmSession;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.getState() == 1) goto L8;
        return;
    L8:
        throw ((DrmSession.DrmSessionException) Assertions.checkNotNull(this.currentDrmSession.getError()));
    }

    public final synchronized long peekSourceId() {
        monitor-enter(this);
        int r0 = getRelativeIndex(this.readPosition);     // Catch: Throwable -> L6
        if (hasNextSample() == false) goto L8;
        long r02 = this.sourceIds[r0];     // Catch: Throwable -> L6
    L9:
        monitor-exit(this);
        return r02;
    L8:
        r02 = this.upstreamSourceId;     // Catch: Throwable -> L6
    L6:
        th = move-exception;
        throw th;
    }

    public void preRelease() {
        discardToEnd();
        releaseDrmSessionReferences();
    }

    public int read(FormatHolder r10, DecoderInputBuffer r11, int r12, boolean r13) {
        boolean r1 = false;
        if ((r12 & 2) == 0) goto L5;
        boolean r6 = true;
    L6:
        int r9 = peekSampleMetadata(r10, r11, r6, r13, this.extrasHolder);
        if (r9 == (-4)) goto L9;
    L21:
        return r9;
    L9:
        if (r11.isEndOfStream() == true) goto L21;
        if ((r12 & 1) == 0) goto L14;
        r1 = true;
    L14:
        if ((r12 & 4) != 0) goto L19;
        SampleDataQueue r102 = this.sampleDataQueue;
        if (r1 == false) goto L18;
        r102.peekToBuffer(r11, this.extrasHolder);
        goto L19
    L18:
        r102.readToBuffer(r11, this.extrasHolder);
    L19:
        if (r1 == true) goto L21;
        this.readPosition++;
        goto L21
    L5:
        r6 = false;
        goto L6
    }

    public void release() {
        reset(true);
        releaseDrmSessionReferences();
    }

    public final void reset() {
        reset(false);
    }

    public void reset(boolean r5) {
        this.sampleDataQueue.reset();
        this.length = 0;
        this.absoluteFirstIndex = 0;
        this.relativeFirstIndex = 0;
        this.readPosition = 0;
        this.upstreamKeyframeRequired = true;
        this.startTimeUs = Long.MIN_VALUE;
        this.largestDiscardedTimestampUs = Long.MIN_VALUE;
        this.largestQueuedTimestampUs = Long.MIN_VALUE;
        this.isLastSampleQueued = false;
        this.sharedSampleMetadata.clear();
        if (r5 == false) goto L6;
        this.unadjustedUpstreamFormat = null;
        this.upstreamFormat = null;
        this.upstreamFormatRequired = true;
        this.allSamplesAreSyncSamples = true;
        return;
    }

    @Override // androidx.media3.extractor.TrackOutput
    public final int sampleData(DataReader r1, int r2, boolean r3, int r4) {
        return this.sampleDataQueue.sampleData(r1, r2, r3);
    }

    @Override // androidx.media3.extractor.TrackOutput
    public final void sampleData(ParsableByteArray r1, int r2, int r3) {
        this.sampleDataQueue.sampleData(r1, r2);
    }

    @Override // androidx.media3.extractor.TrackOutput
    public void sampleMetadata(long r12, int r14, int r15, int r16, TrackOutput.CryptoData r17) {
        if (this.upstreamFormatAdjustmentRequired == false) goto L5;
        format((Format) Assertions.checkStateNotNull(this.unadjustedUpstreamFormat));
    L5:
        int r1 = r14 & 1;
        if (r1 == 0) goto L8;
        boolean r4 = true;
    L10:
        if (this.upstreamKeyframeRequired == false) goto L14;
        if (r4 == false) goto L35;
        this.upstreamKeyframeRequired = false;
        goto L14
    L35:
        return;
    L14:
        long r5 = this.sampleOffsetUs + r12;
        if (this.allSamplesAreSyncSamples == true) goto L17;
    L24:
        int r3 = r14;
    L26:
        if (this.pendingSplice == false) goto L33;
        if (r4 == true) goto L29;
        return;
    L29:
        if (attemptSplice(r5) == false) goto L37;
        this.pendingSplice = false;
        goto L33
    L37:
        return;
    L33:
        commitSample(r5, r3, (this.sampleDataQueue.getTotalBytesWritten() - r15) - r16, r15, r17);
        return;
    L17:
        if (r5 < this.startTimeUs) goto L36;
        if (r1 != 0) goto L24;
        if (this.loggedUnexpectedNonSyncSample == true) goto L23;
        Log.w("SampleQueue", "Overriding unexpected non-sync sample for format: " + this.upstreamFormat);
        this.loggedUnexpectedNonSyncSample = true;
    L23:
        r3 = r14 | 1;
        goto L26
    L36:
        return;
    L8:
        r4 = false;
        goto L10
    }

    public final synchronized boolean seekTo(int r4) {
        monitor-enter(this);
        rewind();     // Catch: Throwable -> L12
        int r0 = this.absoluteFirstIndex;     // Catch: Throwable -> L12
        if (r4 >= r0) goto L6;
    L14:
        monitor-exit(this);
        return false;
    L6:
        if (r4 > (this.length + r0)) goto L14;
        this.startTimeUs = Long.MIN_VALUE;     // Catch: Throwable -> L12
        this.readPosition = r4 - r0;     // Catch: Throwable -> L12
        monitor-exit(this);
        return true;
    L12:
        th = move-exception;
        throw th;
    }

    public final synchronized boolean seekTo(long r9, boolean r11) {
        monitor-enter(this);
        rewind();     // Catch: Throwable -> L39
        int r2 = getRelativeIndex(this.readPosition);     // Catch: Throwable -> L39
        if (hasNextSample() == false) goto L41;
        if (r9 < this.timesUs[r2]) goto L41;
        if (r9 <= this.largestQueuedTimestampUs) goto L11;
        if (r11 == false) goto L41;
    L11:
        boolean r0 = this.allSamplesAreSyncSamples;     // Catch: Throwable -> L39
        int r1 = this.length;
        if (r0 == true) goto L50;
        long r4 = r9;
        int r3 = r1 - this.readPosition;
        SampleQueue r12 = this;
        int r8 = r12.findSampleBefore(r2, r3, r4, true);     // Catch: Throwable -> L35
    L28:
        if (r8 != (-1)) goto L31;
        monitor-exit(r12);
        return false;
    L31:
        r12.startTimeUs = r4;     // Catch: Throwable -> L35
        r12.readPosition += r8;
        monitor-exit(r12);
        return true;
    L37:
        th = th;
        SampleQueue r13 = this;
    L20:
        Throwable r82 = th;
    L43:
        monitor-exit(r13);     // Catch: Throwable -> L35
        throw r82;
    L50:
        int r32 = r1 - this.readPosition;
        r13 = this;
        r4 = r9;
        r8 = r13.findSampleAfter(r2, r32, r4, r11);     // Catch: Throwable -> L18
        r12 = r13;
    L18:
        th = th;
    L21:
        th = move-exception;
        r82 = th;
        r13 = this;
    L41:
        monitor-exit(this);
        return false;
    L39:
        th = th;
        r13 = this;
    L35:
        th = th;
        goto L20
    }

    public final void setSampleOffsetUs(long r3) {
        if (this.sampleOffsetUs == r3) goto L6;
        this.sampleOffsetUs = r3;
        invalidateUpstreamFormatAdjustment();
        return;
    }

    public final void setStartTimeUs(long r1) {
        this.startTimeUs = r1;
    }

    public final void setUpstreamFormatChangeListener(UpstreamFormatChangedListener r1) {
        this.upstreamFormatChangeListener = r1;
    }

    public final synchronized void skip(int r3) {
        monitor-enter(this);
        if (r3 >= 0) goto L15;
    L9:
        boolean r0 = false;
    L10:
        Assertions.checkArgument(r0);     // Catch: Throwable -> L7
        this.readPosition += r3;
        monitor-exit(this);
        return;
    L7:
        th = move-exception;
        throw th;
    L15:
        if ((this.readPosition + r3) > this.length) goto L9;
        r0 = true;
        goto L10
    }

    public final void sourceId(long r1) {
        this.upstreamSourceId = r1;
    }

    public final void splice() {
        this.pendingSplice = true;
    }
}
