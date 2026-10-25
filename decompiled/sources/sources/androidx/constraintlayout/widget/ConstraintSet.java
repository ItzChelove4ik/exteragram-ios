package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.core.motion.utils.Easing;
import androidx.constraintlayout.motion.widget.Debug;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import okhttp3.internal.url._UrlKt;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes3.dex */
public class ConstraintSet {
    private static final int[] VISIBILITY_FLAGS = null;
    private static SparseIntArray sMapToConstant;
    private static SparseIntArray sOverrideMapToConstant;
    public String derivedState;
    private HashMap<Integer, Constraint> mConstraints;
    private boolean mForceId;
    private String[] mMatchLabels;
    public int mRotate;
    private HashMap<String, ConstraintAttribute> mSavedAttributes;

    public static class Constraint {
        public final Layout layout;
        public HashMap<String, ConstraintAttribute> mCustomConstraints;
        Delta mDelta;
        String mTargetString;
        int mViewId;
        public final Motion motion;
        public final PropertySet propertySet;
        public final Transform transform;

        public static class Delta {
            int mCountBoolean;
            int mCountFloat;
            int mCountInt;
            int mCountString;
            int[] mTypeBoolean;
            int[] mTypeFloat;
            int[] mTypeInt;
            int[] mTypeString;
            boolean[] mValueBoolean;
            float[] mValueFloat;
            int[] mValueInt;
            String[] mValueString;

            public Delta() {
                this.mTypeInt = new int[10];
                this.mValueInt = new int[10];
                this.mCountInt = 0;
                this.mTypeFloat = new int[10];
                this.mValueFloat = new float[10];
                this.mCountFloat = 0;
                this.mTypeString = new int[5];
                this.mValueString = new String[5];
                this.mCountString = 0;
                this.mTypeBoolean = new int[4];
                this.mValueBoolean = new boolean[4];
                this.mCountBoolean = 0;
            }

            public void add(int r4, float r5) {
                int r0 = this.mCountFloat;
                int[] r1 = this.mTypeFloat;
                if (r0 < r1.length) goto L5;
                this.mTypeFloat = Arrays.copyOf(r1, r1.length * 2);
                float[] r02 = this.mValueFloat;
                this.mValueFloat = Arrays.copyOf(r02, r02.length * 2);
            L5:
                int[] r03 = this.mTypeFloat;
                int r12 = this.mCountFloat;
                r03[r12] = r4;
                float[] r42 = this.mValueFloat;
                this.mCountFloat = r12 + 1;
                r42[r12] = r5;
            }

            public void add(int r4, int r5) {
                int r0 = this.mCountInt;
                int[] r1 = this.mTypeInt;
                if (r0 < r1.length) goto L5;
                this.mTypeInt = Arrays.copyOf(r1, r1.length * 2);
                int[] r02 = this.mValueInt;
                this.mValueInt = Arrays.copyOf(r02, r02.length * 2);
            L5:
                int[] r03 = this.mTypeInt;
                int r12 = this.mCountInt;
                r03[r12] = r4;
                int[] r42 = this.mValueInt;
                this.mCountInt = r12 + 1;
                r42[r12] = r5;
            }

            public void add(int r4, String r5) {
                int r0 = this.mCountString;
                int[] r1 = this.mTypeString;
                if (r0 < r1.length) goto L5;
                this.mTypeString = Arrays.copyOf(r1, r1.length * 2);
                String[] r02 = this.mValueString;
                this.mValueString = (String[]) Arrays.copyOf(r02, r02.length * 2);
            L5:
                int[] r03 = this.mTypeString;
                int r12 = this.mCountString;
                r03[r12] = r4;
                String[] r42 = this.mValueString;
                this.mCountString = r12 + 1;
                r42[r12] = r5;
            }

            public void add(int r4, boolean r5) {
                int r0 = this.mCountBoolean;
                int[] r1 = this.mTypeBoolean;
                if (r0 < r1.length) goto L5;
                this.mTypeBoolean = Arrays.copyOf(r1, r1.length * 2);
                boolean[] r02 = this.mValueBoolean;
                this.mValueBoolean = Arrays.copyOf(r02, r02.length * 2);
            L5:
                int[] r03 = this.mTypeBoolean;
                int r12 = this.mCountBoolean;
                r03[r12] = r4;
                boolean[] r42 = this.mValueBoolean;
                this.mCountBoolean = r12 + 1;
                r42[r12] = r5;
            }
        }

        public Constraint() {
            this.propertySet = new PropertySet();
            this.motion = new Motion();
            this.layout = new Layout();
            this.transform = new Transform();
            this.mCustomConstraints = new HashMap();
        }

        public static /* synthetic */ void access$000(Constraint r0, int r1, ConstraintLayout.LayoutParams r2) {
            r0.fillFrom(r1, r2);
        }

        private void fillFrom(int r2, ConstraintLayout.LayoutParams r3) {
            this.mViewId = r2;
            Layout r22 = this.layout;
            r22.leftToLeft = r3.leftToLeft;
            r22.leftToRight = r3.leftToRight;
            r22.rightToLeft = r3.rightToLeft;
            r22.rightToRight = r3.rightToRight;
            r22.topToTop = r3.topToTop;
            r22.topToBottom = r3.topToBottom;
            r22.bottomToTop = r3.bottomToTop;
            r22.bottomToBottom = r3.bottomToBottom;
            r22.baselineToBaseline = r3.baselineToBaseline;
            r22.baselineToTop = r3.baselineToTop;
            r22.baselineToBottom = r3.baselineToBottom;
            r22.startToEnd = r3.startToEnd;
            r22.startToStart = r3.startToStart;
            r22.endToStart = r3.endToStart;
            r22.endToEnd = r3.endToEnd;
            r22.horizontalBias = r3.horizontalBias;
            r22.verticalBias = r3.verticalBias;
            r22.dimensionRatio = r3.dimensionRatio;
            r22.circleConstraint = r3.circleConstraint;
            r22.circleRadius = r3.circleRadius;
            r22.circleAngle = r3.circleAngle;
            r22.editorAbsoluteX = r3.editorAbsoluteX;
            r22.editorAbsoluteY = r3.editorAbsoluteY;
            r22.orientation = r3.orientation;
            r22.guidePercent = r3.guidePercent;
            r22.guideBegin = r3.guideBegin;
            r22.guideEnd = r3.guideEnd;
            r22.mWidth = ((ViewGroup.MarginLayoutParams) r3).width;
            r22.mHeight = ((ViewGroup.MarginLayoutParams) r3).height;
            r22.leftMargin = ((ViewGroup.MarginLayoutParams) r3).leftMargin;
            r22.rightMargin = ((ViewGroup.MarginLayoutParams) r3).rightMargin;
            r22.topMargin = ((ViewGroup.MarginLayoutParams) r3).topMargin;
            r22.bottomMargin = ((ViewGroup.MarginLayoutParams) r3).bottomMargin;
            r22.baselineMargin = r3.baselineMargin;
            r22.verticalWeight = r3.verticalWeight;
            r22.horizontalWeight = r3.horizontalWeight;
            r22.verticalChainStyle = r3.verticalChainStyle;
            r22.horizontalChainStyle = r3.horizontalChainStyle;
            r22.constrainedWidth = r3.constrainedWidth;
            r22.constrainedHeight = r3.constrainedHeight;
            r22.widthDefault = r3.matchConstraintDefaultWidth;
            r22.heightDefault = r3.matchConstraintDefaultHeight;
            r22.widthMax = r3.matchConstraintMaxWidth;
            r22.heightMax = r3.matchConstraintMaxHeight;
            r22.widthMin = r3.matchConstraintMinWidth;
            r22.heightMin = r3.matchConstraintMinHeight;
            r22.widthPercent = r3.matchConstraintPercentWidth;
            r22.heightPercent = r3.matchConstraintPercentHeight;
            r22.mConstraintTag = r3.constraintTag;
            r22.goneTopMargin = r3.goneTopMargin;
            r22.goneBottomMargin = r3.goneBottomMargin;
            r22.goneLeftMargin = r3.goneLeftMargin;
            r22.goneRightMargin = r3.goneRightMargin;
            r22.goneStartMargin = r3.goneStartMargin;
            r22.goneEndMargin = r3.goneEndMargin;
            r22.goneBaselineMargin = r3.goneBaselineMargin;
            r22.mWrapBehavior = r3.wrapBehaviorInParent;
            r22.endMargin = r3.getMarginEnd();
            Layout r1 = this.layout;
            r1.startMargin = r3.getMarginStart();
        }

        public void applyTo(ConstraintLayout.LayoutParams r3) {
            Layout r0 = this.layout;
            r3.leftToLeft = r0.leftToLeft;
            r3.leftToRight = r0.leftToRight;
            r3.rightToLeft = r0.rightToLeft;
            r3.rightToRight = r0.rightToRight;
            r3.topToTop = r0.topToTop;
            r3.topToBottom = r0.topToBottom;
            r3.bottomToTop = r0.bottomToTop;
            r3.bottomToBottom = r0.bottomToBottom;
            r3.baselineToBaseline = r0.baselineToBaseline;
            r3.baselineToTop = r0.baselineToTop;
            r3.baselineToBottom = r0.baselineToBottom;
            r3.startToEnd = r0.startToEnd;
            r3.startToStart = r0.startToStart;
            r3.endToStart = r0.endToStart;
            r3.endToEnd = r0.endToEnd;
            ((ViewGroup.MarginLayoutParams) r3).leftMargin = r0.leftMargin;
            ((ViewGroup.MarginLayoutParams) r3).rightMargin = r0.rightMargin;
            ((ViewGroup.MarginLayoutParams) r3).topMargin = r0.topMargin;
            ((ViewGroup.MarginLayoutParams) r3).bottomMargin = r0.bottomMargin;
            r3.goneStartMargin = r0.goneStartMargin;
            r3.goneEndMargin = r0.goneEndMargin;
            r3.goneTopMargin = r0.goneTopMargin;
            r3.goneBottomMargin = r0.goneBottomMargin;
            r3.horizontalBias = r0.horizontalBias;
            r3.verticalBias = r0.verticalBias;
            r3.circleConstraint = r0.circleConstraint;
            r3.circleRadius = r0.circleRadius;
            r3.circleAngle = r0.circleAngle;
            r3.dimensionRatio = r0.dimensionRatio;
            r3.editorAbsoluteX = r0.editorAbsoluteX;
            r3.editorAbsoluteY = r0.editorAbsoluteY;
            r3.verticalWeight = r0.verticalWeight;
            r3.horizontalWeight = r0.horizontalWeight;
            r3.verticalChainStyle = r0.verticalChainStyle;
            r3.horizontalChainStyle = r0.horizontalChainStyle;
            r3.constrainedWidth = r0.constrainedWidth;
            r3.constrainedHeight = r0.constrainedHeight;
            r3.matchConstraintDefaultWidth = r0.widthDefault;
            r3.matchConstraintDefaultHeight = r0.heightDefault;
            r3.matchConstraintMaxWidth = r0.widthMax;
            r3.matchConstraintMaxHeight = r0.heightMax;
            r3.matchConstraintMinWidth = r0.widthMin;
            r3.matchConstraintMinHeight = r0.heightMin;
            r3.matchConstraintPercentWidth = r0.widthPercent;
            r3.matchConstraintPercentHeight = r0.heightPercent;
            r3.orientation = r0.orientation;
            r3.guidePercent = r0.guidePercent;
            r3.guideBegin = r0.guideBegin;
            r3.guideEnd = r0.guideEnd;
            ((ViewGroup.MarginLayoutParams) r3).width = r0.mWidth;
            ((ViewGroup.MarginLayoutParams) r3).height = r0.mHeight;
            String r1 = r0.mConstraintTag;
            if (r1 == null) goto L5;
            r3.constraintTag = r1;
        L5:
            r3.wrapBehaviorInParent = r0.mWrapBehavior;
            r3.setMarginStart(r0.startMargin);
            r3.setMarginEnd(this.layout.endMargin);
            r3.validate();
        }

        public Constraint clone() {
            Constraint r0 = new Constraint();
            r0.layout.copyFrom(this.layout);
            r0.motion.copyFrom(this.motion);
            r0.propertySet.copyFrom(this.propertySet);
            r0.transform.copyFrom(this.transform);
            r0.mViewId = this.mViewId;
            r0.mDelta = this.mDelta;
            return r0;
        }

