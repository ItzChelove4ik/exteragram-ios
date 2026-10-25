package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import androidx.constraintlayout.core.widgets.Guideline;
import androidx.constraintlayout.core.widgets.HelperWidget;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/* loaded from: classes3.dex */
public class DependencyGraph {
    private ConstraintWidgetContainer mContainer;
    ArrayList<RunGroup> mGroups;
    private BasicMeasure.Measure mMeasure;
    private BasicMeasure.Measurer mMeasurer;
    private boolean mNeedBuildGraph;
    private boolean mNeedRedoMeasures;
    private ArrayList<RunGroup> mRunGroups;
    private ArrayList<WidgetRun> mRuns;
    private ConstraintWidgetContainer mWidgetcontainer;

    public DependencyGraph(ConstraintWidgetContainer r2) {
        this.mNeedBuildGraph = true;
        this.mNeedRedoMeasures = true;
        this.mRuns = new ArrayList();
        this.mRunGroups = new ArrayList();
        this.mMeasurer = null;
        this.mMeasure = new BasicMeasure.Measure();
        this.mGroups = new ArrayList();
        this.mWidgetcontainer = r2;
        this.mContainer = r2;
    }

    private void applyGroup(DependencyNode r8, int r9, int r10, DependencyNode r11, ArrayList<RunGroup> r12, RunGroup r13) {
        WidgetRun r82 = r8.mRun;
        if (r82.mRunGroup != null) goto L56;
        ConstraintWidgetContainer r0 = this.mWidgetcontainer;
        if (r82 != r0.mHorizontalRun) goto L7;
        return;
    L7:
        if (r82 == r0.mVerticalRun) goto L80;
        if (r13 != null) goto L11;
        r13 = new RunGroup(r82, r10);
        r12.add(r13);
    L11:
        RunGroup r6 = r13;
        r82.mRunGroup = r6;
        r6.add(r82);
        Iterator<Dependency> r102 = r82.start.mDependencies.iterator();
    L13:
        if (r102.hasNext() == false) goto L19;
        Dependency r132 = r102.next();
        if ((r132 instanceof DependencyNode) == false) goto L17;
        DependencyGraph r02 = this;
        int r2 = r9;
        DependencyNode r4 = r11;
        ArrayList<RunGroup> r5 = r12;
        r02.applyGroup((DependencyNode) r132, r2, 0, r4, r5, r6);
    L18:
        DependencyGraph this = r02;
        r9 = r2;
        r11 = r4;
        r12 = r5;
        goto L13
    L17:
        r02 = this;
        r2 = r9;
        r4 = r11;
        r5 = r12;
        goto L18
    L19:
        DependencyGraph r03 = this;
        int r22 = r9;
        DependencyNode r42 = r11;
        ArrayList<RunGroup> r52 = r12;
        Iterator<Dependency> r7 = r82.end.mDependencies.iterator();
    L21:
        if (r7.hasNext() == false) goto L26;
        Dependency r92 = r7.next();
        if ((r92 instanceof DependencyNode) == false) goto L21;
        r03.applyGroup((DependencyNode) r92, r22, 1, r42, r52, r6);
        goto L21
    L26:
        if (r22 == 1) goto L28;
    L35:
        Iterator<DependencyNode> r93 = r82.start.mTargets.iterator();
    L37:
        if (r93.hasNext() == false) goto L42;
        DependencyNode r1 = r93.next();
        if (r1 != r42) goto L41;
        r6.dual = true;
    L41:
        r03.applyGroup(r1, r22, 0, r42, r52, r6);
        goto L37
    L42:
        Iterator<DependencyNode> r94 = r82.end.mTargets.iterator();
    L44:
        if (r94.hasNext() == false) goto L49;
        DependencyNode r14 = r94.next();
        if (r14 != r42) goto L48;
        r6.dual = true;
    L48:
        r03.applyGroup(r14, r22, 1, r42, r52, r6);
        goto L44
    L49:
        if (r22 == 1) goto L51;
        return;
    L51:
        if ((r82 instanceof VerticalWidgetRun) == false) goto L79;
        Iterator<DependencyNode> r72 = ((VerticalWidgetRun) r82).baseline.mTargets.iterator();
    L54:
        if (r72.hasNext() == false) goto L81;
        r03.applyGroup(r72.next(), r22, 2, r42, r52, r6);
        goto L54
    L81:
        return;
    L79:
        return;
    L28:
        if ((r82 instanceof VerticalWidgetRun) == false) goto L35;
        Iterator<Dependency> r95 = ((VerticalWidgetRun) r82).baseline.mDependencies.iterator();
    L31:
        if (r95.hasNext() == false) goto L35;
        Dependency r103 = r95.next();
        if ((r103 instanceof DependencyNode) == false) goto L31;
        r03.applyGroup((DependencyNode) r103, r22, 2, r42, r52, r6);
        goto L31
    L80:
        return;
    }

