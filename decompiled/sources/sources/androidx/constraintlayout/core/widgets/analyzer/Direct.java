package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.Barrier;
import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import androidx.constraintlayout.core.widgets.Guideline;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes3.dex */
public abstract class Direct {
    private static int sHcount;
    private static BasicMeasure.Measure sMeasure;
    private static int sVcount;

    static {
        sMeasure = new BasicMeasure.Measure();
        sHcount = 0;
        sVcount = 0;
    }

    private static boolean canMeasure(int r7, ConstraintWidget r8) {
        ConstraintWidget.DimensionBehaviour r72 = r8.getHorizontalDimensionBehaviour();
        ConstraintWidget.DimensionBehaviour r0 = r8.getVerticalDimensionBehaviour();
        if (r8.getParent() == null) goto L5;
        ConstraintWidgetContainer r1 = (ConstraintWidgetContainer) r8.getParent();
    L6:
        if (r1 == null) goto L8;
        r1.getHorizontalDimensionBehaviour();
        ConstraintWidget.DimensionBehaviour r3 = ConstraintWidget.DimensionBehaviour.FIXED;
    L8:
        if (r1 == null) goto L10;
        r1.getVerticalDimensionBehaviour();
        ConstraintWidget.DimensionBehaviour r2 = ConstraintWidget.DimensionBehaviour.FIXED;
    L10:
        ConstraintWidget.DimensionBehaviour r12 = ConstraintWidget.DimensionBehaviour.FIXED;
        if (r72 != r12) goto L13;
    L31:
        boolean r73 = true;
    L32:
        if (r0 != r12) goto L34;
    L52:
        boolean r02 = true;
    L54:
        if (r8.mDimensionRatio <= 0.0f) goto L58;
        if (r73 == true) goto L57;
        if (r02 == false) goto L58;
    L57:
        return true;
    L58:
        if (r73 == false) goto L61;
        if (r02 == false) goto L61;
        return true;
    L61:
        return false;
    L34:
        if (r8.isResolvedVertically() == true) goto L52;
        if (r0 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) goto L52;
        ConstraintWidget.DimensionBehaviour r13 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        if (r0 == r13) goto L40;
    L45:
        if (r0 == r13) goto L47;
    L51:
        r02 = false;
        goto L54
    L47:
        if (r8.mMatchConstraintDefaultHeight != 1) goto L51;
        if (r8.hasResolvedTargets(1, r8.getHeight()) == false) goto L51;
    L40:
        if (r8.mMatchConstraintDefaultHeight != 0) goto L45;
        if (r8.mDimensionRatio != 0.0f) goto L45;
        if (r8.hasDanglingDimension(1) == true) goto L52;
    L13:
        if (r8.isResolvedHorizontally() == true) goto L31;
        if (r72 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) goto L31;
        ConstraintWidget.DimensionBehaviour r5 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        if (r72 == r5) goto L19;
    L24:
        if (r72 == r5) goto L26;
    L30:
        r73 = false;
        goto L32
    L26:
        if (r8.mMatchConstraintDefaultWidth != 1) goto L30;
        if (r8.hasResolvedTargets(0, r8.getWidth()) == false) goto L30;
    L19:
        if (r8.mMatchConstraintDefaultWidth != 0) goto L24;
        if (r8.mDimensionRatio != 0.0f) goto L24;
        if (r8.hasDanglingDimension(0) == true) goto L31;
    L5:
        r1 = null;
        goto L6
    }

