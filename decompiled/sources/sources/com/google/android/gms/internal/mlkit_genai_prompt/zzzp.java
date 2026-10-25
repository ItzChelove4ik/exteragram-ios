package com.google.android.gms.internal.mlkit_genai_prompt;

import com.google.mlkit.genai.prompt.GenerateContentRequest;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* loaded from: classes4.dex */
final class zzzp extends ContinuationImpl {
    Object zza;
    Object zzb;
    boolean zzc;
    /* synthetic */ Object zzd;
    final /* synthetic */ zzzy zze;
    int zzf;
    GenerateContentRequest zzg;
    String zzh;
    zzxt zzi;

    public zzzp(zzzy r1, Continuation r2) {
        this.zze = r1;
        super(r2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r8) {
        this.zzd = r8;
        this.zzf |= Integer.MIN_VALUE;
        return zzzy.zzl(this.zze, null, null, null, null, false, this);
    }
}
