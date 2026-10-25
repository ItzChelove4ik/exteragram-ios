package org.telegram.ui.Components;

import android.text.InputFilter;
import android.text.Spanned;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes7.dex */
public abstract class CodepointsLengthInputFilter implements InputFilter {
    private final int mMax;

    public CodepointsLengthInputFilter(int r1) {
        this.mMax = r1;
    }

    @Override // android.text.InputFilter
    public CharSequence filter(CharSequence r3, int r4, int r5, Spanned r6, int r7, int r8) {
        int r2 = this.mMax - (Character.codePointCount(r6, 0, r6.length()) - Character.codePointCount(r6, r7, r8));
        if (r2 > 0) goto L6;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        if (r2 < Character.codePointCount(r3, r4, r5)) goto L9;
        return null;
    L9:
        int r22 = r2 + r4;
        if (Character.isHighSurrogate(r3.charAt(r22 - 1)) == false) goto L15;
        r22 = r22 - 1;
        if (r22 != r4) goto L15;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L15:
        return r3.subSequence(r4, r22);
    }
}
