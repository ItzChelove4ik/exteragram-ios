package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.SharedConfig;

/* loaded from: classes3.dex */
public abstract class BlurredRecyclerView extends RecyclerListView {
    public int additionalClipBottom;
    public boolean alwaysDrawChild;
    public int blurTopPadding;
    public int bottomPadding;
    boolean globalIgnoreLayout;
    public int topPadding;

    public BlurredRecyclerView(Context r1) {
        super(r1);
    }

    private void updateTopPadding() {
        if (getLayoutParams() != null) goto L6;
        return;
    L6:
        if (SharedConfig.chatBlurEnabled() == false) goto L9;
        this.blurTopPadding = measureBlurTopPadding();
        ViewGroup.MarginLayoutParams r0 = (ViewGroup.MarginLayoutParams) getLayoutParams();
        r0.topMargin = -this.blurTopPadding;
        return;
    L9:
        this.blurTopPadding = 0;
        ((ViewGroup.MarginLayoutParams) getLayoutParams()).topMargin = 0;
    }

    @Override // org.telegram.ui.Components.RecyclerListView, org.telegram.ui.Components.blur3.capture.IBlur3Capture
    public void capture(Canvas r2, RectF r3) {
        this.alwaysDrawChild = true;
        super.capture(r2, r3);
        this.alwaysDrawChild = false;
    }

    @Override // org.telegram.ui.Components.RecyclerListView, android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r5) {
        if (this.blurTopPadding != 0) goto L5;
    L8:
        super.dispatchDraw(r5);
        return;
    L5:
        if (hasActiveEdgeEffects() == true) goto L8;
        r5.clipRect(0, this.blurTopPadding, getMeasuredWidth(), getMeasuredHeight() + this.additionalClipBottom);
        super.dispatchDraw(r5);
    }

    @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
    public boolean drawChild(Canvas r3, View r4, long r5) {
        if ((r4.getY() + r4.getMeasuredHeight()) >= this.blurTopPadding) goto L11;
        if (this.alwaysDrawChild == true) goto L11;
        if (hasActiveEdgeEffects() == true) goto L11;
        return true;
    L11:
        return super.drawChild(r3, r4, r5);
    }

    public int measureBlurTopPadding() {
        return AndroidUtilities.dp(203.0f);
    }

    @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        updateTopPadding();
    }

    @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.View
    public void onMeasure(int r5, int r6) {
        this.globalIgnoreLayout = true;
        updateTopPadding();
        super.setPadding(getPaddingLeft(), this.topPadding + this.blurTopPadding, getPaddingRight(), getPaddingBottom());
        this.globalIgnoreLayout = false;
        super.onMeasure(r5, r6);
    }

    @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (this.globalIgnoreLayout == false) goto L5;
        return;
    L5:
        super.requestLayout();
    }

    @Override // android.view.View
    public void setPadding(int r2, int r3, int r4, int r5) {
        this.topPadding = r3;
        this.bottomPadding = r5;
        super.setPadding(r2, r3 + this.blurTopPadding, r4, r5);
    }
}
