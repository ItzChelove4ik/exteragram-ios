package com.google.android.gms.internal.clearcut;

import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
abstract class zzbx {
    private static final zzbu<?> zzgr = null;
    private static final zzbu<?> zzgs = null;

    static {
        zzgr = new zzbv();
        zzgs = zzao();
    }

    private static zzbu<?> zzao() {
        return (zzbu) Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor(null).newInstance(null);
    L5:
        return null;
    }

    public static zzbu<?> zzap() {
        return zzgr;
    }

    public static zzbu<?> zzaq() {
        zzbu<?> r0 = zzgs;
        if (r0 == null) goto L5;
        return r0;
    L5:
        Segment$$ExternalSyntheticBUOutline1.m("Protobuf runtime is not correctly loaded.");
        return null;
    }
}
