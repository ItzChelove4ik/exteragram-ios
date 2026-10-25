package com.googlecode.mp4parser.util;

/* loaded from: classes5.dex */
public abstract class CastUtils {
    public static int l2i(long r3) {
        if (r3 > 2147483647L) goto L9;
        if (r3 < (-2147483648L)) goto L9;
        return (int) r3;
    L9:
        throw new RuntimeException("A cast to int has gone wrong. Please contact the mp4parser discussion group (" + r3 + ")");
    }
}
