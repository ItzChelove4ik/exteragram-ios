package org.telegram.ui.Stories.recorder;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.core.graphics.ColorUtils;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Stories.recorder.FlashViews;

/* loaded from: classes7.dex */
public class ToggleButton extends View implements FlashViews.Invertable {
    private Bitmap activeBitmap;
    private final Paint activeBitmapPaint;
    private final Paint activePaint;
    private int activeResId;
    private final Path clipPath;
    private Drawable drawable;
    private float value;
    private final AnimatedFloat valueAnimated;

    public ToggleButton(Context r11, int r12, int r13) {
        super(r11);
        Paint r0 = new Paint(1);
        this.activePaint = r0;
        Paint r1 = new Paint(3);
        this.activeBitmapPaint = r1;
        this.valueAnimated = new AnimatedFloat(this, 0, 350, CubicBezierInterpolator.EASE_OUT_QUINT);
        this.clipPath = new Path();
        this.drawable = r11.getResources().getDrawable(r12).mutate();
        this.activeResId = r13;
        r0.setColor(-1);
        r1.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.activeBitmap != null) goto L6;
        this.activeBitmap = BitmapFactory.decodeResource(getResources(), this.activeResId);
        return;
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Bitmap r0 = this.activeBitmap;
        if (r0 == null) goto L6;
        r0.recycle();
        this.activeBitmap = null;
        return;
    }

    @Override // android.view.View
    public void onDraw(Canvas r14) {
        super.onDraw(r14);
        float r0 = this.valueAnimated.set(this.value);
        int r1 = this.drawable.getIntrinsicWidth();
        int r2 = this.drawable.getIntrinsicHeight();
        Rect r3 = AndroidUtilities.rectTmp2;
        r3.set((getWidth() - r1) / 2, (getHeight() - r2) / 2, (getWidth() + r1) / 2, (getHeight() + r2) / 2);
        if (r0 > 0.0f) goto L6;
        this.drawable.setBounds(r3);
        this.drawable.draw(r14);
    L9:
        if (r0 <= 0.0f) goto L15;
        r14.saveLayerAlpha(0.0f, 0.0f, getWidth(), getHeight(), 255, 31);
        r14.drawCircle(getWidth() / 2.0f, getHeight() / 2.0f, AndroidUtilities.dp(16.0f) * r0, this.activePaint);
        r14.save();
        Bitmap r142 = this.activeBitmap;
        if (r142 == null) goto L13;
        r14.drawBitmap(r142, null, r3, this.activeBitmapPaint);
    L13:
        r14.restore();
        r14.restore();
        return;
    L15:
        return;
    L6:
        if (r0 >= 1.0f) goto L9;
        r14.save();
        this.clipPath.rewind();
        this.clipPath.addCircle(getWidth() / 2.0f, getHeight() / 2.0f, AndroidUtilities.dp(16.0f) * r0, Path.Direction.CW);
        r14.clipPath(this.clipPath, Region.Op.DIFFERENCE);
        this.drawable.setBounds(r3);
        this.drawable.draw(r14);
        r14.restore();
        goto L9
    }

    @Override // org.telegram.ui.Stories.recorder.FlashViews.Invertable
    public void setInvert(float r7) {
        this.drawable.setColorFilter(new PorterDuffColorFilter(ColorUtils.blendARGB(-1, -16777216, r7), PorterDuff.Mode.MULTIPLY));
        this.activePaint.setColor(ColorUtils.blendARGB(-1, -16777216, r7));
    }

    public void setValue(boolean r1) {
        if (r1 == false) goto L4;
        float r12 = 1.0f;
    L5:
        this.value = r12;
        invalidate();
        return;
    L4:
        r12 = 0.0f;
        goto L5
    }
}
