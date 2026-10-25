package org.telegram.ui.Components;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Layout;
import android.text.Spanned;
import android.text.style.LeadingMarginSpan;

/* loaded from: classes7.dex */
public class BulletSpan implements LeadingMarginSpan {
    private final int mBulletRadius;
    private final int mColor;
    private final int mGapWidth;
    private final boolean mWantColor;

    public BulletSpan(int r2, int r3, int r4) {
        this(r2, r3, true, r4);
    }

    private BulletSpan(int r1, int r2, boolean r3, int r4) {
        this.mGapWidth = r1;
        this.mBulletRadius = r4;
        this.mColor = r2;
        this.mWantColor = r3;
    }

    @Override // android.text.style.LeadingMarginSpan
    public void drawLeadingMargin(Canvas r1, Paint r2, int r3, int r4, int r5, int r6, int r7, CharSequence r8, int r9, int r10, boolean r11, Layout r12) {
        if (((Spanned) r8).getSpanStart(this) != r9) goto L19;
        Paint.Style r62 = r2.getStyle();
        int r102 = 0;
        if (this.mWantColor == false) goto L7;
        int r82 = r2.getColor();
        r2.setColor(this.mColor);
    L8:
        r2.setStyle(Paint.Style.FILL);
        if (r12 != null) goto L11;
    L14:
        r1.drawCircle(r3 + (r4 * r7), (r5 + r7) / 2.0f, this.mBulletRadius, r2);
        if (this.mWantColor == false) goto L17;
        r2.setColor(r82);
    L17:
        r2.setStyle(r62);
        return;
    L11:
        if (r12.getLineForOffset(r9) == (r12.getLineCount() - 1)) goto L13;
        r102 = (int) r12.getSpacingAdd();
    L13:
        r7 = r7 - r102;
        goto L14
    L7:
        r82 = 0;
        goto L8
    }

    @Override // android.text.style.LeadingMarginSpan
    public int getLeadingMargin(boolean r1) {
        return (this.mBulletRadius * 2) + this.mGapWidth;
    }
}
