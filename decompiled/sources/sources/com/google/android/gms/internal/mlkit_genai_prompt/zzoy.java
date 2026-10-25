package com.google.android.gms.internal.mlkit_genai_prompt;

import java.util.concurrent.locks.AbstractOwnableSynchronizer;

/* loaded from: classes4.dex */
final class zzoy extends AbstractOwnableSynchronizer implements Runnable {
    private final zzpa zza;

    public /* synthetic */ zzoy(zzpa r1, byte[] r2) {
        this.zza = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
    }

    public final String toString() {
        return this.zza.toString();
    }

    public final /* synthetic */ void zza(Thread r1) {
        super.setExclusiveOwnerThread(r1);
    }
}
