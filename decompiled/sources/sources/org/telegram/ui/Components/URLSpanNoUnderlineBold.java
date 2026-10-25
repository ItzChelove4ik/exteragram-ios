package org.telegram.ui.Components;

import android.text.TextPaint;
import org.telegram.messenger.AndroidUtilities;

/* loaded from: classes3.dex */
public class URLSpanNoUnderlineBold extends URLSpanNoUnderline {
    public URLSpanNoUnderlineBold(String r3) {
        if (r3 == null) goto L4;
        r3 = r3.replace(8238, ' ');
    L4:
        super(r3);
    }

    @Override // org.telegram.ui.Components.URLSpanNoUnderline, android.text.style.ClickableSpan, android.text.style.CharacterStyle
    public void updateDrawState(TextPaint r1) {
        super.updateDrawState(r1);
        r1.setTypeface(AndroidUtilities.bold());
        r1.setUnderlineText(false);
    }
}
