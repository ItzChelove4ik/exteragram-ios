package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import java.util.ArrayList;

/* loaded from: classes3.dex */
class RunGroup {
    public static int index;
    public boolean dual;
    int mDirection;
    WidgetRun mFirstRun;
    int mGroupIndex;
    WidgetRun mLastRun;
    ArrayList<WidgetRun> mRuns;
    public int position;

    public RunGroup(WidgetRun r2, int r3) {
        this.position = 0;
        this.dual = false;
        this.mFirstRun = null;
        this.mLastRun = null;
        this.mRuns = new ArrayList();
        int r0 = index;
        this.mGroupIndex = r0;
        index = r0 + 1;
        this.mFirstRun = r2;
        this.mLastRun = r2;
        this.mDirection = r3;
    }

    private long traverseEnd(DependencyNode r9, long r10) {
        WidgetRun r0 = r9.mRun;
        if ((r0 instanceof HelperReferences) == false) goto L5;
        return r10;
    L5:
        int r1 = r9.mDependencies.size();
        int r2 = 0;
        long r3 = r10;
    L6:
        if (r2 >= r1) goto L15;
        Dependency r5 = r9.mDependencies.get(r2);
        if ((r5 instanceof DependencyNode) == false) goto L13;
        DependencyNode r52 = (DependencyNode) r5;
        if (r52.mRun == r0) goto L13;
        r3 = Math.min(r3, traverseEnd(r52, r52.mMargin + r10));
    L13:
        r2 = r2 + 1;
        goto L6
    L15:
        if (r9 != r0.end) goto L18;
        long r102 = r10 - r0.getWrapDimension();
        return Math.min(Math.min(r3, traverseEnd(r0.start, r102)), r102 - r0.start.mMargin);
    L18:
        return r3;
    }

    private long traverseStart(DependencyNode r9, long r10) {
        WidgetRun r0 = r9.mRun;
        if ((r0 instanceof HelperReferences) == false) goto L5;
        return r10;
    L5:
        int r1 = r9.mDependencies.size();
        int r2 = 0;
        long r3 = r10;
    L6:
        if (r2 >= r1) goto L15;
        Dependency r5 = r9.mDependencies.get(r2);
        if ((r5 instanceof DependencyNode) == false) goto L13;
        DependencyNode r52 = (DependencyNode) r5;
        if (r52.mRun == r0) goto L13;
        r3 = Math.max(r3, traverseStart(r52, r52.mMargin + r10));
    L13:
        r2 = r2 + 1;
        goto L6
    L15:
        if (r9 != r0.start) goto L18;
        long r102 = r10 + r0.getWrapDimension();
        return Math.max(Math.max(r3, traverseStart(r0.end, r102)), r102 - r0.end.mMargin);
    L18:
        return r3;
    }

    public void add(WidgetRun r2) {
        this.mRuns.add(r2);
        this.mLastRun = r2;
    }

    public long computeWrapSize(ConstraintWidgetContainer r12, int r13) {
        WidgetRun r0 = this.mFirstRun;
        long r2 = 0;
        if ((r0 instanceof ChainRun) == true) goto L5;
        if (r13 != 0) goto L12;
        if ((r0 instanceof HorizontalWidgetRun) == true) goto L14;
        return 0;
    L14:
        if (r13 != 0) goto L17;
        WidgetRun r1 = r12.mHorizontalRun;
    L16:
        DependencyNode r14 = r1.start;
        if (r13 != 0) goto L21;
        WidgetRun r122 = r12.mHorizontalRun;
    L20:
        DependencyNode r123 = r122.end;
        boolean r02 = r0.start.mTargets.contains(r14);
        boolean r124 = this.mFirstRun.end.mTargets.contains(r123);
        long r4 = this.mFirstRun.getWrapDimension();
        if (r02 == false) goto L36;
        if (r124 == false) goto L36;
        long r03 = traverseStart(this.mFirstRun.start, 0);
        long r6 = traverseEnd(this.mFirstRun.end, 0);
        long r04 = r03 - r4;
        WidgetRun r125 = this.mFirstRun;
        int r8 = r125.end.mMargin;
        if (r04 < (-r8)) goto L28;
        r04 = r04 + r8;
    L28:
        int r82 = r125.start.mMargin;
        long r62 = ((-r6) - r4) - r82;
        if (r62 < r82) goto L31;
        r62 = r62 - r82;
    L31:
        float r126 = r125.mWidget.getBiasPercent(r13);
        if (r126 <= 0.0f) goto L34;
        r2 = (long) ((r62 / r126) + (r04 / (1.0f - r126)));
    L34:
        float r132 = r2;
        long r22 = (((long) ((r132 * r126) + 0.5f)) + r4) + ((long) ((r132 * (1.0f - r126)) + 0.5f));
        WidgetRun r11 = this.mFirstRun;
        return (r11.start.mMargin + r22) - r11.end.mMargin;
    L36:
        if (r02 == true) goto L38;
        WidgetRun r133 = this.mFirstRun;
        if (r124 == false) goto L44;
        return Math.max(-traverseEnd(r133.end, r12.mMargin), (-this.mFirstRun.end.mMargin) + r4);
    L44:
        return (r133.start.mMargin + r133.getWrapDimension()) - this.mFirstRun.end.mMargin;
    L38:
        return Math.max(traverseStart(this.mFirstRun.start, r12.mMargin), this.mFirstRun.start.mMargin + r4);
    L21:
        r122 = r12.mVerticalRun;
        goto L20
    L17:
        r1 = r12.mVerticalRun;
        goto L16
    L12:
        if ((r0 instanceof VerticalWidgetRun) == true) goto L14;
        return 0;
    L5:
        if (((ChainRun) r0).orientation == r13) goto L14;
        return 0;
    }
}
