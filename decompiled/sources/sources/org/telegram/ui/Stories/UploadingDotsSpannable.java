package org.telegram.ui.Stories;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;
import android.text.style.ReplacementSpan;
import android.view.View;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.Components.CubicBezierInterpolator;

/* loaded from: classes7.dex */
public class UploadingDotsSpannable extends ReplacementSpan {
    CubicBezierInterpolator circle;
    public boolean fixTop;
    private boolean isMediumTypeface;
    long lastTime;
    private View parent;
    int swapPosition1;
    int swapPosition2;
    float swapProgress;
    private final String text;
    boolean waitForNextAnimation;

    public UploadingDotsSpannable() {
        this.text = "…";
        this.swapPosition1 = 1;
        this.swapPosition2 = 2;
        this.circle = new CubicBezierInterpolator(0.0f, 0.5f, 0.5f, 1.0f);
    }

    @Override // android.text.style.ReplacementSpan
    public void draw(Canvas r5, CharSequence r6, int r7, int r8, float r9, int r10, int r11, int r12, Paint r13) {
        TextPaint r62 = (TextPaint) r13;
        float r72 = r13.measureText("…") / 3.0f;
        if (this.fixTop == false) goto L6;
        float r82 = r62.getFontMetrics().ascent;
    L5:
        float r83 = -r82;
        float r102 = r62.getFontMetrics().bottom - r62.getFontMetrics().top;
        if (this.isMediumTypeface == false) goto L10;
        float r63 = 0.05f;
    L11:
        float r103 = r102 * r63;
        float r84 = r83 - r103;
        int r122 = 0;
        if (this.waitForNextAnimation == true) goto L14;
        float r64 = this.swapProgress + 0.053333335f;
        this.swapProgress = r64;
        if (r64 <= 1.0f) goto L22;
        this.swapProgress = 0.0f;
        int r65 = this.swapPosition1 - 1;
        this.swapPosition1 = r65;
        this.swapPosition2--;
        if (r65 >= 0) goto L22;
        this.swapPosition1 = 1;
        this.swapPosition2 = 2;
        this.waitForNextAnimation = true;
        this.lastTime = System.currentTimeMillis();
    L22:
        if (r122 >= 3) goto L35;
        float r0 = r72 / 2.0f;
        float r66 = ((r122 * r72) + r9) + r0;
        if (r122 != this.swapPosition1) goto L31;
        r66 = AndroidUtilities.lerp(r66, (((r122 + 1) * r72) + r9) + r0, this.swapProgress);
        float r1 = this.swapProgress;
        if (r1 >= 0.5f) goto L28;
        float r14 = r1 / 0.5f;
    L29:
        float r02 = AndroidUtilities.lerp(r84, r84 - r0, this.circle.getInterpolation(r14));
    L34:
        r5.drawCircle(r66, r02, r103, r13);
        r122 = r122 + 1;
        goto L22
    L28:
        r14 = 1.0f - ((r1 - 0.5f) / 0.5f);
        goto L29
    L31:
        if (r122 != this.swapPosition2) goto L33;
        r66 = AndroidUtilities.lerp(r66, (((r122 - 1) * r72) + r9) + r0, this.swapProgress);
    L33:
        r02 = r84;
        goto L34
    L35:
        View r4 = this.parent;
        if (r4 == null) goto L43;
        r4.invalidate();
        return;
    L43:
        return;
    L14:
        if ((System.currentTimeMillis() - this.lastTime) <= 1000) goto L22;
        this.waitForNextAnimation = false;
        goto L22
    L10:
        r63 = 0.0365f;
        goto L11
    L6:
        r82 = r62.getFontMetrics().top;
        goto L5
    }

    @Override // android.text.style.ReplacementSpan
    public int getSize(Paint r1, CharSequence r2, int r3, int r4, Paint.FontMetricsInt r5) {
        return (int) r1.measureText("…");
    }

    public void setParent(View r1, boolean r2) {
        this.parent = r1;
        this.isMediumTypeface = r2;
    }
}
