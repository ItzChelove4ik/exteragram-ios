package com.google.android.gms.internal.measurement;

import c.f$$ExternalSyntheticBUOutline1;
import org.mvel2.util.Make$Map$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
final class zzado implements zzafa {
    private static final zzado zza = null;

    static {
        zza = new zzado();
    }

    private zzado() {
    }

    public static zzado zza() {
        return zza;
    }

    @Override // com.google.android.gms.internal.measurement.zzafa
    public final boolean zzb(Class r1) {
        return zzadu.class.isAssignableFrom(r1);
    }

    @Override // com.google.android.gms.internal.measurement.zzafa
    public final zzaez zzc(Class r3) {
        if (zzadu.class.isAssignableFrom(r3) == true) goto L11;
        f$$ExternalSyntheticBUOutline1.m("Unsupported message type: ".concat(r3.getName()));
        return null;
    L11:
        return (zzaez) zzadu.zzcr(r3.asSubclass(zzadu.class)).zzg(3, null, null);
    L6:
        e = move-exception;
        Make$Map$$ExternalSyntheticBUOutline0.m("Unable to get message info for ".concat(r3.getName()), e);
        return null;
    }
}
