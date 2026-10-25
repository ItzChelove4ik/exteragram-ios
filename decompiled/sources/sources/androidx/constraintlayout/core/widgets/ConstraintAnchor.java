package androidx.constraintlayout.core.widgets;

import androidx.constraintlayout.core.Cache;
import androidx.constraintlayout.core.SolverVariable;
import androidx.constraintlayout.core.widgets.analyzer.Grouping;
import androidx.constraintlayout.core.widgets.analyzer.WidgetGroup;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import okio.Buffer$$ExternalSyntheticBUOutline2;

/* loaded from: classes3.dex */
public class ConstraintAnchor {
    private HashSet<ConstraintAnchor> mDependents;
    private int mFinalValue;
    int mGoneMargin;
    private boolean mHasFinalValue;
    public int mMargin;
    public final ConstraintWidget mOwner;
    SolverVariable mSolverVariable;
    public ConstraintAnchor mTarget;
    public final Type mType;

    public enum Type extends Enum<Type> {
        private static final /* synthetic */ Type[] $VALUES = null;
        public static final Type BASELINE = null;
        public static final Type BOTTOM = null;
        public static final Type CENTER = null;
        public static final Type CENTER_X = null;
        public static final Type CENTER_Y = null;
        public static final Type LEFT = null;
        public static final Type NONE = null;
        public static final Type RIGHT = null;
        public static final Type TOP = null;

        private static /* synthetic */ Type[] $values() {
            return new Type[]{NONE, LEFT, TOP, RIGHT, BOTTOM, BASELINE, CENTER, CENTER_X, CENTER_Y};
        }

        static {
            NONE = new Type("NONE", 0);
            LEFT = new Type("LEFT", 1);
            TOP = new Type("TOP", 2);
            RIGHT = new Type("RIGHT", 3);
            BOTTOM = new Type("BOTTOM", 4);
            BASELINE = new Type("BASELINE", 5);
            CENTER = new Type("CENTER", 6);
            CENTER_X = new Type("CENTER_X", 7);
            CENTER_Y = new Type("CENTER_Y", 8);
            $VALUES = $values();
        }

        Type(String r1, int r2) {
        }

        public static Type valueOf(String r1) {
            return (Type) Enum.valueOf(Type.class, r1);
        }

        public static Type[] values() {
            return (Type[]) $VALUES.clone();
        }
    }

    public ConstraintAnchor(ConstraintWidget r2, Type r3) {
        this.mDependents = null;
        this.mMargin = 0;
        this.mGoneMargin = Integer.MIN_VALUE;
        this.mOwner = r2;
        this.mType = r3;
    }

    public boolean connect(ConstraintAnchor r3, int r4) {
        return connect(r3, r4, Integer.MIN_VALUE, false);
    }

    public boolean connect(ConstraintAnchor r2, int r3, int r4, boolean r5) {
        if (r2 != null) goto L6;
        reset();
        return true;
    L6:
        if (r5 == false) goto L8;
    L11:
        this.mTarget = r2;
        if (r2.mDependents != null) goto L14;
        r2.mDependents = new HashSet();
    L14:
        HashSet<ConstraintAnchor> r22 = this.mTarget.mDependents;
        if (r22 == null) goto L17;
        r22.add(this);
    L17:
        this.mMargin = r3;
        this.mGoneMargin = r4;
        return true;
    L8:
        if (isValidConnection(r2) == true) goto L11;
        return false;
    }

    public void findDependents(int r2, ArrayList<WidgetGroup> r3, WidgetGroup r4) {
        HashSet<ConstraintAnchor> r1 = this.mDependents;
        if (r1 == null) goto L8;
        Iterator<ConstraintAnchor> r12 = r1.iterator();
    L6:
        if (r12.hasNext() == false) goto L10;
        Grouping.findDependents(r12.next().mOwner, r2, r3, r4);
        goto L6
    L10:
        return;
    }

    public HashSet<ConstraintAnchor> getDependents() {
        return this.mDependents;
    }

    public int getFinalValue() {
        if (this.mHasFinalValue == true) goto L7;
        return 0;
    L7:
        return this.mFinalValue;
    }

    public int getMargin() {
        if (this.mOwner.getVisibility() != 8) goto L7;
        return 0;
    L7:
        if (this.mGoneMargin == Integer.MIN_VALUE) goto L15;
        ConstraintAnchor r0 = this.mTarget;
        if (r0 == null) goto L15;
        if (r0.mOwner.getVisibility() != 8) goto L15;
        return this.mGoneMargin;
    L15:
        return this.mMargin;
    }

    public final ConstraintAnchor getOpposite() {
        switch(this.mType.ordinal()) {
            case 0: goto L14;
            case 1: goto L13;
            case 2: goto L11;
            case 3: goto L9;
            case 4: goto L7;
            case 5: goto L14;
            case 6: goto L14;
            case 7: goto L14;
            case 8: goto L14;
            default: goto L4;
        };
    L4:
        Buffer$$ExternalSyntheticBUOutline2.m(this.mType.name());
        return null;
    L14:
        return null;
    L7:
        return this.mOwner.mTop;
    L9:
        return this.mOwner.mLeft;
    L11:
        return this.mOwner.mBottom;
    L13:
        return this.mOwner.mRight;
    }

