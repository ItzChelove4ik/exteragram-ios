package com.google.android.gms.internal.clearcut;

import com.google.android.gms.internal.clearcut.zzcg;

/* loaded from: classes4.dex */
final class zzcf implements zzdn {
    private static final zzcf zzjo = null;

    static {
        zzjo = new zzcf();
    }

    private zzcf() {
    }

    public static zzcf zzay() {
        return zzjo;
    }

    @Override // com.google.android.gms.internal.clearcut.zzdn
    public final boolean zza(Class<?> r1) {
        return zzcg.class.isAssignableFrom(r1);
    }

    @Override // com.google.android.gms.internal.clearcut.zzdn
    public final zzdm zzb(Class<?> r4) {
        if (zzcg.class.isAssignableFrom(r4) == true) goto L19;
        String r42 = r4.getName();
        if (r42.length() == 0) goto L7;
        String r43 = "Unsupported message type: ".concat(r42);
    L9:
        throw new IllegalArgumentException(r43);
    L7:
        r43 = new String("Unsupported message type: ");
        goto L9
    L19:
        return (zzdm) zzcg.zzc(r4.asSubclass(zzcg.class)).zza(zzcg.zzg.zzkf, null, null);
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
