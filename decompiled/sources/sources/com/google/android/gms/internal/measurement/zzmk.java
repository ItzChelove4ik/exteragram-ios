package com.google.android.gms.internal.measurement;

/* loaded from: classes4.dex */
public final class zzmk extends RuntimeException {
    private final int zza;

    public zzmk(int r4, String r5, Throwable r6) {
        if (r5 == null) goto L4;
        StringBuilder r2 = new StringBuilder((String.valueOf(r4).length() + 2) + r5.length());
        r2.append(r4);
        r2.append(": ");
        r2.append(r5);
        String r52 = r2.toString();
    L5:
        super(r52, r6);
        this.zza = r4;
        return;
    L4:
        r52 = String.valueOf(r4);
        goto L5
    }

    public final int zza() {
        return this.zza;
    }
}
