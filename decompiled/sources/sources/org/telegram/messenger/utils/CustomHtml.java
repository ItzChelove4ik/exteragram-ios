package org.telegram.messenger.utils;

import android.text.Spanned;
import android.text.TextUtils;
import org.telegram.messenger.CodeHighlighting;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.QuoteSpan;
import org.telegram.ui.Components.TextStyleSpan;
import org.telegram.ui.Components.URLSpanMono;
import org.telegram.ui.Components.URLSpanReplacement;

/* loaded from: classes6.dex */
public abstract class CustomHtml {
    private static void textStyleSpanBegin(TextStyleSpan.TextStyleRun r1, StringBuilder r2) {
        if (r1 != null) goto L5;
        return;
    L5:
        if ((r1.flags & 768) <= 0) goto L8;
        r2.append("<spoiler>");
    L8:
        if ((r1.flags & 1) <= 0) goto L11;
        r2.append("<b>");
    L11:
        if ((r1.flags & 2) <= 0) goto L14;
        r2.append("<i>");
    L14:
        if ((r1.flags & 16) <= 0) goto L17;
        r2.append("<u>");
    L17:
        if ((r1.flags & 8) <= 0) goto L20;
        r2.append("<s>");
    L20:
        if ((r1.flags & 4) <= 0) goto L23;
        r2.append("<code>");
    L23:
        if ((r1.flags & 128) > 0) goto L25;
        return;
    L25:
        if (r1.urlEntity == null) goto L29;
        r2.append("<a href=\"");
        r2.append(r1.urlEntity.url);
        r2.append("\">");
        return;
    }

    private static void textStyleSpanEnd(TextStyleSpan.TextStyleRun r1, StringBuilder r2) {
        if (r1 != null) goto L5;
        return;
    L5:
        if ((r1.flags & 128) <= 0) goto L10;
        if (r1.urlEntity == null) goto L10;
        r2.append("</a>");
    L10:
        if ((r1.flags & 4) <= 0) goto L13;
        r2.append("</code>");
    L13:
        if ((r1.flags & 8) <= 0) goto L16;
        r2.append("</s>");
    L16:
        if ((r1.flags & 16) <= 0) goto L19;
        r2.append("</u>");
    L19:
        if ((r1.flags & 2) <= 0) goto L22;
        r2.append("</i>");
    L22:
        if ((r1.flags & 1) <= 0) goto L25;
        r2.append("</b>");
    L25:
        if ((r1.flags & 768) <= 0) goto L28;
        r2.append("</spoiler>");
        return;
    }

    private static void toHTML_0_wrapQuote(StringBuilder r4, Spanned r5, int r6, int r7) {
    L2:
        if (r6 >= r7) goto L22;
        int r1 = r5.nextSpanTransition(r6, r7, QuoteSpan.class);
        if (r1 >= 0) goto L6;
        r1 = r7;
    L6:
        QuoteSpan[] r0 = (QuoteSpan[]) r5.getSpans(r6, r1, QuoteSpan.class);
        if (r0 == null) goto L16;
        int r2 = 0;
    L10:
        if (r2 >= r0.length) goto L16;
        if (r0[r2].isCollapsing == false) goto L14;
        String r3 = "<blockquote collapsed>";
    L15:
        r4.append(r3);
        r2 = r2 + 1;
        goto L10
    L14:
        r3 = "<blockquote>";
    L16:
        toHTML_1_wrapTextStyle(r4, r5, r6, r1);
        if (r0 == null) goto L21;
        int r62 = r0.length - 1;
    L19:
        if (r62 < 0) goto L21;
        r4.append("</blockquote>");
        r62 = r62 - 1;
    L21:
        r6 = r1;
        goto L2
    }

