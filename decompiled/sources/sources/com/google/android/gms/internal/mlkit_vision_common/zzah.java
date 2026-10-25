package com.google.android.gms.internal.mlkit_vision_common;

import com.exteragram.messenger.plugins.PluginsConstants;

/* loaded from: classes4.dex */
public enum zzah extends Enum {
    public static final zzah zza = null;
    public static final zzah zzb = null;
    public static final zzah zzc = null;
    private static final /* synthetic */ zzah[] zzd = null;

    static {
        zzah r0 = new zzah(PluginsConstants.Strategy.DEFAULT, 0);
        zza = r0;
        zzah r1 = new zzah("SIGNED", 1);
        zzb = r1;
        zzah r2 = new zzah("FIXED", 2);
        zzc = r2;
        zzd = new zzah[]{r0, r1, r2};
    }

    zzah(String r1, int r2) {
    }

    public static zzah[] values() {
        return (zzah[]) zzd.clone();
    }
}
