package com.google.mlkit.genai.prompt.java;

import kotlinx.coroutines.Job;

/* loaded from: classes5.dex */
final /* synthetic */ class zzz implements Runnable {
    private final /* synthetic */ Job zza;

    public /* synthetic */ zzz(Job r1) {
        this.zza = r1;
    }

    @Override // java.lang.Runnable
    public final /* synthetic */ void run() {
        Job.cancel$default(this.zza, null, 1, null);
    }
}
