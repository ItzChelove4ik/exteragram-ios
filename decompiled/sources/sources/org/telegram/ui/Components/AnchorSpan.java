package org.telegram.ui.Components;

import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;

/* loaded from: classes7.dex */
public class AnchorSpan extends MetricAffectingSpan {
    private String name;

    public AnchorSpan(String r1) {
        this.name = r1.toLowerCase();
    }

    public String getName() {
        return this.name;
    }

    @Override // android.text.style.CharacterStyle
    public void updateDrawState(TextPaint r1) {
    }

    @Override // android.text.style.MetricAffectingSpan
    public void updateMeasureState(TextPaint r1) {
    }
}
