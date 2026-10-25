package androidx.constraintlayout.core.widgets;

import androidx.constraintlayout.core.LinearSystem;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import java.util.ArrayList;
import java.util.Arrays;

/* loaded from: classes3.dex */
public class Flow extends VirtualLayout {
    private ConstraintWidget[] mAlignedBiggestElementsInCols;
    private ConstraintWidget[] mAlignedBiggestElementsInRows;
    private int[] mAlignedDimensions;
    private ArrayList<WidgetsList> mChainList;
    private ConstraintWidget[] mDisplayedWidgets;
    private int mDisplayedWidgetsCount;
    private float mFirstHorizontalBias;
    private int mFirstHorizontalStyle;
    private float mFirstVerticalBias;
    private int mFirstVerticalStyle;
    private int mHorizontalAlign;
    private float mHorizontalBias;
    private int mHorizontalGap;
    private int mHorizontalStyle;
    private float mLastHorizontalBias;
    private int mLastHorizontalStyle;
    private float mLastVerticalBias;
    private int mLastVerticalStyle;
    private int mMaxElementsWrap;
    private int mOrientation;
    private int mVerticalAlign;
    private float mVerticalBias;
    private int mVerticalGap;
    private int mVerticalStyle;
    private int mWrapMode;

    public class WidgetsList {
        private ConstraintWidget mBiggest;
        int mBiggestDimension;
        private ConstraintAnchor mBottom;
        private int mCount;
        private int mHeight;
        private ConstraintAnchor mLeft;
        private int mMax;
        private int mNbMatchConstraintsWidgets;
        private int mOrientation;
        private int mPaddingBottom;
        private int mPaddingLeft;
        private int mPaddingRight;
        private int mPaddingTop;
        private ConstraintAnchor mRight;
        private int mStartIndex;
        private ConstraintAnchor mTop;
        private int mWidth;
        final /* synthetic */ Flow this$0;

        public WidgetsList(Flow r2, int r3, ConstraintAnchor r4, ConstraintAnchor r5, ConstraintAnchor r6, ConstraintAnchor r7, int r8) {
            this.this$0 = r2;
            this.mBiggest = null;
            this.mBiggestDimension = 0;
            this.mPaddingLeft = 0;
            this.mPaddingTop = 0;
            this.mPaddingRight = 0;
            this.mPaddingBottom = 0;
            this.mWidth = 0;
            this.mHeight = 0;
            this.mStartIndex = 0;
            this.mCount = 0;
            this.mNbMatchConstraintsWidgets = 0;
            this.mMax = 0;
            this.mOrientation = r3;
            this.mLeft = r4;
            this.mTop = r5;
            this.mRight = r6;
            this.mBottom = r7;
            this.mPaddingLeft = r2.getPaddingLeft();
            this.mPaddingTop = r2.getPaddingTop();
            this.mPaddingRight = r2.getPaddingRight();
            this.mPaddingBottom = r2.getPaddingBottom();
            this.mMax = r8;
        }

        public static /* synthetic */ ConstraintWidget access$2000(WidgetsList r0) {
            return r0.mBiggest;
        }

        private void recomputeDimensions() {
            this.mWidth = 0;
            this.mHeight = 0;
            this.mBiggest = null;
            this.mBiggestDimension = 0;
            int r1 = this.mCount;
            int r2 = 0;
        L3:
            if (r2 >= r1) goto L26;
            if ((this.mStartIndex + r2) >= Flow.access$400(this.this$0)) goto L33;
            ConstraintWidget r3 = Flow.access$500(this.this$0)[this.mStartIndex + r2];
            if (this.mOrientation != 0) goto L17;
            int r4 = r3.getWidth();
            int r6 = Flow.access$000(this.this$0);
            if (r3.getVisibility() != 8) goto L12;
            r6 = 0;
        L12:
            this.mWidth += r4 + r6;
            int r42 = Flow.access$300(this.this$0, r3, this.mMax);
            if (this.mBiggest != null) goto L15;
        L16:
            this.mBiggest = r3;
            this.mBiggestDimension = r42;
            this.mHeight = r42;
        L25:
            r2 = r2 + 1;
            goto L3
        L15:
            if (this.mBiggestDimension >= r42) goto L25;
        L17:
            int r43 = Flow.access$200(this.this$0, r3, this.mMax);
            int r62 = Flow.access$300(this.this$0, r3, this.mMax);
            int r7 = Flow.access$100(this.this$0);
            if (r3.getVisibility() != 8) goto L20;
            r7 = 0;
        L20:
            this.mHeight += r62 + r7;
            if (this.mBiggest != null) goto L23;
        L24:
            this.mBiggest = r3;
            this.mBiggestDimension = r43;
            this.mWidth = r43;
            goto L25
        L23:
            if (this.mBiggestDimension >= r43) goto L25;
        L33:
            return;
        }

        public void add(ConstraintWidget r7) {
            int r0 = this.mOrientation;
            Flow r1 = this.this$0;
            int r3 = 0;
            if (r0 != 0) goto L16;
            int r02 = Flow.access$200(r1, r7, this.mMax);
            if (r7.getHorizontalDimensionBehaviour() != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) goto L7;
            this.mNbMatchConstraintsWidgets++;
            r02 = 0;
        L7:
            int r12 = Flow.access$000(this.this$0);
            if (r7.getVisibility() == 8) goto L11;
            r3 = r12;
        L11:
            this.mWidth += r02 + r3;
            int r03 = Flow.access$300(this.this$0, r7, this.mMax);
            if (this.mBiggest != null) goto L14;
        L15:
            this.mBiggest = r7;
            this.mBiggestDimension = r03;
            this.mHeight = r03;
        L28:
            this.mCount++;
            return;
        L14:
            if (this.mBiggestDimension >= r03) goto L28;
        L16:
            int r04 = Flow.access$200(r1, r7, this.mMax);
            int r13 = Flow.access$300(this.this$0, r7, this.mMax);
            if (r7.getVerticalDimensionBehaviour() != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) goto L19;
            this.mNbMatchConstraintsWidgets++;
            r13 = 0;
        L19:
            int r4 = Flow.access$100(this.this$0);
            if (r7.getVisibility() == 8) goto L23;
            r3 = r4;
        L23:
            this.mHeight += r13 + r3;
            if (this.mBiggest != null) goto L26;
        L27:
            this.mBiggest = r7;
            this.mBiggestDimension = r04;
            this.mWidth = r04;
            goto L28
        L26:
            if (this.mBiggestDimension >= r04) goto L28;
            goto L27
        }

        public void clear() {
            this.mBiggestDimension = 0;
            this.mBiggest = null;
            this.mWidth = 0;
            this.mHeight = 0;
            this.mStartIndex = 0;
            this.mCount = 0;
            this.mNbMatchConstraintsWidgets = 0;
        }

