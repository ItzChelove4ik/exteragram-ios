package com.google.android.gms.internal.mlkit_language_id_common;

import com.google.mlkit.common.sdkinternal.SharedPrefManager;
import java.util.concurrent.Callable;

/* loaded from: classes4.dex */
public final /* synthetic */ class zzlf implements Callable {
    public final /* synthetic */ SharedPrefManager zza;

    public /* synthetic */ zzlf(SharedPrefManager r1) {
        this.zza = r1;
    }

    @Override // java.util.concurrent.Callable
    public final Object call() {
        return this.zza.getMlSdkInstanceId();
    }
}
