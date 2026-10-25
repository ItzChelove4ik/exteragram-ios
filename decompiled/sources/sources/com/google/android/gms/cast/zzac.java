package com.google.android.gms.cast;

import java.util.Objects;

/* loaded from: classes4.dex */
final class zzac implements Runnable {
    final /* synthetic */ CastRemoteDisplayLocalService zza;

    public zzac(CastRemoteDisplayLocalService r1) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        CastRemoteDisplayLocalService r3 = this.zza;
        boolean r0 = r3.zzs();
        StringBuilder r2 = new StringBuilder(String.valueOf(r0).length() + 54);
        r2.append("onCreate after delay. The local service been started: ");
        r2.append(r0);
        r3.zzg(r2.toString());
        if (r3.zzs() == true) goto L6;
        r3.zzh("The local service has not been been started, stopping it");
        r3.stopSelf();
        return;
    }
}
