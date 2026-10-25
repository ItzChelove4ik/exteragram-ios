package com.google.android.gms.internal.mlkit_genai_prompt;

import kotlin.enums.EnumEntriesKt;

/* loaded from: classes4.dex */
enum zzyi extends Enum {
    public static final zzyi zza = null;
    public static final zzyi zzb = null;
    public static final zzyi zzc = null;
    public static final zzyi zzd = null;
    private static final /* synthetic */ zzyi[] zze = null;

    static {
        zzyi r0 = new zzyi("CREATE", 0);
        zza = r0;
        zzyi r1 = new zzyi("INFERENCE", 1);
        zzb = r1;
        zzyi r2 = new zzyi("COUNT_TOKENS", 2);
        zzc = r2;
        zzyi r3 = new zzyi("CLOSE", 3);
        zzd = r3;
        zzyi[] r02 = {r0, r1, r2, r3};
        zze = r02;
        EnumEntriesKt.enumEntries(r02);
    }

    zzyi(String r1, int r2) {
    }

    public static zzyi[] values() {
        return (zzyi[]) zze.clone();
    }
}
