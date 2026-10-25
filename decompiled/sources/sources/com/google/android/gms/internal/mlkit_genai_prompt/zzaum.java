package com.google.android.gms.internal.mlkit_genai_prompt;

import com.google.common.util.concurrent.ListenableFuture;

/* loaded from: classes4.dex */
final /* synthetic */ class zzaum implements zzoj {
    static final /* synthetic */ zzaum zza = null;

    static {
        zza = new zzaum();
    }

    private /* synthetic */ zzaum() {
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzoj
    public final /* synthetic */ ListenableFuture zza(Object r3) {
        zzdq r32 = (zzdq) r3;
        if (r32 == null) goto L5;
        String r2 = r32.zzb();
        if ("nano-v4-full".equals(r2) == false) goto L15;
        if (r32.zze() == 646) goto L13;
        if (r32.zze() != 645) goto L15;
    L13:
        return zzot.zza("nano-v4-fast");
    L15:
        return zzot.zza(r2);
    L5:
        return zzot.zzc(new IllegalStateException("Failed to get base model name. aiFeature is null."));
    }
}