    public ConstraintWidget getOwner() {
        return this.mOwner;
    }

    public SolverVariable getSolverVariable() {
        return this.mSolverVariable;
    }

    public ConstraintAnchor getTarget() {
        return this.mTarget;
    }

    public Type getType() {
        return this.mType;
    }

    public boolean hasCenteredDependents() {
        HashSet<ConstraintAnchor> r2 = this.mDependents;
        if (r2 != null) goto L5;
        return false;
    L5:
        Iterator<ConstraintAnchor> r22 = r2.iterator();
    L7:
        if (r22.hasNext() == false) goto L12;
        if (r22.next().getOpposite().isConnected() == false) goto L7;
        return true;
    L12:
        return false;
    }

    public boolean hasDependents() {
        HashSet<ConstraintAnchor> r1 = this.mDependents;
        if (r1 != null) goto L6;
        return false;
    L6:
        if (r1.size() <= 0) goto L9;
        return true;
    L9:
        return false;
    }

    public boolean hasFinalValue() {
        return this.mHasFinalValue;
    }

    public boolean isConnected() {
        if (this.mTarget == null) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isValidConnection(ConstraintAnchor r5) {
        if (r5 != null) goto L5;
        return false;
    L5:
        Type r1 = r5.getType();
        Type r2 = this.mType;
        if (r1 != r2) goto L16;
        if (r2 == Type.BASELINE) goto L10;
    L14:
        return true;
    L10:
        if (r5.getOwner().hasBaseline() == true) goto L12;
    L13:
        return false;
    L12:
        if (getOwner().hasBaseline() == true) goto L14;
    L16:
        switch(r2.ordinal()) {
            case 0: goto L66;
            case 1: goto L51;
            case 2: goto L35;
            case 3: goto L51;
            case 4: goto L35;
            case 5: goto L28;
            case 6: goto L20;
            case 7: goto L66;
            case 8: goto L66;
            default: goto L17;
        };
    L17:
        Buffer$$ExternalSyntheticBUOutline2.m(this.mType.name());
        return false;
    L66:
        return false;
    L20:
        if (r1 != Type.BASELINE) goto L22;
    L26:
        return false;
    L22:
        if (r1 == Type.CENTER_X) goto L26;
        if (r1 == Type.CENTER_Y) goto L26;
        return true;
    L28:
        if (r1 != Type.LEFT) goto L30;
    L33:
        return false;
    L30:
        if (r1 == Type.RIGHT) goto L33;
        return true;
    L35:
        if (r1 != Type.TOP) goto L37;
    L40:
        boolean r4 = true;
    L42:
        if ((r5.getOwner() instanceof Guideline) == false) goto L49;
        if (r4 == false) goto L45;
    L48:
        return true;
    L45:
        if (r1 == Type.CENTER_Y) goto L48;
        return false;
    L49:
        return r4;
    L37:
        if (r1 == Type.BOTTOM) goto L40;
        r4 = false;
        goto L42
    L51:
        if (r1 != Type.LEFT) goto L53;
    L56:
        boolean r42 = true;
    L58:
        if ((r5.getOwner() instanceof Guideline) == false) goto L65;
        if (r42 == false) goto L61;
    L64:
        return true;
    L61:
        if (r1 == Type.CENTER_X) goto L64;
        return false;
    L65:
        return r42;
    L53:
        if (r1 == Type.RIGHT) goto L56;
        r42 = false;
        goto L58
    }

    public void reset() {
        ConstraintAnchor r0 = this.mTarget;
        if (r0 == null) goto L9;
        HashSet<ConstraintAnchor> r02 = r0.mDependents;
        if (r02 == null) goto L9;
        r02.remove(this);
        if (this.mTarget.mDependents.size() != 0) goto L9;
        this.mTarget.mDependents = null;
    L9:
        this.mDependents = null;
        this.mTarget = null;
        this.mMargin = 0;
        this.mGoneMargin = Integer.MIN_VALUE;
        this.mHasFinalValue = false;
        this.mFinalValue = 0;
    }

    public void resetFinalResolution() {
        this.mHasFinalValue = false;
        this.mFinalValue = 0;
    }

    public void resetSolverVariable(Cache r3) {
        SolverVariable r32 = this.mSolverVariable;
        if (r32 != null) goto L6;
        this.mSolverVariable = new SolverVariable(SolverVariable.Type.UNRESTRICTED, null);
        return;
    L6:
        r32.reset();
    }

    public void setFinalValue(int r1) {
        this.mFinalValue = r1;
        this.mHasFinalValue = true;
    }

    public void setGoneMargin(int r2) {
        if (isConnected() == false) goto L6;
        this.mGoneMargin = r2;
        return;
    }

    public String toString() {
        return this.mOwner.getDebugName() + ":" + this.mType.toString();
    }
}
