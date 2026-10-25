package org.telegram.ui.Components;

import android.text.TextPaint;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.TextStyleSpan;

/* loaded from: classes3.dex */
public class URLSpanBotCommand extends URLSpanNoUnderline {
    public static boolean enabled = true;
    public int currentType;
    private TextStyleSpan.TextStyleRun style;

    static {
    }

    public URLSpanBotCommand(String r2, int r3) {
        this(r2, r3, null);
    }

    public URLSpanBotCommand(String r1, int r2, TextStyleSpan.TextStyleRun r3) {
        super(r1);
        this.currentType = r2;
        this.style = r3;
    }

    @Override // org.telegram.ui.Components.URLSpanNoUnderline, android.text.style.ClickableSpan, android.text.style.CharacterStyle
    public void updateDrawState(TextPaint r3) {
        super.updateDrawState(r3);
        int r0 = this.currentType;
        if (r0 != 2) goto L6;
        r3.setColor(-1);
    L17:
        TextStyleSpan.TextStyleRun r2 = this.style;
        if (r2 == null) goto L21;
        r2.applyStyle(r3);
        return;
    L21:
        r3.setUnderlineText(false);
        return;
    L6:
        if (r0 != 1) goto L13;
        if (enabled == false) goto L10;
        int r02 = Theme.key_chat_messageLinkOut;
    L11:
        r3.setColor(Theme.getColor(r02));
        goto L17
    L10:
        r02 = Theme.key_chat_messageTextOut;
        goto L11
    L13:
        if (enabled == false) goto L15;
        int r03 = Theme.key_chat_messageLinkIn;
    L16:
        r3.setColor(Theme.getColor(r03));
        goto L17
    L15:
        r03 = Theme.key_chat_messageTextIn;
        goto L16
    }
}