    private static void toHTML_1_wrapTextStyle(StringBuilder r5, Spanned r6, int r7, int r8) {
    L2:
        if (r7 >= r8) goto L24;
        int r1 = r6.nextSpanTransition(r7, r8, TextStyleSpan.class);
        if (r1 >= 0) goto L6;
        r1 = r8;
    L6:
        TextStyleSpan[] r0 = (TextStyleSpan[]) r6.getSpans(r7, r1, TextStyleSpan.class);
        int r2 = 0;
        if (r0 == null) goto L15;
        int r3 = 0;
    L10:
        if (r3 >= r0.length) goto L15;
        TextStyleSpan r4 = r0[r3];
        if (r4 == null) goto L14;
        textStyleSpanBegin(r4.getTextStyleRun(), r5);
    L14:
        r3 = r3 + 1;
    L15:
        toHTML_2_wrapURLReplacements(r5, r6, r7, r1);
        if (r0 == null) goto L23;
    L18:
        if (r2 >= r0.length) goto L23;
        TextStyleSpan r72 = r0[r2];
        if (r72 == null) goto L22;
        textStyleSpanEnd(r72.getTextStyleRun(), r5);
    L22:
        r2 = r2 + 1;
    L23:
        r7 = r1;
        goto L2
    }

    private static void toHTML_2_wrapURLReplacements(StringBuilder r6, Spanned r7, int r8, int r9) {
    L2:
        if (r8 >= r9) goto L18;
        int r1 = r7.nextSpanTransition(r8, r9, URLSpanReplacement.class);
        if (r1 >= 0) goto L6;
        r1 = r9;
    L6:
        URLSpanReplacement[] r0 = (URLSpanReplacement[]) r7.getSpans(r8, r1, URLSpanReplacement.class);
        int r2 = 0;
        if (r0 == null) goto L12;
        int r3 = 0;
    L10:
        if (r3 >= r0.length) goto L12;
        URLSpanReplacement r4 = r0[r3];
        textStyleSpanBegin(r4.getTextStyleRun(), r6);
        r6.append("<a href=\"");
        r6.append(r4.getURL());
        r6.append("\">");
        r3 = r3 + 1;
    L12:
        toHTML_3_wrapMonoscape(r6, r7, r8, r1);
        if (r0 == null) goto L17;
    L15:
        if (r2 >= r0.length) goto L17;
        URLSpanReplacement r82 = r0[r2];
        r6.append("</a>");
        textStyleSpanEnd(r82.getTextStyleRun(), r6);
        r2 = r2 + 1;
    L17:
        r8 = r1;
        goto L2
    }

    private static void toHTML_3_wrapMonoscape(StringBuilder r5, Spanned r6, int r7, int r8) {
    L2:
        if (r7 >= r8) goto L24;
        int r1 = r6.nextSpanTransition(r7, r8, URLSpanMono.class);
        if (r1 >= 0) goto L6;
        r1 = r8;
    L6:
        URLSpanMono[] r0 = (URLSpanMono[]) r6.getSpans(r7, r1, URLSpanMono.class);
        int r2 = 0;
        if (r0 == null) goto L15;
        int r3 = 0;
    L10:
        if (r3 >= r0.length) goto L15;
        if (r0[r3] == null) goto L14;
        r5.append("<code>");
    L14:
        r3 = r3 + 1;
    L15:
        toHTML_4_wrapMonoscape2(r5, r6, r7, r1);
        if (r0 == null) goto L23;
    L18:
        if (r2 >= r0.length) goto L23;
        if (r0[r2] == null) goto L22;
        r5.append("</code>");
    L22:
        r2 = r2 + 1;
    L23:
        r7 = r1;
        goto L2
    }

