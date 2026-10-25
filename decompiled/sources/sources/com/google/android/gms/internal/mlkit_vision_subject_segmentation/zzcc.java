package com.google.android.gms.internal.mlkit_vision_subject_segmentation;

import com.exteragram.messenger.plugins.PluginsConstants;

/* loaded from: classes4.dex */
public enum zzcc extends Enum {
    public static final zzcc zza = null;
    public static final zzcc zzb = null;
    public static final zzcc zzc = null;
    private static final /* synthetic */ zzcc[] zzd = null;

    static {
        zzcc r0 = new zzcc(PluginsConstants.Strategy.DEFAULT, 0);
        zza = r0;
        zzcc r1 = new zzcc("SIGNED", 1);
        zzb = r1;
        zzcc r2 = new zzcc("FIXED", 2);
        zzc = r2;
        zzd = new zzcc[]{r0, r1, r2};
    }

    zzcc(String r1, int r2) {
    }

    public static zzcc[] values() {
        return (zzcc[]) zzd.clone();
    }
}
