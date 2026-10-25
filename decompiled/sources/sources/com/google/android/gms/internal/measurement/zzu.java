package com.google.android.gms.internal.measurement;

import java.util.List;
import java.util.concurrent.Callable;

/* loaded from: classes4.dex */
public final class zzu extends zzai {
    private final Callable zza;

    public zzu(String r1, Callable r2) {
        super("internal.appMetadata");
        this.zza = r2;
    }

    @Override // com.google.android.gms.internal.measurement.zzai
    public final zzao zza(zzg r1, List r2) {
        return zzi.zza(this.zza.call());
    L5:
        return zzao.zzf;
    }
}
