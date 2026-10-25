package androidx.constraintlayout.core.widgets;

import androidx.constraintlayout.core.LinearSystem;
import androidx.constraintlayout.core.Metrics;
import androidx.constraintlayout.core.SolverVariable;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import androidx.constraintlayout.core.widgets.analyzer.DependencyGraph;
import androidx.constraintlayout.core.widgets.analyzer.Direct;
import androidx.constraintlayout.core.widgets.analyzer.Grouping;
import androidx.mediarouter.media.GlobalMediaRouter;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

/* loaded from: classes3.dex */
public class ConstraintWidgetContainer extends WidgetContainer {
    BasicMeasure mBasicMeasureSolver;
    int mDebugSolverPassCount;
    public DependencyGraph mDependencyGraph;
    public boolean mGroupsWrapOptimized;
    private boolean mHeightMeasuredTooSmall;
    ChainHead[] mHorizontalChainsArray;
    public int mHorizontalChainsSize;
    private WeakReference<ConstraintAnchor> mHorizontalWrapMax;
    private WeakReference<ConstraintAnchor> mHorizontalWrapMin;
    public boolean mHorizontalWrapOptimized;
    private boolean mIsRtl;
    public BasicMeasure.Measure mMeasure;
    protected BasicMeasure.Measurer mMeasurer;
    private int mOptimizationLevel;
    int mPaddingBottom;
    int mPaddingLeft;
    int mPaddingRight;
    int mPaddingTop;
    private int mPass;
    public boolean mSkipSolver;
    protected LinearSystem mSystem;
    ChainHead[] mVerticalChainsArray;
    public int mVerticalChainsSize;
    private WeakReference<ConstraintAnchor> mVerticalWrapMax;
    private WeakReference<ConstraintAnchor> mVerticalWrapMin;
    public boolean mVerticalWrapOptimized;
    HashSet<ConstraintWidget> mWidgetsToAdd;
    private boolean mWidthMeasuredTooSmall;
    public int mWrapFixedHeight;
    public int mWrapFixedWidth;

    static {
    }

    public ConstraintWidgetContainer() {
        this.mBasicMeasureSolver = new BasicMeasure(this);
        this.mDependencyGraph = new DependencyGraph(this);
        this.mMeasurer = null;
        this.mIsRtl = false;
        this.mSystem = new LinearSystem();
        this.mHorizontalChainsSize = 0;
        this.mVerticalChainsSize = 0;
        this.mVerticalChainsArray = new ChainHead[4];
        this.mHorizontalChainsArray = new ChainHead[4];
        this.mGroupsWrapOptimized = false;
        this.mHorizontalWrapOptimized = false;
        this.mVerticalWrapOptimized = false;
        this.mWrapFixedWidth = 0;
        this.mWrapFixedHeight = 0;
        this.mOptimizationLevel = GlobalMediaRouter.CallbackHandler.MSG_ROUTE_ADDED;
        this.mSkipSolver = false;
        this.mWidthMeasuredTooSmall = false;
        this.mHeightMeasuredTooSmall = false;
        this.mDebugSolverPassCount = 0;
        this.mVerticalWrapMin = null;
        this.mHorizontalWrapMin = null;
        this.mVerticalWrapMax = null;
        this.mHorizontalWrapMax = null;
        this.mWidgetsToAdd = new HashSet();
        this.mMeasure = new BasicMeasure.Measure();
    }

    private void addHorizontalChain(ConstraintWidget r6) {
        int r0 = this.mHorizontalChainsSize + 1;
        ChainHead[] r1 = this.mHorizontalChainsArray;
        if (r0 < r1.length) goto L5;
        this.mHorizontalChainsArray = (ChainHead[]) Arrays.copyOf(r1, r1.length * 2);
    L5:
        this.mHorizontalChainsArray[this.mHorizontalChainsSize] = new ChainHead(r6, 0, isRtl());
        this.mHorizontalChainsSize++;
    }

    private void addMaxWrap(ConstraintAnchor r3, SolverVariable r4) {
        SolverVariable r32 = this.mSystem.createObjectVariable(r3);
        this.mSystem.addGreaterThan(r4, r32, 0, 5);
    }

    private void addMinWrap(ConstraintAnchor r3, SolverVariable r4) {
        SolverVariable r32 = this.mSystem.createObjectVariable(r3);
        this.mSystem.addGreaterThan(r32, r4, 0, 5);
    }

