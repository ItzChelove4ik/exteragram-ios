package kotlin;

/* loaded from: classes5.dex */
public abstract /* synthetic */ class UByte$$ExternalSyntheticBackport3 {
    public static /* synthetic */ long m(long r7, long r9) {
        if (r9 >= 0) goto L10;
        if ((r7 ^ Long.MIN_VALUE) >= (r9 ^ Long.MIN_VALUE)) goto L7;
        return 0;
    L7:
        return 1;
    L10:
        if (r7 >= 0) goto L12;
        int r0 = 1;
        long r1 = ((r7 >>> 1) / r9) << 1;
        if (((r7 - (r1 * r9)) ^ Long.MIN_VALUE) >= (r9 ^ Long.MIN_VALUE)) goto L18;
        r0 = 0;
    L18:
        return r1 + r0;
    L12:
        return r7 / r9;
    }
}