    private boolean basicMeasureWidgets(ConstraintWidgetContainer r22) {
        ArrayList<ConstraintWidget> r1 = r22.mChildren;
        int r2 = r1.size();
        char r3 = 0;
        int r4 = 0;
    L3:
        if (r4 >= r2) goto L146;
        ConstraintWidget r5 = r1.get(r4);
        r4 = r4 + 1;
        ConstraintWidget r7 = r5;
        ConstraintWidget.DimensionBehaviour[] r52 = r7.mListDimensionBehaviors;
        ConstraintWidget.DimensionBehaviour r6 = r52[r3 == true ? 1 : 0];
        ConstraintWidget.DimensionBehaviour r53 = r52[1];
        if (r7.getVisibility() == 8) goto L6;
        if (r7.mMatchConstraintPercentWidth >= 1.0f) goto L13;
        if (r6 != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) goto L13;
        r7.mMatchConstraintDefaultWidth = 2;
    L13:
        if (r7.mMatchConstraintPercentHeight >= 1.0f) goto L18;
        if (r53 != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) goto L18;
        r7.mMatchConstraintDefaultHeight = 2;
    L18:
        if (r7.getDimensionRatio() <= 0.0f) goto L40;
        ConstraintWidget.DimensionBehaviour r8 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        if (r6 == r8) goto L22;
    L26:
        if (r53 == r8) goto L28;
    L32:
        if (r6 != r8) goto L40;
        if (r53 != r8) goto L40;
        if (r7.mMatchConstraintDefaultWidth != 0) goto L38;
        r7.mMatchConstraintDefaultWidth = 3;
    L38:
        if (r7.mMatchConstraintDefaultHeight != 0) goto L40;
        r7.mMatchConstraintDefaultHeight = 3;
        goto L40
    L28:
        if (r6 != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) goto L30;
    L31:
        r7.mMatchConstraintDefaultHeight = 3;
        goto L40
    L30:
        if (r6 != ConstraintWidget.DimensionBehaviour.FIXED) goto L32;
    L22:
        if (r53 != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) goto L24;
    L25:
        r7.mMatchConstraintDefaultWidth = 3;
        goto L40
    L24:
        if (r53 != ConstraintWidget.DimensionBehaviour.FIXED) goto L26;
    L40:
        ConstraintWidget.DimensionBehaviour r82 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        if (r6 == r82) goto L43;
    L49:
        if (r53 == r82) goto L51;
    L57:
        HorizontalWidgetRun r11 = r7.mHorizontalRun;
        r11.mDimensionBehavior = r6;
        int r14 = r7.mMatchConstraintDefaultWidth;
        r11.matchConstraintsType = r14;
        VerticalWidgetRun r112 = r7.mVerticalRun;
        r112.mDimensionBehavior = r53;
        int r15 = r7.mMatchConstraintDefaultHeight;
        r112.matchConstraintsType = r15;
        ConstraintWidget.DimensionBehaviour r113 = ConstraintWidget.DimensionBehaviour.MATCH_PARENT;
        char r16 = r3 == true ? 1 : 0;
        if (r6 != r113) goto L60;
    L63:
        if (r53 != r113) goto L65;
    L68:
        ConstraintWidget.DimensionBehaviour r10 = r53;
        ConstraintWidget.DimensionBehaviour r83 = r6;
        int r32 = r7.getWidth();
        if (r83 != r113) goto L141;
        r32 = (r22.getWidth() - r7.mLeft.mMargin) - r7.mRight.mMargin;
        r83 = ConstraintWidget.DimensionBehaviour.FIXED;
    L141:
        int r9 = r32;
        int r33 = r7.getHeight();
        if (r10 != r113) goto L144;
        r33 = (r22.getHeight() - r7.mTop.mMargin) - r7.mBottom.mMargin;
        r10 = ConstraintWidget.DimensionBehaviour.FIXED;
    L144:
        measure(r7, r83, r9, r10, r33);
        r7.mHorizontalRun.mDimension.resolve(r7.getWidth());
        r7.mVerticalRun.mDimension.resolve(r7.getHeight());
        r7.measured = true;
    L80:
        r3 = r16;
        goto L3
    L65:
        if (r53 == ConstraintWidget.DimensionBehaviour.FIXED) goto L68;
        if (r53 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) goto L68;
    L69:
        if (r6 != r82) goto L94;
        ConstraintWidget.DimensionBehaviour r34 = r82;
        float r17 = 0.5f;
        ConstraintWidget.DimensionBehaviour r84 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        float r18 = 1.0f;
        if (r53 != r84) goto L73;
    L76:
        if (r14 != 3) goto L81;
        if (r53 != r84) goto L79;
        measure(r7, r84, 0, r84, 0);
    L79:
        int r114 = r7.getHeight();
        int r92 = (int) ((r114 * r7.mDimensionRatio) + 0.5f);
        ConstraintWidget.DimensionBehaviour r85 = ConstraintWidget.DimensionBehaviour.FIXED;
        measure(r7, r85, r92, r85, r114);
        r7.mHorizontalRun.mDimension.resolve(r7.getWidth());
        r7.mVerticalRun.mDimension.resolve(r7.getHeight());
        r7.measured = true;
        goto L80
    L81:
        if (r14 != 1) goto L83;
        measure(r7, r84, 0, r53, 0);
        r7.mHorizontalRun.mDimension.wrapValue = r7.getWidth();
        goto L80
    L83:
        ConstraintWidget.DimensionBehaviour r102 = r53;
        int r54 = 3;
        if (r14 != 2) goto L89;
        ConstraintWidget.DimensionBehaviour r86 = r22.mListDimensionBehaviors[r16];
        ConstraintWidget.DimensionBehaviour r13 = ConstraintWidget.DimensionBehaviour.FIXED;
        if (r86 == r13) goto L88;
        if (r86 == r113) goto L88;
    L95:
        if (r102 != r34) goto L101;
        ConstraintWidget.DimensionBehaviour r87 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (r6 != r87) goto L99;
    L102:
        if (r15 != r54) goto L109;
        if (r6 != r87) goto L105;
        measure(r7, r87, 0, r87, 0);
    L105:
        int r93 = r7.getWidth();
        float r35 = r7.mDimensionRatio;
        if (r7.getDimensionRatioSide() != (-1)) goto L108;
        r35 = r18 / r35;
    L108:
        ConstraintWidget.DimensionBehaviour r88 = ConstraintWidget.DimensionBehaviour.FIXED;
        measure(r7, r88, r93, r88, (int) ((r93 * r35) + r17));
        r7.mHorizontalRun.mDimension.resolve(r7.getWidth());
        r7.mVerticalRun.mDimension.resolve(r7.getHeight());
        r7.measured = true;
        goto L80
    L109:
        if (r15 != 1) goto L111;
        measure(r7, r6, 0, r87, 0);
        r7.mVerticalRun.mDimension.wrapValue = r7.getHeight();
        goto L80
    L111:
        ConstraintWidget.DimensionBehaviour r89 = r6;
        if (r15 != 2) goto L119;
        ConstraintWidget.DimensionBehaviour r55 = r22.mListDimensionBehaviors[1];
        ConstraintWidget.DimensionBehaviour r94 = r102;
        ConstraintWidget.DimensionBehaviour r103 = ConstraintWidget.DimensionBehaviour.FIXED;
        if (r55 == r103) goto L118;
        if (r55 == r113) goto L118;
    L117:
        r102 = r94;
    L124:
        if (r89 != r34) goto L80;
        if (r102 != r34) goto L80;
        if (r14 == 1) goto L137;
        if (r15 == 1) goto L137;
        if (r15 != 2) goto L80;
        if (r14 != 2) goto L80;
        ConstraintWidget.DimensionBehaviour[] r36 = r22.mListDimensionBehaviors;
        ConstraintWidget.DimensionBehaviour r56 = r36[r16];
        ConstraintWidget.DimensionBehaviour r810 = ConstraintWidget.DimensionBehaviour.FIXED;
        if (r56 != r810) goto L80;
        if (r36[1] != r810) goto L80;
        measure(r7, r810, (int) ((r7.mMatchConstraintPercentWidth * r22.getWidth()) + r17), r810, (int) ((r7.mMatchConstraintPercentHeight * r22.getHeight()) + r17));
        r7.mHorizontalRun.mDimension.resolve(r7.getWidth());
        r7.mVerticalRun.mDimension.resolve(r7.getHeight());
        r7.measured = true;
    L137:
        ConstraintWidget.DimensionBehaviour r811 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        measure(r7, r811, 0, r811, 0);
        r7.mHorizontalRun.mDimension.wrapValue = r7.getWidth();
        r7.mVerticalRun.mDimension.wrapValue = r7.getHeight();
    L118:
        measure(r7, r89, r7.getWidth(), r103, (int) ((r7.mMatchConstraintPercentHeight * r22.getHeight()) + r17));
        r7.mHorizontalRun.mDimension.resolve(r7.getWidth());
        r7.mVerticalRun.mDimension.resolve(r7.getHeight());
        r7.measured = true;
        goto L80
    L119:
        r94 = r102;
        ConstraintAnchor[] r104 = r7.mListAnchors;
        if (r104[2].mTarget != null) goto L122;
    L123:
        measure(r7, r87, 0, r94, 0);
        r7.mHorizontalRun.mDimension.resolve(r7.getWidth());
        r7.mVerticalRun.mDimension.resolve(r7.getHeight());
        r7.measured = true;
        goto L80
    L122:
        if (r104[r54].mTarget != null) goto L117;
    L99:
        if (r6 == ConstraintWidget.DimensionBehaviour.FIXED) goto L102;
    L101:
        r89 = r6;
    L88:
        measure(r7, r13, (int) ((r7.mMatchConstraintPercentWidth * r22.getWidth()) + 0.5f), r102, r7.getHeight());
        r7.mHorizontalRun.mDimension.resolve(r7.getWidth());
        r7.mVerticalRun.mDimension.resolve(r7.getHeight());
        r7.measured = true;
        goto L80
    L89:
        ConstraintAnchor[] r132 = r7.mListAnchors;
        if (r132[r16].mTarget != null) goto L92;
    L93:
        measure(r7, r84, 0, r102, 0);
        r7.mHorizontalRun.mDimension.resolve(r7.getWidth());
        r7.mVerticalRun.mDimension.resolve(r7.getHeight());
        r7.measured = true;
        goto L80
    L92:
        if (r132[1].mTarget != null) goto L95;
    L73:
        if (r53 == ConstraintWidget.DimensionBehaviour.FIXED) goto L76;
        r102 = r53;
        r54 = 3;
        goto L95
    L94:
        r102 = r53;
        r54 = 3;
        r34 = r82;
        r18 = 1.0f;
        r17 = 0.5f;
        goto L95
    L60:
        if (r6 == ConstraintWidget.DimensionBehaviour.FIXED) goto L63;
        if (r6 != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) goto L69;
    L51:
        if (r7.mMatchConstraintDefaultHeight != 1) goto L57;
        if (r7.mTop.mTarget != null) goto L55;
    L56:
        r53 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        goto L57
    L55:
        if (r7.mBottom.mTarget != null) goto L57;
    L43:
        if (r7.mMatchConstraintDefaultWidth != 1) goto L49;
        if (r7.mLeft.mTarget != null) goto L47;
    L48:
        r6 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        goto L49
    L47:
        if (r7.mRight.mTarget != null) goto L49;
    L6:
        r7.measured = true;
        goto L3
    L146:
        return r3 == true ? 1 : 0;
    }

