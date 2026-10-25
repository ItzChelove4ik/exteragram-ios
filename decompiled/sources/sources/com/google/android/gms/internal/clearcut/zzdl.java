package com.google.android.gms.internal.clearcut;

/* loaded from: classes4.dex */
abstract class zzdl {
    private static final zzdj zzmf = null;
    private static final zzdj zzmg = null;

    static {
        zzmf = zzce();
        zzmg = new zzdk();
    }

    public static zzdj zzcc() {
        return zzmf;
    }

    public static zzdj zzcd() {
        return zzmg;
    }

    private static zzdj zzce() {
        return (zzdj) Class.forName("com.google.protobuf.MapFieldSchemaFull").getDeclaredConstructor(null).newInstance(null);
    L5:
        return null;
    }
}
