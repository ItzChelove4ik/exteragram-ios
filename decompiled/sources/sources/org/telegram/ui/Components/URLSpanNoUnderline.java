package org.telegram.ui.Components;

import android.net.Uri;
import android.text.TextPaint;
import android.text.style.URLSpan;
import android.view.View;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.Components.TextStyleSpan;

/* loaded from: classes3.dex */
public class URLSpanNoUnderline extends URLSpan {
    private boolean forceNoUnderline;
    public String label;
    private TLObject object;
    private TextStyleSpan.TextStyleRun style;

    public URLSpanNoUnderline(String r2) {
        this(r2, null);
    }

    public URLSpanNoUnderline(String r3, TextStyleSpan.TextStyleRun r4) {
        if (r3 == null) goto L4;
        r3 = r3.replace(8238, ' ');
    L4:
        super(r3);
        this.forceNoUnderline = false;
        this.style = r4;
    }

    public URLSpanNoUnderline(String r2, boolean r3) {
        this(r2, null);
        this.forceNoUnderline = r3;
    }

    public TLObject getObject() {
        return this.object;
    }

    @Override // android.text.style.URLSpan, android.text.style.ClickableSpan
    public void onClick(View r2) {
        String r1 = getURL();
        if (r1.startsWith("@") == false) goto L6;
        Uri r12 = Uri.parse("https://t.me/".concat(r1.substring(1)));
        Browser.openUrl(r2.getContext(), r12);
        return;
    L6:
        Browser.openUrl(r2.getContext(), r1);
    }

    public void setObject(TLObject r1) {
        this.object = r1;
    }

    @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
    public void updateDrawState(TextPaint r4) {
        int r0 = r4.linkColor;
        int r1 = r4.getColor();
        super.updateDrawState(r4);
        TextStyleSpan.TextStyleRun r2 = this.style;
        if (r2 == null) goto L5;
        r2.applyStyle(r4);
    L5:
        if (r0 == r1) goto L7;
    L9:
        boolean r3 = false;
    L10:
        r4.setUnderlineText(r3);
        return;
    L7:
        if (this.forceNoUnderline == true) goto L9;
        r3 = true;
        goto L10
    }
}
