package androidx.constraintlayout.core.widgets;

import androidx.constraintlayout.core.LinearSystem;
import androidx.constraintlayout.core.SolverVariable;
import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;

/* loaded from: classes3.dex */
public class Barrier extends HelperWidget {
    private boolean mAllowsGoneWidget;
    private int mBarrierType;
    private int mMargin;
    boolean mResolved;

    public Barrier() {
        this.mBarrierType = 0;
        this.mAllowsGoneWidget = true;
        this.mMargin = 0;
        this.mResolved = false;
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public void addToSolver(LinearSystem r13, boolean r14) {
        ConstraintAnchor[] r142 = this.mListAnchors;
        r142[0] = this.mLeft;
        r142[2] = this.mTop;
        r142[1] = this.mRight;
        r142[3] = this.mBottom;
        int r143 = 0;
    L3:
        ConstraintAnchor[] r0 = this.mListAnchors;
        if (r143 >= r0.length) goto L6;
        ConstraintAnchor r02 = r0[r143];
        r02.mSolverVariable = r13.createObjectVariable(r02);
        r143 = r143 + 1;
        goto L3
    L6:
        int r144 = this.mBarrierType;
        if (r144 >= 0) goto L9;
        return;
    L9:
        if (r144 >= 4) goto L131;
        ConstraintAnchor r145 = r0[r144];
        if (this.mResolved == true) goto L14;
        allSolved();
    L14:
        if (this.mResolved == false) goto L25;
        this.mResolved = false;
        int r146 = this.mBarrierType;
        if (r146 == 0) goto L23;
        if (r146 == 1) goto L23;
        if (r146 == 2) goto L21;
        if (r146 == 3) goto L21;
        return;
    L21:
        r13.addEquality(this.mTop.mSolverVariable, this.mY);
        r13.addEquality(this.mBottom.mSolverVariable, this.mY);
        return;
    L23:
        r13.addEquality(this.mLeft.mSolverVariable, this.mX);
        r13.addEquality(this.mRight.mSolverVariable, this.mX);
        return;
    L25:
        int r03 = 0;
    L27:
        if (r03 >= this.mWidgetsCount) goto L54;
        ConstraintWidget r6 = this.mWidgets[r03];
        if (this.mAllowsGoneWidget == false) goto L31;
    L33:
        int r7 = this.mBarrierType;
        if (r7 == 0) goto L37;
        if (r7 == 1) goto L37;
    L43:
        int r72 = this.mBarrierType;
        if (r72 == 2) goto L47;
        if (r72 == 3) goto L47;
    L53:
        r03 = r03 + 1;
    L47:
        if (r6.getVerticalDimensionBehaviour() != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) goto L53;
        if (r6.mTop.mTarget == null) goto L53;
        if (r6.mBottom.mTarget == null) goto L53;
    L42:
        boolean r04 = true;
    L56:
        if (this.mLeft.hasCenteredDependents() == false) goto L58;
    L61:
        boolean r62 = true;
    L63:
        if (this.mTop.hasCenteredDependents() == false) goto L65;
    L68:
        boolean r73 = true;
    L69:
        if (r04 == true) goto L80;
        int r8 = this.mBarrierType;
        if (r8 != 0) goto L73;
        if (r62 == false) goto L73;
    L79:
        boolean r63 = true;
    L81:
        if (r63 == true) goto L83;
        int r64 = 4;
    L84:
        int r74 = 0;
    L86:
        if (r74 >= this.mWidgetsCount) goto L105;
        ConstraintWidget r82 = this.mWidgets[r74];
        if (this.mAllowsGoneWidget == false) goto L90;
    L92:
        SolverVariable r9 = r13.createObjectVariable(r82.mListAnchors[this.mBarrierType]);
        ConstraintAnchor[] r83 = r82.mListAnchors;
        int r10 = this.mBarrierType;
        ConstraintAnchor r84 = r83[r10];
        r84.mSolverVariable = r9;
        ConstraintAnchor r11 = r84.mTarget;
        if (r11 != null) goto L95;
    L97:
        int r85 = 0;
    L98:
        if (r10 == 0) goto L102;
        if (r10 == 2) goto L102;
        r13.addGreaterBarrier(r145.mSolverVariable, r9, this.mMargin + r85, r04);
    L103:
        r13.addEquality(r145.mSolverVariable, r9, this.mMargin + r85, r64);
    L104:
        r74 = r74 + 1;
    L102:
        r13.addLowerBarrier(r145.mSolverVariable, r9, this.mMargin - r85, r04);
        goto L103
    L95:
        if (r11.mOwner != this) goto L97;
        r85 = r84.mMargin;
        goto L98
    L90:
        if (r82.allowedInBarrier() == true) goto L92;
    L105:
        int r147 = this.mBarrierType;
        if (r147 != 0) goto L109;
        r13.addEquality(this.mRight.mSolverVariable, this.mLeft.mSolverVariable, 0, 8);
        r13.addEquality(this.mLeft.mSolverVariable, this.mParent.mRight.mSolverVariable, 0, 4);
        r13.addEquality(this.mLeft.mSolverVariable, this.mParent.mLeft.mSolverVariable, 0, 0);
        return;
    L109:
        if (r147 != 1) goto L112;
        r13.addEquality(this.mLeft.mSolverVariable, this.mRight.mSolverVariable, 0, 8);
        r13.addEquality(this.mLeft.mSolverVariable, this.mParent.mLeft.mSolverVariable, 0, 4);
        r13.addEquality(this.mLeft.mSolverVariable, this.mParent.mRight.mSolverVariable, 0, 0);
        return;
    L112:
        if (r147 != 2) goto L115;
        r13.addEquality(this.mBottom.mSolverVariable, this.mTop.mSolverVariable, 0, 8);
        r13.addEquality(this.mTop.mSolverVariable, this.mParent.mBottom.mSolverVariable, 0, 4);
        r13.addEquality(this.mTop.mSolverVariable, this.mParent.mTop.mSolverVariable, 0, 0);
        return;
    L115:
        if (r147 != 3) goto L133;
        r13.addEquality(this.mTop.mSolverVariable, this.mBottom.mSolverVariable, 0, 8);
        r13.addEquality(this.mTop.mSolverVariable, this.mParent.mTop.mSolverVariable, 0, 4);
        r13.addEquality(this.mTop.mSolverVariable, this.mParent.mBottom.mSolverVariable, 0, 0);
        return;
    L133:
        return;
    L83:
        r64 = 5;
    L73:
        if (r8 != 2) goto L75;
        if (r73 == true) goto L79;
    L75:
        if (r8 != 1) goto L77;
        if (r62 == true) goto L79;
    L77:
        if (r8 != 3) goto L80;
        if (r73 == true) goto L79;
    L80:
        r63 = false;
        goto L81
    L65:
        if (this.mBottom.hasCenteredDependents() == true) goto L68;
        r73 = false;
        goto L69
    L58:
        if (this.mRight.hasCenteredDependents() == true) goto L61;
        r62 = false;
    L37:
        if (r6.getHorizontalDimensionBehaviour() != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) goto L43;
        if (r6.mLeft.mTarget == null) goto L43;
        if (r6.mRight.mTarget == null) goto L43;
    L31:
        if (r6.allowedInBarrier() == true) goto L33;
    L54:
        r04 = false;
        goto L56
    }

    public boolean allSolved() {
        int r1 = 0;
        boolean r3 = true;
        int r2 = 0;
    L3:
        int r4 = this.mWidgetsCount;
        if (r2 >= r4) goto L23;
        ConstraintWidget r42 = this.mWidgets[r2];
        if (this.mAllowsGoneWidget == false) goto L8;
    L10:
        int r7 = this.mBarrierType;
        if (r7 == 0) goto L14;
        if (r7 == 1) goto L14;
    L16:
        int r72 = this.mBarrierType;
        if (r72 == 2) goto L20;
        if (r72 == 3) goto L20;
    L22:
        r2 = r2 + 1;
    L20:
        if (r42.isResolvedVertically() == true) goto L22;
    L15:
        r3 = false;
    L14:
        if (r42.isResolvedHorizontally() == true) goto L16;
    L8:
        if (r42.allowedInBarrier() == true) goto L10;
    L23:
        if (r3 == false) goto L62;
        if (r4 <= 0) goto L62;
        int r22 = 0;
        boolean r32 = false;
    L27:
        if (r1 >= this.mWidgetsCount) goto L54;
        ConstraintWidget r43 = this.mWidgets[r1];
        if (this.mAllowsGoneWidget == false) goto L31;
    L33:
        if (r32 == true) goto L44;
        int r33 = this.mBarrierType;
        if (r33 != 0) goto L37;
        r22 = r43.getAnchor(ConstraintAnchor.Type.LEFT).getFinalValue();
    L43:
        r32 = true;
        goto L44
    L37:
        if (r33 != 1) goto L39;
        r22 = r43.getAnchor(ConstraintAnchor.Type.RIGHT).getFinalValue();
        goto L43
    L39:
        if (r33 != 2) goto L41;
        r22 = r43.getAnchor(ConstraintAnchor.Type.TOP).getFinalValue();
        goto L43
    L41:
        if (r33 != 3) goto L43;
        r22 = r43.getAnchor(ConstraintAnchor.Type.BOTTOM).getFinalValue();
    L44:
        int r73 = this.mBarrierType;
        if (r73 != 0) goto L47;
        r22 = Math.min(r22, r43.getAnchor(ConstraintAnchor.Type.LEFT).getFinalValue());
    L53:
        r1 = r1 + 1;
        goto L27
    L47:
        if (r73 != 1) goto L49;
        r22 = Math.max(r22, r43.getAnchor(ConstraintAnchor.Type.RIGHT).getFinalValue());
        goto L53
    L49:
        if (r73 != 2) goto L51;
        r22 = Math.min(r22, r43.getAnchor(ConstraintAnchor.Type.TOP).getFinalValue());
        goto L53
    L51:
        if (r73 != 3) goto L53;
        r22 = Math.max(r22, r43.getAnchor(ConstraintAnchor.Type.BOTTOM).getFinalValue());
        goto L53
    L31:
        if (r43.allowedInBarrier() == true) goto L33;
    L54:
        int r23 = r22 + this.mMargin;
        int r12 = this.mBarrierType;
        if (r12 == 0) goto L59;
        if (r12 == 1) goto L59;
        setFinalVertical(r23, r23);
    L60:
        this.mResolved = true;
        return true;
    L59:
        setFinalHorizontal(r23, r23);
    L62:
        return false;
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public boolean allowedInBarrier() {
        return true;
    }

    public boolean getAllowsGoneWidget() {
        return this.mAllowsGoneWidget;
    }

    public int getBarrierType() {
        return this.mBarrierType;
    }

    public int getMargin() {
        return this.mMargin;
    }

    public int getOrientation() {
        int r2 = this.mBarrierType;
        if (r2 != 0) goto L5;
        return 0;
    L5:
        if (r2 != 1) goto L7;
        return 0;
    L7:
        if (r2 != 2) goto L9;
    L12:
        return 1;
    L9:
        if (r2 == 3) goto L12;
        return -1;
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public boolean isResolvedHorizontally() {
        return this.mResolved;
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public boolean isResolvedVertically() {
        return this.mResolved;
    }

    public void markWidgets() {
        int r1 = 0;
    L4:
        if (r1 >= this.mWidgetsCount) goto L21;
        ConstraintWidget r2 = this.mWidgets[r1];
        if (this.mAllowsGoneWidget == false) goto L8;
    L10:
        int r3 = this.mBarrierType;
        if (r3 == 0) goto L19;
        if (r3 == 1) goto L19;
        if (r3 != 2) goto L17;
    L18:
        r2.setInBarrier(1, true);
    L20:
        r1 = r1 + 1;
        goto L4
    L17:
        if (r3 != 3) goto L20;
    L19:
        r2.setInBarrier(0, true);
        goto L20
    L8:
        if (r2.allowedInBarrier() == true) goto L10;
    }

    public void setAllowsGoneWidget(boolean r1) {
        this.mAllowsGoneWidget = r1;
    }

    public void setBarrierType(int r1) {
        this.mBarrierType = r1;
    }

    public void setMargin(int r1) {
        this.mMargin = r1;
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public String toString() {
        String r0 = "[Barrier] " + getDebugName() + " {";
        int r1 = 0;
    L4:
        if (r1 >= this.mWidgetsCount) goto L10;
        ConstraintWidget r2 = this.mWidgets[r1];
        if (r1 <= 0) goto L8;
        r0 = r0.concat(", ");
    L8:
        r0 = r0 + r2.getDebugName();
        r1 = r1 + 1;
        goto L4
    L10:
        return r0.concat("}");
    }
}
