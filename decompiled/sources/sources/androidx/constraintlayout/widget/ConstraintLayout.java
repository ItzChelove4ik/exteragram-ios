package androidx.constraintlayout.widget;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.core.Metrics;
import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import androidx.constraintlayout.core.widgets.Optimizer;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import androidx.mediarouter.media.GlobalMediaRouter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.mvel2.asm.Opcodes;
import org.telegram.tgnet.TLObject;

/* loaded from: classes3.dex */
public class ConstraintLayout extends ViewGroup {
    private static final boolean DEBUG = false;
    private static final boolean DEBUG_DRAW_CONSTRAINTS = false;
    public static final int DESIGN_INFO_ID = 0;
    private static final boolean OPTIMIZE_HEIGHT_CHANGE = false;
    private static final String TAG = "ConstraintLayout";
    private static final boolean USE_CONSTRAINTS_HELPER = true;
    public static final String VERSION = "ConstraintLayout-2.2.0-alpha04";
    private static SharedValues sSharedValues;
    SparseArray<View> mChildrenByIds;
    private ArrayList<ConstraintHelper> mConstraintHelpers;
    protected ConstraintLayoutStates mConstraintLayoutSpec;
    private ConstraintSet mConstraintSet;
    private int mConstraintSetId;
    private HashMap<String, Integer> mDesignIds;
    protected boolean mDirtyHierarchy;
    private int mLastMeasureHeight;
    int mLastMeasureHeightMode;
    int mLastMeasureHeightSize;
    private int mLastMeasureWidth;
    int mLastMeasureWidthMode;
    int mLastMeasureWidthSize;
    protected ConstraintWidgetContainer mLayoutWidget;
    private int mMaxHeight;
    private int mMaxWidth;
    Measurer mMeasurer;
    private Metrics mMetrics;
    private int mMinHeight;
    private int mMinWidth;
    private ArrayList<ValueModifier> mModifiers;
    private int mOnMeasureHeightMeasureSpec;
    private int mOnMeasureWidthMeasureSpec;
    private int mOptimizationLevel;
    private SparseArray<ConstraintWidget> mTempMapIdToWidget;

