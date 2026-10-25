package androidx.media3.exoplayer;

import androidx.media3.common.AdPlaybackState;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.source.ForwardingTimeline;
import androidx.media3.exoplayer.source.ShuffleOrder;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
final class PlaylistTimeline extends AbstractConcatenatedTimeline {
    private final HashMap<Object, Integer> childIndexByUid;
    private final int[] firstPeriodInChildIndices;
    private final int[] firstWindowInChildIndices;
    private final int periodCount;
    private final Timeline[] timelines;
    private final Object[] uids;
    private final int windowCount;

    /* renamed from: androidx.media3.exoplayer.PlaylistTimeline$1, reason: invalid class name */
    public class AnonymousClass1 extends ForwardingTimeline {
        final /* synthetic */ PlaylistTimeline this$0;
        private final Timeline.Window window;

        public AnonymousClass1(PlaylistTimeline r1, Timeline r2) {
            this.this$0 = r1;
            super(r2);
            this.window = new Timeline.Window();
        }

        @Override // androidx.media3.exoplayer.source.ForwardingTimeline, androidx.media3.common.Timeline
        public Timeline.Period getPeriod(int r11, Timeline.Period r12, boolean r13) {
            Timeline.Period r0 = super.getPeriod(r11, r12, r13);
            if (super.getWindow(r0.windowIndex, this.window).isLive() == false) goto L6;
            r0.set(r12.id, r12.uid, r12.windowIndex, r12.durationUs, r12.positionInWindowUs, AdPlaybackState.NONE, true);
            return r0;
        L6:
            r0.isPlaceholder = true;
            return r0;
        }
    }

    public PlaylistTimeline(Collection<? extends MediaSourceInfoHolder> r2, ShuffleOrder r3) {
        this(getTimelines(r2), getUids(r2), r3);
    }

    private PlaylistTimeline(Timeline[] r8, Object[] r9, ShuffleOrder r10) {
        int r0 = 0;
        super(false, r10);
        int r102 = r8.length;
        this.timelines = r8;
        this.firstPeriodInChildIndices = new int[r102];
        this.firstWindowInChildIndices = new int[r102];
        this.uids = r9;
        this.childIndexByUid = new HashMap();
        int r103 = r8.length;
        int r1 = 0;
        int r2 = 0;
        int r3 = 0;
    L3:
        if (r0 >= r103) goto L5;
        Timeline r4 = r8[r0];
        this.timelines[r3] = r4;
        this.firstWindowInChildIndices[r3] = r1;
        this.firstPeriodInChildIndices[r3] = r2;
        r1 = r1 + r4.getWindowCount();
        r2 = r2 + this.timelines[r3].getPeriodCount();
        this.childIndexByUid.put(r9[r3], Integer.valueOf(r3));
        r0 = r0 + 1;
        r3 = r3 + 1;
        goto L3
    L5:
        this.windowCount = r1;
        this.periodCount = r2;
    }

    private static Timeline[] getTimelines(Collection<? extends MediaSourceInfoHolder> r4) {
        Timeline[] r0 = new Timeline[r4.size()];
        Iterator<? extends MediaSourceInfoHolder> r42 = r4.iterator();
        int r1 = 0;
    L4:
        if (r42.hasNext() == false) goto L6;
        r0[r1] = r42.next().getTimeline();
        r1 = r1 + 1;
        goto L4
    L6:
        return r0;
    }

    private static Object[] getUids(Collection<? extends MediaSourceInfoHolder> r4) {
        Object[] r0 = new Object[r4.size()];
        Iterator<? extends MediaSourceInfoHolder> r42 = r4.iterator();
        int r1 = 0;
    L4:
        if (r42.hasNext() == false) goto L6;
        r0[r1] = r42.next().getUid();
        r1 = r1 + 1;
        goto L4
    L6:
        return r0;
    }

    public PlaylistTimeline copyWithPlaceholderTimeline(ShuffleOrder r5) {
        Timeline[] r0 = new Timeline[this.timelines.length];
        int r1 = 0;
    L3:
        Timeline[] r2 = this.timelines;
        if (r1 >= r2.length) goto L7;
        r0[r1] = new AnonymousClass1(this, r2[r1]);
        r1 = r1 + 1;
        goto L3
    L7:
        return new PlaylistTimeline(r0, this.uids, r5);
    }

    @Override // androidx.media3.exoplayer.AbstractConcatenatedTimeline
    public int getChildIndexByChildUid(Object r1) {
        Integer r0 = this.childIndexByUid.get(r1);
        if (r0 != null) goto L7;
        return -1;
    L7:
        return r0.intValue();
    }

    @Override // androidx.media3.exoplayer.AbstractConcatenatedTimeline
    public int getChildIndexByPeriodIndex(int r2) {
        return Util.binarySearchFloor(this.firstPeriodInChildIndices, r2 + 1, false, false);
    }

    @Override // androidx.media3.exoplayer.AbstractConcatenatedTimeline
    public int getChildIndexByWindowIndex(int r2) {
        return Util.binarySearchFloor(this.firstWindowInChildIndices, r2 + 1, false, false);
    }

    public List<Timeline> getChildTimelines() {
        return Arrays.asList(this.timelines);
    }

    @Override // androidx.media3.exoplayer.AbstractConcatenatedTimeline
    public Object getChildUidByChildIndex(int r1) {
        return this.uids[r1];
    }

    @Override // androidx.media3.exoplayer.AbstractConcatenatedTimeline
    public int getFirstPeriodIndexByChildIndex(int r1) {
        return this.firstPeriodInChildIndices[r1];
    }

    @Override // androidx.media3.exoplayer.AbstractConcatenatedTimeline
    public int getFirstWindowIndexByChildIndex(int r1) {
        return this.firstWindowInChildIndices[r1];
    }

    @Override // androidx.media3.common.Timeline
    public int getPeriodCount() {
        return this.periodCount;
    }

    @Override // androidx.media3.exoplayer.AbstractConcatenatedTimeline
    public Timeline getTimelineByChildIndex(int r1) {
        return this.timelines[r1];
    }

    @Override // androidx.media3.common.Timeline
    public int getWindowCount() {
        return this.windowCount;
    }
}
