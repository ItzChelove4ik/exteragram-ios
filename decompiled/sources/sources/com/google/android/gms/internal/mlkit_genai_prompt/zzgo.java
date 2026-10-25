package com.google.android.gms.internal.mlkit_genai_prompt;

import android.util.Pair;
import java.util.function.Consumer;

/* loaded from: classes4.dex */
final /* synthetic */ class zzgo implements Consumer {
    private final /* synthetic */ zzgp zza;
    private final /* synthetic */ zzwh zzb;

    public /* synthetic */ zzgo(zzgp r1, zzwh r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // java.util.function.Consumer
    public final /* synthetic */ void accept(Object r2) {
        this.zza.zzb(this.zzb, (Pair) r2);
    }
}
