package com.google.android.gms.internal.clearcut;

import com.google.android.gms.internal.clearcut.zzcg;

/* loaded from: classes4.dex */
final class zzec implements zzdm {
    private final String info;
    private final zzdo zzmn;
    private final zzed zzng;

    public zzec(zzdo r2, String r3, Object[] r4) {
        this.zzmn = r2;
        this.info = r3;
        this.zzng = new zzed(r2.getClass(), r3, r4);
    }

    public final int getFieldCount() {
        return zzed.zzd(this.zzng);
    }

    @Override // com.google.android.gms.internal.clearcut.zzdm
    public final int zzcf() {
        if ((zzed.zza(this.zzng) & 1) != 1) goto L7;
        return zzcg.zzg.zzkl;
    L7:
        return zzcg.zzg.zzkm;
    }

    @Override // com.google.android.gms.internal.clearcut.zzdm
    public final boolean zzcg() {
        if ((zzed.zza(this.zzng) & 2) != 2) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // com.google.android.gms.internal.clearcut.zzdm
    public final zzdo zzch() {
        return this.zzmn;
    }

    public final zzed zzco() {
        return this.zzng;
    }

    public final int zzcp() {
        return zzed.zzb(this.zzng);
    }

    public final int zzcq() {
        return zzed.zzc(this.zzng);
    }

    public final int zzcr() {
        return zzed.zze(this.zzng);
    }

    public final int zzcs() {
        return zzed.zzf(this.zzng);
    }

    public final int[] zzct() {
        return zzed.zzg(this.zzng);
    }

    public final int zzcu() {
        return zzed.zzh(this.zzng);
    }

    public final int zzcv() {
        return zzed.zzi(this.zzng);
    }
}
