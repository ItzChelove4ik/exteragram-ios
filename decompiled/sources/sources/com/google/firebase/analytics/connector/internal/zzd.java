package com.google.firebase.analytics.connector.internal;

import android.os.Bundle;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.android.gms.measurement.internal.zzjm;
import java.util.Objects;

/* loaded from: classes5.dex */
final class zzd implements AppMeasurementSdk.OnEventListener {
    final /* synthetic */ zze zza;

    public zzd(zze r1) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    @Override // com.google.android.gms.measurement.internal.zzjq
    public final void onEvent(String r1, String r2, Bundle r3, long r4) {
        zze r0 = this.zza;
        if (r0.zza.contains(r2) == true) goto L5;
        return;
    L5:
        Bundle r12 = new Bundle();
        int r32 = zzc.$r8$clinit;
        String r33 = zzjm.zza(r2);
        if (r33 == null) goto L8;
        r2 = r33;
    L8:
        r12.putString("events", r2);
        r0.zzd().onMessageTriggered(2, r12);
    }
}
