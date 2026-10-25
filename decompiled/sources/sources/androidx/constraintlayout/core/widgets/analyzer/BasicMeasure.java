package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.LinearSystem;
import androidx.constraintlayout.core.widgets.Barrier;
import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import androidx.constraintlayout.core.widgets.Guideline;
import androidx.constraintlayout.core.widgets.Helper;
import androidx.constraintlayout.core.widgets.Optimizer;
import androidx.constraintlayout.core.widgets.VirtualLayout;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public class BasicMeasure {
    private ConstraintWidgetContainer mConstraintWidgetContainer;
    private Measure mMeasure;
    private final ArrayList<ConstraintWidget> mVariableDimensionsWidgets;

    public static class Measure {
        public static int SELF_DIMENSIONS = 0;
        public static int TRY_GIVEN_DIMENSIONS = 1;
        public static int USE_GIVEN_DIMENSIONS = 2;
        public ConstraintWidget.DimensionBehaviour horizontalBehavior;
        public int horizontalDimension;
        public int measureStrategy;
        public int measuredBaseline;
        public boolean measuredHasBaseline;
        public int measuredHeight;
        public boolean measuredNeedsSolverPass;
        public int measuredWidth;
        public ConstraintWidget.DimensionBehaviour verticalBehavior;
        public int verticalDimension;

        static {
        }

        public Measure() {
        }
    }

    public interface Measurer {
        void didMeasures();

        void measure(ConstraintWidget r1, Measure r2);
    }

    public BasicMeasure(ConstraintWidgetContainer r2) {
        this.mVariableDimensionsWidgets = new ArrayList();
        this.mMeasure = new Measure();
        this.mConstraintWidgetContainer = r2;
    }

    private boolean measure(Measurer r6, ConstraintWidget r7, int r8) {
        this.mMeasure.horizontalBehavior = r7.getHorizontalDimensionBehaviour();
        this.mMeasure.verticalBehavior = r7.getVerticalDimensionBehaviour();
        this.mMeasure.horizontalDimension = r7.getWidth();
        this.mMeasure.verticalDimension = r7.getHeight();
        Measure r0 = this.mMeasure;
        r0.measuredNeedsSolverPass = false;
        r0.measureStrategy = r8;
        ConstraintWidget.DimensionBehaviour r82 = r0.horizontalBehavior;
        ConstraintWidget.DimensionBehaviour r2 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        if (r82 != r2) goto L5;
        boolean r83 = true;
    L7:
        if (r0.verticalBehavior != r2) goto L9;
        boolean r22 = true;
    L11:
        if (r83 == true) goto L13;
    L15:
        boolean r84 = false;
    L16:
        if (r22 == true) goto L18;
    L20:
        boolean r23 = false;
    L22:
        if (r84 == true) goto L24;
    L26:
        if (r23 == true) goto L28;
    L30:
        r6.measure(r7, r0);
        r7.setWidth(this.mMeasure.measuredWidth);
        r7.setHeight(this.mMeasure.measuredHeight);
        r7.setHasBaseline(this.mMeasure.measuredHasBaseline);
        r7.setBaselineDistance(this.mMeasure.measuredBaseline);
        Measure r5 = this.mMeasure;
        r5.measureStrategy = Measure.SELF_DIMENSIONS;
        return r5.measuredNeedsSolverPass;
    L28:
        if (r7.mResolvedMatchConstraintDefault[1] != 4) goto L30;
        r0.verticalBehavior = ConstraintWidget.DimensionBehaviour.FIXED;
        goto L30
    L24:
        if (r7.mResolvedMatchConstraintDefault[0] != 4) goto L26;
        r0.horizontalBehavior = ConstraintWidget.DimensionBehaviour.FIXED;
        goto L26
    L18:
        if (r7.mDimensionRatio <= 0.0f) goto L20;
        r23 = true;
        goto L22
    L13:
        if (r7.mDimensionRatio <= 0.0f) goto L15;
        r84 = true;
        goto L16
    L9:
        r22 = false;
        goto L11
    L5:
        r83 = false;
        goto L7
    }

    private void measureChildren(ConstraintWidgetContainer r13) {
        int r0 = r13.mChildren.size();
        boolean r1 = r13.optimizeFor(64);
        Measurer r2 = r13.getMeasurer();
        int r4 = 0;
    L3:
        if (r4 >= r0) goto L61;
        ConstraintWidget r5 = r13.mChildren.get(r4);
        if ((r5 instanceof Guideline) == true) goto L60;
        if ((r5 instanceof Barrier) == true) goto L60;
        if (r5.isInVirtualLayout() == true) goto L60;
        if (r1 == false) goto L23;
        HorizontalWidgetRun r6 = r5.mHorizontalRun;
        if (r6 == null) goto L23;
        VerticalWidgetRun r7 = r5.mVerticalRun;
        if (r7 == null) goto L23;
        if (r6.mDimension.resolved == false) goto L23;
        if (r7.mDimension.resolved == true) goto L60;
    L23:
        ConstraintWidget.DimensionBehaviour r62 = r5.getDimensionBehaviour(0);
        boolean r72 = true;
        ConstraintWidget.DimensionBehaviour r8 = r5.getDimensionBehaviour(1);
        ConstraintWidget.DimensionBehaviour r9 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        if (r62 == r9) goto L26;
    L31:
        boolean r10 = false;
    L32:
        if (r10 == false) goto L34;
    L56:
        r72 = r10;
    L57:
        if (r72 == true) goto L60;
        measure(r2, r5, Measure.SELF_DIMENSIONS);
        goto L60
    L34:
        if (r13.optimizeFor(1) == false) goto L56;
        if ((r5 instanceof VirtualLayout) == true) goto L56;
        if (r62 == r9) goto L39;
    L44:
        if (r8 == r9) goto L46;
    L51:
        if (r62 == r9) goto L54;
        if (r8 != r9) goto L56;
    L54:
        if (r5.mDimensionRatio <= 0.0f) goto L56;
    L46:
        if (r5.mMatchConstraintDefaultHeight != 0) goto L51;
        if (r62 == r9) goto L51;
        if (r5.isInHorizontalChain() == true) goto L51;
        r10 = true;
        goto L51
    L39:
        if (r5.mMatchConstraintDefaultWidth != 0) goto L44;
        if (r8 == r9) goto L44;
        if (r5.isInHorizontalChain() == true) goto L44;
        r10 = true;
        goto L44
    L26:
        if (r5.mMatchConstraintDefaultWidth == 1) goto L31;
        if (r8 != r9) goto L31;
        if (r5.mMatchConstraintDefaultHeight == 1) goto L31;
        r10 = true;
    L60:
        r4 = r4 + 1;
        goto L3
    L61:
        r2.didMeasures();
    }

    private void solveLinearSystem(ConstraintWidgetContainer r3, String r4, int r5, int r6, int r7) {
        r3.getClass();
        int r42 = r3.getMinWidth();
        int r0 = r3.getMinHeight();
        r3.setMinWidth(0);
        r3.setMinHeight(0);
        r3.setWidth(r6);
        r3.setHeight(r7);
        r3.setMinWidth(r42);
        r3.setMinHeight(r0);
        this.mConstraintWidgetContainer.setPass(r5);
        this.mConstraintWidgetContainer.layout();
    }

    public long solverMeasure(ConstraintWidgetContainer r19, int r20, int r21, int r22, int r23, int r24, int r25, int r26, int r27, int r28) {
        BasicMeasure r0 = this;
        Measurer r5 = r19.getMeasurer();
        int r6 = r19.mChildren.size();
        int r7 = r19.getWidth();
        int r8 = r19.getHeight();
        boolean r9 = Optimizer.enabled(r20, 128);
        if (r9 == false) goto L5;
    L8:
        boolean r2 = true;
    L9:
        if (r2 == false) goto L43;
        int r12 = 0;
    L11:
        if (r12 >= r6) goto L43;
        ConstraintWidget r13 = r19.mChildren.get(r12);
        ConstraintWidget.DimensionBehaviour r14 = r13.getHorizontalDimensionBehaviour();
        ConstraintWidget.DimensionBehaviour r15 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        if (r14 != r15) goto L15;
        boolean r142 = true;
    L17:
        if (r13.getVerticalDimensionBehaviour() != r15) goto L19;
        boolean r10 = true;
    L20:
        if (r142 == false) goto L25;
        if (r10 == false) goto L25;
        if (r13.getDimensionRatio() <= 0.0f) goto L25;
        boolean r102 = true;
    L27:
        if (r13.isInHorizontalChain() == false) goto L31;
        if (r102 == false) goto L31;
    L29:
        r2 = false;
    L31:
        if (r13.isInVerticalChain() == false) goto L35;
        if (r102 == true) goto L29;
    L35:
        if ((r13 instanceof VirtualLayout) == true) goto L29;
        if (r13.isInHorizontalChain() == true) goto L29;
        if (r13.isInVerticalChain() == true) goto L29;
        r12 = r12 + 1;
    L25:
        r102 = false;
        goto L27
    L19:
        r10 = false;
        goto L20
    L15:
        r142 = false;
    L43:
        if (r2 == false) goto L46;
        boolean r103 = LinearSystem.USE_DEPENDENCY_ORDERING;
    L46:
        if (r23 != 1073741824) goto L48;
        if (r25 != 1073741824) goto L48;
    L49:
        boolean r122 = true;
    L51:
        boolean r29 = r2 & r122;
        int r123 = 2;
        if (r29 == false) goto L80;
        int r132 = Math.min(r19.getMaxWidth(), r24);
        int r143 = Math.min(r19.getMaxHeight(), r26);
        if (r23 == 1073741824) goto L56;
    L58:
        if (r25 == 1073741824) goto L60;
    L62:
        if (r23 != 1073741824) goto L65;
        if (r25 != 1073741824) goto L65;
        boolean r92 = r19.directMeasure(r9);
        int r144 = 2;
    L72:
        if (r92 == false) goto L81;
        if (r23 != 1073741824) goto L75;
        boolean r152 = true;
    L76:
        if (r25 != 1073741824) goto L78;
        boolean r3 = true;
    L79:
        r19.updateFromRuns(r152, r3);
        goto L81
    L78:
        r3 = false;
        goto L79
    L75:
        r152 = false;
    L81:
        if (r92 == false) goto L83;
        if (r144 != 2) goto L83;
        return 0;
    L83:
        int r32 = r19.getOptimizationLevel();
        if (r6 <= 0) goto L86;
        measureChildren(r19);
    L86:
        updateHierarchy(r19);
        int r4 = r0.mVariableDimensionsWidgets.size();
        if (r6 <= 0) goto L89;
        r0.solveLinearSystem(r19, "First pass", 0, r7, r8);
        int r1 = r7;
        int r62 = r8;
    L90:
        if (r4 <= 0) goto L173;
        ConstraintWidget.DimensionBehaviour r72 = r19.getHorizontalDimensionBehaviour();
        ConstraintWidget.DimensionBehaviour r82 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (r72 != r82) goto L94;
        boolean r153 = true;
    L96:
        if (r19.getVerticalDimensionBehaviour() != r82) goto L98;
        boolean r73 = true;
    L99:
        int r83 = Math.max(r19.getWidth(), r0.mConstraintWidgetContainer.getMinWidth());
        int r93 = Math.max(r19.getHeight(), r0.mConstraintWidgetContainer.getMinHeight());
        int r104 = 0;
        boolean r133 = false;
    L100:
        if (r104 >= r4) goto L122;
        ConstraintWidget r145 = r0.mVariableDimensionsWidgets.get(r104);
        if ((r145 instanceof VirtualLayout) == true) goto L104;
        int r242 = r1;
    L121:
        r104 = r104 + 1;
        r1 = r242;
        r123 = 2;
        goto L100
    L104:
        int r11 = r145.getWidth();
        int r124 = r145.getHeight();
        r242 = r1;
        boolean r16 = r0.measure(r5, r145, Measure.TRY_GIVEN_DIMENSIONS) | r133;
        int r134 = r145.getWidth();
        int r17 = r145.getHeight();
        if (r134 == r11) goto L112;
        r145.setWidth(r134);
        if (r153 == true) goto L109;
    L111:
        boolean r112 = true;
    L113:
        if (r17 == r124) goto L120;
        r145.setHeight(r17);
        if (r73 == true) goto L117;
    L119:
        r112 = true;
        goto L120
    L117:
        if (r145.getBottom() <= r93) goto L119;
        r93 = Math.max(r93, r145.getBottom() + r145.getAnchor(ConstraintAnchor.Type.BOTTOM).getMargin());
    L120:
        r133 = r112 | ((VirtualLayout) r145).needSolverPass();
        goto L121
    L109:
        if (r145.getRight() <= r83) goto L111;
        r83 = Math.max(r83, r145.getRight() + r145.getAnchor(ConstraintAnchor.Type.RIGHT).getMargin());
        goto L111
    L112:
        r112 = r16;
        goto L113
    L122:
        int r243 = r1;
        int r105 = r123;
        int r18 = 0;
    L123:
        if (r18 >= r105) goto L173;
        int r113 = 0;
    L125:
        if (r113 >= r4) goto L170;
        ConstraintWidget r125 = r0.mVariableDimensionsWidgets.get(r113);
        if ((r125 instanceof Helper) == false) goto L131;
        if ((r125 instanceof VirtualLayout) == true) goto L131;
    L144:
        boolean r162 = r29;
        int r262 = r4;
    L169:
        r113 = r113 + 1;
        r0 = this;
        r4 = r262;
        r29 = r162;
    L131:
        if ((r125 instanceof Guideline) == true) goto L144;
        if (r125.getVisibility() == 8) goto L144;
        if (r29 == false) goto L143;
        if (r125.mHorizontalRun.mDimension.resolved == false) goto L143;
        if (r125.mVerticalRun.mDimension.resolved == true) goto L144;
    L143:
        if ((r125 instanceof VirtualLayout) == true) goto L144;
        int r106 = r125.getWidth();
        int r146 = r125.getHeight();
        r162 = r29;
        int r210 = r125.getBaselineDistance();
        int r172 = Measure.TRY_GIVEN_DIMENSIONS;
        r262 = r4;
        if (r18 != 1) goto L148;
        r172 = Measure.USE_GIVEN_DIMENSIONS;
    L148:
        boolean r42 = r0.measure(r5, r125, r172) | r133;
        int r135 = r125.getWidth();
        int r02 = r125.getHeight();
        if (r135 == r106) goto L156;
        r125.setWidth(r135);
        if (r153 == true) goto L153;
    L155:
        r42 = true;
        goto L156
    L153:
        if (r125.getRight() <= r83) goto L155;
        r83 = Math.max(r83, r125.getRight() + r125.getAnchor(ConstraintAnchor.Type.RIGHT).getMargin());
    L156:
        if (r02 == r146) goto L164;
        r125.setHeight(r02);
        if (r73 == true) goto L160;
    L162:
        r42 = true;
        goto L164
    L160:
        if (r125.getBottom() <= r93) goto L162;
        r93 = Math.max(r93, r125.getBottom() + r125.getAnchor(ConstraintAnchor.Type.BOTTOM).getMargin());
    L164:
        if (r125.hasBaseline() == true) goto L166;
    L168:
        r133 = r42;
        goto L169
    L166:
        if (r210 == r125.getBaselineDistance()) goto L168;
        r133 = true;
        goto L169
    L170:
        boolean r163 = r29;
        int r263 = r4;
        if (r133 == false) goto L173;
        r18 = r18 + 1;
        solveLinearSystem(r19, "intermediate pass", r18, r243, r62);
        r0 = this;
        r4 = r263;
        r29 = r163;
        r105 = 2;
        r133 = false;
        goto L123
    L98:
        r73 = false;
        goto L99
    L94:
        r153 = false;
    L173:
        r19.setOptimizationLevel(r32);
        return 0;
    L89:
        r1 = r7;
        r62 = r8;
    L65:
        boolean r136 = r19.directMeasureSetup(r9);
        if (r23 != 1073741824) goto L68;
        r136 = r136 & r19.directMeasureWithOrientation(r9, 0);
        r144 = 1;
    L69:
        if (r25 != 1073741824) goto L71;
        r92 = r19.directMeasureWithOrientation(r9, 1) & r136;
        r144 = r144 + 1;
        goto L72
    L71:
        r92 = r136;
        goto L72
    L68:
        r144 = 0;
        goto L69
    L60:
        if (r19.getHeight() == r143) goto L62;
        r19.setHeight(r143);
        r19.invalidateGraph();
        goto L62
    L56:
        if (r19.getWidth() == r132) goto L58;
        r19.setWidth(r132);
        r19.invalidateGraph();
        goto L58
    L80:
        r92 = false;
        r144 = 0;
    L48:
        if (r9 == true) goto L49;
        r122 = false;
        goto L51
    L5:
        if (Optimizer.enabled(r20, 64) == true) goto L8;
        r2 = false;
        goto L9
    }

    public void updateHierarchy(ConstraintWidgetContainer r6) {
        this.mVariableDimensionsWidgets.clear();
        int r0 = r6.mChildren.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L10;
        ConstraintWidget r2 = r6.mChildren.get(r1);
        ConstraintWidget.DimensionBehaviour r3 = r2.getHorizontalDimensionBehaviour();
        ConstraintWidget.DimensionBehaviour r4 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        if (r3 != r4) goto L7;
    L8:
        this.mVariableDimensionsWidgets.add(r2);
    L9:
        r1 = r1 + 1;
        goto L3
    L7:
        if (r2.getVerticalDimensionBehaviour() != r4) goto L9;
    L10:
        r6.invalidateGraph();
    }
}
