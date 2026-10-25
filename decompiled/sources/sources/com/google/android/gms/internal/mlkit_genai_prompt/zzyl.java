package com.google.android.gms.internal.mlkit_genai_prompt;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.Job;

/* loaded from: classes4.dex */
final /* synthetic */ class zzyl implements Function0 {
    private final /* synthetic */ Job zza;

    public /* synthetic */ zzyl(Job r1) {
        this.zza = r1;
    }

    @Override // kotlin.jvm.functions.Function0
    public final /* synthetic */ Object invoke() {
        Job.cancel$default(this.zza, null, 1, null);
        return Unit.INSTANCE;
    }
}
