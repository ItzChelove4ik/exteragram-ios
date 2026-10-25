package com.google.android.gms.internal.vision;

/* loaded from: classes5.dex */
abstract class zzku {
    private static final zzks zza = null;
    private static final zzks zzb = null;

    static {
        zza = zzc();
        zzb = new zzkv();
    }

    public static zzks zza() {
        return zza;
    }

    public static zzks zzb() {
        return zzb;
    }

    private static zzks zzc() {
        return (zzks) Class.forName("com.google.protobuf.NewInstanceSchemaFull").getDeclaredConstructor(null).newInstance(null);
    L5:
        return null;
    }
}
