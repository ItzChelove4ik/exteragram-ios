package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.text.TextUtils;
import java.util.Objects;

/* loaded from: classes5.dex */
final class zzpb implements zzpo {
    final /* synthetic */ zzpg zza;

    public zzpb(zzpg r1) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    @Override // com.google.android.gms.measurement.internal.zzpo
    public final void zza(String r3, String r4, Bundle r5) {
        boolean r0 = TextUtils.isEmpty(r3);
        zzpg r1 = this.zza;
        if (r0 == true) goto L5;
        r1.zzaX().zzj(new zzpa(this, r3, r4, r5));
        return;
    L5:
        if (r1.zzay() == null) goto L10;
        r1.zzay().zzaW().zzb().zzb("AppId not known when logging event", r4);
        return;
    }
}
