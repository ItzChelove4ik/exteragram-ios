package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.LinearLayout;
import androidx.appcompat.R$styleable;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.mediarouter.media.PlatformMediaRouter1RouteProvider;
import com.sun.jna.Structure$$ExternalSyntheticBUOutline2;
import org.telegram.tgnet.TLObject;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes3.dex */
public abstract class LinearLayoutCompat extends ViewGroup {
    private static final String ACCESSIBILITY_CLASS_NAME = "androidx.appcompat.widget.LinearLayoutCompat";
    public static final int HORIZONTAL = 0;
    private static final int INDEX_BOTTOM = 2;
    private static final int INDEX_CENTER_VERTICAL = 0;
    private static final int INDEX_FILL = 3;
    private static final int INDEX_TOP = 1;
    public static final int SHOW_DIVIDER_BEGINNING = 1;
    public static final int SHOW_DIVIDER_END = 4;
    public static final int SHOW_DIVIDER_MIDDLE = 2;
    public static final int SHOW_DIVIDER_NONE = 0;
    public static final int VERTICAL = 1;
    private static final int VERTICAL_GRAVITY_COUNT = 4;
    private boolean mBaselineAligned;
    private int mBaselineAlignedChildIndex;
    private int mBaselineChildTop;
    private Drawable mDivider;
    private int mDividerHeight;
    private int mDividerPadding;
    private int mDividerWidth;
    private int mGravity;
    private int[] mMaxAscent;
    private int[] mMaxDescent;
    private int mOrientation;
    private int mShowDividers;
    private int mTotalLength;
    private boolean mUseLargestChild;
    private float mWeightSum;

    public static class LayoutParams extends LinearLayout.LayoutParams {
        public LayoutParams(int r1, int r2) {
            super(r1, r2);
        }

        public LayoutParams(Context r1, AttributeSet r2) {
            super(r1, r2);
        }

        public LayoutParams(ViewGroup.LayoutParams r1) {
            super(r1);
        }

        public LayoutParams(ViewGroup.MarginLayoutParams r1) {
            super(r1);
        }
    }

    public LinearLayoutCompat(Context r2) {
        this(r2, null);
    }

    public LinearLayoutCompat(Context r2, AttributeSet r3) {
        this(r2, r3, 0);
    }

    public LinearLayoutCompat(Context r12, AttributeSet r13, int r14) {
        super(r12, r13, r14);
        this.mBaselineAligned = true;
        this.mBaselineAlignedChildIndex = -1;
        this.mBaselineChildTop = 0;
        this.mGravity = 8388659;
        TintTypedArray r3 = TintTypedArray.obtainStyledAttributes(r12, r13, R$styleable.LinearLayoutCompat, r14, 0);
        ViewCompat.saveAttributeDataForStyleable(this, r12, R$styleable.LinearLayoutCompat, r13, r3.getWrappedTypeArray(), r14, 0);
        int r11 = r3.getInt(R$styleable.LinearLayoutCompat_android_orientation, -1);
        if (r11 < 0) goto L5;
        setOrientation(r11);
    L5:
        int r112 = r3.getInt(R$styleable.LinearLayoutCompat_android_gravity, -1);
        if (r112 < 0) goto L8;
        setGravity(r112);
    L8:
        boolean r113 = r3.getBoolean(R$styleable.LinearLayoutCompat_android_baselineAligned, true);
        if (r113 == true) goto L11;
        setBaselineAligned(r113);
    L11:
        this.mWeightSum = r3.getFloat(R$styleable.LinearLayoutCompat_android_weightSum, -1.0f);
        this.mBaselineAlignedChildIndex = r3.getInt(R$styleable.LinearLayoutCompat_android_baselineAlignedChildIndex, -1);
        this.mUseLargestChild = r3.getBoolean(R$styleable.LinearLayoutCompat_measureWithLargestChild, false);
        setDividerDrawable(r3.getDrawable(R$styleable.LinearLayoutCompat_divider));
        this.mShowDividers = r3.getInt(R$styleable.LinearLayoutCompat_showDividers, 0);
        this.mDividerPadding = r3.getDimensionPixelSize(R$styleable.LinearLayoutCompat_dividerPadding, 0);
        r3.recycle();
    }

