package com.google.android.gms.internal.cast;

import okio.ByteString$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
final class zzyz implements zzzg {
    private final zzzg[] zza;

    public zzyz(zzzg... r1) {
        this.zza = r1;
    }

    @Override // com.google.android.gms.internal.cast.zzzg
    public final boolean zzb(Class r4) {
        int r1 = 0;
    L4:
        if (r1 >= 2) goto L10;
        if (this.zza[r1].zzb(r4) == true) goto L7;
        r1 = r1 + 1;
        goto L4
    L7:
        return true;
    L10:
        return false;
    }

    @Override // com.google.android.gms.internal.cast.zzzg
    public final zzzf zzc(Class r4) {
        int r0 = 0;
    L4:
        if (r0 >= 2) goto L10;
        zzzg r1 = this.zza[r0];
        if (r1.zzb(r4) == true) goto L8;
        r0 = r0 + 1;
        goto L4
    L8:
        return r1.zzc(r4);
    L10:
        ByteString$$ExternalSyntheticBUOutline0.m("No factory is available for message type: ".concat(r4.getName()));
        return null;
    }
}
