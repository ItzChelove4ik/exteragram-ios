package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;

/* loaded from: classes3.dex */
public abstract class WidgetRun implements Dependency {
    public DependencyNode end;
    DimensionDependency mDimension;
    protected ConstraintWidget.DimensionBehaviour mDimensionBehavior;
    boolean mResolved;
    RunGroup mRunGroup;
    protected RunType mRunType;
    ConstraintWidget mWidget;
    public int matchConstraintsType;
    public int orientation;
    public DependencyNode start;

    /* renamed from: androidx.constraintlayout.core.widgets.analyzer.WidgetRun$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintAnchor$Type = null;

        static {
            int[] r0 = new int[ConstraintAnchor.Type.values().length];
            $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintAnchor$Type = r0;
            r0[ConstraintAnchor.Type.LEFT.ordinal()] = 1;     // Catch: NoSuchFieldError -> L9
        L14:
            $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.RIGHT.ordinal()] = 2;     // Catch: NoSuchFieldError -> L10
        L18:
            $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.TOP.ordinal()] = 3;     // Catch: NoSuchFieldError -> L11
        L22:
            $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.BASELINE.ordinal()] = 4;     // Catch: NoSuchFieldError -> L12
        L16:
            $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.BOTTOM.ordinal()] = 5;     // Catch: NoSuchFieldError -> L13
            return;
        }
    }

    public enum RunType extends Enum<RunType> {
        private static final /* synthetic */ RunType[] $VALUES = null;
        public static final RunType CENTER = null;
        public static final RunType END = null;
        public static final RunType NONE = null;
        public static final RunType START = null;

        private static /* synthetic */ RunType[] $values() {
            return new RunType[]{NONE, START, END, CENTER};
        }

        static {
            NONE = new RunType("NONE", 0);
            START = new RunType("START", 1);
            END = new RunType("END", 2);
            CENTER = new RunType("CENTER", 3);
            $VALUES = $values();
        }

        RunType(String r1, int r2) {
        }

        public static RunType valueOf(String r1) {
            return (RunType) Enum.valueOf(RunType.class, r1);
        }

