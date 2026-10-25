package org.telegram.ui.Components;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.view.View;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.SvgHelper;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes3.dex */
public class LoadingStickerDrawable extends Drawable {
    private Bitmap bitmap;
    int currentColor0;
    int currentColor1;
    private float gradientWidth;
    private long lastUpdateTime;
    private View parentView;
    private LinearGradient placeholderGradient;
    private Matrix placeholderMatrix;
    private Paint placeholderPaint;
    private float totalTranslation;

    public LoadingStickerDrawable(View r3, String r4, int r5, int r6) {
        this.placeholderPaint = new Paint(2);
        this.bitmap = SvgHelper.getBitmapByPathOnly(r4, 512, 512, r5, r6);
        this.parentView = r3;
        this.placeholderMatrix = new Matrix();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r9) {
        if (this.bitmap != null) goto L5;
        return;
    L5:
        setColors(Theme.key_dialogBackground, Theme.key_dialogBackgroundGray);
        Rect r0 = getBounds();
        r9.drawRect(r0.left, r0.top, r0.right, r0.bottom, this.placeholderPaint);
        long r02 = SystemClock.elapsedRealtime();
        long r2 = Math.abs(this.lastUpdateTime - r02);
        if (r2 <= 17) goto L8;
        r2 = 16;
    L8:
        this.lastUpdateTime = r02;
        this.totalTranslation += (r2 * this.gradientWidth) / 1800.0f;
    L9:
        float r92 = this.totalTranslation;
        float r03 = this.gradientWidth;
        if (r92 < (r03 * 2.0f)) goto L12;
        this.totalTranslation = r92 - (r03 * 2.0f);
        goto L9
    L12:
        this.placeholderMatrix.setTranslate(r92, 0.0f);
        this.placeholderGradient.setLocalMatrix(this.placeholderMatrix);
        this.parentView.invalidate();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -2;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r1) {
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r1) {
    }

    public void setColors(int r10, int r11) {
        int r102 = Theme.getColor(r10);
        int r112 = Theme.getColor(r11);
        if (this.currentColor0 == r102) goto L5;
    L8:
        this.currentColor0 = r102;
        this.currentColor1 = r112;
        int r103 = AndroidUtilities.getAverageColor(r112, r102);
        this.placeholderPaint.setColor(r112);
        float r4 = AndroidUtilities.dp(500.0f);
        this.gradientWidth = r4;
        LinearGradient r1 = new LinearGradient(0.0f, 0.0f, r4, 0.0f, new int[]{r112, r103, r112}, new float[]{0.0f, 0.18f, 0.36f}, Shader.TileMode.REPEAT);
        this.placeholderGradient = r1;
        r1.setLocalMatrix(this.placeholderMatrix);
        Bitmap r113 = this.bitmap;
        Shader.TileMode r0 = Shader.TileMode.CLAMP;
        this.placeholderPaint.setShader(new ComposeShader(this.placeholderGradient, new BitmapShader(r113, r0, r0), PorterDuff.Mode.MULTIPLY));
        return;
    L5:
        if (this.currentColor1 != r112) goto L8;
    }
}
