package com.google.android.gms.internal.mlkit_genai_prompt;

import com.google.mlkit.common.sdkinternal.SharedPrefManager;
import java.util.concurrent.Callable;

/* loaded from: classes4.dex */
final /* synthetic */ class zzavb implements Callable {
    private final /* synthetic */ SharedPrefManager zza;

    public /* synthetic */ zzavb(SharedPrefManager r1) {
        this.zza = r1;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ Object call() {
        return this.zza.getMlSdkInstanceId();
    }
}