    private void addVerticalChain(ConstraintWidget r6) {
        int r0 = this.mVerticalChainsSize + 1;
        ChainHead[] r2 = this.mVerticalChainsArray;
        if (r0 < r2.length) goto L5;
        this.mVerticalChainsArray = (ChainHead[]) Arrays.copyOf(r2, r2.length * 2);
    L5:
        this.mVerticalChainsArray[this.mVerticalChainsSize] = new ChainHead(r6, 1, isRtl());
        this.mVerticalChainsSize++;
    }

    public static boolean measure(int r5, ConstraintWidget r6, BasicMeasure.Measurer r7, BasicMeasure.Measure r8, int r9) {
        if (r7 != null) goto L6;
        return false;
    L6:
        if (r6.getVisibility() != 8) goto L8;
    L87:
        r8.measuredWidth = 0;
        r8.measuredHeight = 0;
        return false;
    L8:
        if ((r6 instanceof Guideline) == true) goto L87;
        if ((r6 instanceof Barrier) == true) goto L87;
        r8.horizontalBehavior = r6.getHorizontalDimensionBehaviour();
        r8.verticalBehavior = r6.getVerticalDimensionBehaviour();
        r8.horizontalDimension = r6.getWidth();
        r8.verticalDimension = r6.getHeight();
        r8.measuredNeedsSolverPass = false;
        r8.measureStrategy = r9;
        ConstraintWidget.DimensionBehaviour r92 = r8.horizontalBehavior;
        ConstraintWidget.DimensionBehaviour r0 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        if (r92 != r0) goto L15;
        boolean r93 = true;
    L17:
        if (r8.verticalBehavior != r0) goto L19;
        boolean r02 = true;
    L21:
        if (r93 == true) goto L23;
    L25:
        boolean r3 = false;
    L26:
        if (r02 == true) goto L28;
    L30:
        boolean r2 = false;
    L31:
        if (r93 == true) goto L33;
    L43:
        if (r02 == false) goto L56;
        if (r6.hasDanglingDimension(1) == false) goto L56;
        if (r6.mMatchConstraintDefaultHeight != 0) goto L56;
        if (r2 == true) goto L56;
        r8.verticalBehavior = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (r93 == true) goto L52;
    L54:
        r02 = false;
        goto L56
    L52:
        if (r6.mMatchConstraintDefaultWidth != 0) goto L54;
        r8.verticalBehavior = ConstraintWidget.DimensionBehaviour.FIXED;
    L56:
        if (r6.isResolvedHorizontally() == false) goto L59;
        r8.horizontalBehavior = ConstraintWidget.DimensionBehaviour.FIXED;
        r93 = false;
    L59:
        if (r6.isResolvedVertically() == false) goto L62;
        r8.verticalBehavior = ConstraintWidget.DimensionBehaviour.FIXED;
        r02 = false;
    L62:
        if (r3 == true) goto L64;
    L72:
        if (r2 == true) goto L74;
    L85:
        r7.measure(r6, r8);
        r6.setWidth(r8.measuredWidth);
        r6.setHeight(r8.measuredHeight);
        r6.setHasBaseline(r8.measuredHasBaseline);
        r6.setBaselineDistance(r8.measuredBaseline);
        r8.measureStrategy = BasicMeasure.Measure.SELF_DIMENSIONS;
        return r8.measuredNeedsSolverPass;
    L74:
        if (r6.mResolvedMatchConstraintDefault[1] != 4) goto L76;
        r8.verticalBehavior = ConstraintWidget.DimensionBehaviour.FIXED;
        goto L85
    L76:
        if (r93 == true) goto L85;
        ConstraintWidget.DimensionBehaviour r52 = r8.horizontalBehavior;
        ConstraintWidget.DimensionBehaviour r94 = ConstraintWidget.DimensionBehaviour.FIXED;
        if (r52 != r94) goto L80;
        int r53 = r8.horizontalDimension;
    L81:
        r8.verticalBehavior = r94;
        if (r6.getDimensionRatioSide() != (-1)) goto L84;
        r8.verticalDimension = (int) (r53 / r6.getDimensionRatio());
        goto L85
    L84:
        r8.verticalDimension = (int) (r6.getDimensionRatio() * r53);
        goto L85
    L80:
        r8.verticalBehavior = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        r7.measure(r6, r8);
        r53 = r8.measuredWidth;
        goto L81
    L64:
        if (r6.mResolvedMatchConstraintDefault[0] != 4) goto L66;
        r8.horizontalBehavior = ConstraintWidget.DimensionBehaviour.FIXED;
        goto L72
    L66:
        if (r02 == true) goto L72;
        ConstraintWidget.DimensionBehaviour r54 = r8.verticalBehavior;
        ConstraintWidget.DimensionBehaviour r03 = ConstraintWidget.DimensionBehaviour.FIXED;
        if (r54 != r03) goto L70;
        int r55 = r8.verticalDimension;
    L71:
        r8.horizontalBehavior = r03;
        r8.horizontalDimension = (int) (r6.getDimensionRatio() * r55);
        goto L72
    L70:
        r8.horizontalBehavior = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        r7.measure(r6, r8);
        r55 = r8.measuredHeight;
        goto L71
    L33:
        if (r6.hasDanglingDimension(0) == false) goto L43;
        if (r6.mMatchConstraintDefaultWidth != 0) goto L43;
        if (r3 == true) goto L43;
        r8.horizontalBehavior = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (r02 == true) goto L40;
    L42:
        r93 = false;
        goto L43
    L40:
        if (r6.mMatchConstraintDefaultHeight != 0) goto L42;
        r8.horizontalBehavior = ConstraintWidget.DimensionBehaviour.FIXED;
        goto L42
    L28:
        if (r6.mDimensionRatio <= 0.0f) goto L30;
        r2 = true;
        goto L31
    L23:
        if (r6.mDimensionRatio <= 0.0f) goto L25;
        r3 = true;
        goto L26
    L19:
        r02 = false;
        goto L21
    L15:
        r93 = false;
        goto L17
    }

