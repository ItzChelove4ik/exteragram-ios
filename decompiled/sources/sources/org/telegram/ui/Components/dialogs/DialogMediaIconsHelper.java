package org.telegram.ui.Components.dialogs;

import android.text.SpannableStringBuilder;
import android.util.SparseArray;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.ColoredImageSpan;

/* loaded from: classes7.dex */
public abstract class DialogMediaIconsHelper {
    private static final SparseArray<ColoredImageSpan> spans = null;

    static {
        spans = new SparseArray(6);
    }

    public static CharSequence addDialogMediaSpan(CharSequence r4, int r5, boolean r6) {
        if ((r4 instanceof SpannableStringBuilder) == false) goto L5;
        SpannableStringBuilder r42 = (SpannableStringBuilder) r4;
    L7:
        if (r6 == false) goto L9;
        r42.insert(0, "* \u2068");
    L10:
        SparseArray<ColoredImageSpan> r1 = spans;
        ColoredImageSpan r2 = r1.get(r5);
        if (r2 != null) goto L13;
        r2 = new ColoredImageSpan(r5);
        r2.setColorKey(Theme.key_telegram_color_text);
        r1.put(r5, r2);
    L13:
        r42.setSpan(r2, 0, 1, 33);
        if (r6 == false) goto L16;
        r42.append(8297);
    L16:
        return r42;
    L9:
        r42.insert(0, "* ");
        goto L10
    L5:
        r42 = new SpannableStringBuilder(r4);
        goto L7
    }
}
