package org.telegram.ui.Components;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.view.View;
import org.telegram.messenger.R;

/* loaded from: classes7.dex */
public class SummaryIcon extends Drawable {
    private int alpha;
    private final Drawable arrow;
    private boolean on;
    private final AnimatedFloat progress;
    private final Drawable stars;

    public SummaryIcon(View r5) {
        this.alpha = 255;
        this.progress = new AnimatedFloat(r5, 420, CubicBezierInterpolator.EASE_OUT_QUINT);
        this.arrow = r5.getContext().getResources().getDrawable(R.drawable.summary_arrow);
        this.stars = r5.getContext().getResources().getDrawable(R.drawable.summary_stars);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r15) {
        this.stars.setBounds(getBounds());
        this.stars.setAlpha(this.alpha);
        this.stars.draw(r15);
        float r0 = this.progress.set(this.on);
        float r1 = getBounds().centerX();
        float r2 = getBounds().centerY();
        float r3 = getBounds().width();
        r15.save();
        if (r0 >= 0.5f) goto L5;
        float r5 = Math.abs(r0 - 0.5f) + 0.5f;
        r15.scale(r5, r5, r1, r2);
    L5:
        r15.save();
        if (r0 <= 0.5f) goto L8;
        float r9 = Math.abs(r0 - 0.5f) + 0.5f;
        float r10 = -r9;
        float r12 = r3 * 0.32f;
        r15.scale(r10, r10, getBounds().left + r12, getBounds().bottom - r12);
        float r92 = 1.0f - r9;
        r15.translate(((-r3) * r92) * 0.4f, (r92 * r3) * 0.4f);
    L8:
        this.arrow.setBounds(getBounds());
        this.arrow.setAlpha(this.alpha);
        this.arrow.draw(r15);
        r15.restore();
        r15.save();
        if (r0 <= 0.5f) goto L11;
        float r93 = -(Math.abs(r0 - 0.5f) + 0.5f);
        float r7 = 0.32f * r3;
        r15.scale(r93, r93, getBounds().right - r7, getBounds().top + r7);
    L11:
        r15.rotate(180.0f, r1, r2);
        if (r0 <= 0.5f) goto L14;
        float r6 = 1.0f - (Math.abs(r0 - 0.5f) + 0.5f);
        r15.translate(((-r3) * r6) * 0.4f, (r3 * r6) * 0.4f);
    L14:
        this.arrow.setBounds(getBounds());
        this.arrow.setAlpha(this.alpha);
        this.arrow.draw(r15);
        r15.restore();
        r15.restore();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.arrow.getIntrinsicHeight();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.arrow.getIntrinsicWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -2;
    }

    public void set(boolean r2) {
        set(r2, true);
    }

    public void set(boolean r1, boolean r2) {
        this.on = r1;
        if (r2 == true) goto L6;
        this.progress.set(r1);
        return;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r1) {
        this.alpha = r1;
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r2) {
        this.arrow.setColorFilter(r2);
        this.stars.setColorFilter(r2);
    }
}
