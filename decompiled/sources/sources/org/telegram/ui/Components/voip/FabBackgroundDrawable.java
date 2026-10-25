package org.telegram.ui.Components.voip;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import androidx.annotation.Keep;
import org.telegram.messenger.AndroidUtilities;

/* loaded from: classes7.dex */
public class FabBackgroundDrawable extends Drawable {
    private Paint bgPaint;
    private Bitmap shadowBitmap;
    private Paint shadowPaint;

    public FabBackgroundDrawable() {
        this.bgPaint = new Paint(1);
        Paint r0 = new Paint();
        this.shadowPaint = r0;
        r0.setColor(1275068416);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r6) {
        if (this.shadowBitmap != null) goto L5;
        onBoundsChange(getBounds());
    L5:
        int r0 = Math.min(getBounds().width(), getBounds().height());
        Bitmap r1 = this.shadowBitmap;
        if (r1 == null) goto L8;
        r6.drawBitmap(r1, getBounds().centerX() - (this.shadowBitmap.getWidth() / 2), getBounds().centerY() - (this.shadowBitmap.getHeight() / 2), this.shadowPaint);
    L8:
        float r12 = r0 / 2;
        r6.drawCircle(r12, r12, r0 - AndroidUtilities.dp(4.0f), this.bgPaint);
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -2;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean getPadding(Rect r1) {
        int r0 = AndroidUtilities.dp(4.0f);
        r1.set(r0, r0, r0, r0);
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect r4) {
        int r42 = Math.min(r4.width(), r4.height());
        if (r42 > 0) goto L6;
        this.shadowBitmap = null;
        return;
    L6:
        this.shadowBitmap = Bitmap.createBitmap(r42, r42, Bitmap.Config.ALPHA_8);
        float r1 = r42 / 2;
        new Canvas(this.shadowBitmap).drawCircle(r1, r1, r4 - AndroidUtilities.dp(4.0f), new Paint(1));
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r1) {
    }

    @Keep
    public void setColor(int r2) {
        this.bgPaint.setColor(r2);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r1) {
    }
}
