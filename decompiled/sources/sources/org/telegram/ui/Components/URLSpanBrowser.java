package org.telegram.ui.Components;

import android.net.Uri;
import android.text.TextPaint;
import android.text.style.URLSpan;
import android.view.View;
import org.telegram.messenger.browser.Browser;
import org.telegram.ui.Components.TextStyleSpan;

/* loaded from: classes3.dex */
public class URLSpanBrowser extends URLSpan {
    private TextStyleSpan.TextStyleRun style;

    public URLSpanBrowser(String r3, TextStyleSpan.TextStyleRun r4) {
        if (r3 == null) goto L4;
        r3 = r3.replace(8238, ' ');
    L4:
        super(r3);
        this.style = r4;
    }

    public TextStyleSpan.TextStyleRun getStyle() {
        return this.style;
    }

    @Override // android.text.style.URLSpan, android.text.style.ClickableSpan
    public void onClick(View r1) {
        Uri r0 = Uri.parse(getURL());
        Browser.openUrl(r1.getContext(), r0);
    }

    @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
    public void updateDrawState(TextPaint r1) {
        super.updateDrawState(r1);
        TextStyleSpan.TextStyleRun r0 = this.style;
        if (r0 == null) goto L5;
        r0.applyStyle(r1);
    L5:
        r1.setUnderlineText(true);
    }
}
