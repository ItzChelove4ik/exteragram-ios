package kotlin;

/* loaded from: classes5.dex */
public abstract /* synthetic */ class UByte$$ExternalSyntheticBackport2 {
    public static /* synthetic */ long m(long r7, long r9) {
        if (r9 >= 0) goto L10;
        if ((r7 ^ Long.MIN_VALUE) >= (r9 ^ Long.MIN_VALUE)) goto L8;
        return r7;
    L8:
        return r7 - r9;
    L10:
        if (r7 >= 0) goto L12;
        long r72 = r7 - ((((r7 >>> 1) / r9) << 1) * r9);
        if ((r72 ^ Long.MIN_VALUE) >= (r9 ^ Long.MIN_VALUE)) goto L18;
        r9 = 0;
    L18:
        return r72 - r9;
    L12:
        return r7 % r9;
    }
}
