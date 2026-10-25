package androidx.constraintlayout.core.widgets;

import androidx.constraintlayout.core.widgets.ConstraintWidget;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public class ChainHead {
    private boolean mDefined;
    protected ConstraintWidget mFirst;
    protected ConstraintWidget mFirstMatchConstraintWidget;
    protected ConstraintWidget mFirstVisibleWidget;
    protected boolean mHasComplexMatchWeights;
    protected boolean mHasDefinedWeights;
    protected boolean mHasRatio;
    protected boolean mHasUndefinedWeights;
    protected ConstraintWidget mHead;
    private boolean mIsRtl;
    protected ConstraintWidget mLast;
    protected ConstraintWidget mLastMatchConstraintWidget;
    protected ConstraintWidget mLastVisibleWidget;
    boolean mOptimizable;
    private int mOrientation;
    int mTotalMargins;
    int mTotalSize;
    protected float mTotalWeight;
    int mVisibleWidgets;
    protected ArrayList<ConstraintWidget> mWeightedMatchConstraintsWidgets;
    protected int mWidgetsCount;
    protected int mWidgetsMatchCount;

    public ChainHead(ConstraintWidget r2, int r3, boolean r4) {
        this.mTotalWeight = 0.0f;
        this.mFirst = r2;
        this.mOrientation = r3;
        this.mIsRtl = r4;
    }

    private void defineChainProperties() {
        int r0 = this.mOrientation * 2;
        ConstraintWidget r2 = this.mFirst;
        boolean r3 = true;
        this.mOptimizable = true;
        ConstraintWidget r5 = r2;
        boolean r6 = false;
    L3:
        if (r6 == true) goto L74;
        this.mWidgetsCount++;
        ConstraintWidget[] r7 = r2.mNextChainWidget;
        int r8 = this.mOrientation;
        ConstraintWidget r9 = null;
        r7[r8] = null;
        r2.mListNextMatchConstraintsWidget[r8] = null;
        if (r2.getVisibility() == 8) goto L60;
        this.mVisibleWidgets++;
        ConstraintWidget.DimensionBehaviour r72 = r2.getDimensionBehaviour(this.mOrientation);
        ConstraintWidget.DimensionBehaviour r82 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        if (r72 == r82) goto L9;
        this.mTotalSize += r2.getLength(this.mOrientation);
    L9:
        int r73 = this.mTotalSize + r2.mListAnchors[r0].getMargin();
        this.mTotalSize = r73;
        int r11 = r0 + 1;
        this.mTotalSize = r73 + r2.mListAnchors[r11].getMargin();
        int r74 = this.mTotalMargins + r2.mListAnchors[r0].getMargin();
        this.mTotalMargins = r74;
        this.mTotalMargins = r74 + r2.mListAnchors[r11].getMargin();
        if (this.mFirstVisibleWidget != null) goto L12;
        this.mFirstVisibleWidget = r2;
    L12:
        this.mLastVisibleWidget = r2;
        ConstraintWidget.DimensionBehaviour[] r75 = r2.mListDimensionBehaviors;
        int r10 = this.mOrientation;
        if (r75[r10] != r82) goto L60;
        int r76 = r2.mResolvedMatchConstraintDefault[r10];
        if (r76 != 0) goto L17;
    L19:
        this.mWidgetsMatchCount++;
        float r77 = r2.mWeight[r10];
        if (r77 <= 0.0f) goto L23;
        this.mTotalWeight += r77;
    L23:
        if (isMatchConstraintEqualityCandidate(r2, r10) == false) goto L33;
        if (r77 >= 0.0f) goto L27;
        this.mHasUndefinedWeights = true;
    L29:
        if (this.mWeightedMatchConstraintsWidgets != null) goto L31;
        this.mWeightedMatchConstraintsWidgets = new ArrayList();
    L31:
        this.mWeightedMatchConstraintsWidgets.add(r2);
        goto L33
    L27:
        this.mHasDefinedWeights = true;
    L33:
        if (this.mFirstMatchConstraintWidget != null) goto L35;
        this.mFirstMatchConstraintWidget = r2;
    L35:
        ConstraintWidget r78 = this.mLastMatchConstraintWidget;
        if (r78 == null) goto L38;
        r78.mListNextMatchConstraintsWidget[this.mOrientation] = r2;
    L38:
        this.mLastMatchConstraintWidget = r2;
    L40:
        if (this.mOrientation != 0) goto L50;
        if (r2.mMatchConstraintDefaultWidth == 0) goto L45;
        this.mOptimizable = false;
    L58:
        if (r2.mDimensionRatio == 0.0f) goto L60;
        this.mOptimizable = false;
        this.mHasRatio = true;
        goto L60
    L45:
        if (r2.mMatchConstraintMinWidth == 0) goto L47;
    L48:
        this.mOptimizable = false;
        goto L58
    L47:
        if (r2.mMatchConstraintMaxWidth == 0) goto L58;
    L50:
        if (r2.mMatchConstraintDefaultHeight == 0) goto L53;
        this.mOptimizable = false;
        goto L58
    L53:
        if (r2.mMatchConstraintMinHeight == 0) goto L55;
    L56:
        this.mOptimizable = false;
        goto L58
    L55:
        if (r2.mMatchConstraintMaxHeight == 0) goto L58;
    L17:
        if (r76 == 3) goto L19;
        if (r76 != 2) goto L40;
    L60:
        if (r5 == r2) goto L62;
        r5.mNextChainWidget[this.mOrientation] = r2;
    L62:
        ConstraintAnchor r52 = r2.mListAnchors[r0 + 1].mTarget;
        if (r52 == null) goto L70;
        ConstraintWidget r53 = r52.mOwner;
        ConstraintAnchor r79 = r53.mListAnchors[r0].mTarget;
        if (r79 == null) goto L70;
        if (r79.mOwner != r2) goto L70;
        r9 = r53;
    L70:
        if (r9 != null) goto L73;
        r9 = r2;
        r6 = true;
    L73:
        r5 = r2;
        r2 = r9;
        goto L3
    L74:
        ConstraintWidget r1 = this.mFirstVisibleWidget;
        if (r1 == null) goto L77;
        this.mTotalSize -= r1.mListAnchors[r0].getMargin();
    L77:
        ConstraintWidget r12 = this.mLastVisibleWidget;
        if (r12 == null) goto L80;
        this.mTotalSize -= r12.mListAnchors[r0 + 1].getMargin();
    L80:
        this.mLast = r2;
        if (this.mOrientation == 0) goto L83;
    L85:
        this.mHead = this.mFirst;
    L87:
        if (this.mHasDefinedWeights == true) goto L89;
    L91:
        r3 = false;
    L92:
        this.mHasComplexMatchWeights = r3;
        return;
    L89:
        if (this.mHasUndefinedWeights == false) goto L91;
    L83:
        if (this.mIsRtl == false) goto L85;
        this.mHead = r2;
        goto L87
    }

    private static boolean isMatchConstraintEqualityCandidate(ConstraintWidget r2, int r3) {
        if (r2.getVisibility() != 8) goto L5;
        return false;
    L5:
        if (r2.mListDimensionBehaviors[r3] != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) goto L15;
        int r22 = r2.mResolvedMatchConstraintDefault[r3];
        if (r22 != 0) goto L9;
        return true;
    L9:
        if (r22 != 3) goto L16;
        return true;
    L16:
        return false;
    L15:
        return false;
    }

    public void define() {
        if (this.mDefined == true) goto L5;
        defineChainProperties();
    L5:
        this.mDefined = true;
    }
}
