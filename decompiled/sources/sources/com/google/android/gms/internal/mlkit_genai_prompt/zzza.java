package com.google.android.gms.internal.mlkit_genai_prompt;

import androidx.core.content.ContextCompat;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import kotlin.jvm.functions.Function0;

/* loaded from: classes4.dex */
final /* synthetic */ class zzza implements Function0 {
    static final /* synthetic */ zzza zza = null;

    static {
        zza = new zzza();
    }

    private /* synthetic */ zzza() {
    }

    @Override // kotlin.jvm.functions.Function0
    public final /* synthetic */ Object invoke() {
        zzyt r0 = zzzy.zza;
        return ContextCompat.getMainExecutor(MlKitContext.getInstance().getApplicationContext());
    }
}
