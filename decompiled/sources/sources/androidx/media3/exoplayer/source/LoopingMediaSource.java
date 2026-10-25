package androidx.media3.exoplayer.source;

import androidx.media3.common.Timeline;
import androidx.media3.common.util.Assertions;
import androidx.media3.exoplayer.AbstractConcatenatedTimeline;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.ShuffleOrder;
import androidx.media3.exoplayer.upstream.Allocator;
import java.util.HashMap;
import java.util.Map;

@Deprecated
/* loaded from: classes4.dex */
public final class LoopingMediaSource extends WrappingMediaSource {
    private final Map<MediaSource.MediaPeriodId, MediaSource.MediaPeriodId> childMediaPeriodIdToMediaPeriodId;
    private final int loopCount;
    private final Map<MediaPeriod, MediaSource.MediaPeriodId> mediaPeriodToChildMediaPeriodId;

    public static final class InfinitelyLoopingTimeline extends ForwardingTimeline {
        public InfinitelyLoopingTimeline(Timeline r1) {
            super(r1);
        }

        @Override // androidx.media3.exoplayer.source.ForwardingTimeline, androidx.media3.common.Timeline
        public int getNextWindowIndex(int r2, int r3, boolean r4) {
            int r22 = this.timeline.getNextWindowIndex(r2, r3, r4);
            if (r22 == (-1)) goto L5;
            return r22;
        L5:
            return getFirstWindowIndex(r4);
        }

        @Override // androidx.media3.exoplayer.source.ForwardingTimeline, androidx.media3.common.Timeline
        public int getPreviousWindowIndex(int r2, int r3, boolean r4) {
            int r22 = this.timeline.getPreviousWindowIndex(r2, r3, r4);
            if (r22 == (-1)) goto L5;
            return r22;
        L5:
            return getLastWindowIndex(r4);
        }
    }

    public static final class LoopingTimeline extends AbstractConcatenatedTimeline {
        private final int childPeriodCount;
        private final Timeline childTimeline;
        private final int childWindowCount;
        private final int loopCount;

        public LoopingTimeline(Timeline r3, int r4) {
            boolean r1 = false;
            super(false, new ShuffleOrder.UnshuffledShuffleOrder(r4));
            this.childTimeline = r3;
            int r0 = r3.getPeriodCount();
            this.childPeriodCount = r0;
            this.childWindowCount = r3.getWindowCount();
            this.loopCount = r4;
            if (r0 > 0) goto L5;
            return;
        L5:
            if (r4 > (Integer.MAX_VALUE / r0)) goto L7;
            r1 = true;
        L7:
            Assertions.checkState(r1, "LoopingMediaSource contains too many periods");
        }

        @Override // androidx.media3.exoplayer.AbstractConcatenatedTimeline
        public int getChildIndexByChildUid(Object r1) {
            if ((r1 instanceof Integer) == true) goto L7;
            return -1;
        L7:
            return ((Integer) r1).intValue();
        }

        @Override // androidx.media3.exoplayer.AbstractConcatenatedTimeline
        public int getChildIndexByPeriodIndex(int r1) {
            return r1 / this.childPeriodCount;
        }

        @Override // androidx.media3.exoplayer.AbstractConcatenatedTimeline
        public int getChildIndexByWindowIndex(int r1) {
            return r1 / this.childWindowCount;
        }

        @Override // androidx.media3.exoplayer.AbstractConcatenatedTimeline
        public Object getChildUidByChildIndex(int r1) {
            return Integer.valueOf(r1);
        }

        @Override // androidx.media3.exoplayer.AbstractConcatenatedTimeline
        public int getFirstPeriodIndexByChildIndex(int r1) {
            return r1 * this.childPeriodCount;
        }

        @Override // androidx.media3.exoplayer.AbstractConcatenatedTimeline
        public int getFirstWindowIndexByChildIndex(int r1) {
            return r1 * this.childWindowCount;
        }

        @Override // androidx.media3.common.Timeline
        public int getPeriodCount() {
            return this.childPeriodCount * this.loopCount;
        }

        @Override // androidx.media3.exoplayer.AbstractConcatenatedTimeline
        public Timeline getTimelineByChildIndex(int r1) {
            return this.childTimeline;
        }

        @Override // androidx.media3.common.Timeline
        public int getWindowCount() {
            return this.childWindowCount * this.loopCount;
        }
    }

    public LoopingMediaSource(MediaSource r2) {
        this(r2, Integer.MAX_VALUE);
    }

    public LoopingMediaSource(MediaSource r3, int r4) {
        boolean r1 = false;
        super(new MaskingMediaSource(r3, false));
        if (r4 <= 0) goto L5;
        r1 = true;
    L5:
        Assertions.checkArgument(r1);
        this.loopCount = r4;
        this.childMediaPeriodIdToMediaPeriodId = new HashMap();
        this.mediaPeriodToChildMediaPeriodId = new HashMap();
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public MediaPeriod createPeriod(MediaSource.MediaPeriodId r3, Allocator r4, long r5) {
        if (this.loopCount == Integer.MAX_VALUE) goto L5;
        MediaSource.MediaPeriodId r0 = r3.copyWithPeriodUid(AbstractConcatenatedTimeline.getChildPeriodUidFromConcatenatedUid(r3.periodUid));
        this.childMediaPeriodIdToMediaPeriodId.put(r0, r3);
        MediaPeriod r32 = this.mediaSource.createPeriod(r0, r4, r5);
        this.mediaPeriodToChildMediaPeriodId.put(r32, r0);
        return r32;
    L5:
        return this.mediaSource.createPeriod(r3, r4, r5);
    }

    @Override // androidx.media3.exoplayer.source.WrappingMediaSource, androidx.media3.exoplayer.source.MediaSource
    public Timeline getInitialTimeline() {
        MaskingMediaSource r0 = (MaskingMediaSource) this.mediaSource;
        if (this.loopCount == Integer.MAX_VALUE) goto L7;
        return new LoopingTimeline(r0.getTimeline(), this.loopCount);
    L7:
        return new InfinitelyLoopingTimeline(r0.getTimeline());
    }

    @Override // androidx.media3.exoplayer.source.WrappingMediaSource
    public MediaSource.MediaPeriodId getMediaPeriodIdForChildMediaPeriodId(MediaSource.MediaPeriodId r3) {
        if (this.loopCount != Integer.MAX_VALUE) goto L5;
        return r3;
    L5:
        return this.childMediaPeriodIdToMediaPeriodId.get(r3);
    }

    @Override // androidx.media3.exoplayer.source.WrappingMediaSource, androidx.media3.exoplayer.source.MediaSource
    public boolean isSingleWindow() {
        return false;
    }

    @Override // androidx.media3.exoplayer.source.WrappingMediaSource
    public void onChildSourceInfoRefreshed(Timeline r3) {
        if (this.loopCount == Integer.MAX_VALUE) goto L5;
        Timeline r0 = new LoopingTimeline(r3, this.loopCount);
    L6:
        refreshSourceInfo(r0);
        return;
    L5:
        r0 = new InfinitelyLoopingTimeline(r3);
        goto L6
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public void releasePeriod(MediaPeriod r2) {
        this.mediaSource.releasePeriod(r2);
        MediaSource.MediaPeriodId r22 = this.mediaPeriodToChildMediaPeriodId.remove(r2);
        if (r22 == null) goto L6;
        this.childMediaPeriodIdToMediaPeriodId.remove(r22);
        return;
    }
}
