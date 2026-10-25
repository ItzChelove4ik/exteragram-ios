package com.google.android.gms.internal.measurement;

import java.util.Collections;
import java.util.List;

/* loaded from: classes4.dex */
public final class zzhz extends zzadp implements zzafd {
    public /* synthetic */ zzhz(byte[] r1) {
        super(zzib.zzq());
    }

    public final List zza() {
        return Collections.unmodifiableList(((zzib) this.zza).zza());
    }

    public final int zzb() {
        return ((zzib) this.zza).zzb();
    }

    public final zzid zzc(int r1) {
        return ((zzib) this.zza).zzc(r1);
    }

    public final zzhz zzd(int r2, zzic r3) {
        zzaY();
        ((zzib) this.zza).zzk(r2, (zzid) r3.zzbd());
        return this;
    }

    public final zzhz zze(zzic r2) {
        zzaY();
        ((zzib) this.zza).zzl((zzid) r2.zzbd());
        return this;
    }

    public final zzhz zzf(Iterable r2) {
        zzaY();
        ((zzib) this.zza).zzm(r2);
        return this;
    }

    public final zzhz zzg() {
        zzaY();
        ((zzib) this.zza).zzn();
        return this;
    }

    public final String zzh() {
        return ((zzib) this.zza).zze();
    }

    public final zzhz zzi(String r2) {
        zzaY();
        ((zzib) this.zza).zzo(r2);
        return this;
    }

    public final zzhz zzj(String r2) {
        zzaY();
        ((zzib) this.zza).zzp(r2);
        return this;
    }
}
