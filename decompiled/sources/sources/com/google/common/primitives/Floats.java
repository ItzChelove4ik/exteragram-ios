package com.google.common.primitives;

import com.google.common.base.Preconditions;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public abstract class Floats extends FloatsMethodsForWeb {
    public static int hashCode(float r0) {
        return Float.hashCode(r0);
    }

    public static String join(String r3, float... r4) {
        Preconditions.checkNotNull(r3);
        if (r4.length != 0) goto L6;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        StringBuilder r0 = new StringBuilder(r4.length * 12);
        r0.append(r4[0]);
        int r1 = 1;
    L8:
        if (r1 >= r4.length) goto L11;
        r0.append(r3);
        r0.append(r4[r1]);
        r1 = r1 + 1;
        goto L8
    L11:
        return r0.toString();
    }
}
