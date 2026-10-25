package androidx.constraintlayout.core.widgets;

import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import java.util.HashSet;

/* loaded from: classes3.dex */
public abstract class VirtualLayout extends HelperWidget {
    protected BasicMeasure.Measure mMeasure;
    private int mMeasuredHeight;
    private int mMeasuredWidth;
    BasicMeasure.Measurer mMeasurer;
    private boolean mNeedsCallFromSolver;
    private int mPaddingBottom;
    private int mPaddingEnd;
    private int mPaddingLeft;
    private int mPaddingRight;
    private int mPaddingStart;
    private int mPaddingTop;
    private int mResolvedPaddingLeft;
    private int mResolvedPaddingRight;

    public VirtualLayout() {
        this.mPaddingTop = 0;
        this.mPaddingBottom = 0;
        this.mPaddingLeft = 0;
        this.mPaddingRight = 0;
        this.mPaddingStart = 0;
        this.mPaddingEnd = 0;
        this.mResolvedPaddingLeft = 0;
        this.mResolvedPaddingRight = 0;
        this.mNeedsCallFromSolver = false;
        this.mMeasuredWidth = 0;
        this.mMeasuredHeight = 0;
        this.mMeasure = new BasicMeasure.Measure();
        this.mMeasurer = null;
    }

    public void applyRtl(boolean r3) {
        int r0 = this.mPaddingStart;
        if (r0 <= 0) goto L5;
    L8:
        if (r3 == false) goto L11;
        this.mResolvedPaddingLeft = this.mPaddingEnd;
        this.mResolvedPaddingRight = r0;
        return;
    L11:
        this.mResolvedPaddingLeft = r0;
        this.mResolvedPaddingRight = this.mPaddingEnd;
        return;
    L5:
        if (this.mPaddingEnd > 0) goto L8;
    }

    public void captureWidgets() {
        int r0 = 0;
    L4:
        if (r0 >= this.mWidgetsCount) goto L9;
        ConstraintWidget r1 = this.mWidgets[r0];
        if (r1 == null) goto L8;
        r1.setInVirtualLayout(true);
    L8:
        r0 = r0 + 1;
        goto L4
    }

    public boolean contains(HashSet<ConstraintWidget> r4) {
        int r1 = 0;
    L4:
        if (r1 >= this.mWidgetsCount) goto L10;
        if (r4.contains(this.mWidgets[r1]) == true) goto L7;
        r1 = r1 + 1;
        goto L4
    L7:
        return true;
    L10:
        return false;
    }

    public int getMeasuredHeight() {
        return this.mMeasuredHeight;
    }

    public int getMeasuredWidth() {
        return this.mMeasuredWidth;
    }

    public int getPaddingBottom() {
        return this.mPaddingBottom;
    }

    public int getPaddingLeft() {
        return this.mResolvedPaddingLeft;
    }

    public int getPaddingRight() {
        return this.mResolvedPaddingRight;
    }

    public int getPaddingTop() {
        return this.mPaddingTop;
    }

    public abstract void measure(int r1, int r2, int r3, int r4);

    public void measure(ConstraintWidget r2, ConstraintWidget.DimensionBehaviour r3, int r4, ConstraintWidget.DimensionBehaviour r5, int r6) {
    L3:
        if (this.mMeasurer != null) goto L7;
        if (getParent() == null) goto L7;
        this.mMeasurer = ((ConstraintWidgetContainer) getParent()).getMeasurer();
    L7:
        BasicMeasure.Measure r0 = this.mMeasure;
        r0.horizontalBehavior = r3;
        r0.verticalBehavior = r5;
        r0.horizontalDimension = r4;
        r0.verticalDimension = r6;
        this.mMeasurer.measure(r2, r0);
        r2.setWidth(this.mMeasure.measuredWidth);
        r2.setHeight(this.mMeasure.measuredHeight);
        r2.setHasBaseline(this.mMeasure.measuredHasBaseline);
        r2.setBaselineDistance(this.mMeasure.measuredBaseline);
    }

    public boolean measureChildren() {
        ConstraintWidget r0 = this.mParent;
        if (r0 == null) goto L5;
        BasicMeasure.Measurer r02 = ((ConstraintWidgetContainer) r0).getMeasurer();
    L7:
        if (r02 != null) goto L9;
        return false;
    L9:
        int r2 = 0;
    L11:
        if (r2 >= this.mWidgetsCount) goto L32;
        ConstraintWidget r3 = this.mWidgets[r2];
        if (r3 == null) goto L31;
        if ((r3 instanceof Guideline) == true) goto L31;
        ConstraintWidget.DimensionBehaviour r5 = r3.getDimensionBehaviour(0);
        ConstraintWidget.DimensionBehaviour r6 = r3.getDimensionBehaviour(1);
        ConstraintWidget.DimensionBehaviour r7 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        if (r5 == r7) goto L21;
    L26:
        if (r5 != r7) goto L28;
        r5 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
    L28:
        if (r6 != r7) goto L30;
        r6 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
    L30:
        BasicMeasure.Measure r4 = this.mMeasure;
        r4.horizontalBehavior = r5;
        r4.verticalBehavior = r6;
        r4.horizontalDimension = r3.getWidth();
        this.mMeasure.verticalDimension = r3.getHeight();
        r02.measure(r3, this.mMeasure);
        r3.setWidth(this.mMeasure.measuredWidth);
        r3.setHeight(this.mMeasure.measuredHeight);
        r3.setBaselineDistance(this.mMeasure.measuredBaseline);
        goto L31
    L21:
        if (r3.mMatchConstraintDefaultWidth == 1) goto L26;
        if (r6 != r7) goto L26;
        if (r3.mMatchConstraintDefaultHeight == 1) goto L26;
    L31:
        r2 = r2 + 1;
        goto L11
    L32:
        return true;
    L5:
        r02 = null;
        goto L7
    }

    public boolean needSolverPass() {
        return this.mNeedsCallFromSolver;
    }

    public void needsCallbackFromSolver(boolean r1) {
        this.mNeedsCallFromSolver = r1;
    }

    public void setMeasure(int r1, int r2) {
        this.mMeasuredWidth = r1;
        this.mMeasuredHeight = r2;
    }

    public void setPadding(int r1) {
        this.mPaddingLeft = r1;
        this.mPaddingTop = r1;
        this.mPaddingRight = r1;
        this.mPaddingBottom = r1;
        this.mPaddingStart = r1;
        this.mPaddingEnd = r1;
    }

    public void setPaddingBottom(int r1) {
        this.mPaddingBottom = r1;
    }

    public void setPaddingEnd(int r1) {
        this.mPaddingEnd = r1;
    }

    public void setPaddingLeft(int r1) {
        this.mPaddingLeft = r1;
        this.mResolvedPaddingLeft = r1;
    }

    public void setPaddingRight(int r1) {
        this.mPaddingRight = r1;
        this.mResolvedPaddingRight = r1;
    }

    public void setPaddingStart(int r1) {
        this.mPaddingStart = r1;
        this.mResolvedPaddingLeft = r1;
        this.mResolvedPaddingRight = r1;
    }

    public void setPaddingTop(int r1) {
        this.mPaddingTop = r1;
    }

    @Override // androidx.constraintlayout.core.widgets.HelperWidget, androidx.constraintlayout.core.widgets.Helper
    public void updateConstraints(ConstraintWidgetContainer r1) {
        captureWidgets();
    }
}