    private int computeWrap(ConstraintWidgetContainer r7, int r8) {
        int r0 = this.mGroups.size();
        long r1 = 0;
        int r3 = 0;
    L3:
        if (r3 >= r0) goto L6;
        r1 = Math.max(r1, this.mGroups.get(r3).computeWrapSize(r7, r8));
        r3 = r3 + 1;
        goto L3
    L6:
        return (int) r1;
    }

    private void findGroup(WidgetRun r19, int r20, ArrayList<RunGroup> r21) {
        Iterator<Dependency> r1 = r19.start.mDependencies.iterator();
    L4:
        if (r1.hasNext() == false) goto L11;
        Dependency r2 = r1.next();
        if ((r2 instanceof DependencyNode) == true) goto L7;
        if ((r2 instanceof WidgetRun) == false) goto L4;
        applyGroup(((WidgetRun) r2).start, r20, 0, r19.end, r21, null);
        goto L4
    L7:
        applyGroup((DependencyNode) r2, r20, 0, r19.end, r21, null);
        goto L4
    L11:
        Iterator<Dependency> r12 = r19.end.mDependencies.iterator();
    L13:
        if (r12.hasNext() == false) goto L20;
        Dependency r22 = r12.next();
        if ((r22 instanceof DependencyNode) == true) goto L16;
        if ((r22 instanceof WidgetRun) == false) goto L13;
        applyGroup(((WidgetRun) r22).end, r20, 1, r19.start, r21, null);
        goto L13
    L16:
        applyGroup((DependencyNode) r22, r20, 1, r19.start, r21, null);
        goto L13
    L20:
        int r13 = r20;
        if (r13 != 1) goto L29;
        Iterator<Dependency> r0 = ((VerticalWidgetRun) r19).baseline.mDependencies.iterator();
    L24:
        if (r0.hasNext() == false) goto L49;
        Dependency r14 = r0.next();
        if ((r14 instanceof DependencyNode) == false) goto L28;
        applyGroup((DependencyNode) r14, r13, 2, null, r21, null);
    L28:
        r13 = r20;
        goto L24
    L49:
        return;
    }

