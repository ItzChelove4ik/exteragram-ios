package com.google.android.gms.internal.clearcut;

/* loaded from: classes4.dex */
abstract class zzdy {
    private static final zzdw zzna = null;
    private static final zzdw zznb = null;

    static {
        zzna = zzcl();
        zznb = new zzdx();
    }

    public static zzdw zzcj() {
        return zzna;
    }

    public static zzdw zzck() {
        return zznb;
    }

    private static zzdw zzcl() {
        return (zzdw) Class.forName("com.google.protobuf.NewInstanceSchemaFull").getDeclaredConstructor(null).newInstance(null);
    L5:
        return null;
    }
}
