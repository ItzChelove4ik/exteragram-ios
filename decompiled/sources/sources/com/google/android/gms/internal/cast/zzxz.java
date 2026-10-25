package com.google.android.gms.internal.cast;

import c.f$$ExternalSyntheticBUOutline1;
import org.mvel2.util.Make$Map$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
final class zzxz implements zzzg {
    private static final zzxz zza = null;

    static {
        zza = new zzxz();
    }

    private zzxz() {
    }

    public static zzxz zza() {
        return zza;
    }

    @Override // com.google.android.gms.internal.cast.zzzg
    public final boolean zzb(Class r1) {
        return zzyd.class.isAssignableFrom(r1);
    }

    @Override // com.google.android.gms.internal.cast.zzzg
    public final zzzf zzc(Class r3) {
        if (zzyd.class.isAssignableFrom(r3) == true) goto L11;
        f$$ExternalSyntheticBUOutline1.m("Unsupported message type: ".concat(r3.getName()));
        return null;
    L11:
        return (zzzf) zzyd.zzF(r3.asSubclass(zzyd.class)).zzb(3, null, null);
    L6:
        e = move-exception;
        Make$Map$$ExternalSyntheticBUOutline0.m("Unable to get message info for ".concat(r3.getName()), e);
        return null;
    }
}