    private static void horizontalSolvingPass(int r18, ConstraintWidget r19, BasicMeasure.Measurer r20, boolean r21) {
        if (r19.isHorizontalSolvingPassDone() == false) goto L5;
        return;
    L5:
        boolean r4 = true;
        sHcount++;
        if ((r19 instanceof ConstraintWidgetContainer) == false) goto L8;
    L12:
        ConstraintAnchor r3 = r19.getAnchor(ConstraintAnchor.Type.LEFT);
        ConstraintAnchor r5 = r19.getAnchor(ConstraintAnchor.Type.RIGHT);
        int r6 = r3.getFinalValue();
        int r7 = r5.getFinalValue();
        if (r3.getDependents() != null) goto L15;
    L79:
        boolean r16 = r4;
        if ((r19 instanceof Guideline) == false) goto L83;
        return;
    L83:
        if (r5.getDependents() != null) goto L85;
    L148:
        r19.markHorizontalSolvingPassDone();
        return;
    L85:
        if (r5.hasFinalValue() == false) goto L148;
        Iterator<ConstraintAnchor> r32 = r5.getDependents().iterator();
    L88:
        if (r32.hasNext() == false) goto L148;
        ConstraintAnchor r42 = r32.next();
        ConstraintWidget r52 = r42.mOwner;
        int r62 = r18 + 1;
        boolean r8 = canMeasure(r62, r52);
        if (r52.isMeasureRequested() == false) goto L94;
        if (r8 == false) goto L94;
        ConstraintWidgetContainer.measure(r62, r52, r20, new BasicMeasure.Measure(), BasicMeasure.Measure.SELF_DIMENSIONS);
    L94:
        if (r42 != r52.mLeft) goto L100;
        ConstraintAnchor r9 = r52.mRight.mTarget;
        if (r9 == null) goto L100;
        if (r9.hasFinalValue() == false) goto L100;
    L105:
        boolean r92 = r16;
    L107:
        ConstraintWidget.DimensionBehaviour r12 = r52.getHorizontalDimensionBehaviour();
        ConstraintWidget.DimensionBehaviour r13 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        if (r12 != r13) goto L132;
        if (r8 == true) goto L132;
        if (r52.getHorizontalDimensionBehaviour() != r13) goto L88;
        if (r52.mMatchConstraintMaxWidth < 0) goto L88;
        if (r52.mMatchConstraintMinWidth < 0) goto L88;
        if (r52.getVisibility() == 8) goto L124;
        if (r52.mMatchConstraintDefaultWidth != 0) goto L88;
        if (r52.getDimensionRatio() != 0.0f) goto L88;
    L124:
        if (r52.isInHorizontalChain() == true) goto L88;
        if (r52.isInVirtualLayout() == true) goto L88;
        if (r92 == false) goto L88;
        if (r52.isInHorizontalChain() == true) goto L88;
        solveHorizontalMatchConstraint(r62, r19, r20, r52, r21);
    L132:
        if (r52.isMeasureRequested() == true) goto L88;
        ConstraintAnchor r82 = r52.mLeft;
        if (r42 != r82) goto L139;
        if (r52.mRight.mTarget != null) goto L139;
        int r43 = r82.getMargin() + r7;
        r52.setFinalHorizontal(r43, r52.getWidth() + r43);
        horizontalSolvingPass(r62, r52, r20, r21);
    L139:
        ConstraintAnchor r122 = r52.mRight;
        if (r42 != r122) goto L144;
        if (r82.mTarget != null) goto L144;
        int r44 = r7 - r122.getMargin();
        r52.setFinalHorizontal(r44 - r52.getWidth(), r44);
        horizontalSolvingPass(r62, r52, r20, r21);
    L144:
        if (r92 == false) goto L88;
        if (r52.isInHorizontalChain() == true) goto L88;
        solveHorizontalCenterConstraints(r62, r20, r52, r21);
    L100:
        if (r42 != r52.mRight) goto L106;
        ConstraintAnchor r93 = r52.mLeft.mTarget;
        if (r93 == null) goto L106;
        if (r93.hasFinalValue() == true) goto L105;
    L106:
        r92 = false;
        goto L107
    L15:
        if (r3.hasFinalValue() == false) goto L79;
        Iterator<ConstraintAnchor> r33 = r3.getDependents().iterator();
    L18:
        if (r33.hasNext() == false) goto L79;
        ConstraintAnchor r83 = r33.next();
        ConstraintWidget r123 = r83.mOwner;
        int r132 = r18 + 1;
        boolean r14 = canMeasure(r132, r123);
        if (r123.isMeasureRequested() == false) goto L23;
        if (r14 == false) goto L23;
        boolean r162 = r4;
        ConstraintWidgetContainer.measure(r132, r123, r20, new BasicMeasure.Measure(), BasicMeasure.Measure.SELF_DIMENSIONS);
    L25:
        if (r83 != r123.mLeft) goto L31;
        ConstraintAnchor r45 = r123.mRight.mTarget;
        if (r45 == null) goto L31;
        if (r45.hasFinalValue() == false) goto L31;
    L36:
        boolean r46 = r162;
    L38:
        ConstraintWidget.DimensionBehaviour r15 = r123.getHorizontalDimensionBehaviour();
        ConstraintWidget.DimensionBehaviour r94 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        if (r15 != r94) goto L63;
        if (r14 == true) goto L63;
        if (r123.getHorizontalDimensionBehaviour() != r94) goto L64;
        if (r123.mMatchConstraintMaxWidth < 0) goto L64;
        if (r123.mMatchConstraintMinWidth < 0) goto L64;
        if (r123.getVisibility() == 8) goto L55;
        if (r123.mMatchConstraintDefaultWidth != 0) goto L64;
        if (r123.getDimensionRatio() != 0.0f) goto L64;
    L55:
        if (r123.isInHorizontalChain() == true) goto L64;
        if (r123.isInVirtualLayout() == true) goto L64;
        if (r46 == false) goto L64;
        if (r123.isInHorizontalChain() == true) goto L64;
        solveHorizontalMatchConstraint(r132, r19, r20, r123, r21);
    L64:
        r4 = r162;
    L63:
        if (r123.isMeasureRequested() == true) goto L64;
        ConstraintAnchor r95 = r123.mLeft;
        if (r83 == r95) goto L68;
    L70:
        ConstraintAnchor r142 = r123.mRight;
        if (r83 == r142) goto L73;
    L75:
        if (r46 == false) goto L64;
        if (r123.isInHorizontalChain() == true) goto L64;
        solveHorizontalCenterConstraints(r132, r20, r123, r21);
        goto L64
    L73:
        if (r95.mTarget != null) goto L75;
        int r47 = r6 - r142.getMargin();
        r123.setFinalHorizontal(r47 - r123.getWidth(), r47);
        horizontalSolvingPass(r132, r123, r20, r21);
        goto L64
    L68:
        if (r123.mRight.mTarget != null) goto L70;
        int r48 = r95.getMargin() + r6;
        r123.setFinalHorizontal(r48, r123.getWidth() + r48);
        horizontalSolvingPass(r132, r123, r20, r21);
    L31:
        if (r83 != r123.mRight) goto L37;
        ConstraintAnchor r49 = r123.mLeft.mTarget;
        if (r49 == null) goto L37;
        if (r49.hasFinalValue() == true) goto L36;
    L37:
        r46 = false;
    L23:
        r162 = r4;
        goto L25
    L8:
        if (r19.isMeasureRequested() == false) goto L12;
        int r34 = r18 + 1;
        if (canMeasure(r34, r19) == false) goto L12;
        ConstraintWidgetContainer.measure(r34, r19, r20, new BasicMeasure.Measure(), BasicMeasure.Measure.SELF_DIMENSIONS);
        goto L12
    }