    private void resetChains() {
        this.mHorizontalChainsSize = 0;
        this.mVerticalChainsSize = 0;
    }

    public void addChain(ConstraintWidget r2, int r3) {
        if (r3 != 0) goto L6;
        addHorizontalChain(r2);
        return;
    L6:
        if (r3 != 1) goto L9;
        addVerticalChain(r2);
        return;
    }

    public boolean addChildrenToSolver(LinearSystem r13) {
        boolean r0 = optimizeFor(64);
        addToSolver(r13, r0);
        int r1 = this.mChildren.size();
        int r3 = 0;
        boolean r4 = false;
    L4:
        if (r3 >= r1) goto L9;
        ConstraintWidget r6 = this.mChildren.get(r3);
        r6.setInBarrier(0, false);
        r6.setInBarrier(1, false);
        if ((r6 instanceof Barrier) == false) goto L8;
        r4 = true;
    L8:
        r3 = r3 + 1;
        goto L4
    L9:
        if (r4 == false) goto L16;
        int r32 = 0;
    L11:
        if (r32 >= r1) goto L16;
        ConstraintWidget r42 = this.mChildren.get(r32);
        if ((r42 instanceof Barrier) == false) goto L15;
        ((Barrier) r42).markWidgets();
    L15:
        r32 = r32 + 1;
    L16:
        this.mWidgetsToAdd.clear();
        int r33 = 0;
    L17:
        if (r33 >= r1) goto L26;
        ConstraintWidget r43 = this.mChildren.get(r33);
        if (r43.addFirst() == false) goto L24;
        if ((r43 instanceof VirtualLayout) == false) goto L23;
        this.mWidgetsToAdd.add(r43);
        goto L24
    L23:
        r43.addToSolver(r13, r0);
    L24:
        r33 = r33 + 1;
    L26:
        if (this.mWidgetsToAdd.size() <= 0) goto L41;
        int r34 = this.mWidgetsToAdd.size();
        Iterator<ConstraintWidget> r44 = this.mWidgetsToAdd.iterator();
    L29:
        if (r44.hasNext() == false) goto L34;
        VirtualLayout r62 = (VirtualLayout) r44.next();
        if (r62.contains(this.mWidgetsToAdd) == false) goto L29;
        r62.addToSolver(r13, r0);
        this.mWidgetsToAdd.remove(r62);
    L34:
        if (r34 != this.mWidgetsToAdd.size()) goto L26;
        Iterator<ConstraintWidget> r35 = this.mWidgetsToAdd.iterator();
    L37:
        if (r35.hasNext() == false) goto L39;
        r35.next().addToSolver(r13, r0);
        goto L37
    L39:
        this.mWidgetsToAdd.clear();
        goto L26
    L41:
        if (LinearSystem.USE_DEPENDENCY_ORDERING == false) goto L56;
        HashSet<ConstraintWidget> r9 = new HashSet();
        int r36 = 0;
    L43:
        if (r36 >= r1) goto L49;
        ConstraintWidget r45 = this.mChildren.get(r36);
        if (r45.addFirst() == true) goto L47;
        r9.add(r45);
    L47:
        r36 = r36 + 1;
        goto L43
    L49:
        if (getHorizontalDimensionBehaviour() != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) goto L51;
        int r10 = 0;
    L52:
        ConstraintWidgetContainer r63 = this;
        LinearSystem r8 = r13;
        r63.addChildrenToSolverByDependency(this, r8, r9, r10, false);
        Iterator<ConstraintWidget> r12 = r9.iterator();
    L54:
        if (r12.hasNext() == false) goto L75;
        ConstraintWidget r132 = r12.next();
        Optimizer.checkMatchParent(r63, r8, r132);
        r132.addToSolver(r8, r0);
    L75:
        if (r63.mHorizontalChainsSize <= 0) goto L78;
        Chain.applyChainConstraints(r63, r8, null, 0);
    L78:
        if (r63.mVerticalChainsSize <= 0) goto L80;
        Chain.applyChainConstraints(r63, r8, null, 1);
    L80:
        return true;
    L51:
        r10 = 1;
        goto L52
    L56:
        r63 = this;
        r8 = r13;
        int r122 = 0;
    L57:
        if (r122 >= r1) goto L75;
        ConstraintWidget r133 = r63.mChildren.get(r122);
        if ((r133 instanceof ConstraintWidgetContainer) == false) goto L70;
        ConstraintWidget.DimensionBehaviour[] r37 = r133.mListDimensionBehaviors;
        ConstraintWidget.DimensionBehaviour r46 = r37[0];
        ConstraintWidget.DimensionBehaviour r38 = r37[1];
        ConstraintWidget.DimensionBehaviour r7 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (r46 != r7) goto L63;
        r133.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
    L63:
        if (r38 != r7) goto L65;
        r133.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
    L65:
        r133.addToSolver(r8, r0);
        if (r46 != r7) goto L68;
        r133.setHorizontalDimensionBehaviour(r46);
    L68:
        if (r38 != r7) goto L73;
        r133.setVerticalDimensionBehaviour(r38);
    L73:
        r122 = r122 + 1;
        goto L57
    L70:
        Optimizer.checkMatchParent(r63, r8, r133);
        if (r133.addFirst() == true) goto L73;
        r133.addToSolver(r8, r0);
        goto L73
    }

