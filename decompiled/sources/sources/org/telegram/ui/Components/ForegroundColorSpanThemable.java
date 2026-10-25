package org.telegram.ui.Components;

import android.text.TextPaint;
import android.text.style.CharacterStyle;
import android.text.style.UpdateAppearance;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes3.dex */
public class ForegroundColorSpanThemable extends CharacterStyle implements UpdateAppearance {
    private float alpha;
    private int color;
    private int colorKey;
    private final Theme.ResourcesProvider resourcesProvider;

    public ForegroundColorSpanThemable(int r2) {
        this(r2, null);
    }

    public ForegroundColorSpanThemable(int r2, Theme.ResourcesProvider r3) {
        this.alpha = 1.0f;
        this.colorKey = r2;
        this.resourcesProvider = r3;
    }

    public void setAlpha(float r1) {
        this.alpha = r1;
    }

    public void setColorKey(int r1) {
        this.colorKey = r1;
    }

    @Override // android.text.style.CharacterStyle
    public void updateDrawState(TextPaint r3) {
        this.color = Theme.multAlpha(Theme.getColor(this.colorKey, this.resourcesProvider), this.alpha);
        int r0 = r3.getColor();
        int r2 = this.color;
        if (r0 == r2) goto L6;
        r3.setColor(r2);
        return;
    }
}