    private static void solveBarrier(int r1, Barrier r2, BasicMeasure.Measurer r3, int r4, boolean r5) {
        if (r2.allSolved() == false) goto L9;
        if (r4 != 0) goto L7;
        horizontalSolvingPass(r1 + 1, r2, r3, r5);
        return;
    L7:
        verticalSolvingPass(r1 + 1, r2, r3);
        return;
    }

    private static void solveHorizontalCenterConstraints(int r6, BasicMeasure.Measurer r7, ConstraintWidget r8, boolean r9) {
        float r0 = r8.getHorizontalBiasPercent();
        int r1 = r8.mLeft.mTarget.getFinalValue();
        int r2 = r8.mRight.mTarget.getFinalValue();
        int r3 = r8.mLeft.getMargin() + r1;
        int r4 = r2 - r8.mRight.getMargin();
        if (r1 != r2) goto L5;
        r0 = 0.5f;
    L6:
        int r32 = r8.getWidth();
        int r42 = (r2 - r1) - r32;
        if (r1 <= r2) goto L9;
        r42 = (r1 - r2) - r32;
    L9:
        if (r42 <= 0) goto L12;
        float r02 = (r0 * r42) + 0.5f;
    L13:
        int r03 = ((int) r02) + r1;
        int r43 = r03 + r32;
        if (r1 <= r2) goto L16;
        r43 = r03 - r32;
    L16:
        r8.setFinalHorizontal(r03, r43);
        horizontalSolvingPass(r6 + 1, r8, r7, r9);
        return;
    L12:
        r02 = r0 * r42;
        goto L13
    L5:
        r1 = r3;
        r2 = r4;
        goto L6
    }

