package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.LinearSystem;
import androidx.constraintlayout.core.widgets.Chain;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes3.dex */
public class WidgetGroup {
    static int sCount;
    boolean mAuthoritative;
    int mId;
    private int mMoveTo;
    int mOrientation;
    ArrayList<MeasureResult> mResults;
    ArrayList<ConstraintWidget> mWidgets;

    public static class MeasureResult {
        int mBaseline;
        int mBottom;
        int mLeft;
        int mOrientation;
        int mRight;
        int mTop;
        WeakReference<ConstraintWidget> mWidgetRef;

        public MeasureResult(ConstraintWidget r2, LinearSystem r3, int r4) {
            this.mWidgetRef = new WeakReference(r2);
            this.mLeft = r3.getObjectVariableValue(r2.mLeft);
            this.mTop = r3.getObjectVariableValue(r2.mTop);
            this.mRight = r3.getObjectVariableValue(r2.mRight);
            this.mBottom = r3.getObjectVariableValue(r2.mBottom);
            this.mBaseline = r3.getObjectVariableValue(r2.mBaseline);
            this.mOrientation = r4;
        }
    }

    static {
    }

    public WidgetGroup(int r3) {
        this.mWidgets = new ArrayList();
        this.mAuthoritative = false;
        this.mResults = null;
        this.mMoveTo = -1;
        int r0 = sCount;
        sCount = r0 + 1;
        this.mId = r0;
        this.mOrientation = r3;
    }

    private String getOrientationString() {
        int r1 = this.mOrientation;
        if (r1 != 0) goto L7;
        return "Horizontal";
    L7:
        if (r1 != 1) goto L11;
        return "Vertical";
    L11:
        if (r1 != 2) goto L14;
        return "Both";
    L14:
        return "Unknown";
    }

    private int solverMeasure(LinearSystem r8, ArrayList<ConstraintWidget> r9, int r10) {
        int r0 = 0;
        ConstraintWidgetContainer r1 = (ConstraintWidgetContainer) r9.get(0).getParent();
        r8.reset();
        r1.addToSolver(r8, false);
        int r2 = 0;
    L4:
        if (r2 >= r9.size()) goto L6;
        r9.get(r2).addToSolver(r8, false);
        r2 = r2 + 1;
        goto L4
    L6:
        if (r10 != 0) goto L11;
        if (r1.mHorizontalChainsSize <= 0) goto L11;
        Chain.applyChainConstraints(r1, r8, r9, 0);
    L11:
        if (r10 == 1) goto L13;
    L28:
        r8.minimize();     // Catch: Exception -> L17
    L19:
        this.mResults = new ArrayList();
    L21:
        if (r0 >= r9.size()) goto L23;
        this.mResults.add(new MeasureResult(r9.get(r0), r8, r10));
        r0 = r0 + 1;
        goto L21
    L23:
        if (r10 != 0) goto L27;
        int r7 = r8.getObjectVariableValue(r1.mLeft);
        int r92 = r8.getObjectVariableValue(r1.mRight);
        r8.reset();
    L26:
        return r92 - r7;
    L27:
        r7 = r8.getObjectVariableValue(r1.mTop);
        r92 = r8.getObjectVariableValue(r1.mBottom);
        r8.reset();
    L17:
        e = move-exception;
        System.err.println(e.toString() + "\n" + Arrays.toString(e.getStackTrace()).replace("[", "   at ").replace(",", "\n   at").replace("]", _UrlKt.FRAGMENT_ENCODE_SET));
        goto L19
    L13:
        if (r1.mVerticalChainsSize <= 0) goto L28;
        Chain.applyChainConstraints(r1, r8, r9, 1);
        goto L28
    }

    public boolean add(ConstraintWidget r2) {
        if (this.mWidgets.contains(r2) == false) goto L6;
        return false;
    L6:
        this.mWidgets.add(r2);
        return true;
    }

    public void cleanup(ArrayList<WidgetGroup> r6) {
        int r0 = this.mWidgets.size();
        if (this.mMoveTo == (-1)) goto L12;
        if (r0 <= 0) goto L12;
        int r1 = 0;
    L7:
        if (r1 >= r6.size()) goto L12;
        WidgetGroup r2 = r6.get(r1);
        if (this.mMoveTo != r2.mId) goto L11;
        moveTo(this.mOrientation, r2);
    L11:
        r1 = r1 + 1;
    L12:
        if (r0 != 0) goto L18;
        r6.remove(this);
        return;
    }

    public int getId() {
        return this.mId;
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    public int measureWrap(LinearSystem r2, int r3) {
        if (this.mWidgets.size() != 0) goto L7;
        return 0;
    L7:
        return solverMeasure(r2, this.mWidgets, r3);
    }

    public void moveTo(int r6, WidgetGroup r7) {
        ArrayList<ConstraintWidget> r0 = this.mWidgets;
        int r1 = r0.size();
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L8;
        ConstraintWidget r3 = r0.get(r2);
        r2 = r2 + 1;
        ConstraintWidget r32 = r3;
        r7.add(r32);
        if (r6 == 0) goto L6;
        r32.verticalGroup = r7.getId();
        goto L3
    L6:
        r32.horizontalGroup = r7.getId();
        goto L3
    L8:
        this.mMoveTo = r7.mId;
    }

    public void setAuthoritative(boolean r1) {
        this.mAuthoritative = r1;
    }

    public void setOrientation(int r1) {
        this.mOrientation = r1;
    }

    public String toString() {
        String r0 = getOrientationString() + " [" + this.mId + "] <";
        ArrayList<ConstraintWidget> r5 = this.mWidgets;
        int r1 = r5.size();
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L6;
        ConstraintWidget r3 = r5.get(r2);
        r2 = r2 + 1;
        r0 = r0 + " " + r3.getDebugName();
        goto L3
    L6:
        return r0.concat(" >");
    }
}
