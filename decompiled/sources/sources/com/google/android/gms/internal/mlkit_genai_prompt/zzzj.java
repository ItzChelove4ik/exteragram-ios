package com.google.android.gms.internal.mlkit_genai_prompt;

import com.google.mlkit.genai.common.DownloadCallback;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendFunction;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* loaded from: classes4.dex */
final /* synthetic */ class zzzj extends FunctionReferenceImpl implements Function2, SuspendFunction {
    public zzzj(Object r8) {
        super(2, r8, zzyd.class, "downloadFeatures", "downloadFeatures(Lcom/google/mlkit/genai/common/DownloadCallback;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", 0);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        zzyd r0 = (zzyd) this.receiver;
        return zzyd.zzb(r0, (DownloadCallback) r1, (Continuation) r2);
    }
}
