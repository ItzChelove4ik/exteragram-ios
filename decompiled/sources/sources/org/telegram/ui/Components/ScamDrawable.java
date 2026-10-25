package org.telegram.ui.Components;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;

/* loaded from: classes3.dex */
public class ScamDrawable extends Drawable {
    int alpha;
    int colorAlpha;
    private int currentType;
    private Paint paint;
    private RectF rect;
    private String text;
    private TextPaint textPaint;
    private int textWidth;

    public ScamDrawable(int r3, int r4) {
        this.rect = new RectF();
        this.paint = new Paint(1);
        TextPaint r0 = new TextPaint(1);
        this.textPaint = r0;
        this.colorAlpha = 255;
        this.alpha = 255;
        this.currentType = r4;
        r0.setTextSize(AndroidUtilities.dp(r3));
        this.textPaint.setTypeface(AndroidUtilities.bold());
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setStrokeWidth(AndroidUtilities.dp(1.0f));
        if (r4 != 0) goto L5;
        this.text = LocaleController.getString(R.string.ScamMessage);
    L6:
        this.textWidth = (int) Math.ceil(this.textPaint.measureText(this.text));
        return;
    L5:
        this.text = LocaleController.getString(R.string.FakeMessage);
        goto L6
    }

    public void checkText() {
        if (this.currentType != 0) goto L5;
        String r0 = LocaleController.getString(R.string.ScamMessage);
    L7:
        if (r0.equals(this.text) == true) goto L10;
        this.text = r0;
        this.textWidth = (int) Math.ceil(this.textPaint.measureText(r0));
        return;
    L10:
        return;
    L5:
        r0 = LocaleController.getString(R.string.FakeMessage);
        goto L7
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r5) {
        this.rect.set(getBounds());
        r5.drawRoundRect(this.rect, AndroidUtilities.dp(2.0f), AndroidUtilities.dp(2.0f), this.paint);
        r5.drawText(this.text, this.rect.left + AndroidUtilities.dp(5.0f), this.rect.top + AndroidUtilities.dp(12.0f), this.textPaint);
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return AndroidUtilities.dp(16.0f);
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.textWidth + AndroidUtilities.dp(10.0f);
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -2;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r3) {
        if (this.alpha == r3) goto L6;
        int r32 = (int) (this.colorAlpha * (r3 / 255.0f));
        this.paint.setAlpha(r32);
        this.textPaint.setAlpha(r32);
        return;
    }

    public void setColor(int r2) {
        this.textPaint.setColor(r2);
        this.paint.setColor(r2);
        this.colorAlpha = Color.alpha(r2);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r1) {
    }
}
