package androidx.constraintlayout.core.widgets;

import androidx.constraintlayout.core.LinearSystem;
import androidx.constraintlayout.core.widgets.ConstraintWidget;

/* loaded from: classes3.dex */
public abstract class Optimizer {
    static boolean[] sFlags;

    static {
        sFlags = new boolean[3];
    }

    public static void checkMatchParent(ConstraintWidgetContainer r6, LinearSystem r7, ConstraintWidget r8) {
        r8.mHorizontalResolution = -1;
        r8.mVerticalResolution = -1;
        ConstraintWidget.DimensionBehaviour r0 = r6.mListDimensionBehaviors[0];
        ConstraintWidget.DimensionBehaviour r2 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (r0 == r2) goto L8;
        if (r8.mListDimensionBehaviors[0] != ConstraintWidget.DimensionBehaviour.MATCH_PARENT) goto L8;
        int r02 = r8.mLeft.mMargin;
        int r1 = r6.getWidth() - r8.mRight.mMargin;
        ConstraintAnchor r4 = r8.mLeft;
        r4.mSolverVariable = r7.createObjectVariable(r4);
        ConstraintAnchor r42 = r8.mRight;
        r42.mSolverVariable = r7.createObjectVariable(r42);
        r7.addEquality(r8.mLeft.mSolverVariable, r02);
        r7.addEquality(r8.mRight.mSolverVariable, r1);
        r8.mHorizontalResolution = 2;
        r8.setHorizontalDimension(r02, r1);
    L8:
        if (r6.mListDimensionBehaviors[1] != r2) goto L10;
        return;
    L10:
        if (r8.mListDimensionBehaviors[1] != ConstraintWidget.DimensionBehaviour.MATCH_PARENT) goto L19;
        int r03 = r8.mTop.mMargin;
        int r62 = r6.getHeight() - r8.mBottom.mMargin;
        ConstraintAnchor r12 = r8.mTop;
        r12.mSolverVariable = r7.createObjectVariable(r12);
        ConstraintAnchor r13 = r8.mBottom;
        r13.mSolverVariable = r7.createObjectVariable(r13);
        r7.addEquality(r8.mTop.mSolverVariable, r03);
        r7.addEquality(r8.mBottom.mSolverVariable, r62);
        if (r8.mBaselineDistance <= 0) goto L14;
    L15:
        ConstraintAnchor r14 = r8.mBaseline;
        r14.mSolverVariable = r7.createObjectVariable(r14);
        r7.addEquality(r8.mBaseline.mSolverVariable, r8.mBaselineDistance + r03);
    L16:
        r8.mVerticalResolution = 2;
        r8.setVerticalDimension(r03, r62);
        return;
    L14:
        if (r8.getVisibility() != 8) goto L16;
    }

    public static final boolean enabled(int r0, int r1) {
        if ((r0 & r1) != r1) goto L6;
        return true;
    L6:
        return false;
    }
}
