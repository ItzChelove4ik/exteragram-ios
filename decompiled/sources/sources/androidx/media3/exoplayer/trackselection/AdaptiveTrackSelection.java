package androidx.media3.exoplayer.trackselection;

import androidx.media3.common.Format;
import androidx.media3.common.Timeline;
import androidx.media3.common.TrackGroup;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.chunk.MediaChunk;
import androidx.media3.exoplayer.source.chunk.MediaChunkIterator;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import androidx.media3.exoplayer.upstream.BandwidthMeter;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes4.dex */
public class AdaptiveTrackSelection extends BaseTrackSelection {
    private final ImmutableList<AdaptationCheckpoint> adaptationCheckpoints;
    private final float bandwidthFraction;
    private final BandwidthMeter bandwidthMeter;
    private final float bufferedFractionToLiveEdgeForQualityIncrease;
    private final Clock clock;
    private MediaChunk lastBufferEvaluationMediaChunk;
    private long lastBufferEvaluationMs;
    private long latestBitrateEstimate;
    private final long maxDurationForQualityDecreaseUs;
    private final int maxHeightToDiscard;
    private final int maxWidthToDiscard;
    private final long minDurationForQualityIncreaseUs;
    private final long minDurationToRetainAfterDiscardUs;
    private float playbackSpeed;
    private int reason;
    private int selectedIndex;

    public static final class AdaptationCheckpoint {
        public final long allocatedBandwidth;
        public final long totalBandwidth;

        public AdaptationCheckpoint(long r1, long r3) {
            this.totalBandwidth = r1;
            this.allocatedBandwidth = r3;
        }

        public boolean equals(Object r8) {
            if (this != r8) goto L6;
            return true;
        L6:
            if ((r8 instanceof AdaptationCheckpoint) == true) goto L8;
            return false;
        L8:
            AdaptationCheckpoint r82 = (AdaptationCheckpoint) r8;
            if (this.totalBandwidth == r82.totalBandwidth) goto L11;
        L13:
            return false;
        L11:
            if (this.allocatedBandwidth != r82.allocatedBandwidth) goto L13;
            return true;
        }

        public int hashCode() {
            return (((int) this.totalBandwidth) * 31) + ((int) this.allocatedBandwidth);
        }
    }

    public static class Factory implements ExoTrackSelection.Factory {
        private final float bandwidthFraction;
        private final float bufferedFractionToLiveEdgeForQualityIncrease;
        private final Clock clock;
        private final int maxDurationForQualityDecreaseMs;
        private final int maxHeightToDiscard;
        private final int maxWidthToDiscard;
        private final int minDurationForQualityIncreaseMs;
        private final int minDurationToRetainAfterDiscardMs;

        public Factory() {
            this(10000, 25000, 25000, 0.7f);
        }

        public Factory(int r10, int r11, int r12, float r13) {
            this(r10, r11, r12, 1279, 719, r13, 0.75f, Clock.DEFAULT);
        }

        public Factory(int r1, int r2, int r3, int r4, int r5, float r6, float r7, Clock r8) {
            this.minDurationForQualityIncreaseMs = r1;
            this.maxDurationForQualityDecreaseMs = r2;
            this.minDurationToRetainAfterDiscardMs = r3;
            this.maxWidthToDiscard = r4;
            this.maxHeightToDiscard = r5;
            this.bandwidthFraction = r6;
            this.bufferedFractionToLiveEdgeForQualityIncrease = r7;
            this.clock = r8;
        }