        /* renamed from: clone, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ Object m667clone() {
            return clone();
        }
    }

    public static class Layout {
        private static SparseIntArray sMapToConstant;
        public int baselineMargin;
        public int baselineToBaseline;
        public int baselineToBottom;
        public int baselineToTop;
        public int bottomMargin;
        public int bottomToBottom;
        public int bottomToTop;
        public float circleAngle;
        public int circleConstraint;
        public int circleRadius;
        public boolean constrainedHeight;
        public boolean constrainedWidth;
        public String dimensionRatio;
        public int editorAbsoluteX;
        public int editorAbsoluteY;
        public int endMargin;
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
        public int heightDefault;
        public int heightMax;
        public int heightMin;
        public float heightPercent;
        public float horizontalBias;
        public int horizontalChainStyle;
        public float horizontalWeight;
        public int leftMargin;
        public int leftToLeft;
        public int leftToRight;
        public boolean mApply;
        public boolean mBarrierAllowsGoneWidgets;
        public int mBarrierDirection;
        public int mBarrierMargin;
        public String mConstraintTag;
        public int mHeight;
        public int mHelperType;
        public boolean mIsGuideline;
        public boolean mOverride;
        public String mReferenceIdString;
        public int[] mReferenceIds;
        public int mWidth;
        public int mWrapBehavior;
        public int orientation;
        public int rightMargin;
        public int rightToLeft;
        public int rightToRight;
        public int startMargin;
        public int startToEnd;
        public int startToStart;
        public int topMargin;
        public int topToBottom;
        public int topToTop;
        public float verticalBias;
        public int verticalChainStyle;
        public float verticalWeight;
        public int widthDefault;
        public int widthMax;
        public int widthMin;
        public float widthPercent;

        static {
            SparseIntArray r0 = new SparseIntArray();
            sMapToConstant = r0;
            r0.append(R$styleable.Layout_layout_constraintLeft_toLeftOf, 24);
            sMapToConstant.append(R$styleable.Layout_layout_constraintLeft_toRightOf, 25);
            sMapToConstant.append(R$styleable.Layout_layout_constraintRight_toLeftOf, 28);
            sMapToConstant.append(R$styleable.Layout_layout_constraintRight_toRightOf, 29);
            sMapToConstant.append(R$styleable.Layout_layout_constraintTop_toTopOf, 35);
            sMapToConstant.append(R$styleable.Layout_layout_constraintTop_toBottomOf, 34);
            sMapToConstant.append(R$styleable.Layout_layout_constraintBottom_toTopOf, 4);
            sMapToConstant.append(R$styleable.Layout_layout_constraintBottom_toBottomOf, 3);
            sMapToConstant.append(R$styleable.Layout_layout_constraintBaseline_toBaselineOf, 1);
            sMapToConstant.append(R$styleable.Layout_layout_editor_absoluteX, 6);
            sMapToConstant.append(R$styleable.Layout_layout_editor_absoluteY, 7);
            sMapToConstant.append(R$styleable.Layout_layout_constraintGuide_begin, 17);
            sMapToConstant.append(R$styleable.Layout_layout_constraintGuide_end, 18);
            sMapToConstant.append(R$styleable.Layout_layout_constraintGuide_percent, 19);
            sMapToConstant.append(R$styleable.Layout_guidelineUseRtl, 90);
            sMapToConstant.append(R$styleable.Layout_android_orientation, 26);
            sMapToConstant.append(R$styleable.Layout_layout_constraintStart_toEndOf, 31);
            sMapToConstant.append(R$styleable.Layout_layout_constraintStart_toStartOf, 32);
            sMapToConstant.append(R$styleable.Layout_layout_constraintEnd_toStartOf, 10);
            sMapToConstant.append(R$styleable.Layout_layout_constraintEnd_toEndOf, 9);
            sMapToConstant.append(R$styleable.Layout_layout_goneMarginLeft, 13);
            sMapToConstant.append(R$styleable.Layout_layout_goneMarginTop, 16);
            sMapToConstant.append(R$styleable.Layout_layout_goneMarginRight, 14);
            sMapToConstant.append(R$styleable.Layout_layout_goneMarginBottom, 11);
            sMapToConstant.append(R$styleable.Layout_layout_goneMarginStart, 15);
            sMapToConstant.append(R$styleable.Layout_layout_goneMarginEnd, 12);
            sMapToConstant.append(R$styleable.Layout_layout_constraintVertical_weight, 38);
            sMapToConstant.append(R$styleable.Layout_layout_constraintHorizontal_weight, 37);
            sMapToConstant.append(R$styleable.Layout_layout_constraintHorizontal_chainStyle, 39);
            sMapToConstant.append(R$styleable.Layout_layout_constraintVertical_chainStyle, 40);
            sMapToConstant.append(R$styleable.Layout_layout_constraintHorizontal_bias, 20);
            sMapToConstant.append(R$styleable.Layout_layout_constraintVertical_bias, 36);
            sMapToConstant.append(R$styleable.Layout_layout_constraintDimensionRatio, 5);
            sMapToConstant.append(R$styleable.Layout_layout_constraintLeft_creator, 91);
            sMapToConstant.append(R$styleable.Layout_layout_constraintTop_creator, 91);
            sMapToConstant.append(R$styleable.Layout_layout_constraintRight_creator, 91);
            sMapToConstant.append(R$styleable.Layout_layout_constraintBottom_creator, 91);
            sMapToConstant.append(R$styleable.Layout_layout_constraintBaseline_creator, 91);
            sMapToConstant.append(R$styleable.Layout_android_layout_marginLeft, 23);
            sMapToConstant.append(R$styleable.Layout_android_layout_marginRight, 27);
            sMapToConstant.append(R$styleable.Layout_android_layout_marginStart, 30);
            sMapToConstant.append(R$styleable.Layout_android_layout_marginEnd, 8);
            sMapToConstant.append(R$styleable.Layout_android_layout_marginTop, 33);
            sMapToConstant.append(R$styleable.Layout_android_layout_marginBottom, 2);
            sMapToConstant.append(R$styleable.Layout_android_layout_width, 22);
            sMapToConstant.append(R$styleable.Layout_android_layout_height, 21);
            sMapToConstant.append(R$styleable.Layout_layout_constraintWidth, 41);
            sMapToConstant.append(R$styleable.Layout_layout_constraintHeight, 42);
            sMapToConstant.append(R$styleable.Layout_layout_constrainedWidth, 87);
            sMapToConstant.append(R$styleable.Layout_layout_constrainedHeight, 88);
            sMapToConstant.append(R$styleable.Layout_layout_wrapBehaviorInParent, 76);
            sMapToConstant.append(R$styleable.Layout_layout_constraintCircle, 61);
            sMapToConstant.append(R$styleable.Layout_layout_constraintCircleRadius, 62);
            sMapToConstant.append(R$styleable.Layout_layout_constraintCircleAngle, 63);
            sMapToConstant.append(R$styleable.Layout_layout_constraintWidth_percent, 69);
            sMapToConstant.append(R$styleable.Layout_layout_constraintHeight_percent, 70);
            sMapToConstant.append(R$styleable.Layout_chainUseRtl, 71);
            sMapToConstant.append(R$styleable.Layout_barrierDirection, 72);
            sMapToConstant.append(R$styleable.Layout_barrierMargin, 73);
            sMapToConstant.append(R$styleable.Layout_constraint_referenced_ids, 74);
            sMapToConstant.append(R$styleable.Layout_barrierAllowsGoneWidgets, 75);
            sMapToConstant.append(R$styleable.Layout_layout_constraintWidth_max, 84);
            sMapToConstant.append(R$styleable.Layout_layout_constraintWidth_min, 86);
            sMapToConstant.append(R$styleable.Layout_layout_constraintWidth_max, 83);
            sMapToConstant.append(R$styleable.Layout_layout_constraintHeight_min, 85);
            sMapToConstant.append(R$styleable.Layout_layout_constraintWidth, 87);
            sMapToConstant.append(R$styleable.Layout_layout_constraintHeight, 88);
            sMapToConstant.append(R$styleable.ConstraintLayout_Layout_layout_constraintTag, 89);
            sMapToConstant.append(R$styleable.Layout_guidelineUseRtl, 90);
        }

        public Layout() {
            this.mIsGuideline = false;
            this.mApply = false;
            this.mOverride = false;
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
            this.startToEnd = -1;
            this.startToStart = -1;
            this.endToStart = -1;
            this.endToEnd = -1;
            this.horizontalBias = 0.5f;
            this.verticalBias = 0.5f;
            this.dimensionRatio = null;
            this.circleConstraint = -1;
            this.circleRadius = 0;
            this.circleAngle = 0.0f;
            this.editorAbsoluteX = -1;
            this.editorAbsoluteY = -1;
            this.orientation = -1;
            this.leftMargin = 0;
            this.rightMargin = 0;
            this.topMargin = 0;
            this.bottomMargin = 0;
            this.endMargin = 0;
            this.startMargin = 0;
            this.baselineMargin = 0;
            this.goneLeftMargin = Integer.MIN_VALUE;
            this.goneTopMargin = Integer.MIN_VALUE;
            this.goneRightMargin = Integer.MIN_VALUE;
            this.goneBottomMargin = Integer.MIN_VALUE;
            this.goneEndMargin = Integer.MIN_VALUE;
            this.goneStartMargin = Integer.MIN_VALUE;
            this.goneBaselineMargin = Integer.MIN_VALUE;
            this.verticalWeight = -1.0f;
            this.horizontalWeight = -1.0f;
            this.horizontalChainStyle = 0;
            this.verticalChainStyle = 0;
            this.widthDefault = 0;
            this.heightDefault = 0;
            this.widthMax = 0;
            this.heightMax = 0;
            this.widthMin = 0;
            this.heightMin = 0;
            this.widthPercent = 1.0f;
            this.heightPercent = 1.0f;
            this.mBarrierDirection = -1;
            this.mBarrierMargin = 0;
            this.mHelperType = -1;
            this.constrainedWidth = false;
            this.constrainedHeight = false;
            this.mBarrierAllowsGoneWidgets = true;
            this.mWrapBehavior = 0;
        }

        public void copyFrom(Layout r3) {
            this.mIsGuideline = r3.mIsGuideline;
            this.mWidth = r3.mWidth;
            this.mApply = r3.mApply;
            this.mHeight = r3.mHeight;
            this.guideBegin = r3.guideBegin;
            this.guideEnd = r3.guideEnd;
            this.guidePercent = r3.guidePercent;
            this.guidelineUseRtl = r3.guidelineUseRtl;
            this.leftToLeft = r3.leftToLeft;
            this.leftToRight = r3.leftToRight;
            this.rightToLeft = r3.rightToLeft;
            this.rightToRight = r3.rightToRight;
            this.topToTop = r3.topToTop;
            this.topToBottom = r3.topToBottom;
            this.bottomToTop = r3.bottomToTop;
            this.bottomToBottom = r3.bottomToBottom;
            this.baselineToBaseline = r3.baselineToBaseline;
            this.baselineToTop = r3.baselineToTop;
            this.baselineToBottom = r3.baselineToBottom;
            this.startToEnd = r3.startToEnd;
            this.startToStart = r3.startToStart;
            this.endToStart = r3.endToStart;
            this.endToEnd = r3.endToEnd;
            this.horizontalBias = r3.horizontalBias;
            this.verticalBias = r3.verticalBias;
            this.dimensionRatio = r3.dimensionRatio;
            this.circleConstraint = r3.circleConstraint;
            this.circleRadius = r3.circleRadius;
            this.circleAngle = r3.circleAngle;
            this.editorAbsoluteX = r3.editorAbsoluteX;
            this.editorAbsoluteY = r3.editorAbsoluteY;
            this.orientation = r3.orientation;
            this.leftMargin = r3.leftMargin;
            this.rightMargin = r3.rightMargin;
            this.topMargin = r3.topMargin;
            this.bottomMargin = r3.bottomMargin;
            this.endMargin = r3.endMargin;
            this.startMargin = r3.startMargin;
            this.baselineMargin = r3.baselineMargin;
            this.goneLeftMargin = r3.goneLeftMargin;
            this.goneTopMargin = r3.goneTopMargin;
            this.goneRightMargin = r3.goneRightMargin;
            this.goneBottomMargin = r3.goneBottomMargin;
            this.goneEndMargin = r3.goneEndMargin;
            this.goneStartMargin = r3.goneStartMargin;
            this.goneBaselineMargin = r3.goneBaselineMargin;
            this.verticalWeight = r3.verticalWeight;
            this.horizontalWeight = r3.horizontalWeight;
            this.horizontalChainStyle = r3.horizontalChainStyle;
            this.verticalChainStyle = r3.verticalChainStyle;
            this.widthDefault = r3.widthDefault;
            this.heightDefault = r3.heightDefault;
            this.widthMax = r3.widthMax;
            this.heightMax = r3.heightMax;
            this.widthMin = r3.widthMin;
            this.heightMin = r3.heightMin;
            this.widthPercent = r3.widthPercent;
            this.heightPercent = r3.heightPercent;
            this.mBarrierDirection = r3.mBarrierDirection;
            this.mBarrierMargin = r3.mBarrierMargin;
            this.mHelperType = r3.mHelperType;
            this.mConstraintTag = r3.mConstraintTag;
            int[] r0 = r3.mReferenceIds;
            if (r0 != null) goto L5;
        L7:
            this.mReferenceIds = null;
        L8:
            this.mReferenceIdString = r3.mReferenceIdString;
            this.constrainedWidth = r3.constrainedWidth;
            this.constrainedHeight = r3.constrainedHeight;
            this.mBarrierAllowsGoneWidgets = r3.mBarrierAllowsGoneWidgets;
            this.mWrapBehavior = r3.mWrapBehavior;
            return;
        L5:
            if (r3.mReferenceIdString != null) goto L7;
            this.mReferenceIds = Arrays.copyOf(r0, r0.length);
            goto L8
        }

        public void fillFromAttributeList(Context r9, AttributeSet r10) {
            TypedArray r92 = r9.obtainStyledAttributes(r10, R$styleable.Layout);
            this.mApply = true;
            int r0 = r92.getIndexCount();
            int r2 = 0;
        L3:
            if (r2 >= r0) goto L79;
            int r3 = r92.getIndex(r2);
            int r4 = sMapToConstant.get(r3);
            switch(r4) {
                case 1: goto L77;
                case 2: goto L76;
                case 3: goto L75;
                case 4: goto L74;
                case 5: goto L73;
                case 6: goto L72;
                case 7: goto L71;
                case 8: goto L70;
                case 9: goto L69;
                case 10: goto L68;
                case 11: goto L67;
                case 12: goto L66;
                case 13: goto L65;
                case 14: goto L64;
                case 15: goto L63;
                case 16: goto L62;
                case 17: goto L61;
                case 18: goto L60;
                case 19: goto L59;
                case 20: goto L58;
                case 21: goto L57;
                case 22: goto L56;
                case 23: goto L55;
                case 24: goto L54;
                case 25: goto L53;
                case 26: goto L52;
                case 27: goto L51;
                case 28: goto L50;
                case 29: goto L49;
                case 30: goto L48;
                case 31: goto L47;
                case 32: goto L46;
                case 33: goto L45;
                case 34: goto L44;
                case 35: goto L43;
                case 36: goto L42;
                case 37: goto L41;
                case 38: goto L40;
                case 39: goto L39;
                case 40: goto L38;
                case 41: goto L37;
                case 42: goto L36;
                default: goto L6;
            };
        L6:
            switch(r4) {
                case 61: goto L35;
                case 62: goto L34;
                case 63: goto L33;
                default: goto L8;
            };
        L33:
            this.circleAngle = r92.getFloat(r3, this.circleAngle);
        L78:
            r2 = r2 + 1;
            goto L3
        L34:
            this.circleRadius = r92.getDimensionPixelSize(r3, this.circleRadius);
            goto L78
        L35:
            this.circleConstraint = ConstraintSet.access$100(r92, r3, this.circleConstraint);
            goto L78
        L8:
            switch(r4) {
                case 69: goto L32;
                case 70: goto L31;
                case 71: goto L30;
                case 72: goto L29;
                case 73: goto L28;
                case 74: goto L27;
                case 75: goto L26;
                case 76: goto L25;
                case 77: goto L24;
                case 78: goto L23;
                case 79: goto L22;
                case 80: goto L21;
                case 81: goto L20;
                case 82: goto L19;
                case 83: goto L18;
                case 84: goto L17;
                case 85: goto L16;
                case 86: goto L15;
                case 87: goto L14;
                case 88: goto L13;
                case 89: goto L12;
                case 90: goto L11;
                case 91: goto L10;
                default: goto L9;
            };
        L9:
            Log.w("ConstraintSet", "Unknown attribute 0x" + Integer.toHexString(r3) + "   " + sMapToConstant.get(r3));
            goto L78
        L10:
            Log.w("ConstraintSet", "unused attribute 0x" + Integer.toHexString(r3) + "   " + sMapToConstant.get(r3));
            goto L78
        L11:
            this.guidelineUseRtl = r92.getBoolean(r3, this.guidelineUseRtl);
            goto L78
        L12:
            this.mConstraintTag = r92.getString(r3);
            goto L78
        L13:
            this.constrainedHeight = r92.getBoolean(r3, this.constrainedHeight);
            goto L78
        L14:
            this.constrainedWidth = r92.getBoolean(r3, this.constrainedWidth);
            goto L78
        L15:
            this.widthMin = r92.getDimensionPixelSize(r3, this.widthMin);
            goto L78
        L16:
            this.heightMin = r92.getDimensionPixelSize(r3, this.heightMin);
            goto L78
        L17:
            this.widthMax = r92.getDimensionPixelSize(r3, this.widthMax);
            goto L78
        L18:
            this.heightMax = r92.getDimensionPixelSize(r3, this.heightMax);
            goto L78
        L19:
            this.heightDefault = r92.getInt(r3, this.heightDefault);
            goto L78
        L20:
            this.widthDefault = r92.getInt(r3, this.widthDefault);
            goto L78
        L21:
            this.baselineMargin = r92.getDimensionPixelSize(r3, this.baselineMargin);
            goto L78
        L22:
            this.goneBaselineMargin = r92.getDimensionPixelSize(r3, this.goneBaselineMargin);
            goto L78
        L23:
            this.baselineToBottom = ConstraintSet.access$100(r92, r3, this.baselineToBottom);
            goto L78
        L24:
            this.baselineToTop = ConstraintSet.access$100(r92, r3, this.baselineToTop);
            goto L78
        L25:
            this.mWrapBehavior = r92.getInt(r3, this.mWrapBehavior);
            goto L78
        L26:
            this.mBarrierAllowsGoneWidgets = r92.getBoolean(r3, this.mBarrierAllowsGoneWidgets);
            goto L78
        L27:
            this.mReferenceIdString = r92.getString(r3);
            goto L78
        L28:
            this.mBarrierMargin = r92.getDimensionPixelSize(r3, this.mBarrierMargin);
            goto L78
        L29:
            this.mBarrierDirection = r92.getInt(r3, this.mBarrierDirection);
            goto L78
        L30:
            Log.e("ConstraintSet", "CURRENTLY UNSUPPORTED");
            goto L78
        L31:
            this.heightPercent = r92.getFloat(r3, 1.0f);
            goto L78
        L32:
            this.widthPercent = r92.getFloat(r3, 1.0f);
            goto L78
        L36:
            ConstraintSet.parseDimensionConstraints(this, r92, r3, 1);
            goto L78
        L37:
            ConstraintSet.parseDimensionConstraints(this, r92, r3, 0);
            goto L78
        L38:
            this.verticalChainStyle = r92.getInt(r3, this.verticalChainStyle);
            goto L78
        L39:
            this.horizontalChainStyle = r92.getInt(r3, this.horizontalChainStyle);
            goto L78
        L40:
            this.verticalWeight = r92.getFloat(r3, this.verticalWeight);
            goto L78
        L41:
            this.horizontalWeight = r92.getFloat(r3, this.horizontalWeight);
            goto L78
        L42:
            this.verticalBias = r92.getFloat(r3, this.verticalBias);
            goto L78
        L43:
            this.topToTop = ConstraintSet.access$100(r92, r3, this.topToTop);
            goto L78
        L44:
            this.topToBottom = ConstraintSet.access$100(r92, r3, this.topToBottom);
            goto L78
        L45:
            this.topMargin = r92.getDimensionPixelSize(r3, this.topMargin);
            goto L78
        L46:
            this.startToStart = ConstraintSet.access$100(r92, r3, this.startToStart);
            goto L78
        L47:
            this.startToEnd = ConstraintSet.access$100(r92, r3, this.startToEnd);
            goto L78
        L48:
            this.startMargin = r92.getDimensionPixelSize(r3, this.startMargin);
            goto L78
        L49:
            this.rightToRight = ConstraintSet.access$100(r92, r3, this.rightToRight);
            goto L78
        L50:
            this.rightToLeft = ConstraintSet.access$100(r92, r3, this.rightToLeft);
            goto L78
        L51:
            this.rightMargin = r92.getDimensionPixelSize(r3, this.rightMargin);
            goto L78
        L52:
            this.orientation = r92.getInt(r3, this.orientation);
            goto L78
        L53:
            this.leftToRight = ConstraintSet.access$100(r92, r3, this.leftToRight);
            goto L78
        L54:
            this.leftToLeft = ConstraintSet.access$100(r92, r3, this.leftToLeft);
            goto L78
        L55:
            this.leftMargin = r92.getDimensionPixelSize(r3, this.leftMargin);
            goto L78
        L56:
            this.mWidth = r92.getLayoutDimension(r3, this.mWidth);
            goto L78
        L57:
            this.mHeight = r92.getLayoutDimension(r3, this.mHeight);
            goto L78
        L58:
            this.horizontalBias = r92.getFloat(r3, this.horizontalBias);
            goto L78
        L59:
            this.guidePercent = r92.getFloat(r3, this.guidePercent);
            goto L78
        L60:
            this.guideEnd = r92.getDimensionPixelOffset(r3, this.guideEnd);
            goto L78
        L61:
            this.guideBegin = r92.getDimensionPixelOffset(r3, this.guideBegin);
            goto L78
        L62:
            this.goneTopMargin = r92.getDimensionPixelSize(r3, this.goneTopMargin);
            goto L78
        L63:
            this.goneStartMargin = r92.getDimensionPixelSize(r3, this.goneStartMargin);
            goto L78
        L64:
            this.goneRightMargin = r92.getDimensionPixelSize(r3, this.goneRightMargin);
            goto L78
        L65:
            this.goneLeftMargin = r92.getDimensionPixelSize(r3, this.goneLeftMargin);
            goto L78
        L66:
            this.goneEndMargin = r92.getDimensionPixelSize(r3, this.goneEndMargin);
            goto L78
        L67:
            this.goneBottomMargin = r92.getDimensionPixelSize(r3, this.goneBottomMargin);
            goto L78
        L68:
            this.endToStart = ConstraintSet.access$100(r92, r3, this.endToStart);
            goto L78
        L69:
            this.endToEnd = ConstraintSet.access$100(r92, r3, this.endToEnd);
            goto L78
        L70:
            this.endMargin = r92.getDimensionPixelSize(r3, this.endMargin);
            goto L78
        L71:
            this.editorAbsoluteY = r92.getDimensionPixelOffset(r3, this.editorAbsoluteY);
            goto L78
        L72:
            this.editorAbsoluteX = r92.getDimensionPixelOffset(r3, this.editorAbsoluteX);
            goto L78
        L73:
            this.dimensionRatio = r92.getString(r3);
            goto L78
        L74:
            this.bottomToTop = ConstraintSet.access$100(r92, r3, this.bottomToTop);
            goto L78
        L75:
            this.bottomToBottom = ConstraintSet.access$100(r92, r3, this.bottomToBottom);
            goto L78
        L76:
            this.bottomMargin = r92.getDimensionPixelSize(r3, this.bottomMargin);
            goto L78
        L77:
            this.baselineToBaseline = ConstraintSet.access$100(r92, r3, this.baselineToBaseline);
            goto L78
        L79:
            r92.recycle();
        }
    }

    public static class Motion {
        private static SparseIntArray sMapToConstant;
        public int mAnimateCircleAngleTo;
        public int mAnimateRelativeTo;
        public boolean mApply;
        public int mDrawPath;
        public float mMotionStagger;
        public int mPathMotionArc;
        public float mPathRotate;
        public int mPolarRelativeTo;
        public int mQuantizeInterpolatorID;
        public String mQuantizeInterpolatorString;
        public int mQuantizeInterpolatorType;
        public float mQuantizeMotionPhase;
        public int mQuantizeMotionSteps;
        public String mTransitionEasing;

        static {
            SparseIntArray r0 = new SparseIntArray();
            sMapToConstant = r0;
            r0.append(R$styleable.Motion_motionPathRotate, 1);
            sMapToConstant.append(R$styleable.Motion_pathMotionArc, 2);
            sMapToConstant.append(R$styleable.Motion_transitionEasing, 3);
            sMapToConstant.append(R$styleable.Motion_drawPath, 4);
            sMapToConstant.append(R$styleable.Motion_animateRelativeTo, 5);
            sMapToConstant.append(R$styleable.Motion_animateCircleAngleTo, 6);
            sMapToConstant.append(R$styleable.Motion_motionStagger, 7);
            sMapToConstant.append(R$styleable.Motion_quantizeMotionSteps, 8);
            sMapToConstant.append(R$styleable.Motion_quantizeMotionPhase, 9);
            sMapToConstant.append(R$styleable.Motion_quantizeMotionInterpolator, 10);
        }

        public Motion() {
            this.mApply = false;
            this.mAnimateRelativeTo = -1;
            this.mAnimateCircleAngleTo = 0;
            this.mTransitionEasing = null;
            this.mPathMotionArc = -1;
            this.mDrawPath = 0;
            this.mMotionStagger = Float.NaN;
            this.mPolarRelativeTo = -1;
            this.mPathRotate = Float.NaN;
            this.mQuantizeMotionPhase = Float.NaN;
            this.mQuantizeMotionSteps = -1;
            this.mQuantizeInterpolatorString = null;
            this.mQuantizeInterpolatorType = -3;
            this.mQuantizeInterpolatorID = -1;
        }

        public void copyFrom(Motion r2) {
            this.mApply = r2.mApply;
            this.mAnimateRelativeTo = r2.mAnimateRelativeTo;
            this.mTransitionEasing = r2.mTransitionEasing;
            this.mPathMotionArc = r2.mPathMotionArc;
            this.mDrawPath = r2.mDrawPath;
            this.mPathRotate = r2.mPathRotate;
            this.mMotionStagger = r2.mMotionStagger;
            this.mPolarRelativeTo = r2.mPolarRelativeTo;
        }

        public void fillFromAttributeList(Context r9, AttributeSet r10) {
            TypedArray r92 = r9.obtainStyledAttributes(r10, R$styleable.Motion);
            this.mApply = true;
            int r0 = r92.getIndexCount();
            int r2 = 0;
        L3:
            if (r2 >= r0) goto L31;
            int r3 = r92.getIndex(r2);
            switch(sMapToConstant.get(r3)) {
                case 1: goto L29;
                case 2: goto L28;
                case 3: goto L25;
                case 4: goto L23;
                case 5: goto L22;
                case 6: goto L21;
                case 7: goto L20;
                case 8: goto L19;
                case 9: goto L18;
                case 10: goto L7;
                default: goto L30;
            };
        L7:
            int r4 = r92.peekValue(r3).type;
            if (r4 != 1) goto L12;
            int r32 = r92.getResourceId(r3, -1);
            this.mQuantizeInterpolatorID = r32;
            if (r32 == (-1)) goto L30;
            this.mQuantizeInterpolatorType = -2;
            goto L30
        L12:
            if (r4 != 3) goto L17;
            String r42 = r92.getString(r3);
            this.mQuantizeInterpolatorString = r42;
            if (r42.indexOf("/") <= 0) goto L16;
            this.mQuantizeInterpolatorID = r92.getResourceId(r3, -1);
            this.mQuantizeInterpolatorType = -2;
            goto L30
        L16:
            this.mQuantizeInterpolatorType = -1;
            goto L30
        L17:
            this.mQuantizeInterpolatorType = r92.getInteger(r3, this.mQuantizeInterpolatorID);
            goto L30
        L18:
            this.mQuantizeMotionPhase = r92.getFloat(r3, this.mQuantizeMotionPhase);
            goto L30
        L19:
            this.mQuantizeMotionSteps = r92.getInteger(r3, this.mQuantizeMotionSteps);
            goto L30
        L20:
            this.mMotionStagger = r92.getFloat(r3, this.mMotionStagger);
            goto L30
        L21:
            this.mAnimateCircleAngleTo = r92.getInteger(r3, this.mAnimateCircleAngleTo);
            goto L30
        L22:
            this.mAnimateRelativeTo = ConstraintSet.access$100(r92, r3, this.mAnimateRelativeTo);
            goto L30
        L23:
            this.mDrawPath = r92.getInt(r3, 0);
            goto L30
        L28:
            this.mPathMotionArc = r92.getInt(r3, this.mPathMotionArc);
            goto L30
        L29:
            this.mPathRotate = r92.getFloat(r3, this.mPathRotate);
            goto L30
        L25:
            if (r92.peekValue(r3).type != 3) goto L27;
            this.mTransitionEasing = r92.getString(r3);
            goto L30
        L27:
            this.mTransitionEasing = Easing.NAMED_EASING[r92.getInteger(r3, 0)];
        L30:
            r2 = r2 + 1;
            goto L3
        L31:
            r92.recycle();
        }
    }

    public static class PropertySet {
        public float alpha;
        public boolean mApply;
        public float mProgress;
        public int mVisibilityMode;
        public int visibility;

        public PropertySet() {
            this.mApply = false;
            this.visibility = 0;
            this.mVisibilityMode = 0;
            this.alpha = 1.0f;
            this.mProgress = Float.NaN;
        }

        public void copyFrom(PropertySet r2) {
            this.mApply = r2.mApply;
            this.visibility = r2.visibility;
            this.alpha = r2.alpha;
            this.mProgress = r2.mProgress;
            this.mVisibilityMode = r2.mVisibilityMode;
        }

        public void fillFromAttributeList(Context r4, AttributeSet r5) {
            TypedArray r42 = r4.obtainStyledAttributes(r5, R$styleable.PropertySet);
            this.mApply = true;
            int r52 = r42.getIndexCount();
            int r0 = 0;
        L3:
            if (r0 >= r52) goto L17;
            int r1 = r42.getIndex(r0);
            if (r1 != R$styleable.PropertySet_android_alpha) goto L8;
            this.alpha = r42.getFloat(r1, this.alpha);
        L16:
            r0 = r0 + 1;
            goto L3
        L8:
            if (r1 != R$styleable.PropertySet_android_visibility) goto L11;
            this.visibility = r42.getInt(r1, this.visibility);
            this.visibility = ConstraintSet.access$200()[this.visibility];
            goto L16
        L11:
            if (r1 != R$styleable.PropertySet_visibilityMode) goto L14;
            this.mVisibilityMode = r42.getInt(r1, this.mVisibilityMode);
            goto L16
        L14:
            if (r1 != R$styleable.PropertySet_motionProgress) goto L16;
            this.mProgress = r42.getFloat(r1, this.mProgress);
            goto L16
        L17:
            r42.recycle();
        }
    }

    public static class Transform {
        private static SparseIntArray sMapToConstant;
        public boolean applyElevation;
        public float elevation;
        public boolean mApply;
        public float rotation;
        public float rotationX;
        public float rotationY;
        public float scaleX;
        public float scaleY;
        public int transformPivotTarget;
        public float transformPivotX;
        public float transformPivotY;
        public float translationX;
        public float translationY;
        public float translationZ;

        static {
            SparseIntArray r0 = new SparseIntArray();
            sMapToConstant = r0;
            r0.append(R$styleable.Transform_android_rotation, 1);
            sMapToConstant.append(R$styleable.Transform_android_rotationX, 2);
            sMapToConstant.append(R$styleable.Transform_android_rotationY, 3);
            sMapToConstant.append(R$styleable.Transform_android_scaleX, 4);
            sMapToConstant.append(R$styleable.Transform_android_scaleY, 5);
            sMapToConstant.append(R$styleable.Transform_android_transformPivotX, 6);
            sMapToConstant.append(R$styleable.Transform_android_transformPivotY, 7);
            sMapToConstant.append(R$styleable.Transform_android_translationX, 8);
            sMapToConstant.append(R$styleable.Transform_android_translationY, 9);
            sMapToConstant.append(R$styleable.Transform_android_translationZ, 10);
            sMapToConstant.append(R$styleable.Transform_android_elevation, 11);
            sMapToConstant.append(R$styleable.Transform_transformPivotTarget, 12);
        }

        public Transform() {
            this.mApply = false;
            this.rotation = 0.0f;
            this.rotationX = 0.0f;
            this.rotationY = 0.0f;
            this.scaleX = 1.0f;
            this.scaleY = 1.0f;
            this.transformPivotX = Float.NaN;
            this.transformPivotY = Float.NaN;
            this.transformPivotTarget = -1;
            this.translationX = 0.0f;
            this.translationY = 0.0f;
            this.translationZ = 0.0f;
            this.applyElevation = false;
            this.elevation = 0.0f;
        }

        public void copyFrom(Transform r2) {
            this.mApply = r2.mApply;
            this.rotation = r2.rotation;
            this.rotationX = r2.rotationX;
            this.rotationY = r2.rotationY;
            this.scaleX = r2.scaleX;
            this.scaleY = r2.scaleY;
            this.transformPivotX = r2.transformPivotX;
            this.transformPivotY = r2.transformPivotY;
            this.transformPivotTarget = r2.transformPivotTarget;
            this.translationX = r2.translationX;
            this.translationY = r2.translationY;
            this.translationZ = r2.translationZ;
            this.applyElevation = r2.applyElevation;
            this.elevation = r2.elevation;
        }

        public void fillFromAttributeList(Context r5, AttributeSet r6) {
            TypedArray r52 = r5.obtainStyledAttributes(r6, R$styleable.Transform);
            this.mApply = true;
            int r0 = r52.getIndexCount();
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L20;
            int r2 = r52.getIndex(r1);
            switch(sMapToConstant.get(r2)) {
                case 1: goto L18;
                case 2: goto L17;
                case 3: goto L16;
                case 4: goto L15;
                case 5: goto L14;
                case 6: goto L13;
                case 7: goto L12;
                case 8: goto L11;
                case 9: goto L10;
                case 10: goto L9;
                case 11: goto L8;
                case 12: goto L7;
                default: goto L19;
            };
        L7:
            this.transformPivotTarget = ConstraintSet.access$100(r52, r2, this.transformPivotTarget);
            goto L19
        L8:
            this.applyElevation = true;
            this.elevation = r52.getDimension(r2, this.elevation);
            goto L19
        L9:
            this.translationZ = r52.getDimension(r2, this.translationZ);
            goto L19
        L10:
            this.translationY = r52.getDimension(r2, this.translationY);
            goto L19
        L11:
            this.translationX = r52.getDimension(r2, this.translationX);
            goto L19
        L12:
            this.transformPivotY = r52.getDimension(r2, this.transformPivotY);
            goto L19
        L13:
            this.transformPivotX = r52.getDimension(r2, this.transformPivotX);
            goto L19
        L14:
            this.scaleY = r52.getFloat(r2, this.scaleY);
            goto L19
        L15:
            this.scaleX = r52.getFloat(r2, this.scaleX);
            goto L19
        L16:
            this.rotationY = r52.getFloat(r2, this.rotationY);
            goto L19
        L17:
            this.rotationX = r52.getFloat(r2, this.rotationX);
            goto L19
        L18:
            this.rotation = r52.getFloat(r2, this.rotation);
        L19:
            r1 = r1 + 1;
            goto L3
        L20:
            r52.recycle();
        }
    }

    static {
        VISIBILITY_FLAGS = new int[]{0, 4, 8};
        sMapToConstant = new SparseIntArray();
        sOverrideMapToConstant = new SparseIntArray();
        sMapToConstant.append(R$styleable.Constraint_layout_constraintLeft_toLeftOf, 25);
        sMapToConstant.append(R$styleable.Constraint_layout_constraintLeft_toRightOf, 26);
        sMapToConstant.append(R$styleable.Constraint_layout_constraintRight_toLeftOf, 29);
        sMapToConstant.append(R$styleable.Constraint_layout_constraintRight_toRightOf, 30);
        sMapToConstant.append(R$styleable.Constraint_layout_constraintTop_toTopOf, 36);
        sMapToConstant.append(R$styleable.Constraint_layout_constraintTop_toBottomOf, 35);
        sMapToConstant.append(R$styleable.Constraint_layout_constraintBottom_toTopOf, 4);
        sMapToConstant.append(R$styleable.Constraint_layout_constraintBottom_toBottomOf, 3);
        sMapToConstant.append(R$styleable.Constraint_layout_constraintBaseline_toBaselineOf, 1);
        sMapToConstant.append(R$styleable.Constraint_layout_constraintBaseline_toTopOf, 91);
        sMapToConstant.append(R$styleable.Constraint_layout_constraintBaseline_toBottomOf, 92);
        sMapToConstant.append(R$styleable.Constraint_layout_editor_absoluteX, 6);
        sMapToConstant.append(R$styleable.Constraint_layout_editor_absoluteY, 7);
        sMapToConstant.append(R$styleable.Constraint_layout_constraintGuide_begin, 17);
        sMapToConstant.append(R$styleable.Constraint_layout_constraintGuide_end, 18);
        sMapToConstant.append(R$styleable.Constraint_layout_constraintGuide_percent, 19);
        sMapToConstant.append(R$styleable.Constraint_guidelineUseRtl, 99);
        sMapToConstant.append(R$styleable.Constraint_android_orientation, 27);
        sMapToConstant.append(R$styleable.Constraint_layout_constraintStart_toEndOf, 32);
        sMapToConstant.append(R$styleable.Constraint_layout_constraintStart_toStartOf, 33);
        sMapToConstant.append(R$styleable.Constraint_layout_constraintEnd_toStartOf, 10);
        sMapToConstant.append(R$styleable.Constraint_layout_constraintEnd_toEndOf, 9);
        sMapToConstant.append(R$styleable.Constraint_layout_goneMarginLeft, 13);
        sMapToConstant.append(R$styleable.Constraint_layout_goneMarginTop, 16);
        sMapToConstant.append(R$styleable.Constraint_layout_goneMarginRight, 14);
        sMapToConstant.append(R$styleable.Constraint_layout_goneMarginBottom, 11);
        sMapToConstant.append(R$styleable.Constraint_layout_goneMarginStart, 15);
        sMapToConstant.append(R$styleable.Constraint_layout_goneMarginEnd, 12);
        sMapToConstant.append(R$styleable.Constraint_layout_constraintVertical_weight, 40);
        sMapToConstant.append(R$styleable.Constraint_layout_constraintHorizontal_weight, 39);
        sMapToConstant.append(R$styleable.Constraint_layout_constraintHorizontal_chainStyle, 41);
        sMapToConstant.append(R$styleable.Constraint_layout_constraintVertical_chainStyle, 42);
        sMapToConstant.append(R$styleable.Constraint_layout_constraintHorizontal_bias, 20);
        sMapToConstant.append(R$styleable.Constraint_layout_constraintVertical_bias, 37);
        sMapToConstant.append(R$styleable.Constraint_layout_constraintDimensionRatio, 5);
        sMapToConstant.append(R$styleable.Constraint_layout_constraintLeft_creator, 87);
        sMapToConstant.append(R$styleable.Constraint_layout_constraintTop_creator, 87);
        sMapToConstant.append(R$styleable.Constraint_layout_constraintRight_creator, 87);
        sMapToConstant.append(R$styleable.Constraint_layout_constraintBottom_creator, 87);
        sMapToConstant.append(R$styleable.Constraint_layout_constraintBaseline_creator, 87);
        sMapToConstant.append(R$styleable.Constraint_android_layout_marginLeft, 24);
        sMapToConstant.append(R$styleable.Constraint_android_layout_marginRight, 28);
        sMapToConstant.append(R$styleable.Constraint_android_layout_marginStart, 31);
        sMapToConstant.append(R$styleable.Constraint_android_layout_marginEnd, 8);
        sMapToConstant.append(R$styleable.Constraint_android_layout_marginTop, 34);
        sMapToConstant.append(R$styleable.Constraint_android_layout_marginBottom, 2);
        sMapToConstant.append(R$styleable.Constraint_android_layout_width, 23);
        sMapToConstant.append(R$styleable.Constraint_android_layout_height, 21);
        sMapToConstant.append(R$styleable.Constraint_layout_constraintWidth, 95);
        sMapToConstant.append(R$styleable.Constraint_layout_constraintHeight, 96);
        sMapToConstant.append(R$styleable.Constraint_android_visibility, 22);
        sMapToConstant.append(R$styleable.Constraint_android_alpha, 43);
        sMapToConstant.append(R$styleable.Constraint_android_elevation, 44);
        sMapToConstant.append(R$styleable.Constraint_android_rotationX, 45);
        sMapToConstant.append(R$styleable.Constraint_android_rotationY, 46);
        sMapToConstant.append(R$styleable.Constraint_android_rotation, 60);
        sMapToConstant.append(R$styleable.Constraint_android_scaleX, 47);
        sMapToConstant.append(R$styleable.Constraint_android_scaleY, 48);
        sMapToConstant.append(R$styleable.Constraint_android_transformPivotX, 49);
        sMapToConstant.append(R$styleable.Constraint_android_transformPivotY, 50);
        sMapToConstant.append(R$styleable.Constraint_android_translationX, 51);
        sMapToConstant.append(R$styleable.Constraint_android_translationY, 52);
        sMapToConstant.append(R$styleable.Constraint_android_translationZ, 53);
        sMapToConstant.append(R$styleable.Constraint_layout_constraintWidth_default, 54);
        sMapToConstant.append(R$styleable.Constraint_layout_constraintHeight_default, 55);
        sMapToConstant.append(R$styleable.Constraint_layout_constraintWidth_max, 56);
        sMapToConstant.append(R$styleable.Constraint_layout_constraintHeight_max, 57);
        sMapToConstant.append(R$styleable.Constraint_layout_constraintWidth_min, 58);
        sMapToConstant.append(R$styleable.Constraint_layout_constraintHeight_min, 59);
        sMapToConstant.append(R$styleable.Constraint_layout_constraintCircle, 61);
        sMapToConstant.append(R$styleable.Constraint_layout_constraintCircleRadius, 62);
        sMapToConstant.append(R$styleable.Constraint_layout_constraintCircleAngle, 63);
        sMapToConstant.append(R$styleable.Constraint_animateRelativeTo, 64);
        sMapToConstant.append(R$styleable.Constraint_transitionEasing, 65);
        sMapToConstant.append(R$styleable.Constraint_drawPath, 66);
        sMapToConstant.append(R$styleable.Constraint_transitionPathRotate, 67);
        sMapToConstant.append(R$styleable.Constraint_motionStagger, 79);
        sMapToConstant.append(R$styleable.Constraint_android_id, 38);
        sMapToConstant.append(R$styleable.Constraint_motionProgress, 68);
        sMapToConstant.append(R$styleable.Constraint_layout_constraintWidth_percent, 69);
        sMapToConstant.append(R$styleable.Constraint_layout_constraintHeight_percent, 70);
        sMapToConstant.append(R$styleable.Constraint_layout_wrapBehaviorInParent, 97);
        sMapToConstant.append(R$styleable.Constraint_chainUseRtl, 71);
        sMapToConstant.append(R$styleable.Constraint_barrierDirection, 72);
        sMapToConstant.append(R$styleable.Constraint_barrierMargin, 73);
        sMapToConstant.append(R$styleable.Constraint_constraint_referenced_ids, 74);
        sMapToConstant.append(R$styleable.Constraint_barrierAllowsGoneWidgets, 75);
        sMapToConstant.append(R$styleable.Constraint_pathMotionArc, 76);
        sMapToConstant.append(R$styleable.Constraint_layout_constraintTag, 77);
        sMapToConstant.append(R$styleable.Constraint_visibilityMode, 78);
        sMapToConstant.append(R$styleable.Constraint_layout_constrainedWidth, 80);
        sMapToConstant.append(R$styleable.Constraint_layout_constrainedHeight, 81);
        sMapToConstant.append(R$styleable.Constraint_polarRelativeTo, 82);
        sMapToConstant.append(R$styleable.Constraint_transformPivotTarget, 83);
        sMapToConstant.append(R$styleable.Constraint_quantizeMotionSteps, 84);
        sMapToConstant.append(R$styleable.Constraint_quantizeMotionPhase, 85);
        sMapToConstant.append(R$styleable.Constraint_quantizeMotionInterpolator, 86);
        sOverrideMapToConstant.append(R$styleable.ConstraintOverride_layout_editor_absoluteY, 6);
        sOverrideMapToConstant.append(R$styleable.ConstraintOverride_layout_editor_absoluteY, 7);
        sOverrideMapToConstant.append(R$styleable.ConstraintOverride_android_orientation, 27);
        sOverrideMapToConstant.append(R$styleable.ConstraintOverride_layout_goneMarginLeft, 13);
        sOverrideMapToConstant.append(R$styleable.ConstraintOverride_layout_goneMarginTop, 16);
        sOverrideMapToConstant.append(R$styleable.ConstraintOverride_layout_goneMarginRight, 14);
        sOverrideMapToConstant.append(R$styleable.ConstraintOverride_layout_goneMarginBottom, 11);
        sOverrideMapToConstant.append(R$styleable.ConstraintOverride_layout_goneMarginStart, 15);
        sOverrideMapToConstant.append(R$styleable.ConstraintOverride_layout_goneMarginEnd, 12);
        sOverrideMapToConstant.append(R$styleable.ConstraintOverride_layout_constraintVertical_weight, 40);
        sOverrideMapToConstant.append(R$styleable.ConstraintOverride_layout_constraintHorizontal_weight, 39);
        sOverrideMapToConstant.append(R$styleable.ConstraintOverride_layout_constraintHorizontal_chainStyle, 41);
        sOverrideMapToConstant.append(R$styleable.ConstraintOverride_layout_constraintVertical_chainStyle, 42);
        sOverrideMapToConstant.append(R$styleable.ConstraintOverride_layout_constraintHorizontal_bias, 20);
        sOverrideMapToConstant.append(R$styleable.ConstraintOverride_layout_constraintVertical_bias, 37);
        sOverrideMapToConstant.append(R$styleable.ConstraintOverride_layout_constraintDimensionRatio, 5);
        sOverrideMapToConstant.append(R$styleable.ConstraintOverride_layout_constraintLeft_creator, 87);
        sOverrideMapToConstant.append(R$styleable.ConstraintOverride_layout_constraintTop_creator, 87);
        sOverrideMapToConstant.append(R$styleable.ConstraintOverride_layout_constraintRight_creator, 87);
        sOverrideMapToConstant.append(R$styleable.ConstraintOverride_layout_constraintBottom_creator, 87);
        sOverrideMapToConstant.append(R$styleable.ConstraintOverride_layout_constraintBaseline_creator, 87);
        sOverrideMapToConstant.append(R$styleable.ConstraintOverride_android_layout_marginLeft, 24);
        sOverrideMapToConstant.append(R$styleable.ConstraintOverride_android_layout_marginRight, 28);
        sOverrideMapToConstant.append(R$styleable.ConstraintOverride_android_layout_marginStart, 31);
        sOverrideMapToConstant.append(R$styleable.ConstraintOverride_android_layout_marginEnd, 8);
        sOverrideMapToConstant.append(R$styleable.ConstraintOverride_android_layout_marginTop, 34);
        sOverrideMapToConstant.append(R$styleable.ConstraintOverride_android_layout_marginBottom, 2);
        sOverrideMapToConstant.append(R$styleable.ConstraintOverride_android_layout_width, 23);
        sOverrideMapToConstant.append(R$styleable.ConstraintOverride_android_layout_height, 21);
        sOverrideMapToConstant.append(R$styleable.ConstraintOverride_layout_constraintWidth, 95);
        sOverrideMapToConstant.append(R$styleable.ConstraintOverride_layout_constraintHeight, 96);
        sOverrideMapToConstant.append(R$styleable.ConstraintOverride_android_visibility, 22);
        sOverrideMapToConstant.append(R$styleable.ConstraintOverride_android_alpha, 43);
        sOverrideMapToConstant.append(R$styleable.ConstraintOverride_android_elevation, 44);
        sOverrideMapToConstant.append(R$styleable.ConstraintOverride_android_rotationX, 45);
        sOverrideMapToConstant.append(R$styleable.ConstraintOverride_android_rotationY, 46);
        sOverrideMapToConstant.append(R$styleable.ConstraintOverride_android_rotation, 60);
        sOverrideMapToConstant.append(R$styleable.ConstraintOverride_android_scaleX, 47);
        sOverrideMapToConstant.append(R$styleable.ConstraintOverride_android_scaleY, 48);
        sOverrideMapToConstant.append(R$styleable.ConstraintOverride_android_transformPivotX, 49);
        sOverrideMapToConstant.append(R$styleable.ConstraintOverride_android_transformPivotY, 50);
        sOverrideMapToConstant.append(R$styleable.ConstraintOverride_android_translationX, 51);
        sOverrideMapToConstant.append(R$styleable.ConstraintOverride_android_translationY, 52);
        sOverrideMapToConstant.append(R$styleable.ConstraintOverride_android_translationZ, 53);
        sOverrideMapToConstant.append(R$styleable.ConstraintOverride_layout_constraintWidth_default, 54);
        sOverrideMapToConstant.append(R$styleable.ConstraintOverride_layout_constraintHeight_default, 55);
        sOverrideMapToConstant.append(R$styleable.ConstraintOverride_layout_constraintWidth_max, 56);
        sOverrideMapToConstant.append(R$styleable.ConstraintOverride_layout_constraintHeight_max, 57);
        sOverrideMapToConstant.append(R$styleable.ConstraintOverride_layout_constraintWidth_min, 58);
        sOverrideMapToConstant.append(R$styleable.ConstraintOverride_layout_constraintHeight_min, 59);
        sOverrideMapToConstant.append(R$styleable.ConstraintOverride_layout_constraintCircleRadius, 62);
        sOverrideMapToConstant.append(R$styleable.ConstraintOverride_layout_constraintCircleAngle, 63);
        sOverrideMapToConstant.append(R$styleable.ConstraintOverride_animateRelativeTo, 64);
        sOverrideMapToConstant.append(R$styleable.ConstraintOverride_transitionEasing, 65);
        sOverrideMapToConstant.append(R$styleable.ConstraintOverride_drawPath, 66);
        sOverrideMapToConstant.append(R$styleable.ConstraintOverride_transitionPathRotate, 67);
        sOverrideMapToConstant.append(R$styleable.ConstraintOverride_motionStagger, 79);
        sOverrideMapToConstant.append(R$styleable.ConstraintOverride_android_id, 38);
        sOverrideMapToConstant.append(R$styleable.ConstraintOverride_motionTarget, 98);
        sOverrideMapToConstant.append(R$styleable.ConstraintOverride_motionProgress, 68);
        sOverrideMapToConstant.append(R$styleable.ConstraintOverride_layout_constraintWidth_percent, 69);
        sOverrideMapToConstant.append(R$styleable.ConstraintOverride_layout_constraintHeight_percent, 70);
        sOverrideMapToConstant.append(R$styleable.ConstraintOverride_chainUseRtl, 71);
        sOverrideMapToConstant.append(R$styleable.ConstraintOverride_barrierDirection, 72);
        sOverrideMapToConstant.append(R$styleable.ConstraintOverride_barrierMargin, 73);
        sOverrideMapToConstant.append(R$styleable.ConstraintOverride_constraint_referenced_ids, 74);
        sOverrideMapToConstant.append(R$styleable.ConstraintOverride_barrierAllowsGoneWidgets, 75);
        sOverrideMapToConstant.append(R$styleable.ConstraintOverride_pathMotionArc, 76);
        sOverrideMapToConstant.append(R$styleable.ConstraintOverride_layout_constraintTag, 77);
        sOverrideMapToConstant.append(R$styleable.ConstraintOverride_visibilityMode, 78);
        sOverrideMapToConstant.append(R$styleable.ConstraintOverride_layout_constrainedWidth, 80);
        sOverrideMapToConstant.append(R$styleable.ConstraintOverride_layout_constrainedHeight, 81);
        sOverrideMapToConstant.append(R$styleable.ConstraintOverride_polarRelativeTo, 82);
        sOverrideMapToConstant.append(R$styleable.ConstraintOverride_transformPivotTarget, 83);
        sOverrideMapToConstant.append(R$styleable.ConstraintOverride_quantizeMotionSteps, 84);
        sOverrideMapToConstant.append(R$styleable.ConstraintOverride_quantizeMotionPhase, 85);
        sOverrideMapToConstant.append(R$styleable.ConstraintOverride_quantizeMotionInterpolator, 86);
        sOverrideMapToConstant.append(R$styleable.ConstraintOverride_layout_wrapBehaviorInParent, 97);
    }

    public ConstraintSet() {
        this.derivedState = _UrlKt.FRAGMENT_ENCODE_SET;
        this.mMatchLabels = new String[0];
        this.mRotate = 0;
        this.mSavedAttributes = new HashMap();
        this.mForceId = true;
        this.mConstraints = new HashMap();
    }

    public static /* synthetic */ int access$100(TypedArray r0, int r1, int r2) {
        return lookupID(r0, r1, r2);
    }

