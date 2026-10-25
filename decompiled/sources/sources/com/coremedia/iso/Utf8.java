package com.coremedia.iso;

/* loaded from: classes4.dex */
public abstract class Utf8 {
    public static String convert(byte[] r2) {
        if (r2 != null) goto L10;
        return null;
    L10:
        return new String(r2, "UTF-8");
    L5:
        e = move-exception;
        throw new Error(e);
    }

    public static byte[] convert(String r1) {
        if (r1 != null) goto L10;
        return null;
    L10:
        return r1.getBytes("UTF-8");
    L5:
        e = move-exception;
        throw new Error(e);
    }

    public static int utf8StringLengthInBytes(String r1) {
        if (r1 != null) goto L9;
        return 0;
    L9:
        return r1.getBytes("UTF-8").length;
    L6:
        throw new RuntimeException();
    }
}
