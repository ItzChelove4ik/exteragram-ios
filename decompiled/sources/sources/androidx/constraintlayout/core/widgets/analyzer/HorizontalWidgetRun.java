package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.Helper;
import androidx.constraintlayout.core.widgets.analyzer.DependencyNode;
import androidx.constraintlayout.core.widgets.analyzer.WidgetRun;

/* loaded from: classes3.dex */
public class HorizontalWidgetRun extends WidgetRun {
    private static int[] sTempDimensions;

    /* renamed from: androidx.constraintlayout.core.widgets.analyzer.HorizontalWidgetRun$1, reason: invalid class name */
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

    static {
        sTempDimensions = new int[2];
    }

    public HorizontalWidgetRun(ConstraintWidget r2) {
        super(r2);
        DependencyNode r22 = this.start;
        r22.mType = DependencyNode.Type.LEFT;
        DependencyNode r23 = this.end;
        r23.mType = DependencyNode.Type.RIGHT;
        this.orientation = 0;
    }

    private void computeInsetRatio(int[] r2, int r3, int r4, int r5, int r6, float r7, int r8) {
        int r42 = r4 - r3;
        int r62 = r6 - r5;
        if (r8 == (-1)) goto L11;
        if (r8 == 0) goto L9;
        if (r8 != 1) goto L19;
        r2[0] = r42;
        r2[1] = (int) ((r42 * r7) + 0.5f);
        return;
    L19:
        return;
    L9:
        r2[0] = (int) ((r62 * r7) + 0.5f);
        r2[1] = r62;
        return;
    L11:
        int r1 = (int) ((r62 * r7) + 0.5f);
        int r52 = (int) ((r42 / r7) + 0.5f);
        if (r1 > r42) goto L15;
        r2[0] = r1;
        r2[1] = r62;
        return;
    L15:
        if (r52 > r62) goto L18;
        r2[0] = r42;
        r2[1] = r52;
        return;
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public void apply() {
        ConstraintWidget r0 = this.mWidget;
        if (r0.measured == false) goto L6;
        this.mDimension.resolve(r0.getWidth());
    L6:
        if (this.mDimension.resolved == true) goto L22;
        ConstraintWidget.DimensionBehaviour r02 = this.mWidget.getHorizontalDimensionBehaviour();
        this.mDimensionBehavior = r02;
        if (r02 == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) goto L32;
        ConstraintWidget.DimensionBehaviour r1 = ConstraintWidget.DimensionBehaviour.MATCH_PARENT;
        if (r02 != r1) goto L20;
        ConstraintWidget r03 = this.mWidget.getParent();
        if (r03 == null) goto L20;
        if (r03.getHorizontalDimensionBehaviour() != ConstraintWidget.DimensionBehaviour.FIXED) goto L16;
    L17:
        int r12 = (r03.getWidth() - this.mWidget.mLeft.getMargin()) - this.mWidget.mRight.getMargin();
        addTarget(this.start, r03.mHorizontalRun.start, this.mWidget.mLeft.getMargin());
        addTarget(this.end, r03.mHorizontalRun.end, -this.mWidget.mRight.getMargin());
        this.mDimension.resolve(r12);
        return;
    L16:
        if (r03.getHorizontalDimensionBehaviour() == r1) goto L17;
    L20:
        if (this.mDimensionBehavior != ConstraintWidget.DimensionBehaviour.FIXED) goto L32;
        this.mDimension.resolve(this.mWidget.getWidth());
    L32:
        DimensionDependency r04 = this.mDimension;
        if (r04.resolved == false) goto L72;
        ConstraintWidget r13 = this.mWidget;
        if (r13.measured == false) goto L72;
        ConstraintAnchor[] r05 = r13.mListAnchors;
        ConstraintAnchor r4 = r05[0];
        ConstraintAnchor r5 = r4.mTarget;
        if (r5 != null) goto L39;
    L52:
        if (r5 == null) goto L57;
        DependencyNode r06 = getTarget(r4);
        if (r06 == null) goto L124;
        addTarget(this.start, r06, this.mWidget.mListAnchors[0].getMargin());
        addTarget(this.end, this.start, this.mDimension.value);
        return;
    L124:
        return;
    L57:
        ConstraintAnchor r07 = r05[1];
        if (r07.mTarget == null) goto L64;
        DependencyNode r08 = getTarget(r07);
        if (r08 == null) goto L125;
        addTarget(this.end, r08, -this.mWidget.mListAnchors[1].getMargin());
        addTarget(this.start, this.end, -this.mDimension.value);
        return;
    L125:
        return;
    L64:
        if ((r13 instanceof Helper) == false) goto L66;
        return;
    L66:
        if (r13.getParent() != null) goto L68;
        return;
    L68:
        if (this.mWidget.getAnchor(ConstraintAnchor.Type.CENTER).mTarget != null) goto L128;
        DependencyNode r09 = this.mWidget.getParent().mHorizontalRun.start;
        addTarget(this.start, r09, this.mWidget.getX());
        addTarget(this.end, this.start, this.mDimension.value);
        return;
    L128:
        return;
    L39:
        if (r05[1].mTarget == null) goto L52;
        if (r13.isInHorizontalChain() == false) goto L44;
        DependencyNode r010 = this.start;
        r010.mMargin = this.mWidget.mListAnchors[0].getMargin();
        DependencyNode r011 = this.end;
        r011.mMargin = -this.mWidget.mListAnchors[1].getMargin();
        return;
    L44:
        DependencyNode r012 = getTarget(this.mWidget.mListAnchors[0]);
        if (r012 == null) goto L47;
        addTarget(this.start, r012, this.mWidget.mListAnchors[0].getMargin());
    L47:
        DependencyNode r013 = getTarget(this.mWidget.mListAnchors[1]);
        if (r013 == null) goto L50;
        addTarget(this.end, r013, -this.mWidget.mListAnchors[1].getMargin());
    L50:
        this.start.delegateToWidgetRun = true;
        this.end.delegateToWidgetRun = true;
        return;
    L72:
        if (this.mDimensionBehavior != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) goto L92;
        ConstraintWidget r14 = this.mWidget;
        int r42 = r14.mMatchConstraintDefaultWidth;
        if (r42 != 2) goto L76;
        ConstraintWidget r014 = r14.getParent();
        if (r014 == null) goto L92;
        DimensionDependency r015 = r014.mVerticalRun.mDimension;
        this.mDimension.mTargets.add(r015);
        r015.mDependencies.add(this.mDimension);
        DimensionDependency r016 = this.mDimension;
        r016.delegateToWidgetRun = true;
        r016.mDependencies.add(this.start);
        this.mDimension.mDependencies.add(this.end);
        goto L92
    L76:
        if (r42 != 3) goto L92;
        if (r14.mMatchConstraintDefaultHeight != 3) goto L87;
        this.start.updateDelegate = this;
        this.end.updateDelegate = this;
        VerticalWidgetRun r43 = r14.mVerticalRun;
        r43.start.updateDelegate = this;
        r43.end.updateDelegate = this;
        r04.updateDelegate = this;
        if (r14.isInVerticalChain() == false) goto L83;
        this.mDimension.mTargets.add(this.mWidget.mVerticalRun.mDimension);
        this.mWidget.mVerticalRun.mDimension.mDependencies.add(this.mDimension);
        VerticalWidgetRun r017 = this.mWidget.mVerticalRun;
        r017.mDimension.updateDelegate = this;
        this.mDimension.mTargets.add(r017.start);
        this.mDimension.mTargets.add(this.mWidget.mVerticalRun.end);
        this.mWidget.mVerticalRun.start.mDependencies.add(this.mDimension);
        this.mWidget.mVerticalRun.end.mDependencies.add(this.mDimension);
        goto L92
    L83:
        boolean r018 = this.mWidget.isInHorizontalChain();
        ConstraintWidget r15 = this.mWidget;
        if (r018 == false) goto L86;
        r15.mVerticalRun.mDimension.mTargets.add(this.mDimension);
        this.mDimension.mDependencies.add(this.mWidget.mVerticalRun.mDimension);
        goto L92
    L86:
        r15.mVerticalRun.mDimension.mTargets.add(this.mDimension);
        goto L92
    L87:
        DimensionDependency r16 = r14.mVerticalRun.mDimension;
        r04.mTargets.add(r16);
        r16.mDependencies.add(this.mDimension);
        this.mWidget.mVerticalRun.start.mDependencies.add(this.mDimension);
        this.mWidget.mVerticalRun.end.mDependencies.add(this.mDimension);
        DimensionDependency r019 = this.mDimension;
        r019.delegateToWidgetRun = true;
        r019.mDependencies.add(this.start);
        this.mDimension.mDependencies.add(this.end);
        this.start.mTargets.add(this.mDimension);
        this.end.mTargets.add(this.mDimension);
    L92:
        ConstraintWidget r020 = this.mWidget;
        ConstraintAnchor[] r17 = r020.mListAnchors;
        ConstraintAnchor r44 = r17[0];
        ConstraintAnchor r52 = r44.mTarget;
        if (r52 != null) goto L95;
    L107:
        if (r52 == null) goto L112;
        DependencyNode r021 = getTarget(r44);
        if (r021 == null) goto L129;
        addTarget(this.start, r021, this.mWidget.mListAnchors[0].getMargin());
        addTarget(this.end, this.start, 1, this.mDimension);
        return;
    L129:
        return;
    L112:
        ConstraintAnchor r18 = r17[1];
        if (r18.mTarget == null) goto L119;
        DependencyNode r022 = getTarget(r18);
        if (r022 == null) goto L130;
        addTarget(this.end, r022, -this.mWidget.mListAnchors[1].getMargin());
        addTarget(this.start, this.end, -1, this.mDimension);
        return;
    L130:
        return;
    L119:
        if ((r020 instanceof Helper) == false) goto L121;
        return;
    L121:
        if (r020.getParent() == null) goto L132;
        DependencyNode r023 = this.mWidget.getParent().mHorizontalRun.start;
        addTarget(this.start, r023, this.mWidget.getX());
        addTarget(this.end, this.start, 1, this.mDimension);
        return;
    L132:
        return;
    L95:
        if (r17[1].mTarget == null) goto L107;
        if (r020.isInHorizontalChain() == false) goto L100;
        DependencyNode r024 = this.start;
        r024.mMargin = this.mWidget.mListAnchors[0].getMargin();
        DependencyNode r025 = this.end;
        r025.mMargin = -this.mWidget.mListAnchors[1].getMargin();
        return;
    L100:
        DependencyNode r026 = getTarget(this.mWidget.mListAnchors[0]);
        DependencyNode r19 = getTarget(this.mWidget.mListAnchors[1]);
        if (r026 == null) goto L103;
        r026.addDependency(this);
    L103:
        if (r19 == null) goto L105;
        r19.addDependency(this);
    L105:
        this.mRunType = WidgetRun.RunType.CENTER;
        return;
    L22:
        ConstraintWidget.DimensionBehaviour r027 = this.mDimensionBehavior;
        ConstraintWidget.DimensionBehaviour r110 = ConstraintWidget.DimensionBehaviour.MATCH_PARENT;
        if (r027 != r110) goto L32;
        ConstraintWidget r028 = this.mWidget.getParent();
        if (r028 == null) goto L32;
        if (r028.getHorizontalDimensionBehaviour() != ConstraintWidget.DimensionBehaviour.FIXED) goto L29;
    L30:
        addTarget(this.start, r028.mHorizontalRun.start, this.mWidget.mLeft.getMargin());
        addTarget(this.end, r028.mHorizontalRun.end, -this.mWidget.mRight.getMargin());
        return;
    L29:
        if (r028.getHorizontalDimensionBehaviour() != r110) goto L32;
        goto L30
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public void applyToWidget() {
        DependencyNode r0 = this.start;
        if (r0.resolved == false) goto L6;
        this.mWidget.setX(r0.value);
        return;
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public void clear() {
        this.mRunGroup = null;
        this.start.clear();
        this.end.clear();
        this.mDimension.clear();
        this.mResolved = false;
    }

    public void reset() {
        this.mResolved = false;
        this.start.clear();
        this.start.resolved = false;
        this.end.clear();
        this.end.resolved = false;
        this.mDimension.resolved = false;
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public boolean supportsWrapComputation() {
        if (this.mDimensionBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) goto L5;
        return true;
    L5:
        if (this.mWidget.mMatchConstraintDefaultWidth != 0) goto L7;
        return true;
    L7:
        return false;
    }

    public String toString() {
        return "HorizontalRun " + this.mWidget.getDebugName();
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun, androidx.constraintlayout.core.widgets.analyzer.Dependency
    public void update(Dependency r14) {
        int r1 = AnonymousClass1.$SwitchMap$androidx$constraintlayout$core$widgets$analyzer$WidgetRun$RunType[this.mRunType.ordinal()];
        if (r1 == 1) goto L10;
        if (r1 == 2) goto L9;
        if (r1 != 3) goto L12;
        ConstraintWidget r12 = this.mWidget;
        updateRunCenter(r14, r12.mLeft, r12.mRight, 0);
        return;
    L12:
        if (this.mDimension.resolved == false) goto L14;
    L131:
        DependencyNode r13 = this.start;
        if (r13.readyToSolve == false) goto L182;
        DependencyNode r2 = this.end;
        if (r2.readyToSolve == true) goto L137;
        return;
    L137:
        if (r13.resolved == false) goto L144;
        if (r2.resolved == false) goto L144;
        if (this.mDimension.resolved == false) goto L144;
        return;
    L144:
        if (this.mDimension.resolved == true) goto L154;
        if (this.mDimensionBehavior != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) goto L154;
        ConstraintWidget r15 = this.mWidget;
        if (r15.mMatchConstraintDefaultWidth != 0) goto L154;
        if (r15.isInHorizontalChain() == true) goto L154;
        DependencyNode r16 = this.start.mTargets.get(0);
        DependencyNode r22 = this.end.mTargets.get(0);
        int r17 = r16.value;
        DependencyNode r3 = this.start;
        int r18 = r17 + r3.mMargin;
        int r23 = r22.value + this.end.mMargin;
        r3.resolve(r18);
        this.end.resolve(r23);
        this.mDimension.resolve(r23 - r18);
        return;
    L154:
        if (this.mDimension.resolved == true) goto L168;
        if (this.mDimensionBehavior != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) goto L168;
        if (this.matchConstraintsType != 1) goto L168;
        if (this.start.mTargets.size() <= 0) goto L168;
        if (this.end.mTargets.size() <= 0) goto L168;
        DependencyNode r19 = this.start.mTargets.get(0);
        DependencyNode r24 = this.end.mTargets.get(0);
        int r110 = Math.min((r24.value + this.end.mMargin) - (r19.value + this.start.mMargin), this.mDimension.wrapValue);
        ConstraintWidget r25 = this.mWidget;
        int r32 = r25.mMatchConstraintMaxWidth;
        int r111 = Math.max(r25.mMatchConstraintMinWidth, r110);
        if (r32 <= 0) goto L166;
        r111 = Math.min(r32, r111);
    L166:
        this.mDimension.resolve(r111);
    L168:
        if (this.mDimension.resolved == false) goto L190;
        DependencyNode r112 = this.start.mTargets.get(0);
        DependencyNode r26 = this.end.mTargets.get(0);
        int r33 = r112.value + this.start.mMargin;
        int r4 = r26.value + this.end.mMargin;
        float r5 = this.mWidget.getHorizontalBiasPercent();
        if (r112 != r26) goto L173;
        r33 = r112.value;
        r4 = r26.value;
        r5 = 0.5f;
    L173:
        this.start.resolve((int) ((r33 + 0.5f) + (((r4 - r33) - this.mDimension.value) * r5)));
        this.end.resolve(this.start.value + this.mDimension.value);
        return;
    L190:
        return;
    L182:
        return;
    L14:
        if (this.mDimensionBehavior != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) goto L131;
        ConstraintWidget r113 = this.mWidget;
        int r42 = r113.mMatchConstraintDefaultWidth;
        if (r42 == 2) goto L126;
        if (r42 != 3) goto L131;
        int r27 = r113.mMatchConstraintDefaultHeight;
        if (r27 == 0) goto L34;
        if (r27 == 3) goto L34;
        int r114 = r113.getDimensionRatioSide();
        if (r114 == (-1)) goto L32;
        if (r114 == 0) goto L31;
        if (r114 == 1) goto L28;
        int r115 = 0;
    L33:
        this.mDimension.resolve(r115);
        goto L131
    L28:
        ConstraintWidget r116 = this.mWidget;
        float r28 = r116.mVerticalRun.mDimension.value;
        float r117 = r116.getDimensionRatio();
    L29:
        float r29 = r28 * r117;
    L30:
        r115 = (int) (r29 + 0.5f);
        goto L33
    L31:
        r29 = r1.mVerticalRun.mDimension.value / this.mWidget.getDimensionRatio();
        goto L30
    L32:
        ConstraintWidget r118 = this.mWidget;
        r28 = r118.mVerticalRun.mDimension.value;
        r117 = r118.getDimensionRatio();
    L34:
        VerticalWidgetRun r210 = r113.mVerticalRun;
        DependencyNode r11 = r210.start;
        DependencyNode r122 = r210.end;
        if (r113.mLeft.mTarget == null) goto L37;
        boolean r211 = true;
    L39:
        if (r113.mTop.mTarget == null) goto L41;
        boolean r34 = true;
    L43:
        if (r113.mRight.mTarget == null) goto L45;
        boolean r52 = true;
    L47:
        if (r113.mBottom.mTarget == null) goto L49;
        boolean r6 = true;
    L50:
        int r7 = r113.getDimensionRatioSide();
        if (r211 == false) goto L86;
        if (r34 == false) goto L86;
        if (r52 == false) goto L86;
        if (r6 == false) goto L86;
        float r62 = this.mWidget.getDimensionRatio();
        if (r11.resolved == true) goto L58;
    L66:
        DependencyNode r119 = this.start;
        if (r119.resolved == false) goto L76;
        DependencyNode r212 = this.end;
        if (r212.resolved == false) goto L76;
        if (r11.readyToSolve == true) goto L73;
        return;
    L73:
        if (r122.readyToSolve == false) goto L184;
        computeInsetRatio(sTempDimensions, r119.value + r119.mMargin, r212.value - r212.mMargin, r11.mMargin + r11.mTargets.get(0).value, r122.mTargets.get(0).value - r122.mMargin, r62, r7);
        this.mDimension.resolve(sTempDimensions[0]);
        this.mWidget.mVerticalRun.mDimension.resolve(sTempDimensions[1]);
        goto L76
    L184:
        return;
    L76:
        DependencyNode r120 = this.start;
        if (r120.readyToSolve == true) goto L79;
        return;
    L79:
        if (this.end.readyToSolve == true) goto L81;
        return;
    L81:
        if (r11.readyToSolve == true) goto L83;
        return;
    L83:
        if (r122.readyToSolve == false) goto L185;
        computeInsetRatio(sTempDimensions, this.start.mMargin + r120.mTargets.get(0).value, this.end.mTargets.get(0).value - this.end.mMargin, r11.mMargin + r11.mTargets.get(0).value, r122.mTargets.get(0).value - r122.mMargin, r62, r7);
        this.mDimension.resolve(sTempDimensions[0]);
        this.mWidget.mVerticalRun.mDimension.resolve(sTempDimensions[1]);
        goto L131
    L185:
        return;
    L58:
        if (r122.resolved == false) goto L66;
        DependencyNode r121 = this.start;
        if (r121.readyToSolve == true) goto L62;
        return;
    L62:
        if (this.end.readyToSolve == false) goto L183;
        computeInsetRatio(sTempDimensions, this.start.mMargin + r121.mTargets.get(0).value, this.end.mTargets.get(0).value - this.end.mMargin, r11.mMargin + r11.value, r122.value - r122.mMargin, r62, r7);
        this.mDimension.resolve(sTempDimensions[0]);
        this.mWidget.mVerticalRun.mDimension.resolve(sTempDimensions[1]);
        return;
    L183:
        return;
    L86:
        if (r211 == false) goto L106;
        if (r52 == false) goto L106;
        if (this.start.readyToSolve == true) goto L91;
        return;
    L91:
        if (this.end.readyToSolve == false) goto L186;
        float r123 = this.mWidget.getDimensionRatio();
        int r213 = this.start.mTargets.get(0).value + this.start.mMargin;
        int r35 = this.end.mTargets.get(0).value - this.end.mMargin;
        if (r7 == (-1)) goto L102;
        if (r7 == 0) goto L102;
        if (r7 != 1) goto L131;
        int r214 = getLimitedDimension(r35 - r213, 0);
        int r36 = (int) ((r214 / r123) + 0.5f);
        int r43 = getLimitedDimension(r36, 1);
        if (r36 == r43) goto L101;
        r214 = (int) ((r43 * r123) + 0.5f);
    L101:
        this.mDimension.resolve(r214);
        this.mWidget.mVerticalRun.mDimension.resolve(r43);
    L102:
        int r215 = getLimitedDimension(r35 - r213, 0);
        int r37 = (int) ((r215 * r123) + 0.5f);
        int r44 = getLimitedDimension(r37, 1);
        if (r37 == r44) goto L105;
        r215 = (int) ((r44 / r123) + 0.5f);
    L105:
        this.mDimension.resolve(r215);
        this.mWidget.mVerticalRun.mDimension.resolve(r44);
        goto L131
    L186:
        return;
    L106:
        if (r34 == false) goto L131;
        if (r6 == false) goto L131;
        if (r11.readyToSolve == true) goto L111;
        return;
    L111:
        if (r122.readyToSolve == false) goto L187;
        float r124 = this.mWidget.getDimensionRatio();
        int r216 = r11.mTargets.get(0).value + r11.mMargin;
        int r38 = r122.mTargets.get(0).value - r122.mMargin;
        if (r7 == (-1)) goto L122;
        if (r7 == 0) goto L118;
        if (r7 == 1) goto L122;
    L118:
        int r217 = getLimitedDimension(r38 - r216, 1);
        int r39 = (int) ((r217 * r124) + 0.5f);
        int r45 = getLimitedDimension(r39, 0);
        if (r39 == r45) goto L121;
        r217 = (int) ((r45 / r124) + 0.5f);
    L121:
        this.mDimension.resolve(r45);
        this.mWidget.mVerticalRun.mDimension.resolve(r217);
    L122:
        int r218 = getLimitedDimension(r38 - r216, 1);
        int r310 = (int) ((r218 / r124) + 0.5f);
        int r46 = getLimitedDimension(r310, 0);
        if (r310 == r46) goto L125;
        r218 = (int) ((r46 * r124) + 0.5f);
    L125:
        this.mDimension.resolve(r46);
        this.mWidget.mVerticalRun.mDimension.resolve(r218);
        goto L131
    L187:
        return;
    L49:
        r6 = false;
        goto L50
    L45:
        r52 = false;
        goto L47
    L41:
        r34 = false;
        goto L43
    L37:
        r211 = false;
        goto L39
    L126:
        ConstraintWidget r125 = r113.getParent();
        if (r125 == null) goto L131;
        if (r125.mHorizontalRun.mDimension.resolved == false) goto L131;
        this.mDimension.resolve((int) ((r1.value * this.mWidget.mMatchConstraintPercentWidth) + 0.5f));
        goto L131
    L9:
        updateRunEnd(r14);
        goto L12
    L10:
        updateRunStart(r14);
        goto L12
    }
}