    /* renamed from: androidx.constraintlayout.widget.ConstraintLayout$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintWidget$DimensionBehaviour = null;

        static {
            int[] r0 = new int[ConstraintWidget.DimensionBehaviour.values().length];
            $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintWidget$DimensionBehaviour = r0;
            r0[ConstraintWidget.DimensionBehaviour.FIXED.ordinal()] = 1;     // Catch: NoSuchFieldError -> L8
        L12:
            $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintWidget$DimensionBehaviour[ConstraintWidget.DimensionBehaviour.WRAP_CONTENT.ordinal()] = 2;     // Catch: NoSuchFieldError -> L9
        L14:
            $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintWidget$DimensionBehaviour[ConstraintWidget.DimensionBehaviour.MATCH_PARENT.ordinal()] = 3;     // Catch: NoSuchFieldError -> L10
        L18:
            $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintWidget$DimensionBehaviour[ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT.ordinal()] = 4;     // Catch: NoSuchFieldError -> L11
            return;
        }
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public int baselineMargin;
        public int baselineToBaseline;
        public int baselineToBottom;
        public int baselineToTop;
        public int bottomToBottom;
        public int bottomToTop;
        public float circleAngle;
        public int circleConstraint;
        public int circleRadius;
        public boolean constrainedHeight;
        public boolean constrainedWidth;
        public String constraintTag;
        public String dimensionRatio;
        public int editorAbsoluteX;
        public int editorAbsoluteY;
        public int endToEnd;
        public int endToStart;
        public int goneBaselineMargin;
        public int goneBottomMargin;
        public int goneEndMargin;
        public int goneLeftMargin;
        public int goneRightMargin;
        public int goneStartMargin;
        public int goneTopMargin;
        public int guideBegin;
        public int guideEnd;
        public float guidePercent;
        public boolean guidelineUseRtl;
        public boolean helped;
        public float horizontalBias;
        public int horizontalChainStyle;
        public float horizontalWeight;
        public int leftToLeft;
        public int leftToRight;
        int mDimensionRatioSide;
        float mDimensionRatioValue;
        boolean mHeightSet;
        boolean mHorizontalDimensionFixed;
        boolean mIsGuideline;
        boolean mIsHelper;
        boolean mIsInPlaceholder;
        boolean mIsVirtualGroup;
        boolean mNeedsBaseline;
        int mResolveGoneLeftMargin;
        int mResolveGoneRightMargin;
        int mResolvedGuideBegin;
        int mResolvedGuideEnd;
        float mResolvedGuidePercent;
        float mResolvedHorizontalBias;
        int mResolvedLeftToLeft;
        int mResolvedLeftToRight;
        int mResolvedRightToLeft;
        int mResolvedRightToRight;
        boolean mVerticalDimensionFixed;
        ConstraintWidget mWidget;
        boolean mWidthSet;
        public int matchConstraintDefaultHeight;
        public int matchConstraintDefaultWidth;
        public int matchConstraintMaxHeight;
        public int matchConstraintMaxWidth;
        public int matchConstraintMinHeight;
        public int matchConstraintMinWidth;
        public float matchConstraintPercentHeight;
        public float matchConstraintPercentWidth;
        public int orientation;
        public int rightToLeft;
        public int rightToRight;
        public int startToEnd;
        public int startToStart;
        public int topToBottom;
        public int topToTop;
        public float verticalBias;
        public int verticalChainStyle;
        public float verticalWeight;
        public int wrapBehaviorInParent;

        public static class Table {
            public static final SparseIntArray sMap = null;

            static {
                SparseIntArray r0 = new SparseIntArray();
                sMap = r0;
                r0.append(R$styleable.ConstraintLayout_Layout_layout_constraintWidth, 64);
                r0.append(R$styleable.ConstraintLayout_Layout_layout_constraintHeight, 65);
                r0.append(R$styleable.ConstraintLayout_Layout_layout_constraintLeft_toLeftOf, 8);
                r0.append(R$styleable.ConstraintLayout_Layout_layout_constraintLeft_toRightOf, 9);
                r0.append(R$styleable.ConstraintLayout_Layout_layout_constraintRight_toLeftOf, 10);
                r0.append(R$styleable.ConstraintLayout_Layout_layout_constraintRight_toRightOf, 11);
                r0.append(R$styleable.ConstraintLayout_Layout_layout_constraintTop_toTopOf, 12);
                r0.append(R$styleable.ConstraintLayout_Layout_layout_constraintTop_toBottomOf, 13);
                r0.append(R$styleable.ConstraintLayout_Layout_layout_constraintBottom_toTopOf, 14);
                r0.append(R$styleable.ConstraintLayout_Layout_layout_constraintBottom_toBottomOf, 15);
                r0.append(R$styleable.ConstraintLayout_Layout_layout_constraintBaseline_toBaselineOf, 16);
                r0.append(R$styleable.ConstraintLayout_Layout_layout_constraintBaseline_toTopOf, 52);
                r0.append(R$styleable.ConstraintLayout_Layout_layout_constraintBaseline_toBottomOf, 53);
                r0.append(R$styleable.ConstraintLayout_Layout_layout_constraintCircle, 2);
                r0.append(R$styleable.ConstraintLayout_Layout_layout_constraintCircleRadius, 3);
                r0.append(R$styleable.ConstraintLayout_Layout_layout_constraintCircleAngle, 4);
                r0.append(R$styleable.ConstraintLayout_Layout_layout_editor_absoluteX, 49);
                r0.append(R$styleable.ConstraintLayout_Layout_layout_editor_absoluteY, 50);
                r0.append(R$styleable.ConstraintLayout_Layout_layout_constraintGuide_begin, 5);
                r0.append(R$styleable.ConstraintLayout_Layout_layout_constraintGuide_end, 6);
                r0.append(R$styleable.ConstraintLayout_Layout_layout_constraintGuide_percent, 7);
                r0.append(R$styleable.ConstraintLayout_Layout_guidelineUseRtl, 67);
                r0.append(R$styleable.ConstraintLayout_Layout_android_orientation, 1);
                r0.append(R$styleable.ConstraintLayout_Layout_layout_constraintStart_toEndOf, 17);
                r0.append(R$styleable.ConstraintLayout_Layout_layout_constraintStart_toStartOf, 18);
                r0.append(R$styleable.ConstraintLayout_Layout_layout_constraintEnd_toStartOf, 19);
                r0.append(R$styleable.ConstraintLayout_Layout_layout_constraintEnd_toEndOf, 20);
                r0.append(R$styleable.ConstraintLayout_Layout_layout_goneMarginLeft, 21);
                r0.append(R$styleable.ConstraintLayout_Layout_layout_goneMarginTop, 22);
                r0.append(R$styleable.ConstraintLayout_Layout_layout_goneMarginRight, 23);
                r0.append(R$styleable.ConstraintLayout_Layout_layout_goneMarginBottom, 24);
                r0.append(R$styleable.ConstraintLayout_Layout_layout_goneMarginStart, 25);
                r0.append(R$styleable.ConstraintLayout_Layout_layout_goneMarginEnd, 26);
                r0.append(R$styleable.ConstraintLayout_Layout_layout_goneMarginBaseline, 55);
                r0.append(R$styleable.ConstraintLayout_Layout_layout_marginBaseline, 54);
                r0.append(R$styleable.ConstraintLayout_Layout_layout_constraintHorizontal_bias, 29);
                r0.append(R$styleable.ConstraintLayout_Layout_layout_constraintVertical_bias, 30);
                r0.append(R$styleable.ConstraintLayout_Layout_layout_constraintDimensionRatio, 44);
                r0.append(R$styleable.ConstraintLayout_Layout_layout_constraintHorizontal_weight, 45);
                r0.append(R$styleable.ConstraintLayout_Layout_layout_constraintVertical_weight, 46);
                r0.append(R$styleable.ConstraintLayout_Layout_layout_constraintHorizontal_chainStyle, 47);
                r0.append(R$styleable.ConstraintLayout_Layout_layout_constraintVertical_chainStyle, 48);
                r0.append(R$styleable.ConstraintLayout_Layout_layout_constrainedWidth, 27);
                r0.append(R$styleable.ConstraintLayout_Layout_layout_constrainedHeight, 28);
                r0.append(R$styleable.ConstraintLayout_Layout_layout_constraintWidth_default, 31);
                r0.append(R$styleable.ConstraintLayout_Layout_layout_constraintHeight_default, 32);
                r0.append(R$styleable.ConstraintLayout_Layout_layout_constraintWidth_min, 33);
                r0.append(R$styleable.ConstraintLayout_Layout_layout_constraintWidth_max, 34);
                r0.append(R$styleable.ConstraintLayout_Layout_layout_constraintWidth_percent, 35);
                r0.append(R$styleable.ConstraintLayout_Layout_layout_constraintHeight_min, 36);
                r0.append(R$styleable.ConstraintLayout_Layout_layout_constraintHeight_max, 37);
                r0.append(R$styleable.ConstraintLayout_Layout_layout_constraintHeight_percent, 38);
                r0.append(R$styleable.ConstraintLayout_Layout_layout_constraintLeft_creator, 39);
                r0.append(R$styleable.ConstraintLayout_Layout_layout_constraintTop_creator, 40);
                r0.append(R$styleable.ConstraintLayout_Layout_layout_constraintRight_creator, 41);
                r0.append(R$styleable.ConstraintLayout_Layout_layout_constraintBottom_creator, 42);
                r0.append(R$styleable.ConstraintLayout_Layout_layout_constraintBaseline_creator, 43);
                r0.append(R$styleable.ConstraintLayout_Layout_layout_constraintTag, 51);
                r0.append(R$styleable.ConstraintLayout_Layout_layout_wrapBehaviorInParent, 66);
            }
        }

        public LayoutParams(int r7, int r8) {
            super(r7, r8);
            this.guideBegin = -1;
            this.guideEnd = -1;
            this.guidePercent = -1.0f;
            this.guidelineUseRtl = true;
            this.leftToLeft = -1;
            this.leftToRight = -1;
            this.rightToLeft = -1;
            this.rightToRight = -1;
            this.topToTop = -1;
            this.topToBottom = -1;
            this.bottomToTop = -1;
            this.bottomToBottom = -1;
            this.baselineToBaseline = -1;
            this.baselineToTop = -1;
            this.baselineToBottom = -1;
            this.circleConstraint = -1;
            this.circleRadius = 0;
            this.circleAngle = 0.0f;
            this.startToEnd = -1;
            this.startToStart = -1;
            this.endToStart = -1;
            this.endToEnd = -1;
            this.goneLeftMargin = Integer.MIN_VALUE;
            this.goneTopMargin = Integer.MIN_VALUE;
            this.goneRightMargin = Integer.MIN_VALUE;
            this.goneBottomMargin = Integer.MIN_VALUE;
            this.goneStartMargin = Integer.MIN_VALUE;
            this.goneEndMargin = Integer.MIN_VALUE;
            this.goneBaselineMargin = Integer.MIN_VALUE;
            this.baselineMargin = 0;
            this.mWidthSet = true;
            this.mHeightSet = true;
            this.horizontalBias = 0.5f;
            this.verticalBias = 0.5f;
            this.dimensionRatio = null;
            this.mDimensionRatioValue = 0.0f;
            this.mDimensionRatioSide = 1;
            this.horizontalWeight = -1.0f;
            this.verticalWeight = -1.0f;
            this.horizontalChainStyle = 0;
            this.verticalChainStyle = 0;
            this.matchConstraintDefaultWidth = 0;
            this.matchConstraintDefaultHeight = 0;
            this.matchConstraintMinWidth = 0;
            this.matchConstraintMinHeight = 0;
            this.matchConstraintMaxWidth = 0;
            this.matchConstraintMaxHeight = 0;
            this.matchConstraintPercentWidth = 1.0f;
            this.matchConstraintPercentHeight = 1.0f;
            this.editorAbsoluteX = -1;
            this.editorAbsoluteY = -1;
            this.orientation = -1;
            this.constrainedWidth = false;
            this.constrainedHeight = false;
            this.constraintTag = null;
            this.wrapBehaviorInParent = 0;
            this.mHorizontalDimensionFixed = true;
            this.mVerticalDimensionFixed = true;
            this.mNeedsBaseline = false;
            this.mIsGuideline = false;
            this.mIsHelper = false;
            this.mIsInPlaceholder = false;
            this.mIsVirtualGroup = false;
            this.mResolvedLeftToLeft = -1;
            this.mResolvedLeftToRight = -1;
            this.mResolvedRightToLeft = -1;
            this.mResolvedRightToRight = -1;
            this.mResolveGoneLeftMargin = Integer.MIN_VALUE;
            this.mResolveGoneRightMargin = Integer.MIN_VALUE;
            this.mResolvedHorizontalBias = 0.5f;
            this.mWidget = new ConstraintWidget();
            this.helped = false;
        }

        public LayoutParams(Context r11, AttributeSet r12) {
            super(r11, r12);
            this.guideBegin = -1;
            this.guideEnd = -1;
            this.guidePercent = -1.0f;
            this.guidelineUseRtl = true;
            this.leftToLeft = -1;
            this.leftToRight = -1;
            this.rightToLeft = -1;
            this.rightToRight = -1;
            this.topToTop = -1;
            this.topToBottom = -1;
            this.bottomToTop = -1;
            this.bottomToBottom = -1;
            this.baselineToBaseline = -1;
            this.baselineToTop = -1;
            this.baselineToBottom = -1;
            this.circleConstraint = -1;
            this.circleRadius = 0;
            this.circleAngle = 0.0f;
            this.startToEnd = -1;
            this.startToStart = -1;
            this.endToStart = -1;
            this.endToEnd = -1;
            this.goneLeftMargin = Integer.MIN_VALUE;
            this.goneTopMargin = Integer.MIN_VALUE;
            this.goneRightMargin = Integer.MIN_VALUE;
            this.goneBottomMargin = Integer.MIN_VALUE;
            this.goneStartMargin = Integer.MIN_VALUE;
            this.goneEndMargin = Integer.MIN_VALUE;
            this.goneBaselineMargin = Integer.MIN_VALUE;
            this.baselineMargin = 0;
            this.mWidthSet = true;
            this.mHeightSet = true;
            this.horizontalBias = 0.5f;
            this.verticalBias = 0.5f;
            this.dimensionRatio = null;
            this.mDimensionRatioValue = 0.0f;
            this.mDimensionRatioSide = 1;
            this.horizontalWeight = -1.0f;
            this.verticalWeight = -1.0f;
            this.horizontalChainStyle = 0;
            this.verticalChainStyle = 0;
            this.matchConstraintDefaultWidth = 0;
            this.matchConstraintDefaultHeight = 0;
            this.matchConstraintMinWidth = 0;
            this.matchConstraintMinHeight = 0;
            this.matchConstraintMaxWidth = 0;
            this.matchConstraintMaxHeight = 0;
            this.matchConstraintPercentWidth = 1.0f;
            this.matchConstraintPercentHeight = 1.0f;
            this.editorAbsoluteX = -1;
            this.editorAbsoluteY = -1;
            this.orientation = -1;
            this.constrainedWidth = false;
            this.constrainedHeight = false;
            this.constraintTag = null;
            this.wrapBehaviorInParent = 0;
            this.mHorizontalDimensionFixed = true;
            this.mVerticalDimensionFixed = true;
            this.mNeedsBaseline = false;
            this.mIsGuideline = false;
            this.mIsHelper = false;
            this.mIsInPlaceholder = false;
            this.mIsVirtualGroup = false;
            this.mResolvedLeftToLeft = -1;
            this.mResolvedLeftToRight = -1;
            this.mResolvedRightToLeft = -1;
            this.mResolvedRightToRight = -1;
            this.mResolveGoneLeftMargin = Integer.MIN_VALUE;
            this.mResolveGoneRightMargin = Integer.MIN_VALUE;
            this.mResolvedHorizontalBias = 0.5f;
            this.mWidget = new ConstraintWidget();
            this.helped = false;
            TypedArray r112 = r11.obtainStyledAttributes(r12, R$styleable.ConstraintLayout_Layout);
            int r122 = r112.getIndexCount();
            int r1 = 0;
        L3:
            if (r1 >= r122) goto L118;
            int r5 = r112.getIndex(r1);
            int r6 = Table.sMap.get(r5);
            switch(r6) {
                case 1: goto L116;
                case 2: goto L113;
                case 3: goto L112;
                case 4: goto L109;
                case 5: goto L108;
                case 6: goto L107;
                case 7: goto L106;
                case 8: goto L103;
                case 9: goto L100;
                case 10: goto L97;
                case 11: goto L94;
                case 12: goto L91;
                case 13: goto L88;
                case 14: goto L85;
                case 15: goto L82;
                case 16: goto L79;
                case 17: goto L76;
                case 18: goto L73;
                case 19: goto L70;
                case 20: goto L67;
                case 21: goto L66;
                case 22: goto L65;
                case 23: goto L64;
                case 24: goto L63;
                case 25: goto L62;
                case 26: goto L61;
                case 27: goto L60;
                case 28: goto L59;
                case 29: goto L58;
                case 30: goto L57;
                case 31: goto L54;
                case 32: goto L51;
                case 33: goto L122;
                case 34: goto L126;
                case 35: goto L40;
                case 36: goto L120;
                case 37: goto L124;
                case 38: goto L29;
                default: goto L6;
            };
        L6:
            switch(r6) {
                case 44: goto L28;
                case 45: goto L27;
                case 46: goto L26;
                case 47: goto L25;
                case 48: goto L24;
                case 49: goto L23;
                case 50: goto L22;
                case 51: goto L21;
                case 52: goto L18;
                case 53: goto L15;
                case 54: goto L14;
                case 55: goto L13;
                default: goto L7;
            };
        L7:
            switch(r6) {
                case 64: goto L12;
                case 65: goto L11;
                case 66: goto L10;
                case 67: goto L9;
                default: goto L117;
            };
        L9:
            this.guidelineUseRtl = r112.getBoolean(r5, this.guidelineUseRtl);
            goto L117
        L10:
            this.wrapBehaviorInParent = r112.getInt(r5, this.wrapBehaviorInParent);
            goto L117
        L11:
            ConstraintSet.parseDimensionConstraints(this, r112, r5, 1);
            this.mHeightSet = true;
            goto L117
        L12:
            ConstraintSet.parseDimensionConstraints(this, r112, r5, 0);
            this.mWidthSet = true;
        L117:
            r1 = r1 + 1;
            goto L3
        L13:
            this.goneBaselineMargin = r112.getDimensionPixelSize(r5, this.goneBaselineMargin);
            goto L117
        L14:
            this.baselineMargin = r112.getDimensionPixelSize(r5, this.baselineMargin);
            goto L117
        L15:
            int r62 = r112.getResourceId(r5, this.baselineToBottom);
            this.baselineToBottom = r62;
            if (r62 != (-1)) goto L117;
            this.baselineToBottom = r112.getInt(r5, -1);
            goto L117
        L18:
            int r63 = r112.getResourceId(r5, this.baselineToTop);
            this.baselineToTop = r63;
            if (r63 != (-1)) goto L117;
            this.baselineToTop = r112.getInt(r5, -1);
            goto L117
        L21:
            this.constraintTag = r112.getString(r5);
            goto L117
        L22:
            this.editorAbsoluteY = r112.getDimensionPixelOffset(r5, this.editorAbsoluteY);
            goto L117
        L23:
            this.editorAbsoluteX = r112.getDimensionPixelOffset(r5, this.editorAbsoluteX);
            goto L117
        L24:
            this.verticalChainStyle = r112.getInt(r5, 0);
            goto L117
        L25:
            this.horizontalChainStyle = r112.getInt(r5, 0);
            goto L117
        L26:
            this.verticalWeight = r112.getFloat(r5, this.verticalWeight);
            goto L117
        L27:
            this.horizontalWeight = r112.getFloat(r5, this.horizontalWeight);
            goto L117
        L28:
            ConstraintSet.parseDimensionRatioString(this, r112.getString(r5));
            goto L117
        L29:
            this.matchConstraintPercentHeight = Math.max(0.0f, r112.getFloat(r5, this.matchConstraintPercentHeight));
            this.matchConstraintDefaultHeight = 2;
            goto L117
        L40:
            this.matchConstraintPercentWidth = Math.max(0.0f, r112.getFloat(r5, this.matchConstraintPercentWidth));
            this.matchConstraintDefaultWidth = 2;
            goto L117
        L51:
            int r52 = r112.getInt(r5, 0);
            this.matchConstraintDefaultHeight = r52;
            if (r52 != 1) goto L117;
            Log.e(ConstraintLayout.TAG, "layout_constraintHeight_default=\"wrap\" is deprecated.\nUse layout_height=\"WRAP_CONTENT\" and layout_constrainedHeight=\"true\" instead.");
            goto L117
        L54:
            int r53 = r112.getInt(r5, 0);
            this.matchConstraintDefaultWidth = r53;
            if (r53 != 1) goto L117;
            Log.e(ConstraintLayout.TAG, "layout_constraintWidth_default=\"wrap\" is deprecated.\nUse layout_width=\"WRAP_CONTENT\" and layout_constrainedWidth=\"true\" instead.");
            goto L117
        L57:
            this.verticalBias = r112.getFloat(r5, this.verticalBias);
            goto L117
        L58:
            this.horizontalBias = r112.getFloat(r5, this.horizontalBias);
            goto L117
        L59:
            this.constrainedHeight = r112.getBoolean(r5, this.constrainedHeight);
            goto L117
        L60:
            this.constrainedWidth = r112.getBoolean(r5, this.constrainedWidth);
            goto L117
        L61:
            this.goneEndMargin = r112.getDimensionPixelSize(r5, this.goneEndMargin);
            goto L117
        L62:
            this.goneStartMargin = r112.getDimensionPixelSize(r5, this.goneStartMargin);
            goto L117
        L63:
            this.goneBottomMargin = r112.getDimensionPixelSize(r5, this.goneBottomMargin);
            goto L117
        L64:
            this.goneRightMargin = r112.getDimensionPixelSize(r5, this.goneRightMargin);
            goto L117
        L65:
            this.goneTopMargin = r112.getDimensionPixelSize(r5, this.goneTopMargin);
            goto L117
        L66:
            this.goneLeftMargin = r112.getDimensionPixelSize(r5, this.goneLeftMargin);
            goto L117
        L67:
            int r64 = r112.getResourceId(r5, this.endToEnd);
            this.endToEnd = r64;
            if (r64 != (-1)) goto L117;
            this.endToEnd = r112.getInt(r5, -1);
            goto L117
        L70:
            int r65 = r112.getResourceId(r5, this.endToStart);
            this.endToStart = r65;
            if (r65 != (-1)) goto L117;
            this.endToStart = r112.getInt(r5, -1);
            goto L117
        L73:
            int r66 = r112.getResourceId(r5, this.startToStart);
            this.startToStart = r66;
            if (r66 != (-1)) goto L117;
            this.startToStart = r112.getInt(r5, -1);
            goto L117
        L76:
            int r67 = r112.getResourceId(r5, this.startToEnd);
            this.startToEnd = r67;
            if (r67 != (-1)) goto L117;
            this.startToEnd = r112.getInt(r5, -1);
            goto L117
        L79:
            int r68 = r112.getResourceId(r5, this.baselineToBaseline);
            this.baselineToBaseline = r68;
            if (r68 != (-1)) goto L117;
            this.baselineToBaseline = r112.getInt(r5, -1);
            goto L117
        L82:
            int r69 = r112.getResourceId(r5, this.bottomToBottom);
            this.bottomToBottom = r69;
            if (r69 != (-1)) goto L117;
            this.bottomToBottom = r112.getInt(r5, -1);
            goto L117
        L85:
            int r610 = r112.getResourceId(r5, this.bottomToTop);
            this.bottomToTop = r610;
            if (r610 != (-1)) goto L117;
            this.bottomToTop = r112.getInt(r5, -1);
            goto L117
        L88:
            int r611 = r112.getResourceId(r5, this.topToBottom);
            this.topToBottom = r611;
            if (r611 != (-1)) goto L117;
            this.topToBottom = r112.getInt(r5, -1);
            goto L117
        L91:
            int r612 = r112.getResourceId(r5, this.topToTop);
            this.topToTop = r612;
            if (r612 != (-1)) goto L117;
            this.topToTop = r112.getInt(r5, -1);
            goto L117
        L94:
            int r613 = r112.getResourceId(r5, this.rightToRight);
            this.rightToRight = r613;
            if (r613 != (-1)) goto L117;
            this.rightToRight = r112.getInt(r5, -1);
            goto L117
        L97:
            int r614 = r112.getResourceId(r5, this.rightToLeft);
            this.rightToLeft = r614;
            if (r614 != (-1)) goto L117;
            this.rightToLeft = r112.getInt(r5, -1);
            goto L117
        L100:
            int r615 = r112.getResourceId(r5, this.leftToRight);
            this.leftToRight = r615;
            if (r615 != (-1)) goto L117;
            this.leftToRight = r112.getInt(r5, -1);
            goto L117
        L103:
            int r616 = r112.getResourceId(r5, this.leftToLeft);
            this.leftToLeft = r616;
            if (r616 != (-1)) goto L117;
            this.leftToLeft = r112.getInt(r5, -1);
            goto L117
        L106:
            this.guidePercent = r112.getFloat(r5, this.guidePercent);
            goto L117
        L107:
            this.guideEnd = r112.getDimensionPixelOffset(r5, this.guideEnd);
            goto L117
        L108:
            this.guideBegin = r112.getDimensionPixelOffset(r5, this.guideBegin);
            goto L117
        L109:
            float r54 = r112.getFloat(r5, this.circleAngle) % 360.0f;
            this.circleAngle = r54;
            if (r54 >= 0.0f) goto L117;
            this.circleAngle = (360.0f - r54) % 360.0f;
            goto L117
        L112:
            this.circleRadius = r112.getDimensionPixelSize(r5, this.circleRadius);
            goto L117
        L113:
            int r617 = r112.getResourceId(r5, this.circleConstraint);
            this.circleConstraint = r617;
            if (r617 != (-1)) goto L117;
            this.circleConstraint = r112.getInt(r5, -1);
            goto L117
        L116:
            this.orientation = r112.getInt(r5, this.orientation);
            goto L117
        L120:
            this.matchConstraintMinHeight = r112.getDimensionPixelSize(r5, this.matchConstraintMinHeight);     // Catch: Exception -> L37
        L38:
            if (r112.getInt(r5, this.matchConstraintMinHeight) != (-2)) goto L117;
            this.matchConstraintMinHeight = -2;
            goto L117
        L122:
            this.matchConstraintMinWidth = r112.getDimensionPixelSize(r5, this.matchConstraintMinWidth);     // Catch: Exception -> L48
        L49:
            if (r112.getInt(r5, this.matchConstraintMinWidth) != (-2)) goto L117;
            this.matchConstraintMinWidth = -2;
            goto L117
        L124:
            this.matchConstraintMaxHeight = r112.getDimensionPixelSize(r5, this.matchConstraintMaxHeight);     // Catch: Exception -> L32
        L33:
            if (r112.getInt(r5, this.matchConstraintMaxHeight) != (-2)) goto L117;
            this.matchConstraintMaxHeight = -2;
            goto L117
        L126:
            this.matchConstraintMaxWidth = r112.getDimensionPixelSize(r5, this.matchConstraintMaxWidth);     // Catch: Exception -> L43
        L44:
            if (r112.getInt(r5, this.matchConstraintMaxWidth) != (-2)) goto L117;
            this.matchConstraintMaxWidth = -2;
            goto L117
        L118:
            r112.recycle();
            validate();
        }

        @SuppressLint({"ClassVerificationFailure"})
        public LayoutParams(ViewGroup.LayoutParams r9) {
            super(r9);
            this.guideBegin = -1;
            this.guideEnd = -1;
            this.guidePercent = -1.0f;
            this.guidelineUseRtl = true;
            this.leftToLeft = -1;
            this.leftToRight = -1;
            this.rightToLeft = -1;
            this.rightToRight = -1;
            this.topToTop = -1;
            this.topToBottom = -1;
            this.bottomToTop = -1;
            this.bottomToBottom = -1;
            this.baselineToBaseline = -1;
            this.baselineToTop = -1;
            this.baselineToBottom = -1;
            this.circleConstraint = -1;
            this.circleRadius = 0;
            this.circleAngle = 0.0f;
            this.startToEnd = -1;
            this.startToStart = -1;
            this.endToStart = -1;
            this.endToEnd = -1;
            this.goneLeftMargin = Integer.MIN_VALUE;
            this.goneTopMargin = Integer.MIN_VALUE;
            this.goneRightMargin = Integer.MIN_VALUE;
            this.goneBottomMargin = Integer.MIN_VALUE;
            this.goneStartMargin = Integer.MIN_VALUE;
            this.goneEndMargin = Integer.MIN_VALUE;
            this.goneBaselineMargin = Integer.MIN_VALUE;
            this.baselineMargin = 0;
            this.mWidthSet = true;
            this.mHeightSet = true;
            this.horizontalBias = 0.5f;
            this.verticalBias = 0.5f;
            this.dimensionRatio = null;
            this.mDimensionRatioValue = 0.0f;
            this.mDimensionRatioSide = 1;
            this.horizontalWeight = -1.0f;
            this.verticalWeight = -1.0f;
            this.horizontalChainStyle = 0;
            this.verticalChainStyle = 0;
            this.matchConstraintDefaultWidth = 0;
            this.matchConstraintDefaultHeight = 0;
            this.matchConstraintMinWidth = 0;
            this.matchConstraintMinHeight = 0;
            this.matchConstraintMaxWidth = 0;
            this.matchConstraintMaxHeight = 0;
            this.matchConstraintPercentWidth = 1.0f;
            this.matchConstraintPercentHeight = 1.0f;
            this.editorAbsoluteX = -1;
            this.editorAbsoluteY = -1;
            this.orientation = -1;
            this.constrainedWidth = false;
            this.constrainedHeight = false;
            this.constraintTag = null;
            this.wrapBehaviorInParent = 0;
            this.mHorizontalDimensionFixed = true;
            this.mVerticalDimensionFixed = true;
            this.mNeedsBaseline = false;
            this.mIsGuideline = false;
            this.mIsHelper = false;
            this.mIsInPlaceholder = false;
            this.mIsVirtualGroup = false;
            this.mResolvedLeftToLeft = -1;
            this.mResolvedLeftToRight = -1;
            this.mResolvedRightToLeft = -1;
            this.mResolvedRightToRight = -1;
            this.mResolveGoneLeftMargin = Integer.MIN_VALUE;
            this.mResolveGoneRightMargin = Integer.MIN_VALUE;
            this.mResolvedHorizontalBias = 0.5f;
            this.mWidget = new ConstraintWidget();
            this.helped = false;
            if ((r9 instanceof ViewGroup.MarginLayoutParams) == false) goto L6;
            ViewGroup.MarginLayoutParams r0 = (ViewGroup.MarginLayoutParams) r9;
            ((ViewGroup.MarginLayoutParams) this).leftMargin = r0.leftMargin;
            ((ViewGroup.MarginLayoutParams) this).rightMargin = r0.rightMargin;
            ((ViewGroup.MarginLayoutParams) this).topMargin = r0.topMargin;
            ((ViewGroup.MarginLayoutParams) this).bottomMargin = r0.bottomMargin;
            setMarginStart(r0.getMarginStart());
            setMarginEnd(r0.getMarginEnd());
        L6:
            if ((r9 instanceof LayoutParams) == true) goto L8;
            return;
        L8:
            LayoutParams r92 = (LayoutParams) r9;
            this.guideBegin = r92.guideBegin;
            this.guideEnd = r92.guideEnd;
            this.guidePercent = r92.guidePercent;
            this.guidelineUseRtl = r92.guidelineUseRtl;
            this.leftToLeft = r92.leftToLeft;
            this.leftToRight = r92.leftToRight;
            this.rightToLeft = r92.rightToLeft;
            this.rightToRight = r92.rightToRight;
            this.topToTop = r92.topToTop;
            this.topToBottom = r92.topToBottom;
            this.bottomToTop = r92.bottomToTop;
            this.bottomToBottom = r92.bottomToBottom;
            this.baselineToBaseline = r92.baselineToBaseline;
            this.baselineToTop = r92.baselineToTop;
            this.baselineToBottom = r92.baselineToBottom;
            this.circleConstraint = r92.circleConstraint;
            this.circleRadius = r92.circleRadius;
            this.circleAngle = r92.circleAngle;
            this.startToEnd = r92.startToEnd;
            this.startToStart = r92.startToStart;
            this.endToStart = r92.endToStart;
            this.endToEnd = r92.endToEnd;
            this.goneLeftMargin = r92.goneLeftMargin;
            this.goneTopMargin = r92.goneTopMargin;
            this.goneRightMargin = r92.goneRightMargin;
            this.goneBottomMargin = r92.goneBottomMargin;
            this.goneStartMargin = r92.goneStartMargin;
            this.goneEndMargin = r92.goneEndMargin;
            this.goneBaselineMargin = r92.goneBaselineMargin;
            this.baselineMargin = r92.baselineMargin;
            this.horizontalBias = r92.horizontalBias;
            this.verticalBias = r92.verticalBias;
            this.dimensionRatio = r92.dimensionRatio;
            this.mDimensionRatioValue = r92.mDimensionRatioValue;
            this.mDimensionRatioSide = r92.mDimensionRatioSide;
            this.horizontalWeight = r92.horizontalWeight;
            this.verticalWeight = r92.verticalWeight;
            this.horizontalChainStyle = r92.horizontalChainStyle;
            this.verticalChainStyle = r92.verticalChainStyle;
            this.constrainedWidth = r92.constrainedWidth;
            this.constrainedHeight = r92.constrainedHeight;
            this.matchConstraintDefaultWidth = r92.matchConstraintDefaultWidth;
            this.matchConstraintDefaultHeight = r92.matchConstraintDefaultHeight;
            this.matchConstraintMinWidth = r92.matchConstraintMinWidth;
            this.matchConstraintMaxWidth = r92.matchConstraintMaxWidth;
            this.matchConstraintMinHeight = r92.matchConstraintMinHeight;
            this.matchConstraintMaxHeight = r92.matchConstraintMaxHeight;
            this.matchConstraintPercentWidth = r92.matchConstraintPercentWidth;
            this.matchConstraintPercentHeight = r92.matchConstraintPercentHeight;
            this.editorAbsoluteX = r92.editorAbsoluteX;
            this.editorAbsoluteY = r92.editorAbsoluteY;
            this.orientation = r92.orientation;
            this.mHorizontalDimensionFixed = r92.mHorizontalDimensionFixed;
            this.mVerticalDimensionFixed = r92.mVerticalDimensionFixed;
            this.mNeedsBaseline = r92.mNeedsBaseline;
            this.mIsGuideline = r92.mIsGuideline;
            this.mResolvedLeftToLeft = r92.mResolvedLeftToLeft;
            this.mResolvedLeftToRight = r92.mResolvedLeftToRight;
            this.mResolvedRightToLeft = r92.mResolvedRightToLeft;
            this.mResolvedRightToRight = r92.mResolvedRightToRight;
            this.mResolveGoneLeftMargin = r92.mResolveGoneLeftMargin;
            this.mResolveGoneRightMargin = r92.mResolveGoneRightMargin;
            this.mResolvedHorizontalBias = r92.mResolvedHorizontalBias;
            this.constraintTag = r92.constraintTag;
            this.wrapBehaviorInParent = r92.wrapBehaviorInParent;
            this.mWidget = r92.mWidget;
            this.mWidthSet = r92.mWidthSet;
            this.mHeightSet = r92.mHeightSet;
        }

        @Override // android.view.ViewGroup.MarginLayoutParams, android.view.ViewGroup.LayoutParams
        public void resolveLayoutDirection(int r12) {
            int r0 = ((ViewGroup.MarginLayoutParams) this).leftMargin;
            int r1 = ((ViewGroup.MarginLayoutParams) this).rightMargin;
            super.resolveLayoutDirection(r12);
            boolean r2 = false;
            if (1 != getLayoutDirection()) goto L5;
            boolean r122 = true;
        L6:
            this.mResolvedRightToLeft = -1;
            this.mResolvedRightToRight = -1;
            this.mResolvedLeftToLeft = -1;
            this.mResolvedLeftToRight = -1;
            this.mResolveGoneLeftMargin = this.goneLeftMargin;
            this.mResolveGoneRightMargin = this.goneRightMargin;
            float r5 = this.horizontalBias;
            this.mResolvedHorizontalBias = r5;
            int r6 = this.guideBegin;
            this.mResolvedGuideBegin = r6;
            int r7 = this.guideEnd;
            this.mResolvedGuideEnd = r7;
            float r8 = this.guidePercent;
            this.mResolvedGuidePercent = r8;
            int r9 = this.startToEnd;
            if (r122 == false) goto L42;
            if (r9 == (-1)) goto L11;
            this.mResolvedRightToLeft = r9;
        L10:
            r2 = true;
        L14:
            int r123 = this.endToStart;
            if (r123 == (-1)) goto L17;
            this.mResolvedLeftToRight = r123;
            r2 = true;
        L17:
            int r124 = this.endToEnd;
            if (r124 == (-1)) goto L20;
            this.mResolvedLeftToLeft = r124;
            r2 = true;
        L20:
            int r125 = this.goneStartMargin;
            if (r125 == Integer.MIN_VALUE) goto L23;
            this.mResolveGoneRightMargin = r125;
        L23:
            int r126 = this.goneEndMargin;
            if (r126 == Integer.MIN_VALUE) goto L27;
            this.mResolveGoneLeftMargin = r126;
        L27:
            if (r2 == false) goto L30;
            this.mResolvedHorizontalBias = 1.0f - r5;
        L30:
            if (this.mIsGuideline == false) goto L60;
            if (this.orientation != 1) goto L60;
            if (this.guidelineUseRtl == false) goto L60;
            if (r8 == (-1.0f)) goto L38;
            this.mResolvedGuidePercent = 1.0f - r8;
            this.mResolvedGuideBegin = -1;
            this.mResolvedGuideEnd = -1;
            goto L60
        L38:
            if (r6 == (-1)) goto L40;
            this.mResolvedGuideEnd = r6;
            this.mResolvedGuideBegin = -1;
            this.mResolvedGuidePercent = -1.0f;
            goto L60
        L40:
            if (r7 == (-1)) goto L60;
            this.mResolvedGuideBegin = r7;
            this.mResolvedGuideEnd = -1;
            this.mResolvedGuidePercent = -1.0f;
        L60:
            if (this.endToStart == (-1)) goto L62;
            return;
        L62:
            if (this.endToEnd == (-1)) goto L64;
            return;
        L64:
            if (this.startToStart == (-1)) goto L66;
            return;
        L66:
            if (this.startToEnd != (-1)) goto L96;
            int r127 = this.rightToLeft;
            if (r127 == (-1)) goto L73;
            this.mResolvedRightToLeft = r127;
            if (((ViewGroup.MarginLayoutParams) this).rightMargin > 0) goto L79;
            if (r1 <= 0) goto L79;
            ((ViewGroup.MarginLayoutParams) this).rightMargin = r1;
        L79:
            int r128 = this.leftToLeft;
            if (r128 == (-1)) goto L86;
            this.mResolvedLeftToLeft = r128;
            if (((ViewGroup.MarginLayoutParams) this).leftMargin > 0) goto L97;
            if (r0 <= 0) goto L98;
            ((ViewGroup.MarginLayoutParams) this).leftMargin = r0;
            return;
        L98:
            return;
        L97:
            return;
        L86:
            int r129 = this.leftToRight;
            if (r129 == (-1)) goto L99;
            this.mResolvedLeftToRight = r129;
            if (((ViewGroup.MarginLayoutParams) this).leftMargin > 0) goto L100;
            if (r0 <= 0) goto L101;
            ((ViewGroup.MarginLayoutParams) this).leftMargin = r0;
            return;
        L101:
            return;
        L100:
            return;
        L99:
            return;
        L73:
            int r1210 = this.rightToRight;
            if (r1210 == (-1)) goto L79;
            this.mResolvedRightToRight = r1210;
            if (((ViewGroup.MarginLayoutParams) this).rightMargin > 0) goto L79;
            if (r1 <= 0) goto L79;
            ((ViewGroup.MarginLayoutParams) this).rightMargin = r1;
            goto L79
        L96:
            return;
        L11:
            int r1211 = this.startToStart;
            if (r1211 == (-1)) goto L14;
            this.mResolvedRightToRight = r1211;
            goto L10
        L42:
            if (r9 == (-1)) goto L44;
            this.mResolvedLeftToRight = r9;
        L44:
            int r1212 = this.startToStart;
            if (r1212 == (-1)) goto L47;
            this.mResolvedLeftToLeft = r1212;
        L47:
            int r1213 = this.endToStart;
            if (r1213 == (-1)) goto L50;
            this.mResolvedRightToLeft = r1213;
        L50:
            int r1214 = this.endToEnd;
            if (r1214 == (-1)) goto L53;
            this.mResolvedRightToRight = r1214;
        L53:
            int r1215 = this.goneStartMargin;
            if (r1215 == Integer.MIN_VALUE) goto L56;
            this.mResolveGoneLeftMargin = r1215;
        L56:
            int r1216 = this.goneEndMargin;
            if (r1216 == Integer.MIN_VALUE) goto L60;
            this.mResolveGoneRightMargin = r1216;
            goto L60
        L5:
            r122 = false;
            goto L6
        }

        public void validate() {
            this.mIsGuideline = false;
            this.mHorizontalDimensionFixed = true;
            this.mVerticalDimensionFixed = true;
            int r2 = ((ViewGroup.MarginLayoutParams) this).width;
            if (r2 == (-2)) goto L5;
        L9:
            int r4 = ((ViewGroup.MarginLayoutParams) this).height;
            if (r4 != (-2)) goto L17;
            if (this.constrainedHeight == false) goto L17;
            this.mVerticalDimensionFixed = false;
            if (this.matchConstraintDefaultHeight != 0) goto L17;
            this.matchConstraintDefaultHeight = 1;
        L17:
            if (r2 == 0) goto L19;
            if (r2 == (-1)) goto L19;
        L24:
            if (r4 == 0) goto L26;
            if (r4 == (-1)) goto L26;
        L32:
            if (this.guidePercent == (-1.0f)) goto L34;
        L39:
            this.mIsGuideline = true;
            this.mHorizontalDimensionFixed = true;
            this.mVerticalDimensionFixed = true;
            if ((this.mWidget instanceof androidx.constraintlayout.core.widgets.Guideline) == true) goto L42;
            this.mWidget = new androidx.constraintlayout.core.widgets.Guideline();
        L42:
            ((androidx.constraintlayout.core.widgets.Guideline) this.mWidget).setOrientation(this.orientation);
            return;
        L34:
            if (this.guideBegin != (-1)) goto L39;
            if (this.guideEnd != (-1)) goto L39;
            return;
        L26:
            this.mVerticalDimensionFixed = false;
            if (r4 != 0) goto L32;
            if (this.matchConstraintDefaultHeight != 1) goto L32;
            ((ViewGroup.MarginLayoutParams) this).height = -2;
            this.constrainedHeight = true;
        L19:
            this.mHorizontalDimensionFixed = false;
            if (r2 != 0) goto L24;
            if (this.matchConstraintDefaultWidth != 1) goto L24;
            ((ViewGroup.MarginLayoutParams) this).width = -2;
            this.constrainedWidth = true;
            goto L24
        L5:
            if (this.constrainedWidth == false) goto L9;
            this.mHorizontalDimensionFixed = false;
            if (this.matchConstraintDefaultWidth != 0) goto L9;
            this.matchConstraintDefaultWidth = 1;
            goto L9
        }
    }

    public class Measurer implements BasicMeasure.Measurer {
        ConstraintLayout mLayout;
        int mLayoutHeightSpec;
        int mLayoutWidthSpec;
        int mPaddingBottom;
        int mPaddingHeight;
        int mPaddingTop;
        int mPaddingWidth;
        final /* synthetic */ ConstraintLayout this$0;

