package com.google.android.gms.internal.mlkit_vision_label;

import com.exteragram.messenger.plugins.PluginsConstants;

/* loaded from: classes4.dex */
public enum zzcl extends Enum {
    public static final zzcl zza = null;
    public static final zzcl zzb = null;
    public static final zzcl zzc = null;
    private static final /* synthetic */ zzcl[] zzd = null;

    static {
        zzcl r0 = new zzcl(PluginsConstants.Strategy.DEFAULT, 0);
        zza = r0;
        zzcl r1 = new zzcl("SIGNED", 1);
        zzb = r1;
        zzcl r2 = new zzcl("FIXED", 2);
        zzc = r2;
        zzd = new zzcl[]{r0, r1, r2};
    }

    zzcl(String r1, int r2) {
    }

    public static zzcl[] values() {
        return (zzcl[]) zzd.clone();
    }
}