    private static void solveHorizontalMatchConstraint(int r7, ConstraintWidget r8, BasicMeasure.Measurer r9, ConstraintWidget r10, boolean r11) {
        float r0 = r10.getHorizontalBiasPercent();
        int r1 = r10.mLeft.mTarget.getFinalValue() + r10.mLeft.getMargin();
        int r2 = r10.mRight.mTarget.getFinalValue() - r10.mRight.getMargin();
        if (r2 < r1) goto L20;
        int r3 = r10.getWidth();
        if (r10.getVisibility() == 8) goto L18;
        int r4 = r10.mMatchConstraintDefaultWidth;
        if (r4 == 2) goto L9;
        if (r4 != 0) goto L15;
        r3 = r2 - r1;
    L15:
        r3 = Math.max(r10.mMatchConstraintMinWidth, r3);
        int r82 = r10.mMatchConstraintMaxWidth;
        if (r82 <= 0) goto L18;
        r3 = Math.min(r82, r3);
        goto L18
    L9:
        if ((r8 instanceof ConstraintWidgetContainer) == false) goto L11;
        int r83 = r8.getWidth();
    L12:
        r3 = (int) ((r10.getHorizontalBiasPercent() * 0.5f) * r83);
        goto L15
    L11:
        r83 = r8.getParent().getWidth();
    L18:
        int r12 = r1 + ((int) ((r0 * ((r2 - r1) - r3)) + 0.5f));
        r10.setFinalHorizontal(r12, r3 + r12);
        horizontalSolvingPass(r7 + 1, r10, r9, r11);
        return;
    }

    private static void solveVerticalCenterConstraints(int r6, BasicMeasure.Measurer r7, ConstraintWidget r8) {
        float r0 = r8.getVerticalBiasPercent();
        int r1 = r8.mTop.mTarget.getFinalValue();
        int r2 = r8.mBottom.mTarget.getFinalValue();
        int r3 = r8.mTop.getMargin() + r1;
        int r4 = r2 - r8.mBottom.getMargin();
        if (r1 != r2) goto L5;
        r0 = 0.5f;
    L6:
        int r32 = r8.getHeight();
        int r42 = (r2 - r1) - r32;
        if (r1 <= r2) goto L9;
        r42 = (r1 - r2) - r32;
    L9:
        if (r42 <= 0) goto L12;
        float r02 = (r0 * r42) + 0.5f;
    L11:
        int r03 = (int) r02;
        int r43 = r1 + r03;
        int r5 = r43 + r32;
        if (r1 <= r2) goto L16;
        r43 = r1 - r03;
        r5 = r43 - r32;
    L16:
        r8.setFinalVertical(r43, r5);
        verticalSolvingPass(r6 + 1, r8, r7);
        return;
    L12:
        r02 = r0 * r42;
        goto L11
    L5:
        r1 = r3;
        r2 = r4;
        goto L6
    }

