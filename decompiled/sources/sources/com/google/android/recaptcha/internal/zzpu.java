package com.google.android.recaptcha.internal;

/* loaded from: classes5.dex */
final class zzpu {
    private final Object zza;
    private final Object zzb;
    private final Object zzc;

    public zzpu(Object r1, Object r2, Object r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    public final IllegalArgumentException zza() {
        Object r0 = this.zzc;
        Object r1 = this.zzb;
        Object r8 = this.zza;
        String r3 = String.valueOf(r8);
        String r12 = String.valueOf(r1);
        String r82 = String.valueOf(r8);
        String r02 = String.valueOf(r0);
        int r4 = r3.length();
        int r5 = r12.length();
        int r6 = r82.length();
        int r42 = ((((r4 + 33) + r5) + 5) + r6) + 1;
        StringBuilder r52 = new StringBuilder(r42 + r02.length());
        r52.append("Multiple entries with same key: ");
        r52.append(r3);
        r52.append("=");
        r52.append(r12);
        r52.append(" and ");
        r52.append(r82);
        r52.append("=");
        r52.append(r02);
        return new IllegalArgumentException(r52.toString());
    }
}