    public static /* synthetic */ int[] access$200() {
        return VISIBILITY_FLAGS;
    }

    private int[] convertReferenceString(View r9, String r10) {
        String[] r8 = r10.split(",");
        Context r102 = r9.getContext();
        int[] r0 = new int[r8.length];
        int r2 = 0;
        int r3 = 0;
    L4:
        if (r2 >= r8.length) goto L23;
        String r4 = r8[r2].trim();
        int r5 = R$id.class.getField(r4).getInt(null);     // Catch: Exception -> L8
    L9:
        if (r5 != 0) goto L11;
        r5 = r102.getResources().getIdentifier(r4, "id", r102.getPackageName());
    L11:
        if (r5 != 0) goto L21;
        if (r9.isInEditMode() == false) goto L21;
        if ((r9.getParent() instanceof ConstraintLayout) == false) goto L21;
        Object r42 = ((ConstraintLayout) r9.getParent()).getDesignInformation(0, r4);
        if (r42 == null) goto L21;
        if ((r42 instanceof Integer) == false) goto L21;
        r5 = ((Integer) r42).intValue();
    L21:
        r0[r3] = r5;
        r2 = r2 + 1;
        r3 = r3 + 1;
    L8:
        r5 = 0;
        goto L9
    L23:
        if (r3 != r8.length) goto L25;
        return r0;
    L25:
        return Arrays.copyOf(r0, r3);
    }

