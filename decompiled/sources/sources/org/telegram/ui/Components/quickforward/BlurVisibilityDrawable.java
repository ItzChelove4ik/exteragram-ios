package org.telegram.ui.Components.quickforward;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import androidx.core.math.MathUtils;
import org.telegram.messenger.Utilities;

/* loaded from: classes7.dex */
public class BlurVisibilityDrawable extends Drawable {
    private int alpha;
    private Bitmap bitmap;
    private float bitmapScale;
    private int blurRadius;
    private Canvas canvas;
    private final DrawRunnable drawRunnable;
    private final Paint emptyPaint;
    private int height;
    private int left;
    private int top;
    private int width;

    public interface DrawRunnable {
        void draw(Canvas r1, int r2);
    }

    public BlurVisibilityDrawable(DrawRunnable r3) {
        this.emptyPaint = new Paint(2);
        this.alpha = 255;
        this.drawRunnable = r3;
    }

    private void drawBlur(Canvas r3, int r4) {
        if (r4 > 0) goto L4;
        return;
    L4:
        if (this.bitmap == null) goto L8;
        this.emptyPaint.setAlpha(r4);
        r3.save();
        int r42 = this.left;
        int r0 = this.blurRadius;
        r3.translate(r42 - r0, this.top - r0);
        float r43 = this.bitmapScale;
        r3.scale(r43, r43);
        r3.drawBitmap(this.bitmap, 0.0f, 0.0f, this.emptyPaint);
        r3.restore();
        return;
    }

    private void drawNormal(Canvas r3, int r4) {
        if (r4 <= 0) goto L5;
        r3.save();
        r3.translate(this.left, this.top);
        this.drawRunnable.draw(r3, r4);
        r3.restore();
        return;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r19) {
        int r2 = this.alpha;
        if (r2 != 255) goto L6;
        drawNormal(r19, 255);
        return;
    L6:
        if (r2 != 0) goto L8;
        return;
    L8:
        double r4 = r2 / 255.0d;
        double r10 = r4 / ((1.0d - r4) * 6.0d);
        double r8 = 1.0d + r10;
        double r6 = ((-r8) + Math.sqrt((r8 * r8) - (((-r10) * 4.0d) * (-r4)))) / ((-2.0d) * r10);
        int r22 = MathUtils.clamp((int) ((r10 * r6) * 255.0d), 0, 255);
        drawBlur(r19, MathUtils.clamp((int) (r6 * 255.0d), 0, 255));
        drawNormal(r19, r22);
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.alpha;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public boolean hasBitmap() {
        if (this.bitmap == null) goto L6;
        return true;
    L6:
        return false;
    }

    public void recycle() {
        Bitmap r0 = this.bitmap;
        if (r0 == null) goto L6;
        r0.recycle();
        this.bitmap = null;
        return;
    }

    public void render(int r4, int r5, int r6, float r7) {
        int r0 = r6 * 2;
        int r1 = (int) ((r4 + r0) / r7);
        int r02 = (int) ((r0 + r5) / r7);
        Bitmap r2 = this.bitmap;
        if (r2 != null) goto L5;
    L10:
        Bitmap r22 = this.bitmap;
        if (r22 == null) goto L13;
        r22.recycle();
    L13:
        this.bitmap = Bitmap.createBitmap(r1, r02, Bitmap.Config.ARGB_8888);
        this.canvas = new Canvas(this.bitmap);
    L14:
        this.bitmapScale = r7;
        this.blurRadius = r6;
        this.width = r4;
        this.height = r5;
        this.canvas.save();
        float r52 = r6 / r7;
        this.canvas.translate(r52, r52);
        float r62 = 1.0f / r7;
        this.canvas.scale(r62, r62);
        this.drawRunnable.draw(this.canvas, 255);
        Utilities.stackBlurBitmap(this.bitmap, (int) r52);
        this.canvas.restore();
        return;
    L5:
        if (r2.getWidth() != r1) goto L10;
        if (this.bitmap.getHeight() != r02) goto L10;
        this.bitmap.eraseColor(0);
        goto L14
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r1) {
        this.alpha = r1;
    }

    @Override // android.graphics.drawable.Drawable
    public void setBounds(int r1, int r2, int r3, int r4) {
        this.left = r1;
        this.top = r2;
        super.setBounds(r1, r2, r3, r4);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r1) {
    }
}
