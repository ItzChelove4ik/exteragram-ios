package org.telegram.ui.Components.Premium.boosts.cells.statistics;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import androidx.core.content.ContextCompat;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.R;

/* loaded from: classes7.dex */
public class CounterDrawable extends Drawable {
    private final Paint bgPaint;
    private final RectF bgRoundRect;
    private final Drawable icon;
    private String text;
    private final TextPaint textPaint;
    private float textWith;

    public CounterDrawable(Context r4) {
        TextPaint r0 = new TextPaint(1);
        this.textPaint = r0;
        TextPaint r2 = new TextPaint(1);
        this.bgPaint = r2;
        this.bgRoundRect = new RectF();
        r0.setColor(-1);
        r0.setTypeface(AndroidUtilities.bold());
        r0.setTextSize(AndroidUtilities.dp(12.0f));
        r2.setColor(-6915073);
        this.icon = ContextCompat.getDrawable(r4, R.drawable.mini_boost_badge);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r8) {
        Rect r0 = getBounds();
        this.bgRoundRect.set(r0.left, r0.top, r0.right, r0.bottom);
        r8.drawRoundRect(this.bgRoundRect, AndroidUtilities.dp(12.0f), AndroidUtilities.dp(12.0f), this.bgPaint);
        this.icon.setBounds(r0.left + AndroidUtilities.dp(2.0f), r0.top + AndroidUtilities.dp(1.0f), (r0.left + AndroidUtilities.dp(2.0f)) + this.icon.getIntrinsicWidth(), (getBounds().top + AndroidUtilities.dp(1.0f)) + this.icon.getIntrinsicHeight());
        this.icon.draw(r8);
        String r1 = this.text;
        if (r1 == null) goto L6;
        r8.drawText(r1, AndroidUtilities.dp(16.5f) + r0.left, r0.top + AndroidUtilities.dp(13.0f), this.textPaint);
        return;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return AndroidUtilities.dp(18.0f);
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return (int) (AndroidUtilities.dp(23.0f) + this.textWith);
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -1;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r1) {
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r1) {
    }

    public void setText(String r2) {
        this.text = r2;
        this.textWith = this.textPaint.measureText(r2);
        invalidateSelf();
    }
}