        public void createConstraints(boolean r18, int r19, boolean r20) {
            int r1 = this.mCount;
            int r3 = 0;
        L3:
            if (r3 >= r1) goto L11;
            if ((this.mStartIndex + r3) >= Flow.access$400(this.this$0)) goto L11;
            ConstraintWidget r4 = Flow.access$500(this.this$0)[this.mStartIndex + r3];
            if (r4 == null) goto L10;
            r4.resetAnchors();
        L10:
            r3 = r3 + 1;
        L11:
            if (r1 != 0) goto L13;
            return;
        L13:
            if (this.mBiggest == null) goto L221;
            if (r20 == false) goto L18;
            if (r19 != 0) goto L18;
            boolean r42 = true;
        L19:
            int r6 = 0;
            int r7 = -1;
            int r8 = -1;
        L20:
            if (r6 >= r1) goto L35;
            if (r18 == false) goto L23;
            int r9 = (r1 - 1) - r6;
        L25:
            if ((this.mStartIndex + r9) >= Flow.access$400(this.this$0)) goto L35;
            ConstraintWidget r92 = Flow.access$500(this.this$0)[this.mStartIndex + r9];
            if (r92 == null) goto L34;
            if (r92.getVisibility() != 0) goto L34;
            if (r7 != (-1)) goto L33;
            r7 = r6;
        L33:
            r8 = r6;
        L34:
            r6 = r6 + 1;
            goto L20
        L23:
            r9 = r6;
        L35:
            int r62 = this.mOrientation;
            ConstraintWidget r93 = this.mBiggest;
            ConstraintWidget r10 = null;
            if (r62 != 0) goto L125;
            r93.setVerticalChainStyle(Flow.access$600(this.this$0));
            int r63 = this.mPaddingTop;
            if (r19 <= 0) goto L40;
            r63 = r63 + Flow.access$100(this.this$0);
        L40:
            r93.mTop.connect(this.mTop, r63);
            if (r20 == false) goto L43;
            r93.mBottom.connect(this.mBottom, this.mPaddingBottom);
        L43:
            if (r19 <= 0) goto L45;
            this.mTop.mOwner.mBottom.connect(r93.mTop, 0);
        L45:
            char r11 = 3;
            if (Flow.access$700(this.this$0) == 3) goto L48;
        L61:
            ConstraintWidget r12 = r93;
        L62:
            int r64 = 0;
        L63:
            if (r64 >= r1) goto L222;
            if (r18 == false) goto L66;
            int r13 = (r1 - 1) - r64;
        L68:
            if ((this.mStartIndex + r13) >= Flow.access$400(this.this$0)) goto L223;
            ConstraintWidget r14 = Flow.access$500(this.this$0)[this.mStartIndex + r13];
            if (r14 != null) goto L73;
            r14 = r10;
            char r102 = r11;
        L124:
            r64 = r64 + 1;
            r11 = r102;
            r10 = r14;
            goto L63
        L73:
            if (r64 != 0) goto L75;
            int r16 = 1;
            r14.connect(r14.mLeft, this.mLeft, this.mPaddingLeft);
        L76:
            if (r13 != 0) goto L95;
            int r32 = Flow.access$800(this.this$0);
            float r112 = Flow.access$900(this.this$0);
            if (r18 == false) goto L81;
            r112 = 1.0f - r112;
        L81:
            if (this.mStartIndex == 0) goto L83;
        L87:
            if (r20 == true) goto L89;
        L93:
            r14.setHorizontalChainStyle(r32);
            r14.setHorizontalBiasPercent(r112);
            goto L95
        L89:
            if (Flow.access$1200(this.this$0) == (-1)) goto L93;
            r32 = Flow.access$1200(this.this$0);
            r112 = Flow.access$1300(this.this$0);
            if (r18 == false) goto L93;
        L86:
            r112 = 1.0f - r112;
            goto L93
        L83:
            if (Flow.access$1000(this.this$0) == (-1)) goto L87;
            r32 = Flow.access$1000(this.this$0);
            r112 = Flow.access$1100(this.this$0);
            if (r18 == false) goto L93;
        L95:
            if (r64 != (r1 - 1)) goto L97;
            r14.connect(r14.mRight, this.mRight, this.mPaddingRight);
        L97:
            if (r10 == null) goto L104;
            r14.mLeft.connect(r10.mRight, Flow.access$000(this.this$0));
            if (r64 != r7) goto L101;
            r14.mLeft.setGoneMargin(this.mPaddingLeft);
        L101:
            r10.mRight.connect(r14.mLeft, 0);
            if (r64 != (r8 + 1)) goto L104;
            r10.mRight.setGoneMargin(this.mPaddingRight);
        L104:
            if (r14 == r93) goto L123;
            r102 = 3;
            if (Flow.access$700(this.this$0) == 3) goto L108;
        L113:
            int r33 = Flow.access$700(this.this$0);
            if (r33 != 0) goto L116;
            r14.mTop.connect(r93.mTop, 0);
            goto L124
        L116:
            if (r33 == r16) goto L121;
            ConstraintAnchor r34 = r14.mTop;
            if (r42 == false) goto L120;
            r34.connect(this.mTop, this.mPaddingTop);
            r14.mBottom.connect(this.mBottom, this.mPaddingBottom);
            goto L124
        L120:
            r34.connect(r93.mTop, 0);
            r14.mBottom.connect(r93.mBottom, 0);
            goto L124
        L121:
            r14.mBottom.connect(r93.mBottom, 0);
            goto L124
        L108:
            if (r12.hasBaseline() == false) goto L113;
            if (r14 == r12) goto L113;
            if (r14.hasBaseline() == false) goto L113;
            r14.mBaseline.connect(r12.mBaseline, 0);
            goto L124
        L123:
            r102 = 3;
            goto L124
        L75:
            r16 = 1;
            goto L76
        L223:
            return;
        L66:
            r13 = r64;
            goto L68
        L222:
            return;
        L48:
            if (r93.hasBaseline() == true) goto L61;
            int r65 = 0;
        L50:
            if (r65 >= r1) goto L61;
            if (r18 == false) goto L53;
            int r122 = (r1 - 1) - r65;
        L55:
            if ((this.mStartIndex + r122) >= Flow.access$400(this.this$0)) goto L61;
            r12 = Flow.access$500(this.this$0)[this.mStartIndex + r122];
            if (r12.hasBaseline() == true) goto L62;
            r65 = r65 + 1;
            goto L50
        L53:
            r122 = r65;
            goto L55
        L125:
            r93.setHorizontalChainStyle(Flow.access$800(this.this$0));
            int r35 = this.mPaddingLeft;
            if (r19 <= 0) goto L128;
            r35 = r35 + Flow.access$000(this.this$0);
        L128:
            if (r18 == false) goto L134;
            r93.mRight.connect(this.mRight, r35);
            if (r20 == false) goto L132;
            r93.mLeft.connect(this.mLeft, this.mPaddingRight);
        L132:
            if (r19 <= 0) goto L139;
            this.mRight.mOwner.mLeft.connect(r93.mRight, 0);
        L139:
            int r36 = 0;
        L140:
            if (r36 >= r1) goto L224;
            if ((this.mStartIndex + r36) >= Flow.access$400(this.this$0)) goto L225;
            ConstraintWidget r66 = Flow.access$500(this.this$0)[this.mStartIndex + r36];
            if (r66 == null) goto L194;
            if (r36 != 0) goto L159;
            r66.connect(r66.mTop, this.mTop, this.mPaddingTop);
            int r113 = Flow.access$600(this.this$0);
            float r123 = Flow.access$1400(this.this$0);
            if (this.mStartIndex == 0) goto L151;
        L153:
            if (r20 == true) goto L155;
        L157:
            r66.setVerticalChainStyle(r113);
            r66.setVerticalBiasPercent(r123);
            goto L159
        L155:
            if (Flow.access$1700(this.this$0) == (-1)) goto L157;
            r113 = Flow.access$1700(this.this$0);
            r123 = Flow.access$1800(this.this$0);
            goto L157
        L151:
            if (Flow.access$1500(this.this$0) == (-1)) goto L153;
            r113 = Flow.access$1500(this.this$0);
            r123 = Flow.access$1600(this.this$0);
        L159:
            if (r36 != (r1 - 1)) goto L161;
            r66.connect(r66.mBottom, this.mBottom, this.mPaddingBottom);
        L161:
            if (r10 == null) goto L168;
            r66.mTop.connect(r10.mBottom, Flow.access$100(this.this$0));
            if (r36 != r7) goto L165;
            r66.mTop.setGoneMargin(this.mPaddingTop);
        L165:
            r10.mBottom.connect(r66.mTop, 0);
            if (r36 != (r8 + 1)) goto L168;
            r10.mBottom.setGoneMargin(this.mPaddingBottom);
        L168:
            if (r66 == r93) goto L193;
            Flow r103 = this.this$0;
            if (r18 == false) goto L181;
            int r104 = Flow.access$1900(r103);
            if (r104 != 0) goto L174;
            r66.mRight.connect(r93.mRight, 0);
            goto L193
        L174:
            if (r104 == 1) goto L178;
            if (r104 != 2) goto L193;
            r66.mLeft.connect(r93.mLeft, 0);
            r66.mRight.connect(r93.mRight, 0);
            goto L193
        L178:
            r66.mLeft.connect(r93.mLeft, 0);
            goto L193
        L181:
            int r105 = Flow.access$1900(r103);
            if (r105 != 0) goto L184;
            r66.mLeft.connect(r93.mLeft, 0);
            goto L193
        L184:
            if (r105 == 1) goto L191;
            if (r105 != 2) goto L193;
            ConstraintAnchor r106 = r66.mLeft;
            if (r42 == false) goto L190;
            r106.connect(this.mLeft, this.mPaddingLeft);
            r66.mRight.connect(this.mRight, this.mPaddingRight);
            goto L193
        L190:
            r106.connect(r93.mLeft, 0);
            r66.mRight.connect(r93.mRight, 0);
            goto L193
        L191:
            r66.mRight.connect(r93.mRight, 0);
        L193:
            r10 = r66;
        L194:
            r36 = r36 + 1;
            goto L140
        L225:
            return;
        L224:
            return;
        L134:
            r93.mLeft.connect(this.mLeft, r35);
            if (r20 == false) goto L137;
            r93.mRight.connect(this.mRight, this.mPaddingRight);
        L137:
            if (r19 <= 0) goto L139;
            this.mLeft.mOwner.mRight.connect(r93.mLeft, 0);
        L18:
            r42 = false;
            goto L19
        }

