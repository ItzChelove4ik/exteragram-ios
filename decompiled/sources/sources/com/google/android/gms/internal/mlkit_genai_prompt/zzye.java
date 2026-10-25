package com.google.android.gms.internal.mlkit_genai_prompt;

import kotlin.enums.EnumEntriesKt;

/* loaded from: classes4.dex */
public enum zzye extends Enum {
    public static final zzye zza = null;
    public static final zzye zzb = null;
    public static final zzye zzc = null;
    public static final zzye zzd = null;
    public static final zzye zze = null;
    private static final /* synthetic */ zzye[] zzf = null;

    static {
        zzye r0 = new zzye("INFERENCE_WITHOUT_CACHE", 0);
        zza = r0;
        zzye r1 = new zzye("CACHE_CREATION", 1);
        zzb = r1;
        zzye r2 = new zzye("INFERENCE_WITH_CACHE", 2);
        zzc = r2;
        zzye r3 = new zzye("STRUCTURED_OUTPUT_INFERENCE_WITHOUT_CACHE", 3);
        zzd = r3;
        zzye r4 = new zzye("STRUCTURED_OUTPUT_INFERENCE_WITH_CACHE", 4);
        zze = r4;
        zzye[] r02 = {r0, r1, r2, r3, r4};
        zzf = r02;
        EnumEntriesKt.enumEntries(r02);
    }

    zzye(String r1, int r2) {
    }

    public static zzye[] values() {
        return (zzye[]) zzf.clone();
    }
}