        public Measurer(ConstraintLayout r1, ConstraintLayout r2) {
            this.this$0 = r1;
            this.mLayout = r2;
        }

        private boolean isSimilarSpec(int r3, int r4, int r5) {
            if (r3 != r4) goto L5;
            return true;
        L5:
            int r32 = View.MeasureSpec.getMode(r3);
            int r0 = View.MeasureSpec.getMode(r4);
            int r42 = View.MeasureSpec.getSize(r4);
            if (r0 == 1073741824) goto L8;
            return false;
        L8:
            if (r32 == Integer.MIN_VALUE) goto L10;
            if (r32 == 0) goto L10;
            return false;
        L10:
            if (r5 != r42) goto L15;
            return true;
        L15:
            return false;
        }

        public void captureLayoutInfo(int r1, int r2, int r3, int r4, int r5, int r6) {
            this.mPaddingTop = r3;
            this.mPaddingBottom = r4;
            this.mPaddingWidth = r5;
            this.mPaddingHeight = r6;
            this.mLayoutWidthSpec = r1;
            this.mLayoutHeightSpec = r2;
        }

        @Override // androidx.constraintlayout.core.widgets.analyzer.BasicMeasure.Measurer
        public final void didMeasures() {
            int r0 = this.mLayout.getChildCount();
            int r1 = 0;
            int r2 = 0;
        L3:
            ConstraintLayout r3 = this.mLayout;
            if (r2 >= r0) goto L6;
            r3.getChildAt(r2);
            r2 = r2 + 1;
            goto L3
        L6:
            int r02 = ConstraintLayout.access$200(r3).size();
            if (r02 <= 0) goto L10;
        L8:
            if (r1 >= r02) goto L13;
            ((ConstraintHelper) ConstraintLayout.access$200(this.mLayout).get(r1)).updatePostMeasure(this.mLayout);
            r1 = r1 + 1;
            goto L8
        L13:
            return;
        }

