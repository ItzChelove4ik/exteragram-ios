package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Objects;

/* loaded from: classes5.dex */
final class zzki implements Runnable {
    final /* synthetic */ Bundle zza;
    final /* synthetic */ zzlj zzb;

    public zzki(zzlj r1, Bundle r2) {
        this.zza = r2;
        Objects.requireNonNull(r1);
        this.zzb = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzlj r2 = this.zzb;
        r2.zzg();
        r2.zzb();
        Bundle r0 = this.zza;
        Preconditions.checkNotNull(r0);
        String r5 = r0.getString("name");
        String r10 = r0.getString("origin");
        Preconditions.checkNotEmpty(r5);
        Preconditions.checkNotEmpty(r10);
        Preconditions.checkNotNull(r0.get("value"));
        if (r2.zzu.zzB() == true) goto L6;
        r2.zzu.zzaW().zzk().zza("Conditional property not set since app measurement is disabled");
        return;
    L6:
        zzpl r4 = new zzpl(r5, r0.getLong("triggered_timestamp"), r0.get("value"), r10);
        zzic r3 = r2.zzu;     // Catch: IllegalArgumentException -> L10
        zzbh r17 = r3.zzk().zzaf(r0.getString("app_id"), r0.getString("triggered_event_name"), r0.getBundle("triggered_event_params"), r10, 0, 0, true, true);     // Catch: IllegalArgumentException -> L10
        zzbh r52 = r3.zzk().zzaf(r0.getString("app_id"), r0.getString("timed_out_event_name"), r0.getBundle("timed_out_event_params"), r10, 0, 0, true, true);     // Catch: IllegalArgumentException -> L10
        zzbh r20 = r3.zzk().zzaf(r0.getString("app_id"), r0.getString("expired_event_name"), r0.getBundle("expired_event_params"), r10, 0, 0, true, true);     // Catch: IllegalArgumentException -> L10
        zzah r6 = new zzah(r0.getString("app_id"), r10, r4, r0.getLong("creation_timestamp"), false, r0.getString("trigger_event_name"), r52, r0.getLong("trigger_timeout"), r17, r0.getLong("time_to_live"), r20);
        r2.zzu.zzt().zzp(r6);
        return;
    }
}