    private void measure(ConstraintWidget r2, ConstraintWidget.DimensionBehaviour r3, int r4, ConstraintWidget.DimensionBehaviour r5, int r6) {
        BasicMeasure.Measure r0 = this.mMeasure;
        r0.horizontalBehavior = r3;
        r0.verticalBehavior = r5;
        r0.horizontalDimension = r4;
        r0.verticalDimension = r6;
        this.mMeasurer.measure(r2, r0);
        r2.setWidth(this.mMeasure.measuredWidth);
        r2.setHeight(this.mMeasure.measuredHeight);
        r2.setHasBaseline(this.mMeasure.measuredHasBaseline);
        r2.setBaselineDistance(this.mMeasure.measuredBaseline);
    }

    public void buildGraph() {
        buildGraph(this.mRuns);
        this.mGroups.clear();
        RunGroup.index = 0;
        findGroup(this.mWidgetcontainer.mHorizontalRun, 0, this.mGroups);
        findGroup(this.mWidgetcontainer.mVerticalRun, 1, this.mGroups);
        this.mNeedBuildGraph = false;
    }

    public void buildGraph(ArrayList<WidgetRun> r9) {
        r9.clear();
        this.mContainer.mHorizontalRun.clear();
        this.mContainer.mVerticalRun.clear();
        r9.add(this.mContainer.mHorizontalRun);
        r9.add(this.mContainer.mVerticalRun);
        ArrayList<ConstraintWidget> r0 = this.mContainer.mChildren;
        int r1 = r0.size();
        HashSet r2 = null;
        int r3 = 0;
        int r4 = 0;
    L3:
        if (r4 >= r1) goto L28;
        ConstraintWidget r5 = r0.get(r4);
        r4 = r4 + 1;
        ConstraintWidget r52 = r5;
        if ((r52 instanceof Guideline) == true) goto L6;
        if (r52.isInHorizontalChain() == true) goto L10;
        r9.add(r52.mHorizontalRun);
    L17:
        if (r52.isInVerticalChain() == true) goto L19;
        r9.add(r52.mVerticalRun);
    L26:
        if ((r52 instanceof HelperWidget) == false) goto L3;
        r9.add(new HelperReferences(r52));
        goto L3
    L19:
        if (r52.verticalChainRun != null) goto L21;
        r52.verticalChainRun = new ChainRun(r52, 1);
    L21:
        if (r2 != null) goto L23;
        r2 = new HashSet();
    L23:
        r2.add(r52.verticalChainRun);
        goto L26
    L10:
        if (r52.horizontalChainRun != null) goto L12;
        r52.horizontalChainRun = new ChainRun(r52, 0);
    L12:
        if (r2 != null) goto L14;
        r2 = new HashSet();
    L14:
        r2.add(r52.horizontalChainRun);
        goto L17
    L6:
        r9.add(new GuidelineReference(r52));
        goto L3
    L28:
        if (r2 == null) goto L30;
        r9.addAll(r2);
    L30:
        int r02 = r9.size();
        int r12 = 0;
    L31:
        if (r12 >= r02) goto L33;
        WidgetRun r22 = r9.get(r12);
        r12 = r12 + 1;
        r22.clear();
        goto L31
    L33:
        int r03 = r9.size();
    L34:
        if (r3 >= r03) goto L39;
        WidgetRun r13 = r9.get(r3);
        r3 = r3 + 1;
        WidgetRun r14 = r13;
        if (r14.mWidget == this.mContainer) goto L34;
        r14.apply();
        goto L34
    }

