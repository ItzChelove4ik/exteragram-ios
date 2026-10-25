package com.google.android.gms.internal.mlkit_genai_prompt;

import com.google.mlkit.genai.prompt.CreateCachedContextRequest;

/* loaded from: classes4.dex */
public final class zzwh {
    final /* synthetic */ zzwl zza;
    final /* synthetic */ CreateCachedContextRequest zzb;

    public zzwh(zzwl r1, CreateCachedContextRequest r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    public final void zza(boolean r2) {
        zzpk r1 = (zzpk) zzwl.zzh(this.zza).get(this.zzb);
        if (r1 == null) goto L6;
        r1.zzh(Boolean.valueOf(r2));
        return;
    }
}
