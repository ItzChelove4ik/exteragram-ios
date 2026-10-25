package org.telegram.ui.Components;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLog;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public class LetterDrawable extends Drawable {
    private static TextPaint namePaint;
    private static TextPaint namePaintSmallTopic;
    private static TextPaint namePaintTopic;
    public static Paint paint;
    private RectF rect;
    public float scale;
    private StringBuilder stringBuilder;
    int style;
    private float textHeight;
    private StaticLayout textLayout;
    private float textLeft;
    final TextPaint textPaint;
    private float textWidth;

    static {
        paint = new Paint();
    }

    public LetterDrawable(Theme.ResourcesProvider r3, int r4) {
        this.rect = new RectF();
        this.stringBuilder = new StringBuilder(5);
        this.scale = 1.0f;
        this.style = r4;
        if (r4 != 0) goto L10;
        if (namePaint != null) goto L7;
        namePaint = new TextPaint(1);
    L7:
        namePaint.setTextSize(AndroidUtilities.dp(28.0f));
        paint.setColor(Theme.getColor(Theme.key_sharedMedia_linkPlaceholder, r3));
        namePaint.setColor(Theme.getColor(Theme.key_sharedMedia_linkPlaceholderText, r3));
        this.textPaint = namePaint;
        return;
    L10:
        if (r4 != 1) goto L17;
        if (namePaintTopic != null) goto L14;
        namePaintTopic = new TextPaint(1);
    L14:
        namePaintTopic.setColor(-1);
        namePaintTopic.setTextSize(AndroidUtilities.dp(13.0f));
        namePaintTopic.setTypeface(Typeface.create(Typeface.DEFAULT, 1));
        this.textPaint = namePaintTopic;
        return;
    L17:
        if (namePaintSmallTopic != null) goto L19;
        namePaintSmallTopic = new TextPaint(1);
    L19:
        namePaintSmallTopic.setColor(-1);
        namePaintSmallTopic.setTextSize(Theme.chat_topicTextPaint.getTextSize() * 0.75f);
        namePaintSmallTopic.setTypeface(Typeface.create(Typeface.DEFAULT, 1));
        this.textPaint = namePaintSmallTopic;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r7) {
        Rect r0 = getBounds();
        if (r0 != null) goto L6;
        return;
    L6:
        if (this.style != 0) goto L8;
        this.rect.set(r0.left, r0.top, r0.right, r0.bottom);
        r7.drawRoundRect(this.rect, AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), paint);
    L8:
        r7.save();
        float r1 = this.scale;
        if (r1 == 1.0f) goto L12;
        r7.scale(r1, r1, r0.centerX(), r0.centerY());
    L12:
        if (this.textLayout == null) goto L14;
        float r12 = r0.width();
        r7.translate((r0.left + ((r12 - this.textWidth) / 2.0f)) - this.textLeft, r0.top + ((r12 - this.textHeight) / 2.0f));
        this.textLayout.draw(r7);
    L14:
        r7.restore();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 0;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 0;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -2;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r1) {
        this.textPaint.setAlpha(r1);
        paint.setAlpha(r1);
    }

    public void setBackgroundColor(int r1) {
        paint.setColor(r1);
    }

    public void setColor(int r1) {
        this.textPaint.setColor(r1);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r1) {
    }

    public void setTitle(String r11) {
        this.stringBuilder.setLength(0);
        if (r11 == null) goto L8;
        if (r11.length() <= 0) goto L8;
        this.stringBuilder.append(r11, 0, Character.charCount(r11.codePointAt(0)));
    L8:
        if (this.stringBuilder.length() > 0) goto L19;
        this.textLayout = null;
        return;
    L19:
        StaticLayout r2 = new StaticLayout(this.stringBuilder.toString().toUpperCase(), this.textPaint, AndroidUtilities.dp(100.0f), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);     // Catch: Exception -> L14
        this.textLayout = r2;     // Catch: Exception -> L14
        if (r2.getLineCount() <= 0) goto L21;
        this.textLeft = this.textLayout.getLineLeft(0);     // Catch: Exception -> L14
        this.textWidth = this.textLayout.getLineWidth(0);     // Catch: Exception -> L14
        this.textHeight = this.textLayout.getLineBottom(0);     // Catch: Exception -> L14
        return;
    L21:
        return;
    L14:
        e = move-exception;
        FileLog.e(e);
    }
}
