package androidx.media3.exoplayer;

import android.util.Pair;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.Assertions;
import androidx.media3.exoplayer.source.ShuffleOrder;

/* loaded from: classes4.dex */
public abstract class AbstractConcatenatedTimeline extends Timeline {
    private final int childCount;
    private final boolean isAtomic;
    private final ShuffleOrder shuffleOrder;

    public AbstractConcatenatedTimeline(boolean r1, ShuffleOrder r2) {
        this.isAtomic = r1;
        this.shuffleOrder = r2;
        this.childCount = r2.getLength();
    }

    public static Object getChildPeriodUidFromConcatenatedUid(Object r0) {
        return ((Pair) r0).second;
    }

    public static Object getChildTimelineUidFromConcatenatedUid(Object r0) {
        return ((Pair) r0).first;
    }

    public static Object getConcatenatedUid(Object r0, Object r1) {
        return Pair.create(r0, r1);
    }

    private int getNextChildIndex(int r1, boolean r2) {
        if (r2 == false) goto L6;
        return this.shuffleOrder.getNextIndex(r1);
    L6:
        if (r1 < (this.childCount - 1)) goto L8;
        return -1;
    L8:
        return r1 + 1;
    }

    private int getPreviousChildIndex(int r1, boolean r2) {
        if (r2 == true) goto L4;
        if (r1 > 0) goto L7;
        return -1;
    L7:
        return r1 - 1;
    L4:
        return this.shuffleOrder.getPreviousIndex(r1);
    }

    public abstract int getChildIndexByChildUid(Object r1);

    public abstract int getChildIndexByPeriodIndex(int r1);

    public abstract int getChildIndexByWindowIndex(int r1);

    public abstract Object getChildUidByChildIndex(int r1);

    public abstract int getFirstPeriodIndexByChildIndex(int r1);

    @Override // androidx.media3.common.Timeline
    public int getFirstWindowIndex(boolean r4) {
        if (this.childCount != 0) goto L5;
        return -1;
    L5:
        int r2 = 0;
        if (this.isAtomic == false) goto L8;
        r4 = false;
    L8:
        if (r4 == false) goto L11;
        r2 = this.shuffleOrder.getFirstIndex();
    L11:
        if (getTimelineByChildIndex(r2).isEmpty() == false) goto L16;
        r2 = getNextChildIndex(r2, r4);
        if (r2 != (-1)) goto L11;
        return -1;
    L16:
        return getFirstWindowIndexByChildIndex(r2) + getTimelineByChildIndex(r2).getFirstWindowIndex(r4);
    }

    public abstract int getFirstWindowIndexByChildIndex(int r1);

    @Override // androidx.media3.common.Timeline
    public final int getIndexOfPeriod(Object r4) {
        if ((r4 instanceof Pair) == true) goto L5;
        return -1;
    L5:
        Object r0 = getChildTimelineUidFromConcatenatedUid(r4);
        Object r42 = getChildPeriodUidFromConcatenatedUid(r4);
        int r02 = getChildIndexByChildUid(r0);
        if (r02 != (-1)) goto L8;
        return -1;
    L8:
        int r43 = getTimelineByChildIndex(r02).getIndexOfPeriod(r42);
        if (r43 != (-1)) goto L12;
        return -1;
    L12:
        return getFirstPeriodIndexByChildIndex(r02) + r43;
    }

    @Override // androidx.media3.common.Timeline
    public int getLastWindowIndex(boolean r4) {
        int r0 = this.childCount;
        if (r0 != 0) goto L6;
        return -1;
    L6:
        if (this.isAtomic == false) goto L8;
        r4 = false;
    L8:
        if (r4 == false) goto L10;
        int r02 = this.shuffleOrder.getLastIndex();
    L12:
        if (getTimelineByChildIndex(r02).isEmpty() == false) goto L17;
        r02 = getPreviousChildIndex(r02, r4);
        if (r02 != (-1)) goto L12;
        return -1;
    L17:
        return getFirstWindowIndexByChildIndex(r02) + getTimelineByChildIndex(r02).getLastWindowIndex(r4);
    L10:
        r02 = r0 - 1;
        goto L12
    }

