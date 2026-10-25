package androidx.constraintlayout.core.widgets;

import androidx.constraintlayout.core.Cache;
import androidx.constraintlayout.core.LinearSystem;
import androidx.constraintlayout.core.SolverVariable;
import androidx.constraintlayout.core.state.WidgetFrame;
import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.analyzer.ChainRun;
import androidx.constraintlayout.core.widgets.analyzer.DependencyNode;
import androidx.constraintlayout.core.widgets.analyzer.HorizontalWidgetRun;
import androidx.constraintlayout.core.widgets.analyzer.VerticalWidgetRun;
import androidx.constraintlayout.core.widgets.analyzer.WidgetRun;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import okhttp3.internal.url._UrlKt;
import okio.Buffer$$ExternalSyntheticBUOutline2;

/* loaded from: classes3.dex */
public class ConstraintWidget {
    public static float DEFAULT_BIAS = 0.5f;
    public WidgetFrame frame;
    public ChainRun horizontalChainRun;
    public int horizontalGroup;
    public boolean[] isTerminalWidget;
    protected ArrayList<ConstraintAnchor> mAnchors;
    private boolean mAnimated;
    public ConstraintAnchor mBaseline;
    int mBaselineDistance;
    public ConstraintAnchor mBottom;
    public ConstraintAnchor mCenter;
    ConstraintAnchor mCenterX;
    ConstraintAnchor mCenterY;
    public float mCircleConstraintAngle;
    private Object mCompanionWidget;
    private int mContainerItemSkip;
    private String mDebugName;
    public float mDimensionRatio;
    protected int mDimensionRatioSide;
    boolean mGroupsToSolver;
    private boolean mHasBaseline;
    int mHeight;
    private int mHeightOverride;
    float mHorizontalBiasPercent;
    boolean mHorizontalChainFixedPosition;
    int mHorizontalChainStyle;
    ConstraintWidget mHorizontalNextWidget;
    public int mHorizontalResolution;
    public HorizontalWidgetRun mHorizontalRun;
    private boolean mHorizontalSolvingPass;
    boolean mHorizontalWrapVisited;
    private boolean mInPlaceholder;
    private boolean mInVirtualLayout;
    private boolean[] mIsInBarrier;
    private int mLastHorizontalMeasureSpec;
    private int mLastVerticalMeasureSpec;
    public ConstraintAnchor mLeft;
    public ConstraintAnchor[] mListAnchors;
    public DimensionBehaviour[] mListDimensionBehaviors;
    protected ConstraintWidget[] mListNextMatchConstraintsWidget;
    public int mMatchConstraintDefaultHeight;
    public int mMatchConstraintDefaultWidth;
    public int mMatchConstraintMaxHeight;
    public int mMatchConstraintMaxWidth;
    public int mMatchConstraintMinHeight;
    public int mMatchConstraintMinWidth;
    public float mMatchConstraintPercentHeight;
    public float mMatchConstraintPercentWidth;
    private int[] mMaxDimension;
    private boolean mMeasureRequested;
    protected int mMinHeight;
    protected int mMinWidth;
    protected ConstraintWidget[] mNextChainWidget;
    protected int mOffsetX;
    protected int mOffsetY;
    private boolean mOptimizeWrapO;
    private boolean mOptimizeWrapOnResolved;
    public ConstraintWidget mParent;
    int mRelX;
    int mRelY;
    float mResolvedDimensionRatio;
    int mResolvedDimensionRatioSide;
    boolean mResolvedHasRatio;
    private boolean mResolvedHorizontal;
    public int[] mResolvedMatchConstraintDefault;
    private boolean mResolvedVertical;
    public ConstraintAnchor mRight;
    public ConstraintAnchor mTop;
    private String mType;
    float mVerticalBiasPercent;
    boolean mVerticalChainFixedPosition;
    int mVerticalChainStyle;
    ConstraintWidget mVerticalNextWidget;
    public int mVerticalResolution;
    public VerticalWidgetRun mVerticalRun;
    private boolean mVerticalSolvingPass;
    boolean mVerticalWrapVisited;
    private int mVisibility;
    public float[] mWeight;
    int mWidth;
    private int mWidthOverride;
    private int mWrapBehaviorInParent;
    protected int mX;
    protected int mY;
    public boolean measured;
    public WidgetRun[] run;
    public String stringId;
    public ChainRun verticalChainRun;
    public int verticalGroup;

    /* renamed from: androidx.constraintlayout.core.widgets.ConstraintWidget$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintAnchor$Type = null;

        static {
            int[] r0 = new int[ConstraintAnchor.Type.values().length];
            $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintAnchor$Type = r0;
            r0[ConstraintAnchor.Type.LEFT.ordinal()] = 1;     // Catch: NoSuchFieldError -> L13
        L24:
            $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.TOP.ordinal()] = 2;     // Catch: NoSuchFieldError -> L14
        L28:
            $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.RIGHT.ordinal()] = 3;     // Catch: NoSuchFieldError -> L15
        L38:
            $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.BOTTOM.ordinal()] = 4;     // Catch: NoSuchFieldError -> L16
        L26:
            $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.BASELINE.ordinal()] = 5;     // Catch: NoSuchFieldError -> L17
        L30:
            $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.CENTER.ordinal()] = 6;     // Catch: NoSuchFieldError -> L18
        L32:
            $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.CENTER_X.ordinal()] = 7;     // Catch: NoSuchFieldError -> L19
        L34:
            $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.CENTER_Y.ordinal()] = 8;     // Catch: NoSuchFieldError -> L20
        L22:
            $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.NONE.ordinal()] = 9;     // Catch: NoSuchFieldError -> L21
            return;
        }
    }

    public enum DimensionBehaviour extends Enum<DimensionBehaviour> {
        private static final /* synthetic */ DimensionBehaviour[] $VALUES = null;
        public static final DimensionBehaviour FIXED = null;
        public static final DimensionBehaviour MATCH_CONSTRAINT = null;
        public static final DimensionBehaviour MATCH_PARENT = null;
        public static final DimensionBehaviour WRAP_CONTENT = null;

        private static /* synthetic */ DimensionBehaviour[] $values() {
            return new DimensionBehaviour[]{FIXED, WRAP_CONTENT, MATCH_CONSTRAINT, MATCH_PARENT};
        }

        static {
            FIXED = new DimensionBehaviour("FIXED", 0);
            WRAP_CONTENT = new DimensionBehaviour("WRAP_CONTENT", 1);
            MATCH_CONSTRAINT = new DimensionBehaviour("MATCH_CONSTRAINT", 2);
            MATCH_PARENT = new DimensionBehaviour("MATCH_PARENT", 3);
            $VALUES = $values();
        }

        DimensionBehaviour(String r1, int r2) {
        }

        public static DimensionBehaviour valueOf(String r1) {
            return (DimensionBehaviour) Enum.valueOf(DimensionBehaviour.class, r1);
        }