        @Override // androidx.constraintlayout.core.widgets.analyzer.BasicMeasure.Measurer
        @SuppressLint({"WrongCall"})
        public final void measure(ConstraintWidget r19, BasicMeasure.Measure r20) {
            if (r19 != null) goto L6;
            return;
        L6:
            if (r19.getVisibility() != 8) goto L12;
            if (r19.isInPlaceholder() == true) goto L12;
            r20.measuredWidth = 0;
            r20.measuredHeight = 0;
            r20.measuredBaseline = 0;
            return;
        L12:
            if (r19.getParent() == null) goto L203;
            ConstraintLayout.access$000(this.this$0);
            ConstraintWidget.DimensionBehaviour r3 = r20.horizontalBehavior;
            ConstraintWidget.DimensionBehaviour r4 = r20.verticalBehavior;
            int r6 = r20.horizontalDimension;
            int r7 = r20.verticalDimension;
            int r8 = this.mPaddingTop + this.mPaddingBottom;
            int r9 = this.mPaddingWidth;
            View r10 = (View) r19.getCompanionWidget();
            int[] r11 = AnonymousClass1.$SwitchMap$androidx$constraintlayout$core$widgets$ConstraintWidget$DimensionBehaviour;
            int r12 = r11[r3.ordinal()];
            if (r12 == 1) goto L42;
            if (r12 == 2) goto L41;
            if (r12 == 3) goto L40;
            if (r12 == 4) goto L20;
            int r62 = 0;
        L43:
            int r92 = r11[r4.ordinal()];
            if (r92 == 1) goto L73;
            if (r92 != 2) goto L47;
            int r72 = ViewGroup.getChildMeasureSpec(this.mLayoutHeightSpec, r8, -2);
        L74:
            ConstraintWidgetContainer r82 = (ConstraintWidgetContainer) r19.getParent();
            if (r82 != null) goto L77;
        L96:
            ConstraintWidget.DimensionBehaviour r83 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
            if (r3 != r83) goto L99;
            boolean r93 = true;
        L100:
            if (r4 != r83) goto L102;
            boolean r84 = true;
        L103:
            ConstraintWidget.DimensionBehaviour r112 = ConstraintWidget.DimensionBehaviour.MATCH_PARENT;
            if (r4 != r112) goto L106;
        L109:
            boolean r42 = true;
        L110:
            if (r3 != r112) goto L112;
        L115:
            boolean r32 = true;
        L117:
            if (r93 == true) goto L119;
        L121:
            boolean r122 = false;
        L122:
            if (r84 == true) goto L124;
        L126:
            boolean r113 = false;
        L127:
            if (r10 != null) goto L129;
            return;
        L129:
            LayoutParams r13 = (LayoutParams) r10.getLayoutParams();
            int r14 = r20.measureStrategy;
            if (r14 == BasicMeasure.Measure.TRY_GIVEN_DIMENSIONS) goto L142;
            if (r14 == BasicMeasure.Measure.USE_GIVEN_DIMENSIONS) goto L142;
            if (r93 == false) goto L142;
            if (r19.mMatchConstraintDefaultWidth != 0) goto L142;
            if (r84 == false) goto L142;
            if (r19.mMatchConstraintDefaultHeight != 0) goto L142;
            int r33 = -1;
            int r5 = 0;
            int r142 = 0;
            int r15 = 0;
        L181:
            if (r142 == r33) goto L183;
            boolean r43 = true;
        L185:
            if (r15 == r20.horizontalDimension) goto L187;
        L190:
            boolean r34 = true;
        L191:
            r20.measuredNeedsSolverPass = r34;
            if (r13.mNeedsBaseline == false) goto L194;
            r43 = true;
        L194:
            if (r43 == true) goto L196;
        L200:
            r20.measuredWidth = r15;
            r20.measuredHeight = r5;
            r20.measuredHasBaseline = r43;
            r20.measuredBaseline = r142;
            ConstraintLayout.access$000(this.this$0);
            return;
        L196:
            if (r142 == (-1)) goto L200;
            if (r19.getBaselineDistance() == r142) goto L200;
            r20.measuredNeedsSolverPass = true;
            goto L200
        L187:
            if (r5 != r20.verticalDimension) goto L190;
            r34 = false;
            goto L191
        L183:
            r43 = false;
        L142:
            if ((r10 instanceof VirtualLayout) == true) goto L144;
        L146:
            r10.measure(r62, r72);
        L147:
            r19.setLastMeasureSpec(r62, r72);
            int r85 = r10.getMeasuredWidth();
            int r94 = r10.getMeasuredHeight();
            r142 = r10.getBaseline();
            int r152 = r19.mMatchConstraintMinWidth;
            if (r152 <= 0) goto L150;
            r15 = Math.max(r152, r85);
        L151:
            int r52 = r19.mMatchConstraintMaxWidth;
            if (r52 <= 0) goto L154;
            r15 = Math.min(r52, r15);
        L154:
            int r53 = r19.mMatchConstraintMinHeight;
            if (r53 <= 0) goto L158;
            r5 = Math.max(r53, r94);
        L157:
            boolean r16 = r32;
            int r35 = r19.mMatchConstraintMaxHeight;
            if (r35 <= 0) goto L162;
            r5 = Math.min(r35, r5);
        L162:
            boolean r17 = r42;
            if (Optimizer.enabled(ConstraintLayout.access$100(this.this$0), 1) == false) goto L165;
        L171:
            if (r85 != r15) goto L176;
            if (r94 != r5) goto L176;
        L174:
            r33 = -1;
        L176:
            if (r85 == r15) goto L178;
            r62 = View.MeasureSpec.makeMeasureSpec(r15, TLObject.FLAG_30);
        L178:
            if (r94 == r5) goto L180;
            r72 = View.MeasureSpec.makeMeasureSpec(r5, TLObject.FLAG_30);
        L180:
            r10.measure(r62, r72);
            r19.setLastMeasureSpec(r62, r72);
            r15 = r10.getMeasuredWidth();
            r5 = r10.getMeasuredHeight();
            r142 = r10.getBaseline();
            goto L174
        L165:
            if (r122 == false) goto L168;
            if (r17 == false) goto L168;
            r15 = (int) ((r5 * r19.mDimensionRatio) + 0.5f);
        L168:
            if (r113 == false) goto L171;
            if (r16 == false) goto L171;
            r5 = (int) ((r15 / r19.mDimensionRatio) + 0.5f);
            goto L171
        L158:
            r5 = r94;
            goto L157
        L150:
            r15 = r85;
            goto L151
        L144:
            if ((r19 instanceof androidx.constraintlayout.core.widgets.VirtualLayout) == false) goto L146;
            ((VirtualLayout) r10).onMeasure((androidx.constraintlayout.core.widgets.VirtualLayout) r19, r62, r72);
            goto L147
        L124:
            if (r19.mDimensionRatio <= 0.0f) goto L126;
            r113 = true;
            goto L127
        L119:
            if (r19.mDimensionRatio <= 0.0f) goto L121;
            r122 = true;
            goto L122
        L112:
            if (r3 == ConstraintWidget.DimensionBehaviour.FIXED) goto L115;
            r32 = false;
            goto L117
        L106:
            if (r4 == ConstraintWidget.DimensionBehaviour.FIXED) goto L109;
            r42 = false;
            goto L110
        L102:
            r84 = false;
            goto L103
        L99:
            r93 = false;
            goto L100
        L77:
            if (Optimizer.enabled(ConstraintLayout.access$100(this.this$0), 256) == false) goto L96;
            if (r10.getMeasuredWidth() != r19.getWidth()) goto L96;
            if (r10.getMeasuredWidth() >= r82.getWidth()) goto L96;
            if (r10.getMeasuredHeight() != r19.getHeight()) goto L96;
            if (r10.getMeasuredHeight() >= r82.getHeight()) goto L96;
            if (r10.getBaseline() != r19.getBaselineDistance()) goto L96;
            if (r19.isMeasureRequested() == true) goto L96;
            if (isSimilarSpec(r19.getLastHorizontalMeasureSpec(), r62, r19.getWidth()) == false) goto L96;
            if (isSimilarSpec(r19.getLastVerticalMeasureSpec(), r72, r19.getHeight()) == false) goto L96;
            r20.measuredWidth = r19.getWidth();
            r20.measuredHeight = r19.getHeight();
            r20.measuredBaseline = r19.getBaselineDistance();
            return;
        L47:
            if (r92 != 3) goto L49;
            r72 = ViewGroup.getChildMeasureSpec(this.mLayoutHeightSpec, r8 + r19.getVerticalMargin(), -1);
            goto L74
        L49:
            if (r92 == 4) goto L51;
            r72 = 0;
            goto L74
        L51:
            r72 = ViewGroup.getChildMeasureSpec(this.mLayoutHeightSpec, r8, -2);
            if (r19.mMatchConstraintDefaultHeight != 1) goto L54;
            boolean r86 = true;
        L55:
            int r95 = r20.measureStrategy;
            if (r95 == BasicMeasure.Measure.TRY_GIVEN_DIMENSIONS) goto L60;
            if (r95 != BasicMeasure.Measure.USE_GIVEN_DIMENSIONS) goto L74;
        L60:
            if (r10.getMeasuredWidth() != r19.getWidth()) goto L62;
            boolean r96 = true;
        L64:
            if (r20.measureStrategy == BasicMeasure.Measure.USE_GIVEN_DIMENSIONS) goto L70;
            if (r86 == false) goto L70;
            if (r86 == false) goto L69;
            if (r96 == true) goto L70;
        L69:
            if (r19.isResolvedVertically() == false) goto L74;
        L70:
            r72 = View.MeasureSpec.makeMeasureSpec(r19.getHeight(), TLObject.FLAG_30);
            goto L74
        L62:
            r96 = false;
            goto L64
        L54:
            r86 = false;
            goto L55
        L73:
            r72 = View.MeasureSpec.makeMeasureSpec(r7, TLObject.FLAG_30);
            goto L74
        L20:
            r62 = ViewGroup.getChildMeasureSpec(this.mLayoutWidthSpec, r9, -2);
            if (r19.mMatchConstraintDefaultWidth != 1) goto L23;
            boolean r97 = true;
        L24:
            int r123 = r20.measureStrategy;
            if (r123 == BasicMeasure.Measure.TRY_GIVEN_DIMENSIONS) goto L29;
            if (r123 != BasicMeasure.Measure.USE_GIVEN_DIMENSIONS) goto L43;
        L29:
            if (r10.getMeasuredHeight() != r19.getHeight()) goto L31;
            boolean r124 = true;
        L33:
            if (r20.measureStrategy == BasicMeasure.Measure.USE_GIVEN_DIMENSIONS) goto L39;
            if (r97 == false) goto L39;
            if (r97 == false) goto L38;
            if (r124 == true) goto L39;
        L38:
            if (r19.isResolvedHorizontally() == false) goto L43;
        L39:
            r62 = View.MeasureSpec.makeMeasureSpec(r19.getWidth(), TLObject.FLAG_30);
            goto L43
        L31:
            r124 = false;
            goto L33
        L23:
            r97 = false;
            goto L24
        L40:
            r62 = ViewGroup.getChildMeasureSpec(this.mLayoutWidthSpec, r9 + r19.getHorizontalMargin(), -1);
            goto L43
        L41:
            r62 = ViewGroup.getChildMeasureSpec(this.mLayoutWidthSpec, r9, -2);
            goto L43
        L42:
            r62 = View.MeasureSpec.makeMeasureSpec(r6, TLObject.FLAG_30);
            goto L43
        }
    }

    public interface ValueModifier {
    }

    static {
    }

    public ConstraintLayout(Context r4) {
        super(r4);
        this.mChildrenByIds = new SparseArray();
        this.mConstraintHelpers = new ArrayList(4);
        this.mLayoutWidget = new ConstraintWidgetContainer();
        this.mMinWidth = 0;
        this.mMinHeight = 0;
        this.mMaxWidth = Integer.MAX_VALUE;
        this.mMaxHeight = Integer.MAX_VALUE;
        this.mDirtyHierarchy = true;
        this.mOptimizationLevel = GlobalMediaRouter.CallbackHandler.MSG_ROUTE_ADDED;
        this.mConstraintSet = null;
        this.mConstraintLayoutSpec = null;
        this.mConstraintSetId = -1;
        this.mDesignIds = new HashMap();
        this.mLastMeasureWidth = -1;
        this.mLastMeasureHeight = -1;
        this.mLastMeasureWidthSize = -1;
        this.mLastMeasureHeightSize = -1;
        this.mLastMeasureWidthMode = 0;
        this.mLastMeasureHeightMode = 0;
        this.mTempMapIdToWidget = new SparseArray();
        this.mMeasurer = new Measurer(this, this);
        this.mOnMeasureWidthMeasureSpec = 0;
        this.mOnMeasureHeightMeasureSpec = 0;
        init(null, 0, 0);
    }

    public ConstraintLayout(Context r3, AttributeSet r4) {
        super(r3, r4);
        this.mChildrenByIds = new SparseArray();
        this.mConstraintHelpers = new ArrayList(4);
        this.mLayoutWidget = new ConstraintWidgetContainer();
        this.mMinWidth = 0;
        this.mMinHeight = 0;
        this.mMaxWidth = Integer.MAX_VALUE;
        this.mMaxHeight = Integer.MAX_VALUE;
        this.mDirtyHierarchy = true;
        this.mOptimizationLevel = GlobalMediaRouter.CallbackHandler.MSG_ROUTE_ADDED;
        this.mConstraintSet = null;
        this.mConstraintLayoutSpec = null;
        this.mConstraintSetId = -1;
        this.mDesignIds = new HashMap();
        this.mLastMeasureWidth = -1;
        this.mLastMeasureHeight = -1;
        this.mLastMeasureWidthSize = -1;
        this.mLastMeasureHeightSize = -1;
        this.mLastMeasureWidthMode = 0;
        this.mLastMeasureHeightMode = 0;
        this.mTempMapIdToWidget = new SparseArray();
        this.mMeasurer = new Measurer(this, this);
        this.mOnMeasureWidthMeasureSpec = 0;
        this.mOnMeasureHeightMeasureSpec = 0;
        init(r4, 0, 0);
    }

    public ConstraintLayout(Context r3, AttributeSet r4, int r5) {
        super(r3, r4, r5);
        this.mChildrenByIds = new SparseArray();
        this.mConstraintHelpers = new ArrayList(4);
        this.mLayoutWidget = new ConstraintWidgetContainer();
        this.mMinWidth = 0;
        this.mMinHeight = 0;
        this.mMaxWidth = Integer.MAX_VALUE;
        this.mMaxHeight = Integer.MAX_VALUE;
        this.mDirtyHierarchy = true;
        this.mOptimizationLevel = GlobalMediaRouter.CallbackHandler.MSG_ROUTE_ADDED;
        this.mConstraintSet = null;
        this.mConstraintLayoutSpec = null;
        this.mConstraintSetId = -1;
        this.mDesignIds = new HashMap();
        this.mLastMeasureWidth = -1;
        this.mLastMeasureHeight = -1;
        this.mLastMeasureWidthSize = -1;
        this.mLastMeasureHeightSize = -1;
        this.mLastMeasureWidthMode = 0;
        this.mLastMeasureHeightMode = 0;
        this.mTempMapIdToWidget = new SparseArray();
        this.mMeasurer = new Measurer(this, this);
        this.mOnMeasureWidthMeasureSpec = 0;
        this.mOnMeasureHeightMeasureSpec = 0;
        init(r4, r5, 0);
    }

    @TargetApi(21)
    public ConstraintLayout(Context r3, AttributeSet r4, int r5, int r6) {
        super(r3, r4, r5, r6);
        this.mChildrenByIds = new SparseArray();
        this.mConstraintHelpers = new ArrayList(4);
        this.mLayoutWidget = new ConstraintWidgetContainer();
        this.mMinWidth = 0;
        this.mMinHeight = 0;
        this.mMaxWidth = Integer.MAX_VALUE;
        this.mMaxHeight = Integer.MAX_VALUE;
        this.mDirtyHierarchy = true;
        this.mOptimizationLevel = GlobalMediaRouter.CallbackHandler.MSG_ROUTE_ADDED;
        this.mConstraintSet = null;
        this.mConstraintLayoutSpec = null;
        this.mConstraintSetId = -1;
        this.mDesignIds = new HashMap();
        this.mLastMeasureWidth = -1;
        this.mLastMeasureHeight = -1;
        this.mLastMeasureWidthSize = -1;
        this.mLastMeasureHeightSize = -1;
        this.mLastMeasureWidthMode = 0;
        this.mLastMeasureHeightMode = 0;
        this.mTempMapIdToWidget = new SparseArray();
        this.mMeasurer = new Measurer(this, this);
        this.mOnMeasureWidthMeasureSpec = 0;
        this.mOnMeasureHeightMeasureSpec = 0;
        init(r4, r5, r6);
    }

    public static /* synthetic */ Metrics access$000(ConstraintLayout r0) {
        r0.getClass();
        return null;
    }

    public static /* synthetic */ int access$100(ConstraintLayout r0) {
        return r0.mOptimizationLevel;
    }

    public static /* synthetic */ ArrayList access$200(ConstraintLayout r0) {
        return r0.mConstraintHelpers;
    }

    private int getPaddingWidth() {
        int r0 = Math.max(0, getPaddingLeft()) + Math.max(0, getPaddingRight());
        int r2 = Math.max(0, getPaddingStart()) + Math.max(0, getPaddingEnd());
        if (r2 <= 0) goto L5;
        return r2;
    L5:
        return r0;
    }

    public static SharedValues getSharedValues() {
        if (sSharedValues != null) goto L6;
        sSharedValues = new SharedValues();
    L6:
        return sSharedValues;
    }

    private ConstraintWidget getTargetWidget(int r2) {
        if (r2 == 0) goto L4;
        View r0 = this.mChildrenByIds.get(r2);
        if (r0 != null) goto L13;
        r0 = findViewById(r2);
        if (r0 == null) goto L13;
        if (r0 == this) goto L13;
        if (r0.getParent() != this) goto L13;
        onViewAdded(r0);
    L13:
        if (r0 == this) goto L15;
        if (r0 != null) goto L20;
        return null;
    L20:
        return ((LayoutParams) r0.getLayoutParams()).mWidget;
    L15:
        return this.mLayoutWidget;
    L4:
        return this.mLayoutWidget;
    }

    private void init(AttributeSet r6, int r7, int r8) {
        this.mLayoutWidget.setCompanionWidget(this);
        this.mLayoutWidget.setMeasurer(this.mMeasurer);
        this.mChildrenByIds.put(getId(), this);
        this.mConstraintSet = null;
        if (r6 == null) goto L37;
        TypedArray r62 = getContext().obtainStyledAttributes(r6, R$styleable.ConstraintLayout_Layout, r7, r8);
        int r72 = r62.getIndexCount();
        int r1 = 0;
    L5:
        if (r1 >= r72) goto L36;
        int r2 = r62.getIndex(r1);
        if (r2 != R$styleable.ConstraintLayout_Layout_android_minWidth) goto L10;
        this.mMinWidth = r62.getDimensionPixelOffset(r2, this.mMinWidth);
    L35:
        r1 = r1 + 1;
        goto L5
    L10:
        if (r2 != R$styleable.ConstraintLayout_Layout_android_minHeight) goto L13;
        this.mMinHeight = r62.getDimensionPixelOffset(r2, this.mMinHeight);
        goto L35
    L13:
        if (r2 != R$styleable.ConstraintLayout_Layout_android_maxWidth) goto L16;
        this.mMaxWidth = r62.getDimensionPixelOffset(r2, this.mMaxWidth);
        goto L35
    L16:
        if (r2 != R$styleable.ConstraintLayout_Layout_android_maxHeight) goto L19;
        this.mMaxHeight = r62.getDimensionPixelOffset(r2, this.mMaxHeight);
        goto L35
    L19:
        if (r2 != R$styleable.ConstraintLayout_Layout_layout_optimizationLevel) goto L22;
        this.mOptimizationLevel = r62.getInt(r2, this.mOptimizationLevel);
        goto L35
    L22:
        if (r2 != R$styleable.ConstraintLayout_Layout_layoutDescription) goto L29;
        int r22 = r62.getResourceId(r2, 0);
        if (r22 == 0) goto L35;
        parseLayoutDescription(r22);     // Catch: Resources.NotFoundException -> L27
    L27:
        this.mConstraintLayoutSpec = null;
        goto L35
    L29:
        if (r2 != R$styleable.ConstraintLayout_Layout_constraintSet) goto L35;
        int r23 = r62.getResourceId(r2, 0);
        ConstraintSet r3 = new ConstraintSet();     // Catch: Resources.NotFoundException -> L33
        this.mConstraintSet = r3;     // Catch: Resources.NotFoundException -> L33
        r3.load(getContext(), r23);     // Catch: Resources.NotFoundException -> L33
    L34:
        this.mConstraintSetId = r23;
    L33:
        this.mConstraintSet = null;
        goto L34
    L36:
        r62.recycle();
    L37:
        this.mLayoutWidget.setOptimizationLevel(this.mOptimizationLevel);
    }

    private void markHierarchyDirty() {
        this.mDirtyHierarchy = true;
        this.mLastMeasureWidth = -1;
        this.mLastMeasureHeight = -1;
        this.mLastMeasureWidthSize = -1;
        this.mLastMeasureHeightSize = -1;
        this.mLastMeasureWidthMode = 0;
        this.mLastMeasureHeightMode = 0;
    }

    private void setChildrenConstraints() {
        boolean r1 = isInEditMode();
        int r6 = getChildCount();
        int r2 = 0;
    L3:
        if (r2 >= r6) goto L10;
        ConstraintWidget r3 = getViewWidget(getChildAt(r2));
        if (r3 == null) goto L8;
        r3.reset();
    L8:
        r2 = r2 + 1;
        goto L3
    L10:
        if (r1 == false) goto L20;
        int r32 = 0;
    L12:
        if (r32 >= r6) goto L20;
        View r4 = getChildAt(r32);
        String r5 = getResources().getResourceName(r4.getId());     // Catch: Resources.NotFoundException -> L46
        setDesignInformation(0, r5, Integer.valueOf(r4.getId()));     // Catch: Resources.NotFoundException -> L46
        int r7 = r5.indexOf(47);     // Catch: Resources.NotFoundException -> L46
        if (r7 == (-1)) goto L17;
        r5 = r5.substring(r7 + 1);     // Catch: Resources.NotFoundException -> L46
    L17:
        getTargetWidget(r4.getId()).setDebugName(r5);     // Catch: Resources.NotFoundException -> L46
    L18:
        r32 = r32 + 1;
    L20:
        if (this.mConstraintSetId == (-1)) goto L24;
        int r22 = 0;
    L22:
        if (r22 >= r6) goto L24;
        getChildAt(r22).getId();
        r22 = r22 + 1;
    L24:
        ConstraintSet r23 = this.mConstraintSet;
        if (r23 == null) goto L27;
        r23.applyToInternal(this, true);
    L27:
        this.mLayoutWidget.removeAllChildren();
        int r24 = this.mConstraintHelpers.size();
        if (r24 <= 0) goto L32;
        int r33 = 0;
    L30:
        if (r33 >= r24) goto L32;
        this.mConstraintHelpers.get(r33).updatePreLayout(this);
        r33 = r33 + 1;
    L32:
        int r25 = 0;
    L33:
        if (r25 >= r6) goto L35;
        getChildAt(r25);
        r25 = r25 + 1;
        goto L33
    L35:
        this.mTempMapIdToWidget.clear();
        this.mTempMapIdToWidget.put(0, this.mLayoutWidget);
        this.mTempMapIdToWidget.put(getId(), this.mLayoutWidget);
        int r26 = 0;
    L36:
        if (r26 >= r6) goto L38;
        View r34 = getChildAt(r26);
        this.mTempMapIdToWidget.put(r34.getId(), getViewWidget(r34));
        r26 = r26 + 1;
        goto L36
    L38:
        int r72 = 0;
    L39:
        if (r72 >= r6) goto L45;
        View r27 = this.getChildAt(r72);
        ConstraintWidget r35 = this.getViewWidget(r27);
        if (r35 != null) goto L43;
        ConstraintLayout r0 = this;
    L44:
        r72 = r72 + 1;
        ConstraintLayout this = r0;
        goto L39
    L43:
        LayoutParams r42 = (LayoutParams) r27.getLayoutParams();
        this.mLayoutWidget.add(r35);
        r0 = this;
        r0.applyConstraintsFromLayoutParams(r1, r27, r35, r42, this.mTempMapIdToWidget);
        goto L44
    }

    private void setWidgetBaseline(ConstraintWidget r2, LayoutParams r3, SparseArray<ConstraintWidget> r4, int r5, ConstraintAnchor.Type r6) {
        View r1 = this.mChildrenByIds.get(r5);
        ConstraintWidget r42 = r4.get(r5);
        if (r42 == null) goto L12;
        if (r1 != null) goto L6;
        return;
    L6:
        if ((r1.getLayoutParams() instanceof LayoutParams) == false) goto L14;
        r3.mNeedsBaseline = true;
        ConstraintAnchor.Type r0 = ConstraintAnchor.Type.BASELINE;
        if (r6 != r0) goto L10;
        LayoutParams r12 = (LayoutParams) r1.getLayoutParams();
        r12.mNeedsBaseline = true;
        r12.mWidget.setHasBaseline(true);
    L10:
        r2.getAnchor(r0).connect(r42.getAnchor(r6), r3.baselineMargin, r3.goneBaselineMargin, true);
        r2.setHasBaseline(true);
        r2.getAnchor(ConstraintAnchor.Type.TOP).reset();
        r2.getAnchor(ConstraintAnchor.Type.BOTTOM).reset();
        return;
    L14:
        return;
    }

    private boolean updateHierarchy() {
        int r0 = getChildCount();
        boolean r1 = false;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L8;
        if (getChildAt(r2).isLayoutRequested() == true) goto L6;
        r2 = r2 + 1;
        goto L3
    L6:
        r1 = true;
    L8:
        if (r1 == false) goto L10;
        setChildrenConstraints();
    L10:
        return r1;
    }

    public void addValueModifier(ValueModifier r2) {
        if (this.mModifiers != null) goto L5;
        this.mModifiers = new ArrayList();
    L5:
        this.mModifiers.add(r2);
    }

    public void applyConstraintsFromLayoutParams(boolean r15, View r16, ConstraintWidget r17, LayoutParams r18, SparseArray<ConstraintWidget> r19) {
        r18.validate();
        r18.helped = false;
        r17.setVisibility(r16.getVisibility());
        if (r18.mIsInPlaceholder == false) goto L5;
        r17.setInPlaceholder(true);
        r17.setVisibility(8);
    L5:
        r17.setCompanionWidget(r16);
        if ((r16 instanceof ConstraintHelper) == false) goto L9;
        ((ConstraintHelper) r16).resolveRtl(r17, this.mLayoutWidget.isRtl());
    L9:
        if (r18.mIsGuideline == false) goto L20;
        androidx.constraintlayout.core.widgets.Guideline r14 = (androidx.constraintlayout.core.widgets.Guideline) r17;
        int r152 = r18.mResolvedGuideBegin;
        int r0 = r18.mResolvedGuideEnd;
        float r1 = r18.mResolvedGuidePercent;
        if (r1 == (-1.0f)) goto L14;
        r14.setGuidePercent(r1);
        return;
    L14:
        if (r152 == (-1)) goto L17;
        r14.setGuideBegin(r152);
        return;
    L17:
        if (r0 == (-1)) goto L113;
        r14.setGuideEnd(r0);
        return;
    L113:
        return;
    L20:
        int r02 = r18.mResolvedLeftToLeft;
        int r2 = r18.mResolvedLeftToRight;
        int r10 = r18.mResolvedRightToLeft;
        int r11 = r18.mResolvedRightToRight;
        int r5 = r18.mResolveGoneLeftMargin;
        int r12 = r18.mResolveGoneRightMargin;
        float r13 = r18.mResolvedHorizontalBias;
        int r3 = r18.circleConstraint;
        if (r3 == (-1)) goto L26;
        ConstraintWidget r142 = r19.get(r3);
        if (r142 == null) goto L25;
        r17.connectCircularConstraint(r142, r18.circleAngle, r18.circleRadius);
    L25:
        ConstraintWidget r03 = r17;
        LayoutParams r22 = r18;
    L79:
        if (r15 == false) goto L86;
        int r143 = r22.editorAbsoluteX;
        if (r143 == (-1)) goto L83;
    L84:
        r03.setOrigin(r143, r22.editorAbsoluteY);
        goto L86
    L83:
        if (r22.editorAbsoluteY != (-1)) goto L84;
    L86:
        if (r22.mHorizontalDimensionFixed == false) goto L88;
        r03.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
        r03.setWidth(((ViewGroup.MarginLayoutParams) r22).width);
        if (((ViewGroup.MarginLayoutParams) r22).width != (-2)) goto L99;
        r03.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.WRAP_CONTENT);
    L99:
        if (r22.mVerticalDimensionFixed == false) goto L101;
        r03.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
        r03.setHeight(((ViewGroup.MarginLayoutParams) r22).height);
        if (((ViewGroup.MarginLayoutParams) r22).height != (-2)) goto L111;
        r03.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.WRAP_CONTENT);
    L111:
        r03.setDimensionRatio(r22.dimensionRatio);
        r03.setHorizontalWeight(r22.horizontalWeight);
        r03.setVerticalWeight(r22.verticalWeight);
        r03.setHorizontalChainStyle(r22.horizontalChainStyle);
        r03.setVerticalChainStyle(r22.verticalChainStyle);
        r03.setWrapBehaviorInParent(r22.wrapBehaviorInParent);
        r03.setHorizontalMatchStyle(r22.matchConstraintDefaultWidth, r22.matchConstraintMinWidth, r22.matchConstraintMaxWidth, r22.matchConstraintPercentWidth);
        r03.setVerticalMatchStyle(r22.matchConstraintDefaultHeight, r22.matchConstraintMinHeight, r22.matchConstraintMaxHeight, r22.matchConstraintPercentHeight);
        return;
    L101:
        if (((ViewGroup.MarginLayoutParams) r22).height == (-1)) goto L103;
        r03.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT);
        r03.setHeight(0);
        goto L111
    L103:
        if (r22.constrainedHeight == false) goto L105;
        r03.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT);
    L106:
        ConstraintAnchor r144 = r03.getAnchor(ConstraintAnchor.Type.TOP);
        r144.mMargin = ((ViewGroup.MarginLayoutParams) r22).topMargin;
        ConstraintAnchor r145 = r03.getAnchor(ConstraintAnchor.Type.BOTTOM);
        r145.mMargin = ((ViewGroup.MarginLayoutParams) r22).bottomMargin;
        goto L111
    L105:
        r03.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.MATCH_PARENT);
        goto L106
    L88:
        if (((ViewGroup.MarginLayoutParams) r22).width == (-1)) goto L90;
        r03.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT);
        r03.setWidth(0);
        goto L99
    L90:
        if (r22.constrainedWidth == false) goto L92;
        r03.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT);
    L93:
        ConstraintAnchor r146 = r03.getAnchor(ConstraintAnchor.Type.LEFT);
        r146.mMargin = ((ViewGroup.MarginLayoutParams) r22).leftMargin;
        ConstraintAnchor r147 = r03.getAnchor(ConstraintAnchor.Type.RIGHT);
        r147.mMargin = ((ViewGroup.MarginLayoutParams) r22).rightMargin;
        goto L99
    L92:
        r03.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.MATCH_PARENT);
        goto L93
    L26:
        if (r02 == (-1)) goto L30;
        ConstraintWidget r23 = r19.get(r02);
        if (r23 == null) goto L34;
        ConstraintAnchor.Type r110 = ConstraintAnchor.Type.LEFT;
        r17.immediateConnect(r110, r23, r110, ((ViewGroup.MarginLayoutParams) r18).leftMargin, r5);
    L34:
        if (r10 == (-1)) goto L39;
        ConstraintWidget r24 = r19.get(r10);
        if (r24 == null) goto L43;
        r17.immediateConnect(ConstraintAnchor.Type.RIGHT, r24, ConstraintAnchor.Type.LEFT, ((ViewGroup.MarginLayoutParams) r18).rightMargin, r12);
    L43:
        int r04 = r18.topToTop;
        if (r04 == (-1)) goto L48;
        ConstraintWidget r25 = r19.get(r04);
        if (r25 == null) goto L53;
        ConstraintAnchor.Type r111 = ConstraintAnchor.Type.TOP;
        r17.immediateConnect(r111, r25, r111, ((ViewGroup.MarginLayoutParams) r18).topMargin, r18.goneTopMargin);
    L53:
        int r05 = r18.bottomToTop;
        if (r05 == (-1)) goto L58;
        ConstraintWidget r26 = r19.get(r05);
        if (r26 == null) goto L63;
        r17.immediateConnect(ConstraintAnchor.Type.BOTTOM, r26, ConstraintAnchor.Type.TOP, ((ViewGroup.MarginLayoutParams) r18).bottomMargin, r18.goneBottomMargin);
    L63:
        int r4 = r18.baselineToBaseline;
        if (r4 == (-1)) goto L67;
        r22 = r18;
        setWidgetBaseline(r17, r22, r19, r4, ConstraintAnchor.Type.BASELINE);
    L66:
        r03 = r17;
    L74:
        if (r13 < 0.0f) goto L76;
        r03.setHorizontalBiasPercent(r13);
    L76:
        float r112 = r22.verticalBias;
        if (r112 < 0.0f) goto L79;
        r03.setVerticalBiasPercent(r112);
        goto L79
    L67:
        r22 = r18;
        int r42 = r22.baselineToTop;
        if (r42 == (-1)) goto L70;
        setWidgetBaseline(r17, r22, r19, r42, ConstraintAnchor.Type.TOP);
        goto L66
    L70:
        int r43 = r22.baselineToBottom;
        if (r43 == (-1)) goto L66;
        setWidgetBaseline(r17, r22, r19, r43, ConstraintAnchor.Type.BOTTOM);
        r03 = r17;
        goto L74
    L58:
        int r06 = r18.bottomToBottom;
        if (r06 == (-1)) goto L63;
        ConstraintWidget r27 = r19.get(r06);
        if (r27 == null) goto L63;
        ConstraintAnchor.Type r113 = ConstraintAnchor.Type.BOTTOM;
        r17.immediateConnect(r113, r27, r113, ((ViewGroup.MarginLayoutParams) r18).bottomMargin, r18.goneBottomMargin);
        goto L63
    L48:
        int r07 = r18.topToBottom;
        if (r07 == (-1)) goto L53;
        ConstraintWidget r28 = r19.get(r07);
        if (r28 == null) goto L53;
        r17.immediateConnect(ConstraintAnchor.Type.TOP, r28, ConstraintAnchor.Type.BOTTOM, ((ViewGroup.MarginLayoutParams) r18).topMargin, r18.goneTopMargin);
        goto L53
    L39:
        if (r11 == (-1)) goto L43;
        ConstraintWidget r29 = r19.get(r11);
        if (r29 == null) goto L43;
        ConstraintAnchor.Type r114 = ConstraintAnchor.Type.RIGHT;
        r17.immediateConnect(r114, r29, r114, ((ViewGroup.MarginLayoutParams) r18).rightMargin, r12);
        goto L43
    L30:
        if (r2 == (-1)) goto L34;
        ConstraintWidget r210 = r19.get(r2);
        if (r210 == null) goto L34;
        r17.immediateConnect(ConstraintAnchor.Type.LEFT, r210, ConstraintAnchor.Type.RIGHT, ((ViewGroup.MarginLayoutParams) r18).leftMargin, r5);
        goto L34
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams r1) {
        return r1 instanceof LayoutParams;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r18) {
        ArrayList<ConstraintHelper> r1 = this.mConstraintHelpers;
        if (r1 == null) goto L9;
        int r12 = r1.size();
        if (r12 <= 0) goto L9;
        int r3 = 0;
    L7:
        if (r3 >= r12) goto L9;
        this.mConstraintHelpers.get(r3).updatePreDraw(this);
        r3 = r3 + 1;
    L9:
        super.dispatchDraw(r18);
        if (isInEditMode() == false) goto L24;
        float r13 = getWidth();
        float r32 = getHeight();
        int r4 = getChildCount();
        int r5 = 0;
    L12:
        if (r5 >= r4) goto L32;
        View r6 = getChildAt(r5);
        if (r6.getVisibility() == 8) goto L23;
        Object r62 = r6.getTag();
        if (r62 == null) goto L23;
        if ((r62 instanceof String) == false) goto L23;
        String[] r63 = ((String) r62).split(",");
        if (r63.length != 4) goto L23;
        int r7 = Integer.parseInt(r63[0]);
        int r8 = Integer.parseInt(r63[1]);
        int r9 = Integer.parseInt(r63[2]);
        int r72 = (int) ((r7 / 1080.0f) * r13);
        int r82 = (int) ((r8 / 1920.0f) * r32);
        Paint r15 = new Paint();
        r15.setColor(Opcodes.V_PREVIEW);
        float r11 = r72;
        float r122 = r82;
        float r132 = r72 + ((int) ((r9 / 1080.0f) * r13));
        r18.drawLine(r11, r122, r132, r122, r15);
        float r14 = r82 + ((int) ((Integer.parseInt(r63[3]) / 1920.0f) * r32));
        r18.drawLine(r132, r122, r132, r14, r15);
        r18.drawLine(r132, r14, r11, r14, r15);
        r18.drawLine(r11, r14, r11, r122, r15);
        r15.setColor(-16711936);
        r18.drawLine(r11, r122, r132, r14, r15);
        r18.drawLine(r11, r14, r132, r122, r15);
    L23:
        r5 = r5 + 1;
        goto L12
    L32:
        return;
    }

    public boolean dynamicUpdateConstraints(int r5, int r6) {
        if (this.mModifiers != null) goto L5;
        return false;
    L5:
        View.MeasureSpec.getSize(r5);
        View.MeasureSpec.getSize(r6);
        ArrayList<ValueModifier> r52 = this.mModifiers;
        int r62 = r52.size();
        int r0 = 0;
    L6:
        if (r0 >= r62) goto L12;
        ValueModifier r2 = r52.get(r0);
        r0 = r0 + 1;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r2);
        Iterator<ConstraintWidget> r22 = this.mLayoutWidget.getChildren().iterator();
        if (r22.hasNext() == false) goto L6;
        View r4 = (View) r22.next().getCompanionWidget();
        r4.getId();
        LayoutParams r42 = (LayoutParams) r4.getLayoutParams();
        throw null;
    L12:
        return false;
    }

    public void fillMetrics(Metrics r1) {
        this.mLayoutWidget.fillMetrics(r1);
    }

    @Override // android.view.View
    public void forceLayout() {
        markHierarchyDirty();
        super.forceLayout();
    }

    @Override // android.view.ViewGroup
    public /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return generateDefaultLayoutParams();
    }

    @Override // android.view.ViewGroup
    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    @Override // android.view.ViewGroup
    public /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(AttributeSet r1) {
        return generateLayoutParams(r1);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams r1) {
        return new LayoutParams(r1);
    }

    @Override // android.view.ViewGroup
    public LayoutParams generateLayoutParams(AttributeSet r2) {
        return new LayoutParams(getContext(), r2);
    }

    public Object getDesignInformation(int r1, Object r2) {
        if (r1 == 0) goto L4;
        return null;
    L4:
        if ((r2 instanceof String) == false) goto L13;
        String r22 = (String) r2;
        HashMap<String, Integer> r12 = this.mDesignIds;
        if (r12 != null) goto L8;
        return null;
    L8:
        if (r12.containsKey(r22) == true) goto L10;
        return null;
    L10:
        return this.mDesignIds.get(r22);
    L13:
        return null;
    }

    public int getMaxHeight() {
        return this.mMaxHeight;
    }

    public int getMaxWidth() {
        return this.mMaxWidth;
    }

    public int getMinHeight() {
        return this.mMinHeight;
    }

    public int getMinWidth() {
        return this.mMinWidth;
    }

    public int getOptimizationLevel() {
        return this.mLayoutWidget.getOptimizationLevel();
    }

    public String getSceneString() {
        StringBuilder r0 = new StringBuilder();
        if (this.mLayoutWidget.stringId != null) goto L9;
        int r1 = getId();
        if (r1 == (-1)) goto L7;
        this.mLayoutWidget.stringId = getContext().getResources().getResourceEntryName(r1);
        goto L9
    L7:
        this.mLayoutWidget.stringId = "parent";
    L9:
        if (this.mLayoutWidget.getDebugName() != null) goto L11;
        ConstraintWidgetContainer r12 = this.mLayoutWidget;
        r12.setDebugName(r12.stringId);
        Log.v(TAG, " setDebugName " + this.mLayoutWidget.getDebugName());
    L11:
        ArrayList<ConstraintWidget> r13 = this.mLayoutWidget.getChildren();
        int r5 = r13.size();
        int r6 = 0;
    L12:
        if (r6 >= r5) goto L23;
        ConstraintWidget r7 = r13.get(r6);
        r6 = r6 + 1;
        ConstraintWidget r72 = r7;
        View r8 = (View) r72.getCompanionWidget();
        if (r8 == null) goto L12;
        if (r72.stringId != null) goto L21;
        int r82 = r8.getId();
        if (r82 == (-1)) goto L21;
        r72.stringId = getContext().getResources().getResourceEntryName(r82);
    L21:
        if (r72.getDebugName() != null) goto L12;
        r72.setDebugName(r72.stringId);
        Log.v(TAG, " setDebugName " + r72.getDebugName());
        goto L12
    L23:
        this.mLayoutWidget.getSceneString(r0);
        return r0.toString();
    }

    public View getViewById(int r1) {
        return this.mChildrenByIds.get(r1);
    }

    public final ConstraintWidget getViewWidget(View r2) {
        if (r2 == this) goto L4;
        if (r2 != null) goto L7;
        return null;
    L7:
        if ((r2.getLayoutParams() instanceof LayoutParams) == true) goto L9;
        r2.setLayoutParams(generateLayoutParams(r2.getLayoutParams()));
        if ((r2.getLayoutParams() instanceof LayoutParams) == true) goto L13;
        return null;
    L13:
        return ((LayoutParams) r2.getLayoutParams()).mWidget;
    L9:
        return ((LayoutParams) r2.getLayoutParams()).mWidget;
    L4:
        return this.mLayoutWidget;
    }

    public boolean isRtl() {
        if ((getContext().getApplicationInfo().flags & 4194304) != 0) goto L5;
        return false;
    L5:
        if (1 != getLayoutDirection()) goto L9;
        return true;
    L9:
        return false;
    }

    public void loadLayoutDescription(int r4) {
        if (r4 != 0) goto L10;
        this.mConstraintLayoutSpec = null;
        return;
    L10:
        this.mConstraintLayoutSpec = new ConstraintLayoutStates(getContext(), this, r4);     // Catch: Resources.NotFoundException -> L6
        return;
    L6:
        this.mConstraintLayoutSpec = null;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean r5, int r6, int r7, int r8, int r9) {
        int r52 = getChildCount();
        boolean r62 = isInEditMode();
        int r72 = 0;
        int r82 = 0;
    L3:
        if (r82 >= r52) goto L19;
        View r92 = getChildAt(r82);
        LayoutParams r0 = (LayoutParams) r92.getLayoutParams();
        ConstraintWidget r1 = r0.mWidget;
        if (r92.getVisibility() != 8) goto L15;
        if (r0.mIsGuideline == true) goto L15;
        if (r0.mIsHelper == true) goto L15;
        if (r0.mIsVirtualGroup == true) goto L15;
        if (r62 == true) goto L15;
    L18:
        r82 = r82 + 1;
    L15:
        if (r0.mIsInPlaceholder == true) goto L18;
        int r02 = r1.getX();
        int r2 = r1.getY();
        r92.layout(r02, r2, r1.getWidth() + r02, r1.getHeight() + r2);
        goto L18
    L19:
        int r53 = this.mConstraintHelpers.size();
        if (r53 <= 0) goto L23;
    L21:
        if (r72 >= r53) goto L29;
        this.mConstraintHelpers.get(r72).updatePostLayout(this);
        r72 = r72 + 1;
        goto L21
    L29:
        return;
    }

    @Override // android.view.View
    public void onMeasure(int r9, int r10) {
        boolean r0 = this.mDirtyHierarchy | dynamicUpdateConstraints(r9, r10);
        this.mDirtyHierarchy = r0;
        if (r0 == true) goto L10;
        int r02 = getChildCount();
        int r2 = 0;
    L5:
        if (r2 >= r02) goto L10;
        if (getChildAt(r2).isLayoutRequested() == true) goto L8;
        r2 = r2 + 1;
        goto L5
    L8:
        this.mDirtyHierarchy = true;
    L10:
        this.mOnMeasureWidthMeasureSpec = r9;
        this.mOnMeasureHeightMeasureSpec = r10;
        this.mLayoutWidget.setRtl(isRtl());
        if (this.mDirtyHierarchy == false) goto L15;
        this.mDirtyHierarchy = false;
        if (updateHierarchy() == false) goto L15;
        this.mLayoutWidget.updateHierarchy();
    L15:
        this.mLayoutWidget.fillMetrics(null);
        resolveSystem(this.mLayoutWidget, this.mOptimizationLevel, r9, r10);
        resolveMeasuredDimension(r9, r10, this.mLayoutWidget.getWidth(), this.mLayoutWidget.getHeight(), this.mLayoutWidget.isWidthMeasuredTooSmall(), this.mLayoutWidget.isHeightMeasuredTooSmall());
    }

    @Override // android.view.ViewGroup
    public void onViewAdded(View r4) {
        super.onViewAdded(r4);
        ConstraintWidget r0 = getViewWidget(r4);
        if ((r4 instanceof Guideline) == false) goto L8;
        if ((r0 instanceof androidx.constraintlayout.core.widgets.Guideline) == true) goto L8;
        LayoutParams r02 = (LayoutParams) r4.getLayoutParams();
        androidx.constraintlayout.core.widgets.Guideline r1 = new androidx.constraintlayout.core.widgets.Guideline();
        r02.mWidget = r1;
        r02.mIsGuideline = true;
        r1.setOrientation(r02.orientation);
    L8:
        if ((r4 instanceof ConstraintHelper) == false) goto L12;
        ConstraintHelper r03 = (ConstraintHelper) r4;
        r03.validateParams();
        ((LayoutParams) r4.getLayoutParams()).mIsHelper = true;
        if (this.mConstraintHelpers.contains(r03) == true) goto L12;
        this.mConstraintHelpers.add(r03);
    L12:
        this.mChildrenByIds.put(r4.getId(), r4);
        this.mDirtyHierarchy = true;
    }

    @Override // android.view.ViewGroup
    public void onViewRemoved(View r3) {
        super.onViewRemoved(r3);
        this.mChildrenByIds.remove(r3.getId());
        ConstraintWidget r0 = getViewWidget(r3);
        this.mLayoutWidget.remove(r0);
        this.mConstraintHelpers.remove(r3);
        this.mDirtyHierarchy = true;
    }

    public void parseLayoutDescription(int r3) {
        this.mConstraintLayoutSpec = new ConstraintLayoutStates(getContext(), this, r3);
    }

    public void removeValueModifier(ValueModifier r1) {
        if (r1 != null) goto L4;
        return;
    L4:
        this.mModifiers.remove(r1);
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        markHierarchyDirty();
        super.requestLayout();
    }

    public void resolveMeasuredDimension(int r3, int r4, int r5, int r6, boolean r7, boolean r8) {
        Measurer r0 = this.mMeasurer;
        int r1 = r0.mPaddingHeight;
        int r32 = View.resolveSizeAndState(r5 + r0.mPaddingWidth, r3, 0);
        int r42 = View.resolveSizeAndState(r6 + r1, r4, 0) & 16777215;
        int r52 = this.mMaxWidth;
        int r33 = Math.min(r52, r32 & 16777215);
        int r43 = Math.min(this.mMaxHeight, r42);
        if (r7 == false) goto L5;
        r33 = r33 | 16777216;
    L5:
        if (r8 == false) goto L7;
        r43 = r43 | 16777216;
    L7:
        setMeasuredDimension(r33, r43);
        this.mLastMeasureWidth = r33;
        this.mLastMeasureHeight = r43;
    }

    public void resolveSystem(ConstraintWidgetContainer r14, int r15, int r16, int r17) {
        int r2 = View.MeasureSpec.getMode(r16);
        int r0 = View.MeasureSpec.getSize(r16);
        int r4 = View.MeasureSpec.getMode(r17);
        int r1 = View.MeasureSpec.getSize(r17);
        int r9 = Math.max(0, getPaddingTop());
        int r10 = Math.max(0, getPaddingBottom());
        int r12 = r9 + r10;
        int r11 = getPaddingWidth();
        this.mMeasurer.captureLayoutInfo(r16, r17, r9, r10, r11, r12);
        int r3 = Math.max(0, getPaddingStart());
        int r6 = Math.max(0, getPaddingEnd());
        if (r3 > 0) goto L9;
        if (r6 > 0) goto L9;
        r3 = Math.max(0, getPaddingLeft());
    L7:
        int r8 = r3;
    L11:
        int r32 = r0 - r11;
        int r5 = r1 - r12;
        setSelfDimensionBehaviour(r14, r2, r32, r4, r5);
        r14.measure(r15, r2, r32, r4, r5, this.mLastMeasureWidth, this.mLastMeasureHeight, r8, r9);
        return;
    L9:
        if (isRtl() == false) goto L7;
        r8 = r6;
        goto L11
    }

    public void setConstraintSet(ConstraintSet r1) {
        this.mConstraintSet = r1;
    }

    public void setDesignInformation(int r2, Object r3, Object r4) {
        if (r2 == 0) goto L4;
        return;
    L4:
        if ((r3 instanceof String) == true) goto L6;
        return;
    L6:
        if ((r4 instanceof Integer) == true) goto L8;
        return;
    L8:
        if (this.mDesignIds != null) goto L10;
        this.mDesignIds = new HashMap();
    L10:
        String r32 = (String) r3;
        int r22 = r32.indexOf("/");
        if (r22 == (-1)) goto L13;
        r32 = r32.substring(r22 + 1);
    L13:
        HashMap<String, Integer> r1 = this.mDesignIds;
        r1.put(r32, (Integer) r4);
    }

    @Override // android.view.View
    public void setId(int r3) {
        this.mChildrenByIds.remove(getId());
        super.setId(r3);
        this.mChildrenByIds.put(getId(), this);
    }

    public void setMaxHeight(int r2) {
        if (r2 != this.mMaxHeight) goto L5;
        return;
    L5:
        this.mMaxHeight = r2;
        requestLayout();
    }

    public void setMaxWidth(int r2) {
        if (r2 != this.mMaxWidth) goto L5;
        return;
    L5:
        this.mMaxWidth = r2;
        requestLayout();
    }

    public void setMinHeight(int r2) {
        if (r2 != this.mMinHeight) goto L5;
        return;
    L5:
        this.mMinHeight = r2;
        requestLayout();
    }

    public void setMinWidth(int r2) {
        if (r2 != this.mMinWidth) goto L5;
        return;
    L5:
        this.mMinWidth = r2;
        requestLayout();
    }

    public void setOnConstraintsChanged(ConstraintsChangedListener r1) {
        ConstraintLayoutStates r0 = this.mConstraintLayoutSpec;
        if (r0 == null) goto L6;
        r0.setOnConstraintsChanged(r1);
        return;
    }

    public void setOptimizationLevel(int r1) {
        this.mOptimizationLevel = r1;
        this.mLayoutWidget.setOptimizationLevel(r1);
    }

    public void setSelfDimensionBehaviour(ConstraintWidgetContainer r8, int r9, int r10, int r11, int r12) {
        Measurer r0 = this.mMeasurer;
        int r1 = r0.mPaddingHeight;
        int r02 = r0.mPaddingWidth;
        ConstraintWidget.DimensionBehaviour r2 = ConstraintWidget.DimensionBehaviour.FIXED;
        int r3 = getChildCount();
        if (r9 == Integer.MIN_VALUE) goto L12;
        if (r9 == 0) goto L9;
        if (r9 == 1073741824) goto L8;
        ConstraintWidget.DimensionBehaviour r92 = r2;
    L7:
        r10 = 0;
    L15:
        if (r11 == Integer.MIN_VALUE) goto L23;
        if (r11 == 0) goto L20;
        if (r11 == 1073741824) goto L19;
    L18:
        r12 = 0;
    L27:
        if (r10 == r8.getWidth()) goto L29;
    L30:
        r8.invalidateMeasures();
    L31:
        r8.setX(0);
        r8.setY(0);
        r8.setMaxWidth(this.mMaxWidth - r02);
        r8.setMaxHeight(this.mMaxHeight - r1);
        r8.setMinWidth(0);
        r8.setMinHeight(0);
        r8.setHorizontalDimensionBehaviour(r92);
        r8.setWidth(r10);
        r8.setVerticalDimensionBehaviour(r2);
        r8.setHeight(r12);
        r8.setMinWidth(this.mMinWidth - r02);
        r8.setMinHeight(this.mMinHeight - r1);
        return;
    L29:
        if (r12 == r8.getHeight()) goto L31;
    L19:
        r12 = Math.min(this.mMaxHeight - r1, r12);
        goto L27
    L20:
        r2 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (r3 != 0) goto L18;
        r12 = Math.max(0, this.mMinHeight);
        goto L27
    L23:
        r2 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (r3 != 0) goto L27;
        r12 = Math.max(0, this.mMinHeight);
        goto L27
    L8:
        r10 = Math.min(this.mMaxWidth - r02, r10);
        r92 = r2;
        goto L15
    L9:
        r92 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (r3 != 0) goto L7;
        r10 = Math.max(0, this.mMinWidth);
        goto L15
    L12:
        r92 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (r3 != 0) goto L15;
        r10 = Math.max(0, this.mMinWidth);
        goto L15
    }

    public void setState(int r1, int r2, int r3) {
        ConstraintLayoutStates r0 = this.mConstraintLayoutSpec;
        if (r0 == null) goto L6;
        r0.updateConstraints(r1, r2, r3);
        return;
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }
}