    private void forceUniformHeight(int r10, int r11) {
        int r6 = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), TLObject.FLAG_30);
        int r0 = 0;
    L3:
        if (r0 >= r10) goto L11;
        View r3 = this.getVirtualChildAt(r0);
        if (r3.getVisibility() == 8) goto L9;
        LayoutParams r1 = (LayoutParams) r3.getLayoutParams();
        if (((LinearLayout.LayoutParams) r1).height != (-1)) goto L9;
        int r8 = ((LinearLayout.LayoutParams) r1).width;
        ((LinearLayout.LayoutParams) r1).width = r3.getMeasuredWidth();
        LinearLayoutCompat r2 = this;
        int r4 = r11;
        r2.measureChildWithMargins(r3, r4, 0, r6, 0);
        ((LinearLayout.LayoutParams) r1).width = r8;
    L10:
        r0 = r0 + 1;
        LinearLayoutCompat this = r2;
        r11 = r4;
    L9:
        r2 = this;
        r4 = r11;
        goto L10
    }

    private void forceUniformWidth(int r10, int r11) {
        int r4 = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), TLObject.FLAG_30);
        int r0 = 0;
    L3:
        if (r0 >= r10) goto L11;
        View r3 = this.getVirtualChildAt(r0);
        if (r3.getVisibility() == 8) goto L9;
        LayoutParams r1 = (LayoutParams) r3.getLayoutParams();
        if (((LinearLayout.LayoutParams) r1).width != (-1)) goto L9;
        int r8 = ((LinearLayout.LayoutParams) r1).height;
        ((LinearLayout.LayoutParams) r1).height = r3.getMeasuredHeight();
        LinearLayoutCompat r2 = this;
        int r6 = r11;
        r2.measureChildWithMargins(r3, r4, 0, r6, 0);
        ((LinearLayout.LayoutParams) r1).height = r8;
    L10:
        r0 = r0 + 1;
        LinearLayoutCompat this = r2;
        r11 = r6;
    L9:
        r2 = this;
        r6 = r11;
        goto L10
    }

    private void setChildFrame(View r1, int r2, int r3, int r4, int r5) {
        r1.layout(r2, r3, r4 + r2, r5 + r3);
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams r1) {
        return r1 instanceof LayoutParams;
    }

    public void drawDividersHorizontal(Canvas r7) {
        int r0 = getVirtualChildCount();
        boolean r1 = ViewUtils.isLayoutRtl(this);
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L17;
        View r3 = getVirtualChildAt(r2);
        if (r3 == null) goto L15;
        if (r3.getVisibility() == 8) goto L15;
        if (hasDividerBeforeChildAt(r2) == false) goto L15;
        LayoutParams r4 = (LayoutParams) r3.getLayoutParams();
        if (r1 == false) goto L13;
        int r32 = r3.getRight() + ((LinearLayout.LayoutParams) r4).rightMargin;
    L14:
        drawVerticalDivider(r7, r32);
        goto L15
    L13:
        r32 = (r3.getLeft() - ((LinearLayout.LayoutParams) r4).leftMargin) - this.mDividerWidth;
    L15:
        r2 = r2 + 1;
        goto L3
    L17:
        if (hasDividerBeforeChildAt(r0) == false) goto L35;
        View r02 = getVirtualChildAt(r0 - 1);
        if (r02 != null) goto L24;
        if (r1 == false) goto L22;
        int r03 = getPaddingLeft();
    L28:
        drawVerticalDivider(r7, r03);
        return;
    L22:
        int r04 = getWidth() - getPaddingRight();
        int r12 = this.mDividerWidth;
    L23:
        r03 = r04 - r12;
        goto L28
    L24:
        LayoutParams r22 = (LayoutParams) r02.getLayoutParams();
        if (r1 == false) goto L27;
        r04 = r02.getLeft() - ((LinearLayout.LayoutParams) r22).leftMargin;
        r12 = this.mDividerWidth;
        goto L23
    L27:
        r03 = r02.getRight() + ((LinearLayout.LayoutParams) r22).rightMargin;
        goto L28
    }

    public void drawDividersVertical(Canvas r6) {
        int r0 = getVirtualChildCount();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L13;
        View r2 = getVirtualChildAt(r1);
        if (r2 == null) goto L11;
        if (r2.getVisibility() == 8) goto L11;
        if (hasDividerBeforeChildAt(r1) == false) goto L11;
        drawHorizontalDivider(r6, (r2.getTop() - ((LinearLayout.LayoutParams) ((LayoutParams) r2.getLayoutParams())).topMargin) - this.mDividerHeight);
    L11:
        r1 = r1 + 1;
        goto L3
    L13:
        if (hasDividerBeforeChildAt(r0) == false) goto L25;
        View r02 = getVirtualChildAt(r0 - 1);
        if (r02 != null) goto L17;
        int r03 = (getHeight() - getPaddingBottom()) - this.mDividerHeight;
    L18:
        drawHorizontalDivider(r6, r03);
        return;
    L17:
        r03 = r02.getBottom() + ((LinearLayout.LayoutParams) ((LayoutParams) r02.getLayoutParams())).bottomMargin;
        goto L18
    }

    public void drawHorizontalDivider(Canvas r5, int r6) {
        this.mDivider.setBounds(getPaddingLeft() + this.mDividerPadding, r6, (getWidth() - getPaddingRight()) - this.mDividerPadding, this.mDividerHeight + r6);
        this.mDivider.draw(r5);
    }

    public void drawVerticalDivider(Canvas r6, int r7) {
        this.mDivider.setBounds(r7, getPaddingTop() + this.mDividerPadding, this.mDividerWidth + r7, (getHeight() - getPaddingBottom()) - this.mDividerPadding);
        this.mDivider.draw(r6);
    }

    @Override // android.view.ViewGroup
    public /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return generateDefaultLayoutParams();
    }

    @Override // android.view.ViewGroup
    public LayoutParams generateDefaultLayoutParams() {
        int r2 = this.mOrientation;
        if (r2 != 0) goto L7;
        return new LayoutParams(-2, -2);
    L7:
        if (r2 == 1) goto L9;
        return null;
    L9:
        return new LayoutParams(-1, -2);
    }

    @Override // android.view.ViewGroup
    public /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(AttributeSet r1) {
        return generateLayoutParams(r1);
    }

    @Override // android.view.ViewGroup
    public /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams r1) {
        return generateLayoutParams(r1);
    }

    @Override // android.view.ViewGroup
    public LayoutParams generateLayoutParams(AttributeSet r2) {
        return new LayoutParams(getContext(), r2);
    }

    @Override // android.view.ViewGroup
    public LayoutParams generateLayoutParams(ViewGroup.LayoutParams r1) {
        if ((r1 instanceof LayoutParams) == false) goto L7;
        return new LayoutParams((LayoutParams) r1);
    L7:
        if ((r1 instanceof ViewGroup.MarginLayoutParams) == false) goto L11;
        return new LayoutParams((ViewGroup.MarginLayoutParams) r1);
    L11:
        return new LayoutParams(r1);
    }

    @Override // android.view.View
    public int getBaseline() {
        if (this.mBaselineAlignedChildIndex < 0) goto L5;
        int r0 = getChildCount();
        int r1 = this.mBaselineAlignedChildIndex;
        if (r0 <= r1) goto L28;
        View r02 = getChildAt(r1);
        int r12 = r02.getBaseline();
        if (r12 == (-1)) goto L11;
        int r2 = this.mBaselineChildTop;
        if (this.mOrientation != 1) goto L27;
        int r3 = this.mGravity & 112;
        if (r3 == 48) goto L27;
        if (r3 != 16) goto L22;
        r2 = r2 + (((((getBottom() - getTop()) - getPaddingTop()) - getPaddingBottom()) - this.mTotalLength) / 2);
        goto L27
    L22:
        if (r3 != 80) goto L27;
        r2 = ((getBottom() - getTop()) - getPaddingBottom()) - this.mTotalLength;
    L27:
        return (r2 + ((LinearLayout.LayoutParams) ((LayoutParams) r02.getLayoutParams())).topMargin) + r12;
    L11:
        if (this.mBaselineAlignedChildIndex != 0) goto L13;
        return -1;
    L13:
        GlShader$$ExternalSyntheticBUOutline1.m("mBaselineAlignedChildIndex of LinearLayout points to a View that doesn't know how to get its baseline.");
        return 0;
    L28:
        GlShader$$ExternalSyntheticBUOutline1.m("mBaselineAlignedChildIndex of LinearLayout set to an index that is out of bounds.");
        return 0;
    L5:
        return super.getBaseline();
    }

    public int getBaselineAlignedChildIndex() {
        return this.mBaselineAlignedChildIndex;
    }

    public int getChildrenSkipCount(View r1, int r2) {
        return 0;
    }

    public Drawable getDividerDrawable() {
        return this.mDivider;
    }

    public int getDividerPadding() {
        return this.mDividerPadding;
    }

    public int getDividerWidth() {
        return this.mDividerWidth;
    }

    public int getGravity() {
        return this.mGravity;
    }

    public int getLocationOffset(View r1) {
        return 0;
    }

    public int getNextLocationOffset(View r1) {
        return 0;
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    public int getShowDividers() {
        return this.mShowDividers;
    }

    public View getVirtualChildAt(int r1) {
        return getChildAt(r1);
    }

    public int getVirtualChildCount() {
        return getChildCount();
    }

    public float getWeightSum() {
        return this.mWeightSum;
    }

    public boolean hasDividerBeforeChildAt(int r5) {
        if (r5 == 0) goto L5;
        int r2 = getChildCount();
        int r3 = this.mShowDividers;
        if (r5 != r2) goto L15;
        if ((r3 & 4) == 0) goto L13;
        return true;
    L13:
        return false;
    L15:
        if ((r3 & 2) == 0) goto L22;
        int r52 = r5 - 1;
    L17:
        if (r52 < 0) goto L22;
        if (getChildAt(r52).getVisibility() != 8) goto L20;
        r52 = r52 - 1;
        goto L17
    L20:
        return true;
    L22:
        return false;
    L5:
        if ((this.mShowDividers & 1) == 0) goto L7;
        return true;
    L7:
        return false;
    }

    public boolean isBaselineAligned() {
        return this.mBaselineAligned;
    }

    public boolean isMeasureWithLargestChildEnabled() {
        return this.mUseLargestChild;
    }

    public void layoutHorizontal(int r22, int r23, int r24, int r25) {
        boolean r1 = ViewUtils.isLayoutRtl(this);
        int r6 = getPaddingTop();
        int r2 = r25 - r23;
        int r7 = r2 - getPaddingBottom();
        int r8 = (r2 - r6) - getPaddingBottom();
        int r9 = getVirtualChildCount();
        int r26 = this.mGravity;
        int r10 = r26 & 112;
        boolean r11 = this.mBaselineAligned;
        int[] r12 = this.mMaxAscent;
        int[] r13 = this.mMaxDescent;
        int r27 = GravityCompat.getAbsoluteGravity(8388615 & r26, getLayoutDirection());
        char r14 = 2;
        boolean r15 = true;
        if (r27 != 1) goto L5;
        int r28 = getPaddingLeft() + (((r24 - r22) - this.mTotalLength) / 2);
    L10:
        if (r1 == false) goto L12;
        int r16 = r9 - 1;
        int r17 = -1;
    L13:
        int r18 = 0;
    L14:
        if (r18 >= r9) goto L52;
        int r3 = r16 + (r17 * r18);
        int r5 = r18;
        View r19 = getVirtualChildAt(r3);
        if (r19 != null) goto L18;
        r28 = r28 + measureNullChild(r3);
        int r110 = r5;
        int r192 = r6;
        char r232 = r14;
        boolean r252 = r15;
    L51:
        r18 = r110 + 1;
        r14 = r232;
        r15 = r252;
        r6 = r192;
        goto L14
    L18:
        r232 = r14;
        r252 = r15;
        if (r19.getVisibility() == 8) goto L50;
        int r142 = r19.getMeasuredWidth();
        int r52 = r19.getMeasuredHeight();
        LayoutParams r4 = (LayoutParams) r19.getLayoutParams();
        int r242 = r28;
        if (r11 == false) goto L25;
        int r182 = r52;
        if (((LinearLayout.LayoutParams) r4).height == (-1)) goto L26;
        int r53 = r19.getBaseline();
    L27:
        int r29 = ((LinearLayout.LayoutParams) r4).gravity;
        if (r29 >= 0) goto L30;
        r29 = r10;
    L30:
        int r210 = r29 & 112;
        r192 = r6;
        if (r210 != 16) goto L33;
        int r211 = (r192 + ((r8 - r182) / 2)) + ((LinearLayout.LayoutParams) r4).topMargin;
        int r54 = ((LinearLayout.LayoutParams) r4).bottomMargin;
    L40:
        r211 = r211 - r54;
    L46:
        if (hasDividerBeforeChildAt(r3) == false) goto L48;
        int r55 = r242 + this.mDividerWidth;
    L49:
        int r62 = ((LinearLayout.LayoutParams) r4).leftMargin + r55;
        setChildFrame(r19, getLocationOffset(r19) + r62, r211, r142, r182);
        int r63 = r62 + ((((LinearLayout.LayoutParams) r4).rightMargin + r142) + getNextLocationOffset(r19));
        r110 = getChildrenSkipCount(r19, r3) + r5;
        r28 = r63;
        goto L51
    L48:
        r55 = r242;
        goto L49
    L33:
        if (r210 != 48) goto L35;
        r211 = r192 + ((LinearLayout.LayoutParams) r4).topMargin;
        if (r53 == (-1)) goto L46;
        r211 = r211 + (r12[r252 ? 1 : 0] - r53);
        goto L46
    L35:
        if (r210 == 80) goto L37;
        r211 = r192;
        goto L46
    L37:
        r211 = (r7 - r182) - ((LinearLayout.LayoutParams) r4).bottomMargin;
        if (r53 == (-1)) goto L46;
        r54 = r13[r232] - (r19.getMeasuredHeight() - r53);
    L26:
        r53 = -1;
        goto L27
    L25:
        r182 = r52;
        goto L26
    L50:
        r192 = r6;
        r110 = r5;
        goto L51
    L52:
        return;
    L12:
        r16 = 0;
        r17 = 1;
        goto L13
    L5:
        if (r27 == 5) goto L7;
        r28 = getPaddingLeft();
        goto L10
    L7:
        r28 = ((getPaddingLeft() + r24) - r22) - this.mTotalLength;
        goto L10
    }

    public void layoutVertical(int r12, int r13, int r14, int r15) {
        int r0 = getPaddingLeft();
        int r142 = r14 - r12;
        int r122 = r142 - getPaddingRight();
        int r143 = (r142 - r0) - getPaddingRight();
        int r1 = getVirtualChildCount();
        int r2 = this.mGravity;
        int r3 = r2 & 112;
        int r22 = r2 & 8388615;
        if (r3 != 16) goto L5;
        int r132 = getPaddingTop() + (((r15 - r13) - this.mTotalLength) / 2);
    L9:
        int r152 = 0;
    L10:
        if (r152 >= r1) goto L34;
        View r4 = this.getVirtualChildAt(r152);
        if (r4 != null) goto L16;
        r132 = r132 + this.measureNullChild(r152);
    L14:
        LinearLayoutCompat r32 = this;
    L33:
        r152 = r152 + 1;
        LinearLayoutCompat this = r32;
        goto L10
    L16:
        if (r4.getVisibility() == 8) goto L14;
        int r7 = r4.getMeasuredWidth();
        int r8 = r4.getMeasuredHeight();
        LayoutParams r10 = (LayoutParams) r4.getLayoutParams();
        int r33 = ((LinearLayout.LayoutParams) r10).gravity;
        if (r33 >= 0) goto L20;
        r33 = r22;
    L20:
        int r34 = GravityCompat.getAbsoluteGravity(r33, this.getLayoutDirection()) & 7;
        if (r34 != 1) goto L23;
        int r35 = (((r143 - r7) / 2) + r0) + ((LinearLayout.LayoutParams) r10).leftMargin;
        int r5 = ((LinearLayout.LayoutParams) r10).rightMargin;
    L27:
        int r36 = r35 - r5;
    L25:
        int r52 = r36;
        if (this.hasDividerBeforeChildAt(r152) == false) goto L32;
        r132 = r132 + this.mDividerHeight;
    L32:
        int r133 = r132 + ((LinearLayout.LayoutParams) r10).topMargin;
        int r6 = r133 + this.getLocationOffset(r4);
        r32 = this;
        r32.setChildFrame(r4, r52, r6, r7, r8);
        r132 = r133 + ((r8 + ((LinearLayout.LayoutParams) r10).bottomMargin) + r32.getNextLocationOffset(r4));
        r152 = r152 + r32.getChildrenSkipCount(r4, r152);
        goto L33
    L23:
        if (r34 == 5) goto L26;
        r36 = ((LinearLayout.LayoutParams) r10).leftMargin + r0;
        goto L25
    L26:
        r35 = r122 - r7;
        r5 = ((LinearLayout.LayoutParams) r10).rightMargin;
        goto L27
    L34:
        return;
    L5:
        if (r3 == 80) goto L7;
        r132 = getPaddingTop();
        goto L9
    L7:
        r132 = ((getPaddingTop() + r15) - r13) - this.mTotalLength;
        goto L9
    }

    public void measureChildBeforeLayout(View r1, int r2, int r3, int r4, int r5, int r6) {
        measureChildWithMargins(r1, r3, r4, r5, r6);
    }

    public void measureHorizontal(int r39, int r40) {
        this.mTotalLength = 0;
        int r8 = getVirtualChildCount();
        int r9 = View.MeasureSpec.getMode(r39);
        int r10 = View.MeasureSpec.getMode(r40);
        if (this.mMaxAscent != null) goto L5;
    L6:
        this.mMaxAscent = new int[4];
        this.mMaxDescent = new int[4];
    L7:
        int[] r12 = this.mMaxAscent;
        int[] r13 = this.mMaxDescent;
        r12[3] = -1;
        r12[2] = -1;
        r12[1] = -1;
        r12[0] = -1;
        r13[3] = -1;
        r13[2] = -1;
        r13[1] = -1;
        r13[0] = -1;
        boolean r1 = this.mBaselineAligned;
        boolean r2 = this.mUseLargestChild;
        int r3 = TLObject.FLAG_30;
        if (r9 != 1073741824) goto L10;
        boolean r18 = true;
    L11:
        boolean r4 = r2;
        int r22 = 0;
        int r6 = 0;
        int r15 = 0;
        boolean r222 = false;
        int r24 = 0;
        boolean r26 = false;
        boolean r25 = true;
        float r5 = 0.0f;
        int r11 = 0;
        int r14 = 0;
    L12:
        int r27 = r6;
        if (r22 >= r8) goto L89;
        boolean r30 = r1;
        View r16 = getVirtualChildAt(r22);
        if (r16 != null) goto L19;
        this.mTotalLength += measureNullChild(r22);
    L17:
        int r35 = r8;
        int r33 = r9;
        int[] r34 = r12;
        int[] r28 = r13;
        r6 = r27;
        boolean r272 = r4;
    L88:
        r22 = r22 + 1;
        r4 = r272;
        r13 = r28;
        r1 = r30;
        r9 = r33;
        r12 = r34;
        r8 = r35;
        r3 = TLObject.FLAG_30;
        goto L12
    L19:
        if (r16.getVisibility() != 8) goto L22;
        r22 = r22 + getChildrenSkipCount(r16, r22);
        goto L17
    L22:
        if (hasDividerBeforeChildAt(r22) == false) goto L24;
        this.mTotalLength += this.mDividerWidth;
    L24:
        LayoutParams r7 = (LayoutParams) r16.getLayoutParams();
        float r62 = ((LinearLayout.LayoutParams) r7).weight;
        float r32 = r5 + r62;
        if (r9 != r3) goto L39;
        if (((LinearLayout.LayoutParams) r7).width != 0) goto L39;
        if (r62 <= 0.0f) goto L39;
        int r52 = this.mTotalLength;
        if (r18 == false) goto L33;
        this.mTotalLength = r52 + (((LinearLayout.LayoutParams) r7).leftMargin + ((LinearLayout.LayoutParams) r7).rightMargin);
    L34:
        if (r30 == false) goto L36;
        int r53 = View.MeasureSpec.makeMeasureSpec(0, 0);
        r16.measure(r53, r53);
        r35 = r8;
        r33 = r9;
        r34 = r12;
        r28 = r13;
    L37:
        int r122 = r27;
        int r82 = TLObject.FLAG_30;
        r272 = r4;
        View r42 = r16;
    L57:
        if (r10 != r82) goto L59;
    L61:
        boolean r54 = false;
    L62:
        int r63 = ((LinearLayout.LayoutParams) r7).topMargin + ((LinearLayout.LayoutParams) r7).bottomMargin;
        int r92 = r42.getMeasuredHeight() + r63;
        r24 = View.combineMeasuredStates(r24, r42.getMeasuredState());
        if (r30 == false) goto L70;
        int r83 = r42.getBaseline();
        if (r83 == (-1)) goto L70;
        int r132 = ((LinearLayout.LayoutParams) r7).gravity;
        if (r132 >= 0) goto L69;
        r132 = this.mGravity;
    L69:
        int r133 = (((r132 & 112) >> 4) & (-2)) >> 1;
        boolean r36 = r54;
        r34[r133] = Math.max(r34[r133], r83);
        r28[r133] = Math.max(r28[r133], r92 - r83);
    L71:
        int r55 = Math.max(r122, r92);
        if (r25 == true) goto L74;
    L76:
        r25 = false;
    L78:
        if (((LinearLayout.LayoutParams) r7).weight <= 0.0f) goto L83;
        if (r36 == true) goto L82;
        r63 = r92;
    L82:
        r14 = Math.max(r14, r63);
    L87:
        r22 = r22 + getChildrenSkipCount(r42, r22);
        r6 = r55;
        r5 = r32;
        goto L88
    L83:
        if (r36 == true) goto L86;
        r63 = r92;
    L86:
        r11 = Math.max(r11, r63);
        goto L87
    L74:
        if (((LinearLayout.LayoutParams) r7).height != (-1)) goto L76;
        r25 = true;
    L70:
        r36 = r54;
        goto L71
    L59:
        if (((LinearLayout.LayoutParams) r7).height != (-1)) goto L61;
        r54 = true;
        r26 = true;
        goto L62
    L36:
        r35 = r8;
        r33 = r9;
        r34 = r12;
        r28 = r13;
        r222 = true;
        goto L37
    L33:
        this.mTotalLength = Math.max(r52, (((LinearLayout.LayoutParams) r7).leftMargin + r52) + ((LinearLayout.LayoutParams) r7).rightMargin);
    L39:
        if (((LinearLayout.LayoutParams) r7).width == 0) goto L41;
    L43:
        char r37 = 65534;
        int r56 = Integer.MIN_VALUE;
    L45:
        if (r32 != 0.0f) goto L47;
        boolean r64 = r4;
        int r43 = this.mTotalLength;
    L48:
        r35 = r8;
        r33 = r9;
        r34 = r12;
        r122 = r27;
        r82 = TLObject.FLAG_30;
        r272 = r64;
        r28 = r13;
        int r134 = r56;
        measureChildBeforeLayout(r16, r22, r39, r43, r40, 0);
        r42 = r16;
        if (r134 == Integer.MIN_VALUE) goto L51;
        ((LinearLayout.LayoutParams) r7).width = r134;
    L51:
        int r57 = r42.getMeasuredWidth();
        int r65 = this.mTotalLength;
        if (r18 == false) goto L54;
        this.mTotalLength = r65 + (((((LinearLayout.LayoutParams) r7).leftMargin + r57) + ((LinearLayout.LayoutParams) r7).rightMargin) + getNextLocationOffset(r42));
    L55:
        if (r272 == false) goto L57;
        r15 = Math.max(r57, r15);
        goto L57
    L54:
        this.mTotalLength = Math.max(r65, (((r65 + r57) + ((LinearLayout.LayoutParams) r7).leftMargin) + ((LinearLayout.LayoutParams) r7).rightMargin) + getNextLocationOffset(r42));
        goto L55
    L47:
        r64 = r4;
        r43 = 0;
        goto L48
    L41:
        if (r62 <= 0.0f) goto L43;
        r37 = 65534;
        ((LinearLayout.LayoutParams) r7).width = -2;
        r56 = 0;
        goto L45
    L89:
        boolean r302 = r1;
        int r352 = r8;
        int r332 = r9;
        int[] r342 = r12;
        int[] r282 = r13;
        int r84 = r24;
        boolean r273 = r4;
        if (this.mTotalLength <= 0) goto L94;
        int r23 = r352;
        if (hasDividerBeforeChildAt(r23) == false) goto L95;
        this.mTotalLength += this.mDividerWidth;
    L95:
        int r44 = r342[1];
        if (r44 == (-1)) goto L98;
    L105:
        int r66 = Math.max(r27, Math.max(r342[3], Math.max(r342[0], Math.max(r44, r342[2]))) + Math.max(r282[3], Math.max(r282[0], Math.max(r282[1], r282[2]))));
    L106:
        if (r273 == false) goto L126;
        int r45 = r332;
        if (r45 == Integer.MIN_VALUE) goto L112;
        if (r45 == 0) goto L112;
    L111:
        float r322 = r5;
    L127:
        int r58 = this.mTotalLength + (getPaddingLeft() + getPaddingRight());
        this.mTotalLength = r58;
        int r59 = View.resolveSizeAndState(Math.max(r58, getSuggestedMinimumWidth()), r39, 0);
        int r72 = (16777215 & r59) - this.mTotalLength;
        if (r222 == true) goto L150;
        if (r72 != 0) goto L131;
    L133:
        int r73 = Math.max(r11, r14);
        if (r273 == true) goto L136;
    L148:
        int r274 = r59;
        int r223 = -16777216;
    L149:
        int r242 = r84;
        if (r25 == false) goto L220;
    L222:
        setMeasuredDimension(r274 | (r242 & r223), View.resolveSizeAndState(Math.max(r66 + (getPaddingTop() + getPaddingBottom()), getSuggestedMinimumHeight()), r40, r242 << 16));
        if (r26 == false) goto L241;
        forceUniformHeight(r23, r39);
        return;
    L241:
        return;
    L220:
        if (r10 == 1073741824) goto L222;
        r66 = r73;
        goto L222
    L136:
        if (r45 == 1073741824) goto L148;
        int r46 = 0;
    L138:
        if (r46 >= r23) goto L148;
        View r112 = getVirtualChildAt(r46);
        if (r112 == null) goto L147;
        if (r112.getVisibility() == 8) goto L147;
        if (((LinearLayout.LayoutParams) ((LayoutParams) r112.getLayoutParams())).weight <= 0.0f) goto L147;
        r112.measure(View.MeasureSpec.makeMeasureSpec(r15, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r112.getMeasuredHeight(), TLObject.FLAG_30));
    L147:
        r46 = r46 + 1;
        goto L138
    L131:
        if (r322 <= 0.0f) goto L133;
    L150:
        float r67 = this.mWeightSum;
        if (r67 <= 0.0f) goto L153;
        r322 = r67;
    L153:
        r342[3] = -1;
        r342[2] = -1;
        r342[1] = -1;
        r342[0] = -1;
        r282[3] = -1;
        r282[2] = -1;
        r282[1] = -1;
        r282[0] = -1;
        this.mTotalLength = 0;
        r66 = -1;
        int r123 = 0;
    L154:
        if (r123 >= r23) goto L208;
        View r135 = getVirtualChildAt(r123);
        if (r135 != null) goto L158;
    L159:
        int r275 = r59;
    L207:
        r123 = r123 + 1;
        r59 = r275;
        goto L154
    L158:
        if (r135.getVisibility() == 8) goto L159;
        LayoutParams r142 = (LayoutParams) r135.getLayoutParams();
        float r93 = ((LinearLayout.LayoutParams) r142).weight;
        if (r93 <= 0.0f) goto L177;
        int r152 = (int) ((r72 * r93) / r322);
        r322 = r322 - r93;
        r72 = r72 - r152;
        r275 = r59;
        int r510 = ViewGroup.getChildMeasureSpec(r40, ((getPaddingTop() + getPaddingBottom()) + ((LinearLayout.LayoutParams) r142).topMargin) + ((LinearLayout.LayoutParams) r142).bottomMargin, ((LinearLayout.LayoutParams) r142).height);
        if (((LinearLayout.LayoutParams) r142).width != 0) goto L171;
        int r94 = TLObject.FLAG_30;
        if (r45 != 1073741824) goto L172;
        if (r152 > 0) goto L170;
        r152 = 0;
    L170:
        r135.measure(View.MeasureSpec.makeMeasureSpec(r152, TLObject.FLAG_30), r510);
    L176:
        r84 = View.combineMeasuredStates(r84, r135.getMeasuredState() & (-16777216));
    L178:
        int r511 = this.mTotalLength;
        if (r18 == false) goto L182;
        this.mTotalLength = r511 + (((r135.getMeasuredWidth() + ((LinearLayout.LayoutParams) r142).leftMargin) + ((LinearLayout.LayoutParams) r142).rightMargin) + getNextLocationOffset(r135));
    L183:
        if (r10 != 1073741824) goto L185;
    L187:
        boolean r512 = false;
    L188:
        int r95 = ((LinearLayout.LayoutParams) r142).topMargin + ((LinearLayout.LayoutParams) r142).bottomMargin;
        int r153 = r135.getMeasuredHeight() + r95;
        r66 = Math.max(r66, r153);
        if (r512 == true) goto L192;
        r95 = r153;
    L192:
        int r513 = Math.max(r11, r95);
        if (r25 == false) goto L197;
        int r113 = -1;
        if (((LinearLayout.LayoutParams) r142).height != (-1)) goto L198;
        boolean r96 = true;
    L199:
        if (r302 == false) goto L206;
        int r136 = r135.getBaseline();
        if (r136 == r113) goto L206;
        int r114 = ((LinearLayout.LayoutParams) r142).gravity;
        if (r114 >= 0) goto L205;
        r114 = this.mGravity;
    L205:
        int r115 = (((r114 & 112) >> 4) & (-2)) >> 1;
        r342[r115] = Math.max(r342[r115], r136);
        r282[r115] = Math.max(r282[r115], r153 - r136);
    L206:
        r11 = r513;
        r25 = r96;
    L198:
        r96 = false;
        goto L199
    L197:
        r113 = -1;
        goto L198
    L185:
        if (((LinearLayout.LayoutParams) r142).height != (-1)) goto L187;
        r512 = true;
        goto L188
    L182:
        this.mTotalLength = Math.max(r511, (((r135.getMeasuredWidth() + r511) + ((LinearLayout.LayoutParams) r142).leftMargin) + ((LinearLayout.LayoutParams) r142).rightMargin) + getNextLocationOffset(r135));
    L172:
        int r154 = r135.getMeasuredWidth() + r152;
        if (r154 >= 0) goto L175;
        r154 = 0;
    L175:
        r135.measure(View.MeasureSpec.makeMeasureSpec(r154, r94), r510);
        goto L176
    L171:
        r94 = TLObject.FLAG_30;
        goto L172
    L177:
        r275 = r59;
        goto L178
    L208:
        r274 = r59;
        r223 = -16777216;
        this.mTotalLength += getPaddingLeft() + getPaddingRight();
        int r47 = r342[1];
        if (r47 == (-1)) goto L211;
    L216:
        r66 = Math.max(r66, Math.max(r342[3], Math.max(r342[0], Math.max(r47, r342[2]))) + Math.max(r282[3], Math.max(r282[0], Math.max(r282[1], r282[2]))));
    L217:
        r73 = r11;
        goto L149
    L211:
        if (r342[0] != (-1)) goto L216;
        if (r342[2] != (-1)) goto L216;
        if (r342[3] == (-1)) goto L217;
    L112:
        this.mTotalLength = 0;
        int r74 = 0;
    L113:
        if (r74 >= r23) goto L111;
        View r97 = getVirtualChildAt(r74);
        if (r97 != null) goto L118;
        this.mTotalLength += measureNullChild(r74);
    L120:
        float r323 = r5;
    L125:
        r74 = r74 + 1;
        r5 = r323;
        goto L113
    L118:
        if (r97.getVisibility() != 8) goto L121;
        r74 = r74 + getChildrenSkipCount(r97, r74);
        goto L120
    L121:
        LayoutParams r124 = (LayoutParams) r97.getLayoutParams();
        int r137 = this.mTotalLength;
        if (r18 == false) goto L124;
        r323 = r5;
        this.mTotalLength = r137 + (((((LinearLayout.LayoutParams) r124).leftMargin + r15) + ((LinearLayout.LayoutParams) r124).rightMargin) + getNextLocationOffset(r97));
        goto L125
    L124:
        r323 = r5;
        this.mTotalLength = Math.max(r137, (((r137 + r15) + ((LinearLayout.LayoutParams) r124).leftMargin) + ((LinearLayout.LayoutParams) r124).rightMargin) + getNextLocationOffset(r97));
        goto L125
    L126:
        r322 = r5;
        r45 = r332;
        goto L127
    L98:
        if (r342[0] != (-1)) goto L105;
        if (r342[2] != (-1)) goto L105;
        if (r342[3] != (-1)) goto L105;
        r66 = r27;
        goto L106
    L94:
        r23 = r352;
        goto L95
    L10:
        r18 = false;
        goto L11
    L5:
        if (this.mMaxDescent != null) goto L7;
        goto L6
    }

    public int measureNullChild(int r1) {
        return 0;
    }

    public void measureVertical(int r28, int r29) {
        this.mTotalLength = 0;
        int r8 = getVirtualChildCount();
        int r9 = View.MeasureSpec.getMode(r28);
        int r10 = View.MeasureSpec.getMode(r29);
        int r11 = this.mBaselineAlignedChildIndex;
        boolean r12 = this.mUseLargestChild;
        int r2 = 0;
        int r3 = 0;
        int r4 = 0;
        int r5 = 0;
        int r6 = 0;
        int r15 = 0;
        boolean r16 = false;
        boolean r18 = false;
        float r1 = 0.0f;
        boolean r17 = true;
    L3:
        int r7 = 8;
        if (r2 >= r8) goto L74;
        float r21 = r1;
        View r13 = getVirtualChildAt(r2);
        if (r13 != null) goto L10;
        this.mTotalLength += measureNullChild(r2);
    L8:
        int r14 = r3;
        int r26 = r8;
        int r25 = r10;
        boolean r24 = r12;
        r1 = r21;
        int r102 = r5;
    L73:
        r2 = r2 + 1;
        r5 = r102;
        r3 = r14;
        r12 = r24;
        r10 = r25;
        r8 = r26;
        goto L3
    L10:
        if (r13.getVisibility() != 8) goto L13;
        r2 = r2 + getChildrenSkipCount(r13, r2);
        goto L8
    L13:
        if (hasDividerBeforeChildAt(r2) == false) goto L15;
        this.mTotalLength += this.mDividerHeight;
    L15:
        LayoutParams r72 = (LayoutParams) r13.getLayoutParams();
        float r132 = ((LinearLayout.LayoutParams) r72).weight;
        float r212 = r21 + r132;
        if (r10 != 1073741824) goto L23;
        if (((LinearLayout.LayoutParams) r72).height != 0) goto L23;
        if (r132 <= 0.0f) goto L23;
        int r133 = this.mTotalLength;
        this.mTotalLength = Math.max(r133, (((LinearLayout.LayoutParams) r72).topMargin + r133) + ((LinearLayout.LayoutParams) r72).bottomMargin);
        r14 = r3;
        r26 = r8;
        r25 = r10;
        r16 = true;
        View r19 = r13;
        int r103 = r5;
        int r82 = r6;
        r24 = r12;
    L40:
        if (r11 >= 0) goto L42;
    L44:
        if (r2 >= r11) goto L50;
        if (((LinearLayout.LayoutParams) r72).weight <= 0.0f) goto L50;
        GlShader$$ExternalSyntheticBUOutline1.m("A child of LinearLayout with index less than mBaselineAlignedChildIndex has weight > 0, which won't work.  Either remove the weight, or don't set mBaselineAlignedChildIndex.");
        return;
    L50:
        if (r9 != 1073741824) goto L52;
    L54:
        boolean r62 = false;
    L55:
        int r122 = ((LinearLayout.LayoutParams) r72).leftMargin + ((LinearLayout.LayoutParams) r72).rightMargin;
        int r134 = r19.getMeasuredWidth() + r122;
        r102 = Math.max(r103, r134);
        int r22 = r4;
        int r42 = View.combineMeasuredStates(r82, r19.getMeasuredState());
        if (r17 == false) goto L60;
        int r23 = r42;
        if (((LinearLayout.LayoutParams) r72).width != (-1)) goto L61;
        r17 = true;
    L63:
        if (((LinearLayout.LayoutParams) r72).weight <= 0.0f) goto L68;
        if (r62 == true) goto L67;
        r122 = r134;
    L67:
        r14 = Math.max(r14, r122);
    L72:
        r2 = r2 + getChildrenSkipCount(r19, r2);
        r1 = r212;
        r4 = r22;
        r6 = r23;
        goto L73
    L68:
        if (r62 == true) goto L71;
        r122 = r134;
    L71:
        r15 = Math.max(r15, r122);
    L61:
        r17 = false;
        goto L63
    L60:
        r23 = r42;
        goto L61
    L52:
        if (((LinearLayout.LayoutParams) r72).width != (-1)) goto L54;
        r62 = true;
        r18 = true;
        goto L55
    L42:
        if (r11 != (r2 + 1)) goto L44;
        this.mBaselineChildTop = this.mTotalLength;
    L23:
        if (((LinearLayout.LayoutParams) r72).height == 0) goto L25;
    L27:
        int r135 = Integer.MIN_VALUE;
    L29:
        if (r212 != 0.0f) goto L32;
        int r142 = r6;
        int r63 = this.mTotalLength;
        int r110 = r142;
    L31:
        int r143 = r4;
        r26 = r8;
        r25 = r10;
        r82 = r110;
        r103 = r5;
        r19 = r13;
        r24 = r12;
        r14 = r3;
        measureChildBeforeLayout(r19, r2, r28, 0, r29, r63);
        if (r135 == Integer.MIN_VALUE) goto L36;
        ((LinearLayout.LayoutParams) r72).height = r135;
    L36:
        int r43 = r19.getMeasuredHeight();
        int r64 = this.mTotalLength;
        this.mTotalLength = Math.max(r64, (((r64 + r43) + ((LinearLayout.LayoutParams) r72).topMargin) + ((LinearLayout.LayoutParams) r72).bottomMargin) + getNextLocationOffset(r19));
        if (r24 == false) goto L39;
        r4 = Math.max(r43, r143);
        goto L40
    L39:
        r4 = r143;
        goto L40
    L32:
        r110 = r6;
        r63 = 0;
        goto L31
    L25:
        if (r132 <= 0.0f) goto L27;
        ((LinearLayout.LayoutParams) r72).height = -2;
        r135 = 0;
        goto L29
    L74:
        float r213 = r1;
        int r144 = r3;
        int r262 = r8;
        int r252 = r10;
        boolean r242 = r12;
        int r123 = r4;
        int r104 = r5;
        int r83 = r6;
        if (this.mTotalLength <= 0) goto L79;
        int r111 = r262;
        if (hasDividerBeforeChildAt(r111) == false) goto L80;
        this.mTotalLength += this.mDividerHeight;
    L80:
        int r27 = r252;
        if (r242 == true) goto L83;
    L95:
        int r44 = this.mTotalLength + (getPaddingTop() + getPaddingBottom());
        this.mTotalLength = r44;
        int r45 = View.resolveSizeAndState(Math.max(r44, getSuggestedMinimumHeight()), r29, 0);
        int r65 = (16777215 & r45) - this.mTotalLength;
        if (r16 == true) goto L116;
        if (r65 != 0) goto L99;
    L101:
        int r66 = Math.max(r15, r144);
        if (r242 == true) goto L104;
    L159:
        if (r17 == false) goto L161;
    L163:
        setMeasuredDimension(View.resolveSizeAndState(Math.max(r104 + (getPaddingLeft() + getPaddingRight()), getSuggestedMinimumWidth()), r28, r83), r45);
        if (r18 == false) goto L183;
        forceUniformWidth(r111, r29);
        return;
    L183:
        return;
    L161:
        if (r9 == 1073741824) goto L163;
        r104 = r66;
        goto L163
    L104:
        if (r27 == 1073741824) goto L159;
        int r73 = 0;
    L106:
        if (r73 >= r111) goto L159;
        View r210 = getVirtualChildAt(r73);
        if (r210 == null) goto L115;
        if (r210.getVisibility() == 8) goto L115;
        if (((LinearLayout.LayoutParams) ((LayoutParams) r210.getLayoutParams())).weight <= 0.0f) goto L115;
        r210.measure(View.MeasureSpec.makeMeasureSpec(r210.getMeasuredWidth(), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r123, TLObject.FLAG_30));
    L115:
        r73 = r73 + 1;
        goto L106
    L99:
        if (r213 <= 0.0f) goto L101;
    L116:
        float r74 = this.mWeightSum;
        if (r74 > 0.0f) goto L120;
        r74 = r213;
    L120:
        this.mTotalLength = 0;
        float r124 = r74;
        int r75 = r65;
        int r67 = 0;
    L121:
        if (r67 >= r111) goto L158;
        View r136 = getVirtualChildAt(r67);
        if (r136.getVisibility() != 8) goto L125;
        int r222 = r27;
        int r162 = r67;
    L157:
        r67 = r162 + 1;
        r27 = r222;
        goto L121
    L125:
        LayoutParams r145 = (LayoutParams) r136.getLayoutParams();
        float r112 = ((LinearLayout.LayoutParams) r145).weight;
        if (r112 <= 0.0f) goto L143;
        r162 = r67;
        int r68 = (int) ((r75 * r112) / r124);
        r124 = r124 - r112;
        r75 = r75 - r68;
        int r69 = ViewGroup.getChildMeasureSpec(r28, ((getPaddingLeft() + getPaddingRight()) + ((LinearLayout.LayoutParams) r145).leftMargin) + ((LinearLayout.LayoutParams) r145).rightMargin, ((LinearLayout.LayoutParams) r145).width);
        if (((LinearLayout.LayoutParams) r145).height != 0) goto L137;
        int r113 = TLObject.FLAG_30;
        if (r27 == 1073741824) goto L132;
    L131:
        r222 = r27;
        int r211 = r136.getMeasuredHeight() + r68;
        if (r211 >= 0) goto L141;
        r211 = 0;
    L141:
        r136.measure(r69, View.MeasureSpec.makeMeasureSpec(r211, r113));
    L142:
        r83 = View.combineMeasuredStates(r83, r136.getMeasuredState() & (-256));
    L144:
        int r214 = ((LinearLayout.LayoutParams) r145).leftMargin + ((LinearLayout.LayoutParams) r145).rightMargin;
        int r610 = r136.getMeasuredWidth() + r214;
        r104 = Math.max(r104, r610);
        if (r9 == 1073741824) goto L149;
        int r215 = -1;
        if (((LinearLayout.LayoutParams) r145).width != (-1)) goto L150;
        r610 = r214;
    L150:
        int r611 = Math.max(r15, r610);
        if (r17 == true) goto L153;
    L155:
        boolean r114 = false;
    L156:
        int r152 = this.mTotalLength;
        this.mTotalLength = Math.max(r152, (((r152 + r136.getMeasuredHeight()) + ((LinearLayout.LayoutParams) r145).topMargin) + ((LinearLayout.LayoutParams) r145).bottomMargin) + getNextLocationOffset(r136));
        r15 = r611;
        r17 = r114;
        goto L157
    L153:
        if (((LinearLayout.LayoutParams) r145).width != r215) goto L155;
        r114 = true;
        goto L156
    L149:
        r215 = -1;
        goto L150
    L132:
        r222 = r27;
        if (r68 <= 0) goto L135;
        int r216 = r68;
    L136:
        r136.measure(r69, View.MeasureSpec.makeMeasureSpec(r216, TLObject.FLAG_30));
        goto L142
    L135:
        r216 = 0;
        goto L136
    L137:
        r113 = TLObject.FLAG_30;
        goto L131
    L143:
        r222 = r27;
        r162 = r67;
        goto L144
    L158:
        this.mTotalLength += getPaddingTop() + getPaddingBottom();
        r66 = r15;
        goto L159
    L83:
        if (r27 == Integer.MIN_VALUE) goto L85;
        if (r27 != 0) goto L95;
    L85:
        this.mTotalLength = 0;
        int r46 = 0;
    L86:
        if (r46 >= r111) goto L95;
        View r612 = getVirtualChildAt(r46);
        if (r612 != null) goto L91;
        this.mTotalLength += measureNullChild(r46);
    L94:
        r46 = r46 + 1;
        r7 = 8;
        goto L86
    L91:
        if (r612.getVisibility() != r7) goto L93;
        r46 = r46 + getChildrenSkipCount(r612, r46);
        goto L94
    L93:
        LayoutParams r115 = (LayoutParams) r612.getLayoutParams();
        int r137 = this.mTotalLength;
        this.mTotalLength = Math.max(r137, (((r137 + r123) + ((LinearLayout.LayoutParams) r115).topMargin) + ((LinearLayout.LayoutParams) r115).bottomMargin) + getNextLocationOffset(r612));
        goto L94
    L79:
        r111 = r262;
        goto L80
    }

    @Override // android.view.View
    public void onDraw(Canvas r3) {
        if (this.mDivider != null) goto L6;
        return;
    L6:
        if (this.mOrientation != 1) goto L9;
        drawDividersVertical(r3);
        return;
    L9:
        drawDividersHorizontal(r3);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent r1) {
        super.onInitializeAccessibilityEvent(r1);
        r1.setClassName(ACCESSIBILITY_CLASS_NAME);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r1) {
        super.onInitializeAccessibilityNodeInfo(r1);
        r1.setClassName(ACCESSIBILITY_CLASS_NAME);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean r2, int r3, int r4, int r5, int r6) {
        if (this.mOrientation != 1) goto L6;
        layoutVertical(r3, r4, r5, r6);
        return;
    L6:
        layoutHorizontal(r3, r4, r5, r6);
    }

    @Override // android.view.View
    public void onMeasure(int r3, int r4) {
        if (this.mOrientation != 1) goto L6;
        measureVertical(r3, r4);
        return;
    L6:
        measureHorizontal(r3, r4);
    }

    public void setBaselineAligned(boolean r1) {
        this.mBaselineAligned = r1;
    }

    public void setBaselineAlignedChildIndex(int r2) {
        if (r2 >= 0) goto L4;
    L7:
        Structure$$ExternalSyntheticBUOutline2.m("base aligned child index out of range (0, ", getChildCount(), ")");
        return;
    L4:
        if (r2 >= getChildCount()) goto L7;
        this.mBaselineAlignedChildIndex = r2;
    }

    public void setDividerDrawable(Drawable r3) {
        if (r3 != this.mDivider) goto L5;
        return;
    L5:
        this.mDivider = r3;
        boolean r0 = false;
        if (r3 == null) goto L8;
        this.mDividerWidth = r3.getIntrinsicWidth();
        this.mDividerHeight = r3.getIntrinsicHeight();
    L9:
        if (r3 != null) goto L11;
        r0 = true;
    L11:
        setWillNotDraw(r0);
        requestLayout();
        return;
    L8:
        this.mDividerWidth = 0;
        this.mDividerHeight = 0;
        goto L9
    }

    public void setDividerPadding(int r1) {
        this.mDividerPadding = r1;
    }

    public void setGravity(int r2) {
        if (this.mGravity != r2) goto L5;
        return;
    L5:
        if ((8388615 & r2) != 0) goto L8;
        r2 = r2 | PlatformMediaRouter1RouteProvider.ALL_ROUTE_TYPES;
    L8:
        if ((r2 & 112) != 0) goto L10;
        r2 = r2 | 48;
    L10:
        this.mGravity = r2;
        requestLayout();
    }

    public void setHorizontalGravity(int r3) {
        int r32 = r3 & 8388615;
        int r1 = this.mGravity;
        if ((8388615 & r1) == r32) goto L6;
        this.mGravity = r32 | ((-8388616) & r1);
        requestLayout();
        return;
    }

    public void setMeasureWithLargestChildEnabled(boolean r1) {
        this.mUseLargestChild = r1;
    }

    public void setOrientation(int r2) {
        if (this.mOrientation == r2) goto L6;
        this.mOrientation = r2;
        requestLayout();
        return;
    }

    public void setShowDividers(int r2) {
        if (r2 == this.mShowDividers) goto L5;
        requestLayout();
    L5:
        this.mShowDividers = r2;
    }

    public void setVerticalGravity(int r3) {
        int r32 = r3 & 112;
        int r0 = this.mGravity;
        if ((r0 & 112) == r32) goto L6;
        this.mGravity = r32 | (r0 & (-113));
        requestLayout();
        return;
    }

    public void setWeightSum(float r2) {
        this.mWeightSum = Math.max(0.0f, r2);
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }
}
