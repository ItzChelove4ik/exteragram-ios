package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import androidx.appcompat.R$id;
import androidx.appcompat.R$styleable;
import androidx.core.view.ViewCompat;

/* loaded from: classes3.dex */
public class ButtonBarLayout extends LinearLayout {
    private boolean mAllowStacking;
    private int mLastWidthSize;
    private boolean mStacked;

    public ButtonBarLayout(Context r9, AttributeSet r10) {
        super(r9, r10);
        this.mLastWidthSize = -1;
        TypedArray r5 = r9.obtainStyledAttributes(r10, R$styleable.ButtonBarLayout);
        ViewCompat.saveAttributeDataForStyleable(this, r9, R$styleable.ButtonBarLayout, r10, r5, 0, 0);
        this.mAllowStacking = r5.getBoolean(R$styleable.ButtonBarLayout_allowStacking, true);
        r5.recycle();
        if (getOrientation() != 1) goto L6;
        setStacked(this.mAllowStacking);
        return;
    }

    private int getNextVisibleChildIndex(int r3) {
        int r0 = getChildCount();
    L3:
        if (r3 >= r0) goto L8;
        if (getChildAt(r3).getVisibility() == 0) goto L6;
        r3 = r3 + 1;
        goto L3
    L6:
        return r3;
    L8:
        return -1;
    }

    private boolean isStacked() {
        return this.mStacked;
    }

    private void setStacked(boolean r2) {
        if (this.mStacked == r2) goto L20;
        if (r2 == true) goto L6;
    L7:
        this.mStacked = r2;
        setOrientation(r2 ? 1 : 0);
        if (r2 == false) goto L10;
        int r0 = 8388613;
    L11:
        setGravity(r0);
        View r02 = findViewById(R$id.spacer);
        if (r02 == null) goto L17;
        if (r2 == false) goto L15;
        int r22 = 8;
    L16:
        r02.setVisibility(r22);
        goto L17
    L15:
        r22 = 4;
    L17:
        int r23 = getChildCount() - 2;
    L18:
        if (r23 < 0) goto L23;
        bringChildToFront(getChildAt(r23));
        r23 = r23 - 1;
        goto L18
    L23:
        return;
    L10:
        r0 = 80;
        goto L11
    L6:
        if (this.mAllowStacking == true) goto L7;
        return;
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int r6, int r7) {
        int r0 = View.MeasureSpec.getSize(r6);
        int r2 = 0;
        if (this.mAllowStacking == false) goto L11;
        if (r0 > this.mLastWidthSize) goto L7;
    L9:
        this.mLastWidthSize = r0;
        goto L11
    L7:
        if (isStacked() == false) goto L9;
        setStacked(false);
    L11:
        if (isStacked() == false) goto L13;
    L15:
        int r02 = r6;
        boolean r1 = false;
    L16:
        super.onMeasure(r02, r7);
        if (this.mAllowStacking == true) goto L19;
    L23:
        if (r1 == false) goto L25;
        super.onMeasure(r6, r7);
    L25:
        int r03 = getNextVisibleChildIndex(0);
        if (r03 < 0) goto L35;
        View r12 = getChildAt(r03);
        LinearLayout.LayoutParams r22 = (LinearLayout.LayoutParams) r12.getLayoutParams();
        int r4 = ((getPaddingTop() + r12.getMeasuredHeight()) + r22.topMargin) + r22.bottomMargin;
        if (isStacked() == false) goto L33;
        int r04 = getNextVisibleChildIndex(r03 + 1);
        if (r04 < 0) goto L32;
        r4 = r4 + (getChildAt(r04).getPaddingTop() + ((int) (getResources().getDisplayMetrics().density * 16.0f)));
    L32:
        r2 = r4;
        goto L35
    L33:
        r2 = r4 + getPaddingBottom();
    L35:
        if (ViewCompat.getMinimumHeight(this) == r2) goto L40;
        setMinimumHeight(r2);
        if (r7 != 0) goto L41;
        super.onMeasure(r6, r7);
        return;
    L41:
        return;
    L40:
        return;
    L19:
        if (isStacked() == true) goto L23;
        if ((getMeasuredWidthAndState() & (-16777216)) != 16777216) goto L23;
        setStacked(true);
        r1 = true;
        goto L23
    L13:
        if (View.MeasureSpec.getMode(r6) != 1073741824) goto L15;
        r02 = View.MeasureSpec.makeMeasureSpec(r0, Integer.MIN_VALUE);
        r1 = true;
        goto L16
    }

    public void setAllowStacking(boolean r2) {
        if (this.mAllowStacking == r2) goto L11;
        this.mAllowStacking = r2;
        if (r2 == false) goto L7;
    L9:
        requestLayout();
        return;
    L7:
        if (isStacked() == false) goto L9;
        setStacked(false);
        goto L9
    }
}