        public AdaptiveTrackSelection createAdaptiveTrackSelection(TrackGroup r18, int[] r19, int r20, BandwidthMeter r21, ImmutableList<AdaptationCheckpoint> r22) {
            return new AdaptiveTrackSelection(r18, r19, r20, r21, this.minDurationForQualityIncreaseMs, this.maxDurationForQualityDecreaseMs, this.minDurationToRetainAfterDiscardMs, this.maxWidthToDiscard, this.maxHeightToDiscard, this.bandwidthFraction, this.bufferedFractionToLiveEdgeForQualityIncrease, r22, this.clock);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection.Factory
        public final ExoTrackSelection[] createTrackSelections(ExoTrackSelection.Definition[] r10, BandwidthMeter r11, MediaSource.MediaPeriodId r12, Timeline r13) {
            ImmutableList r122 = AdaptiveTrackSelection.access$000(r10);
            ExoTrackSelection[] r132 = new ExoTrackSelection[r10.length];
            int r1 = 0;
        L4:
            if (r1 >= r10.length) goto L16;
            ExoTrackSelection.Definition r2 = r10[r1];
            if (r2 == null) goto L9;
            int[] r5 = r2.tracks;
            if (r5.length == 0) goto L9;
            int r3 = r5.length;
            TrackGroup r4 = r2.group;
            if (r3 != 1) goto L13;
            Factory r32 = this;
            ExoTrackSelection r9 = new FixedTrackSelection(r4, r5[0], r2.type);
            BandwidthMeter r7 = r11;
        L14:
            r132[r1] = r9;
        L15:
            r1 = r1 + 1;
            Factory this = r32;
            r11 = r7;
            goto L4
        L13:
            r32 = this;
            r7 = r11;
            r9 = r32.createAdaptiveTrackSelection(r4, r5, r2.type, r7, (ImmutableList) r122.get(r1));
        L9:
            r32 = this;
            r7 = r11;
            goto L15
        L16:
            return r132;
        }
    }

    public AdaptiveTrackSelection(TrackGroup r1, int[] r2, int r3, BandwidthMeter r4, long r5, long r7, long r9, int r11, int r12, float r13, float r14, List<AdaptationCheckpoint> r15, Clock r16) {
        super(r1, r2, r3);
        if (r9 >= r5) goto L5;
        Log.w("AdaptiveTrackSelection", "Adjusting minDurationToRetainAfterDiscardMs to be at least minDurationForQualityIncreaseMs");
        long r17 = r5;
    L6:
        this.bandwidthMeter = r4;
        this.minDurationForQualityIncreaseUs = r5 * 1000;
        this.maxDurationForQualityDecreaseUs = r7 * 1000;
        this.minDurationToRetainAfterDiscardUs = r17 * 1000;
        this.maxWidthToDiscard = r11;
        this.maxHeightToDiscard = r12;
        this.bandwidthFraction = r13;
        this.bufferedFractionToLiveEdgeForQualityIncrease = r14;
        this.adaptationCheckpoints = ImmutableList.copyOf(r15);
        this.clock = r16;
        this.playbackSpeed = 1.0f;
        this.reason = 0;
        this.lastBufferEvaluationMs = -9223372036854775807L;
        this.latestBitrateEstimate = -2147483647L;
        return;
    L5:
        r17 = r9;
        goto L6
    }

    public static /* synthetic */ ImmutableList access$000(ExoTrackSelection.Definition[] r0) {
        return getAdaptationCheckpoints(r0);
    }

    private static void addCheckpoint(List<ImmutableList.Builder<AdaptationCheckpoint>> r7, long[] r8) {
        long r0 = 0;
        int r2 = 0;
        int r3 = 0;
    L4:
        if (r3 >= r8.length) goto L7;
        r0 = r0 + r8[r3];
        r3 = r3 + 1;
    L7:
        if (r2 >= r7.size()) goto L13;
        ImmutableList.Builder<AdaptationCheckpoint> r32 = r7.get(r2);
        if (r32 == null) goto L12;
        r32.add(new AdaptationCheckpoint(r0, r8[r2]));
    L12:
        r2 = r2 + 1;
        goto L7
    }

