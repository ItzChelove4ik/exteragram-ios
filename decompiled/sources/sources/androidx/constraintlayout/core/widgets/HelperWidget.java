package androidx.constraintlayout.core.widgets;

import androidx.constraintlayout.core.widgets.analyzer.Grouping;
import androidx.constraintlayout.core.widgets.analyzer.WidgetGroup;
import java.util.ArrayList;
import java.util.Arrays;

/* loaded from: classes3.dex */
public abstract class HelperWidget extends ConstraintWidget implements Helper {
    public ConstraintWidget[] mWidgets;
    public int mWidgetsCount;

    public HelperWidget() {
        this.mWidgets = new ConstraintWidget[4];
        this.mWidgetsCount = 0;
    }

    @Override // androidx.constraintlayout.core.widgets.Helper
    public void add(ConstraintWidget r4) {
        if (r4 == this) goto L10;
        if (r4 == null) goto L11;
        int r0 = this.mWidgetsCount + 1;
        ConstraintWidget[] r1 = this.mWidgets;
        if (r0 <= r1.length) goto L8;
        this.mWidgets = (ConstraintWidget[]) Arrays.copyOf(r1, r1.length * 2);
    L8:
        ConstraintWidget[] r02 = this.mWidgets;
        int r12 = this.mWidgetsCount;
        r02[r12] = r4;
        this.mWidgetsCount = r12 + 1;
        return;
    L11:
        return;
    }

    public void addDependents(ArrayList<WidgetGroup> r4, int r5, WidgetGroup r6) {
        int r0 = 0;
        int r1 = 0;
    L4:
        if (r1 >= this.mWidgetsCount) goto L7;
        r6.add(this.mWidgets[r1]);
        r1 = r1 + 1;
    L7:
        if (r0 >= this.mWidgetsCount) goto L9;
        Grouping.findDependents(this.mWidgets[r0], r5, r4, r6);
        r0 = r0 + 1;
        goto L7
    }

    public int findGroupInDependents(int r5) {
        int r0 = 0;
    L4:
        if (r0 >= this.mWidgetsCount) goto L16;
        ConstraintWidget r1 = this.mWidgets[r0];
        if (r5 != 0) goto L11;
        int r3 = r1.horizontalGroup;
        if (r3 == (-1)) goto L11;
        return r3;
    L11:
        if (r5 != 1) goto L15;
        int r12 = r1.verticalGroup;
        if (r12 == (-1)) goto L15;
        return r12;
    L15:
        r0 = r0 + 1;
        goto L4
    L16:
        return -1;
    }

    @Override // androidx.constraintlayout.core.widgets.Helper
    public void removeAllIds() {
        this.mWidgetsCount = 0;
        Arrays.fill(this.mWidgets, null);
    }

    @Override // androidx.constraintlayout.core.widgets.Helper
    public void updateConstraints(ConstraintWidgetContainer r1) {
    }
}