    private Constraint fillFromAttributeList(Context r3, AttributeSet r4, boolean r5) {
        Constraint r0 = new Constraint();
        if (r5 == false) goto L5;
        int[] r1 = R$styleable.ConstraintOverride;
    L6:
        TypedArray r32 = r3.obtainStyledAttributes(r4, r1);
        populateConstraint(r0, r32, r5);
        r32.recycle();
        return r0;
    L5:
        r1 = R$styleable.Constraint;
        goto L6
    }

    private Constraint get(int r4) {
        if (this.mConstraints.containsKey(Integer.valueOf(r4)) == true) goto L6;
        this.mConstraints.put(Integer.valueOf(r4), new Constraint());
    L6:
        return this.mConstraints.get(Integer.valueOf(r4));
    }

    private static int lookupID(TypedArray r1, int r2, int r3) {
        int r32 = r1.getResourceId(r2, r3);
        if (r32 == (-1)) goto L5;
        return r32;
    L5:
        return r1.getInt(r2, -1);
    }

    public static void parseDimensionConstraints(Object r4, TypedArray r5, int r6, int r7) {
        if (r4 == null) goto L46;
        int r0 = r5.peekValue(r6).type;
        if (r0 == 3) goto L43;
        int r2 = 0;
        if (r0 == 5) goto L18;
        int r52 = r5.getInt(r6, 0);
        if (r52 != (-4)) goto L11;
        boolean r53 = true;
        r2 = -2;
    L20:
        if ((r4 instanceof ConstraintLayout.LayoutParams) == false) goto L28;
        ConstraintLayout.LayoutParams r42 = (ConstraintLayout.LayoutParams) r4;
        if (r7 != 0) goto L25;
        ((ViewGroup.MarginLayoutParams) r42).width = r2;
        r42.constrainedWidth = r53;
        return;
    L25:
        ((ViewGroup.MarginLayoutParams) r42).height = r2;
        r42.constrainedHeight = r53;
        return;
    L28:
        if ((r4 instanceof Layout) == false) goto L36;
        Layout r43 = (Layout) r4;
        if (r7 != 0) goto L33;
        r43.mWidth = r2;
        r43.constrainedWidth = r53;
        return;
    L33:
        r43.mHeight = r2;
        r43.constrainedHeight = r53;
        return;
    L36:
        if ((r4 instanceof Constraint.Delta) == false) goto L45;
        Constraint.Delta r44 = (Constraint.Delta) r4;
        if (r7 != 0) goto L41;
        r44.add(23, r2);
        r44.add(80, r53);
        return;
    L41:
        r44.add(21, r2);
        r44.add(81, r53);
        return;
    L45:
        return;
    L11:
        if (r52 == (-3)) goto L15;
        if (r52 != (-2)) goto L14;
    L16:
        r2 = r52;
        r53 = false;
        goto L20
    L14:
        if (r52 == (-1)) goto L16;
    L15:
        r53 = false;
        goto L20
    L18:
        r52 = r5.getDimensionPixelSize(r6, 0);
        goto L16
    L43:
        parseDimensionConstraintsString(r4, r5.getString(r6), r7);
        return;
    }