    private int determineIdealSelectedIndex(int r11, long r12, long r14) {
        long r142 = getAllocatedBandwidth(r14);
        HashMap r0 = new HashMap();
        ArrayList r1 = new ArrayList();
        int r2 = 0;
        int r3 = 0;
    L4:
        if (r3 >= this.length) goto L25;
        if (r12 != Long.MIN_VALUE) goto L8;
    L10:
        Format r4 = getFormat(r3);
        int r5 = Math.max(r4.width, r4.height);
        if (r0.containsKey(Integer.valueOf(r5)) == true) goto L13;
        r0.put(Integer.valueOf(r5), Integer.valueOf(r3));
        r1.add(Integer.valueOf(r3));
    L24:
        r3 = r3 + 1;
        goto L4
    L13:
        Integer r6 = (Integer) r0.get(Integer.valueOf(r5));
        Format r7 = getFormat(r6.intValue());
        boolean r8 = r7.cached;
        if (r8 == true) goto L16;
    L18:
        if (r8 == true) goto L22;
        if (r4.cached == false) goto L22;
    L23:
        r0.put(Integer.valueOf(r5), Integer.valueOf(r3));
        r1.remove(r6);
        r1.add(Integer.valueOf(r3));
    L22:
        if (r4.bitrate >= r7.bitrate) goto L24;
    L16:
        if (r4.cached == true) goto L18;
    L8:
        if (isTrackExcluded(r3, r12) == false) goto L10;
    L25:
        if (r11 != 0) goto L31;
        int r112 = r1.size();
        int r122 = 0;
    L27:
        if (r122 >= r112) goto L31;
        Object r13 = r1.get(r122);
        r122 = r122 + 1;
        int r132 = ((Integer) r13).intValue();
        if (getFormat(r132).cached == false) goto L27;
        return r132;
    L31:
        int r113 = r1.size();
        int r123 = 0;
    L32:
        if (r123 >= r113) goto L35;
        Object r133 = r1.get(r123);
        r123 = r123 + 1;
        r2 = ((Integer) r133).intValue();
        Format r134 = getFormat(r2);
        if (canSelectFormat(r134, r134.bitrate, r142) == false) goto L32;
    L35:
        return r2;
    }

    private static ImmutableList<ImmutableList<AdaptationCheckpoint>> getAdaptationCheckpoints(ExoTrackSelection.Definition[] r12) {
        ArrayList r0 = new ArrayList();
        int r1 = 0;
        int r2 = 0;
    L4:
        if (r2 >= r12.length) goto L12;
        ExoTrackSelection.Definition r3 = r12[r2];
        if (r3 != null) goto L8;
    L10:
        r0.add(null);
    L11:
        r2 = r2 + 1;
        goto L4
    L8:
        if (r3.tracks.length <= 1) goto L10;
        ImmutableList.Builder r32 = ImmutableList.builder();
        r32.add(new AdaptationCheckpoint(0, 0));
        r0.add(r32);
        goto L11
    L12:
        long[][] r22 = getSortedTrackBitrates(r12);
        int[] r33 = new int[r22.length];
        long[] r7 = new long[r22.length];
        int r8 = 0;
    L14:
        if (r8 >= r22.length) goto L20;
        long[] r9 = r22[r8];
        if (r9.length != 0) goto L18;
        long r10 = 0;
    L19:
        r7[r8] = r10;
        r8 = r8 + 1;
        goto L14
    L18:
        r10 = r9[0];
        goto L19
    L20:
        addCheckpoint(r0, r7);
        ImmutableList<Integer> r4 = getSwitchOrder(r22);
        int r5 = 0;
    L22:
        if (r5 >= r4.size()) goto L24;
        int r82 = r4.get(r5).intValue();
        int r92 = r33[r82] + 1;
        r33[r82] = r92;
        r7[r82] = r22[r82][r92];
        addCheckpoint(r0, r7);
        r5 = r5 + 1;
        goto L22
    L24:
        int r23 = 0;
    L26:
        if (r23 >= r12.length) goto L31;
        if (r0.get(r23) == null) goto L30;
        r7[r23] = r7[r23] * 2;
    L30:
        r23 = r23 + 1;
        goto L26
    L31:
        addCheckpoint(r0, r7);
        ImmutableList.Builder r122 = ImmutableList.builder();
    L33:
        if (r1 >= r0.size()) goto L40;
        ImmutableList.Builder r24 = (ImmutableList.Builder) r0.get(r1);
        if (r24 != null) goto L37;
        ImmutableList r25 = ImmutableList.of();
    L38:
        r122.add(r25);
        r1 = r1 + 1;
        goto L33
    L37:
        r25 = r24.build();
        goto L38
    L40:
        return r122.build();
    }

