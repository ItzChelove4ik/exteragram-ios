package org.telegram.ui.Components;

import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes3.dex */
public class TypefaceSpan extends MetricAffectingSpan {
    private int color;
    private int colorKey;
    Theme.ResourcesProvider resourcesProvider;
    private int textSize;
    private Typeface typeface;

    public TypefaceSpan(Typeface r2) {
        this.colorKey = -1;
        this.typeface = r2;
    }

    public TypefaceSpan(Typeface r2, int r3) {
        this.colorKey = -1;
        this.typeface = r2;
        this.textSize = r3;
    }

    public TypefaceSpan(Typeface r2, int r3, int r4) {
        this.colorKey = -1;
        this.typeface = r2;
        if (r3 <= 0) goto L5;
        this.textSize = r3;
    L5:
        this.color = r4;
    }

    public TypefaceSpan(Typeface r2, int r3, int r4, Theme.ResourcesProvider r5) {
        this.colorKey = -1;
        this.typeface = r2;
        if (r3 <= 0) goto L5;
        this.textSize = r3;
    L5:
        this.resourcesProvider = r5;
        this.colorKey = r4;
        this.color = Theme.getColor(r4, r5);
    }

    public Typeface getTypeface() {
        return this.typeface;
    }

    public void setColor(int r1) {
        this.color = r1;
    }

    @Override // android.text.style.CharacterStyle
    public void updateDrawState(TextPaint r3) {
        int r0 = this.colorKey;
        if (r0 < 0) goto L5;
        this.color = Theme.getColor(r0, this.resourcesProvider);
    L5:
        Typeface r02 = this.typeface;
        if (r02 == null) goto L8;
        r3.setTypeface(r02);
    L8:
        int r03 = this.textSize;
        if (r03 == 0) goto L11;
        r3.setTextSize(r03);
    L11:
        int r2 = this.color;
        if (r2 == 0) goto L14;
        r3.setColor(r2);
    L14:
        r3.setFlags(r3.getFlags() | 128);
    }

    @Override // android.text.style.MetricAffectingSpan
    public void updateMeasureState(TextPaint r2) {
        Typeface r0 = this.typeface;
        if (r0 == null) goto L5;
        r2.setTypeface(r0);
    L5:
        int r1 = this.textSize;
        if (r1 == 0) goto L8;
        r2.setTextSize(r1);
    L8:
        r2.setFlags(r2.getFlags() | 128);
    }
}
