package com.google.android.recaptcha.internal;

import c.f$$ExternalSyntheticBUOutline1;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.mvel2.util.Make$Map$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
final class zzahg {
    private static final zzahg zza = null;
    private final ConcurrentMap zzb;

    static {
        zza = new zzahg();
    }

    private zzahg() {
        this.zzb = new ConcurrentHashMap();
        zzafp.zza();
    }

    public static zzahg zza() {
        return zza;
    }

    private <T extends zzafv> zzahn<T> zzc(Class<T> r11) {
        int r0 = zzaho.$r8$clinit;
        if (zzafv.class.isAssignableFrom(r11) == true) goto L5;
        int r1 = zzaef.$r8$clinit;
    L5:
        int r12 = zzaef.$r8$clinit;
        zzafi r2 = null;
        if (zzafv.class.isAssignableFrom(r11) == true) goto L24;
        f$$ExternalSyntheticBUOutline1.m("Unsupported message type: ".concat(r11.getName()));
        return null;
    L24:
        zzagv r4 = (zzagv) zzafv.zzK(r11.asSubclass(zzafv.class)).zzf(3, null, null);     // Catch: Exception -> L19
        if (r4.zza() == false) goto L11;
        zzahn<T> r02 = zzahb.zzh(zzaho.zzA(), zzafk.zza(), r4.zzb());
        Class<T> r3 = r11;
    L15:
        zzahn<T> r10 = (zzahn) this.zzb.putIfAbsent(r3, r02);
        if (r10 == null) goto L18;
        return r10;
    L18:
        return r02;
    L11:
        zzahc r5 = zzahd.zza();
        zzago r6 = zzagp.zza();
        zzahz r7 = zzaho.zzA();
        if ((r4.zzc() - 1) == 1) goto L14;
        r2 = zzafk.zza();
    L14:
        r3 = r11;
        r02 = zzaha.zzm(r3, r4, r5, r6, r7, r2, zzagu.zza());
    L19:
        e = move-exception;
        Make$Map$$ExternalSyntheticBUOutline0.m("Unable to get message info for ".concat(r11.getName()), e);
        return null;
    }

    public final zzahn zzb(Class r2) {
        Object r0 = this.zzb.get(r2);
        if (r0 != null) goto L7;
        return zzc(r2);
    L7:
        return (zzahn) r0;
    }
}