    private static void solveVerticalMatchConstraint(int r7, ConstraintWidget r8, BasicMeasure.Measurer r9, ConstraintWidget r10) {
        float r0 = r10.getVerticalBiasPercent();
        int r1 = r10.mTop.mTarget.getFinalValue() + r10.mTop.getMargin();
        int r2 = r10.mBottom.mTarget.getFinalValue() - r10.mBottom.getMargin();
        if (r2 < r1) goto L20;
        int r3 = r10.getHeight();
        if (r10.getVisibility() == 8) goto L18;
        int r4 = r10.mMatchConstraintDefaultHeight;
        if (r4 == 2) goto L9;
        if (r4 != 0) goto L15;
        r3 = r2 - r1;
    L15:
        r3 = Math.max(r10.mMatchConstraintMinHeight, r3);
        int r82 = r10.mMatchConstraintMaxHeight;
        if (r82 <= 0) goto L18;
        r3 = Math.min(r82, r3);
        goto L18
    L9:
        if ((r8 instanceof ConstraintWidgetContainer) == false) goto L11;
        int r83 = r8.getHeight();
    L12:
        r3 = (int) ((r0 * 0.5f) * r83);
        goto L15
    L11:
        r83 = r8.getParent().getHeight();
    L18:
        int r12 = r1 + ((int) ((r0 * ((r2 - r1) - r3)) + 0.5f));
        r10.setFinalVertical(r12, r3 + r12);
        verticalSolvingPass(r7 + 1, r10, r9);
        return;
    }

