package org.telegram.ui.Components;

import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;
import java.lang.ref.WeakReference;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.SharedConfig;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.TextStyleSpan;

/* loaded from: classes3.dex */
public class URLSpanMono extends MetricAffectingSpan {
    private int currentEnd;
    private CharSequence currentMessage;
    private int currentStart;
    private byte currentType;
    private WeakReference<Theme.ResourcesProvider> resourcesProvider;
    private TextStyleSpan.TextStyleRun style;

    public URLSpanMono(CharSequence r7, int r8, int r9, byte r10) {
        this(r7, r8, r9, r10, null);
    }

    public URLSpanMono(CharSequence r1, int r2, int r3, byte r4, TextStyleSpan.TextStyleRun r5) {
        this.currentMessage = r1;
        this.currentStart = r2;
        this.currentEnd = r3;
        this.currentType = r4;
        this.style = r5;
    }

    public void copyToClipboard() {
        AndroidUtilities.addToClipboard(this.currentMessage.subSequence(this.currentStart, this.currentEnd).toString());
    }

    public void setCurrentType(byte r1) {
        this.currentType = r1;
    }

    public void setResourcesProvider(Theme.ResourcesProvider r2) {
        if (r2 != null) goto L4;
        WeakReference<Theme.ResourcesProvider> r22 = null;
    L5:
        this.resourcesProvider = r22;
        return;
    L4:
        r22 = new WeakReference(r2);
        goto L5
    }

    @Override // android.text.style.CharacterStyle
    public void updateDrawState(TextPaint r5) {
        r5.setTextSize(AndroidUtilities.dp(SharedConfig.fontSize - 1));
        WeakReference<Theme.ResourcesProvider> r0 = this.resourcesProvider;
        if (r0 == null) goto L5;
        Theme.ResourcesProvider r02 = r0.get();
    L6:
        byte r2 = this.currentType;
        if (r2 != 2) goto L9;
        r5.setColor(Theme.getColor(Theme.key_chats_message, r02));
    L12:
        TextStyleSpan.TextStyleRun r4 = this.style;
        if (r4 == null) goto L16;
        r4.applyStyle(r5);
        return;
    L16:
        r5.setTypeface(Typeface.MONOSPACE);
        r5.setUnderlineText(false);
        return;
    L9:
        if (r2 != 1) goto L11;
        r5.setColor(Theme.getColor(Theme.key_chat_messageTextOut, r02));
        goto L12
    L11:
        r5.setColor(Theme.getColor(Theme.key_chat_messageTextIn, r02));
        goto L12
    L5:
        r02 = null;
        goto L6
    }

    @Override // android.text.style.MetricAffectingSpan
    public void updateMeasureState(TextPaint r2) {
        r2.setTextSize(AndroidUtilities.dp(SharedConfig.fontSize - 1));
        r2.setFlags(r2.getFlags() | 128);
        TextStyleSpan.TextStyleRun r1 = this.style;
        if (r1 == null) goto L6;
        r1.applyStyle(r2);
        return;
    L6:
        r2.setTypeface(Typeface.MONOSPACE);
    }
}
