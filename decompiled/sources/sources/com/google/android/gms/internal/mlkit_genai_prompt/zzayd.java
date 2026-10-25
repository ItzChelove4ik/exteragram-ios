package com.google.android.gms.internal.mlkit_genai_prompt;

import c.f$$ExternalSyntheticBUOutline1;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.mvel2.util.Make$Map$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
final class zzayd {
    private static final zzayd zza = null;
    private final ConcurrentMap zzb;

    static {
        zza = new zzayd();
    }

    private zzayd() {
        this.zzb = new ConcurrentHashMap();
        zzawt.zza();
    }

    public static zzayd zza() {
        return zza;
    }

    private <T extends zzawy> zzayg<T> zzc(Class<T> r11) {
        int r0 = zzayh.$r8$clinit;
        if (zzawy.class.isAssignableFrom(r11) == true) goto L5;
        int r1 = zzavq.$r8$clinit;
    L5:
        int r12 = zzavq.$r8$clinit;
        zzawm r2 = null;
        if (zzawy.class.isAssignableFrom(r11) == true) goto L24;
        f$$ExternalSyntheticBUOutline1.m("Unsupported message type: ".concat(r11.getName()));
        return null;
    L24:
        zzaxu r4 = (zzaxu) zzawy.zzM(r11.asSubclass(zzawy.class)).zzb(3, null, null);     // Catch: Exception -> L19
        if (r4.zza() == false) goto L11;
        zzayg<T> r02 = zzaya.zze(zzayh.zza(), zzawo.zza(), r4.zzb());
        Class<T> r3 = r11;
    L15:
        zzayg<T> r10 = (zzayg) this.zzb.putIfAbsent(r3, r02);
        if (r10 == null) goto L18;
        return r10;
    L18:
        return r02;
    L11:
        zzayb r5 = zzayc.zza();
        zzaxn r6 = zzaxo.zza();
        zzayq r7 = zzayh.zza();
        if ((r4.zzc() - 1) == 1) goto L14;
        r2 = zzawo.zza();
    L14:
        r3 = r11;
        r02 = zzaxz.zzj(r3, r4, r5, r6, r7, r2, zzaxt.zza());
    L19:
        e = move-exception;
        Make$Map$$ExternalSyntheticBUOutline0.m("Unable to get message info for ".concat(r11.getName()), e);
        return null;
    }

    public final zzayg zzb(Class r2) {
        Object r0 = this.zzb.get(r2);
        if (r0 != null) goto L7;
        return zzc(r2);
    L7:
        return (zzayg) r0;
    }
}
