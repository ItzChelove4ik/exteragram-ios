package org.telegram.ui.Components;

import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;

/* loaded from: classes7.dex */
public class TextPaintSpan extends MetricAffectingSpan {
    private TextPaint textPaint;

    public TextPaintSpan(TextPaint r1) {
        this.textPaint = r1;
    }

    @Override // android.text.style.CharacterStyle
    public void updateDrawState(TextPaint r2) {
        r2.setColor(this.textPaint.getColor());
        r2.setTypeface(this.textPaint.getTypeface());
        r2.setFlags(this.textPaint.getFlags());
        r2.setTextSize(this.textPaint.getTextSize());
        TextPaint r1 = this.textPaint;
        r2.baselineShift = r1.baselineShift;
        r2.bgColor = r1.bgColor;
    }

    @Override // android.text.style.MetricAffectingSpan
    public void updateMeasureState(TextPaint r2) {
        r2.setColor(this.textPaint.getColor());
        r2.setTypeface(this.textPaint.getTypeface());
        r2.setFlags(this.textPaint.getFlags());
        r2.setTextSize(this.textPaint.getTextSize());
        TextPaint r1 = this.textPaint;
        r2.baselineShift = r1.baselineShift;
        r2.bgColor = r1.bgColor;
    }
}
