package com.google.android.gms.internal.mlkit_genai_prompt;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.mlkit.genai.common.GenAiException;
import java.util.concurrent.CancellationException;

/* loaded from: classes4.dex */
final /* synthetic */ class zzauc implements zzoj {
    private final /* synthetic */ zzaua zza;

    public /* synthetic */ zzauc(zzaua r1) {
        this.zza = r1;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzoj
    public final /* synthetic */ ListenableFuture zza(Object r4) {
        Throwable r42 = (Throwable) r4;
        if ((r42 instanceof GenAiException) == false) goto L6;
        GenAiException r43 = (GenAiException) r42;
    L13:
        this.zza.zza(r43);
        return zzot.zzc(r43);
    L6:
        if ((r42 instanceof zzdo) == false) goto L10;
        zzdo r44 = (zzdo) r42;
        GenAiException r0 = new GenAiException(r44, r44.zzg(), r44.zzh());
    L8:
        r43 = r0;
        goto L13
    L10:
        if ((r42 instanceof CancellationException) == false) goto L12;
        r0 = new GenAiException(zzlb.zza(r42.getMessage()), r42, 7);
        goto L8
    L12:
        r0 = new GenAiException(zzlb.zza(r42.getMessage()), r42, 0);
        goto L8
    }
}
