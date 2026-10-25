package org.telegram.ui.Components;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import androidx.annotation.Keep;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public class GroupCreateCheckBox extends View {
    private static Paint eraser;
    private static Paint eraser2;
    private boolean attachedToWindow;
    private Paint backgroundInnerPaint;
    private int backgroundKey;
    private Paint backgroundPaint;
    private Canvas bitmapCanvas;
    private int checkKey;
    private Paint checkPaint;
    private float checkScale;
    private Bitmap drawBitmap;
    private int innerKey;
    private int innerRadDiff;
    private boolean isCheckAnimation;
    private float progress;

    @Keep
    public float getProgress() {
        return this.progress;
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        updateColors();
        this.attachedToWindow = true;
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.attachedToWindow = false;
    }

    @Override // android.view.View
    public void onDraw(Canvas r15) {
        if (getVisibility() == 0) goto L6;
        return;
    L6:
        if (this.progress == 0.0f) goto L31;
        int r2 = getMeasuredWidth() / 2;
        int r4 = getMeasuredHeight() / 2;
        eraser2.setStrokeWidth(AndroidUtilities.dp(30.0f));
        this.drawBitmap.eraseColor(0);
        float r5 = this.progress;
        if (r5 < 0.5f) goto L10;
        float r7 = 1.0f;
    L12:
        if (r5 >= 0.5f) goto L14;
        float r9 = 0.0f;
    L16:
        if (this.isCheckAnimation == true) goto L20;
        r5 = 1.0f - r5;
    L20:
        if (r5 >= 0.2f) goto L23;
        float r10 = (AndroidUtilities.dp(2.0f) * r5) / 0.2f;
    L27:
        if (r9 == 0.0f) goto L29;
        r15.drawCircle(r2, r4, ((r2 - AndroidUtilities.dp(2.0f)) + (AndroidUtilities.dp(2.0f) * r9)) - r10, this.backgroundPaint);
    L29:
        float r52 = (r2 - this.innerRadDiff) - r10;
        float r102 = r2;
        float r12 = r4;
        this.bitmapCanvas.drawCircle(r102, r12, r52, this.backgroundInnerPaint);
        this.bitmapCanvas.drawCircle(r102, r12, r52 * (1.0f - r7), eraser);
        r15.drawBitmap(this.drawBitmap, 0.0f, 0.0f, null);
        float r72 = (AndroidUtilities.dp(10.0f) * r9) * this.checkScale;
        float r3 = (AndroidUtilities.dp(5.0f) * r9) * this.checkScale;
        int r8 = r2 - AndroidUtilities.dp(1.0f);
        int r42 = r4 + AndroidUtilities.dp(4.0f);
        float r22 = (float) Math.sqrt((r3 * r3) / 2.0f);
        float r23 = r8;
        float r43 = r42;
        r15.drawLine(r23, r43, r23 - r22, r43 - r22, this.checkPaint);
        float r1 = (float) Math.sqrt((r72 * r72) / 2.0f);
        float r24 = r8 - AndroidUtilities.dp(1.2f);
        r15.drawLine(r24, r43, r24 + r1, r43 - r1, this.checkPaint);
        return;
    L23:
        if (r5 >= 0.4f) goto L25;
        r10 = AndroidUtilities.dp(2.0f) - ((AndroidUtilities.dp(2.0f) * (r5 - 0.2f)) / 0.2f);
        goto L27
    L25:
        r10 = 0.0f;
        goto L27
    L14:
        r9 = (r5 - 0.5f) / 0.5f;
        goto L16
    L10:
        r7 = r5 / 0.5f;
        goto L12
    }

    public void setCheckScale(float r1) {
        this.checkScale = r1;
    }

    public void setInnerRadDiff(int r1) {
        this.innerRadDiff = r1;
    }

    @Keep
    public void setProgress(float r2) {
        if (this.progress != r2) goto L5;
        return;
    L5:
        this.progress = r2;
        invalidate();
    }

    public void updateColors() {
        this.backgroundInnerPaint.setColor(Theme.getColor(this.innerKey));
        this.backgroundPaint.setColor(Theme.getColor(this.backgroundKey));
        this.checkPaint.setColor(Theme.getColor(this.checkKey));
        invalidate();
    }
}
