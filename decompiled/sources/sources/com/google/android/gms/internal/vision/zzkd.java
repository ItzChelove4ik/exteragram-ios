package com.google.android.gms.internal.vision;

/* loaded from: classes5.dex */
final class zzkd implements zzkl {
    private zzkl[] zza;

    public zzkd(zzkl... r1) {
        this.zza = r1;
    }

    @Override // com.google.android.gms.internal.vision.zzkl
    public final boolean zza(Class<?> r5) {
        zzkl[] r4 = this.zza;
        int r0 = r4.length;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L9;
        if (r4[r2].zza(r5) == true) goto L6;
        r2 = r2 + 1;
        goto L3
    L6:
        return true;
    L9:
        return false;
    }

    @Override // com.google.android.gms.internal.vision.zzkl
    public final zzki zzb(Class<?> r5) {
        zzkl[] r4 = this.zza;
        int r0 = r4.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L9;
        zzkl r2 = r4[r1];
        if (r2.zza(r5) == true) goto L7;
        r1 = r1 + 1;
        goto L3
    L7:
        return r2.zzb(r5);
    L9:
        String r52 = r5.getName();
        if (r52.length() == 0) goto L12;
        String r53 = "No factory is available for message type: ".concat(r52);
    L14:
        throw new UnsupportedOperationException(r53);
    L12:
        r53 = new String("No factory is available for message type: ");
        goto L14
    }
}
