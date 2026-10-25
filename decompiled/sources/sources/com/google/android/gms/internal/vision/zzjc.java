package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzjb;

/* loaded from: classes5.dex */
final class zzjc implements zzkl {
    private static final zzjc zza = null;

    static {
        zza = new zzjc();
    }

    private zzjc() {
    }

    public static zzjc zza() {
        return zza;
    }

    @Override // com.google.android.gms.internal.vision.zzkl
    public final boolean zza(Class<?> r1) {
        return zzjb.class.isAssignableFrom(r1);
    }

    @Override // com.google.android.gms.internal.vision.zzkl
    public final zzki zzb(Class<?> r4) {
        if (zzjb.class.isAssignableFrom(r4) == true) goto L19;
        String r42 = r4.getName();
        if (r42.length() == 0) goto L7;
        String r43 = "Unsupported message type: ".concat(r42);
    L9:
        throw new IllegalArgumentException(r43);
    L7:
        r43 = new String("Unsupported message type: ");
        goto L9
    L19:
        return (zzki) zzjb.zza(r4.asSubclass(zzjb.class)).zza(zzjb.zzg.zzc, null, null);
    L12:
        e = move-exception;
        String r44 = r4.getName();
        if (r44.length() == 0) goto L16;
        String r45 = "Unable to get message info for ".concat(r44);
    L18:
        throw new RuntimeException(r45, e);
    L16:
        r45 = new String("Unable to get message info for ");
        goto L18
    }
}
