package com.google.android.gms.internal.mlkit_language_id_common;

import com.exteragram.messenger.plugins.PluginsConstants;

/* loaded from: classes4.dex */
public enum zzal extends Enum {
    public static final zzal zza = null;
    public static final zzal zzb = null;
    public static final zzal zzc = null;
    private static final /* synthetic */ zzal[] zzd = null;

    static {
        zzal r0 = new zzal(PluginsConstants.Strategy.DEFAULT, 0);
        zza = r0;
        zzal r1 = new zzal("SIGNED", 1);
        zzb = r1;
        zzal r2 = new zzal("FIXED", 2);
        zzc = r2;
        zzd = new zzal[]{r0, r1, r2};
    }

    zzal(String r1, int r2) {
    }

    public static zzal[] values() {
        return (zzal[]) zzd.clone();
    }
}
