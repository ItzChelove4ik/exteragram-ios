package com.google.android.gms.internal.measurement;

/* loaded from: classes4.dex */
public abstract class zzabm {
    private final zzaaf zza;
    private int zzb;
    private int zzc;

    public zzabm(zzaaf r2) {
        this.zzb = 0;
        this.zzc = -1;
        zzabr.zza(r2, "context");
        this.zza = r2;
    }

    public abstract void zzb(int r1, int r2, zzabh r3);

    public abstract Object zzg();

    public final zzabn zzh() {
        return this.zza.zza();
    }

    public final String zzi() {
        return this.zza.zzb();
    }

    public final int zzj() {
        return this.zzc + 1;
    }

    public final void zzk(int r4, int r5, zzabh r6) {
        if (r6.zzc() >= 32) goto L5;
        this.zzb |= 1 << r6.zzc();
    L5:
        this.zzc = Math.max(this.zzc, r6.zzc());
        zzb(r4, r5, r6);
    }

    public final Object zzl() {
        zzaaf r0 = this.zza;
        r0.zza().zzc(this);
        int r1 = this.zzb;
        if (((r1 + 1) & r1) != 0) goto L11;
        if (this.zzc <= 31) goto L9;
        if (r1 != (-1)) goto L11;
    L9:
        return zzg();
    L11:
        throw zzabo.zzd(String.format("unreferenced arguments [first missing index=%d]", new Object[]{Integer.valueOf(Integer.numberOfTrailingZeros(~r1))}), r0.zzb());
    }
}