    public static void solvingPass(ConstraintWidgetContainer r13, BasicMeasure.Measurer r14) {
        ConstraintWidget.DimensionBehaviour r0 = r13.getHorizontalDimensionBehaviour();
        ConstraintWidget.DimensionBehaviour r1 = r13.getVerticalDimensionBehaviour();
        sHcount = 0;
        sVcount = 0;
        r13.resetFinalResolution();
        ArrayList<ConstraintWidget> r3 = r13.getChildren();
        int r4 = r3.size();
        int r5 = 0;
    L3:
        if (r5 >= r4) goto L5;
        r3.get(r5).resetFinalResolution();
        r5 = r5 + 1;
        goto L3
    L5:
        boolean r52 = r13.isRtl();
        if (r0 != ConstraintWidget.DimensionBehaviour.FIXED) goto L8;
        r13.setFinalHorizontal(0, r13.getWidth());
    L9:
        int r02 = 0;
        boolean r6 = false;
        boolean r7 = false;
    L11:
        if (r02 >= r4) goto L34;
        ConstraintWidget r11 = r3.get(r02);
        if ((r11 instanceof Guideline) == false) goto L29;
        Guideline r112 = (Guideline) r11;
        if (r112.getOrientation() != 1) goto L33;
        if (r112.getRelativeBegin() == (-1)) goto L20;
        r112.setFinalValue(r112.getRelativeBegin());
    L27:
        r6 = true;
        goto L33
    L20:
        if (r112.getRelativeEnd() == (-1)) goto L25;
        if (r13.isResolvedHorizontally() == false) goto L25;
        r112.setFinalValue(r13.getWidth() - r112.getRelativeEnd());
    L25:
        if (r13.isResolvedHorizontally() == false) goto L27;
        r112.setFinalValue((int) ((r112.getRelativePercent() * r13.getWidth()) + 0.5f));
    L33:
        r02 = r02 + 1;
        goto L11
    L29:
        if ((r11 instanceof Barrier) == false) goto L33;
        if (((Barrier) r11).getOrientation() != 0) goto L33;
        r7 = true;
        goto L33
    L34:
        if (r6 == false) goto L43;
        int r03 = 0;
    L36:
        if (r03 >= r4) goto L43;
        ConstraintWidget r62 = r3.get(r03);
        if ((r62 instanceof Guideline) == false) goto L42;
        Guideline r63 = (Guideline) r62;
        if (r63.getOrientation() != 1) goto L42;
        horizontalSolvingPass(0, r63, r14, r52);
    L42:
        r03 = r03 + 1;
    L43:
        horizontalSolvingPass(0, r13, r14, r52);
        if (r7 == false) goto L54;
        int r04 = 0;
    L46:
        if (r04 >= r4) goto L54;
        ConstraintWidget r64 = r3.get(r04);
        if ((r64 instanceof Barrier) == false) goto L52;
        Barrier r65 = (Barrier) r64;
        if (r65.getOrientation() != 0) goto L52;
        solveBarrier(0, r65, r14, 0, r52);
    L52:
        r04 = r04 + 1;
    L54:
        if (r1 != ConstraintWidget.DimensionBehaviour.FIXED) goto L56;
        r13.setFinalVertical(0, r13.getHeight());
    L57:
        int r05 = 0;
        boolean r12 = false;
        boolean r66 = false;
    L58:
        if (r05 >= r4) goto L81;
        ConstraintWidget r72 = r3.get(r05);
        if ((r72 instanceof Guideline) == false) goto L76;
        Guideline r73 = (Guideline) r72;
        if (r73.getOrientation() != 0) goto L80;
        if (r73.getRelativeBegin() == (-1)) goto L67;
        r73.setFinalValue(r73.getRelativeBegin());
    L74:
        r12 = true;
        goto L80
    L67:
        if (r73.getRelativeEnd() == (-1)) goto L72;
        if (r13.isResolvedVertically() == false) goto L72;
        r73.setFinalValue(r13.getHeight() - r73.getRelativeEnd());
    L72:
        if (r13.isResolvedVertically() == false) goto L74;
        r73.setFinalValue((int) ((r73.getRelativePercent() * r13.getHeight()) + 0.5f));
    L80:
        r05 = r05 + 1;
        goto L58
    L76:
        if ((r72 instanceof Barrier) == false) goto L80;
        if (((Barrier) r72).getOrientation() != 1) goto L80;
        r66 = true;
        goto L80
    L81:
        if (r12 == false) goto L90;
        int r06 = 0;
    L83:
        if (r06 >= r4) goto L90;
        ConstraintWidget r15 = r3.get(r06);
        if ((r15 instanceof Guideline) == false) goto L89;
        Guideline r16 = (Guideline) r15;
        if (r16.getOrientation() != 0) goto L89;
        verticalSolvingPass(1, r16, r14);
    L89:
        r06 = r06 + 1;
    L90:
        verticalSolvingPass(0, r13, r14);
        if (r66 == false) goto L100;
        int r132 = 0;
    L93:
        if (r132 >= r4) goto L100;
        ConstraintWidget r07 = r3.get(r132);
        if ((r07 instanceof Barrier) == false) goto L99;
        Barrier r08 = (Barrier) r07;
        if (r08.getOrientation() != 1) goto L99;
        solveBarrier(0, r08, r14, 1, r52);
    L99:
        r132 = r132 + 1;
    L100:
        int r133 = 0;
    L101:
        if (r133 >= r4) goto L114;
        ConstraintWidget r09 = r3.get(r133);
        if (r09.isMeasureRequested() == false) goto L113;
        if (canMeasure(0, r09) == false) goto L113;
        ConstraintWidgetContainer.measure(0, r09, r14, sMeasure, BasicMeasure.Measure.SELF_DIMENSIONS);
        if ((r09 instanceof Guideline) == true) goto L109;
        horizontalSolvingPass(0, r09, r14, r52);
        verticalSolvingPass(0, r09, r14);
        goto L113
    L109:
        if (((Guideline) r09).getOrientation() != 0) goto L111;
        verticalSolvingPass(0, r09, r14);
        goto L113
    L111:
        horizontalSolvingPass(0, r09, r14, r52);
    L113:
        r133 = r133 + 1;
        goto L101
    L114:
        return;
    L56:
        r13.setFinalTop(0);
        goto L57
    L8:
        r13.setFinalLeft(0);
        goto L9
    }