    public boolean directMeasure(boolean r11) {
        boolean r1 = false;
        if (this.mNeedBuildGraph == false) goto L5;
    L6:
        ArrayList<ConstraintWidget> r0 = this.mWidgetcontainer.mChildren;
        int r2 = r0.size();
        int r3 = 0;
    L7:
        if (r3 >= r2) goto L9;
        ConstraintWidget r4 = r0.get(r3);
        r3 = r3 + 1;
        ConstraintWidget r42 = r4;
        r42.ensureWidgetRuns();
        r42.measured = false;
        r42.mHorizontalRun.reset();
        r42.mVerticalRun.reset();
        goto L7
    L9:
        this.mWidgetcontainer.ensureWidgetRuns();
        ConstraintWidgetContainer r02 = this.mWidgetcontainer;
        r02.measured = false;
        r02.mHorizontalRun.reset();
        this.mWidgetcontainer.mVerticalRun.reset();
        this.mNeedRedoMeasures = false;
    L11:
        if (basicMeasureWidgets(this.mContainer) == false) goto L13;
        return false;
    L13:
        this.mWidgetcontainer.setX(0);
        this.mWidgetcontainer.setY(0);
        ConstraintWidget.DimensionBehaviour r03 = this.mWidgetcontainer.getDimensionBehaviour(0);
        ConstraintWidget.DimensionBehaviour r22 = this.mWidgetcontainer.getDimensionBehaviour(1);
        if (this.mNeedBuildGraph == false) goto L16;
        buildGraph();
    L16:
        int r43 = this.mWidgetcontainer.getX();
        int r5 = this.mWidgetcontainer.getY();
        this.mWidgetcontainer.mHorizontalRun.start.resolve(r43);
        this.mWidgetcontainer.mVerticalRun.start.resolve(r5);
        measureWidgets();
        ConstraintWidget.DimensionBehaviour r6 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (r03 == r6) goto L19;
        if (r22 == r6) goto L19;
    L33:
        ConstraintWidgetContainer r112 = this.mWidgetcontainer;
        ConstraintWidget.DimensionBehaviour r62 = r112.mListDimensionBehaviors[0];
        ConstraintWidget.DimensionBehaviour r7 = ConstraintWidget.DimensionBehaviour.FIXED;
        if (r62 != r7) goto L36;
    L39:
        int r113 = r112.getWidth() + r43;
        this.mWidgetcontainer.mHorizontalRun.end.resolve(r113);
        this.mWidgetcontainer.mHorizontalRun.mDimension.resolve(r113 - r43);
        measureWidgets();
        ConstraintWidgetContainer r114 = this.mWidgetcontainer;
        ConstraintWidget.DimensionBehaviour r44 = r114.mListDimensionBehaviors[1];
        if (r44 != r7) goto L42;
    L43:
        int r115 = r114.getHeight() + r5;
        this.mWidgetcontainer.mVerticalRun.end.resolve(r115);
        this.mWidgetcontainer.mVerticalRun.mDimension.resolve(r115 - r5);
    L44:
        measureWidgets();
        boolean r116 = true;
    L45:
        ArrayList<WidgetRun> r45 = this.mRuns;
        int r52 = r45.size();
        int r63 = 0;
    L46:
        if (r63 >= r52) goto L53;
        WidgetRun r72 = r45.get(r63);
        r63 = r63 + 1;
        WidgetRun r73 = r72;
        if (r73.mWidget != this.mWidgetcontainer) goto L52;
        if (r73.mResolved == false) goto L46;
    L52:
        r73.applyToWidget();
        goto L46
    L53:
        ArrayList<WidgetRun> r46 = this.mRuns;
        int r53 = r46.size();
        int r64 = 0;
    L54:
        if (r64 >= r53) goto L75;
        WidgetRun r74 = r46.get(r64);
        r64 = r64 + 1;
        WidgetRun r75 = r74;
        if (r116 == true) goto L61;
        if (r75.mWidget == this.mWidgetcontainer) goto L54;
    L61:
        if (r75.start.resolved == false) goto L76;
        if (r75.end.resolved == true) goto L69;
        if ((r75 instanceof GuidelineReference) == false) goto L76;
    L69:
        if (r75.mDimension.resolved == true) goto L54;
        if ((r75 instanceof ChainRun) == true) goto L54;
        if ((r75 instanceof GuidelineReference) == true) goto L54;
    L76:
        this.mWidgetcontainer.setHorizontalDimensionBehaviour(r03);
        this.mWidgetcontainer.setVerticalDimensionBehaviour(r22);
        return r1;
    L75:
        r1 = true;
        goto L76
    L42:
        if (r44 != ConstraintWidget.DimensionBehaviour.MATCH_PARENT) goto L44;
    L36:
        if (r62 == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) goto L39;
        r116 = false;
    L19:
        if (r11 == false) goto L25;
        ArrayList<WidgetRun> r65 = this.mRuns;
        int r76 = r65.size();
        int r8 = 0;
    L21:
        if (r8 >= r76) goto L25;
        WidgetRun r9 = r65.get(r8);
        r8 = r8 + 1;
        if (r9.supportsWrapComputation() == true) goto L21;
        r11 = false;
    L25:
        if (r11 == true) goto L27;
    L29:
        if (r11 == false) goto L33;
        if (r22 != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) goto L33;
        this.mWidgetcontainer.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
        ConstraintWidgetContainer r117 = this.mWidgetcontainer;
        r117.setHeight(computeWrap(r117, 1));
        ConstraintWidgetContainer r118 = this.mWidgetcontainer;
        r118.mVerticalRun.mDimension.resolve(r118.getHeight());
        goto L33
    L27:
        if (r03 != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) goto L29;
        this.mWidgetcontainer.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
        ConstraintWidgetContainer r66 = this.mWidgetcontainer;
        r66.setWidth(computeWrap(r66, 0));
        ConstraintWidgetContainer r67 = this.mWidgetcontainer;
        r67.mHorizontalRun.mDimension.resolve(r67.getWidth());
        goto L29
    L5:
        if (this.mNeedRedoMeasures == false) goto L11;
        goto L6
    }

