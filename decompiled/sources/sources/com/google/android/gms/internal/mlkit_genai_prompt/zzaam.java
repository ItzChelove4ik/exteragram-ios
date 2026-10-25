package com.google.android.gms.internal.mlkit_genai_prompt;

import kotlin.enums.EnumEntriesKt;

/* loaded from: classes4.dex */
public enum zzaam extends Enum {
    public static final zzaam zza = null;
    public static final zzaam zzb = null;
    private static final /* synthetic */ zzaam[] zzc = null;

    static {
        zzaam r0 = new zzaam("INFERENCE_WITHOUT_CACHE", 0);
        zza = r0;
        zzaam r1 = new zzaam("INFERENCE_WITH_CACHE", 1);
        zzb = r1;
        zzaam[] r02 = {r0, r1};
        zzc = r02;
        EnumEntriesKt.enumEntries(r02);
    }

    zzaam(String r1, int r2) {
    }

    public static zzaam[] values() {
        return (zzaam[]) zzc.clone();
    }
}
