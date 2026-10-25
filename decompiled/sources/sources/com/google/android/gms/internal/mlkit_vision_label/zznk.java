package com.google.android.gms.internal.mlkit_vision_label;

import com.google.mlkit.common.sdkinternal.SharedPrefManager;
import java.util.concurrent.Callable;

/* loaded from: classes4.dex */
public final /* synthetic */ class zznk implements Callable {
    public final /* synthetic */ SharedPrefManager zza;

    public /* synthetic */ zznk(SharedPrefManager r1) {
        this.zza = r1;
    }

    @Override // java.util.concurrent.Callable
    public final Object call() {
        return this.zza.getMlSdkInstanceId();
    }
}
