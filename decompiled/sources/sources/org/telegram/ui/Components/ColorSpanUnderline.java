package org.telegram.ui.Components;

import android.text.TextPaint;
import android.text.style.ForegroundColorSpan;

/* loaded from: classes7.dex */
public class ColorSpanUnderline extends ForegroundColorSpan {
    public ColorSpanUnderline(int r1) {
        super(r1);
    }

    @Override // android.text.style.ForegroundColorSpan, android.text.style.CharacterStyle
    public void updateDrawState(TextPaint r1) {
        super.updateDrawState(r1);
        r1.setUnderlineText(true);
    }
}
