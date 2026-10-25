package androidx.media3.common.text;

import android.text.Spannable;
import android.text.style.RelativeSizeSpan;

/* loaded from: classes3.dex */
public abstract class SpanUtil {
    public static void addInheritedRelativeSizeSpan(Spannable r5, float r6, int r7, int r8, int r9) {
        RelativeSizeSpan[] r0 = (RelativeSizeSpan[]) r5.getSpans(r7, r8, RelativeSizeSpan.class);
        int r1 = r0.length;
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L10;
        RelativeSizeSpan r3 = r0[r2];
        if (r5.getSpanStart(r3) > r7) goto L9;
        if (r5.getSpanEnd(r3) < r8) goto L9;
        r6 = r6 * r3.getSizeChange();
    L9:
        removeIfStartEndAndFlagsMatch(r5, r3, r7, r8, r9);
        r2 = r2 + 1;
        goto L3
    L10:
        r5.setSpan(new RelativeSizeSpan(r6), r7, r8, r9);
    }

    public static void addOrReplaceSpan(Spannable r4, Object r5, int r6, int r7, int r8) {
        Object[] r0 = r4.getSpans(r6, r7, r5.getClass());
        int r1 = r0.length;
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L5;
        removeIfStartEndAndFlagsMatch(r4, r0[r2], r6, r7, r8);
        r2 = r2 + 1;
        goto L3
    L5:
        r4.setSpan(r5, r6, r7, r8);
    }

    private static void removeIfStartEndAndFlagsMatch(Spannable r1, Object r2, int r3, int r4, int r5) {
        if (r1.getSpanStart(r2) == r3) goto L5;
        return;
    L5:
        if (r1.getSpanEnd(r2) == r4) goto L7;
        return;
    L7:
        if (r1.getSpanFlags(r2) != r5) goto L12;
        r1.removeSpan(r2);
        return;
    }
}