    private long getAllocatedBandwidth(long r6) {
        long r62 = getTotalAllocatableBandwidth(r6);
        if (this.adaptationCheckpoints.isEmpty() == false) goto L5;
        return r62;
    L5:
        int r1 = 1;
    L7:
        if (r1 >= (this.adaptationCheckpoints.size() - 1)) goto L11;
        if (this.adaptationCheckpoints.get(r1).totalBandwidth >= r62) goto L11;
        r1 = r1 + 1;
    L11:
        AdaptationCheckpoint r0 = this.adaptationCheckpoints.get(r1 - 1);
        AdaptationCheckpoint r5 = this.adaptationCheckpoints.get(r1);
        long r12 = r0.totalBandwidth;
        float r63 = (r62 - r12) / (r5.totalBandwidth - r12);
        return r0.allocatedBandwidth + ((long) (r63 * (r5.allocatedBandwidth - r0)));
    }

    private long getLastChunkDurationUs(List<? extends MediaChunk> r6) {
        if (r6.isEmpty() == false) goto L5;
        return -9223372036854775807L;
    L5:
        MediaChunk r5 = (MediaChunk) Iterables.getLast(r6);
        long r2 = r5.startTimeUs;
        if (r2 == (-9223372036854775807L)) goto L11;
        long r52 = r5.endTimeUs;
        if (r52 == (-9223372036854775807L)) goto L11;
        return r52 - r2;
    L11:
        return -9223372036854775807L;
    }

    private long getNextChunkDurationUs(MediaChunkIterator[] r5, List<? extends MediaChunk> r6) {
        int r0 = this.selectedIndex;
        if (r0 < r5.length) goto L5;
    L8:
        int r02 = r5.length;
        int r1 = 0;
    L9:
        if (r1 >= r02) goto L16;
        MediaChunkIterator r2 = r5[r1];
        if (r2.next() == true) goto L13;
        r1 = r1 + 1;
        goto L9
    L13:
        return r2.getChunkEndTimeUs() - r2.getChunkStartTimeUs();
    L16:
        return getLastChunkDurationUs(r6);
    L5:
        if (r5[r0].next() == false) goto L8;
        MediaChunkIterator r4 = r5[this.selectedIndex];
        return r4.getChunkEndTimeUs() - r4.getChunkStartTimeUs();
    }

    private static long[][] getSortedTrackBitrates(ExoTrackSelection.Definition[] r10) {
        long[][] r0 = new long[r10.length][];
        int r2 = 0;
    L4:
        if (r2 >= r10.length) goto L17;
        ExoTrackSelection.Definition r3 = r10[r2];
        if (r3 != null) goto L8;
        r0[r2] = new long[0];
    L16:
        r2 = r2 + 1;
        goto L4
    L8:
        r0[r2] = new long[r3.tracks.length];
        int r4 = 0;
    L9:
        int[] r5 = r3.tracks;
        if (r4 >= r5.length) goto L15;
        long r52 = r3.group.getFormat(r5[r4]).bitrate;
        long[] r7 = r0[r2];
        if (r52 != (-1)) goto L14;
        r52 = 0;
    L14:
        r7[r4] = r52;
        r4 = r4 + 1;
        goto L9
    L15:
        Arrays.sort(r0[r2]);
        goto L16
    L17:
        return r0;
    }

