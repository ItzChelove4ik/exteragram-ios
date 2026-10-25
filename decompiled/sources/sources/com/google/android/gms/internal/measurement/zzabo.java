package com.google.android.gms.internal.measurement;

/* loaded from: classes4.dex */
public final class zzabo extends RuntimeException {
    private zzabo(String r1, String r2) {
        super(r1);
    }

    public static zzabo zza(String r1, String r2, int r3, int r4) {
        return new zzabo(zze(r1, r2, r3, r4), r2);
    }

    public static zzabo zzb(String r2, String r3, int r4) {
        return new zzabo(zze(r2, r3, r4, r4 + 1), r3);
    }

    public static zzabo zzc(String r2, String r3, int r4) {
        return new zzabo(zze(r2, r3, r4, -1), r3);
    }

    public static zzabo zzd(String r1, String r2) {
        return new zzabo(r1, r2);
    }

    private static String zze(String r3, String r4, int r5, int r6) {
        if (r6 >= 0) goto L4;
        r6 = r4.length();
    L4:
        StringBuilder r0 = new StringBuilder(r3);
        r0.append(": ");
        if (r5 <= 8) goto L7;
        r0.append("...");
        r0.append(r4, r5 - 5, r5);
    L8:
        r0.append('[');
        r0.append(r4.substring(r5, r6));
        r0.append(']');
        if ((r4.length() - r6) <= 8) goto L11;
        r0.append(r4, r6, r6 + 5);
        r0.append("...");
    L13:
        return r0.toString();
    L11:
        r0.append(r4, r6, r4.length());
        goto L13
    L7:
        r0.append(r4, 0, r5);
        goto L8
    }

    @Override // java.lang.Throwable
    public final synchronized Throwable fillInStackTrace() {
        monitor-enter(this);
        monitor-exit(this);
        return this;
    }
}
