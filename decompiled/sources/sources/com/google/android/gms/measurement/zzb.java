package com.google.android.gms.measurement;

import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.measurement.internal.zzlk;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
final class zzb extends zzc {
    private final zzlk zza;

    public zzb(zzlk r2) {
        super(null);
        Preconditions.checkNotNull(r2);
        this.zza = r2;
    }

    @Override // com.google.android.gms.measurement.internal.zzlk
    public final void zza(String r1, String r2, Bundle r3) {
        this.zza.zza(r1, r2, r3);
    }

    @Override // com.google.android.gms.measurement.internal.zzlk
    public final Map zzd(String r1, String r2, boolean r3) {
        return this.zza.zzd(r1, r2, r3);
    }

    @Override // com.google.android.gms.measurement.internal.zzlk
    public final String zzh() {
        return this.zza.zzh();
    }

    @Override // com.google.android.gms.measurement.internal.zzlk
    public final String zzi() {
        return this.zza.zzi();
    }

    @Override // com.google.android.gms.measurement.internal.zzlk
    public final String zzj() {
        return this.zza.zzj();
    }

    @Override // com.google.android.gms.measurement.internal.zzlk
    public final String zzk() {
        return this.zza.zzk();
    }

    @Override // com.google.android.gms.measurement.internal.zzlk
    public final long zzl() {
        return this.zza.zzl();
    }

    @Override // com.google.android.gms.measurement.internal.zzlk
    public final void zzm(String r1) {
        this.zza.zzm(r1);
    }

    @Override // com.google.android.gms.measurement.internal.zzlk
    public final void zzn(String r1) {
        this.zza.zzn(r1);
    }

    @Override // com.google.android.gms.measurement.internal.zzlk
    public final void zzo(Bundle r1) {
        this.zza.zzo(r1);
    }

    @Override // com.google.android.gms.measurement.internal.zzlk
    public final void zzp(String r1, String r2, Bundle r3) {
        this.zza.zzp(r1, r2, r3);
    }

    @Override // com.google.android.gms.measurement.internal.zzlk
    public final List zzq(String r1, String r2) {
        return this.zza.zzq(r1, r2);
    }

    @Override // com.google.android.gms.measurement.internal.zzlk
    public final int zzr(String r1) {
        return this.zza.zzr(r1);
    }
}
