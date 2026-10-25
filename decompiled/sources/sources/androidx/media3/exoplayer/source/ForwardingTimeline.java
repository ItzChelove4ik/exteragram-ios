package androidx.media3.exoplayer.source;

import androidx.media3.common.Timeline;

/* loaded from: classes4.dex */
public abstract class ForwardingTimeline extends Timeline {
    protected final Timeline timeline;

    public ForwardingTimeline(Timeline r1) {
        this.timeline = r1;
    }

    @Override // androidx.media3.common.Timeline
    public final boolean equals(Object r1) {
        return super.equals(r1);
    }

    @Override // androidx.media3.common.Timeline
    public int getFirstWindowIndex(boolean r1) {
        return this.timeline.getFirstWindowIndex(r1);
    }

    @Override // androidx.media3.common.Timeline
    public int getIndexOfPeriod(Object r1) {
        return this.timeline.getIndexOfPeriod(r1);
    }

    @Override // androidx.media3.common.Timeline
    public int getLastWindowIndex(boolean r1) {
        return this.timeline.getLastWindowIndex(r1);
    }

    @Override // androidx.media3.common.Timeline
    public int getNextWindowIndex(int r1, int r2, boolean r3) {
        return this.timeline.getNextWindowIndex(r1, r2, r3);
    }

    @Override // androidx.media3.common.Timeline
    public Timeline.Period getPeriod(int r1, Timeline.Period r2, boolean r3) {
        return this.timeline.getPeriod(r1, r2, r3);
    }

    @Override // androidx.media3.common.Timeline
    public final Timeline.Period getPeriodByUid(Object r1, Timeline.Period r2) {
        return super.getPeriodByUid(r1, r2);
    }

    @Override // androidx.media3.common.Timeline
    public int getPeriodCount() {
        return this.timeline.getPeriodCount();
    }

    @Override // androidx.media3.common.Timeline
    public int getPreviousWindowIndex(int r1, int r2, boolean r3) {
        return this.timeline.getPreviousWindowIndex(r1, r2, r3);
    }

    @Override // androidx.media3.common.Timeline
    public Object getUidOfPeriod(int r1) {
        return this.timeline.getUidOfPeriod(r1);
    }

    @Override // androidx.media3.common.Timeline
    public Timeline.Window getWindow(int r1, Timeline.Window r2, long r3) {
        return this.timeline.getWindow(r1, r2, r3);
    }

    @Override // androidx.media3.common.Timeline
    public int getWindowCount() {
        return this.timeline.getWindowCount();
    }

    @Override // androidx.media3.common.Timeline
    public final int hashCode() {
        return super.hashCode();
    }
}
