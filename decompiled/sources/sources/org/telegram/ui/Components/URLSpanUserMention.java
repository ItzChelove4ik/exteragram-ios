package org.telegram.ui.Components;

import android.text.TextPaint;
import android.view.View;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.TextStyleSpan;

/* loaded from: classes3.dex */
public class URLSpanUserMention extends URLSpanNoUnderline {
    private int currentType;
    private TextStyleSpan.TextStyleRun style;

    public URLSpanUserMention(String r2, int r3) {
        this(r2, r3, null);
    }

    public URLSpanUserMention(String r1, int r2, TextStyleSpan.TextStyleRun r3) {
        super(r1);
        this.currentType = r2;
        this.style = r3;
    }

    @Override // org.telegram.ui.Components.URLSpanNoUnderline, android.text.style.URLSpan, android.text.style.ClickableSpan
    public void onClick(View r1) {
        super.onClick(r1);
    }

    @Override // org.telegram.ui.Components.URLSpanNoUnderline, android.text.style.ClickableSpan, android.text.style.CharacterStyle
    public void updateDrawState(TextPaint r3) {
        super.updateDrawState(r3);
        int r0 = this.currentType;
        if (r0 != 3) goto L6;
        r3.setColor(Theme.getColor(Theme.key_windowBackgroundWhiteLinkText));
    L12:
        TextStyleSpan.TextStyleRun r2 = this.style;
        if (r2 == null) goto L16;
        r2.applyStyle(r3);
        return;
    L16:
        r3.setUnderlineText(false);
        return;
    L6:
        if (r0 != 2) goto L9;
        r3.setColor(-1);
        goto L12
    L9:
        if (r0 != 1) goto L11;
        r3.setColor(Theme.getColor(Theme.key_chat_messageLinkOut));
        goto L12
    L11:
        r3.setColor(Theme.getColor(Theme.key_chat_messageLinkIn));
        goto L12
    }
}