    public boolean directMeasureSetup(boolean r7) {
        if (this.mNeedBuildGraph == false) goto L9;
        ArrayList<ConstraintWidget> r72 = this.mWidgetcontainer.mChildren;
        int r1 = r72.size();
        int r2 = 0;
    L5:
        if (r2 >= r1) goto L7;
        ConstraintWidget r3 = r72.get(r2);
        r2 = r2 + 1;
        ConstraintWidget r32 = r3;
        r32.ensureWidgetRuns();
        r32.measured = false;
        HorizontalWidgetRun r4 = r32.mHorizontalRun;
        r4.mDimension.resolved = false;
        r4.mResolved = false;
        r4.reset();
        VerticalWidgetRun r33 = r32.mVerticalRun;
        r33.mDimension.resolved = false;
        r33.mResolved = false;
        r33.reset();
        goto L5
    L7:
        this.mWidgetcontainer.ensureWidgetRuns();
        ConstraintWidgetContainer r73 = this.mWidgetcontainer;
        r73.measured = false;
        HorizontalWidgetRun r74 = r73.mHorizontalRun;
        r74.mDimension.resolved = false;
        r74.mResolved = false;
        r74.reset();
        VerticalWidgetRun r75 = this.mWidgetcontainer.mVerticalRun;
        r75.mDimension.resolved = false;
        r75.mResolved = false;
        r75.reset();
        buildGraph();
    L9:
        if (basicMeasureWidgets(this.mContainer) == false) goto L11;
        return false;
    L11:
        this.mWidgetcontainer.setX(0);
        this.mWidgetcontainer.setY(0);
        this.mWidgetcontainer.mHorizontalRun.start.resolve(0);
        this.mWidgetcontainer.mVerticalRun.start.resolve(0);
        return true;
    }