        public int getHeight() {
            int r0 = this.mOrientation;
            int r1 = this.mHeight;
            if (r0 == 1) goto L5;
            return r1;
        L5:
            return r1 - Flow.access$100(this.this$0);
        }

        public int getWidth() {
            int r0 = this.mOrientation;
            int r1 = this.mWidth;
            if (r0 == 0) goto L5;
            return r1;
        L5:
            return r1 - Flow.access$000(this.this$0);
        }

        public void measureMatchConstraints(int r9) {
            int r0 = this.mNbMatchConstraintsWidgets;
            if (r0 != 0) goto L5;
            return;
        L5:
            int r1 = this.mCount;
            int r5 = r9 / r0;
            int r92 = 0;
        L6:
            if (r92 >= r1) goto L25;
            if ((this.mStartIndex + r92) >= Flow.access$400(this.this$0)) goto L25;
            ConstraintWidget r3 = Flow.access$500(this.this$0)[this.mStartIndex + r92];
            if (this.mOrientation != 0) goto L18;
            if (r3 == null) goto L24;
            if (r3.getHorizontalDimensionBehaviour() != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) goto L24;
            if (r3.mMatchConstraintDefaultWidth != 0) goto L24;
            this.this$0.measure(r3, ConstraintWidget.DimensionBehaviour.FIXED, r5, r3.getVerticalDimensionBehaviour(), r3.getHeight());
        L24:
            r92 = r92 + 1;
            goto L6
        L18:
            if (r3 == null) goto L24;
            if (r3.getVerticalDimensionBehaviour() != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) goto L24;
            if (r3.mMatchConstraintDefaultHeight != 0) goto L24;
            int r7 = r5;
            this.this$0.measure(r3, r3.getHorizontalDimensionBehaviour(), r3.getWidth(), ConstraintWidget.DimensionBehaviour.FIXED, r7);
            r5 = r7;
        L25:
            recomputeDimensions();
        }

        public void setStartIndex(int r1) {
            this.mStartIndex = r1;
        }

        public void setup(int r1, ConstraintAnchor r2, ConstraintAnchor r3, ConstraintAnchor r4, ConstraintAnchor r5, int r6, int r7, int r8, int r9, int r10) {
            this.mOrientation = r1;
            this.mLeft = r2;
            this.mTop = r3;
            this.mRight = r4;
            this.mBottom = r5;
            this.mPaddingLeft = r6;
            this.mPaddingTop = r7;
            this.mPaddingRight = r8;
            this.mPaddingBottom = r9;
            this.mMax = r10;
        }
    }

    public Flow() {
        this.mHorizontalStyle = -1;
        this.mVerticalStyle = -1;
        this.mFirstHorizontalStyle = -1;
        this.mFirstVerticalStyle = -1;
        this.mLastHorizontalStyle = -1;
        this.mLastVerticalStyle = -1;
        this.mHorizontalBias = 0.5f;
        this.mVerticalBias = 0.5f;
        this.mFirstHorizontalBias = 0.5f;
        this.mFirstVerticalBias = 0.5f;
        this.mLastHorizontalBias = 0.5f;
        this.mLastVerticalBias = 0.5f;
        this.mHorizontalGap = 0;
        this.mVerticalGap = 0;
        this.mHorizontalAlign = 2;
        this.mVerticalAlign = 2;
        this.mWrapMode = 0;
        this.mMaxElementsWrap = -1;
        this.mOrientation = 0;
        this.mChainList = new ArrayList();
        this.mAlignedBiggestElementsInRows = null;
        this.mAlignedBiggestElementsInCols = null;
        this.mAlignedDimensions = null;
        this.mDisplayedWidgetsCount = 0;
    }

    public static /* synthetic */ int access$000(Flow r0) {
        return r0.mHorizontalGap;
    }

    public static /* synthetic */ int access$100(Flow r0) {
        return r0.mVerticalGap;
    }

    public static /* synthetic */ int access$1000(Flow r0) {
        return r0.mFirstHorizontalStyle;
    }

    public static /* synthetic */ float access$1100(Flow r0) {
        return r0.mFirstHorizontalBias;
    }

    public static /* synthetic */ int access$1200(Flow r0) {
        return r0.mLastHorizontalStyle;
    }

    public static /* synthetic */ float access$1300(Flow r0) {
        return r0.mLastHorizontalBias;
    }

    public static /* synthetic */ float access$1400(Flow r0) {
        return r0.mVerticalBias;
    }