    private static ImmutableList<Integer> getSwitchOrder(long[][] r14) {
        Multimap r0 = MultimapBuilder.treeKeys().arrayListValues().build();
        int r2 = 0;
    L4:
        if (r2 >= r14.length) goto L25;
        long[] r3 = r14[r2];
        if (r3.length <= 1) goto L23;
        int r32 = r3.length;
        double[] r4 = new double[r32];
        int r5 = 0;
    L9:
        long[] r6 = r14[r2];
        double r8 = 0.0d;
        if (r5 >= r6.length) goto L16;
        long r10 = r6[r5];
        if (r10 == (-1)) goto L15;
        r8 = Math.log(r10);
    L15:
        r4[r5] = r8;
        r5 = r5 + 1;
        goto L9
    L16:
        int r33 = r32 - 1;
        double r52 = r4[r33] - r4[0];
        int r7 = 0;
    L17:
        if (r7 >= r33) goto L23;
        double r102 = r4[r7];
        r7 = r7 + 1;
        double r103 = (r102 + r4[r7]) * 0.5d;
        if (r52 != 0.0d) goto L21;
        double r104 = 1.0d;
    L22:
        r0.put(Double.valueOf(r104), Integer.valueOf(r2));
        goto L17
    L21:
        r104 = (r103 - r4[0]) / r52;
    L23:
        r2 = r2 + 1;
        goto L4
    L25:
        return ImmutableList.copyOf(r0.values());
    }

    private long getTotalAllocatableBandwidth(long r8) {
        long r0 = this.bandwidthMeter.getBitrateEstimate();
        this.latestBitrateEstimate = r0;
        long r02 = (long) (r0 * this.bandwidthFraction);
        long r2 = this.bandwidthMeter.getTimeToFirstByteEstimateUs();
        if (r2 == (-9223372036854775807L)) goto L10;
        if (r8 == (-9223372036854775807L)) goto L10;
        float r82 = r8;
        return (long) ((r02 * Math.max((r82 / this.playbackSpeed) - r2, 0.0f)) / r82);
    L10:
        return (long) (r02 / this.playbackSpeed);
    }

    private long minDurationForQualityIncreaseUs(long r4, long r6) {
        if (r4 != (-9223372036854775807L)) goto L7;
        return this.minDurationForQualityIncreaseUs;
    L7:
        if (r6 == (-9223372036854775807L)) goto L10;
        r4 = r4 - r6;
    L10:
        return Math.min((long) (r4 * this.bufferedFractionToLiveEdgeForQualityIncrease), this.minDurationForQualityIncreaseUs);
    }

    public boolean canSelectFormat(Format r1, int r2, long r3) {
        if (r1.cached == false) goto L5;
        return true;
    L5:
        if (r2 <= r3) goto L11;
        return false;
    L11:
        return true;
    }

    @Override // androidx.media3.exoplayer.trackselection.BaseTrackSelection, androidx.media3.exoplayer.trackselection.ExoTrackSelection
    public void disable() {
        this.lastBufferEvaluationMediaChunk = null;
    }

    @Override // androidx.media3.exoplayer.trackselection.BaseTrackSelection, androidx.media3.exoplayer.trackselection.ExoTrackSelection
    public void enable() {
        this.lastBufferEvaluationMs = -9223372036854775807L;
        this.lastBufferEvaluationMediaChunk = null;
    }

