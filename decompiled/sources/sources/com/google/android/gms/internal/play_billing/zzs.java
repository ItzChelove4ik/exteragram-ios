package com.google.android.gms.internal.play_billing;

import java.util.Objects;

/* loaded from: classes5.dex */
final class zzs extends zzo {
    final /* synthetic */ zzt zzg;

    public zzs(zzt r1) {
        Objects.requireNonNull(r1);
        this.zzg = r1;
    }

    @Override // com.google.android.gms.internal.play_billing.zzo
    public final String zza() {
        zzp r2 = (zzp) this.zzg.zza.get();
        if (r2 != null) goto L7;
        return "Completer object has been garbage collected, future will fail soon";
    L7:
        return "tag=[" + String.valueOf(r2.zza) + "]";
    }
}
