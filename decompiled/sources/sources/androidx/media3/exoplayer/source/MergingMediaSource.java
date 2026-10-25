package androidx.media3.exoplayer.source;

import androidx.media3.common.MediaItem;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.Assertions;
import androidx.media3.datasource.TransferListener;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.upstream.Allocator;
import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public final class MergingMediaSource extends CompositeMediaSource<Integer> {
    private static final MediaItem PLACEHOLDER_MEDIA_ITEM = null;
    private final boolean adjustPeriodTimeOffsets;
    private final boolean clipDurations;
    private final Map<Object, Long> clippedDurationsUs;
    private final Multimap<Object, ClippingMediaPeriod> clippedMediaPeriods;
    private final CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory;
    private final List<List<MediaPeriodAndId>> mediaPeriods;
    private final MediaSource[] mediaSources;
    private IllegalMergeException mergeError;
    private final ArrayList<MediaSource> pendingTimelineSources;
    private int periodCount;
    private long[][] periodTimeOffsetsUs;
    private final Timeline[] timelines;

    /* renamed from: androidx.media3.exoplayer.source.MergingMediaSource$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class ClippedTimeline extends ForwardingTimeline {
        private final long[] periodDurationsUs;
        private final long[] windowDurationsUs;

        public ClippedTimeline(Timeline r12, Map<Object, Long> r13) {
            super(r12);
            int r0 = r12.getWindowCount();
            this.windowDurationsUs = new long[r12.getWindowCount()];
            Timeline.Window r1 = new Timeline.Window();
            int r2 = 0;
            int r3 = 0;
        L3:
            if (r3 >= r0) goto L5;
            this.windowDurationsUs[r3] = r12.getWindow(r3, r1).durationUs;
            r3 = r3 + 1;
            goto L3
        L5:
            int r02 = r12.getPeriodCount();
            this.periodDurationsUs = new long[r02];
            Timeline.Period r14 = new Timeline.Period();
        L6:
            if (r2 >= r02) goto L15;
            r12.getPeriod(r2, r14, true);
            long r32 = ((Long) Assertions.checkNotNull(r13.get(r14.uid))).longValue();
            long[] r5 = this.periodDurationsUs;
            if (r32 != Long.MIN_VALUE) goto L11;
            r32 = r14.durationUs;
        L11:
            r5[r2] = r32;
            long r52 = r14.durationUs;
            if (r52 == (-9223372036854775807L)) goto L14;
            long[] r7 = this.windowDurationsUs;
            int r8 = r14.windowIndex;
            r7[r8] = r7[r8] - (r52 - r32);
        L14:
            r2 = r2 + 1;
            goto L6
        }

        @Override // androidx.media3.exoplayer.source.ForwardingTimeline, androidx.media3.common.Timeline
        public Timeline.Period getPeriod(int r3, Timeline.Period r4, boolean r5) {
            super.getPeriod(r3, r4, r5);
            r4.durationUs = this.periodDurationsUs[r3];
            return r4;
        }

        @Override // androidx.media3.exoplayer.source.ForwardingTimeline, androidx.media3.common.Timeline
        public Timeline.Window getWindow(int r3, Timeline.Window r4, long r5) {
            super.getWindow(r3, r4, r5);
            long r52 = this.windowDurationsUs[r3];
            r4.durationUs = r52;
            if (r52 == (-9223372036854775807L)) goto L8;
            long r0 = r4.defaultPositionUs;
            if (r0 == (-9223372036854775807L)) goto L8;
            long r2 = Math.min(r0, r52);
        L9:
            r4.defaultPositionUs = r2;
            return r4;
        L8:
            r2 = r4.defaultPositionUs;
            goto L9
        }
    }

    public static final class IllegalMergeException extends IOException {
        public final int reason;

        public IllegalMergeException(int r1) {
            this.reason = r1;
        }
    }

    public static final class MediaPeriodAndId {
        private final MediaPeriod mediaPeriod;
        private final MediaSource.MediaPeriodId mediaPeriodId;

        private MediaPeriodAndId(MediaSource.MediaPeriodId r1, MediaPeriod r2) {
            this.mediaPeriodId = r1;
            this.mediaPeriod = r2;
        }

        public /* synthetic */ MediaPeriodAndId(MediaSource.MediaPeriodId r1, MediaPeriod r2, AnonymousClass1 r3) {
            this(r1, r2);
        }

        public static /* synthetic */ MediaPeriod access$100(MediaPeriodAndId r0) {
            return r0.mediaPeriod;
        }

        public static /* synthetic */ MediaSource.MediaPeriodId access$200(MediaPeriodAndId r0) {
            return r0.mediaPeriodId;
        }
    }

    static {
        PLACEHOLDER_MEDIA_ITEM = new MediaItem.Builder().setMediaId("MergingMediaSource").build();
    }

    public MergingMediaSource(boolean r2, boolean r3, CompositeSequenceableLoaderFactory r4, MediaSource... r5) {
        this.adjustPeriodTimeOffsets = r2;
        this.clipDurations = r3;
        this.mediaSources = r5;
        this.compositeSequenceableLoaderFactory = r4;
        this.pendingTimelineSources = new ArrayList(Arrays.asList(r5));
        this.periodCount = -1;
        this.mediaPeriods = new ArrayList(r5.length);
        int r32 = 0;
    L4:
        if (r32 >= r5.length) goto L6;
        this.mediaPeriods.add(new ArrayList());
        r32 = r32 + 1;
        goto L4
    L6:
        this.timelines = new Timeline[r5.length];
        this.periodTimeOffsetsUs = new long[0][];
        this.clippedDurationsUs = new HashMap();
        this.clippedMediaPeriods = MultimapBuilder.hashKeys().arrayListValues().build();
    }

    public MergingMediaSource(boolean r2, boolean r3, MediaSource... r4) {
        this(r2, r3, new DefaultCompositeSequenceableLoaderFactory(), r4);
    }

    public MergingMediaSource(boolean r2, MediaSource... r3) {
        this(r2, false, r3);
    }

    public MergingMediaSource(MediaSource... r2) {
        this(false, r2);
    }

    private void computePeriodTimeOffsets() {
        Timeline.Period r0 = new Timeline.Period();
        int r2 = 0;
    L4:
        if (r2 >= this.periodCount) goto L10;
        long r3 = -this.timelines[0].getPeriod(r2, r0).getPositionInWindowUs();
        int r5 = 1;
    L6:
        Timeline[] r6 = this.timelines;
        if (r5 >= r6.length) goto L9;
        this.periodTimeOffsetsUs[r2][r5] = r3 - (-r6[r5].getPeriod(r2, r0).getPositionInWindowUs());
        r5 = r5 + 1;
        goto L6
    L9:
        r2 = r2 + 1;
        goto L4
    }

    private void updateClippedDuration() {
        Timeline.Period r0 = new Timeline.Period();
        int r2 = 0;
    L4:
        if (r2 >= this.periodCount) goto L22;
        int r5 = 0;
        long r6 = Long.MIN_VALUE;
    L6:
        Timeline[] r8 = this.timelines;
        if (r5 >= r8.length) goto L17;
        long r82 = r8[r5].getPeriod(r2, r0).getDurationUs();
        if (r82 == (-9223372036854775807L)) goto L16;
        long r83 = r82 + this.periodTimeOffsetsUs[r2][r5];
        if (r6 != Long.MIN_VALUE) goto L14;
    L15:
        r6 = r83;
        goto L16
    L14:
        if (r83 < r6) goto L15;
    L16:
        r5 = r5 + 1;
        goto L6
    L17:
        Object r3 = r8[0].getUidOfPeriod(r2);
        this.clippedDurationsUs.put(r3, Long.valueOf(r6));
        Iterator<ClippingMediaPeriod> r32 = this.clippedMediaPeriods.get(r3).iterator();
    L19:
        if (r32.hasNext() == false) goto L21;
        r32.next().updateClipping(0, r6);
        goto L19
    L21:
        r2 = r2 + 1;
        goto L4
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public boolean canUpdateMediaItem(MediaItem r3) {
        MediaSource[] r2 = this.mediaSources;
        if (r2.length > 0) goto L5;
    L8:
        return false;
    L5:
        if (r2[0].canUpdateMediaItem(r3) == false) goto L8;
        return true;
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public MediaPeriod createPeriod(MediaSource.MediaPeriodId r12, Allocator r13, long r14) {
        int r0 = this.mediaSources.length;
        MediaPeriod[] r1 = new MediaPeriod[r0];
        int r3 = 0;
        int r2 = this.timelines[0].getIndexOfPeriod(r12.periodUid);
    L3:
        if (r3 >= r0) goto L5;
        MediaSource.MediaPeriodId r4 = r12.copyWithPeriodUid(this.timelines[r3].getUidOfPeriod(r2));
        r1[r3] = this.mediaSources[r3].createPeriod(r4, r13, r14 - this.periodTimeOffsetsUs[r2][r3]);
        this.mediaPeriods.get(r3).add(new MediaPeriodAndId(r4, r1[r3], null));
        r3 = r3 + 1;
        goto L3
    L5:
        MergingMediaPeriod r5 = new MergingMediaPeriod(this.compositeSequenceableLoaderFactory, this.periodTimeOffsetsUs[r2], r1);
        if (this.clipDurations == false) goto L9;
        ClippingMediaPeriod r42 = new ClippingMediaPeriod(r5, false, 0, ((Long) Assertions.checkNotNull(this.clippedDurationsUs.get(r12.periodUid))).longValue());
        this.clippedMediaPeriods.put(r12.periodUid, r42);
        return r42;
    L9:
        return r5;
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public MediaItem getMediaItem() {
        MediaSource[] r1 = this.mediaSources;
        if (r1.length <= 0) goto L7;
        return r1[0].getMediaItem();
    L7:
        return PLACEHOLDER_MEDIA_ITEM;
    }

    /* renamed from: getMediaPeriodIdForChildMediaPeriodId, reason: avoid collision after fix types in other method */
    public MediaSource.MediaPeriodId getMediaPeriodIdForChildMediaPeriodId2(Integer r4, MediaSource.MediaPeriodId r5) {
        List<MediaPeriodAndId> r42 = this.mediaPeriods.get(r4.intValue());
        int r1 = 0;
    L4:
        if (r1 >= r42.size()) goto L10;
        if (MediaPeriodAndId.access$200(r42.get(r1)).equals(r5) == true) goto L8;
        r1 = r1 + 1;
        goto L4
    L8:
        return MediaPeriodAndId.access$200(this.mediaPeriods.get(0).get(r1));
    L10:
        return null;
    }

    @Override // androidx.media3.exoplayer.source.CompositeMediaSource
    public /* bridge */ /* synthetic */ MediaSource.MediaPeriodId getMediaPeriodIdForChildMediaPeriodId(Integer r1, MediaSource.MediaPeriodId r2) {
        return getMediaPeriodIdForChildMediaPeriodId2(r1, r2);
    }

    @Override // androidx.media3.exoplayer.source.CompositeMediaSource, androidx.media3.exoplayer.source.MediaSource
    public void maybeThrowSourceInfoRefreshError() {
        IllegalMergeException r0 = this.mergeError;
        if (r0 != null) goto L6;
        super.maybeThrowSourceInfoRefreshError();
        return;
    L6:
        throw r0;
    }

    /* renamed from: onChildSourceInfoRefreshed, reason: avoid collision after fix types in other method */
    public void onChildSourceInfoRefreshed2(Integer r6, MediaSource r7, Timeline r8) {
        if (this.mergeError == null) goto L6;
        return;
    L6:
        if (this.periodCount != (-1)) goto L9;
        this.periodCount = r8.getPeriodCount();
    L13:
        if (this.periodTimeOffsetsUs.length != 0) goto L15;
        this.periodTimeOffsetsUs = (long[][]) Array.newInstance(Long.TYPE, new int[]{this.periodCount, this.timelines.length});
    L15:
        this.pendingTimelineSources.remove(r7);
        this.timelines[r6.intValue()] = r8;
        if (this.pendingTimelineSources.isEmpty() == true) goto L18;
        return;
    L18:
        if (this.adjustPeriodTimeOffsets == false) goto L20;
        computePeriodTimeOffsets();
    L20:
        Timeline r62 = this.timelines[0];
        if (this.clipDurations == false) goto L23;
        updateClippedDuration();
        r62 = new ClippedTimeline(r62, this.clippedDurationsUs);
    L23:
        refreshSourceInfo(r62);
        return;
    L9:
        if (r8.getPeriodCount() == this.periodCount) goto L13;
        this.mergeError = new IllegalMergeException(0);
    }

    @Override // androidx.media3.exoplayer.source.CompositeMediaSource
    public /* bridge */ /* synthetic */ void onChildSourceInfoRefreshed(Integer r1, MediaSource r2, Timeline r3) {
        onChildSourceInfoRefreshed2(r1, r2, r3);
    }

    @Override // androidx.media3.exoplayer.source.CompositeMediaSource, androidx.media3.exoplayer.source.BaseMediaSource
    public void prepareSourceInternal(TransferListener r3) {
        super.prepareSourceInternal(r3);
        int r32 = 0;
    L4:
        if (r32 >= this.mediaSources.length) goto L6;
        prepareChildSource(Integer.valueOf(r32), this.mediaSources[r32]);
        r32 = r32 + 1;
        goto L4
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public void releasePeriod(MediaPeriod r7) {
        if (this.clipDurations == false) goto L11;
        ClippingMediaPeriod r72 = (ClippingMediaPeriod) r7;
        Iterator<Map.Entry<Object, ClippingMediaPeriod>> r0 = this.clippedMediaPeriods.entries().iterator();
    L6:
        if (r0.hasNext() == false) goto L10;
        Map.Entry<Object, ClippingMediaPeriod> r1 = r0.next();
        if (r1.getValue().equals(r72) == false) goto L6;
        this.clippedMediaPeriods.remove(r1.getKey(), r1.getValue());
    L10:
        r7 = r72.mediaPeriod;
    L11:
        MergingMediaPeriod r73 = (MergingMediaPeriod) r7;
        int r12 = 0;
    L13:
        if (r12 >= this.mediaSources.length) goto L22;
        List<MediaPeriodAndId> r2 = this.mediaPeriods.get(r12);
        MediaPeriod r3 = r73.getChildPeriod(r12);
        int r4 = 0;
    L16:
        if (r4 >= r2.size()) goto L21;
        if (MediaPeriodAndId.access$100(r2.get(r4)).equals(r3) == true) goto L19;
        r4 = r4 + 1;
        goto L16
    L19:
        r2.remove(r4);
    L21:
        this.mediaSources[r12].releasePeriod(r73.getChildPeriod(r12));
        r12 = r12 + 1;
        goto L13
    }

    @Override // androidx.media3.exoplayer.source.CompositeMediaSource, androidx.media3.exoplayer.source.BaseMediaSource
    public void releaseSourceInternal() {
        super.releaseSourceInternal();
        Arrays.fill(this.timelines, null);
        this.periodCount = -1;
        this.mergeError = null;
        this.pendingTimelineSources.clear();
        Collections.addAll(this.pendingTimelineSources, this.mediaSources);
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public void updateMediaItem(MediaItem r2) {
        this.mediaSources[0].updateMediaItem(r2);
    }
}
