package com.google.android.recaptcha.internal;

/* loaded from: classes5.dex */
final class zzto {
    final zztn zza;
    final long[] zzb;

    public zzto() {
        this(new zztn(), new long[10]);
    }

    public zzto(zztm r1) {
        this();
        zzb(this, r1);
    }

    public zzto(zztn r1, long[] r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    public static /* synthetic */ zzto zza(zzto r0, zztm r1) {
        zzb(r0, r1);
        return r0;
    }

    private static zzto zzb(zzto r5, zztm r6) {
        zztn r0 = r6.zza;
        zztn r1 = r5.zza;
        long[] r2 = r1.zza;
        long[] r3 = r0.zza;
        long[] r62 = r6.zzb;
        zztw.zze(r2, r3, r62);
        long[] r22 = r1.zzb;
        long[] r4 = r0.zzb;
        long[] r02 = r0.zzc;
        zztw.zze(r22, r4, r02);
        zztw.zze(r1.zzc, r02, r62);
        zztw.zze(r5.zzb, r3, r4);
        return r5;
    }
}
