package org.telegram.ui.Components;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.style.ReplacementSpan;
import android.view.View;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public class LoadingSpan extends ReplacementSpan {
    public float alpha;
    private LoadingDrawable drawable;
    public boolean fullWidth;
    public float height;
    private float scaleY;
    public int size;
    private View view;
    public int yOffset;

    public LoadingSpan(View r2, int r3) {
        this(r2, r3, AndroidUtilities.dp(2.0f));
    }

    public LoadingSpan(View r2, int r3, int r4) {
        this(r2, r3, r4, null);
    }

    public LoadingSpan(View r3, int r4, int r5, Theme.ResourcesProvider r6) {
        this.scaleY = 1.0f;
        this.height = -1.0f;
        this.alpha = 1.0f;
        this.fullWidth = false;
        this.view = r3;
        this.size = r4;
        this.yOffset = r5;
        LoadingDrawable r32 = new LoadingDrawable(r6);
        this.drawable = r32;
        r32.setRadiiDp(4.0f);
    }

    @Override // android.text.style.ReplacementSpan
    public void draw(Canvas r4, CharSequence r5, int r6, int r7, float r8, int r9, int r10, int r11, Paint r12) {
        int r52 = this.size;
        if (this.fullWidth == false) goto L9;
        View r62 = this.view;
        if (r62 == null) goto L9;
        if (r62.getMeasuredWidth() <= 0) goto L9;
        r52 = ((this.view.getMeasuredWidth() - this.view.getPaddingLeft()) - this.view.getPaddingRight()) - this.size;
    L9:
        float r63 = this.height;
        LoadingDrawable r102 = this.drawable;
        if (r63 <= 0.0f) goto L12;
        float r72 = (r9 + r11) / 2.0f;
        int r82 = (int) r8;
        r102.setBounds(r82, (int) (r72 - (r63 / 2.0f)), r52 + r82, (int) (r72 + (r63 / 2.0f)));
    L13:
        LoadingDrawable r53 = this.drawable;
        if (r12 != null) goto L16;
        int r64 = 255;
    L17:
        r53.setAlpha((int) (r64 * this.alpha));
        this.drawable.draw(r4);
        View r3 = this.view;
        if (r3 == null) goto L21;
        r3.invalidate();
        return;
    L21:
        return;
    L16:
        r64 = r12.getAlpha();
        goto L17
    L12:
        int r65 = (int) r8;
        r102.setBounds(r65, (int) ((r9 + ((((r11 - AndroidUtilities.dp(2.0f)) - r9) / 2.0f) * (1.0f - this.scaleY))) + this.yOffset), r52 + r65, (int) (((r11 - AndroidUtilities.dp(2.0f)) - ((((r11 - AndroidUtilities.dp(2.0f)) - r9) / 2.0f) * (1.0f - this.scaleY))) + this.yOffset));
        goto L13
    }

    @Override // android.text.style.ReplacementSpan
    public int getSize(Paint r1, CharSequence r2, int r3, int r4, Paint.FontMetricsInt r5) {
        Paint.FontMetrics r22 = r1.getFontMetrics();
        if (r5 == null) goto L5;
        r5.ascent = (int) r22.ascent;
        r5.bottom = (int) r22.bottom;
        r5.descent = (int) r22.descent;
        r5.leading = (int) r22.leading;
        r5.top = (int) r22.top;
    L5:
        LoadingDrawable r23 = this.drawable;
        if (r23.color1 != null) goto L11;
        if (r23.color2 != null) goto L11;
        r23.setColors(Theme.multAlpha(r1.getColor(), 0.1f), Theme.multAlpha(r1.getColor(), 0.25f));
    L11:
        if (this.fullWidth == false) goto L19;
        View r12 = this.view;
        if (r12 == null) goto L19;
        if (r12.getMeasuredWidth() <= 0) goto L19;
        return ((this.view.getMeasuredWidth() - this.view.getPaddingLeft()) - this.view.getPaddingRight()) - this.size;
    L19:
        return this.size;
    }

    public LoadingSpan setAlpha(float r1) {
        this.alpha = r1;
        return this;
    }

    public void setColors(int r2, int r3) {
        LoadingDrawable r0 = this.drawable;
        r0.color1 = Integer.valueOf(r2);
        LoadingDrawable r1 = this.drawable;
        r1.color2 = Integer.valueOf(r3);
    }

    public LoadingSpan setFullWidth(boolean r1) {
        this.fullWidth = r1;
        return this;
    }

    public LoadingSpan setHeight(float r1) {
        this.height = r1;
        return this;
    }

    public void setScaleY(float r1) {
        this.scaleY = r1;
    }

    public void setView(View r1) {
        this.view = r1;
    }
}