    @Override // androidx.media3.common.Timeline
    public int getNextWindowIndex(int r6, int r7, boolean r8) {
        int r1 = 0;
        if (this.isAtomic == true) goto L5;
    L8:
        int r0 = getChildIndexByWindowIndex(r6);
        int r3 = getFirstWindowIndexByChildIndex(r0);
        Timeline r4 = getTimelineByChildIndex(r0);
        int r62 = r6 - r3;
        if (r7 == 2) goto L12;
        r1 = r7;
    L12:
        int r63 = r4.getNextWindowIndex(r62, r1, r8);
        if (r63 != (-1)) goto L15;
        int r64 = getNextChildIndex(r0, r8);
    L17:
        if (r64 == (-1)) goto L21;
        if (getTimelineByChildIndex(r64).isEmpty() == false) goto L21;
        r64 = getNextChildIndex(r64, r8);
    L21:
        if (r64 != (-1)) goto L23;
        if (r7 == 2) goto L26;
        return -1;
    L26:
        return getFirstWindowIndex(r8);
    L23:
        return getFirstWindowIndexByChildIndex(r64) + getTimelineByChildIndex(r64).getFirstWindowIndex(r8);
    L15:
        return r3 + r63;
    L5:
        if (r7 != 1) goto L7;
        r7 = 2;
    L7:
        r8 = false;
        goto L8
    }

    @Override // androidx.media3.common.Timeline
    public final Timeline.Period getPeriod(int r5, Timeline.Period r6, boolean r7) {
        int r0 = getChildIndexByPeriodIndex(r5);
        int r1 = getFirstWindowIndexByChildIndex(r0);
        getTimelineByChildIndex(r0).getPeriod(r5 - getFirstPeriodIndexByChildIndex(r0), r6, r7);
        r6.windowIndex += r1;
        if (r7 == false) goto L5;
        r6.uid = getConcatenatedUid(getChildUidByChildIndex(r0), Assertions.checkNotNull(r6.uid));
    L5:
        return r6;
    }

    @Override // androidx.media3.common.Timeline
    public final Timeline.Period getPeriodByUid(Object r4, Timeline.Period r5) {
        Object r0 = getChildTimelineUidFromConcatenatedUid(r4);
        Object r1 = getChildPeriodUidFromConcatenatedUid(r4);
        int r02 = getChildIndexByChildUid(r0);
        int r2 = getFirstWindowIndexByChildIndex(r02);
        getTimelineByChildIndex(r02).getPeriodByUid(r1, r5);
        r5.windowIndex += r2;
        r5.uid = r4;
        return r5;
    }

    @Override // androidx.media3.common.Timeline
    public int getPreviousWindowIndex(int r6, int r7, boolean r8) {
        int r1 = 0;
        if (this.isAtomic == true) goto L5;
    L8:
        int r0 = getChildIndexByWindowIndex(r6);
        int r3 = getFirstWindowIndexByChildIndex(r0);
        Timeline r4 = getTimelineByChildIndex(r0);
        int r62 = r6 - r3;
        if (r7 == 2) goto L12;
        r1 = r7;
    L12:
        int r63 = r4.getPreviousWindowIndex(r62, r1, r8);
        if (r63 != (-1)) goto L15;
        int r64 = getPreviousChildIndex(r0, r8);
    L17:
        if (r64 == (-1)) goto L21;
        if (getTimelineByChildIndex(r64).isEmpty() == false) goto L21;
        r64 = getPreviousChildIndex(r64, r8);
    L21:
        if (r64 != (-1)) goto L23;
        if (r7 == 2) goto L26;
        return -1;
    L26:
        return getLastWindowIndex(r8);
    L23:
        return getFirstWindowIndexByChildIndex(r64) + getTimelineByChildIndex(r64).getLastWindowIndex(r8);
    L15:
        return r3 + r63;
    L5:
        if (r7 != 1) goto L7;
        r7 = 2;
    L7:
        r8 = false;
        goto L8
    }

    public abstract Timeline getTimelineByChildIndex(int r1);

    @Override // androidx.media3.common.Timeline
    public final Object getUidOfPeriod(int r4) {
        int r0 = getChildIndexByPeriodIndex(r4);
        int r1 = getFirstPeriodIndexByChildIndex(r0);
        Object r42 = getTimelineByChildIndex(r0).getUidOfPeriod(r4 - r1);
        return getConcatenatedUid(getChildUidByChildIndex(r0), r42);
    }

    @Override // androidx.media3.common.Timeline
    public final Timeline.Window getWindow(int r5, Timeline.Window r6, long r7) {
        int r0 = getChildIndexByWindowIndex(r5);
        int r1 = getFirstWindowIndexByChildIndex(r0);
        int r2 = getFirstPeriodIndexByChildIndex(r0);
        getTimelineByChildIndex(r0).getWindow(r5 - r1, r6, r7);
        Object r4 = getChildUidByChildIndex(r0);
        if (Timeline.Window.SINGLE_WINDOW_UID.equals(r6.uid) == true) goto L6;
        r4 = getConcatenatedUid(r4, r6.uid);
    L6:
        r6.uid = r4;
        r6.firstPeriodIndex += r2;
        r6.lastPeriodIndex += r2;
        return r6;
    }
}