        public static RunType[] values() {
            return (RunType[]) $VALUES.clone();
        }
    }

    public WidgetRun(ConstraintWidget r2) {
        this.mDimension = new DimensionDependency(this);
        this.orientation = 0;
        this.mResolved = false;
        this.start = new DependencyNode(this);
        this.end = new DependencyNode(this);
        this.mRunType = RunType.NONE;
        this.mWidget = r2;
    }

    private void resolveDimension(int r8, int r9) {
        int r0 = this.matchConstraintsType;
        if (r0 != 0) goto L5;
        this.mDimension.resolve(getLimitedDimension(r9, r8));
        return;
    L5:
        if (r0 != 1) goto L7;
        this.mDimension.resolve(Math.min(getLimitedDimension(this.mDimension.wrapValue, r8), r9));
        return;
    L7:
        if (r0 != 2) goto L9;
        ConstraintWidget r92 = this.mWidget.getParent();
        if (r92 == null) goto L48;
        if (r8 != 0) goto L34;
        WidgetRun r93 = r92.mHorizontalRun;
    L36:
        if (r93.mDimension.resolved == false) goto L49;
        ConstraintWidget r02 = this.mWidget;
        if (r8 != 0) goto L40;
        float r03 = r02.mMatchConstraintPercentWidth;
    L41:
        this.mDimension.resolve(getLimitedDimension((int) ((r9.value * r03) + 0.5f), r8));
        return;
    L40:
        r03 = r02.mMatchConstraintPercentHeight;
        goto L41
    L49:
        return;
    L34:
        r93 = r92.mVerticalRun;
        goto L36
    L48:
        return;
    L9:
        if (r0 != 3) goto L50;
        ConstraintWidget r04 = this.mWidget;
        WidgetRun r3 = r04.mHorizontalRun;
        ConstraintWidget.DimensionBehaviour r4 = r3.mDimensionBehavior;
        ConstraintWidget.DimensionBehaviour r5 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        if (r4 == r5) goto L14;
    L20:
        if (r8 != 0) goto L23;
        r3 = r04.mVerticalRun;
    L23:
        if (r3.mDimension.resolved == false) goto L47;
        float r94 = r04.getDimensionRatio();
        DimensionDependency r05 = r3.mDimension;
        if (r8 != 1) goto L27;
        int r82 = (int) ((r05.value / r94) + 0.5f);
    L28:
        this.mDimension.resolve(r82);
        return;
    L27:
        r82 = (int) ((r94 * r05.value) + 0.5f);
        goto L28
    L47:
        return;
    L14:
        if (r3.matchConstraintsType != 3) goto L20;
        VerticalWidgetRun r42 = r04.mVerticalRun;
        if (r42.mDimensionBehavior != r5) goto L20;
        if (r42.matchConstraintsType != 3) goto L20;
        return;
    }

    public final void addTarget(DependencyNode r1, DependencyNode r2, int r3) {
        r1.mTargets.add(r2);
        r1.mMargin = r3;
        r2.mDependencies.add(r1);
    }

    public final void addTarget(DependencyNode r2, DependencyNode r3, int r4, DimensionDependency r5) {
        r2.mTargets.add(r3);
        r2.mTargets.add(this.mDimension);
        r2.mMarginFactor = r4;
        r2.mMarginDependency = r5;
        r3.mDependencies.add(r2);
        r5.mDependencies.add(r2);
    }

    public abstract void apply();

    public abstract void applyToWidget();

    public abstract void clear();

    public final int getLimitedDimension(int r1, int r2) {
        ConstraintWidget r0 = this.mWidget;
        if (r2 != 0) goto L9;
        int r22 = r0.mMatchConstraintMaxWidth;
        int r02 = Math.max(r0.mMatchConstraintMinWidth, r1);
        if (r22 <= 0) goto L7;
        r02 = Math.min(r22, r1);
    L7:
        if (r02 == r1) goto L14;
        return r02;
    L14:
        return r1;
    L9:
        int r23 = r0.mMatchConstraintMaxHeight;
        int r03 = Math.max(r0.mMatchConstraintMinHeight, r1);
        if (r23 <= 0) goto L12;
        r03 = Math.min(r23, r1);
    L12:
        if (r03 == r1) goto L14;
        return r03;
    }

    public final DependencyNode getTarget(ConstraintAnchor r3) {
        ConstraintAnchor r2 = r3.mTarget;
        if (r2 != null) goto L5;
        return null;
    L5:
        ConstraintWidget r0 = r2.mOwner;
        ConstraintAnchor.Type r22 = r2.mType;
        int r23 = AnonymousClass1.$SwitchMap$androidx$constraintlayout$core$widgets$ConstraintAnchor$Type[r22.ordinal()];
        if (r23 == 1) goto L25;
        if (r23 == 2) goto L23;
        if (r23 == 3) goto L21;
        if (r23 == 4) goto L19;
        if (r23 == 5) goto L17;
        return null;
    L17:
        return r0.mVerticalRun.end;
    L19:
        return r0.mVerticalRun.baseline;
    L21:
        return r0.mVerticalRun.start;
    L23:
        return r0.mHorizontalRun.end;
    L25:
        return r0.mHorizontalRun.start;
    }

    public final DependencyNode getTarget(ConstraintAnchor r2, int r3) {
        ConstraintAnchor r1 = r2.mTarget;
        if (r1 != null) goto L5;
        return null;
    L5:
        ConstraintWidget r0 = r1.mOwner;
        if (r3 != 0) goto L8;
        WidgetRun r32 = r0.mHorizontalRun;
    L9:
        ConstraintAnchor.Type r12 = r1.mType;
        int r13 = AnonymousClass1.$SwitchMap$androidx$constraintlayout$core$widgets$ConstraintAnchor$Type[r12.ordinal()];
        if (r13 == 1) goto L21;
        if (r13 == 2) goto L19;
        if (r13 == 3) goto L21;
        if (r13 == 5) goto L19;
        return null;
    L19:
        return r32.end;
    L21:
        return r32.start;
    L8:
        r32 = r0.mVerticalRun;
        goto L9
    }

    public long getWrapDimension() {
        if (this.mDimension.resolved == true) goto L5;
        return 0;
    L5:
        return r2.value;
    }

    public boolean isResolved() {
        return this.mResolved;
    }

    public abstract boolean supportsWrapComputation();

    @Override // androidx.constraintlayout.core.widgets.analyzer.Dependency
    public abstract void update(Dependency r1);

    public void updateRunCenter(Dependency r5, ConstraintAnchor r6, ConstraintAnchor r7, int r8) {
        DependencyNode r52 = getTarget(r6);
        DependencyNode r0 = getTarget(r7);
        if (r52.resolved == true) goto L5;
        return;
    L5:
        if (r0.resolved == false) goto L29;
        int r1 = r52.value + r6.getMargin();
        int r62 = r0.value - r7.getMargin();
        int r72 = r62 - r1;
        if (this.mDimension.resolved == false) goto L10;
    L12:
        DimensionDependency r2 = this.mDimension;
        if (r2.resolved == true) goto L16;
        return;
    L16:
        if (r2.value != r72) goto L19;
        this.start.resolve(r1);
        this.end.resolve(r62);
        return;
    L19:
        ConstraintWidget r73 = this.mWidget;
        if (r8 != 0) goto L22;
        float r74 = r73.getHorizontalBiasPercent();
    L24:
        if (r52 != r0) goto L26;
        r1 = r52.value;
        r62 = r0.value;
        r74 = 0.5f;
    L26:
        this.start.resolve((int) ((r1 + 0.5f) + (((r62 - r1) - this.mDimension.value) * r74)));
        this.end.resolve(this.start.value + this.mDimension.value);
        return;
    L22:
        r74 = r73.getVerticalBiasPercent();
        goto L24
    L10:
        if (this.mDimensionBehavior != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) goto L12;
        resolveDimension(r8, r72);
        goto L12
    }

    public void updateRunEnd(Dependency r1) {
    }

    public void updateRunStart(Dependency r1) {
    }
}