    public static void parseDimensionConstraintsString(Object r5, String r6, int r7) {
        if (r6 == null) goto L89;
        int r0 = r6.indexOf(61);
        int r1 = r6.length();
        if (r0 > 0) goto L7;
        return;
    L7:
        if (r0 >= (r1 - 1)) goto L83;
        String r2 = r6.substring(0, r0);
        String r62 = r6.substring(r0 + 1);
        if (r62.length() <= 0) goto L84;
        String r02 = r2.trim();
        String r63 = r62.trim();
        if ("ratio".equalsIgnoreCase(r02) == false) goto L81;
        if ((r5 instanceof ConstraintLayout.LayoutParams) == false) goto L21;
        ConstraintLayout.LayoutParams r52 = (ConstraintLayout.LayoutParams) r5;
        if (r7 != 0) goto L17;
        ((ViewGroup.MarginLayoutParams) r52).width = 0;
    L18:
        parseDimensionRatioString(r52, r63);
        return;
    L17:
        ((ViewGroup.MarginLayoutParams) r52).height = 0;
        goto L18
    L21:
        if ((r5 instanceof Layout) == false) goto L25;
        ((Layout) r5).dimensionRatio = r63;
        return;
    L25:
        if ((r5 instanceof Constraint.Delta) == false) goto L85;
        ((Constraint.Delta) r5).add(5, r63);
        return;
    L85:
        return;
    L81:
        if ("weight".equalsIgnoreCase(r02) == false) goto L55;
        float r64 = Float.parseFloat(r63);     // Catch: NumberFormatException -> L80
        if ((r5 instanceof ConstraintLayout.LayoutParams) == false) goto L39;
        ConstraintLayout.LayoutParams r53 = (ConstraintLayout.LayoutParams) r5;     // Catch: NumberFormatException -> L80
        if (r7 != 0) goto L36;
        ((ViewGroup.MarginLayoutParams) r53).width = 0;     // Catch: NumberFormatException -> L80
        r53.horizontalWeight = r64;     // Catch: NumberFormatException -> L80
        return;
    L36:
        ((ViewGroup.MarginLayoutParams) r53).height = 0;     // Catch: NumberFormatException -> L80
        r53.verticalWeight = r64;     // Catch: NumberFormatException -> L80
        return;
    L39:
        if ((r5 instanceof Layout) == false) goto L47;
        Layout r54 = (Layout) r5;     // Catch: NumberFormatException -> L80
        if (r7 != 0) goto L44;
        r54.mWidth = 0;     // Catch: NumberFormatException -> L80
        r54.horizontalWeight = r64;     // Catch: NumberFormatException -> L80
        return;
    L44:
        r54.mHeight = 0;     // Catch: NumberFormatException -> L80
        r54.verticalWeight = r64;     // Catch: NumberFormatException -> L80
        return;
    L47:
        if ((r5 instanceof Constraint.Delta) == false) goto L86;
        Constraint.Delta r55 = (Constraint.Delta) r5;     // Catch: NumberFormatException -> L80
        if (r7 != 0) goto L52;
        r55.add(23, 0);     // Catch: NumberFormatException -> L80
        r55.add(39, r64);     // Catch: NumberFormatException -> L80
        return;
    L52:
        r55.add(21, 0);     // Catch: NumberFormatException -> L80
        r55.add(40, r64);     // Catch: NumberFormatException -> L80
        return;
    L86:
        return;
    L55:
        if ("parent".equalsIgnoreCase(r02) == false) goto L87;
        float r65 = Math.max(0.0f, Math.min(1.0f, Float.parseFloat(r63)));     // Catch: NumberFormatException -> L80
        if ((r5 instanceof ConstraintLayout.LayoutParams) == false) goto L65;
        ConstraintLayout.LayoutParams r56 = (ConstraintLayout.LayoutParams) r5;     // Catch: NumberFormatException -> L80
        if (r7 != 0) goto L62;
        ((ViewGroup.MarginLayoutParams) r56).width = 0;     // Catch: NumberFormatException -> L80
        r56.matchConstraintPercentWidth = r65;     // Catch: NumberFormatException -> L80
        r56.matchConstraintDefaultWidth = 2;     // Catch: NumberFormatException -> L80
        return;
    L62:
        ((ViewGroup.MarginLayoutParams) r56).height = 0;     // Catch: NumberFormatException -> L80
        r56.matchConstraintPercentHeight = r65;     // Catch: NumberFormatException -> L80
        r56.matchConstraintDefaultHeight = 2;     // Catch: NumberFormatException -> L80
        return;
    L65:
        if ((r5 instanceof Layout) == false) goto L73;
        Layout r57 = (Layout) r5;     // Catch: NumberFormatException -> L80
        if (r7 != 0) goto L70;
        r57.mWidth = 0;     // Catch: NumberFormatException -> L80
        r57.widthPercent = r65;     // Catch: NumberFormatException -> L80
        r57.widthDefault = 2;     // Catch: NumberFormatException -> L80
        return;
    L70:
        r57.mHeight = 0;     // Catch: NumberFormatException -> L80
        r57.heightPercent = r65;     // Catch: NumberFormatException -> L80
        r57.heightDefault = 2;     // Catch: NumberFormatException -> L80
        return;
    L73:
        if ((r5 instanceof Constraint.Delta) == false) goto L88;
        Constraint.Delta r58 = (Constraint.Delta) r5;     // Catch: NumberFormatException -> L80
        if (r7 != 0) goto L78;
        r58.add(23, 0);     // Catch: NumberFormatException -> L80
        r58.add(54, 2);     // Catch: NumberFormatException -> L80
        return;
    L78:
        r58.add(21, 0);     // Catch: NumberFormatException -> L80
        r58.add(55, 2);     // Catch: NumberFormatException -> L80
        return;
    L88:
        return;
    L87:
        return;
    L90:
        return;
    L84:
        return;
    L83:
        return;
    }

    public static void parseDimensionRatioString(ConstraintLayout.LayoutParams r8, String r9) {
        float r0 = Float.NaN;
        int r1 = -1;
        if (r9 == null) goto L34;
        int r2 = r9.length();
        int r3 = r9.indexOf(44);
        int r4 = 0;
        if (r3 > 0) goto L7;
    L15:
        int r32 = r9.indexOf(58);
        if (r32 >= 0) goto L18;
    L31:
        String r22 = r9.substring(r4);
        if (r22.length() <= 0) goto L34;
        r0 = Float.parseFloat(r22);     // Catch: NumberFormatException -> L36
    L37:
        goto L34
    L18:
        if (r32 >= (r2 - 1)) goto L31;
        String r23 = r9.substring(r4, r32);
        String r33 = r9.substring(r32 + 1);
        if (r23.length() <= 0) goto L34;
        if (r33.length() <= 0) goto L34;
        float r24 = Float.parseFloat(r23);     // Catch: NumberFormatException -> L36
        float r34 = Float.parseFloat(r33);     // Catch: NumberFormatException -> L36
        if (r24 <= 0.0f) goto L37;
        if (r34 <= 0.0f) goto L37;
        if (r1 != 1) goto L29;
        r0 = Math.abs(r34 / r24);     // Catch: NumberFormatException -> L36
        goto L37
    L29:
        r0 = Math.abs(r24 / r34);     // Catch: NumberFormatException -> L36
        goto L37
    L7:
        if (r3 >= (r2 - 1)) goto L15;
        String r6 = r9.substring(0, r3);
        if (r6.equalsIgnoreCase("W") == false) goto L12;
        r1 = 0;
    L14:
        r4 = r3 + 1;
        goto L15
    L12:
        if (r6.equalsIgnoreCase("H") == false) goto L14;
        r1 = 1;
    L34:
        r8.dimensionRatio = r9;
        r8.mDimensionRatioValue = r0;
        r8.mDimensionRatioSide = r1;
    }