    public void addHorizontalWrapMaxVariable(ConstraintAnchor r3) {
        WeakReference<ConstraintAnchor> r0 = this.mHorizontalWrapMax;
        if (r0 != null) goto L5;
    L10:
        this.mHorizontalWrapMax = new WeakReference(r3);
        return;
    L5:
        if (r0.get() == null) goto L10;
        if (r3.getFinalValue() > this.mHorizontalWrapMax.get().getFinalValue()) goto L10;
    }

    public void addHorizontalWrapMinVariable(ConstraintAnchor r3) {
        WeakReference<ConstraintAnchor> r0 = this.mHorizontalWrapMin;
        if (r0 != null) goto L5;
    L10:
        this.mHorizontalWrapMin = new WeakReference(r3);
        return;
    L5:
        if (r0.get() == null) goto L10;
        if (r3.getFinalValue() > this.mHorizontalWrapMin.get().getFinalValue()) goto L10;
    }

    public void addVerticalWrapMaxVariable(ConstraintAnchor r3) {
        WeakReference<ConstraintAnchor> r0 = this.mVerticalWrapMax;
        if (r0 != null) goto L5;
    L10:
        this.mVerticalWrapMax = new WeakReference(r3);
        return;
    L5:
        if (r0.get() == null) goto L10;
        if (r3.getFinalValue() > this.mVerticalWrapMax.get().getFinalValue()) goto L10;
    }

    public void addVerticalWrapMinVariable(ConstraintAnchor r3) {
        WeakReference<ConstraintAnchor> r0 = this.mVerticalWrapMin;
        if (r0 != null) goto L5;
    L10:
        this.mVerticalWrapMin = new WeakReference(r3);
        return;
    L5:
        if (r0.get() == null) goto L10;
        if (r3.getFinalValue() > this.mVerticalWrapMin.get().getFinalValue()) goto L10;
    }

