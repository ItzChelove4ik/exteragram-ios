package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.text.TextPaint;
import android.util.TypedValue;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public class HintEditText extends EditTextBoldCursor {
    protected TextPaint hintPaint;
    private String hintText;
    private Rect rect;

    public HintEditText(Context r2) {
        super(r2);
        this.hintPaint = new TextPaint(1);
        this.rect = new Rect();
        this.hintPaint.setColor(Theme.getColor(Theme.key_windowBackgroundWhiteHintText));
    }

    public String getHintText() {
        return this.hintText;
    }

    @Override // org.telegram.ui.Components.EditTextBoldCursor, org.telegram.ui.Components.EditTextEffects, android.widget.TextView, android.view.View
    public void onDraw(Canvas r11) {
        if (this.hintText != null) goto L5;
    L20:
        super.onDraw(r11);
        return;
    L5:
        if (length() >= this.hintText.length()) goto L20;
        int r4 = 0;
        float r6 = 0.0f;
    L8:
        if (r4 >= this.hintText.length()) goto L20;
        if (r4 >= length()) goto L12;
        float r1 = getPaint().measureText(getText(), r4, r4 + 1);
    L14:
        if (shouldDrawBehindText(r4) == false) goto L16;
    L18:
        int r9 = this.hintPaint.getColor();
        r11.save();
        TextPaint r2 = this.hintPaint;
        String r3 = this.hintText;
        r2.getTextBounds(r3, 0, r3.length(), this.rect);
        float r7 = (getHeight() + this.rect.height()) / 2.0f;
        onPreDrawHintCharacter(r4, r11, r6, r7);
        Canvas r22 = r11;
        r22.drawText(this.hintText, r4, r4 + 1, r6, r7, this.hintPaint);
        r6 = r6 + r1;
        r22.restore();
        this.hintPaint.setColor(r9);
    L19:
        r4 = r4 + 1;
        r11 = r22;
        goto L8
    L16:
        if (r4 >= length()) goto L18;
        r6 = r6 + r1;
        r22 = r11;
        goto L19
    L12:
        r1 = this.hintPaint.measureText(this.hintText, r4, r4 + 1);
        goto L14
    }

    @Override // org.telegram.ui.Components.EditTextEffects, android.widget.TextView, android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        super.onLayout(r1, r2, r3, r4, r5);
        onTextChange();
    }

    public void onPreDrawHintCharacter(int r1, Canvas r2, float r3, float r4) {
    }

    public void onTextChange() {
        invalidate();
    }

    public void setHintText(String r1) {
        this.hintText = r1;
        onTextChange();
        setText(getText());
    }

    @Override // org.telegram.ui.Components.EditTextBoldCursor, android.widget.TextView
    public void setTextSize(int r2, float r3) {
        super.setTextSize(r2, r3);
        this.hintPaint.setTextSize(TypedValue.applyDimension(r2, r3, getResources().getDisplayMetrics()));
    }

    public boolean shouldDrawBehindText(int r1) {
        return false;
    }
}
