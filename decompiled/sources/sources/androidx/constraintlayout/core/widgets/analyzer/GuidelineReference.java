package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.Guideline;

/* loaded from: classes3.dex */
class GuidelineReference extends WidgetRun {
    public GuidelineReference(ConstraintWidget r2) {
        super(r2);
        r2.mHorizontalRun.clear();
        r2.mVerticalRun.clear();
        this.orientation = ((Guideline) r2).getOrientation();
    }

    private void addDependency(DependencyNode r2) {
        this.start.mDependencies.add(r2);
        r2.mTargets.add(this.start);
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public void apply() {
        Guideline r0 = (Guideline) this.mWidget;
        int r1 = r0.getRelativeBegin();
        int r2 = r0.getRelativeEnd();
        r0.getRelativePercent();
        if (r0.getOrientation() != 1) goto L12;
        if (r1 == (-1)) goto L6;
        this.start.mTargets.add(this.mWidget.mParent.mHorizontalRun.start);
        this.mWidget.mParent.mHorizontalRun.start.mDependencies.add(this.start);
        this.start.mMargin = r1;
    L10:
        addDependency(this.mWidget.mHorizontalRun.start);
        addDependency(this.mWidget.mHorizontalRun.end);
        return;
    L6:
        DependencyNode r02 = this.start;
        if (r2 == (-1)) goto L9;
        r02.mTargets.add(this.mWidget.mParent.mHorizontalRun.end);
        this.mWidget.mParent.mHorizontalRun.end.mDependencies.add(this.start);
        int r12 = -r2;
        this.start.mMargin = r12;
        goto L10
    L9:
        r02.delegateToWidgetRun = true;
        r02.mTargets.add(this.mWidget.mParent.mHorizontalRun.end);
        this.mWidget.mParent.mHorizontalRun.end.mDependencies.add(this.start);
        goto L10
    L12:
        if (r1 == (-1)) goto L14;
        this.start.mTargets.add(this.mWidget.mParent.mVerticalRun.start);
        this.mWidget.mParent.mVerticalRun.start.mDependencies.add(this.start);
        this.start.mMargin = r1;
    L18:
        addDependency(this.mWidget.mVerticalRun.start);
        addDependency(this.mWidget.mVerticalRun.end);
        return;
    L14:
        DependencyNode r03 = this.start;
        if (r2 == (-1)) goto L17;
        r03.mTargets.add(this.mWidget.mParent.mVerticalRun.end);
        this.mWidget.mParent.mVerticalRun.end.mDependencies.add(this.start);
        int r13 = -r2;
        this.start.mMargin = r13;
        goto L18
    L17:
        r03.delegateToWidgetRun = true;
        r03.mTargets.add(this.mWidget.mParent.mVerticalRun.end);
        this.mWidget.mParent.mVerticalRun.end.mDependencies.add(this.start);
        goto L18
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public void applyToWidget() {
        int r0 = ((Guideline) this.mWidget).getOrientation();
        ConstraintWidget r1 = this.mWidget;
        if (r0 != 1) goto L6;
        r1.setX(this.start.value);
        return;
    L6:
        r1.setY(this.start.value);
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public void clear() {
        this.start.clear();
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public boolean supportsWrapComputation() {
        return false;
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun, androidx.constraintlayout.core.widgets.analyzer.Dependency
    public void update(Dependency r2) {
        DependencyNode r22 = this.start;
        if (r22.readyToSolve == true) goto L6;
        return;
    L6:
        if (r22.resolved == false) goto L8;
        return;
    L8:
        DependencyNode r23 = r22.mTargets.get(0);
        this.start.resolve((int) ((r23.value * ((Guideline) this.mWidget).getRelativePercent()) + 0.5f));
    }
}