    private void populateConstraint(Constraint r9, TypedArray r10, boolean r11) {
        if (r11 == false) goto L5;
        populateOverride(r9, r10);
        return;
    L5:
        int r8 = r10.getIndexCount();
        int r0 = 0;
    L6:
        if (r0 >= r8) goto L125;
        int r1 = r10.getIndex(r0);
        if (r1 == R$styleable.Constraint_android_id) goto L15;
        if (R$styleable.Constraint_android_layout_marginStart == r1) goto L15;
        if (R$styleable.Constraint_android_layout_marginEnd == r1) goto L15;
        r9.motion.mApply = true;
        r9.layout.mApply = true;
        r9.propertySet.mApply = true;
        r9.transform.mApply = true;
    L15:
        switch(sMapToConstant.get(r1)) {
            case 1: goto L123;
            case 2: goto L122;
            case 3: goto L121;
            case 4: goto L120;
            case 5: goto L119;
            case 6: goto L118;
            case 7: goto L117;
            case 8: goto L116;
            case 9: goto L115;
            case 10: goto L114;
            case 11: goto L113;
            case 12: goto L112;
            case 13: goto L111;
            case 14: goto L110;
            case 15: goto L109;
            case 16: goto L108;
            case 17: goto L107;
            case 18: goto L106;
            case 19: goto L105;
            case 20: goto L104;
            case 21: goto L103;
            case 22: goto L102;
            case 23: goto L101;
            case 24: goto L100;
            case 25: goto L99;
            case 26: goto L98;
            case 27: goto L97;
            case 28: goto L96;
            case 29: goto L95;
            case 30: goto L94;
            case 31: goto L93;
            case 32: goto L92;
            case 33: goto L91;
            case 34: goto L90;
            case 35: goto L89;
            case 36: goto L88;
            case 37: goto L87;
            case 38: goto L86;
            case 39: goto L85;
            case 40: goto L84;
            case 41: goto L83;
            case 42: goto L82;
            case 43: goto L81;
            case 44: goto L80;
            case 45: goto L79;
            case 46: goto L78;
            case 47: goto L77;
            case 48: goto L76;
            case 49: goto L75;
            case 50: goto L74;
            case 51: goto L73;
            case 52: goto L72;
            case 53: goto L71;
            case 54: goto L70;
            case 55: goto L69;
            case 56: goto L68;
            case 57: goto L67;
            case 58: goto L66;
            case 59: goto L65;
            case 60: goto L64;
            case 61: goto L63;
            case 62: goto L62;
            case 63: goto L61;
            case 64: goto L60;
            case 65: goto L57;
            case 66: goto L55;
            case 67: goto L54;
            case 68: goto L53;
            case 69: goto L52;
            case 70: goto L51;
            case 71: goto L50;
            case 72: goto L49;
            case 73: goto L48;
            case 74: goto L47;
            case 75: goto L46;
            case 76: goto L45;
            case 77: goto L44;
            case 78: goto L43;
            case 79: goto L42;
            case 80: goto L41;
            case 81: goto L40;
            case 82: goto L39;
            case 83: goto L38;
            case 84: goto L37;
            case 85: goto L36;
            case 86: goto L25;
            case 87: goto L24;
            case 88: goto L16;
            case 89: goto L16;
            case 90: goto L16;
            case 91: goto L23;
            case 92: goto L22;
            case 93: goto L21;
            case 94: goto L20;
            case 95: goto L19;
            case 96: goto L18;
            case 97: goto L17;
            default: goto L16;
        };
    L16:
        Log.w("ConstraintSet", "Unknown attribute 0x" + Integer.toHexString(r1) + "   " + sMapToConstant.get(r1));
    L124:
        r0 = r0 + 1;
        goto L6
    L17:
        Layout r2 = r9.layout;
        r2.mWrapBehavior = r10.getInt(r1, r2.mWrapBehavior);
        goto L124
    L18:
        parseDimensionConstraints(r9.layout, r10, r1, 1);
        goto L124
    L19:
        parseDimensionConstraints(r9.layout, r10, r1, 0);
        goto L124
    L20:
        Layout r22 = r9.layout;
        r22.goneBaselineMargin = r10.getDimensionPixelSize(r1, r22.goneBaselineMargin);
        goto L124
    L21:
        Layout r23 = r9.layout;
        r23.baselineMargin = r10.getDimensionPixelSize(r1, r23.baselineMargin);
        goto L124
    L22:
        Layout r24 = r9.layout;
        r24.baselineToBottom = lookupID(r10, r1, r24.baselineToBottom);
        goto L124
    L23:
        Layout r25 = r9.layout;
        r25.baselineToTop = lookupID(r10, r1, r25.baselineToTop);
        goto L124
    L24:
        Log.w("ConstraintSet", "unused attribute 0x" + Integer.toHexString(r1) + "   " + sMapToConstant.get(r1));
        goto L124
    L25:
        int r26 = r10.peekValue(r1).type;
        if (r26 != 1) goto L30;
        r9.motion.mQuantizeInterpolatorID = r10.getResourceId(r1, -1);
        Motion r12 = r9.motion;
        if (r12.mQuantizeInterpolatorID == (-1)) goto L124;
        r12.mQuantizeInterpolatorType = -2;
        goto L124
    L30:
        if (r26 != 3) goto L35;
        r9.motion.mQuantizeInterpolatorString = r10.getString(r1);
        int r27 = r9.motion.mQuantizeInterpolatorString.indexOf("/");
        Motion r3 = r9.motion;
        if (r27 <= 0) goto L34;
        r3.mQuantizeInterpolatorID = r10.getResourceId(r1, -1);
        r9.motion.mQuantizeInterpolatorType = -2;
        goto L124
    L34:
        r3.mQuantizeInterpolatorType = -1;
        goto L124
    L35:
        Motion r28 = r9.motion;
        r28.mQuantizeInterpolatorType = r10.getInteger(r1, r28.mQuantizeInterpolatorID);
        goto L124
    L36:
        Motion r29 = r9.motion;
        r29.mQuantizeMotionPhase = r10.getFloat(r1, r29.mQuantizeMotionPhase);
        goto L124
    L37:
        Motion r210 = r9.motion;
        r210.mQuantizeMotionSteps = r10.getInteger(r1, r210.mQuantizeMotionSteps);
        goto L124
    L38:
        Transform r211 = r9.transform;
        r211.transformPivotTarget = lookupID(r10, r1, r211.transformPivotTarget);
        goto L124
    L39:
        Motion r212 = r9.motion;
        r212.mAnimateCircleAngleTo = r10.getInteger(r1, r212.mAnimateCircleAngleTo);
        goto L124
    L40:
        Layout r213 = r9.layout;
        r213.constrainedHeight = r10.getBoolean(r1, r213.constrainedHeight);
        goto L124
    L41:
        Layout r214 = r9.layout;
        r214.constrainedWidth = r10.getBoolean(r1, r214.constrainedWidth);
        goto L124
    L42:
        Motion r215 = r9.motion;
        r215.mMotionStagger = r10.getFloat(r1, r215.mMotionStagger);
        goto L124
    L43:
        PropertySet r216 = r9.propertySet;
        r216.mVisibilityMode = r10.getInt(r1, r216.mVisibilityMode);
        goto L124
    L44:
        r9.layout.mConstraintTag = r10.getString(r1);
        goto L124
    L45:
        Motion r217 = r9.motion;
        r217.mPathMotionArc = r10.getInt(r1, r217.mPathMotionArc);
        goto L124
    L46:
        Layout r218 = r9.layout;
        r218.mBarrierAllowsGoneWidgets = r10.getBoolean(r1, r218.mBarrierAllowsGoneWidgets);
        goto L124
    L47:
        r9.layout.mReferenceIdString = r10.getString(r1);
        goto L124
    L48:
        Layout r219 = r9.layout;
        r219.mBarrierMargin = r10.getDimensionPixelSize(r1, r219.mBarrierMargin);
        goto L124
    L49:
        Layout r220 = r9.layout;
        r220.mBarrierDirection = r10.getInt(r1, r220.mBarrierDirection);
        goto L124
    L50:
        Log.e("ConstraintSet", "CURRENTLY UNSUPPORTED");
        goto L124
    L51:
        r9.layout.heightPercent = r10.getFloat(r1, 1.0f);
        goto L124
    L52:
        r9.layout.widthPercent = r10.getFloat(r1, 1.0f);
        goto L124
    L53:
        PropertySet r221 = r9.propertySet;
        r221.mProgress = r10.getFloat(r1, r221.mProgress);
        goto L124
    L54:
        Motion r222 = r9.motion;
        r222.mPathRotate = r10.getFloat(r1, r222.mPathRotate);
        goto L124
    L55:
        r9.motion.mDrawPath = r10.getInt(r1, 0);
        goto L124
    L60:
        Motion r223 = r9.motion;
        r223.mAnimateRelativeTo = lookupID(r10, r1, r223.mAnimateRelativeTo);
        goto L124
    L61:
        Layout r224 = r9.layout;
        r224.circleAngle = r10.getFloat(r1, r224.circleAngle);
        goto L124
    L62:
        Layout r225 = r9.layout;
        r225.circleRadius = r10.getDimensionPixelSize(r1, r225.circleRadius);
        goto L124
    L63:
        Layout r226 = r9.layout;
        r226.circleConstraint = lookupID(r10, r1, r226.circleConstraint);
        goto L124
    L64:
        Transform r227 = r9.transform;
        r227.rotation = r10.getFloat(r1, r227.rotation);
        goto L124
    L65:
        Layout r228 = r9.layout;
        r228.heightMin = r10.getDimensionPixelSize(r1, r228.heightMin);
        goto L124
    L66:
        Layout r229 = r9.layout;
        r229.widthMin = r10.getDimensionPixelSize(r1, r229.widthMin);
        goto L124
    L67:
        Layout r230 = r9.layout;
        r230.heightMax = r10.getDimensionPixelSize(r1, r230.heightMax);
        goto L124
    L68:
        Layout r231 = r9.layout;
        r231.widthMax = r10.getDimensionPixelSize(r1, r231.widthMax);
        goto L124
    L69:
        Layout r232 = r9.layout;
        r232.heightDefault = r10.getInt(r1, r232.heightDefault);
        goto L124
    L70:
        Layout r233 = r9.layout;
        r233.widthDefault = r10.getInt(r1, r233.widthDefault);
        goto L124
    L71:
        Transform r234 = r9.transform;
        r234.translationZ = r10.getDimension(r1, r234.translationZ);
        goto L124
    L72:
        Transform r235 = r9.transform;
        r235.translationY = r10.getDimension(r1, r235.translationY);
        goto L124
    L73:
        Transform r236 = r9.transform;
        r236.translationX = r10.getDimension(r1, r236.translationX);
        goto L124
    L74:
        Transform r237 = r9.transform;
        r237.transformPivotY = r10.getDimension(r1, r237.transformPivotY);
        goto L124
    L75:
        Transform r238 = r9.transform;
        r238.transformPivotX = r10.getDimension(r1, r238.transformPivotX);
        goto L124
    L76:
        Transform r239 = r9.transform;
        r239.scaleY = r10.getFloat(r1, r239.scaleY);
        goto L124
    L77:
        Transform r240 = r9.transform;
        r240.scaleX = r10.getFloat(r1, r240.scaleX);
        goto L124
    L78:
        Transform r241 = r9.transform;
        r241.rotationY = r10.getFloat(r1, r241.rotationY);
        goto L124
    L79:
        Transform r242 = r9.transform;
        r242.rotationX = r10.getFloat(r1, r242.rotationX);
        goto L124
    L80:
        Transform r243 = r9.transform;
        r243.applyElevation = true;
        r243.elevation = r10.getDimension(r1, r243.elevation);
        goto L124
    L81:
        PropertySet r244 = r9.propertySet;
        r244.alpha = r10.getFloat(r1, r244.alpha);
        goto L124
    L82:
        Layout r245 = r9.layout;
        r245.verticalChainStyle = r10.getInt(r1, r245.verticalChainStyle);
        goto L124
    L83:
        Layout r246 = r9.layout;
        r246.horizontalChainStyle = r10.getInt(r1, r246.horizontalChainStyle);
        goto L124
    L84:
        Layout r247 = r9.layout;
        r247.verticalWeight = r10.getFloat(r1, r247.verticalWeight);
        goto L124
    L85:
        Layout r248 = r9.layout;
        r248.horizontalWeight = r10.getFloat(r1, r248.horizontalWeight);
        goto L124
    L86:
        r9.mViewId = r10.getResourceId(r1, r9.mViewId);
        goto L124
    L87:
        Layout r249 = r9.layout;
        r249.verticalBias = r10.getFloat(r1, r249.verticalBias);
        goto L124
    L88:
        Layout r250 = r9.layout;
        r250.topToTop = lookupID(r10, r1, r250.topToTop);
        goto L124
    L89:
        Layout r251 = r9.layout;
        r251.topToBottom = lookupID(r10, r1, r251.topToBottom);
        goto L124
    L90:
        Layout r252 = r9.layout;
        r252.topMargin = r10.getDimensionPixelSize(r1, r252.topMargin);
        goto L124
    L91:
        Layout r253 = r9.layout;
        r253.startToStart = lookupID(r10, r1, r253.startToStart);
        goto L124
    L92:
        Layout r254 = r9.layout;
        r254.startToEnd = lookupID(r10, r1, r254.startToEnd);
        goto L124
    L93:
        Layout r255 = r9.layout;
        r255.startMargin = r10.getDimensionPixelSize(r1, r255.startMargin);
        goto L124
    L94:
        Layout r256 = r9.layout;
        r256.rightToRight = lookupID(r10, r1, r256.rightToRight);
        goto L124
    L95:
        Layout r257 = r9.layout;
        r257.rightToLeft = lookupID(r10, r1, r257.rightToLeft);
        goto L124
    L96:
        Layout r258 = r9.layout;
        r258.rightMargin = r10.getDimensionPixelSize(r1, r258.rightMargin);
        goto L124
    L97:
        Layout r259 = r9.layout;
        r259.orientation = r10.getInt(r1, r259.orientation);
        goto L124
    L98:
        Layout r260 = r9.layout;
        r260.leftToRight = lookupID(r10, r1, r260.leftToRight);
        goto L124
    L99:
        Layout r261 = r9.layout;
        r261.leftToLeft = lookupID(r10, r1, r261.leftToLeft);
        goto L124
    L100:
        Layout r262 = r9.layout;
        r262.leftMargin = r10.getDimensionPixelSize(r1, r262.leftMargin);
        goto L124
    L101:
        Layout r263 = r9.layout;
        r263.mWidth = r10.getLayoutDimension(r1, r263.mWidth);
        goto L124
    L102:
        PropertySet r264 = r9.propertySet;
        r264.visibility = r10.getInt(r1, r264.visibility);
        PropertySet r13 = r9.propertySet;
        r13.visibility = VISIBILITY_FLAGS[r13.visibility];
        goto L124
    L103:
        Layout r265 = r9.layout;
        r265.mHeight = r10.getLayoutDimension(r1, r265.mHeight);
        goto L124
    L104:
        Layout r266 = r9.layout;
        r266.horizontalBias = r10.getFloat(r1, r266.horizontalBias);
        goto L124
    L105:
        Layout r267 = r9.layout;
        r267.guidePercent = r10.getFloat(r1, r267.guidePercent);
        goto L124
    L106:
        Layout r268 = r9.layout;
        r268.guideEnd = r10.getDimensionPixelOffset(r1, r268.guideEnd);
        goto L124
    L107:
        Layout r269 = r9.layout;
        r269.guideBegin = r10.getDimensionPixelOffset(r1, r269.guideBegin);
        goto L124
    L108:
        Layout r270 = r9.layout;
        r270.goneTopMargin = r10.getDimensionPixelSize(r1, r270.goneTopMargin);
        goto L124
    L109:
        Layout r271 = r9.layout;
        r271.goneStartMargin = r10.getDimensionPixelSize(r1, r271.goneStartMargin);
        goto L124
    L110:
        Layout r272 = r9.layout;
        r272.goneRightMargin = r10.getDimensionPixelSize(r1, r272.goneRightMargin);
        goto L124
    L111:
        Layout r273 = r9.layout;
        r273.goneLeftMargin = r10.getDimensionPixelSize(r1, r273.goneLeftMargin);
        goto L124
    L112:
        Layout r274 = r9.layout;
        r274.goneEndMargin = r10.getDimensionPixelSize(r1, r274.goneEndMargin);
        goto L124
    L113:
        Layout r275 = r9.layout;
        r275.goneBottomMargin = r10.getDimensionPixelSize(r1, r275.goneBottomMargin);
        goto L124
    L114:
        Layout r276 = r9.layout;
        r276.endToStart = lookupID(r10, r1, r276.endToStart);
        goto L124
    L115:
        Layout r277 = r9.layout;
        r277.endToEnd = lookupID(r10, r1, r277.endToEnd);
        goto L124
    L116:
        Layout r278 = r9.layout;
        r278.endMargin = r10.getDimensionPixelSize(r1, r278.endMargin);
        goto L124
    L117:
        Layout r279 = r9.layout;
        r279.editorAbsoluteY = r10.getDimensionPixelOffset(r1, r279.editorAbsoluteY);
        goto L124
    L118:
        Layout r280 = r9.layout;
        r280.editorAbsoluteX = r10.getDimensionPixelOffset(r1, r280.editorAbsoluteX);
        goto L124
    L119:
        r9.layout.dimensionRatio = r10.getString(r1);
        goto L124
    L120:
        Layout r281 = r9.layout;
        r281.bottomToTop = lookupID(r10, r1, r281.bottomToTop);
        goto L124
    L121:
        Layout r282 = r9.layout;
        r282.bottomToBottom = lookupID(r10, r1, r282.bottomToBottom);
        goto L124
    L122:
        Layout r283 = r9.layout;
        r283.bottomMargin = r10.getDimensionPixelSize(r1, r283.bottomMargin);
        goto L124
    L123:
        Layout r284 = r9.layout;
        r284.baselineToBaseline = lookupID(r10, r1, r284.baselineToBaseline);
        goto L124
    L57:
        if (r10.peekValue(r1).type != 3) goto L59;
        r9.motion.mTransitionEasing = r10.getString(r1);
        goto L124
    L59:
        r9.motion.mTransitionEasing = Easing.NAMED_EASING[r10.getInteger(r1, 0)];
        goto L124
    L125:
        Layout r82 = r9.layout;
        if (r82.mReferenceIdString == null) goto L230;
        r82.mReferenceIds = null;
        return;
    }

