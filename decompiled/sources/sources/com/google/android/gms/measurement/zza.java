package com.google.android.gms.measurement;

import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.measurement.internal.zzic;
import com.google.android.gms.measurement.internal.zzlj;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
final class zza extends zzc {
    private final zzic zza;
    private final zzlj zzb;

    public zza(zzic r2) {
        super(null);
        Preconditions.checkNotNull(r2);
        this.zza = r2;
        this.zzb = r2.zzj();
    }

    @Override // com.google.android.gms.measurement.internal.zzlk
    public final void zza(String r1, String r2, Bundle r3) {
        this.zzb.zzA(r1, r2, r3);
    }

    @Override // com.google.android.gms.measurement.internal.zzlk
    public final Map zzd(String r1, String r2, boolean r3) {
        return this.zzb.zzO(r1, r2, r3);
    }

    @Override // com.google.android.gms.measurement.internal.zzlk
    public final String zzh() {
        return this.zzb.zzac();
    }

    @Override // com.google.android.gms.measurement.internal.zzlk
    public final String zzi() {
        return this.zzb.zzad();
    }

    @Override // com.google.android.gms.measurement.internal.zzlk
    public final String zzj() {
        return this.zzb.zzP();
    }

    @Override // com.google.android.gms.measurement.internal.zzlk
    public final String zzk() {
        return this.zzb.zzae();
    }

    @Override // com.google.android.gms.measurement.internal.zzlk
    public final long zzl() {
        return this.zza.zzk().zzd();
    }

    @Override // com.google.android.gms.measurement.internal.zzlk
    public final void zzm(String r4) {
        zzic r3 = this.zza;
        r3.zzw().zza(r4, r3.zzba().elapsedRealtime());
    }

    @Override // com.google.android.gms.measurement.internal.zzlk
    public final void zzn(String r4) {
        zzic r3 = this.zza;
        r3.zzw().zzb(r4, r3.zzba().elapsedRealtime());
    }

    @Override // com.google.android.gms.measurement.internal.zzlk
    public final void zzo(Bundle r1) {
        this.zzb.zzY(r1);
    }

    @Override // com.google.android.gms.measurement.internal.zzlk
    public final void zzp(String r1, String r2, Bundle r3) {
        this.zza.zzj().zzaa(r1, r2, r3);
    }

    @Override // com.google.android.gms.measurement.internal.zzlk
    public final List zzq(String r1, String r2) {
        return this.zzb.zzab(r1, r2);
    }

    @Override // com.google.android.gms.measurement.internal.zzlk
    public final int zzr(String r1) {
        this.zzb.zzX(r1);
        return 25;
    }
}
