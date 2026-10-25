package com.google.android.gms.internal.mlkit_genai_prompt;

import com.google.mlkit.genai.prompt.CreateCachedContextRequest;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* loaded from: classes4.dex */
final class zzxk extends ContinuationImpl {
    /* synthetic */ Object zza;
    final /* synthetic */ zzxo zzb;
    int zzc;
    CreateCachedContextRequest zzd;
    String zze;
    String zzf;

    public zzxk(zzxo r1, Continuation r2) {
        this.zzb = r1;
        super(r2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r2) {
        this.zza = r2;
        this.zzc |= Integer.MIN_VALUE;
        return this.zzb.create(null, this);
    }
}
