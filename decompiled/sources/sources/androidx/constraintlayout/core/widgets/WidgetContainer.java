package androidx.constraintlayout.core.widgets;

import androidx.constraintlayout.core.Cache;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public abstract class WidgetContainer extends ConstraintWidget {
    public ArrayList<ConstraintWidget> mChildren;

    public WidgetContainer() {
        this.mChildren = new ArrayList();
    }

    public void add(ConstraintWidget r2) {
        this.mChildren.add(r2);
        if (r2.getParent() == null) goto L5;
        ((WidgetContainer) r2.getParent()).remove(r2);
    L5:
        r2.setParent(this);
    }

    public ArrayList<ConstraintWidget> getChildren() {
        return this.mChildren;
    }

    public abstract void layout();

    public void remove(ConstraintWidget r1) {
        this.mChildren.remove(r1);
        r1.reset();
    }

    public void removeAllChildren() {
        this.mChildren.clear();
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public void reset() {
        this.mChildren.clear();
        super.reset();
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public void resetSolverVariables(Cache r4) {
        super.resetSolverVariables(r4);
        int r0 = this.mChildren.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        this.mChildren.get(r1).resetSolverVariables(r4);
        r1 = r1 + 1;
        goto L3
    }
}
