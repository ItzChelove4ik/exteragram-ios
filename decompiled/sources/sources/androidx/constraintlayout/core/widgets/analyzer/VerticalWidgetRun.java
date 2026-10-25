package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.Helper;
import androidx.constraintlayout.core.widgets.analyzer.DependencyNode;
import androidx.constraintlayout.core.widgets.analyzer.WidgetRun;

/* loaded from: classes3.dex */
public class VerticalWidgetRun extends WidgetRun {
    public DependencyNode baseline;
    DimensionDependency mBaselineDimension;

    /* renamed from: androidx.constraintlayout.core.widgets.analyzer.VerticalWidgetRun$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$androidx$constraintlayout$core$widgets$analyzer$WidgetRun$RunType = null;

        static {
            int[] r0 = new int[WidgetRun.RunType.values().length];
            $SwitchMap$androidx$constraintlayout$core$widgets$analyzer$WidgetRun$RunType = r0;
            r0[WidgetRun.RunType.START.ordinal()] = 1;     // Catch: NoSuchFieldError -> L7
        L10:
            $SwitchMap$androidx$constraintlayout$core$widgets$analyzer$WidgetRun$RunType[WidgetRun.RunType.END.ordinal()] = 2;     // Catch: NoSuchFieldError -> L8
        L12:
            $SwitchMap$androidx$constraintlayout$core$widgets$analyzer$WidgetRun$RunType[WidgetRun.RunType.CENTER.ordinal()] = 3;     // Catch: NoSuchFieldError -> L9
            return;
        }
    }

    public VerticalWidgetRun(ConstraintWidget r3) {
        super(r3);
        DependencyNode r32 = new DependencyNode(this);
        this.baseline = r32;
        this.mBaselineDimension = null;
        DependencyNode r0 = this.start;
        r0.mType = DependencyNode.Type.TOP;
        DependencyNode r02 = this.end;
        r02.mType = DependencyNode.Type.BOTTOM;
        r32.mType = DependencyNode.Type.BASELINE;
        this.orientation = 1;
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public void apply() {
        ConstraintWidget r0 = this.mWidget;
        if (r0.measured == false) goto L6;
        this.mDimension.resolve(r0.getHeight());
    L6:
        if (this.mDimension.resolved == true) goto L24;
        this.mDimensionBehavior = this.mWidget.getVerticalDimensionBehaviour();
        if (this.mWidget.hasBaseline() == false) goto L10;
        this.mBaselineDimension = new BaselineDimensionDependency(this);
    L10:
        ConstraintWidget.DimensionBehaviour r02 = this.mDimensionBehavior;
        if (r02 != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) goto L13;
    L31:
        DimensionDependency r03 = this.mDimension;
        boolean r1 = r03.resolved;
        if (r1 == false) goto L85;
        ConstraintWidget r7 = this.mWidget;
        if (r7.measured == false) goto L85;
        ConstraintAnchor[] r04 = r7.mListAnchors;
        ConstraintAnchor r12 = r04[2];
        ConstraintAnchor r8 = r12.mTarget;
        if (r8 != null) goto L38;
    L53:
        if (r8 == null) goto L60;
        DependencyNode r05 = getTarget(r12);
        if (r05 == null) goto L164;
        addTarget(this.start, r05, this.mWidget.mListAnchors[2].getMargin());
        addTarget(this.end, this.start, this.mDimension.value);
        if (this.mWidget.hasBaseline() == false) goto L165;
        addTarget(this.baseline, this.start, this.mWidget.getBaselineDistance());
        return;
    L165:
        return;
    L164:
        return;
    L60:
        ConstraintAnchor r13 = r04[3];
        if (r13.mTarget == null) goto L69;
        DependencyNode r06 = getTarget(r13);
        if (r06 == null) goto L66;
        addTarget(this.end, r06, -this.mWidget.mListAnchors[3].getMargin());
        addTarget(this.start, this.end, -this.mDimension.value);
    L66:
        if (this.mWidget.hasBaseline() == false) goto L166;
        addTarget(this.baseline, this.start, this.mWidget.getBaselineDistance());
        return;
    L166:
        return;
    L69:
        ConstraintAnchor r07 = r04[4];
        if (r07.mTarget == null) goto L76;
        DependencyNode r08 = getTarget(r07);
        if (r08 == null) goto L167;
        addTarget(this.baseline, r08, 0);
        addTarget(this.start, this.baseline, -this.mWidget.getBaselineDistance());
        addTarget(this.end, this.start, this.mDimension.value);
        return;
    L167:
        return;
    L76:
        if ((r7 instanceof Helper) == false) goto L78;
        return;
    L78:
        if (r7.getParent() != null) goto L80;
        return;
    L80:
        if (this.mWidget.getAnchor(ConstraintAnchor.Type.CENTER).mTarget != null) goto L170;
        DependencyNode r09 = this.mWidget.getParent().mVerticalRun.start;
        addTarget(this.start, r09, this.mWidget.getY());
        addTarget(this.end, this.start, this.mDimension.value);
        if (this.mWidget.hasBaseline() == false) goto L171;
        addTarget(this.baseline, this.start, this.mWidget.getBaselineDistance());
        return;
    L171:
        return;
    L170:
        return;
    L38:
        if (r04[3].mTarget == null) goto L53;
        if (r7.isInVerticalChain() == false) goto L42;
        DependencyNode r010 = this.start;
        r010.mMargin = this.mWidget.mListAnchors[2].getMargin();
        DependencyNode r011 = this.end;
        r011.mMargin = -this.mWidget.mListAnchors[3].getMargin();
    L50:
        if (this.mWidget.hasBaseline() == false) goto L163;
        addTarget(this.baseline, this.start, this.mWidget.getBaselineDistance());
        return;
    L163:
        return;
    L42:
        DependencyNode r012 = getTarget(this.mWidget.mListAnchors[2]);
        if (r012 == null) goto L45;
        addTarget(this.start, r012, this.mWidget.mListAnchors[2].getMargin());
    L45:
        DependencyNode r013 = getTarget(this.mWidget.mListAnchors[3]);
        if (r013 == null) goto L48;
        addTarget(this.end, r013, -this.mWidget.mListAnchors[3].getMargin());
    L48:
        this.start.delegateToWidgetRun = true;
        this.end.delegateToWidgetRun = true;
    L85:
        if (r1 == false) goto L87;
    L102:
        r03.addDependency(this);
    L103:
        ConstraintWidget r014 = this.mWidget;
        ConstraintAnchor[] r14 = r014.mListAnchors;
        ConstraintAnchor r72 = r14[2];
        ConstraintAnchor r82 = r72.mTarget;
        if (r82 == null) goto L120;
        if (r14[3].mTarget == null) goto L120;
        if (r014.isInVerticalChain() == false) goto L110;
        DependencyNode r015 = this.start;
        r015.mMargin = this.mWidget.mListAnchors[2].getMargin();
        DependencyNode r016 = this.end;
        r016.mMargin = -this.mWidget.mListAnchors[3].getMargin();
    L117:
        if (this.mWidget.hasBaseline() == false) goto L160;
        addTarget(this.baseline, this.start, 1, this.mBaselineDimension);
    L160:
        if (this.mDimension.mTargets.size() != 0) goto L172;
        this.mDimension.readyToSolve = true;
        return;
    L172:
        return;
    L110:
        DependencyNode r017 = getTarget(this.mWidget.mListAnchors[2]);
        DependencyNode r15 = getTarget(this.mWidget.mListAnchors[3]);
        if (r017 == null) goto L113;
        r017.addDependency(this);
    L113:
        if (r15 == null) goto L115;
        r15.addDependency(this);
    L115:
        this.mRunType = WidgetRun.RunType.CENTER;
    L120:
        if (r82 == null) goto L133;
        DependencyNode r018 = getTarget(r72);
        if (r018 == null) goto L160;
        addTarget(this.start, r018, this.mWidget.mListAnchors[2].getMargin());
        addTarget(this.end, this.start, 1, this.mDimension);
        if (this.mWidget.hasBaseline() == false) goto L126;
        addTarget(this.baseline, this.start, 1, this.mBaselineDimension);
    L126:
        ConstraintWidget.DimensionBehaviour r019 = this.mDimensionBehavior;
        ConstraintWidget.DimensionBehaviour r16 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        if (r019 != r16) goto L160;
        if (this.mWidget.getDimensionRatio() <= 0.0f) goto L160;
        HorizontalWidgetRun r020 = this.mWidget.mHorizontalRun;
        if (r020.mDimensionBehavior != r16) goto L160;
        r020.mDimension.mDependencies.add(this.mDimension);
        this.mDimension.mTargets.add(this.mWidget.mHorizontalRun.mDimension);
        this.mDimension.updateDelegate = this;
        goto L160
    L133:
        ConstraintAnchor r4 = r14[3];
        if (r4.mTarget == null) goto L140;
        DependencyNode r021 = getTarget(r4);
        if (r021 == null) goto L160;
        addTarget(this.end, r021, -this.mWidget.mListAnchors[3].getMargin());
        addTarget(this.start, this.end, -1, this.mDimension);
        if (this.mWidget.hasBaseline() == false) goto L160;
        addTarget(this.baseline, this.start, 1, this.mBaselineDimension);
        goto L160
    L140:
        ConstraintAnchor r17 = r14[4];
        if (r17.mTarget == null) goto L146;
        DependencyNode r022 = getTarget(r17);
        if (r022 == null) goto L160;
        addTarget(this.baseline, r022, 0);
        addTarget(this.start, this.baseline, -1, this.mBaselineDimension);
        addTarget(this.end, this.start, 1, this.mDimension);
        goto L160
    L146:
        if ((r014 instanceof Helper) == true) goto L160;
        if (r014.getParent() == null) goto L160;
        DependencyNode r023 = this.mWidget.getParent().mVerticalRun.start;
        addTarget(this.start, r023, this.mWidget.getY());
        addTarget(this.end, this.start, 1, this.mDimension);
        if (this.mWidget.hasBaseline() == false) goto L152;
        addTarget(this.baseline, this.start, 1, this.mBaselineDimension);
    L152:
        ConstraintWidget.DimensionBehaviour r024 = this.mDimensionBehavior;
        ConstraintWidget.DimensionBehaviour r18 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        if (r024 != r18) goto L160;
        if (this.mWidget.getDimensionRatio() <= 0.0f) goto L160;
        HorizontalWidgetRun r025 = this.mWidget.mHorizontalRun;
        if (r025.mDimensionBehavior != r18) goto L160;
        r025.mDimension.mDependencies.add(this.mDimension);
        this.mDimension.mTargets.add(this.mWidget.mHorizontalRun.mDimension);
        this.mDimension.updateDelegate = this;
        goto L160
    L87:
        if (this.mDimensionBehavior != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) goto L102;
        ConstraintWidget r026 = this.mWidget;
        int r19 = r026.mMatchConstraintDefaultHeight;
        if (r19 == 2) goto L98;
        if (r19 != 3) goto L103;
        if (r026.isInVerticalChain() == true) goto L103;
        ConstraintWidget r027 = this.mWidget;
        if (r027.mMatchConstraintDefaultWidth == 3) goto L103;
        DimensionDependency r028 = r027.mHorizontalRun.mDimension;
        this.mDimension.mTargets.add(r028);
        r028.mDependencies.add(this.mDimension);
        DimensionDependency r029 = this.mDimension;
        r029.delegateToWidgetRun = true;
        r029.mDependencies.add(this.start);
        this.mDimension.mDependencies.add(this.end);
        goto L103
    L98:
        ConstraintWidget r030 = r026.getParent();
        if (r030 == null) goto L103;
        DimensionDependency r031 = r030.mVerticalRun.mDimension;
        this.mDimension.mTargets.add(r031);
        r031.mDependencies.add(this.mDimension);
        DimensionDependency r032 = this.mDimension;
        r032.delegateToWidgetRun = true;
        r032.mDependencies.add(this.start);
        this.mDimension.mDependencies.add(this.end);
        goto L103
    L13:
        if (r02 != ConstraintWidget.DimensionBehaviour.MATCH_PARENT) goto L21;
        ConstraintWidget r033 = this.mWidget.getParent();
        if (r033 == null) goto L21;
        if (r033.getVerticalDimensionBehaviour() != ConstraintWidget.DimensionBehaviour.FIXED) goto L21;
        int r110 = (r033.getHeight() - this.mWidget.mTop.getMargin()) - this.mWidget.mBottom.getMargin();
        addTarget(this.start, r033.mVerticalRun.start, this.mWidget.mTop.getMargin());
        addTarget(this.end, r033.mVerticalRun.end, -this.mWidget.mBottom.getMargin());
        this.mDimension.resolve(r110);
        return;
    L21:
        if (this.mDimensionBehavior != ConstraintWidget.DimensionBehaviour.FIXED) goto L31;
        this.mDimension.resolve(this.mWidget.getHeight());
        goto L31
    L24:
        if (this.mDimensionBehavior != ConstraintWidget.DimensionBehaviour.MATCH_PARENT) goto L31;
        ConstraintWidget r034 = this.mWidget.getParent();
        if (r034 == null) goto L31;
        if (r034.getVerticalDimensionBehaviour() != ConstraintWidget.DimensionBehaviour.FIXED) goto L31;
        addTarget(this.start, r034.mVerticalRun.start, this.mWidget.mTop.getMargin());
        addTarget(this.end, r034.mVerticalRun.end, -this.mWidget.mBottom.getMargin());
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public void applyToWidget() {
        DependencyNode r0 = this.start;
        if (r0.resolved == false) goto L6;
        this.mWidget.setY(r0.value);
        return;
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public void clear() {
        this.mRunGroup = null;
        this.start.clear();
        this.end.clear();
        this.baseline.clear();
        this.mDimension.clear();
        this.mResolved = false;
    }

    public void reset() {
        this.mResolved = false;
        this.start.clear();
        this.start.resolved = false;
        this.end.clear();
        this.end.resolved = false;
        this.baseline.clear();
        this.baseline.resolved = false;
        this.mDimension.resolved = false;
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public boolean supportsWrapComputation() {
        if (this.mDimensionBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) goto L5;
        return true;
    L5:
        if (this.mWidget.mMatchConstraintDefaultHeight != 0) goto L7;
        return true;
    L7:
        return false;
    }

    public String toString() {
        return "VerticalRun " + this.mWidget.getDebugName();
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun, androidx.constraintlayout.core.widgets.analyzer.Dependency
    public void update(Dependency r7) {
        int r0 = AnonymousClass1.$SwitchMap$androidx$constraintlayout$core$widgets$analyzer$WidgetRun$RunType[this.mRunType.ordinal()];
        if (r0 == 1) goto L10;
        if (r0 == 2) goto L9;
        if (r0 != 3) goto L11;
        ConstraintWidget r02 = this.mWidget;
        updateRunCenter(r7, r02.mTop, r02.mBottom, 1);
        return;
    L11:
        DimensionDependency r72 = this.mDimension;
        if (r72.readyToSolve == true) goto L14;
    L39:
        DependencyNode r73 = this.start;
        if (r73.readyToSolve == false) goto L87;
        DependencyNode r03 = this.end;
        if (r03.readyToSolve == true) goto L45;
        return;
    L45:
        if (r73.resolved == false) goto L52;
        if (r03.resolved == false) goto L52;
        if (this.mDimension.resolved == false) goto L52;
        return;
    L52:
        if (this.mDimension.resolved == true) goto L62;
        if (this.mDimensionBehavior != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) goto L62;
        ConstraintWidget r74 = this.mWidget;
        if (r74.mMatchConstraintDefaultWidth != 0) goto L62;
        if (r74.isInVerticalChain() == true) goto L62;
        DependencyNode r75 = this.start.mTargets.get(0);
        DependencyNode r04 = this.end.mTargets.get(0);
        int r76 = r75.value;
        DependencyNode r1 = this.start;
        int r77 = r76 + r1.mMargin;
        int r05 = r04.value + this.end.mMargin;
        r1.resolve(r77);
        this.end.resolve(r05);
        this.mDimension.resolve(r05 - r77);
        return;
    L62:
        if (this.mDimension.resolved == true) goto L76;
        if (this.mDimensionBehavior != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) goto L76;
        if (this.matchConstraintsType != 1) goto L76;
        if (this.start.mTargets.size() <= 0) goto L76;
        if (this.end.mTargets.size() <= 0) goto L76;
        DependencyNode r78 = this.start.mTargets.get(0);
        DependencyNode r06 = this.end.mTargets.get(0);
        int r07 = (r06.value + this.end.mMargin) - (r78.value + this.start.mMargin);
        DimensionDependency r79 = this.mDimension;
        int r12 = r79.wrapValue;
        if (r07 >= r12) goto L74;
        r79.resolve(r07);
        goto L76
    L74:
        r79.resolve(r12);
    L76:
        if (this.mDimension.resolved == true) goto L79;
        return;
    L79:
        if (this.start.mTargets.size() > 0) goto L81;
        return;
    L81:
        if (this.end.mTargets.size() <= 0) goto L89;
        DependencyNode r710 = this.start.mTargets.get(0);
        DependencyNode r08 = this.end.mTargets.get(0);
        int r13 = r710.value + this.start.mMargin;
        int r2 = r08.value + this.end.mMargin;
        float r3 = this.mWidget.getVerticalBiasPercent();
        if (r710 != r08) goto L85;
        r13 = r710.value;
        r2 = r08.value;
        r3 = 0.5f;
    L85:
        this.start.resolve((int) ((r13 + 0.5f) + (((r2 - r13) - this.mDimension.value) * r3)));
        this.end.resolve(this.start.value + this.mDimension.value);
        return;
    L89:
        return;
    L87:
        return;
    L14:
        if (r72.resolved == true) goto L39;
        if (this.mDimensionBehavior != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) goto L39;
        ConstraintWidget r711 = this.mWidget;
        int r09 = r711.mMatchConstraintDefaultHeight;
        if (r09 == 2) goto L34;
        if (r09 != 3) goto L39;
        if (r711.mHorizontalRun.mDimension.resolved == false) goto L39;
        int r712 = r711.getDimensionRatioSide();
        if (r712 == (-1)) goto L32;
        if (r712 == 0) goto L31;
        if (r712 == 1) goto L28;
        int r713 = 0;
    L33:
        this.mDimension.resolve(r713);
        goto L39
    L28:
        ConstraintWidget r714 = this.mWidget;
        float r010 = r714.mHorizontalRun.mDimension.value;
        float r715 = r714.getDimensionRatio();
    L29:
        float r011 = r010 / r715;
    L30:
        r713 = (int) (r011 + 0.5f);
        goto L33
    L31:
        r011 = r7.mHorizontalRun.mDimension.value * this.mWidget.getDimensionRatio();
        goto L30
    L32:
        ConstraintWidget r716 = this.mWidget;
        r010 = r716.mHorizontalRun.mDimension.value;
        r715 = r716.getDimensionRatio();
        goto L29
    L34:
        ConstraintWidget r717 = r711.getParent();
        if (r717 == null) goto L39;
        if (r717.mVerticalRun.mDimension.resolved == false) goto L39;
        this.mDimension.resolve((int) ((r7.value * this.mWidget.mMatchConstraintPercentHeight) + 0.5f));
        goto L39
    L9:
        updateRunEnd(r7);
        goto L11
    L10:
        updateRunStart(r7);
        goto L11
    }
}
