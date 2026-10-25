package org.telegram.ui.Components;

import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLog;

/* loaded from: classes7.dex */
public abstract class StaticLayoutEx {
    public static Layout.Alignment[] alignments;

    static {
        alignments = Layout.Alignment.values();
    }

    public static Layout.Alignment ALIGN_LEFT() {
        Layout.Alignment[] r0 = alignments;
        if (r0.length < 5) goto L7;
        return r0[3];
    L7:
        return Layout.Alignment.ALIGN_NORMAL;
    }

    public static Layout.Alignment ALIGN_RIGHT() {
        Layout.Alignment[] r0 = alignments;
        if (r0.length < 5) goto L7;
        return r0[4];
    L7:
        return Layout.Alignment.ALIGN_OPPOSITE;
    }

    public static StaticLayout createStaticLayout(CharSequence r11, TextPaint r12, int r13, Layout.Alignment r14, float r15, float r16, boolean r17, TextUtils.TruncateAt r18, int r19, int r20) {
        return createStaticLayout(r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, true);
    }

    public static StaticLayout createStaticLayout(CharSequence r17, TextPaint r18, int r19, Layout.Alignment r20, float r21, float r22, boolean r23, TextUtils.TruncateAt r24, int r25, int r26, boolean r27) {
        CharSequence r0 = r17;
        if (r26 != 1) goto L11;
        int r2 = TextUtils.indexOf(r0, "\n") - 1;     // Catch: Exception -> L7
        if (r2 <= 0) goto L9;
        r0 = SpannableStringBuilder.valueOf(r0.subSequence(0, r2)).append("…");     // Catch: Exception -> L7
    L9:
        CharSequence r1 = TextUtils.ellipsize(r0, r18, r25, TextUtils.TruncateAt.END);     // Catch: Exception -> L7
        return new StaticLayout(r1, 0, r1.length(), r18, r19, r20, r21, r22, r23);
    L11:
        StaticLayout r6 = StaticLayout.Builder.obtain(r0, 0, r0.length(), r18, r19).setAlignment(r20).setLineSpacing(r22, r21).setIncludePad(r23).setEllipsize(null).setEllipsizedWidth(r25).setMaxLines(r26).setBreakStrategy(1).setHyphenationFrequency(0).build();     // Catch: Exception -> L7
        int r13 = 0;
    L13:
        if (r13 >= r6.getLineCount()) goto L19;
        if (r6.getLineRight(r13) > r19) goto L16;
        r13 = r13 + 1;     // Catch: Exception -> L7
        goto L13
    L16:
        r6 = StaticLayout.Builder.obtain(r0, 0, r0.length(), r18, r19).setAlignment(r20).setLineSpacing(r22, r21).setIncludePad(r23).setEllipsize(null).setEllipsizedWidth(r25).setMaxLines(r26).setBreakStrategy(0).setHyphenationFrequency(0).build();     // Catch: Exception -> L7
    L19:
        if (r6.getLineCount() > r26) goto L21;
        return r6;
    L21:
        int r132 = r26 - 1;
        float r14 = r6.getLineLeft(r132);     // Catch: Exception -> L7
        float r15 = r6.getLineWidth(r132);     // Catch: Exception -> L7
        if (r14 == 0.0f) goto L24;
        int r62 = r6.getOffsetForHorizontal(r132, r14);     // Catch: Exception -> L7
    L26:
        if (r15 >= (r25 - AndroidUtilities.dp(10.0f))) goto L28;
        r62 = r62 + 3;     // Catch: Exception -> L7
    L28:
        SpannableStringBuilder r133 = new SpannableStringBuilder(r0.subSequence(0, Math.max(0, r62 - 3)));     // Catch: Exception -> L7
        r133.append("…");     // Catch: Exception -> L7
        StaticLayout.Builder r02 = StaticLayout.Builder.obtain(r133, 0, r133.length(), r18, r19).setAlignment(r20).setLineSpacing(r22, r21).setIncludePad(r23);     // Catch: Exception -> L7
        if (((AnimatedEmojiSpan[]) r133.getSpans(0, r133.length(), AnimatedEmojiSpan.class)).length <= 0) goto L31;
        TextUtils.TruncateAt r3 = null;
    L33:
        return r02.setEllipsize(r3).setEllipsizedWidth(r25).setMaxLines(r26).setBreakStrategy(r27 ? 1 : 0).setHyphenationFrequency(0).build();
    L31:
        r3 = r24;
        goto L33
    L24:
        r62 = r6.getOffsetForHorizontal(r132, r15);     // Catch: Exception -> L7
    L7:
        e = move-exception;
        FileLog.e(e);
        return null;
    }

    public static StaticLayout createStaticLayout2(CharSequence r0, TextPaint r1, int r2, Layout.Alignment r3, float r4, float r5, boolean r6, TextUtils.TruncateAt r7, int r8, int r9) {
        return StaticLayout.Builder.obtain(r0, 0, r0.length(), r1, r8).setAlignment(r3).setLineSpacing(r5, r4).setIncludePad(r6).setEllipsize(TextUtils.TruncateAt.END).setEllipsizedWidth(r8).setMaxLines(r9).setBreakStrategy(1).setHyphenationFrequency(0).build();
    }
}
