package androidx.constraintlayout.core.widgets;

import androidx.constraintlayout.core.LinearSystem;
import androidx.constraintlayout.core.SolverVariable;
import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;

/* loaded from: classes3.dex */
public class Guideline extends ConstraintWidget {
    private ConstraintAnchor mAnchor;
    protected boolean mGuidelineUseRtl;
    private int mMinimumPosition;
    private int mOrientation;
    protected int mRelativeBegin;
    protected int mRelativeEnd;
    protected float mRelativePercent;
    private boolean mResolved;

    /* renamed from: androidx.constraintlayout.core.widgets.Guideline$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintAnchor$Type = null;

        static {
            int[] r0 = new int[ConstraintAnchor.Type.values().length];
            $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintAnchor$Type = r0;
            r0[ConstraintAnchor.Type.LEFT.ordinal()] = 1;     // Catch: NoSuchFieldError -> L13
        L24:
            $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.RIGHT.ordinal()] = 2;     // Catch: NoSuchFieldError -> L14
        L28:
            $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.TOP.ordinal()] = 3;     // Catch: NoSuchFieldError -> L15
        L38:
            $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.BOTTOM.ordinal()] = 4;     // Catch: NoSuchFieldError -> L16
        L26:
            $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.BASELINE.ordinal()] = 5;     // Catch: NoSuchFieldError -> L17
        L30:
            $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.CENTER.ordinal()] = 6;     // Catch: NoSuchFieldError -> L18
        L32:
            $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.CENTER_X.ordinal()] = 7;     // Catch: NoSuchFieldError -> L19
        L34:
            $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.CENTER_Y.ordinal()] = 8;     // Catch: NoSuchFieldError -> L20
        L22:
            $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.NONE.ordinal()] = 9;     // Catch: NoSuchFieldError -> L21
            return;
        }
    }

    public Guideline() {
        this.mRelativePercent = -1.0f;
        this.mRelativeBegin = -1;
        this.mRelativeEnd = -1;
        this.mGuidelineUseRtl = true;
        this.mAnchor = this.mTop;
        int r0 = 0;
        this.mOrientation = 0;
        this.mMinimumPosition = 0;
        this.mAnchors.clear();
        this.mAnchors.add(this.mAnchor);
        int r1 = this.mListAnchors.length;
    L3:
        if (r0 >= r1) goto L5;
        this.mListAnchors[r0] = this.mAnchor;
        r0 = r0 + 1;
        goto L3
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public void addToSolver(LinearSystem r8, boolean r9) {
        ConstraintWidgetContainer r92 = (ConstraintWidgetContainer) getParent();
        if (r92 == null) goto L53;
        ConstraintAnchor r0 = r92.getAnchor(ConstraintAnchor.Type.LEFT);
        ConstraintAnchor r1 = r92.getAnchor(ConstraintAnchor.Type.RIGHT);
        ConstraintWidget r2 = this.mParent;
        boolean r3 = true;
        if (r2 != null) goto L8;
    L10:
        boolean r22 = false;
    L12:
        if (this.mOrientation != 0) goto L21;
        r0 = r92.getAnchor(ConstraintAnchor.Type.TOP);
        r1 = r92.getAnchor(ConstraintAnchor.Type.BOTTOM);
        ConstraintWidget r93 = this.mParent;
        if (r93 != null) goto L16;
    L18:
        r3 = false;
    L19:
        r22 = r3;
        goto L21
    L16:
        if (r93.mListDimensionBehaviors[1] != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) goto L18;
    L21:
        if (this.mResolved == false) goto L35;
        if (this.mAnchor.hasFinalValue() == false) goto L35;
        SolverVariable r94 = r8.createObjectVariable(this.mAnchor);
        r8.addEquality(r94, this.mAnchor.getFinalValue());
        if (this.mRelativeBegin == (-1)) goto L29;
        if (r22 == false) goto L32;
        r8.addGreaterThan(r8.createObjectVariable(r1), r94, 0, 5);
    L32:
        this.mResolved = false;
        return;
    L29:
        if (this.mRelativeEnd == (-1)) goto L32;
        if (r22 == false) goto L32;
        SolverVariable r12 = r8.createObjectVariable(r1);
        r8.addGreaterThan(r94, r8.createObjectVariable(r0), 0, 5);
        r8.addGreaterThan(r12, r94, 0, 5);
    L35:
        if (this.mRelativeBegin == (-1)) goto L41;
        SolverVariable r95 = r8.createObjectVariable(this.mAnchor);
        r8.addEquality(r95, r8.createObjectVariable(r0), this.mRelativeBegin, 8);
        if (r22 == false) goto L50;
        r8.addGreaterThan(r8.createObjectVariable(r1), r95, 0, 5);
        return;
    L50:
        return;
    L41:
        if (this.mRelativeEnd == (-1)) goto L47;
        SolverVariable r96 = r8.createObjectVariable(this.mAnchor);
        SolverVariable r13 = r8.createObjectVariable(r1);
        r8.addEquality(r96, r13, -this.mRelativeEnd, 8);
        if (r22 == false) goto L51;
        r8.addGreaterThan(r96, r8.createObjectVariable(r0), 0, 5);
        r8.addGreaterThan(r13, r96, 0, 5);
        return;
    L51:
        return;
    L47:
        if (this.mRelativePercent == (-1.0f)) goto L52;
        r8.addConstraint(LinearSystem.createRowDimensionPercent(r8, r8.createObjectVariable(this.mAnchor), r8.createObjectVariable(r1), this.mRelativePercent));
        return;
    L52:
        return;
    L8:
        if (r2.mListDimensionBehaviors[0] != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) goto L10;
        r22 = true;
        goto L12
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public boolean allowedInBarrier() {
        return true;
    }

    public ConstraintAnchor getAnchor() {
        return this.mAnchor;
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public ConstraintAnchor getAnchor(ConstraintAnchor.Type r3) {
        int r32 = AnonymousClass1.$SwitchMap$androidx$constraintlayout$core$widgets$ConstraintAnchor$Type[r3.ordinal()];
        if (r32 == 1) goto L16;
        if (r32 == 2) goto L16;
        if (r32 == 3) goto L12;
        if (r32 == 4) goto L12;
        return null;
    L12:
        if (this.mOrientation == 0) goto L14;
        return null;
    L14:
        return this.mAnchor;
    L16:
        if (this.mOrientation == 1) goto L18;
        return null;
    L18:
        return this.mAnchor;
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    public int getRelativeBegin() {
        return this.mRelativeBegin;
    }

    public int getRelativeEnd() {
        return this.mRelativeEnd;
    }

    public float getRelativePercent() {
        return this.mRelativePercent;
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public boolean isResolvedHorizontally() {
        return this.mResolved;
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public boolean isResolvedVertically() {
        return this.mResolved;
    }

    public void setFinalValue(int r2) {
        this.mAnchor.setFinalValue(r2);
        this.mResolved = true;
    }

    public void setGuideBegin(int r3) {
        if (r3 <= (-1)) goto L6;
        this.mRelativePercent = -1.0f;
        this.mRelativeBegin = r3;
        this.mRelativeEnd = -1;
        return;
    }

    public void setGuideEnd(int r3) {
        if (r3 <= (-1)) goto L6;
        this.mRelativePercent = -1.0f;
        this.mRelativeBegin = -1;
        this.mRelativeEnd = r3;
        return;
    }

    public void setGuidePercent(float r2) {
        if (r2 <= (-1.0f)) goto L6;
        this.mRelativePercent = r2;
        this.mRelativeBegin = -1;
        this.mRelativeEnd = -1;
        return;
    }

    public void setOrientation(int r4) {
        if (this.mOrientation == r4) goto L12;
        this.mOrientation = r4;
        this.mAnchors.clear();
        if (this.mOrientation != 1) goto L8;
        this.mAnchor = this.mLeft;
    L9:
        this.mAnchors.add(this.mAnchor);
        int r42 = this.mListAnchors.length;
        int r0 = 0;
    L10:
        if (r0 >= r42) goto L14;
        this.mListAnchors[r0] = this.mAnchor;
        r0 = r0 + 1;
        goto L10
    L14:
        return;
    L8:
        this.mAnchor = this.mTop;
        goto L9
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public void updateFromSolver(LinearSystem r3, boolean r4) {
        if (getParent() != null) goto L5;
        return;
    L5:
        int r32 = r3.getObjectVariableValue(this.mAnchor);
        if (this.mOrientation != 1) goto L9;
        setX(r32);
        setY(0);
        setHeight(getParent().getHeight());
        setWidth(0);
        return;
    L9:
        setX(0);
        setY(r32);
        setWidth(getParent().getWidth());
        setHeight(0);
    }
}
