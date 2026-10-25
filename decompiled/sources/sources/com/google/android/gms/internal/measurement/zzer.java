package com.google.android.gms.internal.measurement;

import android.app.Activity;
import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Objects;

/* loaded from: classes.dex */
final class zzer extends zzeo {
    final /* synthetic */ Bundle zza;
    final /* synthetic */ Activity zzb;
    final /* synthetic */ zzey zzc;

    public zzer(zzey r1, Bundle r2, Activity r3) {
        this.zza = r2;
        this.zzb = r3;
        Objects.requireNonNull(r1);
        this.zzc = r1;
        super(r1.zza, true);
    }

    @Override // com.google.android.gms.internal.measurement.zzeo
    public final void zza() {
        Bundle r0 = this.zza;
        if (r0 == null) goto L9;
        Bundle r1 = new Bundle();
        if (r0.containsKey("com.google.app_measurement.screen_service") == false) goto L10;
        Object r02 = r0.get("com.google.app_measurement.screen_service");
        if ((r02 instanceof Bundle) == false) goto L10;
        r1.putBundle("com.google.app_measurement.screen_service", (Bundle) r02);
    L10:
        zzcp r03 = (zzcp) Preconditions.checkNotNull(this.zzc.zza.zzS());
        Activity r2 = this.zzb;
        long r3 = this.zzj;
        r03.onActivityCreatedByScionActivityInfo(zzdd.zza(r2), r1, r3);
        return;
    L9:
        r1 = null;
        goto L10
    }
}
