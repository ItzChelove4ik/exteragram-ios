package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Objects;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
final class zzkj implements Runnable {
    final /* synthetic */ Bundle zza;
    final /* synthetic */ zzlj zzb;

    public zzkj(zzlj r1, Bundle r2) {
        this.zza = r2;
        Objects.requireNonNull(r1);
        this.zzb = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzlj r3 = this.zzb;
        r3.zzg();
        r3.zzb();
        Bundle r0 = this.zza;
        Preconditions.checkNotNull(r0);
        String r6 = Preconditions.checkNotEmpty(r0.getString("name"));
        if (r3.zzu.zzB() == true) goto L6;
        r3.zzu.zzaW().zzk().zza("Conditional property not cleared since app measurement is disabled");
        return;
    L6:
        zzpl r5 = new zzpl(r6, 0, null, _UrlKt.FRAGMENT_ENCODE_SET);
        zzbh r18 = r3.zzu.zzk().zzaf(r0.getString("app_id"), r0.getString("expired_event_name"), r0.getBundle("expired_event_params"), _UrlKt.FRAGMENT_ENCODE_SET, r0.getLong("creation_timestamp"), 0, true, true);     // Catch: IllegalArgumentException -> L10
        zzah r4 = new zzah(r0.getString("app_id"), _UrlKt.FRAGMENT_ENCODE_SET, r5, r0.getLong("creation_timestamp"), r0.getBoolean("active"), r0.getString("trigger_event_name"), null, r0.getLong("trigger_timeout"), null, r0.getLong("time_to_live"), r18);
        r3.zzu.zzt().zzp(r4);
        return;
    }
}
