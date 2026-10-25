package org.telegram.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.graphics.ColorUtils;
import androidx.core.view.ViewCompat;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes3.dex */
public class UpdateLayoutWrapper extends ViewGroup {
    private boolean lastUpdateLayoutVisible;
    private final Paint paint;
    private View updateLayout;

    public UpdateLayoutWrapper(Context r2) {
        super(r2);
        this.paint = new Paint(1);
        setClipToPadding(false);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r17) {
        float r2 = AndroidUtilities.getNavigationBarThirdButtonsFactor(0.1f, 0.75f, getPaddingBottom());
        int r3 = Theme.getColor(Theme.key_featuredStickers_addButton);
        int r22 = ColorUtils.compositeColors(Theme.multAlpha(Theme.getColor(Theme.key_windowBackgroundWhite), r2), r3);
        this.paint.setColor(r3);
        r17.drawRect(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight() - r1, this.paint);
        this.paint.setColor(r22);
        r17.drawRect(0.0f, getMeasuredHeight() - r1, getMeasuredWidth(), getMeasuredHeight(), this.paint);
        super.dispatchDraw(r17);
    }

    public boolean isUpdateLayoutVisible() {
        View r0 = this.updateLayout;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.getVisibility() != 0) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean r2, int r3, int r4, int r5, int r6) {
        int r22 = getChildCount();
        int r42 = 0;
    L3:
        if (r42 >= r22) goto L5;
        View r52 = getChildAt(r42);
        r52.layout(0, 0, r52.getMeasuredWidth(), r52.getMeasuredHeight());
        r42 = r42 + 1;
        goto L3
    }

    @Override // android.view.View
    public void onMeasure(int r5, int r6) {
        boolean r62 = isUpdateLayoutVisible();
        int r52 = View.MeasureSpec.getSize(r5);
        int r0 = 0;
        if (r62 == false) goto L5;
        int r1 = AndroidUtilities.dp(44.0f) + getPaddingBottom();
    L6:
        setMeasuredDimension(r52, r1);
        int r53 = View.MeasureSpec.makeMeasureSpec(r52, TLObject.FLAG_30);
        int r12 = View.MeasureSpec.makeMeasureSpec(r1, TLObject.FLAG_30);
        int r2 = getChildCount();
    L7:
        if (r0 >= r2) goto L10;
        getChildAt(r0).measure(r53, r12);
        r0 = r0 + 1;
        goto L7
    L10:
        if (this.lastUpdateLayoutVisible == r62) goto L14;
        this.lastUpdateLayoutVisible = r62;
        ViewCompat.requestApplyInsets(this);
        return;
    L14:
        return;
    L5:
        r1 = 0;
        goto L6
    }

    @Override // android.view.ViewGroup
    public void onViewAdded(View r1) {
        super.onViewAdded(r1);
        this.updateLayout = r1;
    }

    @Override // android.view.View
    public void setPadding(int r4, int r5, int r6, int r7) {
        super.setPadding(r4, r5, r6, r7);
        int r0 = getChildCount();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        getChildAt(r1).setPadding(r4, r5, r6, r7);
        r1 = r1 + 1;
        goto L3
    }
}