    private static void populateOverride(Constraint r12, TypedArray r13) {
        int r0 = r13.getIndexCount();
        Constraint.Delta r1 = new Constraint.Delta();
        r12.mDelta = r1;
        r12.motion.mApply = false;
        r12.layout.mApply = false;
        r12.propertySet.mApply = false;
        r12.transform.mApply = false;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L110;
        int r4 = r13.getIndex(r2);
        switch(sOverrideMapToConstant.get(r4)) {
            case 2: goto L108;
            case 3: goto L6;
            case 4: goto L6;
            case 5: goto L107;
            case 6: goto L106;
            case 7: goto L105;
            case 8: goto L104;
            case 9: goto L6;
            case 10: goto L6;
            case 11: goto L103;
            case 12: goto L102;
            case 13: goto L101;
            case 14: goto L100;
            case 15: goto L99;
            case 16: goto L98;
            case 17: goto L97;
            case 18: goto L96;
            case 19: goto L95;
            case 20: goto L94;
            case 21: goto L93;
            case 22: goto L92;
            case 23: goto L91;
            case 24: goto L90;
            case 25: goto L6;
            case 26: goto L6;
            case 27: goto L89;
            case 28: goto L88;
            case 29: goto L6;
            case 30: goto L6;
            case 31: goto L87;
            case 32: goto L6;
            case 33: goto L6;
            case 34: goto L86;
            case 35: goto L6;
            case 36: goto L6;
            case 37: goto L85;
            case 38: goto L84;
            case 39: goto L83;
            case 40: goto L82;
            case 41: goto L81;
            case 42: goto L80;
            case 43: goto L79;
            case 44: goto L78;
            case 45: goto L77;
            case 46: goto L76;
            case 47: goto L75;
            case 48: goto L74;
            case 49: goto L73;
            case 50: goto L72;
            case 51: goto L71;
            case 52: goto L70;
            case 53: goto L69;
            case 54: goto L68;
            case 55: goto L67;
            case 56: goto L66;
            case 57: goto L65;
            case 58: goto L64;
            case 59: goto L63;
            case 60: goto L62;
            case 61: goto L6;
            case 62: goto L61;
            case 63: goto L60;
            case 64: goto L59;
            case 65: goto L56;
            case 66: goto L54;
            case 67: goto L53;
            case 68: goto L52;
            case 69: goto L51;
            case 70: goto L50;
            case 71: goto L49;
            case 72: goto L48;
            case 73: goto L47;
            case 74: goto L46;
            case 75: goto L45;
            case 76: goto L44;
            case 77: goto L43;
            case 78: goto L42;
            case 79: goto L41;
            case 80: goto L40;
            case 81: goto L39;
            case 82: goto L38;
            case 83: goto L37;
            case 84: goto L36;
            case 85: goto L35;
            case 86: goto L23;
            case 87: goto L22;
            case 88: goto L6;
            case 89: goto L6;
            case 90: goto L6;
            case 91: goto L6;
            case 92: goto L6;
            case 93: goto L21;
            case 94: goto L20;
            case 95: goto L19;
            case 96: goto L18;
            case 97: goto L17;
            case 98: goto L9;
            case 99: goto L7;
            default: goto L6;
        };
    L6:
        Log.w("ConstraintSet", "Unknown attribute 0x" + Integer.toHexString(r4) + "   " + sMapToConstant.get(r4));
    L109:
        r2 = r2 + 1;
        goto L3
    L7:
        r1.add(99, r13.getBoolean(r4, r12.layout.guidelineUseRtl));
        goto L109
    L17:
        r1.add(97, r13.getInt(r4, r12.layout.mWrapBehavior));
        goto L109
    L18:
        parseDimensionConstraints(r1, r13, r4, 1);
        goto L109
    L19:
        parseDimensionConstraints(r1, r13, r4, 0);
        goto L109
    L20:
        r1.add(94, r13.getDimensionPixelSize(r4, r12.layout.goneBaselineMargin));
        goto L109
    L21:
        r1.add(93, r13.getDimensionPixelSize(r4, r12.layout.baselineMargin));
        goto L109
    L22:
        Log.w("ConstraintSet", "unused attribute 0x" + Integer.toHexString(r4) + "   " + sMapToConstant.get(r4));
        goto L109
    L23:
        int r5 = r13.peekValue(r4).type;
        if (r5 != 1) goto L28;
        r12.motion.mQuantizeInterpolatorID = r13.getResourceId(r4, -1);
        r1.add(89, r12.motion.mQuantizeInterpolatorID);
        Motion r42 = r12.motion;
        if (r42.mQuantizeInterpolatorID == (-1)) goto L109;
        r42.mQuantizeInterpolatorType = -2;
        r1.add(88, -2);
        goto L109
    L28:
        Motion r10 = r12.motion;
        if (r5 != 3) goto L34;
        r10.mQuantizeInterpolatorString = r13.getString(r4);
        r1.add(90, r12.motion.mQuantizeInterpolatorString);
        int r52 = r12.motion.mQuantizeInterpolatorString.indexOf("/");
        Motion r8 = r12.motion;
        if (r52 <= 0) goto L33;
        r8.mQuantizeInterpolatorID = r13.getResourceId(r4, -1);
        r1.add(89, r12.motion.mQuantizeInterpolatorID);
        r12.motion.mQuantizeInterpolatorType = -2;
        r1.add(88, -2);
        goto L109
    L33:
        r8.mQuantizeInterpolatorType = -1;
        r1.add(88, -1);
        goto L109
    L34:
        r10.mQuantizeInterpolatorType = r13.getInteger(r4, r10.mQuantizeInterpolatorID);
        r1.add(88, r12.motion.mQuantizeInterpolatorType);
        goto L109
    L35:
        r1.add(85, r13.getFloat(r4, r12.motion.mQuantizeMotionPhase));
        goto L109
    L36:
        r1.add(84, r13.getInteger(r4, r12.motion.mQuantizeMotionSteps));
        goto L109
    L37:
        r1.add(83, lookupID(r13, r4, r12.transform.transformPivotTarget));
        goto L109
    L38:
        r1.add(82, r13.getInteger(r4, r12.motion.mAnimateCircleAngleTo));
        goto L109
    L39:
        r1.add(81, r13.getBoolean(r4, r12.layout.constrainedHeight));
        goto L109
    L40:
        r1.add(80, r13.getBoolean(r4, r12.layout.constrainedWidth));
        goto L109
    L41:
        r1.add(79, r13.getFloat(r4, r12.motion.mMotionStagger));
        goto L109
    L42:
        r1.add(78, r13.getInt(r4, r12.propertySet.mVisibilityMode));
        goto L109
    L43:
        r1.add(77, r13.getString(r4));
        goto L109
    L44:
        r1.add(76, r13.getInt(r4, r12.motion.mPathMotionArc));
        goto L109
    L45:
        r1.add(75, r13.getBoolean(r4, r12.layout.mBarrierAllowsGoneWidgets));
        goto L109
    L46:
        r1.add(74, r13.getString(r4));
        goto L109
    L47:
        r1.add(73, r13.getDimensionPixelSize(r4, r12.layout.mBarrierMargin));
        goto L109
    L48:
        r1.add(72, r13.getInt(r4, r12.layout.mBarrierDirection));
        goto L109
    L49:
        Log.e("ConstraintSet", "CURRENTLY UNSUPPORTED");
        goto L109
    L50:
        r1.add(70, r13.getFloat(r4, 1.0f));
        goto L109
    L51:
        r1.add(69, r13.getFloat(r4, 1.0f));
        goto L109
    L52:
        r1.add(68, r13.getFloat(r4, r12.propertySet.mProgress));
        goto L109
    L53:
        r1.add(67, r13.getFloat(r4, r12.motion.mPathRotate));
        goto L109
    L54:
        r1.add(66, r13.getInt(r4, 0));
        goto L109
    L59:
        r1.add(64, lookupID(r13, r4, r12.motion.mAnimateRelativeTo));
        goto L109
    L60:
        r1.add(63, r13.getFloat(r4, r12.layout.circleAngle));
        goto L109
    L61:
        r1.add(62, r13.getDimensionPixelSize(r4, r12.layout.circleRadius));
        goto L109
    L62:
        r1.add(60, r13.getFloat(r4, r12.transform.rotation));
        goto L109
    L63:
        r1.add(59, r13.getDimensionPixelSize(r4, r12.layout.heightMin));
        goto L109
    L64:
        r1.add(58, r13.getDimensionPixelSize(r4, r12.layout.widthMin));
        goto L109
    L65:
        r1.add(57, r13.getDimensionPixelSize(r4, r12.layout.heightMax));
        goto L109
    L66:
        r1.add(56, r13.getDimensionPixelSize(r4, r12.layout.widthMax));
        goto L109
    L67:
        r1.add(55, r13.getInt(r4, r12.layout.heightDefault));
        goto L109
    L68:
        r1.add(54, r13.getInt(r4, r12.layout.widthDefault));
        goto L109
    L69:
        r1.add(53, r13.getDimension(r4, r12.transform.translationZ));
        goto L109
    L70:
        r1.add(52, r13.getDimension(r4, r12.transform.translationY));
        goto L109
    L71:
        r1.add(51, r13.getDimension(r4, r12.transform.translationX));
        goto L109
    L72:
        r1.add(50, r13.getDimension(r4, r12.transform.transformPivotY));
        goto L109
    L73:
        r1.add(49, r13.getDimension(r4, r12.transform.transformPivotX));
        goto L109
    L74:
        r1.add(48, r13.getFloat(r4, r12.transform.scaleY));
        goto L109
    L75:
        r1.add(47, r13.getFloat(r4, r12.transform.scaleX));
        goto L109
    L76:
        r1.add(46, r13.getFloat(r4, r12.transform.rotationY));
        goto L109
    L77:
        r1.add(45, r13.getFloat(r4, r12.transform.rotationX));
        goto L109
    L78:
        r1.add(44, true);
        r1.add(44, r13.getDimension(r4, r12.transform.elevation));
        goto L109
    L79:
        r1.add(43, r13.getFloat(r4, r12.propertySet.alpha));
        goto L109
    L80:
        r1.add(42, r13.getInt(r4, r12.layout.verticalChainStyle));
        goto L109
    L81:
        r1.add(41, r13.getInt(r4, r12.layout.horizontalChainStyle));
        goto L109
    L82:
        r1.add(40, r13.getFloat(r4, r12.layout.verticalWeight));
        goto L109
    L83:
        r1.add(39, r13.getFloat(r4, r12.layout.horizontalWeight));
        goto L109
    L84:
        int r43 = r13.getResourceId(r4, r12.mViewId);
        r12.mViewId = r43;
        r1.add(38, r43);
        goto L109
    L85:
        r1.add(37, r13.getFloat(r4, r12.layout.verticalBias));
        goto L109
    L86:
        r1.add(34, r13.getDimensionPixelSize(r4, r12.layout.topMargin));
        goto L109
    L87:
        r1.add(31, r13.getDimensionPixelSize(r4, r12.layout.startMargin));
        goto L109
    L88:
        r1.add(28, r13.getDimensionPixelSize(r4, r12.layout.rightMargin));
        goto L109
    L89:
        r1.add(27, r13.getInt(r4, r12.layout.orientation));
        goto L109
    L90:
        r1.add(24, r13.getDimensionPixelSize(r4, r12.layout.leftMargin));
        goto L109
    L91:
        r1.add(23, r13.getLayoutDimension(r4, r12.layout.mWidth));
        goto L109
    L92:
        r1.add(22, VISIBILITY_FLAGS[r13.getInt(r4, r12.propertySet.visibility)]);
        goto L109
    L93:
        r1.add(21, r13.getLayoutDimension(r4, r12.layout.mHeight));
        goto L109
    L94:
        r1.add(20, r13.getFloat(r4, r12.layout.horizontalBias));
        goto L109
    L95:
        r1.add(19, r13.getFloat(r4, r12.layout.guidePercent));
        goto L109
    L96:
        r1.add(18, r13.getDimensionPixelOffset(r4, r12.layout.guideEnd));
        goto L109
    L97:
        r1.add(17, r13.getDimensionPixelOffset(r4, r12.layout.guideBegin));
        goto L109
    L98:
        r1.add(16, r13.getDimensionPixelSize(r4, r12.layout.goneTopMargin));
        goto L109
    L99:
        r1.add(15, r13.getDimensionPixelSize(r4, r12.layout.goneStartMargin));
        goto L109
    L100:
        r1.add(14, r13.getDimensionPixelSize(r4, r12.layout.goneRightMargin));
        goto L109
    L101:
        r1.add(13, r13.getDimensionPixelSize(r4, r12.layout.goneLeftMargin));
        goto L109
    L102:
        r1.add(12, r13.getDimensionPixelSize(r4, r12.layout.goneEndMargin));
        goto L109
    L103:
        r1.add(11, r13.getDimensionPixelSize(r4, r12.layout.goneBottomMargin));
        goto L109
    L104:
        r1.add(8, r13.getDimensionPixelSize(r4, r12.layout.endMargin));
        goto L109
    L105:
        r1.add(7, r13.getDimensionPixelOffset(r4, r12.layout.editorAbsoluteY));
        goto L109
    L106:
        r1.add(6, r13.getDimensionPixelOffset(r4, r12.layout.editorAbsoluteX));
        goto L109
    L107:
        r1.add(5, r13.getString(r4));
        goto L109
    L108:
        r1.add(2, r13.getDimensionPixelSize(r4, r12.layout.bottomMargin));
        goto L109
    L9:
        if (MotionLayout.IS_IN_EDIT_MODE == false) goto L14;
        int r53 = r13.getResourceId(r4, r12.mViewId);
        r12.mViewId = r53;
        if (r53 != (-1)) goto L109;
        r12.mTargetString = r13.getString(r4);
        goto L109
    L14:
        if (r13.peekValue(r4).type != 3) goto L16;
        r12.mTargetString = r13.getString(r4);
        goto L109
    L16:
        r12.mViewId = r13.getResourceId(r4, r12.mViewId);
        goto L109
    L56:
        if (r13.peekValue(r4).type != 3) goto L58;
        r1.add(65, r13.getString(r4));
        goto L109
    L58:
        r1.add(65, Easing.NAMED_EASING[r13.getInteger(r4, 0)]);
        goto L109
    }

    public void applyTo(ConstraintLayout r2) {
        applyToInternal(r2, true);
        r2.setConstraintSet(null);
        r2.requestLayout();
    }

