package com.google.android.gms.internal.measurement;

import java.util.Collections;
import java.util.List;

/* loaded from: classes4.dex */
public final class zzgk extends zzadp implements zzafd {
    public /* synthetic */ zzgk(byte[] r1) {
        super(zzgl.zzx());
    }

    public final int zza() {
        return ((zzgl) this.zza).zzf();
    }

    public final zzgj zzb(int r1) {
        return ((zzgl) this.zza).zzh(r1);
    }

    public final zzgk zzc(int r2, zzgi r3) {
        zzaY();
        ((zzgl) this.zza).zzu(r2, (zzgj) r3.zzbd());
        return this;
    }

    public final List zzd() {
        return Collections.unmodifiableList(((zzgl) this.zza).zzi());
    }

    public final zzgk zze() {
        zzaY();
        ((zzgl) this.zza).zzv();
        return this;
    }

    public final zzgk zzf() {
        zzaY();
        ((zzgl) this.zza).zzw();
        return this;
    }

    public final List zzg() {
        return Collections.unmodifiableList(((zzgl) this.zza).zzl());
    }

    public final String zzh() {
        return ((zzgl) this.zza).zzm();
    }

    public final List zzi() {
        return Collections.unmodifiableList(((zzgl) this.zza).zzr());
    }
}