    public boolean directMeasureWithOrientation(boolean r12, int r13) {
        boolean r1 = false;
        ConstraintWidget.DimensionBehaviour r0 = this.mWidgetcontainer.getDimensionBehaviour(0);
        ConstraintWidget.DimensionBehaviour r2 = this.mWidgetcontainer.getDimensionBehaviour(1);
        int r4 = this.mWidgetcontainer.getX();
        int r5 = this.mWidgetcontainer.getY();
        if (r12 == false) goto L23;
        ConstraintWidget.DimensionBehaviour r6 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (r0 == r6) goto L7;
        if (r2 != r6) goto L23;
    L7:
        ArrayList<WidgetRun> r62 = this.mRuns;
        int r7 = r62.size();
        int r8 = 0;
    L8:
        if (r8 >= r7) goto L14;
        WidgetRun r9 = r62.get(r8);
        r8 = r8 + 1;
        WidgetRun r92 = r9;
        if (r92.orientation != r13) goto L8;
        if (r92.supportsWrapComputation() == true) goto L8;
        r12 = false;
    L14:
        if (r13 != 0) goto L19;
        if (r12 == false) goto L23;
        if (r0 != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) goto L23;
        this.mWidgetcontainer.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
        ConstraintWidgetContainer r122 = this.mWidgetcontainer;
        r122.setWidth(computeWrap(r122, 0));
        ConstraintWidgetContainer r123 = this.mWidgetcontainer;
        r123.mHorizontalRun.mDimension.resolve(r123.getWidth());
        goto L23
    L19:
        if (r12 == false) goto L23;
        if (r2 != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) goto L23;
        this.mWidgetcontainer.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
        ConstraintWidgetContainer r124 = this.mWidgetcontainer;
        r124.setHeight(computeWrap(r124, 1));
        ConstraintWidgetContainer r125 = this.mWidgetcontainer;
        r125.mVerticalRun.mDimension.resolve(r125.getHeight());
    L23:
        ConstraintWidgetContainer r126 = this.mWidgetcontainer;
        if (r13 != 0) goto L31;
        ConstraintWidget.DimensionBehaviour r52 = r126.mListDimensionBehaviors[0];
        if (r52 != ConstraintWidget.DimensionBehaviour.FIXED) goto L28;
    L29:
        int r127 = r126.getWidth() + r4;
        this.mWidgetcontainer.mHorizontalRun.end.resolve(r127);
        this.mWidgetcontainer.mHorizontalRun.mDimension.resolve(r127 - r4);
    L30:
        boolean r128 = true;
    L38:
        measureWidgets();
        ArrayList<WidgetRun> r42 = this.mRuns;
        int r53 = r42.size();
        int r63 = 0;
    L39:
        if (r63 >= r53) goto L49;
        WidgetRun r72 = r42.get(r63);
        r63 = r63 + 1;
        WidgetRun r73 = r72;
        if (r73.orientation != r13) goto L39;
        if (r73.mWidget != this.mWidgetcontainer) goto L48;
        if (r73.mResolved == false) goto L39;
    L48:
        r73.applyToWidget();
        goto L39
    L49:
        ArrayList<WidgetRun> r43 = this.mRuns;
        int r54 = r43.size();
        int r64 = 0;
    L50:
        if (r64 >= r54) goto L69;
        WidgetRun r74 = r43.get(r64);
        r64 = r64 + 1;
        WidgetRun r75 = r74;
        if (r75.orientation != r13) goto L50;
        if (r128 == true) goto L59;
        if (r75.mWidget == this.mWidgetcontainer) goto L50;
    L59:
        if (r75.start.resolved == false) goto L70;
        if (r75.end.resolved == false) goto L70;
        if ((r75 instanceof ChainRun) == true) goto L50;
        if (r75.mDimension.resolved == true) goto L50;
    L70:
        this.mWidgetcontainer.setHorizontalDimensionBehaviour(r0);
        this.mWidgetcontainer.setVerticalDimensionBehaviour(r2);
        return r1;
    L69:
        r1 = true;
        goto L70
    L28:
        if (r52 == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) goto L29;
    L36:
        r128 = false;
        goto L38
    L31:
        ConstraintWidget.DimensionBehaviour r44 = r126.mListDimensionBehaviors[1];
        if (r44 != ConstraintWidget.DimensionBehaviour.FIXED) goto L34;
    L37:
        int r129 = r126.getHeight() + r5;
        this.mWidgetcontainer.mVerticalRun.end.resolve(r129);
        this.mWidgetcontainer.mVerticalRun.mDimension.resolve(r129 - r5);
        goto L30
    L34:
        if (r44 != ConstraintWidget.DimensionBehaviour.MATCH_PARENT) goto L36;
        goto L36
    }