        public static DimensionBehaviour[] values() {
            return (DimensionBehaviour[]) $VALUES.clone();
        }
    }

    static {
    }

    public ConstraintWidget() {
        this.measured = false;
        this.run = new WidgetRun[2];
        this.mHorizontalRun = null;
        this.mVerticalRun = null;
        this.isTerminalWidget = new boolean[]{true, true};
        this.mResolvedHasRatio = false;
        this.mMeasureRequested = true;
        this.mOptimizeWrapO = false;
        this.mOptimizeWrapOnResolved = true;
        this.mWidthOverride = -1;
        this.mHeightOverride = -1;
        this.frame = new WidgetFrame(this);
        this.mResolvedHorizontal = false;
        this.mResolvedVertical = false;
        this.mHorizontalSolvingPass = false;
        this.mVerticalSolvingPass = false;
        this.mHorizontalResolution = -1;
        this.mVerticalResolution = -1;
        this.mWrapBehaviorInParent = 0;
        this.mMatchConstraintDefaultWidth = 0;
        this.mMatchConstraintDefaultHeight = 0;
        this.mResolvedMatchConstraintDefault = new int[2];
        this.mMatchConstraintMinWidth = 0;
        this.mMatchConstraintMaxWidth = 0;
        this.mMatchConstraintPercentWidth = 1.0f;
        this.mMatchConstraintMinHeight = 0;
        this.mMatchConstraintMaxHeight = 0;
        this.mMatchConstraintPercentHeight = 1.0f;
        this.mResolvedDimensionRatioSide = -1;
        this.mResolvedDimensionRatio = 1.0f;
        this.mMaxDimension = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
        this.mCircleConstraintAngle = Float.NaN;
        this.mHasBaseline = false;
        this.mInVirtualLayout = false;
        this.mLastHorizontalMeasureSpec = 0;
        this.mLastVerticalMeasureSpec = 0;
        this.mLeft = new ConstraintAnchor(this, ConstraintAnchor.Type.LEFT);
        this.mTop = new ConstraintAnchor(this, ConstraintAnchor.Type.TOP);
        this.mRight = new ConstraintAnchor(this, ConstraintAnchor.Type.RIGHT);
        this.mBottom = new ConstraintAnchor(this, ConstraintAnchor.Type.BOTTOM);
        this.mBaseline = new ConstraintAnchor(this, ConstraintAnchor.Type.BASELINE);
        this.mCenterX = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_X);
        this.mCenterY = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_Y);
        ConstraintAnchor r11 = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER);
        this.mCenter = r11;
        this.mListAnchors = new ConstraintAnchor[]{this.mLeft, this.mRight, this.mTop, this.mBottom, this.mBaseline, r11};
        this.mAnchors = new ArrayList();
        this.mIsInBarrier = new boolean[2];
        DimensionBehaviour r4 = DimensionBehaviour.FIXED;
        this.mListDimensionBehaviors = new DimensionBehaviour[]{r4, r4};
        this.mParent = null;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mDimensionRatio = 0.0f;
        this.mDimensionRatioSide = -1;
        this.mX = 0;
        this.mY = 0;
        this.mRelX = 0;
        this.mRelY = 0;
        this.mOffsetX = 0;
        this.mOffsetY = 0;
        this.mBaselineDistance = 0;
        float r42 = DEFAULT_BIAS;
        this.mHorizontalBiasPercent = r42;
        this.mVerticalBiasPercent = r42;
        this.mContainerItemSkip = 0;
        this.mVisibility = 0;
        this.mAnimated = false;
        this.mDebugName = null;
        this.mType = null;
        this.mGroupsToSolver = false;
        this.mHorizontalChainStyle = 0;
        this.mVerticalChainStyle = 0;
        this.mWeight = new float[]{-1.0f, -1.0f};
        this.mListNextMatchConstraintsWidget = new ConstraintWidget[]{null, null};
        this.mNextChainWidget = new ConstraintWidget[]{null, null};
        this.mHorizontalNextWidget = null;
        this.mVerticalNextWidget = null;
        this.horizontalGroup = -1;
        this.verticalGroup = -1;
        addAnchors();
    }

    private void addAnchors() {
        this.mAnchors.add(this.mLeft);
        this.mAnchors.add(this.mTop);
        this.mAnchors.add(this.mRight);
        this.mAnchors.add(this.mBottom);
        this.mAnchors.add(this.mCenterX);
        this.mAnchors.add(this.mCenterY);
        this.mAnchors.add(this.mCenter);
        this.mAnchors.add(this.mBaseline);
    }

    private void applyConstraints(LinearSystem r31, boolean r32, boolean r33, boolean r34, boolean r35, SolverVariable r36, SolverVariable r37, DimensionBehaviour r38, boolean r39, ConstraintAnchor r40, ConstraintAnchor r41, int r42, int r43, int r44, int r45, float r46, boolean r47, boolean r48, boolean r49, boolean r50, boolean r51, int r52, int r53, int r54, int r55, float r56, boolean r57) {
        LinearSystem r1 = r31;
        SolverVariable r6 = r1.createObjectVariable(r40);
        SolverVariable r7 = r1.createObjectVariable(r41);
        SolverVariable r15 = r1.createObjectVariable(r40.getTarget());
        SolverVariable r8 = r1.createObjectVariable(r41.getTarget());
        LinearSystem.getMetrics();
        boolean r16 = r40.isConnected();
        boolean r17 = r41.isConnected();
        boolean r18 = this.mCenter.isConnected();
        if (r17 == false) goto L5;
        int r9 = (r16 ? 1 : 0) + 1;
    L6:
        if (r18 == false) goto L8;
        r9 = r9 + 1;
    L8:
        if (r47 == false) goto L11;
        int r11 = 3;
    L12:
        int r82 = r38.ordinal();
        if (r82 == 0) goto L17;
        if (r82 == 1) goto L17;
        if (r82 != 2) goto L17;
        if (r11 == 4) goto L17;
        boolean r83 = true;
    L21:
        int r3 = this.mWidthOverride;
        if (r3 == (-1)) goto L25;
        if (r32 == false) goto L25;
        this.mWidthOverride = -1;
        r43 = r3;
        r83 = false;
    L25:
        int r310 = this.mHeightOverride;
        if (r310 == (-1)) goto L29;
        if (r32 == true) goto L29;
        this.mHeightOverride = -1;
        r83 = false;
    L30:
        int r432 = r310;
        if (this.mVisibility != 8) goto L33;
        int r84 = 0;
        boolean r13 = false;
    L34:
        if (r57 == false) goto L43;
        if (r16 == true) goto L40;
        if (r17 == true) goto L40;
        if (r18 == true) goto L40;
        r1.addEquality(r6, r42);
    L39:
        boolean r23 = r13;
        int r132 = 8;
    L44:
        if (r23 == true) goto L55;
        if (r39 == false) goto L52;
        r1.addEquality(r7, r6, 0, 3);
        if (r44 <= 0) goto L50;
        r1.addGreaterThan(r7, r6, r44, 8);
    L50:
        if (r45 >= Integer.MAX_VALUE) goto L53;
        r1.addLowerThan(r7, r6, r45, 8);
    L53:
        int r311 = r55;
        SolverVariable r5 = r7;
        int r2 = r9;
        SolverVariable r133 = r8;
        boolean r24 = r23;
        boolean r19 = r35;
        int r232 = r54;
    L108:
        if (r57 == false) goto L341;
        if (r49 == false) goto L111;
        SolverVariable r112 = r37;
        int r92 = 2;
        int r152 = 8;
        char r26 = 1;
    L342:
        if (r2 >= r92) goto L372;
        if (r33 == false) goto L373;
        if (r19 == false) goto L374;
        r1.addGreaterThan(r6, r36, 0, r152);
        if (r32 == false) goto L348;
    L351:
        char r312 = r26;
    L352:
        if (r32 == true) goto L363;
        ConstraintAnchor r0 = this.mBaseline.mTarget;
        if (r0 == null) goto L363;
        ConstraintWidget r02 = r0.mOwner;
        if (r02.mDimensionRatio == 0.0f) goto L362;
        DimensionBehaviour[] r03 = r02.mListDimensionBehaviors;
        DimensionBehaviour r22 = r03[0];
        DimensionBehaviour r313 = DimensionBehaviour.MATCH_CONSTRAINT;
        if (r22 != r313) goto L362;
        if (r03[r26] != r313) goto L362;
        r312 = r26;
    L362:
        r312 = 0;
    L363:
        if (r312 == 0) goto L375;
        r1.addGreaterThan(r112, r5, 0, r152);
        return;
    L375:
        return;
    L348:
        if (this.mBaseline.mTarget == null) goto L351;
        r312 = 0;
        goto L352
    L374:
        return;
    L373:
        return;
    L372:
        return;
    L111:
        if (r16 == true) goto L115;
        if (r17 == true) goto L115;
        if (r18 == true) goto L115;
    L114:
        SolverVariable r25 = r133;
        int r4 = 5;
    L318:
        boolean r182 = r33;
    L317:
        int r314 = r4;
    L319:
        if (r182 == false) goto L369;
        if (r19 == true) goto L322;
        return;
    L322:
        if (r41.mTarget == null) goto L325;
        int r134 = r41.getMargin();
    L326:
        if (r25 != r37) goto L328;
        return;
    L328:
        if (this.mOptimizeWrapO == true) goto L330;
    L339:
        r1.addGreaterThan(r37, r5, r134, r314);
        return;
    L330:
        if (r5.isFinalValue == false) goto L339;
        ConstraintWidget r04 = this.mParent;
        if (r04 == null) goto L339;
        ConstraintWidgetContainer r05 = (ConstraintWidgetContainer) r04;
        if (r32 == false) goto L337;
        r05.addHorizontalWrapMaxVariable(r41);
        return;
    L337:
        r05.addVerticalWrapMaxVariable(r41);
        return;
    L325:
        r134 = 0;
        goto L326
    L369:
        return;
    L115:
        if (r16 == false) goto L124;
        if (r17 == true) goto L124;
        ConstraintWidget r27 = r40.mTarget.mOwner;
        if (r33 == true) goto L120;
    L122:
        r314 = 5;
    L123:
        r182 = r33;
        r25 = r133;
        goto L319
    L120:
        if ((r27 instanceof Barrier) == false) goto L122;
        r314 = 8;
    L124:
        if (r16 == true) goto L139;
        if (r17 == false) goto L139;
        r1.addEquality(r5, r133, -r41.getMargin(), 8);
        if (r33 == false) goto L114;
        if (this.mOptimizeWrapO == true) goto L131;
    L138:
        r4 = 5;
        r1.addGreaterThan(r6, r36, 0, 5);
        r25 = r133;
        goto L318
    L131:
        if (r6.isFinalValue == false) goto L138;
        ConstraintWidget r28 = this.mParent;
        if (r28 == null) goto L138;
        ConstraintWidgetContainer r29 = (ConstraintWidgetContainer) r28;
        if (r32 == false) goto L137;
        r29.addHorizontalWrapMinVariable(r40);
        goto L114
    L137:
        r29.addVerticalWrapMinVariable(r40);
    L139:
        if (r16 == false) goto L114;
        if (r17 == false) goto L114;
        ConstraintWidget r210 = r40.mTarget.mOwner;
        ConstraintWidget r72 = r41.mTarget.mOwner;
        ConstraintWidget r85 = getParent();
        int r162 = 6;
        if (r24 == false) goto L215;
        if (r11 != 0) goto L164;
        if (r311 != 0) goto L153;
        if (r232 != 0) goto L153;
        if (r15.isFinalValue == true) goto L149;
    L152:
        int r315 = 8;
        int r172 = 8;
        boolean r183 = false;
        boolean r222 = true;
        boolean r252 = false;
    L155:
        if ((r210 instanceof Barrier) == false) goto L157;
    L162:
        SolverVariable r93 = r133;
        SolverVariable r135 = r6;
        SolverVariable r62 = r93;
        boolean r262 = r183;
        boolean r272 = r222;
        boolean r94 = true;
        int r20 = 4;
    L160:
        int r223 = r315;
        SolverVariable r316 = r15;
        int r184 = 6;
    L161:
        SolverVariable r153 = r37;
    L231:
        if (r252 == false) goto L235;
        if (r316 != r62) goto L235;
        if (r210 == r85) goto L235;
        r252 = false;
        boolean r282 = false;
    L236:
        if (r262 == false) goto L245;
        if (r24 == true) goto L243;
        if (r48 == true) goto L243;
        if (r50 == true) goto L243;
        if (r316 != r36) goto L243;
        if (r62 != r153) goto L243;
        boolean r263 = r94;
        int r95 = 8;
        r182 = false;
        r223 = 8;
        r282 = false;
    L244:
        SolverVariable r12 = r135;
        ConstraintWidget r136 = r210;
        ConstraintWidget r122 = r72;
        ConstraintWidget r14 = r85;
        int r154 = 8;
        SolverVariable r73 = r5;
        r1.addCentering(r12, r316, r40.getMargin(), r46, r62, r73, r41.getMargin(), r95);
        SolverVariable r58 = r62;
        SolverVariable r63 = r12;
        r25 = r58;
        r5 = r73;
    L247:
        if (this.mVisibility == r154) goto L249;
    L251:
        if (r252 == false) goto L262;
        if (r182 == false) goto L260;
        if (r316 == r25) goto L260;
        if (r24 == true) goto L260;
        if ((r136 instanceof Barrier) == false) goto L258;
    L259:
        int r410 = 6;
    L261:
        r1.addGreaterThan(r63, r316, r40.getMargin(), r410);
        r1.addLowerThan(r5, r25, -r41.getMargin(), r410);
        r223 = r410;
        goto L262
    L258:
        if ((r122 instanceof Barrier) == true) goto L259;
    L260:
        r410 = r223;
    L262:
        if (r182 == false) goto L270;
        if (r51 == false) goto L270;
        if ((r136 instanceof Barrier) == true) goto L270;
        if ((r122 instanceof Barrier) == true) goto L270;
        if (r122 == r14) goto L270;
        int r411 = 6;
        int r74 = 6;
    L271:
        if (r263 == false) goto L301;
        if (r272 == false) goto L293;
        if (r50 == false) goto L275;
        if (r34 == false) goto L293;
    L275:
        if (r136 == r14) goto L280;
        if (r122 == r14) goto L280;
        r162 = r411;
    L280:
        if ((r136 instanceof Guideline) == false) goto L282;
    L283:
        r162 = 5;
    L285:
        if ((r136 instanceof Barrier) == false) goto L287;
    L288:
        r162 = 5;
    L289:
        if (r50 == false) goto L291;
        int r86 = 5;
    L292:
        r411 = Math.max(r86, r411);
        goto L293
    L291:
        r86 = r162;
        goto L292
    L287:
        if ((r122 instanceof Barrier) == false) goto L289;
    L282:
        if ((r122 instanceof Guideline) == false) goto L285;
    L293:
        if (r182 == false) goto L300;
        r411 = Math.min(r74, r411);
        if (r47 == false) goto L300;
        if (r50 == true) goto L300;
        if (r136 == r14) goto L299;
        if (r122 != r14) goto L300;
    L299:
        r411 = 4;
    L300:
        r1.addEquality(r63, r316, r40.getMargin(), r411);
        r1.addEquality(r5, r25, -r41.getMargin(), r411);
    L301:
        if (r182 == false) goto L307;
        if (r36 != r316) goto L304;
        int r412 = r40.getMargin();
    L305:
        if (r316 == r36) goto L307;
        r1.addGreaterThan(r63, r36, r412, 5);
        goto L307
    L304:
        r412 = 0;
    L307:
        if (r182 == false) goto L315;
        if (r24 == false) goto L315;
        if (r44 != 0) goto L315;
        if (r232 != 0) goto L315;
        if (r24 == true) goto L313;
    L316:
        r4 = 5;
        r1.addGreaterThan(r5, r63, 0, 5);
        goto L317
    L313:
        if (r11 != 3) goto L316;
        r1.addGreaterThan(r5, r63, 0, r154);
    L315:
        r4 = 5;
    L270:
        r411 = r20;
        r74 = r223;
        r263 = r282;
        goto L271
    L249:
        if (r41.hasDependents() == true) goto L251;
        return;
    L243:
        r263 = r94;
        r95 = r184;
        r182 = r33;
        goto L244
    L245:
        SolverVariable r123 = r135;
        r136 = r210;
        r25 = r62;
        r63 = r123;
        r122 = r72;
        r14 = r85;
        r263 = r94;
        r154 = 8;
        r182 = r33;
    L235:
        r282 = r94;
        goto L236
    L157:
        if ((r72 instanceof Barrier) == true) goto L162;
        SolverVariable r96 = r133;
        r135 = r6;
        r62 = r96;
        r20 = r172;
        r262 = r183;
        r272 = r222;
        r94 = true;
        goto L160
    L149:
        if (r133.isFinalValue == false) goto L152;
        r1.addEquality(r6, r15, r40.getMargin(), 8);
        r1.addEquality(r5, r133, -r41.getMargin(), 8);
        return;
    L153:
        r315 = 5;
        r172 = 5;
        r183 = true;
        r222 = false;
        r252 = true;
        goto L155
    L164:
        if (r11 != 2) goto L176;
        if ((r210 instanceof Barrier) == false) goto L168;
    L174:
        SolverVariable r317 = r133;
        r135 = r6;
        r62 = r317;
        r316 = r15;
        r184 = 6;
        r94 = true;
        r20 = 4;
    L171:
        r223 = 5;
    L172:
        r252 = true;
        r262 = true;
    L173:
        r272 = false;
        goto L161
    L168:
        if ((r72 instanceof Barrier) == true) goto L174;
        SolverVariable r318 = r133;
        r135 = r6;
        r62 = r318;
        r316 = r15;
        r184 = 6;
        r94 = true;
        r20 = 5;
        goto L171
    L176:
        if (r11 != 1) goto L179;
        SolverVariable r319 = r133;
        r135 = r6;
        r62 = r319;
        r316 = r15;
        r184 = 6;
        r94 = true;
        r20 = 4;
        r223 = 8;
        goto L172
    L179:
        if (r11 == 3) goto L181;
        r94 = true;
        SolverVariable r110 = r133;
        r135 = r6;
        r62 = r110;
        r1 = r31;
        r316 = r15;
        r184 = 6;
        r20 = 4;
        r223 = 5;
        r252 = false;
        r262 = false;
        goto L173
    L181:
        if (this.mResolvedDimensionRatioSide != (-1)) goto L189;
        if (r50 == false) goto L188;
        SolverVariable r111 = r133;
        r135 = r6;
        r62 = r111;
        r1 = r31;
        r316 = r15;
        r94 = true;
        if (r33 == false) goto L187;
        r184 = 5;
    L186:
        r20 = 5;
        r223 = 8;
        r252 = true;
        r262 = true;
        r272 = true;
        goto L161
    L187:
        r184 = 4;
        goto L186
    L188:
        SolverVariable r113 = r133;
        r135 = r6;
        r62 = r113;
        r1 = r31;
        r316 = r15;
        r94 = true;
        r184 = 8;
        goto L186
    L189:
        if (r47 == true) goto L191;
        r94 = true;
        if (r311 <= 0) goto L204;
        SolverVariable r114 = r133;
        r135 = r6;
        r62 = r114;
        r1 = r31;
        r252 = true;
        r262 = true;
        r272 = true;
        r316 = r15;
        r184 = 6;
        r20 = 5;
    L203:
        r223 = 5;
        goto L161
    L204:
        if (r311 != 0) goto L213;
        if (r232 != 0) goto L213;
        if (r50 == true) goto L208;
        SolverVariable r115 = r133;
        r135 = r6;
        r62 = r115;
        r1 = r31;
        r252 = true;
        r262 = true;
        r272 = true;
        r316 = r15;
        r184 = 6;
        r20 = 8;
        goto L203
    L208:
        if (r210 == r85) goto L211;
        if (r72 == r85) goto L211;
        int r116 = 4;
    L212:
        SolverVariable r320 = r133;
        r135 = r6;
        r62 = r320;
        r223 = r116;
        r252 = true;
        r262 = true;
        r272 = true;
        r316 = r15;
        r184 = 6;
        r20 = 4;
    L199:
        r1 = r31;
    L211:
        r116 = 5;
    L213:
        SolverVariable r117 = r133;
        r135 = r6;
        r62 = r117;
        r1 = r31;
        r252 = true;
        r262 = true;
        r272 = true;
        r316 = r15;
        r184 = 6;
        r20 = 4;
        goto L203
    L191:
        if (r53 == 2) goto L196;
        r94 = true;
        if (r53 == 1) goto L197;
        int r118 = 8;
        int r321 = 5;
    L198:
        SolverVariable r173 = r133;
        r135 = r6;
        r62 = r173;
        r223 = r118;
        r20 = r321;
        r252 = r94;
        r262 = r252;
        r272 = r262;
        r316 = r15;
        r184 = 6;
    L197:
        r118 = 5;
        r321 = 4;
        goto L198
    L196:
        r94 = true;
        goto L197
    L215:
        r94 = true;
        if (r15.isFinalValue == true) goto L218;
    L230:
        SolverVariable r119 = r133;
        r135 = r6;
        r62 = r119;
        r1 = r31;
        r316 = r15;
        r153 = r37;
        r252 = true;
        r262 = true;
        r184 = 6;
        r223 = 5;
        r20 = 4;
        r272 = false;
        goto L231
    L218:
        if (r133.isFinalValue == false) goto L230;
        SolverVariable r522 = r133;
        r31.addCentering(r6, r15, r40.getMargin(), r46, r522, r5, r41.getMargin(), 8);
        if (r33 == false) goto L366;
        if (r19 == true) goto L223;
        return;
    L223:
        if (r41.mTarget == null) goto L226;
        int r137 = r41.getMargin();
    L227:
        if (r522 == r37) goto L368;
        r31.addGreaterThan(r37, r5, r137, 5);
        return;
    L368:
        return;
    L226:
        r137 = 0;
        goto L227
    L366:
        return;
    L341:
        r112 = r37;
        r152 = 8;
        r26 = 1;
        r92 = 2;
        goto L342
    L52:
        r1.addEquality(r7, r6, r84, r132);
        goto L53
    L55:
        if (r9 == 2) goto L65;
        if (r47 == true) goto L65;
        if (r11 == 1) goto L60;
        if (r11 != 0) goto L65;
    L60:
        int r211 = Math.max(r54, r84);
        if (r55 <= 0) goto L63;
        r211 = Math.min(r55, r211);
    L63:
        r1.addEquality(r7, r6, r211, 8);
        r232 = r54;
        r311 = r55;
        r5 = r7;
        r2 = r9;
        r133 = r8;
        r24 = false;
        r19 = r35;
    L65:
        if (r54 != (-2)) goto L67;
        int r322 = r84;
    L68:
        if (r55 != (-2)) goto L70;
        int r212 = r84;
    L71:
        if (r84 <= 0) goto L76;
        if (r11 == 1) goto L76;
        r84 = 0;
    L76:
        if (r322 <= 0) goto L79;
        r1.addGreaterThan(r7, r6, r322, 8);
        r84 = Math.max(r84, r322);
    L79:
        if (r212 <= 0) goto L85;
        if (r33 == false) goto L83;
        if (r11 != 1) goto L83;
    L84:
        r84 = Math.min(r84, r212);
    L83:
        r1.addLowerThan(r7, r6, r212, 8);
    L85:
        if (r11 != 1) goto L93;
        if (r33 == false) goto L88;
        r1.addEquality(r7, r6, r84, 8);
    L91:
        r5 = r7;
        r133 = r8;
        r24 = r23;
        r19 = r35;
        r232 = r322;
        r311 = r212;
        r2 = r9;
        goto L108
    L88:
        if (r49 == false) goto L90;
        r1.addEquality(r7, r6, r84, 5);
        r1.addLowerThan(r7, r6, r84, 8);
        goto L91
    L90:
        r1.addEquality(r7, r6, r84, 5);
        r1.addLowerThan(r7, r6, r84, 8);
        goto L91
    L93:
        if (r11 != 2) goto L107;
        ConstraintAnchor.Type r413 = r40.getType();
        ConstraintAnchor.Type r59 = ConstraintAnchor.Type.TOP;
        if (r413 != r59) goto L97;
    L101:
        SolverVariable r414 = r1.createObjectVariable(this.mParent.getAnchor(r59));
        SolverVariable r510 = r1.createObjectVariable(this.mParent.getAnchor(ConstraintAnchor.Type.BOTTOM));
    L102:
        SolverVariable r75 = r510;
        r5 = r7;
        int r392 = r212;
        r2 = r9;
        r133 = r8;
        r1.addConstraint(r1.createRow().createRowDimensionRatio(r5, r6, r75, r414, r56));
        if (r33 == false) goto L105;
        r23 = false;
    L105:
        r19 = r35;
        r24 = r23;
    L106:
        r232 = r322;
        r311 = r392;
        goto L108
    L97:
        if (r40.getType() == ConstraintAnchor.Type.BOTTOM) goto L101;
        r414 = r1.createObjectVariable(this.mParent.getAnchor(ConstraintAnchor.Type.LEFT));
        r510 = r1.createObjectVariable(this.mParent.getAnchor(ConstraintAnchor.Type.RIGHT));
        goto L102
    L107:
        r392 = r212;
        r5 = r7;
        r2 = r9;
        r133 = r8;
        r24 = r23;
        r19 = true;
        goto L106
    L70:
        r212 = r55;
        goto L71
    L67:
        r322 = r54;
    L40:
        if (r16 == false) goto L39;
        if (r17 == true) goto L39;
        r23 = r13;
        r132 = 8;
        r1.addEquality(r6, r15, r40.getMargin(), 8);
        goto L44
    L43:
        r23 = r13;
        r132 = 8;
        goto L44
    L33:
        r13 = r83;
        r84 = r432;
    L29:
        r310 = r43;
    L17:
        r83 = false;
        goto L21
    L11:
        r11 = r52;
        goto L12
    L5:
        r9 = r16 ? 1 : 0;
        goto L6
    }

    private void getSceneString(StringBuilder r1, String r2, int r3, int r4, int r5, int r6, int r7, int r8, float r9, DimensionBehaviour r10, float r11) {
        r1.append(r2);
        r1.append(" :  {\n");
        serializeAttribute(r1, "      behavior", r10.toString(), DimensionBehaviour.FIXED.toString());
        serializeAttribute(r1, "      size", r3, 0);
        serializeAttribute(r1, "      min", r4, 0);
        serializeAttribute(r1, "      max", r5, Integer.MAX_VALUE);
        serializeAttribute(r1, "      matchMin", r7, 0);
        serializeAttribute(r1, "      matchDef", r8, 0);
        serializeAttribute(r1, "      matchPercent", r9, 1.0f);
        r1.append("    },\n");
    }

    private void getSceneString(StringBuilder r2, String r3, ConstraintAnchor r4) {
        if (r4.mTarget != null) goto L5;
        return;
    L5:
        r2.append("    ");
        r2.append(r3);
        r2.append(" : [ '");
        r2.append(r4.mTarget);
        r2.append("'");
        if (r4.mGoneMargin == Integer.MIN_VALUE) goto L8;
    L9:
        r2.append(",");
        r2.append(r4.mMargin);
        if (r4.mGoneMargin == Integer.MIN_VALUE) goto L12;
        r2.append(",");
        r2.append(r4.mGoneMargin);
        r2.append(",");
    L12:
        r2.append(" ] ,\n");
        return;
    L8:
        if (r4.mMargin == 0) goto L12;
        goto L9
    }

    private boolean isChainHead(int r3) {
        int r32 = r3 * 2;
        ConstraintAnchor[] r2 = this.mListAnchors;
        ConstraintAnchor r0 = r2[r32];
        ConstraintAnchor r1 = r0.mTarget;
        if (r1 != null) goto L5;
        return false;
    L5:
        if (r1.mTarget == r0) goto L13;
        ConstraintAnchor r22 = r2[r32 + 1];
        ConstraintAnchor r33 = r22.mTarget;
        if (r33 != null) goto L9;
        return false;
    L9:
        if (r33.mTarget != r22) goto L15;
        return true;
    L15:
        return false;
    L13:
        return false;
    }

    private void serializeAttribute(StringBuilder r1, String r2, float r3, float r4) {
        if (r3 != r4) goto L5;
        return;
    L5:
        r1.append(r2);
        r1.append(" :   ");
        r1.append(r3);
        r1.append(",\n");
    }

    private void serializeAttribute(StringBuilder r1, String r2, int r3, int r4) {
        if (r3 != r4) goto L4;
        return;
    L4:
        r1.append(r2);
        r1.append(" :   ");
        r1.append(r3);
        r1.append(",\n");
    }

    private void serializeAttribute(StringBuilder r1, String r2, String r3, String r4) {
        if (r4.equals(r3) == false) goto L5;
        return;
    L5:
        r1.append(r2);
        r1.append(" :   ");
        r1.append(r3);
        r1.append(",\n");
    }

    private void serializeDimensionRatio(StringBuilder r1, String r2, float r3, int r4) {
        if (r3 != 0.0f) goto L5;
        return;
    L5:
        r1.append(r2);
        r1.append(" :  [");
        r1.append(r3);
        r1.append(",");
        r1.append(r4);
        r1.append(_UrlKt.FRAGMENT_ENCODE_SET);
        r1.append("],\n");
    }

    public void addChildrenToSolverByDependency(ConstraintWidgetContainer r9, LinearSystem r10, HashSet<ConstraintWidget> r11, int r12, boolean r13) {
        if (r13 == true) goto L4;
    L7:
        if (r12 != 0) goto L20;
        HashSet<ConstraintAnchor> r1 = this.mLeft.getDependents();
        if (r1 == null) goto L14;
        Iterator<ConstraintAnchor> r7 = r1.iterator();
    L12:
        if (r7.hasNext() == false) goto L14;
        r7.next().mOwner.addChildrenToSolverByDependency(r9, r10, r11, r12, true);
    L14:
        HashSet<ConstraintAnchor> r0 = this.mRight.getDependents();
        if (r0 == null) goto L38;
        Iterator<ConstraintAnchor> r6 = r0.iterator();
    L18:
        if (r6.hasNext() == false) goto L46;
        r6.next().mOwner.addChildrenToSolverByDependency(r9, r10, r11, r12, true);
        goto L18
    L46:
        return;
    L38:
        return;
    L20:
        HashSet<ConstraintAnchor> r14 = this.mTop.getDependents();
        if (r14 == null) goto L26;
        Iterator<ConstraintAnchor> r72 = r14.iterator();
    L24:
        if (r72.hasNext() == false) goto L26;
        r72.next().mOwner.addChildrenToSolverByDependency(r9, r10, r11, r12, true);
    L26:
        HashSet<ConstraintAnchor> r15 = this.mBottom.getDependents();
        if (r15 == null) goto L32;
        Iterator<ConstraintAnchor> r73 = r15.iterator();
    L30:
        if (r73.hasNext() == false) goto L32;
        r73.next().mOwner.addChildrenToSolverByDependency(r9, r10, r11, r12, true);
    L32:
        HashSet<ConstraintAnchor> r02 = this.mBaseline.getDependents();
        if (r02 == null) goto L44;
        Iterator<ConstraintAnchor> r62 = r02.iterator();
    L36:
        if (r62.hasNext() == false) goto L47;
        r62.next().mOwner.addChildrenToSolverByDependency(r9, r10, r11, r12, true);
        goto L36
    L47:
        return;
    L44:
        return;
    L4:
        if (r11.contains(this) == false) goto L45;
        Optimizer.checkMatchParent(r9, r10, this);
        r11.remove(this);
        addToSolver(r10, r9.optimizeFor(64));
        goto L7
    }

    public boolean addFirst() {
        if ((this instanceof VirtualLayout) == false) goto L5;
        return true;
    L5:
        if ((this instanceof Guideline) == true) goto L11;
        return false;
    L11:
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r21v10 */
    /* JADX WARN: Type inference failed for: r21v3 */
    /* JADX WARN: Type inference failed for: r21v4 */
    public void addToSolver(LinearSystem r51, boolean r52) {
        LinearSystem r1 = r51;
        SolverVariable r2 = r1.createObjectVariable(this.mLeft);
        SolverVariable r3 = r1.createObjectVariable(this.mRight);
        SolverVariable r4 = r1.createObjectVariable(this.mTop);
        SolverVariable r5 = r1.createObjectVariable(this.mBottom);
        SolverVariable r6 = r1.createObjectVariable(this.mBaseline);
        ConstraintWidget r7 = this.mParent;
        if (r7 == null) goto L19;
        if (r7 != null) goto L6;
    L8:
        boolean r12 = false;
    L9:
        if (r7 != null) goto L11;
    L13:
        boolean r72 = false;
    L14:
        int r13 = this.mWrapBehaviorInParent;
        if (r13 == 1) goto L21;
        if (r13 == 2) goto L20;
        if (r13 == 3) goto L19;
    L23:
        if (this.mVisibility == 8) goto L25;
    L33:
        boolean r132 = this.mResolvedHorizontal;
        if (r132 == false) goto L36;
    L37:
        if (r132 == false) goto L47;
        r1.addEquality(r2, this.mX);
        r1.addEquality(r3, this.mX + this.mWidth);
        if (r12 == false) goto L47;
        ConstraintWidget r8 = this.mParent;
        if (r8 == null) goto L47;
        if (this.mOptimizeWrapOnResolved == false) goto L45;
        ConstraintWidgetContainer r82 = (ConstraintWidgetContainer) r8;
        r82.addHorizontalWrapMinVariable(this.mLeft);
        r82.addHorizontalWrapMaxVariable(this.mRight);
        goto L47
    L45:
        r1.addGreaterThan(r1.createObjectVariable(r8.mRight), r3, 0, 5);
    L47:
        if (this.mResolvedVertical == false) goto L59;
        r1.addEquality(r4, this.mY);
        r1.addEquality(r5, this.mY + this.mHeight);
        if (this.mBaseline.hasDependents() == false) goto L51;
        r1.addEquality(r6, this.mY + this.mBaselineDistance);
    L51:
        if (r72 == false) goto L59;
        ConstraintWidget r83 = this.mParent;
        if (r83 == null) goto L59;
        if (this.mOptimizeWrapOnResolved == false) goto L57;
        ConstraintWidgetContainer r84 = (ConstraintWidgetContainer) r83;
        r84.addVerticalWrapMinVariable(this.mTop);
        r84.addVerticalWrapMaxVariable(this.mBottom);
        goto L59
    L57:
        r1.addGreaterThan(r1.createObjectVariable(r83.mBottom), r5, 0, 5);
    L59:
        if (this.mResolvedHorizontal == true) goto L61;
    L64:
        boolean r85 = LinearSystem.USE_DEPENDENCY_ORDERING;
        if (r52 == false) goto L95;
        HorizontalWidgetRun r86 = this.mHorizontalRun;
        if (r86 == null) goto L95;
        VerticalWidgetRun r133 = this.mVerticalRun;
        if (r133 == null) goto L95;
        DependencyNode r15 = r86.start;
        if (r15.resolved == false) goto L95;
        if (r86.end.resolved == false) goto L95;
        if (r133.start.resolved == false) goto L95;
        if (r133.end.resolved == false) goto L95;
        r1.addEquality(r2, r15.value);
        r1.addEquality(r3, this.mHorizontalRun.end.value);
        r1.addEquality(r4, this.mVerticalRun.start.value);
        r1.addEquality(r5, this.mVerticalRun.end.value);
        r1.addEquality(r6, this.mVerticalRun.baseline.value);
        if (this.mParent == null) goto L92;
        if (r12 == true) goto L82;
    L86:
        if (r72 == false) goto L92;
        if (this.isTerminalWidget[1] == false) goto L92;
        if (isInVerticalChain() == true) goto L92;
        r1.addGreaterThan(r1.createObjectVariable(this.mParent.mBottom), r5, 0, 8);
        goto L92
    L82:
        if (this.isTerminalWidget[0] == false) goto L86;
        if (isInHorizontalChain() == true) goto L86;
        r1.addGreaterThan(r1.createObjectVariable(this.mParent.mRight), r3, 0, 8);
    L92:
        this.mResolvedHorizontal = false;
        this.mResolvedVertical = false;
        return;
    L95:
        if (this.mParent != null) goto L97;
        boolean r19 = false;
        boolean r20 = false;
    L126:
        int r87 = this.mWidth;
        int r9 = this.mMinWidth;
        if (r87 < r9) goto L130;
        r9 = r87;
    L130:
        int r134 = this.mHeight;
        int r152 = this.mMinHeight;
        if (r134 < r152) goto L134;
        r152 = r134;
    L134:
        DimensionBehaviour[] r10 = this.mListDimensionBehaviors;
        DimensionBehaviour r11 = r10[0];
        DimensionBehaviour r14 = DimensionBehaviour.MATCH_CONSTRAINT;
        if (r11 == r14) goto L137;
        boolean r42 = true;
    L138:
        DimensionBehaviour r102 = r10[1];
        if (r102 == r14) goto L142;
        boolean r53 = true;
    L143:
        int r62 = this.mDimensionRatioSide;
        this.mResolvedDimensionRatioSide = r62;
        int r27 = r9;
        float r92 = this.mDimensionRatio;
        this.mResolvedDimensionRatio = r92;
        int r93 = this.mMatchConstraintDefaultWidth;
        int r94 = this.mMatchConstraintDefaultHeight;
        if (r92 <= 0.0f) goto L181;
        int r32 = r152;
        if (this.mVisibility == 8) goto L180;
        if (r11 != r14) goto L150;
        if (r93 != 0) goto L150;
        int r95 = 3;
    L151:
        if (r102 != r14) goto L154;
        if (r94 != 0) goto L154;
        int r153 = 3;
    L155:
        if (r11 != r14) goto L161;
        if (r102 != r14) goto L161;
        SolverVariable r33 = r3;
        int r34 = 3;
        if (r95 != 3) goto L163;
        if (r153 != 3) goto L163;
        setupDimensionRatio(r12, r72, r42, r53);
    L179:
        int r23 = r153;
        boolean r35 = true;
    L183:
        int[] r43 = this.mResolvedMatchConstraintDefault;
        r43[0] = r95;
        r43[1] = r23;
        this.mResolvedHasRatio = r35;
        if (r35 == false) goto L190;
        int r44 = this.mResolvedDimensionRatioSide;
        int r54 = -1;
        if (r44 == 0) goto L188;
        if (r44 == (-1)) goto L188;
    L191:
        boolean r17 = false;
    L192:
        if (r35 == false) goto L197;
        int r63 = this.mResolvedDimensionRatioSide;
        if (r63 == 1) goto L196;
        if (r63 != r54) goto L197;
    L196:
        boolean r28 = true;
    L198:
        DimensionBehaviour r55 = this.mListDimensionBehaviors[0];
        DimensionBehaviour r64 = DimensionBehaviour.WRAP_CONTENT;
        if (r55 == r64) goto L201;
    L203:
        boolean r56 = false;
    L204:
        if (r56 == false) goto L206;
        int r135 = 0;
    L207:
        boolean r272 = !this.mCenter.isConnected();
        boolean[] r88 = this.mIsInBarrier;
        boolean r103 = r88[0];
        boolean r29 = r88[1];
        SolverVariable r30 = null;
        if (this.mHorizontalResolution != 2) goto L210;
    L230:
        SolverVariable r432 = r2;
        boolean r36 = r35;
        DimensionBehaviour r49 = r64;
        boolean r45 = r72;
    L229:
        int r22 = r95;
        boolean r37 = r12;
        DimensionBehaviour r48 = r14;
        SolverVariable r452 = r4;
        SolverVariable r46 = r5;
        SolverVariable r47 = r6;
    L244:
        if (r52 == false) goto L261;
        VerticalWidgetRun r24 = this.mVerticalRun;
        if (r24 == null) goto L261;
        DependencyNode r57 = r24.start;
        if (r57.resolved == false) goto L261;
        if (r24.end.resolved == false) goto L261;
        int r25 = r57.value;
        SolverVariable r58 = r452;
        r1.addEquality(r58, r25);
        SolverVariable r65 = r46;
        r1.addEquality(r65, this.mVerticalRun.end.value);
        SolverVariable r73 = r47;
        r1.addEquality(r73, this.mVerticalRun.baseline.value);
        ConstraintWidget r26 = this.mParent;
        if (r26 == null) goto L259;
        if (r20 == true) goto L259;
        if (r45 == false) goto L259;
        boolean r21 = true;
        r21 = true;
        if (this.isTerminalWidget[1] == false) goto L258;
        int r89 = 0;
        int r154 = 8;
        r1.addGreaterThan(r1.createObjectVariable(r26.mBottom), r65, 0, 8);
    L260:
        int r104 = r89;
        ?? r212 = r21;
    L263:
        if (this.mVerticalResolution != 2) goto L265;
        int r112 = r89;
    L266:
        if (r112 != 0) goto L268;
    L304:
        ConstraintWidget r74 = this;
        SolverVariable r453 = r58;
        SolverVariable r462 = r65;
    L305:
        if (r36 == false) goto L309;
        int r0 = r74.mResolvedDimensionRatioSide;
        float r59 = r74.mResolvedDimensionRatio;
        if (r0 != 1) goto L310;
        SolverVariable r16 = r462;
        r51.addRatio(r16, r453, r33, r432, r59, 8);
        goto L309
    L310:
        r51.addRatio(r33, r432, r462, r453, r59, 8);
        LinearSystem r18 = r51;
    L312:
        if (r74.mCenter.isConnected() == false) goto L314;
        r18.addCenterPoint(r74, r74.mCenter.getTarget().getOwner(), (float) Math.toRadians(r74.mCircleConstraintAngle + 90.0f), r74.mCenter.getMargin());
    L314:
        r74.mResolvedHorizontal = false;
        r74.mResolvedVertical = false;
        return;
    L309:
        r18 = r51;
        goto L312
    L268:
        if (this.mResolvedVertical == true) goto L304;
        if (this.mListDimensionBehaviors[r212] == r49) goto L272;
    L274:
        boolean r96 = r89;
    L275:
        if (r96 == false) goto L277;
        int r136 = r89;
    L278:
        ConstraintWidget r210 = this.mParent;
        if (r210 == null) goto L281;
        SolverVariable r211 = r1.createObjectVariable(r210.mBottom);
    L282:
        ConstraintWidget r105 = this.mParent;
        if (r105 == null) goto L286;
        r30 = r1.createObjectVariable(r105.mTop);
    L286:
        if (this.mBaselineDistance <= 0) goto L288;
    L289:
        ConstraintAnchor r106 = this.mBaseline;
        if (r106.mTarget == null) goto L296;
        r1.addEquality(r73, r58, getBaselineDistance(), r154);
        r1.addEquality(r73, r1.createObjectVariable(this.mBaseline.mTarget), this.mBaseline.getMargin(), r154);
        if (r45 == false) goto L294;
        r1.addGreaterThan(r211, r1.createObjectVariable(this.mBottom), r89, 5);
    L294:
        r272 = r89;
    L299:
        boolean r75 = this.isTerminalWidget[r212];
        DimensionBehaviour[] r107 = this.mListDimensionBehaviors;
        int r442 = r89;
        DimensionBehaviour r810 = r107[r212];
        ConstraintAnchor r108 = this.mTop;
        ConstraintAnchor r113 = this.mBottom;
        int r122 = this.mY;
        int r142 = this.mMinHeight;
        int r110 = this.mMaxDimension[r212];
        float r111 = this.mVerticalBiasPercent;
        if (r107[r442] != r48) goto L302;
        boolean r182 = r212;
    L303:
        r453 = r58;
        boolean r172 = r45;
        boolean r410 = r37;
        r462 = r65;
        boolean r173 = r28;
        SolverVariable r66 = r30;
        applyConstraints(r51, false, r172, r410, r75, r66, r211, r810, r96, r108, r113, r122, r136, r142, r110, r111, r173, r182, r20, r19, r29, r23, r22, this.mMatchConstraintMinHeight, this.mMatchConstraintMaxHeight, this.mMatchConstraintPercentHeight, r272);
        r74 = this;
        goto L305
    L302:
        r182 = r442;
        goto L303
    L296:
        if (this.mVisibility != r154) goto L298;
        r1.addEquality(r73, r58, r106.getMargin(), r154);
        goto L299
    L298:
        r1.addEquality(r73, r58, getBaselineDistance(), r154);
        goto L299
    L288:
        if (this.mVisibility != r154) goto L299;
    L281:
        r211 = null;
        goto L282
    L277:
        r136 = r32;
        goto L278
    L272:
        if ((this instanceof ConstraintWidgetContainer) == false) goto L274;
        r96 = r212;
        goto L275
    L265:
        r112 = r104;
        goto L266
    L258:
        r89 = 0;
        r154 = 8;
    L259:
        r89 = 0;
        r154 = 8;
        r21 = true;
    L261:
        r58 = r452;
        r65 = r46;
        r73 = r47;
        r89 = 0;
        r154 = 8;
        r212 = 1;
        r104 = 1;
        goto L263
    L210:
        if (this.mResolvedHorizontal == true) goto L230;
        if (r52 == false) goto L218;
        HorizontalWidgetRun r811 = this.mHorizontalRun;
        if (r811 == null) goto L218;
        DependencyNode r411 = r811.start;
        if (r411.resolved == false) goto L218;
        if (r811.end.resolved == false) goto L218;
        if (r52 == false) goto L230;
        r1.addEquality(r2, r411.value);
        SolverVariable r812 = r33;
        r1.addEquality(r812, this.mHorizontalRun.end.value);
        if (this.mParent == null) goto L228;
        if (r12 == false) goto L228;
        if (this.isTerminalWidget[0] == false) goto L228;
        if (isInHorizontalChain() == true) goto L228;
        r1.addGreaterThan(r1.createObjectVariable(this.mParent.mRight), r812, 0, 8);
    L228:
        r432 = r2;
        r36 = r35;
        r49 = r64;
        r45 = r72;
        r33 = r812;
    L218:
        SolverVariable r813 = r33;
        ConstraintWidget r412 = this.mParent;
        if (r412 == null) goto L234;
        SolverVariable r413 = r1.createObjectVariable(r412.mRight);
    L235:
        ConstraintWidget r114 = this.mParent;
        if (r114 == null) goto L238;
        SolverVariable r115 = r1.createObjectVariable(r114.mLeft);
    L239:
        boolean r155 = this.isTerminalWidget[0];
        DimensionBehaviour[] r116 = this.mListDimensionBehaviors;
        r33 = r813;
        DimensionBehaviour r814 = r116[0];
        ConstraintAnchor r109 = this.mLeft;
        SolverVariable r67 = r115;
        ConstraintAnchor r117 = this.mRight;
        r36 = r35;
        r37 = r12;
        int r123 = this.mX;
        int r118 = this.mMinWidth;
        int r119 = this.mMaxDimension[0];
        float r120 = this.mHorizontalBiasPercent;
        if (r116[1] != r14) goto L242;
        boolean r183 = true;
    L243:
        r432 = r2;
        boolean r162 = r72;
        SolverVariable r76 = r413;
        r45 = r162;
        r22 = r95;
        r48 = r14;
        r452 = r4;
        r46 = r5;
        r47 = r6;
        r49 = r64;
        r1 = r51;
        applyConstraints(r1, true, r37, r45, r155, r67, r76, r814, r56, r109, r117, r123, r135, r118, r119, r120, r17, r183, r19, r20, r103, r22, r23, this.mMatchConstraintMinWidth, this.mMatchConstraintMaxWidth, this.mMatchConstraintPercentWidth, r272);
        goto L244
    L242:
        r183 = false;
        goto L243
    L238:
        r115 = null;
        goto L239
    L234:
        r413 = null;
        goto L235
    L206:
        r135 = r27;
        goto L207
    L201:
        if ((this instanceof ConstraintWidgetContainer) == false) goto L203;
        r56 = true;
    L197:
        r28 = false;
    L188:
        r17 = true;
        goto L192
    L190:
        r54 = -1;
    L163:
        if (r11 != r14) goto L171;
        if (r95 != r34) goto L171;
        this.mResolvedDimensionRatioSide = 0;
        r27 = (int) (r134 * r92);
        if (r102 == r14) goto L170;
        r95 = 4;
        r23 = r153;
    L168:
        r35 = false;
        goto L183
    L170:
        r23 = r153;
        r35 = true;
    L171:
        if (r102 != r14) goto L179;
        if (r153 != r34) goto L179;
        this.mResolvedDimensionRatioSide = 1;
        if (r62 != (-1)) goto L176;
        this.mResolvedDimensionRatio = 1.0f / r92;
    L176:
        r32 = (int) (this.mResolvedDimensionRatio * r87);
        if (r11 == r14) goto L179;
        r23 = 4;
    L161:
        r33 = r3;
        r34 = 3;
    L154:
        r153 = r94;
    L150:
        r95 = r93;
    L180:
        r33 = r3;
        r95 = r93;
        r23 = r94;
        goto L168
    L181:
        r32 = r152;
        goto L180
    L142:
        r53 = false;
        goto L143
    L137:
        r42 = false;
        goto L138
    L97:
        if (isChainHead(0) == false) goto L99;
        ((ConstraintWidgetContainer) this.mParent).addChain(this, 0);
        boolean r815 = true;
    L101:
        if (isChainHead(1) == false) goto L103;
        ((ConstraintWidgetContainer) this.mParent).addChain(this, 1);
        boolean r97 = true;
    L104:
        if (r815 == true) goto L113;
        if (r12 == false) goto L113;
        if (this.mVisibility == 8) goto L113;
        if (this.mLeft.mTarget != null) goto L113;
        if (this.mRight.mTarget != null) goto L113;
        r1.addGreaterThan(r1.createObjectVariable(this.mParent.mRight), r3, 0, 1);
    L113:
        if (r97 == true) goto L124;
        if (r72 == false) goto L124;
        if (this.mVisibility == 8) goto L124;
        if (this.mTop.mTarget != null) goto L124;
        if (this.mBottom.mTarget != null) goto L124;
        if (this.mBaseline != null) goto L124;
        r1.addGreaterThan(r1.createObjectVariable(this.mParent.mBottom), r5, 0, 1);
    L124:
        r19 = r815;
        r20 = r97;
        goto L126
    L103:
        r97 = isInVerticalChain();
        goto L104
    L99:
        r815 = isInHorizontalChain();
        goto L101
    L61:
        if (this.mResolvedVertical == false) goto L64;
        this.mResolvedHorizontal = false;
        this.mResolvedVertical = false;
        return;
    L36:
        if (this.mResolvedVertical == false) goto L64;
    L25:
        if (this.mAnimated == true) goto L33;
        if (hasDependencies() == true) goto L33;
        boolean[] r137 = this.mIsInBarrier;
        if (r137[0] == true) goto L33;
        if (r137[1] == true) goto L33;
        return;
    L20:
        r12 = false;
        goto L23
    L21:
        r72 = false;
        goto L23
    L11:
        if (r7.mListDimensionBehaviors[1] != DimensionBehaviour.WRAP_CONTENT) goto L13;
        r72 = true;
        goto L14
    L6:
        if (r7.mListDimensionBehaviors[0] != DimensionBehaviour.WRAP_CONTENT) goto L8;
        r12 = true;
    L19:
        r72 = false;
        r12 = false;
        goto L23
    }

    public boolean allowedInBarrier() {
        if (this.mVisibility == 8) goto L6;
        return true;
    L6:
        return false;
    }

    public void connect(ConstraintAnchor.Type r9, ConstraintWidget r10, ConstraintAnchor.Type r11, int r12) {
        ConstraintAnchor.Type r0 = ConstraintAnchor.Type.CENTER;
        if (r9 != r0) goto L45;
        if (r11 != r0) goto L32;
        ConstraintAnchor.Type r92 = ConstraintAnchor.Type.LEFT;
        ConstraintAnchor r112 = getAnchor(r92);
        ConstraintAnchor.Type r122 = ConstraintAnchor.Type.RIGHT;
        ConstraintAnchor r2 = getAnchor(r122);
        ConstraintAnchor.Type r3 = ConstraintAnchor.Type.TOP;
        ConstraintAnchor r4 = getAnchor(r3);
        ConstraintAnchor.Type r5 = ConstraintAnchor.Type.BOTTOM;
        ConstraintAnchor r6 = getAnchor(r5);
        boolean r7 = true;
        if (r112 != null) goto L8;
    L9:
        if (r2 != null) goto L11;
    L13:
        connect(r92, r10, r92, 0);
        connect(r122, r10, r122, 0);
        boolean r93 = true;
    L14:
        if (r4 != null) goto L16;
    L17:
        if (r6 != null) goto L19;
    L21:
        connect(r3, r10, r3, 0);
        connect(r5, r10, r5, 0);
    L22:
        if (r93 == false) goto L26;
        if (r7 == false) goto L26;
        getAnchor(r0).connect(r10.getAnchor(r0), 0);
        return;
    L26:
        if (r93 == false) goto L29;
        ConstraintAnchor.Type r94 = ConstraintAnchor.Type.CENTER_X;
        getAnchor(r94).connect(r10.getAnchor(r94), 0);
        return;
    L29:
        if (r7 == false) goto L104;
        ConstraintAnchor.Type r95 = ConstraintAnchor.Type.CENTER_Y;
        getAnchor(r95).connect(r10.getAnchor(r95), 0);
        return;
    L104:
        return;
    L19:
        if (r6.isConnected() == false) goto L21;
    L20:
        r7 = false;
        goto L22
    L16:
        if (r4.isConnected() == true) goto L20;
    L11:
        if (r2.isConnected() == false) goto L13;
    L12:
        r93 = false;
        goto L14
    L8:
        if (r112.isConnected() == true) goto L12;
    L32:
        ConstraintAnchor.Type r96 = ConstraintAnchor.Type.LEFT;
        if (r11 != r96) goto L35;
    L43:
        connect(r96, r10, r11, 0);
        connect(ConstraintAnchor.Type.RIGHT, r10, r11, 0);
        getAnchor(r0).connect(r10.getAnchor(r11), 0);
        return;
    L35:
        if (r11 == ConstraintAnchor.Type.RIGHT) goto L43;
        ConstraintAnchor.Type r97 = ConstraintAnchor.Type.TOP;
        if (r11 != r97) goto L40;
    L41:
        connect(r97, r10, r11, 0);
        connect(ConstraintAnchor.Type.BOTTOM, r10, r11, 0);
        getAnchor(r0).connect(r10.getAnchor(r11), 0);
        return;
    L40:
        if (r11 == ConstraintAnchor.Type.BOTTOM) goto L41;
        return;
    L45:
        ConstraintAnchor.Type r22 = ConstraintAnchor.Type.CENTER_X;
        if (r9 != r22) goto L53;
        ConstraintAnchor.Type r32 = ConstraintAnchor.Type.LEFT;
        if (r11 != r32) goto L50;
    L51:
        ConstraintAnchor r98 = getAnchor(r32);
        ConstraintAnchor r102 = r10.getAnchor(r11);
        ConstraintAnchor r113 = getAnchor(ConstraintAnchor.Type.RIGHT);
        r98.connect(r102, 0);
        r113.connect(r102, 0);
        getAnchor(r22).connect(r102, 0);
        return;
    L50:
        if (r11 == ConstraintAnchor.Type.RIGHT) goto L51;
    L53:
        ConstraintAnchor.Type r33 = ConstraintAnchor.Type.CENTER_Y;
        if (r9 != r33) goto L61;
        ConstraintAnchor.Type r42 = ConstraintAnchor.Type.TOP;
        if (r11 != r42) goto L58;
    L59:
        ConstraintAnchor r99 = r10.getAnchor(r11);
        getAnchor(r42).connect(r99, 0);
        getAnchor(ConstraintAnchor.Type.BOTTOM).connect(r99, 0);
        getAnchor(r33).connect(r99, 0);
        return;
    L58:
        if (r11 == ConstraintAnchor.Type.BOTTOM) goto L59;
    L61:
        if (r9 != r22) goto L65;
        if (r11 != r22) goto L65;
        ConstraintAnchor.Type r910 = ConstraintAnchor.Type.LEFT;
        getAnchor(r910).connect(r10.getAnchor(r910), 0);
        ConstraintAnchor.Type r911 = ConstraintAnchor.Type.RIGHT;
        getAnchor(r911).connect(r10.getAnchor(r911), 0);
        getAnchor(r22).connect(r10.getAnchor(r11), 0);
        return;
    L65:
        if (r9 != r33) goto L69;
        if (r11 != r33) goto L69;
        ConstraintAnchor.Type r912 = ConstraintAnchor.Type.TOP;
        getAnchor(r912).connect(r10.getAnchor(r912), 0);
        ConstraintAnchor.Type r913 = ConstraintAnchor.Type.BOTTOM;
        getAnchor(r913).connect(r10.getAnchor(r913), 0);
        getAnchor(r33).connect(r10.getAnchor(r11), 0);
        return;
    L69:
        ConstraintAnchor r1 = getAnchor(r9);
        ConstraintAnchor r103 = r10.getAnchor(r11);
        if (r1.isValidConnection(r103) == false) goto L106;
        ConstraintAnchor.Type r114 = ConstraintAnchor.Type.BASELINE;
        if (r9 != r114) goto L79;
        ConstraintAnchor r914 = getAnchor(ConstraintAnchor.Type.TOP);
        ConstraintAnchor r8 = getAnchor(ConstraintAnchor.Type.BOTTOM);
        if (r914 == null) goto L76;
        r914.reset();
    L76:
        if (r8 == null) goto L102;
        r8.reset();
    L102:
        r1.connect(r103, r12);
        return;
    L79:
        if (r9 != ConstraintAnchor.Type.TOP) goto L81;
    L93:
        ConstraintAnchor r115 = getAnchor(r114);
        if (r115 == null) goto L96;
        r115.reset();
    L96:
        ConstraintAnchor r116 = getAnchor(r0);
        if (r116.getTarget() == r103) goto L99;
        r116.reset();
    L99:
        ConstraintAnchor r915 = getAnchor(r9).getOpposite();
        ConstraintAnchor r82 = getAnchor(r33);
        if (r82.isConnected() == false) goto L102;
        r915.reset();
        r82.reset();
        goto L102
    L81:
        if (r9 == ConstraintAnchor.Type.BOTTOM) goto L93;
        if (r9 != ConstraintAnchor.Type.LEFT) goto L86;
    L87:
        ConstraintAnchor r117 = getAnchor(r0);
        if (r117.getTarget() == r103) goto L90;
        r117.reset();
    L90:
        ConstraintAnchor r916 = getAnchor(r9).getOpposite();
        ConstraintAnchor r83 = getAnchor(r22);
        if (r83.isConnected() == false) goto L102;
        r916.reset();
        r83.reset();
        goto L102
    L86:
        if (r9 != ConstraintAnchor.Type.RIGHT) goto L102;
    }

    public void connect(ConstraintAnchor r2, ConstraintAnchor r3, int r4) {
        if (r2.getOwner() != this) goto L6;
        connect(r2.getType(), r3.getOwner(), r3.getType(), r4);
        return;
    }

    public void connectCircularConstraint(ConstraintWidget r7, float r8, int r9) {
        ConstraintAnchor.Type r1 = ConstraintAnchor.Type.CENTER;
        immediateConnect(r1, r7, r1, r9, 0);
        this.mCircleConstraintAngle = r8;
    }

    public void createObjectVariables(LinearSystem r2) {
        r2.createObjectVariable(this.mLeft);
        r2.createObjectVariable(this.mTop);
        r2.createObjectVariable(this.mRight);
        r2.createObjectVariable(this.mBottom);
        if (this.mBaselineDistance <= 0) goto L6;
        r2.createObjectVariable(this.mBaseline);
        return;
    }

    public void ensureWidgetRuns() {
        if (this.mHorizontalRun != null) goto L6;
        this.mHorizontalRun = new HorizontalWidgetRun(this);
    L6:
        if (this.mVerticalRun != null) goto L9;
        this.mVerticalRun = new VerticalWidgetRun(this);
        return;
    }

    public ConstraintAnchor getAnchor(ConstraintAnchor.Type r3) {
        switch(AnonymousClass1.$SwitchMap$androidx$constraintlayout$core$widgets$ConstraintAnchor$Type[r3.ordinal()]) {
            case 1: goto L21;
            case 2: goto L19;
            case 3: goto L17;
            case 4: goto L15;
            case 5: goto L13;
            case 6: goto L11;
            case 7: goto L9;
            case 8: goto L7;
            case 9: goto L5;
            default: goto L4;
        };
    L4:
        Buffer$$ExternalSyntheticBUOutline2.m(r3.name());
    L5:
        return null;
    L7:
        return this.mCenterY;
    L9:
        return this.mCenterX;
    L11:
        return this.mCenter;
    L13:
        return this.mBaseline;
    L15:
        return this.mBottom;
    L17:
        return this.mRight;
    L19:
        return this.mTop;
    L21:
        return this.mLeft;
    }

    public int getBaselineDistance() {
        return this.mBaselineDistance;
    }

    public float getBiasPercent(int r2) {
        if (r2 != 0) goto L6;
        return this.mHorizontalBiasPercent;
    L6:
        if (r2 == 1) goto L8;
        return -1.0f;
    L8:
        return this.mVerticalBiasPercent;
    }

    public int getBottom() {
        return getY() + this.mHeight;
    }

    public Object getCompanionWidget() {
        return this.mCompanionWidget;
    }

    public String getDebugName() {
        return this.mDebugName;
    }

    public DimensionBehaviour getDimensionBehaviour(int r2) {
        if (r2 != 0) goto L6;
        return getHorizontalDimensionBehaviour();
    L6:
        if (r2 == 1) goto L8;
        return null;
    L8:
        return getVerticalDimensionBehaviour();
    }

    public float getDimensionRatio() {
        return this.mDimensionRatio;
    }

    public int getDimensionRatioSide() {
        return this.mDimensionRatioSide;
    }

    public int getHeight() {
        if (this.mVisibility != 8) goto L7;
        return 0;
    L7:
        return this.mHeight;
    }

    public float getHorizontalBiasPercent() {
        return this.mHorizontalBiasPercent;
    }

    public int getHorizontalChainStyle() {
        return this.mHorizontalChainStyle;
    }

    public DimensionBehaviour getHorizontalDimensionBehaviour() {
        return this.mListDimensionBehaviors[0];
    }

    public int getHorizontalMargin() {
        ConstraintAnchor r0 = this.mLeft;
        if (r0 == null) goto L5;
        int r02 = r0.mMargin;
    L6:
        ConstraintAnchor r1 = this.mRight;
        if (r1 != null) goto L9;
        return r02;
    L9:
        return r02 + r1.mMargin;
    L5:
        r02 = 0;
        goto L6
    }

    public int getLastHorizontalMeasureSpec() {
        return this.mLastHorizontalMeasureSpec;
    }

    public int getLastVerticalMeasureSpec() {
        return this.mLastVerticalMeasureSpec;
    }

    public int getLength(int r2) {
        if (r2 != 0) goto L6;
        return getWidth();
    L6:
        if (r2 == 1) goto L8;
        return 0;
    L8:
        return getHeight();
    }

    public int getMaxHeight() {
        return this.mMaxDimension[1];
    }

    public int getMaxWidth() {
        return this.mMaxDimension[0];
    }

    public int getMinHeight() {
        return this.mMinHeight;
    }

    public int getMinWidth() {
        return this.mMinWidth;
    }

    public ConstraintWidget getNextChainMember(int r2) {
        if (r2 != 0) goto L10;
        ConstraintAnchor r1 = this.mRight;
        ConstraintAnchor r22 = r1.mTarget;
        if (r22 != null) goto L6;
        return null;
    L6:
        if (r22.mTarget == r1) goto L8;
        return null;
    L8:
        return r22.mOwner;
    L10:
        if (r2 != 1) goto L20;
        ConstraintAnchor r12 = this.mBottom;
        ConstraintAnchor r23 = r12.mTarget;
        if (r23 != null) goto L14;
        return null;
    L14:
        if (r23.mTarget == r12) goto L16;
        return null;
    L16:
        return r23.mOwner;
    L20:
        return null;
    }

    public ConstraintWidget getParent() {
        return this.mParent;
    }

    public ConstraintWidget getPreviousChainMember(int r2) {
        if (r2 != 0) goto L10;
        ConstraintAnchor r1 = this.mLeft;
        ConstraintAnchor r22 = r1.mTarget;
        if (r22 != null) goto L6;
        return null;
    L6:
        if (r22.mTarget == r1) goto L8;
        return null;
    L8:
        return r22.mOwner;
    L10:
        if (r2 != 1) goto L20;
        ConstraintAnchor r12 = this.mTop;
        ConstraintAnchor r23 = r12.mTarget;
        if (r23 != null) goto L14;
        return null;
    L14:
        if (r23.mTarget == r12) goto L16;
        return null;
    L16:
        return r23.mOwner;
    L20:
        return null;
    }

    public int getRight() {
        return getX() + this.mWidth;
    }

    public WidgetRun getRun(int r2) {
        if (r2 != 0) goto L6;
        return this.mHorizontalRun;
    L6:
        if (r2 == 1) goto L8;
        return null;
    L8:
        return this.mVerticalRun;
    }

    public void getSceneString(StringBuilder r14) {
        r14.append("  " + this.stringId + ":{\n");
        StringBuilder r2 = new StringBuilder("    actualWidth:");
        r2.append(this.mWidth);
        r14.append(r2.toString());
        r14.append("\n");
        r14.append("    actualHeight:" + this.mHeight);
        r14.append("\n");
        r14.append("    actualLeft:" + this.mX);
        r14.append("\n");
        r14.append("    actualTop:" + this.mY);
        r14.append("\n");
        getSceneString(r14, "left", this.mLeft);
        getSceneString(r14, "top", this.mTop);
        getSceneString(r14, "right", this.mRight);
        getSceneString(r14, "bottom", this.mBottom);
        getSceneString(r14, "baseline", this.mBaseline);
        getSceneString(r14, "centerX", this.mCenterX);
        getSceneString(r14, "centerY", this.mCenterY);
        getSceneString(r14, "    width", this.mWidth, this.mMinWidth, this.mMaxDimension[0], this.mWidthOverride, this.mMatchConstraintMinWidth, this.mMatchConstraintDefaultWidth, this.mMatchConstraintPercentWidth, this.mListDimensionBehaviors[0], this.mWeight[0]);
        getSceneString(r14, "    height", this.mHeight, this.mMinHeight, this.mMaxDimension[1], this.mHeightOverride, this.mMatchConstraintMinHeight, this.mMatchConstraintDefaultHeight, this.mMatchConstraintPercentHeight, this.mListDimensionBehaviors[1], this.mWeight[1]);
        serializeDimensionRatio(r14, "    dimensionRatio", this.mDimensionRatio, this.mDimensionRatioSide);
        serializeAttribute(r14, "    horizontalBias", this.mHorizontalBiasPercent, DEFAULT_BIAS);
        serializeAttribute(r14, "    verticalBias", this.mVerticalBiasPercent, DEFAULT_BIAS);
        serializeAttribute(r14, "    horizontalChainStyle", this.mHorizontalChainStyle, 0);
        serializeAttribute(r14, "    verticalChainStyle", this.mVerticalChainStyle, 0);
        r14.append("  }");
    }

    public float getVerticalBiasPercent() {
        return this.mVerticalBiasPercent;
    }

    public int getVerticalChainStyle() {
        return this.mVerticalChainStyle;
    }

    public DimensionBehaviour getVerticalDimensionBehaviour() {
        return this.mListDimensionBehaviors[1];
    }

    public int getVerticalMargin() {
        if (this.mLeft == null) goto L5;
        int r0 = this.mTop.mMargin;
    L7:
        if (this.mRight != null) goto L9;
        return r0;
    L9:
        return r0 + this.mBottom.mMargin;
    L5:
        r0 = 0;
        goto L7
    }

    public int getVisibility() {
        return this.mVisibility;
    }

    public int getWidth() {
        if (this.mVisibility != 8) goto L7;
        return 0;
    L7:
        return this.mWidth;
    }

    public int getX() {
        ConstraintWidget r0 = this.mParent;
        if (r0 == null) goto L9;
        if ((r0 instanceof ConstraintWidgetContainer) == false) goto L9;
        return ((ConstraintWidgetContainer) r0).mPaddingLeft + this.mX;
    L9:
        return this.mX;
    }

    public int getY() {
        ConstraintWidget r0 = this.mParent;
        if (r0 == null) goto L9;
        if ((r0 instanceof ConstraintWidgetContainer) == false) goto L9;
        return ((ConstraintWidgetContainer) r0).mPaddingTop + this.mY;
    L9:
        return this.mY;
    }

    public boolean hasBaseline() {
        return this.mHasBaseline;
    }

    public boolean hasDanglingDimension(int r5) {
        if (r5 != 0) goto L17;
        if (this.mLeft.mTarget == null) goto L7;
        int r52 = 1;
    L9:
        if (this.mRight.mTarget == null) goto L11;
        int r4 = 1;
    L13:
        if ((r52 + r4) >= 2) goto L15;
        return true;
    L15:
        return false;
    L11:
        r4 = 0;
        goto L13
    L7:
        r52 = 0;
        goto L9
    L17:
        if (this.mTop.mTarget == null) goto L19;
        int r53 = 1;
    L21:
        if (this.mBottom.mTarget == null) goto L23;
        int r3 = 1;
    L24:
        int r54 = r53 + r3;
        if (this.mBaseline.mTarget == null) goto L27;
        int r42 = 1;
    L29:
        if ((r54 + r42) >= 2) goto L31;
        return true;
    L31:
        return false;
    L27:
        r42 = 0;
        goto L29
    L23:
        r3 = 0;
        goto L24
    L19:
        r53 = 0;
        goto L21
    }

    public boolean hasDependencies() {
        int r0 = this.mAnchors.size();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L9;
        if (this.mAnchors.get(r2).hasDependents() == true) goto L6;
        r2 = r2 + 1;
        goto L3
    L6:
        return true;
    L9:
        return false;
    }

    public boolean hasDimensionOverride() {
        if (this.mWidthOverride == (-1)) goto L5;
        return true;
    L5:
        if (this.mHeightOverride != (-1)) goto L11;
        return false;
    L11:
        return true;
    }

    public boolean hasResolvedTargets(int r4, int r5) {
        if (r4 != 0) goto L16;
        ConstraintAnchor r42 = this.mLeft.mTarget;
        if (r42 != null) goto L7;
    L27:
        return false;
    L7:
        if (r42.hasFinalValue() == false) goto L27;
        ConstraintAnchor r43 = this.mRight.mTarget;
        if (r43 == null) goto L27;
        if (r43.hasFinalValue() == false) goto L27;
        if (((this.mRight.mTarget.getFinalValue() - this.mRight.getMargin()) - (this.mLeft.mTarget.getFinalValue() + this.mLeft.getMargin())) < r5) goto L15;
        return true;
    L15:
        return false;
    L16:
        ConstraintAnchor r44 = this.mTop.mTarget;
        if (r44 == null) goto L27;
        if (r44.hasFinalValue() == false) goto L27;
        ConstraintAnchor r45 = this.mBottom.mTarget;
        if (r45 == null) goto L27;
        if (r45.hasFinalValue() == false) goto L27;
        if (((this.mBottom.mTarget.getFinalValue() - this.mBottom.getMargin()) - (this.mTop.mTarget.getFinalValue() + this.mTop.getMargin())) < r5) goto L27;
        return true;
    }

    public void immediateConnect(ConstraintAnchor.Type r1, ConstraintWidget r2, ConstraintAnchor.Type r3, int r4, int r5) {
        getAnchor(r1).connect(r2.getAnchor(r3), r4, r5, true);
    }

    public boolean isHorizontalSolvingPassDone() {
        return this.mHorizontalSolvingPass;
    }

    public boolean isInBarrier(int r1) {
        return this.mIsInBarrier[r1];
    }

    public boolean isInHorizontalChain() {
        ConstraintAnchor r0 = this.mLeft;
        ConstraintAnchor r1 = r0.mTarget;
        if (r1 != null) goto L5;
    L6:
        ConstraintAnchor r2 = this.mRight;
        ConstraintAnchor r02 = r2.mTarget;
        if (r02 != null) goto L9;
        return false;
    L9:
        if (r02.mTarget != r2) goto L15;
        return true;
    L15:
        return false;
    L5:
        if (r1.mTarget != r0) goto L6;
        return true;
    }

    public boolean isInPlaceholder() {
        return this.mInPlaceholder;
    }

    public boolean isInVerticalChain() {
        ConstraintAnchor r0 = this.mTop;
        ConstraintAnchor r1 = r0.mTarget;
        if (r1 != null) goto L5;
    L6:
        ConstraintAnchor r2 = this.mBottom;
        ConstraintAnchor r02 = r2.mTarget;
        if (r02 != null) goto L9;
        return false;
    L9:
        if (r02.mTarget != r2) goto L15;
        return true;
    L15:
        return false;
    L5:
        if (r1.mTarget != r0) goto L6;
        return true;
    }

    public boolean isInVirtualLayout() {
        return this.mInVirtualLayout;
    }

    public boolean isMeasureRequested() {
        if (this.mMeasureRequested == true) goto L5;
        return false;
    L5:
        if (this.mVisibility == 8) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isResolvedHorizontally() {
        if (this.mResolvedHorizontal == false) goto L5;
        return true;
    L5:
        if (this.mLeft.hasFinalValue() == true) goto L7;
        return false;
    L7:
        if (this.mRight.hasFinalValue() == true) goto L14;
        return false;
    L14:
        return true;
    }

    public boolean isResolvedVertically() {
        if (this.mResolvedVertical == false) goto L5;
        return true;
    L5:
        if (this.mTop.hasFinalValue() == true) goto L7;
        return false;
    L7:
        if (this.mBottom.hasFinalValue() == true) goto L14;
        return false;
    L14:
        return true;
    }

    public boolean isVerticalSolvingPassDone() {
        return this.mVerticalSolvingPass;
    }

    public void markHorizontalSolvingPassDone() {
        this.mHorizontalSolvingPass = true;
    }

    public void markVerticalSolvingPassDone() {
        this.mVerticalSolvingPass = true;
    }

    public boolean oppositeDimensionsTied() {
        DimensionBehaviour[] r3 = this.mListDimensionBehaviors;
        DimensionBehaviour r1 = r3[0];
        DimensionBehaviour r2 = DimensionBehaviour.MATCH_CONSTRAINT;
        if (r1 == r2) goto L5;
    L7:
        return false;
    L5:
        if (r3[1] != r2) goto L7;
        return true;
    }

    public void reset() {
        this.mLeft.reset();
        this.mTop.reset();
        this.mRight.reset();
        this.mBottom.reset();
        this.mBaseline.reset();
        this.mCenterX.reset();
        this.mCenterY.reset();
        this.mCenter.reset();
        this.mParent = null;
        this.mCircleConstraintAngle = Float.NaN;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mDimensionRatio = 0.0f;
        this.mDimensionRatioSide = -1;
        this.mX = 0;
        this.mY = 0;
        this.mOffsetX = 0;
        this.mOffsetY = 0;
        this.mBaselineDistance = 0;
        this.mMinWidth = 0;
        this.mMinHeight = 0;
        float r3 = DEFAULT_BIAS;
        this.mHorizontalBiasPercent = r3;
        this.mVerticalBiasPercent = r3;
        DimensionBehaviour[] r32 = this.mListDimensionBehaviors;
        DimensionBehaviour r4 = DimensionBehaviour.FIXED;
        r32[0] = r4;
        r32[1] = r4;
        this.mCompanionWidget = null;
        this.mContainerItemSkip = 0;
        this.mVisibility = 0;
        this.mType = null;
        this.mHorizontalWrapVisited = false;
        this.mVerticalWrapVisited = false;
        this.mHorizontalChainStyle = 0;
        this.mVerticalChainStyle = 0;
        this.mHorizontalChainFixedPosition = false;
        this.mVerticalChainFixedPosition = false;
        float[] r0 = this.mWeight;
        r0[0] = -1.0f;
        r0[1] = -1.0f;
        this.mHorizontalResolution = -1;
        this.mVerticalResolution = -1;
        int[] r02 = this.mMaxDimension;
        r02[0] = Integer.MAX_VALUE;
        r02[1] = Integer.MAX_VALUE;
        this.mMatchConstraintDefaultWidth = 0;
        this.mMatchConstraintDefaultHeight = 0;
        this.mMatchConstraintPercentWidth = 1.0f;
        this.mMatchConstraintPercentHeight = 1.0f;
        this.mMatchConstraintMaxWidth = Integer.MAX_VALUE;
        this.mMatchConstraintMaxHeight = Integer.MAX_VALUE;
        this.mMatchConstraintMinWidth = 0;
        this.mMatchConstraintMinHeight = 0;
        this.mResolvedHasRatio = false;
        this.mResolvedDimensionRatioSide = -1;
        this.mResolvedDimensionRatio = 1.0f;
        this.mGroupsToSolver = false;
        boolean[] r03 = this.isTerminalWidget;
        r03[0] = true;
        r03[1] = true;
        this.mInVirtualLayout = false;
        boolean[] r04 = this.mIsInBarrier;
        r04[0] = false;
        r04[1] = false;
        this.mMeasureRequested = true;
        int[] r05 = this.mResolvedMatchConstraintDefault;
        r05[0] = 0;
        r05[1] = 0;
        this.mWidthOverride = -1;
        this.mHeightOverride = -1;
    }

    public void resetAnchors() {
        ConstraintWidget r0 = getParent();
        if (r0 != null) goto L5;
    L9:
        int r02 = this.mAnchors.size();
        int r1 = 0;
    L10:
        if (r1 >= r02) goto L14;
        this.mAnchors.get(r1).reset();
        r1 = r1 + 1;
        goto L10
    L14:
        return;
    L5:
        if ((r0 instanceof ConstraintWidgetContainer) == false) goto L9;
        if (((ConstraintWidgetContainer) getParent()).handlesInternalConstraints() == false) goto L9;
    }

    public void resetFinalResolution() {
        int r0 = 0;
        this.mResolvedHorizontal = false;
        this.mResolvedVertical = false;
        this.mHorizontalSolvingPass = false;
        this.mVerticalSolvingPass = false;
        int r1 = this.mAnchors.size();
    L3:
        if (r0 >= r1) goto L5;
        this.mAnchors.get(r0).resetFinalResolution();
        r0 = r0 + 1;
        goto L3
    }

    public void resetSolverVariables(Cache r2) {
        this.mLeft.resetSolverVariable(r2);
        this.mTop.resetSolverVariable(r2);
        this.mRight.resetSolverVariable(r2);
        this.mBottom.resetSolverVariable(r2);
        this.mBaseline.resetSolverVariable(r2);
        this.mCenter.resetSolverVariable(r2);
        this.mCenterX.resetSolverVariable(r2);
        this.mCenterY.resetSolverVariable(r2);
    }

    public void setBaselineDistance(int r1) {
        this.mBaselineDistance = r1;
        if (r1 <= 0) goto L5;
        boolean r12 = true;
    L6:
        this.mHasBaseline = r12;
        return;
    L5:
        r12 = false;
        goto L6
    }

    public void setCompanionWidget(Object r1) {
        this.mCompanionWidget = r1;
    }

    public void setDebugName(String r1) {
        this.mDebugName = r1;
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:39:0x0086 -> B:40:0x0087). Please report as a decompilation issue!!! */
    public void setDimensionRatio(String r9) {
        int r0 = 0;
        if (r9 != null) goto L5;
    L44:
        this.mDimensionRatio = 0.0f;
        return;
    L5:
        if (r9.length() == 0) goto L44;
        int r1 = r9.length();
        int r2 = r9.indexOf(44);
        int r3 = 0;
        int r5 = -1;
        if (r2 > 0) goto L10;
    L19:
        int r22 = r9.indexOf(58);
        if (r22 >= 0) goto L22;
    L35:
        String r92 = r9.substring(r3);
        if (r92.length() <= 0) goto L39;
        float r93 = Float.parseFloat(r92);     // Catch: NumberFormatException -> L46
    L40:
        r0 = (r93 > r0 ? 1 : (r93 == r0 ? 0 : -1));
        if (r0 <= 0) goto L49;
        this.mDimensionRatio = r93;
        this.mDimensionRatioSide = r5;
        return;
    L49:
        return;
    L22:
        if (r22 >= (r1 - 1)) goto L35;
        String r12 = r9.substring(r3, r22);
        String r94 = r9.substring(r22 + 1);
        if (r12.length() <= 0) goto L39;
        if (r94.length() <= 0) goto L39;
        float r13 = Float.parseFloat(r12);     // Catch: NumberFormatException -> L46
        float r95 = Float.parseFloat(r94);     // Catch: NumberFormatException -> L46
        if (r13 <= 0.0f) goto L39;
        if (r95 <= 0.0f) goto L39;
        if (r5 != 1) goto L33;
        r93 = Math.abs(r95 / r13);     // Catch: NumberFormatException -> L46
        goto L40
    L33:
        r93 = Math.abs(r13 / r95);     // Catch: NumberFormatException -> L46
    L39:
        r93 = r0;
        goto L40
    L10:
        if (r2 >= (r1 - 1)) goto L19;
        String r6 = r9.substring(0, r2);
        if (r6.equalsIgnoreCase("W") == false) goto L15;
    L18:
        r5 = r3;
        r3 = r2 + 1;
        goto L19
    L15:
        if (r6.equalsIgnoreCase("H") == false) goto L17;
        r3 = 1;
        goto L18
    L17:
        r3 = -1;
        goto L18
    }

    public void setFinalBaseline(int r4) {
        if (this.mHasBaseline == true) goto L5;
        return;
    L5:
        int r0 = r4 - this.mBaselineDistance;
        int r1 = this.mHeight + r0;
        this.mY = r0;
        this.mTop.setFinalValue(r0);
        this.mBottom.setFinalValue(r1);
        this.mBaseline.setFinalValue(r4);
        this.mResolvedVertical = true;
    }

    public void setFinalHorizontal(int r2, int r3) {
        if (this.mResolvedHorizontal == false) goto L5;
        return;
    L5:
        this.mLeft.setFinalValue(r2);
        this.mRight.setFinalValue(r3);
        this.mX = r2;
        this.mWidth = r3 - r2;
        this.mResolvedHorizontal = true;
    }

    public void setFinalLeft(int r2) {
        this.mLeft.setFinalValue(r2);
        this.mX = r2;
    }

    public void setFinalTop(int r2) {
        this.mTop.setFinalValue(r2);
        this.mY = r2;
    }

    public void setFinalVertical(int r2, int r3) {
        if (this.mResolvedVertical == false) goto L5;
        return;
    L5:
        this.mTop.setFinalValue(r2);
        this.mBottom.setFinalValue(r3);
        this.mY = r2;
        this.mHeight = r3 - r2;
        if (this.mHasBaseline == false) goto L8;
        this.mBaseline.setFinalValue(r2 + this.mBaselineDistance);
    L8:
        this.mResolvedVertical = true;
    }

    public void setFrame(int r3, int r4, int r5, int r6) {
        int r52 = r5 - r3;
        int r62 = r6 - r4;
        this.mX = r3;
        this.mY = r4;
        if (this.mVisibility != 8) goto L6;
        this.mWidth = 0;
        this.mHeight = 0;
        return;
    L6:
        DimensionBehaviour[] r32 = this.mListDimensionBehaviors;
        DimensionBehaviour r42 = r32[0];
        DimensionBehaviour r0 = DimensionBehaviour.FIXED;
        if (r42 != r0) goto L12;
        int r1 = this.mWidth;
        if (r52 >= r1) goto L12;
        r52 = r1;
    L12:
        if (r32[1] != r0) goto L16;
        int r33 = this.mHeight;
        if (r62 >= r33) goto L16;
        r62 = r33;
    L16:
        this.mWidth = r52;
        this.mHeight = r62;
        int r34 = this.mMinHeight;
        if (r62 >= r34) goto L19;
        this.mHeight = r34;
    L19:
        int r35 = this.mMinWidth;
        if (r52 >= r35) goto L22;
        this.mWidth = r35;
    L22:
        int r36 = this.mMatchConstraintMaxWidth;
        if (r36 > 0) goto L25;
    L27:
        int r37 = this.mMatchConstraintMaxHeight;
        if (r37 > 0) goto L30;
    L32:
        int r38 = this.mWidth;
        if (r52 == r38) goto L35;
        this.mWidthOverride = r38;
    L35:
        int r39 = this.mHeight;
        if (r62 == r39) goto L39;
        this.mHeightOverride = r39;
        return;
    L39:
        return;
    L30:
        if (this.mListDimensionBehaviors[1] != DimensionBehaviour.MATCH_CONSTRAINT) goto L32;
        this.mHeight = Math.min(this.mHeight, r37);
        goto L32
    L25:
        if (r42 != DimensionBehaviour.MATCH_CONSTRAINT) goto L27;
        this.mWidth = Math.min(this.mWidth, r36);
        goto L27
    }

    public void setHasBaseline(boolean r1) {
        this.mHasBaseline = r1;
    }

    public void setHeight(int r2) {
        this.mHeight = r2;
        int r0 = this.mMinHeight;
        if (r2 >= r0) goto L6;
        this.mHeight = r0;
        return;
    }

    public void setHorizontalBiasPercent(float r1) {
        this.mHorizontalBiasPercent = r1;
    }

    public void setHorizontalChainStyle(int r1) {
        this.mHorizontalChainStyle = r1;
    }

    public void setHorizontalDimension(int r1, int r2) {
        this.mX = r1;
        int r22 = r2 - r1;
        this.mWidth = r22;
        int r12 = this.mMinWidth;
        if (r22 >= r12) goto L6;
        this.mWidth = r12;
        return;
    }

    public void setHorizontalDimensionBehaviour(DimensionBehaviour r2) {
        this.mListDimensionBehaviors[0] = r2;
    }

    public void setHorizontalMatchStyle(int r1, int r2, int r3, float r4) {
        this.mMatchConstraintDefaultWidth = r1;
        this.mMatchConstraintMinWidth = r2;
        if (r3 != Integer.MAX_VALUE) goto L5;
        r3 = 0;
    L5:
        this.mMatchConstraintMaxWidth = r3;
        this.mMatchConstraintPercentWidth = r4;
        if (r4 > 0.0f) goto L8;
        return;
    L8:
        if (r4 >= 1.0f) goto L13;
        if (r1 != 0) goto L14;
        this.mMatchConstraintDefaultWidth = 2;
        return;
    L14:
        return;
    }

    public void setHorizontalWeight(float r2) {
        this.mWeight[0] = r2;
    }

    public void setInBarrier(int r1, boolean r2) {
        this.mIsInBarrier[r1] = r2;
    }

    public void setInPlaceholder(boolean r1) {
        this.mInPlaceholder = r1;
    }

    public void setInVirtualLayout(boolean r1) {
        this.mInVirtualLayout = r1;
    }

    public void setLastMeasureSpec(int r1, int r2) {
        this.mLastHorizontalMeasureSpec = r1;
        this.mLastVerticalMeasureSpec = r2;
        setMeasureRequested(false);
    }

    public void setMaxHeight(int r2) {
        this.mMaxDimension[1] = r2;
    }

    public void setMaxWidth(int r2) {
        this.mMaxDimension[0] = r2;
    }

    public void setMeasureRequested(boolean r1) {
        this.mMeasureRequested = r1;
    }

    public void setMinHeight(int r1) {
        if (r1 >= 0) goto L5;
        this.mMinHeight = 0;
        return;
    L5:
        this.mMinHeight = r1;
    }

    public void setMinWidth(int r1) {
        if (r1 >= 0) goto L5;
        this.mMinWidth = 0;
        return;
    L5:
        this.mMinWidth = r1;
    }

    public void setOrigin(int r1, int r2) {
        this.mX = r1;
        this.mY = r2;
    }

    public void setParent(ConstraintWidget r1) {
        this.mParent = r1;
    }

    public void setVerticalBiasPercent(float r1) {
        this.mVerticalBiasPercent = r1;
    }

    public void setVerticalChainStyle(int r1) {
        this.mVerticalChainStyle = r1;
    }

    public void setVerticalDimension(int r1, int r2) {
        this.mY = r1;
        int r22 = r2 - r1;
        this.mHeight = r22;
        int r12 = this.mMinHeight;
        if (r22 >= r12) goto L6;
        this.mHeight = r12;
        return;
    }

    public void setVerticalDimensionBehaviour(DimensionBehaviour r2) {
        this.mListDimensionBehaviors[1] = r2;
    }

    public void setVerticalMatchStyle(int r1, int r2, int r3, float r4) {
        this.mMatchConstraintDefaultHeight = r1;
        this.mMatchConstraintMinHeight = r2;
        if (r3 != Integer.MAX_VALUE) goto L5;
        r3 = 0;
    L5:
        this.mMatchConstraintMaxHeight = r3;
        this.mMatchConstraintPercentHeight = r4;
        if (r4 > 0.0f) goto L8;
        return;
    L8:
        if (r4 >= 1.0f) goto L13;
        if (r1 != 0) goto L14;
        this.mMatchConstraintDefaultHeight = 2;
        return;
    L14:
        return;
    }

    public void setVerticalWeight(float r2) {
        this.mWeight[1] = r2;
    }

    public void setVisibility(int r1) {
        this.mVisibility = r1;
    }

    public void setWidth(int r2) {
        this.mWidth = r2;
        int r0 = this.mMinWidth;
        if (r2 >= r0) goto L6;
        this.mWidth = r0;
        return;
    }

    public void setWrapBehaviorInParent(int r2) {
        if (r2 >= 0) goto L4;
        return;
    L4:
        if (r2 > 3) goto L8;
        this.mWrapBehaviorInParent = r2;
        return;
    }

    public void setX(int r1) {
        this.mX = r1;
    }

    public void setY(int r1) {
        this.mY = r1;
    }

    public void setupDimensionRatio(boolean r4, boolean r5, boolean r6, boolean r7) {
        if (this.mResolvedDimensionRatioSide != (-1)) goto L13;
        if (r6 == false) goto L7;
        if (r7 == true) goto L7;
        this.mResolvedDimensionRatioSide = 0;
    L7:
        if (r6 == true) goto L13;
        if (r7 == false) goto L13;
        this.mResolvedDimensionRatioSide = 1;
        if (this.mDimensionRatioSide != (-1)) goto L13;
        this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
    L13:
        if (this.mResolvedDimensionRatioSide != 0) goto L20;
        if (this.mTop.isConnected() == true) goto L17;
    L18:
        this.mResolvedDimensionRatioSide = 1;
    L27:
        if (this.mResolvedDimensionRatioSide != (-1)) goto L47;
        if (this.mTop.isConnected() == false) goto L37;
        if (this.mBottom.isConnected() == false) goto L37;
        if (this.mLeft.isConnected() == false) goto L37;
        if (this.mRight.isConnected() == true) goto L47;
    L37:
        if (this.mTop.isConnected() == false) goto L42;
        if (this.mBottom.isConnected() == false) goto L42;
        this.mResolvedDimensionRatioSide = 0;
    L42:
        if (this.mLeft.isConnected() == false) goto L47;
        if (this.mRight.isConnected() == false) goto L47;
        this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
        this.mResolvedDimensionRatioSide = 1;
    L47:
        if (this.mResolvedDimensionRatioSide != (-1)) goto L59;
        int r42 = this.mMatchConstraintMinWidth;
        if (r42 > 0) goto L51;
    L54:
        if (r42 == 0) goto L56;
        return;
    L56:
        if (this.mMatchConstraintMinHeight <= 0) goto L61;
        this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
        this.mResolvedDimensionRatioSide = 1;
        return;
    L61:
        return;
    L51:
        if (this.mMatchConstraintMinHeight != 0) goto L54;
        this.mResolvedDimensionRatioSide = 0;
        return;
    L59:
        return;
    L17:
        if (this.mBottom.isConnected() == false) goto L18;
    L20:
        if (this.mResolvedDimensionRatioSide != 1) goto L27;
        if (this.mLeft.isConnected() == true) goto L24;
    L25:
        this.mResolvedDimensionRatioSide = 0;
        goto L27
    L24:
        if (this.mRight.isConnected() == true) goto L27;
        goto L25
    }

    public String toString() {
        StringBuilder r0 = new StringBuilder();
        String r1 = this.mType;
        String r2 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r1 == null) goto L5;
        String r12 = "type: " + this.mType + " ";
    L6:
        r0.append(r12);
        if (this.mDebugName == null) goto L9;
        r2 = "id: " + this.mDebugName + " ";
    L9:
        r0.append(r2);
        r0.append("(");
        r0.append(this.mX);
        r0.append(", ");
        r0.append(this.mY);
        r0.append(") - (");
        r0.append(this.mWidth);
        r0.append(" x ");
        r0.append(this.mHeight);
        r0.append(")");
        return r0.toString();
    L5:
        r12 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L6
    }

    public void updateFromRuns(boolean r8, boolean r9) {
        boolean r82 = r8 & this.mHorizontalRun.isResolved();
        boolean r92 = r9 & this.mVerticalRun.isResolved();
        HorizontalWidgetRun r0 = this.mHorizontalRun;
        int r1 = r0.start.value;
        VerticalWidgetRun r2 = this.mVerticalRun;
        int r3 = r2.start.value;
        int r02 = r0.end.value;
        int r22 = r2.end.value;
        int r5 = r22 - r3;
        if ((r02 - r1) < 0) goto L15;
        if (r5 < 0) goto L15;
        if (r1 == Integer.MIN_VALUE) goto L15;
        if (r1 == Integer.MAX_VALUE) goto L15;
        if (r3 == Integer.MIN_VALUE) goto L15;
        if (r3 == Integer.MAX_VALUE) goto L15;
        if (r02 == Integer.MIN_VALUE) goto L15;
        if (r02 == Integer.MAX_VALUE) goto L15;
        if (r22 == Integer.MIN_VALUE) goto L15;
        if (r22 == Integer.MAX_VALUE) goto L15;
    L16:
        int r03 = r02 - r1;
        int r23 = r22 - r3;
        if (r82 == false) goto L19;
        this.mX = r1;
    L19:
        if (r92 == false) goto L22;
        this.mY = r3;
    L22:
        if (this.mVisibility != 8) goto L25;
        this.mWidth = 0;
        this.mHeight = 0;
        return;
    L25:
        if (r82 == true) goto L27;
    L34:
        if (r92 == true) goto L36;
        return;
    L36:
        if (this.mListDimensionBehaviors[1] != DimensionBehaviour.FIXED) goto L40;
        int r83 = this.mHeight;
        if (r23 >= r83) goto L40;
        r23 = r83;
    L40:
        this.mHeight = r23;
        int r84 = this.mMinHeight;
        if (r23 >= r84) goto L45;
        this.mHeight = r84;
        return;
    L45:
        return;
    L27:
        if (this.mListDimensionBehaviors[0] != DimensionBehaviour.FIXED) goto L31;
        int r85 = this.mWidth;
        if (r03 >= r85) goto L31;
        r03 = r85;
    L31:
        this.mWidth = r03;
        int r86 = this.mMinWidth;
        if (r03 >= r86) goto L34;
        this.mWidth = r86;
    L15:
        r02 = 0;
        r1 = 0;
        r22 = 0;
        r3 = 0;
        goto L16
    }

    public void updateFromSolver(LinearSystem r7, boolean r8) {
        int r0 = r7.getObjectVariableValue(this.mLeft);
        int r1 = r7.getObjectVariableValue(this.mTop);
        int r2 = r7.getObjectVariableValue(this.mRight);
        int r72 = r7.getObjectVariableValue(this.mBottom);
        if (r8 == false) goto L11;
        HorizontalWidgetRun r3 = this.mHorizontalRun;
        if (r3 == null) goto L11;
        DependencyNode r4 = r3.start;
        if (r4.resolved == false) goto L11;
        DependencyNode r32 = r3.end;
        if (r32.resolved == false) goto L11;
        r0 = r4.value;
        r2 = r32.value;
    L11:
        if (r8 == false) goto L19;
        VerticalWidgetRun r82 = this.mVerticalRun;
        if (r82 == null) goto L19;
        DependencyNode r33 = r82.start;
        if (r33.resolved == false) goto L19;
        DependencyNode r83 = r82.end;
        if (r83.resolved == false) goto L19;
        r1 = r33.value;
        r72 = r83.value;
    L19:
        int r34 = r72 - r1;
        if ((r2 - r0) < 0) goto L32;
        if (r34 < 0) goto L32;
        if (r0 == Integer.MIN_VALUE) goto L32;
        if (r0 == Integer.MAX_VALUE) goto L32;
        if (r1 == Integer.MIN_VALUE) goto L32;
        if (r1 == Integer.MAX_VALUE) goto L32;
        if (r2 == Integer.MIN_VALUE) goto L32;
        if (r2 == Integer.MAX_VALUE) goto L32;
        if (r72 == Integer.MIN_VALUE) goto L32;
        if (r72 == Integer.MAX_VALUE) goto L32;
    L33:
        setFrame(r0, r1, r2, r72);
        return;
    L32:
        r0 = 0;
        r72 = 0;
        r1 = 0;
        r2 = 0;
        goto L33
    }
}
