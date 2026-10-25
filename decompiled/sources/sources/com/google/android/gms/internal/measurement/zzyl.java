package com.google.android.gms.internal.measurement;

import java.util.Iterator;

/* loaded from: classes4.dex */
public class zzyl {
    private final String zza;
    private final Class zzb;
    private final boolean zzc;
    private final boolean zzd;
    private final long zze;

    public zzyl(String r2, Class r3, boolean r4) {
        this(r2, r3, r4, true);
    }

    private zzyl(String r4, Class r5, boolean r6, boolean r7) {
        zzabr.zzd(r4);
        this.zza = r4;
        this.zzb = r5;
        this.zzc = r6;
        this.zzd = r7;
        int r42 = System.identityHashCode(this);
        int r52 = 0;
        long r62 = 0;
    L4:
        if (r52 >= 5) goto L6;
        r62 = r62 | (1 << (r42 & 63));
        r42 = r42 >>> 6;
        r52 = r52 + 1;
        goto L4
    L6:
        this.zze = r62;
    }

    public static zzyl zzc(String r2, Class r3) {
        return new zzyl(r2, r3, false, false);
    }

    public final String toString() {
        Class r0 = this.zzb;
        String r1 = getClass().getName();
        String r02 = r0.getName();
        int r2 = r1.length();
        int r3 = r02.length();
        String r6 = this.zza;
        StringBuilder r4 = new StringBuilder(((((r2 + 1) + r6.length()) + 1) + r3) + 1);
        r4.append(r1);
        r4.append("/");
        r4.append(r6);
        r4.append("[");
        r4.append(r02);
        r4.append("]");
        return r4.toString();
    }

    public void zza(Iterator r2, zzyk r3) {
    L3:
        if (r2.hasNext() == false) goto L5;
        zzb(r2.next(), r3);
        goto L3
    }

    public void zzb(Object r1, zzyk r2) {
        r2.zza(this.zza, r1);
    }

    public final String zzd() {
        return this.zza;
    }

    public final Object zze(Object r1) {
        return this.zzb.cast(r1);
    }

    public final boolean zzf() {
        return this.zzc;
    }

    public final void zzg(Object r3, zzyk r4) {
        if (this.zzd == true) goto L5;
    L8:
        zzb(r3, r4);
        return;
    L5:
        if (zzaad.zza() <= 20) goto L8;
        r4.zza(this.zza, r3);
    }

    public final void zzh(Iterator r3, zzyk r4) {
        zzabr.zzc(this.zzc, "non repeating key");
        if (this.zzd == true) goto L5;
    L10:
        zza(r3, r4);
        return;
    L5:
        if (zzaad.zza() <= 20) goto L10;
    L7:
        if (r3.hasNext() == false) goto L9;
        r4.zza(this.zza, r3.next());
        goto L7
    }

    public final long zzi() {
        return this.zze;
    }
}
