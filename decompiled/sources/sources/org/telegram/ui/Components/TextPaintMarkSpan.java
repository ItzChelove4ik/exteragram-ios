package org.telegram.ui.Components;

import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;

/* loaded from: classes7.dex */
public class TextPaintMarkSpan extends MetricAffectingSpan {
    private TextPaint textPaint;

    public TextPaintMarkSpan(TextPaint r1) {
        this.textPaint = r1;
    }

    public TextPaint getTextPaint() {
        return this.textPaint;
    }

    @Override // android.text.style.CharacterStyle
    public void updateDrawState(TextPaint r2) {
        TextPaint r0 = this.textPaint;
        if (r0 == null) goto L6;
        r2.setColor(r0.getColor());
        r2.setTypeface(this.textPaint.getTypeface());
        r2.setFlags(this.textPaint.getFlags());
        r2.setTextSize(this.textPaint.getTextSize());
        TextPaint r1 = this.textPaint;
        r2.baselineShift = r1.baselineShift;
        r2.bgColor = r1.bgColor;
        return;
    }

    @Override // android.text.style.MetricAffectingSpan
    public void updateMeasureState(TextPaint r2) {
        TextPaint r0 = this.textPaint;
        if (r0 == null) goto L6;
        r2.setColor(r0.getColor());
        r2.setTypeface(this.textPaint.getTypeface());
        r2.setFlags(this.textPaint.getFlags());
        r2.setTextSize(this.textPaint.getTextSize());
        TextPaint r1 = this.textPaint;
        r2.baselineShift = r1.baselineShift;
        r2.bgColor = r1.bgColor;
        return;
    }
}
