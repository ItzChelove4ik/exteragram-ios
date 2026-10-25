package org.telegram.ui.Components;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import org.telegram.messenger.AndroidUtilities;

/* loaded from: classes7.dex */
public class MotionPhotoDrawable extends Drawable {
    private final AnimatedFloat animatedDisabled;
    public final Paint clearPaint;
    private boolean disabled;
    private final Path play;
    public final Paint playPaint;
    public final Paint strokePaint;

    public MotionPhotoDrawable() {
        Path r0 = new Path();
        this.play = r0;
        Paint r1 = new Paint(1);
        this.strokePaint = r1;
        Paint r3 = new Paint(1);
        this.playPaint = r3;
        Paint r4 = new Paint(1);
        this.clearPaint = r4;
        this.animatedDisabled = new AnimatedFloat(new MotionPhotoDrawable$$ExternalSyntheticLambda0(this), 0, 320, CubicBezierInterpolator.EASE_OUT_QUINT);
        Paint.Style r12 = Paint.Style.STROKE;
        r1.setStyle(r12);
        r1.setColor(-1);
        r3.setPathEffect(new CornerPathEffect(AndroidUtilities.dpf2(2.0f)));
        r3.setColor(-1);
        r4.setStyle(r12);
        r4.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        r0.moveTo(-AndroidUtilities.dpf2(3.75f), -AndroidUtilities.dpf2(5.4166f));
        r0.lineTo(AndroidUtilities.dpf2(3.75f), 0.0f);
        r0.lineTo(-AndroidUtilities.dpf2(3.75f), AndroidUtilities.dpf2(5.4166f));
        r0.close();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r13) {
        this.strokePaint.setStrokeWidth(AndroidUtilities.dpf2(1.66f));
        this.clearPaint.setStrokeWidth(AndroidUtilities.dpf2(3.32f));
        float r7 = this.animatedDisabled.set(this.disabled);
        float r8 = getBounds().centerX();
        float r9 = getBounds().centerY();
        float r2 = AndroidUtilities.dpf2(10.66f);
        RectF r3 = AndroidUtilities.rectTmp;
        r3.set(r8 - r2, r9 - r2, r8 + r2, r2 + r9);
        r13.drawRoundRect(r3, AndroidUtilities.dpf2(8.33f), AndroidUtilities.dpf2(8.33f), this.strokePaint);
        if (r7 <= 0.0f) goto L5;
        r13.saveLayerAlpha(r3, 255, 31);
    L6:
        r13.save();
        r13.translate(AndroidUtilities.dpf2(1.0f) + r8, r9 - AndroidUtilities.dpf2(0.5f));
        r13.drawPath(this.play, this.playPaint);
        r13.restore();
        if (r7 > 0.0f) goto L9;
    L12:
        r13.restore();
        return;
    L9:
        if (this.disabled == false) goto L11;
        r13.drawLine(r8 - AndroidUtilities.dpf2(8.33f), r9 - AndroidUtilities.dpf2(8.33f), (r8 - AndroidUtilities.dpf2(8.33f)) + (AndroidUtilities.dpf2(16.66f) * r7), (r9 - AndroidUtilities.dpf2(8.33f)) + (AndroidUtilities.dpf2(16.66f) * r7), this.clearPaint);
        r13.drawLine(r8 - AndroidUtilities.dpf2(8.33f), r9 - AndroidUtilities.dpf2(8.33f), (AndroidUtilities.dpf2(16.66f) * r7) + (r8 - AndroidUtilities.dpf2(8.33f)), (AndroidUtilities.dpf2(16.66f) * r7) + (r9 - AndroidUtilities.dpf2(8.33f)), this.strokePaint);
        goto L12
    L11:
        r13.drawLine(r8 + AndroidUtilities.dpf2(8.33f), r9 + AndroidUtilities.dpf2(8.33f), (AndroidUtilities.dpf2(8.33f) + r8) - (AndroidUtilities.dpf2(16.66f) * r7), (AndroidUtilities.dpf2(8.33f) + r9) - (AndroidUtilities.dpf2(16.66f) * r7), this.clearPaint);
        r13.drawLine(AndroidUtilities.dpf2(8.33f) + r8, AndroidUtilities.dpf2(8.33f) + r9, (r8 + AndroidUtilities.dpf2(8.33f)) - (AndroidUtilities.dpf2(16.66f) * r7), (r9 + AndroidUtilities.dpf2(8.33f)) - (AndroidUtilities.dpf2(16.66f) * r7), this.strokePaint);
        goto L12
    L5:
        r13.save();
        goto L6
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return AndroidUtilities.dp(24.0f);
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return AndroidUtilities.dp(24.0f);
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -2;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r2) {
        this.strokePaint.setAlpha(r2);
        this.playPaint.setAlpha(r2);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r2) {
        this.playPaint.setColorFilter(r2);
        this.strokePaint.setColorFilter(r2);
    }

    public void setDisabled(boolean r1, boolean r2) {
        this.disabled = r1;
        if (r2 == true) goto L5;
        this.animatedDisabled.force(r1);
    L5:
        invalidateSelf();
    }
}