    public void applyToInternal(ConstraintLayout r12, boolean r13) {
        int r0 = r12.getChildCount();
        HashSet r1 = new HashSet(this.mConstraints.keySet());
        int r2 = 0;
        int r3 = 0;
    L4:
        if (r3 >= r0) goto L55;
        View r5 = r12.getChildAt(r3);
        int r6 = r5.getId();
        if (this.mConstraints.containsKey(Integer.valueOf(r6)) == true) goto L9;
        Log.w("ConstraintSet", "id unknown " + Debug.getName(r5));
    L54:
        r3 = r3 + 1;
        goto L4
    L9:
        if (this.mForceId == false) goto L14;
        if (r6 != (-1)) goto L14;
        GlShader$$ExternalSyntheticBUOutline1.m("All children of ConstraintLayout must have ids to use ConstraintSet");
        return;
    L14:
        if (r6 == (-1)) goto L54;
        if (this.mConstraints.containsKey(Integer.valueOf(r6)) == false) goto L53;
        r1.remove(Integer.valueOf(r6));
        Constraint r7 = this.mConstraints.get(Integer.valueOf(r6));
        if (r7 == null) goto L54;
        if ((r5 instanceof Barrier) == false) goto L29;
        r7.layout.mHelperType = 1;
        Barrier r4 = (Barrier) r5;
        r4.setId(r6);
        r4.setType(r7.layout.mBarrierDirection);
        r4.setMargin(r7.layout.mBarrierMargin);
        r4.setAllowsGoneWidget(r7.layout.mBarrierAllowsGoneWidgets);
        Layout r62 = r7.layout;
        int[] r8 = r62.mReferenceIds;
        if (r8 == null) goto L26;
        r4.setReferencedIds(r8);
        goto L29
    L26:
        String r82 = r62.mReferenceIdString;
        if (r82 == null) goto L29;
        r62.mReferenceIds = convertReferenceString(r4, r82);
        r4.setReferencedIds(r7.layout.mReferenceIds);
    L29:
        ConstraintLayout.LayoutParams r42 = (ConstraintLayout.LayoutParams) r5.getLayoutParams();
        r42.validate();
        r7.applyTo(r42);
        if (r13 == false) goto L32;
        ConstraintAttribute.setAttributes(r5, r7.mCustomConstraints);
    L32:
        r5.setLayoutParams(r42);
        PropertySet r43 = r7.propertySet;
        if (r43.mVisibilityMode != 0) goto L35;
        r5.setVisibility(r43.visibility);
    L35:
        r5.setAlpha(r7.propertySet.alpha);
        r5.setRotation(r7.transform.rotation);
        r5.setRotationX(r7.transform.rotationX);
        r5.setRotationY(r7.transform.rotationY);
        r5.setScaleX(r7.transform.scaleX);
        r5.setScaleY(r7.transform.scaleY);
        Transform r44 = r7.transform;
        if (r44.transformPivotTarget == (-1)) goto L45;
        if (((View) r5.getParent()).findViewById(r7.transform.transformPivotTarget) == null) goto L50;
        float r63 = (r4.getTop() + r4.getBottom()) / 2.0f;
        float r45 = (r4.getLeft() + r4.getRight()) / 2.0f;
        if ((r5.getRight() - r5.getLeft()) <= 0) goto L50;
        if ((r5.getBottom() - r5.getTop()) <= 0) goto L50;
        r5.setPivotX(r45 - r5.getLeft());
        r5.setPivotY(r63 - r5.getTop());
    L50:
        r5.setTranslationX(r7.transform.translationX);
        r5.setTranslationY(r7.transform.translationY);
        r5.setTranslationZ(r7.transform.translationZ);
        Transform r46 = r7.transform;
        if (r46.applyElevation == false) goto L54;
        r5.setElevation(r46.elevation);
        goto L54
    L45:
        if (Float.isNaN(r44.transformPivotX) == true) goto L48;
        r5.setPivotX(r7.transform.transformPivotX);
    L48:
        if (Float.isNaN(r7.transform.transformPivotY) == true) goto L50;
        r5.setPivotY(r7.transform.transformPivotY);
        goto L50
    L53:
        Log.v("ConstraintSet", "WARNING NO CONSTRAINTS for view " + r6);
        goto L54
    L55:
        Iterator r132 = r1.iterator();
    L57:
        if (r132.hasNext() == false) goto L73;
        Integer r14 = (Integer) r132.next();
        Constraint r32 = this.mConstraints.get(r14);
        if (r32 == null) goto L57;
        if (r32.layout.mHelperType != 1) goto L71;
        Barrier r52 = new Barrier(r12.getContext());
        r52.setId(r14.intValue());
        Layout r64 = r32.layout;
        int[] r72 = r64.mReferenceIds;
        if (r72 == null) goto L66;
        r52.setReferencedIds(r72);
    L69:
        r52.setType(r32.layout.mBarrierDirection);
        r52.setMargin(r32.layout.mBarrierMargin);
        ConstraintLayout.LayoutParams r65 = r12.generateDefaultLayoutParams();
        r52.validateParams();
        r32.applyTo(r65);
        r12.addView(r52, r65);
        goto L71
    L66:
        String r73 = r64.mReferenceIdString;
        if (r73 == null) goto L69;
        r64.mReferenceIds = convertReferenceString(r52, r73);
        r52.setReferencedIds(r32.layout.mReferenceIds);
    L71:
        if (r32.layout.mIsGuideline == false) goto L57;
        View r53 = new Guideline(r12.getContext());
        r53.setId(r14.intValue());
        ConstraintLayout.LayoutParams r15 = r12.generateDefaultLayoutParams();
        r32.applyTo(r15);
        r12.addView(r53, r15);
    L73:
        if (r2 >= r0) goto L78;
        View r11 = r12.getChildAt(r2);
        if ((r11 instanceof ConstraintHelper) == false) goto L77;
        ((ConstraintHelper) r11).applyLayoutFeaturesInConstraintSet(r12);
    L77:
        r2 = r2 + 1;
        goto L73
    }

    public void clone(Context r2, int r3) {
        clone((ConstraintLayout) LayoutInflater.from(r2).inflate(r3, null));
    }

    public void clone(ConstraintLayout r11) {
        int r0 = r11.getChildCount();
        this.mConstraints.clear();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L29;
        View r2 = r11.getChildAt(r1);
        ConstraintLayout.LayoutParams r3 = (ConstraintLayout.LayoutParams) r2.getLayoutParams();
        int r4 = r2.getId();
        if (this.mForceId == false) goto L12;
        if (r4 != (-1)) goto L12;
        GlShader$$ExternalSyntheticBUOutline1.m("All children of ConstraintLayout must have ids to use ConstraintSet");
        return;
    L12:
        if (this.mConstraints.containsKey(Integer.valueOf(r4)) == true) goto L14;
        this.mConstraints.put(Integer.valueOf(r4), new Constraint());
    L14:
        Constraint r5 = this.mConstraints.get(Integer.valueOf(r4));
        if (r5 == null) goto L28;
        r5.mCustomConstraints = ConstraintAttribute.extractAttributes(this.mSavedAttributes, r2);
        Constraint.access$000(r5, r4, r3);
        r5.propertySet.visibility = r2.getVisibility();
        r5.propertySet.alpha = r2.getAlpha();
        r5.transform.rotation = r2.getRotation();
        r5.transform.rotationX = r2.getRotationX();
        r5.transform.rotationY = r2.getRotationY();
        r5.transform.scaleX = r2.getScaleX();
        r5.transform.scaleY = r2.getScaleY();
        float r32 = r2.getPivotX();
        float r42 = r2.getPivotY();
        if (r32 == 0.0d) goto L20;
    L21:
        Transform r6 = r5.transform;
        r6.transformPivotX = r32;
        r6.transformPivotY = r42;
    L22:
        r5.transform.translationX = r2.getTranslationX();
        r5.transform.translationY = r2.getTranslationY();
        r5.transform.translationZ = r2.getTranslationZ();
        Transform r33 = r5.transform;
        if (r33.applyElevation == false) goto L26;
        r33.elevation = r2.getElevation();
    L26:
        if ((r2 instanceof Barrier) == false) goto L28;
        Barrier r22 = (Barrier) r2;
        r5.layout.mBarrierAllowsGoneWidgets = r22.getAllowsGoneWidget();
        r5.layout.mReferenceIds = r22.getReferencedIds();
        r5.layout.mBarrierDirection = r22.getType();
        r5.layout.mBarrierMargin = r22.getMargin();
        goto L28
    L20:
        if (r42 == 0.0d) goto L22;
    L28:
        r1 = r1 + 1;
        goto L3
    }

    public void constrainCircle(int r1, int r2, int r3, float r4) {
        Layout r0 = get(r1).layout;
        r0.circleConstraint = r2;
        r0.circleRadius = r3;
        r0.circleAngle = r4;
    }

    public void load(Context r8, int r9) {
        XmlResourceParser r2 = r8.getResources().getXml(r9);
        int r3 = r2.getEventType();     // Catch: IOException -> L12 XmlPullParserException -> L14
    L5:
        if (r3 == 1) goto L29;
        if (r3 != 2) goto L17;
        String r32 = r2.getName();     // Catch: IOException -> L12 XmlPullParserException -> L14
        Constraint r5 = fillFromAttributeList(r8, Xml.asAttributeSet(r2), false);     // Catch: IOException -> L12 XmlPullParserException -> L14
        if (r32.equalsIgnoreCase("Guideline") == false) goto L16;
        r5.layout.mIsGuideline = true;     // Catch: IOException -> L12 XmlPullParserException -> L14
    L16:
        this.mConstraints.put(Integer.valueOf(r5.mViewId), r5);     // Catch: IOException -> L12 XmlPullParserException -> L14
    L17:
        r3 = r2.next();     // Catch: IOException -> L12 XmlPullParserException -> L14
    L29:
        return;
    L12:
        e = move-exception;
        Log.e("ConstraintSet", "Error parsing resource: " + r9, e);
        return;
    L14:
        e = move-exception;
        Log.e("ConstraintSet", "Error parsing resource: " + r9, e);
    }

    public void load(Context r10, XmlPullParser r11) {
        int r2 = r11.getEventType();     // Catch: IOException -> L18 XmlPullParserException -> L20
        Constraint r4 = null;
    L5:
        if (r2 == 1) goto L123;
        if (r2 != 0) goto L8;
        r11.getName();     // Catch: IOException -> L18 XmlPullParserException -> L20
    L81:
        r2 = r11.next();     // Catch: IOException -> L18 XmlPullParserException -> L20
        goto L5
    L8:
        if (r2 != 2) goto L10;
        String r22 = r11.getName();     // Catch: IOException -> L18 XmlPullParserException -> L20
        switch(r22.hashCode()) {
            case -2025855158: goto L75;
            case -1984451626: goto L69;
            case -1962203927: goto L66;
            case -1269513683: goto L60;
            case -1238332596: goto L54;
            case -71750448: goto L51;
            case 366511058: goto L45;
            case 1331510167: goto L42;
            case 1791837707: goto L39;
            case 1803088381: goto L36;
            default: goto L81;
        };
    L36:
        if (r22.equals("Constraint") == false) goto L81;
        r4 = fillFromAttributeList(r10, Xml.asAttributeSet(r11), false);     // Catch: IOException -> L18 XmlPullParserException -> L20
        goto L81
    L39:
        if (r22.equals("CustomAttribute") == false) goto L81;
    L46:
        if (r4 == null) goto L49;
        ConstraintAttribute.parse(r10, r11, r4.mCustomConstraints);     // Catch: IOException -> L18 XmlPullParserException -> L20
        goto L81
    L49:
        throw new RuntimeException("XML parser error must be within a Constraint " + r11.getLineNumber());     // Catch: IOException -> L18 XmlPullParserException -> L20
    L42:
        if (r22.equals("Barrier") == false) goto L81;
        r4 = fillFromAttributeList(r10, Xml.asAttributeSet(r11), false);     // Catch: IOException -> L18 XmlPullParserException -> L20
        r4.layout.mHelperType = 1;     // Catch: IOException -> L18 XmlPullParserException -> L20
        goto L81
    L45:
        if (r22.equals("CustomMethod") == false) goto L81;
    L51:
        if (r22.equals("Guideline") == false) goto L81;
        r4 = fillFromAttributeList(r10, Xml.asAttributeSet(r11), false);     // Catch: IOException -> L18 XmlPullParserException -> L20
        Layout r23 = r4.layout;     // Catch: IOException -> L18 XmlPullParserException -> L20
        r23.mIsGuideline = true;     // Catch: IOException -> L18 XmlPullParserException -> L20
        r23.mApply = true;     // Catch: IOException -> L18 XmlPullParserException -> L20
        goto L81
    L54:
        if (r22.equals("Transform") == false) goto L81;
        if (r4 == null) goto L58;
        r4.transform.fillFromAttributeList(r10, Xml.asAttributeSet(r11));     // Catch: IOException -> L18 XmlPullParserException -> L20
        goto L81
    L58:
        throw new RuntimeException("XML parser error must be within a Constraint " + r11.getLineNumber());     // Catch: IOException -> L18 XmlPullParserException -> L20
    L60:
        if (r22.equals("PropertySet") == false) goto L81;
        if (r4 == null) goto L64;
        r4.propertySet.fillFromAttributeList(r10, Xml.asAttributeSet(r11));     // Catch: IOException -> L18 XmlPullParserException -> L20
        goto L81
    L64:
        throw new RuntimeException("XML parser error must be within a Constraint " + r11.getLineNumber());     // Catch: IOException -> L18 XmlPullParserException -> L20
    L66:
        if (r22.equals("ConstraintOverride") == false) goto L81;
        r4 = fillFromAttributeList(r10, Xml.asAttributeSet(r11), true);     // Catch: IOException -> L18 XmlPullParserException -> L20
        goto L81
    L69:
        if (r22.equals("Motion") == false) goto L81;
        if (r4 == null) goto L73;
        r4.motion.fillFromAttributeList(r10, Xml.asAttributeSet(r11));     // Catch: IOException -> L18 XmlPullParserException -> L20
        goto L81
    L73:
        throw new RuntimeException("XML parser error must be within a Constraint " + r11.getLineNumber());     // Catch: IOException -> L18 XmlPullParserException -> L20
    L75:
        if (r22.equals("Layout") == false) goto L81;
        if (r4 == null) goto L79;
        r4.layout.fillFromAttributeList(r10, Xml.asAttributeSet(r11));     // Catch: IOException -> L18 XmlPullParserException -> L20
        goto L81
    L79:
        throw new RuntimeException("XML parser error must be within a Constraint " + r11.getLineNumber());     // Catch: IOException -> L18 XmlPullParserException -> L20
    L10:
        if (r2 != 3) goto L81;
        String r24 = r11.getName().toLowerCase(Locale.ROOT);     // Catch: IOException -> L18 XmlPullParserException -> L20
        switch(r24.hashCode()) {
            case -2075718416: goto L29;
            case -190376483: goto L26;
            case 426575017: goto L23;
            case 2146106725: goto L16;
            default: goto L81;
        };     // Catch: IOException -> L18 XmlPullParserException -> L20
    L16:
        if (r24.equals("constraintset") == false) goto L81;
        return;
    L23:
        if (r24.equals("constraintoverride") == false) goto L81;
    L30:
        this.mConstraints.put(Integer.valueOf(r4.mViewId), r4);     // Catch: IOException -> L18 XmlPullParserException -> L20
        r4 = null;
        goto L81
    L26:
        if (r24.equals("constraint") == false) goto L81;
    L29:
        if (r24.equals("guideline") == false) goto L81;
    L123:
        return;
    L18:
        e = move-exception;
        Log.e("ConstraintSet", "Error parsing XML resource", e);
        return;
    L20:
        e = move-exception;
        Log.e("ConstraintSet", "Error parsing XML resource", e);
    }
}
