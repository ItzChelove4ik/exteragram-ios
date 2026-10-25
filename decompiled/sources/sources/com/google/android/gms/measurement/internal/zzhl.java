package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.content.Intent;
import com.google.android.gms.common.internal.Preconditions;

/* loaded from: classes5.dex */
public final class zzhl {
    private final zza zza;

    public interface zza {
        void doStartService(Context r1, Intent r2);
    }

    public zzhl(zza r1) {
        Preconditions.checkNotNull(r1);
        this.zza = r1;
    }

    public final void zza(Context r4, Intent r5) {
        zzic r0 = zzic.zzy(r4, null, null, null);
        zzgu r1 = r0.zzaW();
        if (r5 != null) goto L6;
        r1.zze().zza("Receiver called with null intent");
        return;
    L6:
        r0.zzaV();
        String r52 = r5.getAction();
        r1.zzk().zzb("Local receiver got", r52);
        if ("com.google.android.gms.measurement.UPLOAD".equals(r52) == false) goto L11;
        Intent r53 = new Intent().setClassName(r4, "com.google.android.gms.measurement.AppMeasurementService");
        r53.setAction("com.google.android.gms.measurement.UPLOAD");
        r1.zzk().zza("Starting wakeful intent.");
        this.zza.doStartService(r4, r53);
        return;
    L11:
        if ("com.android.vending.INSTALL_REFERRER".equals(r52) == false) goto L14;
        r1.zze().zza("Install Referrer Broadcasts are deprecated");
        return;
    }
}
