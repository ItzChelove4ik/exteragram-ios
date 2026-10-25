package org.telegram.ui.Components;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Shader;
import android.os.SystemClock;
import android.view.View;
import android.widget.TextView;
import androidx.core.math.MathUtils;
import org.telegram.messenger.AndroidUtilities;

@SuppressLint({"AppCompatCustomView"})
/* loaded from: classes7.dex */
public class MarqueeTextView extends TextView {
    private LinearGradient gradient;
    private final Matrix gradientMatrix;
    private long lastFrameTime;
    private boolean marqueeIsPending;
    private boolean marqueeIsStarted;
    private boolean needMarquee;
    private int originalWidth;
    private int rightPadding;
    private float scrollX;
    private final Runnable startMarquee;

    /* renamed from: $r8$lambda$-9L0aL1QzDHyzfT0Nevdk0gD_ZU, reason: not valid java name */
    public static /* synthetic */ void m11207$r8$lambda$9L0aL1QzDHyzfT0Nevdk0gD_ZU(MarqueeTextView r0) {
        r0.startMarqueeInternal();
    }

    public MarqueeTextView(Context r1) {
        super(r1);
        this.gradientMatrix = new Matrix();
        this.startMarquee = new MarqueeTextView$$ExternalSyntheticLambda0(this);
    }

    private void invalidateGradient() {
        float r0 = Math.min(AndroidUtilities.dp(10.0f) / this.originalWidth, 0.49f);
        int r1 = getCurrentTextColor();
        int r3 = 1048575 & r1;
        this.gradient = new LinearGradient(0.0f, 0.0f, this.originalWidth, 0.0f, new int[]{r3, r1, r1, r3}, new float[]{0.0f, r0, 1.0f - r0, 1.0f}, Shader.TileMode.CLAMP);
        if (this.needMarquee == false) goto L5;
        getPaint().setShader(this.gradient);
    L6:
        this.gradient.setLocalMatrix(this.gradientMatrix);
        invalidate();
        return;
    L5:
        getPaint().setShader(null);
        goto L6
    }

    private void pendingMarqueeInternal() {
        if (this.marqueeIsPending == true) goto L6;
        this.marqueeIsPending = true;
        AndroidUtilities.runOnUIThread(this.startMarquee, 1500);
        return;
    }

    private void startMarqueeInternal() {
        if (this.needMarquee == false) goto L6;
        this.marqueeIsStarted = true;
        this.marqueeIsPending = false;
        this.scrollX = 0.0f;
        this.lastFrameTime = SystemClock.uptimeMillis();
        invalidate();
        return;
    }

    private void stopMarqueeInternal() {
        AndroidUtilities.cancelRunOnUIThread(this.startMarquee);
        this.marqueeIsPending = false;
        this.marqueeIsStarted = false;
        this.scrollX = 0.0f;
    }

    public boolean isNeedMarquee() {
        return this.needMarquee;
    }

    @Override // android.widget.TextView, android.view.View
    public void onDraw(Canvas r11) {
        int r0 = getMeasuredWidth();
        int r1 = AndroidUtilities.dp(40.0f);
        float r2 = this.scrollX;
        float r3 = r0;
        if (r2 >= r3) goto L5;
        float r22 = MathUtils.clamp(r2 / AndroidUtilities.dp(10.0f), 0.0f, 1.0f);
    L6:
        this.gradientMatrix.reset();
        Matrix r4 = this.gradientMatrix;
        float r5 = AndroidUtilities.dp(10.0f);
        int r8 = this.originalWidth;
        r4.postScale(((r5 / r8) * (1.0f - r22)) + 1.0f, 1.0f, r8, 0.0f);
        this.gradientMatrix.postScale(1.0f - (this.rightPadding / this.originalWidth), 1.0f, 0.0f, 0.0f);
        this.gradientMatrix.postTranslate(this.scrollX, 0.0f);
        this.gradient.setLocalMatrix(this.gradientMatrix);
        r11.save();
        r11.translate(-this.scrollX, 0.0f);
        super.onDraw(r11);
        r11.restore();
        if (r0 <= 0) goto L18;
        float r23 = this.scrollX;
        if (r23 <= 0.0f) goto L18;
        if ((r23 + getWidth()) <= r3) goto L18;
        if (this.needMarquee == false) goto L18;
        if (this.marqueeIsStarted == false) goto L18;
        Matrix r24 = this.gradientMatrix;
        float r42 = this.scrollX;
        float r6 = r1;
        r24.postTranslate((-r42) - (((-r42) + r3) + r6), 0.0f);
        this.gradient.setLocalMatrix(this.gradientMatrix);
        r11.save();
        r11.translate(((-this.scrollX) + r3) + r6, 0.0f);
        super.onDraw(r11);
        r11.restore();
    L18:
        if (this.scrollX >= 1.0E-4d) goto L20;
        boolean r112 = true;
    L21:
        long r25 = SystemClock.uptimeMillis();
        long r43 = this.lastFrameTime;
        if (r43 == 0) goto L25;
        if (r112 == true) goto L25;
        long r44 = Math.min(r25 - r43, 120);
    L26:
        this.lastFrameTime = r25;
        if (this.needMarquee == true) goto L29;
    L30:
        if (r112 == true) goto L36;
    L31:
        float r113 = this.scrollX + (AndroidUtilities.dp(60.0f) * (r44 / 1000.0f));
        this.scrollX = r113;
        if (r113 <= (r0 + r1)) goto L34;
        stopMarqueeInternal();
    L34:
        invalidate();
    L36:
        if (this.needMarquee == true) goto L38;
        return;
    L38:
        if (this.marqueeIsStarted == false) goto L40;
        return;
    L40:
        if (this.marqueeIsPending == true) goto L45;
        pendingMarqueeInternal();
        return;
    L45:
        return;
    L29:
        if (this.marqueeIsStarted == true) goto L31;
    L25:
        r44 = 16;
        goto L26
    L20:
        r112 = false;
        goto L21
    L5:
        r22 = 0.0f;
        goto L6
    }

    @Override // android.widget.TextView, android.view.View
    public void onMeasure(int r3, int r4) {
        boolean r0 = false;
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(0, 0), r4);
        this.originalWidth = View.MeasureSpec.getSize(r3);
        if (getMeasuredWidth() <= (this.originalWidth - this.rightPadding)) goto L5;
        r0 = true;
    L5:
        this.needMarquee = r0;
        invalidateGradient();
    }

    public void setCustomPaddingRight(int r3) {
        this.rightPadding = r3;
        if (getMeasuredWidth() <= (this.originalWidth - this.rightPadding)) goto L5;
        boolean r32 = true;
    L6:
        this.needMarquee = r32;
        if (r32 == false) goto L9;
        getPaint().setShader(this.gradient);
    L10:
        invalidate();
        return;
    L9:
        getPaint().setShader(null);
        goto L10
    L5:
        r32 = false;
        goto L6
    }

    @Override // android.widget.TextView
    public void setText(CharSequence r1, TextView.BufferType r2) {
        super.setText(r1, r2);
        stopMarqueeInternal();
    }

    @Override // android.widget.TextView
    public void setTextColor(int r1) {
        super.setTextColor(r1);
        invalidateGradient();
    }
}
