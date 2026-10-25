package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public abstract class zzav {
    final List zza;

    public zzav() {
        this.zza = new ArrayList();
    }

    public abstract zzao zza(String r1, zzg r2, List r3);

    public final zzao zzb(String r2) {
        if (this.zza.contains(zzh.zze(r2)) == false) goto L7;
        throw new UnsupportedOperationException("Command not implemented: ".concat(String.valueOf(r2)));
    L7:
        throw new IllegalArgumentException("Command not supported");
    }
}
