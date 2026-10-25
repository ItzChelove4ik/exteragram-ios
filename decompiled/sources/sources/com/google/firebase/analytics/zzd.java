package com.google.firebase.analytics;

import android.os.Bundle;
import com.google.android.gms.internal.measurement.zzez;
import com.google.android.gms.measurement.internal.zzlk;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
final class zzd implements zzlk {
    final /* synthetic */ zzez zza;

    public zzd(zzez r1) {
        this.zza = r1;
    }

    @Override // com.google.android.gms.measurement.internal.zzlk
    public final void zza(String r1, String r2, Bundle r3) {
        this.zza.zzi(r1, r2, r3);
    }

    @Override // com.google.android.gms.measurement.internal.zzlk
    public final Map zzd(String r1, String r2, boolean r3) {
        return this.zza.zzC(r1, r2, r3);
    }

    @Override // com.google.android.gms.measurement.internal.zzlk
    public final String zzh() {
        return this.zza.zzA();
    }

    @Override // com.google.android.gms.measurement.internal.zzlk
    public final String zzi() {
        return this.zza.zzB();
    }

    @Override // com.google.android.gms.measurement.internal.zzlk
    public final String zzj() {
        return this.zza.zzy();
    }

    @Override // com.google.android.gms.measurement.internal.zzlk
    public final String zzk() {
        return this.zza.zzx();
    }

    @Override // com.google.android.gms.measurement.internal.zzlk
    public final long zzl() {
        return this.zza.zzz();
    }

    @Override // com.google.android.gms.measurement.internal.zzlk
    public final void zzm(String r1) {
        this.zza.zzu(r1);
    }

    @Override // com.google.android.gms.measurement.internal.zzlk
    public final void zzn(String r1) {
        this.zza.zzv(r1);
    }

    @Override // com.google.android.gms.measurement.internal.zzlk
    public final void zzo(Bundle r1) {
        this.zza.zzl(r1);
    }

    @Override // com.google.android.gms.measurement.internal.zzlk
    public final void zzp(String r1, String r2, Bundle r3) {
        this.zza.zzm(r1, r2, r3);
    }

    @Override // com.google.android.gms.measurement.internal.zzlk
    public final List zzq(String r1, String r2) {
        return this.zza.zzn(r1, r2);
    }

    @Override // com.google.android.gms.measurement.internal.zzlk
    public final int zzr(String r1) {
        return this.zza.zzF(r1);
    }
}