    public boolean directMeasure(boolean r1) {
        return this.mDependencyGraph.directMeasure(r1);
    }

    public boolean directMeasureSetup(boolean r1) {
        return this.mDependencyGraph.directMeasureSetup(r1);
    }

    public boolean directMeasureWithOrientation(boolean r1, int r2) {
        return this.mDependencyGraph.directMeasureWithOrientation(r1, r2);
    }

    public void fillMetrics(Metrics r1) {
        this.mSystem.fillMetrics(r1);
    }

    public BasicMeasure.Measurer getMeasurer() {
        return this.mMeasurer;
    }

    public int getOptimizationLevel() {
        return this.mOptimizationLevel;
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public void getSceneString(StringBuilder r4) {
        r4.append(this.stringId + ":{\n");
        StringBuilder r0 = new StringBuilder("  actualWidth:");
        r0.append(this.mWidth);
        r4.append(r0.toString());
        r4.append("\n");
        r4.append("  actualHeight:" + this.mHeight);
        r4.append("\n");
        ArrayList<ConstraintWidget> r3 = getChildren();
        int r02 = r3.size();
        int r1 = 0;
    L3:
        if (r1 >= r02) goto L5;
        ConstraintWidget r2 = r3.get(r1);
        r1 = r1 + 1;
        r2.getSceneString(r4);
        r4.append(",\n");
        goto L3
    L5:
        r4.append("}");
    }

    public LinearSystem getSystem() {
        return this.mSystem;
    }

    public boolean handlesInternalConstraints() {
        return false;
    }

    public void invalidateGraph() {
        this.mDependencyGraph.invalidateGraph();
    }

    public void invalidateMeasures() {
        this.mDependencyGraph.invalidateMeasures();
    }

    public boolean isHeightMeasuredTooSmall() {
        return this.mHeightMeasuredTooSmall;
    }

    public boolean isRtl() {
        return this.mIsRtl;
    }

    public boolean isWidthMeasuredTooSmall() {
        return this.mWidthMeasuredTooSmall;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r16v1 */
    /* JADX WARN: Type inference failed for: r16v2 */
    /* JADX WARN: Type inference failed for: r16v3 */
    /* JADX WARN: Type inference failed for: r16v4 */
    /* JADX WARN: Type inference failed for: r16v7 */
    /* JADX WARN: Type inference failed for: r16v8 */
    /* JADX WARN: Type inference failed for: r18v1 */
    /* JADX WARN: Type inference failed for: r18v10 */
    /* JADX WARN: Type inference failed for: r18v2 */
    /* JADX WARN: Type inference failed for: r18v3 */
    /* JADX WARN: Type inference failed for: r18v4 */
    /* JADX WARN: Type inference failed for: r18v5 */
    /* JADX WARN: Type inference failed for: r18v6 */
    /* JADX WARN: Type inference failed for: r18v7 */
    /* JADX WARN: Type inference failed for: r18v8 */
    /* JADX WARN: Type inference failed for: r18v9 */
    /* JADX WARN: Type inference failed for: r6v23 */
    /* JADX WARN: Type inference failed for: r6v4 */
    /* JADX WARN: Type inference failed for: r6v5, types: [boolean] */
    @Override // androidx.constraintlayout.core.widgets.WidgetContainer
    public void layout() {
        int r2 = 0;
        this.mX = 0;
        this.mY = 0;
        this.mWidthMeasuredTooSmall = false;
        this.mHeightMeasuredTooSmall = false;
        int r3 = this.mChildren.size();
        int r0 = Math.max(0, getWidth());
        int r4 = Math.max(0, getHeight());
        ConstraintWidget.DimensionBehaviour[] r5 = this.mListDimensionBehaviors;
        boolean r6 = true;
        ConstraintWidget.DimensionBehaviour r7 = r5[1];
        ConstraintWidget.DimensionBehaviour r52 = r5[0];
        if (this.mPass == 0) goto L5;
    L28:
        char r8 = 2;
        if (r3 <= 2) goto L50;
        ConstraintWidget.DimensionBehaviour r9 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (r52 == r9) goto L34;
        if (r7 != r9) goto L50;
    L34:
        if (Optimizer.enabled(this.mOptimizationLevel, 1024) == false) goto L50;
        if (Grouping.simpleSolvingPass(this, getMeasurer()) == false) goto L50;
        if (r52 == r9) goto L39;
    L43:
        if (r7 == r9) goto L45;
    L49:
        int r92 = r4;
        int r42 = r0;
        boolean r02 = true;
    L52:
        if (optimizeFor(64) == false) goto L54;
    L57:
        boolean r11 = true;
    L58:
        LinearSystem r12 = this.mSystem;
        r12.graphOptimizer = false;
        r12.newgraphOptimizer = false;
        if (this.mOptimizationLevel == 0) goto L62;
        if (r11 == false) goto L62;
        r12.newgraphOptimizer = true;
    L62:
        ArrayList<ConstraintWidget> r112 = this.mChildren;
        ConstraintWidget.DimensionBehaviour r122 = getHorizontalDimensionBehaviour();
        ConstraintWidget.DimensionBehaviour r13 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (r122 != r13) goto L65;
    L68:
        boolean r123 = true;
    L69:
        resetChains();
        int r132 = 0;
    L70:
        if (r132 >= r3) goto L75;
        ConstraintWidget r14 = this.mChildren.get(r132);
        if ((r14 instanceof WidgetContainer) == false) goto L74;
        ((WidgetContainer) r14).layout();
    L74:
        r132 = r132 + 1;
        goto L70
    L75:
        boolean r10 = optimizeFor(64);
        boolean r133 = r02;
        int r03 = 0;
        boolean r142 = true;
    L76:
        if (r142 == false) goto L170;
        int r15 = r03 + 1;
        this.mSystem.reset();     // Catch: Exception -> L117
        resetChains();     // Catch: Exception -> L117
        createObjectVariables(this.mSystem);     // Catch: Exception -> L117
        int r04 = r2;
    L80:
        if (r04 >= r3) goto L89;
        int r16 = r2;
        char r17 = r8;
        this.mChildren.get(r04).createObjectVariables(this.mSystem);     // Catch: Exception -> L85
        r04 = r04 + 1;     // Catch: Exception -> L85
        r2 = r16 == true ? 1 : 0;
        r8 = r17;
    L85:
        e = e;
    L86:
        ?? r18 = r6;
        ?? r162 = r16;
    L119:
        e.printStackTrace();
        System.out.println("EXCEPTION : " + e);
    L120:
        LinearSystem r05 = this.mSystem;
        if (r142 == false) goto L123;
        boolean r06 = updateChildrenFromSolver(r05, Optimizer.sFlags);
    L128:
        if (r123 == false) goto L144;
        if (r15 >= 8) goto L144;
        if (Optimizer.sFlags[r17] == false) goto L144;
        int r62 = r162;
        int r82 = r62;
        int r143 = r82;
    L133:
        if (r62 >= r3) goto L135;
        ConstraintWidget r22 = this.mChildren.get(r62);
        r82 = Math.max(r82, r22.mX + r22.getWidth());
        r143 = Math.max(r143, r22.mY + r22.getHeight());
        r62 = r62 + 1;
        r06 = r06;
        goto L133
    L135:
        boolean r19 = r06;
        int r07 = Math.max(this.mMinWidth, r82);
        int r23 = Math.max(this.mMinHeight, r143);
        ConstraintWidget.DimensionBehaviour r63 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (r52 == r63) goto L138;
    L140:
        if (r7 == r63) goto L142;
    L145:
        int r08 = Math.max(this.mMinWidth, getWidth());
        if (r08 <= getWidth()) goto L148;
        setWidth(r08);
        this.mListDimensionBehaviors[r162] = ConstraintWidget.DimensionBehaviour.FIXED;
        r133 = r18;
        r19 = r133;
    L148:
        int r09 = Math.max(this.mMinHeight, getHeight());
        if (r09 <= getHeight()) goto L151;
        setHeight(r09);
        this.mListDimensionBehaviors[r18] = ConstraintWidget.DimensionBehaviour.FIXED;
        r133 = r18;
        r19 = r133;
    L151:
        if (r133 == true) goto L165;
        ConstraintWidget.DimensionBehaviour r010 = this.mListDimensionBehaviors[r162];
        ConstraintWidget.DimensionBehaviour r24 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (r010 != r24) goto L158;
        if (r42 <= 0) goto L158;
        if (getWidth() <= r42) goto L158;
        boolean r64 = r18;
        this.mWidthMeasuredTooSmall = r64;
        this.mListDimensionBehaviors[r162] = ConstraintWidget.DimensionBehaviour.FIXED;
        setWidth(r42);
        r133 = r64 ? 1 : 0;
        r19 = r133;
        ?? r65 = r64;
    L160:
        if (this.mListDimensionBehaviors[r65] != r24) goto L165;
        if (r92 <= 0) goto L165;
        if (getHeight() <= r92) goto L165;
        this.mHeightMeasuredTooSmall = r65;
        this.mListDimensionBehaviors[r65] = ConstraintWidget.DimensionBehaviour.FIXED;
        setHeight(r92);
        int r011 = 8;
        r133 = true;
        r19 = true;
    L166:
        if (r15 <= r011) goto L168;
        r142 = r162;
    L169:
        r03 = r15;
        r2 = r162;
        r8 = r17;
        r6 = true;
        goto L76
    L168:
        r142 = r19;
    L158:
        r65 = r18;
    L165:
        r011 = 8;
        goto L166
    L142:
        if (getHeight() >= r23) goto L145;
        setHeight(r23);
        this.mListDimensionBehaviors[r18] = r63;
        r133 = r18;
        r19 = r133;
        goto L145
    L138:
        if (getWidth() >= r07) goto L140;
        setWidth(r07);
        this.mListDimensionBehaviors[r162] = r63;
        r133 = r18;
        r19 = r133;
    L144:
        r19 = r06;
        goto L145
    L123:
        updateFromSolver(r05, r10);
        int r012 = r162;
    L124:
        if (r012 >= r3) goto L126;
        this.mChildren.get(r012).updateFromSolver(this.mSystem, r10);
        r012 = r012 + 1;
        goto L124
    L126:
        r06 = r162;
    L87:
        e = e;
        r17 = r8;
        goto L86
    L89:
        r162 = r2;
        r17 = r8;
        r142 = addChildrenToSolver(this.mSystem);     // Catch: Exception -> L85
        WeakReference<ConstraintAnchor> r013 = this.mVerticalWrapMin;     // Catch: Exception -> L85
        if (r013 != null) goto L92;
    L98:
        r18 = r6;
    L99:
        WeakReference<ConstraintAnchor> r014 = this.mVerticalWrapMax;     // Catch: Exception -> L96
        if (r014 != null) goto L102;
    L104:
        WeakReference<ConstraintAnchor> r015 = this.mHorizontalWrapMin;     // Catch: Exception -> L96
        if (r015 != null) goto L107;
    L109:
        WeakReference<ConstraintAnchor> r016 = this.mHorizontalWrapMax;     // Catch: Exception -> L96
        if (r016 != null) goto L112;
    L114:
        if (r142 == false) goto L120;
        this.mSystem.minimize();     // Catch: Exception -> L96
        goto L120
    L112:
        if (r016.get() == null) goto L114;
        addMaxWrap(this.mHorizontalWrapMax.get(), this.mSystem.createObjectVariable(this.mRight));     // Catch: Exception -> L96
        this.mHorizontalWrapMax = null;     // Catch: Exception -> L96
        goto L114
    L107:
        if (r015.get() == null) goto L109;
        addMinWrap(this.mHorizontalWrapMin.get(), this.mSystem.createObjectVariable(this.mLeft));     // Catch: Exception -> L96
        this.mHorizontalWrapMin = null;     // Catch: Exception -> L96
        goto L109
    L102:
        if (r014.get() == null) goto L104;
        addMaxWrap(this.mVerticalWrapMax.get(), this.mSystem.createObjectVariable(this.mBottom));     // Catch: Exception -> L96
        this.mVerticalWrapMax = null;     // Catch: Exception -> L96
    L96:
        e = e;
        r162 = r162;
        r18 = r18;
        goto L119
    L92:
        if (r013.get() == null) goto L98;
        r18 = r6;
        addMinWrap(this.mVerticalWrapMin.get(), this.mSystem.createObjectVariable(this.mTop));     // Catch: Exception -> L96
        this.mVerticalWrapMin = null;     // Catch: Exception -> L96
        r18 = r18;
    L117:
        e = e;
        r162 = r2;
        r18 = r6;
        r17 = r8;
        goto L119
    L170:
        int r163 = r2;
        this.mChildren = r112;
        if (r133 == false) goto L173;
        ConstraintWidget.DimensionBehaviour[] r017 = this.mListDimensionBehaviors;
        r017[r163] = r52;
        r017[1] = r7;
    L173:
        resetSolverVariables(this.mSystem.getCache());
        return;
    L65:
        if (getVerticalDimensionBehaviour() == r13) goto L68;
        r123 = false;
        goto L69
    L54:
        if (optimizeFor(128) == true) goto L57;
        r11 = false;
        goto L58
    L45:
        if (r4 >= getHeight()) goto L48;
        if (r4 <= 0) goto L48;
        setHeight(r4);
        this.mHeightMeasuredTooSmall = true;
    L48:
        r4 = getHeight();
        goto L49
    L39:
        if (r0 >= getWidth()) goto L42;
        if (r0 <= 0) goto L42;
        setWidth(r0);
        this.mWidthMeasuredTooSmall = true;
    L42:
        r0 = getWidth();
    L50:
        r92 = r4;
        r42 = r0;
        r02 = false;
        goto L52
    L5:
        if (Optimizer.enabled(this.mOptimizationLevel, 1) == false) goto L28;
        Direct.solvingPass(this, getMeasurer());
        int r83 = 0;
    L7:
        if (r83 >= r3) goto L28;
        ConstraintWidget r93 = this.mChildren.get(r83);
        if (r93.isMeasureRequested() == false) goto L27;
        if ((r93 instanceof Guideline) == true) goto L27;
        if ((r93 instanceof Barrier) == true) goto L27;
        if ((r93 instanceof VirtualLayout) == true) goto L27;
        if (r93.isInVirtualLayout() == true) goto L27;
        ConstraintWidget.DimensionBehaviour r102 = r93.getDimensionBehaviour(0);
        ConstraintWidget.DimensionBehaviour r113 = r93.getDimensionBehaviour(1);
        ConstraintWidget.DimensionBehaviour r124 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        if (r102 == r124) goto L21;
    L26:
        measure(0, r93, this.mMeasurer, new BasicMeasure.Measure(), BasicMeasure.Measure.SELF_DIMENSIONS);
        goto L27
    L21:
        if (r93.mMatchConstraintDefaultWidth == 1) goto L26;
        if (r113 != r124) goto L26;
        if (r93.mMatchConstraintDefaultHeight == 1) goto L26;
    L27:
        r83 = r83 + 1;
        goto L7
    }

    public long measure(int r12, int r13, int r14, int r15, int r16, int r17, int r18, int r19, int r20) {
        this.mPaddingLeft = r19;
        this.mPaddingTop = r20;
        return this.mBasicMeasureSolver.solverMeasure(this, r12, r19, r20, r13, r14, r15, r16, r17, r18);
    }

    public boolean optimizeFor(int r1) {
        if ((this.mOptimizationLevel & r1) != r1) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // androidx.constraintlayout.core.widgets.WidgetContainer, androidx.constraintlayout.core.widgets.ConstraintWidget
    public void reset() {
        this.mSystem.reset();
        this.mPaddingLeft = 0;
        this.mPaddingRight = 0;
        this.mPaddingTop = 0;
        this.mPaddingBottom = 0;
        this.mSkipSolver = false;
        super.reset();
    }

    public void setMeasurer(BasicMeasure.Measurer r1) {
        this.mMeasurer = r1;
        this.mDependencyGraph.setMeasurer(r1);
    }

    public void setOptimizationLevel(int r1) {
        this.mOptimizationLevel = r1;
        LinearSystem.USE_DEPENDENCY_ORDERING = optimizeFor(512);
    }

    public void setPass(int r1) {
        this.mPass = r1;
    }

    public void setRtl(boolean r1) {
        this.mIsRtl = r1;
    }

    public boolean updateChildrenFromSolver(LinearSystem r5, boolean[] r6) {
        int r1 = 0;
        r6[2] = false;
        boolean r62 = optimizeFor(64);
        updateFromSolver(r5, r62);
        int r0 = this.mChildren.size();
        boolean r2 = false;
    L3:
        if (r1 >= r0) goto L8;
        ConstraintWidget r3 = this.mChildren.get(r1);
        r3.updateFromSolver(r5, r62);
        if (r3.hasDimensionOverride() == false) goto L7;
        r2 = true;
    L7:
        r1 = r1 + 1;
        goto L3
    L8:
        return r2;
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public void updateFromRuns(boolean r4, boolean r5) {
        super.updateFromRuns(r4, r5);
        int r0 = this.mChildren.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        this.mChildren.get(r1).updateFromRuns(r4, r5);
        r1 = r1 + 1;
        goto L3
    }

    public void updateHierarchy() {
        this.mBasicMeasureSolver.updateHierarchy(this);
    }
}