    private static void verticalSolvingPass(int r17, ConstraintWidget r18, BasicMeasure.Measurer r19) {
        if (r18.isVerticalSolvingPassDone() == false) goto L5;
        return;
    L5:
        boolean r3 = true;
        sVcount++;
        if ((r18 instanceof ConstraintWidgetContainer) == false) goto L8;
    L12:
        ConstraintAnchor r2 = r18.getAnchor(ConstraintAnchor.Type.TOP);
        ConstraintAnchor r4 = r18.getAnchor(ConstraintAnchor.Type.BOTTOM);
        int r5 = r2.getFinalValue();
        int r6 = r4.getFinalValue();
        if (r2.getDependents() != null) goto L15;
    L78:
        boolean r16 = r3;
        if ((r18 instanceof Guideline) == false) goto L82;
        return;
    L82:
        if (r4.getDependents() != null) goto L84;
    L147:
        ConstraintAnchor r22 = r18.getAnchor(ConstraintAnchor.Type.BASELINE);
        if (r22.getDependents() != null) goto L150;
    L167:
        r18.markVerticalSolvingPassDone();
        return;
    L150:
        if (r22.hasFinalValue() == false) goto L167;
        int r32 = r22.getFinalValue();
        Iterator<ConstraintAnchor> r23 = r22.getDependents().iterator();
    L153:
        if (r23.hasNext() == false) goto L167;
        ConstraintAnchor r42 = r23.next();
        ConstraintWidget r52 = r42.mOwner;
        int r62 = r17 + 1;
        boolean r7 = canMeasure(r62, r52);
        if (r52.isMeasureRequested() == false) goto L159;
        if (r7 == false) goto L159;
        ConstraintWidgetContainer.measure(r62, r52, r19, new BasicMeasure.Measure(), BasicMeasure.Measure.SELF_DIMENSIONS);
    L159:
        if (r52.getVerticalDimensionBehaviour() != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) goto L162;
        if (r7 == false) goto L153;
    L162:
        if (r52.isMeasureRequested() == true) goto L153;
        if (r42 != r52.mBaseline) goto L153;
        r52.setFinalBaseline(r42.getMargin() + r32);
        verticalSolvingPass(r62, r52, r19);
        goto L153
    L84:
        if (r4.hasFinalValue() == false) goto L147;
        Iterator<ConstraintAnchor> r24 = r4.getDependents().iterator();
    L87:
        if (r24.hasNext() == false) goto L147;
        ConstraintAnchor r33 = r24.next();
        ConstraintWidget r43 = r33.mOwner;
        int r53 = r17 + 1;
        boolean r72 = canMeasure(r53, r43);
        if (r43.isMeasureRequested() == false) goto L93;
        if (r72 == false) goto L93;
        ConstraintWidgetContainer.measure(r53, r43, r19, new BasicMeasure.Measure(), BasicMeasure.Measure.SELF_DIMENSIONS);
    L93:
        if (r33 != r43.mTop) goto L99;
        ConstraintAnchor r11 = r43.mBottom.mTarget;
        if (r11 == null) goto L99;
        if (r11.hasFinalValue() == false) goto L99;
    L104:
        boolean r112 = r16;
    L106:
        ConstraintWidget.DimensionBehaviour r12 = r43.getVerticalDimensionBehaviour();
        ConstraintWidget.DimensionBehaviour r13 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        if (r12 != r13) goto L131;
        if (r72 == true) goto L131;
        if (r43.getVerticalDimensionBehaviour() != r13) goto L87;
        if (r43.mMatchConstraintMaxHeight < 0) goto L87;
        if (r43.mMatchConstraintMinHeight < 0) goto L87;
        if (r43.getVisibility() == 8) goto L123;
        if (r43.mMatchConstraintDefaultHeight != 0) goto L87;
        if (r43.getDimensionRatio() != 0.0f) goto L87;
    L123:
        if (r43.isInVerticalChain() == true) goto L87;
        if (r43.isInVirtualLayout() == true) goto L87;
        if (r112 == false) goto L87;
        if (r43.isInVerticalChain() == true) goto L87;
        solveVerticalMatchConstraint(r53, r18, r19, r43);
    L131:
        if (r43.isMeasureRequested() == true) goto L87;
        ConstraintAnchor r73 = r43.mTop;
        if (r33 != r73) goto L138;
        if (r43.mBottom.mTarget != null) goto L138;
        int r34 = r73.getMargin() + r6;
        r43.setFinalVertical(r34, r43.getHeight() + r34);
        verticalSolvingPass(r53, r43, r19);
    L138:
        ConstraintAnchor r122 = r43.mBottom;
        if (r33 != r122) goto L143;
        if (r73.mTarget != null) goto L143;
        int r35 = r6 - r122.getMargin();
        r43.setFinalVertical(r35 - r43.getHeight(), r35);
        verticalSolvingPass(r53, r43, r19);
    L143:
        if (r112 == false) goto L87;
        if (r43.isInVerticalChain() == true) goto L87;
        solveVerticalCenterConstraints(r53, r19, r43);
    L99:
        if (r33 != r43.mBottom) goto L105;
        ConstraintAnchor r113 = r43.mTop.mTarget;
        if (r113 == null) goto L105;
        if (r113.hasFinalValue() == true) goto L104;
    L105:
        r112 = false;
        goto L106
    L15:
        if (r2.hasFinalValue() == false) goto L78;
        Iterator<ConstraintAnchor> r25 = r2.getDependents().iterator();
    L18:
        if (r25.hasNext() == false) goto L78;
        ConstraintAnchor r74 = r25.next();
        ConstraintWidget r114 = r74.mOwner;
        int r123 = r17 + 1;
        boolean r132 = canMeasure(r123, r114);
        if (r114.isMeasureRequested() == false) goto L24;
        if (r132 == false) goto L24;
        ConstraintWidgetContainer.measure(r123, r114, r19, new BasicMeasure.Measure(), BasicMeasure.Measure.SELF_DIMENSIONS);
    L24:
        if (r74 != r114.mTop) goto L30;
        ConstraintAnchor r14 = r114.mBottom.mTarget;
        if (r14 == null) goto L30;
        if (r14.hasFinalValue() == false) goto L30;
    L35:
        boolean r142 = r3;
    L37:
        ConstraintWidget.DimensionBehaviour r15 = r114.getVerticalDimensionBehaviour();
        boolean r162 = r3;
        ConstraintWidget.DimensionBehaviour r36 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        if (r15 != r36) goto L62;
        if (r132 == true) goto L62;
        if (r114.getVerticalDimensionBehaviour() != r36) goto L63;
        if (r114.mMatchConstraintMaxHeight < 0) goto L63;
        if (r114.mMatchConstraintMinHeight < 0) goto L63;
        if (r114.getVisibility() == 8) goto L54;
        if (r114.mMatchConstraintDefaultHeight != 0) goto L63;
        if (r114.getDimensionRatio() != 0.0f) goto L63;
    L54:
        if (r114.isInVerticalChain() == true) goto L63;
        if (r114.isInVirtualLayout() == true) goto L63;
        if (r142 == false) goto L63;
        if (r114.isInVerticalChain() == true) goto L63;
        solveVerticalMatchConstraint(r123, r18, r19, r114);
    L63:
        r3 = r162;
    L62:
        if (r114.isMeasureRequested() == true) goto L63;
        ConstraintAnchor r37 = r114.mTop;
        if (r74 == r37) goto L67;
    L69:
        ConstraintAnchor r133 = r114.mBottom;
        if (r74 == r133) goto L72;
    L74:
        if (r142 == false) goto L63;
        if (r114.isInVerticalChain() == true) goto L63;
        solveVerticalCenterConstraints(r123, r19, r114);
        goto L63
    L72:
        if (r37.mTarget != null) goto L74;
        int r38 = r5 - r133.getMargin();
        r114.setFinalVertical(r38 - r114.getHeight(), r38);
        verticalSolvingPass(r123, r114, r19);
        goto L63
    L67:
        if (r114.mBottom.mTarget != null) goto L69;
        int r39 = r37.getMargin() + r5;
        r114.setFinalVertical(r39, r114.getHeight() + r39);
        verticalSolvingPass(r123, r114, r19);
    L30:
        if (r74 != r114.mBottom) goto L36;
        ConstraintAnchor r143 = r114.mTop.mTarget;
        if (r143 == null) goto L36;
        if (r143.hasFinalValue() == true) goto L35;
    L36:
        r142 = false;
        goto L37
    L8:
        if (r18.isMeasureRequested() == false) goto L12;
        int r26 = r17 + 1;
        if (canMeasure(r26, r18) == false) goto L12;
        ConstraintWidgetContainer.measure(r26, r18, r19, new BasicMeasure.Measure(), BasicMeasure.Measure.SELF_DIMENSIONS);
        goto L12
    }
}
