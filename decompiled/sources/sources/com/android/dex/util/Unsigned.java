package com.android.dex.util;

import kotlin.UShort;

/* loaded from: classes4.dex */
public abstract class Unsigned {
    public static int compare(int r4, int r5) {
        if (r4 != r5) goto L6;
        return 0;
    L6:
        if ((r4 & 4294967295L) >= (r5 & 4294967295L)) goto L9;
        return -1;
    L9:
        return 1;
    }

    public static int compare(short r1, short r2) {
        if (r1 != r2) goto L6;
        return 0;
    L6:
        if ((r1 & UShort.MAX_VALUE) >= (r2 & UShort.MAX_VALUE)) goto L9;
        return -1;
    L9:
        return 1;
    }
}
