package com.exteragram.messenger.pillstack.ui.pills.crypto.utils;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes.dex */
public class ColoredBackground extends Drawable {
    private final Paint paint;
    private final Paint strokePaint;

    public ColoredBackground() {
        this(-14965523, -15431455);
    }

    public ColoredBackground(int r20, int r21) {
        Paint r1 = new Paint(1);
        this.paint = r1;
        Paint r3 = new Paint(1);
        this.strokePaint = r3;
        Shader.TileMode r18 = Shader.TileMode.CLAMP;
        r1.setShader(new LinearGradient(0.0f, 0.0f, 0.0f, AndroidUtilities.dp(28.0f), new int[]{r20, r21}, new float[]{0.0f, 1.0f}, r18));
        r3.setStyle(Paint.Style.STROKE);
        r3.setStrokeWidth(AndroidUtilities.dp(1.0f));
        r3.setShader(new LinearGradient(0.0f, 0.0f, 0.0f, AndroidUtilities.dp(28.0f), new int[]{1308622847, 0, 452984831}, new float[]{0.0f, 0.5f, 1.0f}, r18));
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r5) {
        Rect r0 = getBounds();
        float r1 = AndroidUtilities.dp(14.0f);
        RectF r2 = AndroidUtilities.rectTmp;
        r2.set(r0);
        r5.drawRoundRect(r2, r1, r1, this.paint);
        if (Theme.isCurrentThemeDark() == true) goto L5;
        return;
    L5:
        if (Theme.isCurrentThemeMonet() == true) goto L9;
        float r02 = AndroidUtilities.dp(1.0f);
        this.strokePaint.setStrokeWidth(r02);
        float r03 = r02 / 2.0f;
        r2.inset(r03, r03);
        r5.drawRoundRect(r2, r1, r1, this.strokePaint);
        return;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -2;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r2) {
        this.paint.setAlpha(r2);
        this.strokePaint.setAlpha(r2);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r2) {
        this.paint.setColorFilter(r2);
        this.strokePaint.setColorFilter(r2);
    }
}
