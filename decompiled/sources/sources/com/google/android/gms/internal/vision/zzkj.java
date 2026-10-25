package com.google.android.gms.internal.vision;

/* loaded from: classes5.dex */
abstract class zzkj {
    private static final zzkh zza = null;
    private static final zzkh zzb = null;

    static {
        zza = zzc();
        zzb = new zzkg();
    }

    public static zzkh zza() {
        return zza;
    }

    public static zzkh zzb() {
        return zzb;
    }

    private static zzkh zzc() {
        return (zzkh) Class.forName("com.google.protobuf.MapFieldSchemaFull").getDeclaredConstructor(null).newInstance(null);
    L5:
        return null;
    }
}
