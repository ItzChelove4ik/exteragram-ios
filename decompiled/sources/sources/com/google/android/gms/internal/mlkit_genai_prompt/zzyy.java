package com.google.android.gms.internal.mlkit_genai_prompt;

import kotlinx.coroutines.Job;

/* loaded from: classes4.dex */
final /* synthetic */ class zzyy implements Runnable {
    private final /* synthetic */ Job zza;

    public /* synthetic */ zzyy(Job r1) {
        this.zza = r1;
    }

    @Override // java.lang.Runnable
    public final /* synthetic */ void run() {
        zzyt r0 = zzzy.zza;
        Job.cancel$default(this.zza, null, 1, null);
    }
}
