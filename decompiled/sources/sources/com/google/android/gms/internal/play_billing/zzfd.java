package com.google.android.gms.internal.play_billing;

import c.f$$ExternalSyntheticBUOutline1;
import org.mvel2.util.Make$Map$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
final class zzfd implements zzgj {
    private static final zzfd zza = null;

    static {
        zza = new zzfd();
    }

    private zzfd() {
    }

    public static zzfd zza() {
        return zza;
    }

    @Override // com.google.android.gms.internal.play_billing.zzgj
    public final zzgi zzb(Class r3) {
        if (zzfi.class.isAssignableFrom(r3) == true) goto L11;
        f$$ExternalSyntheticBUOutline1.m("Unsupported message type: ".concat(r3.getName()));
        return null;
    L11:
        return (zzgi) zzfi.zzn(r3.asSubclass(zzfi.class)).zzb(3, null, null);
    L6:
        e = move-exception;
        Make$Map$$ExternalSyntheticBUOutline0.m("Unable to get message info for ".concat(r3.getName()), e);
        return null;
    }

    @Override // com.google.android.gms.internal.play_billing.zzgj
    public final boolean zzc(Class r1) {
        return zzfi.class.isAssignableFrom(r1);
    }
}
