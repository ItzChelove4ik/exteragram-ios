package com.google.android.gms.internal.vision;

import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
abstract class zzir {
    private static final zziq<?> zza = null;
    private static final zziq<?> zzb = null;

    static {
        zza = new zzip();
        zzb = zzc();
    }

    public static zziq<?> zza() {
        return zza;
    }

    public static zziq<?> zzb() {
        zziq<?> r0 = zzb;
        if (r0 == null) goto L5;
        return r0;
    L5:
        Segment$$ExternalSyntheticBUOutline1.m("Protobuf runtime is not correctly loaded.");
        return null;
    }

    private static zziq<?> zzc() {
        return (zziq) Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor(null).newInstance(null);
    L5:
        return null;
    }
}