    public static /* synthetic */ int access$1500(Flow r0) {
        return r0.mFirstVerticalStyle;
    }

    public static /* synthetic */ float access$1600(Flow r0) {
        return r0.mFirstVerticalBias;
    }

    public static /* synthetic */ int access$1700(Flow r0) {
        return r0.mLastVerticalStyle;
    }

    public static /* synthetic */ float access$1800(Flow r0) {
        return r0.mLastVerticalBias;
    }

    public static /* synthetic */ int access$1900(Flow r0) {
        return r0.mHorizontalAlign;
    }

    public static /* synthetic */ int access$200(Flow r0, ConstraintWidget r1, int r2) {
        return r0.getWidgetWidth(r1, r2);
    }

    public static /* synthetic */ int access$300(Flow r0, ConstraintWidget r1, int r2) {
        return r0.getWidgetHeight(r1, r2);
    }

    public static /* synthetic */ int access$400(Flow r0) {
        return r0.mDisplayedWidgetsCount;
    }

    public static /* synthetic */ ConstraintWidget[] access$500(Flow r0) {
        return r0.mDisplayedWidgets;
    }

    public static /* synthetic */ int access$600(Flow r0) {
        return r0.mVerticalStyle;
    }

    public static /* synthetic */ int access$700(Flow r0) {
        return r0.mVerticalAlign;
    }

    public static /* synthetic */ int access$800(Flow r0) {
        return r0.mHorizontalStyle;
    }

    public static /* synthetic */ float access$900(Flow r0) {
        return r0.mHorizontalBias;
    }

    private void createAlignedConstraints(boolean r12) {
        if (this.mAlignedDimensions != null) goto L5;
        return;
    L5:
        if (this.mAlignedBiggestElementsInCols != null) goto L7;
        return;
    L7:
        if (this.mAlignedBiggestElementsInRows == null) goto L91;
        int r1 = 0;
    L11:
        if (r1 >= this.mDisplayedWidgetsCount) goto L13;
        this.mDisplayedWidgets[r1].resetAnchors();
        r1 = r1 + 1;
        goto L11
    L13:
        int[] r13 = this.mAlignedDimensions;
        int r2 = r13[0];
        int r14 = r13[1];
        float r4 = this.mHorizontalBias;
        ConstraintWidget r5 = null;
        int r6 = 0;
    L15:
        if (r6 >= r2) goto L34;
        if (r12 == false) goto L18;
        int r42 = (r2 - r6) - 1;
        float r8 = 1.0f - this.mHorizontalBias;
    L19:
        ConstraintWidget r43 = this.mAlignedBiggestElementsInCols[r42];
        if (r43 == null) goto L33;
        if (r43.getVisibility() == 8) goto L33;
        if (r6 != 0) goto L27;
        r43.connect(r43.mLeft, this.mLeft, getPaddingLeft());
        r43.setHorizontalChainStyle(this.mHorizontalStyle);
        r43.setHorizontalBiasPercent(r8);
    L27:
        if (r6 != (r2 - 1)) goto L29;
        r43.connect(r43.mRight, this.mRight, getPaddingRight());
    L29:
        if (r6 <= 0) goto L32;
        if (r5 == null) goto L32;
        r43.connect(r43.mLeft, r5.mRight, this.mHorizontalGap);
        r5.connect(r5.mRight, r43.mLeft, 0);
    L32:
        r5 = r43;
    L33:
        r6 = r6 + 1;
        r4 = r8;
        goto L15
    L18:
        r8 = r4;
        r42 = r6;
        goto L19
    L34:
        int r122 = 0;
    L35:
        if (r122 >= r14) goto L51;
        ConstraintWidget r44 = this.mAlignedBiggestElementsInRows[r122];
        if (r44 == null) goto L50;
        if (r44.getVisibility() == 8) goto L50;
        if (r122 != 0) goto L44;
        r44.connect(r44.mTop, this.mTop, getPaddingTop());
        r44.setVerticalChainStyle(this.mVerticalStyle);
        r44.setVerticalBiasPercent(this.mVerticalBias);
    L44:
        if (r122 != (r14 - 1)) goto L46;
        r44.connect(r44.mBottom, this.mBottom, getPaddingBottom());
    L46:
        if (r122 <= 0) goto L49;
        if (r5 == null) goto L49;
        r44.connect(r44.mTop, r5.mBottom, this.mVerticalGap);
        r5.connect(r5.mBottom, r44.mTop, 0);
    L49:
        r5 = r44;
    L50:
        r122 = r122 + 1;
        goto L35
    L51:
        int r123 = 0;
    L52:
        if (r123 >= r2) goto L92;
        int r45 = 0;
    L54:
        if (r45 >= r14) goto L72;
        int r52 = (r45 * r2) + r123;
        if (this.mOrientation != 1) goto L58;
        r52 = (r123 * r14) + r45;
    L58:
        ConstraintWidget[] r62 = this.mDisplayedWidgets;
        if (r52 >= r62.length) goto L71;
        ConstraintWidget r53 = r62[r52];
        if (r53 == null) goto L71;
        if (r53.getVisibility() == 8) goto L71;
        ConstraintWidget r63 = this.mAlignedBiggestElementsInCols[r123];
        ConstraintWidget r82 = this.mAlignedBiggestElementsInRows[r45];
        if (r53 == r63) goto L69;
        r53.connect(r53.mLeft, r63.mLeft, 0);
        r53.connect(r53.mRight, r63.mRight, 0);
    L69:
        if (r53 == r82) goto L71;
        r53.connect(r53.mTop, r82.mTop, 0);
        r53.connect(r53.mBottom, r82.mBottom, 0);
    L71:
        r45 = r45 + 1;
        goto L54
    L72:
        r123 = r123 + 1;
        goto L52
    L92:
        return;
    }

    private int getWidgetHeight(ConstraintWidget r10, int r11) {
        if (r10 != null) goto L6;
        return 0;
    L6:
        if (r10.getVerticalDimensionBehaviour() != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) goto L24;
        int r1 = r10.mMatchConstraintDefaultHeight;
        if (r1 != 0) goto L11;
        return 0;
    L11:
        if (r1 != 2) goto L16;
        int r8 = (int) (r10.mMatchConstraintPercentHeight * r11);
        if (r8 == r10.getHeight()) goto L15;
        r10.setMeasureRequested(true);
        measure(r10, r10.getHorizontalDimensionBehaviour(), r10.getWidth(), ConstraintWidget.DimensionBehaviour.FIXED, r8);
    L15:
        return r8;
    L16:
        ConstraintWidget r4 = r10;
        if (r1 != 1) goto L21;
        return r4.getHeight();
    L21:
        if (r1 != 3) goto L26;
        return (int) ((r4.getWidth() * r4.mDimensionRatio) + 0.5f);
    L26:
        return r4.getHeight();
    L24:
        r4 = r10;
        goto L26
    }

