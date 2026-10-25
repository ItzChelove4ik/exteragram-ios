package com.google.android.recaptcha.internal;

/* loaded from: classes5.dex */
final class zztl extends zztk {
    private final long[] zzd;

    public zztl(zzto r6) {
        long[] r3 = new long[10];
        super(new long[10], new long[10], new long[10]);
        this.zzd = r3;
        long[] r1 = this.zza;
        zztn r2 = r6.zza;
        zztw.zza(r1, r2.zzb, r2.zza);
        long[] r12 = this.zzb;
        zztn r22 = r6.zza;
        zztw.zzb(r12, r22.zzb, r22.zza);
        System.arraycopy(r6.zza.zzc, 0, r3, 0, 10);
        zztw.zze(this.zzc, r6.zzb, zztr.zzb);
    }

    @Override // com.google.android.recaptcha.internal.zztk
    public final void zza(long[] r1, long[] r2) {
        zztw.zze(r1, r2, this.zzd);
    }
}