    private static void toHTML_4_wrapMonoscape2(StringBuilder r6, Spanned r7, int r8, int r9) {
    L2:
        if (r8 >= r9) goto L27;
        int r1 = r7.nextSpanTransition(r8, r9, CodeHighlighting.Span.class);
        if (r1 >= 0) goto L6;
        r1 = r9;
    L6:
        CodeHighlighting.Span[] r0 = (CodeHighlighting.Span[]) r7.getSpans(r8, r1, CodeHighlighting.Span.class);
        int r2 = 0;
        if (r0 == null) goto L18;
        int r3 = 0;
    L10:
        if (r3 >= r0.length) goto L18;
        CodeHighlighting.Span r4 = r0[r3];
        if (r4 == null) goto L17;
        if (TextUtils.isEmpty(r4.lng) == false) goto L16;
        r6.append("<pre>");
        goto L17
    L16:
        r6.append("<pre lang=\"");
        r6.append(r4.lng);
        r6.append("\">");
    L17:
        r3 = r3 + 1;
    L18:
        toHTML_6_wrapAnimatedEmoji(r6, r7, r8, r1);
        if (r0 == null) goto L26;
    L21:
        if (r2 >= r0.length) goto L26;
        if (r0[r2] == null) goto L25;
        r6.append("</pre>");
    L25:
        r2 = r2 + 1;
    L26:
        r8 = r1;
        goto L2
    }

    private static void toHTML_6_wrapAnimatedEmoji(StringBuilder r8, Spanned r9, int r10, int r11) {
    L2:
        if (r10 >= r11) goto L28;
        int r1 = r9.nextSpanTransition(r10, r11, AnimatedEmojiSpan.class);
        if (r1 >= 0) goto L6;
        r1 = r11;
    L6:
        AnimatedEmojiSpan[] r0 = (AnimatedEmojiSpan[]) r9.getSpans(r10, r1, AnimatedEmojiSpan.class);
        int r2 = 0;
        if (r0 == null) goto L17;
        int r3 = 0;
    L10:
        if (r3 >= r0.length) goto L17;
        AnimatedEmojiSpan r4 = r0[r3];
        if (r4 == null) goto L16;
        if (r4.standard == true) goto L16;
        r8.append("<animated-emoji data-document-id=\"" + r4.documentId + "\">");
    L16:
        r3 = r3 + 1;
    L17:
        toHTML_7_withinStyle(r8, r9, r10, r1);
        if (r0 == null) goto L27;
    L20:
        if (r2 >= r0.length) goto L27;
        AnimatedEmojiSpan r102 = r0[r2];
        if (r102 == null) goto L26;
        if (r102.standard == true) goto L26;
        r8.append("</animated-emoji>");
    L26:
        r2 = r2 + 1;
    L27:
        r10 = r1;
        goto L2
    }

    private static void toHTML_7_withinStyle(StringBuilder r9, CharSequence r10, int r11, int r12) {
    L2:
        if (r11 >= r12) goto L42;
        char r0 = r10.charAt(r11);
        if (r0 != '\n') goto L7;
        r9.append("<br>");
    L41:
        r11 = r11 + 1;
        goto L2
    L7:
        if (r0 != '<') goto L10;
        r9.append("&lt;");
        goto L41
    L10:
        if (r0 != '>') goto L13;
        r9.append("&gt;");
        goto L41
    L13:
        if (r0 != '&') goto L16;
        r9.append("&amp;");
        goto L41
    L16:
        if (r0 < 55296) goto L28;
        if (r0 > 57343) goto L28;
        if (r0 >= 56320) goto L41;
        int r7 = r11 + 1;
        if (r7 >= r12) goto L41;
        char r8 = r10.charAt(r7);
        if (r8 < 56320) goto L41;
        if (r8 > 57343) goto L41;
        r9.append("&#");
        r9.append((((r0 - 55296) << 10) | 65536) | (r8 - 56320));
        r9.append(";");
        r11 = r7;
    L28:
        if (r0 <= '~') goto L30;
    L40:
        r9.append("&#");
        r9.append(r0);
        r9.append(";");
        goto L41
    L30:
        if (r0 < ' ') goto L40;
        if (r0 != ' ') goto L39;
    L33:
        int r02 = r11 + 1;
        if (r02 >= r12) goto L38;
        if (r10.charAt(r02) != ' ') goto L38;
        r9.append("&nbsp;");
        r11 = r02;
    L38:
        r9.append(' ');
        goto L41
    L39:
        r9.append(r0);
        goto L41
    }

    public static String toHtml(Spanned r3) {
        StringBuilder r0 = new StringBuilder();
        toHTML_0_wrapQuote(r0, r3, 0, r3.length());
        return r0.toString();
    }
}