    public void invalidateGraph() {
        this.mNeedBuildGraph = true;
    }

    public void invalidateMeasures() {
        this.mNeedRedoMeasures = true;
    }

    public void measureWidgets() {
        ArrayList<ConstraintWidget> r0 = this.mWidgetcontainer.mChildren;
        int r1 = r0.size();
        int r3 = 0;
    L3:
        if (r3 >= r1) goto L46;
        ConstraintWidget r4 = r0.get(r3);
        r3 = r3 + 1;
        ConstraintWidget r6 = r4;
        if (r6.measured == true) goto L3;
        ConstraintWidget.DimensionBehaviour[] r42 = r6.mListDimensionBehaviors;
        ConstraintWidget.DimensionBehaviour r11 = r42[0];
        ConstraintWidget.DimensionBehaviour r43 = r42[1];
        int r5 = r6.mMatchConstraintDefaultWidth;
        int r7 = r6.mMatchConstraintDefaultHeight;
        ConstraintWidget.DimensionBehaviour r9 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (r11 != r9) goto L10;
    L14:
        boolean r52 = true;
    L15:
        if (r43 != r9) goto L17;
    L21:
        boolean r72 = true;
    L22:
        DimensionDependency r8 = r6.mHorizontalRun.mDimension;
        boolean r10 = r8.resolved;
        DimensionDependency r13 = r6.mVerticalRun.mDimension;
        boolean r14 = r13.resolved;
        if (r10 == false) goto L26;
        if (r14 == false) goto L26;
        ConstraintWidget.DimensionBehaviour r73 = ConstraintWidget.DimensionBehaviour.FIXED;
        DependencyGraph r53 = this;
        r53.measure(r6, r73, r8.value, r73, r13.value);
        r6.measured = true;
    L41:
        if (r6.measured == false) goto L45;
        DimensionDependency r15 = r6.mVerticalRun.mBaselineDimension;
        if (r15 == null) goto L45;
        r15.resolve(r6.getBaselineDistance());
    L45:
        DependencyGraph this = r53;
    L26:
        if (r10 == false) goto L34;
        if (r72 == false) goto L34;
        this.measure(r6, ConstraintWidget.DimensionBehaviour.FIXED, r8.value, r9, r13.value);
        ConstraintWidget.DimensionBehaviour r54 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        VerticalWidgetRun r74 = r6.mVerticalRun;
        if (r43 != r54) goto L32;
        r74.mDimension.wrapValue = r6.getHeight();
    L31:
        r53 = this;
        goto L41
    L32:
        r74.mDimension.resolve(r6.getHeight());
        r6.measured = true;
    L34:
        if (r14 == false) goto L31;
        if (r52 == false) goto L31;
        r53 = this;
        r53.measure(r6, r9, r8.value, ConstraintWidget.DimensionBehaviour.FIXED, r13.value);
        ConstraintWidget.DimensionBehaviour r152 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        HorizontalWidgetRun r44 = r6.mHorizontalRun;
        if (r11 != r152) goto L39;
        r44.mDimension.wrapValue = r6.getWidth();
        goto L41
    L39:
        r44.mDimension.resolve(r6.getWidth());
        r6.measured = true;
        goto L41
    L17:
        if (r43 != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) goto L20;
        if (r7 == 1) goto L21;
    L20:
        r72 = false;
        goto L22
    L10:
        if (r11 != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) goto L13;
        if (r5 == 1) goto L14;
    L13:
        r52 = false;
        goto L15
    }

    public void setMeasurer(BasicMeasure.Measurer r1) {
        this.mMeasurer = r1;
    }
}