    private int getWidgetWidth(ConstraintWidget r10, int r11) {
        if (r10 != null) goto L6;
        return 0;
    L6:
        if (r10.getHorizontalDimensionBehaviour() != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) goto L24;
        int r1 = r10.mMatchConstraintDefaultWidth;
        if (r1 != 0) goto L11;
        return 0;
    L11:
        if (r1 != 2) goto L16;
        int r6 = (int) (r10.mMatchConstraintPercentWidth * r11);
        if (r6 == r10.getWidth()) goto L15;
        r10.setMeasureRequested(true);
        measure(r10, ConstraintWidget.DimensionBehaviour.FIXED, r6, r10.getVerticalDimensionBehaviour(), r10.getHeight());
    L15:
        return r6;
    L16:
        ConstraintWidget r4 = r10;
        if (r1 != 1) goto L21;
        return r4.getWidth();
    L21:
        if (r1 != 3) goto L26;
        return (int) ((r4.getHeight() * r4.mDimensionRatio) + 0.5f);
    L26:
        return r4.getWidth();
    L24:
        r4 = r10;
        goto L26
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:103:0x010b -> B:40:0x0057). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:104:0x010d -> B:40:0x0057). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:106:0x0113 -> B:40:0x0057). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:107:0x0115 -> B:40:0x0057). Please report as a decompilation issue!!! */
    private void measureAligned(ConstraintWidget[] r11, int r12, int r13, int r14, int[] r15) {
        int r0 = this.mMaxElementsWrap;
        if (r13 != 0) goto L18;
        if (r0 > 0) goto L17;
        r0 = 0;
        int r2 = 0;
        int r3 = 0;
    L6:
        if (r2 >= r12) goto L17;
        if (r2 <= 0) goto L9;
        r3 = r3 + this.mHorizontalGap;
    L9:
        ConstraintWidget r4 = r11[r2];
        if (r4 == null) goto L16;
        r3 = r3 + getWidgetWidth(r4, r14);
        if (r3 > r14) goto L17;
        r0 = r0 + 1;
    L16:
        r2 = r2 + 1;
    L17:
        int r22 = r0;
        r0 = 0;
    L33:
        if (this.mAlignedDimensions != null) goto L36;
        this.mAlignedDimensions = new int[2];
    L36:
        if (r0 != 0) goto L38;
        if (r13 != 1) goto L38;
    L40:
        boolean r42 = true;
    L42:
        if (r42 == true) goto L109;
        if (r13 != 0) goto L45;
        r0 = (int) Math.ceil(r12 / r22);
    L46:
        ConstraintWidget[] r5 = this.mAlignedBiggestElementsInCols;
        if (r5 != null) goto L49;
    L52:
        this.mAlignedBiggestElementsInCols = new ConstraintWidget[r22];
    L53:
        ConstraintWidget[] r52 = this.mAlignedBiggestElementsInRows;
        if (r52 != null) goto L56;
    L59:
        this.mAlignedBiggestElementsInRows = new ConstraintWidget[r0];
    L60:
        int r53 = 0;
    L61:
        if (r53 >= r22) goto L85;
        int r6 = 0;
    L63:
        if (r6 >= r0) goto L84;
        int r7 = (r6 * r22) + r53;
        if (r13 != 1) goto L68;
        r7 = (r53 * r0) + r6;
    L68:
        if (r7 >= r11.length) goto L83;
        ConstraintWidget r72 = r11[r7];
        if (r72 == null) goto L83;
        int r8 = getWidgetWidth(r72, r14);
        ConstraintWidget r9 = this.mAlignedBiggestElementsInCols[r53];
        if (r9 != null) goto L76;
    L77:
        this.mAlignedBiggestElementsInCols[r53] = r72;
    L78:
        int r82 = getWidgetHeight(r72, r14);
        ConstraintWidget r92 = this.mAlignedBiggestElementsInRows[r6];
        if (r92 != null) goto L81;
    L82:
        this.mAlignedBiggestElementsInRows[r6] = r72;
        goto L83
    L81:
        if (r92.getHeight() >= r82) goto L83;
    L76:
        if (r9.getWidth() >= r8) goto L78;
    L83:
        r6 = r6 + 1;
        goto L63
    L84:
        r53 = r53 + 1;
        goto L61
    L85:
        int r54 = 0;
        int r62 = 0;
    L86:
        if (r54 >= r22) goto L93;
        ConstraintWidget r73 = this.mAlignedBiggestElementsInCols[r54];
        if (r73 == null) goto L92;
        if (r54 <= 0) goto L91;
        r62 = r62 + this.mHorizontalGap;
    L91:
        r62 = r62 + getWidgetWidth(r73, r14);
    L92:
        r54 = r54 + 1;
        goto L86
    L93:
        int r55 = 0;
        int r74 = 0;
    L94:
        if (r55 >= r0) goto L101;
        ConstraintWidget r83 = this.mAlignedBiggestElementsInRows[r55];
        if (r83 == null) goto L100;
        if (r55 <= 0) goto L99;
        r74 = r74 + this.mVerticalGap;
    L99:
        r74 = r74 + getWidgetHeight(r83, r14);
    L100:
        r55 = r55 + 1;
        goto L94
    L101:
        r15[0] = r62;
        r15[1] = r74;
        if (r13 == 0) goto L103;
        if (r74 <= r14) goto L40;
        if (r0 <= 1) goto L40;
        r0 = r0 - 1;
        goto L42
    L103:
        if (r62 <= r14) goto L40;
        if (r22 <= 1) goto L40;
        r22 = r22 - 1;
        goto L42
    L56:
        if (r52.length < r0) goto L59;
        Arrays.fill(r52, null);
        goto L60
    L49:
        if (r5.length < r22) goto L52;
        Arrays.fill(r5, null);
        goto L53
    L45:
        r22 = (int) Math.ceil(r12 / r0);
        goto L46
    L109:
        int[] r10 = this.mAlignedDimensions;
        r10[0] = r22;
        r10[1] = r0;
        return;
    L38:
        if (r22 != 0) goto L41;
        if (r13 == 0) goto L40;
    L41:
        r42 = false;
        goto L42
    L18:
        if (r0 > 0) goto L31;
        r0 = 0;
        int r23 = 0;
        int r32 = 0;
    L20:
        if (r23 >= r12) goto L31;
        if (r23 <= 0) goto L23;
        r32 = r32 + this.mVerticalGap;
    L23:
        ConstraintWidget r43 = r11[r23];
        if (r43 == null) goto L30;
        r32 = r32 + getWidgetHeight(r43, r14);
        if (r32 > r14) goto L31;
        r0 = r0 + 1;
    L30:
        r23 = r23 + 1;
    L31:
        r22 = 0;
        goto L33
    }

    private void measureChainWrap(ConstraintWidget[] r20, int r21, int r22, int r23, int[] r24) {
        Flow r1 = this;
        if (r21 != 0) goto L5;
        return;
    L5:
        r1.mChainList.clear();
        int r7 = r23;
        WidgetsList r0 = new WidgetsList(r1, r22, r1.mLeft, r1.mTop, r1.mRight, r1.mBottom, r7);
        r1.mChainList.add(r0);
        if (r22 != 0) goto L34;
        int r2 = 0;
        int r3 = 0;
        int r9 = 0;
    L8:
        if (r9 >= r21) goto L33;
        ConstraintWidget r10 = r20[r9];
        int r13 = r1.getWidgetWidth(r10, r7);
        if (r10.getHorizontalDimensionBehaviour() != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) goto L12;
        r2 = r2 + 1;
    L12:
        int r14 = r2;
        if (r3 == r7) goto L17;
        if (((r1.mHorizontalGap + r3) + r13) > r7) goto L17;
    L19:
        boolean r25 = false;
    L20:
        if (r25 == true) goto L27;
        if (r9 <= 0) goto L27;
        int r4 = r1.mMaxElementsWrap;
        if (r4 <= 0) goto L27;
        if ((r9 % r4) != 0) goto L27;
        r25 = true;
    L27:
        if (r25 == false) goto L30;
        r0 = new WidgetsList(r1, r22, r1.mLeft, r1.mTop, r1.mRight, r1.mBottom, r7);
        r0.setStartIndex(r9);
        r1.mChainList.add(r0);
    L29:
        r3 = r13;
    L32:
        r0.add(r10);
        r9 = r9 + 1;
        r2 = r14;
        goto L8
    L30:
        if (r9 <= 0) goto L29;
        r3 = r3 + (r1.mHorizontalGap + r13);
    L17:
        if (WidgetsList.access$2000(r0) == null) goto L19;
        r25 = true;
    L33:
        Flow r15 = r1;
        int r132 = r15.mChainList.size();
        ConstraintAnchor r02 = r15.mLeft;
        ConstraintAnchor r12 = r15.mTop;
        ConstraintAnchor r32 = r15.mRight;
        ConstraintAnchor r42 = r15.mBottom;
        int r5 = r15.getPaddingLeft();
        int r6 = r15.getPaddingTop();
        int r72 = r15.getPaddingRight();
        int r8 = r15.getPaddingBottom();
        ConstraintWidget.DimensionBehaviour r92 = r15.getHorizontalDimensionBehaviour();
        ConstraintWidget.DimensionBehaviour r102 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (r92 != r102) goto L64;
    L67:
        boolean r93 = true;
    L68:
        if (r2 <= 0) goto L77;
        if (r93 == false) goto L77;
        int r26 = 0;
    L71:
        if (r26 >= r132) goto L77;
        WidgetsList r94 = r15.mChainList.get(r26);
        if (r22 != 0) goto L75;
        r94.measureMatchConstraints(r23 - r94.getWidth());
    L76:
        r26 = r26 + 1;
        goto L71
    L75:
        r94.measureMatchConstraints(r23 - r94.getHeight());
    L77:
        ConstraintAnchor r27 = r02;
        int r95 = r8;
        int r03 = 0;
        int r142 = 0;
        int r82 = r72;
        int r73 = r6;
        int r62 = r5;
        ConstraintAnchor r52 = r42;
        ConstraintAnchor r43 = r32;
        ConstraintAnchor r33 = r12;
        int r16 = 0;
    L78:
        if (r142 >= r132) goto L100;
        WidgetsList r103 = r15.mChainList.get(r142);
        if (r22 == 0) goto L82;
        int r122 = r03;
        int r11 = r16;
        if (r142 >= (r132 - 1)) goto L94;
        ConstraintAnchor r17 = WidgetsList.access$2000(r15.mChainList.get(r142 + 1)).mLeft;
        r82 = 0;
    L93:
        r43 = r17;
        ConstraintAnchor r18 = WidgetsList.access$2000(r103).mRight;
        r103.setup(r22, r27, r33, r43, r52, r62, r73, r82, r95, r23);
        int r19 = r103.getWidth() + r122;
        int r04 = Math.max(r11, r103.getHeight());
        if (r142 <= 0) goto L98;
        r19 = r19 + r15.mHorizontalGap;
    L98:
        int r28 = r19;
        r16 = r04;
        r03 = r28;
        r62 = 0;
        r27 = r18;
    L99:
        r142 = r142 + 1;
        goto L78
    L94:
        r17 = r15.mRight;
        r82 = r15.getPaddingRight();
        goto L93
    L82:
        if (r142 >= (r132 - 1)) goto L85;
        r52 = WidgetsList.access$2000(r15.mChainList.get(r142 + 1)).mTop;
        r95 = 0;
    L86:
        ConstraintAnchor r112 = WidgetsList.access$2000(r103).mBottom;
        int r113 = r16;
        r103.setup(r22, r27, r33, r43, r52, r62, r73, r82, r95, r23);
        int r110 = Math.max(r03, r103.getWidth());
        int r05 = r103.getHeight() + r113;
        if (r142 <= 0) goto L89;
        r05 = r05 + r15.mVerticalGap;
    L89:
        r16 = r05;
        r03 = r110;
        r33 = r112;
        r73 = 0;
        goto L99
    L85:
        r52 = r15.mBottom;
        r95 = r15.getPaddingBottom();
        goto L86
    L100:
        r24[0] = r03;
        r24[1] = r16;
        return;
    L64:
        if (r15.getVerticalDimensionBehaviour() == r102) goto L67;
        r93 = false;
        goto L68
    L34:
        r2 = 0;
        int r34 = 0;
        int r96 = 0;
    L35:
        if (r96 >= r21) goto L33;
        ConstraintWidget r104 = r20[r96];
        int r133 = r1.getWidgetHeight(r104, r7);
        if (r104.getVerticalDimensionBehaviour() != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) goto L39;
        r2 = r2 + 1;
    L39:
        int r143 = r2;
        if (r34 == r7) goto L44;
        if (((r1.mVerticalGap + r34) + r133) > r7) goto L44;
    L46:
        boolean r29 = false;
    L47:
        if (r29 == true) goto L54;
        if (r96 <= 0) goto L54;
        int r44 = r1.mMaxElementsWrap;
        if (r44 <= 0) goto L54;
        if ((r96 % r44) != 0) goto L54;
        r29 = true;
    L54:
        if (r29 == false) goto L57;
        r0 = new WidgetsList(r1, r22, r1.mLeft, r1.mTop, r1.mRight, r1.mBottom, r7);
        Flow r152 = r1;
        r0.setStartIndex(r96);
        r152.mChainList.add(r0);
    L56:
        r34 = r133;
    L60:
        r0.add(r104);
        r96 = r96 + 1;
        r7 = r23;
        r2 = r143;
        r1 = r152;
        goto L35
    L57:
        r152 = r1;
        if (r96 <= 0) goto L56;
        r34 = r34 + (r152.mVerticalGap + r133);
    L44:
        if (WidgetsList.access$2000(r0) == null) goto L46;
        r29 = true;
        goto L47
    }

    private void measureChainWrap_new(ConstraintWidget[] r20, int r21, int r22, int r23, int[] r24) {
        Flow r1 = this;
        if (r21 != 0) goto L5;
        return;
    L5:
        r1.mChainList.clear();
        int r7 = r23;
        WidgetsList r0 = new WidgetsList(r1, r22, r1.mLeft, r1.mTop, r1.mRight, r1.mBottom, r7);
        r1.mChainList.add(r0);
        boolean r12 = true;
        if (r22 != 0) goto L33;
        int r2 = 0;
        int r3 = 0;
        int r4 = 0;
        int r9 = 0;
    L8:
        if (r9 >= r21) goto L32;
        r2 = r2 + 1;
        ConstraintWidget r10 = r20[r9];
        int r13 = r1.getWidgetWidth(r10, r7);
        if (r10.getHorizontalDimensionBehaviour() != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) goto L12;
        r3 = r3 + 1;
    L12:
        int r14 = r3;
        if (r4 == r7) goto L17;
        if (((r1.mHorizontalGap + r4) + r13) > r7) goto L17;
    L19:
        boolean r32 = false;
    L20:
        if (r32 == true) goto L26;
        if (r9 <= 0) goto L26;
        int r5 = r1.mMaxElementsWrap;
        if (r5 <= 0) goto L26;
        if (r2 <= r5) goto L26;
        r32 = true;
    L26:
        if (r32 == false) goto L29;
        r0 = new WidgetsList(r1, r22, r1.mLeft, r1.mTop, r1.mRight, r1.mBottom, r7);
        r0.setStartIndex(r9);
        r1.mChainList.add(r0);
        r2 = 1;
    L28:
        r4 = r13;
    L31:
        r0.add(r10);
        r9 = r9 + 1;
        r3 = r14;
        goto L8
    L29:
        if (r9 <= 0) goto L28;
        r4 = r4 + (r1.mHorizontalGap + r13);
    L17:
        if (WidgetsList.access$2000(r0) == null) goto L19;
        r32 = true;
    L32:
        Flow r15 = r1;
        int r132 = r15.mChainList.size();
        ConstraintAnchor r02 = r15.mLeft;
        ConstraintAnchor r16 = r15.mTop;
        ConstraintAnchor r25 = r15.mRight;
        ConstraintAnchor r42 = r15.mBottom;
        int r52 = r15.getPaddingLeft();
        int r6 = r15.getPaddingTop();
        int r72 = r15.getPaddingRight();
        int r8 = r15.getPaddingBottom();
        ConstraintWidget.DimensionBehaviour r92 = r15.getHorizontalDimensionBehaviour();
        ConstraintWidget.DimensionBehaviour r102 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (r92 != r102) goto L62;
    L65:
        boolean r93 = true;
    L66:
        if (r3 <= 0) goto L75;
        if (r93 == false) goto L75;
        int r33 = 0;
    L69:
        if (r33 >= r132) goto L75;
        WidgetsList r94 = r15.mChainList.get(r33);
        if (r22 != 0) goto L73;
        r94.measureMatchConstraints(r23 - r94.getWidth());
    L74:
        r33 = r33 + 1;
        goto L69
    L73:
        r94.measureMatchConstraints(r23 - r94.getHeight());
    L75:
        ConstraintAnchor r34 = r16;
        int r95 = r8;
        int r17 = 0;
        int r142 = 0;
        int r82 = r72;
        int r73 = r6;
        int r62 = r52;
        ConstraintAnchor r53 = r42;
        ConstraintAnchor r43 = r25;
        ConstraintAnchor r26 = r02;
        int r03 = 0;
    L76:
        if (r142 >= r132) goto L98;
        WidgetsList r103 = r15.mChainList.get(r142);
        if (r22 == 0) goto L80;
        int r11 = r17;
        boolean r172 = r12;
        int r122 = r03;
        if (r142 >= (r132 - 1)) goto L92;
        ConstraintAnchor r18 = WidgetsList.access$2000(r15.mChainList.get(r142 + 1)).mLeft;
        r82 = 0;
    L91:
        r43 = r18;
        ConstraintAnchor r19 = WidgetsList.access$2000(r103).mRight;
        r103.setup(r22, r26, r34, r43, r53, r62, r73, r82, r95, r23);
        int r110 = r103.getWidth() + r122;
        int r04 = Math.max(r11, r103.getHeight());
        if (r142 <= 0) goto L96;
        r110 = r110 + r15.mHorizontalGap;
    L96:
        int r27 = r110;
        r17 = r04;
        r03 = r27;
        r62 = 0;
        r26 = r19;
    L97:
        r142 = r142 + 1;
        r12 = r172;
        goto L76
    L92:
        r18 = r15.mRight;
        r82 = r15.getPaddingRight();
        goto L91
    L80:
        if (r142 >= (r132 - 1)) goto L83;
        r53 = WidgetsList.access$2000(r15.mChainList.get(r142 + 1)).mTop;
        r95 = 0;
    L84:
        ConstraintAnchor r112 = WidgetsList.access$2000(r103).mBottom;
        r172 = r12;
        int r113 = r17;
        r103.setup(r22, r26, r34, r43, r53, r62, r73, r82, r95, r23);
        int r111 = Math.max(r03, r103.getWidth());
        int r05 = r103.getHeight() + r113;
        if (r142 <= 0) goto L87;
        r05 = r05 + r15.mVerticalGap;
    L87:
        r17 = r05;
        r03 = r111;
        r34 = r112;
        r73 = 0;
        goto L97
    L83:
        r53 = r15.mBottom;
        r95 = r15.getPaddingBottom();
        goto L84
    L98:
        r24[0] = r03;
        r24[r12 ? 1 : 0] = r17;
        return;
    L62:
        if (r15.getVerticalDimensionBehaviour() == r102) goto L65;
        r93 = false;
        goto L66
    L33:
        int r28 = 0;
        r3 = 0;
        int r44 = 0;
        int r96 = 0;
    L34:
        if (r96 >= r21) goto L32;
        r28 = r28 + 1;
        ConstraintWidget r104 = r20[r96];
        int r133 = r1.getWidgetHeight(r104, r7);
        if (r104.getVerticalDimensionBehaviour() != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) goto L38;
        r3 = r3 + 1;
    L38:
        int r143 = r3;
        if (r44 == r7) goto L43;
        if (((r1.mVerticalGap + r44) + r133) > r7) goto L43;
    L45:
        boolean r35 = false;
    L46:
        if (r35 == true) goto L52;
        if (r96 <= 0) goto L52;
        int r54 = r1.mMaxElementsWrap;
        if (r54 <= 0) goto L52;
        if (r28 <= r54) goto L52;
        r35 = true;
    L52:
        if (r35 == false) goto L55;
        r0 = new WidgetsList(r1, r22, r1.mLeft, r1.mTop, r1.mRight, r1.mBottom, r7);
        Flow r152 = r1;
        r0.setStartIndex(r96);
        r152.mChainList.add(r0);
        r28 = 1;
    L54:
        r44 = r133;
    L58:
        r0.add(r104);
        r96 = r96 + 1;
        r7 = r23;
        r3 = r143;
        r1 = r152;
        goto L34
    L55:
        r152 = r1;
        if (r96 <= 0) goto L54;
        r44 = r44 + (r152.mVerticalGap + r133);
    L43:
        if (WidgetsList.access$2000(r0) == null) goto L45;
        r35 = true;
        goto L46
    }

    private void measureNoWrap(ConstraintWidget[] r22, int r23, int r24, int r25, int[] r26) {
        if (r23 != 0) goto L6;
        return;
    L6:
        if (this.mChainList.size() != 0) goto L8;
        WidgetsList r0 = new WidgetsList(this, r24, this.mLeft, this.mTop, this.mRight, this.mBottom, r25);
        this.mChainList.add(r0);
    L9:
        int r1 = 0;
    L10:
        if (r1 >= r23) goto L12;
        r0.add(r22[r1]);
        r1 = r1 + 1;
        goto L10
    L12:
        r26[0] = r0.getWidth();
        r26[1] = r0.getHeight();
        return;
    L8:
        WidgetsList r10 = this.mChainList.get(0);
        r10.clear();
        r10.setup(r24, this.mLeft, this.mTop, this.mRight, this.mBottom, getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom(), r25);
        r0 = r10;
        goto L9
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public void addToSolver(LinearSystem r6, boolean r7) {
        super.addToSolver(r6, r7);
        if (getParent() != null) goto L5;
    L7:
        boolean r62 = false;
    L8:
        int r1 = this.mWrapMode;
        if (r1 == 0) goto L32;
        if (r1 != 1) goto L12;
        int r12 = this.mChainList.size();
        int r2 = 0;
    L25:
        if (r2 >= r12) goto L34;
        WidgetsList r3 = this.mChainList.get(r2);
        if (r2 != (r12 - 1)) goto L29;
        boolean r4 = true;
    L30:
        r3.createConstraints(r62, r2, r4);
        r2 = r2 + 1;
        goto L25
    L29:
        r4 = false;
    L34:
        needsCallbackFromSolver(false);
        return;
    L12:
        if (r1 != 2) goto L14;
        createAlignedConstraints(r62);
        goto L34
    L14:
        if (r1 != 3) goto L34;
        int r13 = this.mChainList.size();
        int r22 = 0;
    L17:
        if (r22 >= r13) goto L34;
        WidgetsList r32 = this.mChainList.get(r22);
        if (r22 != (r13 - 1)) goto L21;
        boolean r42 = true;
    L22:
        r32.createConstraints(r62, r22, r42);
        r22 = r22 + 1;
        goto L17
    L21:
        r42 = false;
        goto L22
    L32:
        if (this.mChainList.size() <= 0) goto L34;
        this.mChainList.get(0).createConstraints(r62, 0, true);
        goto L34
    L5:
        if (((ConstraintWidgetContainer) getParent()).isRtl() == false) goto L7;
        r62 = true;
        goto L8
    }

    @Override // androidx.constraintlayout.core.widgets.VirtualLayout
    public void measure(int r20, int r21, int r22, int r23) {
        if (this.mWidgetsCount > 0) goto L5;
    L8:
        int r11 = getPaddingLeft();
        int r12 = getPaddingRight();
        int r13 = getPaddingTop();
        int r14 = getPaddingBottom();
        int[] r5 = new int[2];
        int r2 = (r21 - r11) - r12;
        int r3 = this.mOrientation;
        if (r3 != 1) goto L11;
        r2 = (r23 - r13) - r14;
    L11:
        int r4 = r2;
        int r24 = this.mHorizontalStyle;
        if (r3 != 0) goto L18;
        if (r24 != (-1)) goto L16;
        this.mHorizontalStyle = 0;
    L16:
        if (this.mVerticalStyle != (-1)) goto L23;
        this.mVerticalStyle = 0;
    L23:
        ConstraintWidget[] r1 = this.mWidgets;
        int r25 = 0;
        int r32 = 0;
    L24:
        int r10 = this.mWidgetsCount;
        if (r25 >= r10) goto L30;
        if (this.mWidgets[r25].getVisibility() != 8) goto L29;
        r32 = r32 + 1;
    L29:
        r25 = r25 + 1;
        goto L24
    L30:
        if (r32 <= 0) goto L39;
        r1 = new ConstraintWidget[r10 - r32];
        int r26 = 0;
        r10 = 0;
    L33:
        if (r26 >= this.mWidgetsCount) goto L39;
        ConstraintWidget r33 = this.mWidgets[r26];
        ConstraintWidget[] r18 = r1;
        if (r33.getVisibility() == 8) goto L37;
        r18[r10] = r33;
        r10 = r10 + 1;
    L37:
        r26 = r26 + 1;
        r1 = r18;
    L39:
        int r27 = r10;
        this.mDisplayedWidgets = r1;
        this.mDisplayedWidgetsCount = r27;
        int r34 = this.mWrapMode;
        if (r34 != 0) goto L42;
        measureNoWrap(r1, r27, this.mOrientation, r4, r5);
    L52:
        int r15 = (r5[0] + r11) + r12;
        int r28 = (r5[1] + r13) + r14;
        if (r20 != 1073741824) goto L55;
        r15 = r21;
    L60:
        if (r22 != 1073741824) goto L62;
        r28 = r23;
    L67:
        setMeasure(r15, r28);
        setWidth(r15);
        setHeight(r28);
        if (this.mWidgetsCount <= 0) goto L70;
        boolean r102 = true;
    L71:
        needsCallbackFromSolver(r102);
        return;
    L70:
        r102 = false;
        goto L71
    L62:
        if (r22 != Integer.MIN_VALUE) goto L64;
        r28 = Math.min(r28, r23);
        goto L67
    L64:
        if (r22 == 0) goto L67;
        r28 = 0;
        goto L67
    L55:
        if (r20 != Integer.MIN_VALUE) goto L57;
        r15 = Math.min(r15, r21);
        goto L60
    L57:
        if (r20 == 0) goto L60;
        r15 = 0;
        goto L60
    L42:
        if (r34 != 1) goto L44;
        measureChainWrap(r1, r27, this.mOrientation, r4, r5);
        goto L52
    L44:
        if (r34 != 2) goto L46;
        measureAligned(r1, r27, this.mOrientation, r4, r5);
        goto L52
    L46:
        if (r34 != 3) goto L52;
        measureChainWrap_new(r1, r27, this.mOrientation, r4, r5);
        goto L52
    L18:
        if (r24 != (-1)) goto L21;
        this.mHorizontalStyle = 0;
    L21:
        if (this.mVerticalStyle != (-1)) goto L23;
        this.mVerticalStyle = 0;
        goto L23
    L5:
        if (measureChildren() == true) goto L8;
        setMeasure(0, 0);
        needsCallbackFromSolver(false);
    }

    public void setFirstHorizontalBias(float r1) {
        this.mFirstHorizontalBias = r1;
    }

    public void setFirstHorizontalStyle(int r1) {
        this.mFirstHorizontalStyle = r1;
    }

    public void setFirstVerticalBias(float r1) {
        this.mFirstVerticalBias = r1;
    }

    public void setFirstVerticalStyle(int r1) {
        this.mFirstVerticalStyle = r1;
    }

    public void setHorizontalAlign(int r1) {
        this.mHorizontalAlign = r1;
    }

    public void setHorizontalBias(float r1) {
        this.mHorizontalBias = r1;
    }

    public void setHorizontalGap(int r1) {
        this.mHorizontalGap = r1;
    }

    public void setHorizontalStyle(int r1) {
        this.mHorizontalStyle = r1;
    }

    public void setLastHorizontalBias(float r1) {
        this.mLastHorizontalBias = r1;
    }

    public void setLastHorizontalStyle(int r1) {
        this.mLastHorizontalStyle = r1;
    }

    public void setLastVerticalBias(float r1) {
        this.mLastVerticalBias = r1;
    }

    public void setLastVerticalStyle(int r1) {
        this.mLastVerticalStyle = r1;
    }

    public void setMaxElementsWrap(int r1) {
        this.mMaxElementsWrap = r1;
    }

    public void setOrientation(int r1) {
        this.mOrientation = r1;
    }

    public void setVerticalAlign(int r1) {
        this.mVerticalAlign = r1;
    }

    public void setVerticalBias(float r1) {
        this.mVerticalBias = r1;
    }

    public void setVerticalGap(int r1) {
        this.mVerticalGap = r1;
    }

    public void setVerticalStyle(int r1) {
        this.mVerticalStyle = r1;
    }

    public void setWrapMode(int r1) {
        this.mWrapMode = r1;
    }
}
