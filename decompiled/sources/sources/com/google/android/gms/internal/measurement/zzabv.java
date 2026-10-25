package com.google.android.gms.internal.measurement;

/* loaded from: classes4.dex */
final class zzabv implements zzabu {
    public zzabv() {
    }

    private static final int zzc(StackTraceElement[] r2, Class r3, int r4) {
        String r32 = r3.getName();
        int r42 = 3;
        boolean r0 = false;
    L4:
        if (r42 >= r2.length) goto L11;
        if (r2[r42].getClassName().equals(r32) == false) goto L8;
        r0 = true;
    L10:
        r42 = r42 + 1;
        goto L4
    L8:
        if (r0 == false) goto L10;
        return r42;
    L11:
        return -1;
    }

    @Override // com.google.android.gms.internal.measurement.zzabu
    public final StackTraceElement[] zzb(Class r3, int r4, int r5) {
        boolean r2 = true;
        if (r4 == (-1)) goto L7;
        if (r4 > 0) goto L7;
        r2 = false;
    L7:
        zzabr.zzb(r2, "maxDepth must be > 0 or -1");
        StackTraceElement[] r22 = new Throwable().getStackTrace();
        int r32 = zzc(r22, r3, 3);
        if (r32 == (-1)) goto L10;
        int r0 = r22.length - r32;
        if (r4 <= 0) goto L14;
        if (r4 >= r0) goto L14;
    L15:
        StackTraceElement[] r02 = new StackTraceElement[r4];
        System.arraycopy(r22, r32, r02, 0, r4);
        return r02;
    L14:
        r4 = r0;
        goto L15
    L10:
        return new StackTraceElement[0];
    }
}
