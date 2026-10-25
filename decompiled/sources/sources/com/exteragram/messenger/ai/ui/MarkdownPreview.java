package com.exteragram.messenger.ai.ui;

import android.text.Spannable;
import android.text.TextUtils;
import android.text.style.TypefaceSpan;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.CodeHighlighting;
import org.telegram.messenger.FileLog;
import org.telegram.ui.Components.MarkdownParser;
import org.telegram.ui.Components.QuoteSpan;
import org.telegram.ui.iv.RichMessageConvert;

/* loaded from: classes4.dex */
public abstract class MarkdownPreview {
    public static CharSequence format(String r9) {
        if (TextUtils.isEmpty(r9) == false) goto L6;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        ArrayList r0 = new ArrayList();
        MarkdownParser.parse(r9, r0);     // Catch: Throwable -> L9
    L12:
        if (r0.isEmpty() == true) goto L16;
        CharSequence r02 = RichMessageConvert.blocksToCharSequence(r0);
        if (TextUtils.isEmpty(r02) == true) goto L16;
        if ((r02 instanceof Spannable) == false) goto L22;
        Spannable r92 = (Spannable) r02;
        CodeHighlighting.Span[] r2 = (CodeHighlighting.Span[]) r92.getSpans(0, r92.length(), CodeHighlighting.Span.class);
        int r3 = r2.length;
        int r4 = 0;
    L20:
        if (r4 >= r3) goto L22;
        CodeHighlighting.Span r5 = r2[r4];
        int r6 = r92.getSpanStart(r5);
        int r7 = r92.getSpanEnd(r5);
        r92.removeSpan(r5);
        r92.setSpan(new TypefaceSpan("monospace"), r6, r7, 33);
        r4 = r4 + 1;
    L22:
        AndroidUtilities.removeSpans(r02, QuoteSpan.QuoteStyleSpan.class);
        AndroidUtilities.removeSpans(r02, QuoteSpan.class);
        int r93 = r02.length();
    L23:
        if (r93 <= 0) goto L28;
        if (Character.isWhitespace(r02.charAt(r93 - 1)) == false) goto L28;
        r93 = r93 - 1;
    L28:
        if (r93 != r02.length()) goto L31;
        return r02;
    L31:
        return r02.subSequence(0, r93);
    L16:
        return r9;
    L9:
        th = move-exception;
        FileLog.e(th);
        r0.clear();
        goto L12
    }
}
