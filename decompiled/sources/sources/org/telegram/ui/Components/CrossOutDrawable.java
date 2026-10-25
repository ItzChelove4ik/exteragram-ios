package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public class CrossOutDrawable extends Drawable {
    int color;
    int colorKey;
    boolean cross;
    Drawable iconDrawable;
    private float inversionProgress;
    private float lenOffsetBottom;
    private float lenOffsetTop;
    Paint paint;
    float progress;
    RectF rectF;
    private float startXPadding;
    private float startYPadding;
    private float stopXPadding;
    private float stopYPadding;
    private float xOffset;
    final Paint xRefPaint;

    public CrossOutDrawable(Context r3, int r4, int r5) {
        this.rectF = new RectF();
        this.paint = new Paint(1);
        Paint r0 = new Paint(1);
        this.xRefPaint = r0;
        this.inversionProgress = 0.0f;
        this.iconDrawable = ContextCompat.getDrawable(r3, r4);
        this.colorKey = r5;
        Paint r32 = this.paint;
        Paint.Style r42 = Paint.Style.STROKE;
        r32.setStyle(r42);
        this.paint.setStrokeWidth(AndroidUtilities.dpf2(1.7f));
        this.paint.setStrokeCap(Paint.Cap.ROUND);
        r0.setColor(-16777216);
        r0.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        r0.setStyle(r42);
        r0.setStrokeWidth(AndroidUtilities.dpf2(2.5f));
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r13) {
        boolean r0 = this.cross;
        if (r0 == false) goto L9;
        float r4 = this.progress;
        if (r4 == 1.0f) goto L9;
        this.progress = r4 + 0.10666667f;
        invalidateSelf();
        if (this.progress <= 1.0f) goto L15;
        this.progress = 1.0f;
    L15:
        int r02 = this.colorKey;
        if (r02 >= 0) goto L18;
        int r03 = ColorUtils.blendARGB(-1, -16777216, this.inversionProgress);
    L20:
        if (this.color == r03) goto L23;
        this.color = r03;
        this.paint.setColor(r03);
        this.iconDrawable.setColorFilter(new PorterDuffColorFilter(r03, PorterDuff.Mode.MULTIPLY));
    L23:
        if (this.progress != 0.0f) goto L26;
        this.iconDrawable.draw(r13);
        return;
    L26:
        this.rectF.set(this.iconDrawable.getBounds());
        r13.saveLayerAlpha(this.rectF, 255, 31);
        this.iconDrawable.draw(r13);
        float r04 = (((this.rectF.left + AndroidUtilities.dpf2(4.5f)) + this.xOffset) + this.lenOffsetTop) + this.startXPadding;
        float r2 = (((this.rectF.top + AndroidUtilities.dpf2(4.5f)) - AndroidUtilities.dp(1.0f)) + this.lenOffsetTop) + this.startYPadding;
        float r1 = (((this.rectF.right - AndroidUtilities.dp(3.0f)) + this.xOffset) - this.lenOffsetBottom) - this.stopXPadding;
        float r5 = (((this.rectF.bottom - AndroidUtilities.dp(1.0f)) - AndroidUtilities.dp(3.0f)) - this.lenOffsetBottom) - this.stopYPadding;
        boolean r42 = this.cross;
        float r6 = this.progress;
        if (r42 == false) goto L30;
        r1 = ((r1 - r04) * r6) + r04;
        r5 = ((r5 - r2) * r6) + r2;
    L29:
        float r7 = r04;
        float r9 = r1;
        r13.drawLine(r7, r2 - this.paint.getStrokeWidth(), r9, r5 - this.paint.getStrokeWidth(), this.xRefPaint);
        float r132 = ((this.xRefPaint.getStrokeWidth() - this.paint.getStrokeWidth()) / 2.0f) + 1.0f;
        r13.drawLine(r7, r2 - r132, r9, r5 - r132, this.xRefPaint);
        r13.drawLine(r7, r2, r9, r5, this.paint);
        r13.restore();
        return;
    L30:
        r04 = r04 + ((r1 - r04) * (1.0f - r6));
        r2 = r2 + ((r5 - r2) * (1.0f - r6));
        goto L29
    L18:
        r03 = Theme.getColor(r02);
    L9:
        if (r0 == true) goto L15;
        float r05 = this.progress;
        if (r05 == 0.0f) goto L15;
        this.progress = r05 - 0.10666667f;
        invalidateSelf();
        if (this.progress >= 0.0f) goto L15;
        this.progress = 0.0f;
        goto L15
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.iconDrawable.getIntrinsicHeight();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.iconDrawable.getIntrinsicWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -2;
    }

    public float getProgress() {
        return this.progress;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r1) {
    }

    @Override // android.graphics.drawable.Drawable
    public void setBounds(int r1, int r2, int r3, int r4) {
        super.setBounds(r1, r2, r3, r4);
        this.iconDrawable.setBounds(r1, r2, r3, r4);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r1) {
    }

    public void setCrossOut(boolean r3, boolean r4) {
        if (this.cross == r3) goto L15;
        this.cross = r3;
        float r0 = 0.0f;
        if (r4 == true) goto L9;
        if (r3 == false) goto L8;
        r0 = 1.0f;
    L8:
        this.progress = r0;
    L13:
        invalidateSelf();
        return;
    L9:
        if (r3 == true) goto L12;
        r0 = 1.0f;
    L12:
        this.progress = r0;
        goto L13
    }

    public void setOffsets(float r1, float r2, float r3) {
        this.xOffset = r1;
        this.lenOffsetTop = r2;
        this.lenOffsetBottom = r3;
        invalidateSelf();
    }

    public void setPaddings(float r1, float r2, float r3, float r4) {
        this.startXPadding = r1;
        this.stopXPadding = r2;
        this.startYPadding = r3;
        this.stopYPadding = r4;
        invalidateSelf();
    }

    public void setStrokeWidth(float r2) {
        this.paint.setStrokeWidth(r2);
        this.xRefPaint.setStrokeWidth(r2 * 1.47f);
    }
}
