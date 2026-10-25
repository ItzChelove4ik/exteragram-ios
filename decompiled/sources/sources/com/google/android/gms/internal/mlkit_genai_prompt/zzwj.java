package com.google.android.gms.internal.mlkit_genai_prompt;

import com.google.common.util.concurrent.ListenableFuture;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* loaded from: classes4.dex */
public final class zzwj implements Function1 {
    final /* synthetic */ ListenableFuture zza;

    public zzwj(ListenableFuture r1) {
        this.zza = r1;
    }

    @Override // kotlin.jvm.functions.Function1
    public final /* bridge */ /* synthetic */ Object invoke(Object r1) {
        Throwable r12 = (Throwable) r1;
        this.zza.cancel(false);
        return Unit.INSTANCE;
    }
}
