package org.telegram.ui.ActionBar;

import android.content.Context;
import android.graphics.Canvas;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.CircularProgressDrawable;
import org.telegram.ui.Components.CubicBezierInterpolator;

/* loaded from: classes3.dex */
public abstract class TextViewWithLoading extends TextView {
    private final AnimatedFloat animatedLoading;
    private boolean loading;
    private CircularProgressDrawable spinner;

    public TextViewWithLoading(Context r4) {
        super(r4);
        this.loading = false;
        this.animatedLoading = new AnimatedFloat(this, 320, CubicBezierInterpolator.EASE_OUT_QUINT);
        this.spinner = new CircularProgressDrawable();
    }

    public boolean isLoading() {
        return this.loading;
    }

    @Override // android.widget.TextView, android.view.View
    public void onDraw(Canvas r14) {
        float r0 = this.animatedLoading.set(this.loading);
        if (r0 < 1.0f) goto L5;
        Canvas r6 = r14;
    L11:
        if (r0 <= 0.0f) goto L14;
        int r142 = getWidth() / 2;
        int r2 = getHeight() / 2;
        int r143 = r142 - ((int) (AndroidUtilities.dp(6.0f) * (1.0f - r0)));
        this.spinner.setAlpha((int) (r0 * 255.0f));
        CircularProgressDrawable r02 = this.spinner;
        r02.setBounds(r143 - (r02.getIntrinsicWidth() / 2), r2 - (this.spinner.getIntrinsicWidth() / 2), r143 + (this.spinner.getIntrinsicWidth() / 2), r2 + (this.spinner.getIntrinsicHeight() / 2));
        this.spinner.draw(r6);
        invalidate();
        return;
    L14:
        return;
    L5:
        if (r0 > 0.0f) goto L7;
        r14.save();
        r6 = r14;
    L8:
        r6.translate(0.0f, AndroidUtilities.dp(6.0f) * r0);
        super.onDraw(r6);
        r6.restore();
        goto L11
    L7:
        r6 = r14;
        r6.saveLayerAlpha(0.0f, 0.0f, getWidth(), getHeight(), (int) ((1.0f - r0) * 255.0f), 31);
        goto L8
    }

    public void setLoading(boolean r2, boolean r3) {
        if (this.loading == r2) goto L10;
        this.loading = r2;
        invalidate();
        if (r3 == true) goto L9;
        this.animatedLoading.force(r2);
        return;
    L9:
        return;
    }

    @Override // android.widget.TextView
    public void setTextColor(int r1) {
        super.setTextColor(r1);
        this.spinner.setColor(r1);
    }
}