    @Override // androidx.media3.exoplayer.trackselection.BaseTrackSelection, androidx.media3.exoplayer.trackselection.ExoTrackSelection
    public int evaluateQueueSize(long r11, List<? extends MediaChunk> r13) {
        long r3 = this.clock.elapsedRealtime();
        if (shouldEvaluateQueueSize(r3, r13) == false) goto L5;
        this.lastBufferEvaluationMs = r3;
        if (r13.isEmpty() == false) goto L9;
        MediaChunk r0 = null;
    L10:
        this.lastBufferEvaluationMediaChunk = r0;
        int r7 = 0;
        if (r13.isEmpty() == false) goto L13;
        return 0;
    L13:
        int r02 = r13.size();
        long r1 = Util.getPlayoutDurationForMediaDuration(r13.get(r02 - 1).startTimeUs - r11, this.playbackSpeed);
        long r8 = getMinDurationToRetainAfterDiscardUs();
        if (r1 < r8) goto L34;
        Format r10 = getFormat(determineIdealSelectedIndex(-1, r3, getLastChunkDurationUs(r13)));
    L17:
        if (r7 >= r02) goto L34;
        MediaChunk r2 = r13.get(r7);
        Format r32 = r2.trackFormat;
        if (Util.getPlayoutDurationForMediaDuration(r2.startTimeUs - r11, this.playbackSpeed) < r8) goto L33;
        if (r32.bitrate >= r10.bitrate) goto L33;
        int r22 = r32.height;
        if (r22 == (-1)) goto L33;
        if (r22 > this.maxHeightToDiscard) goto L33;
        int r33 = r32.width;
        if (r33 == (-1)) goto L33;
        if (r33 > this.maxWidthToDiscard) goto L33;
        if (r22 >= r10.height) goto L33;
        return r7;
    L33:
        r7 = r7 + 1;
    L34:
        return r02;
    L9:
        r0 = (MediaChunk) Iterables.getLast(r13);
        goto L10
    L5:
        return r13.size();
    }

    public long getMinDurationToRetainAfterDiscardUs() {
        return this.minDurationToRetainAfterDiscardUs;
    }

    @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
    public int getSelectedIndex() {
        return this.selectedIndex;
    }

    @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
    public Object getSelectionData() {
        return null;
    }

    @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
    public int getSelectionReason() {
        return this.reason;
    }

    @Override // androidx.media3.exoplayer.trackselection.BaseTrackSelection, androidx.media3.exoplayer.trackselection.ExoTrackSelection
    public void onPlaybackSpeed(float r1) {
        this.playbackSpeed = r1;
    }

    public boolean shouldEvaluateQueueSize(long r5, List<? extends MediaChunk> r7) {
        long r0 = this.lastBufferEvaluationMs;
        if (r0 != (-9223372036854775807L)) goto L5;
        return true;
    L5:
        if ((r5 - r0) < 1000) goto L7;
        return true;
    L7:
        if (r7.isEmpty() == false) goto L9;
        return false;
    L9:
        if (((MediaChunk) Iterables.getLast(r7)).equals(this.lastBufferEvaluationMediaChunk) == false) goto L17;
        return false;
    L17:
        return true;
    }

    @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
    public void updateSelectedTrack(long r7, long r9, long r11, List<? extends MediaChunk> r13, MediaChunkIterator[] r14) {
        long r2 = this.clock.elapsedRealtime();
        long r4 = getNextChunkDurationUs(r14, r13);
        int r72 = this.reason;
        if (r72 != 0) goto L6;
        this.reason = 1;
        this.selectedIndex = determineIdealSelectedIndex(0, r2, r4);
        return;
    L6:
        int r6 = this.selectedIndex;
        if (r13.isEmpty() == false) goto L9;
        int r142 = -1;
    L10:
        if (r142 == (-1)) goto L12;
        r72 = ((MediaChunk) Iterables.getLast(r13)).trackSelectionReason;
        r6 = r142;
    L12:
        int r8 = determineIdealSelectedIndex(1, r2, r4);
        if (r8 != r6) goto L15;
    L25:
        if (r8 == r6) goto L28;
        r72 = 3;
    L28:
        this.reason = r72;
        this.selectedIndex = r8;
        return;
    L15:
        if (isTrackExcluded(r6, r2) == true) goto L25;
        Format r132 = getFormat(r6);
        Format r143 = getFormat(r8);
        long r112 = minDurationForQualityIncreaseUs(r11, r4);
        int r144 = r143.bitrate;
        int r133 = r132.bitrate;
        if (r144 > r133) goto L19;
    L21:
        if (r144 >= r133) goto L25;
        if (r9 < this.maxDurationForQualityDecreaseUs) goto L25;
    L24:
        r8 = r6;
        goto L25
    L19:
        if (r9 >= r112) goto L21;
    L9:
        r142 = indexOf(((MediaChunk) Iterables.getLast(r13)).trackFormat);
        goto L10
    }
}
