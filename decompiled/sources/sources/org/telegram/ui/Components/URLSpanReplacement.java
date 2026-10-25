package org.telegram.ui.Components;

import android.net.Uri;
import android.text.TextPaint;
import android.text.style.URLSpan;
import android.view.View;
import org.telegram.messenger.browser.Browser;
import org.telegram.ui.Components.TextStyleSpan;
import org.telegram.ui.LaunchActivity;

/* loaded from: classes3.dex */
public class URLSpanReplacement extends URLSpan {
    private boolean navigateToPremiumBot;
    private TextStyleSpan.TextStyleRun style;

    public URLSpanReplacement(String r2) {
        this(r2, null);
    }

    public URLSpanReplacement(String r3, TextStyleSpan.TextStyleRun r4) {
        if (r3 == null) goto L4;
        r3 = r3.replace(8238, ' ');
    L4:
        super(r3);
        this.style = r4;
    }

    public TextStyleSpan.TextStyleRun getTextStyleRun() {
        return this.style;
    }

    @Override // android.text.style.URLSpan, android.text.style.ClickableSpan
    public void onClick(View r3) {
        if (this.navigateToPremiumBot == true) goto L5;
    L7:
        Uri r2 = Uri.parse(getURL());
        Browser.openUrl(r3.getContext(), r2);
        return;
    L5:
        if ((r3.getContext() instanceof LaunchActivity) == false) goto L7;
        ((LaunchActivity) r3.getContext()).setNavigateToPremiumBot(true);
        goto L7
    }

    public void setNavigateToPremiumBot(boolean r1) {
        this.navigateToPremiumBot = r1;
    }

    @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
    public void updateDrawState(TextPaint r2) {
        int r0 = r2.getColor();
        super.updateDrawState(r2);
        TextStyleSpan.TextStyleRun r1 = this.style;
        if (r1 == null) goto L10;
        r1.applyStyle(r2);
        if (r2.linkColor != r0) goto L7;
        boolean r12 = true;
    L8:
        r2.setUnderlineText(r12);
        return;
    L7:
        r12 = false;
        goto L8
    }
}
