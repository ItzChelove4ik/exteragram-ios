package org.telegram.ui.Components.voip;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.tgnet.TLObject;

/* loaded from: classes7.dex */
public class VoIPButtonsLayout extends FrameLayout {
    int childPadding;
    private int childSize;
    int childWidth;
    private boolean startPadding;
    int visibleChildCount;

    public VoIPButtonsLayout(Context r1) {
        super(r1);
        this.childSize = 68;
        this.startPadding = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent r2) {
        if (isEnabled() == true) goto L7;
        return false;
    L7:
        return super.dispatchTouchEvent(r2);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r5, int r6, int r7, int r8, int r9) {
        if (this.startPadding == false) goto L12;
        int r52 = (int) (((getChildCount() - this.visibleChildCount) / 2.0f) * (this.childWidth + (this.childPadding * 2)));
        int r82 = 0;
    L6:
        if (r82 >= getChildCount()) goto L22;
        View r92 = getChildAt(r82);
        if (r92.getVisibility() == 8) goto L10;
        int r0 = this.childPadding;
        r92.layout(r52 + r0, 0, (r0 + r52) + r92.getMeasuredWidth(), r92.getMeasuredHeight());
        r52 = r52 + ((this.childPadding * 2) + r92.getMeasuredWidth());
    L10:
        r82 = r82 + 1;
        goto L6
    L22:
        return;
    L12:
        if (this.visibleChildCount <= 0) goto L14;
        int r53 = (getMeasuredWidth() - this.childWidth) / (this.visibleChildCount - 1);
    L15:
        int r83 = 0;
        int r93 = 0;
    L17:
        if (r83 >= getChildCount()) goto L29;
        View r02 = getChildAt(r83);
        if (r02.getVisibility() == 8) goto L21;
        int r1 = r93 * r53;
        r02.layout(r1, 0, r02.getMeasuredWidth() + r1, r02.getMeasuredHeight());
        r93 = r93 + 1;
    L21:
        r83 = r83 + 1;
        goto L17
    L29:
        return;
    L14:
        r53 = 0;
        goto L15
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r7, int r8) {
        int r72 = View.MeasureSpec.getSize(r7);
        int r0 = 0;
        this.visibleChildCount = 0;
        int r1 = 0;
    L4:
        if (r1 >= getChildCount()) goto L9;
        if (getChildAt(r1).getVisibility() == 8) goto L8;
        this.visibleChildCount++;
    L8:
        r1 = r1 + 1;
        goto L4
    L9:
        this.childWidth = AndroidUtilities.dp(this.childSize);
        this.childPadding = ((r72 / getChildCount()) - this.childWidth) / 2;
        int r12 = 0;
    L11:
        if (r0 >= getChildCount()) goto L18;
        if (getChildAt(r0).getVisibility() == 8) goto L17;
        getChildAt(r0).measure(View.MeasureSpec.makeMeasureSpec(this.childWidth, TLObject.FLAG_30), r8);
        if (getChildAt(r0).getMeasuredHeight() <= r12) goto L17;
        r12 = getChildAt(r0).getMeasuredHeight();
    L17:
        r0 = r0 + 1;
        goto L11
    L18:
        setMeasuredDimension(r72, Math.max(r12, AndroidUtilities.dp(80.0f)));
    }

    public void setChildSize(int r1) {
        this.childSize = r1;
    }

    public void setUseStartPadding(boolean r1) {
        this.startPadding = r1;
    }
}
