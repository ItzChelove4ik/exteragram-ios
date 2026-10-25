package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import org.mvel2.asm.Opcodes;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.R;

/* loaded from: classes7.dex */
public class MuteDrawable extends Drawable {
    private final AnimatedFloat animatedMuted;
    private Drawable baseDrawable;
    private final Paint clipPaint;
    private boolean muted;
    private final Paint strokePaint;

    public MuteDrawable(Context r11) {
        Paint r0 = new Paint(1);
        this.strokePaint = r0;
        Paint r2 = new Paint(1);
        this.clipPaint = r2;
        this.animatedMuted = new AnimatedFloat(new MuteDrawable$$ExternalSyntheticLambda0(this), 0, 200, CubicBezierInterpolator.EASE_OUT);
        this.baseDrawable = r11.getResources().getDrawable(R.drawable.filled_sound_on).mutate();
        Paint.Style r10 = Paint.Style.STROKE;
        r0.setStyle(r10);
        r0.setStrokeWidth(AndroidUtilities.dpf2(1.566f));
        r0.setColor(-1);
        Paint.Cap r112 = Paint.Cap.ROUND;
        r0.setStrokeCap(r112);
        Paint.Join r1 = Paint.Join.ROUND;
        r0.setStrokeJoin(r1);
        r2.setStyle(r10);
        r2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        r2.setStrokeWidth(AndroidUtilities.dpf2(4.5f));
        r2.setColor(Opcodes.V_PREVIEW);
        r2.setStrokeCap(r112);
        r2.setStrokeJoin(r1);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r17) {
        Rect r1 = getBounds();
        r17.saveLayerAlpha(r1.left, r1.top, r1.right, r1.bottom, 255, 31);
        this.baseDrawable.setBounds(r1);
        this.baseDrawable.draw(r17);
        float r2 = this.animatedMuted.set(this.muted);
        if (r2 <= 0.0f) goto L10;
        float r3 = AndroidUtilities.dpf2(0.783f);
        float r4 = (r1.centerX() - AndroidUtilities.dp(9.0f)) + r3;
        float r6 = (r1.centerY() - AndroidUtilities.dp(9.0f)) + r3;
        float r7 = (r1.centerX() + AndroidUtilities.dp(9.0f)) - r3;
        float r12 = (r1.centerY() + AndroidUtilities.dp(9.0f)) - r3;
        if (this.muted == false) goto L8;
        r4 = AndroidUtilities.lerp(r7, r4, r2);
        r6 = AndroidUtilities.lerp(r12, r6, r2);
    L7:
        float r14 = r12;
        float r11 = r4;
        float r122 = r6;
        float r13 = r7;
        r17.drawLine(r11, r122, r13, r14, this.clipPaint);
        this.strokePaint.setAlpha((int) (Math.min(1.0f, r2 * 10.0f) * 255.0f));
        r17.drawLine(r11, r122, r13, r14, this.strokePaint);
        goto L10
    L8:
        r7 = AndroidUtilities.lerp(r4, r7, r2);
        r12 = AndroidUtilities.lerp(r6, r12, r2);
    L10:
        r17.restore();
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
    public void setAlpha(int r1) {
        this.baseDrawable.setAlpha(r1);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r1) {
    }

    public void setMuted(boolean r2, boolean r3) {
        this.muted = r2;
        if (r3 == true) goto L5;
        this.animatedMuted.set(r2, true);
    L5:
        invalidateSelf();
    }
}
