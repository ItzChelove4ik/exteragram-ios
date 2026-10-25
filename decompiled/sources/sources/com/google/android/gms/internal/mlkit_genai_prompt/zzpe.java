package com.google.android.gms.internal.mlkit_genai_prompt;

import java.util.concurrent.Executor;

/* loaded from: classes4.dex */
final /* synthetic */ class zzpe implements Executor {
    private final /* synthetic */ Executor zza;
    private final /* synthetic */ zzoc zzb;

    public /* synthetic */ zzpe(Executor r1, zzoc r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // java.util.concurrent.Executor
    public final /* synthetic */ void execute(Runnable r2) {
        zzpi.zzd(this.zza, this.zzb, r2);
    }
}
