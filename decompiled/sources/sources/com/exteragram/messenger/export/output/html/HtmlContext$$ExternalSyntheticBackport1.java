package com.exteragram.messenger.export.output.html;

import kotlin.CharCodeKt$$ExternalSyntheticBUOutline0;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public abstract /* synthetic */ class HtmlContext$$ExternalSyntheticBackport1 {
    public static /* synthetic */ String m(String r4, int r5) {
        if (r5 < 0) goto L22;
        int r1 = r4.length();
        if (r5 == 0) goto L20;
        if (r1 != 0) goto L9;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L9:
        if (r5 != 1) goto L12;
        return r4;
    L12:
        if (r4.length() > (Integer.MAX_VALUE / r5)) goto L18;
        StringBuilder r0 = new StringBuilder(r1 * r5);
        int r12 = 0;
    L14:
        if (r12 >= r5) goto L17;
        r0.append(r4);
        r12 = r12 + 1;
        goto L14
    L17:
        return r0.toString();
    L18:
        HtmlContext$$ExternalSyntheticBUOutline0.m(r4.length(), r5);
        return null;
    L20:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L22:
        CharCodeKt$$ExternalSyntheticBUOutline0.m("count is negative: ", r5);
        return null;
    }
}
