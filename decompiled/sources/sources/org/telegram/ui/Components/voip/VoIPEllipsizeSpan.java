package org.telegram.ui.Components.voip;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.SystemClock;
import android.text.style.ReplacementSpan;
import android.view.View;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.Components.CubicBezierInterpolator;

/* loaded from: classes7.dex */
public class VoIPEllipsizeSpan extends ReplacementSpan {
    private final CubicBezierInterpolator interpolator;
    private final View[] parents;

    public VoIPEllipsizeSpan(View... r10) {
        this.interpolator = new CubicBezierInterpolator(0.33d, 0.0d, 0.67d, 1.0d);
        this.parents = r10;
    }

    @Override // android.text.style.ReplacementSpan
    public void draw(Canvas r6, CharSequence r7, int r8, int r9, float r10, int r11, int r12, int r13, Paint r14) {
        r6.save();
        r6.translate(r10 + AndroidUtilities.dp(4.0f), r12 / 2.0f);
        long r92 = (SystemClock.uptimeMillis() % 250) + 500;
        int r72 = 0;
        int r132 = 0;
    L4:
        if (r132 >= 3) goto L10;
        float r0 = Math.min(1.0f, (((r132 * 250) + r92) % 750) / 667.0f);
        CubicBezierInterpolator r4 = this.interpolator;
        if (r0 > 0.425f) goto L8;
        float r1 = r4.getInterpolation(r0 / 0.425f);
    L9:
        r6.drawCircle(AndroidUtilities.dpf2((this.interpolator.getInterpolation(r0) * 16.0f) + 1.667f), AndroidUtilities.dp(3.0f), AndroidUtilities.dpf2(r1 * 2.0f), r14);
        r132 = r132 + 1;
        goto L4
    L8:
        r1 = 1.0f - r4.getInterpolation((r0 - 0.425f) / 0.575f);
        goto L9
    L10:
        r6.restore();
        View[] r5 = this.parents;
        int r62 = r5.length;
    L11:
        if (r72 >= r62) goto L13;
        r5[r72].invalidate();
        r72 = r72 + 1;
        goto L11
    }

    @Override // android.text.style.ReplacementSpan
    public int getSize(Paint r1, CharSequence r2, int r3, int r4, Paint.FontMetricsInt r5) {
        return AndroidUtilities.dp(20.0f);
    }
}
