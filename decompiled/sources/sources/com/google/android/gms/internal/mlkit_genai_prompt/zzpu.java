package com.google.android.gms.internal.mlkit_genai_prompt;

import com.exteragram.messenger.plugins.PluginsConstants;

/* loaded from: classes4.dex */
public enum zzpu extends Enum {
    public static final zzpu zza = null;
    public static final zzpu zzb = null;
    public static final zzpu zzc = null;
    private static final /* synthetic */ zzpu[] zzd = null;

    static {
        zzpu r0 = new zzpu(PluginsConstants.Strategy.DEFAULT, 0);
        zza = r0;
        zzpu r1 = new zzpu("SIGNED", 1);
        zzb = r1;
        zzpu r2 = new zzpu("FIXED", 2);
        zzc = r2;
        zzd = new zzpu[]{r0, r1, r2};
    }

    zzpu(String r1, int r2) {
    }

    public static zzpu[] values() {
        return (zzpu[]) zzd.clone();
    }
}
