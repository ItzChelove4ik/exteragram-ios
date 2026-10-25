package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.Barrier;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.analyzer.DependencyNode;
import java.util.Iterator;

/* loaded from: classes3.dex */
class HelperReferences extends WidgetRun {
    public HelperReferences(ConstraintWidget r1) {
        super(r1);
    }

    private void addDependency(DependencyNode r2) {
        this.start.mDependencies.add(r2);
        r2.mTargets.add(this.start);
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public void apply() {
        ConstraintWidget r0 = this.mWidget;
        if ((r0 instanceof Barrier) == false) goto L72;
        this.start.delegateToWidgetRun = true;
        Barrier r02 = (Barrier) r0;
        int r1 = r02.getBarrierType();
        boolean r3 = r02.getAllowsGoneWidget();
        int r5 = 0;
        if (r1 == 0) goto L48;
        if (r1 != 1) goto L8;
        DependencyNode r12 = this.start;
        r12.mType = DependencyNode.Type.RIGHT;
    L38:
        if (r5 >= r02.mWidgetsCount) goto L46;
        ConstraintWidget r13 = r02.mWidgets[r5];
        if (r3 == false) goto L42;
    L44:
        DependencyNode r14 = r13.mHorizontalRun.end;
        r14.mDependencies.add(this.start);
        this.start.mTargets.add(r14);
    L45:
        r5 = r5 + 1;
        goto L38
    L42:
        if (r13.getVisibility() != 8) goto L44;
    L46:
        addDependency(this.mWidget.mHorizontalRun.start);
        addDependency(this.mWidget.mHorizontalRun.end);
        return;
    L8:
        if (r1 != 2) goto L10;
        DependencyNode r15 = this.start;
        r15.mType = DependencyNode.Type.TOP;
    L26:
        if (r5 >= r02.mWidgetsCount) goto L34;
        ConstraintWidget r16 = r02.mWidgets[r5];
        if (r3 == false) goto L30;
    L32:
        DependencyNode r17 = r16.mVerticalRun.start;
        r17.mDependencies.add(this.start);
        this.start.mTargets.add(r17);
    L33:
        r5 = r5 + 1;
        goto L26
    L30:
        if (r16.getVisibility() != 8) goto L32;
    L34:
        addDependency(this.mWidget.mVerticalRun.start);
        addDependency(this.mWidget.mVerticalRun.end);
        return;
    L10:
        if (r1 != 3) goto L73;
        DependencyNode r18 = this.start;
        r18.mType = DependencyNode.Type.BOTTOM;
    L14:
        if (r5 >= r02.mWidgetsCount) goto L22;
        ConstraintWidget r19 = r02.mWidgets[r5];
        if (r3 == false) goto L18;
    L20:
        DependencyNode r110 = r19.mVerticalRun.end;
        r110.mDependencies.add(this.start);
        this.start.mTargets.add(r110);
    L21:
        r5 = r5 + 1;
        goto L14
    L18:
        if (r19.getVisibility() != 8) goto L20;
    L22:
        addDependency(this.mWidget.mVerticalRun.start);
        addDependency(this.mWidget.mVerticalRun.end);
        return;
    L73:
        return;
    L48:
        DependencyNode r111 = this.start;
        r111.mType = DependencyNode.Type.LEFT;
    L50:
        if (r5 >= r02.mWidgetsCount) goto L58;
        ConstraintWidget r112 = r02.mWidgets[r5];
        if (r3 == false) goto L54;
    L56:
        DependencyNode r113 = r112.mHorizontalRun.start;
        r113.mDependencies.add(this.start);
        this.start.mTargets.add(r113);
    L57:
        r5 = r5 + 1;
        goto L50
    L54:
        if (r112.getVisibility() != 8) goto L56;
    L58:
        addDependency(this.mWidget.mHorizontalRun.start);
        addDependency(this.mWidget.mHorizontalRun.end);
        return;
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public void applyToWidget() {
        ConstraintWidget r0 = this.mWidget;
        if ((r0 instanceof Barrier) == false) goto L13;
        int r02 = ((Barrier) r0).getBarrierType();
        if (r02 != 0) goto L7;
    L11:
        this.mWidget.setX(this.start.value);
        return;
    L7:
        if (r02 == 1) goto L11;
        this.mWidget.setY(this.start.value);
        return;
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public void clear() {
        this.mRunGroup = null;
        this.start.clear();
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public boolean supportsWrapComputation() {
        return false;
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun, androidx.constraintlayout.core.widgets.analyzer.Dependency
    public void update(Dependency r7) {
        Barrier r72 = (Barrier) this.mWidget;
        int r0 = r72.getBarrierType();
        Iterator<DependencyNode> r1 = this.start.mTargets.iterator();
        int r3 = 0;
        int r4 = -1;
    L4:
        if (r1.hasNext() == false) goto L11;
        int r5 = r1.next().value;
        if (r4 == (-1)) goto L8;
        if (r5 < r4) goto L8;
    L9:
        if (r3 >= r5) goto L4;
        r3 = r5;
    L8:
        r4 = r5;
        goto L9
    L11:
        if (r0 != 0) goto L13;
    L17:
        this.start.resolve(r4 + r72.getMargin());
        return;
    L13:
        if (r0 == 2) goto L17;
        this.start.resolve(r3 + r72.getMargin());
    }
}
