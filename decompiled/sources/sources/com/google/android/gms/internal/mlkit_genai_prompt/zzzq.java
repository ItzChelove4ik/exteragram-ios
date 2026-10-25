package com.google.android.gms.internal.mlkit_genai_prompt;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* loaded from: classes4.dex */
final class zzzq extends ContinuationImpl {
    Object zza;
    Object zzb;
    Object zzc;
    boolean zzd;
    int zze;
    /* synthetic */ Object zzf;
    final /* synthetic */ zzzy zzg;
    int zzh;
    String zzi;
    String zzj;
    zzaab zzk;

    public zzzq(zzzy r1, Continuation r2) {
        this.zzg = r1;
        super(r2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r8) {
        this.zzf = r8;
        this.zzh |= Integer.MIN_VALUE;
        return zzzy.zzk(this.zzg, null, null, null, null, false, this);
    }
}
